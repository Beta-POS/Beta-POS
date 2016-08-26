package com.mac.zsystem.util.item.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Feb 13, 2015 12:38:45 PM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.HashSet;
import java.util.Set;

/**
 * MItem generated by hbm2java
 */
public class MItem  implements java.io.Serializable {


     private String hash;
     private String code;
     private String branch;
     private String name;
     private String barcode;
     private String sinhalaName;
     private String tamilName;
     private Integer brand;
     private Integer make;
     private Integer model;
     private Integer size;
     private Integer unit;
     private Integer generic;
     private String department;
     private String mainCategory;
     private String subCategory;
     private int quantityInPack;
     private double costPrice;
     private double wholeSaleMargin;
     private double wholeSalePrice;
     private double maxDiscountPercent;
     private double lastSalePrice;
     private double saleMargin;
     private double salePrice;
     private double averagePrice;
     private boolean batch;
     private boolean serial;
     private boolean service;
     private boolean active;
     private Set<MItemBatch> MItemBatchs = new HashSet<MItemBatch>(0);

    public MItem() {
    }

	
    public MItem(String hash, String code, String branch, String name, int quantityInPack, double costPrice, double wholeSaleMargin, double wholeSalePrice, double maxDiscountPercent, double lastSalePrice, double saleMargin, double salePrice, double averagePrice, boolean batch, boolean serial, boolean service, boolean active) {
        this.hash = hash;
        this.code = code;
        this.branch = branch;
        this.name = name;
        this.quantityInPack = quantityInPack;
        this.costPrice = costPrice;
        this.wholeSaleMargin = wholeSaleMargin;
        this.wholeSalePrice = wholeSalePrice;
        this.maxDiscountPercent = maxDiscountPercent;
        this.lastSalePrice = lastSalePrice;
        this.saleMargin = saleMargin;
        this.salePrice = salePrice;
        this.averagePrice = averagePrice;
        this.batch = batch;
        this.serial = serial;
        this.service = service;
        this.active = active;
    }
    public MItem(String hash, String code, String branch, String name, String barcode, String sinhalaName, String tamilName, Integer brand, Integer make, Integer model, Integer size, Integer unit, Integer generic, String department, String mainCategory, String subCategory, int quantityInPack, double costPrice, double wholeSaleMargin, double wholeSalePrice, double maxDiscountPercent, double lastSalePrice, double saleMargin, double salePrice, double averagePrice, boolean batch, boolean serial, boolean service, boolean active, Set<MItemBatch> MItemBatchs) {
       this.hash = hash;
       this.code = code;
       this.branch = branch;
       this.name = name;
       this.barcode = barcode;
       this.sinhalaName = sinhalaName;
       this.tamilName = tamilName;
       this.brand = brand;
       this.make = make;
       this.model = model;
       this.size = size;
       this.unit = unit;
       this.generic = generic;
       this.department = department;
       this.mainCategory = mainCategory;
       this.subCategory = subCategory;
       this.quantityInPack = quantityInPack;
       this.costPrice = costPrice;
       this.wholeSaleMargin = wholeSaleMargin;
       this.wholeSalePrice = wholeSalePrice;
       this.maxDiscountPercent = maxDiscountPercent;
       this.lastSalePrice = lastSalePrice;
       this.saleMargin = saleMargin;
       this.salePrice = salePrice;
       this.averagePrice = averagePrice;
       this.batch = batch;
       this.serial = serial;
       this.service = service;
       this.active = active;
       this.MItemBatchs = MItemBatchs;
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
    public String getBarcode() {
        return this.barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getSinhalaName() {
        return this.sinhalaName;
    }
    
    public void setSinhalaName(String sinhalaName) {
        this.sinhalaName = sinhalaName;
    }
    public String getTamilName() {
        return this.tamilName;
    }
    
    public void setTamilName(String tamilName) {
        this.tamilName = tamilName;
    }
    public Integer getBrand() {
        return this.brand;
    }
    
    public void setBrand(Integer brand) {
        this.brand = brand;
    }
    public Integer getMake() {
        return this.make;
    }
    
    public void setMake(Integer make) {
        this.make = make;
    }
    public Integer getModel() {
        return this.model;
    }
    
    public void setModel(Integer model) {
        this.model = model;
    }
    public Integer getSize() {
        return this.size;
    }
    
    public void setSize(Integer size) {
        this.size = size;
    }
    public Integer getUnit() {
        return this.unit;
    }
    
    public void setUnit(Integer unit) {
        this.unit = unit;
    }
    public Integer getGeneric() {
        return this.generic;
    }
    
    public void setGeneric(Integer generic) {
        this.generic = generic;
    }
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getMainCategory() {
        return this.mainCategory;
    }
    
    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }
    public String getSubCategory() {
        return this.subCategory;
    }
    
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
    public int getQuantityInPack() {
        return this.quantityInPack;
    }
    
    public void setQuantityInPack(int quantityInPack) {
        this.quantityInPack = quantityInPack;
    }
    public double getCostPrice() {
        return this.costPrice;
    }
    
    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
    public double getWholeSaleMargin() {
        return this.wholeSaleMargin;
    }
    
    public void setWholeSaleMargin(double wholeSaleMargin) {
        this.wholeSaleMargin = wholeSaleMargin;
    }
    public double getWholeSalePrice() {
        return this.wholeSalePrice;
    }
    
    public void setWholeSalePrice(double wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }
    public double getMaxDiscountPercent() {
        return this.maxDiscountPercent;
    }
    
    public void setMaxDiscountPercent(double maxDiscountPercent) {
        this.maxDiscountPercent = maxDiscountPercent;
    }
    public double getLastSalePrice() {
        return this.lastSalePrice;
    }
    
    public void setLastSalePrice(double lastSalePrice) {
        this.lastSalePrice = lastSalePrice;
    }
    public double getSaleMargin() {
        return this.saleMargin;
    }
    
    public void setSaleMargin(double saleMargin) {
        this.saleMargin = saleMargin;
    }
    public double getSalePrice() {
        return this.salePrice;
    }
    
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    public double getAveragePrice() {
        return this.averagePrice;
    }
    
    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }
    public boolean isBatch() {
        return this.batch;
    }
    
    public void setBatch(boolean batch) {
        this.batch = batch;
    }
    public boolean isSerial() {
        return this.serial;
    }
    
    public void setSerial(boolean serial) {
        this.serial = serial;
    }
    public boolean isService() {
        return this.service;
    }
    
    public void setService(boolean service) {
        this.service = service;
    }
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    public Set<MItemBatch> getMItemBatchs() {
        return this.MItemBatchs;
    }
    
    public void setMItemBatchs(Set<MItemBatch> MItemBatchs) {
        this.MItemBatchs = MItemBatchs;
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
		
		if ( !(other instanceof MItem) ){
			return false;
		}
		
		MItem castOther = ( MItem ) other; 

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

