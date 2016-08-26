/*
 *  HashUtil.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 11, 2015, 10:05:46 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.util.hash;

import com.mac.af.core.environment.CApplication;

/**
 *
 * @author mohan
 */
public class HashUtil {

    public static String getHash(String code) {
        return CApplication.getSessionVariable(CApplication.STORE_ID)
                + "-" + code;
    }

    public static String getHash(String codeOne, String codeTwo) {
        return CApplication.getSessionVariable(CApplication.STORE_ID)
                + "-" + codeOne
                + "-" + codeTwo;
    }

    public static String getHashWithoutBranch(String... codes) {
        StringBuilder builder = new StringBuilder();

        int index = 0;
        for (String string : codes) {
            builder.append(string);
            if (index != codes.length-1) {
                builder.append("-");
            }

            index++;
        }

        return builder.toString();
    }

    public static String getBranch() {
        return (String) CApplication.getSessionVariable(CApplication.STORE_ID);
    }
//    public static String getStore() {
//        return (String) CApplication.getSessionVariable(CApplication.STORE_ID);
//    }
//
//    public static String getRoute() {
//        return (String) CApplication.getSessionVariable(CApplication.STORE_ID);
//    }
}
