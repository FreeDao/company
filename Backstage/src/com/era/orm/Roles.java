package com.era.orm;



/**
 * Roles entity. @author MyEclipse Persistence Tools
 */

public class Roles  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String rolesName;
     private String rolesAction;


    // Constructors

    /** default constructor */
    public Roles() {
    }

	/** minimal constructor */
    public Roles(String rolesName) {
        this.rolesName = rolesName;
    }
    
    /** full constructor */
    public Roles(String rolesName, String rolesAction) {
        this.rolesName = rolesName;
        this.rolesAction = rolesAction;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolesName() {
        return this.rolesName;
    }
    
    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getRolesAction() {
        return this.rolesAction;
    }
    
    public void setRolesAction(String rolesAction) {
        this.rolesAction = rolesAction;
    }
   








}