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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for M_Fifapps_Offices
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Offices extends PO implements I_M_Fifapps_Offices, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Offices (Properties ctx, int M_Fifapps_Offices_ID, String trxName)
    {
      super (ctx, M_Fifapps_Offices_ID, trxName);
      /** if (M_Fifapps_Offices_ID == 0)
        {
			setaccspricedevpct (Env.ZERO);
			setbpkbprctime (0);
			setcollincentive (Env.ZERO);
			setdifftime (0);
			setM_Fifapps_Offices_ID (0);
			setprocessgroup (0);
			setprofitdealerflatamt (Env.ZERO);
			setprofitdealerpct (Env.ZERO);
			setvalidpodays (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Offices (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Offices[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set accspricedevpct.
		@param accspricedevpct accspricedevpct	  */
	public void setaccspricedevpct (BigDecimal accspricedevpct)
	{
		set_Value (COLUMNNAME_accspricedevpct, accspricedevpct);
	}

	/** Get accspricedevpct.
		@return accspricedevpct	  */
	public BigDecimal getaccspricedevpct () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_accspricedevpct);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Address 3.
		@param Address3 
		Address Line 3 for the location
	  */
	public void setAddress3 (String Address3)
	{
		set_ValueNoCheck (COLUMNNAME_Address3, Address3);
	}

	/** Get Address 3.
		@return Address Line 3 for the location
	  */
	public String getAddress3 () 
	{
		return (String)get_Value(COLUMNNAME_Address3);
	}

	/** Set bpkbprctime.
		@param bpkbprctime bpkbprctime	  */
	public void setbpkbprctime (int bpkbprctime)
	{
		set_Value (COLUMNNAME_bpkbprctime, Integer.valueOf(bpkbprctime));
	}

	/** Get bpkbprctime.
		@return bpkbprctime	  */
	public int getbpkbprctime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_bpkbprctime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set branchcode.
		@param branchcode branchcode	  */
	public void setbranchcode (String branchcode)
	{
		set_Value (COLUMNNAME_branchcode, branchcode);
	}

	/** Get branchcode.
		@return branchcode	  */
	public String getbranchcode () 
	{
		return (String)get_Value(COLUMNNAME_branchcode);
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

	/** Set collincentive.
		@param collincentive collincentive	  */
	public void setcollincentive (BigDecimal collincentive)
	{
		set_Value (COLUMNNAME_collincentive, collincentive);
	}

	/** Get collincentive.
		@return collincentive	  */
	public BigDecimal getcollincentive () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_collincentive);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set created_fifapps.
		@param created_fifapps created_fifapps	  */
	public void setcreated_fifapps (Timestamp created_fifapps)
	{
		set_ValueNoCheck (COLUMNNAME_created_fifapps, created_fifapps);
	}

	/** Get created_fifapps.
		@return created_fifapps	  */
	public Timestamp getcreated_fifapps () 
	{
		return (Timestamp)get_Value(COLUMNNAME_created_fifapps);
	}

	/** Set createdby_fifapps.
		@param createdby_fifapps createdby_fifapps	  */
	public void setcreatedby_fifapps (String createdby_fifapps)
	{
		set_ValueNoCheck (COLUMNNAME_createdby_fifapps, createdby_fifapps);
	}

	/** Get createdby_fifapps.
		@return createdby_fifapps	  */
	public String getcreatedby_fifapps () 
	{
		return (String)get_Value(COLUMNNAME_createdby_fifapps);
	}

	/** Set difftime.
		@param difftime difftime	  */
	public void setdifftime (int difftime)
	{
		set_Value (COLUMNNAME_difftime, Integer.valueOf(difftime));
	}

	/** Get difftime.
		@return difftime	  */
	public int getdifftime () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_difftime);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set location.
		@param location location	  */
	public void setlocation (String location)
	{
		set_Value (COLUMNNAME_location, location);
	}

	/** Get location.
		@return location	  */
	public String getlocation () 
	{
		return (String)get_Value(COLUMNNAME_location);
	}

	/** Set M_Fifapps_Offices.
		@param M_Fifapps_Offices_ID M_Fifapps_Offices	  */
	public void setM_Fifapps_Offices_ID (int M_Fifapps_Offices_ID)
	{
		if (M_Fifapps_Offices_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Offices_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Offices_ID, Integer.valueOf(M_Fifapps_Offices_ID));
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

	/** Set M_Fifapps_Offices_UU.
		@param M_Fifapps_Offices_UU M_Fifapps_Offices_UU	  */
	public void setM_Fifapps_Offices_UU (String M_Fifapps_Offices_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Offices_UU, M_Fifapps_Offices_UU);
	}

	/** Get M_Fifapps_Offices_UU.
		@return M_Fifapps_Offices_UU	  */
	public String getM_Fifapps_Offices_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Offices_UU);
	}

	/** Set namefull.
		@param namefull namefull	  */
	public void setnamefull (String namefull)
	{
		set_Value (COLUMNNAME_namefull, namefull);
	}

	/** Get namefull.
		@return namefull	  */
	public String getnamefull () 
	{
		return (String)get_Value(COLUMNNAME_namefull);
	}

	/** Set nameshort.
		@param nameshort nameshort	  */
	public void setnameshort (String nameshort)
	{
		set_Value (COLUMNNAME_nameshort, nameshort);
	}

	/** Get nameshort.
		@return nameshort	  */
	public String getnameshort () 
	{
		return (String)get_Value(COLUMNNAME_nameshort);
	}

	/** Set officecategory.
		@param officecategory officecategory	  */
	public void setofficecategory (String officecategory)
	{
		set_Value (COLUMNNAME_officecategory, officecategory);
	}

	/** Get officecategory.
		@return officecategory	  */
	public String getofficecategory () 
	{
		return (String)get_Value(COLUMNNAME_officecategory);
	}

	/** Set officeclasscode.
		@param officeclasscode officeclasscode	  */
	public void setofficeclasscode (String officeclasscode)
	{
		set_Value (COLUMNNAME_officeclasscode, officeclasscode);
	}

	/** Get officeclasscode.
		@return officeclasscode	  */
	public String getofficeclasscode () 
	{
		return (String)get_Value(COLUMNNAME_officeclasscode);
	}

	/** Set officecode.
		@param officecode officecode	  */
	public void setofficecode (String officecode)
	{
		set_Value (COLUMNNAME_officecode, officecode);
	}

	/** Get officecode.
		@return officecode	  */
	public String getofficecode () 
	{
		return (String)get_Value(COLUMNNAME_officecode);
	}

	/** Set officecoderep.
		@param officecoderep officecoderep	  */
	public void setofficecoderep (String officecoderep)
	{
		set_Value (COLUMNNAME_officecoderep, officecoderep);
	}

	/** Get officecoderep.
		@return officecoderep	  */
	public String getofficecoderep () 
	{
		return (String)get_Value(COLUMNNAME_officecoderep);
	}

	/** Set officetype.
		@param officetype officetype	  */
	public void setofficetype (String officetype)
	{
		set_Value (COLUMNNAME_officetype, officetype);
	}

	/** Get officetype.
		@return officetype	  */
	public String getofficetype () 
	{
		return (String)get_Value(COLUMNNAME_officetype);
	}

	/** Set phone1.
		@param phone1 phone1	  */
	public void setphone1 (String phone1)
	{
		set_Value (COLUMNNAME_phone1, phone1);
	}

	/** Get phone1.
		@return phone1	  */
	public String getphone1 () 
	{
		return (String)get_Value(COLUMNNAME_phone1);
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

	/** Set phone3.
		@param phone3 phone3	  */
	public void setphone3 (String phone3)
	{
		set_Value (COLUMNNAME_phone3, phone3);
	}

	/** Get phone3.
		@return phone3	  */
	public String getphone3 () 
	{
		return (String)get_Value(COLUMNNAME_phone3);
	}

	/** Set picaddr1.
		@param picaddr1 picaddr1	  */
	public void setpicaddr1 (String picaddr1)
	{
		set_Value (COLUMNNAME_picaddr1, picaddr1);
	}

	/** Get picaddr1.
		@return picaddr1	  */
	public String getpicaddr1 () 
	{
		return (String)get_Value(COLUMNNAME_picaddr1);
	}

	/** Set picaddr2.
		@param picaddr2 picaddr2	  */
	public void setpicaddr2 (String picaddr2)
	{
		set_Value (COLUMNNAME_picaddr2, picaddr2);
	}

	/** Get picaddr2.
		@return picaddr2	  */
	public String getpicaddr2 () 
	{
		return (String)get_Value(COLUMNNAME_picaddr2);
	}

	/** Set picaddr3.
		@param picaddr3 picaddr3	  */
	public void setpicaddr3 (String picaddr3)
	{
		set_Value (COLUMNNAME_picaddr3, picaddr3);
	}

	/** Get picaddr3.
		@return picaddr3	  */
	public String getpicaddr3 () 
	{
		return (String)get_Value(COLUMNNAME_picaddr3);
	}

	/** Set piccity.
		@param piccity piccity	  */
	public void setpiccity (String piccity)
	{
		set_Value (COLUMNNAME_piccity, piccity);
	}

	/** Get piccity.
		@return piccity	  */
	public String getpiccity () 
	{
		return (String)get_Value(COLUMNNAME_piccity);
	}

	/** Set picfirstname.
		@param picfirstname picfirstname	  */
	public void setpicfirstname (String picfirstname)
	{
		set_Value (COLUMNNAME_picfirstname, picfirstname);
	}

	/** Get picfirstname.
		@return picfirstname	  */
	public String getpicfirstname () 
	{
		return (String)get_Value(COLUMNNAME_picfirstname);
	}

	/** Set piclastname.
		@param piclastname piclastname	  */
	public void setpiclastname (String piclastname)
	{
		set_Value (COLUMNNAME_piclastname, piclastname);
	}

	/** Get piclastname.
		@return piclastname	  */
	public String getpiclastname () 
	{
		return (String)get_Value(COLUMNNAME_piclastname);
	}

	/** Set picphone1.
		@param picphone1 picphone1	  */
	public void setpicphone1 (String picphone1)
	{
		set_Value (COLUMNNAME_picphone1, picphone1);
	}

	/** Get picphone1.
		@return picphone1	  */
	public String getpicphone1 () 
	{
		return (String)get_Value(COLUMNNAME_picphone1);
	}

	/** Set picphone2.
		@param picphone2 picphone2	  */
	public void setpicphone2 (String picphone2)
	{
		set_Value (COLUMNNAME_picphone2, picphone2);
	}

	/** Get picphone2.
		@return picphone2	  */
	public String getpicphone2 () 
	{
		return (String)get_Value(COLUMNNAME_picphone2);
	}

	/** Set picphone3.
		@param picphone3 picphone3	  */
	public void setpicphone3 (String picphone3)
	{
		set_Value (COLUMNNAME_picphone3, picphone3);
	}

	/** Get picphone3.
		@return picphone3	  */
	public String getpicphone3 () 
	{
		return (String)get_Value(COLUMNNAME_picphone3);
	}

	/** Set positioncode.
		@param positioncode positioncode	  */
	public void setpositioncode (String positioncode)
	{
		set_Value (COLUMNNAME_positioncode, positioncode);
	}

	/** Get positioncode.
		@return positioncode	  */
	public String getpositioncode () 
	{
		return (String)get_Value(COLUMNNAME_positioncode);
	}

	/** Set processgroup.
		@param processgroup processgroup	  */
	public void setprocessgroup (int processgroup)
	{
		set_Value (COLUMNNAME_processgroup, Integer.valueOf(processgroup));
	}

	/** Get processgroup.
		@return processgroup	  */
	public int getprocessgroup () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_processgroup);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set profitdealerbygrade.
		@param profitdealerbygrade profitdealerbygrade	  */
	public void setprofitdealerbygrade (String profitdealerbygrade)
	{
		set_Value (COLUMNNAME_profitdealerbygrade, profitdealerbygrade);
	}

	/** Get profitdealerbygrade.
		@return profitdealerbygrade	  */
	public String getprofitdealerbygrade () 
	{
		return (String)get_Value(COLUMNNAME_profitdealerbygrade);
	}

	/** Set profitdealerflatamt.
		@param profitdealerflatamt profitdealerflatamt	  */
	public void setprofitdealerflatamt (BigDecimal profitdealerflatamt)
	{
		set_Value (COLUMNNAME_profitdealerflatamt, profitdealerflatamt);
	}

	/** Get profitdealerflatamt.
		@return profitdealerflatamt	  */
	public BigDecimal getprofitdealerflatamt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_profitdealerflatamt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set profitdealerpct.
		@param profitdealerpct profitdealerpct	  */
	public void setprofitdealerpct (BigDecimal profitdealerpct)
	{
		set_Value (COLUMNNAME_profitdealerpct, profitdealerpct);
	}

	/** Get profitdealerpct.
		@return profitdealerpct	  */
	public BigDecimal getprofitdealerpct () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_profitdealerpct);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set regionalid.
		@param regionalid regionalid	  */
	public void setregionalid (String regionalid)
	{
		set_Value (COLUMNNAME_regionalid, regionalid);
	}

	/** Get regionalid.
		@return regionalid	  */
	public String getregionalid () 
	{
		return (String)get_Value(COLUMNNAME_regionalid);
	}

	/** Set regionalidinsc.
		@param regionalidinsc regionalidinsc	  */
	public void setregionalidinsc (String regionalidinsc)
	{
		set_Value (COLUMNNAME_regionalidinsc, regionalidinsc);
	}

	/** Get regionalidinsc.
		@return regionalidinsc	  */
	public String getregionalidinsc () 
	{
		return (String)get_Value(COLUMNNAME_regionalidinsc);
	}

	/** Set regionarcode.
		@param regionarcode regionarcode	  */
	public void setregionarcode (String regionarcode)
	{
		set_Value (COLUMNNAME_regionarcode, regionarcode);
	}

	/** Get regionarcode.
		@return regionarcode	  */
	public String getregionarcode () 
	{
		return (String)get_Value(COLUMNNAME_regionarcode);
	}

	/** Set statusflag.
		@param statusflag statusflag	  */
	public void setstatusflag (String statusflag)
	{
		set_Value (COLUMNNAME_statusflag, statusflag);
	}

	/** Get statusflag.
		@return statusflag	  */
	public String getstatusflag () 
	{
		return (String)get_Value(COLUMNNAME_statusflag);
	}

	/** Set updateby_fifapps.
		@param updateby_fifapps updateby_fifapps	  */
	public void setupdateby_fifapps (String updateby_fifapps)
	{
		set_Value (COLUMNNAME_updateby_fifapps, updateby_fifapps);
	}

	/** Get updateby_fifapps.
		@return updateby_fifapps	  */
	public String getupdateby_fifapps () 
	{
		return (String)get_Value(COLUMNNAME_updateby_fifapps);
	}

	/** Set updated_fifapps.
		@param updated_fifapps updated_fifapps	  */
	public void setupdated_fifapps (Timestamp updated_fifapps)
	{
		set_Value (COLUMNNAME_updated_fifapps, updated_fifapps);
	}

	/** Get updated_fifapps.
		@return updated_fifapps	  */
	public Timestamp getupdated_fifapps () 
	{
		return (Timestamp)get_Value(COLUMNNAME_updated_fifapps);
	}

	/** Set validpodays.
		@param validpodays validpodays	  */
	public void setvalidpodays (BigDecimal validpodays)
	{
		set_Value (COLUMNNAME_validpodays, validpodays);
	}

	/** Get validpodays.
		@return validpodays	  */
	public BigDecimal getvalidpodays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_validpodays);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}