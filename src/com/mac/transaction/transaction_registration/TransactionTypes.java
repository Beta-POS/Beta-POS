/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.transaction.transaction_registration;

/**
 *
 * @author user
 */
public class TransactionTypes {

    public static final String SALES = "SALES";
    public static final String PURCHASE = "PURCHASE";
    public static final String TRANSFER = "TRANSFER";
    public static final String SALES_RETURN = "SALES_RETURN";
    public static final String NONE = "NONE";
    
    public static final String[] ITEM_TRANSACTION_TYPES = {
        SALES,
        PURCHASE,
        TRANSFER,
        SALES_RETURN,
        NONE,};
}
