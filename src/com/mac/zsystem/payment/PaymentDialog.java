/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.zsystem.payment;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.panel.dialog.object_creator_dialog.ObjectCreatorDialog;
import com.mac.zsystem.payment.hibernate.MBankBranch;
import com.mac.zsystem.payment.hibernate.TCheque;
import com.mac.zsystem.payment.object.Payment;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc-n
 */
public class PaymentDialog extends ObjectCreatorDialog<Payment> {

    private PCPayments payments;
    private HibernateDatabaseService databaseService;

    public PaymentDialog(HibernateDatabaseService databaseService) {
        super();
        this.databaseService = databaseService;

        payments = new PCPayments() {
            @Override
            protected List getBranches() {
                return PaymentDialog.this.getBranches();
            }
        };
        setObjectCreator(payments);
    }

    public List<TCheque> getCheques() {
        return payments.getCheques();
    }

    public List getBranches() {
        try {
            return databaseService.getCollection(MBankBranch.class);
        } catch (DatabaseException ex) {
            Logger.getLogger(PaymentDialog.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList();
        }
    }

    public void setNetValue(double netValue) {
        payments.setPayableValue(netValue);
    }
}
