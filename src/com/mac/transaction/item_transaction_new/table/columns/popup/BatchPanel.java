/*
 *  BatchPanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 8:19:34 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns.popup;

import com.mac.af.component.derived.input.ComponentFocusKeyEvent;
import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.transaction.item_transaction_new.TransactionDetailUtil;
import com.mac.transaction.item_transaction_new.object.MItem;
import com.mac.transaction.item_transaction_new.object.MItemBatch;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.PopupPanel;
import com.mac.transaction.transaction_registration.PriceTypes;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mohan
 */
public class BatchPanel extends PopupPanel {

    /**
     * Creates new form BatchPanel
     */
    public BatchPanel(RTransactionType transactionType) {
        this.transactionType = transactionType;

        initComponents();

        initOthers();
    }

    private void find() {
        Integer batchNo;
        try {
            batchNo = Integer.valueOf(txtBatch.getText());
        } catch (NumberFormatException numberFormatException) {
            batchNo = 0;
        }

        ((TableRowSorter) tblBatches.getRowSorter()).setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, batchNo, 0));

        if (tblBatches.getRowCount() > 0) {
            tblBatches.setSelectedRow(0);
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        //create model
        CTableColumn batchNoColumn = new CTableColumn("Batch No", "batchNumber");
        CTableColumn priceColumn;
        switch (transactionType.getPriceType()) {
            case PriceTypes.SALES_PRICE:
                priceColumn = new CTableColumn("Sales Price", "salePrice");
                break;
            case PriceTypes.LAST_SALE_PRICE:
                priceColumn = new CTableColumn("Last Sales Price", "lastSalesPrice");
                break;
            case PriceTypes.WHOLE_SALE_PRICE:
                priceColumn = new CTableColumn("Whole Sale Price", "wholeSalePrice");
                break;
            case PriceTypes.COST_PRICE:
                priceColumn = new CTableColumn("Cost Price", "costPrice");
                break;
            default:
                priceColumn = null;
        }

        CTableColumn[] tableColumns;
        if (priceColumn != null) {
            tableColumns = new CTableColumn[]{
                batchNoColumn,
                priceColumn
            };
        } else {
            tableColumns = new CTableColumn[]{
                batchNoColumn
            };
        }

        CTableModel<MItemBatch> tableModel = new CTableModel<>(tableColumns);
        tblBatches.setCModel(tableModel);

        tblBatches.setAutoCreateRowSorter(true);


        //keys
        txtBatch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                find();

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    accept();
                }
            }
        });

        ComponentFocusKeyEvent.remove(txtBatch);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        txtBatch = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBatches = new com.mac.af.component.derived.input.table.CITable();

        cDLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cDLabel1.setText("BATCH");
        cDLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        txtBatch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        tblBatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblBatches);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cDLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBatch, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.mac.af.component.derived.input.table.CITable tblBatches;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtBatch;
    // End of variables declaration//GEN-END:variables
    private RTransactionType transactionType;

    public void fireOInitializeView(TTransactionDetail transactionDetail1) {
        onInitializeView(transactionDetail1);
    }

    public void fireOnAccept(TTransactionDetail transactionDetail1) {
        onAccept(transactionDetail1);
    }

    @Override
    protected void onInitializeView(TTransactionDetail transactionDetail1) {
        txtBatch.requestFocus();

        if (transactionDetail1 != null ? transactionDetail1.getMItem() != null : false) {
            MItem item = transactionDetail1.getMItem();
            List<MItemBatch> itemBatchs = new ArrayList<>(item.getMItemBatchs());
            Collections.sort(itemBatchs, new Comparator<MItemBatch>() {
                @Override
                public int compare(MItemBatch o1, MItemBatch o2) {
                    return Integer.compare(o2.getBatchNumber(), o1.getBatchNumber());
                }
            });
            tblBatches.setCValue(itemBatchs);

            MItemBatch lastBatch = null;
            Iterator<MItemBatch> iterator = item.getMItemBatchs().iterator();
            while (iterator.hasNext()) {
                MItemBatch batch = iterator.next();
                lastBatch = lastBatch != null
                        ? (batch.getBatchNumber() > lastBatch.getBatchNumber()) ? batch : lastBatch
                        : batch;
            }
            if (lastBatch != null) {
                txtBatch.setCValue(lastBatch.getBatchNumber());
            }
        }
    }

    @Override
    protected void onAccept(TTransactionDetail transactionDetail1) {
        int i = tblBatches.getRowSorter().convertRowIndexToModel(
                
                !tblBatches.getSelectionModel().isSelectionEmpty()
                ?tblBatches.getSelectedRow()
                :0
                );

        MItemBatch itemBatch = (MItemBatch) tblBatches.getValueAt(i);

        transactionDetail1.setMItemBatch(itemBatch);

        switch (transactionType.getPriceType()) {
            case PriceTypes.SALES_PRICE:
                transactionDetail1.setPrice(itemBatch.getSalePrice());
                break;
            case PriceTypes.LAST_SALE_PRICE:
                transactionDetail1.setPrice(itemBatch.getLastSalesPrice());
                break;
            case PriceTypes.WHOLE_SALE_PRICE:
                transactionDetail1.setPrice(itemBatch.getWholeSalePrice());
                break;
            case PriceTypes.COST_PRICE:
                transactionDetail1.setPrice(itemBatch.getCostPrice());
                break;
            default:
                transactionDetail1.setPrice(0.0);
        }

        TransactionDetailUtil.calculateNetValues(transactionDetail1);
    }

    @Override
    protected boolean isValueAcceptable() {
        return !tblBatches.getSelectionModel().isSelectionEmpty();
    }

    @Override
    public void resetView() {
        txtBatch.resetValue();
        tblBatches.resetValue();
    }
}
