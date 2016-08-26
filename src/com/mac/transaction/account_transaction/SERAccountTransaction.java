/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.transaction.account_transaction;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.environment.service.AbstractService;
import com.mac.transaction.account_transaction.object.MEmployee;
import com.mac.transaction.account_transaction.object.MTransactor;
import com.mac.transaction.account_transaction.object.RTransactionType;
import com.mac.transaction.account_transaction.object.TTransactionSettlement;
import com.mac.transaction.account_transaction.object.TTransactionSummary;
import com.mac.transaction.account_transaction.object.TTransactorTransaction;
import com.mac.transaction.item_transaction_new.TransactionStatus;
import com.mac.zsystem.payment.ChequeStatus;
import com.mac.zsystem.payment.hibernate.TCheque;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.IndexNumberUtil;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author pc-n
 */
public class SERAccountTransaction extends AbstractService {

    public SERAccountTransaction(Component component) {
        super(component);
    }

    public List getEmployees() {
        try {
            Criteria criteria = getDatabaseService().initCriteria(MEmployee.class);
            criteria.add(Restrictions.eq("branch", HashUtil.getBranch()));
            return criteria.list();
        } catch (DatabaseException databaseException) {
            return new ArrayList();
        }
    }

    public TTransactionSummary loadAccountTransaction(int number, RTransactionType transactionType) throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(TTransactionSummary.class);
        criteria.add(Restrictions.eq("transactionNo", number))
                .add(Restrictions.eq("RTransactionType", transactionType))
                .add(Restrictions.eq("branch", HashUtil.getBranch()));

        List<TTransactionSummary> transactionSummaries = criteria.list();

        return !transactionSummaries.isEmpty() ? transactionSummaries.get(0) : null;
    }

    public List<TTransactionSettlement> getSettlementTransactions(RTransactionType transactionType, MTransactor transactor) throws DatabaseException {
        List<TTransactionSummary> transactionSummarys = getDatabaseService().initCriteria(TTransactionSummary.class)
                .add(Restrictions.eq("RTransactionType", transactionType))
                .add(Restrictions.eq("MTransactor", transactor))
                .add(Restrictions.eq("status", TransactionStatus.ACTIVE))
                .add(Restrictions.gt("balanceAmount", 0.0))
                .list();

        List<TTransactionSettlement> settlements = new ArrayList<>();

        for (TTransactionSummary tTransactionSummary : transactionSummarys) {
            TTransactionSettlement transactionSettlement = new TTransactionSettlement();

            transactionSettlement.setTTransactionSummaryBySettleTransactionSummary(tTransactionSummary);
            transactionSettlement.setAmount(0.0);

            settlements.add(transactionSettlement);
        }

        return settlements;
    }

    public int save(TTransactionSummary transactionSummary, RTransactionType transactionType, boolean isNew, List<TCheque> cheques) throws DatabaseException {
        try {
            if (isNew) {
                int number = IndexNumberUtil.getNextIndex("t_transaction_summary", "transaction_type='" + transactionType.getCode() + "' AND branch='" + HashUtil.getBranch() + "'");
                transactionSummary.setTransactionNo(number);
            }
        } catch (ApplicationException ex) {
            Logger.getLogger(SERAccountTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        //TRANSACTION SETTLEMENT
//        TTransactionSettlement transactionSettlement = new TTransactionSettlement();
//        transactionSettlement.setBranch(transactionSummary.getBranch());
//        transactionSettlement.setTTransactionSummaryByTransactionSummary(transactionSummary);
//        transactionSettlement.setTTransactionSummaryBySettleTransactionSummary(null);
//        transactionSettlement.setAmount(transactionSummary.getNetTotal());
//        transactionSettlement.setStatus(TransactorTransactionStatus.ACTIVE);
//
//        transactionSummary.getTTransactionSettlementsForTransactionSummary().clear();
//        transactionSummary.getTTransactionSettlementsForTransactionSummary().add(transactionSettlement);

        //TRANSACTOR TRANSACTION
        TTransactorTransaction transactorTransaction = new TTransactorTransaction();
        transactorTransaction.setTTransactionSummary(transactionSummary);
        transactorTransaction.setTransactionType(transactionType.getCode());
        transactorTransaction.setMTransactor(transactionSummary.getMTransactor());
        transactorTransaction.setCreditAmount(transactionType.isCreditAmount() ? transactionSummary.getNetTotal() : 0.0);
        transactorTransaction.setDebitAmount(transactionType.isDebitAmount() ? transactionSummary.getNetTotal() : 0.0);
        transactorTransaction.setStatus(TransactorTransactionStatus.ACTIVE);

        transactionSummary.getTTransactorTransactions().clear();
        transactionSummary.getTTransactorTransactions().add(transactorTransaction);

        getDatabaseService().beginLocalTransaction();
        transactionSummary = (TTransactionSummary) getDatabaseService().save(transactionSummary);
        getDatabaseService().commitLocalTransaction();
        getDatabaseService().getObject(TTransactionSummary.class, getDatabaseService().getIdentifier(transactionSummary));

        //save cheques
        if (cheques != null) {
            for (TCheque cheque : cheques) {
                cheque.setBranch(transactionSummary.getBranch());
                cheque.setClient(transactionSummary.getMTransactor().getHash());
                cheque.setTransactionSummary(transactionSummary.getIndexNo());
                cheque.setStatus(ChequeStatus.ACTIVE);
            }
            getDatabaseService().save(cheques);
        }


        return transactionSummary.getIndexNo();
    }

    public List getSuppliers() {
        List suppliers;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("BRANCH", HashUtil.getBranch());

            String hql = "FROM com.mac.transaction.account_transaction.object.MTransactor WHERE branch =:BRANCH and supplier = true and active=true";
            HibernateDatabaseService databaseService = getDatabaseService();
            suppliers = databaseService.getCollection(hql, params);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            suppliers = new ArrayList();
        }
        return suppliers;
    }

    public List getClients() {
        List suppliers;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("BRANCH", HashUtil.getBranch());

            String hql = "FROM com.mac.transaction.account_transaction.object.MTransactor WHERE branch =:BRANCH and client = true and active=true";
            HibernateDatabaseService databaseService = getDatabaseService();
            suppliers = databaseService.getCollection(hql, params);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            suppliers = new ArrayList();
        }
        return suppliers;
    }
}
