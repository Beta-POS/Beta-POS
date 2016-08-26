/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.transactor;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.registration.transactor.object.MTransactor;
import com.mac.zsystem.util.hash.HashUtil;
//import com.mac.registration.transactor.object.MClient;
import java.util.List;

/**
 *
 * @author thilanga
 */
public class REGClient extends AbstractRegistrationForm<MTransactor> {

    @Override
    public AbstractObjectCreator<MTransactor> getObjectCreator() {
        return new PCClient(isClient(), isSuplier());
    }

    @Override
    public Class<? extends MTransactor> getObjectClass() {
        return MTransactor.class;
    }

    @Override
    public CTableModel<MTransactor> getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"),
                new CTableColumn("Mobile", "mobile"),
                new CTableColumn("Telephone1", "telephone1"),
                new CTableColumn("Telephone2", "telephone2"),
                new CTableColumn("Active", "active"));
    }

    public Boolean isClient() {
        return true;
    }

    public Boolean isSuplier() {
        return false;
    }

    @Override
    protected List<MTransactor> getTableData() throws DatabaseException {
        return getDatabaseService().getCollection("FROM com.mac.registration.transactor.object.MTransactor WHERE client = true and branch = '"+ HashUtil.getBranch()+"'");
    }

    @Override
    protected int save(MTransactor object) throws DatabaseException {
        System.out.println(object.isActive() + " - " + "is active");
        return super.save(object); 
    }
    
    
}
