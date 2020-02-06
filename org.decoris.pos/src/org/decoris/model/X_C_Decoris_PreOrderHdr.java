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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for C_Decoris_PreOrderHdr
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_PreOrderHdr extends PO implements I_C_Decoris_PreOrderHdr, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180223L;

    /** Standard Constructor */
    public X_C_Decoris_PreOrderHdr (Properties ctx, int C_Decoris_PreOrderHdr_ID, String trxName)
    {
      super (ctx, C_Decoris_PreOrderHdr_ID, trxName);
      /** if (C_Decoris_PreOrderHdr_ID == 0)
        {
			setC_Decoris_PreOrder_ID (0);
			setC_Decoris_PreOrderHdr_ID (0);
			setobjprice (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_PreOrderHdr (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_PreOrderHdr[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set appldate.
		@param appldate appldate	  */
	public void setappldate (String appldate)
	{
		set_Value (COLUMNNAME_appldate, appldate);
	}

	/** Get appldate.
		@return appldate	  */
	public String getappldate () 
	{
		return (String)get_Value(COLUMNNAME_appldate);
	}

	/** Set applno.
		@param applno applno	  */
	public void setapplno (String applno)
	{
		set_Value (COLUMNNAME_applno, applno);
	}

	/** Get applno.
		@return applno	  */
	public String getapplno () 
	{
		return (String)get_Value(COLUMNNAME_applno);
	}

	/** Set birthdate.
		@param birthdate birthdate	  */
	public void setbirthdate (String birthdate)
	{
		set_Value (COLUMNNAME_birthdate, birthdate);
	}

	/** Get birthdate.
		@return birthdate	  */
	public String getbirthdate () 
	{
		return (String)get_Value(COLUMNNAME_birthdate);
	}

	public I_C_Decoris_PreOrder getC_Decoris_PreOrder() throws RuntimeException
    {
		return (I_C_Decoris_PreOrder)MTable.get(getCtx(), I_C_Decoris_PreOrder.Table_Name)
			.getPO(getC_Decoris_PreOrder_ID(), get_TrxName());	}

	/** Set C_Decoris_PreOrder.
		@param C_Decoris_PreOrder_ID C_Decoris_PreOrder	  */
	public void setC_Decoris_PreOrder_ID (int C_Decoris_PreOrder_ID)
	{
		if (C_Decoris_PreOrder_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrder_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrder_ID, Integer.valueOf(C_Decoris_PreOrder_ID));
	}

	/** Get C_Decoris_PreOrder.
		@return C_Decoris_PreOrder	  */
	public int getC_Decoris_PreOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_PreOrderHdr.
		@param C_Decoris_PreOrderHdr_ID C_Decoris_PreOrderHdr	  */
	public void setC_Decoris_PreOrderHdr_ID (int C_Decoris_PreOrderHdr_ID)
	{
		if (C_Decoris_PreOrderHdr_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrderHdr_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrderHdr_ID, Integer.valueOf(C_Decoris_PreOrderHdr_ID));
	}

	/** Get C_Decoris_PreOrderHdr.
		@return C_Decoris_PreOrderHdr	  */
	public int getC_Decoris_PreOrderHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreOrderHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_PreOrderHdr_UU.
		@param C_Decoris_PreOrderHdr_UU C_Decoris_PreOrderHdr_UU	  */
	public void setC_Decoris_PreOrderHdr_UU (String C_Decoris_PreOrderHdr_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrderHdr_UU, C_Decoris_PreOrderHdr_UU);
	}

	/** Get C_Decoris_PreOrderHdr_UU.
		@return C_Decoris_PreOrderHdr_UU	  */
	public String getC_Decoris_PreOrderHdr_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_PreOrderHdr_UU);
	}

	/** Set CreatedDate.
		@param CreatedDate CreatedDate	  */
	public void setCreatedDate (String CreatedDate)
	{
		set_ValueNoCheck (COLUMNNAME_CreatedDate, CreatedDate);
	}

	/** Get CreatedDate.
		@return CreatedDate	  */
	public String getCreatedDate () 
	{
		return (String)get_Value(COLUMNNAME_CreatedDate);
	}

	/** Set custname.
		@param custname custname	  */
	public void setcustname (String custname)
	{
		set_Value (COLUMNNAME_custname, custname);
	}

	/** Get custname.
		@return custname	  */
	public String getcustname () 
	{
		return (String)get_Value(COLUMNNAME_custname);
	}

	/** Set objprice.
		@param objprice objprice	  */
	public void setobjprice (BigDecimal objprice)
	{
		set_Value (COLUMNNAME_objprice, objprice);
	}

	/** Get objprice.
		@return objprice	  */
	public BigDecimal getobjprice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_objprice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set orderidori.
		@param orderidori orderidori	  */
	public void setorderidori (String orderidori)
	{
		set_Value (COLUMNNAME_orderidori, orderidori);
	}

	/** Get orderidori.
		@return orderidori	  */
	public String getorderidori () 
	{
		return (String)get_Value(COLUMNNAME_orderidori);
	}

	/** Set pono.
		@param pono pono	  */
	public void setpono (String pono)
	{
		set_Value (COLUMNNAME_pono, pono);
	}

	/** Get pono.
		@return pono	  */
	public String getpono () 
	{
		return (String)get_Value(COLUMNNAME_pono);
	}

	/** Set state.
		@param state state	  */
	public void setstate (String state)
	{
		set_Value (COLUMNNAME_state, state);
	}

	/** Get state.
		@return state	  */
	public String getstate () 
	{
		return (String)get_Value(COLUMNNAME_state);
	}

	/** Set statusorder.
		@param statusorder statusorder	  */
	public void setstatusorder (String statusorder)
	{
		set_ValueNoCheck (COLUMNNAME_statusorder, statusorder);
	}

	/** Get statusorder.
		@return statusorder	  */
	public String getstatusorder () 
	{
		return (String)get_Value(COLUMNNAME_statusorder);
	}

	/** Set suppcode.
		@param suppcode suppcode	  */
	public void setsuppcode (String suppcode)
	{
		set_Value (COLUMNNAME_suppcode, suppcode);
	}

	/** Get suppcode.
		@return suppcode	  */
	public String getsuppcode () 
	{
		return (String)get_Value(COLUMNNAME_suppcode);
	}

	/** Set timeservice.
		@param timeservice timeservice	  */
	public void settimeservice (String timeservice)
	{
		set_Value (COLUMNNAME_timeservice, timeservice);
	}

	/** Get timeservice.
		@return timeservice	  */
	public String gettimeservice () 
	{
		return (String)get_Value(COLUMNNAME_timeservice);
	}
}