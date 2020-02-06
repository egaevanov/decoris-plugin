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

/** Generated Interface for M_Fifapps_Offices
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_M_Fifapps_Offices 
{

    /** TableName=M_Fifapps_Offices */
    public static final String Table_Name = "M_Fifapps_Offices";

    /** AD_Table_ID=1000181 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name accspricedevpct */
    public static final String COLUMNNAME_accspricedevpct = "accspricedevpct";

	/** Set accspricedevpct	  */
	public void setaccspricedevpct (BigDecimal accspricedevpct);

	/** Get accspricedevpct	  */
	public BigDecimal getaccspricedevpct();

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

    /** Column name Address3 */
    public static final String COLUMNNAME_Address3 = "Address3";

	/** Set Address 3.
	  * Address Line 3 for the location
	  */
	public void setAddress3 (String Address3);

	/** Get Address 3.
	  * Address Line 3 for the location
	  */
	public String getAddress3();

    /** Column name bpkbprctime */
    public static final String COLUMNNAME_bpkbprctime = "bpkbprctime";

	/** Set bpkbprctime	  */
	public void setbpkbprctime (int bpkbprctime);

	/** Get bpkbprctime	  */
	public int getbpkbprctime();

    /** Column name branchcode */
    public static final String COLUMNNAME_branchcode = "branchcode";

	/** Set branchcode	  */
	public void setbranchcode (String branchcode);

	/** Get branchcode	  */
	public String getbranchcode();

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

    /** Column name collincentive */
    public static final String COLUMNNAME_collincentive = "collincentive";

	/** Set collincentive	  */
	public void setcollincentive (BigDecimal collincentive);

	/** Get collincentive	  */
	public BigDecimal getcollincentive();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name created_fifapps */
    public static final String COLUMNNAME_created_fifapps = "created_fifapps";

	/** Set created_fifapps	  */
	public void setcreated_fifapps (Timestamp created_fifapps);

	/** Get created_fifapps	  */
	public Timestamp getcreated_fifapps();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name createdby_fifapps */
    public static final String COLUMNNAME_createdby_fifapps = "createdby_fifapps";

	/** Set createdby_fifapps	  */
	public void setcreatedby_fifapps (String createdby_fifapps);

	/** Get createdby_fifapps	  */
	public String getcreatedby_fifapps();

    /** Column name difftime */
    public static final String COLUMNNAME_difftime = "difftime";

	/** Set difftime	  */
	public void setdifftime (int difftime);

	/** Get difftime	  */
	public int getdifftime();

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

    /** Column name location */
    public static final String COLUMNNAME_location = "location";

	/** Set location	  */
	public void setlocation (String location);

	/** Get location	  */
	public String getlocation();

    /** Column name M_Fifapps_Offices_ID */
    public static final String COLUMNNAME_M_Fifapps_Offices_ID = "M_Fifapps_Offices_ID";

	/** Set M_Fifapps_Offices	  */
	public void setM_Fifapps_Offices_ID (int M_Fifapps_Offices_ID);

	/** Get M_Fifapps_Offices	  */
	public int getM_Fifapps_Offices_ID();

    /** Column name M_Fifapps_Offices_UU */
    public static final String COLUMNNAME_M_Fifapps_Offices_UU = "M_Fifapps_Offices_UU";

	/** Set M_Fifapps_Offices_UU	  */
	public void setM_Fifapps_Offices_UU (String M_Fifapps_Offices_UU);

	/** Get M_Fifapps_Offices_UU	  */
	public String getM_Fifapps_Offices_UU();

    /** Column name namefull */
    public static final String COLUMNNAME_namefull = "namefull";

	/** Set namefull	  */
	public void setnamefull (String namefull);

	/** Get namefull	  */
	public String getnamefull();

    /** Column name nameshort */
    public static final String COLUMNNAME_nameshort = "nameshort";

	/** Set nameshort	  */
	public void setnameshort (String nameshort);

	/** Get nameshort	  */
	public String getnameshort();

    /** Column name officecategory */
    public static final String COLUMNNAME_officecategory = "officecategory";

	/** Set officecategory	  */
	public void setofficecategory (String officecategory);

	/** Get officecategory	  */
	public String getofficecategory();

    /** Column name officeclasscode */
    public static final String COLUMNNAME_officeclasscode = "officeclasscode";

	/** Set officeclasscode	  */
	public void setofficeclasscode (String officeclasscode);

	/** Get officeclasscode	  */
	public String getofficeclasscode();

    /** Column name officecode */
    public static final String COLUMNNAME_officecode = "officecode";

	/** Set officecode	  */
	public void setofficecode (String officecode);

	/** Get officecode	  */
	public String getofficecode();

    /** Column name officecoderep */
    public static final String COLUMNNAME_officecoderep = "officecoderep";

	/** Set officecoderep	  */
	public void setofficecoderep (String officecoderep);

	/** Get officecoderep	  */
	public String getofficecoderep();

    /** Column name officetype */
    public static final String COLUMNNAME_officetype = "officetype";

	/** Set officetype	  */
	public void setofficetype (String officetype);

	/** Get officetype	  */
	public String getofficetype();

    /** Column name phone1 */
    public static final String COLUMNNAME_phone1 = "phone1";

	/** Set phone1	  */
	public void setphone1 (String phone1);

	/** Get phone1	  */
	public String getphone1();

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

    /** Column name phone3 */
    public static final String COLUMNNAME_phone3 = "phone3";

	/** Set phone3	  */
	public void setphone3 (String phone3);

	/** Get phone3	  */
	public String getphone3();

    /** Column name picaddr1 */
    public static final String COLUMNNAME_picaddr1 = "picaddr1";

	/** Set picaddr1	  */
	public void setpicaddr1 (String picaddr1);

	/** Get picaddr1	  */
	public String getpicaddr1();

    /** Column name picaddr2 */
    public static final String COLUMNNAME_picaddr2 = "picaddr2";

	/** Set picaddr2	  */
	public void setpicaddr2 (String picaddr2);

	/** Get picaddr2	  */
	public String getpicaddr2();

    /** Column name picaddr3 */
    public static final String COLUMNNAME_picaddr3 = "picaddr3";

	/** Set picaddr3	  */
	public void setpicaddr3 (String picaddr3);

	/** Get picaddr3	  */
	public String getpicaddr3();

    /** Column name piccity */
    public static final String COLUMNNAME_piccity = "piccity";

	/** Set piccity	  */
	public void setpiccity (String piccity);

	/** Get piccity	  */
	public String getpiccity();

    /** Column name picfirstname */
    public static final String COLUMNNAME_picfirstname = "picfirstname";

	/** Set picfirstname	  */
	public void setpicfirstname (String picfirstname);

	/** Get picfirstname	  */
	public String getpicfirstname();

    /** Column name piclastname */
    public static final String COLUMNNAME_piclastname = "piclastname";

	/** Set piclastname	  */
	public void setpiclastname (String piclastname);

	/** Get piclastname	  */
	public String getpiclastname();

    /** Column name picphone1 */
    public static final String COLUMNNAME_picphone1 = "picphone1";

	/** Set picphone1	  */
	public void setpicphone1 (String picphone1);

	/** Get picphone1	  */
	public String getpicphone1();

    /** Column name picphone2 */
    public static final String COLUMNNAME_picphone2 = "picphone2";

	/** Set picphone2	  */
	public void setpicphone2 (String picphone2);

	/** Get picphone2	  */
	public String getpicphone2();

    /** Column name picphone3 */
    public static final String COLUMNNAME_picphone3 = "picphone3";

	/** Set picphone3	  */
	public void setpicphone3 (String picphone3);

	/** Get picphone3	  */
	public String getpicphone3();

    /** Column name positioncode */
    public static final String COLUMNNAME_positioncode = "positioncode";

	/** Set positioncode	  */
	public void setpositioncode (String positioncode);

	/** Get positioncode	  */
	public String getpositioncode();

    /** Column name processgroup */
    public static final String COLUMNNAME_processgroup = "processgroup";

	/** Set processgroup	  */
	public void setprocessgroup (int processgroup);

	/** Get processgroup	  */
	public int getprocessgroup();

    /** Column name profitdealerbygrade */
    public static final String COLUMNNAME_profitdealerbygrade = "profitdealerbygrade";

	/** Set profitdealerbygrade	  */
	public void setprofitdealerbygrade (String profitdealerbygrade);

	/** Get profitdealerbygrade	  */
	public String getprofitdealerbygrade();

    /** Column name profitdealerflatamt */
    public static final String COLUMNNAME_profitdealerflatamt = "profitdealerflatamt";

	/** Set profitdealerflatamt	  */
	public void setprofitdealerflatamt (BigDecimal profitdealerflatamt);

	/** Get profitdealerflatamt	  */
	public BigDecimal getprofitdealerflatamt();

    /** Column name profitdealerpct */
    public static final String COLUMNNAME_profitdealerpct = "profitdealerpct";

	/** Set profitdealerpct	  */
	public void setprofitdealerpct (BigDecimal profitdealerpct);

	/** Get profitdealerpct	  */
	public BigDecimal getprofitdealerpct();

    /** Column name regionalid */
    public static final String COLUMNNAME_regionalid = "regionalid";

	/** Set regionalid	  */
	public void setregionalid (String regionalid);

	/** Get regionalid	  */
	public String getregionalid();

    /** Column name regionalidinsc */
    public static final String COLUMNNAME_regionalidinsc = "regionalidinsc";

	/** Set regionalidinsc	  */
	public void setregionalidinsc (String regionalidinsc);

	/** Get regionalidinsc	  */
	public String getregionalidinsc();

    /** Column name regionarcode */
    public static final String COLUMNNAME_regionarcode = "regionarcode";

	/** Set regionarcode	  */
	public void setregionarcode (String regionarcode);

	/** Get regionarcode	  */
	public String getregionarcode();

    /** Column name statusflag */
    public static final String COLUMNNAME_statusflag = "statusflag";

	/** Set statusflag	  */
	public void setstatusflag (String statusflag);

	/** Get statusflag	  */
	public String getstatusflag();

    /** Column name updateby_fifapps */
    public static final String COLUMNNAME_updateby_fifapps = "updateby_fifapps";

	/** Set updateby_fifapps	  */
	public void setupdateby_fifapps (String updateby_fifapps);

	/** Get updateby_fifapps	  */
	public String getupdateby_fifapps();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name updated_fifapps */
    public static final String COLUMNNAME_updated_fifapps = "updated_fifapps";

	/** Set updated_fifapps	  */
	public void setupdated_fifapps (Timestamp updated_fifapps);

	/** Get updated_fifapps	  */
	public Timestamp getupdated_fifapps();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name validpodays */
    public static final String COLUMNNAME_validpodays = "validpodays";

	/** Set validpodays	  */
	public void setvalidpodays (BigDecimal validpodays);

	/** Get validpodays	  */
	public BigDecimal getvalidpodays();
}
