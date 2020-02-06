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

/** Generated Interface for M_Fifapps_Occupations
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_M_Fifapps_Occupations 
{

    /** TableName=M_Fifapps_Occupations */
    public static final String Table_Name = "M_Fifapps_Occupations";

    /** AD_Table_ID=1000180 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name aabcode */
    public static final String COLUMNNAME_aabcode = "aabcode";

	/** Set aabcode	  */
	public void setaabcode (String aabcode);

	/** Get aabcode	  */
	public String getaabcode();

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

    /** Column name bibidangusaha */
    public static final String COLUMNNAME_bibidangusaha = "bibidangusaha";

	/** Set bibidangusaha	  */
	public void setbibidangusaha (String bibidangusaha);

	/** Get bibidangusaha	  */
	public String getbibidangusaha();

    /** Column name bibidangusaharef */
    public static final String COLUMNNAME_bibidangusaharef = "bibidangusaharef";

	/** Set bibidangusaharef	  */
	public void setbibidangusaharef (String bibidangusaharef);

	/** Get bibidangusaharef	  */
	public String getbibidangusaharef();

    /** Column name bigoldebitur */
    public static final String COLUMNNAME_bigoldebitur = "bigoldebitur";

	/** Set bigoldebitur	  */
	public void setbigoldebitur (String bigoldebitur);

	/** Get bigoldebitur	  */
	public String getbigoldebitur();

    /** Column name bigoldebiturref */
    public static final String COLUMNNAME_bigoldebiturref = "bigoldebiturref";

	/** Set bigoldebiturref	  */
	public void setbigoldebiturref (String bigoldebiturref);

	/** Get bigoldebiturref	  */
	public String getbigoldebiturref();

    /** Column name bipekerjaan */
    public static final String COLUMNNAME_bipekerjaan = "bipekerjaan";

	/** Set bipekerjaan	  */
	public void setbipekerjaan (String bipekerjaan);

	/** Get bipekerjaan	  */
	public String getbipekerjaan();

    /** Column name bipekerjaanref */
    public static final String COLUMNNAME_bipekerjaanref = "bipekerjaanref";

	/** Set bipekerjaanref	  */
	public void setbipekerjaanref (String bipekerjaanref);

	/** Get bipekerjaanref	  */
	public String getbipekerjaanref();

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

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

    /** Column name kodebca */
    public static final String COLUMNNAME_kodebca = "kodebca";

	/** Set kodebca	  */
	public void setkodebca (String kodebca);

	/** Get kodebca	  */
	public String getkodebca();

    /** Column name M_Fifapps_Occupations_ID */
    public static final String COLUMNNAME_M_Fifapps_Occupations_ID = "M_Fifapps_Occupations_ID";

	/** Set M_Fifapps_Occupations	  */
	public void setM_Fifapps_Occupations_ID (int M_Fifapps_Occupations_ID);

	/** Get M_Fifapps_Occupations	  */
	public int getM_Fifapps_Occupations_ID();

    /** Column name M_Fifapps_Occupations_UU */
    public static final String COLUMNNAME_M_Fifapps_Occupations_UU = "M_Fifapps_Occupations_UU";

	/** Set M_Fifapps_Occupations_UU	  */
	public void setM_Fifapps_Occupations_UU (String M_Fifapps_Occupations_UU);

	/** Get M_Fifapps_Occupations_UU	  */
	public String getM_Fifapps_Occupations_UU();

    /** Column name mandatory */
    public static final String COLUMNNAME_mandatory = "mandatory";

	/** Set mandatory	  */
	public void setmandatory (String mandatory);

	/** Get mandatory	  */
	public String getmandatory();

    /** Column name mbiprofesi */
    public static final String COLUMNNAME_mbiprofesi = "mbiprofesi";

	/** Set mbiprofesi	  */
	public void setmbiprofesi (String mbiprofesi);

	/** Get mbiprofesi	  */
	public String getmbiprofesi();

    /** Column name ocptcode */
    public static final String COLUMNNAME_ocptcode = "ocptcode";

	/** Set ocptcode	  */
	public void setocptcode (String ocptcode);

	/** Get ocptcode	  */
	public String getocptcode();

    /** Column name ocptcodebca */
    public static final String COLUMNNAME_ocptcodebca = "ocptcodebca";

	/** Set ocptcodebca	  */
	public void setocptcodebca (String ocptcodebca);

	/** Get ocptcodebca	  */
	public String getocptcodebca();

    /** Column name ocptrating */
    public static final String COLUMNNAME_ocptrating = "ocptrating";

	/** Set ocptrating	  */
	public void setocptrating (String ocptrating);

	/** Get ocptrating	  */
	public String getocptrating();

    /** Column name ocpttype */
    public static final String COLUMNNAME_ocpttype = "ocpttype";

	/** Set ocpttype	  */
	public void setocpttype (String ocpttype);

	/** Get ocpttype	  */
	public String getocpttype();

    /** Column name sumberpenghasilan */
    public static final String COLUMNNAME_sumberpenghasilan = "sumberpenghasilan";

	/** Set sumberpenghasilan	  */
	public void setsumberpenghasilan (String sumberpenghasilan);

	/** Get sumberpenghasilan	  */
	public String getsumberpenghasilan();

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

    /** Column name visible */
    public static final String COLUMNNAME_visible = "visible";

	/** Set visible	  */
	public void setvisible (String visible);

	/** Get visible	  */
	public String getvisible();
}
