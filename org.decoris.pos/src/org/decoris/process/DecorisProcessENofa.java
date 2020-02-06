package org.decoris.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MYear;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.decoris.model.X_C_Decoris_ENofa;
import org.decoris.model.X_C_Decoris_ENofaLine;

/**
 * 
 * @author Tegar N
 *
 */

public class DecorisProcessENofa extends SvrProcess{

	private int p_AD_Client_ID = 0;
	private int p_AD_Org_ID = 0;
	private int p_C_Decoris_ENofa_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0 ; i < para.length ;i++){
			
			String name = para[i].getParameterName();
			
			if(para[i].getParameter()==null)
				;
			else if(name.equals("AD_Client_ID"))
				p_AD_Client_ID = (int)para[i].getParameterAsInt();
			
			else if(name.equals("AD_Org_ID"))
				p_AD_Org_ID = (int)para[i].getParameterAsInt();
	
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			
		}
		//get ENOfa_ID
		p_C_Decoris_ENofa_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		String msg = "";
		Integer count = 0;
		Integer lineNo = 10;

		if(p_C_Decoris_ENofa_ID == 0)
			;
		
		X_C_Decoris_ENofa ENofa = new X_C_Decoris_ENofa(getCtx(), p_C_Decoris_ENofa_ID, get_TrxName());
		
		//get Tax 
		StringBuilder SQLGetPPN = new StringBuilder();
		SQLGetPPN.append("SELECT C_Tax_ID ");
		SQLGetPPN.append("FROM C_Tax ");
		SQLGetPPN.append("WHERE AD_Client_ID = ? ");
		SQLGetPPN.append("AND Name = 'PPN' ");
		SQLGetPPN.append("AND IsActive = 'Y' ");

		int C_Tax_ID = DB.getSQLValueEx(get_TrxName(), SQLGetPPN.toString(), p_AD_Client_ID);
		
		if(C_Tax_ID == 0)
			;
		
		//get year p_paramenter
		int C_Year_ID = ENofa.getC_Year_ID();
		MYear year = new MYear(getCtx(), C_Year_ID, get_TrxName());
		String yearName = year.getFiscalYear();

		//get month date
		Timestamp startDate = ENofa.getStartDate();

		//End Date
		Timestamp endDate = Timestamp.valueOf(yearName+"-12-31 00:00:00.0");


		//get Invoice Data
		StringBuilder SQLGetInvoiceData = new StringBuilder();
		SQLGetInvoiceData.append("SELECT DISTINCT (ci.C_Invoice_ID)");
		SQLGetInvoiceData.append("FROM C_Invoice ci ");
		SQLGetInvoiceData.append("INNER JOIN C_InvoiceLine cil ON cil.C_Invoice_ID = ci.C_Invoice_ID ");
		SQLGetInvoiceData.append("WHERE ci.AD_Client_ID = ? ");
		SQLGetInvoiceData.append("AND ci.AD_Org_ID = ? ");
		SQLGetInvoiceData.append("AND ci.C_DocType_ID = ? ");
		SQLGetInvoiceData.append("AND (ci.dateinvoiced BETWEEN '"+startDate+"'  AND '"+endDate+"')");
		SQLGetInvoiceData.append("AND ci.DocStatus = 'CO' ");
		SQLGetInvoiceData.append("AND ci.IsGeneratedENofa = 'N' ");
		SQLGetInvoiceData.append("AND cil.C_Tax_ID = ? ");


		PreparedStatement pstmtENofaLine = null;
		ResultSet rsENofaLine = null;
			
		try {
			pstmtENofaLine = DB.prepareStatement(SQLGetInvoiceData.toString(), get_TrxName());
			pstmtENofaLine.setInt(1, p_AD_Client_ID);
			pstmtENofaLine.setInt(2, p_AD_Org_ID);
			pstmtENofaLine.setInt(3, ENofa.getC_DocType_ID());
			pstmtENofaLine.setInt(4, C_Tax_ID);

			rsENofaLine = pstmtENofaLine.executeQuery();
			while (rsENofaLine.next()) {
				MInvoice inv = new MInvoice(getCtx(), rsENofaLine.getInt(1), get_TrxName());
				MBPartner bp = new MBPartner(getCtx(), inv.getC_BPartner_ID() , get_TrxName());

				X_C_Decoris_ENofaLine newENofaLine = new X_C_Decoris_ENofaLine(getCtx(), 0, get_TrxName());
				lineNo = lineNo +10;	
				//create ENofa Detail (from Invoice data)
				newENofaLine.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
				newENofaLine.setC_Decoris_ENofa_ID(ENofa.getC_Decoris_ENofa_ID());
				newENofaLine.setC_Invoice_ID(inv.getC_Invoice_ID());
				newENofaLine.setC_BPartner_ID(inv.getC_BPartner_ID());
				newENofaLine.setNomorPajak(ENofa.getNoSeri()+"-"+ENofa.getEnofaCounter());
				newENofaLine.setTaxID(bp.getTaxID());
				newENofaLine.setLineNo(lineNo);
				newENofaLine.saveEx();
				
				count++;
				System.out.println(count);

				//update available ENofa Counter
				ENofa.setEnofaCounter(ENofa.getEnofaCounter()+1);
				ENofa.saveEx();
				
				//update invoice (ENofa generated flag )
				inv.set_ValueNoCheck("IsGeneratedENofa", true);
				inv.saveEx();
							
				//Update invoice Tax Data
				StringBuilder UpdateTax = new StringBuilder();
				UpdateTax.append("UPDATE C_InvoiceTax ");
				UpdateTax.append("SET TaxID = '"+ bp.getTaxID()+"', ");
				UpdateTax.append("TaxNbr = '"+newENofaLine.getNomorPajak()+"' ");
				UpdateTax.append("WHERE AD_Client_ID = " +p_AD_Client_ID);
				UpdateTax.append("AND AD_Org_ID = " + p_AD_Org_ID );
				UpdateTax.append("AND C_Invoice_ID = " + inv.getC_Invoice_ID());
				UpdateTax.append("AND C_Tax_ID = " + C_Tax_ID);
				DB.executeUpdate(UpdateTax.toString(), get_TrxName());				
			}
			//update sisa ENofa
			ENofa.setSisaEnofa(ENofa.getSisaEnofa()-count);
			ENofa.saveEx();
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, SQLGetInvoiceData.toString(), e);
			DB.rollback(true, get_TrxName());
			msg = "Error";
		} finally {
			DB.close(rsENofaLine, pstmtENofaLine);
			rsENofaLine = null;
			pstmtENofaLine = null;
		}
			
		return msg;
	}

}
