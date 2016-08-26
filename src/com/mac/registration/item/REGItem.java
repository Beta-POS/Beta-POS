/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.item;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.registration.department.object.MDepartment;
import com.mac.registration.item.object.MItem;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilanga
 */
public class REGItem extends AbstractRegistrationForm<MItem> {

    private SERItem serItem;

    private void initService() {
        if (serItem == null) {
            serItem = new SERItem(this);
        }
    }

    @Override
    public AbstractObjectCreator getObjectCreator() {
        initService();
        return new PCItem(serItem);
    }

    @Override
    public Class getObjectClass() {
        return MItem.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"),
                new CTableColumn("Department", "MDepartment"),
                new CTableColumn("BarCode", "barcode"));
    }

    @Override
    protected int save(MItem object) throws DatabaseException {
        initService();

        serItem.saveItem(object, true);

        return SAVE_SUCCESS;
    }

    @Override
    protected List getTableData() throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(MItem.class);
        criteria.add(Restrictions.eq("branch", HashUtil.getBranch()));
        return criteria.list();
    }
}
