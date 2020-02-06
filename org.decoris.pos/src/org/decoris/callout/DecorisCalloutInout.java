package org.decoris.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.decoris.model.I_C_Decoris_ENofa;

public class DecorisCalloutInout extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals(I_C_Decoris_ENofa.COLUMNNAME_NoAwal)||
				mField.getColumnName().equals(I_C_Decoris_ENofa.COLUMNNAME_NoAkhir)){
		//	return setMAttribute(ctx, WindowNo, mTab, mField, value);
		}		
		
//		System.out.println(ctx);
//		System.out.println(WindowNo);
//		System.out.println(mTab);
//		System.out.println(mField);
//		System.out.println(value);
//		System.out.println(oldValue);


		
		return null;
	}

	
	
//	public String setMAttribute (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
//		
//	//	get
//				
//	return"";
//	}

	
	
}
