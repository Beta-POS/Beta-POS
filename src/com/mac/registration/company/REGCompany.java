/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.company;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;

/**
 *
 * @author user
 */
public class REGCompany extends AbstractRegistrationForm {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCCompany();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.company.object.MCompany.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"));
    }
}
