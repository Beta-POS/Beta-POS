/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.transaction.transaction_registration;

//import static com.mac.transaction.transaction_registration.TransactionTypes.ITEM_TRANSACTION;

/**
 *
 * @author thilanga
 */
public class FormTypes {
    public static final String ITEM_TRANSACTION = "ITEM TRANSACTION";
    public static final String ACCOUNT_TRANSACTION = "ACCOUNT_TRANSACTION";
    public static final String POS_TRANSACTION = "POS_TRANSACTION";
    
   
       public static final String[] values = {
            ITEM_TRANSACTION,
            ACCOUNT_TRANSACTION,
            POS_TRANSACTION
        };
}
