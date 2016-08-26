/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.zsystem.payment;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.zsystem.payment.hibernate.TCheque;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author pc-n
 */
public abstract class PCPayments extends DefaultObjectCreator {

    /**
     * Creates new form payments
     */
    private double payableValue;
    private PCCheque pcCheque;

    public PCPayments() {
        initComponents();
        initOthers();
    }

    protected abstract List getBranches();

    public void setPayableValue(double netvalue) {
        this.payableValue = netvalue;
        txtNetValue.setCValue(netvalue);
        refershChequeAmount();
        refreshCreditAmount();
    }

    public List<TCheque> getCheques() {
        return (List<TCheque>) tblCheques.getCValue();
    }

    public void setCheques(List<TCheque> cheques) {
        tblCheques.setCValue(cheques);
        refershChequeAmount();
        refreshCreditAmount();
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        pcCheque = new PCCheque() {
            @Override
            protected List getBranches() {
                return PCPayments.this.getBranches();
            }
        };
        tblCheques.setObjectCreator(pcCheque);
        tblCheques.setCModel(new CTableModel(
                new CTableColumn[]{
            new CTableColumn("Branch", "MBankBranch", "name"),
            new CTableColumn("Account No.", "accountNo"),
            new CTableColumn("Cheque No.", "chequeNo"),
            new CTableColumn("Cheque Date", "chequeDate"),
            new CTableColumn("Amount", "amount")
        }));
        tblCheques.setDescrption("Add Cheque");
        tblCheques.setCValue(new ArrayList());
        tblCheques.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refershChequeAmount();
                refreshCreditAmount();
            }
        });


        txtCashAmount.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                refreshCreditAmount();
            }
        });
    }

    private void refershChequeAmount() {
        List<TCheque> cheques = (List<TCheque>) tblCheques.getCValue();

        double chequeTotal = 0.0;
        if (cheques != null) {
            for (TCheque tCheque : cheques) {
                chequeTotal += tCheque.getAmount();
            }
        }
        txtChequeAmount.setCValue(chequeTotal);
    }

    private void refreshCreditAmount() {
        double netValue = txtNetValue.getCValue();
        double cashAmount = txtCashAmount.getCValue();
        double chequeAmount = txtChequeAmount.getCValue();

        double creditAmount = netValue - cashAmount - chequeAmount;

        txtCreditAmount.setCValue(creditAmount);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel1 = new com.mac.af.component.base.label.CLabel();
        cLabel2 = new com.mac.af.component.base.label.CLabel();
        cLabel3 = new com.mac.af.component.base.label.CLabel();
        txtNetValue = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        txtCashAmount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        txtCreditAmount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        cLabel4 = new com.mac.af.component.base.label.CLabel();
        txtChequeAmount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        tblCheques = new com.mac.af.component.derived.input.table.CIAddingTable();

        cLabel1.setText("Payable Value :");

        cLabel2.setText("Cash Amount :");

        cLabel3.setText("Credit Amount :");

        cLabel4.setText("Cheque Amount :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblCheques, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNetValue, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addComponent(txtCashAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCreditAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChequeAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNetValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCashAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChequeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblCheques, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.base.label.CLabel cLabel1;
    private com.mac.af.component.base.label.CLabel cLabel2;
    private com.mac.af.component.base.label.CLabel cLabel3;
    private com.mac.af.component.base.label.CLabel cLabel4;
    private com.mac.af.component.derived.input.table.CIAddingTable tblCheques;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtCashAmount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtChequeAmount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtCreditAmount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtNetValue;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List getIdentityComponents() {
        return Arrays.asList(
                txtNetValue);
    }

    @Override
    protected List getEssentialComponents() {
        return Arrays.asList(
                txtNetValue,
                txtCreditAmount,
                txtCashAmount);
    }

    @Override
    protected List getOtherFieldComponents() {
        return Arrays.asList();
    }

    @Override
    protected List getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtNetValue, "netValue"),
                new CInputComponentBinder(txtCashAmount, "cashAmount"),
                new CInputComponentBinder(txtChequeAmount, "chequeAmount"),
                new CInputComponentBinder(txtCreditAmount, "creditAmount"));
    }

    @Override
    protected List getUneditableComponents() {
        return Arrays.asList(
                txtNetValue,
                txtCreditAmount,
                txtChequeAmount);
    }

    @Override
    protected Class getObjectClass() {
        return com.mac.zsystem.payment.object.Payment.class;
    }

    @Override
    public void setNewMood() {
        super.setNewMood();

        txtNetValue.setCValue(payableValue);
    }
}
