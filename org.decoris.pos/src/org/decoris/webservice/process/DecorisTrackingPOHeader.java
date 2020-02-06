package org.decoris.webservice.process;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.X_C_Decoris_PreOrder;
import org.decoris.model.X_C_Decoris_PreOrderHdr;
import org.decoris.webservice.FIF_MainControl;
import org.decoris.webservice.model.FIF_GParam;
import org.decoris.webservice.model.FIF_ModelTrackingPOHeader;
import org.decoris.webservice.model.X_M_Fifapps_Inputsource;
import org.decoris.webservice.model.X_M_Fifapps_Offices;
import org.decoris.webservice.model.X_M_Fifapps_Supplier;
import org.decoris.webservice.model.X_m_fifapps_ws_tmp;

import com.google.gson.Gson;

/**
 * 
 * @author Tegar N
 *
 */
public class DecorisTrackingPOHeader extends SvrProcess{

	private Properties ctx = Env.getCtx();
	private Timestamp p_fromDate = null;
	private Timestamp p_toDate = null;
	private int p_suppCode = 0;
	private int p_officeCode  = 0;
	private int p_sourceInput  = 0;
	
	@Override
	protected void prepare() {
	
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
				
			else if (name.equals("DateOrdered")){
					p_fromDate = (Timestamp)para[i].getParameterAsTimestamp();
					p_toDate = (Timestamp)para[i].getParameter_ToAsTimestamp();
			}
			else if (name.equals("M_Fifapps_Supplier_ID"))
					p_suppCode = (int)para[i].getParameterAsInt();
				
			else if (name.equals("M_Fifapps_Offices_ID"))
					p_officeCode = (int)para[i].getParameterAsInt();
				
			else if (name.equals("M_Fifapps_Inputsource_ID"))
					p_sourceInput = (int)para[i].getParameterAsInt();
			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		
	}

	@Override
	protected String doIt() throws Exception {

		StringBuilder msg = new StringBuilder();
		final int M_FIFApps_Source = FIF_GParam.M_FifApps_TrackingHeader_source;
		final String requestMethod = "GET";
		final String separator = "&";
		String msgHdr = "";
		
	    StringBuilder parameter = new StringBuilder();
	    
	    X_M_Fifapps_Supplier supp = new X_M_Fifapps_Supplier(getCtx(), p_suppCode, get_TrxName()); 
	    
	    String dateFrom = new SimpleDateFormat("yyyy-MM-dd").format(p_fromDate);
	    String dateTo = new SimpleDateFormat("yyyy-MM-dd").format(p_toDate);

	    
	    parameter.append("suppCode="+supp.getsuppliercode()).append(separator);
	    parameter.append("fromDate="+dateFrom.toString()).append(separator);
	    parameter.append("toDate="+dateTo.toString());
	    
	    if(p_officeCode > 0 ){
	    	
		    X_M_Fifapps_Offices off = new X_M_Fifapps_Offices(getCtx(), p_officeCode, get_TrxName()); 
		    parameter.append("&officeCode="+off.getofficecode());
		    
	    }
	    
	    if(p_sourceInput > 0){
	    	
		    X_M_Fifapps_Inputsource inp = new X_M_Fifapps_Inputsource(getCtx(), p_sourceInput, get_TrxName()); 
		    parameter.append("&sourceInput=" + inp.getCode());
	    	
	    }
		
		String baseURL = FIF_MainControl.GetBaseURL();
	    URL url = new URL(baseURL +FIF_GParam.TrackingHeaderURL	+ "?"+parameter);
      

	    try {
	    	Gson gson = new Gson();
	        FIF_ModelTrackingPOHeader[] datas = gson.fromJson(FIF_MainControl.syncDataRun(url,requestMethod), FIF_ModelTrackingPOHeader[].class);
	        for (FIF_ModelTrackingPOHeader data : datas) {
	        	
	        	//Insert to table X_m_fifapps_ws_tmp 	            	 
	            X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    		tmp.setClientOrg(Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
	    		tmp.setm_fifapps_source(M_FIFApps_Source);
	    		tmp.setm_fifapps_param_01(data.applDate);
	    		tmp.setm_fifapps_param_02(data.applNo);
	    		tmp.setm_fifapps_param_03(data.birthDate);
	    		tmp.setm_fifapps_param_04(data.createDate);
	    		tmp.setm_fifapps_param_05(data.custName);
	    		tmp.setm_fifapps_param_06(data.objPrice.toString());
	    		tmp.setm_fifapps_param_07(data.orderIdOri);
	    		tmp.setm_fifapps_param_08(data.poNo);
	    		tmp.setm_fifapps_param_09(data.state);
	    		tmp.setm_fifapps_param_10(data.statusOrder);
	    		tmp.setm_fifapps_param_11(data.suppCode);
	    		tmp.setm_fifapps_param_12(data.timeService);

	    		tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    		tmp.setm_process_by(100);
	    		tmp.saveEx();
	    		
	    		
	        }
	            

	    	msgHdr = createNewRecord();
	        if(msgHdr.equals("OK")){
		        cleanTable(M_FIFApps_Source);
	        	msg.append("Tracking Berhasil, Silakan Cek Window PO Header");
	        }else{
	        	msg.append("Tracking Error, Silakan Hub. Administrator");
	        }
            
            	
	    } catch (Exception ex) {
 	        System.out.println("Err "+ex.getMessage());
	            
	    }
		
			return msg.toString();
	
	}
	
	
	protected void cleanTable(int M_Fifapps_Source){
		
		StringBuilder SQLCleanTable = new StringBuilder();
		SQLCleanTable.append(" DELETE FROM m_fifapps_ws_tmp ");
		SQLCleanTable.append(" WHERE M_Fifapps_Source = "+ M_Fifapps_Source);

		DB.executeUpdate(SQLCleanTable.toString(), null);
	}
	
	public String createNewRecord(){							
		 String msg = ""; 
		 		 
			 //Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_city 
			 StringBuilder SQLTrackingHeader = new StringBuilder();
			 SQLTrackingHeader.append("SELECT a.m_fifapps_param_01,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_02,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_03,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_04,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_05,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_06,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_07,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_08,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_09,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_10,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_11,");
			 SQLTrackingHeader.append(" a.m_fifapps_param_12,");
			 SQLTrackingHeader.append(" a.AD_Client_ID,");
			 SQLTrackingHeader.append(" a.AD_Org_ID, ");
			 SQLTrackingHeader.append(" b.C_Decoris_PreOrder_ID, ");
			 SQLTrackingHeader.append(" b.documentno ");
			 SQLTrackingHeader.append(" FROM m_fifapps_ws_tmp a");
			 SQLTrackingHeader.append(" INNER JOIN C_Decoris_PreOrder b ON a.m_fifapps_param_07 = b.documentno");
			 SQLTrackingHeader.append(" WHERE a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLTrackingHeader.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_TrackingHeader_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						Integer C_Decoris_PreOrderHdr_ID = 0;
						String statusOrd = rs.getString(10);
						if(statusOrd == null){
							statusOrd = "";
						}
						
						C_Decoris_PreOrderHdr_ID = getPreOrdHdr_ID(rs.getInt(13), rs.getInt(15));
						
						if(C_Decoris_PreOrderHdr_ID <= 0 || C_Decoris_PreOrderHdr_ID == null){
							
							C_Decoris_PreOrderHdr_ID = 0;
						}
						
						X_C_Decoris_PreOrderHdr preOrdHdr = new X_C_Decoris_PreOrderHdr(ctx, C_Decoris_PreOrderHdr_ID, null);
						preOrdHdr.setClientOrg(rs.getInt(13),rs.getInt(14));
						preOrdHdr.setappldate(rs.getString(1));
						preOrdHdr.setapplno(rs.getString(2));
						preOrdHdr.setbirthdate(rs.getString(3));
						preOrdHdr.setcustname(rs.getString(5));
						preOrdHdr.setobjprice(StringToBD(rs.getString(6)));
						preOrdHdr.setorderidori(rs.getString(7));
						preOrdHdr.setpono(rs.getString(8));
						System.out.println(rs.getString(9));
						if(rs.getString(9).equalsIgnoreCase("Order DIS") ){
							preOrdHdr.setstate("Order DECORIS");
						}else{
							preOrdHdr.setstate(rs.getString(9));
						}
						preOrdHdr.setstatusorder(rs.getString(10));
						preOrdHdr.setsuppcode(rs.getString(11));
						preOrdHdr.settimeservice(rs.getString(12));
						preOrdHdr.setC_Decoris_PreOrder_ID(rs.getInt(15));
						preOrdHdr.saveEx();
	
						int C_Decoris_PreOrder_ID = rs.getInt(15);
						X_C_Decoris_PreOrder preOrd = new X_C_Decoris_PreOrder(ctx, C_Decoris_PreOrder_ID, null);
						preOrd.set_ValueNoCheck("pono", preOrdHdr.getpono());
						preOrd.set_CustomColumn("UpdateStatus", 1);
						preOrd.saveEx();
													
					}
					msg= "OK";

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLTrackingHeader.toString(), e);
					msg= "Error";
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
				
				return msg;

		 }
	 
	protected int getPreOrdHdr_ID(int AD_Client_ID, int C_Decoris_PreOrder_ID){
		int rs = 0;
		
		StringBuilder SQLGetPreOrdHdr = new StringBuilder();
		
		SQLGetPreOrdHdr.append(" SELECT C_Decoris_PreOrderHdr_ID ");
		SQLGetPreOrdHdr.append(" FROM C_Decoris_PreOrderHdr ");
		SQLGetPreOrdHdr.append(" WHERE AD_Client_ID = ? ");
		SQLGetPreOrdHdr.append(" AND C_Decoris_PreOrder_ID = ? ");

		rs = DB.getSQLValueEx(get_TrxName(), SQLGetPreOrdHdr.toString(), new Object[]{AD_Client_ID,C_Decoris_PreOrder_ID});
		
		return rs;
	} 
	 
	protected BigDecimal StringToBD(String value){
		 BigDecimal rs = Env.ZERO;
		 Double dsub = 0.00;
		
		 if(value == null){
			 value = "0";
		 }
		 dsub = Double.valueOf(value.replaceAll(",", ""));
		 rs = BigDecimal.valueOf(dsub);

		 return rs;
	 }
}
