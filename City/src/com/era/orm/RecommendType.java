package com.era.orm;



/**
 * RecommendType entity. @author MyEclipse Persistence Tools
 */

public class RecommendType  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String type;
     private Integer hot;


    // Constructors

    /** default constructor */
    public RecommendType() {
    }

    
    /** full constructor */
    public RecommendType(String type, Integer hot) {
        this.type = type;
        this.hot = hot;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Integer getHot() {
        return this.hot;
    }
    
    public void setHot(Integer hot) {
        this.hot = hot;
    }
   








}