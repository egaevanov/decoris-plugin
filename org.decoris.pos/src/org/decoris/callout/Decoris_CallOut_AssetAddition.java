package org.decoris.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_A_Asset_Addition;
import org.compiere.model.MAsset;
import org.compiere.model.MInvoiceLine;
import org.compiere.util.DB;

public class Decoris_CallOut_AssetAddition extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {

		if(mField.getColumnName().equals(I_A_Asset_Addition.COLUMNNAME_A_Asset_ID)){
			return setAsset(ctx, WindowNo, mTab, mField, value);
		}
		
		return "";
	}

	
	public String setAsset(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
	
		Integer A_Asset_ID = (Integer)value;
		
		if(A_Asset_ID == null){
			
			A_Asset_ID = 0;
		}
		
		if(A_Asset_ID > 0){
			
			MAsset asset = new MAsset(ctx, A_Asset_ID, null);
			
			StringBuilder SQLGetInvoice = new StringBuilder();
			SQLGetInvoice.append("SELECT C_InvoiceLine_ID ");
			SQLGetInvoice.append("FROM C_InvoiceLine ");
			SQLGetInvoice.append("WHERE AD_Client_ID = ? ");
			SQLGetInvoice.append("AND M_Product_ID = ?");
		
			
			int p_C_InvoiceLine_ID = 0;
			p_C_InvoiceLine_ID = DB.getSQLValue(null, SQLGetInvoice.toString(), new Object[]{asset.getAD_Client_ID(),asset.getM_Product_ID()});
			
			MInvoiceLine invLine = new MInvoiceLine(ctx, p_C_InvoiceLine_ID, null);
			
			mTab.setValue("C_Invoice_ID", invLine.getC_Invoice_ID());
			
			mTab.setValue("C_InvoiceLine_ID", invLine.getC_InvoiceLine_ID());

			

			
		}
		
		
		
	return"";
	}
	
}
