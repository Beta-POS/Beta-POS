/*
 *  QuantityPanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 8:21:20 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns.popup;

import com.mac.af.component.derived.input.ComponentFocusKeyEvent;
import com.mac.transaction.item_transaction_new.TransactionDetailUtil;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.PopupPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author mohan
 */
public class QuantityPanel extends PopupPanel {

    /**
     * Creates new form QuantityPanel
     */
    public QuantityPanel() {
        initComponents();
        initOthers();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initOthers() {
        txtQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    accept();
                }
            }
        });

        ComponentFocusKeyEvent.remove(txtQuantity);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        txtQuantity = new com.mac.af.component.derived.input.textfield.CIIntegerField();

        cDLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cDLabel1.setText("QUANTITY");
        cDLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cDLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtQuantity;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void onInitializeView(TTransactionDetail transactionDetail1) {
        txtQuantity.requestFocus();
    }

    @Override
    protected void onAccept(TTransactionDetail transactionDetail1) {
        transactionDetail1.setQuantity(txtQuantity.getCValue());

        TransactionDetailUtil.calculateNetValues(transactionDetail1);
    }

    @Override
    protected boolean isValueAcceptable() {
        return txtQuantity.getCValue() > 0;
    }

    @Override
    public void resetView() {
        txtQuantity.resetValue();
    }
}
