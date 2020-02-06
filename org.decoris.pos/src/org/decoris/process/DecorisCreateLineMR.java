package org.decoris.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class DecorisCreateLineMR extends SvrProcess {

	private int p_M_Product_ID = 0;
	private BigDecimal p_qtyEntered = Env.ZERO; 
	private int p_M_Locator_ID = 0;
	private int p_M_InOut_ID = 0;
	
	
	@Override
	protected void prepare() {
		
		
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0 ; i < para.length ;i++){
			
			String name = para[i].getParameterName();
			
			if(para[i].getParameter()==null)
				;
			else if(name.equals("M_Product_ID"))
				p_M_Product_ID = (int)para[i].getParameterAsInt();
			
			else if(name.equals("QtyEntered"))
				p_qtyEntered = (BigDecimal)para[i].getParameterAsBigDecimal();
			
			else if(name.equals("M_Locator_ID"))
				p_M_Locator_ID = (int)para[i].getParameterAsInt();
			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			
		}
		
		
		p_M_InOut_ID = getRecord_ID();
		
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_M_InOut_ID > 0){
			
			
			MInOut MaterialReceipt = new MInOut(getCtx(), p_M_InOut_ID, get_TrxName());
			
			if(MaterialReceipt != null){
				
				
				if(p_M_Product_ID >0){
					
					MProduct prod = new MProduct(getCtx(), p_M_Product_ID, get_TrxName());
					Integer Qty = p_qtyEntered.intValueExact();
					
					
					if(prod.getM_AttributeSet_ID() > 0){
						
						for (int i = 0 ; i < Qty ; i++){
							
							MInOutLine line = new MInOutLine(getCtx(), 0, get_TrxName());
							line.setClientOrg(MaterialReceipt.getAD_Client_ID(), MaterialReceipt.getAD_Org_ID());
							line.setM_InOut_ID(p_M_InOut_ID);
							line.setM_Product_ID(p_M_Product_ID);
							line.setM_Locator_ID(p_M_Locator_ID);
							line.setQtyEntered(Env.ONE);
							line.setQty(Env.ONE);
							line.setMovementQty(Env.ONE);
							line.setC_UOM_ID(prod.getC_UOM_ID());
							line.saveEx();
							
							
						}
						
						
					}else{
						
						MInOutLine line = new MInOutLine(getCtx(), 0, get_TrxName());
						line.setClientOrg(MaterialReceipt.getAD_Client_ID(), MaterialReceipt.getAD_Org_ID());
						line.setM_InOut_ID(p_M_InOut_ID);
						line.setM_Product_ID(p_M_Product_ID);
						line.setM_Locator_ID(p_M_Locator_ID);
						line.setQtyEntered(p_qtyEntered);
						line.setQty(p_qtyEntered);
						line.setMovementQty(p_qtyEntered);
						line.setC_UOM_ID(prod.getC_UOM_ID());
						line.saveEx();
						
					}
					
					
				}
				
				
			}
			
			
		}
		
		return "";
	}

}
