/*
 *  SystemTransactions.java
 * 
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 * 
 *  Created on Jul 26, 2014, 12:48:30 PM
 *  Copyrights channa mohan, All rights reserved.
 * 
 */
package com.mac.zsystem.transaction.transaction;

import com.mac.af.core.ApplicationException;
import com.mac.zsystem.transaction.transaction.object.Transaction;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.zsystem.transaction.transaction.object.TransactionHistory;
import com.mac.zsystem.transaction.transaction.object.TransactionType;
import java.util.HashMap;

/**
 *
 * @author mohan
 */
public class SystemTransactions {

    //TRANSACTION CODES
    public static final String LOAN_APPLICATION_TRANSACTION_CODE = "LOAN_APPLICATION";
    public static final String LOAN_APPROVAL_TRANSACTION_CODE = "LOAN_APPROVAL";
    public static final String LOAN_TRANSACTION_CODE = "LOAN";
    public static final String VOUCHER_TRANSACTION_CODE = "VOUCHER";
    public static final String OTHER_CHARGE_TRANSACTION_CODE = "OTHER_CHARGE";
    public static final String REBIT_TRANSACTION_CODE = "REBIT";
    public static final String RECEIPT_TRANSACTION_CODE = "RECEIPT";
    public static final String TEMPORARY_RECEIPT_TRANSACTION_CODE = "TEMP_RECEIPT";
    public static final String DAY_END_TRANSACTION_CODE = "DAY_END";
    public static final String CHEQUE_DEPOSIT_TRANSACTION_CODE = "CHEQUE_DEPOSIT";
    public static final String CHEQUE_REALIZE_TRANSACTION_CODE = "CHEQUE_REALIZE";
    public static final String CHEQUE_RETURN_TRANSACTION_CODE = "CHEQUE_RETURN";
    public static final String CHEQUE_ISSUE_TRANSACTION_CODE = "CHEQUE_ISSUE";
    public static final String JOURNEL_TRANSACTION_CODE = "JOURNEL";
    public static final String OPENING_BALANCE_TRANSACTION_CODE = "OPENING_BALANCE";
    public static final String BANK_DEPOSIT_TRANSACTION_CODE = "BANK_DEPOSIT";
    public static final String GENERAL_RECEIPT_TRANSACTION_CODE = "GENERAL_RECEIPT";
    public static final String GENERAL_VOUCHER_TRANSACTION_CODE = "GENERAL_VOUCHER";
    public static final String SALES_ITEM_TRANSACTION_CODE = "SALES_ITEM";
    //FINAC LAND
    public static final String LAND_PURCHASE_TRANSACTION_CODE = "LAND_PURCHASE";
    public static final String LAND_PROJECT_TRANSACTION_CODE = "LAND_PROJECT";
    public static final String LAND_BLOCK_TRANSACTION_CODE = "LAND_BLOCK";
    public static final String LAND_EXPENDITURE_TRANSACTION_CODE = "LAND_EXPENDITURE";
    //TRANSACTIONS
    private static final TransactionType LOAN_APPLICATION_TRANSACTION = new TransactionType(LOAN_APPLICATION_TRANSACTION_CODE, "Loan Application", new HashSet<Transaction>(0));
    private static final TransactionType LOAN_APPROVAL_TRANSACTION = new TransactionType(LOAN_APPROVAL_TRANSACTION_CODE, "Loan Approval", new HashSet<Transaction>(0));
    private static final TransactionType LOAN_TRANSACTION = new TransactionType(LOAN_TRANSACTION_CODE, "Loan", new HashSet<Transaction>(0));
    private static final TransactionType VOUCHER_TRANSACTION = new TransactionType(VOUCHER_TRANSACTION_CODE, "Voucher", new HashSet<Transaction>(0));
    private static final TransactionType OTHER_CHARGE_TRANSACTION = new TransactionType(OTHER_CHARGE_TRANSACTION_CODE, "Other Charge", new HashSet<Transaction>(0));
    private static final TransactionType REBIT_TRANSACTION = new TransactionType(REBIT_TRANSACTION_CODE, "Rebit", new HashSet<Transaction>(0));
    private static final TransactionType RECEIPT_TRANSACTION = new TransactionType(RECEIPT_TRANSACTION_CODE, "Receipt", new HashSet<Transaction>(0));
    private static final TransactionType TEMPERARY_RECEIPT_TRANSACTION = new TransactionType(TEMPORARY_RECEIPT_TRANSACTION_CODE, "Temperary Receipt", new HashSet<Transaction>(0));
    private static final TransactionType DAY_END_TRANSACTION = new TransactionType(DAY_END_TRANSACTION_CODE, "Day End", new HashSet<Transaction>(0));
    private static final TransactionType CHEQUE_DEPOSIT_TRANSACTION = new TransactionType(CHEQUE_DEPOSIT_TRANSACTION_CODE, "Cheque Deposit", new HashSet<Transaction>(0));
    private static final TransactionType CHEQUE_REALIZE_TRANSACTION = new TransactionType(CHEQUE_REALIZE_TRANSACTION_CODE, "Cheque Realize", new HashSet<Transaction>(0));
    private static final TransactionType CHEQUE_RETURN_TRANSACTION = new TransactionType(CHEQUE_RETURN_TRANSACTION_CODE, "Cheque Return", new HashSet<Transaction>(0));
    private static final TransactionType CHEQUE_ISSUE_TRANSACTION = new TransactionType(CHEQUE_ISSUE_TRANSACTION_CODE, "Cheque Issue", new HashSet<Transaction>(0));
    private static final TransactionType JOURNEL_TRANSACTION = new TransactionType(JOURNEL_TRANSACTION_CODE, "Journal Transaction", new HashSet<Transaction>(0));
    private static final TransactionType OPENING_BALANCE_TRANSACTION = new TransactionType(OPENING_BALANCE_TRANSACTION_CODE, "Opening Balance", new HashSet<Transaction>(0));
    private static final TransactionType BANK_DEPOSIT_TRANSACTION = new TransactionType(BANK_DEPOSIT_TRANSACTION_CODE, "Bank Deposit", new HashSet<Transaction>(0));
    private static final TransactionType GENERAL_RECEIPT_TRANSACTION = new TransactionType(GENERAL_RECEIPT_TRANSACTION_CODE, "General Receipt", new HashSet<Transaction>(0));
    private static final TransactionType GENERAL_VOUCHER_TRANSACTION = new TransactionType(GENERAL_VOUCHER_TRANSACTION_CODE, "General Voucher", new HashSet<Transaction>(0));
    private static final TransactionType SALES_ITEM_TRANSACTION = new TransactionType(SALES_ITEM_TRANSACTION_CODE, "General Item", new HashSet<Transaction>(0));
    //FINAC LAND
    private static final TransactionType LAND_PURCHASE_TRANSACTION = new TransactionType(LAND_PURCHASE_TRANSACTION_CODE, "Land Purchase", new HashSet<Transaction>(0));
    private static final TransactionType LAND_PROJECT_TRANSACTION = new TransactionType(LAND_PROJECT_TRANSACTION_CODE, "Land Project", new HashSet<Transaction>(0));
    private static final TransactionType LAND_BLOCK_TRANSACTION = new TransactionType(LAND_BLOCK_TRANSACTION_CODE, "Land Block", new HashSet<Transaction>(0));
    private static final TransactionType LAND_EXPENDITURE_TRANSACTION = new TransactionType(LAND_EXPENDITURE_TRANSACTION_CODE, "Land Expenditure", new HashSet<Transaction>(0));
    //ACTIONS
    public static final String ACTION_NEW = "NEW";
    public static final String ACTION_EDIT = "EDIT";
    public static final String ACTION_ACCEPT = "ACCEPT";
    public static final String ACTION_SUSPEND = "SUSPEND";
    public static final String ACTION_REJECT = "REJECT";
    public static final String ACTION_DELETE = "DELETE";
    public static final String ACTION_START = "START";

    public static List<TransactionType> getSystemTransactionTypes() {
        return Arrays.asList(
                LOAN_APPLICATION_TRANSACTION,
                LOAN_APPROVAL_TRANSACTION,
                LOAN_TRANSACTION,
                VOUCHER_TRANSACTION,
                OTHER_CHARGE_TRANSACTION,
                REBIT_TRANSACTION,
                RECEIPT_TRANSACTION,
                TEMPERARY_RECEIPT_TRANSACTION,
                DAY_END_TRANSACTION,
                CHEQUE_DEPOSIT_TRANSACTION,
                CHEQUE_REALIZE_TRANSACTION,
                CHEQUE_RETURN_TRANSACTION,
                CHEQUE_ISSUE_TRANSACTION,
                JOURNEL_TRANSACTION,
                OPENING_BALANCE_TRANSACTION,
                BANK_DEPOSIT_TRANSACTION,
                GENERAL_RECEIPT_TRANSACTION,
                GENERAL_VOUCHER_TRANSACTION,
                SALES_ITEM_TRANSACTION,
                //FINAC LAND
                LAND_PURCHASE_TRANSACTION,
                LAND_PROJECT_TRANSACTION,
                LAND_BLOCK_TRANSACTION,
                LAND_EXPENDITURE_TRANSACTION);
    }

    public static int insertTransaction(
            HibernateDatabaseService databaseService,
            String transactionTypeCode,
            String referenceNo,
            String documentNo,
            Integer loan,
            Integer cashierSession,
            String clientCode,
            String note) throws DatabaseException {

        //TRANSACTION
        Transaction transaction = new Transaction();

        transaction.setTransactionType(getTransaction(transactionTypeCode));
        transaction.setTransactionDate((Date) CApplication.getSessionVariable(CApplication.WORKING_DATE));
        transaction.setReferenceNo(referenceNo);
        transaction.setDocumentNo(documentNo);
        transaction.setLoan(loan);
        transaction.setCashierSession(cashierSession);
        transaction.setBranch((String) CApplication.getSessionVariable(CApplication.STORE_ID));
        transaction.setClient(clientCode);
        transaction.setNote(note);
        transaction.setStatus(SystemTransactionStatus.ACTIVE);

        transaction = (Transaction) databaseService.save(transaction);

        //TRANSACTION HISTORY
        insertTransactionHistory(databaseService, transaction, ACTION_NEW, note);

        return transaction.getIndexNo();
    }

    public static void insertTransactionHistory(
            HibernateDatabaseService databaseService,
            Integer transactionIndex,
            String action,
            String note) throws DatabaseException {

        Transaction transaction = (Transaction) databaseService.getObject(Transaction.class, transactionIndex);

        insertTransactionHistory(databaseService, transaction, action, note);
    }

    public static Integer getTransactionIndexNo(
            HibernateDatabaseService databaseService,
            String transactionType,
            String referenceNo) throws DatabaseException, ApplicationException {
        String hql = "FROM com.mac.zsystem.transaction.transaction.object.Transaction WHERE transactionType=:transactionType AND referenceNo=:referenceNo";
        HashMap<String, Object> params = new HashMap<>();
        params.put("transactionType", getTransaction(transactionType));
        params.put("referenceNo", referenceNo);
        List<Transaction> transactions = databaseService.getCollection(hql, params);
        if (transactions.isEmpty()) {
            throw new ApplicationException("No such transaction found");
        }

        if (transactions.size() > 1) {
            throw new ApplicationException("Multiple transactions found");
        }

        return transactions.get(0).getIndexNo();
    }

    private static void insertTransactionHistory(
            HibernateDatabaseService databaseService,
            Transaction transaction,
            String action,
            String note) throws DatabaseException {


        TransactionHistory history = new TransactionHistory();
        history.setTransaction(transaction);
        history.setAction(action);
        history.setEmployee((String) CApplication.getSessionVariable(CApplication.USER_ID));
        history.setDateTime(null);
        history.setNote(note);

        databaseService.save(history);
    }

    private static TransactionType getTransaction(String code) {
        for (TransactionType transaction : getSystemTransactionTypes()) {
            if (transaction.getCode().equals(code)) {
                return transaction;
            }
        }

        return null;
    }
//    public static void update(HibernateDatabaseService databaseService, Integer indexNo, String CHEQUE_DEPOSIT_TRANSACTION_CODE, String ACTION_NEW) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public static void update(HibernateDatabaseService databaseService, Integer indexNo, String LOAN_APPROVAL_TRANSACTION_CODE, String ACTION_SUSPEND, String referenceNo, String documentNo, Integer indexNo0, String code) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
