package org.decoris.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.decoris.webservice.model.I_M_Fifapps_Bussunit;
import org.decoris.webservice.model.I_M_Fifapps_City;
import org.decoris.webservice.model.I_M_Fifapps_Education;
import org.decoris.webservice.model.I_M_Fifapps_Gender;
import org.decoris.webservice.model.I_M_Fifapps_Housestatus;
import org.decoris.webservice.model.I_M_Fifapps_Inputsource;
import org.decoris.webservice.model.I_M_Fifapps_Kecamatan;
import org.decoris.webservice.model.I_M_Fifapps_Kelurahan;
import org.decoris.webservice.model.I_M_Fifapps_Maritalstatus;
import org.decoris.webservice.model.I_M_Fifapps_Objcodes;
import org.decoris.webservice.model.I_M_Fifapps_Objgroups;
import org.decoris.webservice.model.I_M_Fifapps_Occupations;
import org.decoris.webservice.model.I_M_Fifapps_Offices;
import org.decoris.webservice.model.I_M_Fifapps_Supplier;
import org.decoris.webservice.model.I_M_Fifapps_Zipcode;
import org.decoris.webservice.model.I_m_fifapps_ws_tmp;

/**
 * 
 * @author Tegar N
 *
 */

public class POS_ModelFactory implements IModelFactory {

	private static HashMap<String, String> mapTableModels = new HashMap<String, String>();
	static
	{

		mapTableModels.put(I_C_DecorisPOS.Table_Name, "org.decoris.model.X_C_DecorisPOS");
		mapTableModels.put(I_C_DecorisPOSLine.Table_Name, "org.decoris.model.X_C_DecorisPOSLine");
		mapTableModels.put(I_C_Invoice.Table_Name, "org.decoris.model.MInvoiceDecoris");
		mapTableModels.put(I_C_Decoris_ENofa.Table_Name, "org.decoris.model.X_C_Decoris_ENofa");
		mapTableModels.put(I_C_Decoris_PostingMethod.Table_Name, "org.decoris.model.X_C_Decoris_PostingMethod");
		mapTableModels.put(I_C_Decoris_PreSales.Table_Name, "org.decoris.model.MDecorisPresales");
		mapTableModels.put(I_C_Decoris_PreSalesLine.Table_Name, "org.decoris.model.X_C_Decoris_PreSalesLine");
		mapTableModels.put(I_M_Fifapps_City.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_City");
		mapTableModels.put(I_M_Fifapps_Bussunit.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Bussunit");
		mapTableModels.put(I_M_Fifapps_City.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_City");
		mapTableModels.put(I_M_Fifapps_Education.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Education");
		mapTableModels.put(I_M_Fifapps_Gender.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Gender");
		mapTableModels.put(I_M_Fifapps_Housestatus.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Housestatus");
		mapTableModels.put(I_M_Fifapps_Inputsource.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Inputsource");
		mapTableModels.put(I_M_Fifapps_Kecamatan.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Kecamatan");
		mapTableModels.put(I_M_Fifapps_Kelurahan.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Kelurahan");
		mapTableModels.put(I_M_Fifapps_Maritalstatus.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Maritalstatus");
		mapTableModels.put(I_M_Fifapps_Objcodes.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Objcodes");
		mapTableModels.put(I_M_Fifapps_Objgroups.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Objgroups");
		mapTableModels.put(I_M_Fifapps_Occupations.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Occupations");
		mapTableModels.put(I_M_Fifapps_Offices.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Offices");
		mapTableModels.put(I_M_Fifapps_Supplier.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Supplier");
		mapTableModels.put(I_M_Fifapps_Zipcode.Table_Name, "org.decoris.webservice.model.X_M_Fifapps_Zipcode");
		mapTableModels.put(I_m_fifapps_ws_tmp.Table_Name, "org.decoris.webservice.model.X_m_fifapps_ws_tmp");
		mapTableModels.put(I_C_Decoris_PreOrder.Table_Name, "org.decoris.model.X_C_Decoris_PreOrder");
		mapTableModels.put(I_C_Decoris_PreOrderLine.Table_Name, "org.decoris.model.X_C_Decoris_PreOrderLine");
	}
	
	@Override
	public Class<?> getClass(String tableName) {
		
		if (mapTableModels.containsKey(tableName)) {
			Class<?> act = null;
			try {
				act = Class.forName(mapTableModels.get(tableName));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
				return act;
		
		} else 
			return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		
		if (mapTableModels.containsKey(tableName)) {
			Class<?> clazz = null;
			Constructor<?> ctor = null;
			PO object = null;
			try {
				clazz = Class.forName(mapTableModels.get(tableName));
				ctor = clazz.getConstructor(Properties.class, int.class, String.class);
				object = (PO) ctor.newInstance(new Object[] {Env.getCtx(), Record_ID, trxName});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				return object;
		} else 	   
		   return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
	
		if (mapTableModels.containsKey(tableName)) {
			Class<?> clazz = null;
			Constructor<?> ctor = null;
			PO object = null;
			try {
				clazz = Class.forName(mapTableModels.get(tableName));
				ctor = clazz.getConstructor(Properties.class, ResultSet.class, String.class);
				object = (PO) ctor.newInstance(new Object[] {Env.getCtx(), rs, trxName});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				return object;
				
		} else  
			return null;
	}

}
