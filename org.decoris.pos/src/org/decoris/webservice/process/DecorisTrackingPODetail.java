package org.decoris.webservice.process;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.X_C_Decoris_PreOrder;
import org.decoris.model.X_C_Decoris_PreOrderDtl;
import org.decoris.model.X_C_Decoris_PreOrderHdr;
import org.decoris.webservice.FIF_MainControl;
import org.decoris.webservice.model.FIF_GParam;
import org.decoris.webservice.model.FIF_ModelTrackingPODetail;
import org.decoris.webservice.model.X_M_Fifapps_Supplier;
import org.decoris.webservice.model.X_m_fifapps_ws_tmp;

import com.google.gson.Gson;


/**
 * 
 * @author Tegar N
 *
 */
public class DecorisTrackingPODetail extends SvrProcess {

	private Properties ctx = Env.getCtx();
	private int p_suppCode = 0;
	private String p_applNo = "";	
	
	@Override
	protected void prepare() {

		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
				
			else if (name.equals("M_Fifapps_Supplier_ID"))
					p_suppCode = (int)para[i].getParameterAsInt();
				
//			else if (name.equals("M_Fifapps_Offices_ID"))
//					p_officeCode = (int)para[i].getParameterAsInt();
//				
			else if (name.equals("applno"))
					p_applNo = (String)para[i].getParameterAsString();
				
//			else if (name.equals("DocumentNo"))
//					p_orderIdOri = (String)para[i].getParameterAsString();
//				
			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {
				
		StringBuilder msg = new StringBuilder();
		final int M_FIFApps_Source = FIF_GParam.M_FifApps_TrackingDetail_source;
		final String requestMethod = "GET";	
		String msgDtl = "";

		String baseURL = FIF_MainControl.GetBaseURL();
		
	    StringBuilder parameter = new StringBuilder();
	    
	    X_M_Fifapps_Supplier supp = new X_M_Fifapps_Supplier(getCtx(), p_suppCode, get_TrxName()); 
	    
	    parameter.append("suppCode="+supp.getsuppliercode());
	    
//	    if(p_officeCode > 0){
//		    X_M_Fifapps_Offices off = new X_M_Fifapps_Offices(getCtx(), p_officeCode, get_TrxName()); 
//	    	parameter.append("&officeCode="+off.getofficecode());
//	    }
	    
	    if(p_applNo != null && p_applNo != "" && !p_applNo.isEmpty()){
	    	parameter.append("&applNo="+ p_applNo);
	    }
	    
	    StringBuilder suppSQL = new StringBuilder();
	    suppSQL.append("SELECT M_Fifapps_Supplier_ID ");
	    suppSQL.append("FROM M_Fifapps_Supplier ");
	    suppSQL.append("WHERE  M_Fifapps_Supplier_ID = ?");

	    
	    int suppExist = DB.getSQLValueEx(null, suppSQL.toString(), new Object[]{p_suppCode});
	    
	    if(suppExist <= 0){
	    	
	    	return "Tidak Ada Data Untuk Dilakukan Sync";
	    }
	    
	    StringBuilder SQLApplCheck = new StringBuilder();
	    SQLApplCheck.append("SELECT ApplNo ");
	    SQLApplCheck.append(" FROM C_Decoris_PreOrderHdr");
	    SQLApplCheck.append(" WHERE  AD_Client_ID = ?");
	    SQLApplCheck.append(" AND  ApplNo = '"+ p_applNo + "'");

	    String applExist = DB.getSQLValueStringEx(null, SQLApplCheck.toString(), new Object[]{getAD_Client_ID()});

	    if(applExist == "" || applExist == null ||applExist.isEmpty() ){	
	    	return "Tidak Ada Data Untuk Dilakukan Sync";
	    }
 
//	    if(p_orderIdOri != null && p_orderIdOri != "" && !p_orderIdOri.isEmpty()){
//		    parameter.append("&orderIdOri="+p_orderIdOri);
//	    }    
	    
	    URL url = new URL(baseURL +FIF_GParam.TrackingDetailURL+ "?"+parameter.toString());

	    cleanTable(M_FIFApps_Source);
	    try {
	    	Gson gson = new Gson();
	        FIF_ModelTrackingPODetail[] datas = gson.fromJson(FIF_MainControl.syncDataRun(url,requestMethod), FIF_ModelTrackingPODetail[].class);
	        for (FIF_ModelTrackingPODetail data : datas) {
	        	
	        	//Insert to table X_m_fifapps_ws_tmp 	            	 
	            X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    		tmp.setClientOrg(0, 0);
	    		tmp.setm_fifapps_source(M_FIFApps_Source);
	    		tmp.setm_fifapps_param_01(data.apDocStatus);
	    		tmp.setm_fifapps_param_02(data.apTotalAmount.toString());
	    		tmp.setm_fifapps_param_03(data.applDate);
	    		tmp.setm_fifapps_param_04(data.applNo);
	    		tmp.setm_fifapps_param_05(data.approveBy);
	    		tmp.setm_fifapps_param_06(data.birthDate);
	    		tmp.setm_fifapps_param_07(data.birthPlace);
	    		tmp.setm_fifapps_param_08(data.bpkbName);
	    		tmp.setm_fifapps_param_09(data.bussUnit);
	    		tmp.setm_fifapps_param_10(data.cancelReason);
	    		tmp.setm_fifapps_param_11(data.contractActiveDate);
	    		tmp.setm_fifapps_param_12(data.contractNo);
	    		tmp.setm_fifapps_param_13(data.custAddr);
	    		tmp.setm_fifapps_param_14(data.custCity);
	    		tmp.setm_fifapps_param_15(data.custDp.toString());
	    		tmp.setm_fifapps_param_16(data.custFixPhArea);
	    		tmp.setm_fifapps_param_17(data.custFixPhone);
	    		tmp.setm_fifapps_param_18(data.custKec);
	    		tmp.setm_fifapps_param_19(data.custKel);
	    		tmp.setm_fifapps_param_20(data.custMobPhone);
	    		tmp.setm_fifapps_param_21(data.custMobPhone2);
	    		tmp.setm_fifapps_param_22(data.custName);
	    		tmp.setm_fifapps_param_23(data.custNo);
	    		tmp.setm_fifapps_param_24(data.custProv);
	    		tmp.setm_fifapps_param_25(data.custRt);
	    		tmp.setm_fifapps_param_26(data.custRw);
	    		tmp.setm_fifapps_param_27(data.danaSudahCair);
	    		tmp.setm_fifapps_param_28(data.janjiSurvey);
	    		tmp.setm_fifapps_param_29(data.monthInst.toString());
	    		tmp.setm_fifapps_param_30(data.noka);
	    		tmp.setm_fifapps_param_31(data.nosin);
	    		tmp.setm_fifapps_param_32(data.objBrand);
	    		tmp.setm_fifapps_param_33(data.objCode);
	    		tmp.setm_fifapps_param_34(data.objDesc);
	    		tmp.setm_fifapps_param_35(data.objModel);
	    		tmp.setm_fifapps_param_36(data.objPrice.toString());
	    		System.out.println("Object price : "+data.objPrice);
	    		tmp.setm_fifapps_param_37(data.objType);
	    		tmp.setm_fifapps_param_38(data.offFixPhArea);
	    		tmp.setm_fifapps_param_39(data.offFixPhExt);
	    		tmp.setm_fifapps_param_40(data.offFixPhone);
	    		tmp.setm_fifapps_param_41(data.officeCode);
	    		tmp.setm_fifapps_param_42(data.orderId);
	    		tmp.setm_fifapps_param_43(data.poDate);
	    		tmp.setm_fifapps_param_44(data.poNo);
	    		tmp.setm_fifapps_param_45(data.pos);
	    		tmp.setm_fifapps_param_46(data.servOfficeCode);
	    		tmp.setm_fifapps_param_47(data.state);
	    		tmp.setm_fifapps_param_48(data.statusOrder);
	    		tmp.setm_fifapps_param_49(data.suplName);
	    		tmp.setm_fifapps_param_50(data.suppCode);
	    		tmp.setm_fifapps_param_51(data.tenor.toString());
	    		tmp.setm_fifapps_param_52(data.timeService);
	    		tmp.setm_fifapps_param_53(data.warna);
	    		tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    		tmp.setm_process_by(100);
	    		tmp.saveEx();
	    		//count++;
	            
	        }
	            
	        msgDtl = createNewRecord();
	        if(msgDtl.equals("OK")){
		        //cleanTable(M_FIFApps_Source);
	        	msg.append("Tracking Berhasil, Silakan Cek Window PO Header");
	        }else{
	        	msg.append("Tracking Error, Silakan Hub. Administrator");
	        }

            	
	    } catch (Exception ex) {
	        System.out.println("Err "+ex.getMessage());
	        msg.append("Err "+ex.getMessage());    
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
			 SQLTrackingHeader.append("a.m_fifapps_param_02,");
			 SQLTrackingHeader.append("a.m_fifapps_param_03,");
			 SQLTrackingHeader.append("a.m_fifapps_param_04,");
			 SQLTrackingHeader.append("a.m_fifapps_param_05,");
			 SQLTrackingHeader.append("a.m_fifapps_param_06,");
			 SQLTrackingHeader.append("a.m_fifapps_param_07,");
			 SQLTrackingHeader.append("a.m_fifapps_param_08,");
			 SQLTrackingHeader.append("a.m_fifapps_param_09,");
			 SQLTrackingHeader.append("a.m_fifapps_param_10,");
			 SQLTrackingHeader.append("a.m_fifapps_param_11,");
			 SQLTrackingHeader.append("a.m_fifapps_param_12,");
			 SQLTrackingHeader.append("a.m_fifapps_param_13,");
			 SQLTrackingHeader.append("a.m_fifapps_param_14,");
			 SQLTrackingHeader.append("a.m_fifapps_param_15,");
			 SQLTrackingHeader.append("a.m_fifapps_param_16,");
			 SQLTrackingHeader.append("a.m_fifapps_param_17,");
			 SQLTrackingHeader.append("a.m_fifapps_param_18,");
			 SQLTrackingHeader.append("a.m_fifapps_param_19,");
			 SQLTrackingHeader.append("a.m_fifapps_param_20,");
			 SQLTrackingHeader.append("a.m_fifapps_param_21,");
			 SQLTrackingHeader.append("a.m_fifapps_param_22,");
			 SQLTrackingHeader.append("a.m_fifapps_param_23,");
			 SQLTrackingHeader.append("a.m_fifapps_param_24,");
			 SQLTrackingHeader.append("a.m_fifapps_param_25,");
			 SQLTrackingHeader.append("a.m_fifapps_param_26,");
			 SQLTrackingHeader.append("a.m_fifapps_param_27,");
			 SQLTrackingHeader.append("a.m_fifapps_param_28,");
			 SQLTrackingHeader.append("a.m_fifapps_param_29,");
			 SQLTrackingHeader.append("a.m_fifapps_param_30,");
			 SQLTrackingHeader.append("a.m_fifapps_param_31,");
			 SQLTrackingHeader.append("a.m_fifapps_param_32,");
			 SQLTrackingHeader.append("a.m_fifapps_param_33,");
			 SQLTrackingHeader.append("a.m_fifapps_param_34,");
			 SQLTrackingHeader.append("a.m_fifapps_param_35,");
			 SQLTrackingHeader.append("a.m_fifapps_param_36,");
			 SQLTrackingHeader.append("a.m_fifapps_param_37,");
			 SQLTrackingHeader.append("a.m_fifapps_param_38,");
			 SQLTrackingHeader.append("a.m_fifapps_param_39,");
			 SQLTrackingHeader.append("a.m_fifapps_param_40,");
			 SQLTrackingHeader.append("a.m_fifapps_param_41,");
			 SQLTrackingHeader.append("a.m_fifapps_param_42,");
			 SQLTrackingHeader.append("a.m_fifapps_param_43,");
			 SQLTrackingHeader.append("a.m_fifapps_param_44,");
			 SQLTrackingHeader.append("a.m_fifapps_param_45,");
			 SQLTrackingHeader.append("a.m_fifapps_param_46,");
			 SQLTrackingHeader.append("a.m_fifapps_param_47,");
			 SQLTrackingHeader.append("a.m_fifapps_param_48,");
			 SQLTrackingHeader.append("a.m_fifapps_param_49,");
			 SQLTrackingHeader.append("a.m_fifapps_param_50,");
			 SQLTrackingHeader.append("a.m_fifapps_param_51,");
			 SQLTrackingHeader.append("a.m_fifapps_param_52,");
			 SQLTrackingHeader.append("a.m_fifapps_param_53, ");
			 SQLTrackingHeader.append(" a.AD_Client_ID,");
			 SQLTrackingHeader.append(" a.AD_Org_ID, ");
			 SQLTrackingHeader.append(" c.C_Decoris_PreOrder_ID, ");
			 SQLTrackingHeader.append(" c.documentno, ");
			 SQLTrackingHeader.append(" b.C_Decoris_PreOrderHdr_ID ");
			 SQLTrackingHeader.append(" FROM m_fifapps_ws_tmp a");
			 SQLTrackingHeader.append(" INNER JOIN C_Decoris_PreOrderHdr b ON a.m_fifapps_param_04 = b.applNo");
			 SQLTrackingHeader.append(" INNER JOIN C_Decoris_PreOrder c ON b.C_Decoris_PreOrder_id = c.C_Decoris_PreOrder_id");
			 SQLTrackingHeader.append(" WHERE EXISTS(");
			 SQLTrackingHeader.append(" 	SELECT 1 FROM C_Decoris_PreOrder d ");
			 SQLTrackingHeader.append(" 	WHERE a.m_fifapps_param_04 = b.applNo ");
			 SQLTrackingHeader.append(" 	AND c.updatestatus = '1' )");
			 //SQLTrackingHeader.append(" AND a.m_fifapps_status_process = 'N' ");
			 //SQLTrackingHeader.append(" AND a.m_fifapps_param_44 IS NOT NULL ");
			 SQLTrackingHeader.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLTrackingHeader.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_TrackingDetail_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						int C_Decoris_PreOrderDtl_ID = 0;
						int C_Decoris_PreOrderHdr_ID = rs.getInt(58);
						X_C_Decoris_PreOrderHdr hdr = null;
							
						C_Decoris_PreOrderDtl_ID = getPreOrdDtl_ID(rs.getInt(54), rs.getString(32),rs.getString(33));

						
						if(C_Decoris_PreOrderDtl_ID <= 0){
							
							C_Decoris_PreOrderDtl_ID = 0;
						}
						
						if(C_Decoris_PreOrderHdr_ID > 0 ){
							
							hdr = new X_C_Decoris_PreOrderHdr(getCtx(), C_Decoris_PreOrderHdr_ID, null);
						}
						
						
						if(hdr == null){
							
							return "Error Header Tidak Ditemukan";
						}
						
						
						X_C_Decoris_PreOrderDtl preOrdDtl = new X_C_Decoris_PreOrderDtl(ctx, C_Decoris_PreOrderDtl_ID, null);
						preOrdDtl.setClientOrg(hdr.getAD_Client_ID(),rs.getInt(55));
						preOrdDtl.setapdocstatus(rs.getString(1));
						preOrdDtl.setaptotalamount(StringToBD(rs.getString(2)));
						preOrdDtl.setappldate(rs.getString(3));
						preOrdDtl.setapplno(rs.getString(4));
						preOrdDtl.setapproveby(rs.getString(5));
						preOrdDtl.setbirthdate(rs.getString(6));
						preOrdDtl.setbirthplace(rs.getString(7));
						preOrdDtl.setbpkbname(rs.getString(8));
						preOrdDtl.setbussunit(rs.getString(9));
						preOrdDtl.setcancelreason(rs.getString(10));
						preOrdDtl.setcontractactivedate(rs.getString(11));
						preOrdDtl.setcontractno(rs.getString(12));
						preOrdDtl.setcustaddr(rs.getString(13));
						preOrdDtl.setcustcity(rs.getString(14));
						preOrdDtl.setcustdp(StringToBD(rs.getString(15)));
						preOrdDtl.setcustfixpharea(rs.getString(16));
						preOrdDtl.setcustfixphone(rs.getString(17));
						preOrdDtl.setcustkec(rs.getString(18));
						preOrdDtl.setcustkel(rs.getString(19));
						preOrdDtl.setcustmobphone(rs.getString(20));
						preOrdDtl.setcustmobphone2(rs.getString(21));
						preOrdDtl.setcustname(rs.getString(22));
						preOrdDtl.setcustno(rs.getString(23));
						preOrdDtl.setcustprov(rs.getString(24));
						preOrdDtl.setcustrt(rs.getString(25));
						preOrdDtl.setcustrw(rs.getString(26));
						preOrdDtl.setdanasudahcair(rs.getString(27));
						preOrdDtl.setjanjisurvey(rs.getString(28));
						preOrdDtl.setmonthinst(StringToBD(rs.getString(29)));
						preOrdDtl.setnoka(rs.getString(30));
						preOrdDtl.setnosin(rs.getString(31));
						preOrdDtl.setobjbrand(rs.getString(32));
						preOrdDtl.setobjcode(rs.getString(33));
						preOrdDtl.setobjdesc(rs.getString(34));
						preOrdDtl.setobjmodel(rs.getString(35));
						preOrdDtl.setobjprice(StringToBD(rs.getString(36)));
						preOrdDtl.setobjtype(rs.getString(37));
						preOrdDtl.setofffixpharea(rs.getString(38));
						preOrdDtl.setofffixphext(rs.getString(39));
						preOrdDtl.setofffixphone(rs.getString(40));
						preOrdDtl.setofficecode(rs.getString(41));
						preOrdDtl.setorderid(rs.getString(42));
						preOrdDtl.setpodate(rs.getString(43));
						//preOrdDtl.setpono(rs.getString(44));
						preOrdDtl.setpono(hdr.getpono());
						preOrdDtl.setpos(rs.getString(45));
						preOrdDtl.setservofficecode(rs.getString(46));
						if(rs.getString(47).equalsIgnoreCase("Order DIS")){
							preOrdDtl.setstate("Order DECORIS");
						}else{
							preOrdDtl.setstate(rs.getString(47));

						}
						if(rs.getString(48).equalsIgnoreCase("Order DIS")){
							preOrdDtl.setstatusorder("Order DECORIS");
						}else{
							preOrdDtl.setstatusorder(rs.getString(48));
						}
						preOrdDtl.setsuplname(rs.getString(49));
						preOrdDtl.setsuppcode(rs.getString(50));
						preOrdDtl.settenor(StringToBD(rs.getString(51)));
						preOrdDtl.settimeservice(rs.getString(52));
						preOrdDtl.setwarna(rs.getString(53));
						preOrdDtl.setC_Decoris_PreOrder_ID(rs.getInt(56));
						preOrdDtl.saveEx();
	
	
						int C_Decoris_PreOrder_ID = rs.getInt(56);
						X_C_Decoris_PreOrder preOrd = new X_C_Decoris_PreOrder(ctx, C_Decoris_PreOrder_ID, null);
						preOrd.set_CustomColumn("UpdateStatus", 0);
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
	
	
	protected int getPreOrdDtl_ID(int AD_Client_ID, String objBrand, String objCode){
		int rs = 0;
		
		
		StringBuilder SQLGetPreOrdHdr = new StringBuilder();
		
		SQLGetPreOrdHdr.append(" SELECT C_Decoris_PreOrderDtl_ID ");
		SQLGetPreOrdHdr.append(" FROM C_Decoris_PreOrderDtl ");
		SQLGetPreOrdHdr.append(" WHERE AD_Client_ID = ? ");
		SQLGetPreOrdHdr.append(" AND objBrand = ? ");
		SQLGetPreOrdHdr.append(" AND objCode = ? ");


		rs = DB.getSQLValueEx(get_TrxName(), SQLGetPreOrdHdr.toString(), new Object[]{AD_Client_ID,objBrand,objCode});
		
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
 
 


