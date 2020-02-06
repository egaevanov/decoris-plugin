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

/** Generated Model for C_Decoris_PreSales
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_PreSales extends PO implements I_C_Decoris_PreSales, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171227L;

    /** Standard Constructor */
    public X_C_Decoris_PreSales (Properties ctx, int C_Decoris_PreSales_ID, String trxName)
    {
      super (ctx, C_Decoris_PreSales_ID, trxName);
      /** if (C_Decoris_PreSales_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_PreSales (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_PreSales[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AlamatLain.
		@param AlamatLain AlamatLain	  */
	public void setAlamatLain (String AlamatLain)
	{
		set_Value (COLUMNNAME_AlamatLain, AlamatLain);
	}

	/** Get AlamatLain.
		@return AlamatLain	  */
	public String getAlamatLain () 
	{
		return (String)get_Value(COLUMNNAME_AlamatLain);
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

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
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

	/** Set C_Decoris_PreSales_ID.
		@param C_Decoris_PreSales_ID C_Decoris_PreSales_ID	  */
	public void setC_Decoris_PreSales_ID (int C_Decoris_PreSales_ID)
	{
		if (C_Decoris_PreSales_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSales_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSales_ID, Integer.valueOf(C_Decoris_PreSales_ID));
	}

	/** Get C_Decoris_PreSales_ID.
		@return C_Decoris_PreSales_ID	  */
	public int getC_Decoris_PreSales_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreSales_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_PreSales_UU.
		@param C_Decoris_PreSales_UU C_Decoris_PreSales_UU	  */
	public void setC_Decoris_PreSales_UU (String C_Decoris_PreSales_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_PreSales_UU, C_Decoris_PreSales_UU);
	}

	/** Get C_Decoris_PreSales_UU.
		@return C_Decoris_PreSales_UU	  */
	public String getC_Decoris_PreSales_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_PreSales_UU);
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
			set_ValueNoCheck (COLUMNNAME_C_DecorisPOS_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DecorisPOS_ID, Integer.valueOf(C_DecorisPOS_ID));
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

	/** Set C_PreOrder_ID.
		@param C_PreOrder_ID C_PreOrder_ID	  */
	public void setC_PreOrder_ID (int C_PreOrder_ID)
	{
		if (C_PreOrder_ID < 1) 
			set_Value (COLUMNNAME_C_PreOrder_ID, null);
		else 
			set_Value (COLUMNNAME_C_PreOrder_ID, Integer.valueOf(C_PreOrder_ID));
	}

	/** Get C_PreOrder_ID.
		@return C_PreOrder_ID	  */
	public int getC_PreOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PreOrder_ID);
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

	/** Set JumlahPembayaranBank.
		@param JumlahPembayaranBank JumlahPembayaranBank	  */
	public void setJumlahPembayaranBank (BigDecimal JumlahPembayaranBank)
	{
		set_Value (COLUMNNAME_JumlahPembayaranBank, JumlahPembayaranBank);
	}

	/** Get JumlahPembayaranBank.
		@return JumlahPembayaranBank	  */
	public BigDecimal getJumlahPembayaranBank () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_JumlahPembayaranBank);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set JumlahPembayaranHutang.
		@param JumlahPembayaranHutang JumlahPembayaranHutang	  */
	public void setJumlahPembayaranHutang (BigDecimal JumlahPembayaranHutang)
	{
		set_Value (COLUMNNAME_JumlahPembayaranHutang, JumlahPembayaranHutang);
	}

	/** Get JumlahPembayaranHutang.
		@return JumlahPembayaranHutang	  */
	public BigDecimal getJumlahPembayaranHutang () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_JumlahPembayaranHutang);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set JumlahPembayaranLeasing.
		@param JumlahPembayaranLeasing JumlahPembayaranLeasing	  */
	public void setJumlahPembayaranLeasing (BigDecimal JumlahPembayaranLeasing)
	{
		set_Value (COLUMNNAME_JumlahPembayaranLeasing, JumlahPembayaranLeasing);
	}

	/** Get JumlahPembayaranLeasing.
		@return JumlahPembayaranLeasing	  */
	public BigDecimal getJumlahPembayaranLeasing () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_JumlahPembayaranLeasing);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set JumlahPembayaranTunai.
		@param JumlahPembayaranTunai JumlahPembayaranTunai	  */
	public void setJumlahPembayaranTunai (BigDecimal JumlahPembayaranTunai)
	{
		set_Value (COLUMNNAME_JumlahPembayaranTunai, JumlahPembayaranTunai);
	}

	/** Get JumlahPembayaranTunai.
		@return JumlahPembayaranTunai	  */
	public BigDecimal getJumlahPembayaranTunai () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_JumlahPembayaranTunai);
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

	/** Set OrderDocType_ID.
		@param OrderDocType_ID OrderDocType_ID	  */
	public void setOrderDocType_ID (int OrderDocType_ID)
	{
		if (OrderDocType_ID < 1) 
			set_Value (COLUMNNAME_OrderDocType_ID, null);
		else 
			set_Value (COLUMNNAME_OrderDocType_ID, Integer.valueOf(OrderDocType_ID));
	}

	/** Get OrderDocType_ID.
		@return OrderDocType_ID	  */
	public int getOrderDocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OrderDocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = X */
	public static final String PAYMENTRULE_Cash = "X";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Mixed POS Payment = M */
	public static final String PAYMENTRULE_MixedPOSPayment = "M";
	/** Leasing = L */
	public static final String PAYMENTRULE_Leasing = "L";
	/** Authorization = A */
	public static final String PAYMENTRULE_Authorization = "A";
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

	/** Set PreSalesStatus.
		@param PreSalesStatus PreSalesStatus	  */
	public void setPreSalesStatus (String PreSalesStatus)
	{
		set_Value (COLUMNNAME_PreSalesStatus, PreSalesStatus);
	}

	/** Get PreSalesStatus.
		@return PreSalesStatus	  */
	public String getPreSalesStatus () 
	{
		return (String)get_Value(COLUMNNAME_PreSalesStatus);
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

	/** Completed = CO */
	public static final String STATUS_Completed = "CO";
	/** In Progress = IP */
	public static final String STATUS_InProgress = "IP";
	/** Closed = CL */
	public static final String STATUS_Closed = "CL";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
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

	/** Set TotalBayar.
		@param TotalBayar TotalBayar	  */
	public void setTotalBayar (BigDecimal TotalBayar)
	{
		set_Value (COLUMNNAME_TotalBayar, TotalBayar);
	}

	/** Get TotalBayar.
		@return TotalBayar	  */
	public BigDecimal getTotalBayar () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalBayar);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TotalBelanja.
		@param TotalBelanja TotalBelanja	  */
	public void setTotalBelanja (BigDecimal TotalBelanja)
	{
		set_Value (COLUMNNAME_TotalBelanja, TotalBelanja);
	}

	/** Get TotalBelanja.
		@return TotalBelanja	  */
	public BigDecimal getTotalBelanja () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalBelanja);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set AlamatLain.
		@param TotalDiskon AlamatLain	  */
	public void setTotalDiskon (BigDecimal TotalDiskon)
	{
		set_Value (COLUMNNAME_TotalDiskon, TotalDiskon);
	}

	/** Get AlamatLain.
		@return AlamatLain	  */
	public BigDecimal getTotalDiskon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalDiskon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}