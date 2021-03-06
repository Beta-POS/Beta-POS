package com.mac.registration.branch.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Mar 20, 2015 5:08:50 PM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.HashSet;
import java.util.Set;

/**
 * MCompany generated by hbm2java
 */
public class MCompany  implements java.io.Serializable {


     private String code;
     private String name;
     private String versionId;
     private Set<MBranch> MBranchs = new HashSet<MBranch>(0);

    public MCompany() {
    }

	
    public MCompany(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public MCompany(String code, String name, String versionId, Set<MBranch> MBranchs) {
       this.code = code;
       this.name = name;
       this.versionId = versionId;
       this.MBranchs = MBranchs;
    }
   
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getVersionId() {
        return this.versionId;
    }
    
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
    public Set<MBranch> getMBranchs() {
        return this.MBranchs;
    }
    
    public void setMBranchs(Set<MBranch> MBranchs) {
        this.MBranchs = MBranchs;
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
		
		if ( !(other instanceof MCompany) ){
			return false;
		}
		
		MCompany castOther = ( MCompany ) other; 

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


