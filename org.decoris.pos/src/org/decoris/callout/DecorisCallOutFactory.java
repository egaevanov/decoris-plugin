package org.decoris.callout;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.I_A_Asset_Addition;
import org.compiere.model.I_M_InOutLine;
import org.decoris.model.I_C_Decoris_ENofa;
import org.decoris.model.I_C_Decoris_PreOrder;
import org.decoris.model.I_C_Decoris_PreOrderLine;

/**
 * 
 * @author Tegar N
 *
 */

public class DecorisCallOutFactory implements IColumnCalloutFactory {

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) {

		List<IColumnCallout> list = new ArrayList<IColumnCallout>();

		
		if (tableName.equals(I_C_Decoris_ENofa.Table_Name)){
			list.add (new Decoris_CallOut_ENofa());
		}else if(tableName.equals(I_C_Decoris_PreOrderLine.Table_Name)){
			list.add(new Decoris_CallOutPreOrder());
		}else if(tableName.equals(I_C_Decoris_PreOrder.Table_Name)){
			list.add(new Decoris_CallOutPreOrder());
		}else if(tableName.equals(I_A_Asset_Addition.Table_Name)){
			list.add(new Decoris_CallOut_AssetAddition());
		}else if(tableName.equals(I_M_InOutLine.Table_Name)){
			list.add(new DecorisCalloutInout());
		}
		return list != null ? list.toArray(new IColumnCallout[0])
				: new IColumnCallout[0];
	}

}
