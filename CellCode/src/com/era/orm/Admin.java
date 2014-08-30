package com.era.orm;



/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String adminName;
     private String password;
     private String addtime;
     private Integer isSuper;
     private String nickName;
     private String phone;
     private String qq;
     private String email;
     private Integer roles;


    // Constructors

    /** default constructor */
    public Admin() {
    }

	/** minimal constructor */
    public Admin(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }
    
    /** full constructor */
    public Admin(String adminName, String password, String addtime, Integer isSuper, String nickName, String phone, String qq, String email, Integer roles) {
        this.adminName = adminName;
        this.password = password;
        this.addtime = addtime;
        this.isSuper = isSuper;
        this.nickName = nickName;
        this.phone = phone;
        this.qq = qq;
        this.email = email;
        this.roles = roles;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return this.adminName;
    }
    
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Integer getIsSuper() {
        return this.isSuper;
    }
    
    public void setIsSuper(Integer isSuper) {
        this.isSuper = isSuper;
    }

    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return this.qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoles() {
        return this.roles;
    }
    
    public void setRoles(Integer roles) {
        this.roles = roles;
    }
   








}