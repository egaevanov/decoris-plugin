package org.decoris.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.util.ProcessUtil;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class DecorisPrintBAST extends SvrProcess{

	
	private int p_C_Decoris_PreOrder_ID = 0;
	
	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
				//else if (name.equals("C_Decoris_PreOrder_ID"))
			    //p_C_Decoris_PreOrder_ID = (int)para[i].getParameterAsInt();
				
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_C_Decoris_PreOrder_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		boolean isPassStatus = IsPassStatus(p_C_Decoris_PreOrder_ID);
		String msg = "";
		if(isPassStatus){
			 updateData(p_C_Decoris_PreOrder_ID);
			
			 String trxName = Trx.createTrxName("PrintBAST");
			 MProcess proc = new MProcess(Env.getCtx(), 1000099, trxName);
			 MPInstance instance = new MPInstance(proc,proc.getAD_Process_ID());
			 ProcessInfo pi = new ProcessInfo("Print BAST", 1000099);
			 pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			 
			 ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			 list.add(new ProcessInfoParameter("C_Decoris_PreOrder_ID", p_C_Decoris_PreOrder_ID, null,null, null));
	

			 ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			 list.toArray(pars);
			 pi.setParameter(pars);
			 //
			 Trx trx = Trx.get(trxName, true);
			 trx.commit();
			
			 ProcessUtil.startJavaProcess(Env.getCtx(), pi, Trx.get(trxName,true));
			 
		}else{
			msg = "Dokument BAST Belum Dapat DiProses";
		}
		
		return msg;

				
	}

	
	
	protected int updateData(int C_Decoris_PreOrder_ID){
		 
		StringBuilder SQLExFunction = new StringBuilder();
		
		SQLExFunction.append("SELECT update_print_info_bast_fifapps(?)");
        
        int rslt = 0;
         
     	PreparedStatement pstmt = null;
     	ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(SQLExFunction.toString(), null);
				pstmt.setInt(1, C_Decoris_PreOrder_ID);	
				rs = pstmt.executeQuery();
				while (rs.next()) {
					rslt = rs.getInt(1);
				}

			} catch (SQLException err) {
				log.log(Level.SEVERE, SQLExFunction.toString(), err);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			
		 return rslt;
	 }
	
	
	protected boolean IsPassStatus(int C_Decoris_PreOrder_ID){
		 
		StringBuilder SQLGetParam = new StringBuilder();
		SQLGetParam.append("SELECT description FROM ad_param WHERE value = 'Approve_PreOrder_FIFAPPS'");
		
		StringBuilder StatusPreOrder = new StringBuilder();
		StatusPreOrder.append(" SELECT state ");
		StatusPreOrder.append(" FROM C_Decoris_PreOrderHDr ");
		StatusPreOrder.append(" WHERE C_Decoris_PreOrder_ID = "+ C_Decoris_PreOrder_ID);       
		String POStatus = DB.getSQLValueStringEx(get_TrxName(), StatusPreOrder.toString());
		
		boolean rslt = false;
         
     	PreparedStatement pstmt = null;
     	ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(SQLGetParam.toString(), null);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					
					if(POStatus.trim().toUpperCase().equals(rs.getString(1).trim().toUpperCase())){
						
						rslt = true;
						
					}
					
				}

			} catch (SQLException err) {
				log.log(Level.SEVERE, SQLGetParam.toString(), err);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			
		 return rslt;
	 }
}
