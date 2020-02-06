package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Vector;

import org.adempiere.webui.component.Button;
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
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
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
 * Decoris Pencairan Leasing
 *
 */

public class WPosPelunasan extends PosPelunasan implements IFormController,
EventListener<Event>, WTableModelListener, ValueChangeListener  {
	
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
	
	// Pelanggan
	private Label PelangganLabel = new Label("Pelanggan :");
	private WSearchEditor PelangganSearch = null;
	
	//Pencarian Data
	private Button searchButton = new Button();
	
	//Proses pelunasan
	private Button processPelunasan = new Button();
	
	//select All
	private Button selectAll = new Button();
	
	// Leasing provider
	private Label leasingProviderLabel = new Label("Leasing :");
	private WTableDirEditor leasingProviderSearch = null;
	
	//Table Peluanasan
	private WListbox pelunasanTable = ListboxFactory.newDataTable();
	
	//variable
	private Properties ctx = Env.getCtx();
	private int AD_Client_ID  = Env.getAD_Client_ID(ctx);
	private int windowNo = form.getWindowNo();
	//private int AD_Usr_ID = Env.getAD_User_ID(ctx);
	
	public WPosPelunasan() {
		dyInit();
		init();	
	}
	
	private void dyInit() {
		
		//Lookup Client
		MLookup lookupClient = MLookupFactory.get(ctx, form.getWindowNo(), 0,14621, DisplayType.TableDir);
		clientSearch = new WTableDirEditor("AD_Table_ID", true,false, true, lookupClient);
		clientSearch.addValueChangeListener(this);
		clientSearch.setMandatory(true);
		clientSearch.setValue(AD_Client_ID);
		
		//Lookup Org
		MLookup orgLookup = MLookupFactory.get(ctx, form.getWindowNo(), 0,2163, DisplayType.TableDir);
		org = new WTableDirEditor("AD_Org_ID", true, false, true, orgLookup);
		org.addValueChangeListener(this);
		org.setMandatory(true);
		
		//Lookup BP
		MLookup lookupBP = MLookupFactory.get(ctx, form.getWindowNo(), 0, 2893,DisplayType.Search);
		PelangganSearch = new WSearchEditor("C_BPartner_ID", true, false, true,lookupBP);
		PelangganSearch.addValueChangeListener(this);
		PelangganSearch.setMandatory(true);	
		
		//Initial value leasing lookup
		MLookup leasingLookup = MLookupFactory.get(ctx, form.getWindowNo(), 0,331558, DisplayType.List);
		leasingProviderSearch = new WTableDirEditor("leaseprovider", true,false, true, leasingLookup);
		leasingProviderSearch.addValueChangeListener(this);
		
		processPelunasan.setEnabled(false);
		
		
	}

	private void init() {
		
		form.appendChild(mainLayout);
		
		form.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>(){
	
			@Override
			public void onEvent(Event arg0) throws Exception {
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
		row.appendCellChild(new Space(), 1);
		row.appendCellChild(orgLabel.rightAlign(), 1);
		row.appendCellChild(org.getComponent(), 1);
		org.getComponent().setHflex("true");
		row.appendCellChild(new Space(), 1);
		
		//Nama Pelanggan
		row = rows.newRow();
		row.appendCellChild(PelangganLabel.rightAlign(),1);
		row.appendCellChild(PelangganSearch.getComponent(), 1);
		PelangganSearch.getComponent().setHflex("true");
		
		row.appendCellChild(new Space(), 1);

		//Tanggal Transaksi		
		row.appendCellChild(dateTransactionLabel.rightAlign(),1);
		Hbox hBox = new Hbox();
		row.appendCellChild(hBox , 2);
		hBox.setHflex("true");
		hBox.setAlign("center");
		hBox.appendChild(dateTransactionField.getComponent());
		hBox.appendChild(until);
		hBox.appendChild(dateTransactionField2.getComponent());
	
		//Nama Leasing Provider
		row = rows.newRow();
		row.appendCellChild(leasingProviderLabel.rightAlign(),1);
		row.appendCellChild(leasingProviderSearch.getComponent(), 1);
		leasingProviderSearch.getComponent().setHflex("true");		
		
		//Pencarian Data
		row = rows.newRow();
		row.appendCellChild(new Space(), 4);
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
		southRow.appendChild(selectAll);
		selectAll.setLabel("Select All");
		selectAll.addActionListener(this);
		selectAll.setHflex("true");
		
		southRow.appendCellChild( new Space(),2);
		
		Hbox southHBox = new Hbox();
		southRow.appendCellChild(southHBox , 1);
		southHBox.setHflex("true");
		southHBox.setAlign("right");
		southHBox.appendChild(processPelunasan);
		processPelunasan.setLabel("Proses Pelunasan");
		processPelunasan.addActionListener(this);
		processPelunasan.setHflex("true");
		
		//Print
		
		southRow = southRows.newRow();
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
		center.appendChild(pelunasanTable);
		pelunasanTable.setWidth("100%");
		pelunasanTable.setHeight("100%");
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


	@Override
	public void valueChange(ValueChangeEvent evt) {
		
	}

	@Override
	public void tableChanged(WTableModelEvent e) {
		
	}

	@Override
	public void onEvent(Event e) throws Exception {

		if (e.getTarget().equals(searchButton)){
			
			search();
			processPelunasan.setEnabled(true);
			
		}else if(e.getTarget().equals(selectAll)){
			
			if(selectAll.getLabel().equals("Select All")){
			
				for (int i = 0 ; i < pelunasanTable.getRowCount() ; i++){
					
					pelunasanTable.setValueAt(true, i, 0);
					
				}
				selectAll.setLabel("Deselect All");
			}else if (selectAll.getLabel().equals("Deselect All")){
				
				for (int i = 0 ; i < pelunasanTable.getRowCount() ; i++){
					
					pelunasanTable.setValueAt(false, i, 0);
					
				}
				selectAll.setLabel("Select All");

			}
			
		}

		else if(e.getTarget().equals(processPelunasan)){
			//tmp_cmd
			//boolean isLeasing = LeasingFlag.isChecked();
			boolean isLeasing = true;
			String infoMsg = "";
			
			if(isLeasing){
				for (int i =0 ; i<pelunasanTable.getRowCount(); i++){
					
					boolean isSelected = (boolean) pelunasanTable.getValueAt(i,0);
					
					//just allow payment selected
					if(isSelected){
						
						KeyNamePair payPair = (KeyNamePair) pelunasanTable.getValueAt(i, 1);
						int C_Payment_ID = payPair.getKey();
						
						if(C_Payment_ID > 0 ){
							
							MPayment payment = new MPayment(ctx, C_Payment_ID, null);
							payment.processIt(MPayment.DOCACTION_Complete);
							payment.saveEx();
													
							if(infoMsg == ""){
								infoMsg = "Dokumen Penerimaan "+payment.getDocumentNo()+" Completed "+"\n";
							}else{
								infoMsg = infoMsg+"Dokumen Penerimaan "+payment.getDocumentNo()+" Completed "+"\n";
							}
						}
					}
					
				}
				
				//show info result process
				FDialog.info(windowNo, null, "", infoMsg, "Info");
				
				//re-query after process
				search();
			}else if(!isLeasing){
				//boolean DP = IsDP.isChecked(); 
				for (int i =0 ; i<pelunasanTable.getRowCount(); i++){
					
					boolean isSelected = (boolean) pelunasanTable.getValueAt(i,0);
					//BigDecimal Amt = (BigDecimal) pelunasanTable.getValueAt(i, 8);
					//just allow payment selected
					if(isSelected){
						
						KeyNamePair payPair = (KeyNamePair) pelunasanTable.getValueAt(i, 2);
						int C_Invoice_ID = payPair.getKey();
						
						if(C_Invoice_ID > 0 ){
							MInvoice inv = new MInvoice(ctx, C_Invoice_ID, null);
							MOrder ord = new MOrder(ctx, inv.getC_Order_ID(), null);
							MPayment pay = new MPayment(ctx, 0, null);
							pay.setClientOrg(AD_Client_ID,inv.getAD_Org_ID());
						
							//
							StringBuilder SQLDocTypeARR = new StringBuilder();
							SQLDocTypeARR.append("SELECT C_DocType_ID ");
							SQLDocTypeARR.append("FROM  C_DocType ");
							SQLDocTypeARR.append("WHERE AD_Client_ID = ?");
							SQLDocTypeARR.append("AND DocBaseType = '" + MDocType.DOCBASETYPE_ARReceipt+ "' ");
							SQLDocTypeARR.append("AND IsSoTrx ='Y' ");
							
							int C_DocTypeARR_ID = DB.getSQLValueEx(null, SQLDocTypeARR.toString(), AD_Client_ID);

							pay.setC_DocType_ID(C_DocTypeARR_ID);
							pay.setIsReceipt(true);
							pay.setC_BPartner_ID(inv.getC_BPartner_ID());
							pay.setDescription(inv.getDescription());
							pay.setDateTrx(inv.getDateInvoiced());
							pay.setDateAcct(inv.getDateInvoiced());
							pay.setTenderType(MPayment.TENDERTYPE_Cash);
							pay.setC_Currency_ID(inv.getC_Currency_ID());
							pay.set_ValueNoCheck("CreatedByPOS_ID", Env.getAD_User_ID(ctx));
							pay.setC_Invoice_ID(inv.getC_Invoice_ID());
							pay.saveEx();
							pay.processIt(MPayment.DOCACTION_Complete);
							pay.saveEx();
							
							if(infoMsg == ""){
								infoMsg = "Dokumen Penerimaan "+pay.getDocumentNo()+" sudah Terbuat atas Dokumen Penjualan "+ord.getDocumentNo()+"\n";
							}else{
								infoMsg =infoMsg + "Dokumen Penerimaan "+pay.getDocumentNo()+" sudah Terbuat atas Dokumen Penjualan "+ord.getDocumentNo()+"\n";
							}
						
						}
					}
					
				}
				
				//show info result process
				FDialog.info(windowNo, null, "", infoMsg, "Info");
				
				//requery after end process
				search();
			}
		}		
	}

	@Override
	public ADForm getForm() {
		return form;
	}

	private void search(){
		
		Timestamp date1 = (Timestamp) dateTransactionField.getValue();
		Timestamp date2 = (Timestamp) dateTransactionField2.getValue();
		Integer C_BPartner_ID = 0;
		Integer AD_Org_ID = 0;
		boolean isLeasing = true;
	
		C_BPartner_ID = (Integer) PelangganSearch.getValue();
		AD_Org_ID = (Integer) org.getValue();
		
		if(C_BPartner_ID == null){
			C_BPartner_ID = 0;
		}
		
		if(AD_Org_ID == null){
			AD_Org_ID = 0;
		}
		
		if(isLeasing){
			String leaseProv = (String) leasingProviderSearch.getValue();
			
			Vector<Vector<Object>> data = getPelunasanDataLeasing(AD_Client_ID,date1, date2,C_BPartner_ID,AD_Org_ID,pelunasanTable,leaseProv);
			Vector<String> columnNames = getOISColumnNamesLeasing();
			
			pelunasanTable.clear();
	
			// Set Model
			ListModelTable modelP = new ListModelTable(data);
			modelP.addTableModelListener(this);
			pelunasanTable.setData(modelP, columnNames);
			configureMiniTableLeasing(pelunasanTable);
			data.removeAllElements();	
		}else if(!isLeasing){
			
		//	Vector<Vector<Object>> data = getPelunasanDataHutang(AD_Client_ID,date1, date2,C_BPartner_ID,AD_Org_ID,IsDP.isChecked(),pelunasanTable);
			Vector<Vector<Object>> data = getPelunasanDataHutang(AD_Client_ID,date1, date2,C_BPartner_ID,AD_Org_ID,false,pelunasanTable);
			Vector<String> columnNames = getOISColumnNamesHutang();
			
			pelunasanTable.clear();
	
			// Set Model
			ListModelTable modelP = new ListModelTable(data);
			modelP.addTableModelListener(this);
			pelunasanTable.setData(modelP, columnNames);
			configureMiniTableHutang(pelunasanTable);
			data.removeAllElements();	
			
		}	
		
		
	}
	
	//Table Leasing
	protected void configureMiniTableLeasing(IMiniTable miniTable) {
		miniTable.setColumnClass(0, Boolean.class, false);
		miniTable.setColumnClass(1, String.class, true); 		// 2-No Payment
		miniTable.setColumnClass(2, Timestamp.class, true); 	// 3-Tgl Transaksi
		miniTable.setColumnClass(3, String.class, true); 		// 4-Pelanggan
		miniTable.setColumnClass(4, String.class, true); 		// 5-Keterangan
		miniTable.setColumnClass(5, BigDecimal.class, true); 	// 5-Jumlah


		miniTable.autoSize();

	}

	protected Vector<String> getOISColumnNamesLeasing() {
		
		// Header Info
		Vector<String> columnNames = new Vector<String>(6);
		columnNames.add(Msg.getMsg(ctx, "Select"));
		columnNames.add("No Payment");
		columnNames.add("Tgl Transaksi");
		columnNames.add("Pelanggan");
		columnNames.add("Keterangan");
		columnNames.add("Jumlah Pembayaran");


		return columnNames;
	
	}
	
	
	//Table Leasing
	protected void configureMiniTableHutang(IMiniTable miniTable) {
		miniTable.setColumnClass(0, Boolean.class, false);
		miniTable.setColumnClass(1, String.class, true); 		// 2-No Order
		miniTable.setColumnClass(2, String.class, true); 		// 3-No Invoice
		miniTable.setColumnClass(3, Timestamp.class, true); 	// 4-Tgl Invoice
		miniTable.setColumnClass(4, String.class, true); 		// 5-Pelanggan
		miniTable.setColumnClass(5, BigDecimal.class, true); 	// 6-Jumlah
		miniTable.setColumnClass(6, BigDecimal.class, true);	// 7-DP
		miniTable.setColumnClass(7, BigDecimal.class, true);    // 8-jml. hutang
		miniTable.setColumnClass(8, BigDecimal.class, false);   // 9-jml. hutang

		miniTable.autoSize();

	}

	protected Vector<String> getOISColumnNamesHutang() {
			
		// Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(ctx, "Select"));
		columnNames.add("No Order");
		columnNames.add("No Invoice");
		columnNames.add("Tgl Invoice");
		columnNames.add("Pelanggan");
		columnNames.add("Jumlah Tagihan");
		columnNames.add("DP");
		columnNames.add("Jumlah Hutang");
		columnNames.add("Jumlah Bayar");

		return columnNames;
		
	}
	
}
