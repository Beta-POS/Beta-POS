/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.item;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.service.AbstractService;
import com.mac.registration.item.object.MDepartment;
import com.mac.registration.item.object.MItem;
import com.mac.registration.item.object.MItemProperty;
import com.mac.registration.item.object.MMainCategory;
import com.mac.registration.item.object.MSubCategory;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.item.ItemBatchUtil;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilanga
 */
public class SERItem extends AbstractService {

    private List<MDepartment> departments;
    private List<MMainCategory> mainCategorys;
    private List<MSubCategory> subCategorys;
    private List<MItemProperty> itemProperties;

    public SERItem(Component component) {
        super(component);
      
    }

    private synchronized void initData() throws DatabaseException {
        if (departments == null) {
            departments = getDatabaseService().getCollection(getDatabaseService().initCriteria(MDepartment.class)
                    .add(Restrictions.eq("branch", HashUtil.getBranch()))
                    .add(Restrictions.eq("active", true)));
            System.out.println(departments);
        }

        if (mainCategorys == null) {
            mainCategorys = getDatabaseService().getCollection(getDatabaseService().initCriteria(MMainCategory.class)
                    .add(Restrictions.eq("branch", HashUtil.getBranch()))
                    .add(Restrictions.eq("active", true)));
            System.out.println(mainCategorys);
        }

        if (subCategorys == null) {
            subCategorys = getDatabaseService().getCollection(getDatabaseService().initCriteria(MSubCategory.class)
                    .add(Restrictions.eq("branch", HashUtil.getBranch()))
                    .add(Restrictions.eq("active", true)));
            System.out.println(subCategorys);
        }

        if (itemProperties == null) {
            itemProperties = getDatabaseService().getCollection(getDatabaseService().initCriteria(MItemProperty.class).add(Restrictions.eq("branch", HashUtil.getBranch())));
        }

    }

    public void saveItem(MItem mItem, boolean isNew) throws DatabaseException {
        getDatabaseService().save(mItem);//

        if (isNew) {
            ItemBatchUtil.updatePrice(
                    getDatabaseService(),
                    mItem.getHash(),
                    mItem.getSalePrice(),
                    mItem.getWholeSalePrice(),
                    mItem.getCostPrice());
        }
    }

    public List getDepartments() {
        try {
//            String hql = "FROM com.mac.registration.item.object.MDepartment WHERE active = true";
//            department = getDatabaseService().getCollection(hql);

//            department = getDatabaseService().getCollection(getDatabaseService().initCriteria(MDepartment.class)
//                    .add(Restrictions.eq("active", true)));

            initData();


        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            departments = new ArrayList();
        }
        return departments;
    }

    public List getMainCategory(MDepartment department) {
        List mainCategoryList;
        try {
//            Map<String, Object> params = new HashMap<>();
//            params.put("DEPARTMENT", department);
//            String hql = "FROM com.mac.registration.item.object.MMainCategory WHERE department=:DEPARTMENT and active = true";
//            mainCategory = getDatabaseService().getCollection(hql, params);

//            mainCategory = getDatabaseService().getCollection(getDatabaseService().initCriteria(MMainCategory.class)
//                    .add(Restrictions.eq("MDepartment", department))
//                    .add(Restrictions.eq("active", true)));

            initData();
            mainCategoryList = new ArrayList();
            if (department != null) {
                for (MMainCategory mainCategory : mainCategorys) {
                    if (mainCategory.getMDepartment() != null
                            ? mainCategory.getMDepartment().getHash().equals(department.getHash())
                            : false) {
                        mainCategoryList.add(mainCategory);
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            mainCategoryList = new ArrayList();
        }
        return mainCategoryList;
    }

    public List getSubCategory(MMainCategory mainCategory) {
        List subCategoryList;
        try {
//            Map<String, Object> params = new HashMap<>();
//            params.put("MAIN_CATEGORY", mainCategory);
//            String hql = "FROM com.mac.registration.item.object.MSubCategory WHERE main_category=:MAIN_CATEGORY and active = true";
//            subCategory = getDatabaseService().getCollection(hql, params);

//            subCategory = getDatabaseService().getCollection(getDatabaseService().initCriteria(MSubCategory.class)
//                    .add(Restrictions.eq("MMainCategory", mainCategory))
//                    .add(Restrictions.eq("active", true)));

            initData();
            subCategoryList = new ArrayList();
            if (mainCategory != null) {
                for (MSubCategory subCategory : subCategorys) {
                    if (subCategory.getMMainCategory() != null
                            ? subCategory.getMMainCategory().getHash().equals(mainCategory.getHash()) : false) {
                        subCategoryList.add(subCategory);
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            subCategoryList = new ArrayList();
        }
        return subCategoryList;
    }

    public List getItemProperties(String type) {
        List itemProperties;
        try {
//            Map<String, Object> params = new HashMap<>();
//            params.put("TYPE", type);
//            String hql = "FROM com.mac.registration.item.object.MItemProperty WHERE type=:TYPE";

//            itemProperties = getDatabaseService().getCollection(hql, params);

//            Criteria criteria = getDatabaseService().initCriteria(MItemProperty.class)
//                    .add(Restrictions.eq("type", type));
//            itemProperties = getDatabaseService().getCollection(criteria);

            initData();
            itemProperties = new ArrayList();
            for (MItemProperty mItemProperty : this.itemProperties) {
                if (mItemProperty.getType().equals(type)) {
                    itemProperties.add(mItemProperty);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            itemProperties = new ArrayList();
        }
        return itemProperties;
    }
}
