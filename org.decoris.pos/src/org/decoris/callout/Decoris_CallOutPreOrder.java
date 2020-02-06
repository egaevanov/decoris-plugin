package org.decoris.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MProductPricing;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.I_C_Decoris_PreOrder;
import org.decoris.model.I_C_Decoris_PreOrderLine;
import org.decoris.model.X_C_Decoris_PreOrder;
import org.decoris.model.X_C_Decoris_PreOrderLine;
import org.decoris.model.X_C_Decoris_PreSales;
import org.decoris.model.X_C_Decoris_PreSalesLine;


/**
 * 
 * @author Tegar N
 *
 */
public class Decoris_CallOutPreOrder  extends CalloutEngine implements IColumnCallout{

	//private Properties ctx = Env.getCtx();
	//private int AD_Client_ID = Env.getAD_Client_ID(ctx);
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {

		if(mField.getColumnName().equals(I_C_Decoris_PreOrderLine.COLUMNNAME_M_Product_ID)){
			return setPriceList(ctx, WindowNo, mTab, mField, value);
		}else if(mField.getColumnName().equals(I_C_Decoris_PreOrderLine.COLUMNNAME_PriceEntered)){
			return priceEntered(ctx, WindowNo, mTab, mField, value);
		}else if(mField.getColumnName().equals(I_C_Decoris_PreOrderLine.COLUMNNAME_QtyEntered)){
			return qty(ctx, WindowNo, mTab, mField, value);
		}else if(mField.getColumnName().equals(I_C_Decoris_PreOrderLine.COLUMNNAME_C_Decoris_PreSalesLine_ID)){
			return presalesLine(ctx, WindowNo, mTab, mField, value);
		}else if(mField.getColumnName().equals(I_C_Decoris_PreOrder.COLUMNNAME_C_Decoris_PreSales_ID)){
			return presales(ctx, WindowNo, mTab, mField, value);
		}
		
		
		return null;
	}
	
	private String presalesLine(Properties ctx, int windowNo, GridTab mTab,GridField mField, Object value) {

		Integer C_Decoris_PreSalesLine_ID = (Integer)value;
		
		if(C_Decoris_PreSalesLine_ID == null){
			C_Decoris_PreSalesLine_ID = 0;
		}
		
		
		if(C_Decoris_PreSalesLine_ID == 0)
			return null;
		
		
		X_C_Decoris_PreSalesLine preSalesLine = new X_C_Decoris_PreSalesLine(ctx, C_Decoris_PreSalesLine_ID, null);
		mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_M_Product_ID, preSalesLine.getM_Product_ID());
		mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_QtyEntered, preSalesLine.getQtyEntered());
		mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_PriceEntered, preSalesLine.getPriceEntered());

		
		//M_AttributeSetInstance
		Integer M_AttributeSetInstance_ID = (Integer) mTab.getValue(X_C_Decoris_PreOrderLine.COLUMNNAME_M_AttributeSetInstance_ID);
				
		if(M_AttributeSetInstance_ID == null){
			M_AttributeSetInstance_ID = 0;
		}
				
		if(M_AttributeSetInstance_ID > 0){
					
			mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_M_AttributeSetInstance_ID, null);
					
		}
		
		
		return null;
	}

	public String setPriceList (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		Integer M_Product_ID = (Integer)value;
		if (M_Product_ID == null || M_Product_ID.intValue() == 0){
			
			mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_PriceEntered, Env.ZERO);
			mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_LineNetAmt, Env.ZERO);
			
			return "";
			
		}
		
		BigDecimal qtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
		
		int C_Decoris_PreOrder_ID = Env.getContextAsInt(ctx, WindowNo, "C_Decoris_PreOrder_ID");
		X_C_Decoris_PreOrder preOrd = new X_C_Decoris_PreOrder(ctx, C_Decoris_PreOrder_ID, null);
				
		int C_BPartner_ID = preOrd.getC_BPartner_ID();
		BigDecimal Qty = (BigDecimal)mTab.getValue("QtyEntered");
		MProductPricing pp = new MProductPricing (M_Product_ID.intValue(), C_BPartner_ID, Qty, true);
		//
		int M_PriceList_ID = preOrd.getM_PriceList_ID();
		pp.setM_PriceList_ID(M_PriceList_ID);
		Timestamp orderDate = preOrd.getDateOrdered();
		//
		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
		if ( M_PriceList_Version_ID == 0 )
		{
			String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID=? "						//	1
				+ " AND plv.ValidFrom <= ? "
				+ "ORDER BY plv.ValidFrom DESC";
			//	Use newest price list - may not be future

			M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, M_PriceList_ID, orderDate);
			if ( M_PriceList_Version_ID > 0 )
				Env.setContext(ctx, WindowNo, "M_PriceList_Version_ID", M_PriceList_Version_ID );
		}
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		pp.setPriceDate(orderDate);

		
		mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_PriceEntered, pp.getPriceList());

		BigDecimal priceEntered = (BigDecimal) mTab.getValue(X_C_Decoris_PreOrderLine.COLUMNNAME_PriceEntered);
		mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_LineNetAmt, priceEntered.multiply(qtyEntered));
				
		
		//M_AttributeSetInstance
		Integer M_AttributeSetInstance_ID = (Integer) mTab.getValue(X_C_Decoris_PreOrderLine.COLUMNNAME_M_AttributeSetInstance_ID);
		
		if(M_AttributeSetInstance_ID == null){
			M_AttributeSetInstance_ID = 0;
		}
		
		if(M_AttributeSetInstance_ID > 0){
			
			mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_M_AttributeSetInstance_ID, null);
			
		}
		
		
		return"";
	}

	
	public String qty (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		
		Integer M_Product_ID = (Integer)mTab.getValue(I_C_Decoris_PreOrderLine.COLUMNNAME_M_Product_ID);

		if(M_Product_ID == null ){
			M_Product_ID = 0;
		}
	
		//int C_Decoris_PreOrder_ID = Env.getContextAsInt(ctx, WindowNo, "C_Decoris_PreOrder_ID");
		//X_C_Decoris_PreOrder preOrd = new X_C_Decoris_PreOrder(ctx, C_Decoris_PreOrder_ID, null);
		
		BigDecimal QtyEntered;

		QtyEntered = (BigDecimal)value;

		
		//	No Product
		if (M_Product_ID == 0)
		{
			
			return "";
			
		}
		
		BigDecimal priceEntered = (BigDecimal) mTab.getValue(X_C_Decoris_PreOrderLine.COLUMNNAME_PriceEntered);
		
		mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_LineNetAmt, priceEntered.multiply(QtyEntered));
		
		return "";
		
	}
	
	public String priceEntered (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		
		Integer M_Product_ID = (Integer)mTab.getValue(I_C_Decoris_PreOrderLine.COLUMNNAME_M_Product_ID);

		if(M_Product_ID == null ){
			M_Product_ID = 0;
		}
	
		//int C_Decoris_PreOrder_ID = Env.getContextAsInt(ctx, WindowNo, "C_Decoris_PreOrder_ID");
		//X_C_Decoris_PreOrder preOrd = new X_C_Decoris_PreOrder(ctx, C_Decoris_PreOrder_ID, null);
		
		BigDecimal priceEntered;

		priceEntered = (BigDecimal)value;

		
		//	No Product
		if (M_Product_ID == 0)
		{
			
			return "";
			
		}
		
		BigDecimal qtyEntered = (BigDecimal) mTab.getValue(X_C_Decoris_PreOrderLine.COLUMNNAME_QtyEntered);
		
		mTab.setValue(X_C_Decoris_PreOrderLine.COLUMNNAME_LineNetAmt, priceEntered.multiply(qtyEntered));
		
		return "";
		
	}
	
	
	public String presales (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		
		Integer C_Decoris_PreSales_ID = (Integer)mTab.getValue(I_C_Decoris_PreOrder.COLUMNNAME_C_Decoris_PreSales_ID);

		if(C_Decoris_PreSales_ID == null ){
			C_Decoris_PreSales_ID = 0;
		}
	
		
		if(C_Decoris_PreSales_ID == 0)
			return "";
		
			
		X_C_Decoris_PreSales presales = new X_C_Decoris_PreSales(ctx, C_Decoris_PreSales_ID, null);
		
		if(presales.getStatus().equals(X_C_Decoris_PreSales.STATUS_Closed))
			return "Tidak Bisa Mengambil Data Dari Presales, Status Presales Closed";
		
		StringBuilder SQLCekPreSales = new StringBuilder();
		SQLCekPreSales.append("SELECT C_Decoris_PreOrder_ID ");
		SQLCekPreSales.append("FROM C_Decoris_PreOrder  ");
		SQLCekPreSales.append("WHERE AD_Client_ID = ? ");
		SQLCekPreSales.append("AND C_Decoris_PreSales_ID = ? ");

		int exist = DB.getSQLValueEx(null, SQLCekPreSales.toString(), new Object[]{presales.getAD_Client_ID(),presales.getC_Decoris_PreSales_ID()});
		
		if(exist > 0){
			X_C_Decoris_PreOrder cekExist = new X_C_Decoris_PreOrder(ctx, exist, null);
			return "Tidak Bisa Mengambil Data Dari Presales, Data Presales Sudah Terpakai pada Pre Order("+cekExist.getDocumentNo()+")";
			
		}
		
		MBPartner bp = new MBPartner(ctx, presales.getC_BPartner_ID(), null);
		StringBuilder SQLGetBPLoc = new StringBuilder();
		SQLGetBPLoc.append("SELECT C_BPartner_Location_ID ");
		SQLGetBPLoc.append("FROM C_BPartner_Location ");
		SQLGetBPLoc.append("WHERE AD_Client_ID = ? ");
		SQLGetBPLoc.append("AND C_BPartner_ID = ? ");
		
		int C_BPartner_Location_ID = DB.getSQLValueEx(null, SQLGetBPLoc.toString(), new Object[]{presales.getAD_Client_ID(),presales.getC_BPartner_ID()});
		MBPartnerLocation BpLoc = new MBPartnerLocation(ctx, C_BPartner_Location_ID, null);
		MLocation loc = new MLocation(ctx, BpLoc.getC_Location_ID(), null);
		
		
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_DateOrdered, presales.getDateOrdered());
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_C_BPartner_ID,presales.getC_BPartner_ID());
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_mothername, bp.get_Value("IbuKandung"));
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_Birthday, BpLoc.get_Value("BirthDay"));
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_Address1, loc.getAddress1());
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_Address2, loc.getAddress2());
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_Phone,BpLoc.getPhone());
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_Phone2, BpLoc.getPhone2());
		mTab.setValue(X_C_Decoris_PreOrder.COLUMNNAME_M_PriceList_ID, presales.getM_PriceList_ID());

		
		return "";
		
	}
	
}
