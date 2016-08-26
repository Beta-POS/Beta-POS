/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.bank_deposit;

import com.mac.account.ztemplate.entry_payment.EntryPayment;
import com.mac.zsystem.transaction.payment.ChequeType;
import com.mac.zsystem.transaction.transaction.SystemTransactions;
import com.mac.zsystem.util.ReferenceGenerator;

/**
 *
 * @author mohan
 */
public class BankDeposit extends EntryPayment {

    @Override
    protected String getChequeType() {
        return ChequeType.CLIENT;
    }

    @Override
    protected String referenceGeneratorPrefix() {
        return ReferenceGenerator.BANK_DEPOSIT;
    }

    @Override
    public String getTransactionName() {
        return "Bank Deposit";
    }

    @Override
    public String getTransactionTypeCode() {
        return SystemTransactions.BANK_DEPOSIT_TRANSACTION_CODE;
    }
}
