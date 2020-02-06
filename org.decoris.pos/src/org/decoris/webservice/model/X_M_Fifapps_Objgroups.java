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

/** Generated Model for M_Fifapps_Objgroups
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Objgroups extends PO implements I_M_Fifapps_Objgroups, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Objgroups (Properties ctx, int M_Fifapps_Objgroups_ID, String trxName)
    {
      super (ctx, M_Fifapps_Objgroups_ID, trxName);
      /** if (M_Fifapps_Objgroups_ID == 0)
        {
			setM_Fifapps_Objgroups_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Objgroups (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Objgroups[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set colltype.
		@param colltype colltype	  */
	public void setcolltype (String colltype)
	{
		set_Value (COLUMNNAME_colltype, colltype);
	}

	/** Get colltype.
		@return colltype	  */
	public String getcolltype () 
	{
		return (String)get_Value(COLUMNNAME_colltype);
	}

	/** Set M_Fifapps_Objgroups.
		@param M_Fifapps_Objgroups_ID M_Fifapps_Objgroups	  */
	public void setM_Fifapps_Objgroups_ID (int M_Fifapps_Objgroups_ID)
	{
		if (M_Fifapps_Objgroups_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Objgroups_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Objgroups_ID, Integer.valueOf(M_Fifapps_Objgroups_ID));
	}

	/** Get M_Fifapps_Objgroups.
		@return M_Fifapps_Objgroups	  */
	public int getM_Fifapps_Objgroups_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Objgroups_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_Objgroups_UU.
		@param M_Fifapps_Objgroups_UU M_Fifapps_Objgroups_UU	  */
	public void setM_Fifapps_Objgroups_UU (String M_Fifapps_Objgroups_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Objgroups_UU, M_Fifapps_Objgroups_UU);
	}

	/** Get M_Fifapps_Objgroups_UU.
		@return M_Fifapps_Objgroups_UU	  */
	public String getM_Fifapps_Objgroups_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Objgroups_UU);
	}

	/** Set objdescr.
		@param objdescr objdescr	  */
	public void setobjdescr (String objdescr)
	{
		set_Value (COLUMNNAME_objdescr, objdescr);
	}

	/** Get objdescr.
		@return objdescr	  */
	public String getobjdescr () 
	{
		return (String)get_Value(COLUMNNAME_objdescr);
	}

	/** Set objgrp.
		@param objgrp objgrp	  */
	public void setobjgrp (String objgrp)
	{
		set_Value (COLUMNNAME_objgrp, objgrp);
	}

	/** Get objgrp.
		@return objgrp	  */
	public String getobjgrp () 
	{
		return (String)get_Value(COLUMNNAME_objgrp);
	}
}