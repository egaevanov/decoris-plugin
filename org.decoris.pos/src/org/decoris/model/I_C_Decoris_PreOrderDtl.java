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

/** Generated Interface for C_Decoris_PreOrderDtl
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_C_Decoris_PreOrderDtl 
{

    /** TableName=C_Decoris_PreOrderDtl */
    public static final String Table_Name = "C_Decoris_PreOrderDtl";

    /** AD_Table_ID=1000201 */
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

    /** Column name apdocstatus */
    public static final String COLUMNNAME_apdocstatus = "apdocstatus";

	/** Set apdocstatus	  */
	public void setapdocstatus (String apdocstatus);

	/** Get apdocstatus	  */
	public String getapdocstatus();

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

    /** Column name approveby */
    public static final String COLUMNNAME_approveby = "approveby";

	/** Set approveby	  */
	public void setapproveby (String approveby);

	/** Get approveby	  */
	public String getapproveby();

    /** Column name aptotalamount */
    public static final String COLUMNNAME_aptotalamount = "aptotalamount";

	/** Set aptotalamount	  */
	public void setaptotalamount (BigDecimal aptotalamount);

	/** Get aptotalamount	  */
	public BigDecimal getaptotalamount();

    /** Column name birthdate */
    public static final String COLUMNNAME_birthdate = "birthdate";

	/** Set birthdate	  */
	public void setbirthdate (String birthdate);

	/** Get birthdate	  */
	public String getbirthdate();

    /** Column name birthplace */
    public static final String COLUMNNAME_birthplace = "birthplace";

	/** Set birthplace	  */
	public void setbirthplace (String birthplace);

	/** Get birthplace	  */
	public String getbirthplace();

    /** Column name bpkbname */
    public static final String COLUMNNAME_bpkbname = "bpkbname";

	/** Set bpkbname	  */
	public void setbpkbname (String bpkbname);

	/** Get bpkbname	  */
	public String getbpkbname();

    /** Column name bussunit */
    public static final String COLUMNNAME_bussunit = "bussunit";

	/** Set bussunit	  */
	public void setbussunit (String bussunit);

	/** Get bussunit	  */
	public String getbussunit();

    /** Column name C_Decoris_PreOrder_ID */
    public static final String COLUMNNAME_C_Decoris_PreOrder_ID = "C_Decoris_PreOrder_ID";

	/** Set C_Decoris_PreOrder	  */
	public void setC_Decoris_PreOrder_ID (int C_Decoris_PreOrder_ID);

	/** Get C_Decoris_PreOrder	  */
	public int getC_Decoris_PreOrder_ID();

	public I_C_Decoris_PreOrder getC_Decoris_PreOrder() throws RuntimeException;

    /** Column name C_Decoris_PreOrderDtl_ID */
    public static final String COLUMNNAME_C_Decoris_PreOrderDtl_ID = "C_Decoris_PreOrderDtl_ID";

	/** Set C_Decoris_PreOrderDtl	  */
	public void setC_Decoris_PreOrderDtl_ID (int C_Decoris_PreOrderDtl_ID);

	/** Get C_Decoris_PreOrderDtl	  */
	public int getC_Decoris_PreOrderDtl_ID();

    /** Column name C_Decoris_PreOrderDtl_UU */
    public static final String COLUMNNAME_C_Decoris_PreOrderDtl_UU = "C_Decoris_PreOrderDtl_UU";

	/** Set C_Decoris_PreOrderDtl_UU	  */
	public void setC_Decoris_PreOrderDtl_UU (String C_Decoris_PreOrderDtl_UU);

	/** Get C_Decoris_PreOrderDtl_UU	  */
	public String getC_Decoris_PreOrderDtl_UU();

    /** Column name cancelreason */
    public static final String COLUMNNAME_cancelreason = "cancelreason";

	/** Set cancelreason	  */
	public void setcancelreason (String cancelreason);

	/** Get cancelreason	  */
	public String getcancelreason();

    /** Column name contractactivedate */
    public static final String COLUMNNAME_contractactivedate = "contractactivedate";

	/** Set contractactivedate	  */
	public void setcontractactivedate (String contractactivedate);

	/** Get contractactivedate	  */
	public String getcontractactivedate();

    /** Column name contractno */
    public static final String COLUMNNAME_contractno = "contractno";

	/** Set contractno	  */
	public void setcontractno (String contractno);

	/** Get contractno	  */
	public String getcontractno();

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

    /** Column name custaddr */
    public static final String COLUMNNAME_custaddr = "custaddr";

	/** Set custaddr	  */
	public void setcustaddr (String custaddr);

	/** Get custaddr	  */
	public String getcustaddr();

    /** Column name custcity */
    public static final String COLUMNNAME_custcity = "custcity";

	/** Set custcity	  */
	public void setcustcity (String custcity);

	/** Get custcity	  */
	public String getcustcity();

    /** Column name custdp */
    public static final String COLUMNNAME_custdp = "custdp";

	/** Set custdp	  */
	public void setcustdp (BigDecimal custdp);

	/** Get custdp	  */
	public BigDecimal getcustdp();

    /** Column name custfixpharea */
    public static final String COLUMNNAME_custfixpharea = "custfixpharea";

	/** Set custfixpharea	  */
	public void setcustfixpharea (String custfixpharea);

	/** Get custfixpharea	  */
	public String getcustfixpharea();

    /** Column name custfixphone */
    public static final String COLUMNNAME_custfixphone = "custfixphone";

	/** Set custfixphone	  */
	public void setcustfixphone (String custfixphone);

	/** Get custfixphone	  */
	public String getcustfixphone();

    /** Column name custkec */
    public static final String COLUMNNAME_custkec = "custkec";

	/** Set custkec	  */
	public void setcustkec (String custkec);

	/** Get custkec	  */
	public String getcustkec();

    /** Column name custkel */
    public static final String COLUMNNAME_custkel = "custkel";

	/** Set custkel	  */
	public void setcustkel (String custkel);

	/** Get custkel	  */
	public String getcustkel();

    /** Column name custmobphone */
    public static final String COLUMNNAME_custmobphone = "custmobphone";

	/** Set custmobphone	  */
	public void setcustmobphone (String custmobphone);

	/** Get custmobphone	  */
	public String getcustmobphone();

    /** Column name custmobphone2 */
    public static final String COLUMNNAME_custmobphone2 = "custmobphone2";

	/** Set custmobphone2	  */
	public void setcustmobphone2 (String custmobphone2);

	/** Get custmobphone2	  */
	public String getcustmobphone2();

    /** Column name custname */
    public static final String COLUMNNAME_custname = "custname";

	/** Set custname	  */
	public void setcustname (String custname);

	/** Get custname	  */
	public String getcustname();

    /** Column name custno */
    public static final String COLUMNNAME_custno = "custno";

	/** Set custno	  */
	public void setcustno (String custno);

	/** Get custno	  */
	public String getcustno();

    /** Column name custprov */
    public static final String COLUMNNAME_custprov = "custprov";

	/** Set custprov	  */
	public void setcustprov (String custprov);

	/** Get custprov	  */
	public String getcustprov();

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

    /** Column name danasudahcair */
    public static final String COLUMNNAME_danasudahcair = "danasudahcair";

	/** Set danasudahcair	  */
	public void setdanasudahcair (String danasudahcair);

	/** Get danasudahcair	  */
	public String getdanasudahcair();

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

    /** Column name janjisurvey */
    public static final String COLUMNNAME_janjisurvey = "janjisurvey";

	/** Set janjisurvey	  */
	public void setjanjisurvey (String janjisurvey);

	/** Get janjisurvey	  */
	public String getjanjisurvey();

    /** Column name monthinst */
    public static final String COLUMNNAME_monthinst = "monthinst";

	/** Set monthinst	  */
	public void setmonthinst (BigDecimal monthinst);

	/** Get monthinst	  */
	public BigDecimal getmonthinst();

    /** Column name noka */
    public static final String COLUMNNAME_noka = "noka";

	/** Set noka	  */
	public void setnoka (String noka);

	/** Get noka	  */
	public String getnoka();

    /** Column name nosin */
    public static final String COLUMNNAME_nosin = "nosin";

	/** Set nosin	  */
	public void setnosin (String nosin);

	/** Get nosin	  */
	public String getnosin();

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

    /** Column name objdesc */
    public static final String COLUMNNAME_objdesc = "objdesc";

	/** Set objdesc	  */
	public void setobjdesc (String objdesc);

	/** Get objdesc	  */
	public String getobjdesc();

    /** Column name objmodel */
    public static final String COLUMNNAME_objmodel = "objmodel";

	/** Set objmodel	  */
	public void setobjmodel (String objmodel);

	/** Get objmodel	  */
	public String getobjmodel();

    /** Column name objprice */
    public static final String COLUMNNAME_objprice = "objprice";

	/** Set objprice	  */
	public void setobjprice (BigDecimal objprice);

	/** Get objprice	  */
	public BigDecimal getobjprice();

    /** Column name objtype */
    public static final String COLUMNNAME_objtype = "objtype";

	/** Set objtype	  */
	public void setobjtype (String objtype);

	/** Get objtype	  */
	public String getobjtype();

    /** Column name offfixpharea */
    public static final String COLUMNNAME_offfixpharea = "offfixpharea";

	/** Set offfixpharea	  */
	public void setofffixpharea (String offfixpharea);

	/** Get offfixpharea	  */
	public String getofffixpharea();

    /** Column name offfixphext */
    public static final String COLUMNNAME_offfixphext = "offfixphext";

	/** Set offfixphext	  */
	public void setofffixphext (String offfixphext);

	/** Get offfixphext	  */
	public String getofffixphext();

    /** Column name offfixphone */
    public static final String COLUMNNAME_offfixphone = "offfixphone";

	/** Set offfixphone	  */
	public void setofffixphone (String offfixphone);

	/** Get offfixphone	  */
	public String getofffixphone();

    /** Column name officecode */
    public static final String COLUMNNAME_officecode = "officecode";

	/** Set officecode	  */
	public void setofficecode (String officecode);

	/** Get officecode	  */
	public String getofficecode();

    /** Column name orderid */
    public static final String COLUMNNAME_orderid = "orderid";

	/** Set orderid	  */
	public void setorderid (String orderid);

	/** Get orderid	  */
	public String getorderid();

    /** Column name podate */
    public static final String COLUMNNAME_podate = "podate";

	/** Set podate	  */
	public void setpodate (String podate);

	/** Get podate	  */
	public String getpodate();

    /** Column name pono */
    public static final String COLUMNNAME_pono = "pono";

	/** Set pono	  */
	public void setpono (String pono);

	/** Get pono	  */
	public String getpono();

    /** Column name pos */
    public static final String COLUMNNAME_pos = "pos";

	/** Set pos	  */
	public void setpos (String pos);

	/** Get pos	  */
	public String getpos();

    /** Column name servofficecode */
    public static final String COLUMNNAME_servofficecode = "servofficecode";

	/** Set servofficecode	  */
	public void setservofficecode (String servofficecode);

	/** Get servofficecode	  */
	public String getservofficecode();

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

    /** Column name suplname */
    public static final String COLUMNNAME_suplname = "suplname";

	/** Set suplname	  */
	public void setsuplname (String suplname);

	/** Get suplname	  */
	public String getsuplname();

    /** Column name suppcode */
    public static final String COLUMNNAME_suppcode = "suppcode";

	/** Set suppcode	  */
	public void setsuppcode (String suppcode);

	/** Get suppcode	  */
	public String getsuppcode();

    /** Column name tenor */
    public static final String COLUMNNAME_tenor = "tenor";

	/** Set tenor	  */
	public void settenor (BigDecimal tenor);

	/** Get tenor	  */
	public BigDecimal gettenor();

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

    /** Column name warna */
    public static final String COLUMNNAME_warna = "warna";

	/** Set warna	  */
	public void setwarna (String warna);

	/** Get warna	  */
	public String getwarna();
}
