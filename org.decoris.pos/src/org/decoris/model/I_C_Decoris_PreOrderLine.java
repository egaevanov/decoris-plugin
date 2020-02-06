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
import org.decoris.webservice.model.I_M_Fifapps_Objcodes;
import org.decoris.webservice.model.I_M_Fifapps_Supplier;

/** Generated Interface for C_Decoris_PreOrderLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_PreOrderLine 
{

    /** TableName=C_Decoris_PreOrderLine */
    public static final String Table_Name = "C_Decoris_PreOrderLine";

    /** AD_Table_ID=1000199 */
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

    /** Column name C_Decoris_PreOrder_ID */
    public static final String COLUMNNAME_C_Decoris_PreOrder_ID = "C_Decoris_PreOrder_ID";

	/** Set C_Decoris_PreOrder	  */
	public void setC_Decoris_PreOrder_ID (int C_Decoris_PreOrder_ID);

	/** Get C_Decoris_PreOrder	  */
	public int getC_Decoris_PreOrder_ID();

	public I_C_Decoris_PreOrder getC_Decoris_PreOrder() throws RuntimeException;

    /** Column name C_Decoris_PreOrderLine_ID */
    public static final String COLUMNNAME_C_Decoris_PreOrderLine_ID = "C_Decoris_PreOrderLine_ID";

	/** Set C_Decoris_PreOrderLine	  */
	public void setC_Decoris_PreOrderLine_ID (int C_Decoris_PreOrderLine_ID);

	/** Get C_Decoris_PreOrderLine	  */
	public int getC_Decoris_PreOrderLine_ID();

    /** Column name C_Decoris_PreOrderLine_UU */
    public static final String COLUMNNAME_C_Decoris_PreOrderLine_UU = "C_Decoris_PreOrderLine_UU";

	/** Set C_Decoris_PreOrderLine_UU	  */
	public void setC_Decoris_PreOrderLine_UU (String C_Decoris_PreOrderLine_UU);

	/** Get C_Decoris_PreOrderLine_UU	  */
	public String getC_Decoris_PreOrderLine_UU();

    /** Column name C_Decoris_PreSalesLine_ID */
    public static final String COLUMNNAME_C_Decoris_PreSalesLine_ID = "C_Decoris_PreSalesLine_ID";

	/** Set Decoris Presales Line	  */
	public void setC_Decoris_PreSalesLine_ID (int C_Decoris_PreSalesLine_ID);

	/** Get Decoris Presales Line	  */
	public int getC_Decoris_PreSalesLine_ID();

	public I_C_Decoris_PreSalesLine getC_Decoris_PreSalesLine() throws RuntimeException;

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

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name LineNetAmt */
    public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";

	/** Set Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt);

	/** Get Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt();

    /** Column name LineNo */
    public static final String COLUMNNAME_LineNo = "LineNo";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLineNo (int LineNo);

	/** Get Line No.
	  * Unique line for this document
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

    /** Column name M_Fifapps_Objcodes_ID */
    public static final String COLUMNNAME_M_Fifapps_Objcodes_ID = "M_Fifapps_Objcodes_ID";

	/** Set M_Fifapps_Objcodes	  */
	public void setM_Fifapps_Objcodes_ID (int M_Fifapps_Objcodes_ID);

	/** Get M_Fifapps_Objcodes	  */
	public int getM_Fifapps_Objcodes_ID();

	public I_M_Fifapps_Objcodes getM_Fifapps_Objcodes() throws RuntimeException;

    /** Column name M_Fifapps_Supplier_ID */
    public static final String COLUMNNAME_M_Fifapps_Supplier_ID = "M_Fifapps_Supplier_ID";

	/** Set M_Fifapps_Supplier	  */
	public void setM_Fifapps_Supplier_ID (int M_Fifapps_Supplier_ID);

	/** Get M_Fifapps_Supplier	  */
	public int getM_Fifapps_Supplier_ID();

	public I_M_Fifapps_Supplier getM_Fifapps_Supplier() throws RuntimeException;

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

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
