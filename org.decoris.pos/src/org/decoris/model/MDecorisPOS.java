package org.decoris.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

/**
 * 
 * @author Tegar N
 *
 */

public class MDecorisPOS extends X_C_DecorisPOS{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected X_C_DecorisPOSLine[]	m_lines;

	
	public MDecorisPOS(Properties ctx, int C_DecorisPOS_ID, String trxName) {
		super(ctx, C_DecorisPOS_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MDecorisPOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 	Get Invoice Lines of Invoice
	 * 	@param whereClause starting with AND
	 * 	@return lines
	 */
	private X_C_DecorisPOSLine[] getLines (String whereClause)
	{
		String whereClauseFinal = "C_DecorisPOS_ID=? ";
		if (whereClause != null)
			whereClauseFinal += whereClause;
		List<X_C_DecorisPOSLine> list = new Query(getCtx(), I_C_DecorisPOSLine.Table_Name, whereClauseFinal, get_TrxName())
										.setParameters(getC_DecorisPOS_ID())
										.setOrderBy(I_C_DecorisPOSLine.COLUMNNAME_Line)
										.list();
		return list.toArray(new X_C_DecorisPOSLine[list.size()]);
	}	//	getLines

	/**
	 * 	Get Invoice Lines
	 * 	@param requery
	 * 	@return lines
	 */
	public X_C_DecorisPOSLine[] getLines (boolean requery)
	{
		if (m_lines == null || m_lines.length == 0 || requery)
			m_lines = getLines(null);
		set_TrxName(m_lines, get_TrxName());
		return m_lines;
	}	//	getLines

	public X_C_DecorisPOSLine[] getLines()
	{
		return getLines(false);
	}	//	getLines

	
	public int copyData (int C_DecorisPOS_ID_To, boolean IsPenjualan,boolean isManualDoc, Timestamp dateOrdered){
		
		MDecorisPOS newDec = new MDecorisPOS(getCtx(), C_DecorisPOS_ID_To, get_TrxName());
		int C_DecorisPOS_ID = 0;
		if (newDec != null){
		
			newDec.setClientOrg(getAD_Client_ID(), getAD_Org_ID());
			newDec.setC_BPartner_ID(getC_BPartner_ID());
			newDec.setC_DocType_ID(getC_DocType_ID());
			newDec.setC_PaymentTerm_ID(getC_PaymentTerm_ID());
			newDec.setCreatedByPOS_ID(getCreatedByPOS_ID());
			newDec.setDateOrdered(dateOrdered);
			newDec.setDeliveryRule(getDeliveryRule());
			newDec.setDescription(getDescription());
			newDec.setDiscountAmt(getDiscountAmt().negate());
			if(isManualDoc){
				newDec.setDocumentNo(getDocumentNo()+"^^");
				newDec.set_CustomColumn("IsManualDocumentNo", isManualDoc);

			}
			newDec.setdpp(getdpp().negate());
			newDec.setGrandTotal(getGrandTotal().negate());
			newDec.setIsPickup(isPickup());
			newDec.setM_PriceList_ID(getM_PriceList_ID());
			newDec.setM_Warehouse_ID(getM_Warehouse_ID());
			newDec.setPaymentRule(getPaymentRule());
			newDec.setPayType1(getPayType1());
			newDec.setPayType2(getPayType2());
			newDec.setPembayaran1(getPembayaran1().negate());
			newDec.setPembayaran2(getPembayaran2().negate());
			newDec.setSalesRep_ID(getSalesRep_ID());
			newDec.setTotalLines(getTotalLines());
			newDec.setTaxAmt(getTaxAmt().negate());
			newDec.setProcessed(true);
			newDec.setIsTutupKas(isTutupKas());
			newDec.setDocumentNoTutupKas(getDocumentNoTutupKas()); 
			newDec.setC_Payment_ID(getC_Payment_ID());
			newDec.setDateTrx(getDateTrx());
			newDec.setIsSOTrx(isSOTrx());
			newDec.setIsReceipt(isReceipt());
			newDec.setC_BPartner_Location_ID(getC_BPartner_Location_ID());
			newDec.setseqTutupKas(getseqTutupKas());
			newDec.setDeliveryViaRule(getDeliveryViaRule());;
			newDec.setIsPembatalan(true);
			newDec.setIsPenjualan(IsPenjualan);
			newDec.setC_Invoice_ID(getC_Invoice_ID());
			newDec.setIsPembayaran(isPembayaran());
			newDec.saveEx();
			
			
			
			X_C_DecorisPOSLine[] lines = getLines();
			for(X_C_DecorisPOSLine line :lines){
			
			X_C_DecorisPOSLine decLineNew = new X_C_DecorisPOSLine(getCtx(), 0, null);
			decLineNew.setClientOrg(line.getAD_Client_ID(), line.getAD_Org_ID());
			//decLineNew.setC_OrderLine_ID(line.getC_OrderLine_ID());
			decLineNew.setC_Tax_ID(line.getC_Tax_ID());
			decLineNew.setC_UOM_ID(line.getC_UOM_ID());
			decLineNew.setLineNetAmt(line.getLineNetAmt().negate());
			decLineNew.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
			decLineNew.setM_Locator_ID(line.getM_Locator_ID());
			decLineNew.setM_Product_ID(line.getM_Product_ID());
			decLineNew.setPriceEntered(line.getPriceEntered().negate());
			decLineNew.setPriceList(line.getPriceEntered().negate());
			decLineNew.setQtyOrdered(line.getQtyOrdered().negate());
			decLineNew.setC_DecorisPOS_ID(newDec.getC_DecorisPOS_ID());
			//decLineNew.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
			decLineNew.setC_Charge_ID(line.getC_Charge_ID());
			decLineNew.setLine(line.getLine());
			decLineNew.saveEx();
		}
	
	}
		C_DecorisPOS_ID = newDec.getC_DecorisPOS_ID();
		return C_DecorisPOS_ID;

	}	
}
