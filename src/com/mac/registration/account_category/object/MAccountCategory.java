package com.mac.registration.account_category.object;
// Generated Jan 3, 2015 11:33:40 AM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * MAccountCategory generated by hbm2java
 */
public class MAccountCategory implements java.io.Serializable {

    private String code;
    private MAccountCategory MAccountCategory;
    private String name;
    private String type;
    private String path;
    private Set<MAccountCategory> MAccountCategories = new HashSet<MAccountCategory>(0);

    public MAccountCategory() {
    }

    public MAccountCategory(String code) {
        this.code = code;
    }

    public MAccountCategory(String code, MAccountCategory MAccountCategory, String name, String type, String path, Set<MAccountCategory> MAccountCategories) {
        this.code = code;
        this.MAccountCategory = MAccountCategory;
        this.name = name;
        this.type = type;
        this.path = path;
        this.MAccountCategories = MAccountCategories;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MAccountCategory getMAccountCategory() {
        return this.MAccountCategory;
    }

    public void setMAccountCategory(MAccountCategory MAccountCategory) {
        this.MAccountCategory = MAccountCategory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<MAccountCategory> getMAccountCategories() {
        return this.MAccountCategories;
    }

    public void setMAccountCategories(Set<MAccountCategory> MAccountCategories) {
        this.MAccountCategories = MAccountCategories;
    }

    @Override
    public String toString() {
        return code + "-" + name;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }

        if ((other == null)) {
            return false;
        }

        if (!(other instanceof MAccountCategory)) {
            return false;
        }

        MAccountCategory castOther = (MAccountCategory) other;

        if (this.code == null && castOther.code == null) {
            return false;
        }

        if (!java.util.Objects.equals(this.code, castOther.code)) {
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
