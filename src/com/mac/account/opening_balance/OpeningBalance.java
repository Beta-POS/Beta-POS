/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.opening_balance;

import com.mac.account.ztemplate.account_entry.AccountEntry;
import com.mac.zsystem.transaction.account.account_setting.AccountTransactionType;
import com.mac.zsystem.transaction.transaction.SystemTransactions;

/**
 *
 * @author SMTK
 */
public class OpeningBalance extends AccountEntry {

    @Override
    protected String referenceGeneratorPrefix() {
        return REFERENCE_GENARATOR_TYPE;
    }

    @Override
    public String getTransactionName() {
        return TRANSACTION_TYPE;
    }

    @Override
    public String getTransactionTypeCode() {
        return TRANSACTION_TYPE_CODE;
    }
    private static final String REFERENCE_GENARATOR_TYPE =  com.mac.zsystem.util.ReferenceGenerator.OPENING_BALANCE;
    private static final String TRANSACTION_TYPE = "Opening Balance";
   
    private static final String TRANSACTION_TYPE_CODE = SystemTransactions.OPENING_BALANCE_TRANSACTION_CODE;

    @Override
    protected String getAccountTransactionTypeCode() {
        return AccountTransactionType.OPENING;
    }

    @Override
    protected boolean isAccountCreditDebitEqualNeeded() {
        return false;
    }
}
