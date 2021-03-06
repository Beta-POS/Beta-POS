package com.mac.reports.report_parameter.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Feb 17, 2015 9:52:09 AM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.HashSet;
import java.util.Set;

/**
 * MSubCategory generated by hbm2java
 */
public class MSubCategory  implements java.io.Serializable {


     private String hash;
     private MMainCategory MMainCategory;
     private String code;
     private String branch;
     private String name;

    public MSubCategory() {
    }

	
    public MSubCategory(String hash, MMainCategory MMainCategory, String code, String branch, String name, boolean active) {
        this.hash = hash;
        this.MMainCategory = MMainCategory;
        this.code = code;
        this.branch = branch;
        this.name = name;
    }
 
   
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    public MMainCategory getMMainCategory() {
        return this.MMainCategory;
    }
    
    public void setMMainCategory(MMainCategory MMainCategory) {
        this.MMainCategory = MMainCategory;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getBranch() {
        return this.branch;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }



	@Override
     public String toString() {
		return code + "-" + name;
     }




	@Override
	public boolean equals(Object other) {
        if ( (this == other ) ){
			return true;
		}
		
		if ( (other == null ) ){
			return false;
		}
		
		if ( !(other instanceof MSubCategory) ){
			return false;
		}
		
		MSubCategory castOther = ( MSubCategory ) other; 

		if(this.code==null && castOther.code==null) {
			return false;
		}
		
		if(!java.util.Objects.equals(this.code, castOther.code)) {
            return false;
		}
        
		return true;
   }

    @Override
    public int hashCode() {
        int result = 17;
         
		result = result * 17 + java.util.Objects.hashCode(this.code);

        return result;
    }




}


