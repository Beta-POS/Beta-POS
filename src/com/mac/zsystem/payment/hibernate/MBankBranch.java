package com.mac.zsystem.payment.hibernate;

/**
  *	@author Channa Mohan
  *	
  *	Created On Jun 9, 2015 11:33:12 AM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.HashSet;
import java.util.Set;

/**
 * MBankBranch generated by hbm2java
 */
public class MBankBranch  implements java.io.Serializable {


     private String code;
     private String name;
     private String bank;
     private Set<TCheque> TCheques = new HashSet<TCheque>(0);

    public MBankBranch() {
    }

	
    public MBankBranch(String code) {
        this.code = code;
    }
    public MBankBranch(String code, String name, String bank, Set<TCheque> TCheques) {
       this.code = code;
       this.name = name;
       this.bank = bank;
       this.TCheques = TCheques;
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
    public String getBank() {
        return this.bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }
    public Set<TCheque> getTCheques() {
        return this.TCheques;
    }
    
    public void setTCheques(Set<TCheque> TCheques) {
        this.TCheques = TCheques;
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
		
		if ( !(other instanceof MBankBranch) ){
			return false;
		}
		
		MBankBranch castOther = ( MBankBranch ) other; 

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

