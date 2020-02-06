package org.decoris.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAsset;
import org.compiere.model.MInvoiceLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class DecorisProcessAsset extends SvrProcess{

	
	int p_AD_Org_ID = 0;
	int p_AD_Client_ID = 0;;
	String p_Value = "";
	String p_Name = "";
	String p_InventoryNo = "";
	int p_A_Asset_Group_ID = 0;
	int p_ManufacturedYear = 0;
	Timestamp p_AssetServiceDate = null;
	int p_M_Product_ID = 0;
	int p_C_InvoiceLine_ID = 0;
	
	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();

		for (int i = 0 ; i < para.length ;i++){
			
			String name = para[i].getParameterName();
			
			if(para[i].getParameter()==null)
				;
			else if(name.equals("AD_Org_ID"))
				p_AD_Org_ID = (int)para[i].getParameterAsInt();
			else if(name.equals("Value"))
				p_Value = (String)para[i].getParameterAsString();
			else if(name.equals("Name"))
				p_Name = (String)para[i].getParameterAsString();
			else if(name.equals("InventoryNo"))
				p_InventoryNo = (String)para[i].getParameterAsString();
			else if(name.equals("A_Asset_Group_ID"))
				p_A_Asset_Group_ID = (int)para[i].getParameterAsInt();
			else if(name.equals("ManufacturedYear"))
				p_ManufacturedYear = (int)para[i].getParameterAsInt();
			else if(name.equals("AssetServiceDate"))
				p_AssetServiceDate = (Timestamp)para[i].getParameterAsTimestamp();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			
		}
		
		p_C_InvoiceLine_ID = getRecord_ID();
		
	}

	@Override
	protected String doIt() throws Exception {

		MInvoiceLine line = new MInvoiceLine(getCtx(), p_C_InvoiceLine_ID, get_TrxName());
		p_M_Product_ID = line.getM_Product_ID();
		p_AD_Client_ID = line.getAD_Client_ID();
		MAsset asset = new MAsset(getCtx(), 0, get_TrxName());
		
		asset.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
		asset.setValue(p_Value);
		asset.setInventoryNo(p_InventoryNo);
		asset.setName(p_Name);
		asset.setA_Asset_Group_ID(p_A_Asset_Group_ID);
		asset.setM_Product_ID(p_M_Product_ID);
		asset.setManufacturedYear(p_ManufacturedYear);
		asset.setAssetServiceDate(p_AssetServiceDate);
		asset.saveEx();
		
		return "";
	}

}
