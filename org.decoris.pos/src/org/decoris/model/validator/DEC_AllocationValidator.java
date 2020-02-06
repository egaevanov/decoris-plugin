package org.decoris.model.validator;

import java.math.BigDecimal;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

public class DEC_AllocationValidator {
	
	
	
	public static CLogger log = CLogger.getCLogger(DEC_ENofaValidator.class);
	
		public static String executeAllocationEvent(Event event, PO po) {
			
			String msgAlloc = "";
			MAllocationHdr Alloc = (MAllocationHdr) po;
			if (event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE)) {
			
					AllocationBeforeComplete(Alloc);
					
			}
			
		return msgAlloc;
	
	}
		
		
		
	private static String AllocationBeforeComplete(MAllocationHdr Allocation) {
		String rs = "";
		
		MAllocationLine[] lines = Allocation.getLines(false);
		//BigDecimal Amt = Env.ZERO;
		for (MAllocationLine line : lines ){
			
			if(line.getC_Payment_ID() > 0 && line.getC_Invoice_ID() > 0){
				
				MPayment pay = new MPayment(Allocation.getCtx(), line.getC_Payment_ID(), Allocation.get_TrxName());
				
				if(pay.isReceipt() && pay.isProcessed()){
					
					BigDecimal Amt = line.getAmount();
					
					MBPartner bp = new MBPartner(Allocation.getCtx(), pay.getC_BPartner_ID(), Allocation.get_TrxName());
					BigDecimal CreditUsed = bp.getSO_CreditUsed();
					BigDecimal newCreditUsed = Env.ZERO;
					
					 if(pay.getReversal_ID() > 0){
						 newCreditUsed = CreditUsed.add(Amt);
					 }else{
						 newCreditUsed = CreditUsed.subtract(Amt);
					 }
					
					
					bp.setSO_CreditUsed(newCreditUsed);
					bp.saveEx();
					
				}
				
			}
			
			
		}
		
		
		return rs;
	}

}
