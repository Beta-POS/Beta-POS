/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.bank;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;

/**
 *
 * @author user
 */
public class REGBank extends AbstractRegistrationForm{

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCBank();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.bank.object.MBank.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name")
                );
    }
    
}
