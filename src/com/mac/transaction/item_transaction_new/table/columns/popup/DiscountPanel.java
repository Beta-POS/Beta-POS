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
import com.mac.transaction.item_transaction_new.TransactionDetailUtil;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.PopupPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author mohan
 */
public class DiscountPanel extends PopupPanel {

    /**
     * Creates new form DiscountPanel
     */
    public DiscountPanel(RTransactionType transactionType) {
        this.transactionType = transactionType;

        initComponents();

        initOthers();
    }

    private void discountPrecentChanged() {
        double percent = txtPercentDiscount.getCValue();
        double itemValue = transactionDetail.getPrice() * (double) transactionDetail.getQuantity();
        discountValue = itemValue * percent / 100;

        txtValueDiscount.setCValue(discountValue);

        txtDiscount.setCValue(discountValue);
    }

    private void discountValueChanged() {
        discountValue = txtValueDiscount.getCValue();
        double itemValue = transactionDetail.getPrice() * (double) transactionDetail.getQuantity();
        double percent = discountValue / itemValue * 100;

        txtPercentDiscount.setCValue(percent);

        txtDiscount.setCValue(discountValue);
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        lblPercentDiscount.setVisible(transactionType.isPercentageDiscount());
        txtPercentDiscount.setVisible(transactionType.isPercentageDiscount());

        txtPercentDiscount.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                discountPrecentChanged();
            }
        });

        txtValueDiscount.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                discountValueChanged();
            }
        });

//        txtValueDiscount.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    if (!transactionType.isPercentageDiscount()) {
//                        accept();
//                    }
//                }
//            }
//        });
//
//        txtPercentDiscount.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    accept();
//                }
//            }
//        });

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


        ComponentFocusKeyEvent.remove(txtDiscount);
        
        txtPercentDiscount.setName("PERCENT DISCOUNT");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        txtDiscount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        lblValueDiscount = new com.mac.af.component.derived.display.label.CDLabel();
        txtValueDiscount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        lblPercentDiscount = new com.mac.af.component.derived.display.label.CDLabel();
        txtPercentDiscount = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        btnOk = new com.mac.af.component.derived.command.button.CCButton();

        cDLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cDLabel1.setText("DISCOUNT");
        cDLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblValueDiscount.setText("Value :");

        txtValueDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblPercentDiscount.setText("Percent :");

        txtPercentDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnOk.setText("OK");

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
                            .addComponent(lblPercentDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValueDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(txtPercentDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtDiscount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValueDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValueDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPercentDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPercentDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.command.button.CCButton btnOk;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel lblPercentDiscount;
    private com.mac.af.component.derived.display.label.CDLabel lblValueDiscount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtDiscount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtPercentDiscount;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtValueDiscount;
    // End of variables declaration//GEN-END:variables
    private RTransactionType transactionType;
    private TTransactionDetail transactionDetail;
    private double discountValue = 0.0;

    @Override
    protected void onInitializeView(TTransactionDetail transactionDetail1) {
        this.transactionDetail = transactionDetail1;

        txtValueDiscount.requestFocus();
    }

    @Override
    protected void onAccept(TTransactionDetail transactionDetail1) {
        transactionDetail1.setDiscount(discountValue);
        TransactionDetailUtil.calculateNetValues(transactionDetail1);
    }

    @Override
    protected boolean isValueAcceptable() {
        return true;
    }

    @Override
    public void resetView() {
        txtDiscount.resetValue();
        txtPercentDiscount.resetValue();
        txtValueDiscount.resetValue();
        discountValue = 0.0;
    }
}
