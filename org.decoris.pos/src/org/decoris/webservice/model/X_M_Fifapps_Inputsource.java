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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for M_Fifapps_Inputsource
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Inputsource extends PO implements I_M_Fifapps_Inputsource, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Inputsource (Properties ctx, int M_Fifapps_Inputsource_ID, String trxName)
    {
      super (ctx, M_Fifapps_Inputsource_ID, trxName);
      /** if (M_Fifapps_Inputsource_ID == 0)
        {
			setM_Fifapps_Inputsource_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Inputsource (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Inputsource[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set branchtyperight.
		@param branchtyperight branchtyperight	  */
	public void setbranchtyperight (String branchtyperight)
	{
		set_Value (COLUMNNAME_branchtyperight, branchtyperight);
	}

	/** Get branchtyperight.
		@return branchtyperight	  */
	public String getbranchtyperight () 
	{
		return (String)get_Value(COLUMNNAME_branchtyperight);
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

	/** Set created_fifapps.
		@param created_fifapps created_fifapps	  */
	public void setcreated_fifapps (Timestamp created_fifapps)
	{
		set_ValueNoCheck (COLUMNNAME_created_fifapps, created_fifapps);
	}

	/** Get created_fifapps.
		@return created_fifapps	  */
	public Timestamp getcreated_fifapps () 
	{
		return (Timestamp)get_Value(COLUMNNAME_created_fifapps);
	}

	/** Set createdby_fifapps.
		@param createdby_fifapps createdby_fifapps	  */
	public void setcreatedby_fifapps (String createdby_fifapps)
	{
		set_ValueNoCheck (COLUMNNAME_createdby_fifapps, createdby_fifapps);
	}

	/** Get createdby_fifapps.
		@return createdby_fifapps	  */
	public String getcreatedby_fifapps () 
	{
		return (String)get_Value(COLUMNNAME_createdby_fifapps);
	}

	/** Set depositkey.
		@param depositkey depositkey	  */
	public void setdepositkey (String depositkey)
	{
		set_Value (COLUMNNAME_depositkey, depositkey);
	}

	/** Get depositkey.
		@return depositkey	  */
	public String getdepositkey () 
	{
		return (String)get_Value(COLUMNNAME_depositkey);
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

	/** Set M_Fifapps_Inputsource.
		@param M_Fifapps_Inputsource_ID M_Fifapps_Inputsource	  */
	public void setM_Fifapps_Inputsource_ID (int M_Fifapps_Inputsource_ID)
	{
		if (M_Fifapps_Inputsource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Inputsource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Inputsource_ID, Integer.valueOf(M_Fifapps_Inputsource_ID));
	}

	/** Get M_Fifapps_Inputsource.
		@return M_Fifapps_Inputsource	  */
	public int getM_Fifapps_Inputsource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Inputsource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_Inputsource_UU.
		@param M_Fifapps_Inputsource_UU M_Fifapps_Inputsource_UU	  */
	public void setM_Fifapps_Inputsource_UU (String M_Fifapps_Inputsource_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Inputsource_UU, M_Fifapps_Inputsource_UU);
	}

	/** Get M_Fifapps_Inputsource_UU.
		@return M_Fifapps_Inputsource_UU	  */
	public String getM_Fifapps_Inputsource_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Inputsource_UU);
	}

	/** Set shortname.
		@param shortname shortname	  */
	public void setshortname (String shortname)
	{
		set_Value (COLUMNNAME_shortname, shortname);
	}

	/** Get shortname.
		@return shortname	  */
	public String getshortname () 
	{
		return (String)get_Value(COLUMNNAME_shortname);
	}

	/** Set updateby_fifapps.
		@param updateby_fifapps updateby_fifapps	  */
	public void setupdateby_fifapps (String updateby_fifapps)
	{
		set_Value (COLUMNNAME_updateby_fifapps, updateby_fifapps);
	}

	/** Get updateby_fifapps.
		@return updateby_fifapps	  */
	public String getupdateby_fifapps () 
	{
		return (String)get_Value(COLUMNNAME_updateby_fifapps);
	}

	/** Set updated_fifapps.
		@param updated_fifapps updated_fifapps	  */
	public void setupdated_fifapps (Timestamp updated_fifapps)
	{
		set_Value (COLUMNNAME_updated_fifapps, updated_fifapps);
	}

	/** Get updated_fifapps.
		@return updated_fifapps	  */
	public Timestamp getupdated_fifapps () 
	{
		return (Timestamp)get_Value(COLUMNNAME_updated_fifapps);
	}
}