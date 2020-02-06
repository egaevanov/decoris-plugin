package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Messagebox;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;


/**
 * 
 * @author Tegar N
 *
 */
public class PosPreSales {

	public CLogger log = CLogger.getCLogger(PosCloseCash.class);
	Vector<Vector<Object>> data = null;
	Vector<Vector<Object>> Productdata = new Vector<Vector<Object>>();


	
	private Properties ctx = Env.getCtx();
	private int AD_Client_ID  = Env.getAD_Client_ID(ctx);

	
	protected BigDecimal totalPrices = Env.ZERO;
	protected BigDecimal initialDisc = Env.ZERO;
	protected BigDecimal totalDiskons = Env.ZERO;
	protected BigDecimal initialPrice = Env.ZERO;
	protected BigDecimal initialBeforeDisc = Env.ZERO;
	protected BigDecimal totalBeforeDiscounts = Env.ZERO;
	
	protected DecimalFormat format = DisplayType
			.getNumberFormat(DisplayType.Amount);
	
	protected Vector<Vector<Object>> getPreSalesData(int C_Decoris_PreSales_ID){
		
		if(C_Decoris_PreSales_ID > 0){
			data = new Vector<Vector<Object>>();
			StringBuilder SQLGetPreSalesData = new StringBuilder();
			
			SQLGetPreSalesData.append("SELECT M_Product_ID,M_Locator_ID,M_AttributeSetInstance_ID,PriceList,PriceEntered,PersentaseDiskon,QtyEntered,LineNetAmt ");
			SQLGetPreSalesData.append("FROM C_Decoris_PreSalesLine ");
			SQLGetPreSalesData.append("WHERE AD_Client_ID = ? ");
			SQLGetPreSalesData.append("AND C_Decoris_PreSales_ID = ?");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				pstmt = DB.prepareStatement(SQLGetPreSalesData.toString(), null);
				pstmt.setInt(1, AD_Client_ID);
				pstmt.setInt(2, C_Decoris_PreSales_ID);
				rs = pstmt.executeQuery();
				
				int No = 1;
				while (rs.next()) {
				
					int M_Product_ID = rs.getInt(1);
					int M_Locator_ID = rs.getInt(2);
					int M_AttributeSetInstance_ID = rs.getInt(3);
					String IMEI = "";
					
					if(M_Product_ID > 0){
						
						MProduct prod = new MProduct(ctx, M_Product_ID, null);
						
						KeyNamePair kp = new KeyNamePair(prod.getM_Product_ID(),prod.getName());
						KeyNamePair kl = null;
						if(M_AttributeSetInstance_ID > 0){
							
							MAttributeSetInstance imei = new MAttributeSetInstance(ctx, M_AttributeSetInstance_ID, null);
							IMEI = imei.getSerNo();
					
						}
						
						if(M_Locator_ID > 0){
							
							MLocator locator = new MLocator(ctx, M_Locator_ID, null);
							kl = new KeyNamePair(locator.getM_Locator_ID(),locator.getValue());
	
						}
						
						
						Vector<Object> line = new Vector<Object>(10);

						
						
						line.add(new Boolean(false));
						line.add(No++);
						line.add(kp);
						line.add(kl);
						line.add(IMEI);
						line.add(rs.getBigDecimal(4));
						line.add(rs.getBigDecimal(5));
						line.add(rs.getBigDecimal(6));
						line.add(rs.getBigDecimal(7));
						line.add(rs.getBigDecimal(8));
						
						data.add(line);	
					}
					
				}

			} catch (SQLException e) {
				log.log(Level.SEVERE, SQLGetPreSalesData.toString(), e);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}

		}
		
		return data;
	
	
	}
	
	
	protected Vector<Vector<Object>> getProductData(int M_Product_ID,int M_PriceList_ID, int C_BPartner_ID, int M_Locator_ID,int M_AttributeSetInstance_ID, IMiniTable MiniTable) {
		
		boolean issotrx = true;
		MProductPricing pp = new MProductPricing(M_Product_ID, C_BPartner_ID,Env.ONE, issotrx);

		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.AD_Client_ID = ? "
				+ " AND plv.M_PriceList_ID= ? " // 1
				+ " AND plv.ValidFrom <= ? " + "ORDER BY plv.ValidFrom DESC";
		
		int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql,new Object []{AD_Client_ID,M_PriceList_ID, date});
		
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		pp.setPriceDate(date);

		if (pp.getPriceList().compareTo(Env.ZERO) == 0) {
			Messagebox msgbox = new Messagebox();
			msgbox.show("Error !, Produk Belum Terdaftar Dalam PriceList","Error", Messagebox.OK, Messagebox.ERROR);
			return Productdata;
		}
				
		String IMEI = "";
		int No = MiniTable.getRowCount()+1;

		if(M_Product_ID > 0){
			
			MProduct prod = new MProduct(ctx, M_Product_ID, null);
			
			KeyNamePair kp = new KeyNamePair(prod.getM_Product_ID(),prod.getName());
			KeyNamePair kl = null;
			if(M_AttributeSetInstance_ID > 0){
				
				MAttributeSetInstance imei = new MAttributeSetInstance(ctx, M_AttributeSetInstance_ID, null);
				IMEI = imei.getSerNo();
		
			}
			
			if(M_Locator_ID > 0){
				
				MLocator locator = new MLocator(ctx, M_Locator_ID, null);
				kl = new KeyNamePair(locator.getM_Locator_ID(),locator.getValue());

		}
		
		Vector<Object> line = new Vector<Object>(10);

		line.add(new Boolean(false));
		line.add(No++);
		line.add(kp);
		line.add(kl);
		line.add(IMEI);
		line.add(pp.getPriceList());
		line.add(pp.getPriceList());
		line.add(Env.ZERO);
		line.add(Env.ONE);
		line.add(pp.getPriceList().multiply(Env.ONE));

		Productdata.add(line);
		}
		return Productdata;
	}
	
	public int checkShipmentRelated(int C_Order_ID){
		int M_Inout_ID = 0;
		
		String sqlInOut = "SELECT M_InOut_ID FROM M_InOut WHERE AD_Client_ID = ? AND C_Order_ID = ?";
		M_Inout_ID = DB.getSQLValueEx(Env.getCtx().toString(), sqlInOut, new Object[]{AD_Client_ID,C_Order_ID});
		
		return M_Inout_ID;
	}

	
	public int checkInvoiceRelated(int C_Order_ID){
		int C_Invoice_ID = 0;
		
		String sqlInvoice = "SELECT C_Invoice_ID FROM C_Invoice WHERE AD_Client_ID = ? AND C_Order_ID = ?";
		C_Invoice_ID  = DB.getSQLValueEx(Env.getCtx().toString(), sqlInvoice, new Object[]{AD_Client_ID,C_Order_ID});
		
		return C_Invoice_ID ;
	}
	
	public int checkPaymentRelated(int C_Order_ID){
		int C_Payment_ID = 0;
		
		String sqlPayment = "SELECT C_Payment_ID FROM C_POSPayment WHERE AD_Client_ID = ? AND C_Order_ID = ? AND TenderType = 'X' ";
		C_Payment_ID  = DB.getSQLValueEx(Env.getCtx().toString(), sqlPayment, new Object[]{AD_Client_ID,C_Order_ID});
		
		return C_Payment_ID ;
	}
	
	
	public Vector<Vector<Object>> deletedata(int rowindex,IMiniTable PresalesTable) {
		boolean IsData = false;
		
 		if(data != null){	
 			if(data.size() > 0){
				data.remove(rowindex);
				IsData = true;		
 			}
		}
		
		if(Productdata!=null){
			if(Productdata.size() > 0){
				Productdata.remove(rowindex);
			}
		}
		
		return IsData?data:Productdata;
			
	}
	
	public void reCalculate(IMiniTable miniTable) {

		for (int i = 0; i < miniTable.getRowCount(); i++) {

			BigDecimal qty = (BigDecimal) miniTable.getValueAt(i, 8);
			BigDecimal price = (BigDecimal) miniTable.getValueAt(i, 6);
			BigDecimal priceList = (BigDecimal) miniTable.getValueAt(i, 5);

			BigDecimal totalPrice = qty.multiply(price);
			BigDecimal beforeDisc = qty.multiply(priceList);
			BigDecimal disc = priceList.subtract(price).multiply(qty);

			BigDecimal totalDisc = Env.ZERO;
			BigDecimal totalLines = Env.ZERO;
			BigDecimal totalBeforeDisc = Env.ZERO;

			Double dPersen = ((Double.valueOf(priceList.toString()) - Double
					.valueOf(price.toString())) / Double.valueOf(priceList
					.toString())) * 100;
			BigDecimal persentase = BigDecimal.valueOf(dPersen).setScale(2,
					RoundingMode.HALF_DOWN);
			if (persentase.compareTo(Env.ZERO) < 0) {
				persentase = Env.ZERO;
			}

			if (i == 0) {

				initialDisc = disc;
				initialPrice = totalPrice;
				initialBeforeDisc = beforeDisc;

				totalDisc = initialDisc;
				totalLines = initialPrice;
				totalBeforeDisc = initialBeforeDisc;

			} else {

				totalDisc = initialDisc.add(disc);
				initialDisc = totalDisc;

				totalLines = initialPrice.add(totalPrice);
				initialPrice = totalLines;

				totalBeforeDisc = initialBeforeDisc.add(beforeDisc);
				initialBeforeDisc = totalBeforeDisc;
			}

			totalPrices = totalLines;
			totalDiskons = totalDisc;
			totalBeforeDiscounts = totalBeforeDisc;

			if (totalDiskons.compareTo(Env.ZERO) < 0) {
				totalDiskons = Env.ZERO;
			}

			miniTable.setValueAt(totalPrice, i, 9);
			miniTable.setValueAt(persentase, i, 7);

		}
	}
	
	
	public void CalculateTableCanged(IMiniTable miniTable, int col) {

		if(col ==6 || col == 8){
		
			for (int i = 0; i < miniTable.getRowCount(); i++) {
	
				BigDecimal qty = (BigDecimal) miniTable.getValueAt(i, 8);
				BigDecimal price = (BigDecimal) miniTable.getValueAt(i, 6);
				BigDecimal priceList = (BigDecimal) miniTable.getValueAt(i, 5);
	
				BigDecimal totalPrice = qty.multiply(price);
				BigDecimal beforeDisc = qty.multiply(priceList);
				BigDecimal disc = priceList.subtract(price).multiply(qty);
	
				BigDecimal totalDisc = Env.ZERO;
				BigDecimal totalLines = Env.ZERO;
				BigDecimal totalBeforeDisc = Env.ZERO;
	
				Double dPersen = ((Double.valueOf(priceList.toString()) - Double
						.valueOf(price.toString())) / Double.valueOf(priceList
						.toString())) * 100;
				BigDecimal persentase = BigDecimal.valueOf(dPersen).setScale(2,
						RoundingMode.HALF_DOWN);
				if (persentase.compareTo(Env.ZERO) < 0) {
					persentase = Env.ZERO;
				}
	
				if (i == 0) {
	
					initialDisc = disc;
					initialPrice = totalPrice;
					initialBeforeDisc = beforeDisc;
	
					totalDisc = initialDisc;
					totalLines = initialPrice;
					totalBeforeDisc = initialBeforeDisc;
	
				} else {
	
					totalDisc = initialDisc.add(disc);
					initialDisc = totalDisc;
	
					totalLines = initialPrice.add(totalPrice);
					initialPrice = totalLines;
	
					totalBeforeDisc = initialBeforeDisc.add(beforeDisc);
					initialBeforeDisc = totalBeforeDisc;
				}
	
				totalPrices = totalLines;
				totalDiskons = totalDisc;
				totalBeforeDiscounts = totalBeforeDisc;
	
				if (totalDiskons.compareTo(Env.ZERO) < 0) {
					totalDiskons = Env.ZERO;
				}
	
				miniTable.setValueAt(totalPrice, i, 9);
				miniTable.setValueAt(persentase, i, 7);
	
			}
		}
	}
	
	 protected int updateData(int C_DecorisPreasales_ID){
		 
			StringBuilder SQLExFunction = new StringBuilder();
	        SQLExFunction.append("SELECT update_print_info_presales(?)");
	        int rslt = 0;
	         
	     	PreparedStatement pstmt = null;
	     	ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLExFunction.toString(), null);
					pstmt.setInt(1, C_DecorisPreasales_ID);	
					rs = pstmt.executeQuery();
					while (rs.next()) {
						rslt = rs.getInt(1);
					}

				} catch (SQLException err) {
					log.log(Level.SEVERE, SQLExFunction.toString(), err);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
				
				
			 return rslt;
		 }
}
