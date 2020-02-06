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
package org.decoris.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.*;
import org.compiere.util.KeyNamePair;
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

/** Generated Interface for C_Decoris_PreOrder
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_PreOrder 
{

    /** TableName=C_Decoris_PreOrder */
    public static final String Table_Name = "C_Decoris_PreOrder";

    /** AD_Table_ID=1000198 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name adminfee */
    public static final String COLUMNNAME_adminfee = "adminfee";

	/** Set adminfee	  */
	public void setadminfee (BigDecimal adminfee);

	/** Get adminfee	  */
	public BigDecimal getadminfee();

    /** Column name angsuran */
    public static final String COLUMNNAME_angsuran = "angsuran";

	/** Set angsuran	  */
	public void setangsuran (BigDecimal angsuran);

	/** Get angsuran	  */
	public BigDecimal getangsuran();

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name birthplace */
    public static final String COLUMNNAME_birthplace = "birthplace";

	/** Set birthplace	  */
	public void setbirthplace (String birthplace);

	/** Get birthplace	  */
	public String getbirthplace();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Decoris_PreOrder_ID */
    public static final String COLUMNNAME_C_Decoris_PreOrder_ID = "C_Decoris_PreOrder_ID";

	/** Set C_Decoris_PreOrder	  */
	public void setC_Decoris_PreOrder_ID (int C_Decoris_PreOrder_ID);

	/** Get C_Decoris_PreOrder	  */
	public int getC_Decoris_PreOrder_ID();

    /** Column name C_Decoris_PreOrder_UU */
    public static final String COLUMNNAME_C_Decoris_PreOrder_UU = "C_Decoris_PreOrder_UU";

	/** Set C_Decoris_PreOrder_UU	  */
	public void setC_Decoris_PreOrder_UU (String C_Decoris_PreOrder_UU);

	/** Get C_Decoris_PreOrder_UU	  */
	public String getC_Decoris_PreOrder_UU();

    /** Column name C_Decoris_PreSales_ID */
    public static final String COLUMNNAME_C_Decoris_PreSales_ID = "C_Decoris_PreSales_ID";

	/** Set Decoris PreSales	  */
	public void setC_Decoris_PreSales_ID (int C_Decoris_PreSales_ID);

	/** Get Decoris PreSales	  */
	public int getC_Decoris_PreSales_ID();

	public I_C_Decoris_PreSales getC_Decoris_PreSales() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name custoffext */
    public static final String COLUMNNAME_custoffext = "custoffext";

	/** Set custoffext	  */
	public void setcustoffext (String custoffext);

	/** Get custoffext	  */
	public String getcustoffext();

    /** Column name custofficephone */
    public static final String COLUMNNAME_custofficephone = "custofficephone";

	/** Set custofficephone	  */
	public void setcustofficephone (String custofficephone);

	/** Get custofficephone	  */
	public String getcustofficephone();

    /** Column name custoffpharea */
    public static final String COLUMNNAME_custoffpharea = "custoffpharea";

	/** Set custoffpharea	  */
	public void setcustoffpharea (String custoffpharea);

	/** Get custoffpharea	  */
	public String getcustoffpharea();

    /** Column name custphone */
    public static final String COLUMNNAME_custphone = "custphone";

	/** Set custphone	  */
	public void setcustphone (String custphone);

	/** Get custphone	  */
	public String getcustphone();

    /** Column name custphonearea */
    public static final String COLUMNNAME_custphonearea = "custphonearea";

	/** Set custphonearea	  */
	public void setcustphonearea (String custphonearea);

	/** Get custphonearea	  */
	public String getcustphonearea();

    /** Column name custrt */
    public static final String COLUMNNAME_custrt = "custrt";

	/** Set custrt	  */
	public void setcustrt (String custrt);

	/** Get custrt	  */
	public String getcustrt();

    /** Column name custrw */
    public static final String COLUMNNAME_custrw = "custrw";

	/** Set custrw	  */
	public void setcustrw (String custrw);

	/** Get custrw	  */
	public String getcustrw();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name domrt */
    public static final String COLUMNNAME_domrt = "domrt";

	/** Set domrt	  */
	public void setdomrt (String domrt);

	/** Get domrt	  */
	public String getdomrt();

    /** Column name domrw */
    public static final String COLUMNNAME_domrw = "domrw";

	/** Set domrw	  */
	public void setdomrw (String domrw);

	/** Get domrw	  */
	public String getdomrw();

    /** Column name dpamt */
    public static final String COLUMNNAME_dpamt = "dpamt";

	/** Set dpamt	  */
	public void setdpamt (BigDecimal dpamt);

	/** Get dpamt	  */
	public BigDecimal getdpamt();

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

    /** Column name M_Fifapps_Bussunit_ID */
    public static final String COLUMNNAME_M_Fifapps_Bussunit_ID = "M_Fifapps_Bussunit_ID";

	/** Set M_Fifapps_Bussunit	  */
	public void setM_Fifapps_Bussunit_ID (int M_Fifapps_Bussunit_ID);

	/** Get M_Fifapps_Bussunit	  */
	public int getM_Fifapps_Bussunit_ID();

	public I_M_Fifapps_Bussunit getM_Fifapps_Bussunit() throws RuntimeException;

    /** Column name M_Fifapps_Education_ID */
    public static final String COLUMNNAME_M_Fifapps_Education_ID = "M_Fifapps_Education_ID";

	/** Set M_Fifapps_Education	  */
	public void setM_Fifapps_Education_ID (int M_Fifapps_Education_ID);

	/** Get M_Fifapps_Education	  */
	public int getM_Fifapps_Education_ID();

	public I_M_Fifapps_Education getM_Fifapps_Education() throws RuntimeException;

    /** Column name M_Fifapps_Gender_ID */
    public static final String COLUMNNAME_M_Fifapps_Gender_ID = "M_Fifapps_Gender_ID";

	/** Set M_Fifapps_Gender	  */
	public void setM_Fifapps_Gender_ID (int M_Fifapps_Gender_ID);

	/** Get M_Fifapps_Gender	  */
	public int getM_Fifapps_Gender_ID();

	public I_M_Fifapps_Gender getM_Fifapps_Gender() throws RuntimeException;

    /** Column name M_Fifapps_Housestatus_ID */
    public static final String COLUMNNAME_M_Fifapps_Housestatus_ID = "M_Fifapps_Housestatus_ID";

	/** Set M_Fifapps_Housestatus	  */
	public void setM_Fifapps_Housestatus_ID (int M_Fifapps_Housestatus_ID);

	/** Get M_Fifapps_Housestatus	  */
	public int getM_Fifapps_Housestatus_ID();

	public I_M_Fifapps_Housestatus getM_Fifapps_Housestatus() throws RuntimeException;

    /** Column name M_Fifapps_Inputsource_ID */
    public static final String COLUMNNAME_M_Fifapps_Inputsource_ID = "M_Fifapps_Inputsource_ID";

	/** Set M_Fifapps_Inputsource	  */
	public void setM_Fifapps_Inputsource_ID (int M_Fifapps_Inputsource_ID);

	/** Get M_Fifapps_Inputsource	  */
	public int getM_Fifapps_Inputsource_ID();

	public I_M_Fifapps_Inputsource getM_Fifapps_Inputsource() throws RuntimeException;

    /** Column name M_Fifapps_Maritalstatus_ID */
    public static final String COLUMNNAME_M_Fifapps_Maritalstatus_ID = "M_Fifapps_Maritalstatus_ID";

	/** Set M_Fifapps_Maritalstatus	  */
	public void setM_Fifapps_Maritalstatus_ID (int M_Fifapps_Maritalstatus_ID);

	/** Get M_Fifapps_Maritalstatus	  */
	public int getM_Fifapps_Maritalstatus_ID();

	public I_M_Fifapps_Maritalstatus getM_Fifapps_Maritalstatus() throws RuntimeException;

    /** Column name M_Fifapps_Occupations_ID */
    public static final String COLUMNNAME_M_Fifapps_Occupations_ID = "M_Fifapps_Occupations_ID";

	/** Set M_Fifapps_Occupations	  */
	public void setM_Fifapps_Occupations_ID (int M_Fifapps_Occupations_ID);

	/** Get M_Fifapps_Occupations	  */
	public int getM_Fifapps_Occupations_ID();

	public I_M_Fifapps_Occupations getM_Fifapps_Occupations() throws RuntimeException;

    /** Column name M_Fifapps_Offices_ID */
    public static final String COLUMNNAME_M_Fifapps_Offices_ID = "M_Fifapps_Offices_ID";

	/** Set M_Fifapps_Offices	  */
	public void setM_Fifapps_Offices_ID (int M_Fifapps_Offices_ID);

	/** Get M_Fifapps_Offices	  */
	public int getM_Fifapps_Offices_ID();

	public I_M_Fifapps_Offices getM_Fifapps_Offices() throws RuntimeException;

    /** Column name M_Fifapps_Supplier_ID */
    public static final String COLUMNNAME_M_Fifapps_Supplier_ID = "M_Fifapps_Supplier_ID";

	/** Set M_Fifapps_Supplier	  */
	public void setM_Fifapps_Supplier_ID (int M_Fifapps_Supplier_ID);

	/** Get M_Fifapps_Supplier	  */
	public int getM_Fifapps_Supplier_ID();

	public I_M_Fifapps_Supplier getM_Fifapps_Supplier() throws RuntimeException;

    /** Column name M_Fifapps_Zipcode_ID */
    public static final String COLUMNNAME_M_Fifapps_Zipcode_ID = "M_Fifapps_Zipcode_ID";

	/** Set M_Fifapps_Zipcode	  */
	public void setM_Fifapps_Zipcode_ID (int M_Fifapps_Zipcode_ID);

	/** Get M_Fifapps_Zipcode	  */
	public int getM_Fifapps_Zipcode_ID();

	public I_M_Fifapps_Zipcode getM_Fifapps_Zipcode() throws RuntimeException;

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_Zipcode_Dom_ID */
    public static final String COLUMNNAME_M_Zipcode_Dom_ID = "M_Zipcode_Dom_ID";

	/** Set M_Zipcode_Dom_ID	  */
	public void setM_Zipcode_Dom_ID (int M_Zipcode_Dom_ID);

	/** Get M_Zipcode_Dom_ID	  */
	public int getM_Zipcode_Dom_ID();

	public I_M_Fifapps_Zipcode getM_Zipcode_Dom() throws RuntimeException;

    /** Column name mothername */
    public static final String COLUMNNAME_mothername = "mothername";

	/** Set mothername	  */
	public void setmothername (String mothername);

	/** Get mothername	  */
	public String getmothername();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name postatus */
    public static final String COLUMNNAME_postatus = "postatus";

	/** Set postatus	  */
	public void setpostatus (String postatus);

	/** Get postatus	  */
	public String getpostatus();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name processingbast */
    public static final String COLUMNNAME_processingbast = "processingbast";

	/** Set processingbast	  */
	public void setprocessingbast (String processingbast);

	/** Get processingbast	  */
	public String getprocessingbast();

    /** Column name processingpo */
    public static final String COLUMNNAME_processingpo = "processingpo";

	/** Set processingpo	  */
	public void setprocessingpo (String processingpo);

	/** Get processingpo	  */
	public String getprocessingpo();

    /** Column name top */
    public static final String COLUMNNAME_top = "top";

	/** Set top	  */
	public void settop (BigDecimal top);

	/** Get top	  */
	public BigDecimal gettop();

    /** Column name totprodprice */
    public static final String COLUMNNAME_totprodprice = "totprodprice";

	/** Set totprodprice	  */
	public void settotprodprice (BigDecimal totprodprice);

	/** Get totprodprice	  */
	public BigDecimal gettotprodprice();

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

    /** Column name updatestatus */
    public static final String COLUMNNAME_updatestatus = "updatestatus";

	/** Set updatestatus	  */
	public void setupdatestatus (int updatestatus);

	/** Get updatestatus	  */
	public int getupdatestatus();
}
