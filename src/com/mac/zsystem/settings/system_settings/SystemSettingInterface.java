/*
 *  SystemSettingInterface.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 18, 2015, 1:34:56 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.settings.system_settings;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.zsystem.settings.system_settings.object.RSystemSetting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mohan
 */
public class SystemSettingInterface {
    //auto code generate

    public static final RSystemSetting CLIENT_AUTO_CODE = new RSystemSetting("CLIENT_AUTO_CODE", "Auto Generate Client Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting SUPPLIER_AUTO_CODE = new RSystemSetting("SUPPLIER_AUTO_CODE", "Auto Generate Supplier Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting EMPLOYEE_AUTO_CODE = new RSystemSetting("EMPLOYEE_AUTO_CODE", "Auto Generate Employee Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting ROUTE_AUTO_CODE = new RSystemSetting("ROUTE_AUTO_CODE", "Auto Generate Route Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting TRANSACTOR_CATEGORY_AUTO_CODE = new RSystemSetting("TRNSACT_CAT_AUTO_CODE", "Auto Generate Transactor Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting COMPANY_AUTO_CODE = new RSystemSetting("COMPANY_AUTO_CODE", "Auto Generate Company Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting BRANCH_AUTO_CODE = new RSystemSetting("BRANCH_AUTO_CODE", "Auto Generate Branch Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting STORE_AUTO_CODE = new RSystemSetting("STORE_AUTO_CODE", "Auto Generate Store Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting ITEM_AUTO_CODE = new RSystemSetting("ITEM_AUTO_CODE", "Auto Generate Item Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting DEPARTMENT_AUTO_CODE = new RSystemSetting("DEPARTMENT_AUTO_CODE", "Auto Generate Department Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting MAIN_CATEGORY_AUTO_CODE = new RSystemSetting("MAIN_CAT_AUTO_CODE", "Auto Generate Main Category Code Length", "3", "3", SystemSettingTypes.INTEGER);
    public static final RSystemSetting SUB_CATEGORY_AUTO_CODE = new RSystemSetting("SUB_CAT_AUTO_CODE", "Auto Generate Sub Category Code Length", "3", "3", SystemSettingTypes.INTEGER);
    //all settings
    public  static final List<RSystemSetting> SYSTEM_SETTINGS = Arrays.asList(
            CLIENT_AUTO_CODE,
            SUPPLIER_AUTO_CODE,
            EMPLOYEE_AUTO_CODE,
            ROUTE_AUTO_CODE,
            TRANSACTOR_CATEGORY_AUTO_CODE,
            COMPANY_AUTO_CODE,
            BRANCH_AUTO_CODE,
            STORE_AUTO_CODE,
            ITEM_AUTO_CODE,
            DEPARTMENT_AUTO_CODE,
            MAIN_CATEGORY_AUTO_CODE,
            SUB_CATEGORY_AUTO_CODE);
//    private static final HashMap<String, RSystemSetting> SETTINGS_MAP = new HashMap<>();

    public static void checkSystemSettings(HibernateDatabaseService databaseService) throws DatabaseException {
        List<RSystemSetting> databaseSystemSettings = databaseService.getCollection(RSystemSetting.class);
        List<RSystemSetting> notExistSettings = new ArrayList<>();

        RSystemSetting dbSystemSetting;
        for (RSystemSetting systemSetting : SYSTEM_SETTINGS) {
            dbSystemSetting = getSystemSetting(systemSetting.getSetting(), databaseSystemSettings);

            if (dbSystemSetting == null) {
                notExistSettings.add(systemSetting);
            } else {
                systemSetting.setValue(String.copyValueOf(dbSystemSetting.getValue().toCharArray()));
            }
        }

        databaseService.save(notExistSettings);

    }

    public static RSystemSetting getSystemSetting(RSystemSetting systemSetting) {
//        RSystemSetting fromMap = SETTINGS_MAP.get(systemSetting.getSetting());
        RSystemSetting fromMap = getSystemSetting(systemSetting.getSetting(), SYSTEM_SETTINGS);

        if (fromMap != null) {
            return fromMap;
        } else {
            return systemSetting;
        }
    }

    private static void mergeSystemSetting(RSystemSetting dbSystemSetting, RSystemSetting systemSetting) {
        systemSetting.setValue(dbSystemSetting.getValue());
    }

    private static RSystemSetting getSystemSetting(String code, List<RSystemSetting> systemSettings) {
        for (RSystemSetting rSystemSetting : systemSettings) {
            if (rSystemSetting.getSetting().equals(code)) {
                return rSystemSetting;
            }
        }
        return null;
    }
}
