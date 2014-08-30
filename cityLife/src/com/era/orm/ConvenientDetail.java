package com.era.orm;



/**
 * ConvenientDetail entity. @author MyEclipse Persistence Tools
 */

public class ConvenientDetail  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String phone;
     private String log;
     private String dim;
     private Integer cityId;
     private Integer convenientId;
     private String address;


    // Constructors

    /** default constructor */
    public ConvenientDetail() {
    }

    
    /** full constructor */
    public ConvenientDetail(String name, String phone, String log, String dim, Integer cityId, Integer convenientId, String address) {
        this.name = name;
        this.phone = phone;
        this.log = log;
        this.dim = dim;
        this.cityId = cityId;
        this.convenientId = convenientId;
        this.address = address;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLog() {
        return this.log;
    }
    
    public void setLog(String log) {
        this.log = log;
    }

    public String getDim() {
        return this.dim;
    }
    
    public void setDim(String dim) {
        this.dim = dim;
    }

    public Integer getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getConvenientId() {
        return this.convenientId;
    }
    
    public void setConvenientId(Integer convenientId) {
        this.convenientId = convenientId;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
   








}