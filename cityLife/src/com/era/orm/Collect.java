package com.era.orm;



/**
 * Collect entity. @author MyEclipse Persistence Tools
 */

public class Collect  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer sellerId;
     private String addtime;
     private Integer userId;


    // Constructors

    /** default constructor */
    public Collect() {
    }

    
    /** full constructor */
    public Collect(Integer sellerId, String addtime, Integer userId) {
        this.sellerId = sellerId;
        this.addtime = addtime;
        this.userId = userId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return this.sellerId;
    }
    
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
   








}