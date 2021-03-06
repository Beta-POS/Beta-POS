/*
 *  SidePanel.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Dec 1, 2014, 3:20:10 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package sphere;

import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import org.pushingpixels.substance.internal.utils.border.SubstanceTextComponentBorder;

/**
 *
 * @author mohan
 */
public class SidePanel extends javax.swing.JPanel {

    private String employeeName;
    // private Date workingDate;
    private byte[] imageBytes;

    /**
     * Creates new form SidePanel
     */
    public SidePanel() {
    }

    public SidePanel(String employeeName, byte[] imageBytes) {
        this.employeeName = employeeName;
        this.imageBytes = imageBytes;

        initComponents();
        initOthers();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initOthers() {
        lblEmployee.setText(employeeName);

        if (imageBytes != null) {
            ImageIcon imageIcon = new ImageIcon(imageBytes);
            Image image = imageIcon.getImage();
            image = image.getScaledInstance(130, 150, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);

            lblImage.setIcon(imageIcon);
        } else {
            lblImage.setIcon(null);
        }
        pnlImage.setBorder(new SubstanceTextComponentBorder(pnlImage.getInsets()));
        lblEmployee.setBorder(new SubstanceTextComponentBorder(lblEmployee.getInsets()));
        lblWorkingDate.setBorder(new SubstanceTextComponentBorder(lblEmployee.getInsets()));

        Date workingDate = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lblWorkingDate.setText(dateFormat.format(workingDate));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSideBar = new javax.swing.JPanel();
        pnlImage = new javax.swing.JPanel();
        lblImage = new com.mac.af.component.derived.display.label.CDLabel();
        lblWorkingDate = new com.mac.af.component.derived.display.label.CDLabel();
        lblEmployee = new com.mac.af.component.derived.display.label.CDLabel();

        pnlImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlImageLayout = new javax.swing.GroupLayout(pnlImage);
        pnlImage.setLayout(pnlImageLayout);
        pnlImageLayout.setHorizontalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlImageLayout.setVerticalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblWorkingDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWorkingDate.setText("2014-01-01");
        lblWorkingDate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployee.setText("EMPLOYEE");
        lblEmployee.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlSideBarLayout = new javax.swing.GroupLayout(pnlSideBar);
        pnlSideBar.setLayout(pnlSideBarLayout);
        pnlSideBarLayout.setHorizontalGroup(
            pnlSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWorkingDate, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSideBarLayout.setVerticalGroup(
            pnlSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWorkingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSideBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel lblEmployee;
    private com.mac.af.component.derived.display.label.CDLabel lblImage;
    private com.mac.af.component.derived.display.label.CDLabel lblWorkingDate;
    private javax.swing.JPanel pnlImage;
    private javax.swing.JPanel pnlSideBar;
    // End of variables declaration//GEN-END:variables
}
