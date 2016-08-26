/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.reports.registration;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;

/**
 *
 * @author user
 */
public class REGReportRegistration extends AbstractRegistrationForm {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCRepotRegistrationForm();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.reports.object.MReport.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                    new CTableColumn("Code", "code"),
                    new CTableColumn("Name ", "name"),
                    new CTableColumn("Category", "category")
                );
    }
}
