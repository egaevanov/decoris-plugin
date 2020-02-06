package org.decoris.model.validator;

import java.util.ArrayList;

import org.adempiere.base.event.IEventTopics;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.decoris.model.X_C_Decoris_PreOrder;
import org.osgi.service.event.Event;

public class DEC_PreOrderValidator {

public static CLogger log = CLogger.getCLogger(DEC_MInOutValidator.class);

	
	
	public static String executePreOrderEvent(Event event, PO po) {
		String msgPOrder = "";
		String evt = event.getTopic();
		X_C_Decoris_PreOrder POrder = (X_C_Decoris_PreOrder) po;
		if (event.getTopic().equals(IEventTopics.PO_AFTER_NEW) ||
				event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE)) {
			msgPOrder = PreOrderLineBeforeSave(POrder,evt);
			
		}
			
		return msgPOrder;

	}



	private static String PreOrderLineBeforeSave(X_C_Decoris_PreOrder pOrder,String evt) {
		
		ArrayList<String> listInput = new ArrayList<String>();
		//validation no telp
		ArrayList<KeyNamePair> listTlps = new ArrayList<KeyNamePair>();

		boolean IsNum = true;
		String msg = "";
		String phoneArea = pOrder.getcustphonearea();
		String phone = pOrder.getcustphone();
		String officePhoneArea = pOrder.getcustoffpharea();
		String officePhone = pOrder.getcustofficephone();
		String ext =pOrder.getcustoffext();
		String handPhone1 = pOrder.getPhone();
		String handPhone2 = pOrder.getPhone2();
		String custRT = pOrder.getcustrt();
		String custRW = pOrder.getcustrw();
		String domRT = pOrder.getdomrt();
		String domRW = pOrder.getdomrw();

		
		//Add To List Ttp
		listTlps.add(new KeyNamePair(0, phoneArea));
		listTlps.add(new KeyNamePair(1, phone));
		listTlps.add(new KeyNamePair(2, officePhoneArea));
		listTlps.add(new KeyNamePair(3, officePhone));
		listTlps.add(new KeyNamePair(4, ext));
		listTlps.add(new KeyNamePair(5, handPhone1));
		listTlps.add(new KeyNamePair(6, handPhone2));
		listTlps.add(new KeyNamePair(7, custRT));
		listTlps.add(new KeyNamePair(8, custRW));
		listTlps.add(new KeyNamePair(9, domRT));
		listTlps.add(new KeyNamePair(10, domRW));


		for (KeyNamePair listTlp : listTlps){
		
			listInput = splitInput(listTlp.getName());
			int key = listTlp.getKey();
			
			boolean isExist = false,isExist1 = false,isExist2 = false,isExist3 = false,isExist4 = false,isExist5 = false,isExist6 = false,isExist7 = false,isExist8 = false,isExist9 = false,isExist10 = false;
			for (int j = 0 ; j < listInput.size() ; j++){
				
				IsNum = isNumeric(listInput.get(j));
				
				if(!IsNum){		
					
					if(key == 0 && !isExist ){
						msg = "Phone Area \n";
						isExist = true;
					}
					if(key == 1 && !isExist1){
						msg = msg + "- Phone \n";
						isExist1 = true;
					}
					if(key == 2 && !isExist2){
						msg = msg + "- Office Phone Area \n";
						isExist2 = true;
					}
					if(key == 3 && !isExist3){
						msg = msg + "- Office Phone  \n";
						isExist3 = true;
					}
					if(key == 4 && !isExist4){
						msg = msg + "- Ext \n";
						isExist4 = true;
					}
					if(key == 5 && !isExist5){
						msg = msg + "- Handphone \n";
						isExist5 = true;
					}
					if(key == 6 && !isExist6){
						msg = msg + "- Handphone2 \n";
						isExist6 = true;
					}
					if(key == 7 && !isExist7){
						msg = msg + "- Customer RT \n";
						isExist7 = true;
					}
					if(key == 8 && !isExist8){
						msg = msg + "- Customer RW \n";
						isExist8 = true;
					}
					if(key == 9 && !isExist9){
						msg = msg + "- Domisili RT \n";
						isExist9 = true;
					}
					if(key == 10 && !isExist10){
						msg = msg + "- Domisili RW \n";
						isExist10 = true;
					}
				}
				
			}

		}
		
		
		
		
		if(msg != ""){
			
			msg = msg + "--> Tidak Sesuai Format (Hanya Angka Yang Diperbolehkan)"+ "/n";
			
		}
		
		String isPass = IsPassAge(pOrder);
		
		if(isPass != ""){
			
			msg = msg + isPass;
			
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
	
	public static ArrayList<String> splitInput(String input){
		ArrayList<String> list = new ArrayList<String>();
		int lenght = input.length();
		
		for(int i = 0 ; i < lenght ; i++){
			
			list.add(input.substring(i, i+1));
			
		}
		
		
		return list;
		
		
	}
	
	
	public static String IsPassAge(X_C_Decoris_PreOrder preOrd){
		
		String isPass = "";
		StringBuilder SQLGetParam = new StringBuilder();
		SQLGetParam.append("SELECT description FROM ad_param WHERE value = 'Age_Limit_Preorder_FIFAPPS'");
		String ageParam = DB.getSQLValueString(null, SQLGetParam.toString());

		
		
		StringBuilder SQLGetAge = new StringBuilder();
		SQLGetAge.append("SELECT DATE_PART('YEAR',NOW()) - DATE_PART('YEAR','"+preOrd.getBirthday()+"'::timestamp) FROM Dual ");

		Integer age = DB.getSQLValueEx(null, SQLGetAge.toString());
		Integer ageParamToInt = Integer.valueOf(ageParam);
		
		if(age < ageParamToInt){
			
			isPass = "Usia Pelanggan Minimal " + ageParam + " Tahun";
			
		}
		
		
		return isPass;
	
	}
	
}
