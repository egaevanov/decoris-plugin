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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for C_Decoris_PostingMethod
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_PostingMethod extends PO implements I_C_Decoris_PostingMethod, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170908L;

    /** Standard Constructor */
    public X_C_Decoris_PostingMethod (Properties ctx, int C_Decoris_PostingMethod_ID, String trxName)
    {
      super (ctx, C_Decoris_PostingMethod_ID, trxName);
      /** if (C_Decoris_PostingMethod_ID == 0)
        {
			setC_BankAccount_ID (0);
			setC_Currency_ID (0);
// 303
			setC_DocType_ID (0);
// @SQL=SELECT C_DocType_ID AS DefaultValue FROM C_DocType WHERE C_DocType.DocBaseType='POO' AND C_DocType.AD_Client_ID=@#AD_Client_ID@ AND C_DocType.Name = 'Purchase Order'
			setC_PaymentTerm_ID (0);
			setDocumentTypeInvoice_ID (0);
// @SQL=SELECT C_DocType_ID AS DefaultValue FROM C_DocType WHERE C_DocType.DocBaseType='API' AND C_DocType.AD_Client_ID=@#AD_Client_ID@ AND C_DocType.Name='AP Invoice'
			setDocumentTypePayment_ID (0);
// @SQL=SELECT C_DocType_ID AS DefaultValue FROM C_DocType WHERE C_DocType.DocBaseType='APP' AND C_DocType.AD_Client_ID=@#AD_Client_ID@ AND C_DocType.Name='AP Payment'
			setName (null);
			setPaymentRulePO (null);
// X
			setPriority (null);
// M
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_PostingMethod (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_PostingMethod[")
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

	/** Set Decoris Posting Method.
		@param C_Decoris_PostingMethod_ID Decoris Posting Method	  */
	public void setC_Decoris_PostingMethod_ID (int C_Decoris_PostingMethod_ID)
	{
		if (C_Decoris_PostingMethod_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PostingMethod_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PostingMethod_ID, Integer.valueOf(C_Decoris_PostingMethod_ID));
	}

	/** Get Decoris Posting Method.
		@return Decoris Posting Method	  */
	public int getC_Decoris_PostingMethod_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PostingMethod_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Decoris Posting Method.
		@param C_Decoris_PostingMethod_UU Decoris Posting Method	  */
	public void setC_Decoris_PostingMethod_UU (String C_Decoris_PostingMethod_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_PostingMethod_UU, C_Decoris_PostingMethod_UU);
	}

	/** Get Decoris Posting Method.
		@return Decoris Posting Method	  */
	public String getC_Decoris_PostingMethod_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_PostingMethod_UU);
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

	/** Set Complete Document Status Invoice.
		@param CompleteDocStatusInv Complete Document Status Invoice	  */
	public void setCompleteDocStatusInv (boolean CompleteDocStatusInv)
	{
		set_Value (COLUMNNAME_CompleteDocStatusInv, Boolean.valueOf(CompleteDocStatusInv));
	}

	/** Get Complete Document Status Invoice.
		@return Complete Document Status Invoice	  */
	public boolean isCompleteDocStatusInv () 
	{
		Object oo = get_Value(COLUMNNAME_CompleteDocStatusInv);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Complete Document Status Payment.
		@param CompleteDocStatusPay Complete Document Status Payment	  */
	public void setCompleteDocStatusPay (boolean CompleteDocStatusPay)
	{
		set_Value (COLUMNNAME_CompleteDocStatusPay, Boolean.valueOf(CompleteDocStatusPay));
	}

	/** Get Complete Document Status Payment.
		@return Complete Document Status Payment	  */
	public boolean isCompleteDocStatusPay () 
	{
		Object oo = get_Value(COLUMNNAME_CompleteDocStatusPay);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Complete Document Status PO.
		@param CompleteDocStatusPO Complete Document Status PO	  */
	public void setCompleteDocStatusPO (boolean CompleteDocStatusPO)
	{
		set_Value (COLUMNNAME_CompleteDocStatusPO, Boolean.valueOf(CompleteDocStatusPO));
	}

	/** Get Complete Document Status PO.
		@return Complete Document Status PO	  */
	public boolean isCompleteDocStatusPO () 
	{
		Object oo = get_Value(COLUMNNAME_CompleteDocStatusPO);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	public org.compiere.model.I_C_DocType getDocumentTypeInvoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getDocumentTypeInvoice_ID(), get_TrxName());	}

	/** Set Document Type Invoice.
		@param DocumentTypeInvoice_ID 
		Document Type Invoice
	  */
	public void setDocumentTypeInvoice_ID (int DocumentTypeInvoice_ID)
	{
		if (DocumentTypeInvoice_ID < 1) 
			set_Value (COLUMNNAME_DocumentTypeInvoice_ID, null);
		else 
			set_Value (COLUMNNAME_DocumentTypeInvoice_ID, Integer.valueOf(DocumentTypeInvoice_ID));
	}

	/** Get Document Type Invoice.
		@return Document Type Invoice
	  */
	public int getDocumentTypeInvoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DocumentTypeInvoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getDocumentTypePayment() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getDocumentTypePayment_ID(), get_TrxName());	}

	/** Set Document Type Payment.
		@param DocumentTypePayment_ID 
		Document Type Payment
	  */
	public void setDocumentTypePayment_ID (int DocumentTypePayment_ID)
	{
		if (DocumentTypePayment_ID < 1) 
			set_Value (COLUMNNAME_DocumentTypePayment_ID, null);
		else 
			set_Value (COLUMNNAME_DocumentTypePayment_ID, Integer.valueOf(DocumentTypePayment_ID));
	}

	/** Get Document Type Payment.
		@return Document Type Payment
	  */
	public int getDocumentTypePayment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DocumentTypePayment_ID);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** PaymentRulePO AD_Reference_ID=195 */
	public static final int PAYMENTRULEPO_AD_Reference_ID=195;
	/** Cash = X */
	public static final String PAYMENTRULEPO_Cash = "X";
	/** Credit Card = K */
	public static final String PAYMENTRULEPO_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULEPO_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULEPO_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULEPO_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULEPO_DirectDebit = "D";
	/** Mixed POS Payment = M */
	public static final String PAYMENTRULEPO_MixedPOSPayment = "M";
	/** Leasing = L */
	public static final String PAYMENTRULEPO_Leasing = "L";
	/** Authorization = A */
	public static final String PAYMENTRULEPO_Authorization = "A";
	/** Set Payment Rule.
		@param PaymentRulePO 
		Purchase payment option
	  */
	public void setPaymentRulePO (String PaymentRulePO)
	{

		set_Value (COLUMNNAME_PaymentRulePO, PaymentRulePO);
	}

	/** Get Payment Rule.
		@return Purchase payment option
	  */
	public String getPaymentRulePO () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRulePO);
	}

	/** High = H */
	public static final String PRIORITY_High = "H";
	/** Medium = M */
	public static final String PRIORITY_Medium = "M";
	/** Low = L */
	public static final String PRIORITY_Low = "L";
	/** Minor = N */
	public static final String PRIORITY_Minor = "N";
	/** Urgent = U */
	public static final String PRIORITY_Urgent = "U";
	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority)
	{

		set_Value (COLUMNNAME_Priority, Priority);
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority () 
	{
		return (String)get_Value(COLUMNNAME_Priority);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}