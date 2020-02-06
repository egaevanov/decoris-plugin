package org.decoris.pos.webui.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.minigrid.IMiniTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

/**
 * 
 * @author Tegar N
 *
 */

public class PosPelunasan {

	
	public CLogger log = CLogger.getCLogger(PosCloseCash.class);
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	Vector<Vector<Object>> dataSummary = new Vector<Vector<Object>>();
	
	
	protected Vector<Vector<Object>> getPelunasanDataLeasing(int AD_Client_ID,Timestamp DateTrx1,
			Timestamp DateTrx2,int C_Bpartner_ID,int AD_Org_ID,IMiniTable MiniTable,String LeaseProv) {
			
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		
		StringBuilder SQLPelunasan = new StringBuilder();		
		SQLPelunasan.append("SELECT cp.C_Payment_ID,cp.DocumentNo,cp.DateTrx,bp.Name,ci.description,cp.PayAmt ");
		SQLPelunasan.append("FROM C_Payment cp ");
		SQLPelunasan.append("LEFT JOIN C_BPartner bp ON bp.C_Bpartner_ID = cp.C_Bpartner_ID ");
		SQLPelunasan.append("LEFT JOIN C_POSPayment cpos ON cpos.C_Payment_ID = cp.C_Payment_ID ");
		SQLPelunasan.append("LEFT JOIN C_Invoice ci ON ci.C_Invoice_ID = cp.C_Invoice_ID ");
		SQLPelunasan.append("WHERE cp.AD_Client_ID = ? ");
		SQLPelunasan.append("AND cp.TenderType = 'L' ");
		SQLPelunasan.append("AND cp.IsAllocated = 'N' ");
		SQLPelunasan.append("AND cp.DocStatus = 'IP' ");		
		SQLPelunasan.append("AND cp.IsReceipt = 'Y' ");
		
		if (DateTrx1 != null && DateTrx2 == null){
			SQLPelunasan.append(" AND (cp.DateTrx BETWEEN '"+DateTrx1+"' AND '"+now+"') ");
		}else if (DateTrx1 != null && DateTrx2 != null){
			SQLPelunasan.append(" AND (cp.DateTrx BETWEEN '"+DateTrx1+"' AND '"+DateTrx2+"') ");
		}
		
		if(LeaseProv != null && LeaseProv != "" && !LeaseProv.isEmpty()){
			SQLPelunasan.append("AND cpos.leaseprovider = '"+LeaseProv+"'");
		}
		
		//add parameter if AD_Org_ID is not null
		if(AD_Org_ID > 0){
			SQLPelunasan.append("AND cp.AD_Org_ID = ? ");			
		}
		
		//add parameter if C_BPartner_ID is not null
		if(C_Bpartner_ID > 0){
			SQLPelunasan.append(" AND cp.C_BPartner_ID = ? ");
		}
			
		SQLPelunasan.append(" ORDER BY cp.DateTrx ASC ");

		PreparedStatement pstmtLns = null;
		ResultSet rsLns = null;
		
		try {
			pstmtLns = DB.prepareStatement(SQLPelunasan.toString(), null);
			pstmtLns.setInt(1, AD_Client_ID);
			if(AD_Org_ID > 0){
				pstmtLns.setInt(2, AD_Org_ID);
			}
			if(C_Bpartner_ID > 0 && AD_Org_ID > 0){
				pstmtLns.setInt(3, C_Bpartner_ID);
			}else if(C_Bpartner_ID > 0 && AD_Org_ID == 0){
				pstmtLns.setInt(2, C_Bpartner_ID);
			}

			rsLns = pstmtLns.executeQuery();
			while (rsLns.next()) {
				
			Vector<Object> line = new Vector<Object>(5);
			KeyNamePair payPair = new KeyNamePair(rsLns.getInt(1), rsLns.getString(2)) ;
			
				line.add(new Boolean(false));
				line.add(payPair);
				line.add(rsLns.getTimestamp(3));
				line.add(rsLns.getString(4));
				line.add(rsLns.getString(5));
				line.add(rsLns.getBigDecimal(6));	



				data.add(line);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, SQLPelunasan.toString(), e);
		} finally {
			DB.close(rsLns, pstmtLns);
			rsLns = null;
			pstmtLns = null;
		}
			
		return data;
		
	}
	
	
	protected Vector<Vector<Object>> getPelunasanDataHutang(int AD_Client_ID,Timestamp DateTrx1,
			Timestamp DateTrx2,int C_Bpartner_ID,int AD_Org_ID,boolean isDP,IMiniTable MiniTable) {
			
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		
		StringBuilder SQLPelunasan = new StringBuilder();		
		SQLPelunasan.append("SELECT co.C_Order_ID,co.Documentno,ci.C_Invoice_ID,ci.DocumentNo,ci.DateInvoiced,bp.Name,ci.grandtotal,coalesce(cpos.PayAmt,0) ");
		SQLPelunasan.append("FROM C_Order co ");
		SQLPelunasan.append("LEFT JOIN C_POSpayment cpos ON cpos.C_Order_ID= co.C_Order_ID "); 
		SQLPelunasan.append("LEFT JOIN C_Invoice ci ON co.C_Order_ID = ci.C_Order_ID ");
		SQLPelunasan.append("LEFT JOIN C_Payment cp ON ci.C_Invoice_ID = cp.C_Invoice_ID ");
		SQLPelunasan.append("LEFT JOIN C_BPartner bp ON bp.C_Bpartner_ID = co.C_Bpartner_ID ");
		SQLPelunasan.append("LEFT JOIN C_DocType cd ON co.C_DocType_ID= cd.C_DocType_ID ");
		SQLPelunasan.append("WHERE co.AD_Client_ID = ? ");
		SQLPelunasan.append("AND cd.Name IN ('Credit Order','Prepay Order','Standard Order') ");
		SQLPelunasan.append("AND co.IsSoTrx = 'Y' ");
		SQLPelunasan.append("AND co.DocStatus = 'CO' ");
		SQLPelunasan.append("AND ci.C_Invoice_ID IS NOT NULL ");
		SQLPelunasan.append("AND ci.DocStatus = 'CO' ");
		SQLPelunasan.append("AND ci.IsPaid = 'N' ");
		
		
		if(isDP){
			SQLPelunasan.append("AND cpos.C_POSPayment_ID IS NOT NULL ");
			SQLPelunasan.append("AND cpos.TenderType = 'X' ");
			SQLPelunasan.append("AND cp.C_Payment_ID IS NOT NULL ");
			SQLPelunasan.append("AND cpos.PayAmt < ci.grandtotal  ");

		}else if(!isDP){
			SQLPelunasan.append("AND cpos.C_POSPayment_ID IS NULL ");
			SQLPelunasan.append("AND cp.C_Payment_ID IS NULL ");
		}
		
		if (DateTrx1 != null && DateTrx2 == null){
			SQLPelunasan.append(" AND (ci.DateInvoiced BETWEEN '"+DateTrx1+"' AND '"+now+"') ");
		}else if (DateTrx1 != null && DateTrx2 != null){
			SQLPelunasan.append(" AND (ci.DateInvoiced BETWEEN '"+DateTrx1+"' AND '"+DateTrx2+"') ");
		}
		
		//add parameter if AD_Org_ID is not null
		if(AD_Org_ID > 0){
			SQLPelunasan.append("AND co.AD_Org_ID = ? ");			
		}
		
		//add parameter if C_BPartner_ID is not null
		if(C_Bpartner_ID > 0){
			SQLPelunasan.append(" AND co.C_BPartner_ID = ? ");
		}
			
		SQLPelunasan.append(" ORDER BY ci.DateInvoiced ASC ");

		PreparedStatement pstmtLns = null;
		ResultSet rsLns = null;
		
		try {
			pstmtLns = DB.prepareStatement(SQLPelunasan.toString(), null);
			pstmtLns.setInt(1, AD_Client_ID);
			if(AD_Org_ID > 0){
				pstmtLns.setInt(2, AD_Org_ID);
			}
			if(C_Bpartner_ID > 0 && AD_Org_ID > 0){
				pstmtLns.setInt(3, C_Bpartner_ID);
			}else if(C_Bpartner_ID > 0 && AD_Org_ID == 0){
				pstmtLns.setInt(2, C_Bpartner_ID);
			}

			rsLns = pstmtLns.executeQuery();
			while (rsLns.next()) {
				
			Vector<Object> line = new Vector<Object>(6);
			KeyNamePair OrdPair = new KeyNamePair(rsLns.getInt(1), rsLns.getString(2)) ;
			KeyNamePair InvPair = new KeyNamePair(rsLns.getInt(3), rsLns.getString(4)) ;
			
				line.add(new Boolean(false));
				line.add(OrdPair);
				line.add(InvPair);
				line.add(rsLns.getTimestamp(5));
				line.add(rsLns.getString(6));
				line.add(rsLns.getBigDecimal(7));	
				line.add(rsLns.getBigDecimal(8));	
				line.add(rsLns.getBigDecimal(7).subtract(rsLns.getBigDecimal(8)));
				line.add(rsLns.getBigDecimal(7).subtract(rsLns.getBigDecimal(8)));


				data.add(line);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, SQLPelunasan.toString(), e);
		} finally {
			DB.close(rsLns, pstmtLns);
			rsLns = null;
			pstmtLns = null;
		}
			
		return data;
		
	}
	
}
