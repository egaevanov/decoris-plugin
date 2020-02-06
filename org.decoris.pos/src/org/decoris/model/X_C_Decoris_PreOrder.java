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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.decoris.webservice.model.I_M_Fifapps_Bussunit;
import org.decoris.webservice.model.I_M_Fifapps_Education;
import org.decoris.webservice.model.I_M_Fifapps_Gender;
import org.decoris.webservice.model.I_M_Fifapps_Housestatus;
import org.decoris.webservice.model.I_M_Fifapps_Inputsource;
import org.decoris.webservice.model.I_M_Fifapps_Maritalstatus;
import org.decoris.webservice.model.I_M_Fifapps_Occupations;
import org.decoris.webservice.model.I_M_Fifapps_Offices;
import org.decoris.webservice.model.I_M_Fifapps_Supplier;
import org.decoris.webservice.model.I_M_Fifapps_Zipcode;

/** Generated Model for C_Decoris_PreOrder
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_PreOrder extends PO implements I_C_Decoris_PreOrder, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180419L;

    /** Standard Constructor */
    public X_C_Decoris_PreOrder (Properties ctx, int C_Decoris_PreOrder_ID, String trxName)
    {
      super (ctx, C_Decoris_PreOrder_ID, trxName);
      /** if (C_Decoris_PreOrder_ID == 0)
        {
			setAddress1 (null);
			setangsuran (Env.ZERO);
			setBirthday (new Timestamp( System.currentTimeMillis() ));
			setbirthplace (null);
			setC_BPartner_ID (0);
			setC_Decoris_PreOrder_ID (0);
			setC_DocType_ID (0);
			setcustrt (null);
			setcustrw (null);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setdpamt (Env.ZERO);
			setM_Fifapps_Bussunit_ID (0);
			setM_Fifapps_Education_ID (0);
			setM_Fifapps_Gender_ID (0);
			setM_Fifapps_Housestatus_ID (0);
			setM_Fifapps_Inputsource_ID (0);
			setM_Fifapps_Maritalstatus_ID (0);
			setM_Fifapps_Occupations_ID (0);
			setM_Fifapps_Offices_ID (0);
			setM_Fifapps_Supplier_ID (0);
			setM_Fifapps_Zipcode_ID (0);
			setM_PriceList_ID (0);
			setmothername (null);
			setPhone (null);
			settop (Env.ZERO);
			settotprodprice (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_PreOrder (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_PreOrder[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address 1.
		@param Address1 
		Address line 1 for this location
	  */
	public void setAddress1 (String Address1)
	{
		set_Value (COLUMNNAME_Address1, Address1);
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
		set_Value (COLUMNNAME_Address2, Address2);
	}

	/** Get Address 2.
		@return Address line 2 for this location
	  */
	public String getAddress2 () 
	{
		return (String)get_Value(COLUMNNAME_Address2);
	}

	/** Set adminfee.
		@param adminfee adminfee	  */
	public void setadminfee (BigDecimal adminfee)
	{
		set_Value (COLUMNNAME_adminfee, adminfee);
	}

	/** Get adminfee.
		@return adminfee	  */
	public BigDecimal getadminfee () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_adminfee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set angsuran.
		@param angsuran angsuran	  */
	public void setangsuran (BigDecimal angsuran)
	{
		set_Value (COLUMNNAME_angsuran, angsuran);
	}

	/** Get angsuran.
		@return angsuran	  */
	public BigDecimal getangsuran () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_angsuran);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Birthday.
		@param Birthday 
		Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	/** Set birthplace.
		@param birthplace birthplace	  */
	public void setbirthplace (String birthplace)
	{
		set_Value (COLUMNNAME_birthplace, birthplace);
	}

	/** Get birthplace.
		@return birthplace	  */
	public String getbirthplace () 
	{
		return (String)get_Value(COLUMNNAME_birthplace);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

	/** Set C_Decoris_PreOrder_UU.
		@param C_Decoris_PreOrder_UU C_Decoris_PreOrder_UU	  */
	public void setC_Decoris_PreOrder_UU (String C_Decoris_PreOrder_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrder_UU, C_Decoris_PreOrder_UU);
	}

	/** Get C_Decoris_PreOrder_UU.
		@return C_Decoris_PreOrder_UU	  */
	public String getC_Decoris_PreOrder_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_PreOrder_UU);
	}

	public I_C_Decoris_PreSales getC_Decoris_PreSales() throws RuntimeException
    {
		return (I_C_Decoris_PreSales)MTable.get(getCtx(), I_C_Decoris_PreSales.Table_Name)
			.getPO(getC_Decoris_PreSales_ID(), get_TrxName());	}

	/** Set Decoris PreSales.
		@param C_Decoris_PreSales_ID Decoris PreSales	  */
	public void setC_Decoris_PreSales_ID (int C_Decoris_PreSales_ID)
	{
		if (C_Decoris_PreSales_ID < 1) 
			set_Value (COLUMNNAME_C_Decoris_PreSales_ID, null);
		else 
			set_Value (COLUMNNAME_C_Decoris_PreSales_ID, Integer.valueOf(C_Decoris_PreSales_ID));
	}

	/** Get Decoris PreSales.
		@return Decoris PreSales	  */
	public int getC_Decoris_PreSales_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreSales_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set custoffext.
		@param custoffext custoffext	  */
	public void setcustoffext (String custoffext)
	{
		set_Value (COLUMNNAME_custoffext, custoffext);
	}

	/** Get custoffext.
		@return custoffext	  */
	public String getcustoffext () 
	{
		return (String)get_Value(COLUMNNAME_custoffext);
	}

	/** Set custofficephone.
		@param custofficephone custofficephone	  */
	public void setcustofficephone (String custofficephone)
	{
		set_Value (COLUMNNAME_custofficephone, custofficephone);
	}

	/** Get custofficephone.
		@return custofficephone	  */
	public String getcustofficephone () 
	{
		return (String)get_Value(COLUMNNAME_custofficephone);
	}

	/** Set custoffpharea.
		@param custoffpharea custoffpharea	  */
	public void setcustoffpharea (String custoffpharea)
	{
		set_Value (COLUMNNAME_custoffpharea, custoffpharea);
	}

	/** Get custoffpharea.
		@return custoffpharea	  */
	public String getcustoffpharea () 
	{
		return (String)get_Value(COLUMNNAME_custoffpharea);
	}

	/** Set custphone.
		@param custphone custphone	  */
	public void setcustphone (String custphone)
	{
		set_Value (COLUMNNAME_custphone, custphone);
	}

	/** Get custphone.
		@return custphone	  */
	public String getcustphone () 
	{
		return (String)get_Value(COLUMNNAME_custphone);
	}

	/** Set custphonearea.
		@param custphonearea custphonearea	  */
	public void setcustphonearea (String custphonearea)
	{
		set_Value (COLUMNNAME_custphonearea, custphonearea);
	}

	/** Get custphonearea.
		@return custphonearea	  */
	public String getcustphonearea () 
	{
		return (String)get_Value(COLUMNNAME_custphonearea);
	}

	/** Set custrt.
		@param custrt custrt	  */
	public void setcustrt (String custrt)
	{
		set_Value (COLUMNNAME_custrt, custrt);
	}

	/** Get custrt.
		@return custrt	  */
	public String getcustrt () 
	{
		return (String)get_Value(COLUMNNAME_custrt);
	}

	/** Set custrw.
		@param custrw custrw	  */
	public void setcustrw (String custrw)
	{
		set_Value (COLUMNNAME_custrw, custrw);
	}

	/** Get custrw.
		@return custrw	  */
	public String getcustrw () 
	{
		return (String)get_Value(COLUMNNAME_custrw);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set domrt.
		@param domrt domrt	  */
	public void setdomrt (String domrt)
	{
		set_Value (COLUMNNAME_domrt, domrt);
	}

	/** Get domrt.
		@return domrt	  */
	public String getdomrt () 
	{
		return (String)get_Value(COLUMNNAME_domrt);
	}

	/** Set domrw.
		@param domrw domrw	  */
	public void setdomrw (String domrw)
	{
		set_Value (COLUMNNAME_domrw, domrw);
	}

	/** Get domrw.
		@return domrw	  */
	public String getdomrw () 
	{
		return (String)get_Value(COLUMNNAME_domrw);
	}

	/** Set dpamt.
		@param dpamt dpamt	  */
	public void setdpamt (BigDecimal dpamt)
	{
		set_Value (COLUMNNAME_dpamt, dpamt);
	}

	/** Get dpamt.
		@return dpamt	  */
	public BigDecimal getdpamt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_dpamt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_M_Fifapps_Bussunit getM_Fifapps_Bussunit() throws RuntimeException
    {
		return (I_M_Fifapps_Bussunit)MTable.get(getCtx(), I_M_Fifapps_Bussunit.Table_Name)
			.getPO(getM_Fifapps_Bussunit_ID(), get_TrxName());	}

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

	public I_M_Fifapps_Education getM_Fifapps_Education() throws RuntimeException
    {
		return (I_M_Fifapps_Education)MTable.get(getCtx(), I_M_Fifapps_Education.Table_Name)
			.getPO(getM_Fifapps_Education_ID(), get_TrxName());	}

	/** Set M_Fifapps_Education.
		@param M_Fifapps_Education_ID M_Fifapps_Education	  */
	public void setM_Fifapps_Education_ID (int M_Fifapps_Education_ID)
	{
		if (M_Fifapps_Education_ID < 1) 
			set_Value (COLUMNNAME_M_Fifapps_Education_ID, null);
		else 
			set_Value (COLUMNNAME_M_Fifapps_Education_ID, Integer.valueOf(M_Fifapps_Education_ID));
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

	public I_M_Fifapps_Gender getM_Fifapps_Gender() throws RuntimeException
    {
		return (I_M_Fifapps_Gender)MTable.get(getCtx(), I_M_Fifapps_Gender.Table_Name)
			.getPO(getM_Fifapps_Gender_ID(), get_TrxName());	}

	/** Set M_Fifapps_Gender.
		@param M_Fifapps_Gender_ID M_Fifapps_Gender	  */
	public void setM_Fifapps_Gender_ID (int M_Fifapps_Gender_ID)
	{
		if (M_Fifapps_Gender_ID < 1) 
			set_Value (COLUMNNAME_M_Fifapps_Gender_ID, null);
		else 
			set_Value (COLUMNNAME_M_Fifapps_Gender_ID, Integer.valueOf(M_Fifapps_Gender_ID));
	}

	/** Get M_Fifapps_Gender.
		@return M_Fifapps_Gender	  */
	public int getM_Fifapps_Gender_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Gender_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Fifapps_Housestatus getM_Fifapps_Housestatus() throws RuntimeException
    {
		return (I_M_Fifapps_Housestatus)MTable.get(getCtx(), I_M_Fifapps_Housestatus.Table_Name)
			.getPO(getM_Fifapps_Housestatus_ID(), get_TrxName());	}

	/** Set M_Fifapps_Housestatus.
		@param M_Fifapps_Housestatus_ID M_Fifapps_Housestatus	  */
	public void setM_Fifapps_Housestatus_ID (int M_Fifapps_Housestatus_ID)
	{
		if (M_Fifapps_Housestatus_ID < 1) 
			set_Value (COLUMNNAME_M_Fifapps_Housestatus_ID, null);
		else 
			set_Value (COLUMNNAME_M_Fifapps_Housestatus_ID, Integer.valueOf(M_Fifapps_Housestatus_ID));
	}

	/** Get M_Fifapps_Housestatus.
		@return M_Fifapps_Housestatus	  */
	public int getM_Fifapps_Housestatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Housestatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Fifapps_Inputsource getM_Fifapps_Inputsource() throws RuntimeException
    {
		return (I_M_Fifapps_Inputsource)MTable.get(getCtx(), I_M_Fifapps_Inputsource.Table_Name)
			.getPO(getM_Fifapps_Inputsource_ID(), get_TrxName());	}

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

	public I_M_Fifapps_Maritalstatus getM_Fifapps_Maritalstatus() throws RuntimeException
    {
		return (I_M_Fifapps_Maritalstatus)MTable.get(getCtx(), I_M_Fifapps_Maritalstatus.Table_Name)
			.getPO(getM_Fifapps_Maritalstatus_ID(), get_TrxName());	}

	/** Set M_Fifapps_Maritalstatus.
		@param M_Fifapps_Maritalstatus_ID M_Fifapps_Maritalstatus	  */
	public void setM_Fifapps_Maritalstatus_ID (int M_Fifapps_Maritalstatus_ID)
	{
		if (M_Fifapps_Maritalstatus_ID < 1) 
			set_Value (COLUMNNAME_M_Fifapps_Maritalstatus_ID, null);
		else 
			set_Value (COLUMNNAME_M_Fifapps_Maritalstatus_ID, Integer.valueOf(M_Fifapps_Maritalstatus_ID));
	}

	/** Get M_Fifapps_Maritalstatus.
		@return M_Fifapps_Maritalstatus	  */
	public int getM_Fifapps_Maritalstatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Maritalstatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Fifapps_Occupations getM_Fifapps_Occupations() throws RuntimeException
    {
		return (I_M_Fifapps_Occupations)MTable.get(getCtx(), I_M_Fifapps_Occupations.Table_Name)
			.getPO(getM_Fifapps_Occupations_ID(), get_TrxName());	}

	/** Set M_Fifapps_Occupations.
		@param M_Fifapps_Occupations_ID M_Fifapps_Occupations	  */
	public void setM_Fifapps_Occupations_ID (int M_Fifapps_Occupations_ID)
	{
		if (M_Fifapps_Occupations_ID < 1) 
			set_Value (COLUMNNAME_M_Fifapps_Occupations_ID, null);
		else 
			set_Value (COLUMNNAME_M_Fifapps_Occupations_ID, Integer.valueOf(M_Fifapps_Occupations_ID));
	}

	/** Get M_Fifapps_Occupations.
		@return M_Fifapps_Occupations	  */
	public int getM_Fifapps_Occupations_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Occupations_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Fifapps_Offices getM_Fifapps_Offices() throws RuntimeException
    {
		return (I_M_Fifapps_Offices)MTable.get(getCtx(), I_M_Fifapps_Offices.Table_Name)
			.getPO(getM_Fifapps_Offices_ID(), get_TrxName());	}

	/** Set M_Fifapps_Offices.
		@param M_Fifapps_Offices_ID M_Fifapps_Offices	  */
	public void setM_Fifapps_Offices_ID (int M_Fifapps_Offices_ID)
	{
		if (M_Fifapps_Offices_ID < 1) 
			set_Value (COLUMNNAME_M_Fifapps_Offices_ID, null);
		else 
			set_Value (COLUMNNAME_M_Fifapps_Offices_ID, Integer.valueOf(M_Fifapps_Offices_ID));
	}

	/** Get M_Fifapps_Offices.
		@return M_Fifapps_Offices	  */
	public int getM_Fifapps_Offices_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Offices_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Fifapps_Supplier getM_Fifapps_Supplier() throws RuntimeException
    {
		return (I_M_Fifapps_Supplier)MTable.get(getCtx(), I_M_Fifapps_Supplier.Table_Name)
			.getPO(getM_Fifapps_Supplier_ID(), get_TrxName());	}

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

	public I_M_Fifapps_Zipcode getM_Fifapps_Zipcode() throws RuntimeException
    {
		return (I_M_Fifapps_Zipcode)MTable.get(getCtx(), I_M_Fifapps_Zipcode.Table_Name)
			.getPO(getM_Fifapps_Zipcode_ID(), get_TrxName());	}

	/** Set M_Fifapps_Zipcode.
		@param M_Fifapps_Zipcode_ID M_Fifapps_Zipcode	  */
	public void setM_Fifapps_Zipcode_ID (int M_Fifapps_Zipcode_ID)
	{
		if (M_Fifapps_Zipcode_ID < 1) 
			set_Value (COLUMNNAME_M_Fifapps_Zipcode_ID, null);
		else 
			set_Value (COLUMNNAME_M_Fifapps_Zipcode_ID, Integer.valueOf(M_Fifapps_Zipcode_ID));
	}

	/** Get M_Fifapps_Zipcode.
		@return M_Fifapps_Zipcode	  */
	public int getM_Fifapps_Zipcode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Fifapps_Zipcode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
			.getPO(getM_PriceList_ID(), get_TrxName());	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Fifapps_Zipcode getM_Zipcode_Dom() throws RuntimeException
    {
		return (I_M_Fifapps_Zipcode)MTable.get(getCtx(), I_M_Fifapps_Zipcode.Table_Name)
			.getPO(getM_Zipcode_Dom_ID(), get_TrxName());	}

	/** Set M_Zipcode_Dom_ID.
		@param M_Zipcode_Dom_ID M_Zipcode_Dom_ID	  */
	public void setM_Zipcode_Dom_ID (int M_Zipcode_Dom_ID)
	{
		if (M_Zipcode_Dom_ID < 1) 
			set_Value (COLUMNNAME_M_Zipcode_Dom_ID, null);
		else 
			set_Value (COLUMNNAME_M_Zipcode_Dom_ID, Integer.valueOf(M_Zipcode_Dom_ID));
	}

	/** Get M_Zipcode_Dom_ID.
		@return M_Zipcode_Dom_ID	  */
	public int getM_Zipcode_Dom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Zipcode_Dom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set mothername.
		@param mothername mothername	  */
	public void setmothername (String mothername)
	{
		set_Value (COLUMNNAME_mothername, mothername);
	}

	/** Get mothername.
		@return mothername	  */
	public String getmothername () 
	{
		return (String)get_Value(COLUMNNAME_mothername);
	}

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set 2nd Phone.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get 2nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}

	/** Set postatus.
		@param postatus postatus	  */
	public void setpostatus (String postatus)
	{
		set_Value (COLUMNNAME_postatus, postatus);
	}

	/** Get postatus.
		@return postatus	  */
	public String getpostatus () 
	{
		return (String)get_Value(COLUMNNAME_postatus);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set processingbast.
		@param processingbast processingbast	  */
	public void setprocessingbast (String processingbast)
	{
		set_Value (COLUMNNAME_processingbast, processingbast);
	}

	/** Get processingbast.
		@return processingbast	  */
	public String getprocessingbast () 
	{
		return (String)get_Value(COLUMNNAME_processingbast);
	}

	/** Set processingpo.
		@param processingpo processingpo	  */
	public void setprocessingpo (String processingpo)
	{
		set_Value (COLUMNNAME_processingpo, processingpo);
	}

	/** Get processingpo.
		@return processingpo	  */
	public String getprocessingpo () 
	{
		return (String)get_Value(COLUMNNAME_processingpo);
	}

	/** Set top.
		@param top top	  */
	public void settop (BigDecimal top)
	{
		set_Value (COLUMNNAME_top, top);
	}

	/** Get top.
		@return top	  */
	public BigDecimal gettop () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_top);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set totprodprice.
		@param totprodprice totprodprice	  */
	public void settotprodprice (BigDecimal totprodprice)
	{
		set_ValueNoCheck (COLUMNNAME_totprodprice, totprodprice);
	}

	/** Get totprodprice.
		@return totprodprice	  */
	public BigDecimal gettotprodprice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_totprodprice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set updatestatus.
		@param updatestatus updatestatus	  */
	public void setupdatestatus (int updatestatus)
	{
		set_Value (COLUMNNAME_updatestatus, Integer.valueOf(updatestatus));
	}

	/** Get updatestatus.
		@return updatestatus	  */
	public int getupdatestatus () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_updatestatus);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}