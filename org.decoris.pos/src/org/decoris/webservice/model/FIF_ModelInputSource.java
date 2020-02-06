package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelInputSource {

	public String branchTypeRight;
	public String code;
	public String createdBy;
	public String createdDate;
	public String depositKey;
	public String description;
	public String shortName;
	public String updateBy;
	public String updateDate;
	
	public FIF_ModelInputSource(String branchTypeRight,String code,String createdBy,String createdDate,
			String depositKey,String description,String shortName,String updateBy,String updateDate){
		
		this.branchTypeRight = branchTypeRight;
		this.code = code;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.depositKey = depositKey;
		this.description = description;
		this.shortName = shortName;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		
	}
	
	
}
