/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.transactor;

import com.mac.af.core.database.DatabaseException;
import java.util.List;

/**
 *
 * @author thilanga
 */
public class REGDealer extends REGClient {

    @Override
    public Boolean isClient() {
        return false;
    }

    @Override
    public Boolean isSuplier() {
        return false;
    }

//    @Override
//    public Boolean isDealer() {
//        return true;
//    }

 @Override
    protected List getTableData() throws DatabaseException {
        return getDatabaseService().getCollection("FROM com.mac.registration.client.object.MClient WHERE dealer=true");
    }
}
