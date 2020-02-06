package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelProv{

	public String create_by;
	public String create_date;
	public String prov_code;
	public String provinsi;
	
	public FIF_ModelProv(String create_by,String create_date,String prov_code,String provinsi){
		
		this.create_by = create_by;
		this.create_date = create_date;
		this.prov_code = prov_code;
		this.provinsi = provinsi;
		
	}
	
}
