/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.decoris.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.compiere.util.Env;
import org.decoris.webservice.model.X_M_Fifapps_City;
import org.decoris.webservice.model.FIF_GParam;
import org.decoris.webservice.model.FIF_GVariable;
import org.decoris.webservice.model.FIF_ModelCity;
import org.decoris.webservice.model.FIF_ModelTokenAccess;

import com.google.gson.Gson;

/**
 *
 * @author ajimaulanacintafatma
 */
public class DecorisWS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    	try {
            Gson gson = new Gson();
            
            
           FIF_ModelCity[] datas = gson.fromJson(getCities(getToken()), FIF_ModelCity[].class);
            
            for (FIF_ModelCity data : datas) {
            	
                System.out.println("City Kode :" +data.cityCode);
                System.out.println("City :" +data.city);
                System.out.println("Prov Kode :" +data.provCode);
                System.out.println("Created By :" +data.createBy);
                System.out.println("Created Date :" +data.createDate);
                System.out.println("Updated By :" +data.updateBy);
                System.out.println("Updated Date :" +data.updateDate);
                System.out.println("Dati2 :" +data.dati2);
                System.out.println("ZIP :" +data.digitZip);
                System.out.println("-----------------------------");
                
                X_M_Fifapps_City city = new X_M_Fifapps_City(Env.getCtx(), 0, null);
                city.setClientOrg(0,0);
                city.setIsActive(true);
                city.setcitycode(data.cityCode);
                city.setCity(data.city);
                city.setprovcode(data.provCode);
                city.setdati2(data.dati2);
                city.setdigitzip(data.digitZip);
                city.saveEx();
            }
        
        } catch (Exception ex) {
            System.out.println("token "+ex.getMessage());
        }
    }
    
	private static String getCities(String token){
        try {

	        URL url = new URL("http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/cities");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "bearer "+token);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			
			while ((output = br.readLine()) != null) {
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
    
    /**
     * Extends the size of an array.
     * @return 
     */
    public static String getToken() {
       
    final String sparator = "&";
    	   
       StringBuilder data = new StringBuilder();
       data.append(FIF_GVariable.Client_ID).append("=").append(FIF_GParam.Client_ID).append(sparator);
       data.append(FIF_GVariable.Client_Secret).append("=").append(FIF_GParam.Client_Secret).append(sparator);
       data.append(FIF_GVariable.Username).append("=").append(FIF_GParam.Username).append(sparator);
       data.append(FIF_GVariable.Grant_Type).append("=").append(FIF_GParam.Grant_Type).append(sparator);
       data.append(FIF_GVariable.Password).append("=").append(FIF_GParam.Password);     
       
       try {
            
            // Send the request
            URL url = new URL("http://10.17.18.164:8380/auth/realms/fifgroup/protocol/openid-connect/token");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
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
 
}
