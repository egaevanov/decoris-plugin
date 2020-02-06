package org.decoris.webservice.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_GetToken {
	
	
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
	

}
