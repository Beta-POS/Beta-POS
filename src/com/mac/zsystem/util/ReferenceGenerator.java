/*
 *  ReferenceGenerator.java
 * 
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 * 
 *  Created on Aug 19, 2014, 9:12:36 AM
 *  Copyrights channa mohan, All rights reserved.
 * 
 */
package com.mac.zsystem.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import com.mac.af.core.environment.CApplication;
import com.mac.af.component.util.generator.CGenerator;
import sphere.Sphere;

/**
 *
 * @author mohan
 */
public class ReferenceGenerator implements CGenerator<String, String> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMddHHmmss");
    private String key;

    public ReferenceGenerator(String key) {
        this.key = key;
    }

    private Date getDate() {
        return new Date();
    }

    private String getPrefix() {
        return (String) CApplication.getSessionVariable(Sphere.USER_PREFIX_KEY);
    }

    @Override
    public String getValue() {
        return key + getPrefix() + DATE_FORMAT.format(getDate());
    }

    @Override
    public String getKey() {
        return key;
    }

    public static ReferenceGenerator getInstance(String key) {
        return new ReferenceGenerator(key);
    }
    //keys
    public static final String LOAN = "LON";
    public static final String LOAN_APPLICATION = "LAP";
    public static final String VOUCHER = "VOU";
    public static final String RECEIPT = "REC";
    public static final String TEMPERORY_RECEIPT = "TRC";
    public static final String OTHER_CHARGE = "OTC";
    public static final String REBIT = "REB";
    public static final String JOURNAL = "JOU";
    public static final String OPENING_BALANCE = "OPB";
    public static final String CHECQUE_DEPOSIT = "CHD";
    public static final String CHECQUE_REALIZE = "CHR";
    public static final String CHECQUE_RETURN = "CQR";
    public static final String CHECQUE_ISSUE = "CHI";
    public static final String DAY_END = "DAY";
    public static final String BANK_DEPOSIT = "BAD";
    public static final String GENERAL_RECEIPT = "GNR";
    public static final String GENERAL_VOUCHER = "GNV";
    public static final String SALES_INVOICE = "SAI";
    //FINAC LAND
    public static final String LAND_PURCHASE = "LPU";
    public static final String LAND_PROJECT = "LPO";
    public static final String LAND_BLOCK_CREATION = "LBC";
}
