/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.zsystem.settings.system_settings;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.grid_object.AbstractGridObject;
import com.mac.zsystem.settings.system_settings.object.RSystemSetting;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SystemSettings extends AbstractGridObject {

    @Override
    protected CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Setting", "setting"),
                new CTableColumn("Description", "description"),
                new CTableColumn("Default Value", "defaultValue"),
                new CTableColumn("Value", "value"));
    }

    @Override
    protected AbstractObjectCreator getObjectCreator() {
        return new PCSystemSettings();
    }

    @Override
    protected Collection getTableData() {
        try {
            return getDatabaseService().getCollection("FROM com.mac.zsystem.settings.system_settings.object.RSystemSetting ");
        } catch (DatabaseException ex) {
            Logger.getLogger(SystemSettings.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void save(RSystemSetting systemSetting) throws DatabaseException {
        getDatabaseService().save(systemSetting);
    }

}
