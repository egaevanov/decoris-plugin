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

/** Generated Model for C_Decoris_CloseCash
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_CloseCash extends PO implements I_C_Decoris_CloseCash, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170822L;

    /** Standard Constructor */
    public X_C_Decoris_CloseCash (Properties ctx, int C_Decoris_CloseCash_ID, String trxName)
    {
      super (ctx, C_Decoris_CloseCash_ID, trxName);
      /** if (C_Decoris_CloseCash_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_CloseCash (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_CloseCash[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Bank Payment.
		@param BankPayment Bank Payment	  */
	public void setBankPayment (BigDecimal BankPayment)
	{
		set_Value (COLUMNNAME_BankPayment, BankPayment);
	}

	/** Get Bank Payment.
		@return Bank Payment	  */
	public BigDecimal getBankPayment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BankPayment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Decoris Close Cash.
		@param C_Decoris_CloseCash_ID Decoris Close Cash	  */
	public void setC_Decoris_CloseCash_ID (int C_Decoris_CloseCash_ID)
	{
		if (C_Decoris_CloseCash_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_CloseCash_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_CloseCash_ID, Integer.valueOf(C_Decoris_CloseCash_ID));
	}

	/** Get Decoris Close Cash.
		@return Decoris Close Cash	  */
	public int getC_Decoris_CloseCash_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_CloseCash_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_CloseCash_UU.
		@param C_Decoris_CloseCash_UU C_Decoris_CloseCash_UU	  */
	public void setC_Decoris_CloseCash_UU (String C_Decoris_CloseCash_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_CloseCash_UU, C_Decoris_CloseCash_UU);
	}

	/** Get C_Decoris_CloseCash_UU.
		@return C_Decoris_CloseCash_UU	  */
	public String getC_Decoris_CloseCash_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_CloseCash_UU);
	}

	/** Set Cash Payment.
		@param Cash Cash Payment	  */
	public void setCash (BigDecimal Cash)
	{
		set_Value (COLUMNNAME_Cash, Cash);
	}

	/** Get Cash Payment.
		@return Cash Payment	  */
	public BigDecimal getCash () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cash);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set CashIn.
		@param CashIn CashIn	  */
	public void setCashIn (BigDecimal CashIn)
	{
		set_Value (COLUMNNAME_CashIn, CashIn);
	}

	/** Get CashIn.
		@return CashIn	  */
	public BigDecimal getCashIn () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CashIn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set CashOut.
		@param CashOut CashOut	  */
	public void setCashOut (BigDecimal CashOut)
	{
		set_Value (COLUMNNAME_CashOut, CashOut);
	}

	/** Get CashOut.
		@return CashOut	  */
	public BigDecimal getCashOut () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CashOut);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Close Cash Date.
		@param CloseCashDate Close Cash Date	  */
	public void setCloseCashDate (Timestamp CloseCashDate)
	{
		set_Value (COLUMNNAME_CloseCashDate, CloseCashDate);
	}

	/** Get Close Cash Date.
		@return Close Cash Date	  */
	public Timestamp getCloseCashDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CloseCashDate);
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
			set_Value (COLUMNNAME_CreatedByPOS_ID, null);
		else 
			set_Value (COLUMNNAME_CreatedByPOS_ID, Integer.valueOf(CreatedByPOS_ID));
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

	/** Set Current balance.
		@param CurrentBalance 
		Current Balance
	  */
	public void setCurrentBalance (BigDecimal CurrentBalance)
	{
		set_Value (COLUMNNAME_CurrentBalance, CurrentBalance);
	}

	/** Get Current balance.
		@return Current Balance
	  */
	public BigDecimal getCurrentBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set lainlain.
		@param lainlain lainlain	  */
	public void setlainlain (BigDecimal lainlain)
	{
		set_Value (COLUMNNAME_lainlain, lainlain);
	}

	/** Get lainlain.
		@return lainlain	  */
	public BigDecimal getlainlain () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_lainlain);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Leasing payment.
		@param leasingpayment Leasing payment	  */
	public void setleasingpayment (BigDecimal leasingpayment)
	{
		set_Value (COLUMNNAME_leasingpayment, leasingpayment);
	}

	/** Get Leasing payment.
		@return Leasing payment	  */
	public BigDecimal getleasingpayment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_leasingpayment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set seqTutupKas.
		@param seqTutupKas seqTutupKas	  */
	public void setseqTutupKas (int seqTutupKas)
	{
		set_Value (COLUMNNAME_seqTutupKas, Integer.valueOf(seqTutupKas));
	}

	/** Get seqTutupKas.
		@return seqTutupKas	  */
	public int getseqTutupKas () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_seqTutupKas);
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

	/** Set TrxCount.
		@param TrxCount TrxCount	  */
	public void setTrxCount (int TrxCount)
	{
		set_Value (COLUMNNAME_TrxCount, Integer.valueOf(TrxCount));
	}

	/** Get TrxCount.
		@return TrxCount	  */
	public int getTrxCount () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TrxCount);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}