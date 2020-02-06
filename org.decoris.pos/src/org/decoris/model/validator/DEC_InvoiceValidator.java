package org.decoris.model.validator;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.MDecorisPOS;
import org.decoris.model.X_C_Decoris_PostingMethod;
import org.osgi.service.event.Event;

/**
 * 
 * @author Tegar N
 *
 */

public class DEC_InvoiceValidator {

	public static CLogger log = CLogger.getCLogger(DEC_InvoiceValidator.class);
	
	public static String executeInvoiceEvent(Event event, PO po) {
		String msgInv= "";
		MInvoice inv = (MInvoice) po;
		if (event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE)) {
			if (inv.isSOTrx()) {
				msgInv = invoiceBeforeSave(inv);
			}
		}else if (event.getTopic().equals(IEventTopics.DOC_AFTER_REVERSECORRECT)||
				event.getTopic().equals(IEventTopics.DOC_AFTER_REVERSEACCRUAL)){
			
			if(!inv.isSOTrx()){
				
				msgInv = invoiceBeforeReverse(inv);
			}
			
		}else if (event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE)) {
			
			if(inv.isSOTrx()){
				msgInv = afterComplete(inv);
			} 
		}
			
		return msgInv;

	}
	
	
	public static String invoiceBeforeSave(MInvoice Inv){
				
		int C_Order_ID = Inv.getC_Order_ID();
		MOrder ord = new MOrder(Inv.getCtx(), C_Order_ID, Inv.get_TrxName());
		
		int AD_User_ID = Env.getAD_User_ID(Inv.getCtx());
		boolean isManualDocNo = false; 

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		int CreatedByPOS_ID = DB.getSQLValueEx(Inv.get_TrxName(), sqlKasir.toString(),new Object[] { Inv.getAD_Client_ID(), AD_User_ID });
		
		if(CreatedByPOS_ID >0){
			StringBuilder SQLIsManualDoc = new StringBuilder();
			
			SQLIsManualDoc.append("SELECT IsManualDocumentNo");
			SQLIsManualDoc.append(" FROM C_Pos ");
			SQLIsManualDoc.append(" WHERE AD_Client_ID = ? ");
			SQLIsManualDoc.append(" AND CreatedByPOS_ID = ?");
			
			String isManualDoc = DB.getSQLValueStringEx(Inv.get_TrxName(), SQLIsManualDoc.toString(), new Object[]{Inv.getAD_Client_ID(),CreatedByPOS_ID});
			
			if (isManualDoc == null){
				isManualDocNo = false;
			}else if(isManualDoc.toUpperCase().equals("Y")){
				isManualDocNo = true;
			}	
			
			if(isManualDocNo){
				
				Inv.setDocumentNo(ord.getDocumentNo());
				
			}
		}
		
		
		//Set Invoice to Decoris POS Window
		StringBuilder getPOSWindow = new StringBuilder();
		
		getPOSWindow.append("SELECT C_DecorisPOS_ID");
		getPOSWindow.append(" FROM C_DecorisPOS");
		getPOSWindow.append(" WHERE AD_Client_ID = "+ Env.getAD_Client_ID(Env.getCtx()));
		getPOSWindow.append(" AND C_Order_ID = " + Inv.getC_Order_ID());

		Integer DecorisPOS_ID= DB.getSQLValueEx(Inv.get_TrxName(), getPOSWindow.toString());
		
		if(DecorisPOS_ID > 0){
			
			MDecorisPOS decpos = new MDecorisPOS(Inv.getCtx(), DecorisPOS_ID, Inv.get_TrxName());
			decpos.setC_Invoice_ID(Inv.getC_Invoice_ID());
			decpos.saveEx();
			
		}
		
		return "";
		
	}
	
	public static String invoiceBeforeReverse(MInvoice Inv){
		
		int AD_User_ID = Env.getAD_User_ID(Inv.getCtx());
		boolean isManualDocumentNo = false; 

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		int CreatedByPOS_ID = DB.getSQLValueEx(Inv.get_TrxName(), sqlKasir.toString(),new Object[] { Inv.getAD_Client_ID(), AD_User_ID });

		if(CreatedByPOS_ID > 0){
			
			String manualDocumentNoSql = "SELECT IsManualDocumentNo FROM C_Pos WHERE AD_Client_ID = ? AND  CreatedByPOS_ID = ? ";
			String isManualDoc  = DB.getSQLValueStringEx(Inv.get_TrxName(),manualDocumentNoSql,new Object[] { Inv.getAD_Client_ID(), CreatedByPOS_ID });
			if (isManualDoc.toUpperCase().equals("Y")){
				isManualDocumentNo = true;
			}else{
				isManualDocumentNo = false;
			}
		}
			
		if(isManualDocumentNo){
			
			Inv.setDocumentNo(Inv.getDocumentNo()+"^");
			Inv.saveEx();
			
		}
	
		return "";
	}
	
	public static String invoiceVendorBeforeComplete (MInvoice inv){
				
		int C_Decoris_PostingMethod_ID = inv.get_ValueAsInt("C_Decoris_PostingMethod_ID");
				
		if(C_Decoris_PostingMethod_ID > 0){
			int C_BankAccount_ID =0;
			MOrder ord = null;
			
			X_C_Decoris_PostingMethod post = new X_C_Decoris_PostingMethod(inv.getCtx(), C_Decoris_PostingMethod_ID, inv.get_TrxName());
			C_BankAccount_ID = post.getC_BankAccount_ID();
			
			if(C_BankAccount_ID <= 0){
				return "Bank Pembayaran Belum Ditentukan pada Posting Method";
			}
			
			if(inv.getC_Order_ID() > 0){
				ord = new MOrder(inv.getCtx(), inv.getC_Order_ID(), inv.get_TrxName());
			}
			
			boolean isCompletePay = post.isCompleteDocStatusPay();

			String DocActPay = "";
			
			if (isCompletePay){
				DocActPay = "CO";
			}else if(!isCompletePay){
				DocActPay = "PR";
			}
					
			//Generate AP Payment
			MPayment payment = new MPayment(inv.getCtx(), 0, inv.get_TrxName());
				
			payment.setClientOrg(inv.getAD_Client_ID(), inv.getAD_Org_ID());
			payment.setIsReceipt(false);
			payment.setC_DocType_ID(post.getDocumentTypePayment_ID());
			payment.setC_BankAccount_ID(post.getC_BankAccount_ID());
			payment.setDateTrx(inv.getDateInvoiced());
			payment.setDateAcct(inv.getDateAcct());
			payment.setC_Invoice_ID(inv.getC_Invoice_ID());
			payment.setC_BPartner_ID(inv.getC_BPartner_ID());
			payment.setPayAmt(inv.getGrandTotal());
			payment.setC_Currency_ID(ord.getC_Currency_ID());
			payment.setTenderType(ord.getPaymentRule());
			payment.setDocAction(DocActPay);
			payment.setIsPrepayment(false);					
			payment.setDescription(inv.getDescription());
			payment.saveEx();
			
			if(payment != null){
				payment.processIt(DocActPay);
				payment.saveEx();
			}
		}
				
		return "";
	}
	
public static String afterComplete(MInvoice inv){
		
		//Set Invoice DocNo at AR Receipt
			
		StringBuilder SQLGetPay = new StringBuilder();
		SQLGetPay.append("SELECT C_Payment_ID ");
		SQLGetPay.append("FROM C_POSPayment ");
		SQLGetPay.append("WHERE AD_Client_ID = ?");
		SQLGetPay.append("AND C_Order_ID = ? ");

		int C_Payment_ID = DB.getSQLValueEx(null, SQLGetPay.toString(), new Object[]{inv.getAD_Client_ID(),inv.getC_Order_ID()});
				
		int C_Invoice_ID = inv.getC_Invoice_ID();

		if(C_Payment_ID > 0){
			
			MPayment pay = new MPayment(inv.getCtx(), C_Payment_ID, inv.get_TrxName());
			pay.setC_Invoice_ID(C_Invoice_ID);
			pay.saveEx();
				
		}
		return "";					
		
	}
}

