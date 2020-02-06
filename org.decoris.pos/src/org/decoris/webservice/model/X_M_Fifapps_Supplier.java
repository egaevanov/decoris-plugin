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

/** Generated Model for M_Fifapps_Supplier
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Supplier extends PO implements I_M_Fifapps_Supplier, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Supplier (Properties ctx, int M_Fifapps_Supplier_ID, String trxName)
    {
      super (ctx, M_Fifapps_Supplier_ID, trxName);
      /** if (M_Fifapps_Supplier_ID == 0)
        {
			setM_Fifapps_Supplier_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Supplier (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Supplier[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address 1.
		@param Address1 
		Address line 1 for this location
	  */
	public void setAddress1 (String Address1)
	{
		set_ValueNoCheck (COLUMNNAME_Address1, Address1);
	}

	/** Get Address 1.
		@return Address line 1 for this location
	  */
	public String getAddress1 () 
	{
		return (String)get_Value(COLUMNNAME_Address1);
	}

	/** Set Address 2.
		@param Address2 
		Address line 2 for this location
	  */
	public void setAddress2 (String Address2)
	{
		set_ValueNoCheck (COLUMNNAME_Address2, Address2);
	}

	/** Get Address 2.
		@return Address line 2 for this location
	  */
	public String getAddress2 () 
	{
		return (String)get_Value(COLUMNNAME_Address2);
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

	/** Set M_Fifapps_Supplier.
		@param M_Fifapps_Supplier_ID M_Fifapps_Supplier	  */
	public void setM_Fifapps_Supplier_ID (int M_Fifapps_Supplier_ID)
	{
		if (M_Fifapps_Supplier_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Supplier_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Supplier_ID, Integer.valueOf(M_Fifapps_Supplier_ID));
	}

	/** Get M_Fifapps_Supplier.
		@return M_Fifapps_Supplier	  */
	public int getM_Fifapps_Supplier_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Supplier_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_Fifapps_Supplier_UU.
		@param M_Fifapps_Supplier_UU M_Fifapps_Supplier_UU	  */
	public void setM_Fifapps_Supplier_UU (String M_Fifapps_Supplier_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Supplier_UU, M_Fifapps_Supplier_UU);
	}

	/** Get M_Fifapps_Supplier_UU.
		@return M_Fifapps_Supplier_UU	  */
	public String getM_Fifapps_Supplier_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Supplier_UU);
	}

	/** Set provinsi.
		@param provinsi provinsi	  */
	public void setprovinsi (String provinsi)
	{
		set_Value (COLUMNNAME_provinsi, provinsi);
	}

	/** Get provinsi.
		@return provinsi	  */
	public String getprovinsi () 
	{
		return (String)get_Value(COLUMNNAME_provinsi);
	}

	/** Set suppliercode.
		@param suppliercode suppliercode	  */
	public void setsuppliercode (String suppliercode)
	{
		set_Value (COLUMNNAME_suppliercode, suppliercode);
	}

	/** Get suppliercode.
		@return suppliercode	  */
	public String getsuppliercode () 
	{
		return (String)get_Value(COLUMNNAME_suppliercode);
	}

	/** Set suppliercompanyname.
		@param suppliercompanyname suppliercompanyname	  */
	public void setsuppliercompanyname (String suppliercompanyname)
	{
		set_Value (COLUMNNAME_suppliercompanyname, suppliercompanyname);
	}

	/** Get suppliercompanyname.
		@return suppliercompanyname	  */
	public String getsuppliercompanyname () 
	{
		return (String)get_Value(COLUMNNAME_suppliercompanyname);
	}

	/** Set suppliercompanytype.
		@param suppliercompanytype suppliercompanytype	  */
	public void setsuppliercompanytype (String suppliercompanytype)
	{
		set_Value (COLUMNNAME_suppliercompanytype, suppliercompanytype);
	}

	/** Get suppliercompanytype.
		@return suppliercompanytype	  */
	public String getsuppliercompanytype () 
	{
		return (String)get_Value(COLUMNNAME_suppliercompanytype);
	}

	/** Set suppliergroupid.
		@param suppliergroupid suppliergroupid	  */
	public void setsuppliergroupid (String suppliergroupid)
	{
		set_Value (COLUMNNAME_suppliergroupid, suppliergroupid);
	}

	/** Get suppliergroupid.
		@return suppliergroupid	  */
	public String getsuppliergroupid () 
	{
		return (String)get_Value(COLUMNNAME_suppliergroupid);
	}

	/** Set suppliermaindealer.
		@param suppliermaindealer suppliermaindealer	  */
	public void setsuppliermaindealer (String suppliermaindealer)
	{
		set_Value (COLUMNNAME_suppliermaindealer, suppliermaindealer);
	}

	/** Get suppliermaindealer.
		@return suppliermaindealer	  */
	public String getsuppliermaindealer () 
	{
		return (String)get_Value(COLUMNNAME_suppliermaindealer);
	}

	/** Set suppliername.
		@param suppliername suppliername	  */
	public void setsuppliername (String suppliername)
	{
		set_Value (COLUMNNAME_suppliername, suppliername);
	}

	/** Get suppliername.
		@return suppliername	  */
	public String getsuppliername () 
	{
		return (String)get_Value(COLUMNNAME_suppliername);
	}

	/** Set supplieroutlettype.
		@param supplieroutlettype supplieroutlettype	  */
	public void setsupplieroutlettype (String supplieroutlettype)
	{
		set_Value (COLUMNNAME_supplieroutlettype, supplieroutlettype);
	}

	/** Get supplieroutlettype.
		@return supplieroutlettype	  */
	public String getsupplieroutlettype () 
	{
		return (String)get_Value(COLUMNNAME_supplieroutlettype);
	}

	/** Set supplierstatus.
		@param supplierstatus supplierstatus	  */
	public void setsupplierstatus (String supplierstatus)
	{
		set_Value (COLUMNNAME_supplierstatus, supplierstatus);
	}

	/** Get supplierstatus.
		@return supplierstatus	  */
	public String getsupplierstatus () 
	{
		return (String)get_Value(COLUMNNAME_supplierstatus);
	}

	/** Set suppliersubtype.
		@param suppliersubtype suppliersubtype	  */
	public void setsuppliersubtype (String suppliersubtype)
	{
		set_Value (COLUMNNAME_suppliersubtype, suppliersubtype);
	}

	/** Get suppliersubtype.
		@return suppliersubtype	  */
	public String getsuppliersubtype () 
	{
		return (String)get_Value(COLUMNNAME_suppliersubtype);
	}

	/** Set suppliertype.
		@param suppliertype suppliertype	  */
	public void setsuppliertype (String suppliertype)
	{
		set_Value (COLUMNNAME_suppliertype, suppliertype);
	}

	/** Get suppliertype.
		@return suppliertype	  */
	public String getsuppliertype () 
	{
		return (String)get_Value(COLUMNNAME_suppliertype);
	}

	/** Set zipcode.
		@param zipcode zipcode	  */
	public void setzipcode (String zipcode)
	{
		set_Value (COLUMNNAME_zipcode, zipcode);
	}

	/** Get zipcode.
		@return zipcode	  */
	public String getzipcode () 
	{
		return (String)get_Value(COLUMNNAME_zipcode);
	}
}