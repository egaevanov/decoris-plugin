/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.decoris.pos.webui.form;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.AdempiereWebUI;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.DialogEvents;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridTab;
import org.compiere.model.MClient;
import org.compiere.model.MImage;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.North;
import org.zkoss.zul.Separator;
import org.zkoss.zul.South;

/**
 *  Base on the original Swing Image Dialog.
 *  @author   Jorg Janke
 *  
 *  Zk Port
 *  @author Low Heng Sin 
 *  
 */
public class WImageUpload extends ImageUpload implements EventListener<Event>,DialogEvents
{

	private Window window;
	private Integer AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	
	private Button bntImg1 = new Button();
	private Button bntImg2 = new Button();
	private Button bntImg3 = new Button();

	private Image image1 = new Image();
	private Image image2 = new Image();
	private Image image3 = new Image();
	private Grid imageGrid = GridFactory.newGridLayout();


	//private static final String SAVE_PATH = "D:\\image\\";
	//private static final String SAVE_PATH = "/home/idempiere/idempiere.gtk.linux.x86_64/idempiere-server/image/";
	private String SAVE_PATH = "";
	private String AKSES_PATH = "";

	private MImage m_mImage = null;	
	private ConfirmPanel confirmPanel = new ConfirmPanel(true,false,true,false,false,false);
	private int WindowNo;

	
	public static final String SELECT_DESELECT_ALL = "SelectAll";
	
	
	public WImageUpload(GridTab tab) 
	{
		super(tab);
		AD_Client_ID = (Integer)tab.getValue("AD_Client_ID");
		log.info(getGridTab().toString());
		
		window = new Window();
		
		WindowNo = getGridTab().getWindowNo();

		try
		{
			if (!dynInit())
				return;
			zkInit();
			//setInitOK(true);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			setInitOK(false);
			throw new AdempiereException(e.getMessage());
		}
		AEnv.showWindow(window);
	}

	protected void zkInit() throws Exception{
		
		Borderlayout contentPane = new Borderlayout();
		window.appendChild(contentPane);
		window.setAttribute(Window.MODE_KEY, Window.MODE_HIGHLIGHTED);
		window.setSizable(true);
		
		window.setWidth("475px");
		window.setHeight("300px");
		window.setSizable(true);
		window.setBorder("normal");
		
		North north = new North();
		contentPane.appendChild(north);
		//north.appendChild(parameterPanel);		
		//fileLabel.setValue(Msg.getMsg(Env.getCtx(), "SelectFile") + ": ");
		//fileButton.setLabel("Gambar");
		//LayoutUtils.addSclass("txt-btn", fileButton);	
		//Hbox hbox = new Hbox();
		//hbox.setAlign("center");
		//hbox.setPack("start");
		//hbox.appendChild(fileLabel);
		//hbox.appendChild(fileButton);	
		//parameterPanel.setStyle("padding: 5px");
		//parameterPanel.appendChild(hbox);
		//Separator separator = new Separator();
		//separator.setOrient("horizontal");
		//separator.setBar(true);
		//parameterPanel.appendChild(separator);
		
		Center center = new Center();
        contentPane.appendChild(center);
        
       
        center.appendChild(imageGrid);
        imageGrid.setWidth("100%");
        imageGrid.setStyle("Height:100%;");

		Rows rows = null;
		Row row = null;

		rows = imageGrid.newRows();
        
        Hbox hboxImage1 = new Hbox();
        hboxImage1.setAlign("center");
        hboxImage1.setOrient("vertical");
        hboxImage1.setStyle("border: 1px solid #C0C0C0; border-radius:5px;margin:2px");
        bntImg1.setLabel("Upload");
        
		Hbox hboxImage2 = new Hbox();
		hboxImage2.setAlign("center");
        hboxImage2.setOrient("vertical");
        hboxImage2.setStyle("border: 1px solid #C0C0C0; border-radius:5px;margin:2px");
        bntImg2.setLabel("Upload");
        
		Hbox hboxImage3 = new Hbox();
		hboxImage3.setAlign("center");
        hboxImage3.setOrient("vertical");
        hboxImage3.setStyle("border: 1px solid #C0C0C0; border-radius:5px; margin:2px");
        bntImg3.setLabel("Upload");

		image1.setHflex("true");
		image1.setVflex("true");
		image1.setWidth("150px");
		image1.setHeight("170px");
		
		image2.setHflex("true");
		image2.setVflex("true");
		image2.setWidth("150px");
		image2.setHeight("170px");
		
		image3.setHflex("true");
		image3.setVflex("true");
		image3.setWidth("150px");
		image3.setHeight("170px");
		
		row = rows.newRow();
		row.appendCellChild(hboxImage1,1);
		row.appendCellChild(hboxImage2,1);
		row.appendCellChild(hboxImage3,1);
		
		hboxImage1.appendChild(image1);
		hboxImage2.appendChild(image2);
		hboxImage3.appendChild(image3);
		
        hboxImage1.appendChild(bntImg1);
        hboxImage2.appendChild(bntImg2);
        hboxImage3.appendChild(bntImg3);
        
		South south = new South();
		contentPane.appendChild(south);
		Panel southPanel = new Panel();
		south.appendChild(southPanel);
		
        Separator separator = new Separator();
		separator.setOrient("horizontal");
		separator.setBar(true);
		southPanel.appendChild(separator);
		southPanel.appendChild(confirmPanel);		
	
		contentPane.setWidth("100%");
		contentPane.setHeight("100%");
		confirmPanel.addActionListener(this);
				
		bntImg1.setUpload(AdempiereWebUI.getUploadSetting());
		bntImg1.addEventListener(Events.ON_UPLOAD, this);
		bntImg2.setUpload(AdempiereWebUI.getUploadSetting());
		bntImg2.addEventListener(Events.ON_UPLOAD, this);
		bntImg3.setUpload(AdempiereWebUI.getUploadSetting());
		bntImg3.addEventListener(Events.ON_UPLOAD, this);
		
		confirmPanel.addActionListener(Events.ON_CLICK, this);
		window.addEventListener(Events.ON_UPLOAD, this);
	    	
	}
	
	@Override
	public void onEvent(Event e) throws Exception {
		
		if (e instanceof UploadEvent) 
		{
			UploadEvent ue = (UploadEvent) e;
			
			System.out.println(e.getTarget().toString());
			if(e.getTarget().equals(bntImg1)){
				processUploadMedia(ue.getMedia(),bntImg1);
			}else if(e.getTarget().equals(bntImg2)){
				processUploadMedia(ue.getMedia(),bntImg2);
			}else if(e.getTarget().equals(bntImg3)){
				processUploadMedia(ue.getMedia(),bntImg3);

			}
			

		}
		
		else if (e.getTarget().getId().equals(ConfirmPanel.A_OK))
		{
			try{	
				//InputStream imageStream =image.getContent().getStreamData();
				ArrayList<Image> listImg = new ArrayList<Image>();

				if(image1.getContent() != null){
					listImg.add(image1);
				}
				if(image2.getContent() != null){
					listImg.add(image2);

				}
				if(image3.getContent() != null){
					listImg.add(image3);

				}

				for (int i = 0 ; i < listImg.size() ; i++){
					saveFile(listImg.get(i));
				}
				
				
				window.dispose();
				
			}
			catch (Exception ex){
				FDialog.error(WindowNo, null, "Error", "Process Upload Gagal"+ex);
			}
			
			FDialog.info(WindowNo, null, "","Gambar Baru Berhasil Ditambahkan","Info");

			
		}
		//  Cancel
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			window.onClose();
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_RESET))
		{
			AImage img = null;
			image1.setContent(img);
			image2.setContent(img);
			image3.setContent(img);
		}
		
		window.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				// TODO 
			}
		});
		
		
	}
	
	
	private void processUploadMedia(Media imageFile, Button btn) {
		if (imageFile == null)
			return;
	
		String fileName = imageFile.getName();
		
		//  See if we can load & display it
		try
		{
			InputStream is = imageFile.getStreamData();
			AImage aImage = new AImage(fileName, is);
			
			if(btn.equals(bntImg1)){
				image1.setContent(aImage);
			}else if(btn.equals(bntImg2)){
				image2.setContent(aImage);
			}else if(btn.equals(bntImg3)){
				image3.setContent(aImage);
			}

			is.close();
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "load image", e);
			return;
		}

		window.invalidate();
		AEnv.showCenterScreen(window);

	}
	
	
	private void saveFile(Image imgSource) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		MClient client = new MClient(null, AD_Client_ID, null);
		
		SAVE_PATH = ParameterSupport.getParam(AD_Client_ID, "Value", "SAVE_PATH_IMG");
		AKSES_PATH =ParameterSupport.getParam(AD_Client_ID, "Value", "AKSES_PATH_IMG");
		
		try {
			
			InputStream fin =imgSource.getContent().getStreamData();
			in = new BufferedInputStream(fin);
			
			File baseDir = new File(SAVE_PATH);
			
			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}
	
			File file = new File(SAVE_PATH +client.getValue()+imgSource.getContent().getName());

			
			OutputStream fout = new FileOutputStream(file);
			out = new BufferedOutputStream(fout);
			byte buffer[] = new byte[1024];
			int ch = in.read(buffer);
			while (ch != -1) {
				out.write(buffer, 0, ch);
				ch = in.read(buffer);
			}		
			
			
			int M_Product_ID = (int) getGridTab().getValue("M_Product_ID");
			MProduct prod = new MProduct(Env.getCtx(), M_Product_ID, null);
			
			StringBuilder SQLImage = new StringBuilder();
			SQLImage.append("SELECT Gambar_ID ");
			SQLImage.append("FROM M_Product ");
			SQLImage.append("WHERE AD_Client_ID = ? ");
			SQLImage.append("AND M_Product_ID = ? ");
			
			
			Integer AD_Image_ID = DB.getSQLValueEx(prod.get_TrxName(), SQLImage.toString(), new Object[]{AD_Client_ID,M_Product_ID}); 
			if(AD_Image_ID == null ){
				AD_Image_ID = 0;
			}
			
			if(AD_Image_ID > 0){
				
				m_mImage = new MImage(prod.getCtx(), AD_Image_ID, prod.get_TrxName());

			}
						
			if (AD_Image_ID == 0){
				m_mImage = new MImage(prod.getCtx(), AD_Image_ID, prod.get_TrxName());
			}
			
			m_mImage.setName(imgSource.getContent().getName());
			m_mImage.setImageURL(AKSES_PATH +client.getValue()+imgSource.getContent().getName());
			m_mImage.setBinaryData(null);
			m_mImage.saveEx();
			
			if(imgSource.equals(image1)){
				prod.setImageURL(m_mImage.getImageURL());
			}else if(imgSource.equals(image2)){
				prod.set_ValueNoCheck("ImageURL2",m_mImage.getImageURL());
			}else if(imgSource.equals(image3)){
				prod.set_ValueNoCheck("ImageURL3",m_mImage.getImageURL());
			}
			//prod.set_ValueNoCheck("Gambar_ID", m_mImage.getAD_Image_ID());
			prod.saveEx();		
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null) 
					out.close();	
				
				if (in != null)
					in.close();
				
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}
	
}  
