package org.decoris.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.util.ProcessUtil;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * 
 * @author Tegar N
 *
 */

public class PrintNota extends SvrProcess{

	
	private int p_AD_Client_ID = 0;
	private String p_urlpath = "";
	private BigDecimal p_Angsuran = Env.ZERO;
	private String p_NoNota = "";
	private int p_Tenor = 0;
	private BigDecimal p_Adm = Env.ZERO;
	private BigDecimal p_DP = Env.ZERO;
	
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
				else if (name.equals("AD_Client_ID"))
					p_AD_Client_ID = (int)para[i].getParameterAsInt();
				else if (name.equals("url_path"))
					p_urlpath = (String)para[i].getParameterAsString();
				else if (name.equals("NoNota"))
					p_NoNota = (String)para[i].getParameterAsString();
				else if (name.equals("Angsuran"))
					p_Angsuran = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("Tenor"))
					p_Tenor = (int)para[i].getParameterAsInt();
				else if (name.equals("Adm"))
					p_Adm = (BigDecimal)para[i].getParameterAsBigDecimal();
				else if (name.equals("DP"))
					p_DP = (BigDecimal)para[i].getParameterAsBigDecimal();

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}
 
	@Override
	protected String doIt() throws Exception {
		
		
		 String trxName = Trx.createTrxName("penagihan");
		 //String url = "D:\\SourceCode\\iDempiereBase\\reports\\";
		 //ad process id harcode 1000000
		 //TODO must get process id from web pos setup
		 MProcess proc = new MProcess(Env.getCtx(), 1000069, trxName);
		 MPInstance instance = new MPInstance(proc,proc.getAD_Process_ID());
		 ProcessInfo pi = new ProcessInfo("Print Nota Penagihan", 1000069);
		 pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
		 
		 //set params harcode c_order_id 1000106
		 //TODO must get c_order_id
		 ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
		 list.add(new ProcessInfoParameter("AD_Client_ID", p_AD_Client_ID, null,null, null));
		 list.add(new ProcessInfoParameter("url_path",p_urlpath, null,null, null));
		 list.add(new ProcessInfoParameter("NoNota",p_NoNota, null,null, null));
		 list.add(new ProcessInfoParameter("Angsuran",p_Angsuran, null,null, null));
		 list.add(new ProcessInfoParameter("Tenor",p_Tenor, null,null, null));
		 list.add(new ProcessInfoParameter("Adm",p_Adm, null,null, null));
		 list.add(new ProcessInfoParameter("DP",p_DP, null,null, null));

		 ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
		 list.toArray(pars);
		 pi.setParameter(pars);
		 //
		 Trx trx = Trx.get(trxName, true);
		 trx.commit();
		
		 ProcessUtil.startJavaProcess(Env.getCtx(), pi, Trx.get(trxName,true));
		 
		 kredit();
		
		return "";
	}
	
	
	public String kredit(){
		
		 String trxName = Trx.createTrxName("kredit");
		 //String url = "D:\\SourceCode\\iDempiereBase\\reports\\";
		 //ad process id harcode 1000000
		 //TODO must get process id from web pos setup
		 MProcess proc = new MProcess(Env.getCtx(), 1000071, trxName);
		 MPInstance instance = new MPInstance(proc,proc.getAD_Process_ID());
		 ProcessInfo pi = new ProcessInfo("Print Nota Kredit", 1000071);
		 pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
		 
		 //set params harcode c_order_id 1000106
		 //TODO must get c_order_id
		 ArrayList<ProcessInfoParameter> list = new ArrayList<ProcessInfoParameter>();
		 list.add(new ProcessInfoParameter("AD_Client_ID", p_AD_Client_ID, null,null, null));
		 list.add(new ProcessInfoParameter("url_path",p_urlpath, null,null, null));
		 list.add(new ProcessInfoParameter("NoNota",p_NoNota, null,null, null));
		 list.add(new ProcessInfoParameter("Angsuran",p_Angsuran, null,null, null));
		 list.add(new ProcessInfoParameter("Tenor",p_Tenor, null,null, null));
		 list.add(new ProcessInfoParameter("Adm",p_Adm, null,null, null));
		 list.add(new ProcessInfoParameter("DP",p_DP, null,null, null));
		 
		 ProcessInfoParameter[] pars = new ProcessInfoParameter[list.size()];
		 list.toArray(pars);
		 pi.setParameter(pars);
		 //
		 Trx trx = Trx.get(trxName, true);
		 trx.commit();
		
		 ProcessUtil.startJavaProcess(Env.getCtx(), pi, Trx.get(trxName,true));	
		
		return "";
	}

}
