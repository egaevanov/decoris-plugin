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

/** Generated Interface for M_Fifapps_Kecamatan
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_M_Fifapps_Kecamatan 
{

    /** TableName=M_Fifapps_Kecamatan */
    public static final String Table_Name = "M_Fifapps_Kecamatan";

    /** AD_Table_ID=1000175 */
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

    /** Column name citycode */
    public static final String COLUMNNAME_citycode = "citycode";

	/** Set citycode	  */
	public void setcitycode (String citycode);

	/** Get citycode	  */
	public String getcitycode();

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

    /** Column name kecamatan */
    public static final String COLUMNNAME_kecamatan = "kecamatan";

	/** Set kecamatan	  */
	public void setkecamatan (String kecamatan);

	/** Get kecamatan	  */
	public String getkecamatan();

    /** Column name keccode */
    public static final String COLUMNNAME_keccode = "keccode";

	/** Set keccode	  */
	public void setkeccode (String keccode);

	/** Get keccode	  */
	public String getkeccode();

    /** Column name M_Fifapps_Kecamatan_ID */
    public static final String COLUMNNAME_M_Fifapps_Kecamatan_ID = "M_Fifapps_Kecamatan_ID";

	/** Set M_Fifapps_Kecamatan	  */
	public void setM_Fifapps_Kecamatan_ID (int M_Fifapps_Kecamatan_ID);

	/** Get M_Fifapps_Kecamatan	  */
	public int getM_Fifapps_Kecamatan_ID();

    /** Column name M_Fifapps_Kecamatan_UU */
    public static final String COLUMNNAME_M_Fifapps_Kecamatan_UU = "M_Fifapps_Kecamatan_UU";

	/** Set M_Fifapps_Kecamatan_UU	  */
	public void setM_Fifapps_Kecamatan_UU (String M_Fifapps_Kecamatan_UU);

	/** Get M_Fifapps_Kecamatan_UU	  */
	public String getM_Fifapps_Kecamatan_UU();

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
}
