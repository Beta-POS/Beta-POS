package com.mac.transaction.item_transaction_new.object;

/**
 * @author Channa Mohan
 *
 * Created On Mar 20, 2015 9:41:46 AM Mohan Hibernate Mapping Generator
 */
/**
 * TTransactorTransaction generated by hbm2java
 */
public class TTransactorTransaction implements java.io.Serializable {

    private Integer indexNo;
    private MTransactor MTransactor;
    private TTransactionSummary TTransactionSummary;
    private String transactionType;
    private String description;
    private Double debitAmount;
    private Double creditAmount;
    private String status;

    public TTransactorTransaction() {
    }

    public TTransactorTransaction(MTransactor MTransactor, TTransactionSummary TTransactionSummary, String transactionType, Double debitAmount, Double creditAmount, String status) {
        this.MTransactor = MTransactor;
        this.TTransactionSummary = TTransactionSummary;
        this.transactionType = transactionType;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.status = status;
    }

    public Integer getIndexNo() {
        return this.indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public MTransactor getMTransactor() {
        return this.MTransactor;
    }

    public void setMTransactor(MTransactor MTransactor) {
        this.MTransactor = MTransactor;
    }

    public TTransactionSummary getTTransactionSummary() {
        return this.TTransactionSummary;
    }

    public void setTTransactionSummary(TTransactionSummary TTransactionSummary) {
        this.TTransactionSummary = TTransactionSummary;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDebitAmount() {
        return this.debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof TTransactorTransaction)) {
            return false;
        }

        TTransactorTransaction castOther = (TTransactorTransaction) other;

        if (this.indexNo == null && castOther.indexNo == null) {
            return false;
        }

        if (!java.util.Objects.equals(this.indexNo, castOther.indexNo)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 17 + java.util.Objects.hashCode(this.indexNo);

        return result;
    }
}