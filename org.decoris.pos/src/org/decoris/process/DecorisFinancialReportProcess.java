package org.decoris.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.report.MReport;
import org.compiere.util.DB;

public class DecorisFinancialReportProcess extends SvrProcess{

	
	private int p_AD_Client_ID = 0;
	private int p_AD_Org_ID = 0;
	private int p_C_AcctSchema_ID = 0;
	private int p_C_Period_ID = 0;
	private int p_Pa_Report_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null);
				else if (name.equals("C_Period_ID"))
					p_C_Period_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("AD_Org_ID"))
					p_AD_Org_ID = (int)para[i].getParameterAsInt();
								
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_Pa_Report_ID = getRecord_ID();
	}
		
	
	@Override
	protected String doIt() throws Exception {


		if(p_Pa_Report_ID < 0){
			return "";
		}
		
		MReport report = new MReport(getCtx(), p_Pa_Report_ID, get_TrxName());
		
		p_C_AcctSchema_ID = report.getC_AcctSchema_ID();
		p_AD_Client_ID = report.getAD_Client_ID();
		
		
		int cekfinrpt_hdr = 0;
		
		StringBuilder cek_finrpt_hdr = new StringBuilder();
		cek_finrpt_hdr.append("	SELECT	COUNT(src.c_period_id) - COUNT(exs.c_period_id) AS check_finrpthdr ");
		cek_finrpt_hdr.append("	FROM	finrpt_src src ");
		cek_finrpt_hdr.append(" LEFT JOIN	fact_finrpt exs ON src.ad_client_id	= exs.ad_client_id ");
		cek_finrpt_hdr.append(" 	AND src.c_acctschema_id	= exs.c_acctschema_id ");
		cek_finrpt_hdr.append(" WHERE	src.ad_client_id = ? ");
		cek_finrpt_hdr.append(" AND	src.c_acctschema_id	= ? ");
		cek_finrpt_hdr.append(" AND	src.ad_org_id = ? ");
		
		
		cekfinrpt_hdr = DB.getSQLValueEx(get_TrxName(), cek_finrpt_hdr.toString(), new Object[]{p_AD_Client_ID,p_C_AcctSchema_ID,p_AD_Org_ID});
		
		if(cekfinrpt_hdr > 0){
						
			functionRun("insert_finrpt_hdr", 3, p_AD_Client_ID, p_C_AcctSchema_ID, p_AD_Org_ID, 0);	
			checkRun();
				
		}else{	
			checkRun();
			
		}
		
		
		return "";
	}
	
	
	 private int functionRun(String functionName,int countParam, int AD_Client_ID, int C_AcctSchema_ID, int AD_Org_ID, int C_Period_ID){
		 
		 	String param = "";
		 	if(countParam == 3){
		 		param = "(?,?,?)";
		 	}if(countParam == 4){
		 		param = "(?,?,?,?)";
		 	}
		 
		 
			StringBuilder SQLExFunction = new StringBuilder();
	        SQLExFunction.append("SELECT "+functionName+""+param);
	        int result = 0;
	         
	     	PreparedStatement pstmt = null;
	     	ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLExFunction.toString(), null);
					pstmt.setInt(1, AD_Client_ID);
					pstmt.setInt(2, C_AcctSchema_ID);
					pstmt.setInt(3, AD_Org_ID);
				
					if(countParam == 4){
						
						pstmt.setInt(4, C_Period_ID);
					
					}
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						result = rs.getInt(1);
					}

				} catch (SQLException err) {
					log.log(Level.SEVERE, SQLExFunction.toString(), err);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
				
				
			 return result;
		 }

	 
	 private void checkRunTmp(){
		 
		 StringBuilder cek_finrpt_temp = new StringBuilder();

			int cekfinrpt_tmp = 0;

			
			cek_finrpt_temp.append(" SELECT	COALESCE(COUNT(acct.fact_acct_id),0) AS check_finrpttmp ");
			cek_finrpt_temp.append(" FROM	fact_acct acct ");
			cek_finrpt_temp.append(" WHERE	acct.isreported IS NULL ");
			cek_finrpt_temp.append(" AND	acct.AD_Client_ID					= ? ");
			cek_finrpt_temp.append(" AND	acct.C_AcctSchema_ID				= ? ");
			cek_finrpt_temp.append(" AND	acct.AD_Org_ID						= ? ");
			cek_finrpt_temp.append(" AND	acct.C_Period_ID IN ( SELECT  C_Period_ID ");
			
			cek_finrpt_temp.append(" FROM  finrpt_src ");
			cek_finrpt_temp.append(" WHERE  AD_Client_ID		= ? ");
			cek_finrpt_temp.append(" AND  C_AcctSchema_ID		= ? ");
			cek_finrpt_temp.append(" AND  AD_Org_ID				= ? ");
			cek_finrpt_temp.append(" AND  rk <= ( SELECT  rk ");
			cek_finrpt_temp.append(" 	FROM  finrpt_src ");
			
			cek_finrpt_temp.append(" 	WHERE  AD_Client_ID	= ? ");
			cek_finrpt_temp.append(" 	AND  C_AcctSchema_ID	= ? ");
			cek_finrpt_temp.append(" 	AND  AD_Org_ID			= ? ");
			cek_finrpt_temp.append(" 	AND  C_Period_ID		= ? ) ");
			cek_finrpt_temp.append(" ) ");

			cekfinrpt_tmp = DB.getSQLValueEx(get_TrxName(), cek_finrpt_temp.toString(), 
					new Object[]{p_AD_Client_ID,p_C_AcctSchema_ID,p_AD_Org_ID,p_AD_Client_ID,p_C_AcctSchema_ID,p_AD_Org_ID,
				p_AD_Client_ID,p_C_AcctSchema_ID,p_AD_Org_ID,p_C_Period_ID});			
			
			
			if(cekfinrpt_tmp > 0){
				
				functionRun("insert_finrpt_tmp", 4, p_AD_Client_ID, p_C_AcctSchema_ID, p_AD_Org_ID, p_C_Period_ID);
				functionRun("update_finrpt_line", 4, p_AD_Client_ID, p_C_AcctSchema_ID, p_AD_Org_ID, p_C_Period_ID);
				functionRun("update_factacct_rptflg", 4, p_AD_Client_ID, p_C_AcctSchema_ID, p_AD_Org_ID, p_C_Period_ID);
				functionRun("delete_finrpt_tmp", 4, p_AD_Client_ID, p_C_AcctSchema_ID, p_AD_Org_ID, p_C_Period_ID);

			}	
		
	 }
	 
	 private void checkRun(){
		 
		 int cekfinrpt_line = 0;
			
			StringBuilder cek_finrpt_line = new StringBuilder();
			cek_finrpt_line.append(" SELECT	COUNT(src.account_id) - COUNT(exs.account_id) AS check_finrptline ");
			cek_finrpt_line.append(" FROM	finrptline_src src ");
			cek_finrpt_line.append(" LEFT JOIN	fact_finrptline exs ON src.ad_client_id		= exs.ad_client_id ");
			cek_finrpt_line.append(" 	AND src.ad_org_id		= exs.ad_org_id ");
			cek_finrpt_line.append(" WHERE	src.ad_client_id				= ? ");
			cek_finrpt_line.append(" AND	src.c_acctschema_id				= ? ");
			cek_finrpt_line.append(" AND	src.ad_org_id					= ? ");
			
			
			cekfinrpt_line = DB.getSQLValueEx(get_TrxName(), cek_finrpt_line.toString(), new Object[]{p_AD_Client_ID,p_C_AcctSchema_ID,p_AD_Org_ID});
			
			if(cekfinrpt_line > 0){
				
				functionRun("insert_finrpt_line", 3, p_AD_Client_ID, p_C_AcctSchema_ID, p_AD_Org_ID, 0);	
				checkRunTmp();
				
			}else{
				
				checkRunTmp();
				
			}
		 
	 }
		
}
