/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.ztemplate.account_entry;

import com.mac.account.ztemplate.account_entry.object.Entry;
import com.mac.account.ztemplate.account_entry.object.AccountEntryTransaction;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.service.AbstractService;
import com.mac.zsystem.transaction.account.SystemAccountTransaction;
import com.mac.zsystem.transaction.account.account_setting.AccountSettingCreditOrDebit;
//import com.mac.zsystem.transaction.account.account_setting.AccountSettingCreditOrDebit;
//import com.mac.zsystem.transaction.payment.SystemPayment;
import com.mac.zsystem.transaction.transaction.SystemTransactions;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SMTK
 */
public abstract class SERAccountEntry extends AbstractService {

    public SERAccountEntry(Component component) {
        super(component);
    }

    protected abstract String getTransactionTypeCode();

    protected abstract String getAccountTransactionTypeCode();

    public List getAccouns() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.zsystem.transaction.account.object.MAccount");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERAccountEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void save(AccountEntryTransaction transaction, Collection<Entry> journals) throws DatabaseException {
        //TRANSACTION
        int transactionIndex = saveTransaction(transaction);

        //ACCOUNT TRANSACTION
//        saveAccountEntries(transactionIndex, journals);
    }

    public int saveTransaction(AccountEntryTransaction entryTransaction) throws DatabaseException {
        int transactionIndex = SystemTransactions.insertTransaction(
                getDatabaseService(),
                getTransactionTypeCode(),
                entryTransaction.getReferenceNo(),
                entryTransaction.getDocumentNo(),
                null,
                null,
                null,
                entryTransaction.getNote());

        return transactionIndex;
    }

//    public void saveAccountEntries(int transactionIndex, Collection<Entry> entries) throws DatabaseException {
//        SystemAccountTransaction accountTransaction = SystemAccountTransaction.getInstance();
//        for (Entry entry : entries) {
//            accountTransaction.saveAccountTransaction(
//                    getDatabaseService(),
//                    transactionIndex,
//                    getTransactionTypeCode(),
//                    getAccountTransactionTypeCode(),
//                    entry.getCreditOrDebit(),
//                    entry.getCreditOrDebit().equals(AccountSettingCreditOrDebit.CREDIT) ? entry.getCreditAmount() : entry.getDebitAmount(),
//                    entry.getAccount().getCode(),
//                    entry.getDescription());
//        }
//    }

    public boolean showPaymentDialog(String transactionTypeCode, AccountEntryTransaction receipt, String chequeType, String accountEntryType) throws DatabaseException {
//        systemPayment = SystemPayment.getInstance(transactionTypeCode);
//        
//        boolean isPaymentOk = systemPayment.showPaymentDialog(
//                getDatabaseService(),
//                receipt.getReferenceNo(),
//                receipt.getDocumentNo(),
//                receipt.getTransactionDate(),
//                
//                (accountEntryType.equals(AccountEntry.ACCOUNT_ENTRY_CREDIT_ONLY)
//                ? receipt.getTotalCreditAmount()
//                : accountEntryType.equals(AccountEntry.ACCOUNT_ENTRY_DEBIT_ONLY)
//                ? receipt.getTotalDebitAmount()
//                : 0.0)
//                ,
//                chequeType);
//
//        return isPaymentOk;
        return true;
    }

    public void savePayment(int transactionIndex, AccountEntryTransaction receipt) throws DatabaseException {
//        systemPayment.savePayment(
//                getDatabaseService(),
//                null,
//                transactionIndex,
//                null);
//    }
//    private SystemPayment systemPayment;
    }
}
