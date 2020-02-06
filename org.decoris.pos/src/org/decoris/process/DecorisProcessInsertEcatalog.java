package org.decoris.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;


/**
 * 
 * @author Tegar N
 *
 */
public class DecorisProcessInsertEcatalog extends SvrProcess {


	private int p_AD_Client_ID = 0;
	private int p_AD_Org_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0 ; i < para.length ;i++){
			
			String name = para[i].getParameterName();
			
			if(para[i].getParameter()==null)
				;
			else if(name.equals("AD_Client_ID"))
				p_AD_Client_ID = (int)para[i].getParameterAsInt();
			
			else if(name.equals("AD_Org_ID"))
				p_AD_Org_ID = (int)para[i].getParameterAsInt();
	
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			
		}

	}

	@Override
	protected String doIt() throws Exception {
			String msg = "";

			StringBuilder SQLPresalesTmp = new StringBuilder();
			
			SQLPresalesTmp.append("SELECT AD_Client_ID, ");
			SQLPresalesTmp.append("AD_Org_ID, ");
			SQLPresalesTmp.append("C_Decoris_Presales_ID, ");
			SQLPresalesTmp.append("C_BPartner_ID, ");
			SQLPresalesTmp.append("C_BPartner_Location_ID, ");
			SQLPresalesTmp.append("DateOrdered, ");
			SQLPresalesTmp.append("Description, ");
			SQLPresalesTmp.append("IsPickup, ");
			SQLPresalesTmp.append("M_PriceList_ID, ");
			SQLPresalesTmp.append("JumlahPembayaranBank, ");
			SQLPresalesTmp.append("JumlahPembayaranHutang, ");
			SQLPresalesTmp.append("JumlahPembayaranLeasing, ");
			SQLPresalesTmp.append("JumlahPembayaranTunai, ");
			SQLPresalesTmp.append("LeaseProvider, ");
			SQLPresalesTmp.append("M_Warehouse_ID, ");
			SQLPresalesTmp.append("M_Locator_ID, ");
			SQLPresalesTmp.append("SalesRep_ID, ");
			SQLPresalesTmp.append("TaxAmt, ");
			SQLPresalesTmp.append("Total, ");
			SQLPresalesTmp.append("TotalBayar, ");
			SQLPresalesTmp.append("TotalBelanja, ");
			SQLPresalesTmp.append("TotalDiskon, ");
			SQLPresalesTmp.append("CreatedBy, ");
			SQLPresalesTmp.append("Created, ");
			SQLPresalesTmp.append("LineNo, ");
			SQLPresalesTmp.append("M_Product_ID, ");
			SQLPresalesTmp.append("C_Tax_ID, ");
			SQLPresalesTmp.append("C_UOM_ID, ");
			SQLPresalesTmp.append("M_Locator_ID_dtl, ");
			SQLPresalesTmp.append("M_AttributeSetInstance_ID, ");
			SQLPresalesTmp.append("QtyEntered, ");
			SQLPresalesTmp.append("PriceList, ");
			SQLPresalesTmp.append("PriceEntered, ");
			SQLPresalesTmp.append("AlamatLain, ");
			SQLPresalesTmp.append("I_Status, ");
			SQLPresalesTmp.append("ErrorMsg ");

			SQLPresalesTmp.append("FROM i_presales_tmp ");
			SQLPresalesTmp.append("WHERE AD_Client_ID = ? ");
			SQLPresalesTmp.append("AND AD_Org_ID = ? ");
			SQLPresalesTmp.append("AND I_Status NOT IN ('I') ");
			SQLPresalesTmp.append("AND reference_ID  NOT IN ");
			SQLPresalesTmp.append("(SELECT reference_ID ");
			SQLPresalesTmp.append(" FROM C_Decoris_Presales ");
			SQLPresalesTmp.append(" WHERE  AD_Client_ID = ? ");
			SQLPresalesTmp.append(" AND AD_Org_ID = ? )");
	
			PreparedStatement pstmtPreTmp = null;
			ResultSet rsPreTmp = null;
				
			try {
				pstmtPreTmp = DB.prepareStatement(SQLPresalesTmp.toString(), get_TrxName());
				pstmtPreTmp.setInt(1, p_AD_Client_ID);
				pstmtPreTmp.setInt(2, p_AD_Org_ID);
				pstmtPreTmp.setInt(3, p_AD_Client_ID);
				pstmtPreTmp.setInt(4, p_AD_Org_ID);

				rsPreTmp = pstmtPreTmp.executeQuery();
				while (rsPreTmp.next()) {
								
					
					if (rsPreTmp.getInt(1) > 0){
						
					}

					if (rsPreTmp.getInt(2) > 0){
						
					}
					
					if (rsPreTmp.getInt(3) > 0){
						
					}
					
					if (rsPreTmp.getInt(4) > 0){
						
					}
					
					if (rsPreTmp.getInt(5) > 0){
						
					}
					
					if (rsPreTmp.getInt(6) > 0){
						
					}
					
					if (rsPreTmp.getInt(7) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					if (rsPreTmp.getInt(1) > 0){
						
					}
					
					
//					//Update invoice Tax Data
//					StringBuilder UpdateTax = new StringBuilder();
//					UpdateTax.append("UPDATE C_InvoiceTax ");
//					UpdateTax.append("SET TaxID = '"+ bp.getTaxID()+"', ");
//					UpdateTax.append("TaxNbr = '"+newENofaLine.getNomorPajak()+"' ");
//					UpdateTax.append("WHERE AD_Client_ID = " +p_AD_Client_ID);
//					UpdateTax.append("AND AD_Org_ID = " + p_AD_Org_ID );
//					UpdateTax.append("AND C_Invoice_ID = " + inv.getC_Invoice_ID());
//					UpdateTax.append("AND C_Tax_ID = " + C_Tax_ID);
//					DB.executeUpdate(UpdateTax.toString(), get_TrxName());				
				}

				
			} catch (SQLException e) {
				log.log(Level.SEVERE, SQLPresalesTmp.toString(), e);
				DB.rollback(true, get_TrxName());
				msg = "Error";
			} finally {
				DB.close(rsPreTmp, pstmtPreTmp);
				rsPreTmp = null;
				pstmtPreTmp = null;
			}
			
			
		return msg;
	}

	
	
}
