package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelSupplier {

	public String address1;
	public String address2;
	public String city;
	public String provinsi;
	public String supplierCode;
	public String supplierCompanyName;
	public String supplierCompanyType;
	public String supplierGroupId;
	public String supplierMainDealer;
	public String supplierName;
	public String supplierOutletType;
	public String supplierStatus;
	public String supplierSubType;
	public String supplierType;
	public String zipcode;

	
	public FIF_ModelSupplier(String address1,String address2,String city,String provinsi,
			String supplierCode,String supplierCompanyName,String supplierCompanyType,
			String supplierGroupId,String supplierMainDealer,String supplierName,
			String supplierOutletType,String supplierStatus,String supplierSubType,
			String supplierType,String zipcode){
		
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.provinsi = provinsi;
		this.supplierCode = supplierCode;
		this.supplierCompanyName = supplierCompanyName;
		this.supplierCompanyType = supplierCompanyType;
		this.supplierGroupId = supplierGroupId;
		this.supplierMainDealer = supplierMainDealer;
		this.supplierName = supplierName;
		this.supplierOutletType = supplierOutletType;
		this.supplierStatus = supplierStatus;
		this.supplierSubType = supplierSubType;
		this.supplierType = supplierType;
		this.zipcode = zipcode;

		
	}
	
}
