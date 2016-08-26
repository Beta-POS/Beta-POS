/*
 *  Theme.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Jun 30, 2014, 9:03:21 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.settings.theme_chooser;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListCellRenderer;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.setting.SettingUtil;
import com.mac.af.core.setting.framework.Framework;
import com.mac.af.templates.system.theme.AbstractThemeSelectorPanel;

/**
 *
 * @author user0
 */
public class Theme extends AbstractThemeSelectorPanel {

    @Override
    protected String[] getLookAndFeels() {
        String[] substanceLooks = new String[]{
            "org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceEmeraldDuskLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel",
            "org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel"
        };
        return substanceLooks;
    }

    @Override
    protected ListCellRenderer getCellRenderer() {
        return new ThemeListRenderer();
    }

    
    @Override
    protected void applySettings(String lookAndFeel) {
        try {
            Framework framework = (Framework) CApplication.getInstance().getSettingRoot().getSetting(Framework.class);
            framework.getSkin().getSkin().setValue(lookAndFeel);
            SettingUtil.getInstance().writeSettings(CApplication.getInstance().getSettingRoot());
        } catch (Exception e) {
            Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
