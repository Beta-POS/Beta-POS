package com.mac.registration.store.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Feb 11, 2015 10:59:43 AM 
  *	Mohan Hibernate Mapping Generator
  */



/**
 * MStore generated by hbm2java
 */
public class MStore  implements java.io.Serializable {


     private String hash;
     private String code;
     private String branch;
     private String name;
     private boolean active;

    public MStore() {
    }

    public MStore(String hash, String code, String branch, String name, boolean active) {
       this.hash = hash;
       this.code = code;
       this.branch = branch;
       this.name = name;
       this.active = active;
    }
   
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
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
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
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
		
		if ( !(other instanceof MStore) ){
			return false;
		}
		
		MStore castOther = ( MStore ) other; 

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


