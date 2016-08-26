package com.mac.zsystem.settings.branch_settings.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Feb 18, 2015 12:04:29 PM 
  *	Mohan Hibernate Mapping Generator
  */



/**
 * RBranchSetting generated by hbm2java
 */
public class RBranchSetting  implements java.io.Serializable {


     private String hash;
     private MBranch MBranch;
     private String setting;
     private String description;
     private String value;
     private String defaultValue;
     private String type;

    public RBranchSetting() {
    }

    public RBranchSetting(String hash, MBranch MBranch, String setting, String description, String value, String defaultValue, String type) {
       this.hash = hash;
       this.MBranch = MBranch;
       this.setting = setting;
       this.description = description;
       this.value = value;
       this.defaultValue = defaultValue;
       this.type = type;
    }
   
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    public MBranch getMBranch() {
        return this.MBranch;
    }
    
    public void setMBranch(MBranch MBranch) {
        this.MBranch = MBranch;
    }
    public String getSetting() {
        return this.setting;
    }
    
    public void setSetting(String setting) {
        this.setting = setting;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }










}


