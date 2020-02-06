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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for M_Fifapps_Occupations
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_M_Fifapps_Occupations extends PO implements I_M_Fifapps_Occupations, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_M_Fifapps_Occupations (Properties ctx, int M_Fifapps_Occupations_ID, String trxName)
    {
      super (ctx, M_Fifapps_Occupations_ID, trxName);
      /** if (M_Fifapps_Occupations_ID == 0)
        {
			setM_Fifapps_Occupations_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Fifapps_Occupations (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Fifapps_Occupations[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set aabcode.
		@param aabcode aabcode	  */
	public void setaabcode (String aabcode)
	{
		set_Value (COLUMNNAME_aabcode, aabcode);
	}

	/** Get aabcode.
		@return aabcode	  */
	public String getaabcode () 
	{
		return (String)get_Value(COLUMNNAME_aabcode);
	}

	/** Set bibidangusaha.
		@param bibidangusaha bibidangusaha	  */
	public void setbibidangusaha (String bibidangusaha)
	{
		set_Value (COLUMNNAME_bibidangusaha, bibidangusaha);
	}

	/** Get bibidangusaha.
		@return bibidangusaha	  */
	public String getbibidangusaha () 
	{
		return (String)get_Value(COLUMNNAME_bibidangusaha);
	}

	/** Set bibidangusaharef.
		@param bibidangusaharef bibidangusaharef	  */
	public void setbibidangusaharef (String bibidangusaharef)
	{
		set_Value (COLUMNNAME_bibidangusaharef, bibidangusaharef);
	}

	/** Get bibidangusaharef.
		@return bibidangusaharef	  */
	public String getbibidangusaharef () 
	{
		return (String)get_Value(COLUMNNAME_bibidangusaharef);
	}

	/** Set bigoldebitur.
		@param bigoldebitur bigoldebitur	  */
	public void setbigoldebitur (String bigoldebitur)
	{
		set_Value (COLUMNNAME_bigoldebitur, bigoldebitur);
	}

	/** Get bigoldebitur.
		@return bigoldebitur	  */
	public String getbigoldebitur () 
	{
		return (String)get_Value(COLUMNNAME_bigoldebitur);
	}

	/** Set bigoldebiturref.
		@param bigoldebiturref bigoldebiturref	  */
	public void setbigoldebiturref (String bigoldebiturref)
	{
		set_Value (COLUMNNAME_bigoldebiturref, bigoldebiturref);
	}

	/** Get bigoldebiturref.
		@return bigoldebiturref	  */
	public String getbigoldebiturref () 
	{
		return (String)get_Value(COLUMNNAME_bigoldebiturref);
	}

	/** Set bipekerjaan.
		@param bipekerjaan bipekerjaan	  */
	public void setbipekerjaan (String bipekerjaan)
	{
		set_Value (COLUMNNAME_bipekerjaan, bipekerjaan);
	}

	/** Get bipekerjaan.
		@return bipekerjaan	  */
	public String getbipekerjaan () 
	{
		return (String)get_Value(COLUMNNAME_bipekerjaan);
	}

	/** Set bipekerjaanref.
		@param bipekerjaanref bipekerjaanref	  */
	public void setbipekerjaanref (String bipekerjaanref)
	{
		set_Value (COLUMNNAME_bipekerjaanref, bipekerjaanref);
	}

	/** Get bipekerjaanref.
		@return bipekerjaanref	  */
	public String getbipekerjaanref () 
	{
		return (String)get_Value(COLUMNNAME_bipekerjaanref);
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

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set kodebca.
		@param kodebca kodebca	  */
	public void setkodebca (String kodebca)
	{
		set_Value (COLUMNNAME_kodebca, kodebca);
	}

	/** Get kodebca.
		@return kodebca	  */
	public String getkodebca () 
	{
		return (String)get_Value(COLUMNNAME_kodebca);
	}

	/** Set M_Fifapps_Occupations.
		@param M_Fifapps_Occupations_ID M_Fifapps_Occupations	  */
	public void setM_Fifapps_Occupations_ID (int M_Fifapps_Occupations_ID)
	{
		if (M_Fifapps_Occupations_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Occupations_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Fifapps_Occupations_ID, Integer.valueOf(M_Fifapps_Occupations_ID));
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

	/** Set M_Fifapps_Occupations_UU.
		@param M_Fifapps_Occupations_UU M_Fifapps_Occupations_UU	  */
	public void setM_Fifapps_Occupations_UU (String M_Fifapps_Occupations_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Fifapps_Occupations_UU, M_Fifapps_Occupations_UU);
	}

	/** Get M_Fifapps_Occupations_UU.
		@return M_Fifapps_Occupations_UU	  */
	public String getM_Fifapps_Occupations_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Fifapps_Occupations_UU);
	}

	/** Set mandatory.
		@param mandatory mandatory	  */
	public void setmandatory (String mandatory)
	{
		set_Value (COLUMNNAME_mandatory, mandatory);
	}

	/** Get mandatory.
		@return mandatory	  */
	public String getmandatory () 
	{
		return (String)get_Value(COLUMNNAME_mandatory);
	}

	/** Set mbiprofesi.
		@param mbiprofesi mbiprofesi	  */
	public void setmbiprofesi (String mbiprofesi)
	{
		set_Value (COLUMNNAME_mbiprofesi, mbiprofesi);
	}

	/** Get mbiprofesi.
		@return mbiprofesi	  */
	public String getmbiprofesi () 
	{
		return (String)get_Value(COLUMNNAME_mbiprofesi);
	}

	/** Set ocptcode.
		@param ocptcode ocptcode	  */
	public void setocptcode (String ocptcode)
	{
		set_Value (COLUMNNAME_ocptcode, ocptcode);
	}

	/** Get ocptcode.
		@return ocptcode	  */
	public String getocptcode () 
	{
		return (String)get_Value(COLUMNNAME_ocptcode);
	}

	/** Set ocptcodebca.
		@param ocptcodebca ocptcodebca	  */
	public void setocptcodebca (String ocptcodebca)
	{
		set_Value (COLUMNNAME_ocptcodebca, ocptcodebca);
	}

	/** Get ocptcodebca.
		@return ocptcodebca	  */
	public String getocptcodebca () 
	{
		return (String)get_Value(COLUMNNAME_ocptcodebca);
	}

	/** Set ocptrating.
		@param ocptrating ocptrating	  */
	public void setocptrating (String ocptrating)
	{
		set_Value (COLUMNNAME_ocptrating, ocptrating);
	}

	/** Get ocptrating.
		@return ocptrating	  */
	public String getocptrating () 
	{
		return (String)get_Value(COLUMNNAME_ocptrating);
	}

	/** Set ocpttype.
		@param ocpttype ocpttype	  */
	public void setocpttype (String ocpttype)
	{
		set_Value (COLUMNNAME_ocpttype, ocpttype);
	}

	/** Get ocpttype.
		@return ocpttype	  */
	public String getocpttype () 
	{
		return (String)get_Value(COLUMNNAME_ocpttype);
	}

	/** Set sumberpenghasilan.
		@param sumberpenghasilan sumberpenghasilan	  */
	public void setsumberpenghasilan (String sumberpenghasilan)
	{
		set_Value (COLUMNNAME_sumberpenghasilan, sumberpenghasilan);
	}

	/** Get sumberpenghasilan.
		@return sumberpenghasilan	  */
	public String getsumberpenghasilan () 
	{
		return (String)get_Value(COLUMNNAME_sumberpenghasilan);
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

	/** Set visible.
		@param visible visible	  */
	public void setvisible (String visible)
	{
		set_Value (COLUMNNAME_visible, visible);
	}

	/** Get visible.
		@return visible	  */
	public String getvisible () 
	{
		return (String)get_Value(COLUMNNAME_visible);
	}
}