/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.ztemplate.entry_payment;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.service.AbstractService;
//import com.mac.loan.ztemplate.receipt.custom_object.Receipt;
//import com.mac.zsystem.transaction.payment.SystemPayment;
import com.mac.zsystem.transaction.transaction.SystemTransactions;
import java.awt.Component;

/**
 *
 * @author mohan
 */
public abstract class SEREntryPayment extends AbstractService {

    public SEREntryPayment(Component component) {
        super(component);
    }

//    protected abstract String getChequeType();

//    public void save(String transactionTypeCode, Receipt receipt) throws DatabaseException {
//        boolean isPaymentOk = showPaymentDialog(transactionTypeCode, receipt);
//
//        if (isPaymentOk) {
//            //SAVE TRANSACTION
//            int transactionIndex = saveTransaction(transactionTypeCode, receipt);
//
//
//        }
//    }

//    public int saveTransaction(String transactionTypeCode, Receipt receipt) throws DatabaseException {
//        //SAVE TRANSACTION
//        int transactionIndex = SystemTransactions.insertTransaction(
//                getDatabaseService(),
//                transactionTypeCode,
//                receipt.getReferenceNo(),
//                receipt.getDocumentNo(),
//                receipt.getLoan().getIndexNo(),
//                null,
//                receipt.getLoan().getClient().getCode(),
//                receipt.getNote());
//
//        return transactionIndex;
//    }

//    public boolean showPaymentDialog(String transactionTypeCode, Receipt receipt) throws DatabaseException {
//        systemPayment = SystemPayment.getInstance(transactionTypeCode);
//
//        boolean isPaymentOk = systemPayment.showPaymentDialog(
//                getDatabaseService(),
//                receipt.getReferenceNo(),
//                receipt.getDocumentNo(),
//                receipt.getTransactionDate(),
//                receipt.getPaymentAmount(),
//                getChequeType());
//
//        return isPaymentOk;
//    }
//    private SystemPayment systemPayment;
//}
}