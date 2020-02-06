package org.decoris.model.validator;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MDocType;
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
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.X_C_Decoris_PostingMethod;
import org.osgi.service.event.Event;

/**
 * 
 * @author Tegar N
 *
 */

public class DEC_InOutValidator {

	public static CLogger log = CLogger.getCLogger(DEC_InOutValidator.class);
	
	public static String executeInOutEvent(Event event, PO po) {
		String msgInOut = "";
		String TableName = po.get_TableName();
		MInOut InOut = null;
		MInOutLine InoutLine = null;
		
		if(TableName.equals(MInOut.Table_Name)){
			InOut = (MInOut) po;
		}else if(TableName.equals(MInOutLine.Table_Name)){
			InoutLine = (MInOutLine)po;
		}
		
		if (event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE)) {
			if (InOut.isSOTrx()) {
				msgInOut = InOutBeforeSave(InOut);
			}else if (!InOut.isSOTrx() && InOut.getMovementType().equals("V+")&& !InOut.isReversal()) {
				msgInOut = AutoGenerate(InOut);
			}
		}else if (event.getTopic().equals(IEventTopics.DOC_BEFORE_VOID)|| event.getTopic().equals(IEventTopics.DOC_BEFORE_REVERSECORRECT)||event.getTopic().equals(IEventTopics.DOC_BEFORE_CLOSE)) {
			
			msgInOut = deleteIMEI(InOut, InoutLine, event);
			
		}else if (TableName.equals(MInOutLine.Table_Name)&&event.getTopic().equals(IEventTopics.PO_BEFORE_DELETE)){
			msgInOut = deleteIMEI(InOut, InoutLine, event);
		}else if (event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE)){
			msgInOut =  deleteIMEIReturn(InOut,event);
		}
		
		return msgInOut;

	}
	
	public static String InOutBeforeSave(MInOut InOut) {
		
		//set date when backdate transaction
		int C_Order_ID = 0;
		MOrder ord = null;
		
		C_Order_ID = InOut.getC_Order_ID();
		if(C_Order_ID > 0){
			
			ord = new MOrder(InOut.getCtx(), C_Order_ID, InOut.get_TrxName());
			InOut.setDateAcct(ord.getDateOrdered());
			
			String pick = ord.getDeliveryViaRule(); 
			
			
			if(pick.toUpperCase().equals("P")){
				InOut.setMovementDate(ord.getDateOrdered());
				InOut.setDateAcct(ord.getDateOrdered());
			}else if(pick.toUpperCase().equals("D")){
				InOut.setMovementDate(ord.getDatePromised());
				InOut.setDateAcct(ord.getDatePromised());

			}
			
			//set locator when multi locator
			boolean isMultiLocator = false;
			boolean isDecorisMultiLocator = false;
			Integer M_Locator_ID = 0;
			
			M_Locator_ID = ord.get_ValueAsInt("M_Locator_ID");
			isDecorisMultiLocator = ord.get_ValueAsBoolean("IsDecorisMultiLocator");
			if(M_Locator_ID == null){
				M_Locator_ID = 0;
			}
			if(M_Locator_ID == 0){	
				isMultiLocator = true;
			}
			
			if(isMultiLocator){
				
				if (isDecorisMultiLocator){
					//MOrderLine[] ordLines = ord.getLines();
					MInOutLine[] inLines = InOut.getLines();
					
					
					for (MInOutLine inLine : inLines){
						
					MOrderLine ordLine = new MOrderLine(inLine.getCtx(), inLine.getC_OrderLine_ID(), inLine.get_TrxName());
					MProduct as = new MProduct(null, inLine.getM_Product_ID(), null);
					if(as.getProductType().equals(MProduct.PRODUCTTYPE_Item)){
					inLine.setM_Locator_ID(ordLine.get_ValueAsInt("MultiLocator_ID"));
					}
					inLine.saveEx();
					
					//end multilocator
						
					}
					
				}else{
				
				StringBuilder SQLDecorisPOS = new StringBuilder();
				SQLDecorisPOS.append("SELECT C_DecorisPOS_ID ");
				SQLDecorisPOS.append("FROM C_DecorisPOS ");
				SQLDecorisPOS.append("WHERE AD_Client_ID = ? ");
				SQLDecorisPOS.append("AND C_Order_ID = ?");
				
				StringBuilder SQLLocator = new StringBuilder();
				SQLLocator.append("SELECT M_Locator_ID ");
				SQLLocator.append("FROM C_DecorisPOSLine ");
				SQLLocator.append("WHERE AD_Client_ID = ? ");
				SQLLocator.append("AND C_DecorisPOS_ID = ? ");
				SQLLocator.append("AND M_Product_ID = ? ");
				SQLLocator.append("AND QtyOrdered = ? ");
				SQLLocator.append("AND Line = ? ");
				SQLLocator.append("AND C_OrderLine_ID = ? ");

				MInOutLine[] inLines = InOut.getLines();
				
				
				for (MInOutLine inLine : inLines){
					
					int C_DecorisPOS_ID = DB.getSQLValueEx(InOut.get_TrxName(), SQLDecorisPOS.toString(), new Object []{inLine.getAD_Client_ID(),C_Order_ID});
					if(C_DecorisPOS_ID > 0){
						
						Integer M_Loc_ID = DB.getSQLValueEx(InOut.get_TrxName(), SQLLocator.toString(),new Object []{inLine.getAD_Client_ID(),
							C_DecorisPOS_ID,inLine.getM_Product_ID(),inLine.getQtyEntered(),inLine.getLine(),inLine.getC_OrderLine_ID()});
						
						if(M_Loc_ID > 0){
							inLine.setM_Locator_ID(M_Loc_ID);
							inLine.saveEx();
						}	
						
					}
					
				}	

			}//end multi locator
			}
		
		}	
		
		
		int AD_User_ID = Env.getAD_User_ID(InOut.getCtx());
		boolean isManualDocNo = false; 

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		int CreatedByPOS_ID = DB.getSQLValueEx(InOut.get_TrxName(), sqlKasir.toString(),new Object[] { InOut.getAD_Client_ID(), AD_User_ID });

		if(CreatedByPOS_ID > 0){
		
			StringBuilder SQLIsManualDoc = new StringBuilder();
			
			SQLIsManualDoc.append("SELECT IsManualDocumentNo");
			SQLIsManualDoc.append(" FROM C_Pos ");
			SQLIsManualDoc.append(" WHERE AD_Client_ID = ? ");
			SQLIsManualDoc.append(" AND CreatedByPOS_ID = ?");
			
			String isManualDoc = DB.getSQLValueStringEx(InOut.get_TrxName(), SQLIsManualDoc.toString(), new Object[]{InOut.getAD_Client_ID(),CreatedByPOS_ID});
			
			if(isManualDoc == null){
				isManualDocNo = false;
			}else if(isManualDoc.toUpperCase().equals("Y")){
				isManualDocNo = true;
			}
			
			
			
			if(isManualDocNo){
				
				InOut.setDocumentNo(ord.getDocumentNo());
				
			}

		}
		return "";
		
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
		
		if(!IsAutoGenerate && InOut.getC_Order_ID()==0){
			
			return "Purchase Posting Method atau Order Pembelian Tidak Boleh Kosong";
		}

		if(IsAutoGenerate && InOut.getC_Order_ID()>0){
			
			return "Purchase Posting Method dan Order Pembelian Tidak Boleh Diproses Secara Bersamaan";
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
				DocActOrd = "PR";
			}

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
			
//			//Generate Order Header
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
				
//				//Generate OrderLine
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
						
		}
		
		return "";
		
	}
		
	
	/*
	 * 
	 * @author tegar
	 * 
	 */
	public static String AutoGenerate(MInOut InOut){
		boolean IsAutoGenerate = false;
		Integer C_Decoris_PostingMethod_ID = 0;
		
		C_Decoris_PostingMethod_ID = InOut.get_ValueAsInt("C_Decoris_PostingMethod_ID");
		
		
		if(C_Decoris_PostingMethod_ID == null){
			C_Decoris_PostingMethod_ID = 0;
		}
		
		if(C_Decoris_PostingMethod_ID > 0){
			IsAutoGenerate = true;
		}
		
		if(!IsAutoGenerate && InOut.getC_Order_ID()==0){
			
			return "Purchase Posting Method atau Nota Pembelian Tidak Boleh Kosong";
		}
		
		if(IsAutoGenerate && InOut.getC_Order_ID()>0){
			
			return "Purchase Posting Method dan Order Pembelian Tidak Boleh Diproses Secara Bersamaan";
		}
		
		if(IsAutoGenerate){
			
			X_C_Decoris_PostingMethod postInOut = new X_C_Decoris_PostingMethod(InOut.getCtx(), C_Decoris_PostingMethod_ID, InOut.get_TrxName());
			
			MOrder ord = new MOrder(InOut.getCtx(), 0, InOut.get_TrxName());
			
			MInOutLine InOutLines[] = InOut.getLines(); 
			
			boolean isCompleteOrd = postInOut.isCompleteDocStatusPO();
			boolean isCompleteInv = postInOut.isCompleteDocStatusInv();
			boolean isCompletePay = postInOut.isCompleteDocStatusPay();

			String DocActOrd = "";
			String DocActInv = "";
			String DocActPay = "";
			
			if (isCompleteOrd){
				DocActOrd = "CO";
			}else if(!isCompleteOrd){
				DocActOrd = "PR";
			}
			
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
		
			
			MPriceList pList = new MPriceList(InOut.getCtx(), postInOut.getM_PriceList_ID(), InOut.get_TrxName());
			
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
				M_PriceList_Version_ID =  DB.getSQLValueEx(InOut.get_TrxName(), SQLPriceList.toString(), new Object[]{InOut.getAD_Client_ID(),postInOut.getM_PriceList_ID()});
	
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
//				ordLine.setQtyDelivered(line.getQtyEntered());
//				ordLine.setQtyReserved(ordLine.getQtyOrdered().subtract(ordLine.getQtyDelivered()));
//				ordLine.setQtyInvoiced(line.getQtyEntered());
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
	//	InOut.processIt(MInOut.DOCACTION_Post);
		InOut.saveEx();
		
		
		for(MInOutLine line : InOutLines2){
			
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
			invLine.setC_UOM_ID(ordLine.getC_UOM_ID());
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
					return "Bank Account Belum Ditentukan pada Posting Method";
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
		return "";
	}
	
	
	public static String afterComplete(MInOut inOut){
		
		//Set Invoice DocNo at AR Receipt
		
		
		StringBuilder SQLGetPay = new StringBuilder();
		SQLGetPay.append("SELECT C_Payment_ID ");
		SQLGetPay.append("FROM C_POSPayment ");
		SQLGetPay.append("WHERE AD_Client_ID = ?");
		SQLGetPay.append("AND C_Order_ID = ? ");

		int C_Payment_ID = DB.getSQLValueEx(null, SQLGetPay.toString(), new Object[]{inOut.getAD_Client_ID(),inOut.getC_Order_ID()});
		
		
		StringBuilder SQLGetInvoice = new StringBuilder();
		SQLGetInvoice .append("SELECT C_Invoice_ID ");
		SQLGetInvoice .append("FROM C_Invoice ");
		SQLGetInvoice .append("WHERE AD_Client_ID = ?");
		SQLGetInvoice .append(" AND C_Order_ID = ? ");
		
		//int asd = inOut.getC_Invoice_ID();
		
		int C_Invoice_ID = DB.getSQLValueEx(null, SQLGetInvoice.toString(), new Object[]{inOut.getAD_Client_ID(),inOut.getC_Order_ID()});

		if(C_Payment_ID > 0 && C_Invoice_ID > 0){
			
			MPayment pay = new MPayment(inOut.getCtx(), C_Payment_ID, inOut.get_TrxName());
			pay.setC_Invoice_ID(C_Invoice_ID);
			pay.saveEx();
			
			
		}
		return "";			
		
		
	}
	
	
	public static String deleteIMEI(MInOut inout, MInOutLine InoutLine, Event event){
		
		String docType = getDocType(inout);
		
	
		
		if(inout != null && event.getTopic().equals(IEventTopics.DOC_BEFORE_VOID)|| event.getTopic().equals(IEventTopics.DOC_BEFORE_REVERSECORRECT)){
				
			if(docType.equals("MMReceipt")){
				
				MInOutLine[] lines = inout.getLines();
								
				for(MInOutLine line : lines){
					
					if(line.getM_AttributeSetInstance_ID() > 0){
						
						int M_AttributeSetInstance_ID = line.getM_AttributeSetInstance_ID();
						MAttributeSetInstance mattins = new MAttributeSetInstance(line.getCtx(), M_AttributeSetInstance_ID, line.get_TrxName());
						
						updateIMEI(MAttributeSetInstance.Table_Name, line.getAD_Client_ID(), M_AttributeSetInstance_ID, mattins.getSerNo()+"(Reversed)", line.get_TrxName());
						
					
				}
					
				if(inout.getReversal_ID() >0 ){
					
					MInOut revInOut = new MInOut(inout.getCtx(), inout.getReversal_ID(), inout.get_TrxName());
					
					MInOutLine[] revLines = revInOut.getLines();
	
					for(MInOutLine revLine : revLines){
						
						if(revLine.getM_AttributeSetInstance_ID() > 0){
							
							int M_AttributeSetInstance_ID = revLine.getM_AttributeSetInstance_ID();
							MAttributeSetInstance mattins = new MAttributeSetInstance(line.getCtx(), M_AttributeSetInstance_ID, line.get_TrxName());			
							updateIMEI(MAttributeSetInstance.Table_Name, line.getAD_Client_ID(), M_AttributeSetInstance_ID, mattins.getSerNo()+"(Reversed)", line.get_TrxName());
						
						}
					
				}
					
					
				}
				
				}	
			}else if(InoutLine != null && event.getTopic().equals(IEventTopics.PO_BEFORE_DELETE)){
				
				if(InoutLine.getM_AttributeSetInstance_ID() > 0){
					
					
					MAttributeSetInstance imei = new MAttributeSetInstance(InoutLine.getCtx(), InoutLine.getM_AttributeSetInstance_ID(), InoutLine.get_TrxName()); 
					imei.deleteEx(true);
	
				}
				
		}
			
	}
		
		
		return "";
	}
	
	
	
	public static String deleteIMEIReturn(MInOut inout,Event event){
		
		String docType = getDocType(inout);
		
		if(docType.equals("MMVendorReturn")){
			
			MInOutLine[] lines = inout.getLines();
			
			for(MInOutLine line : lines){
				
				if(line.getM_AttributeSetInstance_ID() > 0){
					
					int M_AttributeSetInstance_ID = line.getM_AttributeSetInstance_ID();
					MAttributeSetInstance mattins = new MAttributeSetInstance(line.getCtx(), M_AttributeSetInstance_ID, line.get_TrxName());
					
					updateIMEI(MAttributeSetInstance.Table_Name, line.getAD_Client_ID(), M_AttributeSetInstance_ID, mattins.getSerNo()+"(Reversed)", line.get_TrxName());
					
				
				}
			
			
			}

		}
		
		return "";
		
	}
	
	public static void updateIMEI(String TableName,int AD_Client_ID, int M_AttributeSetInstance_ID, String NewSerNo,String TrxName){
		
		StringBuilder SQLUpdate = new StringBuilder();
		SQLUpdate.append(" UPDATE "+TableName);
		SQLUpdate.append(" SET SerNo =  '"+ NewSerNo +"',");
		SQLUpdate.append(" Description = '#" +NewSerNo+"'");
		SQLUpdate.append(" WHERE AD_Client_ID = ? ");
		SQLUpdate.append(" AND M_AttributeSetInstance_ID = ? ");
		
		DB.executeUpdateEx(SQLUpdate.toString(), new Object[]{AD_Client_ID,M_AttributeSetInstance_ID}, TrxName);

		
	}
	
	
	public static String getDocType(MInOut inout){
		
		String rslt = "";
		
		int C_DocType_ID = inout.getC_DocType_ID();
		
		MDocType docType = new MDocType(null, C_DocType_ID, inout.get_TrxName());
		
		
		if(docType.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialDelivery)&& !docType.isSOTrx()){
			rslt = "MMVendorReturn";	
		}else if(docType.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialDelivery)&& docType.isSOTrx()){
			rslt = "MMShipment";
		}else if(docType.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialReceipt)&& !docType.isSOTrx()){
			rslt = "MMReceipt";
		}else if(docType.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialReceipt)&& docType.isSOTrx()){
			rslt = "MMCustomerReturn";
		}
						
		
		return rslt;
	}

	
	
	
}
