/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.sub_category;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.registration.store.object.MStore;
import com.mac.registration.sub_category.object.MSubCategory;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilanga
 */
public class REGSubCategory extends AbstractRegistrationForm {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCSubCategory();
    }

    @Override
    public Class getObjectClass() {
        return MSubCategory.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"));
    }
    
    @Override
    protected List getTableData() throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(MSubCategory.class);
        criteria.add(Restrictions.eq("branch", HashUtil.getBranch()));
        return criteria.list();
    }  
}
