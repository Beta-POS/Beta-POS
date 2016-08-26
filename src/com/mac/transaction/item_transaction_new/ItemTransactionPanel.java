/*
 *  ItemTransactionPanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 24, 2015, 10:37:10 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new;

import com.mac.af.component.base.button.action.Action;
import com.mac.af.component.base.button.action.ActionUtil;
import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.cpanel.CPanel;
import com.mac.af.core.environment.tab.TabFunctions;
import com.mac.af.core.message.mOptionPane;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.af.resources.ApplicationResources;
import com.mac.af.templates.report.ReportBuilder;
import com.mac.af.templates.report.ReportDialog;
import com.mac.reports.AbstractSphereReportViewer;
import com.mac.transaction.item_transaction_new.object.MEmployee;
import com.mac.transaction.item_transaction_new.object.MReport;
import com.mac.transaction.item_transaction_new.object.MStore;
import com.mac.transaction.item_transaction_new.object.MTransactor;
import com.mac.transaction.item_transaction_new.object.TTransactionSummary;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.TableUtil;
import com.mac.transaction.transaction_registration.TransactionTypes;
import com.mac.zresources.SphereResources;
import com.mac.zsystem.payment.PCPayments;
import com.mac.zsystem.payment.PaymentDialog;
import com.mac.zsystem.payment.hibernate.MBankBranch;
import com.mac.zsystem.payment.hibernate.TCheque;
import com.mac.zsystem.payment.object.Payment;
import com.mac.zsystem.util.hash.HashUtil;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohan
 */
public class ItemTransactionPanel extends CPanel {

    private TableUtil tableUtil;

    /**
     * Creates new form ItemTransactionPanel
     */
    public ItemTransactionPanel(RTransactionType transactionType) {
        //INITILIZE COMPONENTS
        initComponents();

        //INIT ENVIRONMENT VARIABLES
        initEnvironment(transactionType);

        //PREPARE USER INTERFACE
        initUserInterface();

        //INIT ACTIONS
        initActions();

        //create ui for new invoice
        initNewTransaction();
    }

    /**
     * Initializes environment for provided transaction type
     */
    private void initEnvironment(RTransactionType transactionType) {
        //CONTAINS TRANSACTION SETTINGS
        this.transactionType = transactionType;

        //SERVICES
        this.serItemTransaction = new SERItemTransaction(this);

        //payment
        pcPayments = new PCPayments() {
            @Override
            protected List getBranches() {
                try {
                    return getDatabaseService().getCollection(MBankBranch.class);
                } catch (DatabaseException ex) {
                    Logger.getLogger(PaymentDialog.class.getName()).log(Level.SEVERE, null, ex);
                    return new ArrayList();
                }
            }
        };
        pcPaymentContainer.add(pcPayments);
    }

    /**
     * Prepares user interface for the transaction
     */
    private void initUserInterface() {
        //SETTINGS FROM TRANSACTION TYPE
        //from store - item out
        lblFromStore.setVisible(transactionType.isItemOut());
        cboFromStore.setVisible(transactionType.isItemOut());

        //out store - item in
        lblToStore.setVisible(transactionType.isItemIn());
        cboToStore.setVisible(transactionType.isItemIn());

        //supplier
        lblSupplier.setVisible(transactionType.isSupplier());
        cboSupplier.setVisible(transactionType.isSupplier());

        //client
        lblCustomer.setVisible(transactionType.isClient());
        cboCustomer.setVisible(transactionType.isClient());

        //transactor address
        lblTransactorAddress.setVisible(transactionType.isSupplier() || transactionType.isClient());
        lblTransactorTelephone.setVisible(transactionType.isSupplier() || transactionType.isClient());

        //combo
        cboFromStore.setExpressEditable(true);
        cboToStore.setExpressEditable(true);
        cboCustomer.setExpressEditable(true);
        cboSupplier.setExpressEditable(true);
        cboEmployee.setExpressEditable(true);

        //text box enability
        txtInvoiceTotalValue.setValueEditable(false);
//        txtInvoiceItemDiscount.setValueEditable(false);
        txtInvoiceNetValue.setValueEditable(false);
//        txtBF.setValueEditable(false);
//        txtPayableValue.setValueEditable(false);

        //CREATE TABLE
        tableUtil = new TableUtil(serItemTransaction, transactionType, tblItems);
        tableUtil.setItemAddedRunnable(new Runnable() {
            @Override
            public void run() {
                resetItemTotals();
            }
        });

        //PANELS
        pnlStore.setPreferredSize(new Dimension(500, (int) pnlStore.getPreferredSize().getHeight()));

//        btnCustomerPayment.setVisible(TransactionTypes.SALES.equals(transactionType.getType()));
        //BUTTONS
        btnSave.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_SAVE, 16, 16));
//        btnCustomerPayment.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_SAVE, 16, 16));
        btnClear.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_GENERATE, 16, 16));
        btnPrint.setIcon(ApplicationResources.getImageIcon(ApplicationResources.PRINT_PREVIEW_PRINT, 16, 16));
        btnCancel.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_DISCARD, 16, 16));
        btnClose.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_CLOSE, 16, 16));
        btnRemove.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_REMOVE, 16, 16));

        //payment
        paymentDialog = new PaymentDialog(getDatabaseService());

        //recent
        CTableModel tableModel = new CTableModel(
                new CTableColumn("No", "transactionNo"),
                new CTableColumn("Date", "transactionDate"),
                new CTableColumn("Description", "description"),
                new CTableColumn("Cash", "cashAmount"),
                new CTableColumn("Cheque", "chequeAmount"),
                new CTableColumn("Net Value", "netTotal"));
        tblRecentPayments.setCModel(tableModel);
    }

    @Action
    public void doSave() {
        int q = mOptionPane.showConfirmDialog(null, "Do you sure want to save " + transactionType.getName() + " ?", "Save", mOptionPane.YES_NO_OPTION);

        if (q == mOptionPane.YES_OPTION) {
            try {
                if (saveTransaction() == SAVE_SUCCESS) {
                    mOptionPane.showMessageDialog(null, transactionType.getName() + " saved successfully !!!", "Save", mOptionPane.INFORMATION_MESSAGE);
                    initNewTransaction();
                }
            } catch (DatabaseException ex) {
                Logger.getLogger(ItemTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
                mOptionPane.showMessageDialog(this, "Failed to save " + transactionType.getName() + ".\n" + ex.getMessage(), "Cancel", mOptionPane.ERROR_MESSAGE);
            } catch (ObjectCreatorException ex) {
                Logger.getLogger(ItemTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
                mOptionPane.showMessageDialog(this, "Failed to save " + transactionType.getName() + ".\n" + ex.getMessage(), "Cancel", mOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Action
    public void doRemoveTransactorTransaction() {
//        TTransactorTransaction transactorTransaction = (TTransactorTransaction) tblRecentPayments.getSelectedValue();
//        if (transactorTransaction != null) {
//            int q = mOptionPane.showConfirmDialog(null, "Do you sure want to remove?", "Remove", mOptionPane.YES_NO_OPTION);
//            if (q == mOptionPane.YES_OPTION) {
//                transactionSummary.getTTransactorTransactions().remove(transactorTransaction);
//                tblRecentPayments.setCValue(transactionSummary.getTTransactorTransactions());
//            }
//        }

        TTransactionSummary summary = (TTransactionSummary) tblRecentPayments.getSelectedValue();

        if (summary != null) {
            int q = mOptionPane.showConfirmDialog(null, "Do you sure want to remove payment?", "Remove", mOptionPane.YES_NO_OPTION);
            if (q == mOptionPane.YES_OPTION) {
                serItemTransaction.cancel(summary);
                transactionSummary.setBalanceAmount(transactionSummary.getBalanceAmount() + summary.getNetTotal());
                validateUI();
            }
        }
    }

    @Action
    public void doCustomerPayment() {
        doSave();

        RTransactionType transactionType = serItemTransaction.getCutomerPayment();

        TabFunctions.AddTab(
                com.mac.transaction.account_transaction.AccountTransactionPanel.class,
                transactionType.getName(),
                SphereResources.getImageIcon(SphereResources.REGISTRATION_PEOPLE, 22, 22),
                new String[]{transactionType.getCode(), transactor.getHash()},
                true);
    }

    @Action
    public void doPrint() {
        if (!isNew) {
            Map<String, Object> params = new HashMap<>();
            params.put("INDEX_NO", transactionSummary.getIndexNo());

            Map<String, Object> defaultParams = AbstractSphereReportViewer.getDefaultParameterMap();

            MReport report = null;
            transactionType.getMReportByFirstReport();
            if (report != null) {
                ReportDialog reportDialog = new ReportDialog(new ByteArrayInputStream(report.getReport()),
                        defaultParams,
                        params);
                reportDialog.setTitle(ReportBuilder.getFormattedString(report.getName()));
                reportDialog.setVisible(true);
            }
        } else {
            mOptionPane.showMessageDialog(null, transactionType.getName() + " is not saved.", "Print", mOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Action
    public void doClear() {
        int q = mOptionPane.showConfirmDialog(null, "Do you sure want to clear " + transactionType.getName() + " ?", "Clear", mOptionPane.YES_NO_OPTION);

        if (q == mOptionPane.YES_OPTION) {
            initNewTransaction();
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
                    initNewTransaction();
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
        TabFunctions.closeTab(this);
    }

    @SuppressWarnings("unchecked")
    private void initActions() {
        //actions
        ActionUtil actionUtil = new ActionUtil(this);
        actionUtil.setAction(btnSave, "doSave");
//        actionUtil.setAction(btnCustomerPayment, "doCustomerPayment");
        actionUtil.setAction(btnClear, "doClear");
        actionUtil.setAction(btnPrint, "doPrint");
        actionUtil.setAction(btnCancel, "doCancel");
        actionUtil.setAction(btnClose, "doClose");
        actionUtil.setAction(btnRemove, "doRemoveTransactorTransaction");

        txtInvoiceSpecialDiscount.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                resetItemTotals();
            }
        });
        txtNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && txtNo.getCValue() != number) {
                    loadTransaction();
                }
            }
        });

        ItemListener transactorChange = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                MTransactor transactor = (MTransactor) e.getItem();

                if (transactor != null) {
                    lblTransactorAddress.setText(transactor.getAddressLine1() + "," + transactor.getAddressLine2() + "," + transactor.getAddressLine3());
                    lblTransactorTelephone.setText(transactor.getTelephone1());
//                    txtBF.setCValue(transactor.getBalanceAmount());
                } else {
                    lblTransactorAddress.setText(null);
                    lblTransactorTelephone.setText(null);
//                    txtBF.setCValue(0.0);
                }
            }
        };

        cboCustomer.addItemListener(transactorChange);
        cboSupplier.addItemListener(transactorChange);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTransactorAddress = new javax.swing.JLabel();
        lblTransactorTelephone = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItems = new com.mac.af.component.derived.input.table.CITable();
        jPanel5 = new javax.swing.JPanel();
        btnClose = new com.mac.af.component.derived.command.button.CCButton();
        btnSave = new com.mac.af.component.derived.command.button.CCButton();
        btnClear = new com.mac.af.component.derived.command.button.CCButton();
        btnPrint = new com.mac.af.component.derived.command.button.CCButton();
        btnCancel = new com.mac.af.component.derived.command.button.CCButton();
        pcPaymentContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtInvoiceTotalValue = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        lblInvoiceTotalValue = new com.mac.af.component.derived.display.label.CDLabel();
        txtInvoiceNetValue = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        lblInvoiceNetValue = new com.mac.af.component.derived.display.label.CDLabel();
        lblInvoiceSpecialDiscount = new com.mac.af.component.derived.display.label.CDLabel();
        txtInvoiceSpecialDiscount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        pnlStore = new javax.swing.JPanel();
        lblCustomer = new com.mac.af.component.derived.display.label.CDLabel();
        cboSupplier = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return    serItemTransaction.getSuppliers();
            }
        };
        lblFromStore = new com.mac.af.component.derived.display.label.CDLabel();
        cboToStore = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return    serItemTransaction.getStores();
            }
        };
        cboFromStore = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return    serItemTransaction.getStores();
            }
        };
        lblSupplier = new com.mac.af.component.derived.display.label.CDLabel();
        cboCustomer = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return    serItemTransaction.getClients();
            }

        };
        lblToStore = new com.mac.af.component.derived.display.label.CDLabel();
        jPanel3 = new javax.swing.JPanel();
        txtNo = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        cDLabel5 = new com.mac.af.component.derived.display.label.CDLabel();
        txtReferenceNo = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        lblDate = new com.mac.af.component.derived.display.label.CDLabel();
        lblReferenceNo = new com.mac.af.component.derived.display.label.CDLabel();
        txtDate = new com.mac.af.component.derived.input.textfield.CIDateField();
        lblStatus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtDocumentNo = new com.mac.af.component.derived.input.textfield.CIStringField();
        cboEmployee = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return    serItemTransaction.getEmployees();
            }

        };
        lblDocumentNo = new com.mac.af.component.derived.display.label.CDLabel();
        lblDescription = new com.mac.af.component.derived.display.label.CDLabel();
        lblEmployee = new com.mac.af.component.derived.display.label.CDLabel();
        txtDescription = new com.mac.af.component.derived.input.textfield.CIStringField();
        jPanel2 = new javax.swing.JPanel();
        pnlRecentContainer = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRecentPayments = new com.mac.af.component.derived.input.table.CITable();
        btnRemove = new com.mac.af.component.derived.command.button.CCButton();

        lblTransactorAddress.setText("-");

        lblTransactorTelephone.setText("-");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblItems);

        btnClose.setText("Close");

        btnSave.setText("Save");

        btnClear.setText("Clear");

        btnPrint.setText("Print");

        btnCancel.setText("Cancel");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pcPaymentContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pcPaymentContainer.setLayout(new javax.swing.BoxLayout(pcPaymentContainer, javax.swing.BoxLayout.LINE_AXIS));

        txtInvoiceTotalValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblInvoiceTotalValue.setText("Total Value:");

        txtInvoiceNetValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblInvoiceNetValue.setText("Net Value:");

        lblInvoiceSpecialDiscount.setText("Special Discount:");

        txtInvoiceSpecialDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInvoiceTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInvoiceNetValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInvoiceSpecialDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtInvoiceNetValue, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txtInvoiceTotalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtInvoiceSpecialDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInvoiceTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceSpecialDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInvoiceSpecialDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceNetValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInvoiceNetValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCustomer.setText("Customer :");

        lblFromStore.setText("From Store :");

        lblSupplier.setText("Supplier :");

        lblToStore.setText("To Store :");

        javax.swing.GroupLayout pnlStoreLayout = new javax.swing.GroupLayout(pnlStore);
        pnlStore.setLayout(pnlStoreLayout);
        pnlStoreLayout.setHorizontalGroup(
            pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFromStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblToStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboToStore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboFromStore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlStoreLayout.setVerticalGroup(
            pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblFromStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFromStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblToStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboToStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cDLabel5.setText("No.:");

        lblDate.setText("Date :");

        lblReferenceNo.setText("Reference No. :");

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(204, 0, 51));
        lblStatus.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(txtNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtReferenceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDocumentNo.setText("Document No.:");

        lblDescription.setText("Description :");

        lblEmployee.setText("Employee :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDocumentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDocumentNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblRecentPayments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblRecentPayments);

        javax.swing.GroupLayout pnlRecentContainerLayout = new javax.swing.GroupLayout(pnlRecentContainer);
        pnlRecentContainer.setLayout(pnlRecentContainerLayout);
        pnlRecentContainerLayout.setHorizontalGroup(
            pnlRecentContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecentContainerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlRecentContainerLayout.setVerticalGroup(
            pnlRecentContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecentContainerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRecentContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRecentContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnRemove.setText("Remove");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(pnlStore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pcPaymentContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlStore, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(pcPaymentContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.command.button.CCButton btnCancel;
    private com.mac.af.component.derived.command.button.CCButton btnClear;
    private com.mac.af.component.derived.command.button.CCButton btnClose;
    private com.mac.af.component.derived.command.button.CCButton btnPrint;
    private com.mac.af.component.derived.command.button.CCButton btnRemove;
    private com.mac.af.component.derived.command.button.CCButton btnSave;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel5;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboCustomer;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboEmployee;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboFromStore;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSupplier;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboToStore;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.mac.af.component.derived.display.label.CDLabel lblCustomer;
    private com.mac.af.component.derived.display.label.CDLabel lblDate;
    private com.mac.af.component.derived.display.label.CDLabel lblDescription;
    private com.mac.af.component.derived.display.label.CDLabel lblDocumentNo;
    private com.mac.af.component.derived.display.label.CDLabel lblEmployee;
    private com.mac.af.component.derived.display.label.CDLabel lblFromStore;
    private com.mac.af.component.derived.display.label.CDLabel lblInvoiceNetValue;
    private com.mac.af.component.derived.display.label.CDLabel lblInvoiceSpecialDiscount;
    private com.mac.af.component.derived.display.label.CDLabel lblInvoiceTotalValue;
    private com.mac.af.component.derived.display.label.CDLabel lblReferenceNo;
    private javax.swing.JLabel lblStatus;
    private com.mac.af.component.derived.display.label.CDLabel lblSupplier;
    private com.mac.af.component.derived.display.label.CDLabel lblToStore;
    private javax.swing.JLabel lblTransactorAddress;
    private javax.swing.JLabel lblTransactorTelephone;
    private javax.swing.JPanel pcPaymentContainer;
    private javax.swing.JPanel pnlRecentContainer;
    private javax.swing.JPanel pnlStore;
    private com.mac.af.component.derived.input.table.CITable tblItems;
    private com.mac.af.component.derived.input.table.CITable tblRecentPayments;
    private com.mac.af.component.derived.input.textfield.CIDateField txtDate;
    private com.mac.af.component.derived.input.textfield.CIStringField txtDescription;
    private com.mac.af.component.derived.input.textfield.CIStringField txtDocumentNo;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtInvoiceNetValue;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtInvoiceSpecialDiscount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtInvoiceTotalValue;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtNo;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtReferenceNo;
    // End of variables declaration//GEN-END:variables
    private RTransactionType transactionType;   //TRANSACTION SETTINGS
    private SERItemTransaction serItemTransaction;  //TRANSACTION SERVICE
    private TTransactionSummary transactionSummary;
    private int number;
    private boolean isNew = false;
    private PaymentDialog paymentDialog;
    private static final int SAVE_SUCCESS = 0;
    private static final int SAVE_DISCARD = 1;
    private static final int SAVE_FAILED = 2;
    private MTransactor transactor;
    private PCPayments pcPayments;

    private void initNewTransaction() {
        transactionSummary = new TTransactionSummary();
        try {

            number = (serItemTransaction.getNewNumber("transaction_type='" + transactionType.getCode() + "' AND branch='" + HashUtil.getBranch() + "'"));
            transactionSummary.setTransactionNo(number);
            pcPayments.setNewMood();
            isNew = true;
//            tblItems.setCValue(new ArrayList());
//            txtNo.setCValue(transactionSummary.getIndexNo());

            System.out.println("--------------------NUMBER : " + number);
            validateUI();
            resetItemTotals();
        } catch (ApplicationException ex) {
            Logger.getLogger(ItemTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validateUI() {
        cboFromStore.setCValue(transactionSummary.getMStoreByOutStore());
        cboToStore.setCValue(transactionSummary.getMStoreByInStore());
        if (transactionType.isClient()) {
            cboCustomer.setCValue(transactionSummary.getMTransactor());
            cboSupplier.setCValue(null);
        }
        if (transactionType.isSupplier()) {
            cboCustomer.setCValue(null);
            cboSupplier.setCValue(transactionSummary.getMTransactor());
        }

        txtNo.setCValue(transactionSummary.getTransactionNo());
        txtDate.setCValue(transactionSummary.getTransactionDate());
        txtReferenceNo.setCValue(transactionSummary.getReferenceNo());

        txtDescription.setCValue(transactionSummary.getDescription());
        cboEmployee.setCValue(transactionSummary.getMEmployee());
        txtDocumentNo.setCValue(transactionSummary.getDocumentNo());

        txtInvoiceTotalValue.setCValue(transactionSummary.getTotalValue());
//        txtInvoiceItemDiscount.setCValue(transactionSummary.getItemDiscount());
        txtInvoiceSpecialDiscount.setCValue(transactionSummary.getSpecialDiscount());
        txtInvoiceNetValue.setCValue(transactionSummary.getNetTotal());

        tblItems.setCValue(transactionSummary.getTTransactionDetails());

        if (transactionSummary.getStatus() != null) {
            if (transactionSummary.getStatus().equals(TransactionStatus.CANCEL)) {
                lblStatus.setText("Cancelled...!!!");
            } else {
                lblStatus.setText("");
            }
        } else {
            lblStatus.setText("");
        }

        //payment
        pcPayments.setCheques(transactionSummary.getCheques());
        try {
            pcPayments.setValue(transactionSummary.getPayment());
        } catch (ObjectCreatorException ex) {
            Logger.getLogger(ItemTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        pcPayments.setEditMood();

//        pcPaymentContainer.setBorder(BorderFactory.crea);
        if (isNew) {
            tblRecentPayments.setCValue(new ArrayList());
        } else {
            tblRecentPayments.setCValue(serItemTransaction.getSettlements(transactionSummary));
        }

        pcPaymentContainer.setVisible(transactionType.getRTransactionType() != null);
        pnlRecentContainer.setVisible(transactionType.getRTransactionType() != null);
        btnRemove.setVisible(transactionType.getRTransactionType() != null);
    }

    private void resetItemTotals() {
        double totalValue = 0.0;
        double totalItemDiscount = 0.0;
        double specialDiscount = txtInvoiceSpecialDiscount.getCValue();
        double netValue;
//        double bfBalance = txtBF.getCValue();
        double settlementAmount = 0.0;
        double payableValue = 0.0;

        Collection<TTransactionDetail> transactionDetails = tblItems.getCValue();

        for (TTransactionDetail transactionDetail : transactionDetails) {
            totalValue += (transactionDetail.getPrice() * ((double) transactionDetail.getQuantity()));
            totalItemDiscount += (transactionDetail.getDiscount() == null ? 0.0 : transactionDetail.getDiscount());
        }

        netValue = totalValue - totalItemDiscount - specialDiscount;
//        settlementAmount = Math.min(netValue, bfBalance);
        payableValue = netValue - settlementAmount;

        txtInvoiceTotalValue.setCValue(totalValue);
        txtInvoiceNetValue.setCValue(netValue);
//        txtSettlementAmount.setCValue(settlementAmount);
//        txtPayableValue.setCValue(payableValue);
        pcPayments.setPayableValue(payableValue);
    }

    private TTransactionSummary getTransactionSummary() throws ObjectCreatorException {
        if (transactionType.isClient()) {
            transactionSummary.setMTransactor((MTransactor) cboCustomer.getCValue());
        }
        if (transactionType.isSupplier()) {
            transactionSummary.setMTransactor((MTransactor) cboSupplier.getCValue());
        }
        transactor = transactionSummary.getMTransactor();

        transactionSummary.setMStoreByOutStore((MStore) cboFromStore.getCValue());
        transactionSummary.setMStoreByInStore((MStore) cboToStore.getCValue());
        transactionSummary.setRTransactionType(transactionType);
        transactionSummary.setBranch(HashUtil.getBranch());
        transactionSummary.setTransactionDate(txtDate.getCValue());
        transactionSummary.setReferenceNo(txtReferenceNo.getCValue());
        transactionSummary.setTotalValue(txtInvoiceTotalValue.getCValue());
//        transactionSummary.setItemDiscount(txtInvoiceItemDiscount.getCValue());
        transactionSummary.setItemDiscount(0.0);
        transactionSummary.setSpecialDiscount(txtInvoiceSpecialDiscount.getCValue());
        transactionSummary.setNetTotal(txtInvoiceNetValue.getCValue());

        transactionSummary.setSession(0);
        transactionSummary.setDocumentNo(txtDocumentNo.getCValue());
        transactionSummary.setDescription(txtDescription.getCValue());
        transactionSummary.setMEmployee((MEmployee) cboEmployee.getCValue());
        transactionSummary.setRoute(transactionSummary.getMTransactor() != null
                ? transactionSummary.getMTransactor().getRoute()
                : null);

        transactionSummary.setStatus(TransactionStatus.ACTIVE);

        //transaction details
        Collection<TTransactionDetail> collection = tblItems.getCValue();
        Set<TTransactionDetail> transactionDetails = new HashSet<>(collection);
        for (TTransactionDetail transactionDetail : transactionDetails) {
            transactionDetail.setMStoreByInStore(transactionSummary.getMStoreByInStore());
            transactionDetail.setMStoreByOutStore(transactionSummary.getMStoreByOutStore());
            transactionDetail.setTTransactionSummary(transactionSummary);
        }

        transactionSummary.getTTransactionDetails().clear();
        transactionSummary.getTTransactionDetails().addAll(transactionDetails);

//        Payment payment = (Payment) pcPayments.getValue();
        return transactionSummary;
    }

    private void cancelTransaction() throws DatabaseException {
        try {
            transactionSummary = getTransactionSummary();
            transactionSummary.setStatus(TransactionStatus.CANCEL);
            serItemTransaction.saveItemTransaction(transactionSummary, transactionType, isNew, null);
        } catch (ObjectCreatorException ex) {
            Logger.getLogger(ItemTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int saveTransaction() throws DatabaseException, ObjectCreatorException {
        transactionSummary = getTransactionSummary();

        List<TCheque> cheques = null;

        Payment payment = null;
        if (transactionType.isPayment()) {
            paymentDialog.setNetValue(transactionSummary.getNetTotal());
            paymentDialog.setTitle(transactionType.getName());
            payment = paymentDialog.getNewObject();

            System.out.println(payment.getCashAmount() + "------------------->");
            
            transactionSummary.setPayment(payment);
//            transactionSummary.setCashAmount(payment.getCashAmount());
//            transactionSummary.setChequeAmount(payment.getChequeAmount());
//            transactionSummary.setCreditAmount(payment.getChequeAmount());
//            transactionSummary.setBalanceAmount(payment.getCreditAmount());
        }

//        Payment payment = (Payment) pcPayments.getValue();
//        cheques = pcPayments.getCheques();
        if (payment == null) {
            return SAVE_DISCARD;
        }

//        transactionSummary.setCashAmount(0.0);
//        transactionSummary.setChequeAmount(0.0);
//        transactionSummary.setCreditAmount(transactionSummary.getNetTotal());
//        transactionSummary.setBalanceAmount(transactionSummary.getNetTotal());
//        } else {
//            transactionSummary.setCashAmount(0.0);
//            transactionSummary.setChequeAmount(0.0);
//            transactionSummary.setCreditAmount(transactionSummary.getNetTotal());
//            transactionSummary.setBalanceAmount(transactionSummary.getNetTotal());
//        }
        int transactionIndex = serItemTransaction.saveItemTransaction(transactionSummary, transactionType, isNew, cheques);

        if (transactionIndex != -1) {

            Map<String, Object> params = new HashMap<>();
            params.put("TRANSACTION_TYPE", transactionType.getCode());
            params.put("INDEX_NO", transactionIndex);
            params.put("BRANCH", AbstractSphereReportViewer.getBranch());

            Map<String, Object> defaultParams = AbstractSphereReportViewer.getDefaultParameterMap();

            System.out.println(params);
            System.out.println(defaultParams);

            //print reports
            if (transactionType.isPrintFirstReport()) {
                int q = mOptionPane.showConfirmDialog(this, "Do you want to print Report?", "Print Report", mOptionPane.YES_NO_OPTION);
                if (q == mOptionPane.YES_OPTION) {
                    if (isNew) {
                        MReport report = transactionType.getMReportByFirstReport();
                        if (report != null) {
                            ReportDialog reportDialog = new ReportDialog(new ByteArrayInputStream(report.getReport()),
                                    defaultParams,
                                    params);
                            reportDialog.setTitle(ReportBuilder.getFormattedString(report.getName()));
                            reportDialog.setVisible(true);
                        }
                    } else {
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
            }

            return SAVE_SUCCESS;
        } else {
            return SAVE_FAILED;
        }
    }

    private void loadTransaction() {
        number = txtNo.getCValue();

        try {
            transactionSummary = serItemTransaction.loadItemTransaction(number, transactionType);
            if (transactionSummary == null) {
                mOptionPane.showMessageDialog(this, transactionType.getName() + " is not found for number " + number, "Load", mOptionPane.ERROR_MESSAGE);
                initNewTransaction();
            } else {
                isNew = false;
                if (transactionSummary.getStatus().equals(TransactionStatus.CANCEL)) {
                    int q = mOptionPane.showConfirmDialog(null, "Seleced " + transactionType.getName() + " is cancelled. Do you want to edit it?"
                            + "\nNote: " + transactionType.getName() + " will revert to Active after saving.", "Load", mOptionPane.YES_NO_OPTION);

                    if (q != mOptionPane.YES_OPTION) {
                        initNewTransaction();
                    }
                }

                validateUI();
            }
        } catch (DatabaseException databaseException) {
            mOptionPane.showMessageDialog(this, transactionType.getName() + " is unable to load for number " + number, "Load", mOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ItemTransactionPanel.class.getName()).log(Level.SEVERE, null, databaseException);
        }
    }
}
