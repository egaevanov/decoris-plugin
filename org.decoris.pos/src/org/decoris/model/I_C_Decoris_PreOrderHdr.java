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

/** Generated Interface for C_Decoris_PreOrderHdr
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_PreOrderHdr 
{

    /** TableName=C_Decoris_PreOrderHdr */
    public static final String Table_Name = "C_Decoris_PreOrderHdr";

    /** AD_Table_ID=1000200 */
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

    /** Column name appldate */
    public static final String COLUMNNAME_appldate = "appldate";

	/** Set appldate	  */
	public void setappldate (String appldate);

	/** Get appldate	  */
	public String getappldate();

    /** Column name applno */
    public static final String COLUMNNAME_applno = "applno";

	/** Set applno	  */
	public void setapplno (String applno);

	/** Get applno	  */
	public String getapplno();

    /** Column name birthdate */
    public static final String COLUMNNAME_birthdate = "birthdate";

	/** Set birthdate	  */
	public void setbirthdate (String birthdate);

	/** Get birthdate	  */
	public String getbirthdate();

    /** Column name C_Decoris_PreOrder_ID */
    public static final String COLUMNNAME_C_Decoris_PreOrder_ID = "C_Decoris_PreOrder_ID";

	/** Set C_Decoris_PreOrder	  */
	public void setC_Decoris_PreOrder_ID (int C_Decoris_PreOrder_ID);

	/** Get C_Decoris_PreOrder	  */
	public int getC_Decoris_PreOrder_ID();

	public I_C_Decoris_PreOrder getC_Decoris_PreOrder() throws RuntimeException;

    /** Column name C_Decoris_PreOrderHdr_ID */
    public static final String COLUMNNAME_C_Decoris_PreOrderHdr_ID = "C_Decoris_PreOrderHdr_ID";

	/** Set C_Decoris_PreOrderHdr	  */
	public void setC_Decoris_PreOrderHdr_ID (int C_Decoris_PreOrderHdr_ID);

	/** Get C_Decoris_PreOrderHdr	  */
	public int getC_Decoris_PreOrderHdr_ID();

    /** Column name C_Decoris_PreOrderHdr_UU */
    public static final String COLUMNNAME_C_Decoris_PreOrderHdr_UU = "C_Decoris_PreOrderHdr_UU";

	/** Set C_Decoris_PreOrderHdr_UU	  */
	public void setC_Decoris_PreOrderHdr_UU (String C_Decoris_PreOrderHdr_UU);

	/** Get C_Decoris_PreOrderHdr_UU	  */
	public String getC_Decoris_PreOrderHdr_UU();

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

    /** Column name CreatedDate */
    public static final String COLUMNNAME_CreatedDate = "CreatedDate";

	/** Set CreatedDate	  */
	public void setCreatedDate (String CreatedDate);

	/** Get CreatedDate	  */
	public String getCreatedDate();

    /** Column name custname */
    public static final String COLUMNNAME_custname = "custname";

	/** Set custname	  */
	public void setcustname (String custname);

	/** Get custname	  */
	public String getcustname();

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

    /** Column name objprice */
    public static final String COLUMNNAME_objprice = "objprice";

	/** Set objprice	  */
	public void setobjprice (BigDecimal objprice);

	/** Get objprice	  */
	public BigDecimal getobjprice();

    /** Column name orderidori */
    public static final String COLUMNNAME_orderidori = "orderidori";

	/** Set orderidori	  */
	public void setorderidori (String orderidori);

	/** Get orderidori	  */
	public String getorderidori();

    /** Column name pono */
    public static final String COLUMNNAME_pono = "pono";

	/** Set pono	  */
	public void setpono (String pono);

	/** Get pono	  */
	public String getpono();

    /** Column name state */
    public static final String COLUMNNAME_state = "state";

	/** Set state	  */
	public void setstate (String state);

	/** Get state	  */
	public String getstate();

    /** Column name statusorder */
    public static final String COLUMNNAME_statusorder = "statusorder";

	/** Set statusorder	  */
	public void setstatusorder (String statusorder);

	/** Get statusorder	  */
	public String getstatusorder();

    /** Column name suppcode */
    public static final String COLUMNNAME_suppcode = "suppcode";

	/** Set suppcode	  */
	public void setsuppcode (String suppcode);

	/** Get suppcode	  */
	public String getsuppcode();

    /** Column name timeservice */
    public static final String COLUMNNAME_timeservice = "timeservice";

	/** Set timeservice	  */
	public void settimeservice (String timeservice);

	/** Get timeservice	  */
	public String gettimeservice();

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
