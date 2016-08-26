/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.transactor_category;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.registration.transactor_category.object.MTransactorCategory;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilanga
 */
public class REGTransactorCategory extends AbstractRegistrationForm {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCTransactorCategory();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.transactor_category.object.MTransactorCategory.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"));
    }
    
    @Override
    protected List getTableData() throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(MTransactorCategory.class);
        criteria.add(Restrictions.eq("branch", HashUtil.getBranch()));
        return criteria.list();
    }

    @Override
    protected int save(Object object) throws DatabaseException {
        super.save(object);
        return super.save(object);
    }
    
    
}
