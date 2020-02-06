package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelZIP {

	public String cityCode;
	public String createBy;
	public String createDate;
	public String kecCode;
	public String kelCode;
	public String provCode;
	public String subZipCode;
	public String updateBy;
	public String updateDate;
	public String zipCode;
	public String zipDesc;
	
	public FIF_ModelZIP(String cityCode,String createBy,String createDate,String kecCode,String kelCode,
			String provCode,String subZipCode,String updateBy,String updateDate,String zipCode,String zipDesc){
		
		this.cityCode = cityCode;
		this.createBy = createBy;
		this.createDate = createDate;
		this.kecCode = kecCode;
		this.kelCode = kelCode;
		this.provCode = provCode;
		this.subZipCode = subZipCode;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.zipCode = zipCode;
		this.zipDesc = zipDesc;
		
	}
	
}
