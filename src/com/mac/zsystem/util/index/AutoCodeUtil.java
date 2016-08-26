/*
 *  AutoCodeUtil.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 18, 2015, 2:35:40 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.util.index;

import com.mac.af.core.ApplicationException;
import com.mac.zsystem.settings.system_settings.object.RSystemSetting;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author mohan
 */
public class AutoCodeUtil {

    public static String getCode(RSystemSetting systemSetting, String table, String where) throws ApplicationException {
        String value = systemSetting.getValue();

        String prefix = value.substring(0, value.indexOf("0"));
        String numbers = value.substring(value.indexOf("0"));

        int nextIndex = IndexNumberUtil.getNextIndex(table, where);

        NumberFormat format = new DecimalFormat(numbers);

        return prefix + format.format(nextIndex);
    }

    public static String getCode(String prefix, int numberCount, String table, String where) throws ApplicationException {
        int nextIndex = IndexNumberUtil.getNextIndex(table, where);

        String numbers = "";
        for (int i = 0; i < numberCount; i++) {
            numbers = numbers + "0";
        }

        NumberFormat format = new DecimalFormat(numbers);

        return prefix + format.format(nextIndex);
    }
}
