package com.era.orm;



/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String conent;
     private String addtime;
     private Integer sellerId;
     private String user;
     private Double level;
     private String nickName;


    // Constructors

    /** default constructor */
    public Comment() {
    }

    
    /** full constructor */
    public Comment(String conent, String addtime, Integer sellerId, String user, Double level) {
        this.conent = conent;
        this.addtime = addtime;
        this.sellerId = sellerId;
        this.user = user;
        this.level = level;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getConent() {
        return this.conent;
    }
    
    public void setConent(String conent) {
        this.conent = conent;
    }

    public String getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Integer getSellerId() {
        return this.sellerId;
    }
    
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    public Double getLevel() {
        return this.level;
    }
    
    public void setLevel(Double level) {
        this.level = level;
    }


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}