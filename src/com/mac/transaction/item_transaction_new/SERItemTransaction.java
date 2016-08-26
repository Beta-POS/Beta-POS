/*
 *  SERItemTransaction.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 12, 2015, 5:26:39 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.connection_pool.CConnectionProvider;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.database.hibernate.HibernateSQLQuery;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.environment.service.AbstractService;
import com.mac.transaction.account_transaction.TransactorTransactionStatus;
import com.mac.transaction.item_transaction_new.object.MEmployee;
import com.mac.transaction.item_transaction_new.object.MItem;
import com.mac.transaction.item_transaction_new.object.MStore;
import com.mac.transaction.item_transaction_new.object.MTransactor;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.item_transaction_new.object.TItemMovement;
import com.mac.transaction.item_transaction_new.object.TSerialMovement;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.object.TTransactionSettlement;
import com.mac.transaction.item_transaction_new.object.TTransactionSummary;
import com.mac.transaction.item_transaction_new.object.TTransactorTransaction;
import com.mac.transaction.transaction_registration.PriceTypes;
import com.mac.zsystem.payment.ChequeStatus;
import com.mac.zsystem.payment.hibernate.TCheque;
import com.mac.zsystem.payment.object.Payment;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.IndexNumberUtil;
import com.mac.zsystem.util.item.ItemBatchUtil;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mohan
 */
public class SERItemTransaction extends AbstractService {

    public SERItemTransaction(Component component) {
        super(component);
    }

    //GETTERS
    public List<MEmployee> getEmployees() {
        List employees;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("BRANCH", HashUtil.getBranch());

            String hql = "FROM com.mac.transaction.item_transaction_new.object.MEmployee WHERE branch =:BRANCH and active=true";
            HibernateDatabaseService databaseService = getDatabaseService();
            employees = databaseService.getCollection(hql, params);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            employees = new ArrayList();
        }
        return employees;
    }

    public List<MStore> getStores() {
        List stores;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("BRANCH", HashUtil.getBranch());

            String hql = "FROM com.mac.transaction.item_transaction_new.object.MStore WHERE branch =:BRANCH and active=true";
            HibernateDatabaseService databaseService = getDatabaseService();
            stores = databaseService.getCollection(hql, params);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            stores = new ArrayList();
        }
        return stores;
    }

    public List<MTransactor> getSuppliers() {
        List suppliers;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("BRANCH", HashUtil.getBranch());

            String hql = "FROM com.mac.transaction.item_transaction_new.object.MTransactor WHERE branch =:BRANCH and supplier = true and active=true";
            HibernateDatabaseService databaseService = getDatabaseService();
            suppliers = databaseService.getCollection(hql, params);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            suppliers = new ArrayList();
        }
        return suppliers;
    }

    public List<MTransactor> getClients() {
        List clients;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("BRANCH", HashUtil.getBranch());

            String hql = "FROM com.mac.transaction.item_transaction_new.object.MTransactor WHERE branch =:BRANCH and client = true and active=true";
            HibernateDatabaseService databaseService = getDatabaseService();
            clients = databaseService.getCollection(hql, params);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            clients = new ArrayList();
        }
        return clients;
    }

    public List<MItem> getItems() {
        List item;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("BRANCH", HashUtil.getBranch());

            String hql = "FROM com.mac.transaction.item_transaction_new.object.MItem WHERE branch =:BRANCH and active=true";
            HibernateDatabaseService databaseService = getDatabaseService();
            item = databaseService.getCollection(hql, params);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            item = new ArrayList();
        }
        return item;
    }

    public RTransactionType getCutomerPayment() {
        RTransactionType transactionType = null;

        String code = "750";

        try {
            transactionType = (RTransactionType) getDatabaseService().getObject(RTransactionType.class, code);
        } catch (DatabaseException ex) {
            Logger.getLogger(SERItemTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return transactionType;
    }

    public int saveItemTransaction(TTransactionSummary transactionSummary, RTransactionType transactionType, boolean isNew, List<TCheque> cheques) throws DatabaseException {
        try {
            if (transactionSummary == null) {
                throw new ApplicationException(null);
            }

            transactionSummary.getTItemMovements().clear();
            for (TTransactionDetail transactionDetail : transactionSummary.getTTransactionDetails()) {
                //item movement
                if (transactionType.isItemIn()) {
                    TItemMovement itemMovement = new TItemMovement();
                    itemMovement.setBranch(HashUtil.getBranch());
                    itemMovement.setTTransactionSummary(transactionSummary);
                    itemMovement.setMItem(transactionDetail.getMItem());
                    itemMovement.setAvaragePrice(transactionDetail.getMItem().getAveragePrice());
                    itemMovement.setMStore(transactionSummary.getMStoreByInStore());
                    itemMovement.setInQuantity(transactionDetail.getQuantity());
                    itemMovement.setOutQuantity(0);
                    itemMovement.setStatus(ItemMovementStatus.ACTIVE);

                    transactionSummary.getTItemMovements().add(itemMovement);
                }
                if (transactionType.isItemOut()) {
                    TItemMovement itemMovement = new TItemMovement();
                    itemMovement.setBranch(HashUtil.getBranch());
                    itemMovement.setTTransactionSummary(transactionSummary);
                    itemMovement.setMItem(transactionDetail.getMItem());
                    itemMovement.setAvaragePrice(transactionDetail.getMItem().getAveragePrice());
                    itemMovement.setMStore(transactionSummary.getMStoreByOutStore());
                    itemMovement.setInQuantity(0);
                    itemMovement.setOutQuantity(transactionDetail.getQuantity());
                    itemMovement.setStatus(ItemMovementStatus.ACTIVE);

                    transactionSummary.getTItemMovements().add(itemMovement);
                }

                //save serials
                for (TSerialMovement serialMovement : transactionDetail.getTSerialMovements()) {
                    serialMovement.setMStoreByInStore(transactionSummary.getMStoreByInStore());
                    serialMovement.setMStoreByOutStore(transactionSummary.getMStoreByOutStore());
                }

                //create new batch
                if (transactionType.getPriceType().equals(PriceTypes.COST_PRICE)
                        && !transactionDetail.getPrice().equals(transactionDetail.getMItem().getCostPrice())) {
                    ItemBatchUtil.updatePrice(getDatabaseService(), transactionDetail.getMItem().getHash(), transactionDetail.getPrice());
                }
            }

            //settlement
//            TTransactionSummary settlementTTransactionSummary = null;
//            if (transactionSummary.getRTransactionType().getRTransactionType() != null) {
//                settlementTTransactionSummary = createSettleTransactionSummary(transactionSummary);
//
//
//                double balanceAmount = 0.0;
////                if (isNew) {
////                    balanceAmount = transactionSummary.getNetTotal() - settlementTTransactionSummary.getNetTotal();
////                } else {
////                    balanceAmount = transactionSummary.getBalanceAmount() - settlementTTransactionSummary.getNetTotal();
////                }
//                balanceAmount = transactionSummary.getNetTotal() - transactionSummary.getPaidAmount() - settlementTTransactionSummary.getNetTotal();
//                transactionSummary.setBalanceAmount(balanceAmount);
//            } else {
//                double balanceAmount = 0.0;
//                double payment = transactionSummary.getPayment().getCashAmount() + transactionSummary.getPayment().getChequeAmount();
////                if (isNew) {
////                    balanceAmount = transactionSummary.getNetTotal() - payment;
////                } else {
////                    balanceAmount = transactionSummary.getBalanceAmount() - payment;
////                }
//                balanceAmount = transactionSummary.getNetTotal() - transactionSummary.getPaidAmount() - payment;
//
//                transactionSummary.setCashAmount(transactionSummary.getPayment().getCashAmount());
//                transactionSummary.setChequeAmount(transactionSummary.getPayment().getChequeAmount());
//                transactionSummary.setCreditAmount(transactionSummary.getPayment().getCreditAmount());
//                transactionSummary.setBalanceAmount(balanceAmount);
//            }
//            getDatabaseService().beginLocalTransaction();
//            transactionSummary = (TTransactionSummary) getDatabaseService().save(transactionSummary);
//            getDatabaseService().commitLocalTransaction();
//            getDatabaseService().getObject(transactionSummary.getClass(), transactionSummary.getIndexNo());
//            if (settlementTTransactionSummary != null) {
//                if (settlementTTransactionSummary.getNetTotal() != 0.0) {
//                    settlementTTransactionSummary = (TTransactionSummary) getDatabaseService().save(settlementTTransactionSummary);
//                    TTransactionSettlement transactionSettlement = new TTransactionSettlement();
//                    transactionSettlement.setTransactionSummary(settlementTTransactionSummary.getIndexNo());
//                    transactionSettlement.setSettleTransactionSummary(transactionSummary.getIndexNo());
//                    transactionSettlement.setBranch(settlementTTransactionSummary.getBranch());
//                    transactionSettlement.setAmount(settlementTTransactionSummary.getNetTotal());
//                    transactionSettlement.setStatus(settlementTTransactionSummary.getStatus());
//                    getDatabaseService().save(transactionSettlement);
//
//                    if (cheques != null) {
//                        for (TCheque cheque : cheques) {
//                            cheque.setBranch(settlementTTransactionSummary.getBranch());
//                            cheque.setClient(settlementTTransactionSummary.getMTransactor().getHash());
//                            cheque.setTransactionSummary(settlementTTransactionSummary.getIndexNo());
//                            cheque.setStatus(ChequeStatus.ACTIVE);
//                        }
//                        getDatabaseService().save(cheques);
//                    }
//                }
//            } else {
//            }
            double balanceAmount = 0.0;
            double payment = transactionSummary.getPayment().getCashAmount() + transactionSummary.getPayment().getChequeAmount();
            balanceAmount = transactionSummary.getNetTotal() - transactionSummary.getPaidAmount() - payment;

            transactionSummary.setCashAmount(transactionSummary.getPayment().getCashAmount());
            transactionSummary.setChequeAmount(transactionSummary.getPayment().getChequeAmount());
            transactionSummary.setCreditAmount(transactionSummary.getPayment().getCreditAmount());
            transactionSummary.setBalanceAmount(balanceAmount);

            //TRANSACTOR TRANSACTION
            {//invoice debit amount

                TTransactorTransaction transactorTransaction;

                transactorTransaction = new TTransactorTransaction();
                transactorTransaction.setTTransactionSummary(transactionSummary);
                transactorTransaction.setTransactionType(transactionType.getCode());
                transactorTransaction.setDescription(transactionType.getName());
                transactorTransaction.setMTransactor(transactionSummary.getMTransactor());
                transactorTransaction.setCreditAmount(transactionType.isCreditAmount() ? transactionSummary.getCreditAmount() : 0.0);
                transactorTransaction.setDebitAmount(transactionType.isDebitAmount() ? transactionSummary.getCreditAmount() : 0.0);
                transactorTransaction.setStatus(TransactorTransactionStatus.ACTIVE);

                transactionSummary.getTTransactorTransactions().clear();
                transactionSummary.getTTransactorTransactions().add(transactorTransaction);
            }

            getDatabaseService().beginLocalTransaction();
            transactionSummary = (TTransactionSummary) getDatabaseService().save(transactionSummary);
            getDatabaseService().commitLocalTransaction();
            getDatabaseService().getObject(transactionSummary.getClass(), transactionSummary.getIndexNo());

            //chques
            if (cheques != null) {
                for (TCheque cheque : cheques) {
                    cheque.setBranch(transactionSummary.getBranch());
                    cheque.setClient(transactionSummary.getMTransactor().getHash());
                    cheque.setTransactionSummary(transactionSummary.getIndexNo());
                    cheque.setStatus(ChequeStatus.ACTIVE);
                }
                getDatabaseService().save(cheques);
            }

//            System.out.println(settlementTTransactionSummary);
//            System.out.println(settlementTTransactionSummary.getNetTotal());
//
//            getDatabaseService().callUpdateProcedure("CALL z_transaction_update_check(" + transactionSummary.getIndexNo() + ", " + transactionSummary.getTransactionNo() + ", '" + transactionSummary.getRTransactionType().getCode() + "', '" + transactionSummary.getBranch() + "')");
//
//            if ((!isNew) && (settlementTTransactionSummary != null ? settlementTTransactionSummary.getNetTotal() != 0.0 : false)) {
//                return settlementTTransactionSummary.getIndexNo();
//            } else {
//                return transactionSummary.getIndexNo();
//            }
            return transactionSummary.getIndexNo();
        } catch (ApplicationException ex) {
            Logger.getLogger(SERItemTransaction.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /*
    private TTransactionSummary createSettleTransactionSummary(TTransactionSummary transactionSummary) throws ApplicationException {
        TTransactionSummary settleTTransactionSummary = new TTransactionSummary();

        settleTTransactionSummary.setMEmployee(transactionSummary.getMEmployee());
        settleTTransactionSummary.setMTransactor(transactionSummary.getMTransactor());
        settleTTransactionSummary.setMStoreByInStore(transactionSummary.getMStoreByInStore());
        settleTTransactionSummary.setMStoreByOutStore(transactionSummary.getMStoreByOutStore());
        settleTTransactionSummary.setRTransactionType(transactionSummary.getRTransactionType().getRTransactionType());
        settleTTransactionSummary.setTransactionNo(0);
        settleTTransactionSummary.setBranch(transactionSummary.getBranch());
        settleTTransactionSummary.setTransactionDate((Date) CApplication.getSessionVariable(CApplication.WORKING_DATE));
        settleTTransactionSummary.setReferenceNo(transactionSummary.getReferenceNo());
        settleTTransactionSummary.setTotalValue(transactionSummary.getPayment().getCashAmount() + transactionSummary.getPayment().getChequeAmount());
        settleTTransactionSummary.setItemDiscount(0.0);
        settleTTransactionSummary.setSpecialDiscount(0.0);
        settleTTransactionSummary.setNetTotal(transactionSummary.getPayment().getCashAmount() + transactionSummary.getPayment().getChequeAmount());
        settleTTransactionSummary.setBalanceAmount(0.0);
        settleTTransactionSummary.setCashAmount(transactionSummary.getPayment().getCashAmount());
        settleTTransactionSummary.setChequeAmount(transactionSummary.getPayment().getChequeAmount());
        settleTTransactionSummary.setCreditAmount(0.0);
        settleTTransactionSummary.setSession(transactionSummary.getSession());
        settleTTransactionSummary.setDocumentNo(transactionSummary.getDocumentNo());
        settleTTransactionSummary.setDescription(transactionSummary.getDescription());
        settleTTransactionSummary.setRoute(transactionSummary.getRoute());
        settleTTransactionSummary.setStatus(transactionSummary.getStatus());

        //transactor transaction
        if (settleTTransactionSummary.getRTransactionType() != null) {
            TTransactorTransaction transactorTransaction = new TTransactorTransaction();
            transactorTransaction.setTTransactionSummary(settleTTransactionSummary);
            transactorTransaction.setTransactionType(settleTTransactionSummary.getRTransactionType().getCode());
            transactorTransaction.setMTransactor(settleTTransactionSummary.getMTransactor());
            transactorTransaction.setDescription(settleTTransactionSummary.getRTransactionType().getName());
            transactorTransaction.setCreditAmount(settleTTransactionSummary.getRTransactionType().isCreditAmount() ? settleTTransactionSummary.getNetTotal() : 0.0);
            transactorTransaction.setDebitAmount(settleTTransactionSummary.getRTransactionType().isDebitAmount() ? settleTTransactionSummary.getNetTotal() : 0.0);
            transactorTransaction.setStatus(TransactorTransactionStatus.ACTIVE);
            settleTTransactionSummary.getTTransactorTransactions().clear();
            settleTTransactionSummary.getTTransactorTransactions().add(transactorTransaction);

            int number = IndexNumberUtil.getNextIndex("t_transaction_summary", "transaction_type='" + settleTTransactionSummary.getRTransactionType().getCode() + "' AND branch='" + HashUtil.getBranch() + "'");
            settleTTransactionSummary.setTransactionNo(number);
        }
        //number

        return settleTTransactionSummary;
    }
     */
    public TTransactionSummary loadItemTransaction(int number, RTransactionType transactionType) throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(TTransactionSummary.class);
        criteria.add(Restrictions.eq("transactionNo", number))
                .add(Restrictions.eq("RTransactionType", transactionType))
                .add(Restrictions.eq("branch", HashUtil.getBranch()));

        List<TTransactionSummary> transactionSummaries = criteria.list();
        TTransactionSummary transactionSummary = !transactionSummaries.isEmpty() ? transactionSummaries.get(0) : null;

        if (transactionSummary != null) {
            Payment payment = new Payment();

            payment.setCashAmount(transactionSummary.getCashAmount());
            payment.setChequeAmount(transactionSummary.getChequeAmount());
            payment.setNetValue(transactionSummary.getNetTotal());
            payment.setCreditAmount(transactionSummary.getCreditAmount());
            transactionSummary.setPayment(payment);

            Criteria chequeCriteria = getDatabaseService().initCriteria(TCheque.class);
            chequeCriteria.add(Restrictions.eq("transactionSummary", transactionSummary.getIndexNo()));
            transactionSummary.setCheques(chequeCriteria.list());

            transactionSummary.setPaidAmount(transactionSummary.getNetTotal() - transactionSummary.getBalanceAmount());
        }

        return transactionSummary;
    }

    public List<TTransactionSummary> getSettlements(TTransactionSummary transactionSummary) {
        String sql = "SELECT \n"
                + "	* \n"
                + "FROM \n"
                + "	t_transaction_summary \n"
                + "	LEFT JOIN t_transaction_settlement ON t_transaction_settlement.transaction_summary = t_transaction_summary.index_no\n"
                + "WHERE\n"
                + "	t_transaction_settlement.settle_transaction_summary = " + transactionSummary.getIndexNo() + ""
                + "     AND t_transaction_settlement.status = 'ACTIVE';";

        HibernateSQLQuery query = new HibernateSQLQuery(sql, TTransactionSummary.class);
        try {
            return getDatabaseService().getCollection(query, new HashMap<String, Object>());
        } catch (DatabaseException ex) {
            Logger.getLogger(SERItemTransaction.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void cancel(TTransactionSummary summary) {
        try {
            summary.setStatus(TransactionStatus.CANCEL);
            TTransactionSettlement settlements = (TTransactionSettlement) getDatabaseService().initCriteria(TTransactionSettlement.class).add(Restrictions.eq("transactionSummary", summary.getIndexNo())).uniqueResult();
            settlements.setStatus(TransactionStatus.CANCEL);

            getDatabaseService().save(summary);
            getDatabaseService().save(settlements);
        } catch (DatabaseException ex) {
            Logger.getLogger(SERItemTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNewNumber(String where) throws ApplicationException {
        int nextIndex = 0;
        String sql = "SELECT MAX(transaction_no) AS row_count FROM `t_transaction_summary` WHERE " + where;
        System.out.println(sql);
        try {
            Connection connection = CConnectionProvider.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nextIndex = resultSet.getInt("row_count") + 1;
            }
            resultSet.close();
            preparedStatement.close();

            CConnectionProvider.getInstance().closeConnection(connection);

            return nextIndex;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ApplicationException("next index cannot be retrived\n\tSQL:" + sql);
        }
    }
}
