/*
 *  SERSystemSettings.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 27, 2015, 9:33:59 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.settings.system_settings.editor;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.service.AbstractService;
import com.mac.zsystem.settings.system_settings.object.RSystemSetting;
import java.awt.Component;

/**
 *
 * @author mohan
 */
public class SERSystemSettings extends AbstractService {

    public SERSystemSettings(Component component) {
        super(component);
    }

    public void save(RSystemSetting systemSetting) throws DatabaseException {
//        CPanel.GLOBAL.getDatabaseService().beginLocalTransaction();
        systemSetting = (RSystemSetting) getDatabaseService().save(systemSetting);
        getDatabaseService().getObject(RSystemSetting.class, getDatabaseService().getIdentifier(systemSetting));
//        CPanel.GLOBAL.getDatabaseService().commitLocalTransaction();
//        getDatabaseService().update(systemSetting);
    }
}
