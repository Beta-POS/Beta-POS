/*
 *  REGAccount.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Jun 19, 2014, 8:31:06 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.registration.account;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;

/**
 *
 * @author user
 */
public class REGAccount extends AbstractRegistrationForm{

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCAccount();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.account.object.MAccount.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn[]{
                    new CTableColumn("Code", new String[]{"code"}),
                    new CTableColumn("Name", new String[]{"name"}),
                    new CTableColumn("Account Category", new String[]{"MAccountCategory","name"}),
                    new CTableColumn("Account Group", new String[]{"accountGroup"}),
                    new CTableColumn("Print Order", new String[]{"printOrder"}),
                    new CTableColumn("Before Word", new String[]{"beforeWord"}),
                    new CTableColumn("After Word", new String[]{"afterWord"}),
                    new CTableColumn("Active", new String[]{"active"})
                });
    }

}
