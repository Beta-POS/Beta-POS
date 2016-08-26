/*
 *  REGAccountCategory.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Jun 18, 2014, 11:36:51 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.registration.account_category;

import com.mac.registration.account_category.object.MAccountCategory;
import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;

/**
 *
 * @author user
 */
public class REGAccountCategory extends AbstractRegistrationForm<MAccountCategory> {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCAccountCategory();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.account_category.object.MAccountCategory.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn[]{
            new CTableColumn("Code", new String[]{"code"}),
            new CTableColumn("Name", new String[]{"name"}),
            new CTableColumn("Parent Category", new String[]{"MAccountCategory", "name"}),
            new CTableColumn("Type", new String[]{"type"})
        });
    }

    @Override
    protected int save(MAccountCategory object) throws DatabaseException {
        MAccountCategory accountCategory = (MAccountCategory) object;
        if (accountCategory.getMAccountCategory() != null) {
            accountCategory.setPath(accountCategory.getCode());
        } else {
            accountCategory.setPath(accountCategory.getMAccountCategory().getPath() + PATH_SEPARATOR + accountCategory.getCode());
        }

        return super.save(object);
        //return 1;
    }
    public static final String PATH_SEPARATOR = "%";
}
