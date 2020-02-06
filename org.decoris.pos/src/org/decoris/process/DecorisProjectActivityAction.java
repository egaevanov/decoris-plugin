package org.decoris.process;

import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class DecorisProjectActivityAction extends SvrProcess  {

	
	String p_DocAction = "";
	int p_C_ProjectActivity_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null);
			else if (name.equals("DocAction"))
				
				p_DocAction = (String)para[i].getParameterAsString();				
			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_C_ProjectActivity_ID = getRecord_ID();
		
	}

	@Override
	protected String doIt() throws Exception {

		if(p_DocAction.equalsIgnoreCase(DocAction.ACTION_Approve)){
			
			StringBuilder SQLProjectAct = new StringBuilder();
			
			SQLProjectAct.append("UPDATE C_ProjectActivity ");
			SQLProjectAct.append(" SET DocStatus =  '"+p_DocAction+"' ");
			SQLProjectAct.append(" WHERE AD_Client_ID = ? ");
			SQLProjectAct.append(" AND C_ProjectActivity_ID = ? ");

			DB.executeUpdateEx(SQLProjectAct.toString(), new Object[]{getAD_Client_ID(),p_C_ProjectActivity_ID}, get_TrxName());
			
			
		}
		
		
		return "";
	}
	
	

}
