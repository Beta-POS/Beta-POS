/*
 *  SerialPanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 8:24:21 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns.popup;

import com.mac.af.component.derived.input.ComponentFocusKeyEvent;
import com.mac.af.component.model.list.CListModel;
import com.mac.af.core.message.mOptionPane;
import com.mac.transaction.item_transaction_new.SerialMovementStatus;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.item_transaction_new.object.TSerialMovement;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.PopupPanel;
import com.mac.zsystem.util.hash.HashUtil;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author mohan
 */
public class SerialPanel extends PopupPanel {

    /**
     * Creates new form SerialPanel
     */
    public SerialPanel(RTransactionType transactionType) {
        this.transactionType = transactionType;
        initComponents();

        initOthers();
    }

    private void addSerial() {
        String serial = txtSerialNo.getCValue();
        if (!listModel.getListData().contains(serial)) {
            listModel.addElement(0, serial);
            txtSerialNo.selectAll();
        } else {
            mOptionPane.showMessageDialog(this, "The serial number " + serial + " is alreary exists.", "Serial Number", mOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editSerial() {
        if (!lstSerial.isSelectionEmpty()) {
            String serial = (String) lstSerial.getSelectedValue();
            listModel.removeElement(serial);
            txtSerialNo.setCValue(serial);
            txtSerialNo.requestFocus();
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        listModel = new CListModel();
        lstSerial.setCModel(listModel);

        ComponentFocusKeyEvent.remove(txtSerialNo);

        txtSerialNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (transactionDetail.getQuantity() == lstSerial.getCValue().size()) {
                    } else {
                        addSerial();
                    }
                    accept();
                }
            }
        });
        lstSerial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editSerial();
            }
        });
        
        lstSerial.setCellRenderer(new ListRenderer());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        txtSerialNo = new com.mac.af.component.derived.input.textfield.CIStringField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstSerial = new com.mac.af.component.derived.input.list.CIList();

        cDLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cDLabel1.setText("SERIAL NUMBERS");
        cDLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jScrollPane1.setViewportView(lstSerial);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSerialNo, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(cDLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSerialNo, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.mac.af.component.derived.input.list.CIList lstSerial;
    private com.mac.af.component.derived.input.textfield.CIStringField txtSerialNo;
    // End of variables declaration//GEN-END:variables
    private TTransactionDetail transactionDetail;
    private RTransactionType transactionType;
    private CListModel<String> listModel;

    @Override
    protected void onInitializeView(TTransactionDetail transactionDetail1) {
        this.transactionDetail = transactionDetail1;

        txtSerialNo.requestFocus();
    }

    @Override
    protected void onAccept(TTransactionDetail transactionDetail1) {
//        transactionDetail1.getTSerialMovements().add(null);

        Set<TSerialMovement> serialMovements = new HashSet<>();
        for (String string : listModel.getListData()) {
//            TSerialNumber number = new TSerialNumber();
//            number.setSerialNumber(string);

            TSerialMovement serialMovement = new TSerialMovement();
            serialMovement.setBranch(HashUtil.getBranch());
            serialMovement.setMItem(transactionDetail1.getMItem());
            serialMovement.setSerialNumber(string);
            serialMovement.setTTransactionDetail(transactionDetail);
            serialMovement.setMStoreByInStore(transactionDetail1.getMStoreByInStore());
            serialMovement.setMStoreByInStore(transactionDetail1.getMStoreByOutStore());
            serialMovement.setStatus(SerialMovementStatus.ACTIVE);


            serialMovements.add(serialMovement);
        }

        transactionDetail1.setTSerialMovements(serialMovements);
    }

    @Override
    protected boolean isValueAcceptable() {
        return transactionDetail.getQuantity() == lstSerial.getCValue().size();
    }

    @Override
    public void resetView() {
        txtSerialNo.resetValue();
        lstSerial.resetValue();
    }

    private class ListRenderer
            extends JCheckBox
            implements ListCellRenderer<String> {

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);

            return this;
        }
    }
}