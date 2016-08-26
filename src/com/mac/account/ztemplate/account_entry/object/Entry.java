/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.ztemplate.account_entry.object;

//import com.mac.zsystem.transaction.account.object.MAccount;

import com.mac.registration.account.object.MAccount;


/**
 *
 * @author SMTK
 */
public class Entry {

    private MAccount account;
    private String description;
    private Double creditAmount;
    private Double debitAmount;
    private AccountEntryTransaction transaction;
    private String creditOrDebit;

    public Entry() {
    }

    public MAccount getAccount() {
        return account;
    }

    public void setAccount(MAccount account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public AccountEntryTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(AccountEntryTransaction transaction) {
        this.transaction = transaction;
    }

    public String getCreditOrDebit() {
        return creditOrDebit;
    }

    public void setCreditOrDebit(String creditOrDebit) {
        this.creditOrDebit = creditOrDebit;
    }
}
