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

/** Generated Interface for C_Decoris_ENofa
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_ENofa 
{

    /** TableName=C_Decoris_ENofa */
    public static final String Table_Name = "C_Decoris_ENofa";

    /** AD_Table_ID=1000069 */
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

    /** Column name C_Decoris_ENofa_ID */
    public static final String COLUMNNAME_C_Decoris_ENofa_ID = "C_Decoris_ENofa_ID";

	/** Set Decoris E-Nofa	  */
	public void setC_Decoris_ENofa_ID (int C_Decoris_ENofa_ID);

	/** Get Decoris E-Nofa	  */
	public int getC_Decoris_ENofa_ID();

    /** Column name C_Decoris_ENofa_UU */
    public static final String COLUMNNAME_C_Decoris_ENofa_UU = "C_Decoris_ENofa_UU";

	/** Set C_Decoris_ENofa_UU	  */
	public void setC_Decoris_ENofa_UU (String C_Decoris_ENofa_UU);

	/** Get C_Decoris_ENofa_UU	  */
	public String getC_Decoris_ENofa_UU();

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_Year_ID */
    public static final String COLUMNNAME_C_Year_ID = "C_Year_ID";

	/** Set Year.
	  * Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID);

	/** Get Year.
	  * Calendar Year
	  */
	public int getC_Year_ID();

	public org.compiere.model.I_C_Year getC_Year() throws RuntimeException;

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

    /** Column name EnofaCounter */
    public static final String COLUMNNAME_EnofaCounter = "EnofaCounter";

	/** Set Counter	  */
	public void setEnofaCounter (int EnofaCounter);

	/** Get Counter	  */
	public int getEnofaCounter();

    /** Column name Increment */
    public static final String COLUMNNAME_Increment = "Increment";

	/** Set Increment	  */
	public void setIncrement (int Increment);

	/** Get Increment	  */
	public int getIncrement();

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

    /** Column name NoAkhir */
    public static final String COLUMNNAME_NoAkhir = "NoAkhir";

	/** Set No Akhir	  */
	public void setNoAkhir (int NoAkhir);

	/** Get No Akhir	  */
	public int getNoAkhir();

    /** Column name NoAwal */
    public static final String COLUMNNAME_NoAwal = "NoAwal";

	/** Set No Awal	  */
	public void setNoAwal (int NoAwal);

	/** Get No Awal	  */
	public int getNoAwal();

    /** Column name NoSeri */
    public static final String COLUMNNAME_NoSeri = "NoSeri";

	/** Set No Seri	  */
	public void setNoSeri (String NoSeri);

	/** Get No Seri	  */
	public String getNoSeri();

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

    /** Column name SisaEnofa */
    public static final String COLUMNNAME_SisaEnofa = "SisaEnofa";

	/** Set Sisa Enofa	  */
	public void setSisaEnofa (int SisaEnofa);

	/** Get Sisa Enofa	  */
	public int getSisaEnofa();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

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

    /** Column name TotalEnofa */
    public static final String COLUMNNAME_TotalEnofa = "TotalEnofa";

	/** Set Total Enofa	  */
	public void setTotalEnofa (int TotalEnofa);

	/** Get Total Enofa	  */
	public int getTotalEnofa();

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
