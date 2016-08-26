/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.ztemplate.account_entry.object;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thilanga-pc
 */
public class AccountEntryTransaction {

    private String referenceNo;
    private String documentNo;
    private Date transactionDate;
    private String note;
    private Set<Entry> journels = new HashSet<>(0);
    private Double totalCreditAmount;
    private Double totalDebitAmount;

    public Double getTotalCreditAmount() {
        return totalCreditAmount;
    }

    public void setTotalCreditAmount(Double totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }

    public Double getTotalDebitAmount() {
        return totalDebitAmount;
    }

    public void setTotalDebitAmount(Double totalDebitAmount) {
        this.totalDebitAmount = totalDebitAmount;
    }

    public AccountEntryTransaction() {
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<Entry> getJournels() {
        return journels;
    }

    public void setJournels(Set<Entry> journels) {
        this.journels = journels;
    }
    
}
