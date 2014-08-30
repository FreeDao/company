package com.era.orm;



/**
 * NewsDetails entity. @author MyEclipse Persistence Tools
 */

public class NewsDetails  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer cityId;
     private String title;
     private String addtime;
     private String author;
     private Integer imagesId;
     private String conent;


    // Constructors

    /** default constructor */
    public NewsDetails() {
    }

    
    /** full constructor */
    public NewsDetails(Integer cityId, String title, String addtime, String author, Integer imagesId, String conent) {
        this.cityId = cityId;
        this.title = title;
        this.addtime = addtime;
        this.author = author;
        this.imagesId = imagesId;
        this.conent = conent;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getImagesId() {
        return this.imagesId;
    }
    
    public void setImagesId(Integer imagesId) {
        this.imagesId = imagesId;
    }

    public String getConent() {
        return this.conent;
    }
    
    public void setConent(String conent) {
        this.conent = conent;
    }
   








}