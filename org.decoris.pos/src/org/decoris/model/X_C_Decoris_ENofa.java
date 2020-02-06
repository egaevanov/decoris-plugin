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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for C_Decoris_ENofa
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_ENofa extends PO implements I_C_Decoris_ENofa, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171122L;

    /** Standard Constructor */
    public X_C_Decoris_ENofa (Properties ctx, int C_Decoris_ENofa_ID, String trxName)
    {
      super (ctx, C_Decoris_ENofa_ID, trxName);
      /** if (C_Decoris_ENofa_ID == 0)
        {
			setEnofaCounter (0);
			setNoAkhir (0);
			setNoAwal (0);
			setNoSeri (null);
			setSisaEnofa (0);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
			setTotalEnofa (0);
// @NoAkhir@-@NoAwal@
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_ENofa (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_ENofa[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	/** Set C_Decoris_ENofa_UU.
		@param C_Decoris_ENofa_UU C_Decoris_ENofa_UU	  */
	public void setC_Decoris_ENofa_UU (String C_Decoris_ENofa_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_ENofa_UU, C_Decoris_ENofa_UU);
	}

	/** Get C_Decoris_ENofa_UU.
		@return C_Decoris_ENofa_UU	  */
	public String getC_Decoris_ENofa_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_ENofa_UU);
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

	public org.compiere.model.I_C_Year getC_Year() throws RuntimeException
    {
		return (org.compiere.model.I_C_Year)MTable.get(getCtx(), org.compiere.model.I_C_Year.Table_Name)
			.getPO(getC_Year_ID(), get_TrxName());	}

	/** Set Year.
		@param C_Year_ID 
		Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID)
	{
		if (C_Year_ID < 1) 
			set_Value (COLUMNNAME_C_Year_ID, null);
		else 
			set_Value (COLUMNNAME_C_Year_ID, Integer.valueOf(C_Year_ID));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getC_Year_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Year_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Counter.
		@param EnofaCounter Counter	  */
	public void setEnofaCounter (int EnofaCounter)
	{
		set_Value (COLUMNNAME_EnofaCounter, Integer.valueOf(EnofaCounter));
	}

	/** Get Counter.
		@return Counter	  */
	public int getEnofaCounter () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EnofaCounter);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Increment.
		@param Increment Increment	  */
	public void setIncrement (int Increment)
	{
		set_Value (COLUMNNAME_Increment, Integer.valueOf(Increment));
	}

	/** Get Increment.
		@return Increment	  */
	public int getIncrement () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Increment);
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

	/** Set No Akhir.
		@param NoAkhir No Akhir	  */
	public void setNoAkhir (int NoAkhir)
	{
		set_ValueNoCheck (COLUMNNAME_NoAkhir, Integer.valueOf(NoAkhir));
	}

	/** Get No Akhir.
		@return No Akhir	  */
	public int getNoAkhir () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoAkhir);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set No Awal.
		@param NoAwal No Awal	  */
	public void setNoAwal (int NoAwal)
	{
		set_ValueNoCheck (COLUMNNAME_NoAwal, Integer.valueOf(NoAwal));
	}

	/** Get No Awal.
		@return No Awal	  */
	public int getNoAwal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoAwal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set No Seri.
		@param NoSeri No Seri	  */
	public void setNoSeri (String NoSeri)
	{
		set_Value (COLUMNNAME_NoSeri, NoSeri);
	}

	/** Get No Seri.
		@return No Seri	  */
	public String getNoSeri () 
	{
		return (String)get_Value(COLUMNNAME_NoSeri);
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

	/** Set Sisa Enofa.
		@param SisaEnofa Sisa Enofa	  */
	public void setSisaEnofa (int SisaEnofa)
	{
		set_Value (COLUMNNAME_SisaEnofa, Integer.valueOf(SisaEnofa));
	}

	/** Get Sisa Enofa.
		@return Sisa Enofa	  */
	public int getSisaEnofa () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SisaEnofa);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
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

	/** Set Total Enofa.
		@param TotalEnofa Total Enofa	  */
	public void setTotalEnofa (int TotalEnofa)
	{
		set_Value (COLUMNNAME_TotalEnofa, Integer.valueOf(TotalEnofa));
	}

	/** Get Total Enofa.
		@return Total Enofa	  */
	public int getTotalEnofa () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TotalEnofa);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}