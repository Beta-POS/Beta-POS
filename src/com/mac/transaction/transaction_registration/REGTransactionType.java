/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.transaction.transaction_registration;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;

/**
 *
 * @author user
 */
public class REGTransactionType extends AbstractRegistrationForm{

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCTransactionType();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.transaction.transaction_registration.object.RTransactionType.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"),
                new CTableColumn("Transaction Type", "type")
                );
    }
    
}
