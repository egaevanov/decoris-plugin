package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelGender {

	public String aabCode;
	public String createdBy;
	public String createdTimestamp;
	public String description;
	public String genderId;
	public String lastupdateBy;
	public String lastupdateTimestamp;
	public String visible;
	
	public FIF_ModelGender(String aabCode,String createdBy,String createdTimestamp,String description,
			String genderId,String lastupdateBy,String lastupdateTimestamp,String visible){
		
		
		this.aabCode = aabCode;
		this.createdBy = createdBy;
		this.createdTimestamp = createdTimestamp;
		this.description = description;
		this.genderId = genderId;
		this.lastupdateBy = lastupdateBy;
		this.lastupdateTimestamp = lastupdateTimestamp;
		this.visible = visible;

		
	}
	
	
}
