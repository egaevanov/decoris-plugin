package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.util.ProcessUtil;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.NumberBox;
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
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCity;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.MProcess;
import org.compiere.model.MProduct;
import org.compiere.model.MRegion;
import org.compiere.model.MTaxCategory;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.decoris.model.MDecorisPOS;
import org.decoris.model.X_C_DecorisPOSLine;
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

public class WPosMainWindow extends PosMainWindow implements IFormController,
		EventListener<Event>, WTableModelListener, ValueChangeListener {

	// CustomForm
	private CustomForm form = new CustomForm();

	// BorderLayout
	private Borderlayout infoLayout = new Borderlayout();
	private Borderlayout mainLayout = new Borderlayout();
	private Borderlayout salesLayout = new Borderlayout();
	
	// Panel
	private Panel salesPanel = new Panel();
	private Panel southPanel = new Panel();
	private Panel parameterPanel = new Panel();

	// Grid
	private Grid salesGrid = GridFactory.newGridLayout();
	private Grid parameterGrid = GridFactory.newGridLayout();


	protected BigDecimal totalPrice = Env.ZERO;
	protected BigDecimal totalDiskon = Env.ZERO;
	protected BigDecimal totalBeforeDiscount = Env.ZERO;

	private String[] m_paymentRule1 = new String[] { "Tunai", "Hutang", "Bank","Kartu Kredit", "Leasing" };
	private String[] m_paymentRule2 = new String[] { "", "Tunai", "Hutang","Bank", "Kartu Kredit", "Leasing" };
	private String[] m_paymentRule3 = new String[] { "", "Tunai", "Hutang","Bank", "Kartu Kredit", "Leasing" };
	private String[] docAct = new String[] { "Complete", "Prepare" };

	private Map<String, BigDecimal> mapPospay;

	// private List<Map<String, BigDecimal>> listPospay;

	private BigDecimal nilaiDpp = Env.ZERO;
	private BigDecimal nilaiPajak = Env.ZERO;
	private BigDecimal nilaiBayar1 = Env.ZERO;
	private BigDecimal nilaiBayar2 = Env.ZERO;
	private BigDecimal nilaiBayar3 = Env.ZERO;

	private BigDecimal nilaiGrandTotal = Env.ZERO;

	private Properties ctx = Env.getCtx();

	private int M_Pricelist_ID = 0;
	private int AD_User_ID = 0;
	private int AD_Client_ID = Env.getAD_Client_ID(ctx);
	private int C_PaymentTerm_ID = 0;
	private int CreatedByPOS_ID = 0;
	private int C_Currency_ID = 303;
	private int windowNo = form.getWindowNo();
	private int C_OrderPrint_ID = 0;
	private int C_DecorisPOSPrint_ID = 0;
	private String AD_Language = Env.getAD_Language(ctx);

	private Integer rowIndex = null;
	private Integer productID = 0;
	private Integer bpartnerId = 0;
	private Integer imeiIndex = null;

	private String m_docAction = "";

	private boolean isBackDate = false;
	private boolean isThermalPrint = false;
	private boolean isForceLimitPrice = false;
	private boolean isCompleteMultiLocator = false;
	private boolean isManualDocumentNo = false;

	// Button
	private Button plusButton = new Button();
	private Button plusButton2 = new Button();
	private Button printButton = new Button();
	private Button clearButton = new Button();
	private Button deleteButton = new Button();
	private Button processButton = new Button();
	private Button newOrderButton = new Button();
	private Button addBusinessPartner = new Button();
	private Button printThermalButton = new Button();
	private Button printSuratJalan = new Button();
	
	
	// TableLine
	private WListbox salesTable = ListboxFactory.newDataTable();

	// BPartner
	private Label bPartnerLabel = new Label("Pelanggan :");
	private WSearchEditor bpartnerSearch = null;

	// Warehouse
	private Label warehouseLabel = new Label("Gudang :");
	private WTableDirEditor warehouseSearch = null;

	// Locator
	private Label locatorLabel = new Label("Lokasi :");
	private WTableDirEditor locatorSearch = null;

	// Product
	private Label productLabel = new Label("Produk :");
	private WSearchEditor productSearch = null;

	// Org
	private Label orgLabel = new Label("Toko :");
	private WTableDirEditor org;

	// Leasing provider
	private Label leasingProviderLabel = new Label("Leasing :");
	private WTableDirEditor leasingProviderSearch = null;

	// DateOrdered
	private Label dateLabel = new Label("Tanggal :");
	private WDateEditor dateField = new WDateEditor();

	// DateOrdered
	private Label dateDOLabel = new Label("Tanggal Pengiriman :");
	private WDateEditor dateDOField = new WDateEditor();

	// SalesRep
	private Label salesLabel = new Label("Sales :");
	private WTableDirEditor salesSearch = null;

	// IMei
	private Label imeiLabel = new Label("IMEI :");
	// private Listbox imeiList = ListboxFactory.newDropdownListbox();
	private Combobox imeiList = new Combobox();
	// IMei
	private Label bankAccountLabel = new Label("Akun Bank :");
	private WTableDirEditor bankAccountSearch = null;

	// NoNota
	private Label noNotaLabel = new Label("No. Nota :");
	private Textbox noNota = new Textbox();

	// TotalLines
	private Label totalLabel = new Label("Total :");
	private Textbox total = new Textbox();

	// TotalDiskon
	private Label diskonLabel = new Label("Diskon :");
	private Textbox diskon = new Textbox();

	// GrandTotal
	private Label grandtotalLabel = new Label("Grand Total :");
	private Textbox grandtotal = new Textbox();

	// Dpp
	private Label dppLabel = new Label("DPP :");
	private Textbox dpp = new Textbox();

	// Pajak
	private Label pajakLabel = new Label("Pajak :");
	private Textbox pajak = new Textbox();

	// Description
	private Label descriptionLabel = new Label("Keterangan :");
	private Textbox description = new Textbox();

	// SisaPembayaran
	private Label sisaPembayaranLabel = new Label("Sisa Pembayaran :");
	private Textbox sisaPembayaran = new Textbox();

	// PaymentRule1
	private Listbox payruleList1 = ListboxFactory.newDropdownListbox(m_paymentRule1);
	private Textbox paymentRule1 = new Textbox();

	// PaymentRule2
	private Listbox payruleList2 = ListboxFactory.newDropdownListbox(m_paymentRule2);
	private Textbox paymentRule2 = new Textbox();

	// PaymentRule2
	private Listbox payruleList3 = ListboxFactory.newDropdownListbox(m_paymentRule3);
	private Textbox paymentRule3 = new Textbox();

	// PriceList
	private Label priceListLabel = new Label("Daftar Harga :");
	private Listbox priceList = ListboxFactory.newDropdownListbox();

	// DocAction
	private Label docActionLabel = new Label("Aksi Dokumen :");
	private Listbox docActionList = ListboxFactory.newDropdownListbox(docAct);
	
	// Formatter
	private DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);

	// Pickup
	private Checkbox deliveryCheck = new Checkbox();
 
	
	
	public WPosMainWindow() {

		dyinit();
		init();
		
		String msg = cekAkses();

		if (msg != "")
			return;
	}

	private void init() {
		
		form.appendChild(mainLayout);
		mainLayout.setWidth("99%");
		mainLayout.setHeight("100%");
		
		form.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>(){

			@Override
			public void onEvent(Event arg0) throws Exception {

				form.dispose();
			}
			
		});
		
		North north = new North();
		mainLayout.appendChild(north);

		String grid = "border: 1px solid #C0C0C0; border-radius:5px;";
		north.appendChild(parameterPanel);
		north.setStyle(grid);
		//north.setSplittable(true);
		
		parameterPanel.appendChild(parameterGrid);
		parameterGrid.setWidth("100%");
		parameterGrid.setStyle("Height:28%;");
		//parameterGrid.setHflex("min");

		
		Rows rows = null;
		Row row = null;

		rows = parameterGrid.newRows();

		// No Nota
		row = rows.newRow();
		row.appendCellChild(noNotaLabel.rightAlign(), 1);
		row.appendCellChild(noNota, 1);
		noNota.setHflex("true");
		if(!isManualDocumentNo){
			noNota.setReadonly(true);
		}
		
		
		// tanggal
		row.appendCellChild(dateLabel.rightAlign(), 1);
		dateField.getComponent().setHflex("true");
		row.appendCellChild(dateField.getComponent(), 1);

		// Org
		row = rows.newRow();
		row.appendCellChild(orgLabel.rightAlign(), 1);
		org.getComponent().setHflex("true");
		row.appendCellChild(org.getComponent(), 1);
		org.showMenu();

		// customer
		row.appendCellChild(bPartnerLabel.rightAlign(), 1);
		bpartnerSearch.getComponent().setHflex("true");
		row.appendCellChild(bpartnerSearch.getComponent(), 1);
		row.appendChild(addBusinessPartner);
		addBusinessPartner.addActionListener(this);
		//addBusinessPartner.setHeight("24px");
		addBusinessPartner.setImage(ThemeManager.getThemeResource("images/addBP.png"));
		addBusinessPartner.setTooltiptext("Tambah Pelanggan");

		// Salesrep
		row = rows.newRow();
		row.appendCellChild(salesLabel.rightAlign(), 1);
		salesSearch.getComponent().setHflex("true");
		row.appendCellChild(salesSearch.getComponent(), 1);

		// priceList
		// pricelist
		KeyNamePair pp = new KeyNamePair(0, "");
		priceList.removeActionListener(this);
		priceList.removeAllItems();
		priceList.addItem(pp);

		ArrayList<KeyNamePair> list = loadPriceList(CreatedByPOS_ID);
		for (KeyNamePair knp : list)
			priceList.addItem(knp);
		priceList.setSelectedIndex(0);
		priceList.addActionListener(this);

		// pricelist
		row.appendCellChild(priceListLabel.rightAlign(), 1);
		row.appendCellChild(priceList, 1);
		priceList.setHflex("true");
		priceList.setHeight("24px");
		priceList.setStyle("border-radius:3px;");

		// warehouse
		row = rows.newRow();
		row.appendCellChild(warehouseLabel.rightAlign(), 1);
		warehouseSearch.getComponent().setHflex("true");
		row.appendCellChild(warehouseSearch.getComponent(), 1);

		// locator
		row.appendCellChild(locatorLabel.rightAlign(), 1);
		locatorSearch.getComponent().setHflex("true");
		row.appendCellChild(locatorSearch.getComponent(), 1);
		locatorSearch.addValueChangeListener(this);
		row.appendCellChild(new Space(), 1);

		// product
		row = rows.newRow();
		row.appendCellChild(productLabel.rightAlign(), 1);
		productSearch.getComponent().setHflex("true");
		row.appendCellChild(productSearch.getComponent(), 1);

		// imei
		row.appendCellChild(imeiLabel.rightAlign(), 1);
		row.appendCellChild(imeiList, 1);
		imeiList.setEnabled(false);
		
		// imeiList.setWidth("100px");
		imeiList.setHflex("true");
		imeiList.setStyle("align:right;");
		imeiList.addEventListener(0, "onChange", this);
		imeiList.setHeight("24px");
		imeiList.setStyle("border-radius:3px;");

		row = rows.newRow();
		// button new pos
		row.appendCellChild(new Space(), 1);
		Hbox hbox = new Hbox();
		row.appendCellChild(hbox,3);
		hbox.setHflex("true");

		hbox.setAlign("center");
		hbox.setStyle("border-top:1px solid #000000 ;padding:1px;");
		hbox.appendChild(newOrderButton);
		newOrderButton.setLabel("Penjualan Baru");
		newOrderButton.addActionListener(this);
		newOrderButton.setHflex("true");
		newOrderButton.setStyle("background:#3498db;border:1px solid #3498db;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;");
		newOrderButton.setTooltiptext("Buat Penjualan Baru");
		
		hbox.appendChild(clearButton);
		clearButton.setLabel("Hapus Produk");
		clearButton.addActionListener(this);
		clearButton.setHflex("true");
		clearButton.setStyle("background:#2ecc71;border:1px solid #2ecc71;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;");
		clearButton.setTooltiptext("Hapus Produk");

		hbox.appendChild(deleteButton);
		deleteButton.setLabel("Hapus Detail");
		deleteButton.addActionListener(this);
		deleteButton.setHflex("true");
		deleteButton.setStyle("background:#e74c3c;border:1px solid #e74c3c;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;");
		deleteButton.setTooltiptext("Hapus Detail Penjualan");
	
		hbox.appendChild(printButton);
		printButton.setLabel("Cetak Nota");
		printButton.addActionListener(this);
		printButton.setEnabled(false);
		printButton.setHflex("true");
		printButton.setStyle("background:#e67e22;border:1px solid #e67e22;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;");
		printButton.setTooltiptext("Cetak Nota");
		
		if(isThermalPrint){
			hbox.appendChild(printThermalButton);
			printThermalButton.setLabel("Cetak Struk");
			printThermalButton.addActionListener(this);
			printThermalButton.setEnabled(false);
			printThermalButton.setHflex("true");
			printThermalButton.setStyle("background:#e67e22;border:1px solid #e67e22;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;");
			printThermalButton.setTooltiptext("Cetak Struk");
		}
		
		hbox.appendChild(printSuratJalan);
		printSuratJalan.setLabel("Cetak SJ");
		printSuratJalan.addActionListener(this);
		printSuratJalan.setEnabled(false);
		printSuratJalan.setHflex("true");
		printSuratJalan.setStyle("background:#e67e22;border:1px solid #e67e22;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;");
		printSuratJalan.setTooltiptext("Cetak Surat Jalan");
		
		
		Hbox hbox2 = new Hbox();
		row.appendCellChild(hbox2,1);
		hbox2.appendChild(deliveryCheck);
		deliveryCheck.setChecked(true);
		deliveryCheck.setLabel("Pick Up");
		deliveryCheck.addActionListener(this);
		deliveryCheck.setStyle("background:#E7F76D;border:1px solid #E7F76D;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;border-radius:3px");

		// SouthPanel
		South south = new South();
		mainLayout.appendChild(south);
		south.setStyle(grid);
		south.appendChild(southPanel);
		southPanel.appendChild(salesGrid);
			
		rows = salesGrid.newRows();
		row = rows.newRow();

		// paymentrule1
		row.appendCellChild(payruleList1, 1);
		payruleList1.setWidth("100px");
		payruleList1.setStyle("align:right;");
		payruleList1.addActionListener(this);
		payruleList1.setHeight("24px");
		payruleList1.setStyle("border-radius:3px;");

		row.appendCellChild(paymentRule1, 2);
		paymentRule1.setStyle("text-align:right;");
		// paymentRule1.setHflex("true");
		paymentRule1.setText("0.00");
		paymentRule1.addEventListener(0, "onBlur", this);

		// button +
		plusButton.setLabel("+");
		row.appendCellChild(plusButton);
		plusButton.addActionListener(this);
		plusButton.setHeight("24px");


		// leasing
		row.appendCellChild(leasingProviderLabel.rightAlign(), 1);
		row.appendCellChild(leasingProviderSearch.getComponent(), 2);
		leasingProviderSearch.getComponent().setHflex("true");
		leasingProviderLabel.setVisible(false);
		leasingProviderSearch.setVisible(false);

		// totalLines
		row.appendCellChild(totalLabel.rightAlign(), 1);
		total.setText("0.00");
		total.setStyle("text-align:right;");
		total.setReadonly(true);
		row.appendCellChild(total, 2);

		// paymentrule2
		row = rows.newRow();
		row.appendCellChild(payruleList2, 1);
		payruleList2.setWidth("100px");
		payruleList2.setVisible(false);
		payruleList2.addActionListener(this);
		payruleList2.setHeight("24px");
		payruleList2.setStyle("border-radius:3px;");

		row.appendCellChild(paymentRule2, 2);
		paymentRule2.setVisible(false);
		paymentRule2.setStyle("text-align:right;");
		paymentRule2.setText("0.00");
		paymentRule2.addEventListener(0, "onBlur", this);

		// button +2
		plusButton2.setLabel("+");
		row.appendCellChild(plusButton2);
		plusButton2.addActionListener(this);
		plusButton2.setVisible(false);
		plusButton2.setHeight("24px");

		// bankAccount
		row.appendCellChild(bankAccountLabel.rightAlign(), 1);
		row.appendCellChild(bankAccountSearch.getComponent(), 2);
		bankAccountSearch.getComponent().setHflex("true");
		bankAccountLabel.setVisible(false);
		bankAccountSearch.setVisible(false);

		// diskon
		row.appendCellChild(diskonLabel.rightAlign(), 1);
		diskon.setText("0.00");
		row.appendCellChild(diskon, 2);
		diskon.setReadonly(true);
		diskon.setStyle("text-align:right;");

		// paymentrule3
		row = rows.newRow();
		row.appendCellChild(payruleList3, 1);
		payruleList3.setWidth("100px");
		payruleList3.setVisible(false);
		payruleList3.addActionListener(this);
		payruleList3.setHeight("24px");
		payruleList3.setStyle("border-radius:3px;");

		row.appendCellChild(paymentRule3, 2);
		paymentRule3.setVisible(false);
		paymentRule3.setStyle("text-align:right;");
		paymentRule3.setText("0.00");
		paymentRule3.addEventListener(0, "onBlur", this);

		// document Action
		row.appendCellChild(new Space(), 1);
		row.appendCellChild(docActionLabel.rightAlign(), 1);
		row.appendCellChild(docActionList, 2);
		docActionList.addActionListener(this);
		docActionList.setStyle("border-radius :3px;");
		docActionList.setHeight("24px");
		docActionList.setHflex("true");

		// grantotal
		row.appendCellChild(grandtotalLabel.rightAlign(), 1);
		grandtotal.setText("0.00");
		row.appendCellChild(grandtotal, 2);
		grandtotal.setReadonly(true);
		grandtotal.setStyle("text-align:right;");

		// sisabayar
		row = rows.newRow();
		row.appendCellChild(sisaPembayaranLabel.rightAlign(), 1);
		sisaPembayaran.setText("0.00");
		row.appendCellChild(sisaPembayaran, 2);
		sisaPembayaran.setReadonly(true);
		sisaPembayaran.setStyle("text-align:right;");

		// description
		row.appendCellChild(new Space(), 1);
		row.appendCellChild(descriptionLabel.rightAlign(), 1);
		row.appendCellChild(description, 2);
		description.setRows(2);
		description.setHeight("100%");
		description.setHflex("true");

		// dpp
		row.appendCellChild(dppLabel.rightAlign(), 1);
		dpp.setText("0.00");
		row.appendCellChild(dpp, 2);
		dpp.setReadonly(true);
		dpp.setStyle("text-align:right;");

		// button process
		row = rows.newRow();
		row.appendCellChild(new Space(), 1);
		processButton.setLabel("Proses Penjualan");
		processButton.addActionListener(this);
		row.appendCellChild(processButton, 2);
		row.appendCellChild(new Space(), 1);
		processButton.setStyle("background:#2ecc71;border:1px solid #2ecc71;font-size:14px;font-weight:bold;color:#34495e;text-shadow: 0px 0px 0px #34495e;");
		processButton.setTooltiptext("Proses Penjualan");
		
		// date DO
		row.appendCellChild(dateDOLabel.rightAlign(), 1);
		dateDOField.getComponent().setHflex("true");
		row.appendCellChild(dateDOField.getComponent(), 2);

		// pajak
		row.appendCellChild(pajakLabel.rightAlign(), 1);
		pajak.setText("0.00");
		row.appendCellChild(pajak, 2);
		pajak.setReadonly(true);
		pajak.setStyle("text-align:right;");
						
		south = new South();
		salesPanel.appendChild(salesLayout);
		salesLayout.appendChild(south);
		salesPanel.setWidth("100%");
		salesPanel.setHeight("100%");
		salesLayout.setWidth("100%");
		salesLayout.setHeight("100%");
		
		Center center = new Center();
		salesLayout.appendChild(center);
		center.appendChild(salesTable);
		salesTable.setWidth("100%");
		salesTable.setHeight("100%");
		center.setStyle(grid);

		center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(infoLayout);
		infoLayout.setHflex("1");
		infoLayout.setVflex("1");
		infoLayout.setWidth("100%");
		infoLayout.setHeight("100%");

		north = new North();
		north.setHeight("100%");
		infoLayout.appendChild(north);
		north.appendChild(salesPanel);
		north.setSplittable(true);
		center = new Center();
		infoLayout.appendChild(center);

	}

	private void dyinit() {
		AD_User_ID = Env.getAD_User_ID(ctx);

		priceListLabel.setVisible(false);
		priceList.setVisible(false);

		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		CreatedByPOS_ID = DB.getSQLValueEx(ctx.toString(), sqlKasir.toString(),new Object[] { Env.getAD_Client_ID(ctx), AD_User_ID });

		if (CreatedByPOS_ID > 0) {

			String sqlPriceList = "SELECT M_PriceList_ID FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				int count = 0;
				pstmt = DB.prepareStatement(sqlPriceList.toString(), null);
				pstmt.setInt(1, AD_Client_ID);
				pstmt.setInt(2, CreatedByPOS_ID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count++;

					M_Pricelist_ID = DB.getSQLValueEx(ctx.toString(),
							sqlPriceList, new Object[] { AD_Client_ID,
									CreatedByPOS_ID });

					if (count > 1) {

						priceListLabel.setVisible(true);
						priceList.setVisible(true);

					}

				}

			} catch (SQLException e) {
				log.log(Level.SEVERE, sqlPriceList.toString(), e);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}

			String sqlCompleteMultiLocator = "SELECT IsCompleteMultiLocator FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
			String completeMultiLocator = DB.getSQLValueStringEx(ctx.toString(), sqlCompleteMultiLocator, new Object[] {AD_Client_ID, CreatedByPOS_ID });

			if (completeMultiLocator.toUpperCase().equals("Y")) {
				isCompleteMultiLocator = true;
			} else {
				isCompleteMultiLocator = false;
			}

			String sqlIsBackDate = "SELECT IsBackDate FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
			String backDate = DB.getSQLValueStringEx(ctx.toString(),sqlIsBackDate,new Object[] { AD_Client_ID, CreatedByPOS_ID });

			if (backDate.toUpperCase().equals("Y")) {
				isBackDate = true;
			} else {
				isBackDate = false;
			}

			if (isBackDate) {
				dateField.setReadWrite(true);
			} else {
				dateField.setReadWrite(false);
			}
			
			
			
			String thermalPrintSql = "SELECT IsThermalPrint FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
			String isThermal  = DB.getSQLValueStringEx(ctx.toString(),thermalPrintSql,new Object[] { AD_Client_ID, CreatedByPOS_ID });
			
			if (isThermal.toUpperCase().equals("Y")) {
				isThermalPrint = true;
			} else {
				isThermalPrint = false;
			}
			
			
			String manualDocumentNoSql = "SELECT IsManualDocumentNo FROM C_Pos WHERE AD_Client_ID = ? AND  CreatedByPOS_ID = ? ";
			String isManualDoc  = DB.getSQLValueStringEx(ctx.toString(),manualDocumentNoSql,new Object[] { AD_Client_ID, CreatedByPOS_ID });
			if (isManualDoc.toUpperCase().equals("Y")){
				isManualDocumentNo = true;
			}else{
				isManualDocumentNo = false;
			}
		
		}

		MLookup lookupBank = MLookupFactory.get(ctx, form.getWindowNo(), 0,3880, DisplayType.TableDir);
		bankAccountSearch = new WTableDirEditor("C_BankAccount_ID", true,false, true, lookupBank);
		bankAccountSearch.addValueChangeListener(this);
		bankAccountSearch.setMandatory(true);

		MLookup lookupBP = MLookupFactory.get(ctx, form.getWindowNo(), 0, 2893,DisplayType.Search);
		bpartnerSearch = new WSearchEditor("C_BPartner_ID", true, false, true,lookupBP);
		bpartnerSearch.addValueChangeListener(this);
		bpartnerSearch.setMandatory(true);

		MLookup lookupProduct = MLookupFactory.get(ctx, form.getWindowNo(), 0,1402, DisplayType.Search);
		productSearch = new WSearchEditor("M_Product_ID", true, false, true,lookupProduct);
		productSearch.addValueChangeListener(this);
		productSearch.setReadWrite(false);
		productSearch.setMandatory(true);

		MLookup lookupWarehouse = MLookupFactory.get(ctx, form.getWindowNo(),0, 1151, DisplayType.TableDir);
		warehouseSearch = new WTableDirEditor("M_Warehouse_ID", true, false,true, lookupWarehouse);
		warehouseSearch.addValueChangeListener(this);
		warehouseSearch.setMandatory(true);

		MLookup lookupLocator = MLookupFactory.get(ctx, form.getWindowNo(), 0,1389, DisplayType.TableDir);
		locatorSearch = new WTableDirEditor("M_Locator_ID", true, false, true,lookupLocator);
		locatorSearch.addValueChangeListener(this);
		locatorSearch.setMandatory(true);

		//
		String sqlOrg = "SELECT AD_Org_ID FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
		int org_id = DB.getSQLValueEx(ctx.toString(), sqlOrg, new Object[] {
				AD_Client_ID, CreatedByPOS_ID });

		MLookup orgLookup = MLookupFactory.get(ctx, form.getWindowNo(), 0,2163, DisplayType.TableDir);
		org = new WTableDirEditor("AD_Org_ID", true, false, true, orgLookup);
		org.addValueChangeListener(this);
		org.setMandatory(true);
		org.setValue(org_id);
		org.setReadWrite(false);

		// MLookup imeiLookup = MLookupFactory.get(ctx,form.getWindowNo(), 0,
		// 8472, DisplayType.Search);
		// imeiSearch = new WSearchEditor("M_AttributeSetInstance_ID", true,
		// false, true, imeiLookup);
		// imeiSearch.addValueChangeListener(this);
		// imeiSearch.setMandatory(true);

		MLookup salesRepLookup = MLookupFactory.get(ctx, form.getWindowNo(), 0,2186, DisplayType.TableDir);
		salesSearch = new WTableDirEditor("SalesRep_ID", true, false, true,salesRepLookup);
		salesSearch.addValueChangeListener(this);
		salesSearch.setMandatory(true);

		MLookup leasingLookup = MLookupFactory.get(ctx, form.getWindowNo(), 0,331558, DisplayType.List);
		leasingProviderSearch = new WTableDirEditor("leaseprovider", true,false, true, leasingLookup);
		leasingProviderSearch.addValueChangeListener(this);

		// Date set to Login Date
		Calendar cal = Calendar.getInstance();
		cal.setTime(Env.getContextAsDate(ctx, "#Date"));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		dateField.setValue(new Timestamp(cal.getTimeInMillis()));
		dateField.addValueChangeListener(this);

		dateDOField.setValue(new Timestamp(cal.getTimeInMillis()));
		dateDOField.addValueChangeListener(this);
		dateDOField.setVisible(false);
		dateDOLabel.setVisible(false);

	}

	protected void configureMiniTable(IMiniTable miniTable) {

		miniTable.setColumnClass(0, Boolean.class, false);
		miniTable.setColumnClass(1, String.class, true); // 2-Product
		miniTable.setColumnClass(2, BigDecimal.class, false); // 3-Qty
		miniTable.setColumnClass(3, BigDecimal.class, true); // 4-priceList
		miniTable.setColumnClass(4, BigDecimal.class, false); // 5-price
		miniTable.setColumnClass(5, String.class, true); // 6-diskon
		miniTable.setColumnClass(6, BigDecimal.class, true); // 7-totalprice
		miniTable.setColumnClass(7, String.class, true); // 8-locator
		miniTable.setColumnClass(8, String.class, true); // 9-imei
		miniTable.setColumnClass(9, String.class, true); // 10-tipepajak

		miniTable.autoSize();

	}

	protected Vector<String> getOISColumnNames() {

		// Header Info
		Vector<String> columnNames = new Vector<String>(10);
		columnNames.add(Msg.getMsg(ctx, "Select"));
		columnNames.add(Msg.translate(ctx, "M_Product_ID"));
		columnNames.add(Msg.translate(ctx, "Quantity"));
		columnNames.add(Msg.translate(ctx, "M_PriceList_ID"));
		columnNames.add("Price");
		columnNames.add("Diskon");
		columnNames.add("Total Price");
		columnNames.add("Locator");
		columnNames.add("IMEI");
		columnNames.add("Tipe Pajak");

		return columnNames;

	}

	@Override
	public void valueChange(ValueChangeEvent evt) {
		try {
			String name = evt.getPropertyName();

			Object value = evt.getNewValue();
			if (value == null) {

				if (bpartnerId > 0) {
					bpartnerSearch.setValue(bpartnerId);
				} else {

					try {
						WSearchEditor.createBPartner(form.getWindowNo());
						WSearchEditor.createProduct(form.getWindowNo());
					} catch (Exception e) {
						log.log(Level.SEVERE, this.getClass()
								.getCanonicalName() + ".valueChange ERROR: ", e);
					}
				}

				return;
			}
			// BPartner
			if (name.equals("C_BPartner_ID")) {

				bpartnerSearch.setValue(value);
				bpartnerId = ((Integer) value).intValue();
				if ((int) bpartnerSearch.getValue() > 0) {
					int BPartner_ID = (int) bpartnerSearch.getValue();
					MBPartner bp = new MBPartner(ctx, BPartner_ID, null);
					if (bp.getC_PaymentTerm_ID() > 0) {

						C_PaymentTerm_ID = bp.getC_PaymentTerm_ID();

					} else {
						String sqlterm = "SELECT C_PaymentTerm_ID FROM C_PaymentTerm WHERE AD_Client_ID = ? AND IsDefault = 'Y' ";
						C_PaymentTerm_ID = DB.getSQLValueEx(ctx.toString(),
								sqlterm.toString(),
								new Object[] { Env.getAD_Client_ID(ctx) });

					}
				} else {
					String sqlterm = "SELECT C_PaymentTerm_ID FROM C_PaymentTerm WHERE AD_Client_ID = ? AND IsDefault = 'Y' ";
					C_PaymentTerm_ID = DB.getSQLValueEx(ctx.toString(),
							sqlterm.toString(),
							new Object[] { Env.getAD_Client_ID(ctx) });
				}

			}// C_BPartner_ID
			else if (name.equals("M_Product_ID")) {

				productSearch.setValue(value);
				productID = ((Integer) value).intValue();

				if (productID != null) {
					String Locator = locatorSearch.getValue().toString();
					int M_LocatorPOS_ID = Integer.valueOf(Locator);
					MLocator loc = new MLocator(ctx, M_LocatorPOS_ID, null);

					String sqlLocator = "SELECT M_Locator_ID FROM M_StorageOnHand WHERE AD_Client_ID = ? AND M_Product_ID = ? AND M_Locator_ID = ?";
					int M_LocatorProd_ID = DB.getSQLValueEx(ctx.toString(),
							sqlLocator, new Object[] { AD_Client_ID, productID,
									M_LocatorPOS_ID });

					MProduct prodPOS = new MProduct(ctx, productID, null);
					if (!prodPOS.getProductType().equals("E")
							&& M_LocatorProd_ID != M_LocatorPOS_ID) {
						
						if (deliveryCheck.isSelected()){
							FDialog.warn(form.getWindowNo(), null, "","Produk yang anda pilih tidak ada di lokasi "+ loc.getValue(), "Peringatan");
							// productSearch.setValue(null);
							return;
						}
					}

					Vector<Vector<Object>> data = getProductData(productID,M_Pricelist_ID, bpartnerId, M_LocatorPOS_ID, 0,salesTable);
					Vector<String> columnNames = getOISColumnNames();

					salesTable.clear();

					// Set Model
					ListModelTable modelP = new ListModelTable(data);
					modelP.addTableModelListener(this);
					salesTable.setData(modelP, columnNames);
					configureMiniTable(salesTable);

					ArrayList<KeyNamePair> list = loadImei(productID,M_LocatorPOS_ID);
					imeiList.removeAllItems();
					for (KeyNamePair imei : list)
					imeiList.appendItem(imei.getName());
					imeiList.setSelectedIndex(0);
					// imeiList.addActionListener(this);

					reCalculate(salesTable);
					calculate();
					clearPembayaran();
				} else {
					String Locator = locatorSearch.getValue().toString();
					int M_LocatorPOS_ID = Integer.valueOf(Locator);
					ArrayList<KeyNamePair> list = loadImei(productID,M_LocatorPOS_ID);
					imeiList.removeAllItems();
					for (KeyNamePair imei : list)
					imeiList.appendItem(imei.getName());
					imeiList.setSelectedIndex(0);

				}
			} else if (name.equals("M_Locator_ID")) {

				if (locatorSearch.getValue() != null && M_Pricelist_ID > 0) {
					productSearch.setReadWrite(true);
					
					String Locator = locatorSearch.getValue().toString();
					int M_LocatorPOS_ID = Integer.valueOf(Locator);
					imeiList.setEnabled(true);
					ArrayList<KeyNamePair> list = loadImei(productID,M_LocatorPOS_ID);
					imeiList.removeAllItems();
					for (KeyNamePair imei : list)
					imeiList.appendItem(imei.getName());
					imeiList.setSelectedIndex(0);
					
				} else {
					productSearch.setReadWrite(false);
					imeiList.setEnabled(false);

				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, this.getClass().getCanonicalName()
					+ ".valueChange - ERROR: " + e.getMessage(), e);
		}
	}

	@Override
	public void onEvent(Event e) throws Exception {

		log.config("");
		if (e.getTarget().equals(imeiList)) {
						
			int listSize = imeiList.getItemCount();
			if (listSize <= 1)
				return;

			String serno = imeiList.getSelectedItem().getId();

			String sql = "SELECT M_AttributeSetInstance_ID"
					+ " FROM M_AttributeSetInstance "
					+ " WHERE AD_Client_ID = ?" + " AND Serno = '" + serno
					+ "'";

			Integer M_AttributeSetInstance_ID = DB.getSQLValueEx(null, sql,AD_Client_ID);
		
			if(M_AttributeSetInstance_ID <= 0){
				
				FDialog.warn(form.getWindowNo(), null, "","IMEI Yang Anda Inputkan Bermasalah, Silahkan Cek IMEI","Peringatan");
				imeiList.setValue(null);
				return;	
				
			}
			
			MAttributeSetInstance imei = new MAttributeSetInstance(ctx,M_AttributeSetInstance_ID, null);

		
			
			int countSelected = 0;
			
			for (int i = 0; i < salesTable.getRowCount(); i++) {
				boolean isSelect = (boolean) salesTable.getValueAt(i, 0);
				if(isSelect){
					countSelected++;
				}
			}
			
			
			for (int i = 0; i < salesTable.getRowCount(); i++) {

				String imeiProd = imei.getSerNo();
				String imeiTab = (String) salesTable.getValueAt(i, 8);
				boolean isSelect = (boolean) salesTable.getValueAt(i, 0);

				if(countSelected > 1){
					FDialog.warn(form.getWindowNo(), null, "","Tidak Boleh Memilih 2 Detail Untuk Penggantian IMEI","Peringatan");
					imeiList.setValue(null);
					return;	
				}
				else if(countSelected == 1){
						
					if(isSelect){
						if (imeiProd.equalsIgnoreCase(imeiTab)) {
							FDialog.warn(form.getWindowNo(), null, "","Product Dengan IMEI Tersebut Sudah Terinput","Peringatan");
							imeiList.setValue(null);
							return;
						}else{	
							String Locator = locatorSearch.getValue().toString();
							int M_Loc_ID = Integer.valueOf(Locator);
							MLocator loc = new MLocator(ctx, M_Loc_ID, null);
							
							
							String locTble = (String) salesTable.getValueAt(i, 7);
							
							if(loc.getValue().toUpperCase().equals(locTble.toUpperCase())){
								salesTable.setValueAt(imei.getSerNo(), i, 8);
							}else{
								FDialog.warn(form.getWindowNo(), null, "","Locator Detail Tidak Boleh Berbeda Dengan Locator Product Untuk Penggantian Nomor IMEI","Peringatan");
								imeiList.setValue(null);
								return;
							}
							
						}			
					}
					
				}else if (countSelected == 0){
					
					FDialog.warn(form.getWindowNo(), null, "","Silahkan Pilih Product Detail Untuk Penggantian IMEI","Peringatan");
					imeiList.setValue(null);
					return;	
					
					
				}
				
				
			

			}

			if (imeiIndex != null) {
				KeyNamePair product = (KeyNamePair) salesTable.getValueAt(rowIndex, 1);

				String sqlProduct = "SELECT M_Product_ID FROM M_Product WHERE AD_Client_ID = ? AND name = '"+ product.toString() + "'";
				int M_Product_ID = DB.getSQLValue(ctx.toString(),sqlProduct.toString(), AD_Client_ID);

				String sqlImei = "SELECT M_Product_ID FROM M_StorageOnHand WHERE AD_Client_ID = ? AND M_AttributeSetInstance_ID = ?";
				int M_ProductIMEI_ID = DB.getSQLValueEx(ctx.toString(),sqlImei, new Object[] { AD_Client_ID,M_AttributeSetInstance_ID });

				if (M_Product_ID != M_ProductIMEI_ID) {

					FDialog.warn(form.getWindowNo(),null,"","IMEI yang anda inputkan tidak sesuai dengan produk","Peringatan");
					imeiList.setValue(null);
					return;

				}

//				int lastRow = salesTable.getRowCount() - 1;
//				salesTable.setValueAt(imei.getSerNo(), lastRow, 8);
//				imeiIndex = null;
				

			} else {

				String Locator = locatorSearch.getValue().toString();
				int M_ProductIMEI_ID;
				int M_LocatorProd_ID;
				int M_LocatorPOS_ID = Integer.valueOf(Locator);

				MLocator loc = new MLocator(ctx, M_LocatorPOS_ID, null);

				String sqlImei = "SELECT M_Product_ID FROM M_StorageOnHand WHERE M_AttributeSetInstance_ID = ? AND QtyOnHand > 0";
				M_ProductIMEI_ID = DB.getSQLValueEx(ctx.toString(), sqlImei,new Object[] { M_AttributeSetInstance_ID });

				if (M_ProductIMEI_ID < 0) {
					FDialog.warn(form.getWindowNo(), null, "","Produk dengan IMEI yang anda pilih tidak ada di lokasi "+ loc.getValue(), "Peringatan");
					imeiList.setValue(null);
					return;
				}

				String sqlLocator = "SELECT M_Locator_ID FROM M_StorageOnHand WHERE M_AttributeSetInstance_ID = ? AND QtyOnHand > 0";
				M_LocatorProd_ID = DB.getSQLValueEx(ctx.toString(), sqlLocator,new Object[] { M_AttributeSetInstance_ID });

				if (M_LocatorProd_ID != M_LocatorPOS_ID) {

					FDialog.warn(form.getWindowNo(), null, "","Produk dengan IMEI yang anda pilih tidak ada di lokasi "+ loc.getValue(), "Peringatan");
					imeiList.setValue(null);
					return;

				}

				salesTable.clear();

				Vector<Vector<Object>> data = getProductData(M_ProductIMEI_ID,M_Pricelist_ID, bpartnerId, M_LocatorPOS_ID,M_AttributeSetInstance_ID, salesTable);
				Vector<String> columnNames = getOISColumnNames();

				salesTable.clear();

				// Set Model
				ListModelTable modelP = new ListModelTable(data);
				modelP.addTableModelListener(this);
				salesTable.setData(modelP, columnNames);
				configureMiniTable(salesTable);

				reCalculate(salesTable);
				calculate();
				imeiList.setValue(null);
				imeiIndex = null;
			}

		} else if (e.getTarget().equals(deliveryCheck)) {

			if (deliveryCheck.isChecked()) {
				dateDOLabel.setVisible(false);
				dateDOField.setVisible(false);
			} else {
				dateDOLabel.setVisible(true);
				dateDOField.setVisible(true);
				dateDOField.setReadWrite(true);
			}

		} else if (e.getTarget().equals(deleteButton)) {

			int rowCount = 0;
			for (int i = 0; i < salesTable.getRowCount(); i++) {
				boolean isSelect = (boolean) salesTable.getValueAt(i, 0);

				if (isSelect) {
					rowCount++;
				}

			}
			for (int i = 0; i < rowCount; i++) {

				for (int j = 0; j < salesTable.getRowCount(); j++) {
					boolean isSelect = (boolean) salesTable.getValueAt(j, 0);
					if (isSelect) {
						deletedata(j);
						// salesTable.clear();

						Vector<String> columnNames = getOISColumnNames();

						ListModelTable modelP = new ListModelTable(data);

						modelP.addTableModelListener(this);

						salesTable.setData(modelP, columnNames);

						configureMiniTable(salesTable);

						if (salesTable.getRowCount() == 0) {
							total.setText("0.00");
							sisaPembayaran.setText("0.00");
							paymentRule1.setText("0.00");
							diskon.setText("0.00");
							grandtotal.setText("0.00");
							dpp.setText("0.00");
							pajak.setText("0.00");
							totalPrices = Env.ZERO;
							totalDiskons = Env.ZERO;
							return;
						}

						// recalculate component
						reCalculate(salesTable);
						calculate();
						clearPembayaran();
					}
				}
			}
		} else if (e.getTarget().equals(plusButton)) {

			BigDecimal totalbayar = totalBayar();
			BigDecimal sisaByar = nilaiGrandTotal.subtract(totalbayar);

			if (plusButton.getLabel().equalsIgnoreCase("+")) {

				payruleList2.setVisible(true);
				paymentRule2.setVisible(true);
				plusButton2.setVisible(true);
				sisaPembayaranLabel.setText("Kurang Bayar");
				paymentRule2.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					paymentRule2.setText(format.format(0.00));
				}
				// kalkulasi ulang setelah set pay 2
				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}
				plusButton.setLabel("-");
				plusButton2.setLabel("+");
				if(!plusButton2.isEnabled()){
				plusButton2.setEnabled(true);
				}

			} else {

				payruleList2.setVisible(false);
				paymentRule2.setVisible(false);
				plusButton2.setVisible(false);

				if (payruleList3.isVisible()) {
					payruleList3.setVisible(false);
				}

				if (paymentRule3.isVisible()) {
					paymentRule3.setVisible(false);
				}

				plusButton.setLabel("+");
				paymentRule2.setText(format.format(0.00));
				paymentRule3.setText(format.format(0.00));
				payruleList2.setSelectedIndex(0);
				payruleList3.setSelectedIndex(0);
				// kalkulasi ulang setelah set pay 1
				paymentRule1.setText(format.format(nilaiGrandTotal));
				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}

			}

		} else if (e.getTarget().equals(plusButton2)) {

			BigDecimal totalbayar = totalBayar();
			BigDecimal sisaByar = nilaiGrandTotal.subtract(totalbayar);

			if (plusButton2.getLabel().equalsIgnoreCase("+")) {

				payruleList3.setVisible(true);
				paymentRule3.setVisible(true);
				plusButton2.setLabel("-");
				paymentRule3.setText(format.format(sisaByar.abs()));

				if (sisaByar.compareTo(Env.ZERO) < 0) {
					paymentRule3.setText(format.format(0.00));
				}

				// kalkulasi ulang setelah set pay 3
				sisaPembayaranLabel.setText("Kurang Bayar");
				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}

			} else {

				payruleList3.setVisible(false);
				paymentRule3.setVisible(false);
				plusButton2.setLabel("+");

				payruleList3.setSelectedIndex(0);
				paymentRule3.setText(format.format(0.00));

				// kalkulasi ulang setelah set pay 3
				sisaPembayaranLabel.setText("Kurang Bayar");
				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}
			}

		} else if (e.getTarget().equals(clearButton)) {

			productSearch.setValue(null);
			productSearch.setReadWrite(false);
			locatorSearch.setValue(null);
			imeiList.setValue(null);
			imeiList.setEnabled(false);
			imeiList.removeAllItems();
			imeiIndex = null;

		} else if (e.getTarget().equals(paymentRule1)) {

			Double dBayar1 = 0.00;

			if (paymentRule1.getText().isEmpty()) {
				dBayar1 = 0.00;
			} else if (!paymentRule1.getText().isEmpty()) {
				if (AD_Language.toUpperCase().equals("EN_US")) {
					dBayar1 = Double.valueOf(paymentRule1.getText().replaceAll(",", ""));
				} else if (AD_Language.toUpperCase().equals("IN_ID")) {
					String dby1 = paymentRule1.getText().replaceAll("\\.", "").replaceAll(",", ".");
					dBayar1 = Double.valueOf(dby1);
				}
			}

			BigDecimal bayar1 = BigDecimal.valueOf(dBayar1);
			BigDecimal sisaByar = nilaiGrandTotal.subtract(bayar1);
			BigDecimal totalbayar = bayar1;

			if (paymentRule2.isVisible() && !paymentRule3.isVisible()) {
				paymentRule1.setText(format.format(bayar1));

				paymentRule2.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					paymentRule2.setText(format.format(0.00));
				}

				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}
			} else if (paymentRule2.isVisible() && paymentRule3.isVisible()) {
				paymentRule1.setText(format.format(bayar1));
				paymentRule2.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					paymentRule2.setText(format.format(0.00));
				}
				paymentRule3.setVisible(false);
				paymentRule3.setText(format.format(0.00));
				payruleList3.setVisible(false);
				payruleList3.setSelectedIndex(0);
				plusButton2.setLabel("+");
				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}
			} else if (!paymentRule2.isVisible() && !paymentRule3.isVisible()) {
				paymentRule1.setText(format.format(bayar1));
				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}
			}

		} else if (e.getTarget().equals(paymentRule2)) {

			Double dBayar1 = 0.00;
			Double dBayar2 = 0.00;

			if (paymentRule1.getText().isEmpty()) {
				dBayar1 = 0.00;
			} else if (!paymentRule1.getText().isEmpty()) {
				if (AD_Language.toUpperCase().equals("EN_US")) {
					dBayar1 = Double.valueOf(paymentRule1.getText().replaceAll(",", ""));
				} else if (AD_Language.toUpperCase().equals("IN_ID")) {
					String dby1 = paymentRule1.getText().replaceAll("\\.", "").replaceAll(",", ".");
					dBayar1 = Double.valueOf(dby1);
				}
			}

			if (paymentRule2.getText().isEmpty()) {
				dBayar2 = 0.00;
			} else if (!paymentRule2.getText().isEmpty()) {
				if (AD_Language.toUpperCase().equals("EN_US")) {
					dBayar2 = Double.valueOf(paymentRule2.getText().replaceAll(",", ""));
				} else if (AD_Language.toUpperCase().equals("IN_ID")) {
					String dby2 = paymentRule2.getText().replaceAll("\\.", "").replaceAll(",", ".");
					dBayar2 = Double.valueOf(dby2);
				}
			}

			BigDecimal bayar2 = BigDecimal.valueOf(dBayar2);
			paymentRule2.setText(format.format(bayar2));

			BigDecimal totalbayar = BigDecimal.valueOf(dBayar1).add(BigDecimal.valueOf(dBayar2));
			BigDecimal sisaByar = nilaiGrandTotal.subtract(totalbayar);

			if (paymentRule3.isVisible() && payruleList3.isVisible()) {

				paymentRule3.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					paymentRule3.setText(format.format(0.00));
				}
				totalbayar = totalBayar();
				sisaByar = nilaiGrandTotal.subtract(totalbayar);
				sisaPembayaran.setText(format.format(sisaByar.abs()));
				if (sisaByar.compareTo(Env.ZERO) < 0) {
					sisaPembayaran.setText(format.format(0.00));
				}

			}

		} else if (e.getTarget().equals(paymentRule3)) {

			Double dBayar3 = 0.00;

			if (paymentRule3.getText().isEmpty()) {
				dBayar3 = 0.00;
			} else if (!paymentRule3.getText().isEmpty()) {
				if (AD_Language.toUpperCase().equals("EN_US")) {
					dBayar3 = Double.valueOf(paymentRule3.getText().replaceAll(
							",", ""));
				} else if (AD_Language.toUpperCase().equals("IN_ID")) {
					String dby3 = paymentRule3.getText().replaceAll("\\.", "")
							.replaceAll(",", ".");
					dBayar3 = Double.valueOf(dby3);
				}
			}

			paymentRule3.setText(format.format(dBayar3));
			BigDecimal totalbayar = totalBayar();
			BigDecimal sisaByar = nilaiGrandTotal.subtract(totalbayar);
			sisaPembayaran.setText(format.format(sisaByar.abs()));
			if (sisaByar.compareTo(Env.ZERO) < 0) {
				sisaPembayaran.setText(format.format(0.00));
			}

		} else if (e.getTarget().equals(processButton)) {

			MDecorisPOS decPOSd = null;
			
			try{
			
			//processButton.setEnabled(false);
			
			BigDecimal totalByar = totalBayar();
			BigDecimal sisaBayar = nilaiGrandTotal.subtract(totalByar);
			if (sisaBayar.compareTo(Env.ZERO) < 0) {
				FDialog.warn(form.getWindowNo(),null,"","Pembayaran Tidak Pas, Silakan Cek Kembali Detail Pembayaran","Peringatan");
				return;
			} else if (sisaBayar.compareTo(Env.ZERO) > 0) {
				FDialog.warn(form.getWindowNo(),null,"","Total Pembayaran Masih Kurang Dari Total Yang Harus Dibayar","Peringatan");
				return;
			}

			if (!isForceLimitPrice) {

				for (int i = 0; i < salesTable.getRowCount(); i++) {

					BigDecimal price = (BigDecimal) salesTable.getValueAt(i, 4);
					KeyNamePair prod = (KeyNamePair) salesTable.getValueAt(i, 1);

					Timestamp date = new Timestamp(System.currentTimeMillis());

					String sql = "SELECT plv.M_PriceList_Version_ID "
							+ "FROM M_PriceList_Version plv "
							+ "WHERE plv.AD_Client_ID = ? "
							+ " AND plv.M_PriceList_ID= ? " // 1
							+ " AND plv.ValidFrom <= ? "
							+ "ORDER BY plv.ValidFrom DESC";

					int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, new Object[] {AD_Client_ID, M_Pricelist_ID, date });

					String sqlProductPrice = "SELECT PriceLimit FROM M_ProductPrice WHERE AD_Client_ID = ? AND M_PriceList_Version_ID = ? AND M_Product_ID = ?";
					BigDecimal priceLimit = DB.getSQLValueBDEx(null,sqlProductPrice, new Object[] { AD_Client_ID,M_PriceList_Version_ID, prod.getKey() });

					if (price.compareTo(priceLimit) < 0) {
						FDialog.warn(windowNo,null,"","Harga Yang Anda Inputkan Atas Produk "+ prod.getName()+ " Lebih Rendah Dari Limit Harga","Peringantan");
						return;
					}

				}
			}

			if (C_PaymentTerm_ID <= 0) {

				String sqlterm = "SELECT C_PaymentTerm_ID FROM C_PaymentTerm WHERE AD_Client_ID = ? AND IsDefault = 'Y' ";
				C_PaymentTerm_ID = DB.getSQLValueEx(ctx.toString(),sqlterm.toString(),new Object[] { Env.getAD_Client_ID(ctx) });

			}

			if (C_PaymentTerm_ID <= 0) {

				FDialog.info(windowNo, null, "","PaymentTerm belum ditentukan", "Peringatan");
				return;
			}

			Integer AD_Org_ID = (Integer) org.getValue();

			// cek validate period status
			if (!MPeriod.isOpen(ctx, (Timestamp) dateField.getValue(), "SOO",AD_Org_ID)) {
				FDialog.warn(windowNo,null,"","Transaksi Tidak Dapat Diproses Karena Status Period Closed","Peringatan");
				return;
			}

			if (m_docAction == "") {

				if (docActionList.getSelectedItem().toString().toUpperCase().equals("COMPLETE")) {
					m_docAction = "CO";
				} else if (docActionList.getSelectedItem().toString().toUpperCase().equals("PREPARE")) {
					m_docAction = "PR";
				}

			}

			Double bayar1 = 0.00;
			Double bayar2 = 0.00;
			Double bayar3 = 0.00;
			Double disc = 0.00;

			if (AD_Language.toUpperCase().equals("EN_US")) {

				bayar1 = Double.valueOf(paymentRule1.getText().replaceAll(",",""));
				bayar2 = Double.valueOf(paymentRule2.getText().replaceAll(",",""));
				bayar3 = Double.valueOf(paymentRule3.getText().replaceAll(",",""));

				disc = Double.valueOf(diskon.getText().replaceAll(",", ""));

			} else if (AD_Language.toUpperCase().equals("IN_ID")) {
				String by1 = paymentRule1.getText().replaceAll("\\.", "").replaceAll(",", ".");
				bayar1 = Double.valueOf(by1);
				String by2 = paymentRule2.getText().replaceAll("\\.", "").replaceAll(",", ".");
				bayar2 = Double.valueOf(by2);
				String by3 = paymentRule3.getText().replaceAll("\\.", "").replaceAll(",", ".");
				bayar3 = Double.valueOf(by3);
				String ddisc = diskon.getText().replaceAll("\\.", "").replaceAll(",", ".");
				disc = Double.valueOf(ddisc);
			}

			mapPospay = new HashMap<String, BigDecimal>();

			String msg = "";
			String docType = "";
			String paymentRule = (String) paymentRule1.getValue();
			String leaseProv = (String) leasingProviderSearch.getValue();
			String tipeBayar1 = payruleList1.getSelectedItem().getValue();
			String tipeBayar2 = payruleList2.getSelectedItem().getValue();
			String tipeBayar3 = payruleList3.getSelectedItem().getValue();

			Integer SalesRep_ID = (Integer) salesSearch.getValue();
			Integer C_BPartner_ID = (Integer) bpartnerSearch.getValue();
			Integer M_WareHouse_ID = (Integer) warehouseSearch.getValue();
			// Integer M_Locator_ID = (Integer)locatorSearch.getValue();

			// Integer M_AttributeSetInstance_ID =
			// (Integer)imeiSearch.getValue();
			Integer C_BankAccount_ID = (Integer) bankAccountSearch.getValue();

			
//			String loca="";
//
//			for(int i=0; i<salesTable.getRowCount();i++){
//				String rs= (String) salesTable.getValueAt(i, 7);
//				if(!rs.isEmpty() && rs!=null && rs!=""){
//					loca = rs;
//				}
//			}
//			
//			
//				
//				
//			String sqlLoca = "SELECT M_Locator_ID FROM M_Locator WHERE AD_Client_ID = ? AND value = '"+ loca + "'";
//
//			Integer M_Locator_ID = DB.getSQLValueEx(null, sqlLoca, AD_Client_ID);
//			
//			
//			if (M_Locator_ID <0){
//				M_Locator_ID = (Integer)locatorSearch.getValue();
//				
//				if(M_Locator_ID < 0 || M_Locator_ID == null){
//					M_Locator_ID = 0;
//				}
//			}
			
			nilaiBayar1 = BigDecimal.valueOf(bayar1);
			nilaiBayar2 = BigDecimal.valueOf(bayar2);
			nilaiBayar3 = BigDecimal.valueOf(bayar3);

			BigDecimal grandtot = nilaiGrandTotal;
			BigDecimal payment1 = nilaiBayar1;
			BigDecimal payment2 = nilaiBayar2.abs();
			BigDecimal payment3 = nilaiBayar3.abs();
			BigDecimal totalDisc = BigDecimal.valueOf(disc);
			BigDecimal totalbayar = totalBayar();

			IMiniTable miniTable = salesTable;

			boolean IsCashX = false;
			boolean IsCashB = false;
			boolean IsPay2 = false;
			boolean IsBank = false;
			boolean IsHutang = false;
			boolean IsLeasing = false;
			boolean IsPaidAll = false;
			boolean IsFullPay1 = false;
			boolean IsUangMuka = false;
			boolean IsTipeBayar2 = payruleList2.isEnabled();
			boolean IsMultiPL = priceList.isVisible();

			int C_DocType_ID = 0;

			if (AD_Org_ID == null) {
				AD_Org_ID = 0;
			}
			if (C_BPartner_ID == null) {
				C_BPartner_ID = 0;
			}
			if (M_WareHouse_ID == null) {
				M_WareHouse_ID = 0;
			}
			if (SalesRep_ID == null) {
				SalesRep_ID = 0;
			}
			if (C_BankAccount_ID == null) {
				C_BankAccount_ID = 0;
				// }if (M_AttributeSetInstance_ID == null){
				// M_AttributeSetInstance_ID = 0;
			}
			if (totalbayar.compareTo(grandtot) >= 0) {
				IsPaidAll = true;
			}

			if (paymentRule1.isVisible()) {
				if (nilaiBayar1.compareTo(Env.ZERO) <= 0) {
					FDialog.warn(windowNo,null,"","Salah Satu Pembayaran Tidak Boleh Nol, Silakan Cek Kembali Detail Pembayaran","Peringatan");
					return;
				}
			}
			if (paymentRule2.isVisible()) {
				if (nilaiBayar2.compareTo(Env.ZERO) <= 0) {
					FDialog.warn(windowNo,null,"","Salah Satu Pembayaran Tidak Boleh Nol, Silakan Cek Kembali Detail Pembayaran","Peringatan");
					return;
				}
			}
			if (paymentRule3.isVisible()) {
				if (nilaiBayar3.compareTo(Env.ZERO) <= 0) {
					FDialog.warn(windowNo,null,"","Salah Satu Pembayaran Tidak Boleh Nol, Silakan Cek Kembali Detail Pembayaran","Peringatan");
					return;
				}
			}

			if (priceList.isVisible()) {

				KeyNamePair pl = priceList.getSelectedItem().toKeyNamePair();

				int pl_id = pl.getKey();
				StringBuilder sqlPriceList = new StringBuilder();
				sqlPriceList.append("SELECT M_PriceList_ID FROM M_PriceList WHERE AD_Client_ID =? AND IsActive = 'Y' AND isSoPriceList= 'Y'"
								+ " AND  M_PriceList_ID IN "
								+ "(SELECT M_PriceList_ID FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ? AND M_PriceList_ID = ?)");

				M_Pricelist_ID = DB.getSQLValueEx(ctx.toString(),sqlPriceList.toString(), new Object[] { AD_Client_ID,AD_Client_ID, CreatedByPOS_ID, pl_id });

			}

			// cek required input
			msg = checkBeforeProcess(AD_Org_ID, IsMultiPL, M_Pricelist_ID,
					C_BPartner_ID, M_WareHouse_ID, C_PaymentTerm_ID,
					SalesRep_ID, CreatedByPOS_ID, C_Currency_ID,
					C_BankAccount_ID, paymentRule, leaseProv, tipeBayar1,
					tipeBayar2, IsTipeBayar2, IsPaidAll, salesTable);

			if (msg != "") {
				FDialog.warn(form.getWindowNo(), null, msg, "", "Peringatan");
				return;
			}

			String cekImei = "";
			cekImei = checkImeiExist(salesTable, windowNo);
			if (cekImei != "") {
				return;
			}
			
			//cek Lease Provider
			if(leasingProviderSearch.getValue()!= null){
				String leaseProvid = "";
				leaseProvid = cekBankAccount(leasingProviderSearch.getValue().toString());
				if(leaseProvid != ""||!leaseProvid.isEmpty()){
					FDialog.warn(form.getWindowNo(), null, leaseProvid, "", "Peringatan");
					return;
				}
			}
			
			// cek Stok
			boolean isStocked = true;
			String Stocked = checkStok(salesTable, windowNo);

			if (Stocked != "") {
				isStocked = false;
			} else if (Stocked == "") {
				isStocked = true;
			}

			if (!isStocked && deliveryCheck.isChecked()) {

				FDialog.warn(form.getWindowNo(), null, "", Stocked,"Peringatan");
				return;
			}

			// cek must have imei
			for (int i = 0; i < salesTable.getRowCount(); i++) {
				KeyNamePair cekProd = (KeyNamePair) salesTable.getValueAt(i, 1);
				int prod_id = cekProd.getKey();
				MProduct pr = new MProduct(ctx, prod_id, null);
				int M_AttributeSet_ID = pr.getM_AttributeSet_ID();
				String docAc = docActionList.getSelectedItem().getValue().toString();
				if (M_AttributeSet_ID > 0) {
					if (!isStocked && !deliveryCheck.isChecked()&& docAc.toUpperCase().equals("COMPLETE")) {

						FDialog.warn(form.getWindowNo(),null,"","Product "+ pr.getName()+ " Menggunakan IMEI dan Tidak Ada Stok, Silakan Pilih Aksi Dokumen Prepare Untuk Melanjutkan Transaksi Dan Isikan IMEI Secara Manual Pada Sales Order Line","Peringatan");
						return;
					}
				}
			}

			// cek multiLocator
			boolean isMultiLoc = multiLocatorCheck(salesTable);
			
			
			String loca = "";
			Integer M_Locator_ID = 0;
			if (!isMultiLoc){
				
				loca="";

				for(int i=0; i<salesTable.getRowCount();i++){
					String rs= (String) salesTable.getValueAt(i, 7);
					if(!rs.isEmpty() && rs!=null && rs!=""){
						loca = rs;
					}
				}
					
					
				String sqlLoca = "SELECT M_Locator_ID FROM M_Locator WHERE AD_Client_ID = ? AND value = '"+ loca + "'";

				M_Locator_ID = DB.getSQLValueEx(null, sqlLoca, AD_Client_ID);
				
				
				if (M_Locator_ID <0){
					M_Locator_ID = (Integer)locatorSearch.getValue();
					
					if(M_Locator_ID < 0 || M_Locator_ID == null){
						M_Locator_ID = 0;
					}
				}
				
			}
			
			//multilocator
			if (isCompleteMultiLocator) {
				if (isMultiLoc && !isStocked) {
					deliveryCheck.setChecked(false);
				} else if (isMultiLoc && isStocked && deliveryCheck.isChecked()) {
					deliveryCheck.setChecked(true);
				}
			} else {
				if (isMultiLoc) {
					deliveryCheck.setChecked(false);
				}
			}
			//end

			if (payment1.compareTo(grandtot) >= 0) {
				IsFullPay1 = true;
			} else if (payment2.compareTo(Env.ZERO) > 0) {
				IsPay2 = true;
			}

			if (tipeBayar1.toUpperCase().equals("TUNAI")) {
				if (IsFullPay1) {
					IsCashX = true;
				} else if (!IsFullPay1 && tipeBayar2.toUpperCase().equals("HUTANG") && IsPay2) {
					IsUangMuka = true;
					IsHutang = true;
				} else if (!IsFullPay1 && tipeBayar2.toUpperCase().equals("LEASING") && IsPay2) {
					IsUangMuka = true;
					IsLeasing = true;
				} else if (!IsFullPay1 && tipeBayar2.toUpperCase().equals("BANK") && IsPay2) {
					IsCashB = true;
					IsCashX = true;
				}

			} else if (tipeBayar1.toUpperCase().equals("BANK")) {

				if (IsFullPay1) {
					IsCashB = true;
				} else if (!IsFullPay1 && tipeBayar2.toUpperCase().equals("TUNAI") && IsPay2) {
					IsCashB = true;
					IsCashX = true;
				} else if (!IsFullPay1 && tipeBayar2.toUpperCase().equals("HUTANG") && IsPay2) {
					IsBank = true;
					IsHutang = true;
				} else if (!IsFullPay1 && tipeBayar2.toUpperCase().equals("LEASING") && IsPay2) {
					IsBank = true;
					IsLeasing = true;
				}

			} else if (tipeBayar1.toUpperCase().equals("LEASING") && IsFullPay1) {
				IsLeasing = true;

			} else if (tipeBayar1.toUpperCase().equals("HUTANG") && IsFullPay1) {
				IsHutang = true;
			}

			// process create order
			String sql = "SELECT C_DocType_ID FROM C_DocType "
					+ " WHERE IsSoTrx = 'Y' " + " AND AD_Client_ID = ?"
					+ " AND DocSubTypeSO = ?";

			MOrder ord = new MOrder(ctx, 0, null);
			// record for POS
			MDecorisPOS decPos = new MDecorisPOS(ctx, 0, null);

			boolean isPick = deliveryCheck.isChecked();

			if (IsCashX && !IsCashB) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_POSOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_PrepayOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (IsCashB && !IsCashX) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_PrepayOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (IsCashX && IsCashB) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_PrepayOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (IsUangMuka && IsHutang) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_PrepayOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (IsUangMuka && IsLeasing) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_PrepayOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (!IsUangMuka && IsHutang) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_StandardOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (!IsUangMuka && IsLeasing) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_StandardOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (IsBank && IsHutang) {
				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_StandardOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}
			} else if (IsBank && IsLeasing) {
				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_PrepayOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			} else if (IsHutang && IsLeasing) {

				if (isPick) {

					docType = MDocType.DOCSUBTYPESO_OnCreditOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("P");

				} else if (!isPick) {

					docType = MDocType.DOCSUBTYPESO_StandardOrder;
					C_DocType_ID = DB.getSQLValue(ctx.toString(),sql.toString(),new Object[] { Env.getAD_Client_ID(ctx), docType });
					decPos.setC_DocType_ID(C_DocType_ID);
					decPos.setDeliveryViaRule("D");

				}

			}

			StringBuilder sqlBPLoc = new StringBuilder();
			sqlBPLoc.append("SELECT C_BPartner_Location_ID ");
			sqlBPLoc.append("FROM C_BPartner_Location ");
			sqlBPLoc.append("WHERE C_BPartner_ID = ? ");
			int C_BPartner_Location_ID = DB.getSQLValueEx(ctx.toString(),sqlBPLoc.toString(), new Object[] { C_BPartner_ID });

			decPos.setAD_Org_ID((int) org.getValue());
			decPos.setC_BPartner_ID((int) bpartnerSearch.getValue());
			decPos.setC_BPartner_Location_ID(C_BPartner_Location_ID);
			decPos.setDateOrdered((Timestamp) dateField.getValue());
			decPos.setDescription((String) description.getValue().toString());
			decPos.setM_PriceList_ID(M_Pricelist_ID);
			decPos.setDiscountAmt(totalDisc);
			decPos.setM_Warehouse_ID((int) warehouseSearch.getValue());
			decPos.setDeliveryRule(MOrder.DELIVERYRULE_AfterReceipt);
			decPos.setC_PaymentTerm_ID(C_PaymentTerm_ID);
			decPos.setPaymentRule(MOrder.PAYMENTRULE_MixedPOSPayment);
			decPos.setCreatedByPOS_ID(CreatedByPOS_ID);
			decPos.setSalesRep_ID(SalesRep_ID);
			decPos.setIsPickup(isPick);
			decPos.setdpp(nilaiDpp);
			decPos.setGrandTotal(nilaiGrandTotal);
			decPos.setTotalLines(totalPrice);
			decPos.setTaxAmt(nilaiPajak);
			decPos.setPayType1(tipeBayar1.toUpperCase());
			if(tipeBayar1.toUpperCase().equals("LEASING")||
					tipeBayar2.toUpperCase().equals("LEASING")||
					tipeBayar3.toUpperCase().equals("LEASING")){
				
				decPos.set_ValueOfColumnReturningBoolean("IsLeasing", true);
				
				if(leasingProviderSearch.getValue().toString().toUpperCase().equals("SPEKTRA")){
					decPos.set_ValueOfColumnReturningBoolean("IsSpektra", true);
					
				}
				
			}
			
			//manual documentno
			if(isManualDocumentNo){
				decPos.setDocumentNo(noNota.getValue());
			}

			if (tipeBayar2 != "" || !tipeBayar2.isEmpty()) {
				decPos.setPayType2(tipeBayar2.toUpperCase());
				decPos.setPembayaran2(nilaiBayar2);
			}

			decPos.setPembayaran1(nilaiBayar1);
			decPos.setIsPembatalan(false);
			decPos.setIsSOTrx(true);
			decPos.setIsPenjualan(true);
			decPos.setIsPembayaran(false);
			decPos.setIsReceipt(false);
			decPos.saveEx();
			decPOSd = decPos;
			
			C_DecorisPOSPrint_ID = decPos.getC_DecorisPOS_ID();

			for (int i = 0; i < miniTable.getRowCount(); i++) {
				boolean isSelect = (boolean) miniTable.getValueAt(i, 0);
				BigDecimal qty = (BigDecimal) miniTable.getValueAt(i, 2);
				BigDecimal price = (BigDecimal) miniTable.getValueAt(i, 4);
				BigDecimal totalPrice = qty.multiply(price);
				KeyNamePair product = (KeyNamePair) miniTable.getValueAt(i, 1);
				String serno = (String) miniTable.getValueAt(i, 8);
				String rs= (String) miniTable.getValueAt(i, 7);
				int M_Locator_ID_Multi = 0;
				
			//	String sqlProduct = "SELECT M_Product_ID FROM M_Product WHERE AD_Client_ID = ? AND name = '"+ product.toString() + "'";
			//	int Product_ID = DB.getSQLValue(null, sqlProduct.toString(),new Object[] { AD_Client_ID });
			//	int prod_ID = product.getKey();
				int Product_ID = product.getKey();

				MProduct prod = new MProduct(ctx, Product_ID, null);

				String sqlSerno = "SELECT M_AttributeSetInstance_ID FROM M_AttributeSetInstance WHERE AD_Client_ID = ? AND Serno = '"+ serno.toString() + "'";
				int M_AttributeSetInstance_ID = DB.getSQLValue(null,sqlSerno.toString(), new Object[] { AD_Client_ID });

				int taxCategory = prod.getC_TaxCategory_ID();

				if (isSelect) {
					miniTable.setValueAt(false, i, 0);
					miniTable.setColumnReadOnly(i, true);
				}

				MTaxCategory cat = new MTaxCategory(ctx, taxCategory, null);
				String taxCatName = cat.getName();

				String sqlTax = "SELECT C_Tax_ID FROM C_Tax WHERE AD_Client_ID = ? AND Name = '"
						+ taxCatName + "'";
				int C_Tax_ID = DB.getSQLValueEx(cat.get_TrxName(), sqlTax,
						new Object[] { AD_Client_ID });

				X_C_DecorisPOSLine decPOSLine = new X_C_DecorisPOSLine(ctx, 0,null);

				String sqlLine = "SELECT COALESCE(MAX(Line),0)+10 FROM C_DecorisPOSLine WHERE C_DecorisPOS_ID =?";
				int ii = DB.getSQLValue(decPos.get_TrxName(), sqlLine,decPos.getC_DecorisPOS_ID());

				// DecorisLine
				decPOSLine.setClientOrg(decPos);
				decPOSLine.setC_DecorisPOS_ID(decPos.getC_DecorisPOS_ID());
				
				
				if(isMultiLoc && prod.getProductType().toUpperCase().equals("I")){
					String sqlLoca = "SELECT M_Locator_ID FROM M_Locator WHERE AD_Client_ID = ? AND value = '"+ rs + "'";
					M_Locator_ID_Multi = DB.getSQLValueEx(Env.getCtx().toString(), sqlLoca, AD_Client_ID);
				}
				
				
				if(M_Locator_ID_Multi <= 0){
					M_Locator_ID_Multi = M_Locator_ID;
				}
				
				if(prod.getProductType().toString().toUpperCase().equals("I")){
					if(!isMultiLoc){
						decPOSLine.setM_Locator_ID(M_Locator_ID);
					}else if(isMultiLoc){
						decPOSLine.setM_Locator_ID(M_Locator_ID_Multi);
					}
				}
				decPOSLine.setM_Product_ID(Product_ID);
				decPOSLine.setC_UOM_ID(prod.getC_UOM_ID());
				decPOSLine.setC_Tax_ID(C_Tax_ID);
				decPOSLine.setPriceList((BigDecimal) miniTable.getValueAt(i, 3));
				decPOSLine.setPriceEntered((BigDecimal) miniTable.getValueAt(i,4));
				decPOSLine.setQtyOrdered((BigDecimal) miniTable.getValueAt(i, 2));
				decPOSLine.setLineNetAmt(totalPrice);
				decPOSLine.setLine(ii);
				if (M_AttributeSetInstance_ID > 0) {
					decPOSLine.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
				}

				decPOSLine.saveEx();
			}

			ord.setAD_Org_ID(decPos.getAD_Org_ID());
			ord.setC_BPartner_ID(decPos.getC_BPartner_ID());
			ord.setC_BPartner_Location_ID(decPos.getC_BPartner_Location_ID());
			ord.setPOReference(decPos.getDocumentNo());
			ord.setDescription(decPos.getDescription());
			ord.setDeliveryRule(decPos.getDeliveryRule());
			ord.setDeliveryViaRule(decPos.getDeliveryViaRule());
			ord.setSalesRep_ID(decPos.getSalesRep_ID());
			ord.setC_Currency_ID(303);
			ord.setC_DocType_ID(decPos.getC_DocType_ID());

			ord.setC_DocTypeTarget_ID(decPos.getC_DocType_ID());
			if(isManualDocumentNo){
				ord.setDocumentNo(decPos.getDocumentNo());
			}
			
			if (!isMultiLoc && M_Locator_ID != 0) {
				ord.set_ValueNoCheck("M_Locator_ID", M_Locator_ID);
			}

			ord.setDateOrdered(decPos.getDateOrdered());
			ord.setDateAcct(decPos.getDateOrdered());
			ord.setDatePromised(decPos.getDateOrdered());
			if (!deliveryCheck.isChecked()) {
				ord.setDatePromised((Timestamp) dateDOField.getValue());
			}
			
			ord.setM_Warehouse_ID(decPos.getM_Warehouse_ID());
			ord.setC_PaymentTerm_ID(decPos.getC_PaymentTerm_ID());
			ord.setM_PriceList_ID(decPos.getM_PriceList_ID());
			ord.setTotalLines(decPos.getTotalLines());
			ord.setGrandTotal(decPos.getGrandTotal());
			ord.setPaymentRule(decPos.getPaymentRule());
			ord.set_CustomColumn("CreatedByPOS_ID", decPos.getCreatedByPOS_ID());
			ord.setIsSelfService(true);
			ord.setIsSOTrx(decPos.isSOTrx());
			ord.setPOReference(decPos.getDocumentNo());
			ord.saveEx();

			C_OrderPrint_ID = ord.getC_Order_ID();

			decPos.setC_Order_ID(ord.getC_Order_ID());
			decPos.saveEx();

			MDecorisPOS dec = new MDecorisPOS(ctx, decPos.getC_DecorisPOS_ID(),null);

			X_C_DecorisPOSLine DecLines[] = dec.getLines();

			for (X_C_DecorisPOSLine line : DecLines) {

				MOrderLine ordLine = new MOrderLine(ctx, 0, null);

				// ordLine
				ordLine.setClientOrg(ord);
				ordLine.setC_Order_ID(decPos.getC_Order_ID());
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
				ordLine.saveEx();

				line.setC_OrderLine_ID(ordLine.getC_OrderLine_ID());
				line.saveEx();

			}

			// listPospay = new ArrayList<Map<String,BigDecimal>>();

			// add payment
			if (tipeBayar1 != "" && !tipeBayar1.isEmpty()) {

				mapPospay.put(tipeBayar1, payment1);

			}
			if (tipeBayar2 != "" && !tipeBayar2.isEmpty()) {

				mapPospay.put(tipeBayar2, payment2);

			}

			if (tipeBayar3 != "" && !tipeBayar3.isEmpty()) {

				mapPospay.put(tipeBayar3, payment3);

			}
			// listPospay.add(mapPospay);

			for (String key : mapPospay.keySet()) {

				String tipebayar = key.toUpperCase();
				BigDecimal payAmt = mapPospay.get(key);
				String whereClause = "";
				String tenderName = "";
				int C_BankAcct_ID = 0;

				if (tipebayar.equals("TUNAI") || tipebayar.equals("LEASING")|| tipebayar.equals("BANK")) {

					if (tipebayar.equals("TUNAI") || tipebayar.equals("BANK")) {
						String sqlBank = "";
						tenderName = MPayment.TENDERTYPE_Cash;
						whereClause = "Cash";

						if (tipebayar.equals("TUNAI")) {

							sqlBank = "SELECT C_BankAccount_ID FROM C_POS WHERE CreatedByPOS_ID = ? AND AD_Client_ID = ?";
							C_BankAcct_ID = DB.getSQLValueEx(ctx.toString(),sqlBank.toString(), new Object[] {CreatedByPOS_ID, AD_Client_ID });

						} else if (tipebayar.equals("BANK")) {
							Integer b_acct_id = (Integer) bankAccountSearch.getValue();
							if (b_acct_id == null) {
								b_acct_id = 0;
							}
							C_BankAcct_ID = b_acct_id;
						}

					} else if (tipebayar.equals("LEASING")) {

						tenderName = MPayment.TENDERTYPE_Lease;
						whereClause = "Leasing";
						String sqlBank = "SELECT C_BankAccount_ID FROM C_BankAccount WHERE leaseprovider = '"
								+ leaseProv
								+ "'"
								+ " AND AD_Client_ID = "
								+ Env.getAD_Client_ID(ctx);
						C_BankAcct_ID = DB.getSQLValueEx(ctx.toString(),sqlBank.toString());

					}

					String sqlTender = "SELECT C_POSTenderType_ID FROM C_POSTenderType WHERE name = '"+ whereClause + "'";
					int C_POSTenderType_ID = DB.getSQLValueEx(ctx.toString(),sqlTender.toString());
					createPOSPayment(ord.getAD_Org_ID(), ord.getC_Order_ID(),C_POSTenderType_ID, tenderName, payAmt, leaseProv,C_BankAcct_ID);

				}
			}

			if (ord != null) {

				if (m_docAction != null && m_docAction.length() > 0) {
					ord.setDocAction(m_docAction);
					if (!m_docAction.equals("PR")) {
						if (!ord.processIt(m_docAction)) {
							log.warning("Order Process Failed: " + ord + " - "+ ord.getProcessMsg());
							throw new IllegalStateException("Invoice Process Failed: " + ord + " - "+ ord.getProcessMsg());
						}
					}
				}
				
				ord.saveEx();

				C_OrderPrint_ID = ord.getC_Order_ID();
				// infoGeneratedDocument(ord.getC_Order_ID(), windowNo);
				printButton.setEnabled(true);
				printSuratJalan.setEnabled(true);
				if(isThermalPrint){
				printThermalButton.setEnabled(true);
				}
				addBusinessPartner.setEnabled(false);

				// create info generated document

			}

			if (isMultiLoc) {

				int M_InOut_ID = checkShipmentRelated(ord.getC_Order_ID());

				if (M_InOut_ID > 0) {

					MInOut InOut = new MInOut(ctx, M_InOut_ID, null);
					String DocStatus = InOut.getDocStatus();
					MInOutLine lines[] = InOut.getLines();

					if (DocStatus.equals(MInOut.DOCSTATUS_Drafted)) {

						for (int i = 0; i < miniTable.getRowCount(); i++) {

							KeyNamePair product = (KeyNamePair) miniTable.getValueAt(i, 1);
							String POSLoc = (String) miniTable.getValueAt(i, 7);
							String sqlProduct = "SELECT M_Product_ID FROM M_Product WHERE AD_Client_ID = ? AND name = '"+ product.toString() + "'";
							String sqlLoc = "SELECT M_Locator_ID FROM M_Locator WHERE value = '"+ POSLoc + "'" + " AND AD_Client_ID = ?";
							int POSLineNo = (i + 1) * 10;

							int POSProd_ID = DB.getSQLValue(ctx.toString(),sqlProduct.toString(),new Object[] { AD_Client_ID });
							int POSLoc_ID = DB.getSQLValue(ctx.toString(),sqlLoc.toString(),new Object[] { AD_Client_ID });
							BigDecimal POSqQty = ((BigDecimal) miniTable.getValueAt(i, 2)).setScale(2);

							for (MInOutLine line : lines) {
								int shipProd_ID = line.getM_Product_ID();
								BigDecimal shipQty = line.getQtyEntered().setScale(2);
								int shipLineNo = line.getLine();

								if (POSProd_ID == shipProd_ID && POSqQty.compareTo(shipQty) == 0&& POSLineNo == shipLineNo) {

									line.setM_Locator_ID(POSLoc_ID);
									line.saveEx();

								}

							}
						}
					}
				}

			}
			decPos.setProcessed(true);
			noNota.setText(decPos.getDocumentNo());
			
			infoGeneratedDocument(ord.getC_Order_ID(), form.getWindowNo());

			// clear data table

			lockData();
			}catch(Exception err){
				
				FDialog.warn(windowNo,null,"",err.toString(),"Peringatan");
				//log.log(Level.SEVERE, null, err);
				processButton.setEnabled(true);
				
				X_C_DecorisPOSLine[] lines = decPOSd.getLines();
				
				for (X_C_DecorisPOSLine line : lines){
					
					line.delete(true);
					
				}
				decPOSd.delete(true);

				
			}
		} else if (e.getTarget().equals(payruleList1)) {

			// leasing
			if (payruleList1.getSelectedItem().toString().toUpperCase().equals("LEASING")
					|| payruleList2.getSelectedItem().toString().toUpperCase().equals("LEASING")
					|| payruleList3.getSelectedItem().toString().toUpperCase().equals("LEASING")) {

				leasingProviderLabel.setVisible(true);
				leasingProviderSearch.setVisible(true);

			} else {

				leasingProviderLabel.setVisible(false);
				leasingProviderSearch.setVisible(false);
				leasingProviderSearch.setValue(null);
			}

			// bank Account
			if (payruleList1.getSelectedItem().toString().toUpperCase().equals("BANK")
					|| payruleList2.getSelectedItem().toString().toUpperCase().equals("BANK")
					|| payruleList3.getSelectedItem().toString().toUpperCase().equals("BANK")) {

				bankAccountLabel.setVisible(true);
				bankAccountSearch.setVisible(true);

			} else {

				bankAccountLabel.setVisible(false);
				bankAccountSearch.setVisible(false);
				bankAccountSearch.setValue(null);
			}

		} else if (e.getTarget().equals(payruleList2)) {

			// leasing
			if (payruleList1.getSelectedItem().toString().toUpperCase().equals("LEASING")
					|| payruleList2.getSelectedItem().toString().toUpperCase().equals("LEASING")
					|| payruleList3.getSelectedItem().toString().toUpperCase().equals("LEASING")) {

				leasingProviderLabel.setVisible(true);
				leasingProviderSearch.setVisible(true);

			} else {

				leasingProviderLabel.setVisible(false);
				leasingProviderSearch.setVisible(false);
				leasingProviderSearch.setValue(null);
			}

			// bank Account
			if (payruleList1.getSelectedItem().toString().toUpperCase().equals("BANK")
					|| payruleList2.getSelectedItem().toString().toUpperCase().equals("BANK")
					|| payruleList3.getSelectedItem().toString().toUpperCase().equals("BANK")) {

				bankAccountLabel.setVisible(true);
				bankAccountSearch.setVisible(true);

			} else {

				bankAccountLabel.setVisible(false);
				bankAccountSearch.setVisible(false);
				bankAccountSearch.setValue(null);
			}

		} else if (e.getTarget().equals(payruleList3)) {

			// leasing
			if (payruleList1.getSelectedItem().toString().toUpperCase().equals("LEASING")
					|| payruleList2.getSelectedItem().toString().toUpperCase().equals("LEASING")
					|| payruleList3.getSelectedItem().toString().toUpperCase().equals("LEASING")) {

				leasingProviderLabel.setVisible(true);
				leasingProviderSearch.setVisible(true);

			} else {

				leasingProviderLabel.setVisible(false);
				leasingProviderSearch.setVisible(false);
				leasingProviderSearch.setValue(null);
			}

			// bank Account
			if (payruleList1.getSelectedItem().toString().toUpperCase().equals("BANK")
					|| payruleList2.getSelectedItem().toString().toUpperCase().equals("BANK")
					|| payruleList3.getSelectedItem().toString().toUpperCase().equals("BANK")) {

				bankAccountLabel.setVisible(true);
				bankAccountSearch.setVisible(true);

			} else {

				bankAccountLabel.setVisible(false);
				bankAccountSearch.setVisible(false);
				bankAccountSearch.setValue(null);
			}

		} else if (e.getTarget().equals(newOrderButton)) {

			clearData();

		} else if((e.getTarget().equals(addBusinessPartner))){
		
			addBP();
		
		}else if (e.getTarget().equals(docActionList)) {

			if (docActionList.getSelectedItem().toString().toUpperCase().equals("COMPLETE")) {
				m_docAction = "CO";
			} else if (docActionList.getSelectedItem().toString().toUpperCase().equals("PREPARE")) {
				m_docAction = "PR";
			}

		} else if (e.getTarget().equals(priceList)) {

			KeyNamePair pl = priceList.getSelectedItem().toKeyNamePair();
			int pl_id = pl.getKey();
			StringBuilder sqlPriceList = new StringBuilder();
			sqlPriceList.append("SELECT M_PriceList_ID FROM M_PriceList WHERE AD_Client_ID =? AND IsActive = 'Y' AND isSoPriceList= 'Y'"
							+ " AND  M_PriceList_ID IN "
							+ "(SELECT M_PriceList_ID FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ? AND M_PriceList_ID = ?)");

			M_Pricelist_ID = DB.getSQLValueEx(ctx.toString(),sqlPriceList.toString(), new Object[] { AD_Client_ID,AD_Client_ID, CreatedByPOS_ID, pl_id });

			if (M_Pricelist_ID > 0) {

				priceList.setEnabled(false);

			}

		} else if (e.getTarget().equals(printButton)) {
			String trxName = Trx.createTrxName("posprint");
			String url = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/reports/";
			MProcess proc = new MProcess(Env.getCtx(), 1000074, trxName);
			MPInstance instance = new MPInstance(proc, proc.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo("Print Nota Penjualan", 1000074);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			

			String namatoko = "";
			String phone = "";
			String phone2 = "";
			String fax = "";
			String email = "";
			String alamat = "";
			String kota = "";

			
			if(CreatedByPOS_ID> 0){
				
				String getNamaToko = "SELECT NamaToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				namatoko = DB.getSQLValueStringEx(ctx.toString(), getNamaToko, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getPhone = "SELECT TeleponToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				phone = DB.getSQLValueStringEx(ctx.toString(), getPhone, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getPhone2 = "SELECT TeleponToko2 FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				phone2 = DB.getSQLValueStringEx(ctx.toString(), getPhone2, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getFax = "SELECT Fax FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				fax = DB.getSQLValueStringEx(ctx.toString(), getFax, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getEmail = "SELECT EMail FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				email = DB.getSQLValueStringEx(ctx.toString(), getEmail, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getAlamat = "SELECT AlamatToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				alamat = DB.getSQLValueStringEx(ctx.toString(), getAlamat, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getKota = "SELECT Kota FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				kota = DB.getSQLValueStringEx(ctx.toString(), getKota, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
			}
		
			ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			list.add(new ProcessInfoParameter("C_Order_ID", C_OrderPrint_ID,null, null, null));
			list.add(new ProcessInfoParameter("url_path", url, null, null, null));
			list.add(new ProcessInfoParameter("NamaToko", namatoko, null, null, null));
			list.add(new ProcessInfoParameter("TeleponToko", phone, null, null, null));
			list.add(new ProcessInfoParameter("TeleponToko2", phone2, null, null, null));
			list.add(new ProcessInfoParameter("Fax", fax, null, null, null));
			list.add(new ProcessInfoParameter("EMail", email, null, null, null));
			list.add(new ProcessInfoParameter("AlamatToko", alamat, null, null, null));
			list.add(new ProcessInfoParameter("Kota", kota, null, null, null));

			ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			list.toArray(pars);
			pi.setParameter(pars);
			
			Trx trx = Trx.get(trxName, true);
			trx.commit();

			ProcessUtil.startJavaProcess(Env.getCtx(), pi,Trx.get(trxName, true));
			
			StringBuilder sqlIsPrint = new StringBuilder();
			sqlIsPrint.append("UPDATE C_DecorisPOS SET IsPrinted = 'Y' ");
			sqlIsPrint.append("WHERE AD_Client_ID = ? ");
			sqlIsPrint.append("AND C_DecorisPOS_ID = ?");
			
			DB.executeUpdateEx(sqlIsPrint.toString(), new Object[]{AD_Client_ID,C_DecorisPOSPrint_ID},null);

		}else if (e.getTarget().equals(printThermalButton)) {
			
			String ket1 = "";
			String ket2 = "";
			String namatoko = "";
			String phone = "";
			String phone2 = "";
			String fax = "";
			String email = "";
			String alamat = "";
			String kota = "";

			
			if(CreatedByPOS_ID> 0){
				
				String sqlket1 = "SELECT ParameterThermalStruk1 FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				ket1 = DB.getSQLValueStringEx(ctx.toString(), sqlket1, new Object[] {AD_Client_ID, CreatedByPOS_ID });
	
				String sqlket2 = "SELECT ParameterThermalStruk2 FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				ket2 = DB.getSQLValueStringEx(ctx.toString(), sqlket2, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getNamaToko = "SELECT NamaToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				namatoko = DB.getSQLValueStringEx(ctx.toString(), getNamaToko, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getPhone = "SELECT TeleponToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				phone = DB.getSQLValueStringEx(ctx.toString(), getPhone, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getPhone2 = "SELECT TeleponToko2 FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				phone2 = DB.getSQLValueStringEx(ctx.toString(), getPhone2, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getFax = "SELECT Fax FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				fax = DB.getSQLValueStringEx(ctx.toString(), getFax, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getEmail = "SELECT EMail FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				email = DB.getSQLValueStringEx(ctx.toString(), getEmail, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getAlamat = "SELECT AlamatToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				alamat = DB.getSQLValueStringEx(ctx.toString(), getAlamat, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getKota = "SELECT Kota FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				kota = DB.getSQLValueStringEx(ctx.toString(), getKota, new Object[] {AD_Client_ID, CreatedByPOS_ID });

				
			}
						
			String trxName = Trx.createTrxName("posprintthermal");
			MProcess proc = new MProcess(Env.getCtx(), 1000084, trxName);
			MPInstance instance = new MPInstance(proc, proc.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo("Print Struk Penjualan", 1000084);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			
		
			ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			list.add(new ProcessInfoParameter("C_Order_ID", C_OrderPrint_ID,null, null, null));
			list.add(new ProcessInfoParameter("ket1", ket1, null, null, null));
			list.add(new ProcessInfoParameter("ket2", ket2, null, null, null));
			list.add(new ProcessInfoParameter("NamaToko", namatoko, null, null, null));
			list.add(new ProcessInfoParameter("TeleponToko", phone, null, null, null));
			list.add(new ProcessInfoParameter("TeleponToko2", phone2, null, null, null));
			list.add(new ProcessInfoParameter("Fax", fax, null, null, null));
			list.add(new ProcessInfoParameter("EMail", email, null, null, null));
			list.add(new ProcessInfoParameter("AlamatToko", alamat, null, null, null));
			list.add(new ProcessInfoParameter("Kota", kota, null, null, null));
			
			
			ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			list.toArray(pars);
			pi.setParameter(pars);
			
			Trx trx = Trx.get(trxName, true);
			trx.commit();

			ProcessUtil.startJavaProcess(Env.getCtx(), pi,Trx.get(trxName, true));
			
			StringBuilder sqlIsPrint = new StringBuilder();
			sqlIsPrint.append("UPDATE C_DecorisPOS SET IsPrinted = 'Y' ");
			sqlIsPrint.append("WHERE AD_Client_ID = ? ");
			sqlIsPrint.append("AND C_DecorisPOS_ID = ?");
			
			DB.executeUpdateEx(sqlIsPrint.toString(), new Object[]{AD_Client_ID,C_DecorisPOSPrint_ID},null);
			
		}else if (e.getTarget().equals(printSuratJalan)) {
			
			String trxName = Trx.createTrxName("PrintSJ");
			//String url = "D:\\SourceCode\\iDempiereBase\\reports\\";
			String url = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/reports/";
			MProcess proc = new MProcess(Env.getCtx(), 330053, trxName);
			MPInstance instance = new MPInstance(proc, proc.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo("Print SJ", 330053);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			

			String namatoko = "";
			String phone = "";
			String phone2 = "";
			String fax = "";
			String email = "";
			String alamat = "";
			String kota = "";
			int M_InOutPrint_ID = checkShipmentRelated(C_OrderPrint_ID);
			if(M_InOutPrint_ID <= 0)
				return;
 

			
			if(CreatedByPOS_ID> 0){
				
				String getNamaToko = "SELECT NamaToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				namatoko = DB.getSQLValueStringEx(ctx.toString(), getNamaToko, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getPhone = "SELECT TeleponToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				phone = DB.getSQLValueStringEx(ctx.toString(), getPhone, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getPhone2 = "SELECT TeleponToko2 FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				phone2 = DB.getSQLValueStringEx(ctx.toString(), getPhone2, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getFax = "SELECT Fax FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				fax = DB.getSQLValueStringEx(ctx.toString(), getFax, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getEmail = "SELECT EMail FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				email = DB.getSQLValueStringEx(ctx.toString(), getEmail, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getAlamat = "SELECT AlamatToko FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				alamat = DB.getSQLValueStringEx(ctx.toString(), getAlamat, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
				String getKota = "SELECT Kota FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				kota = DB.getSQLValueStringEx(ctx.toString(), getKota, new Object[] {AD_Client_ID, CreatedByPOS_ID });
				
			}
		
			ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			list.add(new ProcessInfoParameter("M_InOut_ID", M_InOutPrint_ID,null, null, null));
			list.add(new ProcessInfoParameter("url_path", url, null, null, null));
			list.add(new ProcessInfoParameter("NamaToko", namatoko, null, null, null));
			list.add(new ProcessInfoParameter("TeleponToko", phone, null, null, null));
			list.add(new ProcessInfoParameter("TeleponToko2", phone2, null, null, null));
			list.add(new ProcessInfoParameter("Fax", fax, null, null, null));
			list.add(new ProcessInfoParameter("EMail", email, null, null, null));
			list.add(new ProcessInfoParameter("AlamatToko", alamat, null, null, null));
			list.add(new ProcessInfoParameter("Kota", kota, null, null, null));

			ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			list.toArray(pars);
			pi.setParameter(pars);
			
			Trx trx = Trx.get(trxName, true);
			trx.commit();

			ProcessUtil.startJavaProcess(Env.getCtx(), pi,Trx.get(trxName, true));
			
			StringBuilder sqlIsPrint = new StringBuilder();
			sqlIsPrint.append("UPDATE C_DecorisPOS SET IsPrintedsj = 'Y' ");
			sqlIsPrint.append("WHERE AD_Client_ID = ? ");
			sqlIsPrint.append("AND C_DecorisPOS_ID = ?");
			
			DB.executeUpdateEx(sqlIsPrint.toString(), new Object[]{AD_Client_ID,C_DecorisPOSPrint_ID},null);

			
			
		}
	}

	@Override
	public ADForm getForm() {

		return form;
	}

	@Override
	public void tableChanged(WTableModelEvent e) {
		boolean isUpdate = (e.getType() == WTableModelEvent.CONTENTS_CHANGED);

		String sqlIsForceLimitPrice = "SELECT IsForceLimitPrice FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ? AND M_PriceList_ID = ?";
		String forceLimitPrice = DB.getSQLValueStringEx(ctx.toString(),sqlIsForceLimitPrice, new Object[] { AD_Client_ID,CreatedByPOS_ID, M_Pricelist_ID });

		if (forceLimitPrice.toUpperCase().equals("Y")) {
			isForceLimitPrice = true;
		} else {
			isForceLimitPrice = false;
		}

		// Not a table update
		if (!isUpdate) {
			return;
		} else if (isUpdate) {
			imeiIndex = e.getFirstRow();
			rowIndex = e.getFirstRow();
			int col = e.getColumn();
			if (col > 0) {
				tableChangeCalculate(rowIndex, col, salesTable,form.getWindowNo(), isForceLimitPrice, M_Pricelist_ID);
				calculate();
				clearPembayaran();
			}
		}
	}

	public void calculate() {

		final double div = 0.90909091;
		BigDecimal divider = BigDecimal.valueOf(div);
		BigDecimal subTotal = Env.ZERO;

		totalPrice = totalPrices.setScale(2, RoundingMode.HALF_UP);
		nilaiGrandTotal = totalPrice.setScale(2, RoundingMode.HALF_UP);
		totalDiskon = totalDiskons.setScale(2, RoundingMode.HALF_DOWN);
		totalBeforeDiscount = totalBeforeDiscounts.setScale(2,RoundingMode.HALF_UP);

		for (int i = 0; i < salesTable.getRowCount(); i++) {
			String pajak = (String) salesTable.getValueAt(i, 9);

			if (pajak.toUpperCase().equals("PPN")) {
				Double dsub = 0.00;
				if (AD_Language.toUpperCase().equals("EN_US")) {
					dsub = Double.valueOf((String) salesTable.getValueAt(i, 6).toString().replaceAll(",", ""));
				} else if (AD_Language.toUpperCase().equals("IN_ID")) {
					String sub = (String) salesTable.getValueAt(i, 6).toString().replaceAll("\\.", "").replaceAll(",", ".");
					dsub = Double.valueOf(sub);
				}
				BigDecimal sub = BigDecimal.valueOf(dsub);
				subTotal = subTotal.add(sub);
			}
		}
		nilaiDpp = (subTotal.multiply(divider)).setScale(2,RoundingMode.HALF_DOWN);
		nilaiPajak = (subTotal.subtract(nilaiDpp)).setScale(2,RoundingMode.HALF_DOWN);

		dpp.setText(format.format(nilaiDpp));
		pajak.setText(format.format(nilaiPajak));
		diskon.setText(format.format(totalDiskon));
		grandtotal.setText(format.format(nilaiGrandTotal));
		total.setText(format.format(totalBeforeDiscount));

		if (payruleList1.getSelectedItem().toString().equalsIgnoreCase("Tunai")
				|| payruleList1.getSelectedItem().toString().equalsIgnoreCase("Bank")
				|| payruleList1.getSelectedItem().toString().equalsIgnoreCase("Kartu Kredit")) {

			paymentRule1.setText(format.format(nilaiGrandTotal));
			BigDecimal totalBayar = totalBayar();
			// Double jmlByar = Double.valueOf(totalBayar.toString());
			BigDecimal sisaByar = (nilaiGrandTotal.subtract(totalBayar)).setScale(2, RoundingMode.HALF_DOWN);
			sisaPembayaranLabel.setText("Kurang Bayar");
			sisaPembayaran.setText(format.format(sisaByar.abs()));
			
		}
	}

	public void lockData() {

		imeiList.setEnabled(false);
		clearButton.setEnabled(false);
		deleteButton.setEnabled(false);
		deliveryCheck.setEnabled(false);
		payruleList1.setEnabled(false);
		payruleList2.setEnabled(false);
		payruleList3.setEnabled(false);
		paymentRule1.setReadonly(true);
		paymentRule2.setReadonly(true);
		paymentRule3.setEnabled(false);
		sisaPembayaran.setReadonly(true);
		processButton.setEnabled(false);
		plusButton.setEnabled(false);
		plusButton2.setEnabled(false);
		dateField.setReadWrite(false);
		dateDOField.setReadWrite(false);
		org.setReadWrite(false);
		bpartnerSearch.setReadWrite(false);
		salesSearch.setReadWrite(false);
		warehouseSearch.setReadWrite(false);
		locatorSearch.setReadWrite(false);
		productSearch.setReadWrite(false);
		leasingProviderSearch.setReadWrite(false);
		bankAccountSearch.setReadWrite(false);
		docActionList.setEnabled(false);
		for (int i =0 ; i<salesTable.getColumnCount() ;i++){
			salesTable.setColumnReadOnly(i, true);
		}

	}

	public void clearData() {
		m_docAction = "";
		salesTable.setEnabled(true);
		bankAccountLabel.setVisible(false);
		bankAccountSearch.setVisible(false);
		dateDOField.setVisible(false);
		dateDOLabel.setVisible(false);
		bankAccountSearch.setReadWrite(true);
		bankAccountSearch.setValue(null);
		imeiList.setEnabled(true);
		imeiList.removeAllItems();
		clearButton.setEnabled(true);
		deleteButton.setEnabled(true);
		deliveryCheck.setEnabled(true);
		payruleList1.setEnabled(true);
		payruleList2.setEnabled(true);
		payruleList3.setEnabled(true);
		paymentRule1.setReadonly(false);
		paymentRule2.setReadonly(false);
		paymentRule3.setEnabled(true);
		sisaPembayaran.setEnabled(true);
		processButton.setEnabled(true);
		plusButton.setEnabled(true);
		printButton.setEnabled(false);
		printThermalButton.setEnabled(false);
		printSuratJalan.setEnabled(false);
		plusButton2.setVisible(false);
		dateField.setReadWrite(true);
		bpartnerSearch.setReadWrite(true);
		salesSearch.setReadWrite(true);
		salesSearch.setValue(null);
		warehouseSearch.setReadWrite(true);
		locatorSearch.setReadWrite(true);
		productSearch.setReadWrite(true);
		leasingProviderSearch.setReadWrite(true);
		docActionList.setEnabled(true);
		if (priceList.isVisible() && !priceList.isEnabled()) {
			priceList.setEnabled(true);
		}
		plusButton.setLabel("+");

		dpp.setText("0.00");
//		jumlahBayar = "0.00";
		total.setText("0.00");
		pajak.setText("0.00");
		diskon.setText("0.00");
		grandtotal.setText("0.00");
		paymentRule1.setText("0.00");
		paymentRule2.setText("0.00");
		sisaPembayaran.setText("0.00");

		paymentRule2.setVisible(false);
		payruleList2.setVisible(false);
		deliveryCheck.setChecked(true);
		productSearch.setReadWrite(false);

		nilaiDpp = Env.ZERO;
		totalPrice = Env.ZERO;
		nilaiPajak = Env.ZERO;
		totalDiskon = Env.ZERO;
		nilaiBayar1 = Env.ZERO;
		nilaiBayar2 = Env.ZERO;
		totalPrices = Env.ZERO;
		totalDiskons = Env.ZERO;
		nilaiGrandTotal = Env.ZERO;
		totalBeforeDiscounts = Env.ZERO;

		payruleList1.setSelectedIndex(0);
		payruleList2.setSelectedIndex(0);
		payruleList3.setSelectedIndex(0);

		description.setValue(null);
		productSearch.setValue(null);
		locatorSearch.setValue(null);
		bpartnerSearch.setValue(null);
		warehouseSearch.setValue(null);
		leasingProviderSearch.setValue(null);
		leasingProviderLabel.setVisible(false);
		leasingProviderSearch.setVisible(false);
		imeiList.setValue(null);
		noNota.setText("");
		C_OrderPrint_ID = 0;
		docActionList.setSelectedIndex(0);
		addBusinessPartner.setEnabled(true);
		
		dateField.setValue(System.currentTimeMillis());

		if (salesTable.getRowCount() > 0) {
			for (int i = 0; i < salesTable.getRowCount(); i++) {
				deletedata(0);
			}
			salesTable.clear();
			salesTable.setRowCount(0);
		}
	}

	private boolean multiLocatorCheck(IMiniTable miniTable) {

		String locA = "";
		String locN = "";
		boolean isMultiLocator = false;

		if (miniTable.getRowCount() > 1) {
			for (int i = 0; i < miniTable.getRowCount(); i++) {

				if (i == 0) {
					locA = (String) miniTable.getValueAt(i, 7);
				} else {
					locN = (String) miniTable.getValueAt(i, 7);
				}

				// cek locator
				if (locA != "" && locN != "") {
					if (!locA.toUpperCase().equals(locN.toUpperCase())) {

						isMultiLocator = true;

						return isMultiLocator;
					}
				}
			}
		}

		return isMultiLocator;
	}

	private String cekAkses() {
		String Msg = "";
		if (CreatedByPOS_ID <= 0) {
			noNota.setReadonly(true);
			dateField.setReadWrite(false);
			org.setReadWrite(false);
			bpartnerSearch.setReadWrite(false);
			salesSearch.setReadWrite(false);
			warehouseSearch.setReadWrite(false);
			locatorSearch.setReadWrite(false);
			productSearch.setReadWrite(false);
			imeiList.setEnabled(false);
			paymentRule1.setReadonly(true);
			description.setReadonly(true);

			docActionList.setEnabled(false);
			payruleList1.setEnabled(false);
			salesTable.setEnabled(false);
			processButton.setEnabled(false);
			newOrderButton.setEnabled(false);
			clearButton.setEnabled(false);
			deleteButton.setEnabled(false);
			deliveryCheck.setEnabled(false);
			plusButton.setEnabled(false);
			

			FDialog.info(windowNo, null, "","User Tidak Mempunyai Akses ke POS", "Info");
			Msg = "error";
		}

		return Msg;
	}

	private BigDecimal totalBayar() {

		BigDecimal totalBayar = Env.ZERO;

		Double dBayar1 = 0.00;
		Double dBayar2 = 0.00;
		Double dBayar3 = 0.00;

		if (!paymentRule1.getText().isEmpty()
				|| !paymentRule2.getText().isEmpty()
				|| !paymentRule3.getText().isEmpty()) {
			if (AD_Language.toUpperCase().equals("EN_US")) {

				dBayar1 = Double.valueOf(paymentRule1.getText().replaceAll(",",
						""));
				dBayar2 = Double.valueOf(paymentRule2.getText().replaceAll(",",
						""));
				dBayar3 = Double.valueOf(paymentRule3.getText().replaceAll(",",
						""));

			} else if (AD_Language.toUpperCase().equals("IN_ID")) {

				String dby1 = paymentRule1.getText().replaceAll("\\.", "")
						.replaceAll(",", ".");
				dBayar1 = Double.valueOf(dby1);
				String dby2 = paymentRule2.getText().replaceAll("\\.", "")
						.replaceAll(",", ".");
				dBayar2 = Double.valueOf(dby2);
				String dby3 = paymentRule3.getText().replaceAll("\\.", "")
						.replaceAll(",", ".");
				dBayar3 = Double.valueOf(dby3);

			}
		}

		totalBayar = BigDecimal.valueOf(dBayar1).add(
				BigDecimal.valueOf(dBayar2).add(BigDecimal.valueOf(dBayar3)));

		return totalBayar;

	}

	private void clearPembayaran() {

		if (payruleList2.isVisible() && paymentRule2.isVisible()) {
			payruleList2.setVisible(false);
			payruleList2.setSelectedIndex(0);
			paymentRule2.setVisible(false);
			paymentRule2.setText("0.00");
			plusButton.setLabel("+");
			plusButton2.setLabel("+");
			plusButton2.setVisible(false);

		}

		if (payruleList3.isVisible() && paymentRule3.isVisible()) {
			payruleList3.setVisible(false);
			paymentRule3.setVisible(false);
			payruleList3.setSelectedIndex(0);
			paymentRule3.setText("0.00");
		}

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
		final Decimalbox ZIP = new Decimalbox();
		final Textbox Telepon1 = new Textbox();
		final Textbox Telepon2 = new Textbox();
		final Textbox BPIDCardNumber = new Textbox();
		final Textbox SearchKey = new Textbox();
		final NumberBox CreditLimit = new NumberBox(true);
		final Checkbox isNamekey = new  Checkbox();
		final Checkbox isBpIDKey = new Checkbox();
		
		String title = "Tambah Pelanggan";
		Label LabelCity = new Label("Kota");
		Label LabelBPName = new Label("Nama");
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
		MLookup lookupCity = MLookupFactory.get(ctx, form.getWindowNo(), 0, 7048,DisplayType.TableDir);
		
		final WTableDirEditor cityList = new WTableDirEditor("C_City_ID", true, false, true,lookupCity);
		cityList.addValueChangeListener(this);
		cityList.setMandatory(true);
		
		
		//get credit limit from pos terminal
		String getCreditLimit = "SELECT CreditLimit FROM C_POS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?";
		BigDecimal creditLimit = DB.getSQLValueBDEx(null, getCreditLimit, new Object[]{AD_Client_ID,CreatedByPOS_ID});
		
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
		
		//Alamat 2
		row = rows.newRow();
		row.appendCellChild(LabelAddress2.rightAlign());
		row.appendCellChild(Address2);
		Address2.setRows(2);
		Address2.setHflex("true");
		
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
		
		//Telepon 1
		row = rows.newRow();
		row.appendCellChild(LabelTelepon1.rightAlign());
		row.appendCellChild(Telepon1);
		Telepon1.setHflex("true");
				
		//Telepon 2
		row = rows.newRow();
		row.appendCellChild(LabelTelepon2.rightAlign());
		row.appendCellChild(Telepon2);
		Telepon2.setHflex("true");
		
		//E-Mail
		row = rows.newRow();
		row.appendCellChild(LabelEMail.rightAlign());
		row.appendCellChild(EMail);
		EMail.setHflex("true");
		
		//Credit Limit
		row = rows.newRow();
		row.appendCellChild(LabelCreditLimit.rightAlign());
		row.appendCellChild(CreditLimit);
		CreditLimit.setHflex("true");
		CreditLimit.setValue(creditLimit);

			
		Vbox vbox = new Vbox();
		w.appendChild(vbox);
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
						bpartnerSearch.setValue(bp.getC_BPartner_ID());
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
				// TODO 
			}
		});
		w.doHighlighted();				
	}
	
	
}