package org.decoris.model.validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MOrder;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.MDecorisPOS;
import org.osgi.service.event.Event;

/**
 * 
 * @author Tegar N
 *
 */

public class DEC_OrderValidator {
	
	public static CLogger log = CLogger.getCLogger(DEC_OrderValidator.class);
	
	public static String executeOrderEvent(Event event, PO po) {
		String msgOrder = "";
		MOrder order = (MOrder) po;
		if (event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE)) {
			if (order.isSOTrx()) {
				msgOrder = POSSOBeforeComplete(order);
			}
		}else if(event.getTopic().equals(IEventTopics.DOC_BEFORE_VOID)){
			if(!order.isSOTrx()){
				msgOrder = POBeforeVoid(order);
			}
		}
			
		return msgOrder;

	}

	public static String POSSOBeforeComplete(MOrder order) {
		
		StringBuilder sqlOrder = new StringBuilder();
		
		if (order.getPOReference()!=null){
		
			//Add Processed To POS When Manual SO Complete
			sqlOrder.append("SELECT C_DecorisPOS_ID ");
			sqlOrder.append("FROM C_DecorisPOS ");
			sqlOrder.append("WHERE AD_Client_ID = ? ");
			sqlOrder.append("AND C_Order_ID = ? ");
			sqlOrder.append("AND DocumentNo = '"+ order.getPOReference() + "'" );
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				
				pstmt = DB.prepareStatement(sqlOrder.toString(), order.get_TrxName());
				pstmt.setInt(1, order.getAD_Client_ID());
				pstmt.setInt(2, order.getC_Order_ID());
				rs = pstmt.executeQuery();
				while (rs.next()){
					
					int C_DecorisPOS_ID = 0;
					C_DecorisPOS_ID = rs.getInt(1);
					
					if (C_DecorisPOS_ID > 0){
						
						MDecorisPOS pos = new MDecorisPOS(order.getCtx(), C_DecorisPOS_ID, order.get_TrxName());
						pos.setProcessed(true);
						pos.saveEx();
						
					}
					
				}
				
				
			}catch(Exception e){
				log.log(Level.SEVERE, sqlOrder.toString(), e);
	
			}finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		}
		//End Processed
		
		return "";
	}	
	
	public static String POBeforeVoid (MOrder ord){
		
		int AD_User_ID = Env.getAD_User_ID(ord.getCtx());
		boolean isManualDocumentNo = false; 

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		int CreatedByPOS_ID = DB.getSQLValueEx(ord.get_TrxName(), sqlKasir.toString(),new Object[] { ord.getAD_Client_ID(), AD_User_ID });

		if(CreatedByPOS_ID > 0){
			
			String manualDocumentNoSql = "SELECT IsManualDocumentNo FROM C_Pos WHERE AD_Client_ID = ? AND  CreatedByPOS_ID = ? ";
			String isManualDoc  = DB.getSQLValueStringEx(ord.get_TrxName(),manualDocumentNoSql,new Object[] { ord.getAD_Client_ID(), CreatedByPOS_ID });
		
			if (isManualDoc == null){
				isManualDocumentNo = false;
			}else if (isManualDoc.toUpperCase().equals("Y")){
				isManualDocumentNo = true;
			}
		}
		
		if(isManualDocumentNo){
			
			ord.set_ValueNoCheck("DocumentNo", ord.getDocumentNo()+"^");
			ord.saveEx();
			
		}
		
		
		return "";
	}
	
	
}
