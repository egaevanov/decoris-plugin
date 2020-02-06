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

/** Generated Model for M_Fifapps_Kecamatan
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Kecamatan extends PO implements I_M_Fifapps_Kecamatan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Kecamatan (Properties ctx, int M_Fifapps_Kecamatan_ID, String trxName)
    {
      super (ctx, M_Fifapps_Kecamatan_ID, trxName);
      /** if (M_Fifapps_Kecamatan_ID == 0)
        {
			setM_Fifapps_Kecamatan_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Kecamatan (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Kecamatan[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set kecamatan.
		@param kecamatan kecamatan	  */
	public void setkecamatan (String kecamatan)
	{
		set_Value (COLUMNNAME_kecamatan, kecamatan);
	}

	/** Get kecamatan.
		@return kecamatan	  */
	public String getkecamatan () 
	{
		return (String)get_Value(COLUMNNAME_kecamatan);
	}

	/** Set keccode.
		@param keccode keccode	  */
	public void setkeccode (String keccode)
	{
		set_Value (COLUMNNAME_keccode, keccode);
	}

	/** Get keccode.
		@return keccode	  */
	public String getkeccode () 
	{
		return (String)get_Value(COLUMNNAME_keccode);
	}

	/** Set M_Fifapps_Kecamatan.
		@param M_Fifapps_Kecamatan_ID M_Fifapps_Kecamatan	  */
	public void setM_Fifapps_Kecamatan_ID (int M_Fifapps_Kecamatan_ID)
	{
		if (M_Fifapps_Kecamatan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Kecamatan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Kecamatan_ID, Integer.valueOf(M_Fifapps_Kecamatan_ID));
	}

	/** Get M_Fifapps_Kecamatan.
		@return M_Fifapps_Kecamatan	  */
	public int getM_Fifapps_Kecamatan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Kecamatan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_Kecamatan_UU.
		@param M_Fifapps_Kecamatan_UU M_Fifapps_Kecamatan_UU	  */
	public void setM_Fifapps_Kecamatan_UU (String M_Fifapps_Kecamatan_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Kecamatan_UU, M_Fifapps_Kecamatan_UU);
	}

	/** Get M_Fifapps_Kecamatan_UU.
		@return M_Fifapps_Kecamatan_UU	  */
	public String getM_Fifapps_Kecamatan_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Kecamatan_UU);
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