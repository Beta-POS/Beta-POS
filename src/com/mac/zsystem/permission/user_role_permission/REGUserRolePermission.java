/*
 *  REGUserRolePermission.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Jan 22, 2015, 9:41:25 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.permission.user_role_permission;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.zsystem.permission.object.RUserRole;

/**
 *
 * @author mohan
 */
public class REGUserRolePermission extends AbstractRegistrationForm<RUserRole> {

    @Override
    public AbstractObjectCreator<RUserRole> getObjectCreator() {
        return new PCUserRolePermission();
    }

    @Override
    public Class<? extends RUserRole> getObjectClass() {
        return RUserRole.class;
    }

    @Override
    public CTableModel<RUserRole> getTableModel() {
        return new CTableModel<>(
                new CTableColumn("Code", "code"),
                new CTableColumn("User Role", "name"));
    }
}
