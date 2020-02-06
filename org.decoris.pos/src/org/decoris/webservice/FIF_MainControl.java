package org.decoris.webservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.decoris.model.X_C_Decoris_PreOrder;
import org.decoris.model.X_C_Decoris_PreOrderLine;
import org.decoris.webservice.model.FIF_GParam;
import org.decoris.webservice.model.FIF_GVariable;
import org.decoris.webservice.model.FIF_ModelTokenAccess;
import org.decoris.webservice.model.X_M_Fifapps_Bussunit;
import org.decoris.webservice.model.X_M_Fifapps_City;
import org.decoris.webservice.model.X_M_Fifapps_Education;
import org.decoris.webservice.model.X_M_Fifapps_Gender;
import org.decoris.webservice.model.X_M_Fifapps_Housestatus;
import org.decoris.webservice.model.X_M_Fifapps_Inputsource;
import org.decoris.webservice.model.X_M_Fifapps_Kecamatan;
import org.decoris.webservice.model.X_M_Fifapps_Kelurahan;
import org.decoris.webservice.model.X_M_Fifapps_Maritalstatus;
import org.decoris.webservice.model.X_M_Fifapps_Occupations;
import org.decoris.webservice.model.X_M_Fifapps_Offices;
import org.decoris.webservice.model.X_M_Fifapps_Prov;
import org.decoris.webservice.model.X_M_Fifapps_Supplier;
import org.decoris.webservice.model.X_M_Fifapps_Zipcode;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_MainControl {
	
	private static CLogger log = CLogger.getCLogger(FIF_MainControl.class);

	
	public static String syncDataRun(URL url,String Method){

		 try {

			 	String token = getToken();
			 
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod(Method);
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "bearer "+token);
	            conn.setConnectTimeout(15 * 1000);
	            conn.setReadTimeout(20 * 1000);

	            if(conn.getResponseCode() == 500){
					throw new RuntimeException("Tidak Ada Data Untuk Dilakukan Sync");
	            }
	            
				if (conn.getResponseCode() == 400 || conn.getResponseCode() == 401) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;

				while ((output = br.readLine()) != null) {
					System.out.println(output);
		             return output;
				}

				conn.disconnect();
				return null;

		  }catch (MalformedURLException e) {
	          return e.getMessage();
		  }catch (IOException e) {
			  return e.getMessage();
		  }
	    
	 }
	
	
	public static String submitDataRun(Properties ctx,int C_Decoris_PreOrder_ID, String requestMethod,StringBuilder parameter,String ObjType, String ObjTypeFIF) {
	       
	 	 String token = getToken();
	 	 String rs = "";
	 	 String ret = "";
		
		 final int p_Order_ID = 1;
  	   
		 
		 X_C_Decoris_PreOrder preOrd = new X_C_Decoris_PreOrder(ctx, C_Decoris_PreOrder_ID, null);
		 X_M_Fifapps_Offices offices = new X_M_Fifapps_Offices(ctx, preOrd.getM_Fifapps_Offices_ID(), null);
		 X_M_Fifapps_Zipcode Zipcode = new X_M_Fifapps_Zipcode(ctx,preOrd.getM_Fifapps_Zipcode_ID(),null);
		 X_M_Fifapps_Zipcode DomZipcode = new X_M_Fifapps_Zipcode(ctx,preOrd.getM_Zipcode_Dom_ID(),null);

		 X_M_Fifapps_Supplier supplier = new X_M_Fifapps_Supplier(ctx,preOrd.getM_Fifapps_Supplier_ID(),null);
		 X_M_Fifapps_Housestatus house = new X_M_Fifapps_Housestatus(ctx, preOrd.getM_Fifapps_Housestatus_ID(), null);
		 X_M_Fifapps_Maritalstatus marital = new X_M_Fifapps_Maritalstatus(ctx, preOrd.getM_Fifapps_Maritalstatus_ID(), null);
		 X_M_Fifapps_Inputsource Inputsource = new X_M_Fifapps_Inputsource(ctx, preOrd.getM_Fifapps_Inputsource_ID(),null);
		 X_M_Fifapps_Gender Gender = new X_M_Fifapps_Gender(ctx, preOrd.getM_Fifapps_Gender_ID(),null);
		 X_M_Fifapps_Education Education = new X_M_Fifapps_Education(ctx, preOrd.getM_Fifapps_Education_ID(),null);
		 X_M_Fifapps_Bussunit Bussunit = new X_M_Fifapps_Bussunit(ctx, preOrd.getM_Fifapps_Bussunit_ID(),null);
		 X_M_Fifapps_Occupations Ocupation = new X_M_Fifapps_Occupations(ctx, preOrd.getM_Fifapps_Occupations_ID(),null);

		 
		 int M_Fifapps_City_ID = getFifTableID(X_M_Fifapps_City.Table_Name, "cityCode" , Zipcode.getcitycode());
		 int M_Fifapps_Prov_ID = getFifTableID(X_M_Fifapps_Prov.Table_Name, "provCode" , Zipcode.getprovcode());
		 int M_Fifapps_Kec_ID = getFifTableID(X_M_Fifapps_Kecamatan.Table_Name, "kecCode" , Zipcode.getkeccode());
		 int M_Fifapps_Kel_ID = getFifTableID(X_M_Fifapps_Kelurahan.Table_Name, "kelCode" , Zipcode.getkelcode());

		 int Dom_M_Fifapps_City_ID = getFifTableID(X_M_Fifapps_City.Table_Name, "cityCode" , DomZipcode.getcitycode());
		 int Dom_M_Fifapps_Prov_ID = getFifTableID(X_M_Fifapps_Prov.Table_Name, "provCode" , DomZipcode.getprovcode());
		 int Dom_M_Fifapps_Kec_ID = getFifTableID(X_M_Fifapps_Kecamatan.Table_Name, "kecCode" , DomZipcode.getkeccode());
		 int Dom_M_Fifapps_Kel_ID = getFifTableID(X_M_Fifapps_Kelurahan.Table_Name, "kelCode" , DomZipcode.getkelcode());

		 
		 X_M_Fifapps_City City = new X_M_Fifapps_City(ctx,M_Fifapps_City_ID,null);
		 X_M_Fifapps_Kecamatan Kecamatan = new X_M_Fifapps_Kecamatan(ctx,M_Fifapps_Kec_ID,null);
		 X_M_Fifapps_Kelurahan Kelurahan = new X_M_Fifapps_Kelurahan(ctx,M_Fifapps_Kel_ID,null);
		 X_M_Fifapps_Prov Prov = new X_M_Fifapps_Prov(ctx,M_Fifapps_Prov_ID,null);
		 
		 X_M_Fifapps_City DomCity = new X_M_Fifapps_City(ctx,Dom_M_Fifapps_City_ID,null);
		 X_M_Fifapps_Kecamatan DomKecamatan = new X_M_Fifapps_Kecamatan(ctx,Dom_M_Fifapps_Kec_ID,null);
		 X_M_Fifapps_Kelurahan DomKelurahan = new X_M_Fifapps_Kelurahan(ctx,Dom_M_Fifapps_Kel_ID,null);
		 X_M_Fifapps_Prov DomProv = new X_M_Fifapps_Prov(ctx,Dom_M_Fifapps_Prov_ID,null);
		 
		 
		 MBPartner bp = new MBPartner(ctx, preOrd.getC_BPartner_ID(), null);
		 JsonObject jsonObjData = new JsonObject();
		 
         jsonObjData.addProperty(FIF_GVariable.orderId, p_Order_ID);
         jsonObjData.addProperty(FIF_GVariable.salesId, "");
         jsonObjData.addProperty(FIF_GVariable.dealerId, supplier.getsuppliercode().toString());
         jsonObjData.addProperty(FIF_GVariable.dealerIdFIF, supplier.getsuppliercode().toString());
         jsonObjData.addProperty(FIF_GVariable.branchId, offices.getofficecode().toString());
         jsonObjData.addProperty(FIF_GVariable.aplikasiId, "");
         jsonObjData.addProperty(FIF_GVariable.promoId, "");
         jsonObjData.addProperty(FIF_GVariable.custName, bp.getName().toString());
         jsonObjData.addProperty(FIF_GVariable.birthPlace, preOrd.getbirthplace().toString());
         jsonObjData.addProperty(FIF_GVariable.birthDate, convertDate(preOrd.getBirthday(),false));
         jsonObjData.addProperty(FIF_GVariable.custAddress,preOrd.getAddress1().toString());
         jsonObjData.addProperty(FIF_GVariable.custRT, preOrd.getcustrt().toString());
         jsonObjData.addProperty(FIF_GVariable.custRW, preOrd.getcustrw().toString());
         jsonObjData.addProperty(FIF_GVariable.custZip, Zipcode.getzipcode().toString());
         jsonObjData.addProperty(FIF_GVariable.custKelCode, Kelurahan.getkelcode().toString());
         jsonObjData.addProperty(FIF_GVariable.custKel, Kelurahan.getkelurahan().toString());
         jsonObjData.addProperty(FIF_GVariable.custKecCode, Kecamatan.getkeccode().toString());
         jsonObjData.addProperty(FIF_GVariable.custKec, Kecamatan.getkecamatan().toString());
         jsonObjData.addProperty(FIF_GVariable.custCityCode, City.getcitycode().toString());
         jsonObjData.addProperty(FIF_GVariable.custCity, City.getCity().toString());
         jsonObjData.addProperty(FIF_GVariable.custProvCode, Prov.getprovcode().toString());
         jsonObjData.addProperty(FIF_GVariable.custProv, Prov.getprovinsi().toString());
         jsonObjData.addProperty(FIF_GVariable.custSubZIP, Zipcode.getsubzipcode().toString());
         jsonObjData.addProperty(FIF_GVariable.custId, "");
         jsonObjData.addProperty(FIF_GVariable.custPhoneArea, preOrd.getcustphonearea().toString()); //
         jsonObjData.addProperty(FIF_GVariable.custPhone, preOrd.getcustphone().toString()); //
         jsonObjData.addProperty(FIF_GVariable.custOffPhArea, preOrd.getcustoffpharea().toString());//
         jsonObjData.addProperty(FIF_GVariable.custOfficePhone, preOrd.getcustofficephone().toString());//
         jsonObjData.addProperty(FIF_GVariable.custMobPhone1, preOrd.getPhone().toString());
         jsonObjData.addProperty(FIF_GVariable.custMobPhone2, preOrd.getPhone2().toString());//
         jsonObjData.addProperty(FIF_GVariable.objectType, ObjType);
         jsonObjData.addProperty(FIF_GVariable.objectTypeFIF, ObjTypeFIF);
         jsonObjData.addProperty(FIF_GVariable.spk, "");
         jsonObjData.addProperty(FIF_GVariable.servOffCode, "");
         jsonObjData.addProperty(FIF_GVariable.platform, "K");
         jsonObjData.addProperty(FIF_GVariable.statusRumah, house.gethousestat().toString());
         jsonObjData.addProperty(FIF_GVariable.pendidikan, Education.getedutype().toString());
         jsonObjData.addProperty(FIF_GVariable.statusPernikahan, marital.getmaritalstat().toString());
         jsonObjData.addProperty(FIF_GVariable.sourceInput, Inputsource.getCode().toString());
         jsonObjData.addProperty(FIF_GVariable.dpAmt, preOrd.getdpamt());
         jsonObjData.addProperty(FIF_GVariable.coyId, "01");
         jsonObjData.addProperty(FIF_GVariable.statusOrder, "NW");
         jsonObjData.addProperty(FIF_GVariable.createdBy, "SYSTEM");
         jsonObjData.addProperty(FIF_GVariable.createdDate, convertDate(preOrd.getCreated(), true));
         jsonObjData.addProperty(FIF_GVariable.updateBy, "");
         jsonObjData.addProperty(FIF_GVariable.updateDate, convertDate(preOrd.getUpdated(), true));
         jsonObjData.addProperty(FIF_GVariable.custNo, "");
         jsonObjData.addProperty(FIF_GVariable.orderDate, convertDate(preOrd.getDateOrdered(), false));
         jsonObjData.addProperty(FIF_GVariable.sex, Gender.getgenderid().toString());
         jsonObjData.addProperty(FIF_GVariable.top, preOrd.gettop());
         jsonObjData.addProperty(FIF_GVariable.actualOCPT, Ocupation.getocptcode().toString());
         jsonObjData.addProperty(FIF_GVariable.transactionId, "");
         jsonObjData.addProperty(FIF_GVariable.virtualAccount, "");
         jsonObjData.addProperty(FIF_GVariable.comments, preOrd.getComments().toString());
         jsonObjData.addProperty(FIF_GVariable.indentStatus, "1");
         jsonObjData.addProperty(FIF_GVariable.bussUnit, Bussunit.getbussunitcode().toString());
         jsonObjData.addProperty(FIF_GVariable.flagMailSend, "0");
         jsonObjData.addProperty(FIF_GVariable.flagMailEskalasi, "0");
         jsonObjData.addProperty(FIF_GVariable.angsuran, preOrd.getangsuran());
         jsonObjData.addProperty(FIF_GVariable.csId, "");
         jsonObjData.addProperty(FIF_GVariable.regionalId, "");
         jsonObjData.addProperty(FIF_GVariable.orderIdORI, preOrd.getDocumentNo().toString());
         jsonObjData.addProperty(FIF_GVariable.runSchdlr, "N");
         jsonObjData.addProperty(FIF_GVariable.adminFee, preOrd.getadminfee());
         jsonObjData.addProperty(FIF_GVariable.custOffExt,preOrd.getcustoffext());
         jsonObjData.addProperty(FIF_GVariable.mothersName, preOrd.getmothername());
         jsonObjData.addProperty(FIF_GVariable.emailCust, "");
         jsonObjData.addProperty(FIF_GVariable.flagSyncCrm, "");
         jsonObjData.addProperty(FIF_GVariable.domAddress, preOrd.getAddress1().toString());
         jsonObjData.addProperty(FIF_GVariable.domRt, preOrd.getdomrt().toString());
         jsonObjData.addProperty(FIF_GVariable.domRw, preOrd.getdomrw().toString());
         jsonObjData.addProperty(FIF_GVariable.domZip, DomZipcode.getzipcode().toString());
         jsonObjData.addProperty(FIF_GVariable.domKelCode, DomKelurahan.getkelcode().toString());
         jsonObjData.addProperty(FIF_GVariable.domKel, DomKelurahan.getkelurahan().toString());
         jsonObjData.addProperty(FIF_GVariable.domKecCode, DomKecamatan.getkeccode().toString());
         jsonObjData.addProperty(FIF_GVariable.domKec, DomKecamatan.getkecamatan().toString());
         jsonObjData.addProperty(FIF_GVariable.domCityCode, DomCity.getcitycode().toString());
         jsonObjData.addProperty(FIF_GVariable.domCity, DomCity.getCity().toString());
         jsonObjData.addProperty(FIF_GVariable.domProvCode, DomProv.getprovcode().toString());
         jsonObjData.addProperty(FIF_GVariable.domProv, DomProv.getprovinsi().toString());
         jsonObjData.addProperty(FIF_GVariable.domSubZip, DomZipcode.getsubzipcode().toString());
         jsonObjData.addProperty(FIF_GVariable.totalProdPrice, preOrd.gettotprodprice());

		 
		 System.out.println(jsonObjData);

		 
		 String baseURL = GetBaseURL();

	       
	     try {
	            
	    	 // Send the request
	    	 URL url = new URL(baseURL+FIF_GParam.CreateNewOrder+"?object="+parameter.toString());

	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestProperty("Authorization", "bearer "+token);
	         conn.setRequestMethod("POST");
	         conn.setRequestProperty("Content-Type", "application/json");
	         conn.setRequestProperty("Accept", "application/json");
	         conn.setDoOutput(true);
	         conn.setDoInput(true);
	         conn.setConnectTimeout(15 * 1000);
	         conn.setReadTimeout(20 * 1000);
	         conn.connect();
	         StringBuffer answer;
	         BufferedReader reader;
	         
	         
	         System.out.println(url);
	         
	          try (DataOutputStream writer = new DataOutputStream(conn.getOutputStream())) {
	        	  //write parameters
	        	   writer.writeBytes(jsonObjData.toString());
	               writer.flush();
	               writer.close();
	               
	            // Get the response
	               answer = new StringBuffer();
	               reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	               String line;

	               System.out.println(conn.getResponseCode());
	               System.out.println(conn.getResponseMessage());
	               
	               if (conn.getResponseCode() != 200) {
	            	ret = "Submit PreOrder Gagal, Error("+conn.getResponseCode()+")";
	            	reader.close();
	   				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	   			   }
	               
	               while ((line = reader.readLine()) != null) {
	                   answer.append(line);
	               }
	               
	               ret = "OK";
	               
	               reader.close();
	           }
	           
	            
	        } catch (MalformedURLException ex) {
	            return ex.getMessage();
	        } catch (IOException ex) {
	            return ex.getMessage();
	        }
		
	     
	    if(ret == "OK"){
	    	 
	    	preOrd.set_ValueNoCheck("Processed", "Y");
	    	preOrd.saveEx();
	    	
	    	StringBuilder SQLUpdated = new StringBuilder();
	    	SQLUpdated.append(" SELECT C_Decoris_PreOrderLine_ID ");
	    	SQLUpdated.append(" FROM  C_Decoris_PreOrderLine ");
	    	SQLUpdated.append(" WHERE  AD_Client_ID = ? ");
	    	SQLUpdated.append(" AND  C_Decoris_PreOrder_ID = ? ");

	    	PreparedStatement pstmt = null;
	 		ResultSet res = null;

	 	    try {
	 			pstmt = DB.prepareStatement(SQLUpdated.toString(), null);
	 			pstmt.setInt(1, preOrd.getAD_Client_ID());
	 			pstmt.setInt(2, preOrd.getC_Decoris_PreOrder_ID());
	 			res = pstmt.executeQuery();

	 			while (res.next()) {
	 				
	 				int C_Decoris_PreOrderLine_ID = res.getInt(1);
	 				X_C_Decoris_PreOrderLine preOrdLine = new X_C_Decoris_PreOrderLine(ctx, C_Decoris_PreOrderLine_ID, null);
	 				preOrdLine.set_ValueNoCheck("Processed", "Y");
	 				preOrdLine.saveEx();
	 				
	 			}
	 			
	 			} catch (SQLException e) {
	 				log.log(Level.SEVERE, SQLUpdated.toString(), e);
	 			} finally {
	 				DB.close(res, pstmt);
	 				res = null;
	 				pstmt = null;
	 			} 	 
	    	 
	    	 rs = "Submit PreOrder Berhasil";
	     }else{
	    	 
	    	 rs = ret;
	     }
	     
	     
	     return rs;
		
	    }
	
	public static String getToken() {
	       
	    final String sparator = "&";
	    	   
	       StringBuilder data = new StringBuilder();
	       data.append(FIF_GVariable.Client_ID).append("=").append(FIF_GParam.Client_ID).append(sparator);
	       data.append(FIF_GVariable.Client_Secret).append("=").append(FIF_GParam.Client_Secret).append(sparator);
	       data.append(FIF_GVariable.Username).append("=").append(FIF_GParam.Username).append(sparator);
	       data.append(FIF_GVariable.Grant_Type).append("=").append(FIF_GParam.Grant_Type).append(sparator);
	       data.append(FIF_GVariable.Password).append("=").append(FIF_GParam.Password);
	       //
	       try {
	            
	            // Send the request
	            URL url = new URL(FIF_GParam.TokenURL);
	            URLConnection conn = url.openConnection();
	            conn.setDoOutput(true);
	            conn.setConnectTimeout(15 * 1000);
	            conn.setReadTimeout(20 * 1000);
	            StringBuffer answer;
	            BufferedReader reader;
	            
	           //write parameters
	           try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
	               //write parameters
	        	   writer.write(data.toString());
	               writer.flush();
	               
	               // Get the response
	               answer = new StringBuffer();
	               reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	               String line;
	               while ((line = reader.readLine()) != null) {
	                   answer.append(line);
	               }
	           }
	            reader.close();
	            
	            Gson g = new Gson();
	            FIF_ModelTokenAccess result = g.fromJson(answer.toString(), FIF_ModelTokenAccess.class);
	            return result.access_token;
	            
	        } catch (MalformedURLException ex) {
	            return ex.getMessage();
	        } catch (IOException ex) {
	            return ex.getMessage();
	        }
	    }
	
	public static int getFifTableID(String tableName, String whereClause, String code){
		
		int ID = 0;
		
		StringBuilder SQLGetID = new StringBuilder();
		SQLGetID.append("SELECT  "+tableName+"_ID");
		SQLGetID.append(" FROM "+ tableName);
		SQLGetID.append(" WHERE "+ whereClause );
		SQLGetID.append(" = '"+code+"'");

		
		ID = DB.getSQLValueEx(null, SQLGetID.toString());
		
		return ID;
		 
	}
	
	public static String convertDate(Timestamp p_date, boolean time){
		String rs ="";
		
		if(time){
			
		    rs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(p_date);

		}else{
			rs = new SimpleDateFormat("yyyy-MM-dd").format(p_date);
		}
		return rs;
	}
	
	public static String GetBaseURL() {
		StringBuilder getParams = new StringBuilder();
		String parameter = "";
		
		getParams.append("SELECT Description ");
		getParams.append("FROM AD_Param ");
		getParams.append("WHERE Value IN ('rest_token','rest_data_fifapps') ");

		parameter = DB.getSQLValueStringEx(null, getParams.toString());
		
		return parameter;
		
	}
}
