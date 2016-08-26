/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.transaction.transaction_registration;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.transaction.transaction_registration.object.MReport;
import com.mac.transaction.transaction_registration.object.RTransactionType;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PCTransactionType extends DefaultObjectCreator {

    /**
     * Creates new form PCTransactionType
     */
    public PCTransactionType() {
        initComponents();

        initOthers();
    }

    private String[] getTransactionTypes() {
        return TransactionTypes.ITEM_TRANSACTION_TYPES;
    }

    private String[] getFormTypes() {
        return FormTypes.values;
    }

    private String[] getPriceTypes() {
        return PriceTypes.values;
    }

    public List getStores() {
        List brands;
        try {
            String hql = "FROM com.mac.transaction.transaction_registration.object.MStore";
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            brands = databaseService.getCollection(hql);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            brands = new ArrayList();
        }
        return brands;
    }
    
    public List getSettlementTransactions() {
        List brands;
        try {
            String hql = "FROM com.mac.transaction.transaction_registration.object.RTransactionType";
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            brands = databaseService.getCollection(hql);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            brands = new ArrayList();
        }
        return brands;
    }

    private List getReports() {
        List reports;

        try {
            reports = getCPanel().getDatabaseService().getCollection(MReport.class);
        } catch (DatabaseException ex) {
            Logger.getLogger(PCTransactionType.class.getName()).log(Level.SEVERE, null, ex);
            reports = new ArrayList();
        }

        return reports;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initOthers() {
//        cboFormType.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                String formType = (String) cboFormType.getCValue();
//                if (formType == null) {
//                    pnlItemTransaction.setVisible(false);
//                    pnlAccountTransaction.setVisible(false);
//                    return;
//                }
//
//                switch (formType) {
//                    case FormTypes.ITEM_TRANSACTION:
//                        pnlItemTransaction.setVisible(true);
//                        pnlAccountTransaction.setVisible(false);
//                        break;
//                    case FormTypes.ACCOUNT_TRANSACTION:
//                        pnlItemTransaction.setVisible(false);
//                        pnlAccountTransaction.setVisible(true);
//                        break;
//                }
//            }
//        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDateField1 = new com.mac.af.component.base.textfield.CDateField();
        jPanel1 = new javax.swing.JPanel();
        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        cboFormType = new com.mac.af.component.derived.input.combobox.CIComboBox(getFormTypes());
        pnlItemTransaction = new javax.swing.JPanel();
        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        txtCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();
        chkQuantityIn = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboQuantityIn = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return getStores();
            }

        }
        ;
        chkQuantityOut = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboQuantityOut = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return getStores();
            }

        }
        ;
        cDLabel4 = new com.mac.af.component.derived.display.label.CDLabel();
        cboPriceType = new com.mac.af.component.derived.input.combobox.CIComboBox(getPriceTypes());
        chkExpireDate = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkPercentDiscount = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkActive = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkSupplier = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkValueDiscount = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cDLabel5 = new com.mac.af.component.derived.display.label.CDLabel();
        cboTransactiontype = new com.mac.af.component.derived.input.combobox.CIComboBox(getTransactionTypes());
        chkClient = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkFirstReport = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboFirstReport = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return getReports();
            }

        }
        ;
        cboSecondReport = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return getReports();
            }

        }
        ;
        chkSecondReport = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboThirdReport = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return getReports();
            }

        }
        ;
        chkThirdReport = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkCreditAcc = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkDebitAcc = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboSettlementTransaction = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return getSettlementTransactions();
            }
        };
        chkSettlementTransaction = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkPayment = new com.mac.af.component.derived.input.checkbox.CICheckBox();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cDLabel1.setText("Form Type :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboFormType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFormType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlItemTransaction.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cDLabel2.setText("Code :");

        cDLabel3.setText("Name :");

        chkQuantityIn.setText("Quantity In :");
        chkQuantityIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkQuantityInActionPerformed(evt);
            }
        });

        chkQuantityOut.setText("Quantity Out :");

        cDLabel4.setText("Price Type :");

        chkExpireDate.setText("Expire Date");

        chkPercentDiscount.setText("Percent Discount");
        chkPercentDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPercentDiscountActionPerformed(evt);
            }
        });

        chkActive.setText("Active");

        chkSupplier.setText("Supplier");

        chkValueDiscount.setText("Value Discount");

        cDLabel5.setText("Transaction Type:");

        chkClient.setText("Client");

        chkFirstReport.setText("First Report");

        chkSecondReport.setText("Second Report");

        chkThirdReport.setText("Third Report");

        chkCreditAcc.setText("Credit Amount");

        chkDebitAcc.setText("Debit Amount");

        chkSettlementTransaction.setText("Settlement Transaction");

        chkPayment.setText("Payment");

        javax.swing.GroupLayout pnlItemTransactionLayout = new javax.swing.GroupLayout(pnlItemTransaction);
        pnlItemTransaction.setLayout(pnlItemTransactionLayout);
        pnlItemTransactionLayout.setHorizontalGroup(
            pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                        .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkSettlementTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkQuantityIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkQuantityOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cDLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkFirstReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkSecondReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkThirdReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTransactiontype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboQuantityIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboQuantityOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboPriceType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboFirstReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSecondReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboThirdReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSettlementTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlItemTransactionLayout.createSequentialGroup()
                        .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                                .addComponent(chkDebitAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 99, Short.MAX_VALUE))
                            .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                                        .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(chkExpireDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chkClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(chkPercentDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chkSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                                        .addComponent(chkCreditAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chkPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkValueDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)))
                .addContainerGap())
        );
        pnlItemTransactionLayout.setVerticalGroup(
            pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemTransactionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTransactiontype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkQuantityIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboQuantityIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkQuantityOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboQuantityOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkFirstReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFirstReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkSecondReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSecondReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkThirdReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboThirdReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPriceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboSettlementTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSettlementTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                        .addComponent(chkExpireDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(chkValueDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemTransactionLayout.createSequentialGroup()
                        .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkPercentDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCreditAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkDebitAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlItemTransaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlItemTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkQuantityInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkQuantityInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkQuantityInActionPerformed

    private void chkPercentDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPercentDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPercentDiscountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel4;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel5;
    private com.mac.af.component.base.textfield.CDateField cDateField1;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboFirstReport;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboFormType;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboPriceType;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboQuantityIn;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboQuantityOut;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSecondReport;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSettlementTransaction;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboThirdReport;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboTransactiontype;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkActive;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkClient;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkCreditAcc;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkDebitAcc;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkExpireDate;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkFirstReport;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkPayment;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkPercentDiscount;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkQuantityIn;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkQuantityOut;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkSecondReport;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkSettlementTransaction;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkSupplier;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkThirdReport;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkValueDiscount;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlItemTransaction;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List getIdentityComponents() {
        return Arrays.asList(txtCode);
    }

    @Override
    protected List getEssentialComponents() {
        return Arrays.asList(
                cboFormType,
                cboTransactiontype,
                chkQuantityIn,
                chkQuantityOut,
                cboPriceType,
                chkExpireDate,
                chkPercentDiscount,
                chkActive,
                chkClient,
                chkSupplier,
                txtName,
                chkValueDiscount,
                chkFirstReport,
                chkSecondReport,
                chkThirdReport);
    }

    @Override
    protected List getOtherFieldComponents() {
        return Arrays.asList(
                chkCreditAcc,
                chkDebitAcc,
                chkSettlementTransaction,
                
                //
                cboSettlementTransaction,
                cboQuantityIn,
                cboQuantityOut,
                cboFirstReport,
                cboSecondReport,
                cboThirdReport);
    }

    @Override
    protected List getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(cboFormType, "formType"),
                new CInputComponentBinder(cboTransactiontype, "type"),
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(txtName, "name"),
                new CInputComponentBinder(chkQuantityIn, "itemIn"),
                new CInputComponentBinder(chkQuantityOut, "itemOut"),
                new CInputComponentBinder(chkExpireDate, "expireDate"),
                new CInputComponentBinder(chkPercentDiscount, "percentageDiscount"),
                new CInputComponentBinder(chkActive, "active"),
                new CInputComponentBinder(chkClient, "client"),
                new CInputComponentBinder(chkSupplier, "supplier"),
                new CInputComponentBinder(chkValueDiscount, "fixedPrice"),
                new CInputComponentBinder(chkSettlementTransaction, "settlementTransaction"),
                new CInputComponentBinder(cboSettlementTransaction, "RTransactionType"),
                new CInputComponentBinder(cboPriceType, "priceType"),
                new CInputComponentBinder(cboQuantityIn, "MStoreByInStore"),
                new CInputComponentBinder(cboQuantityOut, "MStoreByOutStore"),
                new CInputComponentBinder(chkFirstReport, "printFirstReport"),
                new CInputComponentBinder(chkSecondReport, "printSecondReport"),
                new CInputComponentBinder(chkThirdReport, "printThirdReport"),
                new CInputComponentBinder(cboFirstReport, "MReportByFirstReport"),
                new CInputComponentBinder(cboSecondReport, "MReportBySecondReport"),
                new CInputComponentBinder(cboThirdReport, "MReportByThirdReport"),
                new CInputComponentBinder(chkPayment, "payment"),
                new CInputComponentBinder(chkCreditAcc, "creditAmount"),
                new CInputComponentBinder(chkDebitAcc, "debitAmount"));
    }

    @Override
    protected Class getObjectClass() {
        return com.mac.transaction.transaction_registration.object.RTransactionType.class;
    }
}
