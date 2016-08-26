package com.mac.registration.item.object;

/**
 * @author Channa Mohan
 *
 * Created On Feb 27, 2015 12:48:50 PM Mohan Hibernate Mapping Generator
 */
import java.util.HashSet;
import java.util.Set;

/**
 * MItemProperty generated by hbm2java
 */
public class MItemProperty implements java.io.Serializable {

    private Integer indexNo;
    private String branch;
    private String type;
    private String name;
    private Set<MItem> MItemsForMake = new HashSet<MItem>(0);
    private Set<MItem> MItemsForBrand = new HashSet<MItem>(0);
    private Set<MItem> MItemsForSize = new HashSet<MItem>(0);
    private Set<MItem> MItemsForModel = new HashSet<MItem>(0);
    private Set<MItem> MItemsForGeneric = new HashSet<MItem>(0);
    private Set<MItem> MItemsForUnit = new HashSet<MItem>(0);

    public MItemProperty() {
    }

    public MItemProperty(String branch, String type, String name) {
        this.branch = branch;
        this.type = type;
        this.name = name;
    }

    public MItemProperty(String branch, String type, String name, Set<MItem> MItemsForMake, Set<MItem> MItemsForBrand, Set<MItem> MItemsForSize, Set<MItem> MItemsForModel, Set<MItem> MItemsForGeneric, Set<MItem> MItemsForUnit) {
        this.branch = branch;
        this.type = type;
        this.name = name;
        this.MItemsForMake = MItemsForMake;
        this.MItemsForBrand = MItemsForBrand;
        this.MItemsForSize = MItemsForSize;
        this.MItemsForModel = MItemsForModel;
        this.MItemsForGeneric = MItemsForGeneric;
        this.MItemsForUnit = MItemsForUnit;
    }

    public Integer getIndexNo() {
        return this.indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MItem> getMItemsForMake() {
        return this.MItemsForMake;
    }

    public void setMItemsForMake(Set<MItem> MItemsForMake) {
        this.MItemsForMake = MItemsForMake;
    }

    public Set<MItem> getMItemsForBrand() {
        return this.MItemsForBrand;
    }

    public void setMItemsForBrand(Set<MItem> MItemsForBrand) {
        this.MItemsForBrand = MItemsForBrand;
    }

    public Set<MItem> getMItemsForSize() {
        return this.MItemsForSize;
    }

    public void setMItemsForSize(Set<MItem> MItemsForSize) {
        this.MItemsForSize = MItemsForSize;
    }

    public Set<MItem> getMItemsForModel() {
        return this.MItemsForModel;
    }

    public void setMItemsForModel(Set<MItem> MItemsForModel) {
        this.MItemsForModel = MItemsForModel;
    }

    public Set<MItem> getMItemsForGeneric() {
        return this.MItemsForGeneric;
    }

    public void setMItemsForGeneric(Set<MItem> MItemsForGeneric) {
        this.MItemsForGeneric = MItemsForGeneric;
    }

    public Set<MItem> getMItemsForUnit() {
        return this.MItemsForUnit;
    }

    public void setMItemsForUnit(Set<MItem> MItemsForUnit) {
        this.MItemsForUnit = MItemsForUnit;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof MItemProperty)) {
            return false;
        }

        MItemProperty castOther = (MItemProperty) other;

        if (this.indexNo == null && castOther.indexNo == null) {
            return false;
        }

        if (!java.util.Objects.equals(this.indexNo, castOther.indexNo)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 17 + java.util.Objects.hashCode(this.indexNo);

        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
