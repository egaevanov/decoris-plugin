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

/** Generated Interface for C_Decoris_CloseCash
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_CloseCash 
{

    /** TableName=C_Decoris_CloseCash */
    public static final String Table_Name = "C_Decoris_CloseCash";

    /** AD_Table_ID=1000063 */
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

    /** Column name BankPayment */
    public static final String COLUMNNAME_BankPayment = "BankPayment";

	/** Set Bank Payment	  */
	public void setBankPayment (BigDecimal BankPayment);

	/** Get Bank Payment	  */
	public BigDecimal getBankPayment();

    /** Column name C_Decoris_CloseCash_ID */
    public static final String COLUMNNAME_C_Decoris_CloseCash_ID = "C_Decoris_CloseCash_ID";

	/** Set Decoris Close Cash	  */
	public void setC_Decoris_CloseCash_ID (int C_Decoris_CloseCash_ID);

	/** Get Decoris Close Cash	  */
	public int getC_Decoris_CloseCash_ID();

    /** Column name C_Decoris_CloseCash_UU */
    public static final String COLUMNNAME_C_Decoris_CloseCash_UU = "C_Decoris_CloseCash_UU";

	/** Set C_Decoris_CloseCash_UU	  */
	public void setC_Decoris_CloseCash_UU (String C_Decoris_CloseCash_UU);

	/** Get C_Decoris_CloseCash_UU	  */
	public String getC_Decoris_CloseCash_UU();

    /** Column name Cash */
    public static final String COLUMNNAME_Cash = "Cash";

	/** Set Cash Payment	  */
	public void setCash (BigDecimal Cash);

	/** Get Cash Payment	  */
	public BigDecimal getCash();

    /** Column name CashIn */
    public static final String COLUMNNAME_CashIn = "CashIn";

	/** Set CashIn	  */
	public void setCashIn (BigDecimal CashIn);

	/** Get CashIn	  */
	public BigDecimal getCashIn();

    /** Column name CashOut */
    public static final String COLUMNNAME_CashOut = "CashOut";

	/** Set CashOut	  */
	public void setCashOut (BigDecimal CashOut);

	/** Get CashOut	  */
	public BigDecimal getCashOut();

    /** Column name CloseCashDate */
    public static final String COLUMNNAME_CloseCashDate = "CloseCashDate";

	/** Set Close Cash Date	  */
	public void setCloseCashDate (Timestamp CloseCashDate);

	/** Get Close Cash Date	  */
	public Timestamp getCloseCashDate();

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

    /** Column name CreatedByPOS_ID */
    public static final String COLUMNNAME_CreatedByPOS_ID = "CreatedByPOS_ID";

	/** Set CreatedByPOS_ID	  */
	public void setCreatedByPOS_ID (int CreatedByPOS_ID);

	/** Get CreatedByPOS_ID	  */
	public int getCreatedByPOS_ID();

	public org.compiere.model.I_C_BPartner getCreatedByPOS() throws RuntimeException;

    /** Column name CurrentBalance */
    public static final String COLUMNNAME_CurrentBalance = "CurrentBalance";

	/** Set Current balance.
	  * Current Balance
	  */
	public void setCurrentBalance (BigDecimal CurrentBalance);

	/** Get Current balance.
	  * Current Balance
	  */
	public BigDecimal getCurrentBalance();

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

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

    /** Column name lainlain */
    public static final String COLUMNNAME_lainlain = "lainlain";

	/** Set lainlain	  */
	public void setlainlain (BigDecimal lainlain);

	/** Get lainlain	  */
	public BigDecimal getlainlain();

    /** Column name leasingpayment */
    public static final String COLUMNNAME_leasingpayment = "leasingpayment";

	/** Set Leasing payment	  */
	public void setleasingpayment (BigDecimal leasingpayment);

	/** Get Leasing payment	  */
	public BigDecimal getleasingpayment();

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

    /** Column name seqTutupKas */
    public static final String COLUMNNAME_seqTutupKas = "seqTutupKas";

	/** Set seqTutupKas	  */
	public void setseqTutupKas (int seqTutupKas);

	/** Get seqTutupKas	  */
	public int getseqTutupKas();

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

    /** Column name TrxCount */
    public static final String COLUMNNAME_TrxCount = "TrxCount";

	/** Set TrxCount	  */
	public void setTrxCount (int TrxCount);

	/** Get TrxCount	  */
	public int getTrxCount();

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
