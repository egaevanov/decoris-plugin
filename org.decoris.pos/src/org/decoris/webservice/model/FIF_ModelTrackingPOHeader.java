package org.decoris.webservice.model;

import java.math.BigDecimal;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelTrackingPOHeader {


	public String applDate;
	public String applNo;
	public String birthDate;
	public String createDate;
	public String custName;
	public BigDecimal objPrice;
	public String orderIdOri;
	public String poNo;
	public String state;
	public String statusOrder;
	public String suppCode;
	public String timeService;
	
	public FIF_ModelTrackingPOHeader(String applDate,String applNo,String birthDate,String createDate,
			String custName,BigDecimal objPrice,String orderIdOri,String poNo,String state,
			String statusOrder,String suppCode,String timeService){
		
		this.applDate = applDate;
		this.applNo = applNo;
		this.birthDate = birthDate;
		this.createDate = createDate;
		this.custName = custName;
		this.objPrice = objPrice;
		this.orderIdOri = orderIdOri;
		this.poNo = poNo;
		this.state = state;
		this.statusOrder = statusOrder;
		this.suppCode = suppCode;
		this.timeService = timeService;

		
	}
	
}