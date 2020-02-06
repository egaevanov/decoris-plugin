 package org.decoris.model.validator;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.decoris.model.X_C_Decoris_PostingMethod;
import org.osgi.service.event.Event;

/**
 * 
 * @author Tegar N
 *
 */

public class DEC_MInOutValidator {

	public static CLogger log = CLogger.getCLogger(DEC_MInOutValidator.class);

	
	
	public static String executeOrderEvent(Event event, PO po) {
		String msgInOut = "";
		MInOut InOut = (MInOut) po;
		if (event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE)) {
			if (!InOut.isSOTrx() && InOut.getMovementType().equals("V+")) {
				msgInOut = beforeComplete(InOut);
			} 
		
		}
		else if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE)){
			if (!InOut.isSOTrx() && InOut.getMovementType().equals("V+")) {
				msgInOut = afterComplete(InOut);
			} else if (InOut.isSOTrx() && InOut.getMovementType().equals("C-")) {
				msgInOut = ShipmentBeforeComplete(InOut);
			} 
			
		}
		
		
		return msgInOut;

	}

	
	
	
	public static String beforeComplete(MInOut InOut){
		boolean IsAutoGenerate = false;
		Integer C_Decoris_PostingMethod_ID = 0;
		
		C_Decoris_PostingMethod_ID = InOut.get_ValueAsInt("C_Decoris_PostingMethod_ID");
		
		if(C_Decoris_PostingMethod_ID == null){
			C_Decoris_PostingMethod_ID = 0;
		}
		
		if(C_Decoris_PostingMethod_ID > 0){
			IsAutoGenerate = true;
		}
		
		if(IsAutoGenerate){
			
			X_C_Decoris_PostingMethod post = new X_C_Decoris_PostingMethod(InOut.getCtx(), C_Decoris_PostingMethod_ID, InOut.get_TrxName());
			
			MOrder ord = new MOrder(InOut.getCtx(), 0, InOut.get_TrxName());
			
			MInOutLine InOutLines[] = InOut.getLines(); 
			
			boolean isCompleteOrd = post.isCompleteDocStatusPO();
			boolean isCompleteInv = post.isCompleteDocStatusInv();
			boolean isCompletePay = post.isCompleteDocStatusPay();
			
			String DocActOrd = "";
			String DocActInv = "";
			String DocActPay = "";
	
			if (isCompleteOrd){
				DocActOrd = "CO";
			}else if(!isCompleteOrd){
				DocActOrd = "DR";
			}

			if (isCompleteInv){
				DocActInv = "CO";
			}else if(!isCompleteInv){
				DocActInv = "DR";
			}
			
			if (isCompletePay){
				DocActPay = "CO";
			}else if(!isCompletePay){
				DocActPay = "DR";
			}
			
			//IsComplete Validation
			if(DocActInv.equals("DR")){
				if(DocActPay.equals("CO")){
					return "Complete Payment pada Posting Method Kurang Tepat";
				}
			}
			
			//Generate Order Header
			ord.setClientOrg(InOut.getAD_Client_ID(), InOut.getAD_Org_ID());
			ord.setIsSOTrx(false);
			ord.setC_DocTypeTarget_ID(post.getC_DocType_ID());
			ord.setC_DocType_ID(post.getC_DocType_ID());
			ord.setDateOrdered(InOut.getMovementDate());
			ord.setDatePromised(InOut.getMovementDate());
			ord.setC_BPartner_ID(InOut.getC_BPartner_ID());
			ord.setC_BPartner_Location_ID(InOut.getC_BPartner_Location_ID());
			ord.setM_Warehouse_ID(InOut.getM_Warehouse_ID());
			ord.setPriorityRule(post.getPriority());
			ord.setM_PriceList_ID(post.getM_PriceList_ID());
			ord.setC_Currency_ID(post.getC_Currency_ID());
			ord.setPaymentRule(post.getPaymentRulePO());
			ord.setC_PaymentTerm_ID(post.getC_PaymentTerm_ID());
			ord.setDocAction(DocActOrd);
			ord.setDescription(InOut.getDescription());
			ord.setDeliveryRule(InOut.getDeliveryRule());
			ord.setDeliveryViaRule(InOut.getDeliveryViaRule());
			ord.setSalesRep_ID(InOut.getSalesRep_ID());
			ord.setDescription(InOut.getDescription());
			ord.saveEx();
			
			//Generate Invoice Header
//			MInvoice inv = new MInvoice(InOut.getCtx(), 0, InOut.get_TrxName());
//			inv.setClientOrg(InOut.getAD_Client_ID(), InOut.getAD_Org_ID());
//			inv.setC_DocType_ID(post.getDocumentTypeInvoice_ID());
//			inv.setC_DocTypeTarget_ID(post.getDocumentTypeInvoice_ID());
//			inv.setDateInvoiced(InOut.getMovementDate());
//			inv.setDateAcct(InOut.getDateAcct());
//			inv.setC_BPartner_ID(InOut.getC_BPartner_ID());
//			inv.setC_BPartner_Location_ID(InOut.getC_BPartner_Location_ID());
//			inv.setM_PriceList_ID(ord.getM_PriceList_ID());
//			inv.setC_Currency_ID(ord.getC_Currency_ID());
//			inv.setPaymentRule(ord.getPaymentRule());
//			inv.setC_Payment_ID(ord.getC_PaymentTerm_ID());
//			inv.setDocAction(DocActInv);
//			inv.setC_Order_ID(ord.getC_Order_ID());
//			inv.setIsSOTrx(false);
//			inv.setDescription(ord.getDescription());
//			inv.saveEx();
			
			
			for(MInOutLine line : InOutLines){
				
				int M_Product_ID = line.getM_Product_ID();
				Integer C_Tax_ID = 0;
				int C_TaxCategory_ID = 0;
				int M_PriceList_Version_ID = 0;
				MProductPrice mPrice = null;
				
				MProduct prod = new MProduct(InOut.getCtx(), M_Product_ID, InOut.get_TrxName());
				C_TaxCategory_ID = prod.getC_TaxCategory_ID();
				
				if(C_TaxCategory_ID <= 0){
					
					return "Prod "+prod.getName()+" Belum Ditentukan Kategori Pajak";
				}
				
				//Get Tax
				StringBuilder SQLTax = new StringBuilder();		
				SQLTax.append("SELECT C_Tax_ID ");
				SQLTax.append("FROM C_Tax ");
				SQLTax.append("WHERE AD_Client_ID = ? ");
				SQLTax.append("AND C_TaxCategory_ID = ? ");	
				C_Tax_ID = DB.getSQLValueEx(InOut.get_TrxName(), SQLTax.toString(), new Object[]{InOut.getAD_Client_ID(),C_TaxCategory_ID});  
				
				//Get PriceList
				StringBuilder SQLPriceList = new StringBuilder();
				SQLPriceList.append("SELECT M_PriceList_Version_ID ");
				SQLPriceList.append("FROM M_PriceList_Version ");
				SQLPriceList.append("WHERE AD_Client_ID = ? ");
				SQLPriceList.append("AND M_PriceList_ID = ? ");
				M_PriceList_Version_ID =  DB.getSQLValueEx(InOut.get_TrxName(), SQLPriceList.toString(), new Object[]{InOut.getAD_Client_ID(),post.getM_PriceList_ID()});
	
				if(M_PriceList_Version_ID <0 ){	
					return "PriceList Tidak Ada";
				}
				
				//Get Price
				mPrice = new MProductPrice(InOut.getCtx(), M_PriceList_Version_ID, M_Product_ID, InOut.get_TrxName());
				
				//Generate OrderLine
				MOrderLine ordLine = new MOrderLine(InOut.getCtx(), 0, InOut.get_TrxName());
				ordLine.setClientOrg(ord.getAD_Client_ID(), ord.getAD_Org_ID());
				ordLine.setC_BPartner_ID(ord.getC_BPartner_ID());
				ordLine.setC_BPartner_Location_ID(ord.getC_BPartner_Location_ID());
				ordLine.setC_Order_ID(ord.getC_Order_ID());
				ordLine.setLine(line.getLine());
				ordLine.setM_Product_ID(line.getM_Product_ID());
				ordLine.setC_UOM_ID(line.getC_UOM_ID());
				ordLine.setQtyEntered(line.getQtyEntered());
				ordLine.setQtyOrdered(line.getQtyEntered());
				ordLine.setQtyDelivered(line.getQtyEntered());
				ordLine.setQtyReserved(ordLine.getQtyOrdered().subtract(ordLine.getQtyDelivered()));
				ordLine.setQtyInvoiced(line.getQtyEntered());
				ordLine.setC_Tax_ID(C_Tax_ID);
				ordLine.setPrice(mPrice.getPriceList());
				ordLine.setPriceActual(mPrice.getPriceList());
				ordLine.setPriceEntered(mPrice.getPriceList());
				ordLine.setPriceList(mPrice.getPriceList());
				ordLine.setPriceLimit(mPrice.getPriceLimit());
				ordLine.setLineNetAmt();
				ordLine.setDescription(line.getDescription());
				if(line.getM_AttributeSetInstance_ID() > 0){
					ordLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
				}
				ordLine.saveEx();
				
				//Generate InvoceLine
//				MInvoiceLine invLine = new MInvoiceLine(InOut.getCtx(), 0, InOut.get_TrxName());
//				invLine.setClientOrg(inv.getAD_Client_ID(), inv.getAD_Org_ID());
//				invLine.setC_Invoice_ID(inv.getC_Invoice_ID());
//				invLine.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
//				invLine.setM_InOutLine_ID(line.getM_InOutLine_ID());
//				invLine.setLine(line.getLine());
//				invLine.setM_Product_ID(line.getM_Product_ID());
//				invLine.setQty(line.getQtyEntered());
//				invLine.setPrice(ordLine.getPriceActual());
//				invLine.setPriceList(ordLine.getPriceList());
//				invLine.setPriceEntered(ordLine.getPriceEntered());
//				invLine.setPriceLimit(ordLine.getPriceLimit());
//				invLine.setC_Tax_ID(ordLine.getC_Tax_ID());
//				invLine.setTaxAmt();
//				invLine.setLineNetAmt();
//				invLine.setDescription(ordLine.getDescription());
//				invLine.saveEx();
				
				
				//Set Back To ReceipLine
				line.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
				line.saveEx();
				
			}
			
			if(ord != null){
				ord.processIt(DocActOrd);
				ord.saveEx();
			}
			
//			if(inv != null){
//				inv.processIt(DocActInv);
//				inv.saveEx();
//			}
			
//			StringBuilder SQLMatchInv = new StringBuilder();		
//			SQLMatchInv.append(" SELECT M_MatchInv_ID ");
//			SQLMatchInv.append(" FROM M_MatchInv ");
//			SQLMatchInv.append(" WHERE AD_Client_ID = ? ");
//			SQLMatchInv.append(" AND C_InvoiceLine_ID = ? ");
//
//			
//			MInvoiceLine invLines[] = inv.getLines();
//			ArrayList<Integer> MMatchList = new ArrayList<Integer>();
//			for(MInvoiceLine invLine : invLines){
//				
//				PreparedStatement pstmt = null;
//				ResultSet rs = null;
//				try {
//					pstmt = DB.prepareStatement(SQLMatchInv.toString(), null);
//					pstmt.setInt(1, inv.getAD_Client_ID());
//					pstmt.setInt(2, invLine.getC_InvoiceLine_ID());
//	
//					rs = pstmt.executeQuery();
//					while (rs.next()) {
//						MMatchList.add(rs.getInt(1));
//					}
//	
//				} catch (SQLException e) {
//					log.log(Level.SEVERE, SQLMatchInv.toString(), e);
//				} finally {
//					DB.close(rs, pstmt);
//					rs = null;
//					pstmt = null;
//				}
//			}
//			
//			for(int i=0 ; i < MMatchList.size();i++ ){
//				MMatchInv matchInv = new MMatchInv(inv.getCtx(), MMatchList.get(i), inv.get_TrxName());
//				matchInv.saveEx();
//			}
			
			InOut.setPOReference(ord.getDocumentNo());
			InOut.setC_Order_ID(ord.getC_Order_ID());
			//InOut.setC_Invoice_ID(inv.getC_Invoice_ID());
			InOut.saveEx();
						
			//Generate AP Payment
//			MPayment payment = new MPayment(inv.getCtx(), 0, inv.get_TrxName());
//			
//			payment.setClientOrg(InOut.getAD_Client_ID(), InOut.getAD_Org_ID());
//			payment.setIsReceipt(false);
//			payment.setC_DocType_ID(post.getDocumentTypePayment_ID());
//			payment.setC_BankAccount_ID(post.getC_BankAccount_ID());
//			payment.setDateTrx(InOut.getMovementDate());
//			payment.setDateAcct(InOut.getDateAcct());
//			payment.setC_Invoice_ID(inv.getC_Invoice_ID());
//			payment.setC_BPartner_ID(InOut.getC_BPartner_ID());
//			payment.setPayAmt(inv.getGrandTotal());
//			payment.setC_Currency_ID(ord.getC_Currency_ID());
//			payment.setTenderType(ord.getPaymentRule());
//			payment.setDocAction(DocActPay);
//			payment.setIsPrepayment(false);					
//			payment.setDescription(inv.getDescription());
//			payment.saveEx();
//		
//			if(payment != null){
//				payment.processIt(DocActPay);
//				payment.saveEx();
//			}
				
		}
		
		return "";
		
	}
	
	
	public static String afterComplete(MInOut InOut){
		boolean IsAutoGenerate = false;
		Integer C_Decoris_PostingMethod_ID = 0;
		
		C_Decoris_PostingMethod_ID = InOut.get_ValueAsInt("C_Decoris_PostingMethod_ID");
		
		if(C_Decoris_PostingMethod_ID == null){
			C_Decoris_PostingMethod_ID = 0;
		}
		
		if(C_Decoris_PostingMethod_ID > 0){
			IsAutoGenerate = true;
		}
		
		if(IsAutoGenerate){
			
			X_C_Decoris_PostingMethod post = new X_C_Decoris_PostingMethod(InOut.getCtx(), C_Decoris_PostingMethod_ID, InOut.get_TrxName());
			
			MOrder ord = new MOrder(InOut.getCtx(), InOut.getC_Order_ID(), InOut.get_TrxName());
			
			MInOutLine InOutLines[] = InOut.getLines(); 
			
			boolean isCompleteInv = post.isCompleteDocStatusInv();
			boolean isCompletePay = post.isCompleteDocStatusPay();

			String DocActInv = "";
			String DocActPay = "";

			if (isCompleteInv){
				DocActInv = "CO";
			}else if(!isCompleteInv){
				DocActInv = "PR";
			}
			
			if (isCompletePay){
				DocActPay = "CO";
			}else if(!isCompletePay){
				DocActPay = "PR";
			}
			
			//IsComplete Validation
			if(DocActInv.equals("PR")){
				if(DocActPay.equals("CO")){
					return "Complete Payment pada Posting Method Kurang Tepat";
				}
			}
		
			
			//Generate Invoice Header
			MInvoice inv = new MInvoice(InOut.getCtx(), 0, InOut.get_TrxName());
			inv.setClientOrg(InOut.getAD_Client_ID(), InOut.getAD_Org_ID());
			inv.setC_DocType_ID(post.getDocumentTypeInvoice_ID());
			inv.setC_DocTypeTarget_ID(post.getDocumentTypeInvoice_ID());
			inv.setDateInvoiced(InOut.getMovementDate());
			inv.setDateAcct(InOut.getDateAcct());
			inv.setC_BPartner_ID(InOut.getC_BPartner_ID());
			inv.setC_BPartner_Location_ID(InOut.getC_BPartner_Location_ID());
			inv.setM_PriceList_ID(ord.getM_PriceList_ID());
			inv.setC_Currency_ID(ord.getC_Currency_ID());
			inv.setPaymentRule(ord.getPaymentRule());
			inv.setC_Payment_ID(ord.getC_PaymentTerm_ID());
			inv.setDocAction(DocActInv);
			inv.setC_Order_ID(ord.getC_Order_ID());
			inv.setIsSOTrx(false);
			inv.setDescription(ord.getDescription());
			inv.saveEx();
			
			
			for(MInOutLine line : InOutLines){
				
				int M_Product_ID = line.getM_Product_ID();
				int C_TaxCategory_ID = 0;
				int M_PriceList_Version_ID = 0;
				
				MOrderLine ordLine = new MOrderLine(InOut.getCtx(), line.getC_OrderLine_ID(), InOut.get_TrxName());
				MProduct prod = new MProduct(InOut.getCtx(), M_Product_ID, InOut.get_TrxName());
				C_TaxCategory_ID = prod.getC_TaxCategory_ID();
				
				if(C_TaxCategory_ID <= 0){
					
					return "Prod "+prod.getName()+" Belum Ditentukan Kategori Pajak";
				}
				
				//Get Tax
				StringBuilder SQLTax = new StringBuilder();		
				SQLTax.append("SELECT C_Tax_ID ");
				SQLTax.append("FROM C_Tax ");
				SQLTax.append("WHERE AD_Client_ID = ? ");
				SQLTax.append("AND C_TaxCategory_ID = ? ");	
				
				//Get PriceList
				StringBuilder SQLPriceList = new StringBuilder();
				SQLPriceList.append("SELECT M_PriceList_Version_ID ");
				SQLPriceList.append("FROM M_PriceList_Version ");
				SQLPriceList.append("WHERE AD_Client_ID = ? ");
				SQLPriceList.append("AND M_PriceList_ID = ? ");
				M_PriceList_Version_ID =  DB.getSQLValueEx(InOut.get_TrxName(), SQLPriceList.toString(), new Object[]{InOut.getAD_Client_ID(),post.getM_PriceList_ID()});
	
				if(M_PriceList_Version_ID <0 ){	
					return "PriceList Tidak Ada";
				}

				//Generate InvoceLine
				MInvoiceLine invLine = new MInvoiceLine(InOut.getCtx(), 0, InOut.get_TrxName());
				invLine.setClientOrg(inv.getAD_Client_ID(), inv.getAD_Org_ID());
				invLine.setC_Invoice_ID(inv.getC_Invoice_ID());
				invLine.setC_OrderLine_ID(line.getC_OrderLine_ID());
				invLine.setM_InOutLine_ID(line.getM_InOutLine_ID());
				invLine.setLine(line.getLine());
				invLine.setM_Product_ID(line.getM_Product_ID());
				invLine.setQty(line.getQtyEntered());
				invLine.setPrice(ordLine.getPriceActual());
				invLine.setPriceList(ordLine.getPriceList());
				invLine.setPriceEntered(ordLine.getPriceEntered());
				invLine.setPriceLimit(ordLine.getPriceLimit());
				invLine.setC_Tax_ID(ordLine.getC_Tax_ID());
				invLine.setTaxAmt();
				invLine.setLineNetAmt();
				invLine.setDescription(ordLine.getDescription());
				invLine.saveEx();
				
				
			}
			
			if(inv != null){
				inv.processIt(DocActInv); 
				inv.saveEx();
			}
			
//			MInvoiceLine invLines[] = inv.getLines();
//			
//			for (MInvoiceLine lineInv : invLines){
//				MMatchInv asd = new MMatchInv(lineInv, inv.getDateAcct(), lineInv.getQtyEntered());
//				Doc.postImmediate(null, asd.get_Table_ID(), asd.getM_MatchInv_ID(), true, inv.get_TrxName());
//			}
			InOut.setC_Invoice_ID(inv.getC_Invoice_ID());
			InOut.saveEx();
						
			//Generate AP Payment
			MPayment payment = new MPayment(inv.getCtx(), 0, inv.get_TrxName());
			
			payment.setClientOrg(InOut.getAD_Client_ID(), InOut.getAD_Org_ID());
			payment.setIsReceipt(false);
			payment.setC_DocType_ID(post.getDocumentTypePayment_ID());
			payment.setC_BankAccount_ID(post.getC_BankAccount_ID());
			payment.setDateTrx(InOut.getMovementDate());
			payment.setDateAcct(InOut.getDateAcct());
			payment.setC_Invoice_ID(inv.getC_Invoice_ID());
			payment.setC_BPartner_ID(InOut.getC_BPartner_ID());
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
	
	public static String ShipmentBeforeComplete(MInOut inout){
		
		int C_Order_ID = 0;
		
		C_Order_ID = inout.getC_Order_ID();
		if(C_Order_ID > 0){
			
			MOrder ord = new MOrder(inout.getCtx(), C_Order_ID, inout.get_TrxName());
			inout.setDateAcct(ord.getDateOrdered());
			
			if(ord.getDeliveryRule().toUpperCase().equals("P")){
				inout.setMovementDate(ord.getDateOrdered());
				inout.setDateAcct(ord.getDateOrdered());
			}
			
			inout.saveEx();
			
		}
		
		return "";
	}
}
