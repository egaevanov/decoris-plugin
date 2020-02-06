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

/** Generated Model for M_Fifapps_City
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_City extends PO implements I_M_Fifapps_City, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_City (Properties ctx, int M_Fifapps_City_ID, String trxName)
    {
      super (ctx, M_Fifapps_City_ID, trxName);
      /** if (M_Fifapps_City_ID == 0)
        {
			setM_Fifapps_City_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_City (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_City[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set City.
		@param City 
		Identifies a City
	  */
	public void setCity (String City)
	{
		set_ValueNoCheck (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
	}

	/** Set citycode.
		@param citycode citycode	  */
	public void setcitycode (String citycode)
	{
		set_Value (COLUMNNAME_citycode, citycode);
	}

	/** Get citycode.
		@return citycode	  */
	public String getcitycode () 
	{
		return (String)get_Value(COLUMNNAME_citycode);
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

	/** Set dati2.
		@param dati2 dati2	  */
	public void setdati2 (String dati2)
	{
		set_Value (COLUMNNAME_dati2, dati2);
	}

	/** Get dati2.
		@return dati2	  */
	public String getdati2 () 
	{
		return (String)get_Value(COLUMNNAME_dati2);
	}

	/** Set digitzip.
		@param digitzip digitzip	  */
	public void setdigitzip (String digitzip)
	{
		set_Value (COLUMNNAME_digitzip, digitzip);
	}

	/** Get digitzip.
		@return digitzip	  */
	public String getdigitzip () 
	{
		return (String)get_Value(COLUMNNAME_digitzip);
	}

	/** Set M_Fifapps_City.
		@param M_Fifapps_City_ID M_Fifapps_City	  */
	public void setM_Fifapps_City_ID (int M_Fifapps_City_ID)
	{
		if (M_Fifapps_City_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_City_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_City_ID, Integer.valueOf(M_Fifapps_City_ID));
	}

	/** Get M_Fifapps_City.
		@return M_Fifapps_City	  */
	public int getM_Fifapps_City_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_City_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_City_UU.
		@param M_Fifapps_City_UU M_Fifapps_City_UU	  */
	public void setM_Fifapps_City_UU (String M_Fifapps_City_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_City_UU, M_Fifapps_City_UU);
	}

	/** Get M_Fifapps_City_UU.
		@return M_Fifapps_City_UU	  */
	public String getM_Fifapps_City_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_City_UU);
	}

	/** Set provcode.
		@param provcode provcode	  */
	public void setprovcode (String provcode)
	{
		set_Value (COLUMNNAME_provcode, provcode);
	}

	/** Get provcode.
		@return provcode	  */
	public String getprovcode () 
	{
		return (String)get_Value(COLUMNNAME_provcode);
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