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

/** Generated Model for C_Decoris_ENofaLine
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_ENofaLine extends PO implements I_C_Decoris_ENofaLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171121L;

    /** Standard Constructor */
    public X_C_Decoris_ENofaLine (Properties ctx, int C_Decoris_ENofaLine_ID, String trxName)
    {
      super (ctx, C_Decoris_ENofaLine_ID, trxName);
      /** if (C_Decoris_ENofaLine_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_ENofaLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_ENofaLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_C_Decoris_ENofa getC_Decoris_ENofa() throws RuntimeException
    {
		return (I_C_Decoris_ENofa)MTable.get(getCtx(), I_C_Decoris_ENofa.Table_Name)
			.getPO(getC_Decoris_ENofa_ID(), get_TrxName());	}

	/** Set Decoris E-Nofa.
		@param C_Decoris_ENofa_ID Decoris E-Nofa	  */
	public void setC_Decoris_ENofa_ID (int C_Decoris_ENofa_ID)
	{
		if (C_Decoris_ENofa_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_ENofa_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_ENofa_ID, Integer.valueOf(C_Decoris_ENofa_ID));
	}

	/** Get Decoris E-Nofa.
		@return Decoris E-Nofa	  */
	public int getC_Decoris_ENofa_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_ENofa_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_ENofaLine_ID.
		@param C_Decoris_ENofaLine_ID C_Decoris_ENofaLine_ID	  */
	public void setC_Decoris_ENofaLine_ID (int C_Decoris_ENofaLine_ID)
	{
		if (C_Decoris_ENofaLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_ENofaLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_ENofaLine_ID, Integer.valueOf(C_Decoris_ENofaLine_ID));
	}

	/** Get C_Decoris_ENofaLine_ID.
		@return C_Decoris_ENofaLine_ID	  */
	public int getC_Decoris_ENofaLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_ENofaLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_ENofaLine_UU.
		@param C_Decoris_ENofaLine_UU C_Decoris_ENofaLine_UU	  */
	public void setC_Decoris_ENofaLine_UU (String C_Decoris_ENofaLine_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_ENofaLine_UU, C_Decoris_ENofaLine_UU);
	}

	/** Get C_Decoris_ENofaLine_UU.
		@return C_Decoris_ENofaLine_UU	  */
	public String getC_Decoris_ENofaLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_ENofaLine_UU);
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

	/** Set Nomor Pajak.
		@param NomorPajak Nomor Pajak	  */
	public void setNomorPajak (String NomorPajak)
	{
		set_Value (COLUMNNAME_NomorPajak, NomorPajak);
	}

	/** Get Nomor Pajak.
		@return Nomor Pajak	  */
	public String getNomorPajak () 
	{
		return (String)get_Value(COLUMNNAME_NomorPajak);
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

	/** Set Tax ID.
		@param TaxID 
		Tax Identification
	  */
	public void setTaxID (String TaxID)
	{
		set_ValueNoCheck (COLUMNNAME_TaxID, TaxID);
	}

	/** Get Tax ID.
		@return Tax Identification
	  */
	public String getTaxID () 
	{
		return (String)get_Value(COLUMNNAME_TaxID);
	}
}