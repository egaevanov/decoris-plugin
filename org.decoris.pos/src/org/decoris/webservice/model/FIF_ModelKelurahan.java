package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelKelurahan {

	public String createBy;
	public String createDate;
	public String kecCode;
	public String kelCode;
	public String kelurahan;
	public String updateBy;
	public String updateDate;
	
	public FIF_ModelKelurahan (String createBy,String createDate,String kecCode,String kelCode,
			String kelurahan,String updateBy,String updateDate){
		
		this.createBy = createBy;
		this.createDate = createDate;
		this.kecCode = kecCode;
		this.kelCode = kelCode;
		this.kelurahan = kelurahan;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		
	} 
	
}
