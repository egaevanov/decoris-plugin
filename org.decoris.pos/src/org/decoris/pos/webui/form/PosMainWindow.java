package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Messagebox;
import org.adempiere.webui.window.FDialog;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_C_POSPayment;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

public class PosMainWindow {
	//public static CLogger log = CLogger.getCLogger(PosMainWindow.class);
	public CLogger log = CLogger.getCLogger(PosMainWindow.class);
	
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	
	protected BigDecimal totalPrices = Env.ZERO;
	protected BigDecimal initialDisc = Env.ZERO;
	protected BigDecimal totalDiskons = Env.ZERO;
	protected BigDecimal initialPrice = Env.ZERO;
	protected BigDecimal initialBeforeDisc = Env.ZERO;
	protected BigDecimal totalBeforeDiscounts= Env.ZERO;

	protected DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);
	
	private Properties ctx = Env.getCtx();
	private int AD_Client_ID =  Env.getAD_Client_ID(ctx);
	/**
	 * 
	 * Create by :TEGAR N
	 * 
	 */
	protected ArrayList<KeyNamePair> loadPriceList(int CreatedByPos_ID) {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M_PriceList_ID,Name FROM M_PriceList WHERE AD_Client_ID =? AND IsActive = 'Y' AND isSoPriceList= 'Y'"
				+ " AND  M_PriceList_ID IN "
				+ "(SELECT M_PriceList_ID FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?)");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Client_ID);
			pstmt.setInt(3, CreatedByPos_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}

	protected ArrayList<KeyNamePair> loadImei(int M_Product_ID, int M_Locator_ID) {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT "
				+ " a.M_AttributeSetInstance_ID,"
				+ " a.Serno "
				+ " FROM ("
				+ " SELECT null as M_AttributeSetInstance_ID,"
				+ " '' as Serno "
				+ " UNION "
				+ " SELECT M_AttributeSetInstance_ID,"
				+ " Serno "
				+ " FROM M_AttributeSetInstance "
				+ " WHERE AD_Client_ID = ? "
				+ " AND M_AttributeSetInstance_ID IN ("
				+ " SELECT M_AttributeSetInstance_ID "
				+ " FROM M_StorageOnHand "
				+ " WHERE  AD_Client_ID = ? "
				+ " AND M_Product_ID = ? AND M_Locator_ID = ? "
				+ " AND QtyOnHand > 0))a "
				+ " ORDER BY a.Serno");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Client_ID);
			pstmt.setInt(3, M_Product_ID);
			pstmt.setInt(4, M_Locator_ID);


			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}
	
	public Vector<Vector<Object>> deletedata(int rowindex) {
	
		data.remove(rowindex);
		return data;
		
	}


	protected Vector<Vector<Object>> getProductData(int M_Product_ID,int M_PriceList_ID, int C_BPartner_ID, int M_Locator_ID,int M_AttributeSetInstance_ID, IMiniTable MiniTable) {
		
		boolean issotrx = true;
		String serialNo = "-";
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
			return data;
		}
		
		if (M_AttributeSetInstance_ID > 0){
		
			MAttributeSetInstance imei = new MAttributeSetInstance(Env.getCtx(), M_AttributeSetInstance_ID, null);
			serialNo = imei.getSerNo();
			
		}
		
		MProduct product = new MProduct(Env.getCtx(), M_Product_ID, null);
		MTaxCategory taxCat = new MTaxCategory(ctx, product.getC_TaxCategory_ID(), null);
		MLocator loc = new MLocator(Env.getCtx(), M_Locator_ID, null);
		
		String Locator = loc.getValue();

		Vector<Object> line = new Vector<Object>(10);
		
		KeyNamePair kp = new KeyNamePair(product.getM_Product_ID(),product.getName());
		
		line.add(new Boolean(false));
		line.add(kp); 						// 2-Product
		line.add(Env.ONE); 					// 3-qty
		line.add(pp.getPriceList()); 		// 4-Pricelist
		line.add(pp.getPriceList());		// 5-Price
		line.add("0.00%"); 					// 6-diskon
		line.add(pp.getPriceList());		// 7-total-price
		if (!product.getProductType().equals("E")){
			line.add(Locator);
		}else if (product.getProductType().equals("E")){
			line.add("");
		}									// 8-Locator
		line.add(serialNo);					// 9-IMEI
		line.add(taxCat.getName());			// 10-TipePajak

		totalPrices = totalPrices.add(pp.getPriceList());
		data.add(line);
		
		reCalculate(MiniTable);
		
		return data;
	}

	public void tableChangeCalculate(int row, int col, IMiniTable miniTable,int windowNo , boolean IsForceLimitPrice, int M_PriceList_ID) {
		if (col == 2 || col == 3 || col == 4) {

			for (int i = 0; i < miniTable.getRowCount(); i++) {
		
				String imei = (String) miniTable.getValueAt(i, 8);
				
				BigDecimal qty = (BigDecimal) miniTable.getValueAt(i, 2);
				BigDecimal price = (BigDecimal) miniTable.getValueAt(i, 4);
				BigDecimal priceList = (BigDecimal) miniTable.getValueAt(i, 3);
				KeyNamePair prod = (KeyNamePair) miniTable.getValueAt(i, 1);
				
				BigDecimal totalPrice = qty.multiply(price);
				BigDecimal beforeDisc = qty.multiply(priceList);
				BigDecimal disc = priceList.subtract(price).multiply(qty);

				BigDecimal totalDisc = Env.ZERO;
				BigDecimal totalLines = Env.ZERO;
				BigDecimal totalBeforeDisc = Env.ZERO;
				
				
				Timestamp date = new Timestamp(System.currentTimeMillis());

				String sql = "SELECT plv.M_PriceList_Version_ID "
						+ "FROM M_PriceList_Version plv "
						+ "WHERE plv.AD_Client_ID = ? "
						+ " AND plv.M_PriceList_ID= ? " // 1
						+ " AND plv.ValidFrom <= ? " + "ORDER BY plv.ValidFrom DESC";
				
				int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql,new Object []{AD_Client_ID,M_PriceList_ID, date});
				
				String sqlProductPrice = "SELECT PriceLimit FROM M_ProductPrice WHERE AD_Client_ID = ? AND M_PriceList_Version_ID = ? AND M_Product_ID = ?";
				BigDecimal priceLimit = DB.getSQLValueBDEx(null, sqlProductPrice,new Object []{AD_Client_ID,M_PriceList_Version_ID, prod.getKey()});

				Double dPersen = ((Double.valueOf(priceList.toString()) - Double.valueOf(price.toString())) / Double.valueOf(priceList.toString())) * 100;
				BigDecimal persentase = BigDecimal.valueOf(dPersen).setScale(2,RoundingMode.HALF_DOWN);
			
				if(persentase.compareTo(Env.ZERO)<0){
					persentase = Env.ZERO;
				}
				
				if (col==2 && !imei.equals("-")){
					
					if (qty.compareTo(Env.ONE) > 0){
						FDialog.warn(windowNo, null, "", "Order untuk produk IMEI terdaftar tidak bisa lebih dari 1 " , "Peringantan");
						miniTable.setValueAt(Env.ONE, i, 2);

						return;
					}
					
				}
				
				//validasi pricelist
				if (col == 4 && IsForceLimitPrice){
					
					/*
					 * temp cmd for update limit price
					 * 
					 * 
					Timestamp date = new Timestamp(System.currentTimeMillis());

					String sql = "SELECT plv.M_PriceList_Version_ID "
							+ "FROM M_PriceList_Version plv "
							+ "WHERE plv.AD_Client_ID = ? "
							+ " AND plv.M_PriceList_ID= ? " // 1
							+ " AND plv.ValidFrom <= ? " + "ORDER BY plv.ValidFrom DESC";
					
					int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql,new Object []{AD_Client_ID,M_PriceList_ID, date});
					
					String sqlProductPrice = "SELECT M_ProductPrice_ID FROM M_ProductPrice WHERE AD_Client_ID = ? AND M_PriceList_Version_ID = ? AND M_Product_ID = ?";
					int M_ProductPrice_ID = DB.getSQLValueEx(null, sqlProductPrice,new Object []{AD_Client_ID,M_PriceList_Version_ID, prod.getKey()});

					MProductPrice newPrice = new MProductPrice(ctx, M_ProductPrice_ID, null); 
					newPrice.set_ValueNoCheck("PriceLimit", price);
					newPrice.saveEx();
					*/
				}else if (col == 4 && !IsForceLimitPrice){
					
					if(price.compareTo(priceLimit) < 0){
						FDialog.warn(windowNo, null, "", "Harga Yang Anda Inputkan Lebih Rendah Dari Limit Harga" , "Peringantan");
						return;
					}
					
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
				if (totalDiskons.compareTo(Env.ZERO)<0){
					totalDiskons = Env.ZERO;
				}
				
				miniTable.setValueAt(format.format(totalPrice), i, 6);
				miniTable.setValueAt(persentase.toString() + "%", i, 5);
				
			}
		}
	}

	
	public void reCalculate(IMiniTable miniTable) {

		for (int i = 0; i < miniTable.getRowCount(); i++) {
			
			BigDecimal qty = (BigDecimal) miniTable.getValueAt(i, 2);
			BigDecimal price = (BigDecimal) miniTable.getValueAt(i, 4);
			BigDecimal priceList = (BigDecimal) miniTable.getValueAt(i, 3);

			BigDecimal totalPrice = qty.multiply(price);
			BigDecimal beforeDisc = qty.multiply(priceList);
			BigDecimal disc = priceList.subtract(price).multiply(qty);

			BigDecimal totalDisc = Env.ZERO;
			BigDecimal totalLines = Env.ZERO;
			BigDecimal totalBeforeDisc = Env.ZERO;

			Double dPersen = ((Double.valueOf(priceList.toString()) - Double.valueOf(price.toString())) / Double.valueOf(priceList.toString())) * 100;
			BigDecimal persentase = BigDecimal.valueOf(dPersen).setScale(2,RoundingMode.HALF_DOWN);
			if(persentase.compareTo(Env.ZERO)<0){
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
			
			if (totalDiskons.compareTo(Env.ZERO)<0){
				totalDiskons = Env.ZERO;
			}
			
			miniTable.setValueAt(format.format(totalPrice), i, 6);
			miniTable.setValueAt(persentase.toString() + "%", i, 5);

		}
	}
	
	public String checkBeforeProcess(int AD_Org_ID , boolean isMultiPL,int M_PriceList_ID,int C_BPartner_ID, int M_Warehouse_ID,
			int C_PaymentTerm_ID, int SalesRep_ID,int Kasir_ID,int C_Currency_ID,int C_BankAccount_ID, String Pembayaran1, String LeaseProv, 
			String TipeByar1,String TipeByar2,boolean IstTipebayar2,boolean IspaidAll,IMiniTable MiniTable){
		
		String msg = "";
		
		MWarehouse wh = new MWarehouse(ctx, M_Warehouse_ID, null);
		MBPartner bp = new MBPartner(ctx, C_BPartner_ID, null);
		String sqlBPLoc = "SELECT C_BPartner_Location_ID "
				+ " FROM C_BPartner_Location "
				+ " WHERE AD_Client_ID = ? AND C_BPartner_ID =? ";
		
		int C_BPartner_Location_ID = DB.getSQLValueEx(null, sqlBPLoc, new Object[]{AD_Client_ID,C_BPartner_ID});
		
		if (AD_Org_ID == 0){
			msg = "Kolom Cabang Harus Di Isi";
		}else if(C_BPartner_ID ==0){
			msg = "Kolom Pelanggan Belum Di Isi";
		}else if(C_BPartner_Location_ID <=0){
			msg = "Belum Ada Alamat Untuk Pelanggan "+bp.getName() +",Silakan Isikan Alamat Terlebih Dahulu Pada Window Pelanggan";
		}else if(MiniTable.getRowCount() == 0){
			msg = "Belum Ada Input Produk";
		}else if(M_Warehouse_ID ==0){
			msg = "Kolom Warehouse Belum Di Isi";
		}else if(wh.getName().toUpperCase().equals("STANDARD")){
			msg = "Kolom Warehouse Belum Di Isi Dengan Benar";
		}else if(C_PaymentTerm_ID == 0){
			msg = "Kolom Tempo Belum Di Isi";
		}else if(M_PriceList_ID <= 0 && isMultiPL){
			msg = "Kolom Price List Belum Di Isi";
		}else if(SalesRep_ID == 0){
			msg = "Kolom Sales Belum Terisi";
		}else if(Kasir_ID == 0){
			msg = "Kolom Kasir Belum Terisi";
		}else if(Pembayaran1 =="0.00"){
			msg = "Pembayaran Masih Kosong";
		}else if(C_Currency_ID == 0){
			msg = "Kolom Mata Uang Belum Terisi";
		}else if(TipeByar1.toUpperCase().equals("LEASING")&& LeaseProv == null){
			msg = "Kolom Leasing Belum Terisi";
		}else if (IstTipebayar2 && TipeByar2.toUpperCase().equals("LEASING")&&LeaseProv == null){
			msg = "Kolom Leasing Belum Terisi";			
		}else if (!IspaidAll){
			msg = "Total Pembayaran Masih Kurang Dari Total Yang Harus Dibayar";
		}else if(TipeByar1.toUpperCase().equals("BANK")&& C_BankAccount_ID == 0){
			msg = "Kolom Bank Belum Terisi";
		}else if (IstTipebayar2 && TipeByar2.toUpperCase().equals("BANK")&&C_BankAccount_ID == 0){
			msg = "Kolom Bank Belum Terisi";	
		}
		return msg;
		
	}
	
	public void createPOSPayment(int AD_Org_ID,int C_Order_ID,int C_POSTenderType_ID, String tenderType, 
			BigDecimal payAmt,String leasingProvider, int C_BankAccount_ID){
		
		MOrder order = new MOrder(Env.getCtx(), C_Order_ID, null); 
		
		X_C_POSPayment posPayment = new X_C_POSPayment(order.getCtx(), 0, order.get_TrxName());
		
		posPayment.setAD_Org_ID(AD_Org_ID);
		posPayment.setC_Order_ID(C_Order_ID);
		posPayment.setC_POSTenderType_ID(C_POSTenderType_ID);
		posPayment.setTenderType(tenderType);
		posPayment.setPayAmt(payAmt);
		if (tenderType.equals("L")){
			posPayment.set_CustomColumn("LeaseProvider", leasingProvider);
		}
		posPayment.set_CustomColumn("C_BankAccount_ID", C_BankAccount_ID);
		posPayment.saveEx();
	}
	
	public void infoGeneratedDocument(int C_Order_ID, int WindowNo){

		String message = "";
		String sqlInOut = "SELECT M_InOut_ID FROM M_InOut WHERE AD_Client_ID = ? AND C_Order_ID = ?";
		String sqlInvoice = "SELECT C_Invoice_ID FROM C_Invoice WHERE AD_Client_ID = ? AND C_Order_ID = ?";
		String sqlPosPay = "SELECT C_Payment_ID,TenderType FROM C_POSPayment WHERE AD_Client_ID = ? AND C_Order_ID = ?";
		
		int M_InOut_ID = DB.getSQLValueEx(Env.getCtx().toString(), sqlInOut.toString(), new Object[]{AD_Client_ID,C_Order_ID});
		
		Integer C_Invoice_ID = DB.getSQLValueEx(Env.getCtx().toString(), sqlInvoice.toString(), new Object[]{AD_Client_ID,C_Order_ID});
		
		if (C_Invoice_ID == null){
			C_Invoice_ID = 0;
		}
		
		//Info order
		MOrder order = new MOrder(Env.getCtx(), C_Order_ID, null);
		String noSo = order.getDocumentNo();
		String statusSO= order.getDocStatusName();
		String msgOrder = "No Sales Order : " + noSo +"("+statusSO+")";
		
		//Info Shipment
		String msgInOut = "\n"+ "No Surat Jalan : Belum Terbentuk";
		MInOut InOut = new MInOut(Env.getCtx(), M_InOut_ID, null);
		if(M_InOut_ID > 0){
			String noShip = InOut.getDocumentNo();
			String statusShip = InOut.getDocStatusName();
			msgInOut = "\n"+ "No Surat Jalan : " + noShip +"("+statusShip+")";
		}
		//Info Invoice
		String msgInv = "\n"+ "No Faktur : Belum Terbentuk" ;
		if (C_Invoice_ID > 0){
			MInvoice inv = new MInvoice(Env.getCtx(),C_Invoice_ID,null);
			String noInv = inv.getDocumentNo();
			String statusInv = inv.getDocStatusName();
			msgInv = "\n"+ "No Faktur : " + noInv+"("+statusInv+")";
		}

		//infoResult
		message = msgOrder + msgInOut + msgInv;
		
		//Info AR Receipt
		String msgAR = "\n"+ "No Penerimaan :  Belum Terbentuk ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sqlPosPay.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, C_Order_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int C_Payment_ID = rs.getInt(1);
				MPayment AR = new MPayment(Env.getCtx(), C_Payment_ID, null);
				String noAR = AR.getDocumentNo();
				String payType = rs.getString(2);
				String payAmt = format.format(AR.getPayAmt());
				String statusAR = AR.getDocStatusName();
				
				if (payType.equals("L")){
					payType = "Leasing";
				}else if (payType.equals("X")){
					payType = "Tunai";
				}
				if(C_Payment_ID > 0){
					msgAR = "\n"+ "No Penerimaan : " + noAR +"("+payType+" : "+payAmt+" - "+statusAR+")";
					message = message + msgAR;
					msgAR = "";
				}
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sqlPosPay.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		message = message+msgAR;
		FDialog.info(WindowNo,null,"",message,"Dokumen Terbentuk");
		
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
	
	public String checkStok(IMiniTable miniTable, int windowNo) {
		String isStocked = "";
		String sqlStock = "SELECT M_Product_ID FROM M_StorageOnHand WHERE AD_Client_ID = ? AND M_Product_ID = ? AND M_Locator_ID = ? AND QtyOnHand > 0";
		String sqlQty = "SELECT SUM(QtyOnHand) FROM M_StorageOnHand WHERE AD_Client_ID = ? AND M_Product_ID = ? AND M_Locator_ID = ?";
		//cek summary product qty
		Map<Integer, BigDecimal> prodMap;	
		Map<Integer, Map<Integer, BigDecimal>> prodLocMap;
		prodLocMap = new HashMap<Integer, Map<Integer,BigDecimal>>();
		
		for (int i = 0; i < miniTable.getRowCount(); i++){
			
			
			
			KeyNamePair product = (KeyNamePair) miniTable.getValueAt(i, 1);
			String Loc = (String) miniTable.getValueAt(i, 7);
			BigDecimal QtyOrder = (BigDecimal) miniTable.getValueAt(i, 2);

			int product_ID = product.getKey();
			String sqlLoc = "SELECT M_Locator_ID FROM M_Locator WHERE AD_Client_ID = ? AND value = '"+Loc+"'";
			int Loc_ID = DB.getSQLValueEx(Env.getCtx().toString(), sqlLoc, new Object[]{AD_Client_ID});
			MLocator locator = new MLocator(ctx, Loc_ID, null);
			int result_ID = DB.getSQLValueEx(Env.getCtx().toString(), sqlStock, new Object[]{AD_Client_ID,product_ID,Loc_ID}); 
			BigDecimal QtyStock = DB.getSQLValueBDEx(Env.getCtx().toString(), sqlQty, new Object[]{AD_Client_ID,product_ID,Loc_ID}); 

			
			MProduct prod = new MProduct(ctx, product_ID, null);
			if(prod.getProductType().toUpperCase().equals("I")){
				
				
				prodMap = new HashMap<Integer, BigDecimal>();
				if(prodLocMap.isEmpty()){
					prodMap.put(Loc_ID, QtyOrder);
					prodLocMap.put(product_ID, prodMap);
				}
				
				if(result_ID < 0){
					isStocked = "Product "+product.getName()+" Tidak Ada Stok Di Lokasi "+ locator.getValue()+" Silakan Tidak Menceklist Pick Up Dan Mengisi Tanggal Pengiriman";
				}
				else if(result_ID > 0){
					
					if(QtyStock.compareTo(QtyOrder) < 0){
						
						isStocked = "Stok Product "+product.getName()+" Di Lokasi "+ locator.getValue()+ " Kurang Dari Qty Penjualan";
						
					}
					
				}
				else if(prodLocMap.size() >= 1){
	
					BigDecimal qtySum = Env.ZERO;
						
					if(prodLocMap.containsKey(product_ID)){

						HashMap<Integer, BigDecimal>  n = (HashMap<Integer, BigDecimal>) prodLocMap.get(product_ID);
							
						if(n.containsKey(Loc_ID)){
						
							BigDecimal qtyProd = n.get(Loc_ID);
							qtySum = qtyProd.add(QtyOrder);
							n.put(Loc_ID, qtySum);
								
						}else{
							n.put(Loc_ID, QtyOrder);
						}
								
						if(qtySum.compareTo(QtyStock) > 0){
							isStocked = "Stok Product "+product.getName()+" Di Lokasi "+ locator.getValue()+ " Kurang Dari Qty Penjualan";
							return isStocked;
						}
					}else{
						prodMap.put(Loc_ID, QtyOrder);
						prodLocMap.put(product_ID, prodMap);
					}
					
				}
				//end
				
		}
			
		}
		
		return isStocked;
	}
	
	public String checkImeiExist(IMiniTable miniTable, int windowNo){

		String msg = "";

		for (int i = 0; i < miniTable.getRowCount(); i++){
			boolean IsHaveImei = false;
			KeyNamePair product = (KeyNamePair) miniTable.getValueAt(i, 1);
			String imei = (String) miniTable.getValueAt(i, 8);
			
		String sqlProduct = "SELECT M_Product_ID FROM M_Product WHERE AD_Client_ID = ? AND name = '"+product.toString()+"'";
		int Product_ID = DB.getSQLValue(ctx.toString(), sqlProduct.toString(), new Object[]{AD_Client_ID});
			
		String sqlChekImei = "SELECT M_AttributeSetInstance_ID FROM M_StorageOnHand "
				+ "WHERE AD_Client_ID = ? AND M_Product_ID = ? AND M_AttributeSetInstance_ID <> 0 AND QtyOnHand > 0";
		
		int M_AttributeSetInstance_ID = DB.getSQLValueEx(Env.getCtx().toString(), sqlChekImei, new  Object[]{AD_Client_ID,Product_ID});
		
		if (M_AttributeSetInstance_ID > 0){
			IsHaveImei = true;
		}
		
		if (IsHaveImei){
			if (imei.equals("-")){
				FDialog.warn(windowNo, null, "", "Produk "+product+" memiliki IMEI, mohon input IMEI terlebih dahulu", "Peringatan");
				msg = "Error";
			}
		}
		
		
		}
		
		return msg;
	}
	
	public String cekBankAccount(String leaseProv){
		
		StringBuilder SQLleaseProvider =  new StringBuilder();
		Integer C_BankAccount_ID = 0;
		String msg = "";
		

		
		SQLleaseProvider.append("SELECT C_BankAccount_ID ");
		SQLleaseProvider.append("FROM C_BankAccount ");
		SQLleaseProvider.append("WHERE AD_Client_ID = ? ");
		SQLleaseProvider.append("AND LeaseProvider = '"+leaseProv+"'");
		
		
		C_BankAccount_ID = DB.getSQLValueEx(Env.getCtx().toString(), SQLleaseProvider.toString(), new Object[]{AD_Client_ID});
		
		if(C_BankAccount_ID == null ||C_BankAccount_ID == -1){
			C_BankAccount_ID = 0;
		}
		
		if(C_BankAccount_ID == 0){
			msg = "Bank Account Untuk Leasing Yang dipilih Belum Ditentukan";
		}
		
		return msg;
	}
	
}
