/*
 *  CodePanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 24, 2015, 11:07:56 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns.popup;

import com.mac.af.component.derived.input.ComponentFocusKeyEvent;
import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.transaction.item_transaction_new.SERItemTransaction;
import com.mac.transaction.item_transaction_new.object.MItem;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.PopupPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mohan
 */
public class CodePanel extends PopupPanel implements TableCellRenderer {

    private SERItemTransaction serItemTransaction;
    //
    private static final int FIND_BY_CODE = 0;
    private static final int FIND_BY_NAME = 1;
    private static final int FIND_BY_BARCODE = 2;
    private int findBy = FIND_BY_CODE;

    /**
     * Creates new form CodePanel
     */
    public CodePanel(SERItemTransaction itemTransaction) {
        this.serItemTransaction = itemTransaction;

        initComponents();
        initOthers();

        refreshItems();
    }

    private void findItem() {
        String text = convertGlobToRegEx(txtText.getText());

        ((TableRowSorter) tblItem.getRowSorter()).setRowFilter(RowFilter.regexFilter(text, Pattern.CASE_INSENSITIVE, findBy));
        if (!tblItem.isEmpty()) {
            tblItem.setSelectedRow(0);
        }
    }

    private String convertGlobToRegEx(String line) {
        line = line.trim();
        int strLen = line.length();
        StringBuilder sb = new StringBuilder(strLen);
        // Remove beginning and ending * globs because they're useless
        if (line.startsWith("*")) {
            line = line.substring(1);
            strLen--;
        }
        if (line.endsWith("*")) {
            line = line.substring(0, strLen - 1);
            strLen--;
        }
        boolean escaping = false;
        int inCurlies = 0;
        for (char currentChar : line.toCharArray()) {
            switch (currentChar) {
                case '*':
                    if (escaping) {
                        sb.append("\\*");
                    } else {
                        sb.append(".*");
                    }
                    escaping = false;
                    break;
                case '?':
                    if (escaping) {
                        sb.append("\\?");
                    } else {
                        sb.append('.');
                    }
                    escaping = false;
                    break;
                case '.':
                case '(':
                case ')':
                case '+':
                case '|':
                case '^':
                case '$':
                case '@':
                case '%':
                    sb.append('\\');
                    sb.append(currentChar);
                    escaping = false;
                    break;
                case '\\':
                    if (escaping) {
                        sb.append("\\\\");
                        escaping = false;
                    } else {
                        escaping = true;
                    }
                    break;
                case '{':
                    if (escaping) {
                        sb.append("\\{");
                    } else {
                        sb.append('(');
                        inCurlies++;
                    }
                    escaping = false;
                    break;
                case '}':
                    if (inCurlies > 0 && !escaping) {
                        sb.append(')');
                        inCurlies--;
                    } else if (escaping) {
                        sb.append("\\}");
                    } else {
                        sb.append("}");
                    }
                    escaping = false;
                    break;
                case ',':
                    if (inCurlies > 0 && !escaping) {
                        sb.append('|');
                    } else if (escaping) {
                        sb.append("\\,");
                    } else {
                        sb.append(",");
                    }
                    break;
                default:
                    escaping = false;
                    sb.append(currentChar);
            }
        }
        return "(?i)^" + sb.toString();
    }

    private void refreshItems() {
        tblItem.setCValue(serItemTransaction.getItems());
    }

    private void setFindBy(int findBy) {
        switch (findBy) {
            case FIND_BY_CODE:
                lblFindType.setText("CODE");
                this.findBy = findBy;
                break;
            case FIND_BY_NAME:
                lblFindType.setText("NAME");
                this.findBy = findBy;
                break;
            case FIND_BY_BARCODE:
                lblFindType.setText("BARCODE");
                this.findBy = findBy;
                break;
            default:
                throw new AssertionError();
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        CTableModel<MItem> tableModel = new CTableModel<>(
                new CTableColumn("CODE", "code"),
                new CTableColumn("NAME", "name"),
                new CTableColumn("BARCODE", "barcode"));

        tblItem.setCModel(tableModel);
//        tblItem.setFocusable(false);
        tblItem.setAutoCreateRowSorter(true);
        tblItem.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        tblItem.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MItem item = ((MItem) tblItem.getSelectedValue());
                switch (findBy) {
                    case FIND_BY_CODE:
                        txtText.setText(item.getCode());
                        break;
                    case FIND_BY_NAME:
                        txtText.setText(item.getName());
                        break;
                    case FIND_BY_BARCODE:
                        txtText.setText(item.getBarcode());
                        break;
                    default:
                        throw new AssertionError();
                }
                txtText.requestFocus();
            }
        });

        txtText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                findItem();

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = txtText.getText();
                    switch (text) {
                        case ".":
                            setFindBy(FIND_BY_CODE);
                            txtText.setCValue("");
                            findItem();
                            break;
                        case "..":
                            setFindBy(FIND_BY_NAME);
                            txtText.setCValue("");
                            findItem();
                            break;
                        case "...":
                            setFindBy(FIND_BY_BARCODE);
                            txtText.setCValue("");
                            findItem();
                            break;
                        default:
                            accept();
                    }

                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    tblItem.requestFocus();
                }
            }
        });
        tblItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!tblItem.getSelectionModel().isSelectionEmpty()) {
                    int i = tblItem.getRowSorter().convertRowIndexToModel(tblItem.getSelectedRow());

                    MItem item = (MItem) tblItem.getValueAt(i);
                    txtText.setCValue(item.getCode());
                }
            }
        });

        ComponentFocusKeyEvent.remove(txtText);
        setFindBy(FIND_BY_CODE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFindType = new com.mac.af.component.derived.display.label.CDLabel();
        txtText = new com.mac.af.component.derived.input.textfield.CIStringField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItem = new com.mac.af.component.derived.input.table.CITable();

        lblFindType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFindType.setText("CODE");
        lblFindType.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
            .addComponent(lblFindType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblFindType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.mac.af.component.derived.display.label.CDLabel lblFindType;
    private com.mac.af.component.derived.input.table.CITable tblItem;
    private com.mac.af.component.derived.input.textfield.CIStringField txtText;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }

    @Override
    protected void onInitializeView(TTransactionDetail transactionDetail1) {
        txtText.selectAll();
        txtText.requestFocus();
    }

    @Override
    protected void onAccept(TTransactionDetail transactionDetail1) {
        int i = tblItem.getRowSorter().convertRowIndexToModel(tblItem.getSelectedRow());

        MItem item = (MItem) tblItem.getValueAt(i);

        transactionDetail1.setMItem(item);
        transactionDetail1.setMItemBatch(null);
        transactionDetail1.setPrice(null);
        transactionDetail1.setQuantity(null);
        transactionDetail1.setDiscount(null);
        transactionDetail1.setNetValue(null);
    }

    @Override
    protected boolean isValueAcceptable() {
        return tblItem.getRowSorter().getViewRowCount() != 0;
    }

    @Override
    public void resetView() {
        txtText.resetValue();
        findItem();
    }
}
