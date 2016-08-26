/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.reports.registration;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author thilanga
 */
public class ReportsResources {

    public static final URL REPORT_ICON_URL = ReportsResources.class.getResource("resources/save.png");

    public static ImageIcon getImageIcon(URL url) {
        ImageIcon icon;
        try {
            icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            icon = null;
        }

        return icon;
    }
}
