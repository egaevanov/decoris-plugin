package org.decoris.process;

import java.util.logging.Level;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class DecorisUpdateIMEI extends SvrProcess{

	
	private int p_M_Product_ID = 0;
	private int p_M_AttributeSet_ID = 0;
	String p_OldIMEI = "";
	String p_NewIMEI = "";
	String p_msg = "";

	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0 ; i < para.length ;i++){
			
			String name = para[i].getParameterName();
			
			if(para[i].getParameter()==null)
				;
			else if(name.equals("M_Product_ID"))
				p_M_Product_ID = (int)para[i].getParameterAsInt();
			else if(name.equals("M_AttributeSet_ID"))
				p_M_AttributeSet_ID = (int)para[i].getParameterAsInt();
			else if(name.equals("OldIMEI"))
				p_OldIMEI = (String)para[i].getParameterAsString();
			else if(name.equals("NewIMEI"))
				p_NewIMEI = (String)para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			
		}
		
	}

	@Override
	protected String doIt() throws Exception {

		int attributeset_existting = 0;
		
		
		if(p_M_Product_ID <= 0 || p_M_AttributeSet_ID <= 0 || p_OldIMEI == "" || p_NewIMEI == ""){
			p_msg = "Semua Parameter Harus DiIsi";
			return p_msg;
		}
		
		
		MProduct prod = new MProduct(getCtx(), p_M_Product_ID, get_TrxName());
		
		attributeset_existting = prod.getM_AttributeSet_ID();
		
		if(p_M_AttributeSet_ID != attributeset_existting){
			p_msg = "Attribute Set Yang Di Input Tidak Sesuai Dengan AttributeSet Product";
			return p_msg;
		}
		
		
		StringBuilder SQLGetInstance_ID = new StringBuilder();
		
		SQLGetInstance_ID.append("SELECT M_AttributeSetInstance_ID ");
		SQLGetInstance_ID.append(" FROM M_AttributeSetInstance ");
		SQLGetInstance_ID.append(" WHERE AD_Client_ID = ? ");
		SQLGetInstance_ID.append(" AND M_AttributeSet_ID = ? " );
		SQLGetInstance_ID.append(" AND SerNo = '"+p_OldIMEI+"'");
		
		int M_AttributeSetInstance_ID =  DB.getSQLValueEx(get_TrxName(), SQLGetInstance_ID.toString(), new Object[]{getAD_Client_ID(),p_M_AttributeSet_ID});
		
		if(M_AttributeSetInstance_ID <= 0){
			
			p_msg = "IMEI Lama Yang Di Input Tidak Terdaftar";
			return p_msg;
		}
		
		
		MAttributeSetInstance IMEI = new MAttributeSetInstance(getCtx(), M_AttributeSetInstance_ID, get_TrxName());
		IMEI.setSerNo(p_NewIMEI);
		IMEI.setDescription("#"+p_NewIMEI);
		IMEI.saveEx();
		
		
		
		return "";
	}

}
