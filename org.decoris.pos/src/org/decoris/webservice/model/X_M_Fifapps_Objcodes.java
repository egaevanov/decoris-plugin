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

/** Generated Model for M_Fifapps_Objcodes
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Objcodes extends PO implements I_M_Fifapps_Objcodes, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Objcodes (Properties ctx, int M_Fifapps_Objcodes_ID, String trxName)
    {
      super (ctx, M_Fifapps_Objcodes_ID, trxName);
      /** if (M_Fifapps_Objcodes_ID == 0)
        {
			setM_Fifapps_Objcodes_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Objcodes (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Objcodes[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Branch ID.
		@param BranchID 
		Bank Branch ID
	  */
	public void setBranchID (String BranchID)
	{
		set_Value (COLUMNNAME_BranchID, BranchID);
	}

	/** Get Branch ID.
		@return Bank Branch ID
	  */
	public String getBranchID () 
	{
		return (String)get_Value(COLUMNNAME_BranchID);
	}

	/** Set categoryid.
		@param categoryid categoryid	  */
	public void setcategoryid (String categoryid)
	{
		set_Value (COLUMNNAME_categoryid, categoryid);
	}

	/** Get categoryid.
		@return categoryid	  */
	public String getcategoryid () 
	{
		return (String)get_Value(COLUMNNAME_categoryid);
	}

	/** Set isvehicle.
		@param isvehicle isvehicle	  */
	public void setisvehicle (String isvehicle)
	{
		set_Value (COLUMNNAME_isvehicle, isvehicle);
	}

	/** Get isvehicle.
		@return isvehicle	  */
	public String getisvehicle () 
	{
		return (String)get_Value(COLUMNNAME_isvehicle);
	}

	/** Set M_Fifapps_Objcodes.
		@param M_Fifapps_Objcodes_ID M_Fifapps_Objcodes	  */
	public void setM_Fifapps_Objcodes_ID (int M_Fifapps_Objcodes_ID)
	{
		if (M_Fifapps_Objcodes_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Objcodes_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Objcodes_ID, Integer.valueOf(M_Fifapps_Objcodes_ID));
	}

	/** Get M_Fifapps_Objcodes.
		@return M_Fifapps_Objcodes	  */
	public int getM_Fifapps_Objcodes_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Objcodes_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_Objcodes_UU.
		@param M_Fifapps_Objcodes_UU M_Fifapps_Objcodes_UU	  */
	public void setM_Fifapps_Objcodes_UU (String M_Fifapps_Objcodes_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Objcodes_UU, M_Fifapps_Objcodes_UU);
	}

	/** Get M_Fifapps_Objcodes_UU.
		@return M_Fifapps_Objcodes_UU	  */
	public String getM_Fifapps_Objcodes_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Objcodes_UU);
	}

	/** Set needaddcoll.
		@param needaddcoll needaddcoll	  */
	public void setneedaddcoll (String needaddcoll)
	{
		set_Value (COLUMNNAME_needaddcoll, needaddcoll);
	}

	/** Get needaddcoll.
		@return needaddcoll	  */
	public String getneedaddcoll () 
	{
		return (String)get_Value(COLUMNNAME_needaddcoll);
	}

	/** Set newused.
		@param newused newused	  */
	public void setnewused (String newused)
	{
		set_Value (COLUMNNAME_newused, newused);
	}

	/** Get newused.
		@return newused	  */
	public String getnewused () 
	{
		return (String)get_Value(COLUMNNAME_newused);
	}

	/** Set objbrand.
		@param objbrand objbrand	  */
	public void setobjbrand (String objbrand)
	{
		set_Value (COLUMNNAME_objbrand, objbrand);
	}

	/** Get objbrand.
		@return objbrand	  */
	public String getobjbrand () 
	{
		return (String)get_Value(COLUMNNAME_objbrand);
	}

	/** Set objcode.
		@param objcode objcode	  */
	public void setobjcode (String objcode)
	{
		set_Value (COLUMNNAME_objcode, objcode);
	}

	/** Get objcode.
		@return objcode	  */
	public String getobjcode () 
	{
		return (String)get_Value(COLUMNNAME_objcode);
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

	/** Set objmake.
		@param objmake objmake	  */
	public void setobjmake (String objmake)
	{
		set_Value (COLUMNNAME_objmake, objmake);
	}

	/** Get objmake.
		@return objmake	  */
	public String getobjmake () 
	{
		return (String)get_Value(COLUMNNAME_objmake);
	}

	/** Set objmodel.
		@param objmodel objmodel	  */
	public void setobjmodel (String objmodel)
	{
		set_Value (COLUMNNAME_objmodel, objmodel);
	}

	/** Get objmodel.
		@return objmodel	  */
	public String getobjmodel () 
	{
		return (String)get_Value(COLUMNNAME_objmodel);
	}

	/** Set objsize.
		@param objsize objsize	  */
	public void setobjsize (String objsize)
	{
		set_Value (COLUMNNAME_objsize, objsize);
	}

	/** Get objsize.
		@return objsize	  */
	public String getobjsize () 
	{
		return (String)get_Value(COLUMNNAME_objsize);
	}

	/** Set objtype.
		@param objtype objtype	  */
	public void setobjtype (String objtype)
	{
		set_Value (COLUMNNAME_objtype, objtype);
	}

	/** Get objtype.
		@return objtype	  */
	public String getobjtype () 
	{
		return (String)get_Value(COLUMNNAME_objtype);
	}
}