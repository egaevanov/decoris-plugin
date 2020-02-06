package org.decoris.pos.webui.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPayment;
import org.compiere.model.MTaxCategory;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.MDecorisPOS;
import org.decoris.model.X_C_DecorisPOSLine;

public class PosTarikTunai {
	
	public CLogger log = CLogger.getCLogger(PosMainWindow.class);
	private String m_docAction = "CO";

	
	protected int cekKasir(Properties ctx , int AD_User_ID){
		int CreatedByPOS_ID = 0;
		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		CreatedByPOS_ID = DB.getSQLValueEx(ctx.toString(), sqlKasir.toString(),new Object[] { Env.getAD_Client_ID(ctx), AD_User_ID });
		
		return CreatedByPOS_ID;
	}
	
	protected int getLocationBP(Properties ctx,int C_BPartner_ID){
		
		int rsLoc_ID = 0;
		
		if(C_BPartner_ID > 0){
						
			StringBuilder sqlBPLoc = new StringBuilder();
			sqlBPLoc.append("SELECT C_BPartner_Location_ID ");
			sqlBPLoc.append("FROM C_BPartner_Location ");
			sqlBPLoc.append("WHERE C_BPartner_ID = ? ");
			
			rsLoc_ID = DB.getSQLValueEx(ctx.toString(),sqlBPLoc.toString(), new Object[] { C_BPartner_ID });	
		}
		
		
		return rsLoc_ID;
		
	}
	
	protected int getCurrency_ID(Properties ctx){
		
		int rs = 0;
		
		StringBuilder SQLGetCurrency = new StringBuilder();
		SQLGetCurrency.append(" SELECT description::numeric ");
		SQLGetCurrency.append(" FROM ad_param ");
		SQLGetCurrency.append(" WHERE value = 'Tarik_Tunai_Currency' ");
		
		rs = DB.getSQLValueEx(ctx.toString(),SQLGetCurrency.toString());	

		return rs;
	}
	

	protected int getPaymentTerm(Properties ctx, int AD_Client_ID){
		int rs = 0;
		
		StringBuilder SQLGetPaymentTerm = new StringBuilder();
		SQLGetPaymentTerm.append(" SELECT description::numeric ");
		SQLGetPaymentTerm.append(" FROM ad_param ");
		SQLGetPaymentTerm.append(" WHERE value = 'Tarik_Tunai_PaymentTerm' ");
		SQLGetPaymentTerm.append(" AND AD_Client_ID = ? ");
		
		rs = DB.getSQLValueEx(ctx.toString(),SQLGetPaymentTerm.toString(), new Object[] { AD_Client_ID });	

		return rs;

	}
	
	protected int getPriceList_ID(Properties ctx, int AD_Client_ID, String TypeInv){
		int rs = 0;
		StringBuilder SQLPriceList = new StringBuilder();
		SQLPriceList.append(" SELECT description::numeric ");
		SQLPriceList.append(" FROM ad_param ");
		if(TypeInv.equals("AR")){
			SQLPriceList.append(" WHERE value = 'Tarik_Tunai_PriceListIn' ");
		}else if(TypeInv.equals("AP")){
			SQLPriceList.append(" WHERE value = 'Tarik_Tunai_PriceListOut' ");
		}
		SQLPriceList.append(" AND AD_Client_ID = ? ");
		
		rs = DB.getSQLValueEx(ctx.toString(),SQLPriceList.toString(), new Object[] { AD_Client_ID });	

		
		return rs;

	}
	
	
	
	protected int getPayDocType(Properties ctx, int AD_Client_ID,String DocType){
		
		int rsDocType_ID = 0;
		
		
		StringBuilder SQLGetDocType = new StringBuilder();
		SQLGetDocType.append("SELECT C_DocType_ID ");
		SQLGetDocType.append(" FROM C_DocType ");
		SQLGetDocType.append(" WHERE AD_Client_ID = ? ");
		SQLGetDocType.append(" AND DocBaseType = ? ");

		rsDocType_ID = DB.getSQLValueEx(null, SQLGetDocType.toString(), new Object[]{AD_Client_ID,DocType});
		
		return rsDocType_ID;
		
	}
	
	protected int getInvDocType(Properties ctx, int AD_Client_ID,String DocType){
		
		int rsDocType_ID = 0;
		
		String invDocBaseType = "";
		
		if(DocType.equals("AR")){
			invDocBaseType = MDocType.DOCBASETYPE_ARInvoice;		
		}else if(DocType.equals("AP")){
			invDocBaseType = MDocType.DOCBASETYPE_APInvoice;
		}
		
		StringBuilder sqlInvDocType = new StringBuilder();
		sqlInvDocType.append("SELECT C_DocType_ID");
		sqlInvDocType.append(" FROM C_DocType");
		sqlInvDocType.append(" WHERE AD_Client_ID = ?");
		sqlInvDocType.append(" AND DocBaseType = ?");

		rsDocType_ID = DB.getSQLValueEx(ctx.toString(), sqlInvDocType.toString(), new Object[]{AD_Client_ID,invDocBaseType});
		
		return rsDocType_ID;
		
	}
	
	protected int getPOSDocType(Properties ctx, int AD_Client_ID, String DocType){
		
		int rsDocTypePOS_ID = 0;
		
		StringBuilder SQLGetDocTypePOS = new StringBuilder();


		SQLGetDocTypePOS.append(" SELECT C_DocType_ID ");
		SQLGetDocTypePOS.append(" FROM C_DocType  ");
		SQLGetDocTypePOS.append(" WHERE AD_Client_ID = ? ");
		SQLGetDocTypePOS.append(" AND DocBaseType = ? ");
		
		rsDocTypePOS_ID = DB.getSQLValueEx(null, SQLGetDocTypePOS.toString(), new Object[]{AD_Client_ID,DocType});
		
		
		return rsDocTypePOS_ID;
		
	}
	
	protected int getTax_ID(Properties ctx, int C_Charge_ID){
		int rsTax_ID = 0;
		
		MCharge ch = new MCharge(ctx, C_Charge_ID, null);
		int taxCategory = ch.getC_TaxCategory_ID();
		
		MTaxCategory cat = new MTaxCategory(ctx, taxCategory, null);
		String taxCatName = cat.getName();
		
		String sqlTax = "SELECT C_Tax_ID FROM C_Tax WHERE AD_Client_ID = ? AND Name = '"+ taxCatName +"'"; 
		rsTax_ID = DB.getSQLValueEx(cat.get_TrxName(), sqlTax, new Object[]{ch.getAD_Client_ID()});
		
		return rsTax_ID;
		
		
	}
	
	
	protected void createInvoicePayment(Properties ctx, int C_DecorisPOS_ID, String InvType, int C_BankAccount_ID){
		
		if(C_DecorisPOS_ID > 0){
			
			MDecorisPOS decPos = new MDecorisPOS(ctx, C_DecorisPOS_ID, null);
			MInvoice inv = new MInvoice(ctx, 0, null);
			int C_DocType_ID = 0;
			int C_DocTypeAR_ID = 0;
			int C_PaymentTerm_ID = getPaymentTerm(ctx, decPos.getAD_Client_ID());
			int C_PriceList_ID = 0;
			int C_Currency_ID = getCurrency_ID(ctx);
			String TenderType = "";
			
			inv.setClientOrg(decPos.getAD_Client_ID(),decPos.getAD_Org_ID());
			
			if(InvType.equals("AR")){
				C_DocType_ID = getInvDocType(ctx, decPos.getAD_Client_ID(), "AR");
				C_DocTypeAR_ID = getPayDocType(ctx, inv.getAD_Client_ID(), MDocType.DOCBASETYPE_ARReceipt);
				C_PriceList_ID = getPriceList_ID(ctx, decPos.getAD_Client_ID(),"AR");
				TenderType = MPayment.TENDERTYPE_DirectDebit;


			}else if(InvType.equals("AP")){
				C_DocType_ID = getInvDocType(ctx, decPos.getAD_Client_ID(), "AP");
				C_DocTypeAR_ID = getPayDocType(ctx, inv.getAD_Client_ID(), MDocType.DOCBASETYPE_APPayment);
				C_PriceList_ID = getPriceList_ID(ctx, decPos.getAD_Client_ID(),"AP");
				TenderType = MPayment.TENDERTYPE_Cash;


			}
			inv.setC_DocType_ID(C_DocType_ID);
			inv.setDescription(decPos.getDescription());
			inv.setDateAcct(decPos.getDateOrdered());
			inv.setDateInvoiced(decPos.getDateOrdered());
			inv.setC_BPartner_ID(decPos.getC_BPartner_ID());
			inv.setC_BPartner_Location_ID(decPos.getC_BPartner_Location_ID());
			inv.setC_PaymentTerm_ID(C_PaymentTerm_ID);
			inv.setPaymentRule(decPos.getPaymentRule());
			inv.setTotalLines(decPos.getTotalLines());
			inv.setGrandTotal(decPos.getGrandTotal());
			inv.setM_PriceList_ID(C_PriceList_ID);
			inv.setC_Currency_ID(C_Currency_ID);
			inv.setIsSOTrx(decPos.isSOTrx());
			inv.saveEx();
			
			decPos.setC_Invoice_ID(inv.getC_Invoice_ID());
			decPos.saveEx();
			
			
			X_C_DecorisPOSLine DecLines[] = decPos.getLines();
			
			for (X_C_DecorisPOSLine line : DecLines){
				MInvoiceLine invLine = new MInvoiceLine(ctx, 0, null);

				invLine.setClientOrg(inv);
				invLine.setC_Invoice_ID(inv.getC_Invoice_ID());
				invLine.setC_Charge_ID(line.getC_Charge_ID());
				invLine.setQty(Env.ONE);
				invLine.setQtyEntered(Env.ONE);
				invLine.setQtyInvoiced(Env.ONE);
				invLine.setPrice(line.getPriceEntered());
				invLine.setPriceEntered(line.getPriceEntered());
				invLine.setLineNetAmt(line.getPriceEntered());
				invLine.setC_Tax_ID(line.getC_Tax_ID());
				invLine.saveEx();
				
				line.setC_InvoiceLine_ID(invLine.getC_InvoiceLine_ID());
				line.saveEx();
			}
		
			if (inv != null)
			{
				if (m_docAction != null && m_docAction.length() > 0)
				{
					inv.setDocAction(m_docAction);
					if(!inv.processIt (m_docAction)) {
						log.warning("Invoice Process Failed: " + inv + " - " + inv.getProcessMsg());
						throw new IllegalStateException("Invoice Process Failed: " + inv + " - " + inv.getProcessMsg());
					}
				}
				inv.saveEx();
//				msgInv = infoGeneratedDocumentInvoice(inv.getC_Invoice_ID(), windowNo);

			}
			
			if (inv.getDocStatus().equals(MInvoice.DOCSTATUS_Completed)){
								
				MPayment pay = new MPayment(ctx, 0, null);
				pay.setClientOrg(decPos.getAD_Client_ID(),decPos.getAD_Org_ID());
				pay.setC_DocType_ID(C_DocTypeAR_ID);
				pay.setIsReceipt(decPos.isReceipt());
				pay.setC_BPartner_ID(decPos.getC_BPartner_ID());
				pay.setDescription(decPos.getDescription());
				pay.setDateTrx(decPos.getDateOrdered());
				pay.setDateAcct(decPos.getDateOrdered());
				if(decPos.isSOTrx()){
					pay.setC_BankAccount_ID(C_BankAccount_ID);
				}else if(!decPos.isSOTrx()){
					String sqlBank = "SELECT C_BankAccount_ID FROM C_POS WHERE CreatedByPOS_ID = ? AND AD_Client_ID = ?"; 
					int m_C_BankAccount_ID_Tunai = DB.getSQLValueEx(ctx.toString(), sqlBank.toString(), new Object[]{decPos.getCreatedByPOS_ID(),decPos.getAD_Client_ID()});;
					pay.setC_BankAccount_ID(m_C_BankAccount_ID_Tunai);
					
				}
				pay.setTenderType(TenderType);
				pay.setPayAmt(decPos.getGrandTotal());
				pay.setC_Currency_ID(303);
				pay.set_CustomColumn("CreatedByPOS_ID", decPos.getCreatedByPOS_ID());
				pay.setC_Invoice_ID(inv.getC_Invoice_ID());
				pay.saveEx();
					
				decPos.setC_Payment_ID(pay.getC_Payment_ID());
				decPos.saveEx();
					
				if (pay != null)
					{
						
						if (m_docAction != null && m_docAction.length() > 0)
						{
							pay.setDocAction(m_docAction);
							if(!pay.processIt (m_docAction)) {
								log.warning("Invoice Process Failed: " + pay + " - " + inv.getProcessMsg());
								throw new IllegalStateException("Invoice Process Failed: " + pay + " - " + pay.getProcessMsg());
							}
						}
						pay.saveEx();
						//TODO
						//C_PaymentPrint_ID = pay.getC_Payment_ID();
						//msgPay = msgPay+ infoGeneratedDocumentPayment(pay.getC_Payment_ID(), IsSoTrx, windowNo);
						
					}
				
			}
			
			
			
		}
	
	}
	
	 protected int updateData(int C_DecorisPOS_ID){
		 
			StringBuilder SQLExFunction = new StringBuilder();
	        SQLExFunction.append("SELECT update_print_info_pos(?)");
	        int rslt = 0;
	         
	     	PreparedStatement pstmt = null;
	     	ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLExFunction.toString(), null);
					pstmt.setInt(1, C_DecorisPOS_ID);	
					rs = pstmt.executeQuery();
					while (rs.next()) {
						rslt = rs.getInt(1);
					}

				} catch (SQLException err) {
					log.log(Level.SEVERE, SQLExFunction.toString(), err);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
				
				
			 return rslt;
		 }
	
	
}
