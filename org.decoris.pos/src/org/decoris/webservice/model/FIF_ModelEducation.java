package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelEducation {

	public String aabCode;
	public String biStatus;
	public String biStatusRef;
	public String createBy;
	public String createDate;
	public String eduDescr;
	public String eduType;
	public String lippobankCode;
	public String updateBy;
	public String updateDate;
	public String visible;
	
	public FIF_ModelEducation(String aabCode,String biStatus,String biStatusRef,String createBy,String createDate,
			String eduDescr,String eduType,String lippobankCode,String updateBy,String updateDate,String visible){
		
		
		this.aabCode =aabCode;
		this.biStatus = biStatus;
		this.biStatusRef = biStatusRef;
		this.createBy = createBy;
		this.createDate = createDate;
		this.eduDescr = eduDescr;
		this.eduType = eduType;
		this.lippobankCode = lippobankCode;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.visible = visible;
		
	}
	
	public void createNewRecord(){
		
		
		
	}
	
}
