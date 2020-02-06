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
package org.decoris.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for i_presales_tmp
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_i_presales_tmp 
{

    /** TableName=i_presales_tmp */
    public static final String Table_Name = "i_presales_tmp";

    /** AD_Table_ID=1000126 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name alamatlain */
    public static final String COLUMNNAME_alamatlain = "alamatlain";

	/** Set alamatlain	  */
	public void setalamatlain (String alamatlain);

	/** Get alamatlain	  */
	public String getalamatlain();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Decoris_PreSales_ID */
    public static final String COLUMNNAME_C_Decoris_PreSales_ID = "C_Decoris_PreSales_ID";

	/** Set Decoris PreSales	  */
	public void setC_Decoris_PreSales_ID (int C_Decoris_PreSales_ID);

	/** Get Decoris PreSales	  */
	public int getC_Decoris_PreSales_ID();

	public I_C_Decoris_PreSales getC_Decoris_PreSales() throws RuntimeException;

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name ErrorMsg */
    public static final String COLUMNNAME_ErrorMsg = "ErrorMsg";

	/** Set Error Msg	  */
	public void setErrorMsg (String ErrorMsg);

	/** Get Error Msg	  */
	public String getErrorMsg();

    /** Column name I_Status */
    public static final String COLUMNNAME_I_Status = "I_Status";

	/** Set I_Status	  */
	public void setI_Status (String I_Status);

	/** Get I_Status	  */
	public String getI_Status();

    /** Column name IsPickup */
    public static final String COLUMNNAME_IsPickup = "IsPickup";

	/** Set Pickup	  */
	public void setIsPickup (boolean IsPickup);

	/** Get Pickup	  */
	public boolean isPickup();

    /** Column name jumlahpembayaranbank */
    public static final String COLUMNNAME_jumlahpembayaranbank = "jumlahpembayaranbank";

	/** Set jumlahpembayaranbank	  */
	public void setjumlahpembayaranbank (BigDecimal jumlahpembayaranbank);

	/** Get jumlahpembayaranbank	  */
	public BigDecimal getjumlahpembayaranbank();

    /** Column name jumlahpembayaranhutang */
    public static final String COLUMNNAME_jumlahpembayaranhutang = "jumlahpembayaranhutang";

	/** Set jumlahpembayaranhutang	  */
	public void setjumlahpembayaranhutang (BigDecimal jumlahpembayaranhutang);

	/** Get jumlahpembayaranhutang	  */
	public BigDecimal getjumlahpembayaranhutang();

    /** Column name jumlahpembayaranleasing */
    public static final String COLUMNNAME_jumlahpembayaranleasing = "jumlahpembayaranleasing";

	/** Set jumlahpembayaranleasing	  */
	public void setjumlahpembayaranleasing (BigDecimal jumlahpembayaranleasing);

	/** Get jumlahpembayaranleasing	  */
	public BigDecimal getjumlahpembayaranleasing();

    /** Column name jumlahpembayarantunai */
    public static final String COLUMNNAME_jumlahpembayarantunai = "jumlahpembayarantunai";

	/** Set jumlahpembayarantunai	  */
	public void setjumlahpembayarantunai (BigDecimal jumlahpembayarantunai);

	/** Get jumlahpembayarantunai	  */
	public BigDecimal getjumlahpembayarantunai();

    /** Column name leaseprovider */
    public static final String COLUMNNAME_leaseprovider = "leaseprovider";

	/** Set leaseprovider	  */
	public void setleaseprovider (String leaseprovider);

	/** Get leaseprovider	  */
	public String getleaseprovider();

    /** Column name LineNo */
    public static final String COLUMNNAME_LineNo = "LineNo";

	/** Set Line.
	  * Line No
	  */
	public void setLineNo (int LineNo);

	/** Get Line.
	  * Line No
	  */
	public int getLineNo();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name m_locator_id_dtl */
    public static final String COLUMNNAME_m_locator_id_dtl = "m_locator_id_dtl";

	/** Set m_locator_id_dtl	  */
	public void setm_locator_id_dtl (int m_locator_id_dtl);

	/** Get m_locator_id_dtl	  */
	public int getm_locator_id_dtl();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name PriceEntered */
    public static final String COLUMNNAME_PriceEntered = "PriceEntered";

	/** Set Price.
	  * Price Entered - the price based on the selected/base UoM
	  */
	public void setPriceEntered (BigDecimal PriceEntered);

	/** Get Price.
	  * Price Entered - the price based on the selected/base UoM
	  */
	public BigDecimal getPriceEntered();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException;

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();

    /** Column name total */
    public static final String COLUMNNAME_total = "total";

	/** Set total	  */
	public void settotal (BigDecimal total);

	/** Get total	  */
	public BigDecimal gettotal();

    /** Column name totalbayar */
    public static final String COLUMNNAME_totalbayar = "totalbayar";

	/** Set totalbayar	  */
	public void settotalbayar (BigDecimal totalbayar);

	/** Get totalbayar	  */
	public BigDecimal gettotalbayar();

    /** Column name totalbelanja */
    public static final String COLUMNNAME_totalbelanja = "totalbelanja";

	/** Set totalbelanja	  */
	public void settotalbelanja (BigDecimal totalbelanja);

	/** Get totalbelanja	  */
	public BigDecimal gettotalbelanja();

    /** Column name totaldiskon */
    public static final String COLUMNNAME_totaldiskon = "totaldiskon";

	/** Set totaldiskon	  */
	public void settotaldiskon (BigDecimal totaldiskon);

	/** Get totaldiskon	  */
	public BigDecimal gettotaldiskon();
}
