/*
 *  PopupPanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 25, 2015, 11:29:58 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.transaction.item_transaction_new.table.columns;

import com.mac.af.core.ApplicationException;
import com.mac.transaction.item_transaction_new.object.TTransactionDetail;
import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 *
 * @author mohan
 */
public abstract class PopupPanel extends JPanel {

    private TTransactionDetail transactionDetail;
    private Runnable nextStepRunnable;

    public PopupPanel() {
        setBorder(new LineBorder(UIManager.getColor("windowBorder")));
    }

    @SuppressWarnings("empty-statement")
    public static JPopupMenu getIncludingPopup(Component component) throws ApplicationException {
        if (!(component instanceof JPopupMenu)) {
            //wait until component showing
            while (!component.isShowing()) {
            }
            while (component.getParent() == null
                    ? false
                    : !((component = component.getParent()) instanceof JPopupMenu));
        }

        if (component != null) {
            if (component instanceof JPopupMenu) {
                return ((JPopupMenu) component);
            }
        }

        throw new ApplicationException("The component is not related to a Popup Menu");
    }

    private void dispose() {
        try {
            JPopupMenu popupMenu = getIncludingPopup(this);
            popupMenu.setVisible(false);
        } catch (ApplicationException ex) {
            Logger.getLogger(PopupPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initilizeView(TTransactionDetail transactionDetail) {
        this.transactionDetail = transactionDetail;

        onInitializeView(transactionDetail);
    }

    public void accept() {
        if (isValueAcceptable()) {
            onAccept(transactionDetail);
            dispose();

            if (nextStepRunnable != null) {
                nextStepRunnable.run();
            }
        }
    }

    public void setNextStepRunnable(Runnable runnable) {
        this.nextStepRunnable = runnable;
    }

    protected abstract void onInitializeView(TTransactionDetail transactionDetail1);

    protected abstract void onAccept(TTransactionDetail transactionDetail1);

    protected abstract boolean isValueAcceptable();

    public abstract void resetView();
}
