package org.decoris.model.validator;

import java.math.BigDecimal;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.X_C_Decoris_PreOrder;
import org.decoris.model.X_C_Decoris_PreOrderLine;
import org.osgi.service.event.Event;

public class DEC_PreOrderLineValidator {

	public static CLogger log = CLogger.getCLogger(DEC_MInOutValidator.class);

	
	
	public static String executePreOrderLineEvent(Event event, PO po) {
		String msgPOrderLine = "";
		String evt = event.getTopic();
		X_C_Decoris_PreOrderLine POrderLine = (X_C_Decoris_PreOrderLine) po;
		if (event.getTopic().equals(IEventTopics.PO_AFTER_NEW) ||
				event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE)||
				event.getTopic().equals(IEventTopics.PO_AFTER_DELETE)) {
			msgPOrderLine = PreOrderLineBeforeSave(POrderLine,evt);
			
		}
		return msgPOrderLine;

	}
	
	private static String PreOrderLineBeforeSave (X_C_Decoris_PreOrderLine line, String event){
		String rs = "";
		
	      X_C_Decoris_PreOrder preOrder = new X_C_Decoris_PreOrder(line.getCtx(), line.getC_Decoris_PreOrder_ID(), line.get_TrxName());
	      
	      BigDecimal grandTotal = Env.ZERO;
	      StringBuilder SQLGetLineAmt = new StringBuilder();
	      
	      SQLGetLineAmt.append("SELECT Coalesce(SUM(LineNetAmt),0) ");
	      SQLGetLineAmt.append("FROM C_Decoris_PreOrderLine ");
	      SQLGetLineAmt.append("WHERE AD_Client_ID = ? ");
	      SQLGetLineAmt.append("AND C_Decoris_PreOrder_ID = ?");

	      if(event.equals(IEventTopics.PO_AFTER_CHANGE)){
		      SQLGetLineAmt.append("AND C_Decoris_PreOrderLine_ID <> ? "); 
		      grandTotal = DB.getSQLValueBDEx(null, SQLGetLineAmt.toString(), new Object[]{line.getAD_Client_ID(),line.getC_Decoris_PreOrder_ID(),line.getC_Decoris_PreOrderLine_ID()});
	      }else if(event.equals(IEventTopics.PO_AFTER_NEW)){
		      grandTotal = DB.getSQLValueBDEx(null, SQLGetLineAmt.toString(), new Object[]{line.getAD_Client_ID(),line.getC_Decoris_PreOrder_ID()});
	      }
	    	        
	      BigDecimal NextGrandTotal = grandTotal.add(line.getLineNetAmt());
	      
	      if(event.equals(IEventTopics.PO_AFTER_DELETE)){
	    	  grandTotal = preOrder.gettotprodprice();
	    	  BigDecimal grandDelete = grandTotal.subtract(line.getLineNetAmt());
	    	  StringBuilder SQLUpdate = new StringBuilder();
	    	  SQLUpdate.append("UPDATE C_Decoris_PreOrder ");
	    	  SQLUpdate.append("SET totprodprice = "+grandDelete);
	    	  SQLUpdate.append(" WHERE AD_Client_ID = ? ");
	    	  SQLUpdate.append(" AND C_Decoris_PreOrder_ID = ? ");
	    	  
	    	  DB.executeUpdateEx(SQLUpdate.toString(), new Object[]{preOrder.getAD_Client_ID(),preOrder.getC_Decoris_PreOrder_ID()}, preOrder.get_TrxName());
	    	  
	      }else{
	    	  preOrder.settotprodprice(NextGrandTotal);
		      preOrder.saveEx();

	      }
		
		return rs;
		
	}
	
	
}
