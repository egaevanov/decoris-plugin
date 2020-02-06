/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.decoris.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for i_presales_tmp
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_i_presales_tmp extends PO implements I_i_presales_tmp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180109L;

    /** Standard Constructor */
    public X_i_presales_tmp (Properties ctx, int i_presales_tmp_ID, String trxName)
    {
      super (ctx, i_presales_tmp_ID, trxName);
      /** if (i_presales_tmp_ID == 0)
        {
			setC_Decoris_PreSales_ID (0);
			setLineNo (0);
        } */
    }

    /** Load Constructor */
    public X_i_presales_tmp (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_i_presales_tmp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set alamatlain.
		@param alamatlain alamatlain	  */
	public void setalamatlain (String alamatlain)
	{
		set_Value (COLUMNNAME_alamatlain, alamatlain);
	}

	/** Get alamatlain.
		@return alamatlain	  */
	public String getalamatlain () 
	{
		return (String)get_Value(COLUMNNAME_alamatlain);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Decoris_PreSales getC_Decoris_PreSales() throws RuntimeException
    {
		return (I_C_Decoris_PreSales)MTable.get(getCtx(), I_C_Decoris_PreSales.Table_Name)
			.getPO(getC_Decoris_PreSales_ID(), get_TrxName());	}

	/** Set Decoris PreSales.
		@param C_Decoris_PreSales_ID Decoris PreSales	  */
	public void setC_Decoris_PreSales_ID (int C_Decoris_PreSales_ID)
	{
		if (C_Decoris_PreSales_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSales_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSales_ID, Integer.valueOf(C_Decoris_PreSales_ID));
	}

	/** Get Decoris PreSales.
		@return Decoris PreSales	  */
	public int getC_Decoris_PreSales_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreSales_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_ValueNoCheck (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Error Msg.
		@param ErrorMsg Error Msg	  */
	public void setErrorMsg (String ErrorMsg)
	{
		set_Value (COLUMNNAME_ErrorMsg, ErrorMsg);
	}

	/** Get Error Msg.
		@return Error Msg	  */
	public String getErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_ErrorMsg);
	}

	/** Error = E */
	public static final String I_STATUS_Error = "E";
	/** Imported = I */
	public static final String I_STATUS_Imported = "I";
	/** Set I_Status.
		@param I_Status I_Status	  */
	public void setI_Status (String I_Status)
	{

		set_Value (COLUMNNAME_I_Status, I_Status);
	}

	/** Get I_Status.
		@return I_Status	  */
	public String getI_Status () 
	{
		return (String)get_Value(COLUMNNAME_I_Status);
	}

	/** Set Pickup.
		@param IsPickup Pickup	  */
	public void setIsPickup (boolean IsPickup)
	{
		set_Value (COLUMNNAME_IsPickup, Boolean.valueOf(IsPickup));
	}

	/** Get Pickup.
		@return Pickup	  */
	public boolean isPickup () 
	{
		Object oo = get_Value(COLUMNNAME_IsPickup);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set jumlahpembayaranbank.
		@param jumlahpembayaranbank jumlahpembayaranbank	  */
	public void setjumlahpembayaranbank (BigDecimal jumlahpembayaranbank)
	{
		set_Value (COLUMNNAME_jumlahpembayaranbank, jumlahpembayaranbank);
	}

	/** Get jumlahpembayaranbank.
		@return jumlahpembayaranbank	  */
	public BigDecimal getjumlahpembayaranbank () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_jumlahpembayaranbank);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set jumlahpembayaranhutang.
		@param jumlahpembayaranhutang jumlahpembayaranhutang	  */
	public void setjumlahpembayaranhutang (BigDecimal jumlahpembayaranhutang)
	{
		set_Value (COLUMNNAME_jumlahpembayaranhutang, jumlahpembayaranhutang);
	}

	/** Get jumlahpembayaranhutang.
		@return jumlahpembayaranhutang	  */
	public BigDecimal getjumlahpembayaranhutang () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_jumlahpembayaranhutang);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set jumlahpembayaranleasing.
		@param jumlahpembayaranleasing jumlahpembayaranleasing	  */
	public void setjumlahpembayaranleasing (BigDecimal jumlahpembayaranleasing)
	{
		set_Value (COLUMNNAME_jumlahpembayaranleasing, jumlahpembayaranleasing);
	}

	/** Get jumlahpembayaranleasing.
		@return jumlahpembayaranleasing	  */
	public BigDecimal getjumlahpembayaranleasing () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_jumlahpembayaranleasing);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set jumlahpembayarantunai.
		@param jumlahpembayarantunai jumlahpembayarantunai	  */
	public void setjumlahpembayarantunai (BigDecimal jumlahpembayarantunai)
	{
		set_Value (COLUMNNAME_jumlahpembayarantunai, jumlahpembayarantunai);
	}

	/** Get jumlahpembayarantunai.
		@return jumlahpembayarantunai	  */
	public BigDecimal getjumlahpembayarantunai () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_jumlahpembayarantunai);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** leaseprovider AD_Reference_ID=330030 */
	public static final int LEASEPROVIDER_AD_Reference_ID=330030;
	/** Spektra = Spektra */
	public static final String LEASEPROVIDER_Spektra = "Spektra";
	/** Lain-lain = Lain-lain */
	public static final String LEASEPROVIDER_Lain_Lain = "Lain-lain";
	/** Set leaseprovider.
		@param leaseprovider leaseprovider	  */
	public void setleaseprovider (String leaseprovider)
	{

		set_Value (COLUMNNAME_leaseprovider, leaseprovider);
	}

	/** Get leaseprovider.
		@return leaseprovider	  */
	public String getleaseprovider () 
	{
		return (String)get_Value(COLUMNNAME_leaseprovider);
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set m_locator_id_dtl.
		@param m_locator_id_dtl m_locator_id_dtl	  */
	public void setm_locator_id_dtl (int m_locator_id_dtl)
	{
		set_Value (COLUMNNAME_m_locator_id_dtl, Integer.valueOf(m_locator_id_dtl));
	}

	/** Get m_locator_id_dtl.
		@return m_locator_id_dtl	  */
	public int getm_locator_id_dtl () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_m_locator_id_dtl);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
			.getPO(getM_PriceList_ID(), get_TrxName());	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price.
		@param PriceEntered 
		Price Entered - the price based on the selected/base UoM
	  */
	public void setPriceEntered (BigDecimal PriceEntered)
	{
		set_ValueNoCheck (COLUMNNAME_PriceEntered, PriceEntered);
	}

	/** Get Price.
		@return Price Entered - the price based on the selected/base UoM
	  */
	public BigDecimal getPriceEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSalesRep_ID(), get_TrxName());	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_SalesRep_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_ValueNoCheck (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set total.
		@param total total	  */
	public void settotal (BigDecimal total)
	{
		set_ValueNoCheck (COLUMNNAME_total, total);
	}

	/** Get total.
		@return total	  */
	public BigDecimal gettotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_total);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set totalbayar.
		@param totalbayar totalbayar	  */
	public void settotalbayar (BigDecimal totalbayar)
	{
		set_Value (COLUMNNAME_totalbayar, totalbayar);
	}

	/** Get totalbayar.
		@return totalbayar	  */
	public BigDecimal gettotalbayar () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_totalbayar);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set totalbelanja.
		@param totalbelanja totalbelanja	  */
	public void settotalbelanja (BigDecimal totalbelanja)
	{
		set_ValueNoCheck (COLUMNNAME_totalbelanja, totalbelanja);
	}

	/** Get totalbelanja.
		@return totalbelanja	  */
	public BigDecimal gettotalbelanja () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_totalbelanja);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set totaldiskon.
		@param totaldiskon totaldiskon	  */
	public void settotaldiskon (BigDecimal totaldiskon)
	{
		set_Value (COLUMNNAME_totaldiskon, totaldiskon);
	}

	/** Get totaldiskon.
		@return totaldiskon	  */
	public BigDecimal gettotaldiskon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_totaldiskon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}