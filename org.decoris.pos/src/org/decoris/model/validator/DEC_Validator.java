package org.decoris.model.validator;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_AllocationHdr;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_InOutLine;
import org.compiere.model.I_M_Movement;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.decoris.model.I_C_Decoris_ENofa;
import org.decoris.model.I_C_Decoris_PostingMethod;
import org.decoris.model.I_C_Decoris_PreOrder;
import org.decoris.model.I_C_Decoris_PreOrderLine;
import org.decoris.model.I_C_Decoris_PreSales;
import org.decoris.model.I_C_Decoris_PreSalesLine;
import org.osgi.service.event.Event;

/**
 * 
 * @author Tegar N
 *
 */

public class DEC_Validator extends AbstractEventHandler{

	private CLogger log = CLogger.getCLogger(DEC_Validator.class);

	
	@Override
	protected void doHandleEvent(Event event) {
		
		log.info("DECORIS EVENT MANAGER // INITIALIZED");
		String msg = "";
		
		if (event.getTopic().equals(IEventTopics.AFTER_LOGIN)) {
			/*
			LoginEventData eventData = getEventData(event);
			log.info(" topic="+event.getTopic()+" AD_Client_ID="+eventData.getAD_Client_ID()
					+" AD_Org_ID="+eventData.getAD_Org_ID()+" AD_Role_ID="+eventData.getAD_Role_ID()
					+" AD_User_ID="0+eventData.getAD_User_ID());
			 */
		} 

		else  {
			
			if (getPO(event).get_TableName().equals(I_C_Order.Table_Name)) {
				msg = DEC_OrderValidator.executeOrderEvent(event, getPO(event));
			}else if (getPO(event).get_TableName().equals(I_M_InOut.Table_Name)) {
				msg = DEC_InOutValidator.executeInOutEvent(event, getPO(event));				
			}else if (getPO(event).get_TableName().equals(I_C_Invoice.Table_Name)) {
				msg = DEC_InvoiceValidator.executeInvoiceEvent(event, getPO(event));				
			}else if (getPO(event).get_TableName().equals(I_M_Movement.Table_Name)){
				msg = DEC_MovementValidator.executeMoveEvent(event, getPO(event));
			}else if (getPO(event).get_TableName().equals(I_C_Decoris_ENofa.Table_Name)){
				msg = DEC_ENofaValidator.executeENofaEvent(event, getPO(event));
			}else if (getPO(event).get_TableName().equals(I_C_Decoris_PreOrderLine.Table_Name)){
				msg = DEC_PreOrderLineValidator.executePreOrderLineEvent(event, getPO(event));
			}else if (getPO(event).get_TableName().equals(I_C_Decoris_PreOrder.Table_Name)){
				msg = DEC_PreOrderValidator.executePreOrderEvent(event, getPO(event));
			}else if (getPO(event).get_TableName().equals(I_C_Decoris_PreSales.Table_Name)){
				msg = DEC_PreSalesValidator.executePreSalesEvent(event, getPO(event));
			}else if (getPO(event).get_TableName().equals(I_C_Decoris_PreSalesLine.Table_Name)){
				msg = DEC_PreSalesValidator.executePreSalesEvent(event, getPO(event));
			}else if (getPO(event).get_TableName().equals(I_M_InOutLine.Table_Name)){
				msg = DEC_InOutValidator.executeInOutEvent(event, getPO(event));				
			}else if (getPO(event).get_TableName().equals(I_C_AllocationHdr.Table_Name)){
				msg = DEC_AllocationValidator.executeAllocationEvent(event, getPO(event));				
			}	


			logEvent(event, getPO(event), msg);

		}

	}

	@Override
	protected void initialize() {
		
		registerEvent(IEventTopics.AFTER_LOGIN);
		
		//Order event
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, I_C_Order.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_VOID, I_C_Order.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, I_C_Order.Table_Name);

		//InOut Event
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_VOID, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REVERSECORRECT, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_REVERSECORRECT, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_CLOSE, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_DELETE, I_M_InOutLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_M_InOutLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW_REPLICATION, I_M_InOutLine.Table_Name);

	
		//Invoice Event
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_Invoice.Table_Name);		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_Invoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, I_C_Invoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, I_C_Invoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_REVERSECORRECT, I_C_Invoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_REVERSEACCRUAL, I_C_Invoice.Table_Name);
	
		//Movement Event
		registerTableEvent(IEventTopics.DOC_BEFORE_VOID, I_M_Movement.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_REVERSECORRECT, I_M_Movement.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_REVERSEACCRUAL, I_M_Movement.Table_Name);
		
		//postingmethod
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_Decoris_PostingMethod.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_Decoris_PostingMethod.Table_Name);

		
		//ENofa Event
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_Decoris_ENofa.Table_Name);	
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_Decoris_ENofa.Table_Name);	
		
		
		//preOrderLine Event
		registerTableEvent(IEventTopics.PO_AFTER_NEW,I_C_Decoris_PreOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE,I_C_Decoris_PreOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_DELETE,I_C_Decoris_PreOrderLine.Table_Name);

		
		//preOrderL Event
		registerTableEvent(IEventTopics.PO_AFTER_NEW,I_C_Decoris_PreOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE,I_C_Decoris_PreOrder.Table_Name);
		
		
		//PreSales Event
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_Decoris_PreSales.Table_Name);		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_Decoris_PreSales.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_Decoris_PreSalesLine.Table_Name);		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_Decoris_PreSalesLine.Table_Name);
		
		//Allocation Event
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, I_C_AllocationHdr.Table_Name);
		
	}
	
	private void logEvent (Event event, PO po, String msg) {
		log.fine("EVENT MANAGER // "+event.getTopic()+" po="+po+" MESSAGE ="+msg);
		if (msg.length()  > 0) 
			throw new AdempiereException(msg);	
	}
	

}
