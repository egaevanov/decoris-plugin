package org.decoris.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCity;
import org.compiere.model.MClient;
import org.compiere.model.MLocation;
import org.compiere.model.MRegion;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

/**
 * 
 * @author Tegar N
 *
 */

public class DecorisAddBP extends SvrProcess {
	
	private String p_SearchKey = "";
	private String p_BPName1 = "";
	private String p_BPName2 = "";
	private String p_NamaIbu = "";
	private int p_AD_Org_ID = 0;
	private int p_AD_Client_ID = 0;
	private String p_NoKtp = "";
	boolean p_IsCustomer = true;
	private BigDecimal p_CreditLimit = Env.ZERO;

	//location
	private int p_C_City_ID = 0;
	private int p_C_Region_ID = 0;
	private int p_C_Country_ID = 0;
	@SuppressWarnings("unused")
	private String p_ZIP = "";
	private String p_Alamat1 = "";
	private String p_Alamat2 = "";
	
	//BP Location
	private String p_tlp ="";
	private String p_tlp2 = "";
	private String p_fax = "";
	private boolean p_IsShipTo = true;
	private boolean p_IsBillTo = true;
	private boolean p_IsPayFrom = true;
	private boolean p_IsRemitTo = true;
	private boolean p_IsTax = true;
	@SuppressWarnings("unused")
	private String p_email = "";
	@SuppressWarnings("unused")
	private Timestamp p_ttl = null;
	

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
				else if (name.equals("AD_Client_ID"))
					p_AD_Client_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("AD_Org_ID"))
					p_AD_Org_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("C_City_ID"))
					p_C_City_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("C_Country_ID"))
					p_C_Country_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("C_Region_ID"))
					p_C_Region_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("BPName1"))
					p_BPName1 = (String)para[i].getParameterAsString();
				else if (name.equals("BPName2"))
					p_BPName2 = (String)para[i].getParameterAsString();
				else if (name.equals("NamaIbu"))
					p_NamaIbu = (String)para[i].getParameterAsString();
				else if (name.equals("NoKtp"))
					p_NoKtp = (String)para[i].getParameterAsString();
				else if (name.equals("Alamat1"))
					p_Alamat1 = (String)para[i].getParameterAsString();
				else if (name.equals("Alamat2"))
					p_Alamat2 = (String)para[i].getParameterAsString();
				else if (name.equals("ZIP"))
					p_ZIP = (String)para[i].getParameterAsString();
				else if (name.equals("tlp"))
					p_tlp = (String)para[i].getParameterAsString();
				else if (name.equals("tlp2"))
					p_tlp2 = (String)para[i].getParameterAsString();
				else if (name.equals("email"))
					p_email = (String)para[i].getParameterAsString();
				else if (name.equals("fax"))
					p_fax = (String)para[i].getParameterAsString();
				else if (name.equals("CreditLimit"))
					p_CreditLimit = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("ttl"))
					p_ttl = (Timestamp)para[i].getParameterAsTimestamp();
			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		MClient Client = new MClient(getCtx(), p_AD_Client_ID, get_TrxName());
		p_SearchKey = Client.getValue()+"/"+"POS"+System.currentTimeMillis();
	}

	@Override
	protected String doIt() throws Exception {
		
		MBPartner bp = new MBPartner(getCtx(), 0, get_TrxName());
		bp.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
		bp.setValue(p_SearchKey);
		bp.setName(p_BPName1);
		bp.setName2(p_BPName2);
		bp.setSO_CreditLimit(p_CreditLimit);
		bp.set_CustomColumn("IbuKandung", p_NamaIbu);
		bp.set_CustomColumn("NoKTP", p_NoKtp);
		bp.setIsCustomer(p_IsCustomer);
		bp.saveEx();
		
		MLocation loc = new MLocation(getCtx(), 0, get_TrxName());
		MCity city = null;
		MRegion reg = null;
		if (p_C_City_ID > 0){
			city = new MCity(getCtx(), p_C_City_ID, get_TrxName());
		}
		if (p_C_Region_ID > 0){
			reg = new MRegion(getCtx(), p_C_Region_ID, get_TrxName());
		}
		loc.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
		loc.setAddress1(p_Alamat1);
		loc.setAddress2(p_Alamat2);
		loc.setC_City_ID(p_C_City_ID);
		loc.setC_Region_ID(p_C_Region_ID);
		loc.setC_Country_ID(p_C_Country_ID);
		loc.setCity(city.getName());
		loc.setRegionName(reg.getName());
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
		return null;
	}

}

