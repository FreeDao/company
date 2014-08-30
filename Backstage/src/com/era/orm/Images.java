package com.era.orm;



/**
 * Images entity. @author MyEclipse Persistence Tools
 */

public class Images  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String imgUrl;
     private Integer type;
     private Integer compositeId;


    // Constructors

    /** default constructor */
    public Images() {
    }

    
    /** full constructor */
    public Images(String imgUrl, Integer type, Integer compositeId) {
        this.imgUrl = imgUrl;
        this.type = type;
        this.compositeId = compositeId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCompositeId() {
        return this.compositeId;
    }
    
    public void setCompositeId(Integer compositeId) {
        this.compositeId = compositeId;
    }
   








}