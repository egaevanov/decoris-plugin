package org.decoris.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MDecorisPresales extends X_C_Decoris_PreSales {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6676320058832467383L;
	protected X_C_Decoris_PreSalesLine[]	m_lines;

	
	public MDecorisPresales(Properties ctx, int C_Decoris_PreSales_ID,
			String trxName) {
		super(ctx, C_Decoris_PreSales_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MDecorisPresales(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	private X_C_Decoris_PreSalesLine[] getLines (String whereClause)
	{
		String whereClauseFinal = "C_Decoris_Presales_ID=? ";
		if (whereClause != null)
			whereClauseFinal += whereClause;
		List<X_C_Decoris_PreSalesLine> list = new Query(getCtx(), X_C_Decoris_PreSalesLine.Table_Name, whereClauseFinal, get_TrxName())
										.setParameters(getC_Decoris_PreSales_ID())
										.setOrderBy(X_C_Decoris_PreSalesLine.COLUMNNAME_C_Decoris_PreSalesLine_ID)
										.list();
		return list.toArray(new X_C_Decoris_PreSalesLine[list.size()]);
	}	//	getLines

	/**
	 * 	Get Invoice Lines
	 * 	@param requery
	 * 	@return lines
	 */
	public X_C_Decoris_PreSalesLine[] getLines (boolean requery)
	{
		if (m_lines == null || m_lines.length == 0 || requery)
			m_lines = getLines(null);
		set_TrxName(m_lines, get_TrxName());
		return m_lines;
	}	//	getLines

	public X_C_Decoris_PreSalesLine[] getLines()
	{
		return getLines(false);
	}	//	getLines
	

}
