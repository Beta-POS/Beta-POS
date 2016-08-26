/*
 *  SettlementTableModel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on May 15, 2015, 1:13:29 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.account_transaction;

import com.mac.transaction.account_transaction.object.TTransactionSettlement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author mohan
 */
public class SettlementTableModel implements TableModel {

    private List<TTransactionSettlement> settlements = new ArrayList<>();
    private List<TableModelListener> tableModelListeners = new ArrayList<>();

    public void setSettlements(List<TTransactionSettlement> settlements) {
        this.settlements = settlements;
        fireTableModels();
    }

    public List<TTransactionSettlement> getSettlements() {
        return settlements;
    }

    public void setSettlementAmount(double amount) {
        double settlementAmount;
        for (TTransactionSettlement transactionSettlement : settlements) {
            settlementAmount = Math.min(amount, transactionSettlement.getTTransactionSummaryBySettleTransactionSummary().getBalanceAmount());
            transactionSettlement.setAmount(settlementAmount);
            amount = amount - settlementAmount;
        }
        fireTableModels();
    }

    public double getBalanceAmount() {
        double totalBalance = 0.0;
        double settlement = getSettlementAmount();
        for (TTransactionSettlement transactionSettlement : settlements) {
            totalBalance = totalBalance + transactionSettlement.getTTransactionSummaryBySettleTransactionSummary().getBalanceAmount();
        }

        return totalBalance - settlement;
    }

    public double getSettlementAmount() {
        double settlementAmount = 0.0;

        for (TTransactionSettlement settlement : settlements) {
            settlementAmount += settlement.getAmount();
        }

        return settlementAmount;
    }

    @Override
    public int getRowCount() {
        return settlements != null ? settlements.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "No";
            case 1:
                return "Date";
            case 2:
                return "Balance";
            case 3:
                return "Settlement";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return Date.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TTransactionSettlement settlement = settlements.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return settlement.getTTransactionSummaryBySettleTransactionSummary().getTransactionNo();
            case 1:
                return settlement.getTTransactionSummaryBySettleTransactionSummary().getTransactionDate();
            case 2:
                return settlement.getTTransactionSummaryBySettleTransactionSummary().getBalanceAmount();
            case 3:
                return settlement.getAmount();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TTransactionSettlement settlement = settlements.get(rowIndex);
        if (columnIndex == 3) {
            double balance = settlement.getTTransactionSummaryBySettleTransactionSummary().getBalanceAmount();;
            Double amount = (Double) aValue;
            if (amount > balance) {
                settlement.setAmount(balance);
            } else {
                settlement.setAmount((Double) aValue);
            }
        }
        fireTableModels();
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        tableModelListeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        tableModelListeners.remove(l);
    }

    public void fireTableModels() {
        TableModelEvent event = new TableModelEvent(this);
        for (TableModelListener tableModelListener : tableModelListeners) {
            tableModelListener.tableChanged(event);
        }
    }
}
