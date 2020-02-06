package org.decoris.process;

import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCity;
import org.compiere.model.MLocation;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

/**
 * 
 * @author Tegar N
 *
 */

public class DecorisAddVendor extends SvrProcess {
	
	private int p_AD_Org_ID = 0;
	private int p_AD_Client_ID = 0;
	private String p_SearchKey = "";
	private String p_BPName = "";
	private String p_Alamat1 = "";
	private String p_Alamat2 = "";
	private int p_C_City_ID = 0;
	private String p_ZIP = "";
	private int p_C_Country_ID = 0;
	private String p_tlp ="";
	private String p_tlp2 = "";
	private String p_fax = "";
	
	private String p_NamaIbu = "-";
	boolean p_IsVendor= true;

	private boolean p_IsShipTo = true;
	private boolean p_IsBillTo = true;
	private boolean p_IsPayFrom = true;
	private boolean p_IsRemitTo = true;
	private boolean p_IsTax = true;
	@SuppressWarnings("unused")
	private String p_email = "";
	private String msg = "";

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
				else if (name.equals("Value"))
					p_SearchKey = (String)para[i].getParameterAsString();
				else if (name.equals("Name"))
					p_BPName = (String)para[i].getParameterAsString();
				else if (name.equals("Alamat1"))
					p_Alamat1 = (String)para[i].getParameterAsString();
				else if (name.equals("Alamat2"))
					p_Alamat2 = (String)para[i].getParameterAsString();
				else if (name.equals("C_City_ID"))
					p_C_City_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("ZIP"))
					p_ZIP = (String)para[i].getParameterAsString();
				else if (name.equals("C_Country_ID"))
					p_C_Country_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("tlp"))
					p_tlp = (String)para[i].getParameterAsString();
				else if (name.equals("tlp2"))
					p_tlp2 = (String)para[i].getParameterAsString();
				else if (name.equals("fax"))
					p_fax = (String)para[i].getParameterAsString();
				
			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		p_email = p_BPName+"@email.com";
		p_AD_Client_ID = getAD_Client_ID();
		
	}

	@Override
	protected String doIt() throws Exception {
		
		boolean ok = true;
		
		try{
		
		MBPartner bp = new MBPartner(getCtx(), 0, get_TrxName());
		bp.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
		bp.setValue(p_SearchKey);
		bp.setName(p_BPName);
		bp.set_CustomColumn("IbuKandung", p_NamaIbu);
		bp.setIsVendor(p_IsVendor);
		bp.setIsCustomer(false);
		bp.saveEx();
		
		MLocation loc = new MLocation(getCtx(), 0, get_TrxName());
		MCity city = null;
		if (p_C_City_ID > 0){
			city = new MCity(getCtx(), p_C_City_ID, get_TrxName());
		}
		loc.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
		loc.setAddress1(p_Alamat1);
		loc.setAddress2(p_Alamat2);
		loc.setC_City_ID(p_C_City_ID);
		loc.setC_Country_ID(p_C_Country_ID);
		loc.setCity(city.getName());
		loc.setPostal(p_ZIP);
		loc.saveEx();
		
		MBPartnerLocation BPLoc = new MBPartnerLocation(getCtx(), 0, get_TrxName());
		BPLoc.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
		BPLoc.setC_BPartner_ID(bp.getC_BPartner_ID());
		BPLoc.setName(loc.getCity());
		BPLoc.setC_Location_ID(loc.getC_Location_ID());
		BPLoc.setPhone(p_tlp);
		BPLoc.setPhone2(p_tlp2);
		BPLoc.setFax(p_fax);
		BPLoc.setIsShipTo(p_IsShipTo);
		BPLoc.setIsBillTo(p_IsBillTo);
		BPLoc.setIsPayFrom(p_IsPayFrom);
		BPLoc.setIsRemitTo(p_IsRemitTo);
		BPLoc.set_CustomColumn("IsTax", p_IsTax);
		BPLoc.saveEx();
		
		}catch (Exception e){
			
			log.log(Level.SEVERE, "Generate Order - " , e);
			ok = false;
			
			msg = "Process Tambah Vendor Gagal"+ e.toString();		
			
		}
		
		if(ok){
			
			commitEx();
			msg = "Process Tambah Vendor Berhasil";
		}else {
			rollback();
				
		}
		
		return msg;
		
		
	}
		

}

