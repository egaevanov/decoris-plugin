package org.decoris.webservice.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.decoris.model.X_C_Decoris_PreOrder;
import org.decoris.model.X_C_Decoris_PreOrderLine;
import org.decoris.webservice.FIF_MainControl;
import org.decoris.webservice.model.X_M_Fifapps_Objcodes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


/**
 * 
 * @author Tegar N
 *
 */
public class DecorisSubmitPreOrder extends SvrProcess{

	//private String p_orderIdOri = "";
	//private String p_seqNo = "";
	private int p_preorder_id = 0;
	
	
	@Override
	protected void prepare() {

		
		p_preorder_id = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {

		final String requestMethod = "POST";
	    String ret = "";
	    StringBuilder parameter = new StringBuilder();
	    
	    X_C_Decoris_PreOrder preOrd = new X_C_Decoris_PreOrder(getCtx(), p_preorder_id, null);
	    //X_M_Fifapps_Supplier Supplier = new X_M_Fifapps_Supplier(getCtx(), preOrd.getM_Fifapps_Supplier_ID(), null);
	    
	    StringBuilder sqlPreOrdLine = new StringBuilder();
	    sqlPreOrdLine.append("SELECT C_Decoris_PreOrderLine_ID ");
	    sqlPreOrdLine.append("FROM C_Decoris_PreOrderLine ");
	    sqlPreOrdLine.append("WHERE AD_Client_ID = ? ");
	    sqlPreOrdLine.append("AND C_Decoris_PreOrder_ID = ? ");
	    
	    
	    StringBuilder SQLCountLine = new StringBuilder();
	    SQLCountLine.append("SELECT COUNT(C_Decoris_PreOrderLine_ID) ");
	    SQLCountLine.append("FROM C_Decoris_PreOrderLine ");
	    SQLCountLine.append("WHERE AD_Client_ID = ? ");
	    SQLCountLine.append("AND C_Decoris_PreOrder_ID = ?");
	    
	    
	    Integer ct = DB.getSQLValueEx(null, SQLCountLine.toString(), new Object[]{preOrd.getAD_Client_ID(),preOrd.getC_Decoris_PreOrder_ID()});
		
	    if(ct == 0){
	    	return "@Error@ Tidak Dapat Submit Preorder, PreOrder Detail Tidak Ditemukan";
	    }
	    
	    Double divider = Double.valueOf(ct.toString());
	    BigDecimal Bdiv = BigDecimal.valueOf(divider);
	    BigDecimal dpUnit = preOrd.getdpamt().divide(Bdiv,0,RoundingMode.HALF_UP);

	    String ObjType = "";
	    String ObjTypeFIF = "";
	    
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    Integer count = 0;
		Gson gson = new Gson();

	    try {
			pstmt = DB.prepareStatement(sqlPreOrdLine.toString(), null);
			pstmt.setInt(1, preOrd.getAD_Client_ID());
			pstmt.setInt(2, preOrd.getC_Decoris_PreOrder_ID());
			rs = pstmt.executeQuery();
		    JsonArray jsonArr = new JsonArray();
		    
			while (rs.next()) {
				count++;
			    X_C_Decoris_PreOrderLine preOrdLine = new X_C_Decoris_PreOrderLine(getCtx(), rs.getInt(1), null);
			    X_M_Fifapps_Objcodes Objcodes = new X_M_Fifapps_Objcodes(getCtx(), preOrdLine.getM_Fifapps_Objcodes_ID(), null);

			    JsonObject jsonObj = new JsonObject();
			    if(preOrdLine.getLineNo() == 10){
			    	
			    	ObjType = Objcodes.getobjcode();
			    	ObjTypeFIF = Objcodes.getobjcode();
			    	
			    }
			    
				jsonObj.addProperty("orderId","");			    
				jsonObj.addProperty("seqNo","");
				jsonObj.addProperty("suppCode","");
				jsonObj.addProperty("objCode",Objcodes.getobjcode());
				jsonObj.addProperty("objDesc","");
				jsonObj.addProperty("objBrand","");
				jsonObj.addProperty("objModel","");
				jsonObj.addProperty("objType","");
				jsonObj.addProperty("objKind","");
				jsonObj.addProperty("objSize","");
				jsonObj.addProperty("objColour","");
				jsonObj.addProperty("objTahun","");
				jsonObj.addProperty("objPromoId","");
				jsonObj.addProperty("objQty",preOrdLine.getQtyEntered().toString());
				jsonObj.addProperty("objPrice",preOrdLine.getPriceEntered().toString());
				jsonObj.addProperty("objTotPrice",preOrdLine.getLineNetAmt().toString());
				jsonObj.addProperty("objUnitDp",dpUnit.toString());
				jsonObj.addProperty("objAdm",preOrd.getadminfee().toString());
				jsonObj.addProperty("insCoy","");
				jsonObj.addProperty("insProduct","");
				jsonObj.addProperty("createdBy","");
				jsonObj.addProperty("createdDate","");
			    
				jsonArr.add(jsonObj);
			}
			
			parameter.append(gson.toJson(jsonArr));

		} catch (SQLException e) {
			log.log(Level.SEVERE, sqlPreOrdLine.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	    		
		ret = FIF_MainControl.submitDataRun(getCtx(),p_preorder_id, requestMethod,parameter,ObjType,ObjTypeFIF);
		
		return ret;
	}

	
}
