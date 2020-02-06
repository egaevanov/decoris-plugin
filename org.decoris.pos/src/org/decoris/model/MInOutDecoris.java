package org.decoris.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.util.DB;

/**
 * 
 * @author Tegar N
 *
 */

public class MInOutDecoris extends MInOut implements DocAction, DocOptions{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2548653573872422616L;
	
	public MInOutDecoris(MInOut original, int C_DocTypeShipment_ID,
			Timestamp movementDate) {
		super(original, C_DocTypeShipment_ID, movementDate);
		// TODO Auto-generated constructor stub
	}

	public MInOutDecoris(MInvoice invoice, int C_DocTypeShipment_ID,
			Timestamp movementDate, int M_Warehouse_ID) {
		super(invoice, C_DocTypeShipment_ID, movementDate, M_Warehouse_ID);
		// TODO Auto-generated constructor stub
	}

	public MInOutDecoris(MOrder order, int C_DocTypeShipment_ID,
			Timestamp movementDate) {
		super(order, C_DocTypeShipment_ID, movementDate);
		// TODO Auto-generated constructor stub
	}

	public MInOutDecoris(Properties ctx, int M_InOut_ID, String trxName) {
		super(ctx, M_InOut_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInOutDecoris(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}



	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		index = 0;
		if(docStatus.equals(DocAction.STATUS_Drafted)){
			//options[index++] = DocAction.ACTION_Prepare;
			//options[index++] = DocAction.ACTION_Approve;
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}else if(docStatus.equals(DocAction.STATUS_InProgress)){
			options[index++] = DocAction.ACTION_Void;
			//options[index++] = DocAction.ACTION_Approve;
			options[index++] = DocAction.ACTION_Complete;			
		}else if(docStatus.equals(DocAction.STATUS_Invalid)){
			//options[index++] = DocAction.ACTION_Prepare;
			//options[index++] = DocAction.ACTION_Approve;
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}else if(docStatus.equals(DocAction.STATUS_Approved)){
			options[index++] = DocAction.ACTION_Complete;
			options[index++] = DocAction.ACTION_Void;
		}else if(docStatus.equals(DocAction.STATUS_Completed)){
			options[index++] = DocAction.ACTION_Reverse_Correct;
			options[index++] = DocAction.ACTION_Close;			
		}else if(docStatus.equals(DocAction.STATUS_Closed)){
			options[index++] = DocAction.ACTION_None;
		}
		
		return index;
	}


	@Override
	public String completeIt()
	{
		if(!isReversal()){
			
			MInOut autogenerate = new MInOut(getCtx(), getM_InOut_ID(), get_TrxName());
			AutoGenerate(autogenerate);
			
		}
		
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
		
	}
	
	/*
	 * 
	 * @author tegar
	 * 
	 */
	public void AutoGenerate(MInOut InOut){
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
			
			X_C_Decoris_PostingMethod postInOut = new X_C_Decoris_PostingMethod(InOut.getCtx(), C_Decoris_PostingMethod_ID, InOut.get_TrxName());
			
			MOrder ord = new MOrder(InOut.getCtx(), 0, InOut.get_TrxName());
			
			MInOutLine InOutLines[] = InOut.getLines(); 
			
			boolean isInoutCompleteOrd = postInOut.isCompleteDocStatusPO();
			
			String DocActOrd = "";
	
			if (isInoutCompleteOrd){
				DocActOrd = "CO";
			}else if(!isInoutCompleteOrd){
				DocActOrd = "PR";
			}
			
			MPriceList pList = new MPriceList(getCtx(), postInOut.getM_PriceList_ID(), get_TrxName());
			
			//Generate Order Header
			ord.setClientOrg(InOut.getAD_Client_ID(), InOut.getAD_Org_ID());
			ord.setIsSOTrx(false);
			ord.setC_DocTypeTarget_ID(postInOut.getC_DocType_ID());
			ord.setC_DocType_ID(postInOut.getC_DocType_ID());
			ord.setDateOrdered(InOut.getMovementDate());
			ord.setDatePromised(InOut.getMovementDate());
			ord.setC_BPartner_ID(InOut.getC_BPartner_ID());
			ord.setC_BPartner_Location_ID(InOut.getC_BPartner_Location_ID());
			ord.setM_Warehouse_ID(InOut.getM_Warehouse_ID());
			ord.setPriorityRule(postInOut.getPriority());
			ord.setM_PriceList_ID(postInOut.getM_PriceList_ID());
			ord.setC_Currency_ID(postInOut.getC_Currency_ID());
			ord.setPaymentRule(postInOut.getPaymentRulePO());
			ord.setC_PaymentTerm_ID(postInOut.getC_PaymentTerm_ID());
			ord.setDocAction(DocActOrd);
			ord.setDescription(InOut.getDescription());
			ord.setDeliveryRule(InOut.getDeliveryRule());
			ord.setDeliveryViaRule(InOut.getDeliveryViaRule());
			ord.setSalesRep_ID(InOut.getSalesRep_ID());
			ord.setDescription(InOut.getDescription());
			ord.setIsTaxIncluded(pList.isTaxIncluded());
			ord.saveEx();
			
			for(MInOutLine line : InOutLines){
				
				int M_Product_ID = line.getM_Product_ID();
				Integer C_Tax_ID = 0;
				int C_TaxCategory_ID = 0;
				int M_PriceList_Version_ID = 0;
				MProductPrice mPrice = null;
				
				MProduct prod = new MProduct(InOut.getCtx(), M_Product_ID, InOut.get_TrxName());
				C_TaxCategory_ID = prod.getC_TaxCategory_ID();
				
				if(C_TaxCategory_ID <= 0){
					
					return;
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
				M_PriceList_Version_ID =  DB.getSQLValueEx(InOut.get_TrxName(), SQLPriceList.toString(), new Object[]{InOut.getAD_Client_ID(),postInOut.getM_PriceList_ID()});
	
				if(M_PriceList_Version_ID <0 ){	
					return;
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
					
				//Set Back To ReceipLine
				line.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
				line.saveEx();
				
			}
			
			if(ord != null){
				ord.processIt(DocActOrd);
				ord.saveEx();
			}
			
			InOut.setPOReference(ord.getDocumentNo());
			InOut.setC_Order_ID(ord.getC_Order_ID());
			InOut.saveEx();
						
		

		//Generate Invoice Header
		X_C_Decoris_PostingMethod post = new X_C_Decoris_PostingMethod(InOut.getCtx(), C_Decoris_PostingMethod_ID, InOut.get_TrxName());
		
		MOrder ord2 = new MOrder(InOut.getCtx(), InOut.getC_Order_ID(), InOut.get_TrxName());
		
		MInOutLine InOutLines2[] = InOut.getLines(); 
		
		boolean isCompleteInv = post.isCompleteDocStatusInv();
		boolean isCompletePay = post.isCompleteDocStatusPay();

		String DocActInv = "";

		if (isCompleteInv){
			DocActInv = "CO";
		}else if(!isCompleteInv){
			DocActInv = "PR";
		}
		
		MInvoice inv = new MInvoice(InOut.getCtx(), 0, InOut.get_TrxName());
		inv.setClientOrg(InOut.getAD_Client_ID(), InOut.getAD_Org_ID());
		inv.setC_DocType_ID(post.getDocumentTypeInvoice_ID());
		inv.setC_DocTypeTarget_ID(post.getDocumentTypeInvoice_ID());
		inv.setDateInvoiced(InOut.getMovementDate());
		inv.setDateAcct(InOut.getDateAcct());
		inv.setC_BPartner_ID(InOut.getC_BPartner_ID());
		inv.setC_BPartner_Location_ID(InOut.getC_BPartner_Location_ID());
		inv.setM_PriceList_ID(ord2.getM_PriceList_ID());
		inv.setC_Currency_ID(ord2.getC_Currency_ID());
		inv.setPaymentRule(ord2.getPaymentRule());
		inv.setC_PaymentTerm_ID(ord2.getC_PaymentTerm_ID());
		inv.setDocAction(DocActInv);
		inv.setC_Order_ID(ord2.getC_Order_ID());
		inv.setIsSOTrx(false);
		inv.setIsTaxIncluded(ord.isTaxIncluded());
		inv.setDescription(ord.getDescription());
		
		if(isCompleteInv&&isCompletePay){
			inv.set_ValueNoCheck("C_Decoris_PostingMethod_ID", C_Decoris_PostingMethod_ID);
		}
		inv.saveEx();
		
		InOut.setC_Invoice_ID(inv.getC_Invoice_ID());
		InOut.processIt(ACTION_Post);
		InOut.saveEx();
		
		
		for(MInOutLine line : InOutLines2){
			
			int M_Product_ID = line.getM_Product_ID();
			int C_TaxCategory_ID = 0;
			int M_PriceList_Version_ID = 0;
			
			MOrderLine ordLine = new MOrderLine(InOut.getCtx(), line.getC_OrderLine_ID(), InOut.get_TrxName());
			MProduct prod = new MProduct(InOut.getCtx(), M_Product_ID, InOut.get_TrxName());
			C_TaxCategory_ID = prod.getC_TaxCategory_ID();
			
			if(C_TaxCategory_ID <= 0){
				
				return;
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
				return;
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
				
		if(isCompletePay){
		
			int C_Decoris_PostingMethodInv_ID = inv.get_ValueAsInt("C_Decoris_PostingMethod_ID");
			if(C_Decoris_PostingMethodInv_ID > 0){
				int C_BankAccount_ID =0;
				
				X_C_Decoris_PostingMethod postInv = new X_C_Decoris_PostingMethod(inv.getCtx(), C_Decoris_PostingMethodInv_ID, inv.get_TrxName());
				C_BankAccount_ID = post.getC_BankAccount_ID();
				
				if(C_BankAccount_ID <= 0){
					return;
				}
				
				if(inv.getC_Order_ID() > 0){
					ord = new MOrder(inv.getCtx(), inv.getC_Order_ID(), inv.get_TrxName());
				}
				
				boolean isCompletePayment = postInv.isCompleteDocStatusPay();
	
				String DocActPayment = "";
				
				if (isCompletePayment){
					DocActPayment = "CO";
				}else if(!isCompletePayment){
					DocActPayment = "PR";
				}
				
				
				//Generate AP Payment
				MPayment payment = new MPayment(inv.getCtx(), 0, inv.get_TrxName());
					
				payment.setClientOrg(inv.getAD_Client_ID(), inv.getAD_Org_ID());
				payment.setIsReceipt(false);
				payment.setC_DocType_ID(postInv.getDocumentTypePayment_ID());
				payment.setC_BankAccount_ID(postInv.getC_BankAccount_ID());
				payment.setDateTrx(inv.getDateInvoiced());
				payment.setDateAcct(inv.getDateAcct());
				payment.setC_Invoice_ID(inv.getC_Invoice_ID());
				payment.setC_BPartner_ID(inv.getC_BPartner_ID());
				payment.setPayAmt(inv.getGrandTotal());
				payment.setC_Currency_ID(ord.getC_Currency_ID());
				payment.setTenderType(ord.getPaymentRule());
				payment.setDocAction(DocActPayment);
				payment.setIsPrepayment(false);					
				payment.setDescription(inv.getDescription());
				payment.saveEx();
				
				if(payment != null){
					payment.processIt(DocActPayment);
					payment.saveEx();
				}
				
				if(payment.isAllocated()){
					inv.setC_Payment_ID(payment.getC_Payment_ID());
					inv.setIsPaid(true);
					inv.saveEx();
				}
			}
		}
	}
	}
	

}	//	MInOut
