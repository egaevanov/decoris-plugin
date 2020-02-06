package org.decoris.webservice.model;


/**
 * 
 * @author Tegar N
 *
 */
public class FIF_ModelOccupations {

	public String aabCode;
	public String biBidangUsaha;
	public String biBidangUsahaRef;
	public String biGolDebitur;
	public String biGolDebiturRef;
	public String biPekerjaan;
	public String biPekerjaanRef;
	public String createdBy;
	public String createdTimestamp;
	public String description;
	public String kodeBca;
	public String lastupdateBy;
	public String lastupdateTimestamp;
	public String mandatory;
	public String mbiProfesi;
	public String ocptCode;
	public String ocptCodeBca;
	public String ocptRating;
	public String ocptType;
	public String sumberPenghasilan;
	public String visible;

	
	public FIF_ModelOccupations(String aabCode,String biBidangUsaha,String biBidangUsahaRef,String biGolDebitur,
			String biGolDebiturRef,String biPekerjaan,String biPekerjaanRef,String createdBy,String createdTimestamp,
			String description,String kodeBca,String lastupdateBy,String lastupdateTimestamp,String mandatory,
			String mbiProfesi,String ocptCode,String ocptCodeBca,String ocptRating,String ocptType,
			String sumberPenghasilan,String visible){
		
		this.aabCode = aabCode;
		this.biBidangUsaha = biBidangUsaha;
		this.biBidangUsahaRef = biBidangUsahaRef;
		this.biGolDebitur = biGolDebitur;
		this.biGolDebiturRef = biGolDebiturRef;
		this.biPekerjaan = biPekerjaan;
		this.biPekerjaanRef = biPekerjaanRef;
		this.createdBy = createdBy;
		this.createdTimestamp = createdTimestamp;
		this.description = description;
		this.kodeBca = kodeBca;
		this.lastupdateBy = lastupdateBy;
		this.lastupdateTimestamp = lastupdateTimestamp;
		this.mandatory = mandatory;
		this.mbiProfesi = mbiProfesi;
		this.ocptCode = ocptCode;
		this.ocptCodeBca = ocptCodeBca;
		this.ocptRating = ocptRating;
		this.ocptType = ocptType;
		this.sumberPenghasilan = sumberPenghasilan;
		this.visible = visible;	
		
	}
	
}
