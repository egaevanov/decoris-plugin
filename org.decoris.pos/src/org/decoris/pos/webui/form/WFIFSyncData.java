package org.decoris.pos.webui.form;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.DialogEvents;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.window.FDialog;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.decoris.model.X_C_Decoris_PreOrderDtl;
import org.decoris.webservice.model.FIF_GParam;
import org.decoris.webservice.model.FIF_GVariable;
import org.decoris.webservice.model.FIF_ModelCity;
import org.decoris.webservice.model.FIF_ModelEducation;
import org.decoris.webservice.model.FIF_ModelGender;
import org.decoris.webservice.model.FIF_ModelHouseStatus;
import org.decoris.webservice.model.FIF_ModelInputSource;
import org.decoris.webservice.model.FIF_ModelKecamatan;
import org.decoris.webservice.model.FIF_ModelKelurahan;
import org.decoris.webservice.model.FIF_ModelMaritalStatus;
import org.decoris.webservice.model.FIF_ModelObjectCode;
import org.decoris.webservice.model.FIF_ModelObjectGroup;
import org.decoris.webservice.model.FIF_ModelOccupations;
import org.decoris.webservice.model.FIF_ModelOffice;
import org.decoris.webservice.model.FIF_ModelProv;
import org.decoris.webservice.model.FIF_ModelSupplier;
import org.decoris.webservice.model.FIF_ModelTokenAccess;
import org.decoris.webservice.model.FIF_ModelZIP;
import org.decoris.webservice.model.X_M_Fifapps_City;
import org.decoris.webservice.model.X_M_Fifapps_Education;
import org.decoris.webservice.model.X_M_Fifapps_Gender;
import org.decoris.webservice.model.X_M_Fifapps_Housestatus;
import org.decoris.webservice.model.X_M_Fifapps_Inputsource;
import org.decoris.webservice.model.X_M_Fifapps_Kecamatan;
import org.decoris.webservice.model.X_M_Fifapps_Kelurahan;
import org.decoris.webservice.model.X_M_Fifapps_Maritalstatus;
import org.decoris.webservice.model.X_M_Fifapps_Objcodes;
import org.decoris.webservice.model.X_M_Fifapps_Objgroups;
import org.decoris.webservice.model.X_M_Fifapps_Occupations;
import org.decoris.webservice.model.X_M_Fifapps_Offices;
import org.decoris.webservice.model.X_M_Fifapps_Prov;
import org.decoris.webservice.model.X_M_Fifapps_Supplier;
import org.decoris.webservice.model.X_M_Fifapps_Zipcode;
import org.decoris.webservice.model.X_m_fifapps_ws_tmp;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.North;
import org.zkoss.zul.Separator;
import org.zkoss.zul.South;
import org.zkoss.zul.Vbox;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author Tegar N
 *
 */

public class WFIFSyncData implements IFormController,
EventListener<Event>, WTableModelListener, ValueChangeListener{

	public CLogger log = CLogger.getCLogger(WFIFSyncData.class);

	
	// CustomForm
	private CustomForm formPresales = new CustomForm();
	
	// BorderLayout
	private Borderlayout mainLayout = new Borderlayout();
	private Borderlayout infoLayout = new Borderlayout();
	private Borderlayout presalesLayout = new Borderlayout();

	private Panel parameterPanel = new Panel();
	private Panel preSalesPanel = new Panel();

	// Grid
	private Grid parameterGrid = GridFactory.newGridLayout();

	private Button SyncCity = new Button();
	private Button SyncEducation = new Button();
	private Button SyncGender = new Button();
	private Button SyncHouseStatus = new Button();
	private Button SyncInputSource = new Button();
	private Button SyncKecamatan = new Button();
	private Button SyncKelurahan = new Button();
	private Button SyncMaritalStatus = new Button();
	private Button SyncObjCode = new Button();
	private Button SyncObjGroup = new Button();
	private Button SyncOccupation = new Button();
	private Button SyncOffice = new Button();
	private Button SyncSupplier = new Button();
	private Button SyncZIP = new Button();
	private Button SyncProv = new Button();
	
	private Properties ctx = Env.getCtx();
	private int windowNo = formPresales.getWindowNo();

	@Override
	public void valueChange(ValueChangeEvent evt) {}

	@Override
	public void tableChanged(WTableModelEvent ev) {	
	}

	@Override
	public void onEvent(Event e) throws Exception {	
	
		StringBuilder msg = new StringBuilder();
		int M_FIFApps_Source = 0;
		final String baseURL = GetBaseURL();
		
		
		if (e.getTarget().equals(SyncCity)){
			
	        URL url = new URL(baseURL+FIF_GParam.CityURL);
	        int countInsert = 0;
	        int countUpdated = 0;
            //int count = 0;
	        M_FIFApps_Source = FIF_GParam.M_FifApps_City_source;
	        cleanTable(M_FIFApps_Source);

	    	try {
	            Gson gson = new Gson();
	            FIF_ModelCity[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelCity[].class);
	            for (FIF_ModelCity data : datas) {
	   			 	//Insert to table X_m_fifapps_ws_tmp 
	            	 
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 tmp.setm_fifapps_param_01(data.city);
	    			 tmp.setm_fifapps_param_02(data.cityCode);
	    			 tmp.setm_fifapps_param_03(data.dati2);
	    			 tmp.setm_fifapps_param_04(data.digitZip);
	    			 tmp.setm_fifapps_param_05(data.provCode);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			 //count++;
	            
	            
	            }
	            
	            //Insert to table X_M_Fifapps_City
	            countInsert += createNewRecord("X_M_Fifapps_City");
            	
            	//update data on X_M_Fifapps_City if data changed On  FIFAPPS
            	countUpdated = updateData(FIF_GParam.M_FifApps_City_source);

            	msg.append("Data City Berhasil Terinput = " + countInsert + "\n");
            	msg.append("Data City Berhasil Terupdate = " + countUpdated + "\n");
            	msg.append("\n");
            	
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	            
	       }
	    	
		}else if (e.getTarget().equals(SyncEducation)){
			
	        URL url = new URL(baseURL+FIF_GParam.EducationURL);
	        int countInsert = 0;
	        int countUpdated = 0;
        	M_FIFApps_Source = FIF_GParam.M_FifApps_Education_source;
	        cleanTable(M_FIFApps_Source);

            //int count = 0 ;

	    	try {
	            Gson gson = new Gson();
	            FIF_ModelEducation[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelEducation[].class);
	            for (FIF_ModelEducation data : datas) {
	   			 	//Insert to table X_m_fifapps_ws_tmp 
	   			 
	            	X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	            	tmp.setClientOrg(0, 0);
	            	tmp.setm_fifapps_source(M_FIFApps_Source);
	            	tmp.setm_fifapps_param_01(data.aabCode);
	            	tmp.setm_fifapps_param_02(data.biStatus);
	            	tmp.setm_fifapps_param_03(data.biStatusRef);
	            	tmp.setm_fifapps_param_04(data.eduDescr);
	            	tmp.setm_fifapps_param_05(data.eduType);
	            	tmp.setm_fifapps_param_06(data.lippobankCode);
	            	tmp.setm_fifapps_param_07(data.visible);
	            	tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	            	tmp.setm_process_by(100);
	            	tmp.saveEx();
	            	//count++;
	            
	            }
	            
	            //Insert to table X_M_Fifapps_Education
	            countInsert += createNewRecord("X_M_Fifapps_Education");   
	            
            	countUpdated = updateData(FIF_GParam.M_FifApps_Education_source);

	           
	            //TODO
	            
            	msg.append("Data Education Berhasil Terinput = " + countInsert + "\n");
            	msg.append("Data Education Berhasil Terupdate = " + countUpdated + "\n");
            	msg.append("\n");
            	
   
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		
			
		}else if (e.getTarget().equals(SyncGender)){
	        URL url = new URL(baseURL+FIF_GParam.GenderURL);
	        int countInsert = 0;
	        int countUpdated = 0;
        	M_FIFApps_Source = FIF_GParam.M_FifApps_Gender_source;	 
	        cleanTable(M_FIFApps_Source);

           // int count = 0 ;
	        cleanTable(M_FIFApps_Source);
	        
	        try {
	            Gson gson = new Gson();
	            FIF_ModelGender[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelGender[].class);
	            for (FIF_ModelGender data : datas) {
	   			 	//Insert to table X_m_fifapps_ws_tmp 
	            	

	            	X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	            	tmp.setClientOrg(0, 0);
	            	tmp.setm_fifapps_source(M_FIFApps_Source);
	            	tmp.setm_fifapps_param_01(data.aabCode);
	            	tmp.setm_fifapps_param_02(data.description);
	            	tmp.setm_fifapps_param_03(data.genderId);
	            	tmp.setm_fifapps_param_04(data.visible);
	            	tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	            	tmp.setm_process_by(100);
	            	tmp.saveEx();
	            	//count++;
	            }	
	            
	            //Insert to table X_M_Fifapps_Education
	            countInsert += createNewRecord("X_M_Fifapps_Gender");         
            	
            	countUpdated = updateData(FIF_GParam.M_FifApps_Gender_source);
            	
	            msg.append("Data Gender Berhasil Terinput = " + countInsert + "\n");
            	msg.append("Data Gender Berhasil Terupdate = " + countUpdated + "\n");

            	msg.append("\n");
            	
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
	        
		}else if (e.getTarget().equals(SyncHouseStatus)){
	        URL url = new URL(baseURL+FIF_GParam.HouseStatusURL);
	        int countInsert = 0;
	        int countUpdated = 0;
	       // int count = 0;
       	 	M_FIFApps_Source = FIF_GParam.M_FifApps_HouseStatus_source;	
       	 	cleanTable(M_FIFApps_Source);
	        
	        try {
	            Gson gson = new Gson();
	            FIF_ModelHouseStatus[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelHouseStatus[].class);
	           
	            for (FIF_ModelHouseStatus data : datas) {	            	
	    			 
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 tmp.setm_fifapps_param_01(data.houseStat);
	    			 tmp.setm_fifapps_param_02(data.statusDescr);
	    			 tmp.setm_fifapps_param_03(data.visible);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			 //count++;
	            	
	            }
	            
	            countInsert += createNewRecord("X_M_Fifapps_Housestatus");
            	countUpdated = updateData(FIF_GParam.M_FifApps_HouseStatus_source);

           	 	cleanTable(M_FIFApps_Source);

            	msg.append("Data House Status Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data House Status Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");
            	
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}else if (e.getTarget().equals(SyncInputSource)){
	        URL url = new URL(baseURL+FIF_GParam.InputSourceURL);
	        int countInsert = 0 ;
	        int countUpdated = 0;
	        //int count = 0;
	        M_FIFApps_Source = FIF_GParam.M_FifApps_InputSource_source;
	        cleanTable(M_FIFApps_Source);
	        
	        try {
	            Gson gson = new Gson();
	            FIF_ModelInputSource[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelInputSource[].class);
	           
	            for (FIF_ModelInputSource data : datas) {
	    			 
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 tmp.setm_fifapps_param_01(data.branchTypeRight);
	    			 tmp.setm_fifapps_param_02(data.code);
	    			 tmp.setm_fifapps_param_03(data.depositKey);
	    			 tmp.setm_fifapps_param_04(data.description);
	    			 tmp.setm_fifapps_param_05(data.shortName);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			 //count++;
	            
	            }
	        
	            countInsert += createNewRecord("X_M_Fifapps_Inputsource"); 
            	countUpdated = updateData(FIF_GParam.M_FifApps_InputSource_source);
	            
            	msg.append("Data Input Source Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data Input Source Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");
            	
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}else if (e.getTarget().equals(SyncKecamatan)){
	        URL url = new URL(baseURL+FIF_GParam.KecamatanURL);
	        int countInsert = 0;
	        int countUpdated = 0;
	        //int count = 0;
	        M_FIFApps_Source = FIF_GParam.M_FifApps_Kecamatan_source;
	        cleanTable(M_FIFApps_Source);
	        try {
	            Gson gson = new Gson();
	            FIF_ModelKecamatan[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelKecamatan[].class);

	            for (FIF_ModelKecamatan data : datas) {

	   			 	X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	   			 	tmp.setClientOrg(0, 0);
	   			 	tmp.setm_fifapps_source(M_FIFApps_Source);
	   			 	tmp.setm_fifapps_param_01(data.cityCode);
	   			 	tmp.setm_fifapps_param_02(data.kecCode);
	   			 	tmp.setm_fifapps_param_03(data.kecamatan);
	   			 	tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	   			 	tmp.setm_process_by(100);
	   			 	tmp.saveEx();
	   			 	//count++;
	            
	            }
	            
	            countInsert += createNewRecord("X_M_Fifapps_Kecamatan");
            	countUpdated = updateData(FIF_GParam.M_FifApps_Kecamatan_source);

    	        cleanTable(M_FIFApps_Source);

            	msg.append("Data Kecamatan Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data Kecamatan Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");
            	
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}else if (e.getTarget().equals(SyncKelurahan)){
			// pop up confirmation branch
			
			try {

				Grid inputGrid = GridFactory.newGridLayout();
				Panel paraPanel = new Panel();
				Rows rows = null;
				Row row = null;

				final Textbox kecCode = new Textbox();
				final Textbox page = new Textbox();


				String title = "Korfirmasi Kelurahan";
				Label kecCodeLabel = new Label("kecCode");
				Label pageLabel = new Label("Page");


				final Window w = new Window();
				w.setTitle(title);

				Borderlayout mainbBorder = new Borderlayout();
				w.appendChild(mainbBorder);
				w.setWidth("350px");
				w.setHeight("150px");
				w.setBorder("normal");
				w.setPage(this.getForm().getPage());
				w.setClosable(true);
				w.setSizable(true);

				mainbBorder.setWidth("100%");
				mainbBorder.setHeight("100%");
				String grid = "border: 1px solid #C0C0C0; border-radius:5px; vertical-align:center;overflow:auto;";
				paraPanel.appendChild(inputGrid);

				South south = new South();
				south.setStyle(grid);
				mainbBorder.appendChild(south);
				south.appendChild(paraPanel);
				south.setSplittable(false);

				inputGrid.setWidth("100%");
				inputGrid.setStyle("Height:100%;align:left");
				//inputGrid.setHflex("min");

				rows = inputGrid.newRows();

				row = rows.newRow();
				row.appendCellChild(kecCodeLabel.rightAlign());
				row.appendCellChild(kecCode,2);
				kecCode.setHflex("true");
				
				
				row = rows.newRow();
				row.appendCellChild(pageLabel.rightAlign());
				row.appendCellChild(page,2);
				kecCode.setHflex("true");


				Vbox vbox = new Vbox();
				w.appendChild(vbox);
				vbox.appendChild(new Separator());
				final ConfirmPanel panel = new ConfirmPanel(true, false, false,false, false, false, false);
				vbox.appendChild(panel);
				panel.addActionListener(Events.ON_CLICK,new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								if (event.getTarget() == panel.getButton(ConfirmPanel.A_CANCEL)) {						
									w.dispose();
								} else if (event.getTarget() == panel.getButton(ConfirmPanel.A_OK)) {
									String code = "";
									String pag = "";
									
									code = kecCode.getValue();
									pag = page.getValue();
									
									if(code==null || code.isEmpty() || code ==""){
										FDialog.warn(windowNo, null, null, "KecCode Belum Di Isi", "Info");
										return;
										
									}else if(pag == null || pag.isEmpty() || pag==""){
										FDialog.warn(windowNo, null, null, "Page Belum Di Isi", "Info");
										return;
									}
									
									kelData(code, pag, baseURL);
									
									
								}

								w.onClose();

							}

						});

				w.addEventListener(DialogEvents.ON_WINDOW_CLOSE,new EventListener<Event>() {

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
			// end		
			
		}else if (e.getTarget().equals(SyncMaritalStatus)){
	        URL url = new URL(baseURL+FIF_GParam.MaritalStatusURL);
	        int countInsert = 0;
	        int countUpdated = 0;
	       // int count = 0;
        	M_FIFApps_Source = FIF_GParam.M_FifApps_MaritalStatus_source;
        	cleanTable(M_FIFApps_Source);
	        
	        try {
	            Gson gson = new Gson();
	            FIF_ModelMaritalStatus[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelMaritalStatus[].class);
	           
	            for (FIF_ModelMaritalStatus data : datas) {
	   			 
	            	X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	            	tmp.setClientOrg(0, 0);
	            	tmp.setm_fifapps_source(M_FIFApps_Source);
	            	tmp.setm_fifapps_param_01(data.aabCode);
	            	tmp.setm_fifapps_param_02(data.maritalDesc);
	            	tmp.setm_fifapps_param_03(data.maritalStat);
	            	tmp.setm_fifapps_param_04(data.visible);
	            	tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	            	tmp.setm_process_by(100);
	            	tmp.saveEx();
	   			 	//count++;
	            
	            }
	            countInsert += createNewRecord("X_M_Fifapps_Maritalstatus");
            	countUpdated = updateData(FIF_GParam.M_FifApps_MaritalStatus_source);

            	cleanTable(M_FIFApps_Source);
            	
            	msg.append("Data Martial Status Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data Martial Status Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");

	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}else if (e.getTarget().equals(SyncObjCode)){
	        
			try {

				Grid inputGrid = GridFactory.newGridLayout();
				Panel paraPanel = new Panel();
				Rows rows = null;
				Row row = null;

				final Textbox objGrp= new Textbox();


				String title = "Korfirmasi Object Code";
				Label objGrpLabel = new Label("Object Group");


				final Window w = new Window();
				w.setTitle(title);

				Borderlayout mainbBorder = new Borderlayout();
				w.appendChild(mainbBorder);
				w.setWidth("350px");
				w.setHeight("150px");
				w.setBorder("normal");
				w.setPage(this.getForm().getPage());
				w.setClosable(true);
				w.setSizable(true);

				mainbBorder.setWidth("100%");
				mainbBorder.setHeight("100%");
				String grid = "border: 1px solid #C0C0C0; border-radius:5px; vertical-align:center;overflow:auto;";
				paraPanel.appendChild(inputGrid);

				South south = new South();
				south.setStyle(grid);
				mainbBorder.appendChild(south);
				south.appendChild(paraPanel);
				south.setSplittable(false);

				inputGrid.setWidth("100%");
				inputGrid.setStyle("Height:100%;align:left");

				rows = inputGrid.newRows();

				row = rows.newRow();
				row.appendCellChild(objGrpLabel.rightAlign());
				row.appendCellChild(objGrp,2);
				objGrp.setHflex("true");


				Vbox vbox = new Vbox();
				w.appendChild(vbox);
				vbox.appendChild(new Separator());
				final ConfirmPanel panel = new ConfirmPanel(true, false, false,false, false, false, false);
				vbox.appendChild(panel);
				panel.addActionListener(Events.ON_CLICK,new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								if (event.getTarget() == panel.getButton(ConfirmPanel.A_CANCEL)) {						
									w.dispose();
								} else if (event.getTarget() == panel.getButton(ConfirmPanel.A_OK)) {
									String code = "";
									code = objGrp.getValue();
									
									if(code==null || code.isEmpty() || code ==""){
										FDialog.warn(windowNo, null, null, "Object Group Belum Di Isi", "Info");
										return;
										
									}
									
									objCodeData(code,baseURL.toString());									
									
								}

								w.onClose();

							}

						});

				w.addEventListener(DialogEvents.ON_WINDOW_CLOSE,new EventListener<Event>() {

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
			// end	
			
		}else if (e.getTarget().equals(SyncObjGroup)){
			String parameter = "MPF";
	        URL url = new URL(baseURL+FIF_GParam.ObjGroupURL+"?bussUnit="+parameter);
	        int countInsert = 0;
	        int countUpdated = 0;
	        //int count = 0;
	        M_FIFApps_Source = FIF_GParam.M_FifApps_ObjectGroup_source;	
	      //  cleanTable(M_FIFApps_Source);
	        
	        try {
	            Gson gson = new Gson();
	            FIF_ModelObjectGroup[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelObjectGroup[].class);
	           
	            for (FIF_ModelObjectGroup data : datas) {
	     			
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 tmp.setm_fifapps_param_01(data.collType);
	    			 tmp.setm_fifapps_param_02(data.objDescr);
	    			 tmp.setm_fifapps_param_03(data.objGrp);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			// count++;
	            
	            
	            }
	            countInsert += createNewRecord("X_M_Fifapps_Objgroups");
            	countUpdated = updateData(FIF_GParam.M_FifApps_ObjectGroup_source);

    	        cleanTable(M_FIFApps_Source);

            	msg.append("Data Object Group Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data Object Group Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");
            	
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}else if (e.getTarget().equals(SyncOccupation)){
	        URL url = new URL(baseURL+FIF_GParam.OccupationURL);
	        int countInsert = 0;
	        int countUpdated = 0;
	        // int count = 0;
	        M_FIFApps_Source = FIF_GParam.M_FifApps_Occupation_source;
	        cleanTable(M_FIFApps_Source);
	        
	        try {
	            Gson gson = new Gson();
	            FIF_ModelOccupations[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelOccupations[].class);
	           
	            for (FIF_ModelOccupations data : datas) {	            
	    			 
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 tmp.setm_fifapps_param_01(data.aabCode);
	    			 tmp.setm_fifapps_param_02(data.biBidangUsaha);
	    			 tmp.setm_fifapps_param_03(data.biBidangUsahaRef);
	    			 tmp.setm_fifapps_param_04(data.biGolDebitur);
	    			 tmp.setm_fifapps_param_05(data.biGolDebiturRef);
	    			 tmp.setm_fifapps_param_06(data.biPekerjaan);
	    			 tmp.setm_fifapps_param_07(data.biPekerjaanRef);
	    			 tmp.setm_fifapps_param_08(data.description);
	    			 tmp.setm_fifapps_param_09(data.kodeBca);
	    			 tmp.setm_fifapps_param_10(data.mandatory);
	    			 tmp.setm_fifapps_param_11(data.mbiProfesi);
	    			 tmp.setm_fifapps_param_12(data.ocptCode);
	    			 tmp.setm_fifapps_param_13(data.ocptCodeBca);
	    			 tmp.setm_fifapps_param_14(data.ocptRating);
	    			 tmp.setm_fifapps_param_15(data.ocptType);
	    			 tmp.setm_fifapps_param_16(data.sumberPenghasilan);
	    			 tmp.setm_fifapps_param_17(data.visible);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			// count++;
	            
	            }
	            countInsert += createNewRecord("X_M_Fifapps_Occupations");
            	countUpdated = updateData(FIF_GParam.M_FifApps_Occupation_source);

    	        cleanTable(M_FIFApps_Source);

            	
            	msg.append("Data Occupation Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data Occupation Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");

	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}else if (e.getTarget().equals(SyncOffice)){
	        URL url = new URL(baseURL+FIF_GParam.OfficeURL);
	        int countInsert = 0;
	        int countUpdated = 0;
	       // int count = 0;
        	M_FIFApps_Source = FIF_GParam.M_FifApps_Offices_source;
        	cleanTable(M_FIFApps_Source);
	        
	        try {
	            Gson gson = new Gson();
	            FIF_ModelOffice[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelOffice[].class);
	           
	            for (FIF_ModelOffice data : datas) {
	            	
	            	
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 if (data.accsPriceDevPct != null){
	    				 tmp.setm_fifapps_param_01(data.accsPriceDevPct.toString());
	    			 }
	    			 if(data.address1 != null){
		    			 tmp.setm_fifapps_param_02(data.address1);
	    			 }
	    			 if(data.address2 != null){
		    			 tmp.setm_fifapps_param_03(data.address2);		 
	    			 }
	    			 if(data.address3 != null){
		    			 tmp.setm_fifapps_param_04(data.address3);
	    			 }
	    			 if(data.bpkbPrcTime != null){
		    			 tmp.setm_fifapps_param_05(data.bpkbPrcTime.toString());
	    			 }
	    			 if(data.branchCode != null){
		    			 tmp.setm_fifapps_param_06(data.branchCode);
	    			 }
	    			 if(data.city != null){
		    			 tmp.setm_fifapps_param_07(data.city);
	    			 }
	    			 if(data.collIncentive != null){
	    				 tmp.setm_fifapps_param_08(data.collIncentive.toString());
	    			 }
	    			 if(data.diffTime != null){
		    			 tmp.setm_fifapps_param_09(data.diffTime.toString());
	    			 }
	    			 if(data.location != null){
		    			 tmp.setm_fifapps_param_10(data.location);
	    			 }
	    			 if(data.nameFull != null){
		    			 tmp.setm_fifapps_param_11(data.nameFull);
	    			 }
	    			 if(data.nameShort != null){
		    			 tmp.setm_fifapps_param_12(data.nameShort.toString());
	    			 }
	    			 if(data.officeCategory != null){
		    			 tmp.setm_fifapps_param_13(data.officeCategory);
	    			 }
	    			 if(data.officeClasscode != null){
		    			 tmp.setm_fifapps_param_14(data.officeClasscode);
	    			 }
	    			 if(data.officeCode != null){
		    			 tmp.setm_fifapps_param_15(data.officeCode);
	    			 }
	    			 if(data.officeCodeRep != null){
		    			 tmp.setm_fifapps_param_16(data.officeCodeRep);
	    			 }
	    			 if(data.officeType != null){
		    			 tmp.setm_fifapps_param_17(data.officeType);
	    			 }
	    			 if(data.phone1 != null){
		    			 tmp.setm_fifapps_param_18(data.phone1);
	    			 }
	    			 if(data.phone2 != null){
		    			 tmp.setm_fifapps_param_19(data.phone2);
	    			 }
	    			 if(data.phone3 != null){
		    			 tmp.setm_fifapps_param_20(data.phone3);
	    			 }
	    			 if(data.picAddr1 != null){
		    			 tmp.setm_fifapps_param_21(data.picAddr1);
	    			 }
	    			 if(data.picAddr2 != null){
		    			 tmp.setm_fifapps_param_22(data.picAddr2);
	    			 }
	    			 if(data.picAddr3 != null){
		    			 tmp.setm_fifapps_param_23(data.picAddr3);
	    			 }
	    			 if(data.picCity != null){
		    			 tmp.setm_fifapps_param_24(data.picCity);
	    			 }
	    			 if(data.picFirstName != null){
		    			 tmp.setm_fifapps_param_25(data.picFirstName);
	    			 }
	    			 if(data.picLastName != null){
		    			 tmp.setm_fifapps_param_26(data.picLastName);
	    			 }
	    			 if(data.picPhone1 != null){
		    			 tmp.setm_fifapps_param_27(data.picPhone1);
	    			 }
	    			 if(data.picPhone2 != null){
		    			 tmp.setm_fifapps_param_28(data.picPhone2);
	    			 }
	    			 if(data.picPhone3 != null){
		    			 tmp.setm_fifapps_param_29(data.picPhone3);
	    			 }
	    			 if(data.positionCode != null){
		    			 tmp.setm_fifapps_param_30(data.positionCode);
	    			 }
	    			 if(data.processGroup != null){
		    			 tmp.setm_fifapps_param_31(data.processGroup.toString());
	    			 }
	    			 if(data.profitDealerByGrade != null){
		    			 tmp.setm_fifapps_param_32(data.profitDealerByGrade);
	    			 }
	    			 if(data.profitDealerFlatAmt != null){
	    				 tmp.setm_fifapps_param_33(data.profitDealerFlatAmt.toString());
	    			 }
	    			 if(data.profitDealerPct != null){
	    				 tmp.setm_fifapps_param_34(data.profitDealerPct.toString());
	    			 }
	    			 if(data.regionArCode != null){
		    			 tmp.setm_fifapps_param_35(data.regionArCode);
	    			 }
	    			 if(data.regionalId != null){
		    			 tmp.setm_fifapps_param_36(data.regionalId);
	    			 }
	    			 if(data.regionalIdInsc != null){
		    			 tmp.setm_fifapps_param_37(data.regionalIdInsc);
	    			 }
	    			 if(data.statusFlag != null){
		    			 tmp.setm_fifapps_param_38(data.statusFlag);
	    			 }
	    			 if(data.validPoDays != null){
		    			 tmp.setm_fifapps_param_39(data.validPoDays.toString());
	    			 }
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			// count++;
	            
	            
	            }
	            countInsert += createNewRecord("X_M_Fifapps_Offices"); 
            	countUpdated = updateData(FIF_GParam.M_FifApps_Offices_source);

            	cleanTable(M_FIFApps_Source);

            	msg.append("Data Offices Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data Offices Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");

	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}else if (e.getTarget().equals(SyncSupplier)){
			
			// pop up confirmation branch
				try {

					Grid inputGrid = GridFactory.newGridLayout();
					Panel paraPanel = new Panel();
					Rows rows = null;
					Row row = null;

					final Textbox branch = new Textbox();
					final Textbox type = new Textbox();
					final Textbox status = new Textbox();
					final Textbox page = new Textbox();


					String title = "Supplier Branch";
					Label branchLabel = new Label("Branch");
					Label typeLabel = new Label("Type");
					Label statusLabel = new Label("Status");
					Label pageLabel = new Label("Page");


					final Window w = new Window();
					w.setTitle(title);

					Borderlayout mainbBorder = new Borderlayout();
					w.appendChild(mainbBorder);
					w.setWidth("350px");
					w.setHeight("205px");
					w.setBorder("normal");
					w.setPage(this.getForm().getPage());
					w.setClosable(true);
					w.setSizable(true);

					mainbBorder.setWidth("100%");
					mainbBorder.setHeight("100%");
					String grid = "border: 1px solid #C0C0C0; border-radius:5px; vertical-align:center;overflow:auto;";
					paraPanel.appendChild(inputGrid);

					South south = new South();
					south.setStyle(grid);
					mainbBorder.appendChild(south);
					south.appendChild(paraPanel);
					south.setSplittable(false);

					inputGrid.setWidth("100%");
					inputGrid.setStyle("Height:100%;align:left");

					rows = inputGrid.newRows();

					row = rows.newRow();
					row.appendCellChild(branchLabel.rightAlign());
					row.appendCellChild(branch,2);
					branch.setHflex("true");
					
					row = rows.newRow();
					row.appendCellChild(typeLabel.rightAlign());
					row.appendCellChild(type,2);
					type.setHflex("true");
					
					row = rows.newRow();
					row.appendCellChild(statusLabel.rightAlign());
					row.appendCellChild(status,2);
					status.setHflex("true");
					
					row = rows.newRow();
					row.appendCellChild(pageLabel.rightAlign());
					row.appendCellChild(page,2);
					page.setHflex("true");

					Vbox vbox = new Vbox();
					w.appendChild(vbox);
					vbox.appendChild(new Separator());
					final ConfirmPanel panel = new ConfirmPanel(true, false, false,
							false, false, false, false);
					vbox.appendChild(panel);
					panel.addActionListener(Events.ON_CLICK,
							new EventListener<Event>() {

								@Override
								public void onEvent(Event event) throws Exception {
									if (event.getTarget() == panel.getButton(ConfirmPanel.A_CANCEL)) {						
										w.dispose();
									} else if (event.getTarget() == panel.getButton(ConfirmPanel.A_OK)) {
										String p_code = branch.getValue();
										String p_type = type.getValue();
										String p_status = status.getValue();
										String p_page = page.getValue();

										
										//21400
										StringBuilder SQLCleanTable = new StringBuilder();
										SQLCleanTable.append(" DELETE FROM m_fifapps_ws_tmp ");
										SQLCleanTable.append(" WHERE M_Fifapps_Source = "+ FIF_GParam.M_FifApps_Supplier_source);
										SQLCleanTable.append(" AND m_fifapps_param_16 = '"+p_code+"'");

										DB.executeUpdate(SQLCleanTable.toString(), null);
										
										if(p_code==null|| p_code.isEmpty() || p_code ==""){
											FDialog.warn(windowNo, null, null, "BranchID Belum Di Isi", "Info");
											return;
											
										}else if(p_type == null|| p_type.isEmpty() || p_type ==""){
											FDialog.warn(windowNo, null, null, "Type Belum Di Isi", "Info");
											return;
										}else if(p_status == null || p_status.isEmpty() || p_status ==""){
											FDialog.warn(windowNo, null, null, "Status Belum Di Isi", "Info");
											return;
										}else if(p_page == null || p_page.isEmpty() || p_page ==""){
											FDialog.warn(windowNo, null, null, "Page Belum Di Isi", "Info");
											return;
										}
										
										suppData(p_code, p_type, p_status, p_page, baseURL);
										
										
									}

									w.onClose();

								}

							});

					w.addEventListener(DialogEvents.ON_WINDOW_CLOSE,new EventListener<Event>() {

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
				// end		
			       
		}else if (e.getTarget().equals(SyncZIP)){
			try {

				Grid inputGrid = GridFactory.newGridLayout();
				Panel paraPanel = new Panel();
				Rows rows = null;
				Row row = null;

				final Textbox cityCode = new Textbox();
				final Textbox page = new Textbox();


				String title = "Korfirmasi ZIP";
				Label cityCodeLabel = new Label("City Code");
				Label pageLabel = new Label("Page");


				final Window w = new Window();
				w.setTitle(title);

				Borderlayout mainbBorder = new Borderlayout();
				w.appendChild(mainbBorder);
				w.setWidth("350px");
				w.setHeight("150px");
				w.setBorder("normal");
				w.setPage(this.getForm().getPage());
				w.setClosable(true);
				w.setSizable(true);

				mainbBorder.setWidth("100%");
				mainbBorder.setHeight("100%");
				String grid = "border: 1px solid #C0C0C0; border-radius:5px; vertical-align:center;overflow:auto;";
				paraPanel.appendChild(inputGrid);

				South south = new South();
				south.setStyle(grid);
				mainbBorder.appendChild(south);
				south.appendChild(paraPanel);
				south.setSplittable(false);

				inputGrid.setWidth("100%");
				inputGrid.setStyle("Height:100%;align:left");

				rows = inputGrid.newRows();

				row = rows.newRow();
				row.appendCellChild(cityCodeLabel.rightAlign());
				row.appendCellChild(cityCode,2);
				cityCode.setHflex("true");
				
				
				row = rows.newRow();
				row.appendCellChild(pageLabel.rightAlign());
				row.appendCellChild(page,2);
				page.setHflex("true");


				Vbox vbox = new Vbox();
				w.appendChild(vbox);
				vbox.appendChild(new Separator());
				final ConfirmPanel panel = new ConfirmPanel(true, false, false,false, false, false, false);
				vbox.appendChild(panel);
				panel.addActionListener(Events.ON_CLICK,new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								if (event.getTarget() == panel.getButton(ConfirmPanel.A_CANCEL)) {						
									w.dispose();
								} else if (event.getTarget() == panel.getButton(ConfirmPanel.A_OK)) {
									String code = "";
									String pag = "";
									code = cityCode.getValue();
									pag = page.getValue();
									
									if(code==null || code.isEmpty() || code ==""){
										FDialog.warn(windowNo, null, null, "CityCode Belum Di Isi", "Info");
										return;
										
									}else if(pag == null || pag.isEmpty() || pag ==""){
										FDialog.warn(windowNo, null, null, "Page Belum Di Isi", "Info");
										return;
									}
									
									zipData(code, pag, baseURL.toString());									
									
								}

								w.onClose();

							}

						});

				w.addEventListener(DialogEvents.ON_WINDOW_CLOSE,new EventListener<Event>() {

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
			// end		
		}else if (e.getTarget().equals(SyncProv)){
	        URL url = new URL(baseURL+FIF_GParam.ProvURL);
	        int countInsert = 0;
	        int countUpdated = 0;
	       // int count = 0 ;
	        M_FIFApps_Source = FIF_GParam.M_FifApps_Prov_source;
       	 	cleanTable(M_FIFApps_Source);

	        try {
	            Gson gson = new Gson();
	            FIF_ModelProv[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelProv[].class);
	            for (FIF_ModelProv data : datas) {

	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 tmp.setm_fifapps_param_01(data.provinsi);
	    			 tmp.setm_fifapps_param_02(data.prov_code);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			// count++;
	            	
	            }
	        
	            //Insert to table X_M_Fifapps_Education
	            countInsert += createNewRecord("X_M_Fifapps_Prov");
            	countUpdated = updateData(FIF_GParam.M_FifApps_Prov_source);

            	
	            msg.append("Data Provinsi Berhasil Terinput = " + countInsert + "\n");
              	msg.append("Data Provinsi Berhasil Terupdate = " + countUpdated + "\n");
              	msg.append("\n");
              	
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		}

		msgInfo(null, msg.toString());
		
		
	}

	@Override
	public ADForm getForm() {
		
		return this.formPresales;
	}
	
	
	public WFIFSyncData() {
		dyInit();
		init();
		
	}
	
	private void dyInit(){
	
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
		row = rows.newRow();
		row.appendCellChild(SyncCity, 2);
		SyncCity.setHflex("true");
		SyncCity.setLabel("Sync Data City");
		SyncCity.addActionListener(this);
		
		row.appendCellChild(SyncEducation, 2);
		SyncEducation.setHflex("true");
		SyncEducation.setLabel("Sync Data Education");
		SyncEducation.addActionListener(this);
		
		row.appendCellChild(SyncGender	, 2);
		SyncGender.setHflex("true");
		SyncGender.setLabel("Sync Data Gender");
		SyncGender.addActionListener(this);
		
		row = rows.newRow();
		row.appendCellChild(SyncHouseStatus, 2);
		SyncHouseStatus.setHflex("true");
		SyncHouseStatus.setLabel("Sync Data Houses Status");
		SyncHouseStatus.addActionListener(this);
		
		row.appendCellChild(SyncInputSource, 2);
		SyncInputSource.setHflex("true");
		SyncInputSource.setLabel("Sync Data Input Source");
		SyncInputSource.addActionListener(this);
		
		row.appendCellChild(SyncKecamatan, 2);
		SyncKecamatan.setHflex("true");
		SyncKecamatan.setLabel("Sync Data Kecamatan");
		SyncKecamatan.addActionListener(this);
		
		
		
		row = rows.newRow();
		row.appendCellChild(SyncKelurahan, 2);
		SyncKelurahan.setHflex("true");
		SyncKelurahan.setLabel("Sync Data Kelurahan");
		SyncKelurahan.addActionListener(this);
		
		row.appendCellChild(SyncMaritalStatus, 2);
		SyncMaritalStatus.setHflex("true");
		SyncMaritalStatus.setLabel("Sync Data Marital Status");
		SyncMaritalStatus.addActionListener(this);
		
		row.appendCellChild(SyncObjCode, 2);
		SyncObjCode.setHflex("true");
		SyncObjCode.setLabel("Sync Data Object Code");
		SyncObjCode.addActionListener(this);
		
		
		row = rows.newRow();
		row.appendCellChild(SyncObjGroup, 2);
		SyncObjGroup.setHflex("true");
		SyncObjGroup.setLabel("Sync Data Object Group");
		SyncObjGroup.addActionListener(this);
		
		row.appendCellChild(SyncOccupation, 2);
		SyncOccupation.setHflex("true");
		SyncOccupation.setLabel("Sync Data Occupation");
		SyncOccupation.addActionListener(this);
		
		row.appendCellChild(SyncOffice, 2);
		SyncOffice.setHflex("true");
		SyncOffice.setLabel("Sync Data Office");
		SyncOffice.addActionListener(this);
		
		row = rows.newRow();
		row.appendCellChild(SyncSupplier, 2);
		SyncSupplier.setHflex("true");
		SyncSupplier.setLabel("Sync Data Supplier");
		SyncSupplier.addActionListener(this);
		
		row.appendCellChild(SyncZIP, 2);
		SyncZIP.setHflex("true");
		SyncZIP.setLabel("Sync Data ZIP Code");
		SyncZIP.addActionListener(this);
		
		row.appendCellChild(SyncProv, 2);
		SyncProv.setHflex("true");
		SyncProv.setLabel("Sync Data Province");
		SyncProv.addActionListener(this);
		
		Center center = new Center();
		presalesLayout.appendChild(center);
		presalesLayout.setWidth("100%");
		presalesLayout.setHeight("100%");
		center.setStyle(grid);

		center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(infoLayout);
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
	
	 public static String getToken() {
	       
		    final String sparator = "&";
		    	   
		       StringBuilder data = new StringBuilder();
		       data.append(FIF_GVariable.Client_ID).append("=").append(FIF_GParam.Client_ID).append(sparator);
		       data.append(FIF_GVariable.Client_Secret).append("=").append(FIF_GParam.Client_Secret).append(sparator);
		       data.append(FIF_GVariable.Username).append("=").append(FIF_GParam.Username).append(sparator);
		       data.append(FIF_GVariable.Grant_Type).append("=").append(FIF_GParam.Grant_Type).append(sparator);
		       data.append(FIF_GVariable.Password).append("=").append(FIF_GParam.Password);
		       
		       try {
		            
		            // Send the request
		            URL url = new URL(FIF_GParam.TokenURL);
		            URLConnection conn = url.openConnection();
		            conn.setDoOutput(true);
		            conn.setConnectTimeout(15 * 1000);
		            conn.setReadTimeout(20 * 1000);
		            StringBuffer answer;
		            BufferedReader reader;
		            
		           //write parameters
		           try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
		               //write parameters
		        	   writer.write(data.toString());
		               writer.flush();
		               
		               // Get the response
		               answer = new StringBuffer();
		               reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		               String line;
		               while ((line = reader.readLine()) != null) {
		                   answer.append(line);
		               }
		           }
		            reader.close();
		            
		            Gson g = new Gson();
		            FIF_ModelTokenAccess result = g.fromJson(answer.toString(), FIF_ModelTokenAccess.class);
		            return result.access_token;
		            
		        } catch (MalformedURLException ex) {
		            return ex.getMessage();
		        } catch (IOException ex) {
		            return ex.getMessage();
		        }
		    }
		 
	 private String syncDataRun(String token,URL url){
		 String msg = null;
	        try {

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "bearer "+token);
	            conn.setConnectTimeout(15 * 1000);
	            conn.setReadTimeout(20 * 1000);

				if (conn.getResponseCode() != 200) {
		            msg = "Tidak ada data Sync, Silakan cek koneksi internet Atau Tidak Terhubung Ke Server";
		            msgInfo(msg, null);
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;
				
				while ((output = br.readLine()) != null) {
		             return output;
				}

				conn.disconnect();
				return null;

		  }catch (MalformedURLException e) {
	          msg = "Tidak ada data Sync, Silakan cek koneksi internet Atau Tidak Terhubung Ke Server";
	          msgInfo(msg, null);
	          return e.getMessage();
		  }catch (IOException e) {
	          msg = "Tidak ada data Sync, Silakan cek koneksi internet Atau Tidak Terhubung Ke Server";
	          msgInfo(msg, null);
			  return e.getMessage();
		  }
	    
	    }
	     
	 public int createNewRecord(String X_Class){							
		 int count = 0; 
		 
		 if(X_Class.equals("X_M_Fifapps_City")){
			 
			 //Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_city 
			 StringBuilder SQLCity = new StringBuilder();
			 SQLCity.append("SELECT a.m_fifapps_param_01,");
			 SQLCity.append(" a.m_fifapps_param_02,");
			 SQLCity.append(" a.m_fifapps_param_03,");
			 SQLCity.append(" a.m_fifapps_param_04,");
			 SQLCity.append(" a.m_fifapps_param_05 ");
			 SQLCity.append(" FROM m_fifapps_ws_tmp a");
			 SQLCity.append(" WHERE NOT EXISTS(");
			 SQLCity.append(" 	SELECT 1 FROM m_fifapps_city b");
			 SQLCity.append(" 	WHERE a.m_fifapps_param_02 = b.citycode )");
			// SQLCity.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLCity.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLCity.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_City_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_City
						X_M_Fifapps_City city = new X_M_Fifapps_City(ctx, 0, null);
			            city.setClientOrg(0,0);
			            city.setIsActive(true);
			            city.setCity(rs.getString(1));
			            city.setcitycode(rs.getString(2));
			            city.setdati2(rs.getString(3));
			            city.setdigitzip(rs.getString(4));
			            city.setprovcode(rs.getString(5));
			            city.saveEx();
			            count++;
						////System.out.println("Inserted Data : "+count);

			             
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"' ");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_City_source );

			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLCity.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}

		 }
		 else if(X_Class.equals("X_M_Fifapps_Education")){
			 
			 
			 //Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_city 
			 StringBuilder SQLEducation = new StringBuilder();
			 SQLEducation.append("SELECT a.m_fifapps_param_01,");
			 SQLEducation.append(" a.m_fifapps_param_02,");
			 SQLEducation.append(" a.m_fifapps_param_03,");
			 SQLEducation.append(" a.m_fifapps_param_04, ");
			 SQLEducation.append(" a.m_fifapps_param_05, ");
			 SQLEducation.append(" a.m_fifapps_param_06, ");
			 SQLEducation.append(" a.m_fifapps_param_07 ");
			 SQLEducation.append(" FROM m_fifapps_ws_tmp a");
			 SQLEducation.append(" WHERE NOT EXISTS(");
			 SQLEducation.append(" 		SELECT 1 FROM m_fifapps_education b");
			 SQLEducation.append(" 		WHERE a.m_fifapps_param_05 = b.edutype )");
			 //SQLEducation.append(" AND a.m_fifapps_status_process = 'N'");
			 SQLEducation.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLEducation.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_Education_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_Education
						 X_M_Fifapps_Education Education = new X_M_Fifapps_Education(ctx, 0, null);
						 Education.setClientOrg(0,0);
						 Education.setIsActive(true);
						 Education.setaabcode(rs.getString(1));
						 Education.setbistatus(rs.getString(2));
						 Education.setbistatusref(rs.getString(3));
						 Education.setedudescr(rs.getString(4));
						 Education.setedutype(rs.getString(5));
						 Education.setlippobankcode(rs.getString(6));
						 Education.setvisible(rs.getString(7));
						 Education.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_04 = '" + rs.getString(4)+"'");
			            Update.append(" AND m_fifapps_param_05 = '" + rs.getString(5)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Education_source);

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLEducation.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 
			 
		 }
		 else if(X_Class.equals("X_M_Fifapps_Gender")){
			 
			 //Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_gender
			 StringBuilder SQLGender = new StringBuilder();
			 SQLGender.append("SELECT a.m_fifapps_param_01,");
			 SQLGender.append(" a.m_fifapps_param_02,");
			 SQLGender.append(" a.m_fifapps_param_03,");
			 SQLGender.append(" a.m_fifapps_param_04 ");
			 SQLGender.append(" FROM m_fifapps_ws_tmp a");
			 SQLGender.append(" WHERE a.m_fifapps_source = ? ");
			// SQLGender.append(" AND a.m_fifapps_status_process = 'N'");
			 SQLGender.append(" AND NOT EXISTS(");
			 SQLGender.append(" 	SELECT 1 FROM m_fifapps_gender b");
			 SQLGender.append(" 	WHERE a.m_fifapps_param_03 = b.genderid )");

			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLGender.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_Gender_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_Gender
						 X_M_Fifapps_Gender Gender = new X_M_Fifapps_Gender(ctx, 0, null);
						 Gender.setClientOrg(0,0);
						 Gender.setIsActive(true);
						 Gender.setaabcode(rs.getString(1));
						 Gender.setDescription(rs.getString(2));
						 Gender.setgenderid(rs.getString(3));
						 Gender.setvisible(rs.getString(4));
						 Gender.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_03 = '" + rs.getString(3)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Gender_source );

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLGender.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 			 

			 } else if(X_Class.equals("X_M_Fifapps_Housestatus")){
			 
			 
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_housestatus
			 StringBuilder SQLHS = new StringBuilder();
			 SQLHS.append("SELECT a.m_fifapps_param_01,");
			 SQLHS.append(" a.m_fifapps_param_02,");
			 SQLHS.append(" a.m_fifapps_param_03");
			 SQLHS.append(" FROM m_fifapps_ws_tmp a");
			 SQLHS.append(" WHERE NOT EXISTS(");
			 SQLHS.append(" 	SELECT 1 FROM m_fifapps_housestatus b ");
			 SQLHS.append(" 	WHERE a.m_fifapps_param_01 = b.housestat )");
			 //SQLHS.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLHS.append(" AND a.m_fifapps_source = ? ");
			
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLHS.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_HouseStatus_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_housestatus
						 X_M_Fifapps_Housestatus Housestatus = new X_M_Fifapps_Housestatus(ctx, 0, null);
						 Housestatus.setClientOrg(0,0);
						 Housestatus.setIsActive(true);
						 Housestatus.sethousestat(rs.getString(1));
						 Housestatus.setstatusdescr(rs.getString(2));
						 Housestatus.setvisible(rs.getString(3));
						 Housestatus.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

			             
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_HouseStatus_source );

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLHS.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 
			 
			
		 }else if(X_Class.equals("X_M_Fifapps_Inputsource")){
			
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_inputsource
			 StringBuilder SQLIS = new StringBuilder();
			 SQLIS.append("SELECT a.m_fifapps_param_01,");
			 SQLIS.append(" a.m_fifapps_param_02,");
			 SQLIS.append(" a.m_fifapps_param_03,");
			 SQLIS.append(" a.m_fifapps_param_04,");
			 SQLIS.append(" a.m_fifapps_param_05");
			 SQLIS.append(" FROM m_fifapps_ws_tmp a");
			 SQLIS.append(" WHERE NOT EXISTS(");
			 SQLIS.append(" 	SELECT 1 FROM m_fifapps_inputsource b ");
			 SQLIS.append(" 	WHERE a.m_fifapps_param_02 = b.code )");
			// SQLIS.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLIS.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLIS.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_InputSource_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_inputsource
						 X_M_Fifapps_Inputsource Inputsource = new X_M_Fifapps_Inputsource(ctx, 0, null);
						 Inputsource.setClientOrg(0,0);
						 Inputsource.setIsActive(true);
						 Inputsource.setbranchtyperight(rs.getString(1));
						 Inputsource.setCode(rs.getString(2));
						 Inputsource.setdepositkey(rs.getString(3));
						 Inputsource.setDescription(rs.getString(4));
						 Inputsource.setshortname(rs.getString(5));
						 Inputsource.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

			             
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_InputSource_source );

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLIS.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 

			 
		 }else if(X_Class.equals("X_M_Fifapps_Kecamatan")){
		
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_kecamatan
			 StringBuilder SQLKec = new StringBuilder();
			 SQLKec.append("SELECT a.m_fifapps_param_01,");
			 SQLKec.append(" a.m_fifapps_param_02,");
			 SQLKec.append(" a.m_fifapps_param_03");
			 SQLKec.append(" FROM m_fifapps_ws_tmp a");
			 SQLKec.append(" WHERE NOT EXISTS(");
			 SQLKec.append(" 	SELECT 1 FROM m_fifapps_kecamatan b ");
			 SQLKec.append(" 	WHERE a.m_fifapps_param_02 = b.keccode ) ");
			 //SQLKec.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLKec.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLKec.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_Kecamatan_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_kecamatan
						 X_M_Fifapps_Kecamatan Kecamatan = new X_M_Fifapps_Kecamatan(ctx, 0, null);
						 Kecamatan.setClientOrg(0,0);
						 Kecamatan.setIsActive(true);
						 Kecamatan.setcitycode(rs.getString(1));
						 Kecamatan.setkeccode(rs.getString(2));
						 Kecamatan.setkecamatan(rs.getString(3));
						 Kecamatan.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
			            Update.append(" AND m_fifapps_param_03 = '" + rs.getString(3)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Kecamatan_source );

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLKec.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 


			 
		 }else if(X_Class.equals("X_M_Fifapps_Kelurahan")){
			
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_kelurahan
			 StringBuilder SQLKel = new StringBuilder();
			 SQLKel.append("SELECT a.m_fifapps_param_01,");
			 SQLKel.append(" a.m_fifapps_param_02,");
			 SQLKel.append(" a.m_fifapps_param_03");
			 SQLKel.append(" FROM m_fifapps_ws_tmp a");
			 SQLKel.append(" WHERE NOT EXISTS(");
			 SQLKel.append(" 	SELECT 1 FROM m_fifapps_kelurahan b ");
			 SQLKel.append(" 	WHERE  a.m_fifapps_param_02 = b.kelcode )");
			// SQLKel.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLKel.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLKel.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_Kelurahan_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_kelurahan
						 X_M_Fifapps_Kelurahan Kelurahan = new X_M_Fifapps_Kelurahan(ctx, 0, null);
						 Kelurahan.setClientOrg(0,0);
						 Kelurahan.setIsActive(true);
						 Kelurahan.setkeccode(rs.getString(1));
						 Kelurahan.setkelcode(rs.getString(2));
						 Kelurahan.setkelurahan(rs.getString(3));
						 Kelurahan.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Kelurahan_source );

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLKel.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 


			 
		 }
		 else if(X_Class.equals("X_M_Fifapps_Maritalstatus")){
			
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_maritalstatus
			 StringBuilder SQLMS = new StringBuilder();
			 SQLMS.append("SELECT a.m_fifapps_param_01,");
			 SQLMS.append(" a.m_fifapps_param_02,");
			 SQLMS.append(" a.m_fifapps_param_03,");
			 SQLMS.append(" a.m_fifapps_param_04");
			 SQLMS.append(" FROM m_fifapps_ws_tmp a");
			 SQLMS.append(" WHERE NOT EXISTS(");
			 SQLMS.append(" 	SELECT 1 FROM m_fifapps_maritalstatus b ");
			 SQLMS.append(" 	WHERE a.m_fifapps_param_03 = b.maritalstat ) ");
			// SQLMS.append(" AND a.m_fifapps_status_process = 'N'");
			 SQLMS.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLMS.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_MaritalStatus_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_MaritalStatus
						 X_M_Fifapps_Maritalstatus Maritalstatus = new X_M_Fifapps_Maritalstatus(ctx, 0, null);
						 Maritalstatus.setClientOrg(0,0);
						 Maritalstatus.setIsActive(true);
						 Maritalstatus.setaabcode(rs.getString(1));
						 Maritalstatus.setmaritaldesc(rs.getString(2));
						 Maritalstatus.setmaritalstat(rs.getString(3));
						 Maritalstatus.setvisible(rs.getString(4));
						 Maritalstatus.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
			            Update.append(" AND m_fifapps_param_03 = '" + rs.getString(3)+"'");
			            Update.append(" AND m_fifapps_param_04 = '" + rs.getString(4)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_MaritalStatus_source );

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLMS.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 
			

			 
		 }else if(X_Class.equals("X_M_Fifapps_Objcodes")){
			 
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_objcodes
			 StringBuilder SQLOC = new StringBuilder();
			 SQLOC.append("SELECT a.m_fifapps_param_01,");
			 SQLOC.append(" a.m_fifapps_param_02,");
			 SQLOC.append(" a.m_fifapps_param_03,");
			 SQLOC.append(" a.m_fifapps_param_04,");
			 SQLOC.append(" a.m_fifapps_param_05,");
			 SQLOC.append(" a.m_fifapps_param_06,");
			 SQLOC.append(" a.m_fifapps_param_07,");
			 SQLOC.append(" a.m_fifapps_param_08,");
			 SQLOC.append(" a.m_fifapps_param_09,");
			 SQLOC.append(" a.m_fifapps_param_10,");
			 SQLOC.append(" a.m_fifapps_param_11,");
			 SQLOC.append(" a.m_fifapps_param_12,");
			 SQLOC.append(" a.m_fifapps_param_13");
			 SQLOC.append(" FROM m_fifapps_ws_tmp a");
			 SQLOC.append(" WHERE NOT EXISTS(");
			 SQLOC.append(" 	SELECT 1 FROM m_fifapps_objcodes b ");
			 SQLOC.append(" 	WHERE a.m_fifapps_param_07 = b.objcode ");
			 SQLOC.append(" 	AND a.m_fifapps_param_09 = b.objgrp )");
			// SQLOC.append(" AND a.m_fifapps_status_process = 'N'");
			 SQLOC.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLOC.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_ObjectCode_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_Objcodes
						 X_M_Fifapps_Objcodes Objcodes = new X_M_Fifapps_Objcodes(ctx, 0, null);
						 Objcodes.setClientOrg(0,0);
						 Objcodes.setIsActive(true);
						 Objcodes.setBranchID(rs.getString(1));
						 Objcodes.setcategoryid(rs.getString(2));
						 Objcodes.setisvehicle(rs.getString(3));
						 Objcodes.setneedaddcoll(rs.getString(4));
						 Objcodes.setnewused(rs.getString(5));
						 Objcodes.setobjbrand(rs.getString(6));
						 Objcodes.setobjcode(rs.getString(7));
						 Objcodes.setobjdescr(rs.getString(8));
						 Objcodes.setobjgrp(rs.getString(9));
						 Objcodes.setobjmake(rs.getString(10));
						 Objcodes.setobjmodel(rs.getString(11));
						 Objcodes.setobjsize(rs.getString(12));
						 Objcodes.setobjtype(rs.getString(13));
						 Objcodes.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
			            Update.append(" AND m_fifapps_param_07 = '" + rs.getString(7)+"'");
			            Update.append(" AND m_fifapps_param_09 = '" + rs.getString(9)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_ObjectCode_source);

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLOC.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 
	 
		 }else if(X_Class.equals("X_M_Fifapps_Objgroups")){
			
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_objgroup
			 StringBuilder SQLOG = new StringBuilder();
			 SQLOG.append("SELECT a.m_fifapps_param_01,");
			 SQLOG.append(" a.m_fifapps_param_02,");
			 SQLOG.append(" a.m_fifapps_param_03");
			 SQLOG.append(" FROM m_fifapps_ws_tmp a");
			 SQLOG.append(" WHERE NOT EXISTS(");
			 SQLOG.append(" 	SELECT 1 FROM m_fifapps_objgroups b ");
			 SQLOG.append(" 	WHERE coalesce(a.m_fifapps_param_03,'x') = coalesce(b.objgrp,'x') )");
			 //SQLOG.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLOG.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLOG.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_ObjectGroup_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_Objcodes
						 X_M_Fifapps_Objgroups Objgroups = new X_M_Fifapps_Objgroups(ctx, 0, null);
						 Objgroups.setClientOrg(0,0);
						 Objgroups.setIsActive(true);
						 Objgroups.setcolltype(rs.getString(1));
						 Objgroups.setobjdescr(rs.getString(2));
						 Objgroups.setobjgrp(rs.getString(3));
						 Objgroups.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_03 = '" + rs.getString(3)+"'");
			           
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_ObjectGroup_source);

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLOG.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 

			 
		 }else if(X_Class.equals("X_M_Fifapps_Occupations")){
			
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_occupations
			 StringBuilder SQLOccup = new StringBuilder();
			 SQLOccup.append("SELECT a.m_fifapps_param_01,");
			 SQLOccup.append(" a.m_fifapps_param_02,");
			 SQLOccup.append(" a.m_fifapps_param_03,");
			 SQLOccup.append(" a.m_fifapps_param_04,");
			 SQLOccup.append(" a.m_fifapps_param_05,");
			 SQLOccup.append(" a.m_fifapps_param_06,");
			 SQLOccup.append(" a.m_fifapps_param_07,");
			 SQLOccup.append(" a.m_fifapps_param_08,");
			 SQLOccup.append(" a.m_fifapps_param_09,");
			 SQLOccup.append(" a.m_fifapps_param_10,");
			 SQLOccup.append(" a.m_fifapps_param_11,");
			 SQLOccup.append(" a.m_fifapps_param_12,");
			 SQLOccup.append(" a.m_fifapps_param_13,");
			 SQLOccup.append(" a.m_fifapps_param_14,");
			 SQLOccup.append(" a.m_fifapps_param_15,");
			 SQLOccup.append(" a.m_fifapps_param_16,");
			 SQLOccup.append(" a.m_fifapps_param_17");
			 SQLOccup.append(" FROM m_fifapps_ws_tmp a");
			 SQLOccup.append(" WHERE NOT EXISTS(");
			 SQLOccup.append(" 	SELECT 1 FROM m_fifapps_occupations b ");
			 SQLOccup.append(" 	WHERE a.m_fifapps_param_12 = b.ocptcode ) ");
			// SQLOccup.append(" AND a.m_fifapps_status_process = 'N'");
			 SQLOccup.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLOccup.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_Occupation_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_Occupations
						 X_M_Fifapps_Occupations Occupations = new X_M_Fifapps_Occupations(ctx, 0, null);
						 Occupations.setClientOrg(0,0);
						 Occupations.setIsActive(true);
						 Occupations.setaabcode(rs.getString(1));
						 Occupations.setbibidangusaha(rs.getString(2));
						 Occupations.setbibidangusaharef(rs.getString(3));
						 Occupations.setbigoldebitur(rs.getString(4));
						 Occupations.setbigoldebiturref(rs.getString(5));
						 Occupations.setbipekerjaan(rs.getString(6));
						 Occupations.setbipekerjaanref(rs.getString(7));
						 Occupations.setDescription(rs.getString(8));
						 Occupations.setkodebca(rs.getString(9));
						 Occupations.setmandatory(rs.getString(10));
						 Occupations.setmbiprofesi(rs.getString(11));
						 Occupations.setocptcode(rs.getString(12));
						 Occupations.setocptcodebca(rs.getString(13));
						 Occupations.setocptrating(rs.getString(14));
						 Occupations.setocpttype(rs.getString(15));
						 Occupations.setsumberpenghasilan(rs.getString(16));
						 Occupations.setvisible(rs.getString(17));
						 Occupations.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_12 = '" + rs.getString(12)+"'");
			           
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Occupation_source);

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLOccup.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}

			 
		 }else if(X_Class.equals("X_M_Fifapps_Offices")){
			
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_Offices
			 StringBuilder SQLOff = new StringBuilder();
			 SQLOff.append("SELECT a.m_fifapps_param_01,");
			 SQLOff.append(" a.m_fifapps_param_02,");
			 SQLOff.append(" a.m_fifapps_param_03,");
			 SQLOff.append(" a.m_fifapps_param_04,");
			 SQLOff.append(" a.m_fifapps_param_05,");
			 SQLOff.append(" a.m_fifapps_param_06,");
			 SQLOff.append(" a.m_fifapps_param_07,");
			 SQLOff.append(" a.m_fifapps_param_08,");
			 SQLOff.append(" a.m_fifapps_param_09,");
			 SQLOff.append(" a.m_fifapps_param_10,");
			 SQLOff.append(" a.m_fifapps_param_11,");
			 SQLOff.append(" a.m_fifapps_param_12,");
			 SQLOff.append(" a.m_fifapps_param_13,");
			 SQLOff.append(" a.m_fifapps_param_14,");
			 SQLOff.append(" a.m_fifapps_param_15,");
			 SQLOff.append(" a.m_fifapps_param_16,");
			 SQLOff.append(" a.m_fifapps_param_17,");
			 SQLOff.append(" a.m_fifapps_param_18,");
			 SQLOff.append(" a.m_fifapps_param_19,");
			 SQLOff.append(" a.m_fifapps_param_20,");
			 SQLOff.append(" a.m_fifapps_param_21,");
			 SQLOff.append(" a.m_fifapps_param_22,");
			 SQLOff.append(" a.m_fifapps_param_23,");
			 SQLOff.append(" a.m_fifapps_param_24,");
			 SQLOff.append(" a.m_fifapps_param_25,");
			 SQLOff.append(" a.m_fifapps_param_26,");
			 SQLOff.append(" a.m_fifapps_param_27,");
			 SQLOff.append(" a.m_fifapps_param_28,");
			 SQLOff.append(" a.m_fifapps_param_29,");
			 SQLOff.append(" a.m_fifapps_param_30,");
			 SQLOff.append(" a.m_fifapps_param_31,");
			 SQLOff.append(" a.m_fifapps_param_32,");
			 SQLOff.append(" a.m_fifapps_param_33,");
			 SQLOff.append(" a.m_fifapps_param_34,");
			 SQLOff.append(" a.m_fifapps_param_35,");
			 SQLOff.append(" a.m_fifapps_param_36,");
			 SQLOff.append(" a.m_fifapps_param_37,");
			 SQLOff.append(" a.m_fifapps_param_38,");
			 SQLOff.append(" a.m_fifapps_param_39");
			 SQLOff.append(" FROM m_fifapps_ws_tmp a");
			 SQLOff.append(" WHERE NOT EXISTS(");
			 SQLOff.append(" 	SELECT 1 FROM m_fifapps_offices b ");
			 SQLOff.append(" 	WHERE coalesce(a.m_fifapps_param_15,'x') = coalesce(b.officecode,'x') )");
			// SQLOff.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLOff.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLOff.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_Offices_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_Occupations
						 X_M_Fifapps_Offices Offices = new X_M_Fifapps_Offices(ctx, 0, null);
						 Offices.setClientOrg(0,0);
						 Offices.setIsActive(true);
						 Offices.setaccspricedevpct(StringToBD(rs.getString(1)));
						 Offices.setAddress1(rs.getString(2));
						 Offices.setAddress2(rs.getString(3));
						 Offices.setAddress3(rs.getString(4));
						 Offices.setbpkbprctime(StringToInteger(rs.getString(5)));
						 Offices.setbranchcode(rs.getString(6));
						 Offices.setCity(rs.getString(7));
						 Offices.setcollincentive(StringToBD(rs.getString(8)));
						 Offices.setdifftime(StringToInteger(rs.getString(9)));
						 Offices.setlocation(rs.getString(10));
						 Offices.setnamefull(rs.getString(11));
						 Offices.setnameshort(rs.getString(12));
						 Offices.setofficecategory(rs.getString(13));
						 Offices.setofficeclasscode(rs.getString(14));
						 Offices.setofficecode(rs.getString(15));
						 Offices.setofficecoderep(rs.getString(16));
						 Offices.setofficetype(rs.getString(17));
						 Offices.setphone1(rs.getString(18));
						 Offices.setPhone2(rs.getString(19));
						 Offices.setphone3(rs.getString(20));
						 Offices.setpicaddr1(rs.getString(21));
						 Offices.setpicaddr2(rs.getString(22));
						 Offices.setpicaddr3(rs.getString(23));
						 Offices.setpiccity(rs.getString(24));
						 Offices.setpicfirstname(rs.getString(25));
						 Offices.setpiclastname(rs.getString(26));
						 Offices.setpicphone1(rs.getString(27));
						 Offices.setpicphone2(rs.getString(28));
						 Offices.setpicphone3(rs.getString(29));
						 Offices.setpositioncode(rs.getString(30));
						 Offices.setprocessgroup(StringToInteger(rs.getString(31)));
						 Offices.setprofitdealerbygrade(rs.getString(32));
						 Offices.setprofitdealerflatamt(StringToBD(rs.getString(33)));
						 Offices.setprofitdealerpct(StringToBD(rs.getString(34)));
						 Offices.setregionarcode(rs.getString(35));
						 Offices.setregionalid(rs.getString(36));
						 Offices.setregionalidinsc(rs.getString(37));
						 Offices.setstatusflag(rs.getString(38));
						 Offices.setvalidpodays(StringToBD(rs.getString(39)));
						 Offices.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

						 
			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_06 = '" + rs.getString(6)+"'");
			            Update.append(" AND m_fifapps_param_07 = '" + rs.getString(7)+"'");
			            Update.append(" AND m_fifapps_param_13 = '" + rs.getString(13)+"'");
			            Update.append(" AND m_fifapps_param_15 = '" + rs.getString(15)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Offices_source);
			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLOff.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 
			 
			
			 
		 }else if(X_Class.equals("X_M_Fifapps_Supplier")){
			
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_supplier
			 StringBuilder SQLSupp = new StringBuilder();
			 SQLSupp.append("SELECT a.m_fifapps_param_01,");
			 SQLSupp.append(" a.m_fifapps_param_02,");
			 SQLSupp.append(" a.m_fifapps_param_03,");
			 SQLSupp.append(" a.m_fifapps_param_04,");
			 SQLSupp.append(" a.m_fifapps_param_05,");
			 SQLSupp.append(" a.m_fifapps_param_06,");
			 SQLSupp.append(" a.m_fifapps_param_07,");
			 SQLSupp.append(" a.m_fifapps_param_08,");
			 SQLSupp.append(" a.m_fifapps_param_09,");
			 SQLSupp.append(" a.m_fifapps_param_10,");
			 SQLSupp.append(" a.m_fifapps_param_11,");
			 SQLSupp.append(" a.m_fifapps_param_12,");
			 SQLSupp.append(" a.m_fifapps_param_13,");
			 SQLSupp.append(" a.m_fifapps_param_14,");
			 SQLSupp.append(" a.m_fifapps_param_15");
			 SQLSupp.append(" FROM m_fifapps_ws_tmp a");
			 SQLSupp.append(" WHERE NOT EXISTS(");
			 SQLSupp.append(" 	SELECT 1 FROM m_fifapps_supplier b ");
			 SQLSupp.append(" 	WHERE a.m_fifapps_param_05 = b.suppliercode )");
			 //SQLSupp.append(" AND a.m_fifapps_status_process = 'N' ");
			 SQLSupp.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLSupp.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_Supplier_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_suppliers
						 X_M_Fifapps_Supplier Supplier = new X_M_Fifapps_Supplier(ctx, 0, null);
						 Supplier.setClientOrg(0,0);
						 Supplier.setIsActive(true);
						 Supplier.setAddress1(rs.getString(1));
						 Supplier.setAddress2(rs.getString(2));
						 Supplier.setCity(rs.getString(3));
						 Supplier.setprovinsi(rs.getString(4));
						 Supplier.setsuppliercode(rs.getString(5));
						 Supplier.setsuppliercompanyname(rs.getString(6));
						 Supplier.setsuppliercompanytype(rs.getString(7));
						 Supplier.setsuppliergroupid(rs.getString(8));
						 Supplier.setsuppliermaindealer(rs.getString(9));
						 Supplier.setsuppliername(rs.getString(10));
						 Supplier.setsupplieroutlettype(rs.getString(11));
						 Supplier.setsupplierstatus(rs.getString(12));
						 Supplier.setsuppliersubtype(rs.getString(13));
						 Supplier.setsuppliertype(rs.getString(14));
						 Supplier.setzipcode(rs.getString(15));
						 Supplier.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_05 = '" + rs.getString(5)+"'");
			           
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Supplier_source);

			            
			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLSupp.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			 
			
			 
		 } else if(X_Class.equals("X_M_Fifapps_Zipcode")){
		
			//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_zipcode
			 StringBuilder SQLZIP = new StringBuilder();
			 SQLZIP.append("SELECT a.m_fifapps_param_01,");
			 SQLZIP.append(" a.m_fifapps_param_02,");
			 SQLZIP.append(" a.m_fifapps_param_03,");
			 SQLZIP.append(" a.m_fifapps_param_04,");
			 SQLZIP.append(" a.m_fifapps_param_05,");
			 SQLZIP.append(" a.m_fifapps_param_06,");
			 SQLZIP.append(" a.m_fifapps_param_07");
			 SQLZIP.append(" FROM m_fifapps_ws_tmp a");
			 SQLZIP.append(" WHERE NOT EXISTS(");
			 SQLZIP.append(" 	SELECT 1 FROM m_fifapps_zipcode b ");
			 SQLZIP.append(" 	WHERE a.m_fifapps_param_06 = b.zipcode ");
			 SQLZIP.append(" 	AND a.m_fifapps_param_01 = b.citycode");
			 SQLZIP.append(" 	AND a.m_fifapps_param_02 = b.keccode ");
			 SQLZIP.append(" 	AND a.m_fifapps_param_03 = b.kelcode ");
			 SQLZIP.append(" 	AND a.m_fifapps_param_04 = b.provcode ");
			 SQLZIP.append(" 	AND a.m_fifapps_param_05 = b.subzipcode )");
//			 SQLZIP.append(" AND a.m_fifapps_status_process = 'N'");
			 SQLZIP.append(" AND a.m_fifapps_source = ? ");
			 
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLZIP.toString(), null);
					pstmt.setInt(1, FIF_GParam.M_FifApps_ZipCode_source);

					rs = pstmt.executeQuery();
					while (rs.next()) {
						
						//Insert To table X_M_Fifapps_zipcode
						 X_M_Fifapps_Zipcode Zipcode = new X_M_Fifapps_Zipcode(ctx, 0, null);
						 Zipcode.setClientOrg(0,0);
						 Zipcode.setIsActive(true);
						 Zipcode.setcitycode(rs.getString(1));
						 Zipcode.setkeccode(rs.getString(2));
						 Zipcode.setkelcode(rs.getString(3));
						 Zipcode.setprovcode(rs.getString(4));
						 Zipcode.setsubzipcode(rs.getString(5));
						 Zipcode.setzipcode(rs.getString(6));
						 Zipcode.setzipdesc(rs.getString(7));
						 Zipcode.saveEx();
						 count++;
						 //System.out.println("Inserted Data : "+count);

			            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
			            StringBuilder Update = new StringBuilder();
			            Update.append("UPDATE m_fifapps_ws_tmp ");
			            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
			            Update.append("WHERE m_fifapps_status_process = 'N' ");
			            Update.append(" AND m_fifapps_param_01 = '" + rs.getString(1)+"'");
			            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
			            Update.append(" AND m_fifapps_param_03 = '" + rs.getString(3)+"'");
			            Update.append(" AND m_fifapps_param_04 = '" + rs.getString(4)+"'");
			            Update.append(" AND m_fifapps_param_06 = '" + rs.getString(6)+"'");
			            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_ZipCode_source);

			            DB.executeUpdateEx(Update.toString(), null);
	
					}

				} catch (SQLException e) {
					log.log(Level.SEVERE, SQLZIP.toString(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
 
		 }else if(X_Class.equals("X_M_Fifapps_Prov")){
				
				//Query get Data from m_fifapps_ws_tmp where not exist at m_fifapps_zipcode
				 StringBuilder SQLProv = new StringBuilder();
				 SQLProv.append("SELECT a.m_fifapps_param_01,");
				 SQLProv.append(" a.m_fifapps_param_02");
				 SQLProv.append(" FROM m_fifapps_ws_tmp a");
				 SQLProv.append(" WHERE NOT EXISTS(");
				 SQLProv.append(" SELECT 1 FROM m_fifapps_prov b ");
				 SQLProv.append(" WHERE a.m_fifapps_param_02 = b.provcode )");
				 //SQLProv.append(" AND a.m_fifapps_status_process = 'N'");
				 SQLProv.append(" AND a.m_fifapps_source = ? ");
				 
				 PreparedStatement pstmt = null;
				 ResultSet rs = null;
					try {
						pstmt = DB.prepareStatement(SQLProv.toString(), null);
						pstmt.setInt(1, FIF_GParam.M_FifApps_Prov_source);

						rs = pstmt.executeQuery();
						while (rs.next()) {
							
							//Insert To table X_M_Fifapps_zipcode
							 X_M_Fifapps_Prov prov= new X_M_Fifapps_Prov(ctx, 0, null);
							 prov.setClientOrg(0,0);
							 prov.setIsActive(true);
							 prov.setprovinsi(rs.getString(1));
							 prov.setprovcode(rs.getString(2));
							 prov.saveEx();
							 count++;
							 //System.out.println("Inserted Data : "+count);

				            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
				            StringBuilder Update = new StringBuilder();
				            Update.append("UPDATE m_fifapps_ws_tmp ");
				            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
				            Update.append("WHERE m_fifapps_status_process = 'N' ");
				            Update.append(" AND m_fifapps_param_02 = '" + rs.getString(2)+"'");
				            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Prov_source);

				            DB.executeUpdateEx(Update.toString(), null);
		
						}

					} catch (SQLException e) {
						log.log(Level.SEVERE, SQLProv.toString(), e);
					} finally {
						DB.close(rs, pstmt);
						rs = null;
						pstmt = null;
					}
	 
			 }else if(X_Class.equals("X_C_Decoris_PreOrderDtl")){
					
					//Query get Data from m_fifapps_ws_tmp where not exist at C_Decoris_PreOrderDtl
				 StringBuilder SQLTrackDetail = new StringBuilder();
				 SQLTrackDetail.append("SELECT a.m_fifapps_param_01,");
				 SQLTrackDetail.append(" a.m_fifapps_param_02,");
				 SQLTrackDetail.append(" a.m_fifapps_param_03,");
				 SQLTrackDetail.append(" a.m_fifapps_param_04,");
				 SQLTrackDetail.append(" a.m_fifapps_param_05,");
				 SQLTrackDetail.append(" a.m_fifapps_param_06,");
				 SQLTrackDetail.append(" a.m_fifapps_param_07,");
				 SQLTrackDetail.append(" a.m_fifapps_param_08,");
				 SQLTrackDetail.append(" a.m_fifapps_param_09,");
				 SQLTrackDetail.append(" a.m_fifapps_param_10,");
				 SQLTrackDetail.append(" a.m_fifapps_param_11,");
				 SQLTrackDetail.append(" a.m_fifapps_param_12,");
				 SQLTrackDetail.append(" a.m_fifapps_param_13,");
				 SQLTrackDetail.append(" a.m_fifapps_param_14,");
				 SQLTrackDetail.append(" a.m_fifapps_param_15,");
				 SQLTrackDetail.append(" a.m_fifapps_param_16,");
				 SQLTrackDetail.append(" a.m_fifapps_param_17,");
				 SQLTrackDetail.append(" a.m_fifapps_param_18,");
				 SQLTrackDetail.append(" a.m_fifapps_param_19,");
				 SQLTrackDetail.append(" a.m_fifapps_param_20,");
				 SQLTrackDetail.append(" a.m_fifapps_param_21,");
				 SQLTrackDetail.append(" a.m_fifapps_param_22,");
				 SQLTrackDetail.append(" a.m_fifapps_param_23,");
				 SQLTrackDetail.append(" a.m_fifapps_param_24,");
				 SQLTrackDetail.append(" a.m_fifapps_param_25,");
				 SQLTrackDetail.append(" a.m_fifapps_param_26,");
				 SQLTrackDetail.append(" a.m_fifapps_param_27,");
				 SQLTrackDetail.append(" a.m_fifapps_param_28,");
				 SQLTrackDetail.append(" a.m_fifapps_param_29,");
				 SQLTrackDetail.append(" a.m_fifapps_param_30,");
				 SQLTrackDetail.append(" a.m_fifapps_param_31,");
				 SQLTrackDetail.append(" a.m_fifapps_param_32,");
				 SQLTrackDetail.append(" a.m_fifapps_param_33,");
				 SQLTrackDetail.append(" a.m_fifapps_param_34,");
				 SQLTrackDetail.append(" a.m_fifapps_param_35,");
				 SQLTrackDetail.append(" a.m_fifapps_param_36,");
				 SQLTrackDetail.append(" a.m_fifapps_param_37,");
				 SQLTrackDetail.append(" a.m_fifapps_param_38,");
				 SQLTrackDetail.append(" a.m_fifapps_param_39,");
				 SQLTrackDetail.append(" a.m_fifapps_param_40,");
				 SQLTrackDetail.append(" a.m_fifapps_param_41,");
				 SQLTrackDetail.append(" a.m_fifapps_param_42,");
				 SQLTrackDetail.append(" a.m_fifapps_param_43,");
				 SQLTrackDetail.append(" a.m_fifapps_param_44,");
				 SQLTrackDetail.append(" a.m_fifapps_param_45,");
				 SQLTrackDetail.append(" a.m_fifapps_param_46,");
				 SQLTrackDetail.append(" a.m_fifapps_param_47,");
				 SQLTrackDetail.append(" a.m_fifapps_param_48,");
				 SQLTrackDetail.append(" a.m_fifapps_param_49,");
				 SQLTrackDetail.append(" a.m_fifapps_param_50,");
				 SQLTrackDetail.append(" a.m_fifapps_param_51,");
				 SQLTrackDetail.append(" a.m_fifapps_param_52,");
				 SQLTrackDetail.append(" a.m_fifapps_param_53 ");
				 SQLTrackDetail.append(" FROM m_fifapps_ws_tmp a");
				 SQLTrackDetail.append(" WHERE NOT EXISTS(");
				 SQLTrackDetail.append(" 	SELECT 1 FROM C_Decoris_PreOrderDtl b ");
				 SQLTrackDetail.append(" 	WHERE coalesce(a.m_fifapps_param_04,'x') = coalesce(b.applNo,'x') ");
				 SQLTrackDetail.append(" 	AND coalesce(a.m_fifapps_param_41,'x') = coalesce(b.officeCode,'x') ");
				 SQLTrackDetail.append(" 	AND coalesce(a.m_fifapps_param_50,'x') = coalesce(b.suppCode,'x') ");
				 SQLTrackDetail.append(" 	AND coalesce(a.m_fifapps_param_41,'x') = coalesce(b.orderid,'x') )");
				 //SQLTrackDetail.append(" AND a.m_fifapps_status_process = 'N' )");
				 SQLTrackDetail.append(" AND a.m_fifapps_source = ? ");
					 
					 PreparedStatement pstmt = null;
					 ResultSet rs = null;
						try {
							pstmt = DB.prepareStatement(SQLTrackDetail.toString(), null);
							pstmt.setInt(1, FIF_GParam.M_FifApps_TrackingDetail_source);

							rs = pstmt.executeQuery();
							while (rs.next()) {
								
								//Insert To table X_M_Fifapps_Occupations
								X_C_Decoris_PreOrderDtl dtl = new X_C_Decoris_PreOrderDtl(ctx, 0, null);
								dtl.setClientOrg(0,0);
								dtl.setIsActive(true);
								dtl.setapdocstatus(rs.getString(1));
								dtl.setaptotalamount(new BigDecimal(rs.getString(2)));
								dtl.setappldate(rs.getString(3));
								dtl.setapplno(rs.getString(4));
								dtl.setapproveby(rs.getString(5));
								dtl.setbirthdate(rs.getString(6));
								dtl.setbirthplace(rs.getString(7));
								dtl.setbpkbname(rs.getString(8));
								dtl.setbussunit(rs.getString(9));
								dtl.setcancelreason(rs.getString(10));
								dtl.setcontractactivedate(rs.getString(11));
								dtl.setcontractno(rs.getString(12));
								dtl.setcustaddr(rs.getString(13));
								dtl.setcustcity(rs.getString(14));
								dtl.setcustdp(new BigDecimal(rs.getString(15)));
								dtl.setcustfixpharea(rs.getString(16));
								dtl.setcustfixphone(rs.getString(17));
								dtl.setcustkec(rs.getString(18));
								dtl.setcustkel(rs.getString(19));
								dtl.setcustmobphone(rs.getString(120));
								dtl.setcustmobphone2(rs.getString(21));
								dtl.setcustname(rs.getString(22));
								dtl.setcustno(rs.getString(23));
								dtl.setcustprov(rs.getString(24));
								dtl.setcustrt(rs.getString(25));
								dtl.setcustrw(rs.getString(26));
								dtl.setdanasudahcair(rs.getString(27));
								dtl.setjanjisurvey(rs.getString(28));
								dtl.setmonthinst(new BigDecimal(rs.getString(29)));
								dtl.setnoka(rs.getString(30));
								dtl.setnosin(rs.getString(31));
								dtl.setobjbrand(rs.getString(32));
								dtl.setobjcode(rs.getString(33));
								dtl.setobjdesc(rs.getString(34));
								dtl.setobjmodel(rs.getString(35));
								dtl.setobjprice(new BigDecimal(rs.getString(36)));
								dtl.setobjtype(rs.getString(37));
								dtl.setofffixpharea(rs.getString(38));
								dtl.setofffixphext(rs.getString(39));
								dtl.setofffixphone(rs.getString(40));
								dtl.setofficecode(rs.getString(41));
								dtl.setorderid(rs.getString(42));
								dtl.setpodate(rs.getString(43));
								dtl.setpono(rs.getString(44));
								dtl.setpos(rs.getString(45));
								dtl.setservofficecode(rs.getString(46));
								dtl.setstate(rs.getString(47));
								dtl.setstatusorder(rs.getString(49));
								dtl.setsuplname(rs.getString(49));
								dtl.setsuppcode(rs.getString(50));
								dtl.settenor(new BigDecimal(rs.getString(51)));
								dtl.settimeservice(rs.getString(52));
								dtl.setwarna(rs.getString(53));
								dtl.saveEx();
								count++;
								//System.out.println("Inserted Data : "+count);

								 
					            //Update To table m_fif_fifapps_ws_tmp column m_fifapps_status_process
					            StringBuilder Update = new StringBuilder();
					            Update.append("UPDATE m_fifapps_ws_tmp ");
					            Update.append("SET m_fifapps_status_process = 'Y',  m_fifapps_time_process = LOCALTIMESTAMP ");
					            Update.append("WHERE m_fifapps_status_process = 'N' ");
					            Update.append(" AND m_fifapps_param_06 = '" + rs.getString(6)+"'");
					            Update.append(" AND m_fifapps_param_07 = '" + rs.getString(7)+"'");
					            Update.append(" AND m_fifapps_param_13 = '" + rs.getString(13)+"'");
					            Update.append(" AND m_fifapps_param_15 = '" + rs.getString(15)+"'");
					            Update.append(" AND m_fifapps_source = " + FIF_GParam.M_FifApps_Offices_source);
					            
					            DB.executeUpdateEx(Update.toString(), null);
			
							}

						} catch (SQLException e) {
							log.log(Level.SEVERE, SQLTrackDetail.toString(), e);
						} finally {
							DB.close(rs, pstmt);
							rs = null;
							pstmt = null;
						}
					 
					 
					
					 
				 }
		 
		 return count;
		 
	 }
	 
	 private int updateData(int M_FifApps_source){
		 
		StringBuilder SQLExFunction = new StringBuilder();
        SQLExFunction.append("SELECT update_fifapps_f(?)");
        int count = 0;
         
     	PreparedStatement pstmt = null;
     	ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(SQLExFunction.toString(), null);
				pstmt.setInt(1, M_FifApps_source);	
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}

			} catch (SQLException err) {
				log.log(Level.SEVERE, SQLExFunction.toString(), err);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			
		 return count;
	 }
	 
	 private void msgInfo(String Err,String msg){
		 
		 if(Err != null){
			 FDialog.info(windowNo, null, "", Err, "Info");
		 }else if(msg != null && msg != "" && !msg.isEmpty()) { 
			 FDialog.info(windowNo, null, "", msg, "Info");
		 }
		 
	 }
	 
	 
	
	 
	 protected void cleanTable(int M_Fifapps_Source){
			
			StringBuilder SQLCleanTable = new StringBuilder();
			SQLCleanTable.append(" DELETE FROM m_fifapps_ws_tmp ");
			SQLCleanTable.append(" WHERE M_Fifapps_Source = "+ M_Fifapps_Source);

			DB.executeUpdate(SQLCleanTable.toString(), null);
		}
	 
	 protected BigDecimal StringToBD(String value){
		 BigDecimal rs = Env.ZERO;
		 Double dsub = 0.00;
		
		 if(value == null){
			 value = "0";
		 }
		 dsub = Double.valueOf(value.replaceAll(",", ""));
		 rs = BigDecimal.valueOf(dsub);

		 return rs;
	 }
	 
	 protected Integer StringToInteger(String value){
		 Integer rs = 0;
		
		 if(value == null){
			 value = "0";
		 }
		
		 rs = Integer.valueOf(value);

		 return rs;
	 }
	 
	 private String GetBaseURL() {
			StringBuilder getParams = new StringBuilder();
			String parameter = "";
			
			getParams.append("SELECT Description ");
			getParams.append("FROM AD_Param ");
			getParams.append("WHERE Value IN ('rest_token','rest_data_fifapps') ");

			parameter = DB.getSQLValueStringEx(null, getParams.toString());
			
			return parameter;
			
		}
	 
	 private void kelData(String kecCode, String page, String baseURL){
		 
		URL url = null;
		try {
			url = new URL(baseURL+FIF_GParam.KelurahanURL+kecCode+"?"+"page="+page );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	     int countInsert = 0;
	     int countUpdated = 0;
	     cleanTable(FIF_GParam.M_FifApps_Kelurahan_source);
	     StringBuilder msg = new StringBuilder();

	     
	     Gson gson = new Gson();
         
         String dataFromFIF = syncDataRun(getToken(),url);
         JsonParser JsonParser = new JsonParser();
         JsonObject obj = (JsonObject) JsonParser.parse(dataFromFIF);            
         JsonArray kelurahans = obj.getAsJsonArray("kelurahans");
         
	        try {
	            FIF_ModelKelurahan[] datas = gson.fromJson(kelurahans, FIF_ModelKelurahan[].class);
	            System.out.println(datas.toString());
	            for (FIF_ModelKelurahan data : datas) {	
	     			
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(FIF_GParam.M_FifApps_Kelurahan_source);
	    			 tmp.setm_fifapps_param_01(data.kecCode);
	    			 tmp.setm_fifapps_param_02(data.kelCode);
	    			 tmp.setm_fifapps_param_03(data.kelurahan);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			 //count++;
	            
	            }
	            
	        countInsert += createNewRecord("X_M_Fifapps_Kelurahan");
         	countUpdated = updateData(FIF_GParam.M_FifApps_Kelurahan_source);

         	cleanTable(FIF_GParam.M_FifApps_Kelurahan_source);


         	msg.append("Data Kelurahan Berhasil Terinput = " + countInsert + "\n");
           	msg.append("Data Kelurahan Berhasil Terupdate = " + countUpdated + "\n");
           	msg.append("\n");
	        
	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }

	        FDialog.info(windowNo, null, "", msg.toString(), "Info");
		 
	 }
	 
	 private void zipData(String cityCode, String page, String baseURL){
		 URL url =  null;
	     StringBuilder msg = new StringBuilder();

		 try {
			//url = new URL(baseURL+FIF_GParam.ZIPURL+cityCode+"?"+"type="+type+"&"+"status="+Status+"page="+page);
			 url = new URL(baseURL+FIF_GParam.ZIPURL+cityCode+"?page="+page);
		 } catch (MalformedURLException e) {
			e.printStackTrace();
		 }
	     
		 int countInsert = 0;
	     int countUpdated = 0;
	        
	     cleanTable(FIF_GParam.M_FifApps_ZipCode_source);
	     
	     Gson gson = new Gson();
         
         String dataFromFIF = syncDataRun(getToken(),url);
         JsonParser JsonParser = new JsonParser();
         JsonObject obj = (JsonObject) JsonParser.parse(dataFromFIF);            
         JsonArray zips = obj.getAsJsonArray("zips");
         
         
	     	try {
	            FIF_ModelZIP[] datas = gson.fromJson(zips, FIF_ModelZIP[].class);
	           
	            for (FIF_ModelZIP data : datas) {
	           
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(FIF_GParam.M_FifApps_ZipCode_source);
	    			 tmp.setm_fifapps_param_01(data.cityCode);
	    			 tmp.setm_fifapps_param_02(data.kecCode);
	    			 tmp.setm_fifapps_param_03(data.kelCode);
	    			 tmp.setm_fifapps_param_04(data.provCode);
	    			 tmp.setm_fifapps_param_05(data.subZipCode);
	    			 tmp.setm_fifapps_param_06(data.zipCode);
	    			 tmp.setm_fifapps_param_07(data.zipDesc);
	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			// count++;
	            
	            }
	        countInsert += createNewRecord("X_M_Fifapps_Zipcode"); 
         	countUpdated = updateData(FIF_GParam.M_FifApps_ZipCode_source);

	            
         	msg.append("Data ZIP Code Berhasil Terinput = " + countInsert + "\n");
           	msg.append("Data ZIP Code Group Berhasil Terupdate = " + countUpdated + "\n");
           	msg.append("\n");

	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
	     	
	        FDialog.info(windowNo, null, "", msg.toString(), "Info");

	 }

	 
	 private void suppData(String branchid, String type, String status, String page, String baseURL){
		 
	     StringBuilder parameter = new StringBuilder();
	     StringBuilder msg = new StringBuilder();
	        
	     if(branchid != null && branchid != ""){
	    	 
	    	 parameter.append(branchid);
	    	 parameter.append("?");
	    	 parameter.append("type=");
	    	 parameter.append(type);
	    	 parameter.append("&");
	    	 parameter.append("status=");
	    	 parameter.append(status);
	    	 parameter.append("&");
	    	 parameter.append("page=");
	    	 parameter.append(page);
 
	     }
	     
	        
		 URL url = null;
		 try {
			url = new URL(baseURL+FIF_GParam.SupplierURL+parameter.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		 
		 Gson gson = new Gson();
	         
	     String dataFromFIF = syncDataRun(getToken(),url);
	     JsonParser JsonParser = new JsonParser();
	     JsonObject obj = (JsonObject) JsonParser.parse(dataFromFIF);            
	     JsonArray suppliers = obj.getAsJsonArray("suppliers");
	         

	     int countInsert = 0;
	     int countUpdated = 0;
	        try {
	            FIF_ModelSupplier[] datas = gson.fromJson(suppliers, FIF_ModelSupplier[].class);
	            
	            for (FIF_ModelSupplier data : datas) {

	            	 int M_FIFApps_Source = FIF_GParam.M_FifApps_Supplier_source;
	    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
	    			 tmp.setClientOrg(0, 0);
	    			 tmp.setm_fifapps_source(M_FIFApps_Source);
	    			 tmp.setm_fifapps_param_01(data.address1);
	    			 tmp.setm_fifapps_param_02(data.address2);
	    			 tmp.setm_fifapps_param_03(data.city);
	    			 tmp.setm_fifapps_param_04(data.provinsi);
	    			 tmp.setm_fifapps_param_05(data.supplierCode);
	    			 tmp.setm_fifapps_param_06(data.supplierCompanyName);
	    			 tmp.setm_fifapps_param_07(data.supplierCompanyType);
	    			 tmp.setm_fifapps_param_08(data.supplierGroupId);
	    			 tmp.setm_fifapps_param_09(data.supplierMainDealer);
	    			 tmp.setm_fifapps_param_10(data.supplierName);
	    			 tmp.setm_fifapps_param_11(data.supplierOutletType);
	    			 tmp.setm_fifapps_param_12(data.supplierStatus);
	    			 tmp.setm_fifapps_param_13(data.supplierSubType);
	    			 tmp.setm_fifapps_param_14(data.supplierType);
	    			 tmp.setm_fifapps_param_15(data.zipcode);
	    			 tmp.setm_fifapps_param_16(branchid);

	    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
	    			 tmp.setm_process_by(100);
	    			 tmp.saveEx();
	    			// count++;
	            
	            }
	        countInsert += createNewRecord("X_M_Fifapps_Supplier");   
         	countUpdated = updateData(FIF_GParam.M_FifApps_Supplier_source);

	            
         	msg.append("Data Supplier Berhasil Terinput = " + countInsert + "\n");
           	msg.append("Data Supplier Berhasil Terupdate = " + countUpdated + "\n");
           	msg.append("\n");

	        } catch (Exception ex) {
	            System.out.println("Err "+ex.getMessage());
	        }
		 
	        FDialog.info(windowNo, null, "", msg.toString(), "Info");

	 }

	 private void objCodeData(String objGrp, String baseURL){
	     StringBuilder msg = new StringBuilder();

		 StringBuilder SQLObjGrp = new StringBuilder();
			SQLObjGrp.append("SELECT ObjGrp ");
			SQLObjGrp.append("FROM  M_Fifapps_Objgroups ");
			SQLObjGrp.append("WHERE ObjGrp = '"+objGrp + "'");

			
			int countInsert = 0;
			int countUpdated = 0;
			//int count = 0;
			int M_FIFApps_Source = FIF_GParam.M_FifApps_ObjectCode_source;
			cleanTable(M_FIFApps_Source);
			 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(SQLObjGrp.toString(), null);
					

					rs = pstmt.executeQuery();
					while (rs.next()) {
				        URL url= null;
						try {
							url = new URL(baseURL+FIF_GParam.ObjCodeURL+"?objGrp="+rs.getString(1));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						  try {
					            Gson gson = new Gson();
					            FIF_ModelObjectCode[] datas = gson.fromJson(syncDataRun(getToken(),url), FIF_ModelObjectCode[].class);
					            for (FIF_ModelObjectCode data : datas) {
					    			 
					    			 X_m_fifapps_ws_tmp tmp = new X_m_fifapps_ws_tmp(ctx, 0, null);
					    			 tmp.setClientOrg(0, 0);
					    			 tmp.setm_fifapps_source(M_FIFApps_Source);
					    			 tmp.setm_fifapps_param_01(data.branchId);
					    			 tmp.setm_fifapps_param_02(data.categoryId);
					    			 tmp.setm_fifapps_param_03(data.isVehicle);
					    			 tmp.setm_fifapps_param_04(data.needAddColl);
					    			 tmp.setm_fifapps_param_05(data.newUsed);
					    			 tmp.setm_fifapps_param_06(data.objBrand);
					    			 tmp.setm_fifapps_param_07(data.objCode);
					    			 tmp.setm_fifapps_param_08(data.objDescr);
					    			 tmp.setm_fifapps_param_09(data.objGrp);
					    			 tmp.setm_fifapps_param_10(data.objMake);
					    			 tmp.setm_fifapps_param_11(data.objModel);
					    			 tmp.setm_fifapps_param_12(data.objSize);
					    			 tmp.setm_fifapps_param_13(data.objType);
					    			 tmp.setm_fifapps_time_insert(new Timestamp(System.currentTimeMillis()));
					    			 tmp.setm_process_by(100);
					    			 tmp.saveEx();
					    			 //count++;
					            
					            }

					        } catch (Exception ex) {
					            System.out.println("Err "+ex.getMessage());
					            return;
					        }
						
					
					}

				} catch (SQLException err) {
					log.log(Level.SEVERE, SQLObjGrp.toString(), err);
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
				countInsert += createNewRecord("X_M_Fifapps_Objcodes");
				countUpdated = updateData(FIF_GParam.M_FifApps_ObjectCode_source);


         	msg.append("Data Object Code Berhasil Terinput = " + countInsert + "\n");
           	msg.append("Data Object Code Berhasil Terupdate = " + countUpdated + "\n");
           	msg.append("\n");
	      
	        FDialog.info(windowNo, null, "", msg.toString(), "Info");

           	
           	
	 }
	 
	 
}


