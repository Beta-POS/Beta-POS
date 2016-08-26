/*
 * ThemeListRenderer.java
 * 
 * @author channa mohan
 *     hjchanna@gmail.com
 * 
 * Created on Apr 23, 2013
 * Copyright Channa Mohan, All rights reserved.
 * 
 */
package com.mac.zsystem.settings.theme_chooser;

import com.mac.zresources.SphereResources;
import javax.swing.Icon;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultListCellRenderer;

/**
 *
 * @author Mechatrons
 */
public class ThemeListRenderer extends SubstanceDefaultListCellRenderer {

    public ThemeListRenderer() {
        icon = SphereResources.getImageIcon(SphereResources.UNIT, 16, 16);
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public void setText(String name) {
        String text = name;
        if (name.lastIndexOf('.') != -1) {
            name = name.substring(name.lastIndexOf('.') + 1);
            name = name.replace("Substance", "");
            name = name.replace("LookAndFeel", "");
        }

        name = "<html><b><font color='green'>" + name + "</font></b><br/><i><font color='gray'>" + text + "</font></i></html>";
        super.setText(name);
    }
    private Icon icon;
}
