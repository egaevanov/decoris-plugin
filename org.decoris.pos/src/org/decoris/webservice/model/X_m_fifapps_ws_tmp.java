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

/** Generated Model for m_fifapps_ws_tmp
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_m_fifapps_ws_tmp extends PO implements I_m_fifapps_ws_tmp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180425L;

    /** Standard Constructor */
    public X_m_fifapps_ws_tmp (Properties ctx, int m_fifapps_ws_tmp_ID, String trxName)
    {
      super (ctx, m_fifapps_ws_tmp_ID, trxName);
      /** if (m_fifapps_ws_tmp_ID == 0)
        {
			setm_fifapps_source (0);
        } */
    }

    /** Load Constructor */
    public X_m_fifapps_ws_tmp (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_m_fifapps_ws_tmp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set m_fifapps_param_01.
		@param m_fifapps_param_01 m_fifapps_param_01	  */
	public void setm_fifapps_param_01 (String m_fifapps_param_01)
	{
		set_Value (COLUMNNAME_m_fifapps_param_01, m_fifapps_param_01);
	}

	/** Get m_fifapps_param_01.
		@return m_fifapps_param_01	  */
	public String getm_fifapps_param_01 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_01);
	}

	/** Set m_fifapps_param_02.
		@param m_fifapps_param_02 m_fifapps_param_02	  */
	public void setm_fifapps_param_02 (String m_fifapps_param_02)
	{
		set_Value (COLUMNNAME_m_fifapps_param_02, m_fifapps_param_02);
	}

	/** Get m_fifapps_param_02.
		@return m_fifapps_param_02	  */
	public String getm_fifapps_param_02 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_02);
	}

	/** Set m_fifapps_param_03.
		@param m_fifapps_param_03 m_fifapps_param_03	  */
	public void setm_fifapps_param_03 (String m_fifapps_param_03)
	{
		set_Value (COLUMNNAME_m_fifapps_param_03, m_fifapps_param_03);
	}

	/** Get m_fifapps_param_03.
		@return m_fifapps_param_03	  */
	public String getm_fifapps_param_03 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_03);
	}

	/** Set m_fifapps_param_04.
		@param m_fifapps_param_04 m_fifapps_param_04	  */
	public void setm_fifapps_param_04 (String m_fifapps_param_04)
	{
		set_Value (COLUMNNAME_m_fifapps_param_04, m_fifapps_param_04);
	}

	/** Get m_fifapps_param_04.
		@return m_fifapps_param_04	  */
	public String getm_fifapps_param_04 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_04);
	}

	/** Set m_fifapps_param_05.
		@param m_fifapps_param_05 m_fifapps_param_05	  */
	public void setm_fifapps_param_05 (String m_fifapps_param_05)
	{
		set_Value (COLUMNNAME_m_fifapps_param_05, m_fifapps_param_05);
	}

	/** Get m_fifapps_param_05.
		@return m_fifapps_param_05	  */
	public String getm_fifapps_param_05 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_05);
	}

	/** Set m_fifapps_param_06.
		@param m_fifapps_param_06 m_fifapps_param_06	  */
	public void setm_fifapps_param_06 (String m_fifapps_param_06)
	{
		set_Value (COLUMNNAME_m_fifapps_param_06, m_fifapps_param_06);
	}

	/** Get m_fifapps_param_06.
		@return m_fifapps_param_06	  */
	public String getm_fifapps_param_06 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_06);
	}

	/** Set m_fifapps_param_07.
		@param m_fifapps_param_07 m_fifapps_param_07	  */
	public void setm_fifapps_param_07 (String m_fifapps_param_07)
	{
		set_Value (COLUMNNAME_m_fifapps_param_07, m_fifapps_param_07);
	}

	/** Get m_fifapps_param_07.
		@return m_fifapps_param_07	  */
	public String getm_fifapps_param_07 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_07);
	}

	/** Set m_fifapps_param_08.
		@param m_fifapps_param_08 m_fifapps_param_08	  */
	public void setm_fifapps_param_08 (String m_fifapps_param_08)
	{
		set_Value (COLUMNNAME_m_fifapps_param_08, m_fifapps_param_08);
	}

	/** Get m_fifapps_param_08.
		@return m_fifapps_param_08	  */
	public String getm_fifapps_param_08 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_08);
	}

	/** Set m_fifapps_param_09.
		@param m_fifapps_param_09 m_fifapps_param_09	  */
	public void setm_fifapps_param_09 (String m_fifapps_param_09)
	{
		set_Value (COLUMNNAME_m_fifapps_param_09, m_fifapps_param_09);
	}

	/** Get m_fifapps_param_09.
		@return m_fifapps_param_09	  */
	public String getm_fifapps_param_09 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_09);
	}

	/** Set m_fifapps_param_10.
		@param m_fifapps_param_10 m_fifapps_param_10	  */
	public void setm_fifapps_param_10 (String m_fifapps_param_10)
	{
		set_Value (COLUMNNAME_m_fifapps_param_10, m_fifapps_param_10);
	}

	/** Get m_fifapps_param_10.
		@return m_fifapps_param_10	  */
	public String getm_fifapps_param_10 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_10);
	}

	/** Set m_fifapps_param_11.
		@param m_fifapps_param_11 m_fifapps_param_11	  */
	public void setm_fifapps_param_11 (String m_fifapps_param_11)
	{
		set_Value (COLUMNNAME_m_fifapps_param_11, m_fifapps_param_11);
	}

	/** Get m_fifapps_param_11.
		@return m_fifapps_param_11	  */
	public String getm_fifapps_param_11 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_11);
	}

	/** Set m_fifapps_param_12.
		@param m_fifapps_param_12 m_fifapps_param_12	  */
	public void setm_fifapps_param_12 (String m_fifapps_param_12)
	{
		set_Value (COLUMNNAME_m_fifapps_param_12, m_fifapps_param_12);
	}

	/** Get m_fifapps_param_12.
		@return m_fifapps_param_12	  */
	public String getm_fifapps_param_12 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_12);
	}

	/** Set m_fifapps_param_13.
		@param m_fifapps_param_13 m_fifapps_param_13	  */
	public void setm_fifapps_param_13 (String m_fifapps_param_13)
	{
		set_Value (COLUMNNAME_m_fifapps_param_13, m_fifapps_param_13);
	}

	/** Get m_fifapps_param_13.
		@return m_fifapps_param_13	  */
	public String getm_fifapps_param_13 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_13);
	}

	/** Set m_fifapps_param_14.
		@param m_fifapps_param_14 m_fifapps_param_14	  */
	public void setm_fifapps_param_14 (String m_fifapps_param_14)
	{
		set_Value (COLUMNNAME_m_fifapps_param_14, m_fifapps_param_14);
	}

	/** Get m_fifapps_param_14.
		@return m_fifapps_param_14	  */
	public String getm_fifapps_param_14 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_14);
	}

	/** Set m_fifapps_param_15.
		@param m_fifapps_param_15 m_fifapps_param_15	  */
	public void setm_fifapps_param_15 (String m_fifapps_param_15)
	{
		set_Value (COLUMNNAME_m_fifapps_param_15, m_fifapps_param_15);
	}

	/** Get m_fifapps_param_15.
		@return m_fifapps_param_15	  */
	public String getm_fifapps_param_15 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_15);
	}

	/** Set m_fifapps_param_16.
		@param m_fifapps_param_16 m_fifapps_param_16	  */
	public void setm_fifapps_param_16 (String m_fifapps_param_16)
	{
		set_Value (COLUMNNAME_m_fifapps_param_16, m_fifapps_param_16);
	}

	/** Get m_fifapps_param_16.
		@return m_fifapps_param_16	  */
	public String getm_fifapps_param_16 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_16);
	}

	/** Set m_fifapps_param_17.
		@param m_fifapps_param_17 m_fifapps_param_17	  */
	public void setm_fifapps_param_17 (String m_fifapps_param_17)
	{
		set_Value (COLUMNNAME_m_fifapps_param_17, m_fifapps_param_17);
	}

	/** Get m_fifapps_param_17.
		@return m_fifapps_param_17	  */
	public String getm_fifapps_param_17 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_17);
	}

	/** Set m_fifapps_param_18.
		@param m_fifapps_param_18 m_fifapps_param_18	  */
	public void setm_fifapps_param_18 (String m_fifapps_param_18)
	{
		set_Value (COLUMNNAME_m_fifapps_param_18, m_fifapps_param_18);
	}

	/** Get m_fifapps_param_18.
		@return m_fifapps_param_18	  */
	public String getm_fifapps_param_18 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_18);
	}

	/** Set m_fifapps_param_19.
		@param m_fifapps_param_19 m_fifapps_param_19	  */
	public void setm_fifapps_param_19 (String m_fifapps_param_19)
	{
		set_Value (COLUMNNAME_m_fifapps_param_19, m_fifapps_param_19);
	}

	/** Get m_fifapps_param_19.
		@return m_fifapps_param_19	  */
	public String getm_fifapps_param_19 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_19);
	}

	/** Set m_fifapps_param_20.
		@param m_fifapps_param_20 m_fifapps_param_20	  */
	public void setm_fifapps_param_20 (String m_fifapps_param_20)
	{
		set_Value (COLUMNNAME_m_fifapps_param_20, m_fifapps_param_20);
	}

	/** Get m_fifapps_param_20.
		@return m_fifapps_param_20	  */
	public String getm_fifapps_param_20 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_20);
	}

	/** Set m_fifapps_param_21.
		@param m_fifapps_param_21 m_fifapps_param_21	  */
	public void setm_fifapps_param_21 (String m_fifapps_param_21)
	{
		set_Value (COLUMNNAME_m_fifapps_param_21, m_fifapps_param_21);
	}

	/** Get m_fifapps_param_21.
		@return m_fifapps_param_21	  */
	public String getm_fifapps_param_21 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_21);
	}

	/** Set m_fifapps_param_22.
		@param m_fifapps_param_22 m_fifapps_param_22	  */
	public void setm_fifapps_param_22 (String m_fifapps_param_22)
	{
		set_Value (COLUMNNAME_m_fifapps_param_22, m_fifapps_param_22);
	}

	/** Get m_fifapps_param_22.
		@return m_fifapps_param_22	  */
	public String getm_fifapps_param_22 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_22);
	}

	/** Set m_fifapps_param_23.
		@param m_fifapps_param_23 m_fifapps_param_23	  */
	public void setm_fifapps_param_23 (String m_fifapps_param_23)
	{
		set_Value (COLUMNNAME_m_fifapps_param_23, m_fifapps_param_23);
	}

	/** Get m_fifapps_param_23.
		@return m_fifapps_param_23	  */
	public String getm_fifapps_param_23 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_23);
	}

	/** Set m_fifapps_param_24.
		@param m_fifapps_param_24 m_fifapps_param_24	  */
	public void setm_fifapps_param_24 (String m_fifapps_param_24)
	{
		set_Value (COLUMNNAME_m_fifapps_param_24, m_fifapps_param_24);
	}

	/** Get m_fifapps_param_24.
		@return m_fifapps_param_24	  */
	public String getm_fifapps_param_24 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_24);
	}

	/** Set m_fifapps_param_25.
		@param m_fifapps_param_25 m_fifapps_param_25	  */
	public void setm_fifapps_param_25 (String m_fifapps_param_25)
	{
		set_Value (COLUMNNAME_m_fifapps_param_25, m_fifapps_param_25);
	}

	/** Get m_fifapps_param_25.
		@return m_fifapps_param_25	  */
	public String getm_fifapps_param_25 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_25);
	}

	/** Set m_fifapps_param_26.
		@param m_fifapps_param_26 m_fifapps_param_26	  */
	public void setm_fifapps_param_26 (String m_fifapps_param_26)
	{
		set_Value (COLUMNNAME_m_fifapps_param_26, m_fifapps_param_26);
	}

	/** Get m_fifapps_param_26.
		@return m_fifapps_param_26	  */
	public String getm_fifapps_param_26 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_26);
	}

	/** Set m_fifapps_param_27.
		@param m_fifapps_param_27 m_fifapps_param_27	  */
	public void setm_fifapps_param_27 (String m_fifapps_param_27)
	{
		set_Value (COLUMNNAME_m_fifapps_param_27, m_fifapps_param_27);
	}

	/** Get m_fifapps_param_27.
		@return m_fifapps_param_27	  */
	public String getm_fifapps_param_27 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_27);
	}

	/** Set m_fifapps_param_28.
		@param m_fifapps_param_28 m_fifapps_param_28	  */
	public void setm_fifapps_param_28 (String m_fifapps_param_28)
	{
		set_Value (COLUMNNAME_m_fifapps_param_28, m_fifapps_param_28);
	}

	/** Get m_fifapps_param_28.
		@return m_fifapps_param_28	  */
	public String getm_fifapps_param_28 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_28);
	}

	/** Set m_fifapps_param_29.
		@param m_fifapps_param_29 m_fifapps_param_29	  */
	public void setm_fifapps_param_29 (String m_fifapps_param_29)
	{
		set_Value (COLUMNNAME_m_fifapps_param_29, m_fifapps_param_29);
	}

	/** Get m_fifapps_param_29.
		@return m_fifapps_param_29	  */
	public String getm_fifapps_param_29 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_29);
	}

	/** Set m_fifapps_param_30.
		@param m_fifapps_param_30 m_fifapps_param_30	  */
	public void setm_fifapps_param_30 (String m_fifapps_param_30)
	{
		set_Value (COLUMNNAME_m_fifapps_param_30, m_fifapps_param_30);
	}

	/** Get m_fifapps_param_30.
		@return m_fifapps_param_30	  */
	public String getm_fifapps_param_30 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_30);
	}

	/** Set m_fifapps_param_31.
		@param m_fifapps_param_31 m_fifapps_param_31	  */
	public void setm_fifapps_param_31 (String m_fifapps_param_31)
	{
		set_Value (COLUMNNAME_m_fifapps_param_31, m_fifapps_param_31);
	}

	/** Get m_fifapps_param_31.
		@return m_fifapps_param_31	  */
	public String getm_fifapps_param_31 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_31);
	}

	/** Set m_fifapps_param_32.
		@param m_fifapps_param_32 m_fifapps_param_32	  */
	public void setm_fifapps_param_32 (String m_fifapps_param_32)
	{
		set_Value (COLUMNNAME_m_fifapps_param_32, m_fifapps_param_32);
	}

	/** Get m_fifapps_param_32.
		@return m_fifapps_param_32	  */
	public String getm_fifapps_param_32 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_32);
	}

	/** Set m_fifapps_param_33.
		@param m_fifapps_param_33 m_fifapps_param_33	  */
	public void setm_fifapps_param_33 (String m_fifapps_param_33)
	{
		set_Value (COLUMNNAME_m_fifapps_param_33, m_fifapps_param_33);
	}

	/** Get m_fifapps_param_33.
		@return m_fifapps_param_33	  */
	public String getm_fifapps_param_33 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_33);
	}

	/** Set m_fifapps_param_34.
		@param m_fifapps_param_34 m_fifapps_param_34	  */
	public void setm_fifapps_param_34 (String m_fifapps_param_34)
	{
		set_Value (COLUMNNAME_m_fifapps_param_34, m_fifapps_param_34);
	}

	/** Get m_fifapps_param_34.
		@return m_fifapps_param_34	  */
	public String getm_fifapps_param_34 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_34);
	}

	/** Set m_fifapps_param_35.
		@param m_fifapps_param_35 m_fifapps_param_35	  */
	public void setm_fifapps_param_35 (String m_fifapps_param_35)
	{
		set_Value (COLUMNNAME_m_fifapps_param_35, m_fifapps_param_35);
	}

	/** Get m_fifapps_param_35.
		@return m_fifapps_param_35	  */
	public String getm_fifapps_param_35 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_35);
	}

	/** Set m_fifapps_param_36.
		@param m_fifapps_param_36 m_fifapps_param_36	  */
	public void setm_fifapps_param_36 (String m_fifapps_param_36)
	{
		set_Value (COLUMNNAME_m_fifapps_param_36, m_fifapps_param_36);
	}

	/** Get m_fifapps_param_36.
		@return m_fifapps_param_36	  */
	public String getm_fifapps_param_36 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_36);
	}

	/** Set m_fifapps_param_37.
		@param m_fifapps_param_37 m_fifapps_param_37	  */
	public void setm_fifapps_param_37 (String m_fifapps_param_37)
	{
		set_Value (COLUMNNAME_m_fifapps_param_37, m_fifapps_param_37);
	}

	/** Get m_fifapps_param_37.
		@return m_fifapps_param_37	  */
	public String getm_fifapps_param_37 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_37);
	}

	/** Set m_fifapps_param_38.
		@param m_fifapps_param_38 m_fifapps_param_38	  */
	public void setm_fifapps_param_38 (String m_fifapps_param_38)
	{
		set_Value (COLUMNNAME_m_fifapps_param_38, m_fifapps_param_38);
	}

	/** Get m_fifapps_param_38.
		@return m_fifapps_param_38	  */
	public String getm_fifapps_param_38 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_38);
	}

	/** Set m_fifapps_param_39.
		@param m_fifapps_param_39 m_fifapps_param_39	  */
	public void setm_fifapps_param_39 (String m_fifapps_param_39)
	{
		set_Value (COLUMNNAME_m_fifapps_param_39, m_fifapps_param_39);
	}

	/** Get m_fifapps_param_39.
		@return m_fifapps_param_39	  */
	public String getm_fifapps_param_39 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_39);
	}

	/** Set m_fifapps_param_40.
		@param m_fifapps_param_40 m_fifapps_param_40	  */
	public void setm_fifapps_param_40 (String m_fifapps_param_40)
	{
		set_Value (COLUMNNAME_m_fifapps_param_40, m_fifapps_param_40);
	}

	/** Get m_fifapps_param_40.
		@return m_fifapps_param_40	  */
	public String getm_fifapps_param_40 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_40);
	}

	/** Set m_fifapps_param_41.
		@param m_fifapps_param_41 m_fifapps_param_41	  */
	public void setm_fifapps_param_41 (String m_fifapps_param_41)
	{
		set_Value (COLUMNNAME_m_fifapps_param_41, m_fifapps_param_41);
	}

	/** Get m_fifapps_param_41.
		@return m_fifapps_param_41	  */
	public String getm_fifapps_param_41 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_41);
	}

	/** Set m_fifapps_param_42.
		@param m_fifapps_param_42 m_fifapps_param_42	  */
	public void setm_fifapps_param_42 (String m_fifapps_param_42)
	{
		set_Value (COLUMNNAME_m_fifapps_param_42, m_fifapps_param_42);
	}

	/** Get m_fifapps_param_42.
		@return m_fifapps_param_42	  */
	public String getm_fifapps_param_42 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_42);
	}

	/** Set m_fifapps_param_43.
		@param m_fifapps_param_43 m_fifapps_param_43	  */
	public void setm_fifapps_param_43 (String m_fifapps_param_43)
	{
		set_Value (COLUMNNAME_m_fifapps_param_43, m_fifapps_param_43);
	}

	/** Get m_fifapps_param_43.
		@return m_fifapps_param_43	  */
	public String getm_fifapps_param_43 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_43);
	}

	/** Set m_fifapps_param_44.
		@param m_fifapps_param_44 m_fifapps_param_44	  */
	public void setm_fifapps_param_44 (String m_fifapps_param_44)
	{
		set_Value (COLUMNNAME_m_fifapps_param_44, m_fifapps_param_44);
	}

	/** Get m_fifapps_param_44.
		@return m_fifapps_param_44	  */
	public String getm_fifapps_param_44 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_44);
	}

	/** Set m_fifapps_param_45.
		@param m_fifapps_param_45 m_fifapps_param_45	  */
	public void setm_fifapps_param_45 (String m_fifapps_param_45)
	{
		set_Value (COLUMNNAME_m_fifapps_param_45, m_fifapps_param_45);
	}

	/** Get m_fifapps_param_45.
		@return m_fifapps_param_45	  */
	public String getm_fifapps_param_45 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_45);
	}

	/** Set m_fifapps_param_46.
		@param m_fifapps_param_46 m_fifapps_param_46	  */
	public void setm_fifapps_param_46 (String m_fifapps_param_46)
	{
		set_Value (COLUMNNAME_m_fifapps_param_46, m_fifapps_param_46);
	}

	/** Get m_fifapps_param_46.
		@return m_fifapps_param_46	  */
	public String getm_fifapps_param_46 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_46);
	}

	/** Set m_fifapps_param_47.
		@param m_fifapps_param_47 m_fifapps_param_47	  */
	public void setm_fifapps_param_47 (String m_fifapps_param_47)
	{
		set_Value (COLUMNNAME_m_fifapps_param_47, m_fifapps_param_47);
	}

	/** Get m_fifapps_param_47.
		@return m_fifapps_param_47	  */
	public String getm_fifapps_param_47 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_47);
	}

	/** Set m_fifapps_param_48.
		@param m_fifapps_param_48 m_fifapps_param_48	  */
	public void setm_fifapps_param_48 (String m_fifapps_param_48)
	{
		set_Value (COLUMNNAME_m_fifapps_param_48, m_fifapps_param_48);
	}

	/** Get m_fifapps_param_48.
		@return m_fifapps_param_48	  */
	public String getm_fifapps_param_48 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_48);
	}

	/** Set m_fifapps_param_49.
		@param m_fifapps_param_49 m_fifapps_param_49	  */
	public void setm_fifapps_param_49 (String m_fifapps_param_49)
	{
		set_Value (COLUMNNAME_m_fifapps_param_49, m_fifapps_param_49);
	}

	/** Get m_fifapps_param_49.
		@return m_fifapps_param_49	  */
	public String getm_fifapps_param_49 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_49);
	}

	/** Set m_fifapps_param_50.
		@param m_fifapps_param_50 m_fifapps_param_50	  */
	public void setm_fifapps_param_50 (String m_fifapps_param_50)
	{
		set_Value (COLUMNNAME_m_fifapps_param_50, m_fifapps_param_50);
	}

	/** Get m_fifapps_param_50.
		@return m_fifapps_param_50	  */
	public String getm_fifapps_param_50 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_50);
	}

	/** Set m_fifapps_param_51.
		@param m_fifapps_param_51 m_fifapps_param_51	  */
	public void setm_fifapps_param_51 (String m_fifapps_param_51)
	{
		set_Value (COLUMNNAME_m_fifapps_param_51, m_fifapps_param_51);
	}

	/** Get m_fifapps_param_51.
		@return m_fifapps_param_51	  */
	public String getm_fifapps_param_51 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_51);
	}

	/** Set m_fifapps_param_52.
		@param m_fifapps_param_52 m_fifapps_param_52	  */
	public void setm_fifapps_param_52 (String m_fifapps_param_52)
	{
		set_Value (COLUMNNAME_m_fifapps_param_52, m_fifapps_param_52);
	}

	/** Get m_fifapps_param_52.
		@return m_fifapps_param_52	  */
	public String getm_fifapps_param_52 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_52);
	}

	/** Set m_fifapps_param_53.
		@param m_fifapps_param_53 m_fifapps_param_53	  */
	public void setm_fifapps_param_53 (String m_fifapps_param_53)
	{
		set_Value (COLUMNNAME_m_fifapps_param_53, m_fifapps_param_53);
	}

	/** Get m_fifapps_param_53.
		@return m_fifapps_param_53	  */
	public String getm_fifapps_param_53 () 
	{
		return (String)get_Value(COLUMNNAME_m_fifapps_param_53);
	}

	/** Set m_fifapps_source.
		@param m_fifapps_source m_fifapps_source	  */
	public void setm_fifapps_source (int m_fifapps_source)
	{
		set_ValueNoCheck (COLUMNNAME_m_fifapps_source, Integer.valueOf(m_fifapps_source));
	}

	/** Get m_fifapps_source.
		@return m_fifapps_source	  */
	public int getm_fifapps_source () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_m_fifapps_source);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set m_fifapps_status_process.
		@param m_fifapps_status_process m_fifapps_status_process	  */
	public void setm_fifapps_status_process (boolean m_fifapps_status_process)
	{
		set_Value (COLUMNNAME_m_fifapps_status_process, Boolean.valueOf(m_fifapps_status_process));
	}

	/** Get m_fifapps_status_process.
		@return m_fifapps_status_process	  */
	public boolean ism_fifapps_status_process () 
	{
		Object oo = get_Value(COLUMNNAME_m_fifapps_status_process);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set m_fifapps_time_insert.
		@param m_fifapps_time_insert m_fifapps_time_insert	  */
	public void setm_fifapps_time_insert (Timestamp m_fifapps_time_insert)
	{
		set_ValueNoCheck (COLUMNNAME_m_fifapps_time_insert, m_fifapps_time_insert);
	}

	/** Get m_fifapps_time_insert.
		@return m_fifapps_time_insert	  */
	public Timestamp getm_fifapps_time_insert () 
	{
		return (Timestamp)get_Value(COLUMNNAME_m_fifapps_time_insert);
	}

	/** Set m_fifapps_time_process.
		@param m_fifapps_time_process m_fifapps_time_process	  */
	public void setm_fifapps_time_process (Timestamp m_fifapps_time_process)
	{
		set_Value (COLUMNNAME_m_fifapps_time_process, m_fifapps_time_process);
	}

	/** Get m_fifapps_time_process.
		@return m_fifapps_time_process	  */
	public Timestamp getm_fifapps_time_process () 
	{
		return (Timestamp)get_Value(COLUMNNAME_m_fifapps_time_process);
	}

	/** Set m_process_by.
		@param m_process_by m_process_by	  */
	public void setm_process_by (int m_process_by)
	{
		set_Value (COLUMNNAME_m_process_by, Integer.valueOf(m_process_by));
	}

	/** Get m_process_by.
		@return m_process_by	  */
	public int getm_process_by () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_m_process_by);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}