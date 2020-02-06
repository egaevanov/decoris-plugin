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

/** Generated Interface for m_fifapps_ws_tmp
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_m_fifapps_ws_tmp 
{

    /** TableName=m_fifapps_ws_tmp */
    public static final String Table_Name = "m_fifapps_ws_tmp";

    /** AD_Table_ID=1000190 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name m_fifapps_param_01 */
    public static final String COLUMNNAME_m_fifapps_param_01 = "m_fifapps_param_01";

	/** Set m_fifapps_param_01	  */
	public void setm_fifapps_param_01 (String m_fifapps_param_01);

	/** Get m_fifapps_param_01	  */
	public String getm_fifapps_param_01();

    /** Column name m_fifapps_param_02 */
    public static final String COLUMNNAME_m_fifapps_param_02 = "m_fifapps_param_02";

	/** Set m_fifapps_param_02	  */
	public void setm_fifapps_param_02 (String m_fifapps_param_02);

	/** Get m_fifapps_param_02	  */
	public String getm_fifapps_param_02();

    /** Column name m_fifapps_param_03 */
    public static final String COLUMNNAME_m_fifapps_param_03 = "m_fifapps_param_03";

	/** Set m_fifapps_param_03	  */
	public void setm_fifapps_param_03 (String m_fifapps_param_03);

	/** Get m_fifapps_param_03	  */
	public String getm_fifapps_param_03();

    /** Column name m_fifapps_param_04 */
    public static final String COLUMNNAME_m_fifapps_param_04 = "m_fifapps_param_04";

	/** Set m_fifapps_param_04	  */
	public void setm_fifapps_param_04 (String m_fifapps_param_04);

	/** Get m_fifapps_param_04	  */
	public String getm_fifapps_param_04();

    /** Column name m_fifapps_param_05 */
    public static final String COLUMNNAME_m_fifapps_param_05 = "m_fifapps_param_05";

	/** Set m_fifapps_param_05	  */
	public void setm_fifapps_param_05 (String m_fifapps_param_05);

	/** Get m_fifapps_param_05	  */
	public String getm_fifapps_param_05();

    /** Column name m_fifapps_param_06 */
    public static final String COLUMNNAME_m_fifapps_param_06 = "m_fifapps_param_06";

	/** Set m_fifapps_param_06	  */
	public void setm_fifapps_param_06 (String m_fifapps_param_06);

	/** Get m_fifapps_param_06	  */
	public String getm_fifapps_param_06();

    /** Column name m_fifapps_param_07 */
    public static final String COLUMNNAME_m_fifapps_param_07 = "m_fifapps_param_07";

	/** Set m_fifapps_param_07	  */
	public void setm_fifapps_param_07 (String m_fifapps_param_07);

	/** Get m_fifapps_param_07	  */
	public String getm_fifapps_param_07();

    /** Column name m_fifapps_param_08 */
    public static final String COLUMNNAME_m_fifapps_param_08 = "m_fifapps_param_08";

	/** Set m_fifapps_param_08	  */
	public void setm_fifapps_param_08 (String m_fifapps_param_08);

	/** Get m_fifapps_param_08	  */
	public String getm_fifapps_param_08();

    /** Column name m_fifapps_param_09 */
    public static final String COLUMNNAME_m_fifapps_param_09 = "m_fifapps_param_09";

	/** Set m_fifapps_param_09	  */
	public void setm_fifapps_param_09 (String m_fifapps_param_09);

	/** Get m_fifapps_param_09	  */
	public String getm_fifapps_param_09();

    /** Column name m_fifapps_param_10 */
    public static final String COLUMNNAME_m_fifapps_param_10 = "m_fifapps_param_10";

	/** Set m_fifapps_param_10	  */
	public void setm_fifapps_param_10 (String m_fifapps_param_10);

	/** Get m_fifapps_param_10	  */
	public String getm_fifapps_param_10();

    /** Column name m_fifapps_param_11 */
    public static final String COLUMNNAME_m_fifapps_param_11 = "m_fifapps_param_11";

	/** Set m_fifapps_param_11	  */
	public void setm_fifapps_param_11 (String m_fifapps_param_11);

	/** Get m_fifapps_param_11	  */
	public String getm_fifapps_param_11();

    /** Column name m_fifapps_param_12 */
    public static final String COLUMNNAME_m_fifapps_param_12 = "m_fifapps_param_12";

	/** Set m_fifapps_param_12	  */
	public void setm_fifapps_param_12 (String m_fifapps_param_12);

	/** Get m_fifapps_param_12	  */
	public String getm_fifapps_param_12();

    /** Column name m_fifapps_param_13 */
    public static final String COLUMNNAME_m_fifapps_param_13 = "m_fifapps_param_13";

	/** Set m_fifapps_param_13	  */
	public void setm_fifapps_param_13 (String m_fifapps_param_13);

	/** Get m_fifapps_param_13	  */
	public String getm_fifapps_param_13();

    /** Column name m_fifapps_param_14 */
    public static final String COLUMNNAME_m_fifapps_param_14 = "m_fifapps_param_14";

	/** Set m_fifapps_param_14	  */
	public void setm_fifapps_param_14 (String m_fifapps_param_14);

	/** Get m_fifapps_param_14	  */
	public String getm_fifapps_param_14();

    /** Column name m_fifapps_param_15 */
    public static final String COLUMNNAME_m_fifapps_param_15 = "m_fifapps_param_15";

	/** Set m_fifapps_param_15	  */
	public void setm_fifapps_param_15 (String m_fifapps_param_15);

	/** Get m_fifapps_param_15	  */
	public String getm_fifapps_param_15();

    /** Column name m_fifapps_param_16 */
    public static final String COLUMNNAME_m_fifapps_param_16 = "m_fifapps_param_16";

	/** Set m_fifapps_param_16	  */
	public void setm_fifapps_param_16 (String m_fifapps_param_16);

	/** Get m_fifapps_param_16	  */
	public String getm_fifapps_param_16();

    /** Column name m_fifapps_param_17 */
    public static final String COLUMNNAME_m_fifapps_param_17 = "m_fifapps_param_17";

	/** Set m_fifapps_param_17	  */
	public void setm_fifapps_param_17 (String m_fifapps_param_17);

	/** Get m_fifapps_param_17	  */
	public String getm_fifapps_param_17();

    /** Column name m_fifapps_param_18 */
    public static final String COLUMNNAME_m_fifapps_param_18 = "m_fifapps_param_18";

	/** Set m_fifapps_param_18	  */
	public void setm_fifapps_param_18 (String m_fifapps_param_18);

	/** Get m_fifapps_param_18	  */
	public String getm_fifapps_param_18();

    /** Column name m_fifapps_param_19 */
    public static final String COLUMNNAME_m_fifapps_param_19 = "m_fifapps_param_19";

	/** Set m_fifapps_param_19	  */
	public void setm_fifapps_param_19 (String m_fifapps_param_19);

	/** Get m_fifapps_param_19	  */
	public String getm_fifapps_param_19();

    /** Column name m_fifapps_param_20 */
    public static final String COLUMNNAME_m_fifapps_param_20 = "m_fifapps_param_20";

	/** Set m_fifapps_param_20	  */
	public void setm_fifapps_param_20 (String m_fifapps_param_20);

	/** Get m_fifapps_param_20	  */
	public String getm_fifapps_param_20();

    /** Column name m_fifapps_param_21 */
    public static final String COLUMNNAME_m_fifapps_param_21 = "m_fifapps_param_21";

	/** Set m_fifapps_param_21	  */
	public void setm_fifapps_param_21 (String m_fifapps_param_21);

	/** Get m_fifapps_param_21	  */
	public String getm_fifapps_param_21();

    /** Column name m_fifapps_param_22 */
    public static final String COLUMNNAME_m_fifapps_param_22 = "m_fifapps_param_22";

	/** Set m_fifapps_param_22	  */
	public void setm_fifapps_param_22 (String m_fifapps_param_22);

	/** Get m_fifapps_param_22	  */
	public String getm_fifapps_param_22();

    /** Column name m_fifapps_param_23 */
    public static final String COLUMNNAME_m_fifapps_param_23 = "m_fifapps_param_23";

	/** Set m_fifapps_param_23	  */
	public void setm_fifapps_param_23 (String m_fifapps_param_23);

	/** Get m_fifapps_param_23	  */
	public String getm_fifapps_param_23();

    /** Column name m_fifapps_param_24 */
    public static final String COLUMNNAME_m_fifapps_param_24 = "m_fifapps_param_24";

	/** Set m_fifapps_param_24	  */
	public void setm_fifapps_param_24 (String m_fifapps_param_24);

	/** Get m_fifapps_param_24	  */
	public String getm_fifapps_param_24();

    /** Column name m_fifapps_param_25 */
    public static final String COLUMNNAME_m_fifapps_param_25 = "m_fifapps_param_25";

	/** Set m_fifapps_param_25	  */
	public void setm_fifapps_param_25 (String m_fifapps_param_25);

	/** Get m_fifapps_param_25	  */
	public String getm_fifapps_param_25();

    /** Column name m_fifapps_param_26 */
    public static final String COLUMNNAME_m_fifapps_param_26 = "m_fifapps_param_26";

	/** Set m_fifapps_param_26	  */
	public void setm_fifapps_param_26 (String m_fifapps_param_26);

	/** Get m_fifapps_param_26	  */
	public String getm_fifapps_param_26();

    /** Column name m_fifapps_param_27 */
    public static final String COLUMNNAME_m_fifapps_param_27 = "m_fifapps_param_27";

	/** Set m_fifapps_param_27	  */
	public void setm_fifapps_param_27 (String m_fifapps_param_27);

	/** Get m_fifapps_param_27	  */
	public String getm_fifapps_param_27();

    /** Column name m_fifapps_param_28 */
    public static final String COLUMNNAME_m_fifapps_param_28 = "m_fifapps_param_28";

	/** Set m_fifapps_param_28	  */
	public void setm_fifapps_param_28 (String m_fifapps_param_28);

	/** Get m_fifapps_param_28	  */
	public String getm_fifapps_param_28();

    /** Column name m_fifapps_param_29 */
    public static final String COLUMNNAME_m_fifapps_param_29 = "m_fifapps_param_29";

	/** Set m_fifapps_param_29	  */
	public void setm_fifapps_param_29 (String m_fifapps_param_29);

	/** Get m_fifapps_param_29	  */
	public String getm_fifapps_param_29();

    /** Column name m_fifapps_param_30 */
    public static final String COLUMNNAME_m_fifapps_param_30 = "m_fifapps_param_30";

	/** Set m_fifapps_param_30	  */
	public void setm_fifapps_param_30 (String m_fifapps_param_30);

	/** Get m_fifapps_param_30	  */
	public String getm_fifapps_param_30();

    /** Column name m_fifapps_param_31 */
    public static final String COLUMNNAME_m_fifapps_param_31 = "m_fifapps_param_31";

	/** Set m_fifapps_param_31	  */
	public void setm_fifapps_param_31 (String m_fifapps_param_31);

	/** Get m_fifapps_param_31	  */
	public String getm_fifapps_param_31();

    /** Column name m_fifapps_param_32 */
    public static final String COLUMNNAME_m_fifapps_param_32 = "m_fifapps_param_32";

	/** Set m_fifapps_param_32	  */
	public void setm_fifapps_param_32 (String m_fifapps_param_32);

	/** Get m_fifapps_param_32	  */
	public String getm_fifapps_param_32();

    /** Column name m_fifapps_param_33 */
    public static final String COLUMNNAME_m_fifapps_param_33 = "m_fifapps_param_33";

	/** Set m_fifapps_param_33	  */
	public void setm_fifapps_param_33 (String m_fifapps_param_33);

	/** Get m_fifapps_param_33	  */
	public String getm_fifapps_param_33();

    /** Column name m_fifapps_param_34 */
    public static final String COLUMNNAME_m_fifapps_param_34 = "m_fifapps_param_34";

	/** Set m_fifapps_param_34	  */
	public void setm_fifapps_param_34 (String m_fifapps_param_34);

	/** Get m_fifapps_param_34	  */
	public String getm_fifapps_param_34();

    /** Column name m_fifapps_param_35 */
    public static final String COLUMNNAME_m_fifapps_param_35 = "m_fifapps_param_35";

	/** Set m_fifapps_param_35	  */
	public void setm_fifapps_param_35 (String m_fifapps_param_35);

	/** Get m_fifapps_param_35	  */
	public String getm_fifapps_param_35();

    /** Column name m_fifapps_param_36 */
    public static final String COLUMNNAME_m_fifapps_param_36 = "m_fifapps_param_36";

	/** Set m_fifapps_param_36	  */
	public void setm_fifapps_param_36 (String m_fifapps_param_36);

	/** Get m_fifapps_param_36	  */
	public String getm_fifapps_param_36();

    /** Column name m_fifapps_param_37 */
    public static final String COLUMNNAME_m_fifapps_param_37 = "m_fifapps_param_37";

	/** Set m_fifapps_param_37	  */
	public void setm_fifapps_param_37 (String m_fifapps_param_37);

	/** Get m_fifapps_param_37	  */
	public String getm_fifapps_param_37();

    /** Column name m_fifapps_param_38 */
    public static final String COLUMNNAME_m_fifapps_param_38 = "m_fifapps_param_38";

	/** Set m_fifapps_param_38	  */
	public void setm_fifapps_param_38 (String m_fifapps_param_38);

	/** Get m_fifapps_param_38	  */
	public String getm_fifapps_param_38();

    /** Column name m_fifapps_param_39 */
    public static final String COLUMNNAME_m_fifapps_param_39 = "m_fifapps_param_39";

	/** Set m_fifapps_param_39	  */
	public void setm_fifapps_param_39 (String m_fifapps_param_39);

	/** Get m_fifapps_param_39	  */
	public String getm_fifapps_param_39();

    /** Column name m_fifapps_param_40 */
    public static final String COLUMNNAME_m_fifapps_param_40 = "m_fifapps_param_40";

	/** Set m_fifapps_param_40	  */
	public void setm_fifapps_param_40 (String m_fifapps_param_40);

	/** Get m_fifapps_param_40	  */
	public String getm_fifapps_param_40();

    /** Column name m_fifapps_param_41 */
    public static final String COLUMNNAME_m_fifapps_param_41 = "m_fifapps_param_41";

	/** Set m_fifapps_param_41	  */
	public void setm_fifapps_param_41 (String m_fifapps_param_41);

	/** Get m_fifapps_param_41	  */
	public String getm_fifapps_param_41();

    /** Column name m_fifapps_param_42 */
    public static final String COLUMNNAME_m_fifapps_param_42 = "m_fifapps_param_42";

	/** Set m_fifapps_param_42	  */
	public void setm_fifapps_param_42 (String m_fifapps_param_42);

	/** Get m_fifapps_param_42	  */
	public String getm_fifapps_param_42();

    /** Column name m_fifapps_param_43 */
    public static final String COLUMNNAME_m_fifapps_param_43 = "m_fifapps_param_43";

	/** Set m_fifapps_param_43	  */
	public void setm_fifapps_param_43 (String m_fifapps_param_43);

	/** Get m_fifapps_param_43	  */
	public String getm_fifapps_param_43();

    /** Column name m_fifapps_param_44 */
    public static final String COLUMNNAME_m_fifapps_param_44 = "m_fifapps_param_44";

	/** Set m_fifapps_param_44	  */
	public void setm_fifapps_param_44 (String m_fifapps_param_44);

	/** Get m_fifapps_param_44	  */
	public String getm_fifapps_param_44();

    /** Column name m_fifapps_param_45 */
    public static final String COLUMNNAME_m_fifapps_param_45 = "m_fifapps_param_45";

	/** Set m_fifapps_param_45	  */
	public void setm_fifapps_param_45 (String m_fifapps_param_45);

	/** Get m_fifapps_param_45	  */
	public String getm_fifapps_param_45();

    /** Column name m_fifapps_param_46 */
    public static final String COLUMNNAME_m_fifapps_param_46 = "m_fifapps_param_46";

	/** Set m_fifapps_param_46	  */
	public void setm_fifapps_param_46 (String m_fifapps_param_46);

	/** Get m_fifapps_param_46	  */
	public String getm_fifapps_param_46();

    /** Column name m_fifapps_param_47 */
    public static final String COLUMNNAME_m_fifapps_param_47 = "m_fifapps_param_47";

	/** Set m_fifapps_param_47	  */
	public void setm_fifapps_param_47 (String m_fifapps_param_47);

	/** Get m_fifapps_param_47	  */
	public String getm_fifapps_param_47();

    /** Column name m_fifapps_param_48 */
    public static final String COLUMNNAME_m_fifapps_param_48 = "m_fifapps_param_48";

	/** Set m_fifapps_param_48	  */
	public void setm_fifapps_param_48 (String m_fifapps_param_48);

	/** Get m_fifapps_param_48	  */
	public String getm_fifapps_param_48();

    /** Column name m_fifapps_param_49 */
    public static final String COLUMNNAME_m_fifapps_param_49 = "m_fifapps_param_49";

	/** Set m_fifapps_param_49	  */
	public void setm_fifapps_param_49 (String m_fifapps_param_49);

	/** Get m_fifapps_param_49	  */
	public String getm_fifapps_param_49();

    /** Column name m_fifapps_param_50 */
    public static final String COLUMNNAME_m_fifapps_param_50 = "m_fifapps_param_50";

	/** Set m_fifapps_param_50	  */
	public void setm_fifapps_param_50 (String m_fifapps_param_50);

	/** Get m_fifapps_param_50	  */
	public String getm_fifapps_param_50();

    /** Column name m_fifapps_param_51 */
    public static final String COLUMNNAME_m_fifapps_param_51 = "m_fifapps_param_51";

	/** Set m_fifapps_param_51	  */
	public void setm_fifapps_param_51 (String m_fifapps_param_51);

	/** Get m_fifapps_param_51	  */
	public String getm_fifapps_param_51();

    /** Column name m_fifapps_param_52 */
    public static final String COLUMNNAME_m_fifapps_param_52 = "m_fifapps_param_52";

	/** Set m_fifapps_param_52	  */
	public void setm_fifapps_param_52 (String m_fifapps_param_52);

	/** Get m_fifapps_param_52	  */
	public String getm_fifapps_param_52();

    /** Column name m_fifapps_param_53 */
    public static final String COLUMNNAME_m_fifapps_param_53 = "m_fifapps_param_53";

	/** Set m_fifapps_param_53	  */
	public void setm_fifapps_param_53 (String m_fifapps_param_53);

	/** Get m_fifapps_param_53	  */
	public String getm_fifapps_param_53();

    /** Column name m_fifapps_source */
    public static final String COLUMNNAME_m_fifapps_source = "m_fifapps_source";

	/** Set m_fifapps_source	  */
	public void setm_fifapps_source (int m_fifapps_source);

	/** Get m_fifapps_source	  */
	public int getm_fifapps_source();

    /** Column name m_fifapps_status_process */
    public static final String COLUMNNAME_m_fifapps_status_process = "m_fifapps_status_process";

	/** Set m_fifapps_status_process	  */
	public void setm_fifapps_status_process (boolean m_fifapps_status_process);

	/** Get m_fifapps_status_process	  */
	public boolean ism_fifapps_status_process();

    /** Column name m_fifapps_time_insert */
    public static final String COLUMNNAME_m_fifapps_time_insert = "m_fifapps_time_insert";

	/** Set m_fifapps_time_insert	  */
	public void setm_fifapps_time_insert (Timestamp m_fifapps_time_insert);

	/** Get m_fifapps_time_insert	  */
	public Timestamp getm_fifapps_time_insert();

    /** Column name m_fifapps_time_process */
    public static final String COLUMNNAME_m_fifapps_time_process = "m_fifapps_time_process";

	/** Set m_fifapps_time_process	  */
	public void setm_fifapps_time_process (Timestamp m_fifapps_time_process);

	/** Get m_fifapps_time_process	  */
	public Timestamp getm_fifapps_time_process();

    /** Column name m_process_by */
    public static final String COLUMNNAME_m_process_by = "m_process_by";

	/** Set m_process_by	  */
	public void setm_process_by (int m_process_by);

	/** Get m_process_by	  */
	public int getm_process_by();
}
