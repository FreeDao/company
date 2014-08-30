package com.era.orm;



/**
 * Convenient entity. @author MyEclipse Persistence Tools
 */

public class Convenient  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String imgUrl;
     private Integer cityId;


    // Constructors

    /** default constructor */
    public Convenient() {
    }

    
    /** full constructor */
    public Convenient(String name, String imgUrl, Integer cityId) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.cityId = cityId;
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

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
   








}