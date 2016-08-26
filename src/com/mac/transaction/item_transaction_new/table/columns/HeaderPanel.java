/*
 *  HeaderPanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 2:26:20 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns;

import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import javax.swing.JPanel;

/**
 *
 * @author mohan
 */
public abstract class HeaderPanel extends JPanel {

    public void setTransactionDetail(TTransactionDetail transactionDetail) {
        initUI(transactionDetail);
    }

    protected abstract void initUI(TTransactionDetail transactionDetail);

    public abstract void resetUI();
}
