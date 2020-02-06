package org.decoris.model.validator;

import java.sql.Timestamp;
import java.util.Calendar;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MYear;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.decoris.model.X_C_Decoris_ENofa;
import org.osgi.service.event.Event;

/**
 * 
 * @author Tegar N
 *
 */

public class DEC_ENofaValidator {

	public static CLogger log = CLogger.getCLogger(DEC_ENofaValidator.class);
	
	public static String executeENofaEvent(Event event, PO po) {
		String msgEnofa = "";
		X_C_Decoris_ENofa ENofa = (X_C_Decoris_ENofa) po;
		if (event.getTopic().equals(IEventTopics.PO_AFTER_NEW)) {
			
			msgEnofa = ENofaBeforeSave(ENofa,true);
			
		}else if (event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE)){
			
			msgEnofa = ENofaBeforeSave(ENofa,false);
			
		}
			
		return msgEnofa;

	}


	private static String ENofaBeforeSave(X_C_Decoris_ENofa eNofa,boolean IsNew) {
		String msg = "";
		int noAwal = eNofa.getNoAwal();
		int noAkhir = eNofa.getNoAkhir();
		
		if(noAwal!=0 && noAkhir != 0 && IsNew){
			
			eNofa.setEnofaCounter(noAwal);
			eNofa.setSisaEnofa(eNofa.getTotalEnofa());
			eNofa.saveEx();
		}
		
		//validation cek no awal and no akhir
		if(noAwal > noAkhir){
			
			msg = "No Awal Tidak Boleh Lebih Besar Dari No Akhir";
			return msg;
		}
		
		//get year p_paramenter
		int C_Year_ID = eNofa.getC_Year_ID();
		MYear year = new MYear(eNofa.getCtx(), C_Year_ID, eNofa.get_TrxName());
		String yearName = year.getFiscalYear();
		Integer yearPeriod = Integer.valueOf(yearName);
				
		if(yearPeriod == null){
			yearPeriod = 0;
		}
			
		//get month date
		Timestamp startDate = eNofa.getStartDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		Integer yearStart = cal.get(Calendar.YEAR);
		if (yearStart ==null){
			yearStart = 0;
		}
				
		if (yearPeriod > 0 && yearStart > 0){
				
			if(!yearStart.equals(yearPeriod)){
			
				msg = "Tahun Berlaku harus sesuai dengan tahun periode";	
			
				return msg;
			}
			
		}
		
		//validation no seri
		boolean IsNum = true;
		String NoSeri = eNofa.getNoSeri();
		
		if(NoSeri.length() < 6){
			msg = "Input No Seri Tidak Sesuai Format";
			return  msg;	
		}
		
		String point = NoSeri.substring(3,4);

		String[] seri = new String[]{NoSeri.substring(0,1),NoSeri.substring(1,2),NoSeri.substring(2,3),NoSeri.substring(4,5),NoSeri.substring(5,6),};
		
		if(!point.equalsIgnoreCase(".")){
			msg = "Input No Seri Tidak Sesuai Format";
			return  msg;
		}
		
		for (int i = 0 ; i < seri.length ; i++){
			
			IsNum = isNumeric(seri[i]);
			
			if(!IsNum){
				msg = "Input No Seri Tidak Sesuai Format";
				return  msg;
				
			}
			
		}
		
		return msg;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
}
