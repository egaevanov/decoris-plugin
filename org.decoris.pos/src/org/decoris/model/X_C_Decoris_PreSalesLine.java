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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for C_Decoris_PreSalesLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_PreSalesLine extends PO implements I_C_Decoris_PreSalesLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180711L;

    /** Standard Constructor */
    public X_C_Decoris_PreSalesLine (Properties ctx, int C_Decoris_PreSalesLine_ID, String trxName)
    {
      super (ctx, C_Decoris_PreSalesLine_ID, trxName);
      /** if (C_Decoris_PreSalesLine_ID == 0)
        {
			setC_Decoris_PreSalesLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_PreSalesLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_PreSalesLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Decoris Presales Line.
		@param C_Decoris_PreSalesLine_ID Decoris Presales Line	  */
	public void setC_Decoris_PreSalesLine_ID (int C_Decoris_PreSalesLine_ID)
	{
		if (C_Decoris_PreSalesLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSalesLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSalesLine_ID, Integer.valueOf(C_Decoris_PreSalesLine_ID));
	}

	/** Get Decoris Presales Line.
		@return Decoris Presales Line	  */
	public int getC_Decoris_PreSalesLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreSalesLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_PreSalesLine_UU.
		@param C_Decoris_PreSalesLine_UU C_Decoris_PreSalesLine_UU	  */
	public void setC_Decoris_PreSalesLine_UU (String C_Decoris_PreSalesLine_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSalesLine_UU, C_Decoris_PreSalesLine_UU);
	}

	/** Get C_Decoris_PreSalesLine_UU.
		@return C_Decoris_PreSalesLine_UU	  */
	public String getC_Decoris_PreSalesLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_PreSalesLine_UU);
	}

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_InvoiceLine)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceLine.Table_Name)
			.getPO(getC_InvoiceLine_ID(), get_TrxName());	}

	/** Set Invoice Line.
		@param C_InvoiceLine_ID 
		Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
	{
		if (C_InvoiceLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_InvoiceLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_InvoiceLine_ID, Integer.valueOf(C_InvoiceLine_ID));
	}

	/** Get Invoice Line.
		@return Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
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

	public org.compiere.model.I_C_BPartner getCreatedByPOS() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getCreatedByPOS_ID(), get_TrxName());	}

	/** Set CreatedByPOS_ID.
		@param CreatedByPOS_ID CreatedByPOS_ID	  */
	public void setCreatedByPOS_ID (int CreatedByPOS_ID)
	{
		if (CreatedByPOS_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CreatedByPOS_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CreatedByPOS_ID, Integer.valueOf(CreatedByPOS_ID));
	}

	/** Get CreatedByPOS_ID.
		@return CreatedByPOS_ID	  */
	public int getCreatedByPOS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CreatedByPOS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set diskonline.
		@param diskonline diskonline	  */
	public void setdiskonline (BigDecimal diskonline)
	{
		set_Value (COLUMNNAME_diskonline, diskonline);
	}

	/** Get diskonline.
		@return diskonline	  */
	public BigDecimal getdiskonline () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_diskonline);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Line Amount.
		@param LineNetAmt 
		Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		set_ValueNoCheck (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Persentase Diskon.
		@param PersentaseDiskon Persentase Diskon	  */
	public void setPersentaseDiskon (int PersentaseDiskon)
	{
		set_Value (COLUMNNAME_PersentaseDiskon, Integer.valueOf(PersentaseDiskon));
	}

	/** Get Persentase Diskon.
		@return Persentase Diskon	  */
	public int getPersentaseDiskon () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PersentaseDiskon);
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

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
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
}