package org.decoris.pos.webui.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
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
public class PosCustomerRMA {

	public CLogger log = CLogger.getCLogger(PosCustomerRMA.class);
	private Properties ctx = Env.getCtx();
	protected DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);
	
	protected Vector<Vector<Object>> getTrxData(ArrayList<Integer> detailList,IMiniTable MiniTable) {		
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		
		 if (detailList.size() > 0){
		
			for (int i = 0; i < detailList.size(); i++){
				
				Vector<Object> line = new Vector<Object>(1);

				MInOutLine detailShip = new MInOutLine(ctx, detailList.get(i), null);
				MProduct product = new MProduct(ctx, detailShip.getM_Product_ID(), null);
				MLocator loc = new MLocator(ctx, detailShip.getM_Locator_ID(), null);
				MAttributeSetInstance imei = new MAttributeSetInstance(ctx, detailShip.getM_AttributeSetInstance_ID(), null);
								
				Integer lineNo = detailShip.getLine();
				KeyNamePair kpProd = new KeyNamePair(product.getM_Product_ID(),product.getName());
				KeyNamePair kpLoc = new KeyNamePair(loc.getM_Locator_ID(),loc.getValue());
				KeyNamePair kpLine = new KeyNamePair(detailShip.getM_InOutLine_ID(),lineNo.toString());
				
				String IMEI = imei.getSerNo();
				BigDecimal qty = detailShip.getQtyEntered();
				
				MOrderLine ordLine = new MOrderLine(ctx, detailShip.getC_OrderLine_ID(), null);
				BigDecimal price = ordLine.getPriceEntered();
				BigDecimal total = ordLine.getLineNetAmt();
				
				line.add(new Boolean(false));
				line.add(kpLine);
				line.add(kpProd);
				line.add(kpLoc);
				line.add(IMEI);
				line.add(price);       
				line.add(qty);
				line.add(total);
				line.add(Env.ZERO);
				data.add(line);
				
			}
		}
		
		return data;
	}

	protected ArrayList<KeyNamePair> loadDocType() {
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		
		StringBuilder SQLDocType = new StringBuilder();
		SQLDocType.append("SELECT C_DocType_ID, Name ");
		SQLDocType.append("FROM C_DocType ");
		SQLDocType.append("WHERE DocBaseType = '" + MDocType.DOCBASETYPE_MaterialReceipt+ "' ");
		SQLDocType.append(" AND IsSOTrx = 'Y' ");
		SQLDocType.append(" AND IsActive = 'Y' ");
		SQLDocType.append(" AND AD_Client_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(SQLDocType.toString(), null);
			pstmt.setInt(1, AD_Client_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, SQLDocType.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}
	
	
}
