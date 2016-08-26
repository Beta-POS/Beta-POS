/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.item_property;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.List;

/**
 *
 * @author user
 */
public abstract class REGItemProperty extends AbstractRegistrationForm {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCItemProperty() {
            @Override
            protected String getType() {
                return REGItemProperty.this.getType();
            }
        };
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.item_property.object.MItemProperty.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Name", "name"));
    }

    @Override
    protected List getTableData() throws DatabaseException {
        return getDatabaseService().getCollection("from com.mac.registration.item_property.object.MItemProperty WHERE type='" + getType() + "' and branch='"+ HashUtil.getBranch()+"'");
    }

    protected abstract String getType();
    
    
}
