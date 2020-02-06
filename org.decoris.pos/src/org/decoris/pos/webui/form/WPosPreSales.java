package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.base.Core;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.ITaxProvider;
import org.adempiere.util.Callback;
import org.adempiere.util.ProcessUtil;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.DialogEvents;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.window.FDialog;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCity;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.MPriceList;
import org.compiere.model.MProcess;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.model.MRegion;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MTaxProvider;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_C_POSPayment;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.decoris.model.MDecorisPOS;
import org.decoris.model.X_C_DecorisPOS;
import org.decoris.model.X_C_DecorisPOSLine;
import org.decoris.model.X_C_Decoris_PreSales;
import org.decoris.model.X_C_Decoris_PreSalesLine;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.North;
import org.zkoss.zul.Separator;
import org.zkoss.zul.South;
import org.zkoss.zul.Space;
import org.zkoss.zul.Vbox;


/**
 * 
 * @author Tegar N
 *
 */
public class WPosPreSales extends PosPreSales implements IFormController,
EventListener<Event>, WTableModelListener, ValueChangeListener{

	// CustomForm
	private CustomForm formPresales = new CustomForm();
	
	
	// BorderLayout
	private Borderlayout mainLayout = new Borderlayout();
	private Borderlayout infoLayout = new Borderlayout();
	private Borderlayout presalesLayout = new Borderlayout();

	
	private Panel parameterPanel = new Panel();
	private Panel preSalesPanel = new Panel();
	private Panel southPanel = new Panel();


	// Grid
	private Grid parameterGrid = GridFactory.newGridLayout();
	private Grid parameterGrid2 = GridFactory.newGridLayout();
	private Grid parameterGrid3 = GridFactory.newGridLayout();
	private Grid parameterGrid4 = GridFactory.newGridLayout();

	
//	private Grid southGrid1 = GridFactory.newGridLayout();
	private Grid southGrid2 = GridFactory.newGridLayout();
	private Grid southGrid3 = GridFactory.newGridLayout();
	private Grid southGrid4 = GridFactory.newGridLayout();

	private Grid southButtonGrid = GridFactory.newGridLayout();

	//No PreSales
	private Label NoPresalesLabel = new Label("No Presales :");
	private WSearchEditor noPresales = null;
	
	//No Pembayaran
	private Label NoPembayaranLabel = new Label("No Pembayaran :");
	private Textbox noPembayaran = new Textbox();

	//Toko
	private Label TokoLabel = new Label("Toko :");
	private WTableDirEditor TokoSearch = null;

	//Sales
	private Label SalesLabel = new Label("Sales :");
	private WTableDirEditor  SalesSearch = null;

	//Daftar harga
	private Label DaftarHargaLabel = new Label("Daftar Harga :");
	private Combobox  DaftarHargaSearch = new Combobox();
	
	// Leasing provider
//	private Label leasingProviderLabel = new Label("Leasing :");
//	private WTableDirEditor leasingProviderSearch = null;
	
	// Akun bank
//	private Label bankAccountLabel = new Label("Akun Bank :");
//	private WTableDirEditor bankAccountSearch = null;
	
	//Gudang
	private Label GudangLabel = new Label("Gudang :");
	private Combobox  GudangSearch = new Combobox();
	
	//Product
	private Label ProductLabel = new Label("Product :");
	private WSearchEditor  ProductSearch = null;
		
	//Status
	private Label StatusLabel = new Label("Status :");
	private WTableDirEditor Status = null;
	
	// DateOrdered
	private Label tanggalLabel = new Label("Tanggal :");
	private Datebox tanggalField = new Datebox();
	
	// Tutup kas
	private Checkbox TutupKasCheck = new Checkbox();
	
	// Pik Up	
	private Label PickUpLabel = new Label("Pickup :");
	private Checkbox PickUp= new Checkbox();
	
	// Org
	private Label orgLabel = new Label("Org :");
	private WTableDirEditor orgSearch = null;
	
	//Pelanggan Baru
	private Button pelangganBaru = new Button();

	//Pelanggan
	private Label PelangganLabel = new Label("Pelanggan :");
	private WSearchEditor PelangganSearch = null;
	
	//Sales
	private Label LokasiLabel = new Label("Lokasi :");
	private Combobox  Lokasi = new Combobox();
	
	//Sales
	private Label IMEILabel = new Label("IMEI :");
	private Combobox  IMEIList = new Combobox();
	
	//Status
	private Label TotalLabel = new Label("Total :");
	private Textbox TotalTextBox1 = new Textbox();
			
	//Status
	private Label totalBayarLabel = new Label("Total Bayar :");
	private Textbox totalBayarTextBox = new Textbox();
	
	//Status
	private Label kembaliLabel = new Label("Kembali :");
	private Textbox kembaliTextBox = new Textbox();
	
	//Status
	private Label keteranganLabel = new Label("Keterangan :");
	private Textbox keteranganTextBox = new Textbox();
	
	//private Button buatBaru = new Button();
	//private Button process = new Button();
	private Button batal = new Button();
	private Button notaKecil = new Button();
	private Button notaBesar = new Button();
	
	
	private int C_DecorisPresalesPrint_ID = 0;
	
	// PaymentRule1
	//private Textbox payruleBoxTunai = new Textbox();
	//private Decimalbox paymentTunai = new Decimalbox();

	// PaymentRule2
//	private Textbox payruleBoxBank = new Textbox();
//	private Decimalbox paymentBank = new Decimalbox();

	// PaymentRule3
//	private Textbox payruleBoxLeasing = new Textbox();
//	private Decimalbox paymentLeasing = new Decimalbox();
	
	// PaymentRule4
//	private Textbox payruleBoxHutang = new Textbox();
//	private Decimalbox paymentHutang = new Decimalbox();

	
	//Status
	private Label totalBrutoLabel = new Label("Total :");
	private Textbox totalBrutoTextBox = new Textbox();
		
	//Status
	private Label diskonLabel = new Label("Diskon :");
	private Textbox diskonTextBox = new Textbox();
	
	private Label totalBelanjaLabel = new Label("Total Belanja :");
	private Textbox totalBelanjaTextBox = new Textbox();
	
	private Label totalBayarTunaiLabel = new Label("Bayar Tunai:");
	private Textbox totalBayarTunaiTextBox = new Textbox();
	
	private Label kembalianLabel = new Label("Kembalian :");
	private Textbox kembalianTextBox = new Textbox();
	
	private Label DPPLabel = new Label("DPP :");
	private Textbox DPPTextBox = new Textbox();	
	
	private Label PPNLabel = new Label("PPN :");
	private Textbox PPNTextBox = new Textbox();	
	
	private Label space = new Label(" ");
	private Textbox spaceBox = new Textbox();
	
	private Button deleteItem = new Button();

	
//	private Label tipePembayaranLabel = new Label("Tipe Pembayaran");
//	private Label amountlabel = new Label("Amount");
	
	// TableLine
	private WListbox PreSalesTable = ListboxFactory.newDataTable();

	
	private Properties ctx = Env.getCtx();
	private int AD_Client_ID  = Env.getAD_Client_ID(ctx);
	//private int CreatedByPOS_ID = 0;
	private int AD_User_ID = Env.getAD_User_ID(ctx);;
	//private int p_AD_Org_ID = 0;
	private int windowNo = formPresales.getWindowNo();
	private String AD_Language = Env.getAD_Language(ctx);
	private Integer productID = 0;
	Integer m_precision = null;
	private Integer rowIndex = null;
	//private Integer imeiIndex = null;
	private int CreatedByPOS_ID = 0;
	private int inout_ID = 0;

	
	//private int C_DecorisPOSPrint_ID = 0;

	private Map<String, BigDecimal> mapPospay;

	protected BigDecimal postotalPrice = Env.ZERO;
	protected BigDecimal postotalDiskon = Env.ZERO;
	protected BigDecimal postotalBeforeDiscount = Env.ZERO;
	private BigDecimal posnilaiGrandTotal = Env.ZERO;
	private BigDecimal posnilaiDpp = Env.ZERO;
	private BigDecimal posnilaiPajak = Env.ZERO;
	

	// Formatter
	private DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);

	
	@Override
	public void valueChange(ValueChangeEvent evt) {
		
		try {
			String name = evt.getPropertyName();

			Object value = evt.getNewValue();
			Integer presales_ID = (Integer) noPresales.getValue();
			if(presales_ID == null){
				presales_ID=0;
			}
			if (value==null) {
				
				if(name.equals("C_Decoris_PreSales_ID")){
					PreSalesTable.clear();
					PreSalesTable.clearTable();
					PreSalesTable.removeAllItems();
					
					SalesSearch.setValue(null);
					DaftarHargaSearch.setValue(null);
					GudangSearch.setValue(null);
					tanggalField.setValue(null);
					PickUp.setChecked(false);
					PickUp.setEnabled(false);
					PelangganSearch.setValue(null);
					pelangganBaru.setEnabled(false);
					Lokasi.setValue(null);
					TutupKasCheck.setChecked(false);
					TutupKasCheck.setEnabled(false);
					//leasingProviderSearch.setValue(null);
					//bankAccountSearch.setValue(null);
					deleteItem.setEnabled(false);
					
					//pembayaran
//					paymentTunai.setValue("0.00");
//					paymentBank.setValue("0.00");
//					paymentLeasing.setValue("0.00");
//					paymentHutang.setValue("0.00");
//					
//					paymentTunai.setReadonly(false);
//					paymentBank.setReadonly(false);
//					paymentLeasing.setReadonly(false);
//					paymentHutang.setReadonly(false);
					orgSearch.setReadWrite(false);

	
					totalBayarTextBox.setText(format.format(0));
					kembaliTextBox.setText(format.format(0));
					totalBrutoTextBox.setText(format.format(0));
					TotalTextBox1.setText(format.format(0));
					diskonTextBox.setText(format.format(0));
					totalBelanjaTextBox.setText(format.format(0));
					totalBayarTunaiTextBox.setText(format.format(0));
					kembalianTextBox.setText(format.format(0));
					DPPTextBox.setText(format.format(0));
					PPNTextBox.setText(format.format(0));	
				}else if(name.equals("M_PriceList_ID")
						||name.equals("M_Warehouse_ID")
						||name.equals("M_Locator_ID")
						||name.equals("C_Bpartner_ID")){
					
				}else{
					return;
				}
				
			}else if (name.equals("C_Decoris_PreSales_ID")) {
				
				Integer C_Decoris_PreSales_ID = (Integer) value;			
				
				X_C_Decoris_PreSales preSales = new X_C_Decoris_PreSales(ctx, C_Decoris_PreSales_ID, null);
				
				StringBuilder SQLgetDec_ID = new StringBuilder();
				SQLgetDec_ID.append("SELECT C_DecorisPOS_ID ");
				SQLgetDec_ID.append(" FROM C_DecorisPOS ");
				SQLgetDec_ID.append(" WHERE AD_Client_ID = ? ");
				SQLgetDec_ID.append(" AND C_Decoris ");

				
				
//				if(preSales.getStatus().equals("CL")){
//					FDialog.info(windowNo, null, "", "Status Presales yang anda inputkan sudah Close", "Info");
//					return;
//				}
				
				if(preSales != null){
					
					Vector<Vector<Object>> data = getPreSalesData(C_Decoris_PreSales_ID);
					Vector<String> columnNames = getOISColumnNames();

					PreSalesTable.clear();

					// Set Model
					ListModelTable modelP = new ListModelTable(data);
					modelP.addTableModelListener(this);
					PreSalesTable.setData(modelP, columnNames);
					configureMiniTableExistingPresales(PreSalesTable);
					
				}
				
				Status.setValue(preSales.getStatus());
				IMEIList.setEnabled(false);
				noPresales.setValue(preSales.getDocumentNo());
				SalesSearch.setValue(preSales.getSalesRep_ID());
				SalesSearch.setReadWrite(false);
				if(preSales.getM_PriceList_ID() > 0){
					MPriceList PL = new MPriceList(ctx, preSales.getM_PriceList_ID(), null);
					ArrayList<KeyNamePair> listPL = new ArrayList<KeyNamePair>();
					listPL.add(new KeyNamePair(PL.getM_PriceList_ID(), PL.getName()));
					DaftarHargaSearch.removeAllItems();
					for (KeyNamePair imei : listPL)
					DaftarHargaSearch.appendItem(imei.getName());
					DaftarHargaSearch.setSelectedIndex(0);
					
					if(listPL.size() > 0){
						DaftarHargaSearch.setSelectedIndex(0);
					}
					
				}
				orgSearch.setValue(preSales.getAD_Org_ID());
				DaftarHargaSearch.setReadonly(true);
				DaftarHargaSearch.setEnabled(false);
				
				if(preSales.getM_Warehouse_ID() > 0){
					MWarehouse WH = new MWarehouse(ctx, preSales.getM_Warehouse_ID(), null);
					ArrayList<KeyNamePair> listWH = new ArrayList<KeyNamePair>();
					listWH.add(new KeyNamePair(WH.getM_Warehouse_ID(), WH.getName()));
					GudangSearch.removeAllItems();
					for (KeyNamePair imei : listWH)
					GudangSearch.appendItem(imei.getName());
					GudangSearch.setSelectedIndex(0);
					
					if(listWH.size() > 0){
						GudangSearch.setSelectedIndex(0);
					}
					
				}
				
				GudangSearch.setReadonly(true);
				GudangSearch.setEnabled(false);
				ProductSearch.setReadWrite(false);
				tanggalField.setValue(preSales.getDateOrdered());
				tanggalField.setReadonly(true);
				PickUp.setChecked(preSales.isPickup());
				PickUp.setEnabled(false);
				PelangganSearch.setValue(preSales.getC_BPartner_ID());
				PelangganSearch.setReadWrite(false);
				pelangganBaru.setEnabled(false);
				notaKecil.setEnabled(true);
				notaBesar.setEnabled(true);
				keteranganTextBox.setReadonly(false);
				if(preSales.getM_Locator_ID()>0){
					Lokasi.setValue(preSales.getM_Locator_ID());
				}
				Lokasi.setReadonly(true);
				Lokasi.setEnabled(false);

				TutupKasCheck.setChecked(preSales.isTutupKas());
				TutupKasCheck.setEnabled(false);
				TutupKasCheck.setVisible(false);
				deleteItem.setEnabled(false);
				orgSearch.setReadWrite(false);
				//leasingProviderSearch.setValue(preSales.getleaseprovider());
				//bankAccountSearch.setValue(preSales.getC_BankAccount_ID());
				
				
				if(preSales.getC_Payment_ID() > 0){
					MPayment pay = new MPayment(ctx, preSales.getC_Payment_ID(), null);
					noPembayaran.setValue(pay.getDocumentNo());	
				}
						
				//pembayaran				
//				paymentTunai.setValue(preSales.getJumlahPembayaranTunai());
//				paymentBank.setValue(preSales.getJumlahPembayaranBank());
//				paymentLeasing.setValue(preSales.getJumlahPembayaranLeasing());
//				paymentHutang.setValue(preSales.getJumlahPembayaranHutang());

				
				
//				paymentTunai.setReadonly(true);
//				paymentBank.setReadonly(true);
//				paymentLeasing.setReadonly(true);
//				paymentHutang.setReadonly(true);
//				bankAccountSearch.setReadWrite(false);
//				leasingProviderSearch.setReadWrite(false);
				
				
				batal.setEnabled(true);
				//process.setEnabled(true);
				
//				if(preSales.get_Value("orderidori") != null && preSales.getStatus().equals("IP")){
//					process.setEnabled(false);
//				}
				
//				BigDecimal tunai = paymentTunai.getValue();
//				BigDecimal bank = paymentBank.getValue();	
//				BigDecimal leasing = paymentLeasing.getValue();	
//				BigDecimal hutang = paymentHutang.getValue();	

				//BigDecimal totalBayar =tunai.add(bank);
				//BigDecimal totalBayarAll = tunai.add(bank).add(leasing).add(hutang);

				totalBayarTextBox.setText(format.format(preSales.getTotalBayar()));
				
				//BigDecimal kembali = totalBayar.subtract(preSales.getTotalBelanja());
				//kembaliTextBox.setText(format.format(kembali));
				
				TotalTextBox1.setText(format.format(preSales.getTotalBelanja()));
				
				totalBrutoTextBox.setText(format.format(preSales.gettotal()));
				diskonTextBox.setText(format.format(preSales.getTotalDiskon()));
				
				totalBelanjaTextBox.setText(format.format(preSales.getTotalBelanja()));
				totalBayarTunaiTextBox.setText(format.format(preSales.getTotalBayar()));
				//totalBayarTextBox.setText(format.format(totalBayarAll));
				
				
				
				kembalianTextBox.setText(format.format(preSales.get_Value("TotalKembalian")));
				kembaliTextBox.setText(format.format(preSales.get_Value("TotalKembalian")));
				DPPTextBox.setText(format.format(preSales.getdpp()));
				PPNTextBox.setText(format.format(preSales.getTaxAmt()));

				C_DecorisPresalesPrint_ID = preSales.getC_Decoris_PreSales_ID();

				
				if(Status.getValue().equals(X_C_Decoris_PreSales.STATUS_Completed)){
					//process.setEnabled(false);
				}else if(Status.getValue().equals(X_C_Decoris_PreSales.STATUS_Closed)){
					//process.setEnabled(false);
					batal.setEnabled(false);
					notaBesar.setEnabled(false);
					notaKecil.setEnabled(false);
					tanggalField.setEnabled(false);
				}	
				calculateDPPExistingPresales();	
				
			}else if (name.equals("M_Product_ID")) {

				ProductSearch.setValue(value);
				productID = ((Integer) value).intValue();

				if (productID != null) {

					Integer M_PriceList_ID =getIDFromComboBox(DaftarHargaSearch,MPriceList.Table_Name,MPriceList.COLUMNNAME_Name);
					Integer C_BPartner_ID = (Integer) PelangganSearch.getValue();
					Integer M_Locator_ID = getIDFromComboBox(Lokasi, MLocator.Table_Name,MLocator.COLUMNNAME_Value);
					MProduct prod = new MProduct(ctx, productID, null);
					
					
					if(M_PriceList_ID == null){
						FDialog.info(windowNo, null, "", "Kolom Daftar Harga Tidak Boleh Kosong", "Info");
						return;
					}else if (M_Locator_ID == null){
						FDialog.info(windowNo, null, "", "Kolom Lokasi Tidak Boleh Kosong", "Info");
						return;
					}else if (C_BPartner_ID == null){
						FDialog.info(windowNo, null, "", "Kolom Pelanggan Tidak Boleh Kosong", "Info");
						return;
					}					
					
					StringBuilder SQLCekLocation = new StringBuilder();
					SQLCekLocation.append("SELECT M_Locator_ID ");
					SQLCekLocation.append("FROM M_StorageOnHand ");
					SQLCekLocation.append("WHERE AD_Client_ID = ? ");
					SQLCekLocation.append("AND M_Product_ID = ? ");
					SQLCekLocation.append("AND M_Locator_ID = ? ");
					
//					String serno = "";
					Integer Loc_ID = 0;
//					Integer M_AttributeSetInstance_ID = 0;
//					
//					int listSize = IMEIList.getItemCount();
//					if (listSize > 1){
//					
//						serno = IMEIList.getSelectedItem().getId();
//						String sql = "SELECT M_AttributeSetInstance_ID"
//								+ " FROM M_AttributeSetInstance "
//								+ " WHERE AD_Client_ID = ?" + " AND Serno = '" + serno
//								+ "'";
//						M_AttributeSetInstance_ID = DB.getSQLValueEx(null, sql,AD_Client_ID);
//					
//					}
					
					//if(!serno.isEmpty()&& serno != null && serno != ""){
					//	SQLCekLocation.append("AND M_AttributeSetInstance_ID = ? ");
											
					//	Loc_ID = DB.getSQLValueEx(null, SQLCekLocation.toString(),new Object []{AD_Client_ID,productID,M_Locator_ID,M_AttributeSetInstance_ID});

					//}else{
					Loc_ID = DB.getSQLValueEx(null, SQLCekLocation.toString(),new Object []{AD_Client_ID,productID,M_Locator_ID});
					//}
					
					MLocator loc = new MLocator(ctx, M_Locator_ID, null);
					
					if(Loc_ID == null ){
						Loc_ID = 0;		
					}
					
					if(Loc_ID <= 0 && prod.getProductType().equalsIgnoreCase(MProduct.PRODUCTTYPE_Item)){
						FDialog.info(windowNo, null, "", "Produk Tidak Ada Di Lokasi "+ loc.getValue(), "Info");
						return;
					}
						
					
					if(prod.getM_AttributeSet_ID() > 0){
						
						FDialog.info(windowNo, null, "", "Produk yang Anda Pilih memiliki IMEI, Silakan Input IMEI terlebih dahulu sebelum Input Produk Lain", "Info");
					}
					
					Vector<Vector<Object>> data = getProductData(productID,M_PriceList_ID, C_BPartner_ID, M_Locator_ID, 0,PreSalesTable);
					Vector<String> columnNames = getOISColumnNames();

					PreSalesTable.clear();

					// Set Model
					ListModelTable modelP = new ListModelTable(data);
					modelP.addTableModelListener(this);
					PreSalesTable.setData(modelP, columnNames);
					configureMiniTable(PreSalesTable);

					ArrayList<KeyNamePair> list = loadImei(productID);
					IMEIList.removeAllItems();
					for (KeyNamePair imei : list)
					IMEIList.appendItem(imei.getName());
					IMEIList.setSelectedIndex(0);
					calculateAddProduct();
					
					
					
					

				} else {

					ArrayList<KeyNamePair> list = loadImei(productID);
					IMEIList.removeAllItems();
					for (KeyNamePair imei : list)
					IMEIList.appendItem(imei.getName());
					IMEIList.setSelectedIndex(0);

				}
			}else if(name.equals("C_BPartner_ID")){
				String sPriceList = DaftarHargaSearch.getText();
				if( sPriceList != null && sPriceList != "" && Lokasi.getValue() != null){
					
					ProductSearch.setReadWrite(true);
					
				}else{
					ProductSearch.setReadWrite(false);

				}
				
			}else if(name.equals("AD_Org_ID")){
				
				//Warehouse
				Integer Org_ID = (Integer) orgSearch.getValue();
				if(Org_ID == null){
					Org_ID = 0 ;
				}
				
				ArrayList<KeyNamePair> listWH = loadWarehouse(Org_ID);
				Integer warehouse_ID = 0;
				GudangSearch.removeAllItems();
				for (KeyNamePair priceList : listWH){
					GudangSearch.appendItem(priceList.getName());
				}
				GudangSearch.setSelectedIndex(0);
				Lokasi.setEnabled(true);
				
				if(listWH.size()>0){
					warehouse_ID= getIDFromComboBox(GudangSearch, MWarehouse.Table_Name,MWarehouse.COLUMNNAME_Name);
				}
				if(warehouse_ID == null){
					warehouse_ID = 0;
				}
				
				ArrayList<KeyNamePair> listLoc = loadLocator(warehouse_ID,Org_ID);
				Lokasi.removeAllItems();
				for (KeyNamePair loc : listLoc)
				Lokasi.appendItem(loc.getName());
				
				if(listLoc.size()> 0){
					Lokasi.setSelectedIndex(0);
				}
				
			}
		
		} catch (Exception e) {
			log.log(Level.SEVERE, this.getClass().getCanonicalName()
					+ ".valueChange - ERROR: " + e.getMessage(), e);
		}
		
	}

	@Override
	public void tableChanged(WTableModelEvent ev) {
		boolean isUpdate = (ev.getType() == WTableModelEvent.CONTENTS_CHANGED);
		
		if (!isUpdate) {
			return;
		} else if (isUpdate) {
			//imeiIndex = ev.getFirstRow();
			rowIndex = ev.getFirstRow();
			int col = ev.getColumn();
			
			if(Status.getValue().equals("CO")){
				
				PreSalesTable.setColumnClass(6, BigDecimal.class, false); 	// 6-price
				PreSalesTable.setColumnClass(8, BigDecimal.class, false);	// 8-qty
				PreSalesTable.setColumnClass(9, BigDecimal.class, true); 	// 9-total				
			}
			
			if (col > 0) {
				
				if(col == 6 ){
					BigDecimal harga = (BigDecimal) PreSalesTable.getValueAt(rowIndex, col);
					if(harga.compareTo(Env.ZERO) < 0){
						FDialog.warn(windowNo, null, "", "Input Harga Tidak Boleh Kurang Dari 0(Nol)", "Info");
						return;
					}
				}else if(col == 8){
					
					BigDecimal qty = (BigDecimal) PreSalesTable.getValueAt(rowIndex, col);
					if(qty.compareTo(Env.ZERO) < 0){
						FDialog.warn(windowNo, null, "", "Input Qty Tidak Boleh Kurang Dari 0(Nol)", "Info");
						return;
					}
				}
				
				CalculateTableCanged(PreSalesTable, col);
				calculate();
			
			}
		}
		
	}

	@Override
	public void onEvent(Event e) throws Exception {
//		if(e.getTarget().equals(process)){
//		
//			process();
//			
//		}
//		else if(e.getTarget().equals(buatBaru)){
//			
//			buatBaru();
//			
//		}else if(e.getTarget().equals(paymentTunai)
//				||e.getTarget().equals(paymentBank)
//				||e.getTarget().equals(paymentLeasing)
//				||e.getTarget().equals(paymentHutang)){
//			
//			
//			String event = e.getName();
//			
//			if(event.equals(Events.ON_FOCUS)){
//				
//				if (AD_Language.toUpperCase().equals("IN_ID")) {
//					if(e.getTarget().equals(paymentTunai)){
//						String sTunai = paymentTunai.getValue().toString().replaceAll("\\.", "").replaceAll(",", ".");
//						Double tTunai = Double.valueOf(sTunai);
//						paymentTunai.setFormat("#########" );
//						paymentTunai.setValue(BigDecimal.valueOf(tTunai));
//					}else if(e.getTarget().equals(paymentBank)){
//						String sBank = paymentBank.getValue().toString().replaceAll("\\.", "").replaceAll(",", ".");
//						Double tBank = Double.valueOf(sBank);
//						paymentBank.setFormat("#########" );
//						paymentBank.setValue(BigDecimal.valueOf(tBank));
//					}else if(e.getTarget().equals(paymentLeasing)){
//						String sLeasing = paymentLeasing.getValue().toString().replaceAll("\\.", "").replaceAll(",", ".");
//						Double tLeasing = Double.valueOf(sLeasing);
//						paymentLeasing.setFormat("#########" );
//						paymentLeasing.setValue(BigDecimal.valueOf(tLeasing));
//					}
//					else if(e.getTarget().equals(paymentHutang)){
//						String sHutang = paymentHutang.getValue().toString().replaceAll("\\.", "").replaceAll(",", ".");
//						Double tHutang = Double.valueOf(sHutang);
//						paymentHutang.setFormat("#########" );
//						paymentHutang.setValue(BigDecimal.valueOf(tHutang));
//					}
//				}
//				
//			}else if (event.equals(Events.ON_BLUR)){
//			
//
//			BigDecimal payTunai = paymentTunai.getValue();
//			BigDecimal payBank = paymentBank.getValue();
//			BigDecimal payLeasing = paymentLeasing.getValue();
//			BigDecimal payHutang = paymentHutang.getValue();
//			
//			if(payTunai == null){
//				paymentTunai.setValue("0.00");
//				payTunai = Env.ZERO;
//			}
//			
//			if(payBank == null){
//				paymentBank.setValue("0.00");
//				payBank = Env.ZERO;
//			}
//			
//			if(payLeasing == null){
//				paymentLeasing.setValue("0.00");
//				payLeasing = Env.ZERO;
//			}
//			
//			if(payHutang == null){
//				paymentHutang.setValue("0.00");
//				payHutang = Env.ZERO;
//			}
//			
//			BigDecimal totalPay = payTunai.add(payBank);
//			BigDecimal totalPay2 = payTunai.add(payBank).add(payLeasing).add(payHutang);
//
//			
//			paymentLeasing.setValue(payLeasing);
//			paymentHutang.setValue(payHutang);	
//			paymentTunai.setValue(payTunai);
//			paymentBank.setValue(payBank);
//			totalBayarTextBox.setText(format.format(totalPay2));
//			totalBayarTunaiTextBox.setText(format.format(totalPay));
//			paymentTunai.setFormat("#,###,###,##0.00" );
//			paymentBank.setFormat("#,###,###,##0.00" );
//			paymentLeasing.setFormat("#,###,###,##0.00" );
//			paymentHutang.setFormat("#,###,###,##0.00" );
//
//			Double tBlanja = 0.00;
//			
//
//			if (totalBelanjaTextBox.getText().isEmpty()) {
//				tBlanja = 0.00;
//			} else if (!totalBelanjaTextBox.getText().isEmpty()) {
//				if (AD_Language.toUpperCase().equals("EN_US")) {
//					tBlanja = Double.valueOf(totalBelanjaTextBox.getText().replaceAll(",", ""));
//				} else if (AD_Language.toUpperCase().equals("IN_ID")) {
//					String dblanja = totalBelanjaTextBox.getText().replaceAll("\\.", "").replaceAll(",", ".");
//					tBlanja = Double.valueOf(dblanja);
//				}
//			}
//			
//			BigDecimal totalBelanja = BigDecimal.valueOf(tBlanja);
//			boolean isPass = false;
//			Decimalbox triger = null;
//			if(e.getTarget().equals(paymentTunai)){
//				isPass = paymentAlgoritm(payBank, payLeasing, payHutang, payTunai);
//				triger = paymentTunai;
//			}else if(e.getTarget().equals(paymentBank)){
//				isPass = paymentAlgoritm(payTunai, payLeasing, payHutang, payBank);
//				triger = paymentBank;
//			}else if(e.getTarget().equals(paymentLeasing)){
//				isPass = paymentAlgoritm(payTunai, payBank, payHutang, payLeasing);
//				triger = paymentLeasing;
//			}else if(e.getTarget().equals(paymentHutang)){
//				isPass =paymentAlgoritm(payTunai, payBank, payLeasing, payHutang);
//				triger = paymentHutang;
//			}
//			
//			if(totalBelanja.compareTo(Env.ZERO)<=0){
//				
//				if(!triger.isReadonly()){
//					FDialog.info(windowNo, null, "", "Belum Ada Item Untuk Di Bayar, Silakan Input Produk Terlebih Dahulu", "Info Pembayaran");
//					triger.setValue(Env.ZERO);
//					return;
//				}
//			}
//			
//			if(triger == paymentBank||triger == paymentLeasing || triger == paymentHutang){
//				
//				
//				
//				if(triger.getValue().compareTo(totalBelanja)>0){
//					
//					FDialog.info(windowNo, null, "", "Input Amount Tidak Boleh Melebihi Total Belanja", "Info Pembayaran");
//					triger.setValue(Env.ZERO);
//					return;
//					
//				}
//				
//				
//			}
//			
//			
//			if(triger == paymentTunai||triger == paymentBank){
//				
//				if(payLeasing.compareTo(Env.ZERO)>0 ||payHutang.compareTo(Env.ZERO)>0 ){
//					if(!isPass){
//						FDialog.info(windowNo, null, "", "Pembayaran Tidak Boleh Melebihi Total Belanja,Silahkan Cek Kembali", "Info Pembayaran");
//						BigDecimal trigerPay = triger.getValue();
//						if(trigerPay == null){
//							trigerPay = Env.ZERO;
//						}
//						
//						totalPay = totalPay.subtract(trigerPay);
//						totalPay2 = totalPay2.subtract(trigerPay);
//						triger.setValue(Env.ZERO);
//						
//						totalBayarTunaiTextBox.setText(format.format(totalPay));
//						totalBayarTextBox.setText(format.format(totalPay2));
//
//						return;
//					}
//				}else if(payLeasing.compareTo(Env.ZERO)==0 ||payHutang.compareTo(Env.ZERO)==0 ){
//					BigDecimal kembalian = totalPay.subtract(totalBelanja);
//					if(kembalian.compareTo(Env.ONE) < 0){
//						kembalian = Env.ZERO;
//					}
//					
//					kembalianTextBox.setText(format.format(kembalian));
//					kembaliTextBox.setText(format.format(kembalian));	
//					
//				}
//				
//			}else if(triger == paymentHutang||triger == paymentLeasing){
//				
//				if(!isPass){
//					BigDecimal trigerPay = triger.getValue();
//					if(trigerPay == null){
//						trigerPay = Env.ZERO;
//					}
//					totalPay2 = totalPay2.subtract(trigerPay);
//					totalBayarTextBox.setText(format.format(totalPay2));
//
//					
//					FDialog.info(windowNo, null, "", "Pembayaran Tidak Boleh Melebihi Total Belanja,Silahkan Cek Kembali", "Info Pembayaran");
//					triger.setValue(Env.ZERO);
//					return;
//				}
//				
//			}
//			
//			
//
//			
//			
//			
//			}
//	
//		}	
	if(e.getTarget().equals(pelangganBaru)){
			addBP();
		}else if(e.getTarget().equals(batal)){
			Integer no = (Integer) noPresales.getValue();
			
			X_C_Decoris_PreSales PS = new X_C_Decoris_PreSales(ctx, no, null);
			
			FDialog.ask(windowNo, null,"Konfirmasi", "", "Anda Yakin Membatalkan Dokumen Presales "+ PS.getDocumentNo()+"?", new Callback<Boolean>() {
				
				@Override
				public void onCallback(Boolean result) {

					if(result){
						batal();					
					}else{			
						return;
					}
				}
			});
			
		}else if(e.getTarget().equals(deleteItem)){
			
			int rowCount = 0;
			for (int i = 0; i < PreSalesTable.getRowCount(); i++) {
				boolean isSelect = (boolean) PreSalesTable.getValueAt(i, 0);

				if (isSelect) {
					rowCount++;
				}

			}
			
			if(rowCount == 0){
				FDialog.info(windowNo, null, "", "Tidak Detail Untuk Di Hapus, Silakan Cek List Detail Terlebih Dahulu", "Info");
				return;
			}
			
			deleteDetail();
//			FDialog.ask(windowNo, null,"Konfirmasi", "", "Anda Yakin Hapus Detail Yang Di Pilih?", new Callback<Boolean>() {
//				
//				@Override
//				public void onCallback(Boolean result) {
//
//					if(result){
//						deleteDetail();
//					}else{			
//						return;
//					}
//				}
//			});
			
		}else if(e.getTarget().equals(IMEIList)){

			int listSize = IMEIList.getItemCount();
			if (listSize <= 1)
				return;

			int selectedCount = 0;
			
			String serno = IMEIList.getSelectedItem().getId();

			String sql = "SELECT M_AttributeSetInstance_ID"
					+ " FROM M_AttributeSetInstance "
					+ " WHERE AD_Client_ID = ?" + " AND Serno = '" + serno
					+ "'";

			Integer M_AttributeSetInstance_ID = DB.getSQLValueEx(null, sql,AD_Client_ID);
			MAttributeSetInstance imei = new MAttributeSetInstance(ctx,M_AttributeSetInstance_ID, null);

			for (int i = 0; i < PreSalesTable.getRowCount(); i++) {
				if((boolean) PreSalesTable.getValueAt(i, 0)==true){
					selectedCount++;
				}
				String imeiProd = imei.getSerNo();
				String imeiTab = (String) PreSalesTable.getValueAt(i, 4);
				if (imeiProd.equalsIgnoreCase(imeiTab)) {

					FDialog.warn(formPresales.getWindowNo(), null, "","Product Dengan IMEI Tersebut Sudah Terinput","Peringatan");
					IMEIList.setValue(null);
					return;

				}
				;
			}

			
			if(selectedCount > 1){
				FDialog.warn(formPresales.getWindowNo(), null, "","Tidak bisa memilih lebih dari 1(satu) produk untuk mengganti IMEI","Peringatan");
				return;
			}
				
			if (PreSalesTable.getRowCount() > 0) {
				KeyNamePair product = (KeyNamePair) PreSalesTable.getValueAt(rowIndex, 2);

				String sqlProduct = "SELECT M_Product_ID FROM M_Product WHERE AD_Client_ID = ? AND value = '"+ product.toString() + "'";
				int M_Product_ID = DB.getSQLValue(ctx.toString(),sqlProduct.toString(), AD_Client_ID);

				String sqlImei = "SELECT M_Product_ID FROM M_StorageOnHand WHERE AD_Client_ID = ? AND M_AttributeSetInstance_ID = ?";
				int M_ProductIMEI_ID = DB.getSQLValueEx(ctx.toString(),sqlImei, new Object[] { AD_Client_ID,M_AttributeSetInstance_ID });

				if (M_Product_ID != M_ProductIMEI_ID) {

					FDialog.warn(formPresales.getWindowNo(),null,"","IMEI yang anda inputkan tidak sesuai dengan produk","Peringatan");
					IMEIList.setValue(null);
					return;

				}

				for (int i = 0; i < PreSalesTable.getRowCount(); i++) {
					if((boolean) PreSalesTable.getValueAt(i, 0)==true){
						PreSalesTable.setValueAt(imei.getSerNo(), i,4);	
					}
				}
				

			} else {

				String Locator = Lokasi.getValue().toString();
				int M_ProductIMEI_ID;
				int M_LocatorProd_ID;
				int M_LocatorPOS_ID = Integer.valueOf(Locator);

				MLocator loc = new MLocator(ctx, M_LocatorPOS_ID, null);

				String sqlImei = "SELECT M_Product_ID FROM M_StorageOnHand WHERE M_AttributeSetInstance_ID = ? AND QtyOnHand > 0";
				M_ProductIMEI_ID = DB.getSQLValueEx(ctx.toString(), sqlImei,new Object[] { M_AttributeSetInstance_ID });

				if (M_ProductIMEI_ID < 0) {
					FDialog.warn(formPresales.getWindowNo(), null, "","Produk dengan IMEI yang anda pilih tidak ada di lokasi "+ loc.getValue(), "Peringatan");
					IMEIList.setValue(null);
					return;
				}

				String sqlLocator = "SELECT M_Locator_ID FROM M_StorageOnHand WHERE M_AttributeSetInstance_ID = ? AND QtyOnHand > 0";
				M_LocatorProd_ID = DB.getSQLValueEx(ctx.toString(), sqlLocator,new Object[] { M_AttributeSetInstance_ID });

				if (M_LocatorProd_ID != M_LocatorPOS_ID) {

					FDialog.warn(formPresales.getWindowNo(), null, "","Produk dengan IMEI yang anda pilih tidak ada di lokasi "+ loc.getValue(), "Peringatan");
					IMEIList.setValue(null);
					return;

				}

				PreSalesTable.clear();

				Integer M_PriceList_ID = getIDFromComboBox(DaftarHargaSearch,MPriceList.Table_Name,MPriceList.COLUMNNAME_Name);
				if(M_PriceList_ID == null){
					M_PriceList_ID = 0 ;
				}
				
				Integer partner_id = (Integer) PelangganSearch.getValue();
				
				if(partner_id == null){
					
					partner_id = 0 ;
				}
				
				Vector<Vector<Object>> data = getProductData(M_ProductIMEI_ID,M_PriceList_ID, partner_id, M_LocatorPOS_ID,M_AttributeSetInstance_ID, PreSalesTable);
				Vector<String> columnNames = getOISColumnNames();

				PreSalesTable.clear();

				// Set Model
				ListModelTable modelP = new ListModelTable(data);
				modelP.addTableModelListener(this);
				PreSalesTable.setData(modelP, columnNames);
				configureMiniTable(PreSalesTable);


				IMEIList.setValue(null);
			}

		}else if(e.getTarget().equals(notaBesar)){
			updateData(C_DecorisPresalesPrint_ID);
			String trxName = Trx.createTrxName("notabesar");
			//String url = "D:\\SourceCode\\iDempiereBase\\reports\\";
			String url = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/reports/";
			MProcess proc = new MProcess(Env.getCtx(), 1000104, trxName);
			MPInstance instance = new MPInstance(proc, proc.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo("Print Nota Besar", 1000104);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			
			int nopresales = (int) noPresales.getValue();
			
			if(nopresales <0 ){
				return;
			}
			
			X_C_Decoris_PreSales pres = new X_C_Decoris_PreSales(ctx, nopresales, null);
			
			ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			list.add(new ProcessInfoParameter("C_Decoris_PreSales_ID", pres.getC_Decoris_PreSales_ID(),null, null, null));
			list.add(new ProcessInfoParameter("url_path", url, null, null, null));


			ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			list.toArray(pars);
			pi.setParameter(pars);
			
			Trx trx = Trx.get(trxName, true);
			trx.commit();

			ProcessUtil.startJavaProcess(Env.getCtx(), pi,Trx.get(trxName, true));
			
			
		}else if(e.getTarget().equals(notaKecil)){
			updateData(C_DecorisPresalesPrint_ID);
			String trxName = Trx.createTrxName("notakecil");
			//String url = "D:\\SourceCode\\iDempiereBase\\reports\\";
			String url = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/reports/";
			MProcess proc = new MProcess(Env.getCtx(), 1000105, trxName);
			MPInstance instance = new MPInstance(proc, proc.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo("Print Nota Kecil", 1000105);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			
			int nopresales = (int) noPresales.getValue();
			
			if(nopresales <0 ){
				return;
			}
			
			X_C_Decoris_PreSales pres = new X_C_Decoris_PreSales(ctx, nopresales, null);
			int c_order_print = pres.getC_Decoris_PreSales_ID();
			ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			list.add(new ProcessInfoParameter("C_Decoris_PreSales_ID", c_order_print,null, null, null));
			list.add(new ProcessInfoParameter("url_path", url, null, null, null));


			ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			list.toArray(pars);
			pi.setParameter(pars);
			
			Trx trx = Trx.get(trxName, true);
			trx.commit();

			ProcessUtil.startJavaProcess(Env.getCtx(), pi,Trx.get(trxName, true));
			
		}else if (e.getTarget().equals(GudangSearch)){
			
			Lokasi.setReadonly(true);
			Lokasi.setEnabled(true);
			
			Integer warehouse_ID = getIDFromComboBox(GudangSearch, MWarehouse.Table_Name,MWarehouse.COLUMNNAME_Name);
			if(warehouse_ID == null){
				warehouse_ID = 0;
			}
			
			Integer Org_ID = (Integer) orgSearch.getValue();
			if(Org_ID == null){
				Org_ID = 0 ;
			}
			
			ArrayList<KeyNamePair> listLoc = loadLocator(warehouse_ID,Org_ID);
			Lokasi.removeAllItems();
			for (KeyNamePair priceList : listLoc)
			Lokasi.appendItem(priceList.getName());
			
			if(listLoc.size() > 0){
				
				Lokasi.setSelectedIndex(0);

			}
			
		}else if (e.getTarget().equals(Lokasi)){
			
			if(DaftarHargaSearch.getValue() != null && PelangganSearch.getValue() != null){
				
				ProductSearch.setReadWrite(true);
				
			}else{
				ProductSearch.setReadWrite(false);

			}
			
		}
		
		
	}

	@Override
	public ADForm getForm() {
		
		return this.formPresales;
	}
	
	
	public WPosPreSales() {
		AD_User_ID = Env.getAD_User_ID(ctx);

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		CreatedByPOS_ID = DB.getSQLValueEx(ctx.toString(), sqlKasir.toString(),new Object[] { Env.getAD_Client_ID(ctx), AD_User_ID });

		if(CreatedByPOS_ID < 0){	
			FDialog.warn(windowNo, null, "", "User Tidak Memiliki Akses ke Halaman Presales, Silahkan Hubungi Administrator","Info");
			formPresales.dispose();
			return;
		}
		
		
		dyInit();
		init();
		
	}
	
	private void dyInit(){
		AD_User_ID = Env.getAD_User_ID(ctx);

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		CreatedByPOS_ID = DB.getSQLValueEx(ctx.toString(), sqlKasir.toString(),new Object[] { Env.getAD_Client_ID(ctx), AD_User_ID });

		if(CreatedByPOS_ID < 0){	
			FDialog.warn(windowNo, null, "", "User Tidak Memiliki Akses ke Halaman Presales, Silahkan Hubungi Administrator","Info");
			return;
		}
	
		
		MLookup orgLookup = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0,2163, DisplayType.TableDir);
		orgSearch = new WTableDirEditor("AD_Org_ID", true, false, true, orgLookup);
		orgSearch.addValueChangeListener(this);
		orgSearch.setMandatory(true);
		//org.setValue(org_id);
		orgSearch.setReadWrite(true);
		
		MLookup lookupClient = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0,14621, DisplayType.TableDir);
		TokoSearch = new WTableDirEditor("AD_Table_ID", true,false, true, lookupClient);
		TokoSearch.addValueChangeListener(this);
		TokoSearch.setMandatory(true);
		TokoSearch.setValue(AD_Client_ID);
		
		MLookup salesRepLookup = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0,2186, DisplayType.TableDir);
		SalesSearch = new WTableDirEditor("SalesRep_ID", true, false, true,salesRepLookup);
		SalesSearch.addValueChangeListener(this);
		SalesSearch.setMandatory(true);
		
		
//		MLookup lookupWarehouse = MLookupFactory.get(ctx, formPresales.getWindowNo(),0, 1151, DisplayType.TableDir);
//		GudangSearch = new WTableDirEditor("M_Warehouse_ID", true, false,true, lookupWarehouse);
//		GudangSearch.addValueChangeListener(this);
//		GudangSearch.setMandatory(true);
		
		Integer Org_ID = (Integer) orgSearch.getValue();
		if(Org_ID == null){
			Org_ID = 0 ;
		}
		
		ArrayList<KeyNamePair> listWH = loadWarehouse(Org_ID);
		Integer warehouse_ID = 0;
		GudangSearch.removeAllItems();
		for (KeyNamePair priceList : listWH){
			GudangSearch.appendItem(priceList.getName());
		}
		if(listWH.size() > 0){
		warehouse_ID = getIDFromComboBox(GudangSearch, MWarehouse.Table_Name,MWarehouse.COLUMNNAME_Name);
		}
		if(warehouse_ID == null){
			warehouse_ID = 0 ;
		}
		
		ArrayList<KeyNamePair> listLoc = loadLocator(warehouse_ID,Org_ID);
		Lokasi.removeAllItems();
		for (KeyNamePair priceList : listLoc){
			Lokasi.appendItem(priceList.getName());
		}
		
		MLookup lookupProduct = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0,1402, DisplayType.Search);
		ProductSearch = new WSearchEditor("M_Product_ID", true, false, true,lookupProduct);
		ProductSearch.addValueChangeListener(this);
		ProductSearch.setReadWrite(true);
		ProductSearch.setMandatory(true);
		 
		MLookup lookupBP = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0, 2893,DisplayType.Search);
		PelangganSearch = new WSearchEditor("C_BPartner_ID", true, false, true,lookupBP);
		PelangganSearch.addValueChangeListener(this);
		PelangganSearch.setMandatory(true);
		
		ArrayList<KeyNamePair> listPL = loadPriceList();
		DaftarHargaSearch.removeAllItems();
		for (KeyNamePair priceList : listPL){
			DaftarHargaSearch.appendItem(priceList.getName());
		}
		DaftarHargaSearch.setSelectedIndex(0);

//		MLookup lookupBank = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0,3880, DisplayType.TableDir);
//		bankAccountSearch = new WTableDirEditor("C_BankAccount_ID", true,false, true, lookupBank);
//		bankAccountSearch.addValueChangeListener(this);
//		bankAccountSearch.setMandatory(true);
//		
//		MLookup leasingLookup = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0,331558, DisplayType.List);
//		leasingProviderSearch = new WTableDirEditor("leaseprovider", true,false, true, leasingLookup);
//		leasingProviderSearch.addValueChangeListener(this);
	
		MLookup lookupPreSales = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0, 1002249,DisplayType.Search);
		noPresales = new WSearchEditor("C_Decoris_PreSales_ID", true, false, true,lookupPreSales);
		noPresales.addValueChangeListener(this);
		noPresales.setMandatory(true);
			
		MLookup lookupStatus = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0, 1002637,DisplayType.List);
		Status = new WTableDirEditor("Status", true, false, true,lookupStatus);
		Status.addValueChangeListener(this);
		Status.setMandatory(true);
		
		
	
	}
	
	
	private void init(){	
		

		formPresales.appendChild(mainLayout);
		mainLayout.setWidth("99%");
		mainLayout.setHeight("100%");
		
		formPresales.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>(){

			@Override
			public void onEvent(Event arg0) throws Exception {

				formPresales.dispose();
			}
			
		});
		
		North north = new North();
		mainLayout.appendChild(north);

		String grid = "border: 1px solid #C0C0C0; border-radius:5px;";
		north.appendChild(parameterPanel);
		north.setStyle(grid);
		
		Hbox northMainBox = new Hbox();
		parameterPanel.appendChild(northMainBox);
		northMainBox.appendChild(parameterGrid);
		parameterGrid.setWidth("100%");
		parameterGrid.setStyle("Height:100%;");
		
		Rows rows = null;
		Row row = null;

		rows = parameterGrid.newRows();

		// No Nota
		row = rows.newRow();
		row.appendCellChild(NoPresalesLabel.rightAlign(), 1);
		row.appendCellChild(noPresales.getComponent(), 2);
		noPresales.getComponent().setHflex("true");
		
		row = rows.newRow();
		row.appendCellChild(StatusLabel.rightAlign(),1);
		Hbox StatHbox = new Hbox();
		row.appendCellChild(StatHbox,2);
		StatHbox.setHflex("true");

		StatHbox.setAlign("center");
		StatHbox.appendChild(Status.getComponent());
		Status.getComponent().setWidth("100px");
		Status.setReadWrite(false);
		Status.setValue("IP");
		StatHbox.appendChild(TutupKasCheck);
		TutupKasCheck.setLabel("Tutup Kas");
		TutupKasCheck.setEnabled(false);
		TutupKasCheck.setVisible(false);
		
		row = rows.newRow();
		row.appendCellChild(PelangganLabel.rightAlign(),1);
		Hbox pelangganHbox = new Hbox();
		row.appendCellChild(pelangganHbox, 2);
		pelangganHbox.appendChild(PelangganSearch.getComponent());
		PelangganSearch.getComponent().setHflex("true");
		PelangganSearch.setReadWrite(false);
		pelangganHbox.appendChild(pelangganBaru);
		pelangganBaru.addActionListener(this);
		pelangganBaru.setLabel("+");
		pelangganBaru.setEnabled(false);
		
		
		row = rows.newRow();
		row.appendCellChild(GudangLabel.rightAlign(),1);
		row.appendCellChild(GudangSearch, 2);
		GudangSearch.setHflex("true");
		GudangSearch.setReadonly(true);
		GudangSearch.setEnabled(false);
		GudangSearch.addEventListener(0, "onChange", this);
		
		row = rows.newRow();
		row.appendCellChild(new Space(), 1);
		row.appendCellChild(deleteItem, 2);
		deleteItem.setHflex("true");
		deleteItem.setLabel("Hapus Detail");
		deleteItem.addActionListener(this);
		deleteItem.setEnabled(false);
		
	
		northMainBox.appendChild(parameterGrid2);
		parameterGrid2.setWidth("100%");
		parameterGrid2.setStyle("Height:100%;");

		Rows rows2 = null;
		Row row2 = null;
		rows2 = parameterGrid2.newRows();
		
		row2 = rows2.newRow();
		row2.appendCellChild(TokoLabel.rightAlign(), 1);
		row2.appendCellChild(TokoSearch.getComponent(), 2);
		TokoSearch.getComponent().setHflex("true");
		TokoSearch.setReadWrite(false);

		row2 = rows2.newRow();
		row2.appendCellChild(NoPembayaranLabel.rightAlign(), 1);
		row2.appendCellChild(noPembayaran, 2);
		noPembayaran.setHflex("true");
		noPembayaran.setReadonly(true);
		
		row2 = rows2.newRow();
		row2.appendCellChild(SalesLabel.rightAlign(), 1);
		row2.appendCellChild(SalesSearch.getComponent(), 2);
		SalesSearch.getComponent().setHflex("true");
		SalesSearch.setReadWrite(false);
		
		row2 = rows2.newRow();
		row2.appendCellChild(LokasiLabel.rightAlign(), 1);
		row2.appendCellChild(Lokasi, 2);
		Lokasi.setHflex("true");
		Lokasi.setReadonly(true);
		Lokasi.setEnabled(false);
		Lokasi.addEventListener(0, "onChange", this);


		
		
		row2 = rows2.newRow();
		row2.appendCellChild(PickUpLabel.rightAlign(), 1);
		Hbox pickupHbox = new Hbox();
		row2.appendCellChild(pickupHbox, 2);
		pickupHbox.appendChild(PickUp);
		PickUp.setEnabled(false);
	
		northMainBox.appendChild(parameterGrid3);
		parameterGrid3.setWidth("100%");
		parameterGrid3.setStyle("Height:100%;");
		Rows rows3 = null;
		Row row3 = null;
		rows3 = parameterGrid3.newRows();

		row3 = rows3.newRow();
		row3.appendCellChild(orgLabel.rightAlign(), 1);
		row3.appendCellChild(orgSearch.getComponent(), 2);
		orgSearch.getComponent().setHflex("true");
		orgSearch.setReadWrite(false);
		
		
		row3 = rows3.newRow();
		row3.appendCellChild(tanggalLabel.rightAlign(), 1);
		row3.appendCellChild(tanggalField, 2);
		tanggalField.setHflex("true");
		tanggalField.setFormat("dd/MM/yyyy");
		tanggalField.setEnabled(false);
		

		row3 = rows3.newRow();
		row3.appendCellChild(DaftarHargaLabel.rightAlign(),1);
		row3.appendCellChild(DaftarHargaSearch, 2);
		DaftarHargaSearch.setHflex("true");
		DaftarHargaSearch.setEnabled(false);
		DaftarHargaSearch.setReadonly(true);
		
		
		row3 = rows3.newRow();
		row3.appendCellChild(ProductLabel.rightAlign(), 1);
		row3.appendCellChild(ProductSearch.getComponent(),2);
		ProductSearch.getComponent().setHflex("true");
		ProductSearch.setReadWrite(false);
		
		row3 = rows3.newRow();
		row3.appendCellChild(IMEILabel.rightAlign(), 1);
		row3.appendCellChild(IMEIList, 2);
		IMEIList.setHflex("true");
		IMEIList.setReadonly(true);
		IMEIList.addEventListener(0, "onChange", this);
		
		Rows rows4 = null;
		Row row4 = null;
		northMainBox.appendChild(parameterGrid4);
		parameterGrid4.setWidth("100%");
		parameterGrid4.setStyle("Height:100%;");
		
		rows4 = parameterGrid4.newRows();
		row4 = rows4.newRow();
		row4.appendCellChild(TotalLabel.rightAlign(), 1);
		row4.appendCellChild(TotalTextBox1,2);
		TotalTextBox1.setHflex("true");
		TotalTextBox1.setStyle("font-size:20px;font-weight:bold;text-align:right;border: 1px solid #000000;");
		TotalTextBox1.setHeight("50px");
		TotalTextBox1.setText("0");
		TotalTextBox1.setReadonly(true);
	
		row4 = rows4.newRow();
		row4.appendCellChild(totalBayarLabel.rightAlign(), 1);
		row4.appendCellChild(totalBayarTextBox, 2);
		totalBayarTextBox.setHflex("true");
		totalBayarTextBox.setStyle("font-size:20px;font-weight:bold;text-align:right;border: 1px solid #000000;");
		totalBayarTextBox.setHeight("50px");
		totalBayarTextBox.setText("0");
		totalBayarTextBox.setReadonly(true);

		row4 = rows4.newRow();
		row4.appendCellChild(kembaliLabel.rightAlign(), 1);
		row4.appendCellChild(kembaliTextBox, 2);
		kembaliTextBox.setHflex("true");
		kembaliTextBox.setStyle("font-size:20px;font-weight:bold;text-align:right;border: 1px solid #000000;");
		kembaliTextBox.setHeight("50px");
		kembaliTextBox.setText("0");
		kembaliTextBox.setReadonly(true);

		// SouthPanel
		South south = new South();
		mainLayout.appendChild(south);
		south.setStyle(grid);
		south.appendChild(southPanel);
		Hbox southMainBox = new Hbox();
		southPanel.appendChild(southMainBox);
		
//		Hbox subMainBox1 = new Hbox();
//		southMainBox.appendChild(subMainBox1);
//		subMainBox1.appendChild(southGrid1);
//		subMainBox1.setStyle("border: 1px solid #C0C0C0 ;padding:1px;border-radius:3px;margin-top:1px;margin-left:1px");
//		
//		southGrid1.setWidth("100%");
//		//southGrid1.setHflex("min");		
//		
//		Rows southRows1 = null;
//		Row southRow1 = null;
		
//		southRows1 = southGrid1.newRows();

//		southRow1 = southRows1.newRow();	
//		Hbox pembayaranTitle = new Hbox();
//		southRow1.appendChild(pembayaranTitle);
//		pembayaranTitle.appendChild(tipePembayaranLabel);
//		tipePembayaranLabel.setStyle("font-weight:bold;");
//		pembayaranTitle.appendChild(new Space());
//		pembayaranTitle.appendChild(new Space());
//		pembayaranTitle.appendChild(new Space());
//		pembayaranTitle.appendChild(new Space());
//		pembayaranTitle.appendChild(new Space());
//		pembayaranTitle.appendChild(amountlabel);
//		amountlabel.setStyle("font-weight:bold;");

//		southRow1 = southRows1.newRow();
//		Hbox pembayaran1 = new Hbox();
//		southRow1.appendChild(pembayaran1);
//		pembayaran1.setWidth("100%");
//		pembayaran1.appendChild(payruleBoxTunai);
//		payruleBoxTunai.setStyle("align:right;");
//		payruleBoxTunai.setHeight("24px");
//		payruleBoxTunai.setStyle("border-radius:3px;border: 1px solid #C0C0C0 ;");
//		payruleBoxTunai.setReadonly(true);
//		payruleBoxTunai.setText("Tunai");
//		payruleBoxTunai.setWidth("75px");
		
//		pembayaran1.appendChild(paymentTunai);
//		paymentTunai.setStyle("text-align:right;");
//		paymentTunai.setValue("0.00");
//		paymentTunai.addEventListener(0, Events.ON_BLUR, this);
//		paymentTunai.addEventListener(0, Events.ON_FOCUS, this);
//		paymentTunai.setHflex("true");
//		paymentTunai.setFormat("#,###,###,##0.00" );
//		paymentTunai.setWidth("100%");
//		paymentTunai.setMaxlength(32);
//		paymentTunai.setReadonly(true);
	
//		southRow1 = southRows1.newRow();
//		Hbox pembayaran2 = new Hbox();
//		southRow1.appendChild(pembayaran2);
//		pembayaran2.setWidth("100%");
//		pembayaran2.appendChild(payruleBoxBank);
//		payruleBoxBank.setReadonly(true);
//		payruleBoxBank.setHeight("24px");
//		payruleBoxBank.setStyle("border-radius:3px;border: 1px solid #C0C0C0 ;");
//		payruleBoxBank.setText("Bank");
//		payruleBoxBank.setWidth("75px");

//		pembayaran2.appendChild(paymentBank);
//		paymentBank.setStyle("text-align:right;");
//		paymentBank.setValue("0.00");
//		paymentBank.addEventListener(0, Events.ON_BLUR, this);
//		paymentBank.addEventListener(0, Events.ON_FOCUS, this);
//		paymentBank.setHflex("true");
//		paymentBank.setFormat("#,###,###,##0.00" );
//		paymentBank.setWidth("100%");
//		paymentBank.setMaxlength(32);
//		paymentBank.setReadonly(true);
			
//		southRow1 = southRows1.newRow();
//		Hbox pembayaran3 = new Hbox();
//		pembayaran3.setWidth("100%");
//		southRow1.appendChild(pembayaran3);
//		pembayaran3.appendChild(payruleBoxLeasing);
//		payruleBoxLeasing.setReadonly(true);
//		payruleBoxLeasing.setHeight("24px");
//		payruleBoxLeasing.setStyle("border-radius:3px;border: 1px solid #C0C0C0 ;");
//		payruleBoxLeasing.setText("Leasing");
//		payruleBoxLeasing.setWidth("75px");


//		pembayaran3.appendChild(paymentLeasing);
//		paymentLeasing.setStyle("text-align:right;");
//		paymentLeasing.setValue("0.00");
//		paymentLeasing.addEventListener(0, Events.ON_BLUR, this);
//		paymentLeasing.addEventListener(0, Events.ON_FOCUS, this);
//		paymentLeasing.setHflex("true");
//		paymentLeasing.setFormat("#,###,###,##0.00" );
//		paymentLeasing.setWidth("100%");
//		paymentLeasing.setMaxlength(32);
//		paymentLeasing.setReadonly(true);

		
//		southRow1 = southRows1.newRow();
//		Hbox pembayaran4 = new Hbox();
//		pembayaran4.setWidth("100%");
//		southRow1.appendChild(pembayaran4);
//		pembayaran4.appendChild(payruleBoxHutang);
//		payruleBoxHutang.setHeight("24px");
//		payruleBoxHutang.setStyle("border-radius:3px;border: 1px solid #C0C0C0 ;");
//		payruleBoxHutang.setReadonly(true);
//		payruleBoxHutang.setText("Hutang");
//		payruleBoxHutang.setWidth("75px");


//		pembayaran4.appendChild(paymentHutang);
//		paymentHutang.setStyle("text-align:right;");
//		paymentHutang.setValue("0.00");
//		paymentHutang.addEventListener(0, Events.ON_BLUR, this);
//		paymentHutang.addEventListener(0, Events.ON_FOCUS, this);
//		paymentHutang.setHflex("true");
//		paymentHutang.setFormat("#,###,###,##0.00" );
//		paymentHutang.setWidth("100%");
//		paymentHutang.setMaxlength(32);
//		paymentHutang.setReadonly(true);

		
		southMainBox.appendChild(southGrid2);
		southGrid2.setWidth("100%");
		southGrid2.setStyle("Height:100%;");
		//southGrid2.setHflex("min");
		
		Rows southRows2 = null;
		Row southRow2 = null;
		
		southRows2 = southGrid2.newRows();
		southRow2 = southRows2.newRow();
		
//		southRow2.appendCellChild(leasingProviderLabel.rightAlign(), 1);
//		southRow2.appendCellChild(leasingProviderSearch.getComponent(),2);
//		leasingProviderLabel.setVisible(true);
//		leasingProviderSearch.setVisible(true);
//		leasingProviderLabel.setHflex("true");
//		leasingProviderSearch.getComponent().setHflex("true");
//		leasingProviderSearch.setReadWrite(false);
		
//		southRow2 = southRows2.newRow();
//		southRow2.appendCellChild(bankAccountLabel.rightAlign(),1);
//		southRow2.appendCellChild(bankAccountSearch.getComponent(),2);
//		bankAccountLabel.setVisible(true);
//		bankAccountSearch.setVisible(true);
//		bankAccountLabel.setHflex("true");
//		bankAccountSearch.getComponent().setHflex("true");
//		bankAccountSearch.setReadWrite(false);
		
		southRow2 = southRows2.newRow();
		southRow2.appendCellChild(keteranganLabel.rightAlign(),1);
		southRow2.appendCellChild(keteranganTextBox,2);
		keteranganTextBox.setHflex("true");
		keteranganTextBox.setRows(3);
		keteranganTextBox.setMaxlength(255);
		keteranganTextBox.setReadonly(true);
		
		southMainBox.appendChild(southGrid3);
		southGrid3.setWidth("100%");
		southGrid3.setStyle("Height:100%;");
		
		Rows southRows3 = null;
		Row southRow3 = null;
		
		southRows3 = southGrid3.newRows();
		
		southRow3 = southRows3.newRow();
		southRow3.appendCellChild(totalBrutoLabel.rightAlign(),1);
		southRow3.appendCellChild(totalBrutoTextBox, 2);
		totalBrutoTextBox.setReadonly(true);
		totalBrutoTextBox.setStyle("text-align:right;");
		totalBrutoTextBox.setText("0");
		totalBayarTunaiLabel.setHflex("true");
		totalBrutoTextBox.setHflex("true");

	
		southRow3 = southRows3.newRow();
		southRow3.appendCellChild(totalBelanjaLabel.rightAlign(),1);
		southRow3.appendCellChild(totalBelanjaTextBox, 2);
		totalBelanjaLabel.setStyle("font-weight:bold;");
		totalBelanjaTextBox.setStyle("font-weight:bold;text-align:right;");
		totalBelanjaTextBox.setStyle("text-align:right;");
		totalBelanjaTextBox.setReadonly(true);
		totalBelanjaTextBox.setText("0");
		totalBelanjaLabel.setHflex("true");
		totalBelanjaTextBox.setHflex("true");


		southRow3 = southRows3.newRow();
		southRow3.appendCellChild(totalBayarTunaiLabel.rightAlign(),1);
		southRow3.appendCellChild(totalBayarTunaiTextBox, 2);
		totalBayarTunaiLabel.setStyle("font-weight:bold;");
		totalBayarTunaiTextBox.setStyle("font-weight:bold;text-align:right;");
		totalBayarTunaiTextBox.setReadonly(true);
		totalBayarTunaiTextBox.setText("0");
		totalBayarTunaiLabel.setHflex("true");
		totalBayarTunaiTextBox.setHflex("true");

		southRow3 = southRows3.newRow();
		southRow3.appendCellChild(DPPLabel.rightAlign(),1);
		southRow3.appendCellChild(DPPTextBox, 2);
		DPPTextBox.setReadonly(true);
		DPPTextBox.setText("0");
		DPPTextBox.setStyle("text-align:right;");
		DPPLabel.setHflex("true");
		DPPTextBox.setHflex("true");


		southMainBox.appendChild(southGrid4);
		southGrid3.setWidth("100%");
		southGrid3.setStyle("Height:100%;");
		
		Rows southRows4 = null;
		Row southRow4 = null;
		
		southRows4 = southGrid4.newRows();
		
		southRow4 = southRows4.newRow();
		southRow4.appendCellChild(diskonLabel.rightAlign(),1);
		southRow4.appendCellChild(diskonTextBox, 2);
		diskonTextBox.setReadonly(true);
		diskonTextBox.setStyle("text-align:right;");
		diskonTextBox.setText("0");
		diskonLabel.setHflex("true");
		diskonTextBox.setHflex("true");

		southRow4 = southRows4.newRow();
		southRow4.appendCellChild(kembalianLabel.rightAlign(),1);
		southRow4.appendCellChild(kembalianTextBox, 2);
		kembalianLabel.setStyle("font-weight:bold;");
		kembalianTextBox.setStyle("font-weight:bold;text-align:right;");
		kembalianTextBox.setReadonly(true);
		kembalianTextBox.setText("0");
		kembalianLabel.setHflex("true");
		kembalianTextBox.setHflex("true");
		
		southRow4 = southRows4.newRow();
		southRow4.appendCellChild(space.rightAlign(), 1);
		southRow4.appendCellChild(spaceBox,2);
		spaceBox.setStyle("border: 0px solid #FFFFFF ;opacity: 0;");

		southRow4 = southRows4.newRow();
		southRow4.appendCellChild(PPNLabel.rightAlign(),1);
		southRow4.appendCellChild(PPNTextBox, 2);
		PPNTextBox.setReadonly(true);
		PPNTextBox.setText("0");
		PPNTextBox.setStyle("text-align:right;");
		PPNLabel.setHflex("true");
		PPNTextBox.setHflex("true");
		
		

		Hbox south2 = new Hbox();
		southPanel.appendChild(south2);
		south2.appendChild(southButtonGrid);
		
		Rows southBtns = null;
		Row southBtn = null;
		
		southBtns = southButtonGrid.newRows();
		southBtn = southBtns.newRow();
		
		southButtonGrid.setWidth("100%");
		southButtonGrid.setStyle("Height:100%;");
		
//		southBtn.appendChild(buatBaru);
//		buatBaru.setLabel("Buat baru");
//		buatBaru.addActionListener(this);
//		buatBaru.setHflex("true");
		
//		Keylistener keylistener = new Keylistener();
//		keylistener.setParent(formPresales);
//		keylistener.setCtrlKeys("^m");
//		keylistener.addEventListener(Events.ON_CTRL_KEY, new EventListener<Event>() {
//
//			@Override
//			public void onEvent(Event arg0) throws Exception {
//				
//			//	String asd = arg0.getName();
//				
//				//buatBaru();
//				//noPresales.getComponent().focus();
//				buatBaru.focus();
//			}	
//		});
		
		
		//setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK));
		
//		southBtn.appendCellChild(process,1);
//		process.setLabel("Proses");
//		process.addActionListener(this);
//		process.setHflex("true");
//		process.setEnabled(false);

		southBtn.appendCellChild(batal,1);
		batal.setLabel("Batal");
		batal.addActionListener(this);
		batal.setHflex("true");
		batal.setEnabled(false);
		// TODO Auto-generated method stub

		southBtn.appendCellChild(notaKecil,1);
		notaKecil.setLabel("Nota Kecil");
		notaKecil.addActionListener(this);
		notaKecil.setHflex("true");
		notaKecil.setEnabled(false);

		southBtn.appendCellChild(notaBesar,1);
		notaBesar.setLabel("Nota Besar");
		notaBesar.addActionListener(this);
		notaBesar.setHflex("true");
		notaBesar.setEnabled(false);

		south = new South();
		preSalesPanel.appendChild(presalesLayout);
		presalesLayout.appendChild(south);
		preSalesPanel.setWidth("100%");
		preSalesPanel.setHeight("100%");
		presalesLayout.setWidth("100%");
		presalesLayout.setHeight("100%");
		
		Center center = new Center();
		presalesLayout.appendChild(center);
		center.appendChild(PreSalesTable);
		presalesLayout.setWidth("100%");
		presalesLayout.setHeight("100%");
		center.setStyle(grid);

		center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(infoLayout);
		//infoLayout.setHflex("1");
		//infoLayout.setVflex("1");
		infoLayout.setWidth("100%");
		infoLayout.setHeight("100%");

		north = new North();
		north.setHeight("100%");
		infoLayout.appendChild(north);
		north.appendChild(preSalesPanel);
		north.setSplittable(false);
		center = new Center();
		infoLayout.appendChild(center);
		
	}
	
	
	protected void configureMiniTable(IMiniTable miniTable) {

		miniTable.setColumnClass(0, Boolean.class, false);
		miniTable.setColumnClass(1, Integer.class, true);
		miniTable.setColumnClass(2, String.class, true); 		// 2-Product
		miniTable.setColumnClass(3, String.class, true); 		// 3-Lokasi
		miniTable.setColumnClass(4, String.class, true);		// 4-imei
		miniTable.setColumnClass(5, BigDecimal.class, true); 	// 5-priceList
		miniTable.setColumnClass(6, BigDecimal.class, false); 	// 5-price
		miniTable.setColumnClass(7, BigDecimal.class, true); 	// 6-diskon
		miniTable.setColumnClass(8, BigDecimal.class, false);	// 7-qty
		miniTable.setColumnClass(9, BigDecimal.class, true); 	// 8-total
		
		miniTable.autoSize();

	}
	
	protected void configureMiniTableExistingPresales(IMiniTable miniTable) {

		miniTable.setColumnClass(0, Boolean.class, true);
		miniTable.setColumnClass(1, Integer.class, true);
		miniTable.setColumnClass(2, String.class, true); 		// 2-Product
		miniTable.setColumnClass(3, String.class, true); 		// 3-Lokasi
		miniTable.setColumnClass(4, String.class, true);		// 4-imei
		miniTable.setColumnClass(5, BigDecimal.class, true); 	// 5-priceList
		miniTable.setColumnClass(6, BigDecimal.class, true); 	// 5-price
		miniTable.setColumnClass(7, BigDecimal.class, true); 	// 6-diskon
		miniTable.setColumnClass(8, BigDecimal.class, true);	// 7-qty
		miniTable.setColumnClass(9, BigDecimal.class, true); 	// 8-total
		
		miniTable.autoSize();

	}

	protected Vector<String> getOISColumnNames() {

		// Header Info
		Vector<String> columnNames = new Vector<String>(9);
		columnNames.add(Msg.getMsg(ctx, "Select"));
		columnNames.add("No");
		columnNames.add("Nama Barang");
		columnNames.add("Lokasi");
		columnNames.add("IMEI");
		columnNames.add("Daftar Harga");
		columnNames.add("Harga");
		columnNames.add("Diskon(%)");
		columnNames.add("Qty");
		columnNames.add("Total");

		return columnNames;

	}
	
	public String validation (){
		String msg = "";
//		BigDecimal ptunai=paymentTunai.getValue();
//		BigDecimal pbank=paymentBank.getValue();
//		BigDecimal pleasing=paymentLeasing.getValue();
//		BigDecimal phutang=paymentHutang.getValue();
//		BigDecimal jmlByar = ptunai.add(pbank).add(pleasing).add(phutang);
//		BigDecimal totalBlanja = Env.ZERO;
		
		
//		Double dTotalBlj = 0.00;

//		if (totalBelanjaTextBox.getText().isEmpty()) {
//			dTotalBlj = 0.00;
//		} else if (!totalBelanjaTextBox.getText().isEmpty()) {
//			if (AD_Language.toUpperCase().equals("EN_US")) {
//				dTotalBlj = Double.valueOf(totalBelanjaTextBox.getText().replaceAll(",", ""));
//			} else if (AD_Language.toUpperCase().equals("IN_ID")) {
//				String dTtlBlj = totalBelanjaTextBox.getText().replaceAll("\\.", "").replaceAll(",", ".");
//				dTotalBlj = Double.valueOf(dTtlBlj);
//			}
//		}
		
//		totalBlanja = BigDecimal.valueOf(dTotalBlj);
		
		if(PreSalesTable.getRowCount() < 1){
			msg = msg + "\n" +"Belum Ada Produk yang di Inputkan";
		}
		
//		if(jmlByar.compareTo(totalBlanja) < 0){
//			
//			msg = msg + "\n" +"Total Pembayaran Kurang dari Total Belanja";
//		}
		
		//toko null validation
		if(TokoSearch.getValue()==null){
			msg = msg + "\n" +"Kolom Toko Tidak Boleh Kosong";

		}
		
		//toko null validation
		if(orgSearch.getValue()==null){
			msg = msg + "\n" +"Kolom Org Tidak Boleh Kosong";

		}
		
		//Sales null Validation
		if(SalesSearch.getValue() == null){
			msg = msg + "\n" +"Kolom Sales Tidak Boleh Kosong";
		}
		
		//PriceList null Validation
		if(DaftarHargaSearch.getValue() == null){
			msg = msg + "\n" +"Kolom Daftar Harga Tidak Boleh Kosong";
		}
		
		//DatePreSales null Validation
		if(tanggalField.getValue() == null){
			msg = msg + "\n" +"Kolom Tanggal Tidak Boleh Kosong";
		}
		
		//Pelanggan null Validation
		if(PelangganSearch.getValue() == null){
			msg = msg + "\n" +"Kolom Pelanggan Tidak Boleh Kosong";
		}
		
		//Gudang null Validation
		if(GudangSearch.getValue() == null){
			msg = msg + "\n" +"Kolom Gudang Tidak Boleh Kosong";
		}
		
//		if(jmlByar.compareTo(Env.ZERO)<=0){
//			msg = msg + "\n" +"Pembayaran masih kosong";
//		}
		//Location null Validation -(from line)
		//Product null Validation -(from table)
		//IMEI null Validation if product have imei set -(from product)
		
		//LeaseProvider null Validation if leasing transaction
		//get Pembayaran 3
//		BigDecimal leasing = paymentLeasing.getValue();
//		if(leasing.compareTo(Env.ZERO)> 0){
//			
////			if(leasingProviderSearch.getValue() == null){
////				msg = msg + "\n" +"Kolom Leasing Tidak Boleh Kosong Jika Memilih Metode Pembayaran Leasing";
////			}
//			
//		}
		
		//Bank Account null Validation
//		if(bankAccountSearch.getValue() == null){
//			if(paymentBank.getValue().compareTo(Env.ZERO) > 0){
//				msg = msg + "\n" +"Kolom Bank Akun Tidak Boleh Kosong Jika Memilih Metode Pembayaran Bank";
//			}
//		}
//		
		//Description Lenght Validation
		String ket = keteranganTextBox.getValue();{
			if(ket.length() > 255){
				msg = msg + "\n" +"Keterangan tidak boleh melebihi 255 karakter";
			}
			
		}
		
		for(int i = 0 ; i < PreSalesTable.getRowCount() ; i++ ){
			KeyNamePair prod = (KeyNamePair) PreSalesTable.getValueAt(i, 2);
			int prod_id = prod.getKey();
			String imeiprod = (String) PreSalesTable.getValueAt(i, 4);
			if(imeiprod == null){
				imeiprod = "";
			}
			
			if(prod_id > 0){
				
				MProduct mprod = new MProduct(ctx, prod_id, null);
				
				if(imeiprod==""){
					int M_AttributeSet_ID = mprod.getM_AttributeSet_ID();
					if(M_AttributeSet_ID > 0){
						msg = msg + "\n" +"Product "+mprod.getName()+" memiliki IMEI,Silahkan Input IMEI terlebih dahulu";		
					}
					
				}
				
				
			}
			
			
		}
		
		return msg;
	}
	
	private void addBP() {
		Grid inputGrid = GridFactory.newGridLayout();
		Panel paraPanel = new Panel();
		Rows rows = null;
		Row row = null;
				
		final WDateEditor TTL = new WDateEditor();
		final Textbox BPName = new Textbox();
		final Textbox BPLastName = new Textbox();
		final Textbox BPMotherName = new Textbox();
		final Textbox Address1 = new Textbox();
		final Textbox Address2 = new Textbox();
		final Textbox EMail = new Textbox();
		final Textbox ZIP = new Textbox();
		final Textbox Telepon1 = new Textbox();
		final Textbox Telepon2 = new Textbox();
		final Textbox BPIDCardNumber = new Textbox();
		final Textbox SearchKey = new Textbox();
		final Decimalbox CreditLimit = new Decimalbox();
		final Checkbox isNamekey = new  Checkbox();
		final Checkbox isBpIDKey = new Checkbox();
		
		String title = "Tambah Pelanggan";
		Label LabelCity = new Label("Kota");
		Label LabelBPName = new Label("Nama Depan");
		Label LabelBPLastName = new Label("Nama Belakang");
		Label LabelBPMotherName = new Label("Nama Ibu");
		Label LabelBPIDCardNumber = new Label("No KTP");
		Label LabelTTL = new Label("Tanggal Lahir");
		Label LabelAddress1 = new Label("Alamat1");
		Label LabelAddress2 = new Label("Alamat2");
		Label LabelZIP = new Label("ZIP");
		Label LabelTelepon1 = new Label("Telepon1");
		Label LabelTelepon2 = new Label("Telepon2");
		Label LabelEMail = new Label("E-Mail");
		Label LabelCreditLimit = new Label("Batas Kredit");
		Label LabelSearchKey = new Label("Search Key");

		
		//City-7048
		MLookup lookupCity = MLookupFactory.get(ctx, formPresales.getWindowNo(), 0, 7048,DisplayType.TableDir);
		
		final WTableDirEditor cityList = new WTableDirEditor("C_City_ID", true, false, true,lookupCity);
		cityList.addValueChangeListener(this);
		cityList.setMandatory(true);
		
		
		//get credit limit from pos terminal
		//String getCreditLimit = "SELECT CreditLimit FROM C_POS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?";
		//BigDecimal creditLimit = DB.getSQLValueBDEx(null, getCreditLimit, new Object[]{AD_Client_ID,CreatedByPOS_ID});
		BigDecimal creditLimit = Env.ONEHUNDRED.multiply(Env.ONEHUNDRED).multiply(Env.ONEHUNDRED);
		if (creditLimit == null){
			creditLimit = Env.ZERO;
		}
				
		// Date
		Calendar cal = Calendar.getInstance();
		cal.setTime(Env.getContextAsDate(ctx, "#Date"));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//TTL.setValue(new Timestamp(cal.getTimeInMillis()));
		TTL.addValueChangeListener(this);
		
		final Window w = new Window();
		w.setTitle(title);
		
		Borderlayout mainbBorder = new Borderlayout();
		w.appendChild(mainbBorder);
		w.setWidth("450px");
		w.setHeight("510px");
		w.setBorder("normal");
		w.setPage(this.getForm().getPage());
		w.setClosable(true);
		w.setSizable(true);
		
		mainbBorder.setWidth("100%");
		mainbBorder.setHeight("100%");
		String grid = "border: 1px solid #C0C0C0; border-radius:5px; vertical-align:bottom;overflow:auto;";
		paraPanel.appendChild(inputGrid);

		South south = new South();
		south.setStyle(grid);
		mainbBorder.appendChild(south);
		south.appendChild(paraPanel);
		south.setSplittable(false);
		south.setSize("90%");
		
		inputGrid.setWidth("100%");
		inputGrid.setStyle("Height:100%;");
		

		rows = inputGrid.newRows();

		//Search Key
		row = rows.newRow();
		row.appendCellChild(LabelSearchKey.rightAlign());
		row.appendCellChild(SearchKey);
		SearchKey.setMaxlength(60);
		SearchKey.setHflex("true");
		
		//Nama 
		row = rows.newRow();
		row.appendCellChild(LabelBPName.rightAlign());
		row.appendCellChild(BPName);
		row.appendCellChild(isNamekey);
		isNamekey.setChecked(true);
		isNamekey.setLabel("Set Search Key");
		isNamekey.addEventListener(Events.ON_CHECK, new EventListener<Event>() {

			@Override
			public void onEvent(Event ev) throws Exception {
				
				if(!isNamekey.isChecked())
					isNamekey.setChecked(true);
				if(isBpIDKey.isChecked())
					isBpIDKey.setChecked(false);		
				if(isNamekey.isChecked()){
					String setSearchKey = BPName.getValue().toString()+BPLastName.getValue().toString(); 
					SearchKey.setValue(setSearchKey);
				}
				
				
			}
		});
		
		BPName.setHflex("true");
		BPName.setMaxlength(60);
		BPName.addEventListener(Events.ON_BLUR, new EventListener<Event>(){

			@Override
			public void onEvent(Event ev) throws Exception {

				if (ev.getTarget().equals(BPName)){
					
					if(isNamekey.isChecked()){
						String setSearchKey = BPName.getValue().toString()+BPLastName.getValue().toString(); 
						SearchKey.setValue(setSearchKey);
					}
				}
				
			}
			
		}
		);
				
		//Nama Belakang
		row = rows.newRow();
		row.appendCellChild(LabelBPLastName.rightAlign());
		row.appendCellChild(BPLastName);
		BPLastName.setHflex("true");
		BPLastName.setMaxlength(60);
		BPLastName.addEventListener(Events.ON_BLUR, new EventListener<Event>(){

			@Override
			public void onEvent(Event ev) throws Exception {

				if (ev.getTarget().equals(BPLastName)){
					
					if(isNamekey.isChecked()){
						String setSearchKey = BPName.getValue().toString()+BPLastName.getValue().toString(); 						
						SearchKey.setValue(setSearchKey);

					}
				}
				
			}
			
		}
		);
	
		//TTL
		row = rows.newRow();
		row.appendCellChild(LabelTTL.rightAlign());
		row.appendCellChild(TTL.getComponent(),1);
		TTL.getComponent().setHflex("true");

		
		//Nama Ibu
		row = rows.newRow();
		row.appendCellChild(LabelBPMotherName.rightAlign());
		row.appendCellChild(BPMotherName);
		BPMotherName.setMaxlength(60);
		BPMotherName.setHflex("true");
		
		//No KTP
		row = rows.newRow();
		row.appendCellChild(LabelBPIDCardNumber.rightAlign());
		row.appendCellChild(BPIDCardNumber);
		row.appendCellChild(isBpIDKey);
		isBpIDKey.setChecked(false);
		isBpIDKey.setLabel("Set Search Key");
		isBpIDKey.addActionListener(this);
		isBpIDKey.addEventListener(Events.ON_CHECK, new EventListener<Event>() {
			
			@Override
			public void onEvent(Event ev) throws Exception {

				if(!isBpIDKey.isChecked())
					isBpIDKey.setChecked(true);
				if(isNamekey.isChecked())
					isNamekey.setChecked(false);
				if(isBpIDKey.isChecked()){
					SearchKey.setValue(BPIDCardNumber.getValue().toString());
				}
			}
		});
		
		BPIDCardNumber.setHflex("true");
		BPIDCardNumber.setMaxlength(60);
		BPIDCardNumber.addEventListener(Events.ON_BLUR, new EventListener<Event>(){

			@Override
			public void onEvent(Event ev) throws Exception {

				if (ev.getTarget().equals(BPIDCardNumber)){
					
					if(isBpIDKey.isChecked()){
						SearchKey.setValue(BPIDCardNumber.getValue().toString());
					}
				}
				
			}
			
		}
		);

		
		//Alamat 1
		row = rows.newRow();
		row.appendCellChild(LabelAddress1.rightAlign());
		row.appendCellChild(Address1);
		Address1.setRows(2);
		Address1.setHflex("true");
		Address1.setMaxlength(120);
		
		//Alamat 2
		row = rows.newRow();
		row.appendCellChild(LabelAddress2.rightAlign());
		row.appendCellChild(Address2);
		Address2.setRows(2);
		Address2.setHflex("true");
		Address2.setMaxlength(120);
		
		//City		
		row = rows.newRow();
		row.appendCellChild(LabelCity.rightAlign());		
		row.appendCellChild(cityList.getComponent(), 1);
		cityList.getComponent().setHflex("true");
		
		
		//ZIP
		row = rows.newRow();
		row.appendCellChild(LabelZIP.rightAlign());
		row.appendCellChild(ZIP);
		ZIP.setHflex("true");
		ZIP.setMaxlength(5);
		ZIP.addEventListener(Events.ON_BLUR, new EventListener<Event>(){

			@Override
			public void onEvent(Event ev) throws Exception {

				if (ev.getTarget().equals(ZIP)){
					boolean isNum = true;
					String postal = ZIP.getValue();
					if(postal != null && postal != ""){
						isNum = isNumeric(postal);
						
						if(!isNum){
							FDialog.info(windowNo, null, "", "Input tidak sesuai format", "Info");
							ZIP.setValue(null);
							return;
						}
						
						int point = postal.indexOf(".");
						if(point >= 0 ){
							FDialog.info(windowNo, null, "", "Input tidak sesuai format", "Info");
							ZIP.setValue(null);
							return;
						}
						
					}
				}
				
			}
			
		}
		);
		
		
		//Telepon 1
		row = rows.newRow();
		row.appendCellChild(LabelTelepon1.rightAlign());
		row.appendCellChild(Telepon1);
		Telepon1.setHflex("true");
		Telepon1.setMaxlength(60);
		Telepon1.addEventListener(Events.ON_BLUR, new EventListener<Event>(){

			@Override
			public void onEvent(Event ev) throws Exception {

				if (ev.getTarget().equals(Telepon1)){
					boolean isNum = true;
					String tlp = Telepon1.getValue();
					if(tlp != null && tlp != ""){
						isNum = isNumeric(tlp);
						
						if(!isNum){
							FDialog.info(windowNo, null, "", "Input tidak sesuai format", "Info");
							Telepon1.setValue(null);
							return;
						}
						
						int point = tlp.indexOf(".");
						if(point >= 0 ){
							FDialog.info(windowNo, null, "", "Input tidak sesuai format", "Info");
							Telepon1.setValue(null);
							return;
						}
						
					}
				}
				
			}
			
		}
		);

				
		//Telepon 2
		row = rows.newRow();
		row.appendCellChild(LabelTelepon2.rightAlign());
		row.appendCellChild(Telepon2);
		Telepon2.setHflex("true");
		Telepon2.setMaxlength(60);
		Telepon2.addEventListener(Events.ON_BLUR, new EventListener<Event>(){

			@Override
			public void onEvent(Event ev) throws Exception {

				if (ev.getTarget().equals(Telepon2)){
					boolean isNum = true;
					String tlp = Telepon2.getValue();
					if(tlp != null && tlp != ""){
						isNum = isNumeric(tlp);
						
						if(!isNum){
							FDialog.info(windowNo, null, "", "Input tidak sesuai format", "Info");
							Telepon2.setValue(null);
							return;
						}
						
						int point = tlp.indexOf(".");
						if(point >= 0 ){
							FDialog.info(windowNo, null, "", "Input tidak sesuai format", "Info");
							Telepon1.setValue(null);
							return;
						}
					}
				}
				
			}
			
		}
		);
		
		//E-Mail
		row = rows.newRow();
		row.appendCellChild(LabelEMail.rightAlign());
		row.appendCellChild(EMail);
		EMail.setHflex("true");
		EMail.setMaxlength(60);
		
		//Credit Limit
		row = rows.newRow();
		row.appendCellChild(LabelCreditLimit.rightAlign());
		row.appendCellChild(CreditLimit);
		CreditLimit.setHflex("true");
		CreditLimit.setValue(creditLimit);
		CreditLimit.setMaxlength(22);
		CreditLimit.setValue("20000000");
			
		Vbox vbox = new Vbox();
		w.appendChild(vbox);
		w.setClosable(true);
		vbox.appendChild(new Separator());
		final ConfirmPanel panel = new ConfirmPanel(true, false, false, false, false, false, false);
		vbox.appendChild(panel);
		panel.addActionListener(Events.ON_CLICK, new EventListener<Event>() {
		
			public void onEvent(Event event) throws Exception {
				if (event.getTarget() == panel.getButton(ConfirmPanel.A_CANCEL)) {
					w.dispose();
				}
				else if (event.getTarget() == panel.getButton(ConfirmPanel.A_OK)){
					//MClient Client = new MClient(ctx, AD_Client_ID, null);
					//String p_SearchKey = Client.getValue()+"/"+"POS"+System.currentTimeMillis();
					
					 String p_SearchKey = SearchKey.getValue().toString();

					 //exist search key validation
					 StringBuilder SQLCekExistSearchKey = new StringBuilder();
					 SQLCekExistSearchKey.append("SELECT C_BPartner_ID ");
					 SQLCekExistSearchKey.append("FROM C_BPartner ");
					 SQLCekExistSearchKey.append("WHERE Value ='");
					 SQLCekExistSearchKey.append(p_SearchKey+"'");
					 SQLCekExistSearchKey.append(" AND AD_Client_ID = ?");

					
					 int cekPartner_ID = DB.getSQLValueEx(ctx.toString(), SQLCekExistSearchKey.toString(), new Object[]{AD_Client_ID});
					
					 if (cekPartner_ID > 0){
						 FDialog.info(windowNo, null, "","Penambahan Pelanggan Tidak Berhasil, Search Key Pelanggan Duplikat","Info");
						 return;
					 }
					 
					 
					MLocation loc = new MLocation(ctx, 0, null);
					MCity city = null;
					MRegion reg = null;
					Integer  C_City_ID = (Integer) cityList.getValue();
					String msg ="";
					if (C_City_ID == null){
						C_City_ID = 0;
					}
					
					
					//Validasi
					if(SearchKey.getValue().toString() == "" || SearchKey.getValue().isEmpty()){
						msg = msg+"Search Key Tidak Boleh Kosong"+"\n";
					}
					
					if(BPIDCardNumber.getValue().toString().length()!=16 && BPName.getValue().isEmpty() && BPLastName.getValue().isEmpty()){
						msg = msg+"No KTP dan Nama Pelanggan Tidak Boleh Kosong Semua"+"\n";
					}
					
					if(BPName.getValue().toString() == "" || BPName.getValue().isEmpty()){
						msg = msg+"Nama Pelanggan Belum Di Isi "+"\n";
					}
					
					if(BPLastName.getValue().toString() == "" || BPLastName.getValue().isEmpty()){
						msg = msg+"Nama Belakang Pelanggan Belum Di Isi "+"\n";
					}
					
					if(TTL.getValue()!=null){
						Date input = (Date) TTL.getValue();
						Date now =  new Date();
						
						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(input);
						int yearInput = cal1.get(Calendar.YEAR);

						Calendar cal2 = Calendar.getInstance();
						cal1.setTime(now);
						int yearNow = cal2.get(Calendar.YEAR);
						
						if(yearInput>=yearNow){
							msg = msg+"Input Tahun Lahir Harus Lebih Kecil Dari Tahun Sekarang "+"\n";
						}
					}
					if(BPMotherName.getValue().toString()=="" ||BPMotherName.getValue().isEmpty()){
						msg = msg+"Nama Ibu Belum Di Isi "+"\n";
					}
					if(BPIDCardNumber.getValue().toString()==""||BPIDCardNumber.getValue().isEmpty()){
						msg = msg+"No KTP Belum Di Isi "+"\n";
					}
					if(Address1.getValue().toString()=="" ||Address1.getValue().isEmpty()){
						msg = msg+"Alamat Belum Di Isi "+"\n";
					}
					if(C_City_ID <= 0 || C_City_ID == null){
						msg = msg+"Kota Belum Di Pilih "+"\n";
					}
					if(Telepon1.getValue().toString()==""&&Telepon1.getValue().isEmpty()){
						msg = msg+"Telepon Belum Di Isi "+"\n";;
					}
					if(CreditLimit.getValue()==null){
						msg = msg+"Batas Kredit Belum Di Isi "+"\n";;
					}
					
					if (msg != ""){
						FDialog.info(windowNo, null, "",msg,"Info");
						return;
					}
					
					MBPartner bp = new MBPartner(ctx, 0, null);
					bp.setClientOrg(AD_Client_ID, 0);
					bp.setValue(p_SearchKey);
					bp.setName(BPName.getValue().toString());
					bp.setName2(BPLastName.getValue().toString());
					bp.setSO_CreditLimit(CreditLimit.getValue());
					bp.set_CustomColumn("IbuKandung", BPMotherName.getValue().toString());
					bp.set_CustomColumn("NoKTP", BPIDCardNumber.getValue().toString());
					bp.setIsCustomer(true);
					bp.saveEx();
					
					

					int C_Region_ID = 1000000;//Indonesia
					if (C_City_ID > 0){
						city = new MCity(ctx, C_City_ID, null);
					}
					if (C_Region_ID > 0){
						reg = new MRegion(ctx, C_Region_ID, null);
					}
					
					
					loc.setClientOrg(AD_Client_ID, 0);
					loc.setAddress1(Address1.getValue().toString());
					loc.setAddress2(Address2.getValue().toString());
					loc.setC_City_ID(C_City_ID);
					loc.setC_Region_ID(C_Region_ID);
					loc.setC_Country_ID(209);
					loc.setCity(city.getName());
					loc.setRegionName(reg.getName());
					loc.setPostal(ZIP.getValue());
					loc.saveEx();
					
					MBPartnerLocation BPLoc = new MBPartnerLocation(ctx, 0, null);
					BPLoc.setClientOrg(AD_Client_ID, 0);
					BPLoc.setC_BPartner_ID(bp.getC_BPartner_ID());
					BPLoc.setName(loc.getCity());
					BPLoc.setC_Location_ID(loc.getC_Location_ID());
					BPLoc.setPhone(Telepon1.getValue().toString());
					BPLoc.setPhone2(Telepon2.getValue().toString());
					if (TTL.getValue()!=null){
					BPLoc.set_ValueNoCheck("Birthday", TTL.getValue());	
					}
					//BPLoc.setFax(p_fax);
					BPLoc.setIsShipTo(true);
					BPLoc.setIsBillTo(true);
					BPLoc.setIsPayFrom(true);
					BPLoc.setIsRemitTo(true);
					BPLoc.set_CustomColumn("IsTax", true);
					BPLoc.saveEx();
					
					if (bp.getC_BPartner_ID() > 0){
						String BPNameRs = bp.getName();
						FDialog.info(windowNo, null, "","Input Data Pelanggan Dengan Nama "+BPNameRs+" Berhasil Disimpan" ,"Info");
						PelangganSearch.setValue(bp.getC_BPartner_ID());
					}else if (bp.getC_BPartner_ID() <= 0){
						FDialog.info(windowNo, null, "","Penambahan Pelanggan Tidak Berhasil","Info");
						return;
					}
					
					
				}
				w.onClose();
			}
		});
		

		w.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				w.dispose();
			}
		});
		w.doHighlighted();				
	}
	
	
	public void setLineNetAmt (int M_PriceList_ID, int M_Product_ID,int C_Currency_ID,int C_Decoris_PreSalesLine_ID, BigDecimal PriceActual, BigDecimal qty,BigDecimal diskonLine)
	{
		boolean isTaxIncluded = false;
		int precision = getPrecision(C_Currency_ID, C_Decoris_PreSalesLine_ID);
		
		MPriceList pl = MPriceList.get(ctx, M_PriceList_ID, null);
		isTaxIncluded =  pl.isTaxIncluded();
		X_C_Decoris_PreSalesLine preSalesLine = new X_C_Decoris_PreSalesLine(ctx, C_Decoris_PreSalesLine_ID, null);
		
		
		BigDecimal bd = (PriceActual.subtract(diskonLine)).multiply(qty); 
				
		if (isTaxIncluded)	{
			BigDecimal taxStdAmt = Env.ZERO;
			
			MTax stdTax = null;
			MProduct prod = new MProduct(ctx, M_Product_ID, null);
			
			
			MTaxCategory cat = new MTaxCategory(ctx, prod.getC_TaxCategory_ID(), null);
			String taxCatName = cat.getName();

			String sqlTax = "SELECT C_Tax_ID FROM C_Tax WHERE AD_Client_ID = ? AND Name = '"
					+ taxCatName + "'";
			int C_Tax_ID = DB.getSQLValueEx(cat.get_TrxName(), sqlTax,
					new Object[] { AD_Client_ID });

			stdTax = new MTax(ctx, C_Tax_ID, null);

			if (stdTax != null)
			{
				if (log.isLoggable(Level.FINE)){
					log.fine("stdTax rate is " + stdTax.getRate());
			}
								
				//get Tax Provider
				MTaxProvider provider = new MTaxProvider(ctx, stdTax.getC_TaxProvider_ID(), null);
				ITaxProvider calculator = Core.getTaxProvider(provider);
				if (calculator == null)
					throw new AdempiereException(Msg.getMsg(ctx, "TaxNoProvider"));

				taxStdAmt = taxStdAmt.add(calculator.calculateTax(stdTax, bd, isTaxIncluded, m_precision));
				
				bd = bd.subtract(taxStdAmt);
				preSalesLine.setTaxAmt(taxStdAmt);

				if (log.isLoggable(Level.FINE)) log.fine("Price List includes Tax : New Tax Amt: " + " Standard Tax Amt: " + taxStdAmt + " Line Net Amt: " + bd);	
			}
			
		}
		
		if (bd.scale() > precision)
			bd = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
		// preSalesLine.setLineNetAmt((bd));
		 preSalesLine.saveEx();
	}	//	setLineNetAmt
	
	
	public int getPrecision(int C_Currency_ID, int C_Decoris_PreSalesLine_ID)
	{
		
		if (m_precision != null)
			return m_precision.intValue();
		//
		if (C_Currency_ID == 0)
		{
			if (m_precision != null)
				return m_precision.intValue();
		}
		if (C_Currency_ID != 0)
		{
			MCurrency cur = MCurrency.get(ctx, C_Currency_ID);
			if (cur.get_ID() != 0)
			{
				m_precision = new Integer (cur.getStdPrecision());
				return m_precision.intValue();
			}
		}
		//	Fallback
		String sql = "SELECT c.StdPrecision "
			+ "FROM C_Currency c INNER JOIN C_DecorisPresales x ON (x.C_Currency_ID=c.C_Currency_ID) "
			+ "WHERE x.C_DecorisPresalesLine_ID= ?";
		int i = DB.getSQLValue(null, sql, C_Decoris_PreSalesLine_ID);
		m_precision = new Integer(i);
		return m_precision.intValue();
	}	//	getPrecision
	
	
	protected ArrayList<KeyNamePair> loadImei(int M_Product_ID) {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("a.M_AttributeSetInstance_ID, ");
		sql.append("a.Serno ");
		sql.append("FROM ( " );
		sql.append("SELECT null as M_AttributeSetInstance_ID, ");
		sql.append("'' as Serno ");
		sql.append("UNION ");
		sql.append("SELECT M_AttributeSetInstance_ID,Serno ");
		sql.append("FROM M_AttributeSetInstance ");
		sql.append("WHERE AD_Client_ID = ? ");
		sql.append("AND M_AttributeSetInstance_ID IN ( ");
		sql.append("SELECT M_AttributeSetInstance_ID ");
		sql.append("FROM M_StorageOnHAND ");
		sql.append("WHERE  AD_Client_ID = ? ");
		sql.append("AND M_Product_ID = ? ");
		sql.append("AND QtyOnHAND > 0) ");
		sql.append("AND M_Attributesetinstance_ID NOT IN ( ");
		sql.append("SELECT M_Attributesetinstance_ID ");
		sql.append("FROM C_Decoris_PresalesLine ");
		sql.append("WHERE C_Decoris_Presales_ID IN ( ");
		sql.append("SELECT C_Decoris_Presales_ID ");
		sql.append("FROM C_Decoris_Presales ");
		sql.append("WHERE  status <>'CL' ");
		sql.append("AND M_Attributesetinstance_Id is NOT NULL)))a ");
		sql.append("ORDER BY a.Serno ");

		
//				+ " SELECT M_AttributeSetInstance_ID,"
//				+ " Serno "
//				+ " FROM M_AttributeSetInstance "
//				+ " WHERE AD_Client_ID = ? "
//				+ " AND M_AttributeSetInstance_ID IN ("
//				+ " SELECT M_AttributeSetInstance_ID "
//				+ " FROM M_StorageOnHand "
//				+ " WHERE  AD_Client_ID = ? "
//				+ " AND M_Product_ID = ? AND QtyOnHand > 0))a "
//				+ " ORDER BY a.Serno");
		

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Client_ID);
			pstmt.setInt(3, M_Product_ID);

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
	
	public void calculateTableChange(int col){
		
		BigDecimal totalBruto  = Env.ZERO;
		BigDecimal totalBelanja  = Env.ZERO;
		BigDecimal totalDiskon  = Env.ZERO;
		BigDecimal totalDPP  = Env.ZERO;
		BigDecimal totalPPN  = Env.ZERO;
		
		
		if(col == 6 || col == 8){

		for (int i = 0 ; i < PreSalesTable.getRowCount() ; i++){
			
			//boolean isSelected = (boolean) PreSalesTable.getValueAt(i, 0); 
			KeyNamePair kp = (KeyNamePair) PreSalesTable.getValueAt(i, 2);
			BigDecimal totalHargaBruto = Env.ZERO;
			BigDecimal totalharga = Env.ZERO;
			BigDecimal discPerLine = Env.ZERO;
			BigDecimal DPP = Env.ZERO;
			BigDecimal PPN = Env.ZERO;
			MProduct prod = new MProduct(ctx, kp.getKey(), null);

			StringBuilder SQLTaxRate = new StringBuilder();;
			SQLTaxRate.append("SELECT Rate ");
			SQLTaxRate.append("FROM C_Tax ");
			SQLTaxRate.append("WHERE AD_Client_ID = ? ");
			SQLTaxRate.append("AND C_TaxCategory_ID = ? ");

			BigDecimal taxRate = DB.getSQLValueBDEx(null, SQLTaxRate.toString(),new Object []{AD_Client_ID,prod.getC_TaxCategory_ID()});


			
			//if(isSelected){
				
					
				BigDecimal priceList = (BigDecimal) PreSalesTable.getValueAt(i, 5);
				BigDecimal harga = (BigDecimal) PreSalesTable.getValueAt(i, 6);
				BigDecimal qty = (BigDecimal) PreSalesTable.getValueAt(i, 8);
				//BigDecimal diskonPersen = (BigDecimal) PreSalesTable.getValueAt(i, 7);
			
				Integer C_BPartner_ID = (Integer) PelangganSearch.getValue();
				Integer M_PriceList_ID = getIDFromComboBox(DaftarHargaSearch,MPriceList.Table_Name,MPriceList.COLUMNNAME_Name);
				
				
				
				MProductPricing pp = new MProductPricing(kp.getKey(), C_BPartner_ID,Env.ONE, true);
				

				Timestamp date = new Timestamp(System.currentTimeMillis());
				
				String sql = "SELECT plv.M_PriceList_Version_ID "
						+ "FROM M_PriceList_Version plv "
						+ "WHERE plv.AD_Client_ID = ? "
						+ " AND plv.M_PriceList_ID= ? " // 1
						+ " AND plv.ValidFrom <= ? " + "ORDER BY plv.ValidFrom DESC";
				
				int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql,new Object []{AD_Client_ID,M_PriceList_ID, date});
				
				pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
				pp.setPriceDate(date);

				//BigDecimal disc = diskonPersen.divide(Env.ONEHUNDRED);
				BigDecimal disc = priceList.subtract(harga);
				BigDecimal lineBruto = priceList.multiply(qty);
				BigDecimal lineAmt = harga.multiply(qty);
				BigDecimal discAmt = disc.multiply(qty);
				BigDecimal totalLineAmt = lineBruto.subtract(discAmt);
				
				Double dPersen = ((Double.valueOf(priceList.toString()) - Double
						.valueOf(harga.toString())) / Double.valueOf(priceList
						.toString())) * 100;
				BigDecimal persentase = BigDecimal.valueOf(dPersen).setScale(2,
						RoundingMode.HALF_DOWN);

				if (persentase.compareTo(Env.ZERO) < 0) {
					persentase = Env.ZERO;
				}
				
				
				PreSalesTable.setValueAt(persentase, i, 7);
				PreSalesTable.setValueAt(totalLineAmt, i, 9);

				
				if(taxRate.compareTo(Env.ZERO)>0){
					BigDecimal divider = new BigDecimal("1.1");
					DPP = totalLineAmt.divide(divider,2,RoundingMode.HALF_UP);
					PPN = totalLineAmt.subtract(DPP);
				}				
				discPerLine = discAmt;
				totalharga = lineAmt;
				totalHargaBruto = lineBruto;
			
			if(DPP.compareTo(Env.ZERO)== 0 && PPN.compareTo(Env.ZERO)== 0){
				if(taxRate.compareTo(Env.ZERO)> 0 ){
					BigDecimal ttlHarga = (BigDecimal) PreSalesTable.getValueAt(i,9);
					BigDecimal divider = new BigDecimal("1.1");
					DPP = ttlHarga.divide(divider,2,RoundingMode.HALF_UP);
					PPN = ttlHarga.subtract(DPP);
					
				}
			}
		
			totalBruto = totalBruto.add(totalHargaBruto);
			totalBelanja = totalBelanja.add(totalharga);
			totalDiskon = totalDiskon.add(discPerLine);
			totalDPP = totalDPP.add(DPP);
			totalPPN = totalPPN.add(PPN);
		}
		}
		
		totalBrutoTextBox.setText(format.format(totalBruto));
		diskonTextBox.setText(format.format(totalDiskon));
		totalBelanjaTextBox.setText(format.format(totalBruto.subtract(totalDiskon)));
		DPPTextBox.setText(format.format(totalDPP));
		PPNTextBox.setText(format.format(totalPPN));

		
		BigDecimal netto = totalBruto.subtract(totalDiskon);
		TotalTextBox1.setText(format.format(netto));

		
		BigDecimal tunai  = Env.ZERO;
		BigDecimal bank  = Env.ZERO;
		BigDecimal leasing  = Env.ZERO;
		BigDecimal hutang  = Env.ZERO;
		BigDecimal totalBayar = Env.ZERO;
		BigDecimal totalBayar2 = Env.ZERO;
		BigDecimal kembalian = Env.ZERO;
		
//		tunai = paymentTunai.getValue();
//		bank = paymentBank.getValue();
//		leasing = paymentLeasing.getValue();
//		hutang = paymentHutang.getValue();

		totalBayar = tunai.add(bank).add(leasing).add(hutang);
		totalBayar2 = tunai.add(bank);
		
		totalBayarTextBox.setText(format.format(totalBayar));
		totalBayarTunaiTextBox.setText(format.format(totalBayar2));
		
		kembalian = totalBayar.subtract(totalBruto.subtract(totalDiskon));
		if(kembalian.compareTo(Env.ONE) < 0){
			kembalian = Env.ZERO;
		}
		
		kembalianTextBox.setText(format.format(kembalian));
		kembaliTextBox.setText(format.format(kembalian));
		

	}
	
	
	public void calculateAddProduct(){
		
		BigDecimal totalBruto  = Env.ZERO;
		BigDecimal totalBelanja  = Env.ZERO;
		BigDecimal totalDiskon  = Env.ZERO;
		BigDecimal totalDPP  = Env.ZERO;
		BigDecimal totalPPN  = Env.ZERO;
		
		for (int i = 0 ; i < PreSalesTable.getRowCount() ; i++){
			
			BigDecimal totalHargaBruto = Env.ZERO;
			BigDecimal totalPerLine = Env.ZERO;
			BigDecimal discPerLine = Env.ZERO;
			BigDecimal DPP = Env.ZERO;
			BigDecimal PPN = Env.ZERO;
						
			BigDecimal priceList = (BigDecimal) PreSalesTable.getValueAt(i, 5);
			BigDecimal harga = (BigDecimal) PreSalesTable.getValueAt(i, 6);
			BigDecimal qty = (BigDecimal) PreSalesTable.getValueAt(i, 8);
			KeyNamePair kp = (KeyNamePair) PreSalesTable.getValueAt(i, 2);
			Integer C_BPartner_ID = (Integer) PelangganSearch.getValue();
			Integer M_PriceList_ID = getIDFromComboBox(DaftarHargaSearch,MPriceList.Table_Name,MPriceList.COLUMNNAME_Name);
						
			
			MProduct prod = new MProduct(ctx, kp.getKey(), null);

			StringBuilder SQLTaxRate = new StringBuilder();;
			SQLTaxRate.append("SELECT Rate ");
			SQLTaxRate.append("FROM C_Tax ");
			SQLTaxRate.append("WHERE AD_Client_ID = ? ");
			SQLTaxRate.append("AND C_TaxCategory_ID = ? ");

			BigDecimal taxRate = DB.getSQLValueBDEx(null, SQLTaxRate.toString(),new Object []{AD_Client_ID,prod.getC_TaxCategory_ID()});


			
			MProductPricing pp = new MProductPricing(kp.getKey(), C_BPartner_ID,Env.ONE, true);

			Timestamp date = new Timestamp(System.currentTimeMillis());
				
			String sql = "SELECT plv.M_PriceList_Version_ID "
						+ "FROM M_PriceList_Version plv "
						+ "WHERE plv.AD_Client_ID = ? "
						+ " AND plv.M_PriceList_ID= ? " // 1
						+ " AND plv.ValidFrom <= ? " + "ORDER BY plv.ValidFrom DESC";
				
			int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql,new Object []{AD_Client_ID,M_PriceList_ID, date});
				
			pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
			pp.setPriceDate(date);
			
			
			Double dPersen = ((Double.valueOf(priceList.toString()) - Double
					.valueOf(harga.toString())) / Double.valueOf(priceList
					.toString())) * 100;
			BigDecimal persentase = BigDecimal.valueOf(dPersen).setScale(2,
					RoundingMode.HALF_DOWN);

			if (persentase.compareTo(Env.ZERO) < 0) {
				persentase = Env.ZERO;
			}
			

			BigDecimal disc = priceList.subtract(harga);
			BigDecimal lineAmt = harga.multiply(qty);
			BigDecimal discAmt = disc.multiply(qty);
			BigDecimal lineBruto =  priceList.multiply(qty);
			BigDecimal totalLineAmt = lineBruto.subtract(discAmt);

				
			PreSalesTable.setValueAt(totalLineAmt, i, 9);
			PreSalesTable.setValueAt(persentase, i, 7);

			totalPerLine = lineAmt;
			totalHargaBruto = lineBruto;
			discPerLine = discAmt;
			
			if(taxRate.compareTo(Env.ZERO)>0){
				BigDecimal divider = new BigDecimal("1.1");
				DPP = totalLineAmt.divide(divider,2,RoundingMode.HALF_UP);
				PPN = totalLineAmt.subtract(DPP);
			}			

			if(totalPerLine.compareTo(Env.ZERO)== 0){
				totalPerLine = (BigDecimal) PreSalesTable.getValueAt(i, 9);	
			}
			
			if(DPP.compareTo(Env.ZERO)== 0 && PPN.compareTo(Env.ZERO)== 0){
				if(taxRate.compareTo(Env.ZERO)>0){
					BigDecimal divider = new BigDecimal("1.1");
					BigDecimal ttlHarga = (BigDecimal) PreSalesTable.getValueAt(i,9);
					DPP = ttlHarga.divide(divider,2,RoundingMode.HALF_UP);
					PPN = ttlHarga.subtract(DPP);
					
				}
			}
			totalBruto = totalBruto.add(totalHargaBruto);
			totalDiskon = totalDiskon.add(discPerLine);
			totalBelanja = totalBruto.subtract(totalDiskon);
			totalDPP = totalDPP.add(DPP);
			totalPPN = totalPPN.add(PPN);
		}
		
		
		totalBrutoTextBox.setText(format.format(totalBruto));
		diskonTextBox.setText(format.format(totalDiskon));
		totalBelanjaTextBox.setText(format.format(totalBelanja));
		DPPTextBox.setText(format.format(totalDPP));
		PPNTextBox.setText(format.format(totalPPN));
		
		BigDecimal netto = totalBruto.subtract(totalDiskon);
		TotalTextBox1.setText(format.format(netto));

		
		BigDecimal tunai  = Env.ZERO;
		BigDecimal bank  = Env.ZERO;
		BigDecimal leasing  = Env.ZERO;
		BigDecimal hutang  = Env.ZERO;
		BigDecimal totalBayar = Env.ZERO;
		BigDecimal totalBayar2 = Env.ZERO;
		BigDecimal kembalian = Env.ZERO;
		
//		tunai = paymentTunai.getValue();
//		bank = paymentBank.getValue();
//		leasing = paymentLeasing.getValue();
//		hutang = paymentHutang.getValue();

		totalBayar = tunai.add(bank);
		totalBayar2 = tunai.add(bank).add(leasing).add(hutang);

		
		totalBayarTextBox.setText(format.format(totalBayar2));
		totalBayarTunaiTextBox.setText(format.format(totalBayar));
		
		kembalian = totalBayar.subtract(totalBruto.subtract(totalDiskon));
		if(kembalian.compareTo(Env.ONE) < 0){
			kembalian = Env.ZERO;
		}
		
		
		
		kembalianTextBox.setText(format.format(kembalian));
		kembaliTextBox.setText(format.format(kembalian));
		

	}
	
	public void createPOSPayment(int C_Decoris_PreSales_ID,int C_Order_ID,int C_POSTenderType_ID,String tenderType,BigDecimal payAmt,int C_BankAccount_ID ){
		
		MOrder order = new MOrder(Env.getCtx(), C_Order_ID, null); 
		X_C_Decoris_PreSales pSales = new X_C_Decoris_PreSales(ctx, C_Decoris_PreSales_ID, null);
		X_C_POSPayment posPayment = new X_C_POSPayment(order.getCtx(), 0, order.get_TrxName());
		
		posPayment.setClientOrg(AD_Client_ID, pSales.getAD_Org_ID());
		posPayment.setC_Order_ID(C_Order_ID);
		

		posPayment.setC_POSTenderType_ID(C_POSTenderType_ID);
		posPayment.setTenderType(tenderType);
		posPayment.setPayAmt(payAmt);
		if (tenderType.equals("L")){
			posPayment.set_CustomColumn("LeaseProvider", pSales.getleaseprovider());
		}
		posPayment.set_CustomColumn("C_BankAccount_ID", C_BankAccount_ID);
		posPayment.saveEx();
	}

	
	private void calculateDPPExistingPresales(){
		
		BigDecimal totalDPP = Env.ZERO;
		BigDecimal totalPPN = Env.ZERO;
		for (int i = 0 ; i < PreSalesTable.getRowCount() ; i++){
			
			BigDecimal DPP = Env.ZERO;
			BigDecimal PPN = Env.ZERO;
			
			KeyNamePair kp = (KeyNamePair) PreSalesTable.getValueAt(i, 2);
			
			int M_Product_ID = kp.getKey();
			
			MProduct pr = new MProduct(ctx, M_Product_ID, null);
			
			StringBuilder SQLTaxRate = new StringBuilder();;
			SQLTaxRate.append("SELECT Rate ");
			SQLTaxRate.append("FROM C_Tax ");
			SQLTaxRate.append("WHERE AD_Client_ID = ? ");
			SQLTaxRate.append("AND C_TaxCategory_ID = ? ");

			BigDecimal taxRate = DB.getSQLValueBDEx(null, SQLTaxRate.toString(),new Object []{AD_Client_ID,pr.getC_TaxCategory_ID()});

			
			if(taxRate.compareTo(Env.ZERO)> 0 ){
				
				BigDecimal ttlHarga = (BigDecimal) PreSalesTable.getValueAt(i,9);
				BigDecimal divider = new BigDecimal("1.1");
				DPP = ttlHarga.divide(divider,2,RoundingMode.HALF_UP);
				PPN = ttlHarga.subtract(DPP);
						
			}
			
			totalDPP = totalDPP.add(DPP);
			totalPPN = totalPPN.add(PPN);
		}
		
		
		DPPTextBox.setText(format.format(totalDPP));
		PPNTextBox.setText(format.format(totalPPN));
	
	}

	
	
	private BigDecimal calDPP(boolean isDPP , boolean isPPN , int M_Product_ID , BigDecimal totalHarga){
		
			BigDecimal DPP = Env.ZERO;
			BigDecimal PPN = Env.ZERO;
						
			MProduct pr = new MProduct(ctx, M_Product_ID, null);
			
			StringBuilder SQLTaxRate = new StringBuilder();;
			SQLTaxRate.append("SELECT Rate ");
			SQLTaxRate.append("FROM C_Tax ");
			SQLTaxRate.append("WHERE AD_Client_ID = ? ");
			SQLTaxRate.append("AND C_TaxCategory_ID = ? ");

			BigDecimal taxRate = DB.getSQLValueBDEx(null, SQLTaxRate.toString(),new Object []{AD_Client_ID,pr.getC_TaxCategory_ID()});

			
			if(taxRate.compareTo(Env.ZERO)> 0 ){
				
				BigDecimal divider = new BigDecimal("1.1");
				DPP = totalHarga.divide(divider,2,RoundingMode.HALF_UP);
				PPN = totalHarga.subtract(DPP);
						
			}
			
			return isDPP?DPP:PPN;
			
		}


	private BigDecimal callDBfromTextBox (Textbox textBox){
		BigDecimal result = Env.ZERO;
		Double dValue = 0.00;

		if (textBox.getText().isEmpty()) {
			dValue = 0.00;
		} else if (!textBox.getText().isEmpty()) {
			if (AD_Language.toUpperCase().equals("EN_US")) {
				dValue = Double.valueOf(textBox.getText().replaceAll(",", ""));
			} else if (AD_Language.toUpperCase().equals("IN_ID")) {
				String sValue = textBox.getText().replaceAll("\\.", "").replaceAll(",", ".");
				dValue = Double.valueOf(sValue);
			}
		}

		result = BigDecimal.valueOf(dValue);
	
		return result;
	}

	private ArrayList<KeyNamePair> loadPriceList() {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M_PriceList_ID,Name ");
		sql.append("FROM M_PriceList ");
		sql.append("WHERE AD_Client_ID = ? ");
		sql.append("AND IsActive = 'Y' ");
		sql.append("AND IsSOPriceList = 'Y' ");


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);

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
	
	private ArrayList<KeyNamePair> loadLocator(int M_Warehouse_ID,int AD_Org_ID) {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M_Locator_ID,Value ");
		sql.append("FROM M_Locator ");
		sql.append("WHERE AD_Client_ID = ? ");
		sql.append("AND AD_Org_ID = ? ");
		sql.append("AND IsActive = 'Y' ");
		sql.append("AND M_Warehouse_ID = ? ");


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Org_ID);
			pstmt.setInt(3, M_Warehouse_ID);


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
	
	
	private ArrayList<KeyNamePair> loadWarehouse(int AD_Org_ID) {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT M_Warehouse_ID,Name ");
		sql.append("FROM M_Warehouse ");
		sql.append("WHERE AD_Client_ID = ? ");
		sql.append("AND AD_Org_ID = ? ");
		sql.append("AND IsActive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Org_ID);


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
	
	public int getIDFromComboBox (Combobox combobox, String tableName,String selectColumnName){
		int result_ID = 0;
		String select_ID = tableName+"_ID";

		String cbValue = combobox.getText();

		StringBuilder sqlPriceList = new StringBuilder();
		
		sqlPriceList.append("SELECT ");
		sqlPriceList.append(select_ID);
		sqlPriceList.append(" FROM ");
		sqlPriceList.append(tableName);
		sqlPriceList.append(" WHERE AD_Client_ID = ? ");
		sqlPriceList.append(" AND "+selectColumnName+ "= " );
		sqlPriceList.append(" '"+cbValue+"'");


		
		result_ID  = DB.getSQLValueEx(null, sqlPriceList.toString(),AD_Client_ID);

		return result_ID;
		
	} 
	
	
	public void process(){
		String ErrorMsg = "";
		X_C_Decoris_PreSales preSales = null;
		//get C_Decoris_PreSales_ID for validation (create new or get existing)
		Integer C_Decoris_Presales_ID = (Integer) noPresales.getValue();
		if(C_Decoris_Presales_ID == null){
			C_Decoris_Presales_ID = 0;
		}
		
		//get status of presales document
		String status = (String) Status.getValue();
		if(status != null){
			if(status.equals(X_C_Decoris_PreSales.STATUS_Completed)){
				FDialog.info(windowNo, null, "", "Proses tidak dapat dilakukan, Status Presales Completed", "Info");
				return;
			}
		}
		ErrorMsg =  validation();
		if(ErrorMsg != ""){
			FDialog.info(windowNo, null, "",ErrorMsg, "Info");
			return;
		}
		
		// cek validate period status
		Date tglCek = tanggalField.getValue();
		SimpleDateFormat dfCek = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String tglCekStr = dfCek.format(tglCek);
		Timestamp dateCek= Timestamp.valueOf(tglCekStr);
		
		if (!MPeriod.isOpen(ctx, dateCek , "SOO",(int) orgSearch.getValue())) {
			FDialog.warn(windowNo,null,"","Transaksi Tidak Dapat Diproses Karena Status Period Closed","Peringatan");
			return;
		}
		
		if(C_Decoris_Presales_ID  > 0){
			preSales =  new X_C_Decoris_PreSales(ctx, C_Decoris_Presales_ID, null);	
		}
		
		if(Status.getValue().equals(X_C_Decoris_PreSales.STATUS_Completed)){
			
			FDialog.info(windowNo, null, "","Presales tidak dapat diproses karena status complete", "Info");
			return;
		}
		
		if(C_Decoris_Presales_ID  == 0){
			
			//Input to PreSales if existing
			preSales =  new X_C_Decoris_PreSales(ctx, 0, null);
			String DeliveryViaRule = "";
			
			if(PickUp.isChecked()){
				DeliveryViaRule = "P";
			}else{
				DeliveryViaRule = "D";
			}
			
			Integer preSales_ID = (Integer) noPresales.getValue();
			if(preSales_ID == null){
				preSales_ID = 0;
			}
			
			preSales.setClientOrg(AD_Client_ID, (int) orgSearch.getValue());
			preSales.setAD_User_ID(Env.getAD_User_ID(ctx));
			preSales.setC_BPartner_ID((int) PelangganSearch.getValue());
//			if(bankAccountSearch.getValue() != null){
//				if(paymentTunai.getValue().compareTo(Env.ZERO)> 0 ||paymentBank.getValue().compareTo(Env.ZERO)> 0 )
//				preSales.setC_BankAccount_ID((int) bankAccountSearch.getValue());
//			}
			preSales.setC_Currency_ID(303);
			//tipe document baru unt presales
			//preSales.setC_DocType_ID(C_DocType_ID);
			
			String sqlDoc = "SELECT C_DocType_ID FROM C_DocType "
					+ " WHERE IsSoTrx = 'Y' " + " AND AD_Client_ID = ?"
					+ " AND DocSubTypeSO = ?";
		
			String docType = MDocType.DOCSUBTYPESO_PrepayOrder;
			int C_DocType_ID = DB.getSQLValue(ctx.toString(),sqlDoc.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });

			
			Date tgl = tanggalField.getValue();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			String tglTostr = df.format(tgl);
			
			Timestamp dateOrd = Timestamp.valueOf(tglTostr);
			
			String decPOsdocType = "SELECT C_DocType_ID "
					+ " FROM C_DocType "
					+ " WHERE AD_Client_ID = ?"
					+ " AND DocBaseType = ? ";
			String docDec = "PRS";
			
			
			int decDoc_ID = DB.getSQLValueEx(null, decPOsdocType, new Object[]{AD_Client_ID,docDec});
			
			
			preSales.setOrderDocType_ID(C_DocType_ID);
			preSales.setC_DocType_ID(decDoc_ID);
			preSales.setDateOrdered(dateOrd);
			preSales.setDeliveryRule("R");
			preSales.setDeliveryViaRule(DeliveryViaRule);
			preSales.setDescription(keteranganTextBox.getValue());
			preSales.setIsPickup(PickUp.isChecked());
			preSales.setIsPrinted(false);
			preSales.setIsTutupKas(TutupKasCheck.isChecked());
//			if(paymentLeasing.getValue().compareTo(Env.ZERO) > 0){
//				preSales.setleaseprovider((String) leasingProviderSearch.getValue());
//			}
			preSales.setSalesRep_ID((int) SalesSearch.getValue());
			preSales.setM_PriceList_ID((int) getIDFromComboBox(DaftarHargaSearch,MPriceList.Table_Name,MPriceList.COLUMNNAME_Name));
			preSales.setM_Warehouse_ID((int) getIDFromComboBox(GudangSearch, MWarehouse.Table_Name,MWarehouse.COLUMNNAME_Name));
			preSales.set_ValueNoCheck("PaymentRule",X_C_Decoris_PreSales.PAYMENTRULE_MixedPOSPayment);
			preSales.setStatus((String) Status.getValue());		
			StringBuilder SQLPayTerm = new StringBuilder();
			SQLPayTerm.append("SELECT C_PaymentTerm_ID ");
			SQLPayTerm.append("FROM C_PaymentTerm ");
			SQLPayTerm.append("WHERE AD_Client_ID = ? ");
			SQLPayTerm.append("AND IsDefault = 'Y' ");
			int C_PaymentTerm_ID = DB.getSQLValue(ctx.toString(),SQLPayTerm.toString(),new Object[] { Env.getAD_Client_ID(ctx)});

			
			//Double kmbli = 0.00;

//			if (kembalianTextBox.getText().isEmpty()) {
//				kmbli = 0.00;
//			} else if (!kembalianTextBox.getText().isEmpty()) {
//				if (AD_Language.toUpperCase().equals("EN_US")) {
//					kmbli = Double.valueOf(kembalianTextBox.getText().replaceAll(",", ""));
//				} else if (AD_Language.toUpperCase().equals("IN_ID")) {
//					String dTtlBlj = kembalianTextBox.getText().replaceAll("\\.", "").replaceAll(",", ".");
//					kmbli = Double.valueOf(dTtlBlj);
//				}
//			}
			//BigDecimal kemblian = BigDecimal.valueOf(kmbli);
			
			preSales.setC_PaymentTerm_ID(C_PaymentTerm_ID);
//			preSales.setJumlahPembayaranTunai(paymentTunai.getValue().subtract(kemblian));
//			preSales.setJumlahPembayaranBank(paymentBank.getValue());
//			preSales.setJumlahPembayaranLeasing(paymentLeasing.getValue());
//			preSales.setJumlahPembayaranHutang(paymentHutang.getValue());
//			preSales.set_CustomColumn("TotalKembalian", kemblian);
//			preSales.set_CustomColumn("TotalTunai", paymentTunai.getValue());
			
//			BigDecimal TotalBayar = paymentTunai.getValue().add(paymentBank.getValue());
//			preSales.setTotalBayar(TotalBayar);
			preSales.saveEx();
			
			
			int lineno = 0;
			Integer M_PriceList_ID = (Integer) getIDFromComboBox(DaftarHargaSearch,MPriceList.Table_Name,MPriceList.COLUMNNAME_Name);
			if(M_PriceList_ID == null){
				M_PriceList_ID = 0;
			}
			
			for (int i = 0 ; i <PreSalesTable.getRowCount() ; i++){
				X_C_Decoris_PreSalesLine preSalesLine = new X_C_Decoris_PreSalesLine(ctx, 0, null);

				KeyNamePair KPProd = (KeyNamePair) PreSalesTable.getValueAt(i, 2);
				KeyNamePair KPLoc = (KeyNamePair) PreSalesTable.getValueAt(i, 3);

				Integer M_Product_ID = KPProd.getKey();
				Integer M_Locator_ID = KPLoc.getKey();
				BigDecimal diskonPersen = (BigDecimal) PreSalesTable.getValueAt(i, 7);
				BigDecimal price = (BigDecimal) PreSalesTable.getValueAt(i, 6);
				BigDecimal qty = (BigDecimal) PreSalesTable.getValueAt(i, 8);
				String serialNo = (String) PreSalesTable.getValueAt(i, 4);
				
				String sqlSerno = "SELECT M_AttributeSetInstance_ID FROM M_AttributeSetInstance WHERE AD_Client_ID = ? AND Serno = '"+ serialNo.toString() + "'";
				int M_AttributeSetInstance_ID = DB.getSQLValue(null,sqlSerno.toString(), new Object[] { AD_Client_ID });

				
				preSalesLine.setClientOrg(AD_Client_ID, preSales.getAD_Org_ID());
				preSalesLine.setC_Decoris_PreSales_ID(preSales.getC_Decoris_PreSales_ID());
				preSalesLine.setM_Product_ID(M_Product_ID);
				
				MProduct prod = new MProduct(ctx, M_Product_ID, null);
				Integer C_UOM_ID = prod.getC_UOM_ID();
				
				int taxCategory = prod.getC_TaxCategory_ID();
				MTaxCategory cat = new MTaxCategory(ctx, taxCategory, null);
				String taxCatName = cat.getName();

				String sqlTax = "SELECT C_Tax_ID FROM C_Tax WHERE AD_Client_ID = ? AND Name = '" + taxCatName + "'";
				int C_Tax_ID = DB.getSQLValueEx(cat.get_TrxName(), sqlTax,new Object[] { AD_Client_ID });
				
				//diskon
				BigDecimal diskonPerItem = (diskonPersen.divide(Env.ONEHUNDRED)).multiply(price);
				BigDecimal totalHarga = price.multiply(qty);
				BigDecimal totalDiskon = diskonPerItem.multiply(qty);
				//BigDecimal totalLine = totalHarga.subtract(totalDiskon);
				BigDecimal totalLine = (BigDecimal) PreSalesTable.getValueAt(i, 9);
				
				preSalesLine.setC_Tax_ID(C_Tax_ID);
				preSalesLine.setC_UOM_ID(C_UOM_ID);
				preSalesLine.setPersentaseDiskon(Integer.valueOf(diskonPersen.setScale(0,BigDecimal.ROUND_HALF_DOWN).toString()));
				preSalesLine.setdiskonline(totalDiskon);
				preSalesLine.setLineNo(lineno+10);
				preSalesLine.setLineNetAmt(totalLine);
				
				if(M_AttributeSetInstance_ID > 0){
					preSalesLine.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
				}
				
				//get pricelist
				Timestamp date = new Timestamp(System.currentTimeMillis());

				String sql = "SELECT plv.M_PriceList_Version_ID "
						+ "FROM M_PriceList_Version plv "
						+ "WHERE plv.AD_Client_ID = ? "
						+ " AND plv.M_PriceList_ID= ? " // 1
						+ " AND plv.ValidFrom <= ? "
						+ "ORDER BY plv.ValidFrom DESC";

				int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, new Object[] {AD_Client_ID, M_PriceList_ID, date });

				String sqlProductPrice = "SELECT PriceList FROM M_ProductPrice WHERE AD_Client_ID = ? AND M_PriceList_Version_ID = ? AND M_Product_ID = ?";
				BigDecimal priceList = DB.getSQLValueBDEx(null,sqlProductPrice, new Object[] { AD_Client_ID,M_PriceList_Version_ID, M_Product_ID });

				//line taxamt
				BigDecimal PPN = calDPP(false, true, M_Product_ID, totalLine);

				preSalesLine.setTaxAmt(PPN);
				preSalesLine.setM_Locator_ID(M_Locator_ID);
				preSalesLine.setPriceList(priceList);
				preSalesLine.setPriceEntered(totalHarga);
				preSalesLine.setQtyEntered(qty);
				preSalesLine.saveEx();
				
				//setLineNetAmt(M_PriceList_ID, M_Product_ID, preSales.getC_Currency_ID(), preSalesLine.getC_Decoris_PreSalesLine_ID(), preSalesLine.getPriceEntered(), qty,diskonPerItem);
			
			}
			
			StringBuilder SQLTotal = new StringBuilder();
			
			
			SQLTotal.append("SELECT SUM(taxamt),SUM(PriceEntered),SUM(DiskonLine),SUM(LineNetAmt), (100/110)*(SUM(PriceEntered)) ");
			SQLTotal.append("FROM C_Decoris_PreSalesLine ");
			SQLTotal.append("WHERE  AD_Client_ID = ? ");
			SQLTotal.append("AND C_Decoris_PreSales_ID = ? ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(SQLTotal.toString(), null);
				pstmt.setInt(1, AD_Client_ID);
				pstmt.setInt(2, preSales.getC_Decoris_PreSales_ID());

				rs = pstmt.executeQuery();
				while (rs.next()) {
					// update after line save				
					preSales.settotal(rs.getBigDecimal(2));
					preSales.setTotalDiskon(rs.getBigDecimal(3));
					preSales.setTotalBelanja(rs.getBigDecimal(4));
					
					BigDecimal DPP = callDBfromTextBox(DPPTextBox);
					BigDecimal taxAmt = callDBfromTextBox(PPNTextBox);
					
					preSales.setdpp(DPP);
					preSales.setTaxAmt(taxAmt);;


					
					preSales.saveEx();
				}

			} catch (SQLException err) {
				log.log(Level.SEVERE, SQLTotal.toString(), err);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			

			noPresales.setValue(preSales.getC_Decoris_PreSales_ID());


		
		}
		
		
		X_C_DecorisPOS decPos = new X_C_DecorisPOS(ctx, 0, null);
		decPos.setClientOrg(AD_Client_ID, preSales.getAD_Org_ID());
		decPos.setC_BPartner_ID( preSales.getC_BPartner_ID());
		
		StringBuilder sqlBPLoc = new StringBuilder();
		sqlBPLoc.append("SELECT C_BPartner_Location_ID ");
		sqlBPLoc.append("FROM C_BPartner_Location ");
		sqlBPLoc.append("WHERE C_BPartner_ID = ? ");
		int C_BPartner_Location_ID = DB.getSQLValueEx(ctx.toString(),sqlBPLoc.toString(), new Object[] {  preSales.getC_BPartner_ID() });

		
		decPos.setC_BPartner_Location_ID(C_BPartner_Location_ID);
		decPos.setDateOrdered(preSales.getDateOrdered());
		decPos.setDescription(preSales.getDescription());
		decPos.setM_PriceList_ID(preSales.getM_PriceList_ID());
		decPos.setDiscountAmt(preSales.getTotalDiskon());
		decPos.setM_Warehouse_ID(preSales.getM_Warehouse_ID());
		decPos.setDeliveryRule(MOrder.DELIVERYRULE_AfterReceipt);
		decPos.setC_PaymentTerm_ID(preSales.getC_PaymentTerm_ID());
		decPos.setPaymentRule(MOrder.PAYMENTRULE_MixedPOSPayment);
		if(CreatedByPOS_ID > 0){
			decPos.setCreatedByPOS_ID(CreatedByPOS_ID);
		}
		decPos.setSalesRep_ID(preSales.getSalesRep_ID());
		decPos.setIsPickup(preSales.isPickup());
		decPos.setdpp(Env.ZERO);
		decPos.setTaxAmt(Env.ZERO);
		decPos.setGrandTotal(preSales.getTotalBelanja());
		decPos.setTotalLines(preSales.getTotalBelanja());
//		decPos.setPayType1(payruleBoxTunai.getValue().toUpperCase());
//		decPos.setPayType2(payruleBoxBank.getValue().toUpperCase());
//		decPos.setPayType3(payruleBoxLeasing.getValue().toUpperCase());
//		decPos.setPayType4(payruleBoxHutang.getValue().toUpperCase());
//		
		String decPOsdocType = "SELECT C_DocType_ID "
				+ " FROM C_DocType "
				+ " WHERE AD_Client_ID = ?"
				+ " AND DocBaseType = ? ";
		String docDec = "POS";
		
		
		int decDoc_ID = DB.getSQLValueEx(null, decPOsdocType, new Object[]{AD_Client_ID,docDec});
		
		
		decPos.setC_DocType_ID(decDoc_ID);
		decPos.setDeliveryViaRule(preSales.getDeliveryViaRule());
		
//		if(paymentLeasing.getValue().compareTo(Env.ZERO)> 0){
//			decPos.set_ValueOfColumnReturningBoolean("IsLeasing", true);
//			
//			if(leasingProviderSearch.getValue().toString().toUpperCase().equals("SPEKTRA")){
//				decPos.set_ValueOfColumnReturningBoolean("IsSpektra", true);
//				
//			}
//
//		}
		decPos.setPembayaran1(preSales.getJumlahPembayaranTunai());
		decPos.setPembayaran2(preSales.getJumlahPembayaranBank());
		decPos.setPembayaran3(preSales.getJumlahPembayaranLeasing());
		decPos.setPembayaran4(preSales.getJumlahPembayaranHutang());
		decPos.setIsPembatalan(false);
		decPos.setIsSOTrx(true);
		decPos.setIsPenjualan(true);
		decPos.setIsPembayaran(false);
		decPos.setIsReceipt(false);
		decPos.saveEx();
		
		preSales.setC_DecorisPOS_ID(decPos.getC_DecorisPOS_ID());
		preSales.saveEx();
		
		StringBuilder SqlLine = new StringBuilder();
		
		SqlLine.append("SELECT C_Decoris_PresalesLine_ID ");
		SqlLine.append("FROM C_Decoris_PresalesLine ");
		SqlLine.append("WHERE AD_Client_ID = ? ");
		SqlLine.append("AND C_Decoris_PreSales_ID = ?");
		
		
		PreparedStatement pstmtLine = null;
		ResultSet rsLine = null;
		try {
			pstmtLine = DB.prepareStatement(SqlLine.toString(), null);
			pstmtLine.setInt(1, AD_Client_ID);
			pstmtLine.setInt(2, preSales.getC_Decoris_PreSales_ID());

			rsLine = pstmtLine.executeQuery();
			while (rsLine.next()) {
				
				X_C_Decoris_PreSalesLine line = new X_C_Decoris_PreSalesLine(ctx, rsLine.getInt(1), null);
				X_C_DecorisPOSLine decPOSLine = new X_C_DecorisPOSLine(ctx, 0,null);

				String sqlLine = "SELECT COALESCE(MAX(Line),0)+10 FROM C_DecorisPOSLine WHERE C_DecorisPOS_ID =?";
				int ii = DB.getSQLValue(decPos.get_TrxName(), sqlLine,decPos.getC_DecorisPOS_ID());

				// DecorisLine
				decPOSLine.setClientOrg(AD_Client_ID, decPos.getAD_Org_ID());
				decPOSLine.setC_DecorisPOS_ID(decPos.getC_DecorisPOS_ID());
				decPOSLine.setM_Locator_ID(line.getM_Locator_ID());
				decPOSLine.setM_Product_ID(line.getM_Product_ID());
				decPOSLine.setC_UOM_ID(line.getC_UOM_ID());
				decPOSLine.setC_Tax_ID(line.getC_Tax_ID());
				decPOSLine.setPriceList(line.getPriceList());
				decPOSLine.setPriceEntered(line.getPriceEntered());
				decPOSLine.setQtyOrdered(line.getQtyEntered());
				decPOSLine.setLineNetAmt(line.getLineNetAmt());
				decPOSLine.set_CustomColumn("C_Decoris_PreSalesLine_ID", line.getC_Decoris_PreSalesLine_ID());;
				decPOSLine.setLine(ii);
				if (line.getM_AttributeSetInstance_ID()> 0) {
					decPOSLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
				}

				decPOSLine.saveEx();
				
				
			}

		} catch (SQLException err) {
			log.log(Level.SEVERE, SqlLine.toString(), err);
		} finally {
			DB.close(rsLine, pstmtLine);
			rsLine = null;
			pstmtLine = null;
		}
		
		
		
		MOrder ord = new MOrder(ctx, 0, null);
		ord.setClientOrg(AD_Client_ID,decPos.getAD_Org_ID());
		ord.setC_BPartner_ID(decPos.getC_BPartner_ID());
		ord.setC_BPartner_Location_ID(decPos.getC_BPartner_Location_ID());
		ord.setPOReference(decPos.getDocumentNo());
		ord.setDescription(decPos.getDescription());
		ord.setDeliveryRule(decPos.getDeliveryRule());
		ord.setDeliveryViaRule(decPos.getDeliveryViaRule());
		ord.setSalesRep_ID(decPos.getSalesRep_ID());
		ord.setC_Currency_ID(303);
		ord.setC_DocType_ID(preSales.getOrderDocType_ID());
		ord.setC_DocTypeTarget_ID(preSales.getOrderDocType_ID());
		ord.setDateOrdered(decPos.getDateOrdered());
		ord.setDateAcct(decPos.getDateOrdered());
		ord.setDatePromised(decPos.getDateOrdered());
		ord.setM_Warehouse_ID(decPos.getM_Warehouse_ID());
		ord.setC_PaymentTerm_ID(decPos.getC_PaymentTerm_ID());
		ord.setM_PriceList_ID(decPos.getM_PriceList_ID());
		ord.setTotalLines(decPos.getTotalLines());
		ord.setGrandTotal(decPos.getGrandTotal());
		ord.setPaymentRule(decPos.getPaymentRule());
		if(decPos.getCreatedByPOS_ID() > 0){
			ord.set_CustomColumn("CreatedByPOS_ID", decPos.getCreatedByPOS_ID());
		}
		ord.setIsSelfService(true);
		ord.setIsSOTrx(decPos.isSOTrx());
		ord.setPOReference(decPos.getDocumentNo());
		ord.saveEx();
		
		MDecorisPOS dec = new MDecorisPOS(ctx, decPos.getC_DecorisPOS_ID(),null);

	
		StringBuilder SqlPOSLine = new StringBuilder();
		
		SqlPOSLine.append("SELECT C_DecorisPOSLine_ID ");
		SqlPOSLine.append("FROM C_DecorisPOSLine ");
		SqlPOSLine.append("WHERE AD_Client_ID = ? ");
		SqlPOSLine.append("AND C_DecorisPOS_ID = ?");
		
		
		PreparedStatement pstmtPOSLine = null;
		ResultSet rsPOSLine = null;
		try {
			pstmtPOSLine = DB.prepareStatement(SqlPOSLine.toString(), null);
			pstmtPOSLine.setInt(1, AD_Client_ID);
			pstmtPOSLine.setInt(2, dec.getC_DecorisPOS_ID());

			rsPOSLine = pstmtPOSLine.executeQuery();
			while (rsPOSLine.next()) {
				
				X_C_DecorisPOSLine line = new X_C_DecorisPOSLine(ctx,rsPOSLine.getInt(1),null);

				String sqlLine = "SELECT COALESCE(MAX(Line),0)+10 FROM C_DecorisPOSLine WHERE C_DecorisPOS_ID =?";
				int ii = DB.getSQLValue(decPos.get_TrxName(), sqlLine,decPos.getC_DecorisPOS_ID());

				MOrderLine ordLine = new MOrderLine(ctx, 0, null);

				// ordLine
				ordLine.setClientOrg(AD_Client_ID, ord.getAD_Org_ID());
				ordLine.setC_Order_ID(ord.getC_Order_ID());
				ordLine.setM_Product_ID(line.getM_Product_ID());
				ordLine.setC_UOM_ID(line.getC_UOM_ID());
				ordLine.setQtyEntered(line.getQtyOrdered());
				ordLine.setQtyOrdered(line.getQtyOrdered());
				ordLine.setPriceList(line.getPriceList());
				ordLine.setPriceEntered(line.getPriceEntered());
				ordLine.setPriceActual(line.getPriceEntered());
				ordLine.setC_Tax_ID(line.getC_Tax_ID());
				ordLine.setLineNetAmt(line.getLineNetAmt());
				ordLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
				ordLine.setLine(ii);
				X_C_Decoris_PreSalesLine preLine = new X_C_Decoris_PreSalesLine(ctx, line.get_ValueAsInt("C_Decoris_PreSalesLine_ID"), null);
						
				ordLine.setDiscount(preLine.getdiskonline());
				ordLine.saveEx();

				line.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
				line.saveEx();
				
			}

		} catch (SQLException err) {
			log.log(Level.SEVERE, SqlPOSLine.toString(), err);
		} finally {
			DB.close(rsPOSLine, pstmtPOSLine);
			rsPOSLine = null;
			pstmtPOSLine = null;
		}
		

		mapPospay = new HashMap<String, BigDecimal>();
		if(preSales.getJumlahPembayaranTunai().compareTo(Env.ZERO) > 0 ){
			
			mapPospay.put("TUNAI", preSales.getJumlahPembayaranTunai());
		}
		if(preSales.getJumlahPembayaranBank().compareTo(Env.ZERO) > 0 ){
			
			mapPospay.put("BANK", preSales.getJumlahPembayaranBank());
		}
		if(preSales.getJumlahPembayaranLeasing().compareTo(Env.ZERO) > 0 ){
			
			mapPospay.put("LEASING", preSales.getJumlahPembayaranLeasing());
		}
		if(preSales.getJumlahPembayaranHutang().compareTo(Env.ZERO) > 0 ){
			
			mapPospay.put("HUTANG", preSales.getJumlahPembayaranHutang());
		}

		
//		for (String key : mapPospay.keySet()) {

			//String tipebayar = key.toUpperCase();
			//BigDecimal payAmt = mapPospay.get(key);
			//String whereClause = "";
			//String tenderName = "";
			//int C_BankAcct_ID = 0;
			//String leaseProv = preSales.getleaseprovider();

//			if (tipebayar.equals("TUNAI") || tipebayar.equals("LEASING")|| tipebayar.equals("BANK")) {
//
//				if (tipebayar.equals("TUNAI") || tipebayar.equals("BANK")) {
//					String sqlBank = "";
//					tenderName = MPayment.TENDERTYPE_Cash;
//					whereClause = "Cash";
//
//					if (tipebayar.equals("TUNAI")) {
//
//						sqlBank = "SELECT C_BankAccount_ID FROM C_POS WHERE CreatedByPOS_ID = ? AND AD_Client_ID = ?";
//						C_BankAcct_ID = DB.getSQLValueEx(ctx.toString(),sqlBank.toString(), new Object[] {CreatedByPOS_ID, AD_Client_ID });
//
//					} else if (tipebayar.equals("BANK")) {
//						Integer b_acct_id = (Integer) bankAccountSearch.getValue();
//						if (b_acct_id == null) {
//							b_acct_id = 0;
//						}
//						C_BankAcct_ID = b_acct_id;
//					}
//
//				} else if (tipebayar.equals("LEASING")) {
//
//					tenderName = MPayment.TENDERTYPE_Lease;
//					whereClause = "Leasing";
//					String sqlBank = "SELECT C_BankAccount_ID FROM C_BankAccount WHERE leaseprovider = '"
//							+ leaseProv
//							+ "'"
//							+ " AND AD_Client_ID = "
//							+ Env.getAD_Client_ID(ctx);
//					C_BankAcct_ID = DB.getSQLValueEx(ctx.toString(),sqlBank.toString());
//
//				}
//
//				String sqlTender = "SELECT C_POSTenderType_ID FROM C_POSTenderType WHERE name = '"+ whereClause + "'";
//				int C_POSTenderType_ID = DB.getSQLValueEx(ctx.toString(),sqlTender.toString());
//				createPOSPayment(preSales.getC_Decoris_PreSales_ID(), ord.getC_Order_ID(),C_POSTenderType_ID,tenderName,payAmt,C_BankAcct_ID);
//				
//
//			}
//		}
		
		ord.setDocAction(MOrder.DOCACTION_Complete);
		ord.processIt(MOrder.ACTION_Complete);
		ord.saveEx();
		
		preSales.setStatus(X_C_Decoris_PreSales.STATUS_Completed);
		
		int C_Pay_ID = checkPaymentRelated(ord.getC_Order_ID());
		int Ship_ID = checkShipmentRelated(ord.getC_Order_ID());
		inout_ID = Ship_ID;
		
		if(C_Pay_ID > 0){
			
			MPayment pay = new MPayment(ctx, C_Pay_ID, null);
			noPembayaran.setText(pay.getDocumentNo());
		}
		
		//feedback set
//		preSales.setC_Decoris_CloseCash_ID(C_Decoris_CloseCash_ID);
		preSales.setC_Payment_ID(C_Pay_ID);
		preSales.setC_Order_ID(ord.getC_Order_ID());
//feedback after process
//		preSalesLine.setC_InvoiceLine_ID(C_InvoiceLine_ID);
//		preSalesLine.setC_OrderLine_ID(C_OrderLine_ID);

		preSales.saveEx();
		
		decPos.setC_Order_ID(ord.getC_Order_ID());
		decPos.setC_Payment_ID(C_Pay_ID);
		decPos.saveEx();
		
		//pop up confirmation alamat
		try {
			
			Grid inputGrid = GridFactory.newGridLayout();
			Panel paraPanel = new Panel();
			Rows rows = null;
			Row row = null;
			
			final Datebox tglKirimInput = new Datebox();
			final Textbox penerimaInput = new Textbox();
			final Textbox alamatPenerimaInput = new Textbox();
			final Textbox noTlpInput = new Textbox();
			final Checkbox bedaAlamatCheck = new Checkbox();
			
			
			String title = "Korfirmasi Alamat";
			Label LabelBedaAlamat = new Label("Alamat Kirim Berbeda? : ");
			Label LabelTgl = new Label("Tgl. Kirim : ");
			Label LabelPenerima = new Label("Penerima : ");
			Label LabelAlamat = new Label("Alamat : ");
			Label LabelNoTlp = new Label("No. Tlp : ");
			
			
			// Date
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);				
			
			final Window w = new Window();
			w.setTitle(title);
			
			Borderlayout mainbBorder = new Borderlayout();
			w.appendChild(mainbBorder);
			w.setWidth("350px");
			w.setHeight("275px");
			w.setBorder("normal");
			w.setPage(this.getForm().getPage());
			w.setClosable(true);
			w.setSizable(true);
			
			mainbBorder.setWidth("100%");
			mainbBorder.setHeight("100%");
			String grid = "border: 1px solid #C0C0C0; border-radius:5px; vertical-align:bottom;overflow:auto;";
			paraPanel.appendChild(inputGrid);

			South south = new South();
			south.setStyle(grid);
			mainbBorder.appendChild(south);
			south.appendChild(paraPanel);
			south.setSplittable(false);
			
			inputGrid.setWidth("100%");
			inputGrid.setStyle("Height:100%;align:left");
			inputGrid.setHflex("min");
			
			rows = inputGrid.newRows();
		
			row = rows.newRow();
			row.appendCellChild(LabelBedaAlamat.rightAlign());
			row.appendCellChild(bedaAlamatCheck);
			bedaAlamatCheck.setHflex("true");
			bedaAlamatCheck.addEventListener(Events.ON_CHECK, new EventListener<Event>() {

				@Override
				public void onEvent(Event ev) throws Exception {
					
					boolean  isSameAlamat = bedaAlamatCheck.isChecked();

					if(isSameAlamat){
						tglKirimInput.setReadonly(false);;
						penerimaInput.setReadonly(false);
						alamatPenerimaInput.setReadonly(false);
						noTlpInput.setReadonly(false);
					}else if(!isSameAlamat){
						tglKirimInput.setReadonly(true);
						penerimaInput.setReadonly(true);
						alamatPenerimaInput.setReadonly(true);
						noTlpInput.setReadonly(true);
					}
				}
			});
			
			row = rows.newRow();
			row.appendCellChild(LabelTgl.rightAlign());
			row.appendCellChild(tglKirimInput,1);
			tglKirimInput.setHflex("true");
			tglKirimInput.setReadonly(true);;
			tglKirimInput.setFormat("dd/MM/yyyy");
			
			cal.add(Calendar.DATE, 1);
			tglKirimInput.setValue(cal.getTime());

			
			row = rows.newRow();
			row.appendCellChild(LabelPenerima.rightAlign());
			row.appendCellChild(penerimaInput);
			penerimaInput.setHflex("true");
			penerimaInput.setReadonly(true);

			row = rows.newRow();
			row.appendCellChild(LabelAlamat.rightAlign());
			row.appendCellChild(alamatPenerimaInput);
			alamatPenerimaInput.setHflex("true");
			alamatPenerimaInput.setRows(4);
			alamatPenerimaInput.setReadonly(true);

			row = rows.newRow();
			row.appendCellChild(LabelNoTlp.rightAlign());
			row.appendCellChild(noTlpInput);
			noTlpInput.setHflex("true");
			noTlpInput.setReadonly(true);

			
			Vbox vbox = new Vbox();
			w.appendChild(vbox);
			vbox.appendChild(new Separator());
			final ConfirmPanel panel = new ConfirmPanel(true, false, false, false, false, false, false);
			vbox.appendChild(panel);
			panel.addActionListener(Events.ON_CLICK, new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					if (event.getTarget() == panel.getButton(ConfirmPanel.A_CANCEL)) {
						FDialog.info(windowNo, null, "", "Presales Berhasil Di Proses", "Info");
						w.dispose();
					}
					else if(event.getTarget() == panel.getButton(ConfirmPanel.A_OK)){
						Timestamp tglKirim = null;
						String penerima = "";
						String alamatPenerima = "";
						String noTlp = "";
						boolean isBedaAlamat = bedaAlamatCheck.isChecked();
					
						String msgValidation = "";
						
						
						penerima = penerimaInput.getValue();
						alamatPenerima = penerimaInput.getValue();
						noTlp = noTlpInput.getValue();
						
						int M_InOut_ID = inout_ID;
						if(M_InOut_ID <= 0){
							w.onClose();
							w.dispose();
						}
						
						if(tglKirimInput.getValue() != null ){
							Date tgl = tglKirimInput.getValue();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
							String tglTostr = df.format(tgl);
							Timestamp dateShip = Timestamp.valueOf(tglTostr);
							tglKirim = dateShip;
						}
						
						
						MInOut ino = new MInOut(ctx, M_InOut_ID, null);

						//validation
						if(isBedaAlamat){
							
							if(tglKirim == null){
								msgValidation = msgValidation + "\n" +"Tanggal Kirim Belum Di Isi";	
							}
			
							if(penerima == null || penerima.isEmpty() || penerima== ""){
								msgValidation = msgValidation + "\n" + "Nama Penerima Belum Di Isi";	
							}
							
							if(alamatPenerima == null || alamatPenerima.isEmpty() || alamatPenerima== ""){
								msgValidation = msgValidation + "\n" + "Alamat Pengiriman Belum Di Isi";	
							}
							
							if(noTlp == null || noTlp.isEmpty() || noTlp== ""){
								msgValidation = msgValidation + "\n" + "No Tlp Penerima Belum Di Isi";	
							}
							
							if(msgValidation != ""){
								FDialog.info(windowNo, null, "", msgValidation, "Info");
								return;
							}		
							
							ino.setMovementDate(tglKirim);
							ino.set_CustomColumn("Penerima", penerima);
							ino.set_CustomColumn("AlamatPenerima", alamatPenerima);
							ino.set_CustomColumn("NoTlpPenerima", noTlp);
							ino.saveEx();
							
						}
						
						FDialog.info(windowNo, null, "", "Presales Berhasil Di Proses", "Info");
						
					}
					
					w.onClose();
					
				}
				
			});
			
			w.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					w.dispose();
				}
			});
		w.doHighlighted();		
			
		} catch (Exception err) {

			log.log(Level.SEVERE, this.getClass().getCanonicalName()
					+ ".valueChange - ERROR: " + err.getMessage(), err);
		}
		//end confirmation alamat
		
		
		//process.setEnabled(false);
		noPresales.setReadWrite(false);
		SalesSearch.setReadWrite(false);
		DaftarHargaSearch.setReadonly(true);
		DaftarHargaSearch.setEnabled(false);
		TutupKasCheck.setEnabled(false);
		tanggalField.setReadonly(true);
		PickUp.setEnabled(false);
		PelangganSearch.setReadWrite(false);
		GudangSearch.setReadonly(true);
		GudangSearch.setEnabled(false);
		Lokasi.setReadonly(true);
		Lokasi.setEnabled(false);
		ProductSearch.setReadWrite(false);
		IMEIList.setEnabled(false);
		pelangganBaru.setEnabled(false);
		PreSalesTable.setEnabled(false);
//		paymentTunai.setReadonly(true);
//		paymentBank.setReadonly(true);
//		paymentLeasing.setReadonly(true);
//		paymentHutang.setReadonly(true);
//		leasingProviderSearch.setReadWrite(false);
//		bankAccountSearch.setReadWrite(false);
		keteranganTextBox.setReadonly(true);
		deleteItem.setEnabled(false);
		
		batal.setEnabled(true);
		notaKecil.setEnabled(true);
		notaBesar.setEnabled(true);
		
		Status.setValue(preSales.getStatus());
		
	}
	
	public void buatBaru(){
		noPresales.setReadWrite(true);
		noPembayaran.setReadonly(true);
		TokoSearch.setReadWrite(false);
		SalesSearch.setReadWrite(true);
		DaftarHargaSearch.setEnabled(true);
		DaftarHargaSearch.setReadonly(true);
		Status.setReadWrite(false);
		TutupKasCheck.setEnabled(false);
		tanggalField.setReadonly(false);
		tanggalField.setEnabled(true);
		PickUp.setEnabled(true);
		deleteItem.setEnabled(true);
		PelangganSearch.setReadWrite(true);
		GudangSearch.setReadonly(true);
		GudangSearch.setEnabled(true);
		Lokasi.setReadonly(true);
		Lokasi.setEnabled(false);
		ProductSearch.setReadWrite(false);
		IMEIList.setEnabled(true);
		pelangganBaru.setEnabled(true);
		orgSearch.setReadWrite(true);
		keteranganTextBox.setReadonly(false);
		
//		leasingProviderSearch.setReadWrite(true);
//		bankAccountSearch.setReadWrite(true);
//		buatBaru.setEnabled(true);
//		process.setEnabled(true);
		batal.setEnabled(false);
		notaKecil.setEnabled(false);
		notaBesar.setEnabled(false);
//		paymentTunai.setReadonly(false);
//		paymentBank.setReadonly(false);
//		paymentLeasing.setReadonly(false);
//		paymentHutang.setReadonly(false);

		noPembayaran.setValue(null);
		SalesSearch.setValue(null);
		DaftarHargaSearch.setValue(null);
		Status.setValue("IP");
		tanggalField.setValue(null);
		PickUp.setChecked(true);
		PelangganSearch.setValue(null);
		GudangSearch.setValue(null);
		Lokasi.setValue(null);
		ProductSearch.setValue(null);
		IMEIList.setValue(null);
		keteranganTextBox.setValue(null);
//		paymentTunai.setValue(Env.ZERO);
//		paymentBank.setValue(Env.ZERO);
//		paymentLeasing.setValue(Env.ZERO);
//		paymentHutang.setValue(Env.ZERO);
//		leasingProviderSearch.setValue(null);
//		bankAccountSearch.setValue(null);
		totalBayarTunaiTextBox.setText("0.00");
		totalBelanjaTextBox.setText("0.00");
		totalBrutoTextBox.setText("0.00");
		TotalTextBox1.setText("0.00");
		diskonTextBox.setText("0.00");
		kembalianTextBox.setText("0.00");
		kembaliTextBox.setText("0.00");
		DPPTextBox.setText("0.00");
		PPNTextBox.setText("0.00");
		totalBayarTextBox.setText("0.00");
		
		ArrayList<KeyNamePair> listPL = loadPriceList();
		DaftarHargaSearch.removeAllItems();
		for (KeyNamePair priceList : listPL){
			DaftarHargaSearch.appendItem(priceList.getName());
		}
		DaftarHargaSearch.setSelectedIndex(0);
		
		
		if (PreSalesTable.getRowCount() > 0) {
			for (int i = 0; i < PreSalesTable.getRowCount(); i++) {
				
				deletedata(0,PreSalesTable);

			}
			PreSalesTable.clear();
			PreSalesTable.setRowCount(0);
		}
		
		noPresales.setValue(null);
		
	}
	
	private void deleteDetail(){
		
		int rowCount = 0;
		for (int i = 0; i < PreSalesTable.getRowCount(); i++) {
			boolean isSelect = (boolean) PreSalesTable.getValueAt(i, 0);

			if (isSelect) {
				rowCount++;
			}

		}
		
		for (int i = 0; i < rowCount; i++) {

			for (int j = 0; j < PreSalesTable.getRowCount(); j++) {
				boolean isSelect = (boolean) PreSalesTable.getValueAt(j, 0);
				if (isSelect) {
					deletedata(j,PreSalesTable);
					// salesTable.clear();

					Vector<String> columnNames = getOISColumnNames();

					ListModelTable modelP = new ListModelTable(Productdata);

					modelP.addTableModelListener(this);

					PreSalesTable.setData(modelP, columnNames);

					configureMiniTable(PreSalesTable);
					
					
					reCalculate(PreSalesTable);
					calculate();
	
					//calculateDPPExistingPresales();
				}
			}
		}
		
		
		
		for(int i = 0 ; i < PreSalesTable.getRowCount(); i++){
			
			PreSalesTable.setValueAt(i+1, i, 1);
			
		}
		
	}
	
	private void batal(){
		
		Integer presales_ID = (Integer) noPresales.getValue();
		if(presales_ID == null){
			presales_ID = 0 ;
		}
		
		if(presales_ID == 0){
			FDialog.info(windowNo, null, "", "Tidak ada dokumen presales untuk dibatalkan", "Info");
			return;
		}
		
		if(Status.getValue().equals("CL")){
			FDialog.info(windowNo, null, "", "Status Presales yang anda inputkan sudah Close", "Info");
			noPresales.setValue(null);
			return;
		}
			
		if(presales_ID > 0){
			
			X_C_Decoris_PreSales pre = new X_C_Decoris_PreSales(ctx, presales_ID, null);
			
			if(Status.getValue().equals("CO")){				
				pre.setStatus("CL");
				pre.saveEx();
				
			}else if (Status.getValue().equals("IP")){				
				pre.setStatus("CL");
				pre.saveEx();
				
			}
			
			FDialog.info(windowNo, null, "", "Dokumen Berhasil Dibatalkan", "Info");
			Status.setValue(pre.getStatus());
			
			//process.setEnabled(false);
			batal.setEnabled(false);
			notaKecil.setEnabled(false);
			notaBesar.setEnabled(false);
			
		}
			
		
//		try{
//		Grid inputGrid = GridFactory.newGridLayout();
//		Panel paraPanel = new Panel();
//		Rows rows = null;
//		Row row = null;
//		
//		final Checkbox batalTransaksi = new Checkbox();
//		final Checkbox updatePembayaran = new Checkbox();
//
//		String title = "Konfirmasi Tipe Pembatalan";
//		Label batalTrxLabel = new Label("Pembatalan Transaksi");
//		Label updatePembayaranLabel = new Label("Update Pembayaran");
//
//		
//		final Window w = new Window();
//		w.setTitle(title);
//
//		Borderlayout mainbBorder = new Borderlayout();
//		w.appendChild(mainbBorder);
//		w.setWidth("250px");
//		w.setHeight("175px");
//		w.setBorder("normal");
//		w.setPage(this.getForm().getPage());
//		w.setClosable(true);
//		w.setSizable(true);
//		
//		mainbBorder.setWidth("100%");
//		mainbBorder.setHeight("100%");
//		String grid = "border: 1px solid #C0C0C0; border-radius:5px; vertical-align:bottom;overflow:auto;";
//		paraPanel.appendChild(inputGrid);
//		
//		South south = new South();
//		south.setStyle(grid);
//		mainbBorder.appendChild(south);
//		south.appendChild(paraPanel);
//		south.setSplittable(false);
//		
//		inputGrid.setWidth("100%");
//		inputGrid.setStyle("Height:100%;align:left");
//		inputGrid.setHflex("min");
//		
//		rows = inputGrid.newRows();
//	
//		row = rows.newRow();
//		row.appendCellChild(batalTrxLabel.rightAlign(),1);
//		row.appendCellChild(batalTransaksi, 1);
//		batalTransaksi.addEventListener(Events.ON_CHECK, new EventListener<Event>() {
//
//			@Override
//			public void onEvent(Event ev) throws Exception {
//				
//				if(updatePembayaran.isChecked()){
//					updatePembayaran.setChecked(false);
//				}					
//			}
//		});
//		
//		row = rows.newRow();
//		row.appendCellChild(updatePembayaranLabel.rightAlign(),1);
//		row.appendCellChild(updatePembayaran, 1);
//		updatePembayaran.addEventListener(Events.ON_CHECK, new EventListener<Event>() {
//
//			@Override
//			public void onEvent(Event ev) throws Exception {
//				
//				if(batalTransaksi.isChecked()){
//					batalTransaksi.setChecked(false);
//				}					
//			}
//		});
//		
//		
//		Vbox vbox = new Vbox();
//		w.appendChild(vbox);
//		vbox.appendChild(new Separator());
//		final ConfirmPanel panel = new ConfirmPanel(true, false, false, false, false, false, false);
//		vbox.appendChild(panel);
//		panel.addActionListener(Events.ON_CLICK, new EventListener<Event>() {
//
//			@Override
//			public void onEvent(Event event) throws Exception {
//				if (event.getTarget() == panel.getButton(ConfirmPanel.A_CANCEL)) {
//					w.dispose();
//				}
//				else if(event.getTarget() == panel.getButton(ConfirmPanel.A_OK)){
//							
//					// TODO Auto-generated method stub
//					
//					if(updatePembayaran.isChecked()){
//						
//						Integer no_Presales = (Integer) noPresales.getValue();
//						if(no_Presales == null){
//							no_Presales = 0;
//						}
//						
//						if(no_Presales > 0){
//							
//							X_C_Decoris_PreSales pre = new X_C_Decoris_PreSales(ctx, no_Presales, null);
//							int C_Order_ID = pre.getC_Order_ID();
//							
//							StringBuilder SQLPosPay = new StringBuilder();
//							SQLPosPay.append("SELECT C_POSPayment_ID ");
//							SQLPosPay.append("FROM C_POSPayment ");
//							SQLPosPay.append("WHERE AD_Client_ID = ? ");
//							SQLPosPay.append("AND C_Order_ID = ? ");
//							
//							PreparedStatement pstmtPOSPay = null;
//							ResultSet rsPOSPay = null;
//							try {
//								pstmtPOSPay = DB.prepareStatement(SQLPosPay.toString(), null);
//								pstmtPOSPay.setInt(1, AD_Client_ID);
//								pstmtPOSPay.setInt(2, C_Order_ID);
//
//								rsPOSPay = pstmtPOSPay.executeQuery();
//								
//								while (rsPOSPay.next()) {
//									X_C_POSPayment pospay = new X_C_POSPayment(ctx, rsPOSPay.getInt(1), null);
//									int C_Pay_ID = pospay.getC_Payment_ID();
//									
//									if(C_Pay_ID > 0){
//										MPayment pay = new MPayment(ctx, C_Pay_ID, null);
//										
//										pay.processIt(MPayment.ACTION_Reverse_Correct);
//										pay.saveEx();
//									}
//								}
//
//							} catch (SQLException err) {
//								log.log(Level.SEVERE, SQLPosPay.toString(), err);
//							} finally {
//								DB.close(rsPOSPay, pstmtPOSPay);
//								rsPOSPay = null;
//								pstmtPOSPay = null;
//							}
//							
//						}
//						
//					}else if(batalTransaksi.isChecked()){
//						
//						
//						
//					}
//					
//				}
//				
//				w.onClose();
//				
//			}
//			
//		});
//		w.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>() {
//
//			@Override
//			public void onEvent(Event event) throws Exception {
//				w.dispose();
//			}
//		});
//		w.doHighlighted();	
//		
//		} catch (Exception err) {
//
//			log.log(Level.SEVERE, this.getClass().getCanonicalName()
//					+ ".valueChange - ERROR: " + err.getMessage(), e);
//		}
		
	}
	
	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
//	private boolean paymentAlgoritm(BigDecimal FirstPay, BigDecimal Secondpay, BigDecimal ThirdPay,BigDecimal TrigerPay){
//		
//		Double tBlanja = 0.00;
//		boolean isPass = true; 
//
//		if (totalBelanjaTextBox.getText().isEmpty()) {
//			tBlanja = 0.00;
//		} else if (!totalBelanjaTextBox.getText().isEmpty()) {
//			if (AD_Language.toUpperCase().equals("EN_US")) {
//				tBlanja = Double.valueOf(totalBelanjaTextBox.getText().replaceAll(",", ""));
//			} else if (AD_Language.toUpperCase().equals("IN_ID")) {
//				String dblanja = totalBelanjaTextBox.getText().replaceAll("\\.", "").replaceAll(",", ".");
//				tBlanja = Double.valueOf(dblanja);
//			}
//		}
//		
//		BigDecimal totalBelanja = BigDecimal.valueOf(tBlanja);
//		BigDecimal totalByar = FirstPay.add(Secondpay).add(ThirdPay);
//		
//		BigDecimal kurangBayar = totalBelanja.subtract(totalByar);
//		
//		if(TrigerPay.compareTo(kurangBayar)> 0){
//			
//			isPass = false;
//			
//		}
//		
//		return isPass;
		
//	}
	
	
	
	public void calculate() {

		final double div = 0.90909091;
		BigDecimal divider = BigDecimal.valueOf(div);

		postotalPrice = totalPrices.setScale(2, RoundingMode.HALF_UP);
		posnilaiGrandTotal = postotalPrice.setScale(2, RoundingMode.HALF_UP);
		postotalDiskon = totalDiskons.setScale(2, RoundingMode.HALF_DOWN);
		postotalBeforeDiscount = totalBeforeDiscounts.setScale(2,
				RoundingMode.HALF_UP);

		
		diskonTextBox.setText(format.format(postotalDiskon));
		totalBelanjaTextBox.setText(format.format(posnilaiGrandTotal));
		totalBrutoTextBox.setText(format.format(postotalBeforeDiscount));
		TotalTextBox1.setText(format.format(posnilaiGrandTotal));
		
		
		posnilaiDpp = (posnilaiGrandTotal.multiply(divider)).setScale(2,
				RoundingMode.HALF_DOWN);
		posnilaiPajak = (posnilaiGrandTotal.subtract(posnilaiDpp)).setScale(2,
				RoundingMode.HALF_DOWN);

		DPPTextBox.setText(format.format(posnilaiDpp));
		PPNTextBox.setText(format.format(posnilaiPajak));


	}
	
	
}
