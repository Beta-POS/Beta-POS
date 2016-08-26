/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.ztemplate.entry_payment;

import com.mac.account.ztemplate.account_entry.AccountEntry;
import com.mac.account.ztemplate.account_entry.object.AccountEntryTransaction;
import com.mac.account.ztemplate.account_entry.object.Entry;
import com.mac.af.core.database.DatabaseException;
//import com.mac.zsystem.transaction.account.account_setting.AccountTransactionType;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohan
 */
public abstract class EntryPayment extends AccountEntry {

    protected abstract String getChequeType();

    @Override
    public String getAccountEntryType() {
        return ACCOUNT_ENTRY_DEBIT_ONLY;
    }

    @Override
    protected String getAccountTransactionTypeCode() {
        return  null; //AccountTransactionType.AUTO;
    }

    @Override
    protected boolean isAccountCreditDebitEqualNeeded() {
        return false;
    }

    @Override
    public int save(AccountEntryTransaction entryTransaction, Collection<Entry> entries) {
        try {
            boolean isPaymentOK = getService().showPaymentDialog(
                    getTransactionTypeCode(),
                    entryTransaction,
                    getChequeType(),
                    getAccountEntryType());

            if (isPaymentOK) {
                //SAVE TRANSACTION
                int transactionIndex = getService().saveTransaction(entryTransaction);

                //SAVE ACCOUNT ENTRIES
//                getService().saveAccountEntries(transactionIndex, entries);

                //SAVE PAYMENTS
                getService().savePayment(transactionIndex, entryTransaction);
                
                return SAVE_SUCCESS;
            }

            return SAVE_FAILED;
        } catch (DatabaseException ex) {
            Logger.getLogger(EntryPayment.class.getName()).log(Level.SEVERE, null, ex);
            return SAVE_FAILED;
        }
    }
}
