package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.adempiere.util.Callback;
import org.adempiere.util.ProcessUtil;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.DialogEvents;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.decoris.model.MDecorisPOS;
import org.decoris.model.X_C_DecorisPOS;
import org.decoris.model.X_C_DecorisPOSLine;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.North;
import org.zkoss.zul.Space;

public class WPosTarikTunai  extends PosTarikTunai implements IFormController,
EventListener<Event>, ValueChangeListener{

	// CustomForm
	private CustomForm formTarikTunai = new CustomForm();
		
	// BorderLayout
	private Borderlayout mainLayout = new Borderlayout();

	// Panel
	private Panel headerPanel = new Panel();

	// Grid
	private Grid headerGrid = GridFactory.newGridLayout();
	
	//Component Label
	private Label headerTitle = new Label("Decoris Tarik Tunai");
	
	private Label noNotaARLabel = new Label("No Nota AR :");
	private Label noNotaAPLabel = new Label("No Nota AP :");
	private Label tokoLabel = new Label("Toko* :");
	private Label tglLabel = new Label("Tanggal* :");
	private Label ketLabel  = new Label("Keterangan :");
	private Label pelangganLabel  = new Label("Pelanggan* :");
	
	private Label cashInLabel = new Label("Penerimaan Debit/Credit Card Pelanggan (Kas Masuk) :");
	private Label akunBankLabel = new Label("Akun Bank* :");
	private Label chargeAmtInLabel = new Label("Charge / Amt* :");
	private Label biayaAdmLabel = new Label("Biaya Adm :");
	
	private Label cashOutLabel = new Label("Penarikan Tunai Pelanggan (Kas Keluar) :");
	private Label chargeLabel = new Label("Charge* :");
	private Label chargeAmtOutLabel = new Label("Amount* :");
	
	//Component table editor
	//Org
	private WTableDirEditor tokoTDir = null;
	//BPartner
	private WSearchEditor pelangganTDir = null;
	//Akun Bank
	private WTableDirEditor akunBankTDir = null;
	private WTableDirEditor chargeInTDir = null;
	private WTableDirEditor biayaAdmTDir = null;
	private WTableDirEditor chargeOutTDir = null;

	//Component Date
	//DateTrx
	private Datebox tglDtBox = new Datebox();
	
	//Component Textbox
	private Textbox noNotaARTB = new Textbox();
	private Textbox noNotaAPTB = new Textbox();
	private Textbox ketTB= new Textbox();
	
	//Component DecimalBox
	private Decimalbox chargeAmtInDBox = new Decimalbox();
	private Decimalbox biayaAdmDBox = new Decimalbox();
	private Decimalbox chargeAmtOutDBox = new Decimalbox();
	
	//Component button
	private Button baruBtn = new Button();
	private Button prosesBtn = new Button();
	private Button notaBtn = new Button();
		
	//Variable
	Properties ctx = Env.getCtx();
	private int windowNo = formTarikTunai.getWindowNo();
	private int p_AD_User_ID = Env.getAD_User_ID(ctx);
	private int p_AD_Org_ID = 0;
	private int p_CreatedByPOS_ID = 0;
	private int p_AD_Client_ID  = Env.getAD_Client_ID(ctx);
	private String AD_Language = Env.getAD_Language(ctx);
	private Timestamp nowDt = null;
	private int C_DecorisPOSPrint_ID = 0;
	private int C_PaymentPrint_ID = 0;
	
	private Map<String, Integer> mapPayType;
	private Map<String, Integer> mapARLine;
	private Map<String, Integer> mapDecPOS;

	
	public WPosTarikTunai(){
		
		boolean IsOK = dyInit();
		
		if(!IsOK){
			return;
		}
		
		initUI();

		String msg = cekAkses();

		if (msg != "")
			return;
			
	}
		
	private boolean dyInit(){
		boolean IsOk = true;
		//cek kasir
		p_CreatedByPOS_ID = cekKasir(ctx, p_AD_User_ID);
		
		if(p_CreatedByPOS_ID > 0){
		
			String sqlOrg = "SELECT AD_Org_ID FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
			int kasir_Org_ID = DB.getSQLValueEx(ctx.toString(), sqlOrg, new Object[] {p_AD_Client_ID, p_CreatedByPOS_ID });

			// Initial value Toko
			MLookup orgLookup = MLookupFactory.get(ctx, windowNo, 0,2163, DisplayType.TableDir);
			tokoTDir = new WTableDirEditor("AD_Org_ID", true, false, true,orgLookup);
			tokoTDir.addValueChangeListener(this);
			tokoTDir.setMandatory(true);
			tokoTDir.setValue(kasir_Org_ID);
			
			// Initial value pelanggan lookup
			MLookup lookupPelanggan = MLookupFactory.get(ctx, windowNo, 0, 2893,DisplayType.Search);
			pelangganTDir = new WSearchEditor("C_BPartner_ID", true, false, true,lookupPelanggan);
			pelangganTDir.addValueChangeListener(this);
			pelangganTDir.setMandatory(true);
			
			// Initial value bank lookup
			MLookup lookupBank = MLookupFactory.get(ctx, windowNo, 0,3880, DisplayType.TableDir);
			akunBankTDir = new WTableDirEditor("C_BankAccount_ID", true,false, true, lookupBank);
			akunBankTDir.addValueChangeListener(this);
			akunBankTDir.setMandatory(true);
			
			//Lookup Charge
			MLookup chargeLookup = MLookupFactory.get(ctx,windowNo, 0, 3333, DisplayType.TableDir);
			chargeOutTDir = new WTableDirEditor("C_Charge_ID", true, false, true, chargeLookup);
			chargeOutTDir.addValueChangeListener(this);
			chargeOutTDir.setMandatory(true);
			
			chargeInTDir = new WTableDirEditor("C_Charge_ID", true, false, true, chargeLookup);
			chargeInTDir.addValueChangeListener(this);
			chargeInTDir.setMandatory(true);
			
			biayaAdmTDir = new WTableDirEditor("C_Charge_ID", true, false, true, chargeLookup);
			biayaAdmTDir.addValueChangeListener(this);
			biayaAdmTDir.setMandatory(true);
			
			// Date set to Login Date
			Calendar cal = Calendar.getInstance();
			cal.setTime(Env.getContextAsDate(ctx, "#Date"));
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			tglDtBox.setValue(new Timestamp(cal.getTimeInMillis()));
			nowDt = new Timestamp(cal.getTimeInMillis());
			setDisplay(false);
			
		}else {
			
			IsOk = false;
			
		}
		
		return IsOk;
	}
	
	private void initUI(){
		
		formTarikTunai.appendChild(mainLayout);
		formTarikTunai.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>(){
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				formTarikTunai.dispose();
			}
						
		});
		
		mainLayout.setWidth("99%");
		mainLayout.setHeight("100%");
		String grid = "border: 1px solid #C0C0C0; border-radius:5px;";
		headerPanel.appendChild(headerGrid);

		North north = new North();
		north.setStyle(grid);
		mainLayout.appendChild(north);
		north.appendChild(headerPanel);
		north.setSplittable(false);

		Rows rows = null;
		Row row = null;

		headerGrid.setWidth("100%");
		headerGrid.setStyle("Height:28%;");

		rows = headerGrid.newRows();

		//Headet Title
		row = rows.newRow();
		row.appendCellChild(new Space(), 2);
		row.appendCellChild(headerTitle.rightAlign(),1);
		headerTitle.setStyle("border-radius:3px; font-weight: bold; color:#000000 ");
		
		row = rows.newRow();
		row.appendCellChild(new Space(), 1);
		
		// No Nota AR
		row = rows.newRow();
		row.appendCellChild(noNotaARLabel.rightAlign(),1);
		row.appendCellChild(noNotaARTB,1);
		noNotaARTB.setHflex("true");
		noNotaARTB.setReadonly(true);
		row.appendCellChild(new Space(), 1);
		
		//No Nota AP
		row.appendCellChild(noNotaAPLabel.rightAlign(),1);
		row.appendCellChild(noNotaAPTB,1);
		noNotaAPTB.setHflex("true");
		noNotaAPTB.setReadonly(true);
		row.appendCellChild(new Space(), 1);
		
		//Toko
		row = rows.newRow();
		row.appendCellChild(tokoLabel.rightAlign(),1);
		row.appendCellChild(tokoTDir.getComponent(),1);
		tokoTDir.getComponent().setHflex("true");
		row.appendCellChild(new Space(), 1);

		//Tanggal
		row.appendCellChild(tglLabel.rightAlign(),1);
		row.appendCellChild(tglDtBox,1);
		tglDtBox.setHflex("true");
		if (AD_Language.toUpperCase().equals("EN_US")) {
			tglDtBox.setFormat("dd/MM/yyyy");
		} else if (AD_Language.toUpperCase().equals("IN_ID")) {
			tglDtBox.setFormat("dd/MM/yyyy");
		}
		row.appendCellChild(new Space(), 1);
	
		//Keterangan
		row = rows.newRow();
		row.appendCellChild(ketLabel.rightAlign(),1);
		row.appendCellChild(ketTB,1);
		ketTB.setHflex("true");
		ketTB.setRows(3);
		row.appendCellChild(new Space(), 1);

		//Pelanggan
		row.appendCellChild(pelangganLabel.rightAlign(),1);
		row.appendCellChild(pelangganTDir.getComponent(),1);
		pelangganTDir.getComponent().setHflex("true");
		row.appendCellChild(new Space(), 1);

		row = rows.newRow();
		row.appendCellChild(new Space(), 1);
	
		//Kas Masuk
		row = rows.newRow();
		row.appendCellChild(cashInLabel.rightAlign(),2);
		cashInLabel.setStyle("border-radius:3px; font-weight: bold; color:#000000 ");
		row.appendCellChild(new Space(), 1);

		//Kas Keluar
		row.appendCellChild(cashOutLabel.rightAlign(),2);
		cashOutLabel.setStyle("border-radius:3px; font-weight: bold; color:#000000 ");
		row.appendCellChild(new Space(), 1);

		//Akun Bank
		row = rows.newRow();
		row.appendCellChild(akunBankLabel.rightAlign(),1);
		row.appendCellChild(akunBankTDir.getComponent(),1);
		akunBankTDir.getComponent().setHflex("true");
		row.appendCellChild(new Space(), 1);

		//Charge Out
		row.appendCellChild(chargeLabel.rightAlign(),1);
		row.appendCellChild(chargeOutTDir.getComponent(),1);
		chargeOutTDir.getComponent().setHflex("true");
		
		//Charge & Amt
		row = rows.newRow();
		row.appendCellChild(chargeAmtInLabel.rightAlign(),1);
		row.appendCellChild(chargeInTDir.getComponent(),1);
		row.appendCellChild(chargeAmtInDBox,1);
		akunBankTDir.getComponent().setHflex("true");
		chargeInTDir.getComponent().setHflex("true");
		chargeAmtInDBox.setHflex("true");	
		chargeAmtInDBox.setStyle("text-align:right;");
		chargeAmtInDBox.setValue("0.00");
		chargeAmtInDBox.addEventListener(0, Events.ON_BLUR, this);
		chargeAmtInDBox.addEventListener(0, Events.ON_FOCUS, this);
		chargeAmtInDBox.setFormat("#,###,###,##0.00");
		chargeAmtInDBox.setWidth("100%");

		//Amount
		row.appendCellChild(chargeAmtOutLabel.rightAlign(),1);
		row.appendCellChild(chargeAmtOutDBox,1);
		chargeAmtOutDBox.setHflex("true");	
		chargeAmtOutDBox.setStyle("text-align:right;");
		chargeAmtOutDBox.setValue("0.00");
		chargeAmtOutDBox.addEventListener(0, Events.ON_BLUR, this);
		chargeAmtOutDBox.addEventListener(0, Events.ON_FOCUS, this);
		chargeAmtOutDBox.setFormat("#,###,###,##0.00");
		chargeAmtOutDBox.setWidth("100%");
		
		//Biaya Admin
		row = rows.newRow();
		row.appendCellChild(biayaAdmLabel.rightAlign(),1);
		row.appendCellChild(biayaAdmTDir.getComponent(),1);
		row.appendCellChild(biayaAdmDBox,1);
		biayaAdmTDir.getComponent().setHflex("true");
		biayaAdmDBox.setHflex("true");
		
		biayaAdmDBox.setStyle("text-align:right;");
		biayaAdmDBox.setValue("0.00");
		biayaAdmDBox.addEventListener(0, Events.ON_BLUR, this);
		biayaAdmDBox.addEventListener(0, Events.ON_FOCUS, this);
		biayaAdmDBox.setFormat("#,###,###,##0.00");
		biayaAdmDBox.setWidth("100%");
		
		row = rows.newRow();
		row.appendCellChild(new Space(), 1);

		row = rows.newRow();
		row.appendCellChild(new Space(), 1);
		
		row = rows.newRow();
		row.appendCellChild(new Space(), 1);
		
		//Button Baru
		row = rows.newRow();
		row.appendCellChild(baruBtn,2);
		baruBtn.setHflex("true");
		baruBtn.addActionListener(this);
		baruBtn.setTooltiptext("Baru");
		baruBtn.setLabel("Baru");

		//Button Proses
		row.appendCellChild(prosesBtn,2);
		prosesBtn.setHflex("true");
		prosesBtn.addActionListener(this);
		prosesBtn.setTooltiptext("Proses");
		prosesBtn.setLabel("Proses");
		
		//Button Cetak Nota
		row.appendCellChild(notaBtn,2);
		notaBtn.setHflex("true");
		notaBtn.addActionListener(this);
		notaBtn.setTooltiptext("Cetak Nota");
		notaBtn.setLabel("Cetak Nota");
		
	}
	

	private String cekAkses() {
		String Msg = "";
		if (p_CreatedByPOS_ID <= 0) {
			noNotaARTB.setReadonly(true);
			noNotaAPTB.setEnabled(false);
			tokoTDir.setReadWrite(false);
			ketTB.setEnabled(false);
			pelangganTDir.setReadWrite(false);
			tglDtBox.setReadonly(true);
			chargeOutTDir.setReadWrite(false);
			akunBankTDir.setReadWrite(false);
			chargeInTDir.setReadWrite(false);
			biayaAdmTDir.setReadWrite(false);
			chargeAmtInDBox.setReadonly(true);
			biayaAdmDBox.setReadonly(true);
			chargeAmtOutDBox.setReadonly(true);
			baruBtn.setEnabled(false);
			prosesBtn.setEnabled(false);
			notaBtn.setEnabled(false);

			FDialog.info(windowNo, null, "","User Tidak Mempunyai Akses ke Halaman Tarik Tunai", "Info");
			Msg = "error";
		}

		return Msg;
	}
	
	private String checkValidation(){
		String valMsg = "";
		
		//validation Toko
		if(tokoTDir.getValue() == null){
			
			valMsg = valMsg + "\n" +"Kolom Toko Tidak Boleh Kosong";
	
		}
		
		//Validation Tgl
		if(tglDtBox.getValue() == null){
			
			valMsg = valMsg + "\n" +"Kolom Tanggal Tidak Boleh Kosong";
	
		}
		
		//Validation Akun Bank
		if(akunBankTDir.getValue() == null){
			
			valMsg = valMsg + "\n" +"Kolom Akun Bank Tidak Boleh Kosong";
	
		}
		
		//validation Pelanggan
		if(pelangganTDir.getValue() == null){
					
			valMsg = valMsg + "\n" +"Kolom Pelanggan Tidak Boleh Kosong";
			
		}else{
			
			//validation BPLocation
			int C_BPartnerLocation_ID = getLocationBP(ctx, (int)pelangganTDir.getValue());
			if(C_BPartnerLocation_ID <= 0){
				
				valMsg = valMsg + "\n" +"Lokasi Pelanggan Belum Terdaftar, Silahkan Hub. Admin";
				
			}
			
		}
		
		//Validation Charge Out
		if(chargeOutTDir.getValue() == null){
			
			valMsg = valMsg + "\n" +"Kolom Charge Penarikan Tidak Boleh Kosong";
	
		}
		
		//Validation Charge Out Amt
		BigDecimal chargeAmtOut=chargeAmtOutDBox.getValue();
		if(chargeAmtOutDBox.getValue() == null || chargeAmtOut.compareTo(Env.ZERO) <= 0){
			
			valMsg = valMsg + "\n" +"Kolom Amount Charge Penarikan Tidak Boleh Kosong Atau Nol";
	
		}
		
		//Validation Charge In
		if(chargeInTDir.getValue() == null){
					
			valMsg = valMsg + "\n" +"Kolom Charge Penerimaan Tidak Boleh Kosong";
			
		}
		
		//Validation Charge In Amt
		BigDecimal chargeAmtIn=chargeAmtInDBox.getValue();
		if(chargeAmtInDBox.getValue() == null || chargeAmtIn.compareTo(Env.ZERO) <= 0){
					
			valMsg = valMsg + "\n" +"Kolom Amount Charge Penerimaan Tidak Boleh Kosong Atau Nol";
			
		}
		
		//Validation Charge Biaya Admin
		BigDecimal admAmtIn=biayaAdmDBox.getValue();
		if(biayaAdmTDir.getValue() != null && admAmtIn.compareTo(Env.ZERO) <= 0){
							
			valMsg = valMsg + "\n" +"Kolom Amount Biaya Admin Tidak Boleh Kosong Atau Nol";
					
		}
		
		if(biayaAdmTDir.getValue() == null && admAmtIn.compareTo(Env.ZERO) > 0){
			
			valMsg = valMsg + "\n" +"Kolom Charge Biaya Admin Tidak Boleh Kosong";
					
		}
			
		return valMsg;
	}
	
	@Override
	public void valueChange(ValueChangeEvent evt) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(Event e) throws Exception {
	String event = e.getName();
	if (e.getTarget().equals(prosesBtn)) {
		
		FDialog.ask(windowNo, null,"Konfirmasi" ,"", "Anda Yakin Untuk Memproses Transaksi?", new Callback<Boolean>() {
						
						@Override
						public void onCallback(Boolean result) {
		
							if(result){
								
								String valRs = "";
								
								valRs = checkValidation();
								
								if(valRs != ""){
									
									FDialog.info(windowNo, null, "",valRs, "Info");
									return;
									
								}
								
								process();
								
							}else{
								return;			
							}
							
						}
					});
		
	}else if (e.getTarget().equals(baruBtn)) {
		
		setDisplay(true);
		setValue();
		
	}else if (e.getTarget().equals(chargeAmtInDBox)) {
		if (event.equals(Events.ON_BLUR)){
			if(chargeAmtInDBox.getValue() == null){
				
				chargeAmtInDBox.setValue("0.00");
				
			}else if(chargeAmtInDBox.getValue().compareTo(Env.ZERO)>= 0){
				
				chargeAmtOutDBox.setValue(chargeAmtInDBox.getValue());
			}
		}
	}else if (e.getTarget().equals(biayaAdmDBox)) {
		if (event.equals(Events.ON_BLUR)){
			if(biayaAdmDBox.getValue() == null){
				
				biayaAdmDBox.setValue("0.00");
				
			}	
		}
	}else if (e.getTarget().equals(chargeAmtOutDBox)) {
		if (event.equals(Events.ON_BLUR)){
			if(chargeAmtOutDBox.getValue() == null){
				
				chargeAmtOutDBox.setValue("0.00");
				
			}	
		}
	}else if(e.getTarget().equals(notaBtn)){
		
		  	 updateData(C_DecorisPOSPrint_ID);
			 MDecorisPOS decPos2nd = new MDecorisPOS(ctx, C_DecorisPOSPrint_ID, null);
			 C_PaymentPrint_ID = decPos2nd.getC_Payment_ID();
			 String trxName = Trx.createTrxName("printtariktunai");
			 int AD_Process_ID = 0;
			 String url = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/reports/";
			 //String url = "D:\\SourceCode\\SIM-Base\\reports\\";
			 
			 StringBuilder SQLGetProcessID = new StringBuilder();
			 SQLGetProcessID.append("SELECT description::numeric ");
			 SQLGetProcessID.append("FROM ad_param ");
			 SQLGetProcessID.append("WHERE value =  'JasperTarikTunai'");

			 AD_Process_ID = DB.getSQLValueEx(null, SQLGetProcessID.toString());
			 
			 MProcess proc = new MProcess(Env.getCtx(), AD_Process_ID, trxName);
			 MPInstance instance = new MPInstance(proc,proc.getAD_Process_ID());
			 ProcessInfo pi = new ProcessInfo("Print Bukti Tarik Tunai", AD_Process_ID);
			 pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			 ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			 list.add(new ProcessInfoParameter("C_Payment_ID", C_PaymentPrint_ID, null,null, null));
			 list.add(new ProcessInfoParameter("url_path",url, null,null, null));
			 ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			 list.toArray(pars);
			 pi.setParameter(pars);
			 //
			 Trx trx = Trx.get(trxName, true);
			 trx.commit();
			
			 ProcessUtil.startJavaProcess(Env.getCtx(), pi, Trx.get(trxName,true));
		
	}
		
	}

	
	private void process(){
		
		mapPayType = new HashMap<String, Integer>();
		mapDecPOS = new HashMap<String, Integer>();
		mapPayType.put("AR", 1);
		mapPayType.put("AP", 2);
		boolean IsSOTrx = true;
		boolean IsReceipt = true;
		String noNotaAR = "";
		String noNotaAP = "";
		X_C_DecorisPOS decPos = null;
		
		for (String key : mapPayType.keySet()) {
				
			
			
			BigDecimal ChargeInAmt = chargeAmtInDBox.getValue();
			BigDecimal admInAmt = biayaAdmDBox.getValue();
			
			BigDecimal grandTotalAR  = ChargeInAmt.add(admInAmt);
			BigDecimal grandTotalAP  = chargeAmtOutDBox.getValue();

			p_AD_Org_ID = (int) tokoTDir.getValue();
			
			Date date = tglDtBox.getValue();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			String dateTostr = df.format(date);
			Timestamp dateTrx = Timestamp.valueOf(dateTostr);
			
			int C_BPartner_Location_ID = getLocationBP(ctx, (int)pelangganTDir.getValue());
			
			decPos = new X_C_DecorisPOS(ctx, 0, null);
			
			decPos.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);
			decPos.setAD_Org_ID(p_AD_Org_ID);
			decPos.setC_BPartner_ID((int)pelangganTDir.getValue());
			decPos.setDateOrdered(dateTrx);
			decPos.setDateTrx(dateTrx);
			decPos.setDescription((String)ketTB.getValue().toString());
			decPos.setC_BPartner_Location_ID(C_BPartner_Location_ID);
			decPos.setC_DocType_ID(getPOSDocType(ctx, p_AD_Client_ID, "POS"));
			//decPos.setC_PaymentTerm_ID(C_PaymentTerm_ID);
			decPos.setCreatedByPOS_ID(p_CreatedByPOS_ID);
			decPos.setdpp(Env.ZERO);
			decPos.setTaxAmt(Env.ZERO);
			decPos.setIsPembatalan(false);
			decPos.setIsPenjualan(false);
			decPos.setIsPembayaran(true);
			decPos.set_CustomColumn("IsCashAdvance", "Y");
			
			if(key.toUpperCase().equals("AP")){
				IsSOTrx = false;
				IsReceipt = false;
				decPos.setPaymentRule("X");
				decPos.setGrandTotal(grandTotalAP);
				decPos.setTotalLines(grandTotalAP);
				decPos.setPembayaran1(grandTotalAP);
				decPos.setPayType1("TUNAI");
			}else if(key.toUpperCase().equals("AR")){
				decPos.setPaymentRule("M");
				decPos.setGrandTotal(grandTotalAR);
				decPos.setTotalLines(grandTotalAR);
				decPos.setPembayaran2(grandTotalAR);
				decPos.setPayType2("BANK");
			}
			
			decPos.setIsSOTrx(IsSOTrx);
			decPos.setIsReceipt(IsReceipt);
			decPos.saveEx();
			
			if(key.toUpperCase().equals("AR")){
				C_DecorisPOSPrint_ID = decPos.getC_DecorisPOS_ID();
			}
			
			
			if(key.toUpperCase().equals("AP")){
				noNotaAP = decPos.getDocumentNo();
			}else if(key.toUpperCase().equals("AR")){
				noNotaAR = decPos.getDocumentNo();
			}
			
			mapDecPOS.put(key.toUpperCase(), decPos.getC_DecorisPOS_ID());
			
			if(key.equals("AR")){
				mapARLine = new HashMap<String, Integer>();
				mapARLine.put("REG", 1);
				
				if(admInAmt.compareTo(Env.ZERO)>0){
					mapARLine.put("ADM", 2);	
				}
				
				for(String keyAR: mapARLine.keySet()){
					
					X_C_DecorisPOSLine decPOSLine = new X_C_DecorisPOSLine(ctx, 0, null);

					String sqlLine = "SELECT COALESCE(MAX(Line),0)+10 FROM C_DecorisPOSLine WHERE AD_Client_ID = ? AND C_DecorisPOS_ID =?";
					int ii = DB.getSQLValue (decPos.get_TrxName(), sqlLine, new Object[]{p_AD_Client_ID,decPos.getC_DecorisPOS_ID()});
					
					
					decPOSLine.setClientOrg(decPos);
					decPOSLine.setC_DecorisPOS_ID(decPos.getC_DecorisPOS_ID());
					if(keyAR.equals("REG")){
						
						decPOSLine.setC_Charge_ID((int)chargeInTDir.getValue());
						decPOSLine.setPriceEntered(chargeAmtInDBox.getValue());
						decPOSLine.setLineNetAmt(chargeAmtInDBox.getValue());
						decPOSLine.setC_Tax_ID(getTax_ID(ctx, (int)chargeInTDir.getValue()));

					}else if(keyAR.equals("ADM")){
						decPOSLine.setC_Charge_ID((int)biayaAdmTDir.getValue());
						decPOSLine.setPriceEntered(biayaAdmDBox.getValue());
						decPOSLine.setLineNetAmt(biayaAdmDBox.getValue());
						decPOSLine.setC_Tax_ID(getTax_ID(ctx, (int)biayaAdmTDir.getValue()));

					}
					
					decPOSLine.setQtyOrdered(Env.ONE);
					decPOSLine.setLine(ii);
					decPOSLine.saveEx();
					
				}
				
			}else{
				X_C_DecorisPOSLine decPOSLine = new X_C_DecorisPOSLine(ctx, 0, null);
				
				String sqlLine = "SELECT COALESCE(MAX(Line),0)+10 FROM C_DecorisPOSLine WHERE AD_Client_ID = ? AND C_DecorisPOS_ID =?";
				int ii = DB.getSQLValue (decPos.get_TrxName(), sqlLine, new Object[]{p_AD_Client_ID,decPos.getC_DecorisPOS_ID()});
				

				decPOSLine.setClientOrg(decPos);
				decPOSLine.setC_DecorisPOS_ID(decPos.getC_DecorisPOS_ID());	
				decPOSLine.setC_Charge_ID((int)chargeOutTDir.getValue());
				decPOSLine.setPriceEntered(chargeAmtOutDBox.getValue());
				decPOSLine.setLineNetAmt(chargeAmtOutDBox.getValue());
				decPOSLine.setC_Tax_ID(getTax_ID(ctx, (int)chargeOutTDir.getValue()));
				decPOSLine.setQtyOrdered(Env.ONE);
				decPOSLine.setLine(ii);
				decPOSLine.saveEx();
			}		
		}
		
		
		for (String keyPOS : mapDecPOS.keySet()){
			
			int C_DecorisPOS_ID = mapDecPOS.get(keyPOS);

			createInvoicePayment(ctx, C_DecorisPOS_ID, keyPOS, (int)akunBankTDir.getValue());
				
		}
		
		noNotaAPTB.setValue(noNotaAP);
		noNotaARTB.setValue(noNotaAR);
		notaBtn.setEnabled(true);
		prosesBtn.setEnabled(false);
		tokoTDir.setReadWrite(false);
		tglDtBox.setReadonly(true);
		tglDtBox.setEnabled(false);
		ketTB.setReadonly(true);
		pelangganTDir.setReadWrite(false);
		akunBankTDir.setReadWrite(false);
		chargeAmtInDBox.setReadonly(true);
		chargeOutTDir.setReadWrite(false);
		biayaAdmDBox.setReadonly(true);
		chargeInTDir.setReadWrite(false);
		biayaAdmTDir.setReadWrite(false);
		
	}
	
	private void setDisplay(boolean isNewProcess) {
		
		tokoTDir.setReadWrite(isNewProcess);
		tglDtBox.setEnabled(isNewProcess);
		ketTB.setEnabled(isNewProcess);
		ketTB.setReadonly(false);
		pelangganTDir.setReadWrite(isNewProcess);
		akunBankTDir.setReadWrite(isNewProcess);
		chargeInTDir.setReadWrite(isNewProcess);
		biayaAdmTDir.setReadWrite(isNewProcess);
		chargeOutTDir.setReadWrite(isNewProcess);
		biayaAdmDBox.setReadonly(!isNewProcess);
		chargeAmtInDBox.setReadonly(!isNewProcess);
		chargeAmtOutDBox.setReadonly(true);
		prosesBtn.setEnabled(isNewProcess);
		notaBtn.setEnabled(false);
		
	}
	
	private void setValue() {
		
		tokoTDir.setValue(null);
		tglDtBox.setValue(nowDt);
		ketTB.setValue(null);
		pelangganTDir.setValue(null);
		akunBankTDir.setValue(null);
		chargeInTDir.setValue(null);
		biayaAdmTDir.setValue(null);
		chargeOutTDir.setValue(null);
		biayaAdmDBox.setValue("0.00");
		chargeAmtInDBox.setValue("0.00");
		chargeAmtOutDBox.setValue("0.00");
		noNotaAPTB.setValue(null);
		noNotaARTB.setValue(null);

	}
	
	@Override
	public ADForm getForm() {
		// TODO Auto-generated method stub
		return formTarikTunai;
	}

}
