package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelObjectCode {

	public String branchId;
	public String categoryId;
	public String isVehicle;
	public String needAddColl;
	public String newUsed;
	public String objBrand;
	public String objCode;
	public String objDescr;
	public String objGrp;
	public String objMake;
	public String objModel;
	public String objSize;
	public String objType;
	
	public FIF_ModelObjectCode(String branchId,String categoryId,String isVehicle,String needAddColl,
			String newUsed,String objBrand,String objCode,String objDescr,String objGrp,
			String objMake,String objModel,String objSize,String objType){
		
		this.branchId = branchId;
		this.categoryId = categoryId;
		this.isVehicle = isVehicle;
		this.needAddColl = needAddColl;
		this.newUsed = newUsed;
		this.objBrand = objBrand;
		this.objCode = objCode;
		this.objDescr = objDescr;
		this.objGrp = objGrp;
		this.objMake = objMake;
		this.objModel = objModel;
		this.objSize = objSize;
		this.objType = objType;
		
	} 
	
}
