package com.mac.transaction.item_transaction_new.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Mar 20, 2015 9:41:46 AM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * MItemBatch generated by hbm2java
 */
public class MItemBatch  implements java.io.Serializable {


     private String hash;
     private MItem MItem;
     private String branch;
     private int batchNumber;
     private double salePrice;
     private double lastSalesPrice;
     private double wholeSalePrice;
     private double costPrice;
     private Date expireDate;
     private Set<TTransactionDetail> TTransactionDetails = new HashSet<TTransactionDetail>(0);

    public MItemBatch() {
    }

	
    public MItemBatch(String hash, MItem MItem, String branch, int batchNumber, double salePrice, double lastSalesPrice, double wholeSalePrice, double costPrice, Date expireDate) {
        this.hash = hash;
        this.MItem = MItem;
        this.branch = branch;
        this.batchNumber = batchNumber;
        this.salePrice = salePrice;
        this.lastSalesPrice = lastSalesPrice;
        this.wholeSalePrice = wholeSalePrice;
        this.costPrice = costPrice;
        this.expireDate = expireDate;
    }
    public MItemBatch(String hash, MItem MItem, String branch, int batchNumber, double salePrice, double lastSalesPrice, double wholeSalePrice, double costPrice, Date expireDate, Set<TTransactionDetail> TTransactionDetails) {
       this.hash = hash;
       this.MItem = MItem;
       this.branch = branch;
       this.batchNumber = batchNumber;
       this.salePrice = salePrice;
       this.lastSalesPrice = lastSalesPrice;
       this.wholeSalePrice = wholeSalePrice;
       this.costPrice = costPrice;
       this.expireDate = expireDate;
       this.TTransactionDetails = TTransactionDetails;
    }
   
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    public MItem getMItem() {
        return this.MItem;
    }
    
    public void setMItem(MItem MItem) {
        this.MItem = MItem;
    }
    public String getBranch() {
        return this.branch;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public int getBatchNumber() {
        return this.batchNumber;
    }
    
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
    public double getSalePrice() {
        return this.salePrice;
    }
    
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    public double getLastSalesPrice() {
        return this.lastSalesPrice;
    }
    
    public void setLastSalesPrice(double lastSalesPrice) {
        this.lastSalesPrice = lastSalesPrice;
    }
    public double getWholeSalePrice() {
        return this.wholeSalePrice;
    }
    
    public void setWholeSalePrice(double wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }
    public double getCostPrice() {
        return this.costPrice;
    }
    
    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
    public Date getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    public Set<TTransactionDetail> getTTransactionDetails() {
        return this.TTransactionDetails;
    }
    
    public void setTTransactionDetails(Set<TTransactionDetail> TTransactionDetails) {
        this.TTransactionDetails = TTransactionDetails;
    }










}


