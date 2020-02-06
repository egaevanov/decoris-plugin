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

/** Generated Interface for C_Decoris_ENofaLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_ENofaLine 
{

    /** TableName=C_Decoris_ENofaLine */
    public static final String Table_Name = "C_Decoris_ENofaLine";

    /** AD_Table_ID=1000070 */
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

    /** Column name C_Decoris_ENofa_ID */
    public static final String COLUMNNAME_C_Decoris_ENofa_ID = "C_Decoris_ENofa_ID";

	/** Set Decoris E-Nofa	  */
	public void setC_Decoris_ENofa_ID (int C_Decoris_ENofa_ID);

	/** Get Decoris E-Nofa	  */
	public int getC_Decoris_ENofa_ID();

	public I_C_Decoris_ENofa getC_Decoris_ENofa() throws RuntimeException;

    /** Column name C_Decoris_ENofaLine_ID */
    public static final String COLUMNNAME_C_Decoris_ENofaLine_ID = "C_Decoris_ENofaLine_ID";

	/** Set C_Decoris_ENofaLine_ID	  */
	public void setC_Decoris_ENofaLine_ID (int C_Decoris_ENofaLine_ID);

	/** Get C_Decoris_ENofaLine_ID	  */
	public int getC_Decoris_ENofaLine_ID();

    /** Column name C_Decoris_ENofaLine_UU */
    public static final String COLUMNNAME_C_Decoris_ENofaLine_UU = "C_Decoris_ENofaLine_UU";

	/** Set C_Decoris_ENofaLine_UU	  */
	public void setC_Decoris_ENofaLine_UU (String C_Decoris_ENofaLine_UU);

	/** Get C_Decoris_ENofaLine_UU	  */
	public String getC_Decoris_ENofaLine_UU();

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException;

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NomorPajak */
    public static final String COLUMNNAME_NomorPajak = "NomorPajak";

	/** Set Nomor Pajak	  */
	public void setNomorPajak (String NomorPajak);

	/** Get Nomor Pajak	  */
	public String getNomorPajak();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

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
