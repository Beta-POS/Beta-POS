/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.general_voucher;

//import com.mac.account.general_receipt.*;
import com.mac.account.ztemplate.entry_payment.EntryPayment;
import com.mac.zsystem.transaction.payment.ChequeType;
import com.mac.zsystem.transaction.transaction.SystemTransactions;
import com.mac.zsystem.util.ReferenceGenerator;

/**
 *
 * @author mohan
 */
public class GeneralVoucher extends EntryPayment {

    @Override
    protected String getChequeType() {
        return ChequeType.COMPANY;
    }

    @Override
    protected String referenceGeneratorPrefix() {
        return ReferenceGenerator.GENERAL_VOUCHER;
    }

    @Override
    public String getTransactionName() {
        return "General Receipt";
    }

    @Override
    public String getTransactionTypeCode() {
        return SystemTransactions.GENERAL_VOUCHER_TRANSACTION_CODE;
    }
}
