/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.department;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.registration.department.object.MDepartment;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilanga
 */
public class REGDepartment extends AbstractRegistrationForm {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCDepartment();
    }

    @Override
    public Class getObjectClass() {
        return MDepartment.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"));
    }

    @Override
    protected List getTableData() throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(MDepartment.class);
        criteria.add(Restrictions.eq("branch", HashUtil.getBranch()));
        return criteria.list();
    }

    @Override
    protected int save(Object object) throws DatabaseException {
        super.save(object);
        return super.save(object); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
