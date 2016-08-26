package com.mac.zresources;

/*
 *  SphereResources.java
 *  
 *  @author channa mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Jun 20, 2014, 11:50:55 AM
 *  Copyrights Channa Mohan, All rights reserved.
 *  
 */
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author mohan
 */
public class SphereResources {

    public static URL getImageIconURL(String path) {
        return SphereResources.class.getResource(path);
    }

    public static ImageIcon getImageIcon(String path) {
        ImageIcon icon;
        try {
            icon = new ImageIcon(SphereResources.class.getResource(path));
        } catch (Exception e) {
            icon = null;
        }
        return icon;
    }

    public static ImageIcon getImageIcon(String path, int height, int width) {
        ImageIcon icon;
        try {
            icon = new ImageIcon(new ImageIcon(SphereResources.class.getResource(path)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            icon = null;
        }

        return icon;
    }
    //REGISTRATION
    public static final String REGISTRATION_PEOPLE = "icons/registration/people.png";
    public static final String EMPLOYEE = "icons/registration/newEmployee.png";
    public static final String CLIENT = "icons/registration/client.png";
    public static final String SUPPLIER = "icons/registration/supplier.png";
    public static final String MAIN_CATEGORY = "icons/registration/mainCategory.png";
    public static final String SUB_CATEGORY = "icons/registration/subCategory.png";
    public static final String ITEM = "icons/registration/item.png";
    public static final String ITEM_PRICE = "icons/registration/itemPrice.png";
    public static final String BRAND = "icons/registration/brand.png";
    public static final String MAKE = "icons/registration/make.png";
    public static final String COMPANY = "icons/registration/company.png";
    public static final String STORE = "icons/registration/store.png";
    public static final String DEPARTMENT = "icons/registration/department.png";
    public static final String UNIT = "icons/registration/unit.png";
    public static final String GENERIC = "icons/registration/generic.png";
    public static final String ROUTE = "icons/registration/route.png";
    public static final String SIZE = "icons/registration/size.png";
    public static final String MODEL = "icons/registration/model.png";
    public static final String BRANCH = "icons/registration/branch.png";
    //
    public static final String SYSTEM_SETTING = "icons/setting/system_setting.png";
    public static final String BRANCH_SETTING = "icons/setting/branch_setting.png";
    public static final String TRANSACTION_TYPE = "icons/setting/transaction_type.png";
    public static final String REPORT_REGISTRATION = "icons/setting/report_registration.png";
    public static final String USER_ROLE_REGISTRATION = "icons/setting/user_role_registration.png";
    public static final String USER_PERMISSION = "icons/setting/user_permission.png";
    public static final String USER_ROLE_PERMISSION = "icons/setting/user_role_permission.png";
    public static final String THEME = "icons/setting/theme.png";
    
    public static final String TRANSACTION_REPORTS = "icons/reports/transaction_report.png";
//    public static final String SALSE2 = "icons/sales/sales2.png";
//    public static final String PANEL = "icons/setting/panel.png";
//    public static final String PERMISSION = "icons/setting/permition.png";
}
