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
package org.decoris.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for C_Decoris_PreOrderDtl
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_C_Decoris_PreOrderDtl extends PO implements I_C_Decoris_PreOrderDtl, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180223L;

    /** Standard Constructor */
    public X_C_Decoris_PreOrderDtl (Properties ctx, int C_Decoris_PreOrderDtl_ID, String trxName)
    {
      super (ctx, C_Decoris_PreOrderDtl_ID, trxName);
      /** if (C_Decoris_PreOrderDtl_ID == 0)
        {
			setaptotalamount (Env.ZERO);
			setC_Decoris_PreOrder_ID (0);
			setC_Decoris_PreOrderDtl_ID (0);
			setcustdp (Env.ZERO);
			setmonthinst (Env.ZERO);
			setobjprice (Env.ZERO);
			settenor (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_C_Decoris_PreOrderDtl (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_C_Decoris_PreOrderDtl[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set apdocstatus.
		@param apdocstatus apdocstatus	  */
	public void setapdocstatus (String apdocstatus)
	{
		set_Value (COLUMNNAME_apdocstatus, apdocstatus);
	}

	/** Get apdocstatus.
		@return apdocstatus	  */
	public String getapdocstatus () 
	{
		return (String)get_Value(COLUMNNAME_apdocstatus);
	}

	/** Set appldate.
		@param appldate appldate	  */
	public void setappldate (String appldate)
	{
		set_Value (COLUMNNAME_appldate, appldate);
	}

	/** Get appldate.
		@return appldate	  */
	public String getappldate () 
	{
		return (String)get_Value(COLUMNNAME_appldate);
	}

	/** Set applno.
		@param applno applno	  */
	public void setapplno (String applno)
	{
		set_Value (COLUMNNAME_applno, applno);
	}

	/** Get applno.
		@return applno	  */
	public String getapplno () 
	{
		return (String)get_Value(COLUMNNAME_applno);
	}

	/** Set approveby.
		@param approveby approveby	  */
	public void setapproveby (String approveby)
	{
		set_Value (COLUMNNAME_approveby, approveby);
	}

	/** Get approveby.
		@return approveby	  */
	public String getapproveby () 
	{
		return (String)get_Value(COLUMNNAME_approveby);
	}

	/** Set aptotalamount.
		@param aptotalamount aptotalamount	  */
	public void setaptotalamount (BigDecimal aptotalamount)
	{
		set_Value (COLUMNNAME_aptotalamount, aptotalamount);
	}

	/** Get aptotalamount.
		@return aptotalamount	  */
	public BigDecimal getaptotalamount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_aptotalamount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set birthdate.
		@param birthdate birthdate	  */
	public void setbirthdate (String birthdate)
	{
		set_Value (COLUMNNAME_birthdate, birthdate);
	}

	/** Get birthdate.
		@return birthdate	  */
	public String getbirthdate () 
	{
		return (String)get_Value(COLUMNNAME_birthdate);
	}

	/** Set birthplace.
		@param birthplace birthplace	  */
	public void setbirthplace (String birthplace)
	{
		set_ValueNoCheck (COLUMNNAME_birthplace, birthplace);
	}

	/** Get birthplace.
		@return birthplace	  */
	public String getbirthplace () 
	{
		return (String)get_Value(COLUMNNAME_birthplace);
	}

	/** Set bpkbname.
		@param bpkbname bpkbname	  */
	public void setbpkbname (String bpkbname)
	{
		set_Value (COLUMNNAME_bpkbname, bpkbname);
	}

	/** Get bpkbname.
		@return bpkbname	  */
	public String getbpkbname () 
	{
		return (String)get_Value(COLUMNNAME_bpkbname);
	}

	/** Set bussunit.
		@param bussunit bussunit	  */
	public void setbussunit (String bussunit)
	{
		set_Value (COLUMNNAME_bussunit, bussunit);
	}

	/** Get bussunit.
		@return bussunit	  */
	public String getbussunit () 
	{
		return (String)get_Value(COLUMNNAME_bussunit);
	}

	public I_C_Decoris_PreOrder getC_Decoris_PreOrder() throws RuntimeException
    {
		return (I_C_Decoris_PreOrder)MTable.get(getCtx(), I_C_Decoris_PreOrder.Table_Name)
			.getPO(getC_Decoris_PreOrder_ID(), get_TrxName());	}

	/** Set C_Decoris_PreOrder.
		@param C_Decoris_PreOrder_ID C_Decoris_PreOrder	  */
	public void setC_Decoris_PreOrder_ID (int C_Decoris_PreOrder_ID)
	{
		if (C_Decoris_PreOrder_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrder_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrder_ID, Integer.valueOf(C_Decoris_PreOrder_ID));
	}

	/** Get C_Decoris_PreOrder.
		@return C_Decoris_PreOrder	  */
	public int getC_Decoris_PreOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_PreOrderDtl.
		@param C_Decoris_PreOrderDtl_ID C_Decoris_PreOrderDtl	  */
	public void setC_Decoris_PreOrderDtl_ID (int C_Decoris_PreOrderDtl_ID)
	{
		if (C_Decoris_PreOrderDtl_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrderDtl_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrderDtl_ID, Integer.valueOf(C_Decoris_PreOrderDtl_ID));
	}

	/** Get C_Decoris_PreOrderDtl.
		@return C_Decoris_PreOrderDtl	  */
	public int getC_Decoris_PreOrderDtl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Decoris_PreOrderDtl_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Decoris_PreOrderDtl_UU.
		@param C_Decoris_PreOrderDtl_UU C_Decoris_PreOrderDtl_UU	  */
	public void setC_Decoris_PreOrderDtl_UU (String C_Decoris_PreOrderDtl_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Decoris_PreOrderDtl_UU, C_Decoris_PreOrderDtl_UU);
	}

	/** Get C_Decoris_PreOrderDtl_UU.
		@return C_Decoris_PreOrderDtl_UU	  */
	public String getC_Decoris_PreOrderDtl_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Decoris_PreOrderDtl_UU);
	}

	/** Set cancelreason.
		@param cancelreason cancelreason	  */
	public void setcancelreason (String cancelreason)
	{
		set_Value (COLUMNNAME_cancelreason, cancelreason);
	}

	/** Get cancelreason.
		@return cancelreason	  */
	public String getcancelreason () 
	{
		return (String)get_Value(COLUMNNAME_cancelreason);
	}

	/** Set contractactivedate.
		@param contractactivedate contractactivedate	  */
	public void setcontractactivedate (String contractactivedate)
	{
		set_Value (COLUMNNAME_contractactivedate, contractactivedate);
	}

	/** Get contractactivedate.
		@return contractactivedate	  */
	public String getcontractactivedate () 
	{
		return (String)get_Value(COLUMNNAME_contractactivedate);
	}

	/** Set contractno.
		@param contractno contractno	  */
	public void setcontractno (String contractno)
	{
		set_Value (COLUMNNAME_contractno, contractno);
	}

	/** Get contractno.
		@return contractno	  */
	public String getcontractno () 
	{
		return (String)get_Value(COLUMNNAME_contractno);
	}

	/** Set custaddr.
		@param custaddr custaddr	  */
	public void setcustaddr (String custaddr)
	{
		set_Value (COLUMNNAME_custaddr, custaddr);
	}

	/** Get custaddr.
		@return custaddr	  */
	public String getcustaddr () 
	{
		return (String)get_Value(COLUMNNAME_custaddr);
	}

	/** Set custcity.
		@param custcity custcity	  */
	public void setcustcity (String custcity)
	{
		set_Value (COLUMNNAME_custcity, custcity);
	}

	/** Get custcity.
		@return custcity	  */
	public String getcustcity () 
	{
		return (String)get_Value(COLUMNNAME_custcity);
	}

	/** Set custdp.
		@param custdp custdp	  */
	public void setcustdp (BigDecimal custdp)
	{
		set_Value (COLUMNNAME_custdp, custdp);
	}

	/** Get custdp.
		@return custdp	  */
	public BigDecimal getcustdp () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_custdp);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set custfixpharea.
		@param custfixpharea custfixpharea	  */
	public void setcustfixpharea (String custfixpharea)
	{
		set_Value (COLUMNNAME_custfixpharea, custfixpharea);
	}

	/** Get custfixpharea.
		@return custfixpharea	  */
	public String getcustfixpharea () 
	{
		return (String)get_Value(COLUMNNAME_custfixpharea);
	}

	/** Set custfixphone.
		@param custfixphone custfixphone	  */
	public void setcustfixphone (String custfixphone)
	{
		set_Value (COLUMNNAME_custfixphone, custfixphone);
	}

	/** Get custfixphone.
		@return custfixphone	  */
	public String getcustfixphone () 
	{
		return (String)get_Value(COLUMNNAME_custfixphone);
	}

	/** Set custkec.
		@param custkec custkec	  */
	public void setcustkec (String custkec)
	{
		set_Value (COLUMNNAME_custkec, custkec);
	}

	/** Get custkec.
		@return custkec	  */
	public String getcustkec () 
	{
		return (String)get_Value(COLUMNNAME_custkec);
	}

	/** Set custkel.
		@param custkel custkel	  */
	public void setcustkel (String custkel)
	{
		set_Value (COLUMNNAME_custkel, custkel);
	}

	/** Get custkel.
		@return custkel	  */
	public String getcustkel () 
	{
		return (String)get_Value(COLUMNNAME_custkel);
	}

	/** Set custmobphone.
		@param custmobphone custmobphone	  */
	public void setcustmobphone (String custmobphone)
	{
		set_Value (COLUMNNAME_custmobphone, custmobphone);
	}

	/** Get custmobphone.
		@return custmobphone	  */
	public String getcustmobphone () 
	{
		return (String)get_Value(COLUMNNAME_custmobphone);
	}

	/** Set custmobphone2.
		@param custmobphone2 custmobphone2	  */
	public void setcustmobphone2 (String custmobphone2)
	{
		set_Value (COLUMNNAME_custmobphone2, custmobphone2);
	}

	/** Get custmobphone2.
		@return custmobphone2	  */
	public String getcustmobphone2 () 
	{
		return (String)get_Value(COLUMNNAME_custmobphone2);
	}

	/** Set custname.
		@param custname custname	  */
	public void setcustname (String custname)
	{
		set_Value (COLUMNNAME_custname, custname);
	}

	/** Get custname.
		@return custname	  */
	public String getcustname () 
	{
		return (String)get_Value(COLUMNNAME_custname);
	}

	/** Set custno.
		@param custno custno	  */
	public void setcustno (String custno)
	{
		set_Value (COLUMNNAME_custno, custno);
	}

	/** Get custno.
		@return custno	  */
	public String getcustno () 
	{
		return (String)get_Value(COLUMNNAME_custno);
	}

	/** Set custprov.
		@param custprov custprov	  */
	public void setcustprov (String custprov)
	{
		set_Value (COLUMNNAME_custprov, custprov);
	}

	/** Get custprov.
		@return custprov	  */
	public String getcustprov () 
	{
		return (String)get_Value(COLUMNNAME_custprov);
	}

	/** Set custrt.
		@param custrt custrt	  */
	public void setcustrt (String custrt)
	{
		set_Value (COLUMNNAME_custrt, custrt);
	}

	/** Get custrt.
		@return custrt	  */
	public String getcustrt () 
	{
		return (String)get_Value(COLUMNNAME_custrt);
	}

	/** Set custrw.
		@param custrw custrw	  */
	public void setcustrw (String custrw)
	{
		set_Value (COLUMNNAME_custrw, custrw);
	}

	/** Get custrw.
		@return custrw	  */
	public String getcustrw () 
	{
		return (String)get_Value(COLUMNNAME_custrw);
	}

	/** Set danasudahcair.
		@param danasudahcair danasudahcair	  */
	public void setdanasudahcair (String danasudahcair)
	{
		set_Value (COLUMNNAME_danasudahcair, danasudahcair);
	}

	/** Get danasudahcair.
		@return danasudahcair	  */
	public String getdanasudahcair () 
	{
		return (String)get_Value(COLUMNNAME_danasudahcair);
	}

	/** Set janjisurvey.
		@param janjisurvey janjisurvey	  */
	public void setjanjisurvey (String janjisurvey)
	{
		set_Value (COLUMNNAME_janjisurvey, janjisurvey);
	}

	/** Get janjisurvey.
		@return janjisurvey	  */
	public String getjanjisurvey () 
	{
		return (String)get_Value(COLUMNNAME_janjisurvey);
	}

	/** Set monthinst.
		@param monthinst monthinst	  */
	public void setmonthinst (BigDecimal monthinst)
	{
		set_Value (COLUMNNAME_monthinst, monthinst);
	}

	/** Get monthinst.
		@return monthinst	  */
	public BigDecimal getmonthinst () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_monthinst);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set noka.
		@param noka noka	  */
	public void setnoka (String noka)
	{
		set_Value (COLUMNNAME_noka, noka);
	}

	/** Get noka.
		@return noka	  */
	public String getnoka () 
	{
		return (String)get_Value(COLUMNNAME_noka);
	}

	/** Set nosin.
		@param nosin nosin	  */
	public void setnosin (String nosin)
	{
		set_Value (COLUMNNAME_nosin, nosin);
	}

	/** Get nosin.
		@return nosin	  */
	public String getnosin () 
	{
		return (String)get_Value(COLUMNNAME_nosin);
	}

	/** Set objbrand.
		@param objbrand objbrand	  */
	public void setobjbrand (String objbrand)
	{
		set_Value (COLUMNNAME_objbrand, objbrand);
	}

	/** Get objbrand.
		@return objbrand	  */
	public String getobjbrand () 
	{
		return (String)get_Value(COLUMNNAME_objbrand);
	}

	/** Set objcode.
		@param objcode objcode	  */
	public void setobjcode (String objcode)
	{
		set_Value (COLUMNNAME_objcode, objcode);
	}

	/** Get objcode.
		@return objcode	  */
	public String getobjcode () 
	{
		return (String)get_Value(COLUMNNAME_objcode);
	}

	/** Set objdesc.
		@param objdesc objdesc	  */
	public void setobjdesc (String objdesc)
	{
		set_Value (COLUMNNAME_objdesc, objdesc);
	}

	/** Get objdesc.
		@return objdesc	  */
	public String getobjdesc () 
	{
		return (String)get_Value(COLUMNNAME_objdesc);
	}

	/** Set objmodel.
		@param objmodel objmodel	  */
	public void setobjmodel (String objmodel)
	{
		set_Value (COLUMNNAME_objmodel, objmodel);
	}

	/** Get objmodel.
		@return objmodel	  */
	public String getobjmodel () 
	{
		return (String)get_Value(COLUMNNAME_objmodel);
	}

	/** Set objprice.
		@param objprice objprice	  */
	public void setobjprice (BigDecimal objprice)
	{
		set_Value (COLUMNNAME_objprice, objprice);
	}

	/** Get objprice.
		@return objprice	  */
	public BigDecimal getobjprice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_objprice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set objtype.
		@param objtype objtype	  */
	public void setobjtype (String objtype)
	{
		set_Value (COLUMNNAME_objtype, objtype);
	}

	/** Get objtype.
		@return objtype	  */
	public String getobjtype () 
	{
		return (String)get_Value(COLUMNNAME_objtype);
	}

	/** Set offfixpharea.
		@param offfixpharea offfixpharea	  */
	public void setofffixpharea (String offfixpharea)
	{
		set_Value (COLUMNNAME_offfixpharea, offfixpharea);
	}

	/** Get offfixpharea.
		@return offfixpharea	  */
	public String getofffixpharea () 
	{
		return (String)get_Value(COLUMNNAME_offfixpharea);
	}

	/** Set offfixphext.
		@param offfixphext offfixphext	  */
	public void setofffixphext (String offfixphext)
	{
		set_Value (COLUMNNAME_offfixphext, offfixphext);
	}

	/** Get offfixphext.
		@return offfixphext	  */
	public String getofffixphext () 
	{
		return (String)get_Value(COLUMNNAME_offfixphext);
	}

	/** Set offfixphone.
		@param offfixphone offfixphone	  */
	public void setofffixphone (String offfixphone)
	{
		set_Value (COLUMNNAME_offfixphone, offfixphone);
	}

	/** Get offfixphone.
		@return offfixphone	  */
	public String getofffixphone () 
	{
		return (String)get_Value(COLUMNNAME_offfixphone);
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

	/** Set orderid.
		@param orderid orderid	  */
	public void setorderid (String orderid)
	{
		set_Value (COLUMNNAME_orderid, orderid);
	}

	/** Get orderid.
		@return orderid	  */
	public String getorderid () 
	{
		return (String)get_Value(COLUMNNAME_orderid);
	}

	/** Set podate.
		@param podate podate	  */
	public void setpodate (String podate)
	{
		set_Value (COLUMNNAME_podate, podate);
	}

	/** Get podate.
		@return podate	  */
	public String getpodate () 
	{
		return (String)get_Value(COLUMNNAME_podate);
	}

	/** Set pono.
		@param pono pono	  */
	public void setpono (String pono)
	{
		set_Value (COLUMNNAME_pono, pono);
	}

	/** Get pono.
		@return pono	  */
	public String getpono () 
	{
		return (String)get_Value(COLUMNNAME_pono);
	}

	/** Set pos.
		@param pos pos	  */
	public void setpos (String pos)
	{
		set_ValueNoCheck (COLUMNNAME_pos, pos);
	}

	/** Get pos.
		@return pos	  */
	public String getpos () 
	{
		return (String)get_Value(COLUMNNAME_pos);
	}

	/** Set servofficecode.
		@param servofficecode servofficecode	  */
	public void setservofficecode (String servofficecode)
	{
		set_Value (COLUMNNAME_servofficecode, servofficecode);
	}

	/** Get servofficecode.
		@return servofficecode	  */
	public String getservofficecode () 
	{
		return (String)get_Value(COLUMNNAME_servofficecode);
	}

	/** Set state.
		@param state state	  */
	public void setstate (String state)
	{
		set_Value (COLUMNNAME_state, state);
	}

	/** Get state.
		@return state	  */
	public String getstate () 
	{
		return (String)get_Value(COLUMNNAME_state);
	}

	/** Set statusorder.
		@param statusorder statusorder	  */
	public void setstatusorder (String statusorder)
	{
		set_ValueNoCheck (COLUMNNAME_statusorder, statusorder);
	}

	/** Get statusorder.
		@return statusorder	  */
	public String getstatusorder () 
	{
		return (String)get_Value(COLUMNNAME_statusorder);
	}

	/** Set suplname.
		@param suplname suplname	  */
	public void setsuplname (String suplname)
	{
		set_Value (COLUMNNAME_suplname, suplname);
	}

	/** Get suplname.
		@return suplname	  */
	public String getsuplname () 
	{
		return (String)get_Value(COLUMNNAME_suplname);
	}

	/** Set suppcode.
		@param suppcode suppcode	  */
	public void setsuppcode (String suppcode)
	{
		set_Value (COLUMNNAME_suppcode, suppcode);
	}

	/** Get suppcode.
		@return suppcode	  */
	public String getsuppcode () 
	{
		return (String)get_Value(COLUMNNAME_suppcode);
	}

	/** Set tenor.
		@param tenor tenor	  */
	public void settenor (BigDecimal tenor)
	{
		set_ValueNoCheck (COLUMNNAME_tenor, tenor);
	}

	/** Get tenor.
		@return tenor	  */
	public BigDecimal gettenor () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_tenor);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set timeservice.
		@param timeservice timeservice	  */
	public void settimeservice (String timeservice)
	{
		set_Value (COLUMNNAME_timeservice, timeservice);
	}

	/** Get timeservice.
		@return timeservice	  */
	public String gettimeservice () 
	{
		return (String)get_Value(COLUMNNAME_timeservice);
	}

	/** Set warna.
		@param warna warna	  */
	public void setwarna (String warna)
	{
		set_Value (COLUMNNAME_warna, warna);
	}

	/** Get warna.
		@return warna	  */
	public String getwarna () 
	{
		return (String)get_Value(COLUMNNAME_warna);
	}
}