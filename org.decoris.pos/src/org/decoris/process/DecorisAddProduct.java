package org.decoris.process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.compiere.model.MAttributeSet;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class DecorisAddProduct extends SvrProcess{

	private int p_AD_Org_ID = 0;
	private int p_AD_Client_ID = 0;
	private String msg = "";
	
	private String p_Value = "";
	private String p_Name = "";
	private String p_Description = "";
	private int p_M_Brand_ID = 0;
	private int p_M_Product_Category_ID = 0;
	private int p_C_TaxCategory_ID = 0;
	private int p_C_UOM_ID = 0;
	private Boolean HaveImeiNumber = false; 

	
	private int p1_M_PriceList_Version_ID = 0;
	private BigDecimal p1_PriceLimit = Env.ZERO;
	private BigDecimal p1_PriceList = Env.ZERO;
	private BigDecimal p1_PriceStd = Env.ZERO;	
	
	private int p2_M_PriceList_Version_ID = 0;
	private BigDecimal p2_PriceLimit = Env.ZERO;
	private BigDecimal p2_PriceList = Env.ZERO;
	private BigDecimal p2_PriceStd = Env.ZERO;
	
	private int p3_M_PriceList_Version_ID = 0;
	private BigDecimal p3_PriceLimit = Env.ZERO;
	private BigDecimal p3_PriceList = Env.ZERO;
	private BigDecimal p3_PriceStd = Env.ZERO;
	
	private Map<String, Integer> mapPriceList;


	
	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		mapPriceList = new HashMap<String, Integer>();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
				
				else if (name.equals("Value"))
					p_Value = (String)para[i].getParameterAsString();
				else if (name.equals("Name"))
					p_Name = (String)para[i].getParameterAsString();
				else if (name.equals("Description"))
					p_Description = (String)para[i].getParameterAsString();
				else if (name.equals("M_Brand_ID"))
					p_M_Brand_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("M_Product_Category_ID"))
					p_M_Product_Category_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("C_TaxCategory_ID"))
					p_C_TaxCategory_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("C_UOM_ID"))
					p_C_UOM_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("M_PriceList_Version_ID_1")){
						p1_M_PriceList_Version_ID= (int)para[i].getParameterAsInt();
						mapPriceList.put(name, p1_M_PriceList_Version_ID);
					}
				else if (name.equals("PriceLimit"))
					p1_PriceLimit = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("PriceList"))
					p1_PriceList = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("PriceStd"))
					p1_PriceStd = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("M_PriceList_Version_ID_2")){
					p2_M_PriceList_Version_ID = (int)para[i].getParameterAsInt();
					mapPriceList.put(name, p2_M_PriceList_Version_ID);
				}
				else if (name.equals("PriceLimit2"))
					p2_PriceLimit = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("PriceList2"))
					p2_PriceList = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("PriceStd2"))
					p2_PriceStd = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("M_PriceList_Version_ID_3")){
					p3_M_PriceList_Version_ID = (int)para[i].getParameterAsInt();
					mapPriceList.put(name, p3_M_PriceList_Version_ID);
				}
				else if (name.equals("PriceLimit3"))
					p3_PriceLimit = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("PriceList3"))
					p3_PriceList = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("PriceStd3"))
					p3_PriceStd = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("HaveImeiNumber"))
					HaveImeiNumber = (Boolean)para[i].getParameterAsBoolean();
			else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		
		p_AD_Client_ID = getAD_Client_ID();
	
		
		
	}

	@Override
	protected String doIt() throws Exception {

		MProduct product = new MProduct(getCtx(), 0, get_TrxName());
		MAttributeSet attribute = null;
		boolean ok = true;
	
		try{
			
			if(p1_M_PriceList_Version_ID > 0 || p2_M_PriceList_Version_ID > 0 || p3_M_PriceList_Version_ID > 0){
			
				if(p1_M_PriceList_Version_ID == p2_M_PriceList_Version_ID || p1_M_PriceList_Version_ID == p3_M_PriceList_Version_ID ||
						p2_M_PriceList_Version_ID == p3_M_PriceList_Version_ID){
					ok = false;
					msg = "Tambah Product Tidak Berhasil, Price List Tidak Boleh Sama";
					return msg;
					
				}
			}
			if(p1_M_PriceList_Version_ID > 0){
				
				if(p1_PriceLimit.compareTo(Env.ZERO) <= 0 ||p1_PriceList.compareTo(Env.ZERO) <= 0  || p1_PriceStd.compareTo(Env.ZERO) <= 0) {
					msg = "Tambah Product Tidak Berhasil, Harga Pada Pricelist Tidak Boleh Kosong";
					ok = false;
					return msg;
					
				}
				
			}
			
			if(p2_M_PriceList_Version_ID > 0){
				
				if(p2_PriceLimit.compareTo(Env.ZERO) <= 0 ||p2_PriceList.compareTo(Env.ZERO) <= 0  || p2_PriceStd.compareTo(Env.ZERO) <= 0) {
					msg = "Tambah Product Tidak Berhasil, Harga Pada Pricelist Tidak Boleh Kosong";
					ok = false;
					return msg;
					
				}
				
			}
			
			if(p3_M_PriceList_Version_ID > 0){
				
				if(p3_PriceLimit.compareTo(Env.ZERO) <= 0 ||p3_PriceList.compareTo(Env.ZERO) <= 0  || p3_PriceStd.compareTo(Env.ZERO) <= 0) {
					msg = "Tambah Product Tidak Berhasil, Harga Pada Pricelist Tidak Boleh Kosong";
					ok = false;
					return msg;
					
				}
				
			}
			
			product.setIsBOM (false);	// N
			product.setIsInvoicePrintDetails (false);
			product.setIsPickListPrintDetails (false);
			product.setIsPurchased (true);	// Y
			product.setIsSold (true);	// Y
			product.setIsStocked (true);	// Y
			product.setIsSummary (false);
			product.setIsVerified (false);	// N
			product.setIsWebStoreFeatured (false);
			product.setIsSelfService(true);
			product.setIsExcludeAutoDelivery(false);
			product.setProcessing (false);	// N
			product.setLowLevel(0);
			product.setClientOrg(p_AD_Client_ID,p_AD_Org_ID);
			product.setName(p_Name);
			product.setValue(p_Value);
			product.set_CustomColumn("M_Brand_ID", p_M_Brand_ID);
			product.setDescription(p_Description);
			product.setUPC("");
			product.setM_Product_Category_ID(p_M_Product_Category_ID);
			product.setC_TaxCategory_ID(p_C_TaxCategory_ID);
			product.setC_UOM_ID(p_C_UOM_ID);
			product.setProductType (MProduct.PRODUCTTYPE_Item);
			product.saveEx();
		
			
			if(HaveImeiNumber){
				
				attribute = new MAttributeSet(getCtx(), 0, get_TrxName());
				attribute.setIsActive(true);
				attribute.setName(p_Name);
				attribute.setDescription(p_Name);
				attribute.setIsInstanceAttribute(true);
				attribute.setIsSerNo(true);
				attribute.set_CustomColumn("IsUniqueAttributeSet", true);
				attribute.setMandatoryType(MAttributeSet.MANDATORYTYPE_WhenShipping);
				attribute.saveEx();
				
			}
			
			if(attribute != null){
				
				product.setM_AttributeSet_ID(attribute.getM_AttributeSet_ID());
				product.saveEx();
			}
			
			
		
		if(product != null){
			
			for(String key : mapPriceList.keySet()){
					
				MProductPrice price = new MProductPrice(getCtx(), 0, get_TrxName());
				
				price.setM_Product_ID(product.getM_Product_ID());
				price.setM_PriceList_Version_ID(mapPriceList.get(key));
				price.setIsActive(true);
				
				if(key.toUpperCase().equals("M_PRICELIST_VERSION_ID_1")){
					price.setPriceLimit(p1_PriceLimit);
					price.setPriceStd(p1_PriceStd);
					price.setPriceList(p1_PriceList);
				}else if(key.toUpperCase().equals("M_PRICELIST_VERSION_ID_2")){
					price.setPriceLimit(p2_PriceLimit);
					price.setPriceStd(p2_PriceStd);
					price.setPriceList(p2_PriceList);
				}else if(key.toUpperCase().equals("M_PRICELIST_VERSION_ID_3")){
					price.setPriceLimit(p3_PriceLimit);
					price.setPriceStd(p3_PriceStd);
					price.setPriceList(p3_PriceList);
				}
				
				price.saveEx();		
				
			}	
			
		}
		
		
		
		}catch (Exception e){
			
			log.log(Level.SEVERE, "Generate Order - " , e);
			ok = false;
			
			msg = "Process Tambah Product Gagal"+ e.toString();		
			
		}
		
		if(ok){
			
			commitEx();
			msg = "Process Tambah Product Berhasil";
		}else {
			rollback();
				
		}
		
		
		return msg;
		
		
	}

}
