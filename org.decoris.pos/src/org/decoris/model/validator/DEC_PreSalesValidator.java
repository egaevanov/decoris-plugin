package org.decoris.model.validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.I_C_Decoris_PreSales;
import org.decoris.model.I_C_Decoris_PreSalesLine;
import org.decoris.model.X_C_Decoris_PreSales;
import org.decoris.model.X_C_Decoris_PreSalesLine;
import org.osgi.service.event.Event;

public class DEC_PreSalesValidator {

	
	public static String executePreSalesEvent(Event event, PO po) {
		String msgPSales = "";
		
		if(po.get_TableName().equals(I_C_Decoris_PreSales.Table_Name)){
			X_C_Decoris_PreSales PSales = (X_C_Decoris_PreSales) po;

		
			if (event.getTopic().equals(IEventTopics.PO_AFTER_NEW) ||
					event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE)) {
				msgPSales = PreSalesBeforeSave(PSales);
				
			}
		}else if(po.get_TableName().equals(I_C_Decoris_PreSalesLine.Table_Name)){
			X_C_Decoris_PreSalesLine PSalesLine = (X_C_Decoris_PreSalesLine) po;

			if (event.getTopic().equals(IEventTopics.PO_AFTER_NEW) ||
					event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE)) {
				msgPSales = PreSalesLineBeforeSave(PSalesLine);
			}
			
		}	
		return msgPSales;

	}
	
	private static String PreSalesBeforeSave(X_C_Decoris_PreSales PSales) {
		
		return"";
	}
	
	private static String PreSalesLineBeforeSave(X_C_Decoris_PreSalesLine PSalesLine) {
		X_C_Decoris_PreSales preSales = new X_C_Decoris_PreSales(PSalesLine.getCtx(), PSalesLine.getC_Decoris_PreSales_ID(), PSalesLine.get_TrxName());
		
		if(preSales != null){
	    	
	    	BigDecimal DPP = Env.ZERO;
    		BigDecimal PPN = Env.ZERO;

    		
    		boolean issotrx = true;

    		MProductPricing pp = new MProductPricing(PSalesLine.getM_Product_ID(), preSales.getC_BPartner_ID(),Env.ONE, issotrx);

    		Timestamp date = new Timestamp(System.currentTimeMillis());

    		String sql = "SELECT plv.M_PriceList_Version_ID "
    				+ "FROM M_PriceList_Version plv "
    				+ "WHERE plv.AD_Client_ID = ? " + " AND plv.M_PriceList_ID= ? " // 1
    				+ " AND plv.ValidFrom <= ? " + "ORDER BY plv.ValidFrom DESC";

    		int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, new Object[] {preSales.getAD_Client_ID(), preSales.getM_PriceList_ID(), date });

    		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
    		pp.setPriceDate(date);
    		
    		Double dPersen = ((Double.valueOf(pp.getPriceList().toString()) - Double
					.valueOf(PSalesLine.getPriceEntered().toString())) / Double.valueOf(pp.getPriceList()
					.toString())) * 100;
			BigDecimal persentase = BigDecimal.valueOf(dPersen).setScale(0,RoundingMode.HALF_DOWN);
			
			if(persentase.compareTo(Env.ZERO)<0){
				persentase = Env.ZERO;
			}
			
    			
    		int M_Product_ID = PSalesLine.getM_Product_ID();

    		MProduct pr = new MProduct(PSalesLine.getCtx(), M_Product_ID, null);

    		StringBuilder SQLTaxRate = new StringBuilder();
    		SQLTaxRate.append("SELECT Rate ");
    		SQLTaxRate.append("FROM C_Tax ");
    		SQLTaxRate.append("WHERE AD_Client_ID = ? ");
    		SQLTaxRate.append("AND C_TaxCategory_ID = ? ");

    		BigDecimal taxRate = DB.getSQLValueBDEx(null,
    				SQLTaxRate.toString(),
    				new Object[] { PSalesLine.getAD_Client_ID(), pr.getC_TaxCategory_ID() });

			BigDecimal ttlHarga = PSalesLine.getPriceEntered().multiply(PSalesLine.getQtyEntered());

    		if (taxRate.compareTo(Env.ZERO) > 0) {

    			BigDecimal divider = new BigDecimal("1.1");
    			DPP = ttlHarga.divide(divider, 2, RoundingMode.HALF_UP);
    			PPN = ttlHarga.subtract(DPP);


    		}
    		
    		
    		BigDecimal diskonLine = pp.getPriceList().subtract(PSalesLine.getPriceEntered());
    		
    		if(diskonLine.compareTo(Env.ZERO)<0){
    			
    			diskonLine = Env.ZERO;
    		}
    		StringBuilder updateLine = new StringBuilder();
    		updateLine.append("UPDATE ");
    		updateLine.append("C_Decoris_PreSalesLine ");
    		updateLine.append("SET PersentaseDiskon = "+ Integer.valueOf(persentase.setScale(0,BigDecimal.ROUND_DOWN).toString()));
    		updateLine.append(", diskonline =  "+pp.getPriceList().subtract(PSalesLine.getPriceEntered()));
    		updateLine.append(", PriceList =  "+ pp.getPriceList());
    		updateLine.append(", lineNetAmt =  "+PSalesLine.getPriceEntered().multiply(PSalesLine.getQtyEntered()));
    		updateLine.append(", TaxAmt =  "+PPN);
    		updateLine.append(" WHERE AD_Client_ID = ? ");
    		updateLine.append(" AND C_Decoris_PresalesLine_ID = ? ");

    		DB.executeUpdateEx(updateLine.toString(), new Object[]{PSalesLine.getAD_Client_ID(),PSalesLine.getC_Decoris_PreSalesLine_ID()}, PSalesLine.get_TrxName());
    	
    		System.out.println(diskonLine);
    		
    		preSales.setTotalDiskon(preSales.getTotalDiskon().add(diskonLine));
    		preSales.setdpp(preSales.getdpp().add(DPP));
    		preSales.setTaxAmt(preSales.getTaxAmt().add(PPN));
    		preSales.settotal(preSales.gettotal().add(pp.getPriceList().multiply(PSalesLine.getQtyEntered())));
    		preSales.saveEx();
    		
    		preSales.setTotalBelanja(preSales.getTotalBelanja().add(ttlHarga));
    		preSales.saveEx();
    	}
		
		
		return"";
	}
	
}
