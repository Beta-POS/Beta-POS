/*
 *  TransactionDetailUtil.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 26, 2015, 9:14:39 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new;

import com.mac.transaction.item_transaction_new.object.TTransactionDetail;

/**
 *
 * @author mohan
 */
public class TransactionDetailUtil {

    public static void calculateNetValues(TTransactionDetail transactionDetail) {
        double price = getUnWrappedDouble(transactionDetail.getPrice());
        double quantity = getUnWrappedDouble(transactionDetail.getQuantity() == null ? 0.0 : transactionDetail.getQuantity());
        double discount = getUnWrappedDouble(transactionDetail.getDiscount());

        double netValue = (price * quantity) - discount;
        transactionDetail.setNetValue(netValue);
    }

    private static double getUnWrappedDouble(Double d) {
        return d == null ? 0.0 : (double) d;
    }
}
