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

/** Generated Model for i_order_temp
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_i_order_temp extends PO implements I_i_order_temp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170804L;

    /** Standard Constructor */
    public X_i_order_temp (Properties ctx, int i_order_temp_ID, String trxName)
    {
      super (ctx, i_order_temp_ID, trxName);
      /** if (i_order_temp_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_i_order_temp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_i_order_temp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set I_Order_Temp.
		@param I_Order_Temp_ID I_Order_Temp	  */
	public void setI_Order_Temp_ID (int I_Order_Temp_ID)
	{
		if (I_Order_Temp_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_Order_Temp_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_Order_Temp_ID, Integer.valueOf(I_Order_Temp_ID));
	}

	/** Get I_Order_Temp.
		@return I_Order_Temp	  */
	public int getI_Order_Temp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Order_Temp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set insert_iorder.
		@param insert_iorder insert_iorder	  */
	public void setinsert_iorder (boolean insert_iorder)
	{
		set_Value (COLUMNNAME_insert_iorder, Boolean.valueOf(insert_iorder));
	}

	/** Get insert_iorder.
		@return insert_iorder	  */
	public boolean isinsert_iorder () 
	{
		Object oo = get_Value(COLUMNNAME_insert_iorder);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set orders.
		@param orders orders	  */
	public void setorders (String orders)
	{
		set_Value (COLUMNNAME_orders, orders);
	}

	/** Get orders.
		@return orders	  */
	public String getorders () 
	{
		return (String)get_Value(COLUMNNAME_orders);
	}

	/** Set pos.
		@param pos pos	  */
	public void setpos (String pos)
	{
		set_ValueNoCheck (COLUMNNAME_pos, pos);
	}

	/** Get pos.
		@return pos	  */
	public String getpos () 
	{
		return (String)get_Value(COLUMNNAME_pos);
	}
}