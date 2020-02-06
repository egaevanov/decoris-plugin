package org.decoris.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * 
 * @author Tegar N
 *
 */

public class MDecorisPOSLine extends X_C_DecorisPOSLine{

	private static final long serialVersionUID = 1L;

	public MDecorisPOSLine(Properties ctx, int C_DecorisPOSLine_ID,String trxName) {
		super(ctx, C_DecorisPOSLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MDecorisPOSLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
}
