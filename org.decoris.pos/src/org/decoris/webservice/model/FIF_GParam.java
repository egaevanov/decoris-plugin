package org.decoris.webservice.model;

/**
 * 
 * @author Tegar N
 *
 */
public interface FIF_GParam {

	public static final String Client_ID = "fifgroup-token";
	public static final String Client_Secret = "261f1b80-7a18-438e-b9fa-2f9575c97e0b";
	public static final String Username = "073f93074f901defbb9f516ac1e7161ee214c9930391e3a944cd2f013062e05e2d374a57b569bf762f87278ae43a32d3cf830c1f7dbc1e9ca5416cbfd0ad2779";
	public static final String Grant_Type = "password";
	public static final String Password = "d3cd4fcb46a26720151561ba910bb8e4758d00575dfa9e51a45776228983e763d774b9c07418da14b46fb601fe924471bd701697de7f9a6bd38bcde3d8a5cce0:sirocedpuorgfif";
	
	public static final String CityURL = "/fifgrouprest-api/fifappsdaily/v1/master/cities";
	public static final String EducationURL = "/fifgrouprest-api/fifappsdaily/v1/master/education";
	public static final String GenderURL = "/fifgrouprest-api/fifappsdaily/v1/master/gender";
	public static final String HouseStatusURL = "/fifgrouprest-api/fifappsdaily/v1/master/houseStat";
	public static final String InputSourceURL = "/fifgrouprest-api/fifappsdaily/v1/mstinputsources";
	public static final String KecamatanURL = "/fifgrouprest-api/fifappsdaily/v1/master/kecamatan";
	public static final String KelurahanURL = "/fifgrouprest-api/fifappsdaily/v2/master/kelurahan/offset/";
	public static final String MaritalStatusURL = "/fifgrouprest-api/fifappsdaily/v1/master/maritalStatus";
	public static final String ObjCodeURL = "/fifgrouprest-api/fifappsdaily/v1/mstobjects/objcodes";
	public static final String ObjGroupURL = "/fifgrouprest-api/fifappsdaily/v1/mstobjects/objgroups";
	public static final String OccupationURL = "/fifgrouprest-api/fifappsdaily/v1/master/occupations";
	public static final String OfficeURL = "/fifgrouprest-api/fifappsdaily/v1/master/offices";
	public static final String SupplierURL = "/fifgrouprest-api/fifappsdaily/v2/master/suppliers/offset/";
	public static final String SupplierURLBranch = "/fifgrouprest-api/fifappsdaily/v1/master/supplier";
	public static final String ZIPURL = "/fifgrouprest-api/fifappsdaily/v2/master/zip/offset/";
	public static final String ProvURL = "/fifgrouprest-api/fifappsdaily/v1/master/prov";
	public static final String TrackingDetailURL = "/fifgrouprest-api/fifapps/v1/order/tracking/dtl";
	public static final String TrackingHeaderURL = "/fifgrouprest-api/fifapps/v1/order/tracking/hdr";
	public static final String CreateNewOrder = "/fifgrouprest-api/fifapps/v1/order/buckets";
	public static final String TokenURL = "http://testauthtoken.fifgroup.co.id:8380/auth/realms/fifgroup/protocol/openid-connect/token";

//	  public static final String CityURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/cities";
//	  public static final String EducationURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/education";
//	  public static final String GenderURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/gender";
//	  public static final String HouseStatusURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/houseStat";
//	  public static final String InputSourceURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/mstinputsources";
//	  public static final String KecamatanURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/kecamatan";
//	  public static final String KelurahanURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/kelurahan";
//	  public static final String MaritalStatusURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/maritalStatus";
//	  public static final String ObjCodeURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/mstobjects/objcodes";
//	  public static final String ObjGroupURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/mstobjects/objgroups";
//	  public static final String OccupationURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/occupations";
//	  public static final String OfficeURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/offices";
//	  public static final String SupplierURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/supplier";
//	  public static final String ZIPURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/zip";
//	  public static final String ProvURL = "http://10.17.18.115:8084/fifgrouprest-api/fifappsdaily/v1/master/prov";
//	  public static final String TokenURL = "http://10.17.18.164:8380/auth/realms/fifgroup/protocol/openid-connect/token";
//	  
	public static final int M_FifApps_Prov_source = 1;
	public static final int M_FifApps_City_source = 2;
	public static final int M_FifApps_Kecamatan_source = 3;
	public static final int M_FifApps_Kelurahan_source = 4;
	public static final int M_FifApps_Offices_source = 5;
	public static final int M_FifApps_Supplier_source = 6;
	public static final int M_FifApps_ZipCode_source = 7;
	public static final int M_FifApps_Education_source = 8;
	public static final int M_FifApps_Gender_source = 9;
	public static final int M_FifApps_HouseStatus_source = 10;
	public static final int M_FifApps_MaritalStatus_source = 11;
	public static final int M_FifApps_Occupation_source = 12;
	public static final int M_FifApps_InputSource_source = 13;
	public static final int M_FifApps_ObjectGroup_source = 14;
	public static final int M_FifApps_ObjectCode_source = 15;
	public static final int M_FifApps_TrackingHeader_source = 16;
	public static final int M_FifApps_TrackingDetail_source = 17;

	
	public static final String StatusOrdFIF_Reject = "Reject";
	public static final String StatusOrdFIF_Batal = "Batal";
	public static final String StatusOrdFIF_DanaCair = "Dana Sudah Cair";
	public static final String StatusOrdFIF_CancelPO = "Cancel PO";
		
}
