package org.decoris.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;

/**
 *  Send Mail to Interest Area Subscribers
 *
 *  @author Jorg Janke
 *  @version $Id: SendMailText.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class DecorisSendEmail extends SvrProcess
{
	private int				m_R_MailText_ID = -1;
	private MMailText		m_MailText = null;
	private int				m_AD_User_ID = -1;
	private MClient			m_client = null;
	private MUser			m_from = null;
	private int 			m_counter = 0;
	private int 			m_errors = 0;
	private int				m_R_InterestArea_ID = -1;
	//private MInterestArea 	m_Interest = null;
	private int				m_C_order_ID = -1;
	private MOrder			order = null;


	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("R_InterestArea_ID"))
				m_R_InterestArea_ID = para[i].getParameterAsInt();
			else if (name.equals("R_MailText_ID"))
				m_R_MailText_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_User_ID"))
				m_AD_User_ID = para[i].getParameterAsInt();
			else if (name.equals("C_Order_ID"))
				m_C_order_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		if (log.isLoggable(Level.INFO)) log.info("R_MailText_ID=" + m_R_MailText_ID);

		
		
		order = new MOrder(getCtx(), m_C_order_ID, get_TrxName());
		if (order.getC_Order_ID() == 0)
			throw new Exception ("Not found @C_Order_ID@=" + m_C_order_ID);
		
		//	Mail Test
		m_MailText = new MMailText (getCtx(), m_R_MailText_ID, get_TrxName());
		if (m_MailText.getR_MailText_ID() == 0)
			throw new Exception ("Not found @R_MailText_ID@=" + m_R_MailText_ID);
		
		//	Client Info
		m_client = MClient.get (getCtx());
		if (m_client.getAD_Client_ID() == 0)
			throw new Exception ("Not found @AD_Client_ID@");
		
		// Check SMTP Host
		if (m_client.getSMTPHost() == null || m_client.getSMTPHost().length() == 0)
			throw new Exception ("No SMTP Host found");
		
		// Setup User Sender
		if (m_AD_User_ID > 0){
			
			m_from = new MUser (getCtx(), m_AD_User_ID, get_TrxName());
		
			if (m_from.getAD_User_ID() == 0)
				throw new Exception ("No found @AD_User_ID@=" + m_AD_User_ID);
	
		}
		
		
		//log
		if (log.isLoggable(Level.FINE)) log.fine("From " + m_from);
		
		
		
		
		if (m_R_InterestArea_ID > 0)
			sendInterestArea();
		
		
		//Return Info
		long start = System.currentTimeMillis();
		StringBuilder msgreturn = new StringBuilder("@Created@=").append(m_counter).append(", @Errors@=").append(m_errors).append(" - ")
				.append((System.currentTimeMillis()-start)).append("ms");
		
		return msgreturn.toString();
		
	}	//	doIt

	
	private void sendInterestArea()
	{
		if (log.isLoggable(Level.INFO)) log.info("R_InterestArea_ID=" + m_R_InterestArea_ID);
		
		
		//m_Interest = MInterestArea.get(getCtx(), m_R_InterestArea_ID);
		
		StringBuilder Receiver = new StringBuilder();
		
		
		Receiver.append("SELECT u.Name, u.EMail, u.AD_User_ID ");
		Receiver.append(" FROM R_ContactInterest ci ");
		Receiver.append(" INNER JOIN AD_User u ON (ci.AD_User_ID=u.AD_User_ID)");
		Receiver.append(" WHERE ci.AD_Client_ID = "+ Env.getAD_Client_ID(getCtx()));
		Receiver.append(" AND ci.IsActive = 'Y' ");
		Receiver.append(" AND u.IsActive = 'Y' ");
		Receiver.append(" AND ci.OptOutDate IS NULL ");
		Receiver.append(" AND u.EMail IS NOT NULL ");
		Receiver.append(" AND ci.R_InterestArea_ID= " + m_R_InterestArea_ID);
		Receiver.append(" AND u.AD_User_ID= " + Env.getAD_User_ID(getCtx()));

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(Receiver.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Boolean ok = sendIndividualMail (rs.getString(1), rs.getInt(3));
				if (ok == null)
					;
				else if (ok.booleanValue())
					m_counter++;
				else
					m_errors++;
			}
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE,Receiver.toString(), ex);
		}
		//	Clean Up
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//m_Interest = null;
	}	//	sendInterestArea
	
	
	/**
	 * 	Send Individual Mail
	 *	@param Name user name
	 *	@param AD_User_ID user
	 *	@param unsubscribe unsubscribe message
	 *	@return true if mail has been sent
	 */
	private Boolean sendIndividualMail (String Name, int AD_User_ID)
	{

		MUser to = new MUser (getCtx(), AD_User_ID, null);
		m_MailText.setUser(AD_User_ID);		//	parse context
		StringBuilder message = new StringBuilder(m_MailText.getMailText(true));
		MOrderLine[] lines = order.getLines();
		Integer lineNo = 1;
		message.append("\n");
		message.append("Hari Ini Telah Terjadi Penjualan Dengan Nomor Penjualan "+ order.getDocumentNo());
		message.append("\n");
		message.append("Dengan Rincian Sebagai Berikut :");
		for (MOrderLine line : lines ){
			MProduct prod = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
			message.append("\n");
			message.append(lineNo.toString()+ "."+ prod.getName());
			message.append("\n");
			message.append("  Harga PriceList = " + line.getPriceList().toString());
			message.append("\n");
			message.append("  Harga Penjualan = " + line.getPriceEntered().toString());
			message.append("\n");
			lineNo++;
		}
		message.append("\n");
		message.append("\n");
		message.append("\n");
		message.append("\n");
		message.append("Salam Hangat");
		message.append("\n");
		message.append("\n");
		message.append("Team Decoris Ganteng");
		
	
		
		EMail email = m_client.createEMail(m_from, to, m_MailText.getMailHeader(), message.toString());
		
		if (m_MailText.isHtml())
			email.setMessageHTML(m_MailText.getMailHeader(), message.toString());
		else
		{
			email.setSubject (m_MailText.getMailHeader());
			email.setMessageText (message.toString());
		}
		if (!email.isValid() && !email.isValid(true))
		{
			log.warning("NOT VALID - " + email);
			to.setIsActive(false);
			to.addDescription("Invalid EMail");
			to.saveEx();
			return Boolean.FALSE;
		}
		boolean OK = EMail.SENT_OK.equals(email.send());
		new MUserMail(m_MailText, AD_User_ID, email).saveEx();
		//
		if (OK) {
			if (log.isLoggable(Level.FINE)) log.fine(to.getEMail());
		} else {
			log.warning("FAILURE - " + to.getEMail());
		}
		StringBuilder msglog = new StringBuilder((OK ? "@OK@" : "@ERROR@")).append(" - ").append(to.getEMail());
		addLog(0, null, null, msglog.toString());
		return new Boolean(OK);
	}	//	sendIndividualMail

}	//	SendMailText