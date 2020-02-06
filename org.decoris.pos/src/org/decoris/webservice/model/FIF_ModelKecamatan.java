package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelKecamatan {

	public String cityCode;
	public String createBy;
	public String createDate;
	public String kecCode;
	public String kecamatan;
	public String updateBy;
	public String updateDate;

	
	public FIF_ModelKecamatan(String cityCode,String createBy,String createDate,String kecCode,
			String kecamatan,String updateBy,String updateDate){
		
		this.cityCode = cityCode;
		this.createBy = createBy;
		this.createDate = createDate;
		this.kecCode = kecCode;
		this.kecamatan = kecamatan;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		
	}
	
}
