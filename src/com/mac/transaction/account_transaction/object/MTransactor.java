package com.mac.transaction.account_transaction.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On May 15, 2015 12:58:15 PM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.HashSet;
import java.util.Set;

/**
 * MTransactor generated by hbm2java
 */
public class MTransactor  implements java.io.Serializable {


     private String hash;
     private String code;
     private String branch;
     private String name;
     private String contactPerson;
     private String mobile;
     private String addressLine1;
     private String addressLine2;
     private String addressLine3;
     private String telephone1;
     private String telephone2;
     private String telephone3;
     private String fax;
     private String email;
     private String note;
     private String route;
     private String category;
     private double creditLimit;
     private int creditPeriod;
     private boolean client;
     private boolean supplier;
     private boolean active;
     private Set<TTransactorTransaction> TTransactorTransactions = new HashSet<TTransactorTransaction>(0);
     private Set<TTransactionSummary> TTransactionSummaries = new HashSet<TTransactionSummary>(0);

    public MTransactor() {
    }

	
    public MTransactor(String hash, String code, String branch, String name, String category, double creditLimit, int creditPeriod, boolean client, boolean supplier, boolean active) {
        this.hash = hash;
        this.code = code;
        this.branch = branch;
        this.name = name;
        this.category = category;
        this.creditLimit = creditLimit;
        this.creditPeriod = creditPeriod;
        this.client = client;
        this.supplier = supplier;
        this.active = active;
    }
    public MTransactor(String hash, String code, String branch, String name, String contactPerson, String mobile, String addressLine1, String addressLine2, String addressLine3, String telephone1, String telephone2, String telephone3, String fax, String email, String note, String route, String category, double creditLimit, int creditPeriod, boolean client, boolean supplier, boolean active, Set<TTransactorTransaction> TTransactorTransactions, Set<TTransactionSummary> TTransactionSummaries) {
       this.hash = hash;
       this.code = code;
       this.branch = branch;
       this.name = name;
       this.contactPerson = contactPerson;
       this.mobile = mobile;
       this.addressLine1 = addressLine1;
       this.addressLine2 = addressLine2;
       this.addressLine3 = addressLine3;
       this.telephone1 = telephone1;
       this.telephone2 = telephone2;
       this.telephone3 = telephone3;
       this.fax = fax;
       this.email = email;
       this.note = note;
       this.route = route;
       this.category = category;
       this.creditLimit = creditLimit;
       this.creditPeriod = creditPeriod;
       this.client = client;
       this.supplier = supplier;
       this.active = active;
       this.TTransactorTransactions = TTransactorTransactions;
       this.TTransactionSummaries = TTransactionSummaries;
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
    public String getContactPerson() {
        return this.contactPerson;
    }
    
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getAddressLine1() {
        return this.addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return this.addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine3() {
        return this.addressLine3;
    }
    
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }
    public String getTelephone1() {
        return this.telephone1;
    }
    
    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }
    public String getTelephone2() {
        return this.telephone2;
    }
    
    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }
    public String getTelephone3() {
        return this.telephone3;
    }
    
    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public String getRoute() {
        return this.route;
    }
    
    public void setRoute(String route) {
        this.route = route;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public double getCreditLimit() {
        return this.creditLimit;
    }
    
    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
    public int getCreditPeriod() {
        return this.creditPeriod;
    }
    
    public void setCreditPeriod(int creditPeriod) {
        this.creditPeriod = creditPeriod;
    }
    public boolean isClient() {
        return this.client;
    }
    
    public void setClient(boolean client) {
        this.client = client;
    }
    public boolean isSupplier() {
        return this.supplier;
    }
    
    public void setSupplier(boolean supplier) {
        this.supplier = supplier;
    }
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    public Set<TTransactorTransaction> getTTransactorTransactions() {
        return this.TTransactorTransactions;
    }
    
    public void setTTransactorTransactions(Set<TTransactorTransaction> TTransactorTransactions) {
        this.TTransactorTransactions = TTransactorTransactions;
    }
    public Set<TTransactionSummary> getTTransactionSummaries() {
        return this.TTransactionSummaries;
    }
    
    public void setTTransactionSummaries(Set<TTransactionSummary> TTransactionSummaries) {
        this.TTransactionSummaries = TTransactionSummaries;
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
		
		if ( !(other instanceof MTransactor) ){
			return false;
		}
		
		MTransactor castOther = ( MTransactor ) other; 

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

