package org.decoris.process;

import java.util.logging.Level;

import org.compiere.model.MTable;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class DecorisManualUpdate extends SvrProcess {

	
	private String p_AD_Client_ID = "";
	private int p_AD_Table_ID = 0;
	private String p_WhereClause = "";
	private String p_ColumnName = ""; 
	private String p_NewValue = ""; 
	
	
	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null);
			else if (name.equals("AD_Client_ID"))
				
				p_AD_Client_ID = (String)para[i].getParameterAsString();				
			
			else if (name.equals("AD_Table_ID"))
					
				p_AD_Table_ID = (int)para[i].getParameterAsInt();
			
			else if (name.equals("WhereClause"))
				
				p_WhereClause = (String)para[i].getParameterAsString();	
			
			else if (name.equals("ColumnName"))
				
				p_ColumnName = (String)para[i].getParameterAsString();		
			
			else if (name.equals("NewValue"))
				
				p_NewValue = (String)para[i].getParameterAsString();	
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {

		
		if(p_AD_Client_ID != null && p_AD_Table_ID > 0 && p_ColumnName != null && p_NewValue != null){
		
			MTable table = new MTable(getCtx(), p_AD_Table_ID, get_TrxName());
			
			StringBuilder SQLUpdate = new StringBuilder();
			SQLUpdate.append("UPDATE ").append(table.getTableName());
			SQLUpdate.append(" SET ").append(p_ColumnName +"='").append(p_NewValue).append("'");
			SQLUpdate.append(" WHERE AD_Client_ID = ").append(p_AD_Client_ID);
			if(p_WhereClause != null){
				SQLUpdate.append(" AND ").append(table.getTableName()+"_ID IN (").append(p_WhereClause+")");
			}
			
			DB.executeUpdate(SQLUpdate.toString(), get_TrxName());
			
		}
		
		return "Update Sukses";
	}

}
