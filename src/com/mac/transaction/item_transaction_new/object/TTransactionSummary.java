package com.mac.transaction.item_transaction_new.object;

/**
 * @author Channa Mohan
 *
 * Created On Mar 20, 2015 9:41:46 AM Mohan Hibernate Mapping Generator
 */
import com.mac.zsystem.payment.hibernate.TCheque;
import com.mac.zsystem.payment.object.Payment;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TTransactionSummary generated by hbm2java
 */
public class TTransactionSummary implements java.io.Serializable {

    private Integer indexNo;
    private MEmployee MEmployee;
    private MTransactor MTransactor;
    private MStore MStoreByOutStore;
    private MStore MStoreByInStore;
    private RTransactionType RTransactionType;
    private int transactionNo;
    private String branch;
    private Date transactionDate;
    private Integer referenceNo;
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
    private Set<TItemMovement> TItemMovements = new HashSet<TItemMovement>(0);
    private Set<TTransactionDetail> TTransactionDetails = new HashSet<TTransactionDetail>(0);
    private Set<TTransactorTransaction> TTransactorTransactions = new HashSet<TTransactorTransaction>(0);
    //custom
    private Payment payment = new Payment();
    private List<TCheque> cheques = new ArrayList<>();
    private double paidAmount;

    public TTransactionSummary() {
    }

    public TTransactionSummary(RTransactionType RTransactionType, int transactionNo, String branch, Date transactionDate, double totalValue, double itemDiscount, double specialDiscount, double netTotal, double cashAmount, double creditAmount, String status) {
        this.RTransactionType = RTransactionType;
        this.transactionNo = transactionNo;
        this.branch = branch;
        this.transactionDate = transactionDate;
        this.totalValue = totalValue;
        this.itemDiscount = itemDiscount;
        this.specialDiscount = specialDiscount;
        this.netTotal = netTotal;
        this.cashAmount = cashAmount;
        this.creditAmount = creditAmount;
        this.status = status;
    }

    public TTransactionSummary(MEmployee MEmployee, MTransactor MTransactor, MStore MStoreByOutStore, MStore MStoreByInStore, RTransactionType RTransactionType, int transactionNo, String branch, Date transactionDate, Integer referenceNo, double totalValue, double itemDiscount, double specialDiscount, double netTotal, double cashAmount, double creditAmount, Integer session, String documentNo, String description, String route, String status, Set<TItemMovement> TItemMovements, Set<TTransactionDetail> TTransactionDetails, Set<TTransactorTransaction> TTransactorTransactions) {
        this.MEmployee = MEmployee;
        this.MTransactor = MTransactor;
        this.MStoreByOutStore = MStoreByOutStore;
        this.MStoreByInStore = MStoreByInStore;
        this.RTransactionType = RTransactionType;
        this.transactionNo = transactionNo;
        this.branch = branch;
        this.transactionDate = transactionDate;
        this.referenceNo = referenceNo;
        this.totalValue = totalValue;
        this.itemDiscount = itemDiscount;
        this.specialDiscount = specialDiscount;
        this.netTotal = netTotal;
        this.cashAmount = cashAmount;
        this.creditAmount = creditAmount;
        this.session = session;
        this.documentNo = documentNo;
        this.description = description;
        this.route = route;
        this.status = status;
        this.TItemMovements = TItemMovements;
        this.TTransactionDetails = TTransactionDetails;
        this.TTransactorTransactions = TTransactorTransactions;
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

    public MStore getMStoreByOutStore() {
        return this.MStoreByOutStore;
    }

    public void setMStoreByOutStore(MStore MStoreByOutStore) {
        this.MStoreByOutStore = MStoreByOutStore;
    }

    public MStore getMStoreByInStore() {
        return this.MStoreByInStore;
    }

    public void setMStoreByInStore(MStore MStoreByInStore) {
        this.MStoreByInStore = MStoreByInStore;
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

    public Set<TItemMovement> getTItemMovements() {
        return this.TItemMovements;
    }

    public void setTItemMovements(Set<TItemMovement> TItemMovements) {
        this.TItemMovements = TItemMovements;
    }

    public Set<TTransactionDetail> getTTransactionDetails() {
        return this.TTransactionDetails;
    }

    public void setTTransactionDetails(Set<TTransactionDetail> TTransactionDetails) {
        this.TTransactionDetails = TTransactionDetails;
    }

    public Set<TTransactorTransaction> getTTransactorTransactions() {
        return this.TTransactorTransactions;
    }

    public void setTTransactorTransactions(Set<TTransactorTransaction> TTransactorTransactions) {
        this.TTransactorTransactions = TTransactorTransactions;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<TCheque> getCheques() {
        return cheques;
    }

    public void setCheques(List<TCheque> cheques) {
        this.cheques = cheques;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
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