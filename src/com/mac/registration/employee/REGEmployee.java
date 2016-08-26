/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.employee;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import static com.mac.af.templates.registration.AbstractRegistrationForm.SAVE_SUCCESS;
import com.mac.registration.department.object.MDepartment;
import com.mac.registration.employee.object.MEmployee;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilanga
 */
public class REGEmployee extends AbstractRegistrationForm<MEmployee> {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCEmployee();
    }

    @Override
    public Class getObjectClass() {
        return MEmployee.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("NIC No", "nicNo"),
                new CTableColumn("Name", "name"),
                new CTableColumn("Telephone1", "telephone1"),
                new CTableColumn("Telephone2", "telephone2"),
                new CTableColumn("Mobile", "mobile"),
                new CTableColumn("Active", "active"));
    }

    @Override
    protected int save(MEmployee object) throws DatabaseException {
        if (object.getMPhoto() != null) {
            getDatabaseService().save(object.getMPhoto());
        }
        getDatabaseService().save(object);
        
        if (object.getMPhoto() != null) {
            getDatabaseService().save(object.getMPhoto());
        }
        getDatabaseService().save(object);
        
        
        return SAVE_SUCCESS;
    }

    @Override
    protected List getTableData() throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(MEmployee.class);
        criteria.add(Restrictions.eq("branch", HashUtil.getBranch()));
        return criteria.list();
    }
}
