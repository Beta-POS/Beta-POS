/*
 *  BatchHeader.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 1:52:05 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns.header;

import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.HeaderPanel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mohan
 */
public class BatchHeader extends HeaderPanel implements TableCellRenderer {

    /**
     * Creates new form BatchHeader
     */
    public BatchHeader() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBatchNo = new com.mac.af.component.derived.display.label.CDLabel();
        txtBatchNo = new com.mac.af.component.derived.input.textfield.CIIntegerField();

        lblBatchNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBatchNo.setText("BATCH");

        txtBatchNo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBatchNo.setValueEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBatchNo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBatchNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBatchNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel lblBatchNo;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtBatchNo;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }

    @Override
    protected void initUI(TTransactionDetail transactionDetail) {
        if (transactionDetail != null ? transactionDetail.getMItemBatch() != null : false) {
            txtBatchNo.setCValue(transactionDetail.getMItemBatch().getBatchNumber());
        } else {
            txtBatchNo.setCValue(0);
        }
    }

    @Override
    public void resetUI() {
        txtBatchNo.resetValue();
    }
}
