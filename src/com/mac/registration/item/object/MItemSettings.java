package com.mac.registration.item.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Feb 27, 2015 12:48:50 PM 
  *	Mohan Hibernate Mapping Generator
  */



/**
 * MItemSettings generated by hbm2java
 */
public class MItemSettings  implements java.io.Serializable {


     private String hash;
     private MItem MItem;
     private String branch;
     private String store;
     private int reorderLevel;
     private int reorderQuantity;
     private String shelf;

    public MItemSettings() {
    }

	
    public MItemSettings(String hash, MItem MItem, String branch, int reorderLevel, int reorderQuantity, String shelf) {
        this.hash = hash;
        this.MItem = MItem;
        this.branch = branch;
        this.reorderLevel = reorderLevel;
        this.reorderQuantity = reorderQuantity;
        this.shelf = shelf;
    }
    public MItemSettings(String hash, MItem MItem, String branch, String store, int reorderLevel, int reorderQuantity, String shelf) {
       this.hash = hash;
       this.MItem = MItem;
       this.branch = branch;
       this.store = store;
       this.reorderLevel = reorderLevel;
       this.reorderQuantity = reorderQuantity;
       this.shelf = shelf;
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
    public String getStore() {
        return this.store;
    }
    
    public void setStore(String store) {
        this.store = store;
    }
    public int getReorderLevel() {
        return this.reorderLevel;
    }
    
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    public int getReorderQuantity() {
        return this.reorderQuantity;
    }
    
    public void setReorderQuantity(int reorderQuantity) {
        this.reorderQuantity = reorderQuantity;
    }
    public String getShelf() {
        return this.shelf;
    }
    
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }










}


