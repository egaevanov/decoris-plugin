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

/** Generated Model for C_DecorisTemp
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_DecorisTemp extends PO implements I_C_DecorisTemp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180914L;

    /** Standard Constructor */
    public X_C_DecorisTemp (Properties ctx, int C_DecorisTemp_ID, String trxName)
    {
      super (ctx, C_DecorisTemp_ID, trxName);
      /** if (C_DecorisTemp_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_C_DecorisTemp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_DecorisTemp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Decoris_CloseCash getC_Decoris_CloseCash() throws RuntimeException
    {
		return (I_C_Decoris_CloseCash)MTable.get(getCtx(), I_C_Decoris_CloseCash.Table_Name)
			.getPO(getC_Decoris_CloseCash_ID(), get_TrxName());	}

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

	public I_C_DecorisPOS getC_DecorisPOS() throws RuntimeException
    {
		return (I_C_DecorisPOS)MTable.get(getCtx(), I_C_DecorisPOS.Table_Name)
			.getPO(getC_DecorisPOS_ID(), get_TrxName());	}

	/** Set Decoris POS.
		@param C_DecorisPOS_ID 
		Decoris POS
	  */
	public void setC_DecorisPOS_ID (int C_DecorisPOS_ID)
	{
		if (C_DecorisPOS_ID < 1) 
			set_Value (COLUMNNAME_C_DecorisPOS_ID, null);
		else 
			set_Value (COLUMNNAME_C_DecorisPOS_ID, Integer.valueOf(C_DecorisPOS_ID));
	}

	/** Get Decoris POS.
		@return Decoris POS
	  */
	public int getC_DecorisPOS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DecorisPOS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_DecorisTemp.
		@param C_DecorisTemp_ID C_DecorisTemp	  */
	public void setC_DecorisTemp_ID (int C_DecorisTemp_ID)
	{
		if (C_DecorisTemp_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_DecorisTemp_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DecorisTemp_ID, Integer.valueOf(C_DecorisTemp_ID));
	}

	/** Get C_DecorisTemp.
		@return C_DecorisTemp	  */
	public int getC_DecorisTemp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DecorisTemp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_DecorisTemp_UU.
		@param C_DecorisTemp_UU C_DecorisTemp_UU	  */
	public void setC_DecorisTemp_UU (String C_DecorisTemp_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_DecorisTemp_UU, C_DecorisTemp_UU);
	}

	/** Get C_DecorisTemp_UU.
		@return C_DecorisTemp_UU	  */
	public String getC_DecorisTemp_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_DecorisTemp_UU);
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException
    {
		return (org.compiere.model.I_C_PaymentTerm)MTable.get(getCtx(), org.compiere.model.I_C_PaymentTerm.Table_Name)
			.getPO(getC_PaymentTerm_ID(), get_TrxName());	}

	/** Set Payment Term.
		@param C_PaymentTerm_ID 
		The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
	{
		if (C_PaymentTerm_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PaymentTerm_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PaymentTerm_ID, Integer.valueOf(C_PaymentTerm_ID));
	}

	/** Get Payment Term.
		@return The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentTerm_ID);
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

	/** DeliveryRule AD_Reference_ID=151 */
	public static final int DELIVERYRULE_AD_Reference_ID=151;
	/** After Receipt = R */
	public static final String DELIVERYRULE_AfterReceipt = "R";
	/** Availability = A */
	public static final String DELIVERYRULE_Availability = "A";
	/** Complete Line = L */
	public static final String DELIVERYRULE_CompleteLine = "L";
	/** Complete Order = O */
	public static final String DELIVERYRULE_CompleteOrder = "O";
	/** Force = F */
	public static final String DELIVERYRULE_Force = "F";
	/** Manual = M */
	public static final String DELIVERYRULE_Manual = "M";
	/** Set Delivery Rule.
		@param DeliveryRule 
		Defines the timing of Delivery
	  */
	public void setDeliveryRule (String DeliveryRule)
	{

		set_ValueNoCheck (COLUMNNAME_DeliveryRule, DeliveryRule);
	}

	/** Get Delivery Rule.
		@return Defines the timing of Delivery
	  */
	public String getDeliveryRule () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryRule);
	}

	/** DeliveryViaRule AD_Reference_ID=152 */
	public static final int DELIVERYVIARULE_AD_Reference_ID=152;
	/** Pickup = P */
	public static final String DELIVERYVIARULE_Pickup = "P";
	/** Delivery = D */
	public static final String DELIVERYVIARULE_Delivery = "D";
	/** Shipper = S */
	public static final String DELIVERYVIARULE_Shipper = "S";
	/** Set Delivery Via.
		@param DeliveryViaRule 
		How the order will be delivered
	  */
	public void setDeliveryViaRule (String DeliveryViaRule)
	{

		set_ValueNoCheck (COLUMNNAME_DeliveryViaRule, DeliveryViaRule);
	}

	/** Get Delivery Via.
		@return How the order will be delivered
	  */
	public String getDeliveryViaRule () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryViaRule);
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

	/** Set Discount Amount.
		@param DiscountAmt 
		Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt)
	{
		set_ValueNoCheck (COLUMNNAME_DiscountAmt, DiscountAmt);
	}

	/** Get Discount Amount.
		@return Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Document No Tutup Kas.
		@param DocumentNoTutupKas Document No Tutup Kas	  */
	public void setDocumentNoTutupKas (String DocumentNoTutupKas)
	{
		set_Value (COLUMNNAME_DocumentNoTutupKas, DocumentNoTutupKas);
	}

	/** Get Document No Tutup Kas.
		@return Document No Tutup Kas	  */
	public String getDocumentNoTutupKas () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNoTutupKas);
	}

	/** Set dpp.
		@param dpp dpp	  */
	public void setdpp (BigDecimal dpp)
	{
		set_ValueNoCheck (COLUMNNAME_dpp, dpp);
	}

	/** Get dpp.
		@return dpp	  */
	public BigDecimal getdpp () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_dpp);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		set_ValueNoCheck (COLUMNNAME_GrandTotal, GrandTotal);
	}

	/** Get Grand Total.
		@return Total amount of document
	  */
	public BigDecimal getGrandTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GrandTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Leasing.
		@param IsLeasing Leasing	  */
	public void setIsLeasing (boolean IsLeasing)
	{
		set_Value (COLUMNNAME_IsLeasing, Boolean.valueOf(IsLeasing));
	}

	/** Get Leasing.
		@return Leasing	  */
	public boolean isLeasing () 
	{
		Object oo = get_Value(COLUMNNAME_IsLeasing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsManualDocumentNo.
		@param IsManualDocumentNo IsManualDocumentNo	  */
	public void setIsManualDocumentNo (boolean IsManualDocumentNo)
	{
		set_Value (COLUMNNAME_IsManualDocumentNo, Boolean.valueOf(IsManualDocumentNo));
	}

	/** Get IsManualDocumentNo.
		@return IsManualDocumentNo	  */
	public boolean isManualDocumentNo () 
	{
		Object oo = get_Value(COLUMNNAME_IsManualDocumentNo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsMultiLocator.
		@param IsMultiLocator IsMultiLocator	  */
	public void setIsMultiLocator (boolean IsMultiLocator)
	{
		set_Value (COLUMNNAME_IsMultiLocator, Boolean.valueOf(IsMultiLocator));
	}

	/** Get IsMultiLocator.
		@return IsMultiLocator	  */
	public boolean isMultiLocator () 
	{
		Object oo = get_Value(COLUMNNAME_IsMultiLocator);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pembatalan.
		@param IsPembatalan Pembatalan	  */
	public void setIsPembatalan (boolean IsPembatalan)
	{
		set_Value (COLUMNNAME_IsPembatalan, Boolean.valueOf(IsPembatalan));
	}

	/** Get Pembatalan.
		@return Pembatalan	  */
	public boolean isPembatalan () 
	{
		Object oo = get_Value(COLUMNNAME_IsPembatalan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pembayaran/Penerimaan.
		@param IsPembayaran Pembayaran/Penerimaan	  */
	public void setIsPembayaran (boolean IsPembayaran)
	{
		set_Value (COLUMNNAME_IsPembayaran, Boolean.valueOf(IsPembayaran));
	}

	/** Get Pembayaran/Penerimaan.
		@return Pembayaran/Penerimaan	  */
	public boolean isPembayaran () 
	{
		Object oo = get_Value(COLUMNNAME_IsPembayaran);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Penjualan.
		@param IsPenjualan Penjualan	  */
	public void setIsPenjualan (boolean IsPenjualan)
	{
		set_Value (COLUMNNAME_IsPenjualan, Boolean.valueOf(IsPenjualan));
	}

	/** Get Penjualan.
		@return Penjualan	  */
	public boolean isPenjualan () 
	{
		Object oo = get_Value(COLUMNNAME_IsPenjualan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_ValueNoCheck (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPrintedSJ.
		@param IsPrintedSJ IsPrintedSJ	  */
	public void setIsPrintedSJ (boolean IsPrintedSJ)
	{
		set_Value (COLUMNNAME_IsPrintedSJ, Boolean.valueOf(IsPrintedSJ));
	}

	/** Get IsPrintedSJ.
		@return IsPrintedSJ	  */
	public boolean isPrintedSJ () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrintedSJ);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Receipt.
		@param IsReceipt 
		This is a sales transaction (receipt)
	  */
	public void setIsReceipt (boolean IsReceipt)
	{
		set_Value (COLUMNNAME_IsReceipt, Boolean.valueOf(IsReceipt));
	}

	/** Get Receipt.
		@return This is a sales transaction (receipt)
	  */
	public boolean isReceipt () 
	{
		Object oo = get_Value(COLUMNNAME_IsReceipt);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Return.
		@param IsReturn Is Return	  */
	public void setIsReturn (boolean IsReturn)
	{
		set_Value (COLUMNNAME_IsReturn, Boolean.valueOf(IsReturn));
	}

	/** Get Is Return.
		@return Is Return	  */
	public boolean isReturn () 
	{
		Object oo = get_Value(COLUMNNAME_IsReturn);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Transaction.
		@param IsSOTrx 
		This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_ValueNoCheck (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Spektra.
		@param IsSpektra Spektra	  */
	public void setIsSpektra (boolean IsSpektra)
	{
		set_Value (COLUMNNAME_IsSpektra, Boolean.valueOf(IsSpektra));
	}

	/** Get Spektra.
		@return Spektra	  */
	public boolean isSpektra () 
	{
		Object oo = get_Value(COLUMNNAME_IsSpektra);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tutup Kas.
		@param IsTutupKas Tutup Kas	  */
	public void setIsTutupKas (boolean IsTutupKas)
	{
		set_Value (COLUMNNAME_IsTutupKas, Boolean.valueOf(IsTutupKas));
	}

	/** Get Tutup Kas.
		@return Tutup Kas	  */
	public boolean isTutupKas () 
	{
		Object oo = get_Value(COLUMNNAME_IsTutupKas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set LocatorNoMulti_ID.
		@param LocatorNoMulti_ID LocatorNoMulti_ID	  */
	public void setLocatorNoMulti_ID (int LocatorNoMulti_ID)
	{
		if (LocatorNoMulti_ID < 1) 
			set_Value (COLUMNNAME_LocatorNoMulti_ID, null);
		else 
			set_Value (COLUMNNAME_LocatorNoMulti_ID, Integer.valueOf(LocatorNoMulti_ID));
	}

	/** Get LocatorNoMulti_ID.
		@return LocatorNoMulti_ID	  */
	public int getLocatorNoMulti_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LocatorNoMulti_ID);
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

	/** Set orderdoctype_id.
		@param orderdoctype_id orderdoctype_id	  */
	public void setorderdoctype_id (int orderdoctype_id)
	{
		set_Value (COLUMNNAME_orderdoctype_id, Integer.valueOf(orderdoctype_id));
	}

	/** Get orderdoctype_id.
		@return orderdoctype_id	  */
	public int getorderdoctype_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_orderdoctype_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{
		set_ValueNoCheck (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}

	/** Set Tipe Pembayaran Pertama.
		@param PayType1 Tipe Pembayaran Pertama	  */
	public void setPayType1 (String PayType1)
	{
		set_Value (COLUMNNAME_PayType1, PayType1);
	}

	/** Get Tipe Pembayaran Pertama.
		@return Tipe Pembayaran Pertama	  */
	public String getPayType1 () 
	{
		return (String)get_Value(COLUMNNAME_PayType1);
	}

	/** Set Tipe Pembayaran Kedua.
		@param PayType2 Tipe Pembayaran Kedua	  */
	public void setPayType2 (String PayType2)
	{
		set_Value (COLUMNNAME_PayType2, PayType2);
	}

	/** Get Tipe Pembayaran Kedua.
		@return Tipe Pembayaran Kedua	  */
	public String getPayType2 () 
	{
		return (String)get_Value(COLUMNNAME_PayType2);
	}

	/** Set Tipe Pembayaran Ketiga.
		@param PayType3 Tipe Pembayaran Ketiga	  */
	public void setPayType3 (String PayType3)
	{
		set_Value (COLUMNNAME_PayType3, PayType3);
	}

	/** Get Tipe Pembayaran Ketiga.
		@return Tipe Pembayaran Ketiga	  */
	public String getPayType3 () 
	{
		return (String)get_Value(COLUMNNAME_PayType3);
	}

	/** Set Tipe Pembayaran Keempat.
		@param PayType4 Tipe Pembayaran Keempat	  */
	public void setPayType4 (String PayType4)
	{
		set_Value (COLUMNNAME_PayType4, PayType4);
	}

	/** Get Tipe Pembayaran Keempat.
		@return Tipe Pembayaran Keempat	  */
	public String getPayType4 () 
	{
		return (String)get_Value(COLUMNNAME_PayType4);
	}

	/** Set Pembayaran Pertama.
		@param Pembayaran1 Pembayaran Pertama	  */
	public void setPembayaran1 (BigDecimal Pembayaran1)
	{
		set_Value (COLUMNNAME_Pembayaran1, Pembayaran1);
	}

	/** Get Pembayaran Pertama.
		@return Pembayaran Pertama	  */
	public BigDecimal getPembayaran1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pembayaran1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pembayaran Kedua.
		@param Pembayaran2 Pembayaran Kedua	  */
	public void setPembayaran2 (BigDecimal Pembayaran2)
	{
		set_Value (COLUMNNAME_Pembayaran2, Pembayaran2);
	}

	/** Get Pembayaran Kedua.
		@return Pembayaran Kedua	  */
	public BigDecimal getPembayaran2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pembayaran2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pembayaran Ketiga.
		@param Pembayaran3 Pembayaran Ketiga	  */
	public void setPembayaran3 (BigDecimal Pembayaran3)
	{
		set_Value (COLUMNNAME_Pembayaran3, Pembayaran3);
	}

	/** Get Pembayaran Ketiga.
		@return Pembayaran Ketiga	  */
	public BigDecimal getPembayaran3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pembayaran3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pembayaran Keempat.
		@param Pembayaran4 Pembayaran Keempat	  */
	public void setPembayaran4 (BigDecimal Pembayaran4)
	{
		set_Value (COLUMNNAME_Pembayaran4, Pembayaran4);
	}

	/** Get Pembayaran Keempat.
		@return Pembayaran Keempat	  */
	public BigDecimal getPembayaran4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pembayaran4);
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

	public org.compiere.model.I_AD_User getSupervisor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSupervisor_ID(), get_TrxName());	}

	/** Set Supervisor.
		@param Supervisor_ID 
		Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID)
	{
		if (Supervisor_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Supervisor_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
	}

	/** Get Supervisor.
		@return Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Supervisor_ID);
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

	/** Set Total Kembalian.
		@param TotalKembalian Total Kembalian	  */
	public void setTotalKembalian (BigDecimal TotalKembalian)
	{
		set_Value (COLUMNNAME_TotalKembalian, TotalKembalian);
	}

	/** Get Total Kembalian.
		@return Total Kembalian	  */
	public BigDecimal getTotalKembalian () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalKembalian);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Lines.
		@param TotalLines 
		Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines)
	{
		set_ValueNoCheck (COLUMNNAME_TotalLines, TotalLines);
	}

	/** Get Total Lines.
		@return Total of all document lines
	  */
	public BigDecimal getTotalLines () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLines);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TotalTunai.
		@param TotalTunai TotalTunai	  */
	public void setTotalTunai (BigDecimal TotalTunai)
	{
		set_Value (COLUMNNAME_TotalTunai, TotalTunai);
	}

	/** Get TotalTunai.
		@return TotalTunai	  */
	public BigDecimal getTotalTunai () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalTunai);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}