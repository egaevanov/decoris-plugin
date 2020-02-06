package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.util.ProcessUtil;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
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
import org.compiere.model.MClient;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.decoris.model.X_C_DecorisPOS;
import org.decoris.model.X_C_Decoris_CloseCash;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Space;


/**
 * 
 * @author Tegar N
 *
 */

public class WPosCloseCash extends PosCloseCash implements IFormController,
EventListener<Event>, WTableModelListener, ValueChangeListener{

	
	// CustomForm
	private CustomForm form = new CustomForm();
	
	// BorderLayout
	private Borderlayout mainLayout = new Borderlayout();
	private Borderlayout infoLayout = new Borderlayout();
	private Borderlayout summaryLayout = new Borderlayout();

	
	// Panel
	private Panel parameterPanel = new Panel();
	private Panel closeCashPanel = new Panel();
	private Panel southPanel = new Panel();

	
	// Grid
	private Grid summaryGrid = GridFactory.newGridLayout();
	private Grid parameterGrid = GridFactory.newGridLayout();

	
	// Toko
	private Label clientLabel = new Label("Toko :");
	private WTableDirEditor clientSearch = null;
	
	// Org
	private Label orgLabel = new Label("Cabang :");
	private WTableDirEditor org = null;
	
	// Tanggal Transaksi
	private Label dateTransactionLabel = new Label("Tanggal Transaksi :");
	private WDateEditor dateTransactionField = new WDateEditor();
	private Label until = new Label(" s/d ");
	private WDateEditor dateTransactionField2 = new WDateEditor();
	
	//Nama Kasir
	private Label kasirNameLabel = new Label("Nama Kasir :");
	private Combobox kasirNameField = new Combobox();
	
	//Kriteria Data
	//private String[] criteriaList = new String[] { "Seluruh Data", "Belum Tutup Kas","Sudah Tutup Kas" };
	private Label dataCriteriaLabel = new Label("Kriteria Data :");
	private Combobox dataCriteriaList = new Combobox();

	//Pencarian Data
	private Button searchButton = new Button();
	
	//Proses Tutup Kas
	private Button processCloseCash = new Button();
	
	//Cetak Tutup Kas
	private Button printCloseCash = new Button();
	
	// Table Data
	private WListbox closeCashTable = ListboxFactory.newDataTable();
	
	// Table Data Ringkasan
	private WListbox closeCashTableSummary = ListboxFactory.newDataTable();
		
	
	//variable
	private Properties ctx = Env.getCtx();
	private int AD_Client_ID  = Env.getAD_Client_ID(ctx);
	private int CreatedByPOS_ID = 0;
	private int AD_User_ID = 0;
	private int p_AD_Org_ID = 0;
	private int windowNo = form.getWindowNo();

	private boolean isSaldoAwal = false;


	public WPosCloseCash(){
		
		dyInit();
		init();
		
	}
	
	private void init(){
	
		form.appendChild(mainLayout);
		
		form.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>(){

			@Override
			public void onEvent(Event arg0) throws Exception {

				cleanTable(AD_Client_ID,p_AD_Org_ID);
				form.dispose();
			}
			
		});
		
		mainLayout.setWidth("99%");
		mainLayout.setHeight("100%");
		
		North north = new North();
		mainLayout.appendChild(north);
		
		String grid = "border: 1px solid #C0C0C0; border-radius:5px;";
		north.appendChild(parameterPanel);
		north.setStyle(grid);
		
		parameterPanel.appendChild(parameterGrid);
		parameterGrid.setWidth("100%");
		parameterGrid.setStyle("Height:28%;");
		//parameterGrid.setHflex("min");

		Rows rows = null;
		Row row = null;

		rows = parameterGrid.newRows();
		
		// Toko
		row = rows.newRow();
		row.appendCellChild(clientLabel.rightAlign(), 1);
		row.appendCellChild(clientSearch.getComponent(), 1);
		clientSearch.getComponent().setHflex("true");
		clientSearch.setReadWrite(false);
		
		// Cabang
		row.appendCellChild(orgLabel.rightAlign(), 1);
		row.appendCellChild(org.getComponent(), 1);
		org.getComponent().setHflex("true");
		row.appendCellChild(new Space(), 1);

	
		//Tanggal Transaksi		
		row = rows.newRow();
		row.appendCellChild(dateTransactionLabel.rightAlign(),1);
		Hbox hBox = new Hbox();
		row.appendCellChild(hBox , 2);
		hBox.setHflex("true");
		hBox.setAlign("center");
		hBox.appendChild(dateTransactionField.getComponent());
		//dateTransactionField.getComponent().setWidth("116px");
		hBox.appendChild(until);
		hBox.appendChild(dateTransactionField2.getComponent());
		//dateTransactionField2.getComponent().setWidth("116px");

		
		//Nama Kasir
		row = rows.newRow();
		row.appendCellChild(kasirNameLabel.rightAlign(),1);
		row.appendCellChild(kasirNameField, 1);
		kasirNameField.setHflex("true");
		kasirNameField.setStyle("align:right;");
		kasirNameField.addEventListener(0, "onChange", this);
		kasirNameField.setHeight("24px");
		kasirNameField.setStyle("border-radius:3px;");
		
		//Kriteria Data
		row = rows.newRow();
		row.appendCellChild(dataCriteriaLabel.rightAlign(),1);
		row.appendCellChild(dataCriteriaList, 1);
		dataCriteriaList.setWidth("100px");
		dataCriteriaList.addEventListener(0, "onChange", this);
		dataCriteriaList.setHeight("24px");
		dataCriteriaList.setStyle("border-radius:3px;");
		dataCriteriaList.setHflex("true");
		dataCriteriaList.appendItem("Seluruh Data");
		dataCriteriaList.appendItem("Belum Tutup Kas");
		dataCriteriaList.appendItem("Sudah Tutup Kas");

		
		
		//Pencarian Data
		row = rows.newRow();
		row.appendCellChild(new Space(), 3);
		row.appendCellChild(searchButton,1);
		searchButton.setLabel("Pencarian Data");
		searchButton.addActionListener(this);
		searchButton.setHflex("true");
		
		
		// SouthPanel
		South south = new South();
		mainLayout.appendChild(south);
		south.setStyle(grid);
		south.appendChild(southPanel);
		southPanel.appendChild(summaryGrid);
		
		Rows southRows = null;
		Row southRow = null;

		southRows = summaryGrid.newRows();
		
		summaryGrid.setStyle("Height:50%;");

		southRow = southRows.newRow();
		southRow.appendCellChild( new Space(),3);
		Hbox southHBox = new Hbox();
		southRow.appendCellChild(southHBox , 1);
		southHBox.setHflex("true");
		southHBox.setAlign("right");
		southHBox.appendChild(processCloseCash);
		processCloseCash.setLabel("Proses Tutup Kas");
		processCloseCash.addActionListener(this);
		processCloseCash.setHflex("true");
		
		southHBox.appendChild(printCloseCash);
		printCloseCash.setLabel("Cetak Tutup Kas");
		printCloseCash.addActionListener(this);
		printCloseCash.setHflex("true");
		
		southRow = southRows.newRow();
		southRow.appendCellChild( new Space(),1);
		southRow.appendCellChild(closeCashTableSummary, 2);
		closeCashTableSummary.setHeight("100px");
		southRow.appendCellChild( new Space(),1);

		
		south = new South();
		closeCashPanel.appendChild(summaryLayout);
		summaryLayout.appendChild(south);
		closeCashPanel.setWidth("100%");
		closeCashPanel.setHeight("100%");
		summaryLayout.setWidth("100%");
		summaryLayout.setHeight("100%");
		
		Center center = new Center();
		summaryLayout.appendChild(center);
		center.appendChild(closeCashTable);
		closeCashTable.setWidth("100%");
		closeCashTable.setHeight("100%");
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
		north.appendChild(closeCashPanel);
		north.setSplittable(true);
		center = new Center();
		infoLayout.appendChild(center);
		

	}
	
	private void dyInit(){
		
		AD_User_ID = Env.getAD_User_ID(ctx);
		int org_id = 0;
		int admin_Role_ID = 0;
		int kasir_Role_ID = 0;
		int kasirOL_Role_ID = 0;
		int login_Role_ID = Env.getAD_Role_ID(ctx);
		ArrayList<KeyNamePair> kasirList = null;
		ArrayList<Integer> AdminList = new ArrayList<Integer>();
		Boolean IsAdmin = false;


		String SQLRoleAdmin = "SELECT AD_Role_ID FROM AD_Role WHERE AD_Client_ID = ? AND Name Like '%Admin%' AND AD_Role_ID <> 0";
		admin_Role_ID = DB.getSQLValueEx(ctx.toString(), SQLRoleAdmin.toString(),new Object[] {AD_Client_ID});
		
		PreparedStatement pstmtAdm = null;
		ResultSet rsAdm = null;
		try {
			pstmtAdm = DB.prepareStatement(SQLRoleAdmin.toString(), null);
			pstmtAdm.setInt(1, AD_Client_ID);
			rsAdm = pstmtAdm.executeQuery();
			while (rsAdm.next()) {
			
				AdminList.add(rsAdm.getInt(1));
				
			}

		} catch (SQLException ex) {
			log.log(Level.SEVERE, SQLRoleAdmin.toString(), ex);
		} finally {
			DB.close(rsAdm, pstmtAdm);
			rsAdm = null;
			pstmtAdm = null;
		}
		
		String SQLRoleKasir = "SELECT AD_Role_ID FROM AD_Role WHERE AD_Client_ID = ? AND Name Like '%Kasir%' AND AD_Role_ID <> 0";
		kasir_Role_ID = DB.getSQLValueEx(ctx.toString(), SQLRoleKasir.toString(),new Object[] {AD_Client_ID});
		
		String SQLRoleKasirOL = "SELECT AD_Role_ID FROM AD_Role WHERE AD_Client_ID = ? AND Name Like '%Online%' AND AD_Role_ID <> 0";
		kasirOL_Role_ID = DB.getSQLValueEx(ctx.toString(), SQLRoleKasirOL.toString(),new Object[] {AD_Client_ID});
		
		String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
		CreatedByPOS_ID = DB.getSQLValueEx(ctx.toString(), sqlKasir.toString(),new Object[] { Env.getAD_Client_ID(ctx), AD_User_ID });
		
		String SQLIsSaldoAwal = "SELECT IsSaldoAwal FROM C_POS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ? AND IsSaldoAwal = 'Y'";
		String IsSaldo = DB.getSQLValueStringEx(ctx.toString(), SQLIsSaldoAwal.toString(),new Object[] { Env.getAD_Client_ID(ctx), CreatedByPOS_ID});

		if(IsSaldo == null || IsSaldo == "" ){
			isSaldoAwal = false;
		}else if (IsSaldo.toUpperCase().equals("Y")){
			isSaldoAwal = true;
		}
		
		
		for(int i = 0 ; i < AdminList.size();i++){
			
			if(login_Role_ID == AdminList.get(i)){
				IsAdmin = true;
			}
			
		}
		
		if (IsAdmin){
			kasirList = loadKasir(true,CreatedByPOS_ID);	
			processCloseCash.setEnabled(false);
		}else if(login_Role_ID == kasir_Role_ID || login_Role_ID == kasirOL_Role_ID){
			kasirList = loadKasir(false,CreatedByPOS_ID);
			processCloseCash.setEnabled(true);

		}		
		
		
		if(kasirList.size() > 0){
			
			kasirNameField.removeAllItems();
			for (KeyNamePair kasir : kasirList)
				kasirNameField.appendItem(kasir.getName());
			
			if (login_Role_ID == admin_Role_ID){
				kasirNameField.setSelectedIndex(0);
			}else if(login_Role_ID == kasir_Role_ID || login_Role_ID == kasirOL_Role_ID){
				kasirNameField.setSelectedIndex(1);
				kasirNameField.setReadonly(true);
				kasirNameField.setEnabled(false);
			}		
				
		}
		
		MLookup lookupClient = MLookupFactory.get(ctx, form.getWindowNo(), 0,14621, DisplayType.TableDir);
		clientSearch = new WTableDirEditor("AD_Table_ID", true,false, true, lookupClient);
		clientSearch.addValueChangeListener(this);
		clientSearch.setMandatory(true);
		clientSearch.setValue(AD_Client_ID);
		
//		MLookup lookupKasir = MLookupFactory.get(ctx, form.getWindowNo(), 0,1001003, DisplayType.Table);
//		kasirNameField = new WTableDirEditor("CreatedByPOS_ID", true,false, true, lookupKasir);
//		kasirNameField.addValueChangeListener(this);
		
		if (CreatedByPOS_ID > 0){
		String sqlOrg = "SELECT AD_Org_ID FROM C_POS WHERE  AD_Client_ID = ? AND CreatedByPOS_ID = ?";
		org_id = DB.getSQLValueEx(ctx.toString(), sqlOrg, new Object[] {AD_Client_ID, CreatedByPOS_ID });
		p_AD_Org_ID = org_id;
		}
		
		MLookup orgLookup = MLookupFactory.get(ctx, form.getWindowNo(), 0,2163, DisplayType.TableDir);
		org = new WTableDirEditor("AD_Org_ID", true, false, true, orgLookup);
		org.addValueChangeListener(this);
		org.setMandatory(true);
		if(org_id > 0){
			org.setValue(org_id);
		}
		org.setReadWrite(false);	
		
		
		cleanTable(AD_Client_ID,org_id);
		insertTempTable();
		
	}
	
	//configure
	protected void configureMiniTable(IMiniTable miniTable) {

		miniTable.setColumnClass(0, Boolean.class, false);
		miniTable.setColumnClass(1, Timestamp.class, true); 	// 2-DateTrx
		miniTable.setColumnClass(2, Timestamp.class, true); 	// 3-CloseCashDate
		miniTable.setColumnClass(3, String.class, true); 		// 4-DocumentNo
		miniTable.setColumnClass(4, int.class, true); 			// 5-SeqTutupKas
		miniTable.setColumnClass(5, BigDecimal.class, true); 	// 6-CurrentBalance
		miniTable.setColumnClass(6, BigDecimal.class, true); 	// 7-CashIn
		miniTable.setColumnClass(7, BigDecimal.class, true); 	// 8-CashOut
		miniTable.setColumnClass(8, BigDecimal.class, true); 	// 9-TaxAmt
		miniTable.setColumnClass(9, BigDecimal.class, true); 	// 10-Total
		miniTable.setColumnClass(10, BigDecimal.class, true); 	// 11-TotalOmset

		
		miniTable.autoSize();

	}
	
	protected Vector<String> getOISColumnNames() {

		// Header Info
		Vector<String> columnNames = new Vector<String>(10);
		columnNames.add("Select");
		columnNames.add("Tgl. Transaksi");
		columnNames.add("Tgl. Tutup Kas");
		columnNames.add("Nomor");
		columnNames.add("Urutan");
		columnNames.add("Saldo Awal");
		columnNames.add("Kas Masuk");
		columnNames.add("Kas Keluar");
		columnNames.add("Pajak");
		columnNames.add("Total");
		columnNames.add("Total Omset");

		return columnNames;

	}
	
	
	//configure
		protected void configureMiniTableSummary(IMiniTable miniTable) {

			miniTable.setColumnClass(0, BigDecimal.class, true); 	// 1-Cash
			miniTable.setColumnClass(1, BigDecimal.class, true); 	// 2-BankPayment
			miniTable.setColumnClass(2, BigDecimal.class, true); 	// 3-LeasingPayment
			miniTable.setColumnClass(3, BigDecimal.class, true); 	// 4-LainLain
			miniTable.setColumnClass(4, BigDecimal.class, true); 	// 5-Hutang

			miniTable.autoSize();

		}
		
		protected Vector<String> getOISColumnNamesSummary() {

			// Header Info
			Vector<String> columnNamesSummary = new Vector<String>(4);
			columnNamesSummary.add("Tunai");
			columnNamesSummary.add("Bank");
			columnNamesSummary.add("Spektra");
			columnNamesSummary.add("Lain-Lain");
			columnNamesSummary.add("Hutang");


			return columnNamesSummary;

		}
	
	
	
	@Override
	public ADForm getForm() {
		return form;
	}
	
	@Override
	public void valueChange(ValueChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableChanged(WTableModelEvent e) {
		boolean isUpdate = (e.getType() == WTableModelEvent.CONTENTS_CHANGED);
		String kasirName = kasirNameField.getValue();
				
		StringBuilder SQLKasir_ID = new StringBuilder();		
		SQLKasir_ID.append("SELECT C_BPartner_ID ");
		SQLKasir_ID.append("FROM C_BPartner ");
		SQLKasir_ID.append("WHERE AD_Client_ID = ?");
		SQLKasir_ID.append("AND Name = '" + kasirName + "'");

		Integer p_Kasir_ID = DB.getSQLValueEx(null, SQLKasir_ID.toString(), AD_Client_ID);


		
		if (!isUpdate) {
			return;
		} else if (isUpdate) {
			
			int col = e.getColumn();
			int row = e.getLastRow();
			
			if (col == 0 ){
				
				boolean isCek = (boolean) closeCashTable.getValueAt(row, 0);
				
				if(isCek){
				
					for (int i = 0 ; i < closeCashTable.getRowCount(); i++){
					
						if(i != row){
						
							closeCashTable.setValueAt(false, i, 0);
						
						}
					}
				}
				
			}
			
		}
		
		//
		for (int i = 0 ; i < closeCashTable.getRowCount(); i++){
			
			boolean isCek = (boolean) closeCashTable.getValueAt(i, 0);
			Timestamp dateTrx = null;
			Timestamp dateCloseCash = null;
			String documentNo = "";

			int seqTutupKas = 0;

			
			if (isCek){
				dateTrx = (Timestamp) closeCashTable.getValueAt(i, 1);
				dateCloseCash = (Timestamp) closeCashTable.getValueAt(i, 2);
				documentNo = (String) closeCashTable.getValueAt(i, 3);
				seqTutupKas = (int) closeCashTable.getValueAt(i, 4);
				
				Vector<Vector<Object>> dataSummary = getCloseCashSummary(AD_Client_ID,p_AD_Org_ID, dateTrx, dateCloseCash,p_Kasir_ID ,documentNo,seqTutupKas);
				Vector<String> columnNamesSummary = getOISColumnNamesSummary();
				
				closeCashTableSummary.clear();

				
				// Set Model
				ListModelTable modelP = new ListModelTable(dataSummary);
				modelP.addTableModelListener(this);
				closeCashTableSummary.setData(modelP, columnNamesSummary);
				configureMiniTableSummary(closeCashTableSummary);
				dataSummary.removeAllElements();		
				
			}
			
		}
		
	}

	@Override
	public void onEvent(Event e) throws Exception {
		log.config("");
		

		
		if(e.getTarget().equals(searchButton)){
			
			search();
			
		}else if(e.getTarget().equals(processCloseCash)){
			
			Timestamp p_DateTrx  = null;
			int AD_User_ID = Env.getAD_User_ID(ctx);
			String clientValue = "";
			String posName = "";
			String kasirName = "";
			String documentNo = "";
			Integer seqTutupKas = 0;
			int C_DecorisPOS_ID =0;
			
			X_C_DecorisPOS decPOS = null;
			X_C_Decoris_CloseCash dec_closeCash = null;
			
			
			if (closeCashTable.getRowCount()==0){
				return;
			}
			
			//cek have checked data
			boolean IsChecked = false;
			for (int i = 0 ; i < closeCashTable.getRowCount(); i++){
				
				boolean isCek = (boolean) closeCashTable.getValueAt(i, 0);
				if(isCek){
					IsChecked = true;	
				}
			}
			
			
				
			if(!IsChecked){
				FDialog.info(windowNo, null, "", "Tidak Ada Transaksi Terpilih Untuk Dilakukan Tutup Kas","Info");
				return;
			}
			
			for (int i = 0 ; i < closeCashTable.getRowCount(); i++){
				
				boolean isCek = (boolean) closeCashTable.getValueAt(i, 0);
				Timestamp dateTrx = null;
				Timestamp dateCloseCash = null;
				
				if (isCek){
					dateTrx = (Timestamp) closeCashTable.getValueAt(i, 1);
					dateCloseCash = (Timestamp) closeCashTable.getValueAt(i, 2);
					p_DateTrx = dateTrx;

					
					if (dateCloseCash != null){
						
						FDialog.info(windowNo, null, "", "Transaksi Sudah Dilakukan Tutup Kas, Tidak Dapat Di Proses Kembali","Info");
						return;
					}	
					
					String p_dateSeq =new SimpleDateFormat("dd-MM-yyyy").format(p_DateTrx);
					String msg = "";
									
					//Kasir_ID
					String sqlKasir = "SELECT C_BPartner_ID FROM AD_User WHERE AD_Client_ID = ? AND AD_User_ID = ?";
					CreatedByPOS_ID =  DB.getSQLValueEx(ctx.toString(), sqlKasir.toString(), new Object[]{AD_Client_ID,AD_User_ID});
					
					//POSName
					String sqlPOSName = "SELECT Name FROM C_POS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?";
					posName =  DB.getSQLValueStringEx(ctx.toString(), sqlPOSName.toString(), new Object[]{AD_Client_ID,CreatedByPOS_ID});
					
					//KasirName
					//MUser user = new MUser(ctx, AD_User_ID, null);
					//kasirName = user.getName();
					
					//Client Value
					MClient client = new MClient(ctx, AD_Client_ID, null);
					clientValue = client.getValue();
					
					if (clientValue == "" ||clientValue == null || clientValue.isEmpty()){
						FDialog.info(windowNo, null, "", "Silahkan Pilih Kasir Terlebih Dahulu Sebelum Melakukan Proses Tutup Kas","Info");
						return;
					}
					
					String kasirValueField = kasirNameField.getValue();
					StringBuilder SQLKasir_ID = new StringBuilder();		
					SQLKasir_ID.append("SELECT C_BPartner_ID ");
					SQLKasir_ID.append("FROM C_BPartner ");
					SQLKasir_ID.append("WHERE AD_Client_ID = ?");
					SQLKasir_ID.append(" AND Name = '" + kasirValueField + "'");

					Integer p_Kasir_ID = DB.getSQLValueEx(null, SQLKasir_ID.toString(), AD_Client_ID);
					
					//seq tutup kas
					StringBuilder sqlSeq = new StringBuilder();
						sqlSeq.append("SELECT MAX(seqTutupKas) ");
						sqlSeq.append("FROM C_DecorisPOS ");
						sqlSeq.append("WHERE AD_Client_ID= ? ");
						sqlSeq.append("AND CreatedByPOS_ID = ? ");
						sqlSeq.append("AND DateOrdered = '" + dateTrx.toString() + "'");

						
					seqTutupKas = DB.getSQLValueEx(ctx.toString(), sqlSeq.toString(), new Object []{AD_Client_ID,CreatedByPOS_ID});	
					
					if (seqTutupKas == null){
						seqTutupKas = 0;
					}
					
					if (seqTutupKas >= 0){
						seqTutupKas = seqTutupKas + 1;
					}
					
					
					documentNo = clientValue+"/"+posName+"/"+p_dateSeq+"/"+kasirName+"/"+"00"+seqTutupKas.toString() ;
					
					//getData Tutup Kas
					StringBuilder sqlTutupKas = new StringBuilder();
						sqlTutupKas.append("SELECT C_DecorisPOS_ID ");
						sqlTutupKas.append(" FROM C_DecorisPOS ");
						sqlTutupKas.append(" WHERE AD_Client_ID= ? ");
						sqlTutupKas.append(" AND CreatedByPOS_ID = ? ");
						sqlTutupKas.append(" AND IsTutupKas = 'N' ");
						sqlTutupKas.append(" AND DateOrdered = ?");

					BigDecimal bayarTunai1 = Env.ZERO;
					BigDecimal bayarTunai2 = Env.ZERO;	
					BigDecimal bayarTunai3 = Env.ZERO;
					BigDecimal bayarBank1 = Env.ZERO;
					BigDecimal bayarBank2 = Env.ZERO;	
					BigDecimal bayarBank3 = Env.ZERO;
					BigDecimal bayarLeasingSpektra1 = Env.ZERO;	
					BigDecimal bayarLeasingSpektra2 = Env.ZERO;	
					BigDecimal bayarLeasingSpektra3 = Env.ZERO;	
					BigDecimal bayarLeasingOther1 = Env.ZERO;	
					BigDecimal bayarLeasingOther2 = Env.ZERO;	
					BigDecimal bayarLeasingOther3 = Env.ZERO;	
					BigDecimal bayarLainlain1  = Env.ZERO;	
					BigDecimal bayarLainlain2  = Env.ZERO;	
					BigDecimal bayarLainlain3  = Env.ZERO;
					BigDecimal kasKeluar1  = Env.ZERO;	
					BigDecimal kasKeluar2  = Env.ZERO;	
					BigDecimal kasKeluar3  = Env.ZERO;	
					BigDecimal taxAmt  = Env.ZERO;
					BigDecimal curBal  = Env.ZERO;
					BigDecimal bayarTunaiPenerimaan = Env.ZERO;
					BigDecimal bayarTunaiPengeluaran = Env.ZERO;
					BigDecimal jmlKasMasuk = Env.ZERO;
					BigDecimal jmlKasKeluar = Env.ZERO;
		
					int countTrx = 0; 
						
					@SuppressWarnings("unused")
					String WhereClausePenjualan = " WHERE cdec.IsPenjualan = 'Y' "
							+ "AND cdec.IsTutupKas = 'N' "
							+ "AND cdec.AD_Client_ID = ?  "
							+ "AND cdec.CreatedByPOS_ID = ? "
							+ "AND cdec.DateOrdered = ?  "
							+ "AND co.DocStatus NOT IN ('CL','RE','VO')";
					
					
					String sqlByarTunai1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.pembayaran1 > 0 AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ?  AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ?  AND cdec.payType1 = 'TUNAI' AND co.DocStatus NOT IN ('CL','RE','VO')";			
					bayarTunai1 = DB.getSQLValueBDEx(null, sqlByarTunai1, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarBank1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType1 = 'BANK' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarBank1 = DB.getSQLValueBDEx(null, sqlByarBank1 , new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarLeasingSpektra1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType1 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'Y' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLeasingSpektra1 = DB.getSQLValueBDEx(null, sqlByarLeasingSpektra1, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarLeasingOther1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType1 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'N' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLeasingOther1 = DB.getSQLValueBDEx(null, sqlByarLeasingOther1, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});					
					String sqlByarLainLain1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType1 = 'HUTANG' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLainlain1 = DB.getSQLValueBDEx(null, sqlByarLainLain1, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlKasKeluar1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType1 = 'TUNAI' AND cdec.IsPembatalan = 'Y' AND cdec.pembayaran1 < 0 AND co.DocStatus NOT IN ('VO','CL','RE')";
					kasKeluar1 = DB.getSQLValueBDEx(null, sqlKasKeluar1, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});					
					
					String sqlByarTunai2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.pembayaran2 > 0 AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType2 = 'TUNAI' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarTunai2 = DB.getSQLValueBDEx(null, sqlByarTunai2, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarBank2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType2 = 'BANK' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarBank2 = DB.getSQLValueBDEx(null, sqlByarBank2 , new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarLeasingSpektra2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType2 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'Y' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLeasingSpektra2 = DB.getSQLValueBDEx(null, sqlByarLeasingSpektra2, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarLeasingOther2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType2 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'N' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLeasingOther2 = DB.getSQLValueBDEx(null, sqlByarLeasingOther2, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarLainLain2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType2 = 'HUTANG' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLainlain2 = DB.getSQLValueBDEx(null, sqlByarLainLain2, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlKasKeluar2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType2 = 'TUNAI' AND cdec.IsPembatalan = 'Y' AND cdec.pembayaran2 < 0 AND co.DocStatus NOT IN ('VO','CL','RE')";
					kasKeluar2 = DB.getSQLValueBDEx(null, sqlKasKeluar2, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					
					String sqlByarTunai3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.pembayaran3 > 0 AND cdec.IsPembatalan = 'N' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType3 = 'TUNAI' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarTunai3 = DB.getSQLValueBDEx(null, sqlByarTunai3, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarBank3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType3 = 'BANK' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarBank3 = DB.getSQLValueBDEx(null, sqlByarBank3 , new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarLeasingSpektra3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType3 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'Y' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLeasingSpektra3 = DB.getSQLValueBDEx(null, sqlByarLeasingSpektra3, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlByarLeasingOther3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType3 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'N' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLeasingOther3 = DB.getSQLValueBDEx(null, sqlByarLeasingOther3, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					String sqlKasKeluar3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType3 = 'TUNAI' AND cdec.IsPembatalan = 'Y' AND cdec.pembayaran3 < 0 AND co.DocStatus NOT IN ('VO','CL','RE')";
					kasKeluar3 = DB.getSQLValueBDEx(null, sqlKasKeluar3, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					
					
					
					String sqlByarLainLain3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND cdec.payType3 = 'HUTANG' AND co.DocStatus NOT IN ('VO','CL','RE')";
					bayarLainlain3 = DB.getSQLValueBDEx(null, sqlByarLainLain3, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});

					
					String sqltax= "SELECT Coalesce(SUM(cdec.taxAmt),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ? AND co.DocStatus NOT IN ('VO','CL','RE')";
					taxAmt = DB.getSQLValueBDEx(null, sqltax, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});

					String SQLCurBal = "SELECT CurrentBalance FROM C_BankAccount WHERE AD_Client_ID = ? AND C_BankAccount_ID IN (SELECT DISTINCT C_BankAccount_ID FROM C_POS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?)";
					curBal = DB.getSQLValueBDEx(null, SQLCurBal, new Object[]{AD_Client_ID,AD_Client_ID,CreatedByPOS_ID});
					
					
					String sqlByarTunaiPenerimaan = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Payment pay ON pay.C_Payment_ID = cdec.C_Payment_ID  WHERE cdec.IsPembayaran = 'Y' AND cdec.IsReceipt = 'Y' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ?  AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ?  AND cdec.payType1 = 'TUNAI' AND pay.DocStatus NOT IN ('CL','RE','VO')";			
					bayarTunaiPenerimaan = DB.getSQLValueBDEx(null, sqlByarTunaiPenerimaan, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					
					String sqlByarTunaiPengeluaran = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Payment pay ON pay.C_Payment_ID = cdec.C_Payment_ID  WHERE cdec.IsPembayaran = 'Y' AND cdec.IsReceipt = 'N' AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ?  AND cdec.CreatedByPOS_ID = ? AND cdec.DateOrdered = ?  AND cdec.payType1 = 'TUNAI' AND pay.DocStatus NOT IN ('CL','RE','VO')";			
					bayarTunaiPengeluaran = DB.getSQLValueBDEx(null, sqlByarTunaiPengeluaran, new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					
					
					Integer cekDecorisPOS_ID = DB.getSQLValueEx(null, sqlTutupKas.toString(), new Object[]{AD_Client_ID,CreatedByPOS_ID,p_DateTrx});
					
					if(cekDecorisPOS_ID==null){
						cekDecorisPOS_ID = 0;
					}
					
					String sqlAD_Org = "SELECT AD_Org_ID FROM C_DecorisPOS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?";
					Integer Org_ID = DB.getSQLValueEx(null, sqlAD_Org, new Object[]{AD_Client_ID,CreatedByPOS_ID});
					if(Org_ID == null){
						Org_ID = 0;
					}
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(Env.getContextAsDate(ctx, "#Date"));
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					Timestamp now = new Timestamp(cal.getTimeInMillis());
						

					BigDecimal jmlTunaiPenjualan = bayarTunai1.add(bayarTunai2).add(bayarTunai3);
					BigDecimal jmlBankPenjualan = bayarBank1.add(bayarBank2).add(bayarBank3);
					BigDecimal jmlLeasingPenjualanSpektra = bayarLeasingSpektra1.add(bayarLeasingSpektra2).add(bayarLeasingSpektra3);
					BigDecimal jmlLeasingPenjualanOther = bayarLeasingOther1.add(bayarLeasingOther2).add(bayarLeasingOther3);
					BigDecimal jmlLainlainPenjualan = bayarLainlain1.add(bayarLainlain2).add(bayarLainlain3);
					BigDecimal kasKeluarPenjualan = kasKeluar1.add(kasKeluar2).add(kasKeluar3);
					
					jmlKasMasuk = jmlTunaiPenjualan.add(bayarTunaiPenerimaan);
					jmlKasKeluar = bayarTunaiPengeluaran.add(kasKeluarPenjualan.abs());
					
					BigDecimal totalOmset = jmlTunaiPenjualan.add(jmlBankPenjualan).add(jmlLeasingPenjualanSpektra).add(jmlLeasingPenjualanOther).add(jmlLainlainPenjualan);
					
					
					if(cekDecorisPOS_ID <=0){		
						msg = "Tutup Kas Tidak Berhasil,Tidak Ada Dokumen Untuk Tutup Kas";
						FDialog.info(windowNo, null, "", msg, "Info");
						return;
					}
					
					if(cekDecorisPOS_ID > 0){
						
						dec_closeCash = new X_C_Decoris_CloseCash(ctx, 0, null);
						dec_closeCash.setAD_Org_ID(Org_ID);
						dec_closeCash.setDocumentNo(documentNo);
						dec_closeCash.setCloseCashDate(now);
						dec_closeCash.setDateTrx(p_DateTrx);
						dec_closeCash.setCash(jmlKasMasuk);
						dec_closeCash.setBankPayment(jmlBankPenjualan);
						dec_closeCash.setleasingpayment(jmlLeasingPenjualanSpektra);
						dec_closeCash.set_ValueOfColumn("OtherLeasingPayment", jmlLeasingPenjualanOther);
						dec_closeCash.set_ValueOfColumn("totalOmset", totalOmset);
						dec_closeCash.setlainlain(jmlLainlainPenjualan);
						dec_closeCash.setCashIn(jmlKasMasuk);
						dec_closeCash.setCashOut(jmlKasKeluar);
						dec_closeCash.setseqTutupKas(seqTutupKas);
						dec_closeCash.setCreatedByPOS_ID(p_Kasir_ID);
						dec_closeCash.setTaxAmt(taxAmt);
						dec_closeCash.setCurrentBalance(curBal);
						dec_closeCash.saveEx();
						
					}
					
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						pstmt = DB.prepareStatement(sqlTutupKas.toString(), null);
						pstmt.setInt(1, AD_Client_ID);
						pstmt.setInt(2, CreatedByPOS_ID);
						pstmt.setTimestamp(3, p_DateTrx);
						rs = pstmt.executeQuery();
						while (rs.next()) {
							
							countTrx++;
							C_DecorisPOS_ID = rs.getInt(1);
							decPOS = new X_C_DecorisPOS(ctx, C_DecorisPOS_ID, null);
							decPOS.setIsTutupKas(true);
							decPOS.setDocumentNoTutupKas(documentNo);
							decPOS.setseqTutupKas(seqTutupKas);
							decPOS.set_ValueNoCheck("C_Decoris_CloseCash_ID", dec_closeCash.getC_Decoris_CloseCash_ID());
							decPOS.saveEx();
							
						}

					} catch (SQLException ex) {
						log.log(Level.SEVERE, sqlTutupKas.toString(), ex);
						msg = "Error";
					} finally {
						DB.close(rs, pstmt);
						rs = null;
						pstmt = null;
					}
					
					if(dec_closeCash !=null){
						dec_closeCash.setTrxCount(countTrx);
						dec_closeCash.saveEx();
						msg = "Tutup Kas Berhasil Dilakukan";
					}
					
				}
			}
			
			cleanTable(AD_Client_ID,p_AD_Org_ID);
			insertTempTable();
			FDialog.info(windowNo, null, "", "Proses Tutup Kas Berhasil", "Info");
			search();
			
			
			int User_Print_ID = 0;
			String kasirFieldName = "";
	 		
			kasirFieldName = kasirNameField.getValue();
			if(kasirFieldName == null){
				kasirFieldName = "";
			}
			
			StringBuilder SQLPrintUser = new StringBuilder();
			SQLPrintUser.append(" SELECT AD_User_ID ");
			SQLPrintUser.append(" FROM AD_User ");
			SQLPrintUser.append(" WHERE AD_Client_ID = ? AND C_BPartner_ID = ( SELECT C_BPartner_ID ");
			SQLPrintUser.append(" FROM C_BPartner ");
			SQLPrintUser.append(" WHERE AD_Client_ID = ? AND Name = '"+ kasirFieldName +"')");

			User_Print_ID = DB.getSQLValueEx(null, SQLPrintUser.toString(), new Object[]{AD_Client_ID,AD_Client_ID});

			
			
			//
			String trxName = Trx.createTrxName("PrintCloseCash");
			String url = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/reports/";
			//String url = "D:\\SourceCode\\iDempiereBase\\reports\\";
			MProcess proc = new MProcess(Env.getCtx(), 1000073, trxName);
			MPInstance instance = new MPInstance(proc, proc.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo("Print Tutup Kas", 1000073);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			
			ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			list.add(new ProcessInfoParameter("url_path", url, null, null, null));
			list.add(new ProcessInfoParameter("AD_Client_ID", AD_Client_ID, null, null, null));
			list.add(new ProcessInfoParameter("DateOrdered", p_DateTrx, null, null, null));
			list.add(new ProcessInfoParameter("DateOrdered2", p_DateTrx, null, null, null));
			list.add(new ProcessInfoParameter("CreatedByPOS_ID", User_Print_ID, null, null, null));


			ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			list.toArray(pars);
			pi.setParameter(pars);
			
			Trx trx = Trx.get(trxName, true);
			trx.commit();

			ProcessUtil.startJavaProcess(Env.getCtx(), pi,Trx.get(trxName, true));

			
		}else if(e.getTarget().equals(printCloseCash)){
			
			Timestamp dateTrx = null;
			Timestamp dateCloseCash = null;
			int User_Print_ID = 0;
			String kasirName = "";
	 		
			kasirName = kasirNameField.getValue();
			if(kasirName == null){
				kasirName = "";
			}
			
			StringBuilder SQLPrintUser = new StringBuilder();
			SQLPrintUser.append(" SELECT AD_User_ID ");
			SQLPrintUser.append(" FROM AD_User ");
			SQLPrintUser.append(" WHERE AD_Client_ID = ? AND C_BPartner_ID = ( SELECT C_BPartner_ID ");
			SQLPrintUser.append(" FROM C_BPartner ");
			SQLPrintUser.append(" WHERE AD_Client_ID = ? AND Name = '"+ kasirName +"')");

			User_Print_ID = DB.getSQLValueEx(null, SQLPrintUser.toString(), new Object[]{AD_Client_ID,AD_Client_ID});

			
			//
			if(closeCashTable.getRowCount()==0){
				return;
			}
			
			//cek have checked data
			boolean IsChecked = false;
			for (int i = 0 ; i < closeCashTable.getRowCount(); i++){
				
				boolean isCek = (boolean) closeCashTable.getValueAt(i, 0);
				if(isCek){
					IsChecked = true;	
				}
			}
					
			if(!IsChecked){
				FDialog.info(windowNo, null, "", "Tidak Ada Transaksi Terpilih Untuk Di Cetak","Info");
				return;
			}
			
			
			for(int i = 0 ; i<closeCashTable.getRowCount() ; i++){
				boolean isCek = (boolean) closeCashTable.getValueAt(i, 0);

				if (isCek){
					dateTrx = (Timestamp) closeCashTable.getValueAt(i, 1);
					dateCloseCash = (Timestamp) closeCashTable.getValueAt(i, 2);
					
					if(dateCloseCash == null){
						FDialog.info(windowNo, null, "", "Transaksi Tidak Dapat Dicetak Karena Belum Dilakukan Tutup Kas", "Info");
						return;
					}
					
				}
			}
			

						
			String trxName = Trx.createTrxName("PrintCloseCash");
			String url = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/reports/";
			//String url = "D:\\SourceCode\\iDempiereBase\\reports\\";
			MProcess proc = new MProcess(Env.getCtx(), 1000073, trxName);
			MPInstance instance = new MPInstance(proc, proc.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo("Print Tutup Kas", 1000073);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
						
			ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
			list.add(new ProcessInfoParameter("url_path", url, null, null, null));
			list.add(new ProcessInfoParameter("AD_Client_ID", AD_Client_ID, null, null, null));
			list.add(new ProcessInfoParameter("DateOrdered", dateTrx, null, null, null));
			list.add(new ProcessInfoParameter("DateOrdered2", dateTrx, null, null, null));
			list.add(new ProcessInfoParameter("CreatedByPOS_ID", User_Print_ID, null, null, null));


			ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
			list.toArray(pars);
			pi.setParameter(pars);
						
			Trx trx = Trx.get(trxName, true);
			trx.commit();

			ProcessUtil.startJavaProcess(Env.getCtx(), pi,Trx.get(trxName, true));
			
		}

	
	}
	
	private void insertTempTable(){

		ArrayList<Timestamp> notCloseCashList = new ArrayList<Timestamp>();
		ArrayList<Integer> createdByPOSList = new ArrayList<Integer>();
		//
		BigDecimal bayarTunai1 = Env.ZERO;
		BigDecimal bayarTunai2 = Env.ZERO;	
		BigDecimal bayarTunai3 = Env.ZERO;
		BigDecimal bayarBank1 = Env.ZERO;
		BigDecimal bayarBank2 = Env.ZERO;	
		BigDecimal bayarBank3 = Env.ZERO;
		BigDecimal bayarLeasingSpektra1 = Env.ZERO;	
		BigDecimal bayarLeasingSpektra2 = Env.ZERO;	
		BigDecimal bayarLeasingSpektra3 = Env.ZERO;
		BigDecimal bayarLeasingOther1 = Env.ZERO;	
		BigDecimal bayarLeasingOther2 = Env.ZERO;	
		BigDecimal bayarLeasingOther3 = Env.ZERO;
		BigDecimal bayarLainlain1  = Env.ZERO;	
		BigDecimal bayarLainlain2  = Env.ZERO;	
		BigDecimal bayarLainlain3  = Env.ZERO;
		BigDecimal kasKeluar1  = Env.ZERO;	
		BigDecimal kasKeluar2  = Env.ZERO;
		BigDecimal kasKeluar3  = Env.ZERO;	
		BigDecimal taxAmt  = Env.ZERO;	
		BigDecimal curBal = Env.ZERO;
		BigDecimal bayarTunaiPenerimaan = Env.ZERO;
		BigDecimal bayarTunaiPengeluaran = Env.ZERO;
		BigDecimal jmlTunaiPenjualan = Env.ZERO;
		BigDecimal kasKeluarPenjualan = Env.ZERO;


		//get Data not Close Cash
		StringBuilder SQLPreCloseCash = new StringBuilder();
		SQLPreCloseCash.append("SELECT DISTINCT DateOrdered ");
		SQLPreCloseCash.append("FROM C_DecorisPOS ");
		SQLPreCloseCash.append("WHERE IsTutupKas = 'N' ");
		SQLPreCloseCash.append("AND AD_Client_ID = ? ");

		PreparedStatement pstmtcc = null;
		ResultSet rscc = null;
		
		try {
			pstmtcc = DB.prepareStatement(SQLPreCloseCash.toString(), null);
			pstmtcc.setInt(1, AD_Client_ID);

			rscc = pstmtcc.executeQuery();
			while (rscc.next()) {
				
				notCloseCashList.add(rscc.getTimestamp(1));
				
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, SQLPreCloseCash.toString(), e);
		} finally {
			DB.close(rscc, pstmtcc);
			rscc = null;
			pstmtcc = null;
		}
		
		//get Data kasir
		StringBuilder SQLGetCreatedByPOS_ID = new StringBuilder();
		SQLGetCreatedByPOS_ID.append("SELECT DISTINCT CreatedByPOS_ID ");
		SQLGetCreatedByPOS_ID.append("FROM C_POS ");
		SQLGetCreatedByPOS_ID.append("WHERE AD_Client_ID = ?");

		PreparedStatement pstmtksr = null;
		ResultSet rsksr = null;
		
		try {
			pstmtksr = DB.prepareStatement(SQLGetCreatedByPOS_ID.toString(), null);
			pstmtksr.setInt(1, AD_Client_ID);

			rsksr = pstmtksr.executeQuery();
			while (rsksr.next()) {
				
				createdByPOSList.add(rsksr.getInt(1));
				
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, SQLPreCloseCash.toString(), e);
		} finally {
			DB.close(rsksr, pstmtksr);
			rsksr = null;
			pstmtksr = null;
		}
		
		
		
		for (int i = 0 ; i < notCloseCashList.size() ; i++){
			Timestamp dateOrder = notCloseCashList.get(i);

			for(int j = 0 ; j < createdByPOSList.size() ; j++){
				int createdByPOS_ID = createdByPOSList.get(j);	
				
				String sqlByarTunai1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.pembayaran1 > 0 AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'TUNAI' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarTunai1 = DB.getSQLValueBDEx(null, sqlByarTunai1, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarBank1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'BANK' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarBank1 = DB.getSQLValueBDEx(null, sqlByarBank1 , new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarLeasingSpektra1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'Y' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLeasingSpektra1 = DB.getSQLValueBDEx(null, sqlByarLeasingSpektra1, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarLeasingOther1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'N' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLeasingOther1 = DB.getSQLValueBDEx(null, sqlByarLeasingOther1, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});					
				String sqlByarLainLain1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'HUTANG' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLainlain1 = DB.getSQLValueBDEx(null, sqlByarLainLain1, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlKasKeluar1 = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'TUNAI' AND cdec.IsPembatalan = 'Y' AND cdec.pembayaran1 < 0 AND co.DocStatus NOT IN ('CL','VO','RE')";
				kasKeluar1 = DB.getSQLValueBDEx(null, sqlKasKeluar1, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
	
				String sqlByarTunai2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.pembayaran2 > 0 AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType2 = 'TUNAI' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarTunai2 = DB.getSQLValueBDEx(null, sqlByarTunai2, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarBank2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType2 = 'BANK' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarBank2 = DB.getSQLValueBDEx(null, sqlByarBank2 , new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarLeasing2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType2 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'Y' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLeasingSpektra2 = DB.getSQLValueBDEx(null, sqlByarLeasing2, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarLeasingOther2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType2 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'N' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLeasingOther2 = DB.getSQLValueBDEx(null, sqlByarLeasingOther2, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarLainLain2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType2 = 'HUTANG' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLainlain2 = DB.getSQLValueBDEx(null, sqlByarLainLain2, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlKasKeluar2 = "SELECT Coalesce(SUM(cdec.Pembayaran2),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType2 = 'TUNAI' AND cdec.IsPembatalan = 'Y' AND cdec.pembayaran2 < 0 AND co.DocStatus NOT IN ('CL','VO','RE')";
				kasKeluar2 = DB.getSQLValueBDEx(null, sqlKasKeluar2, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
	
				String sqlByarTunai3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.pembayaran3 > 0 AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType3 = 'TUNAI' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarTunai3 = DB.getSQLValueBDEx(null, sqlByarTunai3, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarBank3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType3 = 'BANK' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarBank3 = DB.getSQLValueBDEx(null, sqlByarBank3 , new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarLeasing3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType3 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'Y' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLeasingSpektra3 = DB.getSQLValueBDEx(null, sqlByarLeasing3, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlByarLeasingOther3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType3 = 'LEASING' AND cdec.IsLeasing = 'Y' AND cdec.IsSpektra = 'N' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLeasingOther3 = DB.getSQLValueBDEx(null, sqlByarLeasingOther3, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				String sqlKasKeluar3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType3 = 'TUNAI' AND cdec.IsPembatalan = 'Y' AND cdec.pembayaran3 < 0 AND co.DocStatus NOT IN ('CL','VO','RE')";
				kasKeluar3 = DB.getSQLValueBDEx(null, sqlKasKeluar3, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				
				String sqlByarLainLain3 = "SELECT Coalesce(SUM(cdec.Pembayaran3),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.IsPenjualan = 'Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType3 = 'HUTANG' AND co.DocStatus NOT IN ('CL','VO','RE')";
				bayarLainlain3 = DB.getSQLValueBDEx(null, sqlByarLainLain3, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
					
				String sqlByarTunaiPenerimaan = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Payment pay ON pay.C_Payment_ID = cdec.C_Payment_ID WHERE cdec.IsPembayaran = 'Y' AND cdec.IsReceipt ='Y' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'TUNAI' AND pay.DocStatus NOT IN ('CL','VO','RE')";
				bayarTunaiPenerimaan = DB.getSQLValueBDEx(null, sqlByarTunaiPenerimaan, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				
				String sqlByarTunaiPengeluaran = "SELECT Coalesce(SUM(cdec.Pembayaran1),0) FROM C_DecorisPOS cdec LEFT JOIN C_Payment pay ON pay.C_Payment_ID = cdec.C_Payment_ID WHERE cdec.IsPembayaran = 'Y' AND cdec.IsReceipt ='N' AND cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND cdec.payType1 = 'TUNAI' AND pay.DocStatus NOT IN ('CL','VO','RE')";
				bayarTunaiPengeluaran = DB.getSQLValueBDEx(null, sqlByarTunaiPengeluaran, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				
				
				String sqlCountTrx = "SELECT Count(C_DecorisPOS_ID) FROM C_DecorisPOS WHERE DateOrdered = ? AND IsTutupKas = 'N' AND AD_Client_ID = ? AND CreatedByPOS_ID = ? ";
				int countTrx = DB.getSQLValueEx(null, sqlCountTrx, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});
				
				String sqltax= "SELECT Coalesce(SUM(cdec.taxAmt),0) FROM C_DecorisPOS cdec LEFT JOIN C_Order co ON co.C_Order_ID = cdec.C_Order_ID WHERE cdec.DateOrdered = ? AND cdec.IsTutupKas = 'N' AND cdec.AD_Client_ID = ? AND cdec.CreatedByPOS_ID = ? AND co.DocStatus NOT IN ('CL','VO','RE')";
				taxAmt = DB.getSQLValueBDEx(null, sqltax, new Object[]{dateOrder,AD_Client_ID,createdByPOS_ID});	
				
				String SQLCurBal = "SELECT CurrentBalance FROM C_BankAccount WHERE AD_Client_ID = ? AND C_BankAccount_ID IN (SELECT DISTINCT C_BankAccount_ID FROM C_POS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?)";
				curBal = DB.getSQLValueBDEx(null, SQLCurBal, new Object[]{AD_Client_ID,AD_Client_ID,CreatedByPOS_ID});
							
				String sqlAD_Org = "SELECT AD_Org_ID FROM C_DecorisPOS WHERE AD_Client_ID = ? AND CreatedByPOS_ID = ?";
				Integer Org_ID = DB.getSQLValueEx(null, sqlAD_Org, new Object[]{AD_Client_ID,createdByPOS_ID  });
				if(Org_ID == null){
					Org_ID = 0;
				}
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(Env.getContextAsDate(ctx, "#Date"));
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);					
	


				BigDecimal jmlBankPenjualan = bayarBank1.add(bayarBank2).add(bayarBank3);
				BigDecimal jmlLeasingPenjualanSpektra = bayarLeasingSpektra1.add(bayarLeasingSpektra2).add(bayarLeasingSpektra3);
				BigDecimal jmlLeasingPenjualanOther = bayarLeasingOther1.add(bayarLeasingOther2).add(bayarLeasingOther3);

				BigDecimal jmlLainlainPenjualan = bayarLainlain1.add(bayarLainlain2).add(bayarLainlain3);
				BigDecimal saldoAwal = Env.ZERO;	
				
				jmlTunaiPenjualan = bayarTunai1.add(bayarTunai2).add(bayarTunai3);
				kasKeluarPenjualan = kasKeluar1.add(kasKeluar2).add(kasKeluar3);
				
				BigDecimal jmlKasMasuk = jmlTunaiPenjualan.add(bayarTunaiPenerimaan);
				BigDecimal jmlKasKeluar = bayarTunaiPengeluaran.add(kasKeluarPenjualan.abs());
				
				BigDecimal totalOmset = jmlTunaiPenjualan.add(jmlBankPenjualan).add(jmlLeasingPenjualanSpektra).add(jmlLeasingPenjualanOther).add(jmlLainlainPenjualan);

				
				if (isSaldoAwal){
					saldoAwal = curBal;
				}
				
				if(saldoAwal == null){
					saldoAwal = Env.ZERO;
				}
				
				
				if(countTrx > 0){
					StringBuilder sqlInsert = new StringBuilder();
					sqlInsert.append("INSERT INTO T_CloseCashSummary(AD_Client_ID,AD_Org_ID,AD_PInstance_ID,CashIn,"
							+ "CashOut,CloseCashDate,CurrentBalance,DateTrx,DocumentNoTutupKas,"
							+ "TaxAmt,Total,TrxCount,CreatedByPOS_ID,Cash,BankPayment,LeasingPayment,LainLain,OtherLeasingPayment,totalOmset)"
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					Object[] params = new Object[]{AD_Client_ID,Org_ID,
							null,jmlKasMasuk,jmlKasKeluar,null,saldoAwal,dateOrder,"",
							taxAmt,saldoAwal.add(jmlKasMasuk.subtract(jmlKasKeluar)),countTrx,createdByPOS_ID,
							jmlKasMasuk,jmlBankPenjualan,jmlLeasingPenjualanSpektra,jmlLainlainPenjualan,jmlLeasingPenjualanOther,totalOmset};
					
					int no = DB.executeUpdate(sqlInsert.toString(), params, true, null);
					log.fine("#" + no);
				}
				
			}	
	
		}	
		
		//Insert Closed Cash
		StringBuilder SQLGetClosedCash = new StringBuilder();
		SQLGetClosedCash.append("SELECT AD_Client_ID,AD_Org_ID,CashIn,CashOut,CloseCashDate,");
		SQLGetClosedCash.append("DateTrx,DocumentNo,TaxAmt,CashIn-CashOut,TrxCount,CreatedByPOS_ID,seqTutupKas,Cash,BankPayment,LeasingPayment,LainLain,CurrentBalance,OtherLeasingPayment,totalOmset ");
		SQLGetClosedCash.append("FROM C_Decoris_CloseCash ");
		SQLGetClosedCash.append("WHERE AD_Client_ID = ? ");
		

		PreparedStatement pstmtClsd = null;
		ResultSet rsClsd = null;
		
		try {
			pstmtClsd = DB.prepareStatement(SQLGetClosedCash.toString(), null);
			pstmtClsd.setInt(1, AD_Client_ID);

			rsClsd = pstmtClsd.executeQuery();
			while (rsClsd.next()) {
				BigDecimal saldoAwal = Env.ZERO;
				
				if(isSaldoAwal){
					saldoAwal = rsClsd.getBigDecimal(17);
				}
				
				if (saldoAwal == null){
					saldoAwal = Env.ZERO;
				}
				
				
				StringBuilder sqlInsert = new StringBuilder();
				sqlInsert.append("INSERT INTO T_CloseCashSummary(AD_Client_ID,AD_Org_ID,AD_PInstance_ID,CashIn,"
						+ "CashOut,CloseCashDate,CurrentBalance,DateTrx,DocumentNoTutupKas,"
						+ "TaxAmt,Total,TrxCount,CreatedByPOS_ID,SeqTutupKas,"
						+ "Cash,BankPayment,LeasingPayment,LainLain,OtherLeasingPayment,totalOmset)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				Object[] params = new Object[]{rsClsd.getInt(1),rsClsd.getInt(2),
						null,rsClsd.getBigDecimal(3),rsClsd.getBigDecimal(4),rsClsd.getTimestamp(5),saldoAwal,rsClsd.getTimestamp(6),rsClsd.getString(7),
						rsClsd.getBigDecimal(8),saldoAwal.add(rsClsd.getBigDecimal(9)),rsClsd.getInt(10),rsClsd.getInt(11),rsClsd.getInt(12),
						rsClsd.getBigDecimal(13),rsClsd.getBigDecimal(14),rsClsd.getBigDecimal(15),rsClsd.getBigDecimal(16),rsClsd.getBigDecimal(18),rsClsd.getBigDecimal(19)};
				
				int no = DB.executeUpdate(sqlInsert.toString(), params, true, null);
				log.fine("#" + no);
				
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, SQLGetClosedCash.toString(), e);
		} finally {
			DB.close(rsClsd, pstmtClsd);
			rsClsd = null;
			pstmtClsd = null;
		}
	
	}
	
	protected void cleanTable(int AD_Client_ID, int AD_Org_ID){
			
		StringBuilder SQLCleanTable = new StringBuilder();
		SQLCleanTable.append(" DELETE FROM T_CloseCashSummary");
		SQLCleanTable.append(" WHERE AD_Client_ID = "+ AD_Client_ID);
		SQLCleanTable.append(" AND AD_Org_ID = "+ AD_Org_ID);

		DB.executeUpdate(SQLCleanTable.toString(), null);
	}
	
	private void search(){
		
		Timestamp date1 = (Timestamp) dateTransactionField.getValue();
		Timestamp date2 = (Timestamp) dateTransactionField2.getValue();

		String kasirName = "";
 		
		kasirName = kasirNameField.getValue();
		if(kasirName == null){
			kasirName = "";
		}
 		

		StringBuilder SQLKasir_ID = new StringBuilder();		
		SQLKasir_ID.append("SELECT C_BPartner_ID ");
		SQLKasir_ID.append("FROM C_BPartner ");
		SQLKasir_ID.append("WHERE AD_Client_ID = ?");
		SQLKasir_ID.append("AND Name = '" + kasirName + "'");

		Integer p_Kasir_ID = DB.getSQLValueEx(null, SQLKasir_ID.toString(), AD_Client_ID);
		
		if (p_Kasir_ID == null){
			p_Kasir_ID = 0;
		}
		
		if (p_Kasir_ID == -1){
			p_Kasir_ID = 0;
		}
		
		String kriteriaData = (String) dataCriteriaList.getValue().toString().toUpperCase();
		String kriteria = "";
		
		if (kriteriaData.equals("SELURUH DATA")){
			kriteria = "A";
		}else if(kriteriaData.equals("BELUM TUTUP KAS")){
			kriteria = "B";
		}else if(kriteriaData.equals("SUDAH TUTUP KAS")){
			kriteria = "C";
		}
		
		Vector<Vector<Object>> data = getCloseCashData(AD_Client_ID,p_AD_Org_ID, date1, date2, p_Kasir_ID,kriteria,closeCashTable);
		Vector<String> columnNames = getOISColumnNames();
		
		closeCashTable.clear();

		// Set Model
		ListModelTable modelP = new ListModelTable(data);
		modelP.addTableModelListener(this);
		closeCashTable.setData(modelP, columnNames);
		configureMiniTable(closeCashTable);
		data.removeAllElements();	
	}
}
