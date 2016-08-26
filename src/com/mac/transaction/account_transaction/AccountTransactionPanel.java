/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.transaction.account_transaction;

import com.mac.af.component.base.button.action.Action;
import com.mac.af.component.base.button.action.ActionUtil;
import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.cpanel.CPanel;
import com.mac.af.core.environment.tab.TabFunctions;
import com.mac.af.core.message.mOptionPane;
import com.mac.af.resources.ApplicationResources;
import com.mac.af.templates.report.ReportBuilder;
import com.mac.af.templates.report.ReportDialog;
import com.mac.reports.AbstractSphereReportViewer;
import com.mac.transaction.account_transaction.object.MEmployee;
import com.mac.transaction.account_transaction.object.MReport;
import com.mac.transaction.account_transaction.object.MTransactor;
import com.mac.transaction.account_transaction.object.RTransactionType;
import com.mac.transaction.account_transaction.object.TTransactionSettlement;
import com.mac.transaction.account_transaction.object.TTransactionSummary;
import com.mac.transaction.item_transaction_new.ItemTransactionPanel;
import com.mac.transaction.item_transaction_new.TransactionStatus;
import com.mac.zsystem.payment.PaymentDialog;
import com.mac.zsystem.payment.hibernate.TCheque;
import com.mac.zsystem.payment.object.Payment;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.IndexNumberUtil;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author pc-n
 */
public final class AccountTransactionPanel extends CPanel {

    /**
     * Creates new form AccountTransaction
     */
    public AccountTransactionPanel(String transactionType) {
        try {
            this.transactionType = (RTransactionType) getDatabaseService().getObject(RTransactionType.class, transactionType);
        } catch (DatabaseException ex) {
            Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();

        initUI();

        initOthers();
    }

    public AccountTransactionPanel(String[] params) {
        String transactionType = params[0];
        String client = params[1];


        try {
            this.transactionType = (RTransactionType) getDatabaseService().getObject(RTransactionType.class, transactionType);

            final MTransactor transactor = (MTransactor) getDatabaseService().getObject(MTransactor.class, client);

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    cboClient.setCValue(transactor);
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();

        } catch (DatabaseException ex) {
            Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();

        initUI();

        initOthers();
    }

    private void initUI() {
        pnlSettlement.setVisible(transactionType.isSettlementTransaction());
//        txtAmount.setValueEditable(!transactionType.isSettlementTransaction());
        txtBalanceAmount.setValueEditable(false);
    }

    private void refreshSettlmnetTransactions() {
        if (transactionType.isSettlementTransaction()) {
            MTransactor transactor = null;
            if (transactionType.isClient()) {
                transactor = (MTransactor) cboClient.getCValue();
            }
            if (transactionType.isSupplier()) {
                transactor = (MTransactor) cboSupplier.getCValue();
            }

            RTransactionType settlementTransactionType = transactionType.getRTransactionType();
            if (transactionType != null & transactor != null) {
                List<TTransactionSettlement> transactionSettlements;
                try {
                    transactionSettlements = serAccountTransaction.getSettlementTransactions(settlementTransactionType, transactor);
//                    tblSettlementTransaction.setCValue(transactionSettlements);
                    settlementTableModel.setSettlements(transactionSettlements);
                } catch (DatabaseException ex) {
                    Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                settlementTableModel.setSettlements(new ArrayList<TTransactionSettlement>());
            }
        }
    }

//    private void setSettlementAmount(double amount, boolean updateSettlements) {
//        if (transactionType.isSettlementTransaction()) {
//
//            List<TTransactionSettlement> settlements = settlementTableModel.getSettlements();
//
//            double settlementAmount;
//            double totalBalance = 0.0;
//            double total = amount;
//            for (TTransactionSettlement transactionSettlement : settlements) {
//                settlementAmount = Math.min(amount, transactionSettlement.getTTransactionSummaryBySettleTransactionSummary().getBalanceAmount());
//                transactionSettlement.setAmount(settlementAmount);
//                amount = amount - settlementAmount;
//
//                totalBalance = totalBalance + transactionSettlement.getTTransactionSummaryBySettleTransactionSummary().getBalanceAmount();
//            }
//            txtBalanceAmount.setCValue(totalBalance - total);
//            settlementTableModel.fireTableModels();
//        }
//    }
    private void validatePayment() {
        if (transactionType.isSettlementTransaction()) {
            txtAmount.setCValue(settlementTableModel.getSettlementAmount());
            txtBalanceAmount.setCValue(settlementTableModel.getBalanceAmount());
        } else {
            txtBalanceAmount.setCValue(0.0);
        }
    }

    @Action
    public void doSave() {
        int w = mOptionPane.showConfirmDialog(null, "Do you want to save?", "Save", mOptionPane.YES_NO_OPTION);

        if (w == mOptionPane.YES_OPTION) {
            try {
                initObject();
                List<TCheque> cheques = paymentDialog.getCheques();

                int transactionIndex = serAccountTransaction.save(transactionSummary, transactionType, isNew, cheques);

                if (transactionIndex != -1) {

                    Map<String, Object> params = new HashMap<>();
                    params.put("TRANSACTION_TYPE", transactionType.getCode());
                    params.put("INDEX_NO", transactionIndex);
                    params.put("BRANCH", AbstractSphereReportViewer.getBranch());

                    Map<String, Object> defaultParams = AbstractSphereReportViewer.getDefaultParameterMap();

                    //print reports
                    if (transactionType.isPrintFirstReport()) {
                        int q = mOptionPane.showConfirmDialog(this, "Do you want to print First Report?", "Print Report", mOptionPane.YES_NO_OPTION);
                        if (q == mOptionPane.YES_OPTION) {
                            MReport report = transactionType.getMReportByFirstReport();
                            if (report != null) {
                                ReportDialog reportDialog = new ReportDialog(new ByteArrayInputStream(report.getReport()),
                                        defaultParams,
                                        params);
                                reportDialog.setTitle(ReportBuilder.getFormattedString(report.getName()));
                                reportDialog.setVisible(true);
                            }
                        }
                    }
                    if (transactionType.isPrintSecondReport()) {
                        int q = mOptionPane.showConfirmDialog(this, "Do you want to print Second Report?", "Print Report", mOptionPane.YES_NO_OPTION);
                        if (q == mOptionPane.YES_OPTION) {
                            MReport report = transactionType.getMReportBySecondReport();
                            if (report != null) {
                                ReportDialog reportDialog = new ReportDialog(new ByteArrayInputStream(report.getReport()),
                                        defaultParams,
                                        params);
                                reportDialog.setTitle(ReportBuilder.getFormattedString(report.getName()));
                                reportDialog.setVisible(true);
                            }
                        }
                    }
                    if (transactionType.isPrintThirdReport()) {
                        int q = mOptionPane.showConfirmDialog(this, "Do you want to print Third Report?", "Print Report", mOptionPane.YES_NO_OPTION);
                        if (q == mOptionPane.YES_OPTION) {
                            MReport report = transactionType.getMReportByThirdReport();
                            if (report != null) {
                                ReportDialog reportDialog = new ReportDialog(new ByteArrayInputStream(report.getReport()),
                                        defaultParams,
                                        params);
                                reportDialog.setTitle(ReportBuilder.getFormattedString(report.getName()));
                                reportDialog.setVisible(true);
                            }
                        }
                    }
                }

                mOptionPane.showMessageDialog(null, "Successfully Saved !!!", "Save", mOptionPane.INFORMATION_MESSAGE);
                initNew();
            } catch (DatabaseException ex) {
                Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
                mOptionPane.showMessageDialog(null, "Save Failed:\n" + ex.getMessage(), "Save", mOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
//                Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Action
    public void doClear() {
        int q = mOptionPane.showConfirmDialog(null, "Do you sure want to discard and clear?", "Clear", mOptionPane.YES_NO_OPTION);

        if (q == mOptionPane.YES_OPTION) {
            initNew();
        }
    }

    @Action
    public void doCancel() {
        if (!isNew) {
            int q = mOptionPane.showConfirmDialog(null, "Do you sure want to cancel " + transactionType.getName() + " ?", "Cancel", mOptionPane.YES_NO_OPTION);

            if (q == mOptionPane.YES_OPTION) {
                try {
                    cancelTransaction();
                    mOptionPane.showMessageDialog(null, transactionType.getName() + " cacelled successfully !!!", "Cancel", mOptionPane.INFORMATION_MESSAGE);
                    initNew();
                } catch (DatabaseException ex) {
                    Logger.getLogger(ItemTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    mOptionPane.showMessageDialog(this, "Failed to cancel " + transactionType.getName() + ".\n" + ex.getMessage(), "Cancel", mOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            mOptionPane.showMessageDialog(null, transactionType.getName() + " is not saved.", "Cancel", mOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Action
    public void doClose() {
        int q = mOptionPane.showConfirmDialog(null, "Do you sure want to discard and close?", "Close", mOptionPane.YES_NO_OPTION);

        if (q == mOptionPane.YES_OPTION) {
            TabFunctions.closeTab(this);
        }
    }

    private void initNew() {
        //GENERATE NEW NUMBER
        int number;
        try {
            number = IndexNumberUtil.getNextIndex("t_transaction_summary", "transaction_type='" + transactionType.getCode() + "' AND branch='" + HashUtil.getBranch() + "'");

            //NEW TRANSACTION
            transactionSummary = new TTransactionSummary();
            transactionSummary.setTransactionNo(number);
            isNew = true;

            //INIT INTERFACE
            initInterface();
//            refreshSettlmnetTransactions();
        } catch (ApplicationException ex) {
            Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initInterface() {
        txtNo.setCValue(transactionSummary.getTransactionNo());
        txtDate.setCValue(transactionSummary.getTransactionDate());
        txtReferanceNo.setCValue(transactionSummary.getReferenceNo());
        cboClient.setCValue(transactionSummary.getMTransactor());
        txtDescription.setCValue(transactionSummary.getDescription());
        txtAmount.setCValue(transactionSummary.getNetTotal());
        txtDocumentNo.setCValue(transactionSummary.getDocumentNo());
        cboEmployee.setCValue(transactionSummary.getMEmployee());
    }

    private void cancelTransaction() throws DatabaseException {
        try {
            initObject();
        } catch (Exception ex) {
            Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        transactionSummary.setStatus(TransactionStatus.CANCEL);
        serAccountTransaction.save(transactionSummary, transactionType, isNew, null);
    }

    private void initObject() throws Exception {
        transactionSummary.setRTransactionType(transactionType);
        transactionSummary.setTransactionNo(txtNo.getCValue());
        transactionSummary.setBranch(HashUtil.getBranch());
        transactionSummary.setTransactionDate(txtDate.getCValue());
        transactionSummary.setReferenceNo(txtReferanceNo.getCValue());
        transactionSummary.setOutStore(null);
        transactionSummary.setInStore(null);
        transactionSummary.setMTransactor(
                (MTransactor) (transactionType.isClient() ? cboClient.getCValue() : cboSupplier.getCValue()));
        transactionSummary.setTotalValue(txtAmount.getCValue());
        transactionSummary.setItemDiscount(0.0);
        transactionSummary.setSpecialDiscount(0.0);
        transactionSummary.setNetTotal(txtAmount.getCValue());
//        transactionSummary.setCashAmount(txtAmount.getCValue());
        transactionSummary.setSession(0);
        transactionSummary.setDocumentNo(txtDocumentNo.getCValue());
        transactionSummary.setDescription(txtDescription.getCValue());
        transactionSummary.setMEmployee((MEmployee) cboEmployee.getCValue());
        transactionSummary.setRoute(
                transactionSummary.getMTransactor() != null
                ? transactionSummary.getMTransactor().getRoute()
                : null);
        transactionSummary.setStatus(TransactionStatus.ACTIVE);

        List<TTransactionSettlement> settlements = settlementTableModel.getSettlements();
        List<TTransactionSettlement> selectedSettlements = new ArrayList<>();
        double totalSettlement = 0.0;

        double settlementBalance = transactionSummary.getNetTotal();
        double settlemnetAmount;
        for (TTransactionSettlement transactionSettlement : settlements) {
            if (transactionSettlement.getAmount() != 0.0) {
                transactionSettlement.setBranch(transactionSummary.getBranch());
                transactionSettlement.setStatus(TransactorTransactionStatus.ACTIVE);

                settlemnetAmount = Math.min(settlementBalance, transactionSettlement.getAmount());
                settlementBalance = settlementBalance - settlemnetAmount;

                transactionSettlement.setTTransactionSummaryByTransactionSummary(transactionSummary);
                transactionSettlement.getTTransactionSummaryBySettleTransactionSummary()
                        .setBalanceAmount(
                        transactionSettlement.getTTransactionSummaryBySettleTransactionSummary().getBalanceAmount() - settlemnetAmount);



                selectedSettlements.add(transactionSettlement);
                totalSettlement = totalSettlement + settlemnetAmount;
            }
        }
        transactionSummary.getTTransactionSettlementsForTransactionSummary().clear();
        transactionSummary.getTTransactionSettlementsForTransactionSummary().addAll(selectedSettlements);

        if (totalSettlement > transactionSummary.getNetTotal() && transactionType.isSettlementTransaction()) {
            throw new Exception("Settlement Amount mismatch");
        }

        double balanceAmount;
        balanceAmount = transactionSummary.getNetTotal() - totalSettlement;

        System.out.println("BALANCE AMOUNT:" + balanceAmount);

//        transactionSummary.setCashAmount(transactionSummary.getNetTotal());
//        transactionSummary.setCreditAmount(balanceAmount);
//        transactionSummary.setBalanceAmount(balanceAmount);



        //payment dialog
        if (transactionType.isPayment()) {
            paymentDialog.setNetValue(transactionSummary.getNetTotal());
            Payment payment = paymentDialog.getNewObject();
            if (payment != null) {
                transactionSummary.setCashAmount(payment.getCashAmount());
                transactionSummary.setChequeAmount(payment.getChequeAmount());
                transactionSummary.setCreditAmount(balanceAmount);
                transactionSummary.setBalanceAmount(balanceAmount);
            } else {
                throw new Exception();
            }
        } else {
            transactionSummary.setCashAmount(0.0);
            transactionSummary.setChequeAmount(0.0);
            transactionSummary.setCreditAmount(balanceAmount);
            transactionSummary.setBalanceAmount(balanceAmount);
        }
    }

    private void loadTransaction() {
        int number = txtNo.getCValue();
        try {
            transactionSummary = serAccountTransaction.loadAccountTransaction(number, transactionType);

            if (transactionSummary != null) {
                isNew = false;
                initInterface();
            } else {
                mOptionPane.showMessageDialog(this, transactionType.getName() + " is not found for number " + number, "Load", mOptionPane.ERROR_MESSAGE);
                initNew();
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(AccountTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            initNew();
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        serAccountTransaction = new SERAccountTransaction(this);
        paymentDialog = new PaymentDialog(getDatabaseService());
        paymentDialog.setTitle(transactionType.getName());

        ActionUtil actionUtil = new ActionUtil(this);
        actionUtil.setAction(btnSave, "doSave");
        actionUtil.setAction(btnClear, "doClear");
        actionUtil.setAction(btnCancel, "doCancel");
        actionUtil.setAction(btnClose, "doClose");

        //component visibility
        cboClient.setVisible(transactionType.isClient());
        cboSupplier.setVisible(transactionType.isSupplier());
        lblClient.setVisible(transactionType.isClient());
        lblSupplier.setVisible(transactionType.isSupplier());


        initNew();

        btnSave.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_SAVE, 16, 16));
        btnClear.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_GENERATE, 16, 16));
        btnCancel.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_DISCARD, 16, 16));
        btnClose.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_CLOSE, 16, 16));

        txtNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loadTransaction();
                }
            }
        });

        cboClient.setExpressEditable(true);
        cboSupplier.setExpressEditable(true);
        cboEmployee.setExpressEditable(true);

//        FocusAdapter settlementFA = new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//            }
//        };
//        cboClient.getComboBox().addFocusListener(settlementFA);
//        cboSupplier.getComboBox().addFocusListener(settlementFA);

        cboClient.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                refreshSettlmnetTransactions();
//                setSettlementAmount(txtAmount.getCValue(), true);
                validatePayment();
            }
        });
        cboSupplier.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                refreshSettlmnetTransactions();
//                setSettlementAmount(txtAmount.getCValue(), true);
                validatePayment();
            }
        });

        settlementTableModel = new SettlementTableModel();
        tblSettlementTransaction.setModel(settlementTableModel);
        settlementTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (transactionType.isSettlementTransaction()) {
                    double settlementAmount = settlementTableModel.getSettlementAmount();
                    txtAmount.setCValue(settlementAmount);
                    validatePayment();
                }
            }
        });

        txtAmount.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
//                setSettlementAmount(txtAmount.getCValue(), true);
                settlementTableModel.setSettlementAmount(txtAmount.getCValue());

            }
        });

//        tblSettlementTransaction.setCModel(new CTableModel(
//                new CTableColumn[]{
//            new CTableColumn("No", "TTransactionSummaryBySettleTransactionSummary", "TransactionNo"),
//            new CTableColumn("Date", "TTransactionSummaryBySettleTransactionSummary", "TransactionDate"),
//            new CTableColumn("Balance", "TTransactionSummaryBySettleTransactionSummary", "balanceAmount"),
//            new CTableColumn("Settlement", "amount")}));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cIStringField2 = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDStringField1 = new com.mac.af.component.derived.display.textfield.CDStringField();
        jPanel1 = new javax.swing.JPanel();
        txtDocumentNo = new com.mac.af.component.derived.input.textfield.CIStringField();
        lblClient = new com.mac.af.component.derived.display.label.CDLabel();
        cboSupplier = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serAccountTransaction.getSuppliers();
            }
        };
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel5 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        btnClose = new com.mac.af.component.derived.command.button.CCButton();
        cDLabel8 = new com.mac.af.component.derived.display.label.CDLabel();
        lblSupplier = new com.mac.af.component.derived.display.label.CDLabel();
        cboClient = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serAccountTransaction.getClients();
            }
        };
        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        txtNo = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        btnClear = new com.mac.af.component.derived.command.button.CCButton();
        btnSave = new com.mac.af.component.derived.command.button.CCButton();
        cDLabel7 = new com.mac.af.component.derived.display.label.CDLabel();
        txtAmount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        txtDate = new com.mac.af.component.derived.input.textfield.CIDateField();
        txtReferanceNo = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        cDLabel6 = new com.mac.af.component.derived.display.label.CDLabel();
        cboEmployee = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serAccountTransaction.getEmployees();
            }
        };
        btnCancel = new com.mac.af.component.derived.command.button.CCButton();
        txtDescription = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel9 = new com.mac.af.component.derived.display.label.CDLabel();
        txtBalanceAmount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        pnlSettlement = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSettlementTransaction = new com.mac.af.component.derived.input.table.CITable();

        cIStringField2.setText("cIStringField2");

        cDStringField1.setText("cDStringField1");

        lblClient.setText("Client :");

        cDLabel3.setText("Reference No :");

        cDLabel5.setText("Description :");

        cDLabel1.setText("No");

        btnClose.setText("Close");

        cDLabel8.setText("Employee :");

        lblSupplier.setText("Supplier :");

        cDLabel2.setText("Date :");

        btnClear.setText("Clear");

        btnSave.setText("Save");

        cDLabel7.setText("Document No :");

        cDLabel6.setText("Amount :");

        btnCancel.setText("Cancel");

        cDLabel9.setText("Balance Amount :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtReferanceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboClient, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBalanceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReferanceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBalanceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocumentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblSettlementTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSettlementTransaction);

        javax.swing.GroupLayout pnlSettlementLayout = new javax.swing.GroupLayout(pnlSettlement);
        pnlSettlement.setLayout(pnlSettlementLayout);
        pnlSettlementLayout.setHorizontalGroup(
            pnlSettlementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );
        pnlSettlementLayout.setVerticalGroup(
            pnlSettlementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSettlement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSettlement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.command.button.CCButton btnCancel;
    private com.mac.af.component.derived.command.button.CCButton btnClear;
    private com.mac.af.component.derived.command.button.CCButton btnClose;
    private com.mac.af.component.derived.command.button.CCButton btnSave;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel5;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel6;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel7;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel8;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel9;
    private com.mac.af.component.derived.display.textfield.CDStringField cDStringField1;
    private com.mac.af.component.derived.input.textfield.CIStringField cIStringField2;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboClient;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboEmployee;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSupplier;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.mac.af.component.derived.display.label.CDLabel lblClient;
    private com.mac.af.component.derived.display.label.CDLabel lblSupplier;
    private javax.swing.JPanel pnlSettlement;
    private com.mac.af.component.derived.input.table.CITable tblSettlementTransaction;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtAmount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtBalanceAmount;
    private com.mac.af.component.derived.input.textfield.CIDateField txtDate;
    private com.mac.af.component.derived.input.textfield.CIStringField txtDescription;
    private com.mac.af.component.derived.input.textfield.CIStringField txtDocumentNo;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtNo;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtReferanceNo;
    // End of variables declaration//GEN-END:variables
    private SERAccountTransaction serAccountTransaction;
    private TTransactionSummary transactionSummary;
    private RTransactionType transactionType;
    private boolean isNew = false;
    private SettlementTableModel settlementTableModel;
    private PaymentDialog paymentDialog;
}
