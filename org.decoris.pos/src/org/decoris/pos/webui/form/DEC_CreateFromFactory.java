package org.decoris.pos.webui.form;

import org.compiere.grid.ICreateFrom;
import org.compiere.grid.ICreateFromFactory;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;

public class DEC_CreateFromFactory implements ICreateFromFactory {

	@Override
	public ICreateFrom create(GridTab mTab) {

		String tableName = mTab.getTableName();

		System.out.println(tableName);
		
		if (tableName.equals(MProduct.Table_Name))
			return new WImageUpload(mTab);
		
		return null;
		
		
	}

}
