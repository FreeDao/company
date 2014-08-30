package com.era.orm;



/**
 * UserFeedBack entity. @author MyEclipse Persistence Tools
 */

public class UserFeedBack  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer userId;
     private String content;
     private String iphone;


    // Constructors

    /** default constructor */
    public UserFeedBack() {
    }

    
    /** full constructor */
    public UserFeedBack(Integer userId, String content, String iphone) {
        this.userId = userId;
        this.content = content;
        this.iphone = iphone;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getIphone() {
        return this.iphone;
    }
    
    public void setIphone(String iphone) {
        this.iphone = iphone;
    }
   








}