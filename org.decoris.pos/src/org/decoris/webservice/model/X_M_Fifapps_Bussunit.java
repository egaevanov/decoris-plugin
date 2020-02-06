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
package org.decoris.webservice.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for M_Fifapps_Bussunit
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Bussunit extends PO implements I_M_Fifapps_Bussunit, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Bussunit (Properties ctx, int M_Fifapps_Bussunit_ID, String trxName)
    {
      super (ctx, M_Fifapps_Bussunit_ID, trxName);
      /** if (M_Fifapps_Bussunit_ID == 0)
        {
			setM_Fifapps_Bussunit_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Bussunit (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Bussunit[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set bussunitcode.
		@param bussunitcode bussunitcode	  */
	public void setbussunitcode (String bussunitcode)
	{
		set_Value (COLUMNNAME_bussunitcode, bussunitcode);
	}

	/** Get bussunitcode.
		@return bussunitcode	  */
	public String getbussunitcode () 
	{
		return (String)get_Value(COLUMNNAME_bussunitcode);
	}

	/** Set bussunitname.
		@param bussunitname bussunitname	  */
	public void setbussunitname (String bussunitname)
	{
		set_Value (COLUMNNAME_bussunitname, bussunitname);
	}

	/** Get bussunitname.
		@return bussunitname	  */
	public String getbussunitname () 
	{
		return (String)get_Value(COLUMNNAME_bussunitname);
	}

	/** Set M_Fifapps_Bussunit.
		@param M_Fifapps_Bussunit_ID M_Fifapps_Bussunit	  */
	public void setM_Fifapps_Bussunit_ID (int M_Fifapps_Bussunit_ID)
	{
		if (M_Fifapps_Bussunit_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Bussunit_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Bussunit_ID, Integer.valueOf(M_Fifapps_Bussunit_ID));
	}

	/** Get M_Fifapps_Bussunit.
		@return M_Fifapps_Bussunit	  */
	public int getM_Fifapps_Bussunit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Bussunit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_Bussunit_UU.
		@param M_Fifapps_Bussunit_UU M_Fifapps_Bussunit_UU	  */
	public void setM_Fifapps_Bussunit_UU (String M_Fifapps_Bussunit_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Bussunit_UU, M_Fifapps_Bussunit_UU);
	}

	/** Get M_Fifapps_Bussunit_UU.
		@return M_Fifapps_Bussunit_UU	  */
	public String getM_Fifapps_Bussunit_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Bussunit_UU);
	}
}