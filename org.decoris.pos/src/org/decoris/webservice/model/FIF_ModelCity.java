/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.decoris.webservice.model;

/**
 *
 * @author ajimaulanacintafatma
 */
public class FIF_ModelCity {
    
    public String cityCode;
    public String city;
    public String provCode;
    public String createBy;
    public String createDate;
    public String updateBy;
    public String updateDate;
    public String dati2;
    public String digitZip;

    public FIF_ModelCity(String cityCode, String city,String provCode, String createdBy, String createdDate, String updatedBy,
    		String updatedDate, String dati2 ,String digitZip) {
        this.cityCode = cityCode;
        this.city = city;
        this.provCode = provCode;
        this.createBy = createdBy;
        this.createDate = createdDate;
        this.updateBy = updatedBy;
        this.updateDate = updatedDate;
        this.dati2 = dati2;
        this.digitZip = digitZip;
        
    }
   
}

