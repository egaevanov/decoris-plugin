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

/** Generated Model for M_Fifapps_Education
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Education extends PO implements I_M_Fifapps_Education, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Education (Properties ctx, int M_Fifapps_Education_ID, String trxName)
    {
      super (ctx, M_Fifapps_Education_ID, trxName);
      /** if (M_Fifapps_Education_ID == 0)
        {
			setM_Fifapps_Education_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Education (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Education[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set aabcode.
		@param aabcode aabcode	  */
	public void setaabcode (String aabcode)
	{
		set_Value (COLUMNNAME_aabcode, aabcode);
	}

	/** Get aabcode.
		@return aabcode	  */
	public String getaabcode () 
	{
		return (String)get_Value(COLUMNNAME_aabcode);
	}

	/** Set bistatus.
		@param bistatus bistatus	  */
	public void setbistatus (String bistatus)
	{
		set_Value (COLUMNNAME_bistatus, bistatus);
	}

	/** Get bistatus.
		@return bistatus	  */
	public String getbistatus () 
	{
		return (String)get_Value(COLUMNNAME_bistatus);
	}

	/** Set bistatusref.
		@param bistatusref bistatusref	  */
	public void setbistatusref (String bistatusref)
	{
		set_Value (COLUMNNAME_bistatusref, bistatusref);
	}

	/** Get bistatusref.
		@return bistatusref	  */
	public String getbistatusref () 
	{
		return (String)get_Value(COLUMNNAME_bistatusref);
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

	/** Set edudescr.
		@param edudescr edudescr	  */
	public void setedudescr (String edudescr)
	{
		set_Value (COLUMNNAME_edudescr, edudescr);
	}

	/** Get edudescr.
		@return edudescr	  */
	public String getedudescr () 
	{
		return (String)get_Value(COLUMNNAME_edudescr);
	}

	/** Set edutype.
		@param edutype edutype	  */
	public void setedutype (String edutype)
	{
		set_Value (COLUMNNAME_edutype, edutype);
	}

	/** Get edutype.
		@return edutype	  */
	public String getedutype () 
	{
		return (String)get_Value(COLUMNNAME_edutype);
	}

	/** Set lippobankcode.
		@param lippobankcode lippobankcode	  */
	public void setlippobankcode (String lippobankcode)
	{
		set_Value (COLUMNNAME_lippobankcode, lippobankcode);
	}

	/** Get lippobankcode.
		@return lippobankcode	  */
	public String getlippobankcode () 
	{
		return (String)get_Value(COLUMNNAME_lippobankcode);
	}

	/** Set M_Fifapps_Education.
		@param M_Fifapps_Education_ID M_Fifapps_Education	  */
	public void setM_Fifapps_Education_ID (int M_Fifapps_Education_ID)
	{
		if (M_Fifapps_Education_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Education_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Education_ID, Integer.valueOf(M_Fifapps_Education_ID));
	}

	/** Get M_Fifapps_Education.
		@return M_Fifapps_Education	  */
	public int getM_Fifapps_Education_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Education_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_Education_UU.
		@param M_Fifapps_Education_UU M_Fifapps_Education_UU	  */
	public void setM_Fifapps_Education_UU (String M_Fifapps_Education_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Education_UU, M_Fifapps_Education_UU);
	}

	/** Get M_Fifapps_Education_UU.
		@return M_Fifapps_Education_UU	  */
	public String getM_Fifapps_Education_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Education_UU);
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

	/** Set visible.
		@param visible visible	  */
	public void setvisible (String visible)
	{
		set_Value (COLUMNNAME_visible, visible);
	}

	/** Get visible.
		@return visible	  */
	public String getvisible () 
	{
		return (String)get_Value(COLUMNNAME_visible);
	}
}