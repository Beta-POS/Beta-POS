/*
 *  TableUtil.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 8:45:00 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table;

import com.mac.af.component.derived.input.table.CITable;
import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.ApplicationException;
import com.mac.af.core.concurrent.CExecutable;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.environment.cpanel.CPanel;
import com.mac.af.core.message.mOptionPane;
import com.mac.transaction.item_transaction_new.SERItemTransaction;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import com.mac.transaction.item_transaction_new.table.columns.HeaderPanel;
import com.mac.transaction.item_transaction_new.table.columns.PopupPanel;
import com.mac.transaction.item_transaction_new.table.columns.header.BatchHeader;
import com.mac.transaction.item_transaction_new.table.columns.header.DiscountHeader;
import com.mac.transaction.item_transaction_new.table.columns.header.CodeHeader;
import com.mac.transaction.item_transaction_new.table.columns.header.NameHeader;
import com.mac.transaction.item_transaction_new.table.columns.header.NetValueHeader;
import com.mac.transaction.item_transaction_new.table.columns.header.PriceHeader;
import com.mac.transaction.item_transaction_new.table.columns.header.QuantityHeader;
import com.mac.transaction.item_transaction_new.table.columns.header.SerialHeader;
import com.mac.transaction.item_transaction_new.table.columns.popup.BatchPanel;
import com.mac.transaction.item_transaction_new.table.columns.popup.DiscountPanel;
import com.mac.transaction.item_transaction_new.table.columns.popup.CodePanel;
import com.mac.transaction.item_transaction_new.table.columns.popup.NetValuePanel;
import com.mac.transaction.item_transaction_new.table.columns.popup.PricePanel;
import com.mac.transaction.item_transaction_new.table.columns.popup.QuantityPanel;
import com.mac.transaction.item_transaction_new.table.columns.popup.SerialPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mohan
 */
public final class TableUtil {

    public static final String ITEM_CODE_COLUMN = "ITEM_CODE";
    public static final String ITEM_NAME_COLUMN = "ITEM_NAME";
    public static final String SERIAL_NO_COLUMN = "SERIAL_NO";
    public static final String BATCH_NO_COLUMN = "BATCH_NO";
    public static final String PRICE_COLUMN = "PRICE";
    public static final String QUANTITY_COLUMN = "QUANTITY";
    public static final String DISCOUNT_COLUMN = "DISCOUNT";
    public static final String NET_VALUE_COLUMN = "NET_VALUE";
    //table columns
    private List<CTableColumn> tableColumns;
    private List<HeaderPanel> headerPanels;
    private List<PopupPanel> popupPanels;
    //popup ui
    private JPopupMenu popupContainer;
    //panels
    private CodePanel itemPanel;
    private PricePanel pricePanel;
    private SerialPanel serialPanel;
    private BatchPanel batchPanel;
    private QuantityPanel quantityPanel;
    private DiscountPanel discountPanel;
    private NetValuePanel netValuePanel;
    //transaction settings
    private CITable table;
    private SERItemTransaction serItemTransaction;
    private RTransactionType transactionType;
    private Runnable itemAddedRunnable;
    //runtime
    private TTransactionDetail transactionDetail;
    private String currentPopupStep;

    public TableUtil(SERItemTransaction serItemTransaction, RTransactionType transactionType, final CITable table) {
        //transaction settings
        this.serItemTransaction = serItemTransaction;
        this.transactionType = transactionType;
        this.table = table;

        //key event

        CExecutable<Void> executable = new CExecutable<Void>("TRANSACTION KEY BIND", CPanel.GLOBAL) {
            @Override
            public Void execute() throws Exception {
                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showPopupStep(currentPopupStep);
                    }
                };

                Action editAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TTransactionDetail transactionDetail = (TTransactionDetail) table.getSelectedValue();


                        if (transactionDetail != null) {
                            int q = mOptionPane.showConfirmDialog(table.getParent(), "Do you sure want to edit item '" + transactionDetail.getMItem().getName() + "' ?", "EDIT ITEM", mOptionPane.YES_NO_OPTION);

                            if (q == mOptionPane.YES_OPTION) {
                                TableUtil.this.transactionDetail = transactionDetail;
                                table.getCValue().remove(transactionDetail);
                                repaintHeaders();

                                if (itemAddedRunnable != null) {
                                    itemAddedRunnable.run();
                                }
                            }
                        }
                    }
                };
                try {
                    CPanel panel = CPanel.getIncludingPanel(TableUtil.this.table);

                    panel.getActionMap().put("popupAction", action);
                    panel.getActionMap().put("editAction", editAction);
                    InputMap inputMap = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
                    inputMap.put(KeyStroke.getKeyStroke("F1"), "popupAction");
                    inputMap.put(KeyStroke.getKeyStroke("F12"), "editAction");
                } catch (ApplicationException ex) {
                    Logger.getLogger(TableUtil.class.getName()).log(Level.SEVERE, null, ex);
                }

                return null;
            }
        };

        CApplication.getExecutionManager().execute(executable);


        //next step runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                showNextPopupStep();
            }
        };

        //init panels
        popupPanels = new ArrayList<>();

        itemPanel = new CodePanel(serItemTransaction);
        itemPanel.setNextStepRunnable(runnable);
        popupPanels.add(itemPanel);

        pricePanel = new PricePanel(transactionType);
        pricePanel.setNextStepRunnable(runnable);
        popupPanels.add(pricePanel);

//        serialPanel = new SerialPanel(transactionType);
//        serialPanel.setNextStepRunnable(runnable);
//        popupPanels.add(serialPanel);

//        batchPanel = new BatchPanel(transactionType);
//        batchPanel.setNextStepRunnable(runnable);
//        popupPanels.add(batchPanel);

        quantityPanel = new QuantityPanel();
        quantityPanel.setNextStepRunnable(runnable);
        popupPanels.add(quantityPanel);

//        discountPanel = new DiscountPanel(transactionType);
//        discountPanel.setNextStepRunnable(runnable);
//        popupPanels.add(discountPanel);

        netValuePanel = new NetValuePanel(transactionType);
        netValuePanel.setNextStepRunnable(runnable);
        popupPanels.add(netValuePanel);

        setTableModel();

        newTransactionSummary();
    }

    public void setItemAddedRunnable(Runnable itemAddedRunnable) {
        this.itemAddedRunnable = itemAddedRunnable;
    }

    public void setTableModel() {
        tableColumns = new ArrayList<>();
        headerPanels = new ArrayList<>();

        //code
        CTableColumn codeCColumn = new CTableColumn(ITEM_CODE_COLUMN, "MItem", "code");
        tableColumns.add(codeCColumn);
        headerPanels.add(new CodeHeader());

        //name
        CTableColumn nameCColumn = new CTableColumn(ITEM_NAME_COLUMN, "MItem", "name");
        tableColumns.add(nameCColumn);
        headerPanels.add(new NameHeader());

        //batch column
//        CTableColumn batchCColumn = new CTableColumn(BATCH_NO_COLUMN, "MItemBatch", "batchNumber");
//        tableColumns.add(batchCColumn);
//        headerPanels.add(new BatchHeader());

        //price
        CTableColumn priceCColumn = new CTableColumn(PRICE_COLUMN, "price");
        tableColumns.add(priceCColumn);
        headerPanels.add(new PriceHeader());

        //quantity
        CTableColumn quantityCColumn = new CTableColumn(QUANTITY_COLUMN, "quantity");
        tableColumns.add(quantityCColumn);
        headerPanels.add(new QuantityHeader());

        //serial column
//        CTableColumn serialCColumn = new CTableColumn(SERIAL_NO_COLUMN, "MItem");
//        tableColumns.add(serialCColumn);
//        headerPanels.add(new SerialHeader());

        //discount
//        CTableColumn discountCColumn = new CTableColumn(DISCOUNT_COLUMN, "discount");
//        tableColumns.add(discountCColumn);
//        headerPanels.add(new DiscountHeader());

        //net value
        CTableColumn netValueCColumn = new CTableColumn(NET_VALUE_COLUMN, "netValue");
        tableColumns.add(netValueCColumn);
        headerPanels.add(new NetValueHeader());

        CTableColumn[] columns = new CTableColumn[tableColumns.size()];
        columns = tableColumns.toArray(columns);

        CTableModel<TTransactionDetail> tableModel = new CTableModel<>(columns);
        table.setCModel(tableModel);


        //POPUP MENU
        //popup
        popupContainer = new JPopupMenu();
        popupContainer.getActionMap().clear();

        popupContainer.setBorder(new MatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
        popupContainer.setLightWeightPopupEnabled(true);

        //events
        final JTableHeader header = table.getTableHeader();
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editColumnAt(header, e.getPoint());
            }
        });
        table.getActionMap().clear();
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                TTransactionDetail transactionDetail = (TTransactionDetail) table.getSelectedValue();
//
//
//                if (transactionDetail != null) {
//                    int q = mOptionPane.showConfirmDialog(table.getParent(), "Do you sure want to edit item '" + transactionDetail.getMItem().getName() + "' ?", "EDIT ITEM", mOptionPane.YES_NO_OPTION);
//
//                    if (q == mOptionPane.YES_OPTION) {
//                        TableUtil.this.transactionDetail = transactionDetail;
//                        table.getCValue().remove(transactionDetail);
//                        repaintHeaders();
//
//                        if (itemAddedRunnable != null) {
//                            itemAddedRunnable.run();
//                        }
//                    }
//                }
//            }
//        });


        //table Headers

//        double widthSum = 0.0;
//        System.out.println(table.getWidth());
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        for (HeaderPanel headerPanel : headerPanels) {
//            widthSum = widthSum+headerPanel.getPreferredSize().getWidth();
//        }
//        double tableWidth = table.getParent().getParent().getPreferredSize().getWidth();
//        System.out.println(table.getWidth());

        int headerIndex = 0;
//        double width = 0.0;
        for (HeaderPanel headerPanel : headerPanels) {
            header.getColumnModel().getColumn(headerIndex).setHeaderRenderer((TableCellRenderer) headerPanel);
//            width = (headerPanel.getPreferredSize().getWidth() * tableWidth / widthSum);
//            
//            header.getColumnModel().getColumn(headerIndex).setPreferredWidth((int) width);
//            System.out.println(
//                    "W:"+width
//                    +"\tCW:" + headerPanel.getPreferredSize().getWidth()
//                    + "\tTW:" + tableWidth
//                    + "\tWS:" + widthSum);

//            System.out.println(headerIndex + "-" + (int) (headerPanel.getPreferredSize().getWidth() * tableWidth / widthSum));
            headerIndex++;
        }

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        header.getColumnModel().getColumn(0).setPreferredWidth(150);
        header.getColumnModel().getColumn(1).setPreferredWidth(300);
//        header.getColumnModel().getColumn(2).setPreferredWidth(100);
        header.getColumnModel().getColumn(2).setPreferredWidth(150);
        header.getColumnModel().getColumn(3).setPreferredWidth(150);
        header.getColumnModel().getColumn(4).setPreferredWidth(150);
//        header.getColumnModel().getColumn(5).setPreferredWidth(150);

    }

    private void editColumnAt(JTableHeader header, Point p) {
        int columnIndex = header.columnAtPoint(p);

        showPopupAt(header, columnIndex);
    }

    private void showPopupAt(JTableHeader header, int columnIndex) {
        if (columnIndex != -1) {
            String columnIdentifier = tableColumns.get(columnIndex).getColumnName();
            switch (columnIdentifier) {
                case ITEM_CODE_COLUMN:
                    showPopup(itemPanel, header, columnIndex);
                    break;
                case BATCH_NO_COLUMN:
                    showPopup(batchPanel, header, columnIndex);
                    break;
                case PRICE_COLUMN:
                    showPopup(pricePanel, header, columnIndex);
                    break;
                case QUANTITY_COLUMN:
                    showPopup(quantityPanel, header, columnIndex);
                    break;
                case DISCOUNT_COLUMN:
                    showPopup(discountPanel, header, columnIndex);
                    break;
                case NET_VALUE_COLUMN:
                    showPopup(netValuePanel, header, columnIndex);
                    break;
            }
        } else {
            String next = getNextPopupColumn(currentPopupStep);

            switch (next) {
                case SERIAL_NO_COLUMN:
                    showPopup(serialPanel, header, columnIndex);
                    System.out.println("SERIAL");
                    break;
            }
        }
    }

    private void showPopup(JPanel panel, JTableHeader header, int columnIndex) {
        popupContainer.removeAll();
        popupContainer.add(panel);


        if (columnIndex != -1) {
            Rectangle columnRectangle = header.getHeaderRect(columnIndex);
            if (panel instanceof CodePanel) {
                popupContainer.setPreferredSize(panel.getPreferredSize());
            } else {
                popupContainer.setPreferredSize(new Dimension(columnRectangle.width, (int) panel.getPreferredSize().getHeight()));
            }
            popupContainer.show(header, columnRectangle.x, 0);

            currentPopupStep = tableColumns.get(columnIndex).getColumnName();
        } else {
            popupContainer.setPreferredSize(panel.getPreferredSize());

            int x = (int) header.getWidth() / 2 - popupContainer.getWidth() / 2;
            int y = (int) table.getHeight() / 2 - popupContainer.getHeight() / 2;

            popupContainer.show(header, x, y);

            if (panel instanceof SerialPanel) {
                currentPopupStep = SERIAL_NO_COLUMN;
            }
        }

        if (panel instanceof PopupPanel) {
            ((PopupPanel) panel).initilizeView(transactionDetail);
        }
    }

    private String getNextPopupColumn(String currentStep) {
        switch (currentStep) {
            case ITEM_CODE_COLUMN:
//                if (transactionDetail.getMItem().isBatch()) {
//                    return BATCH_NO_COLUMN;
//                } else {
//                    batchPanel.fireOInitializeView(transactionDetail);
//
//                    try {
//                        Thread.sleep(100);
//                        batchPanel.fireOnAccept(transactionDetail);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(TableUtil.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    return PRICE_COLUMN;
//                }
                return PRICE_COLUMN;
            case BATCH_NO_COLUMN:
                return PRICE_COLUMN;
            case PRICE_COLUMN:
                return QUANTITY_COLUMN;
            case QUANTITY_COLUMN:
//                if (transactionDetail.getMItem().isSerial()) {
//                    return SERIAL_NO_COLUMN;
//                } else {
//                    return DISCOUNT_COLUMN;
//                }
//            case SERIAL_NO_COLUMN:
//                return DISCOUNT_COLUMN;
//            case DISCOUNT_COLUMN:
                return NET_VALUE_COLUMN;
            case NET_VALUE_COLUMN:
                return ITEM_CODE_COLUMN;
            default:
                return null;
        }
    }

    private int getColumnIndex(String identifier) {
        int index = 0;
        for (CTableColumn tableColumn : tableColumns) {
            if (tableColumn.getColumnName().equals(identifier)) {
                return index;
            }
            index++;
        }
        System.out.println("COLUMN NOT FOUND: " + identifier);
        return -1;
    }

    private void showNextPopupStep() {
        repaintHeaders();

        String next = getNextPopupColumn(currentPopupStep);
        showPopupStep(next);
    }

    private void repaintHeaders() {
        //refresh header values
        for (HeaderPanel headerPanel : headerPanels) {
            headerPanel.setTransactionDetail(transactionDetail);
        }
    }

    private void showPopupStep(String popupStep) {
        int nextPopupColumnId = getColumnIndex(popupStep);

        if (currentPopupStep.equals(NET_VALUE_COLUMN)) {
            addItem(transactionDetail);
        }

        showPopupAt(table.getTableHeader(), nextPopupColumnId);
    }

    //INVOICE OPERATIONS
    public void newTransactionSummary() {
        List<TTransactionDetail> transactionDetails = new ArrayList<>();
        table.setCValue(transactionDetails);

        newTransactionDetail();
    }

    private void newTransactionDetail() {
        currentPopupStep = ITEM_CODE_COLUMN;
        transactionDetail = new TTransactionDetail();

        //reset headers
        for (HeaderPanel headerPanel : headerPanels) {
            headerPanel.resetUI();
        }

        //reset panels
        for (PopupPanel popupPanel : popupPanels) {
            popupPanel.resetView();
        }
    }

    private void addItem(TTransactionDetail transactionDetail1) {
        transactionDetail1.setAvaragePrice(0.0);
        transactionDetail1.setExpireDate(new Date());
        transactionDetail1.setMStoreByInStore(null);
        transactionDetail1.setMStoreByOutStore(null);

        table.addData(transactionDetail1);

        if (itemAddedRunnable != null) {
            itemAddedRunnable.run();
        }

        newTransactionDetail();
    }
}
