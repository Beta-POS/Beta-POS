/*
 *  SystemAccountTransaction.java
 * 
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 * 
 *  Created on Oct 14, 2014, 1:56:08 PM
 *  Copyrights channa mohan, All rights reserved.
 * 
 */
package com.mac.zsystem.transaction.account;

import com.mac.af.core.ApplicationRuntimeException;
import com.mac.af.core.concurrent.CExecutable;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.message.mOptionPane;
import com.mac.zsystem.transaction.account.account_setting.AccountInterface;
import com.mac.zsystem.transaction.account.account_setting.AccountSettingCreditOrDebit;
//import com.mac.zsystem.transaction.account.account_setting.AccountTransactionStatus;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.ChequeDepositAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.ChequeIssueAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.ChequeRealizeAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.ChequeReturnAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.DayEndAccountInterface;
//import com.mac.zsystem.transaction.account.object.AccountSetting;
//import com.mac.zsystem.transaction.account.object.AccountTransaction;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.LoanAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.LoanApplicationAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.LoanApprovalAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.LoanChargeAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.ReceiptAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.TemperaryReceiptAccountInterface;
//import com.mac.zsystem.transaction.account.account_setting.system_interface.VoucherAccountInterface;
import com.mac.zsystem.transaction.account.object.MAccount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mohan
 */
public class SystemAccountTransaction {
/*
    private static final AccountInterface LOAN_ACCOUNT_INTERFACE = new LoanAccountInterface();
    private static final AccountInterface LOAN_APPLICATION_ACCOUNT_INTERFACE = new LoanApplicationAccountInterface();
    private static final AccountInterface LOAN_APPROVAL_ACCOUNT_INTERFACE = new LoanApprovalAccountInterface();
    private static final AccountInterface VOUCHER_ACCOUNT_INTERFACE = new VoucherAccountInterface();
    private static final AccountInterface RECEIPT_ACCOUNT_INTERFACE = new ReceiptAccountInterface();
    private static final AccountInterface TEMP_RECEIPT_ACCOUNT_INTERFACE = new TemperaryReceiptAccountInterface();
    private static final AccountInterface CHEQUE_DEPOSIT_ACCOUNT_INTERFACE = new ChequeDepositAccountInterface();
    private static final AccountInterface CHEQUE_REALIZE_ACCOUNT_INTERFACE = new ChequeRealizeAccountInterface();
    private static final AccountInterface CHEQUE_RETURN_ACCOUNT_INTERFACE = new ChequeReturnAccountInterface();
    private static final AccountInterface CHEQUE_ISSUE_ACCOUNT_INTERFACE = new ChequeIssueAccountInterface();
    private static final AccountInterface DAY_END_ACCOUNT_INTERFACE = new DayEndAccountInterface();
    private static final AccountInterface LOAN_CHARGE_ACCOUNT_INTERFACE = new LoanChargeAccountInterface();

    public static List<AccountInterface> getAccountInterfaces() {
        return Arrays.asList(
                (AccountInterface) LOAN_ACCOUNT_INTERFACE,
                (AccountInterface) LOAN_APPLICATION_ACCOUNT_INTERFACE,
                (AccountInterface) LOAN_APPROVAL_ACCOUNT_INTERFACE,
                (AccountInterface) VOUCHER_ACCOUNT_INTERFACE,
                (AccountInterface) RECEIPT_ACCOUNT_INTERFACE,
                (AccountInterface) TEMP_RECEIPT_ACCOUNT_INTERFACE,
                (AccountInterface) CHEQUE_DEPOSIT_ACCOUNT_INTERFACE,
                (AccountInterface) CHEQUE_REALIZE_ACCOUNT_INTERFACE,
                (AccountInterface) CHEQUE_RETURN_ACCOUNT_INTERFACE,
                (AccountInterface) CHEQUE_ISSUE_ACCOUNT_INTERFACE,
                (AccountInterface) DAY_END_ACCOUNT_INTERFACE,
                (AccountInterface) LOAN_CHARGE_ACCOUNT_INTERFACE
                );
    }

    public static SystemAccountTransaction getInstance() {
        return new SystemAccountTransaction();
    }

    public void beginAccountTransactionQueue() {
        if (accountTransactionQueues != null) {
            throw new ApplicationRuntimeException("Account Transaction Queue is already begun");
        }

        accountTransactionQueues = new ArrayList<>();
    }

    public void addAccountTransactionQueue(
            String accountSettingCode,
            String description,
            Double amount,
            String type) {
        checkAccountTransactionQueue();

        AccountTransactionQueue queue = new AccountTransactionQueue();
        queue.setAccountSettingCode(accountSettingCode);
        queue.setDescription(description);
        queue.setAmount(amount);
        queue.setType(type);

        accountTransactionQueues.add(queue);
    }

    public void flushAccountTransactionQueue(
            final HibernateDatabaseService parentDatabaseService,
            final int transaction,
            final String transactionType) throws DatabaseException {

        checkAccountTransactionQueue();

        CExecutable<Void> executable = new CExecutable<Void>(parentDatabaseService.getCPanel().getTabTitle() + " Update accounts", parentDatabaseService.getCPanel()) {
            @Override
            public Void execute() throws Exception {

                HibernateDatabaseService databaseService = parentDatabaseService.createChildDatabaseService();

                HashMap<AccountSetting, AccountTransactionQueue> accountQueues = new HashMap<>();

                List< AccountSetting> accountSettings = getAccountSettings(databaseService, transactionType);

                //CHECK SETTINGS
                boolean isActive = false;
                for (AccountSetting accountSetting : accountSettings) {
                    isActive = isActive || (accountSetting.isActive() ? accountSetting.getAccount() == null : false);

                    if (isActive) {
                        mOptionPane.showMessageDialog(
                                databaseService.getCPanel(),
                                "Error occured in Account Transaction. Please check Account Settings related to the transaction " + accountSetting.getTransactionType().getName() + ".",
                                "Account Transaction",
                                mOptionPane.WARNING_MESSAGE);
                    }
                }

                //MATCH ACCOUNT SETTINGS AND QUEUE
                for (AccountSetting accountSetting : accountSettings) {
                    for (AccountTransactionQueue accountTransactionQueue : accountTransactionQueues) {
                        if (accountSetting.getCode().equals(accountTransactionQueue.getAccountSettingCode())) {
                            accountQueues.put(accountSetting, accountTransactionQueue);
                        }
                    }
                }

                databaseService.beginLocalTransaction();

                AccountTransactionQueue transactionQueue;
                for (AccountSetting accountSetting : accountSettings) {
                    if (accountSetting.isActive()) {

                        transactionQueue = accountQueues.get(accountSetting);

                        AccountTransaction accountTransaction =
                                createAccountTransaction(
                                transaction,
                                transactionType,
                                transactionQueue.getType(),
                                accountSetting.getCreditOrDebit(),
                                transactionQueue.getAmount(),
                                accountSetting.getAccount(),
                                transactionQueue.getDescription(),
                                transactionQueue.getAccountSettingCode());

                        databaseService.save(accountTransaction);
                    }
                }

                databaseService.commitLocalTransaction();

                return null;

            }
        };

        CApplication.getExecutionManager().execute(executable);
    }

    private AccountTransaction createAccountTransaction(
            int transaction,
            String transactionType,
            String type,
            String creditOrDebit,
            double amount,
            Account account,
            String description,
            String accountSetting) {
        AccountTransaction accountTransaction = new AccountTransaction();

        accountTransaction.setTransactionDate((Date) CApplication.getSessionVariable(CApplication.WORKING_DATE));
        accountTransaction.setBranch((String) CApplication.getSessionVariable(CApplication.STORE_ID));
        accountTransaction.setAccountSetting(accountSetting);
        accountTransaction.setDescription(description);
        accountTransaction.setAccount(account);

        if (creditOrDebit.equals(AccountSettingCreditOrDebit.CREDIT)) {
            accountTransaction.setCreditAmount(amount);
            accountTransaction.setDebitAmount(0.0);
        }
        if (creditOrDebit.equals(AccountSettingCreditOrDebit.DEBIT)) {
            accountTransaction.setCreditAmount(0.0);
            accountTransaction.setDebitAmount(amount);
        }
        accountTransaction.setTransaction(transaction);
        accountTransaction.setTransactionType(transactionType);
        accountTransaction.setType(type);
        accountTransaction.setStatus(AccountTransactionStatus.ACTIVE);

        return accountTransaction;
    }

    private void checkAccountTransactionQueue() {
        if (accountTransactionQueues == null) {
            throw new ApplicationRuntimeException("Account Transaction Queue is not begun");
        }
    }

    public void saveAccountTransaction(
            HibernateDatabaseService databaseService,
            int transaction,
            String transactionType,
            String type,
            String creditOrDebit,
            double amount,
            String accountCode,
            String description) throws DatabaseException {


        Account account = (Account) databaseService.getObject(Account.class, accountCode);

        AccountTransaction accountTransaction;
        accountTransaction = createAccountTransaction(
                transaction,
                transactionType,
                type,
                creditOrDebit,
                amount,
                account,
                description,
                null);

        databaseService.save(accountTransaction);
    }

    private List<AccountSetting> getAccountSettings(HibernateDatabaseService databaseService, String transactionType) throws DatabaseException {
        String hql = "FROM com.mac.zsystem.transaction.account.object.AccountSetting WHERE transactionType.code=:TRANSACTION_TYPE";
        HashMap<String, Object> params = new HashMap<>();
        params.put("TRANSACTION_TYPE", transactionType);

        List<AccountSetting> accountSettings = databaseService.getCollection(hql, params);

        if (accountSettings.isEmpty()) {
            throw new ApplicationRuntimeException("Account Interface not found for transaction type " + transactionType);
        }

        return accountSettings;
    }
    private List<AccountTransactionQueue> accountTransactionQueues;

*/ }
