package com.mac.transaction.transaction_registration.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On May 15, 2015 12:29:03 PM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.HashSet;
import java.util.Set;

/**
 * MStore generated by hbm2java
 */
public class MStore  implements java.io.Serializable {


     private String hash;
     private MBranch MBranch;
     private String code;
     private String name;
     private boolean active;
     private Set<RTransactionType> RTransactionTypesForInStore = new HashSet<RTransactionType>(0);
     private Set<RTransactionType> RTransactionTypesForOutStore = new HashSet<RTransactionType>(0);

    public MStore() {
    }

	
    public MStore(String hash, MBranch MBranch, String code, String name, boolean active) {
        this.hash = hash;
        this.MBranch = MBranch;
        this.code = code;
        this.name = name;
        this.active = active;
    }
    public MStore(String hash, MBranch MBranch, String code, String name, boolean active, Set<RTransactionType> RTransactionTypesForInStore, Set<RTransactionType> RTransactionTypesForOutStore) {
       this.hash = hash;
       this.MBranch = MBranch;
       this.code = code;
       this.name = name;
       this.active = active;
       this.RTransactionTypesForInStore = RTransactionTypesForInStore;
       this.RTransactionTypesForOutStore = RTransactionTypesForOutStore;
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
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    public Set<RTransactionType> getRTransactionTypesForInStore() {
        return this.RTransactionTypesForInStore;
    }
    
    public void setRTransactionTypesForInStore(Set<RTransactionType> RTransactionTypesForInStore) {
        this.RTransactionTypesForInStore = RTransactionTypesForInStore;
    }
    public Set<RTransactionType> getRTransactionTypesForOutStore() {
        return this.RTransactionTypesForOutStore;
    }
    
    public void setRTransactionTypesForOutStore(Set<RTransactionType> RTransactionTypesForOutStore) {
        this.RTransactionTypesForOutStore = RTransactionTypesForOutStore;
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


