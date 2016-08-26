/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.branch;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;

/**
 *
 * @author user
 */
public class REGBranch extends AbstractRegistrationForm {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCBranch();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.branch.object.MBranch.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn[]{
            new CTableColumn("Code", new String[]{"code"}),
            new CTableColumn("Name", new String[]{"name"}),
            new CTableColumn("Hotline", new String[]{"hotline"}),
            new CTableColumn("Telephone 1", new String[]{"telephone1"}),
            new CTableColumn("Telephone 2", new String[]{"telephone2"}),
            new CTableColumn("Fax", new String[]{"fax"}),
            new CTableColumn("E mail", new String[]{"email"}),
            new CTableColumn("Active", new String[]{"active"}),});
    }
}
