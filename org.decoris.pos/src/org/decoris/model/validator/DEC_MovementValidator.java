package org.decoris.model.validator;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MMovement;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

/**
 * 
 * @author Tegar N
 *
 */

public class DEC_MovementValidator {

	public static CLogger log = CLogger.getCLogger(DEC_MovementValidator.class);

	public static String executeMoveEvent(Event event, PO po) {
		String msgMove = "";
		MMovement move = (MMovement) po;
		
		int AD_User_ID = Env.getAD_User_ID(move.getCtx());
		boolean isManualDocumentNo = false; 
		
		

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		int CreatedByPOS_ID = DB.getSQLValueEx(move.get_TrxName(), sqlKasir.toString(),new Object[] { move.getAD_Client_ID(), AD_User_ID });

		if(CreatedByPOS_ID > 0){
			
			String manualDocumentNoSql = "SELECT IsManualDocumentNo FROM C_Pos WHERE AD_Client_ID = ? AND  CreatedByPOS_ID = ? ";
			String isManualDoc  = DB.getSQLValueStringEx(move.get_TrxName(),manualDocumentNoSql,new Object[] { move.getAD_Client_ID(), CreatedByPOS_ID });
		
			if (isManualDoc == null){
				isManualDocumentNo = false;
			}else if (isManualDoc.toUpperCase().equals("Y")){
				isManualDocumentNo = true;
			}else{
				isManualDocumentNo = false;
			}
		}
		
		
		if (event.getTopic().equals(IEventTopics.DOC_BEFORE_VOID)||
				event.getTopic().equals(IEventTopics.DOC_BEFORE_REVERSECORRECT)||
				event.getTopic().equals(IEventTopics.DOC_BEFORE_REVERSEACCRUAL)) {
			if (isManualDocumentNo){
				
				msgMove = moveBeforeReverse(move);
		
			} 
		}
			
		return msgMove;

	}
	
	
	public static String moveBeforeReverse(MMovement move){
		
		move.set_ValueNoCheck("DocumentNo", move.getDocumentNo()+"^");
		move.saveEx();
	
		return "";
	}
	
}
