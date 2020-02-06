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
package org.decoris.webservice.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Fifapps_Supplier
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_M_Fifapps_Supplier 
{

    /** TableName=M_Fifapps_Supplier */
    public static final String Table_Name = "M_Fifapps_Supplier";

    /** AD_Table_ID=1000182 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name M_Fifapps_Supplier_ID */
    public static final String COLUMNNAME_M_Fifapps_Supplier_ID = "M_Fifapps_Supplier_ID";

	/** Set M_Fifapps_Supplier	  */
	public void setM_Fifapps_Supplier_ID (int M_Fifapps_Supplier_ID);

	/** Get M_Fifapps_Supplier	  */
	public int getM_Fifapps_Supplier_ID();

    /** Column name M_Fifapps_Supplier_UU */
    public static final String COLUMNNAME_M_Fifapps_Supplier_UU = "M_Fifapps_Supplier_UU";

	/** Set M_Fifapps_Supplier_UU	  */
	public void setM_Fifapps_Supplier_UU (String M_Fifapps_Supplier_UU);

	/** Get M_Fifapps_Supplier_UU	  */
	public String getM_Fifapps_Supplier_UU();

    /** Column name provinsi */
    public static final String COLUMNNAME_provinsi = "provinsi";

	/** Set provinsi	  */
	public void setprovinsi (String provinsi);

	/** Get provinsi	  */
	public String getprovinsi();

    /** Column name suppliercode */
    public static final String COLUMNNAME_suppliercode = "suppliercode";

	/** Set suppliercode	  */
	public void setsuppliercode (String suppliercode);

	/** Get suppliercode	  */
	public String getsuppliercode();

    /** Column name suppliercompanyname */
    public static final String COLUMNNAME_suppliercompanyname = "suppliercompanyname";

	/** Set suppliercompanyname	  */
	public void setsuppliercompanyname (String suppliercompanyname);

	/** Get suppliercompanyname	  */
	public String getsuppliercompanyname();

    /** Column name suppliercompanytype */
    public static final String COLUMNNAME_suppliercompanytype = "suppliercompanytype";

	/** Set suppliercompanytype	  */
	public void setsuppliercompanytype (String suppliercompanytype);

	/** Get suppliercompanytype	  */
	public String getsuppliercompanytype();

    /** Column name suppliergroupid */
    public static final String COLUMNNAME_suppliergroupid = "suppliergroupid";

	/** Set suppliergroupid	  */
	public void setsuppliergroupid (String suppliergroupid);

	/** Get suppliergroupid	  */
	public String getsuppliergroupid();

    /** Column name suppliermaindealer */
    public static final String COLUMNNAME_suppliermaindealer = "suppliermaindealer";

	/** Set suppliermaindealer	  */
	public void setsuppliermaindealer (String suppliermaindealer);

	/** Get suppliermaindealer	  */
	public String getsuppliermaindealer();

    /** Column name suppliername */
    public static final String COLUMNNAME_suppliername = "suppliername";

	/** Set suppliername	  */
	public void setsuppliername (String suppliername);

	/** Get suppliername	  */
	public String getsuppliername();

    /** Column name supplieroutlettype */
    public static final String COLUMNNAME_supplieroutlettype = "supplieroutlettype";

	/** Set supplieroutlettype	  */
	public void setsupplieroutlettype (String supplieroutlettype);

	/** Get supplieroutlettype	  */
	public String getsupplieroutlettype();

    /** Column name supplierstatus */
    public static final String COLUMNNAME_supplierstatus = "supplierstatus";

	/** Set supplierstatus	  */
	public void setsupplierstatus (String supplierstatus);

	/** Get supplierstatus	  */
	public String getsupplierstatus();

    /** Column name suppliersubtype */
    public static final String COLUMNNAME_suppliersubtype = "suppliersubtype";

	/** Set suppliersubtype	  */
	public void setsuppliersubtype (String suppliersubtype);

	/** Get suppliersubtype	  */
	public String getsuppliersubtype();

    /** Column name suppliertype */
    public static final String COLUMNNAME_suppliertype = "suppliertype";

	/** Set suppliertype	  */
	public void setsuppliertype (String suppliertype);

	/** Get suppliertype	  */
	public String getsuppliertype();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name zipcode */
    public static final String COLUMNNAME_zipcode = "zipcode";

	/** Set zipcode	  */
	public void setzipcode (String zipcode);

	/** Get zipcode	  */
	public String getzipcode();
}
