/*
 *  DiscountPanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 8:26:01 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns.popup;

import com.mac.af.component.derived.input.ComponentFocusKeyEvent;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.PopupPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author mohan
 */
public class NetValuePanel extends PopupPanel {

    /**
     * Creates new form DiscountPanel
     */
    public NetValuePanel(RTransactionType transactionType) {
        this.transactionType = transactionType;

        initComponents();

        initOthers();
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        btnOk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    accept();
                }
            }
        });
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accept();
            }
        });

        ComponentFocusKeyEvent.remove(txtPrice);
        ComponentFocusKeyEvent.remove(txtQuantity);
        ComponentFocusKeyEvent.remove(txtValue);
        ComponentFocusKeyEvent.remove(txtDiscount);
        ComponentFocusKeyEvent.remove(txtNetValue);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDiscount = new com.mac.af.component.derived.display.label.CDLabel();
        txtDiscount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        txtNetValue = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        lblValueDiscount = new com.mac.af.component.derived.display.label.CDLabel();
        txtPrice = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        lblQuantity = new com.mac.af.component.derived.display.label.CDLabel();
        btnOk = new com.mac.af.component.derived.command.button.CCButton();
        lblValue = new com.mac.af.component.derived.display.label.CDLabel();
        txtValue = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        txtQuantity = new com.mac.af.component.derived.input.textfield.CIIntegerField();

        lblDiscount.setText("Discount :");

        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        cDLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cDLabel1.setText("NET VALUE");
        cDLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        txtNetValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblValueDiscount.setText("Price :");

        txtPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblQuantity.setText("Quantity :");

        btnOk.setText("OK");

        lblValue.setText("Value :");

        txtValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cDLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValueDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(txtValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtNetValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNetValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValueDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.command.button.CCButton btnOk;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel lblDiscount;
    private com.mac.af.component.derived.display.label.CDLabel lblQuantity;
    private com.mac.af.component.derived.display.label.CDLabel lblValue;
    private com.mac.af.component.derived.display.label.CDLabel lblValueDiscount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtDiscount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtNetValue;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtPrice;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtQuantity;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtValue;
    // End of variables declaration//GEN-END:variables
    private RTransactionType transactionType;

    @Override
    protected void onInitializeView(TTransactionDetail transactionDetail1) {
        txtPrice.setCValue(transactionDetail1.getPrice());
        txtQuantity.setCValue(transactionDetail1.getQuantity());
        txtValue.setCValue(transactionDetail1.getPrice() * transactionDetail1.getQuantity());
        txtDiscount.setCValue(transactionDetail1.getDiscount());
        txtNetValue.setCValue(transactionDetail1.getNetValue());

        btnOk.requestFocus();
    }

    @Override
    protected void onAccept(TTransactionDetail transactionDetail1) {
    }

    @Override
    protected boolean isValueAcceptable() {
        return true;
    }

    @Override
    public void resetView() {
        txtPrice.resetValue();
        txtQuantity.resetValue();
        txtValue.resetValue();
        txtDiscount.resetValue();
        txtNetValue.resetValue();
    }
}
