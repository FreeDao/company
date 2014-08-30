package com.era.orm;



/**
 * Client entity. @author MyEclipse Persistence Tools
 */

public class Client  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer marketId;
     private String name;
     private String imgUrl;
     private String conent;
     private String url;
     private String qrUrl;
     private Integer cityId;


    // Constructors

    /** default constructor */
    public Client() {
    }

    
    /** full constructor */
    public Client(Integer marketId, String name, String imgUrl, String conent, String url, String qrUrl, Integer cityId) {
        this.marketId = marketId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.conent = conent;
        this.url = url;
        this.qrUrl = qrUrl;
        this.cityId = cityId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMarketId() {
        return this.marketId;
    }
    
    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
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

    public String getConent() {
        return this.conent;
    }
    
    public void setConent(String conent) {
        this.conent = conent;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getQrUrl() {
        return this.qrUrl;
    }
    
    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public Integer getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
   








}