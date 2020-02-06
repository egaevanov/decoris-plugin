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

/** Generated Interface for M_Fifapps_Objcodes
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_M_Fifapps_Objcodes 
{

    /** TableName=M_Fifapps_Objcodes */
    public static final String Table_Name = "M_Fifapps_Objcodes";

    /** AD_Table_ID=1000178 */
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

    /** Column name BranchID */
    public static final String COLUMNNAME_BranchID = "BranchID";

	/** Set Branch ID.
	  * Bank Branch ID
	  */
	public void setBranchID (String BranchID);

	/** Get Branch ID.
	  * Bank Branch ID
	  */
	public String getBranchID();

    /** Column name categoryid */
    public static final String COLUMNNAME_categoryid = "categoryid";

	/** Set categoryid	  */
	public void setcategoryid (String categoryid);

	/** Get categoryid	  */
	public String getcategoryid();

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

    /** Column name isvehicle */
    public static final String COLUMNNAME_isvehicle = "isvehicle";

	/** Set isvehicle	  */
	public void setisvehicle (String isvehicle);

	/** Get isvehicle	  */
	public String getisvehicle();

    /** Column name M_Fifapps_Objcodes_ID */
    public static final String COLUMNNAME_M_Fifapps_Objcodes_ID = "M_Fifapps_Objcodes_ID";

	/** Set M_Fifapps_Objcodes	  */
	public void setM_Fifapps_Objcodes_ID (int M_Fifapps_Objcodes_ID);

	/** Get M_Fifapps_Objcodes	  */
	public int getM_Fifapps_Objcodes_ID();

    /** Column name M_Fifapps_Objcodes_UU */
    public static final String COLUMNNAME_M_Fifapps_Objcodes_UU = "M_Fifapps_Objcodes_UU";

	/** Set M_Fifapps_Objcodes_UU	  */
	public void setM_Fifapps_Objcodes_UU (String M_Fifapps_Objcodes_UU);

	/** Get M_Fifapps_Objcodes_UU	  */
	public String getM_Fifapps_Objcodes_UU();

    /** Column name needaddcoll */
    public static final String COLUMNNAME_needaddcoll = "needaddcoll";

	/** Set needaddcoll	  */
	public void setneedaddcoll (String needaddcoll);

	/** Get needaddcoll	  */
	public String getneedaddcoll();

    /** Column name newused */
    public static final String COLUMNNAME_newused = "newused";

	/** Set newused	  */
	public void setnewused (String newused);

	/** Get newused	  */
	public String getnewused();

    /** Column name objbrand */
    public static final String COLUMNNAME_objbrand = "objbrand";

	/** Set objbrand	  */
	public void setobjbrand (String objbrand);

	/** Get objbrand	  */
	public String getobjbrand();

    /** Column name objcode */
    public static final String COLUMNNAME_objcode = "objcode";

	/** Set objcode	  */
	public void setobjcode (String objcode);

	/** Get objcode	  */
	public String getobjcode();

    /** Column name objdescr */
    public static final String COLUMNNAME_objdescr = "objdescr";

	/** Set objdescr	  */
	public void setobjdescr (String objdescr);

	/** Get objdescr	  */
	public String getobjdescr();

    /** Column name objgrp */
    public static final String COLUMNNAME_objgrp = "objgrp";

	/** Set objgrp	  */
	public void setobjgrp (String objgrp);

	/** Get objgrp	  */
	public String getobjgrp();

    /** Column name objmake */
    public static final String COLUMNNAME_objmake = "objmake";

	/** Set objmake	  */
	public void setobjmake (String objmake);

	/** Get objmake	  */
	public String getobjmake();

    /** Column name objmodel */
    public static final String COLUMNNAME_objmodel = "objmodel";

	/** Set objmodel	  */
	public void setobjmodel (String objmodel);

	/** Get objmodel	  */
	public String getobjmodel();

    /** Column name objsize */
    public static final String COLUMNNAME_objsize = "objsize";

	/** Set objsize	  */
	public void setobjsize (String objsize);

	/** Get objsize	  */
	public String getobjsize();

    /** Column name objtype */
    public static final String COLUMNNAME_objtype = "objtype";

	/** Set objtype	  */
	public void setobjtype (String objtype);

	/** Get objtype	  */
	public String getobjtype();

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
}
