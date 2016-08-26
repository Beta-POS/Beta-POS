package com.mac.transaction.account_transaction.object;

/**
 * @author Channa Mohan
 *
 * Created On May 15, 2015 12:58:15 PM Mohan Hibernate Mapping Generator
 */
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TTransactionSummary generated by hbm2java
 */
public class TTransactionSummary implements java.io.Serializable {

    private Integer indexNo;
    private MEmployee MEmployee;
    private MTransactor MTransactor;
    private RTransactionType RTransactionType;
    private int transactionNo;
    private String branch;
    private Date transactionDate;
    private Integer referenceNo;
    private String inStore;
    private String outStore;
    private double totalValue;
    private double itemDiscount;
    private double specialDiscount;
    private double netTotal;
    private double balanceAmount;
    private double cashAmount;
    private double chequeAmount;
    private double creditAmount;
    private Integer session;
    private String documentNo;
    private String description;
    private String route;
    private String status;
    private Date actionTime;
    private Set<TTransactionSettlement> TTransactionSettlementsForTransactionSummary = new HashSet<TTransactionSettlement>(0);
    private Set<TTransactorTransaction> TTransactorTransactions = new HashSet<TTransactorTransaction>(0);
    private Set<TTransactionSettlement> TTransactionSettlementsForSettleTransactionSummary = new HashSet<TTransactionSettlement>(0);

    public TTransactionSummary() {
    }

    public TTransactionSummary(RTransactionType RTransactionType, int transactionNo, String branch, Date transactionDate, double totalValue, double itemDiscount, double specialDiscount, double netTotal, double balanceAmount, double cashAmount, double creditAmount, String status) {
        this.RTransactionType = RTransactionType;
        this.transactionNo = transactionNo;
        this.branch = branch;
        this.transactionDate = transactionDate;
        this.totalValue = totalValue;
        this.itemDiscount = itemDiscount;
        this.specialDiscount = specialDiscount;
        this.netTotal = netTotal;
        this.balanceAmount = balanceAmount;
        this.cashAmount = cashAmount;
        this.creditAmount = creditAmount;
        this.status = status;
    }

    public TTransactionSummary(MEmployee MEmployee, MTransactor MTransactor, RTransactionType RTransactionType, int transactionNo, String branch, Date transactionDate, Integer referenceNo, String inStore, String outStore, double totalValue, double itemDiscount, double specialDiscount, double netTotal, double balanceAmount, double cashAmount, double creditAmount, Integer session, String documentNo, String description, String route, String status, Date actionTime, Set<TTransactionSettlement> TTransactionSettlementsForTransactionSummary, Set<TTransactorTransaction> TTransactorTransactions, Set<TTransactionSettlement> TTransactionSettlementsForSettleTransactionSummary) {
        this.MEmployee = MEmployee;
        this.MTransactor = MTransactor;
        this.RTransactionType = RTransactionType;
        this.transactionNo = transactionNo;
        this.branch = branch;
        this.transactionDate = transactionDate;
        this.referenceNo = referenceNo;
        this.inStore = inStore;
        this.outStore = outStore;
        this.totalValue = totalValue;
        this.itemDiscount = itemDiscount;
        this.specialDiscount = specialDiscount;
        this.netTotal = netTotal;
        this.balanceAmount = balanceAmount;
        this.cashAmount = cashAmount;
        this.creditAmount = creditAmount;
        this.session = session;
        this.documentNo = documentNo;
        this.description = description;
        this.route = route;
        this.status = status;
        this.actionTime = actionTime;
        this.TTransactionSettlementsForTransactionSummary = TTransactionSettlementsForTransactionSummary;
        this.TTransactorTransactions = TTransactorTransactions;
        this.TTransactionSettlementsForSettleTransactionSummary = TTransactionSettlementsForSettleTransactionSummary;
    }

    public Integer getIndexNo() {
        return this.indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public MEmployee getMEmployee() {
        return this.MEmployee;
    }

    public void setMEmployee(MEmployee MEmployee) {
        this.MEmployee = MEmployee;
    }

    public MTransactor getMTransactor() {
        return this.MTransactor;
    }

    public void setMTransactor(MTransactor MTransactor) {
        this.MTransactor = MTransactor;
    }

    public RTransactionType getRTransactionType() {
        return this.RTransactionType;
    }

    public void setRTransactionType(RTransactionType RTransactionType) {
        this.RTransactionType = RTransactionType;
    }

    public int getTransactionNo() {
        return this.transactionNo;
    }

    public void setTransactionNo(int transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getReferenceNo() {
        return this.referenceNo;
    }

    public void setReferenceNo(Integer referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getInStore() {
        return this.inStore;
    }

    public void setInStore(String inStore) {
        this.inStore = inStore;
    }

    public String getOutStore() {
        return this.outStore;
    }

    public void setOutStore(String outStore) {
        this.outStore = outStore;
    }

    public double getTotalValue() {
        return this.totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getItemDiscount() {
        return this.itemDiscount;
    }

    public void setItemDiscount(double itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public double getSpecialDiscount() {
        return this.specialDiscount;
    }

    public void setSpecialDiscount(double specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public double getNetTotal() {
        return this.netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public double getBalanceAmount() {
        return this.balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getCashAmount() {
        return this.cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public double getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Integer getSession() {
        return this.session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public String getDocumentNo() {
        return this.documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getActionTime() {
        return this.actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Set<TTransactionSettlement> getTTransactionSettlementsForTransactionSummary() {
        return this.TTransactionSettlementsForTransactionSummary;
    }

    public void setTTransactionSettlementsForTransactionSummary(Set<TTransactionSettlement> TTransactionSettlementsForTransactionSummary) {
        this.TTransactionSettlementsForTransactionSummary = TTransactionSettlementsForTransactionSummary;
    }

    public Set<TTransactorTransaction> getTTransactorTransactions() {
        return this.TTransactorTransactions;
    }

    public void setTTransactorTransactions(Set<TTransactorTransaction> TTransactorTransactions) {
        this.TTransactorTransactions = TTransactorTransactions;
    }

    public Set<TTransactionSettlement> getTTransactionSettlementsForSettleTransactionSummary() {
        return this.TTransactionSettlementsForSettleTransactionSummary;
    }

    public void setTTransactionSettlementsForSettleTransactionSummary(Set<TTransactionSettlement> TTransactionSettlementsForSettleTransactionSummary) {
        this.TTransactionSettlementsForSettleTransactionSummary = TTransactionSettlementsForSettleTransactionSummary;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof TTransactionSummary)) {
            return false;
        }

        TTransactionSummary castOther = (TTransactionSummary) other;

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
