package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelHouseStatus {

	public String createBy;
	public String createDate;
	public String houseStat;
	public String statusDescr;
	public String updateBy;
	public String updateDate;
	public String visible;

	public FIF_ModelHouseStatus(String createBy,String createDate,String houseStat,String statusDescr,
			String updateBy,String updateDate,String visible){
		
		this.createBy = createBy;
		this.createDate = createDate;
		this.houseStat = houseStat;
		this.statusDescr = statusDescr;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.visible = visible;
		
	}
	
}
