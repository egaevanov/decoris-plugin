package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelMaritalStatus {

	public String aabCode;
	public String createBy;
	public String createDate;
	public String maritalDesc;
	public String maritalStat;
	public String updateBy;
	public String updateDate;
	public String visible;
	
	public FIF_ModelMaritalStatus (String aabCode,String createBy,String createDate,String maritalDesc,
			String maritalStat,String updateBy,String updateDate,String visible){
		
		this.aabCode = aabCode;
		this.createBy = createBy;
		this.createDate = createDate;
		this.maritalDesc = maritalDesc;
		this.maritalStat = maritalStat;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.visible = visible;
		
	}
	
	
}
