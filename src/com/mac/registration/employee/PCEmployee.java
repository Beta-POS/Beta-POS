/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.employee;

import com.mac.af.component.base.button.action.Action;
import com.mac.af.component.base.button.action.ActionUtil;
import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.environment.CApplication;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.registration.Salutation;
import com.mac.registration.company.PCCompany;
import com.mac.registration.employee.object.MEmployee;
import com.mac.registration.employee.object.MPhoto;
import com.mac.zsystem.object_creator.HashObjectCreator;
import com.mac.zsystem.settings.system_settings.SystemSettingInterface;
import com.mac.zsystem.settings.system_settings.object.RSystemSetting;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.AutoCodeUtil;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author thilanga
 */
public class PCEmployee extends HashObjectCreator<MEmployee> {

    private JFileChooser fileChooser;
    private BufferedImage image;
    private byte[] imageBytes;

    /**
     * Creates new form PCEmployee
     */
    public PCEmployee() {
        initComponents();
        initOthers();
    }

    private List getUserRoles() {
        List store;
        try {
            String hql = "FROM com.mac.registration.employee.object.RUserRole";
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            store = databaseService.getCollection(hql);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            store = new ArrayList();
        }
        return store;
    }

    @Action
    public void doBrowse() throws IOException {
        fileChooser.showOpenDialog(CApplication.getInstance().getMainFrame());
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            image = ImageIO.read(file);

            imageBytes = new byte[(int) file.length()];
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                fileInputStream.read(imageBytes);
            }
        } else {
            image = null;
            imageBytes = null;
        }
        setThumbnail();
    }

    private ImageIcon getScaledImage(Image image, int w, int h) {
        ImageIcon loadedImageIcon = new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return loadedImageIcon;
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    private void setThumbnail() {
        if (image != null) {
            lblImage.setIcon(getScaledImage(image, lblImage.getWidth(), lblImage.getHeight()));
        } else {
            lblImage.setIcon(null);
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {

        txtCode.setLength(10);
        txtName.setLength(50);
        txtNicNo.setLength(25);
        txtFullName.setLength(100);
        txtAddressLine1.setLength(50);
        txtAddressLine2.setLength(50);
        txtAddressLine3.setLength(50);
        txtMobile.setLength(25);
        txtTelephone1.setLength(25);
        txtTelephone2.setLength(25);
        txtFax.setLength(25);
        txtEmail.setLength(50);
        // txtNote.setLength(WIDTH);
        txtUserName.setLength(50);
        //  txtPassword.setLength(WIDTH);
//        cboStore.setExpressEditable(true);
//        cboStore.setTableModel(new EmployeeTableModel());

        ActionUtil actionUtil = new ActionUtil(this);
        actionUtil.setAction(btnBrowse, "doBrowse");

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
//Accept all directories and all gif, jpg, tiff, or png files.
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }

                String extension = getExtension(f);
                if (extension != null) {
                    if (extension.equals("jpeg")
                            || extension.equals("jpg")
                            || extension.equals("png")) {
                        return true;
                    } else {
                        return false;
                    }
                }

                return false;
            }
            //The description of this filter

            @Override
            public String getDescription() {
                return "Just Images";
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel4 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel5 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel6 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel7 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel9 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel10 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel12 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel13 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel14 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel15 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel16 = new com.mac.af.component.derived.display.label.CDLabel();
        btnBrowse = new com.mac.af.component.derived.command.button.CCButton();
        lblImage = new com.mac.af.component.derived.display.label.CDLabel();
        txtCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtNicNo = new com.mac.af.component.derived.input.textfield.CIStringField();
        cboSalutation = new com.mac.af.component.derived.input.combobox.CIComboBox(Salutation.ALL);
        txtFullName = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtAddressLine1 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtAddressLine2 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtAddressLine3 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtMobile = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtTelephone1 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtTelephone2 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtFax = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtEmail = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtNote = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtUserName = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtPassword = new com.mac.af.component.derived.input.passwordfield.CIPasswordField();
        chkActive = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cDLabel8 = new com.mac.af.component.derived.display.label.CDLabel();
        cboUserRole = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return getUserRoles();
            }
        };

        setPreferredSize(new java.awt.Dimension(400, 403));

        cDLabel1.setText("Code:");

        cDLabel3.setText("Nic No:");

        cDLabel4.setText("Salutation:");

        cDLabel5.setText("Name:");

        cDLabel6.setText("Full Name:");

        cDLabel7.setText("Address Line:");

        cDLabel9.setText("Mobile:");

        cDLabel10.setText("Telephone:");

        cDLabel12.setText("Fax:");

        cDLabel13.setText("Email:");

        cDLabel14.setText("Note:");

        cDLabel15.setText("User Name:");

        cDLabel16.setText("Password:");

        btnBrowse.setText("Browse");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtNicNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNicNoActionPerformed(evt);
            }
        });

        txtFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFullNameActionPerformed(evt);
            }
        });

        chkActive.setText("Active");

        cDLabel8.setText("User Role :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboSalutation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNicNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(65, 65, 65)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(btnBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelephone1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(chkActive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMobile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboUserRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddressLine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddressLine2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddressLine3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNicNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSalutation, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFullName, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddressLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddressLine2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddressLine3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephone1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(txtTelephone2, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFax, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNote, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNicNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNicNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNicNoActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFullNameActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.command.button.CCButton btnBrowse;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel10;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel12;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel13;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel14;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel15;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel16;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel4;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel5;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel6;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel7;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel8;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel9;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSalutation;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboUserRole;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkActive;
    private com.mac.af.component.derived.display.label.CDLabel lblImage;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddressLine1;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddressLine2;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddressLine3;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    private com.mac.af.component.derived.input.textfield.CIStringField txtEmail;
    private com.mac.af.component.derived.input.textfield.CIStringField txtFax;
    private com.mac.af.component.derived.input.textfield.CIStringField txtFullName;
    private com.mac.af.component.derived.input.textfield.CIStringField txtMobile;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    private com.mac.af.component.derived.input.textfield.CIStringField txtNicNo;
    private com.mac.af.component.derived.input.textfield.CIStringField txtNote;
    private com.mac.af.component.derived.input.passwordfield.CIPasswordField txtPassword;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTelephone1;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTelephone2;
    private com.mac.af.component.derived.input.textfield.CIStringField txtUserName;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List<Component> getIdentityComponents() {
        return Arrays.asList((Component) txtCode);
    }

    @Override
    protected List<Component> getEssentialComponents() {
        return Arrays.asList((Component) txtName,
                (Component) txtNicNo,
                (Component) txtUserName,
                (Component) cboSalutation,
                (Component) cboUserRole,
                (Component) txtPassword);
    }

    @Override
    protected List<Component> getOtherFieldComponents() {
        return Arrays.asList(
                (Component) txtTelephone2,
                (Component) txtTelephone1,
                (Component) txtNote,
                (Component) txtMobile,
                (Component) txtFullName,
                (Component) lblImage,
                (Component) txtFax,
                (Component) txtEmail,
                (Component) txtAddressLine2,
                (Component) txtAddressLine1,
                (Component) txtAddressLine3,
                (Component) chkActive);
    }

    @Override
    protected List<CInputComponentBinder> getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtAddressLine1, "addressLine1"),
                new CInputComponentBinder(txtAddressLine2, "addressLine2"),
                new CInputComponentBinder(txtAddressLine3, "addressLine3"),
                new CInputComponentBinder(cboSalutation, "salutation"),
                new CInputComponentBinder(cboUserRole, "RUserRole"),
                new CInputComponentBinder(txtEmail, "email"),
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(txtFax, "fax"),
                new CInputComponentBinder(txtFullName, "fullName"),
                new CInputComponentBinder(txtMobile, "mobile"),
                new CInputComponentBinder(txtName, "name"),
                new CInputComponentBinder(txtNicNo, "nicNo"),
                new CInputComponentBinder(txtNote, "note"),
                new CInputComponentBinder(txtUserName, "userName"),
                new CInputComponentBinder(txtPassword, "password"),
                new CInputComponentBinder(txtTelephone1, "telephone1"),
                new CInputComponentBinder(txtTelephone2, "telephone2"),
                new CInputComponentBinder(chkActive, "active"));
    }

    @Override
    protected Class<? extends MEmployee> getObjectClass() {
        return MEmployee.class;
    }

//@Override
//    protected void afterNewObject(Object object) {
//        ((com.mac.registration.employee.object.MEmployee) object).setActive(true);
//    }
    @Override
    public void setIdleMood() {
        super.setIdleMood();
        btnBrowse.setEnabled(false);
    }

    @Override
    public void setNewMood() {
        super.setNewMood();
        btnBrowse.setEnabled(true);
        int number = SystemSettingInterface.getSystemSetting(SystemSettingInterface.EMPLOYEE_AUTO_CODE).valueAsInteger();
        String prefix = "";
        if (number != 0) {
            try {
                String code = AutoCodeUtil.getCode(prefix, number, "m_employee", "branch='" + HashUtil.getBranch() + "'");
                txtCode.setCValue(code);
            } catch (ApplicationException ex) {
                Logger.getLogger(PCCompany.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void setEditMood() {
        super.setEditMood();
        btnBrowse.setEnabled(true);
    }

    @Override
    protected void afterInitObject(MEmployee object) throws ObjectCreatorException {
        super.afterInitObject(object);

        object.setHash(HashUtil.getHash(object.getCode()));
        object.setBranch(HashUtil.getBranch());

        MEmployee employee = (MEmployee) object;

        if (image != null) {
            MPhoto photo = employee.getMPhoto();
            if (photo == null) {
                photo = new MPhoto();
            }
            photo.setPhoto(imageBytes);
            employee.setMPhoto(photo);
        } else {
            employee.setMPhoto(null);
        }
    }

    @Override
    protected void initInterface() throws ObjectCreatorException {
        super.initInterface();

        try {
            MEmployee employee = (MEmployee) getValueAbstract();
            if (employee != null) {
                MPhoto photo = employee.getMPhoto();
                if (photo != null) {
                    imageBytes = photo.getPhoto();
                    image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                } else {
                    image = null;
                }
                setThumbnail();
            }
        } catch (IOException ex) {
            Logger.getLogger(PCEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateCode() {
    }
}
