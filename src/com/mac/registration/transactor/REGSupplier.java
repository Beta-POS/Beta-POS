/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.transactor;

import com.mac.af.core.database.DatabaseException;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.List;

/**
 *
 * @author thilanga
 */
public class REGSupplier extends REGClient {

    @Override
    public Boolean isClient() {
        return false;
    }

    @Override
    public Boolean isSuplier() {
        return true;
    }

    @Override
    protected List getTableData() throws DatabaseException {
        return getDatabaseService().getCollection("FROM com.mac.registration.transactor.object.MTransactor WHERE supplier=true and branch = '"+ HashUtil.getBranch()+"'");
    }
}
