package org.decoris.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.decoris.model.I_C_Decoris_ENofa;

/**
 * 
 * @author Tegar N
 *
 */

public class Decoris_CallOut_ENofa extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals(I_C_Decoris_ENofa.COLUMNNAME_NoAwal)||
				mField.getColumnName().equals(I_C_Decoris_ENofa.COLUMNNAME_NoAkhir)){
			return setTotalENofa(ctx, WindowNo, mTab, mField, value);
		}		
		
		return null;
	}
	
	
	public String setTotalENofa (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
	
		Integer NoAwal = (Integer)mTab.getValue("NoAwal");
		Integer NoAkhir = (Integer)mTab.getValue("NoAkhir");

		
		if(NoAwal == null){
			NoAwal = 0;
		}
		
		if(NoAkhir == null){
			NoAkhir = 0;
		}
		
		Integer totalENofa = NoAkhir-NoAwal+1;
		if(NoAwal==0 || NoAkhir == 0){
			totalENofa = 0;
		}
		
		mTab.setValue(I_C_Decoris_ENofa.COLUMNNAME_TotalEnofa, totalENofa);
				
	return"";
	}

}
