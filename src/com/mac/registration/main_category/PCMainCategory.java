/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.main_category;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.message.mOptionPane;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.model.table_model.MainCategoryTableModel;
import com.mac.registration.company.PCCompany;
import com.mac.registration.main_category.object.MMainCategory;
import com.mac.zsystem.settings.system_settings.SystemSettingInterface;
import com.mac.zsystem.settings.system_settings.object.RSystemSetting;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.AutoCodeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thilanga
 */
public class PCMainCategory extends DefaultObjectCreator<MMainCategory> {

    /**
     * Creates new form PCMainCategory
     */
    public PCMainCategory() {
        initComponents();
        initOthers();
    }

    private List getDepartments() {
        List department;
        try {
            String hql = "FROM com.mac.registration.main_category.object.MDepartment where active=true and branch ='"+HashUtil.getBranch()+"'";
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            department = databaseService.getCollection(hql);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            department = new ArrayList();
        }
        return department;
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        cboDepartment.setExpressEditable(true);
        cboDepartment.setTableModel(new MainCategoryTableModel());


//        set max text lengh
        txtCode.setLength(10);
        txtCategoryName.setLength(50);

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
        txtCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        txtCategoryName = new com.mac.af.component.derived.input.textfield.CIStringField();
        chkActive = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        cboDepartment = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return getDepartments();
            }
        };

        cDLabel1.setText("Code:");

        cDLabel2.setText("Name:");

        chkActive.setText("Active");

        cDLabel3.setText("Department:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtCategoryName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboDepartment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboDepartment;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkActive;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCategoryName;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List getIdentityComponents() {
        return Arrays.asList(txtCode);
    }

    @Override
    protected List getEssentialComponents() {
        return Arrays.asList(
                cboDepartment,
                txtCategoryName);
    }

    @Override
    protected List getOtherFieldComponents() {
        return Arrays.asList(
                chkActive);
    }

    @Override
    protected List getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(cboDepartment, "MDepartment"),
                new CInputComponentBinder(txtCategoryName, "name"),
                new CInputComponentBinder(chkActive, "active"));
    }

    @Override
    protected Class getObjectClass() {
        return MMainCategory.class;
    }

    @Override
    protected void afterInitObject(MMainCategory object) throws ObjectCreatorException {
        object.setHash(HashUtil.getHash(object.getCode()));
        object.setBranch(HashUtil.getBranch());
    }

    @Override
    public void setNewMood() {
        super.setNewMood();

//        RSystemSetting systemSetting = SystemSettingInterface.getSystemSetting(SystemSettingInterface.MAIN_CATEGORY_AUTO_CODE);
//        if (!systemSetting.getValue().isEmpty()) {
//            try {
//                String code = AutoCodeUtil.getCode(systemSetting, "m_main_category", "branch='" + HashUtil.getBranch() + "'");
//                txtCode.setCValue(code);
//            } catch (ApplicationException ex) {
//                Logger.getLogger(PCCompany.class.getName()).log(Level.SEVERE, null, ex);
//
//                mOptionPane.showMessageDialog(null, "Unable to auto create code. Please check settings.", "Code Generator", mOptionPane.WARNING_MESSAGE);
//            }
//        }
        int number = SystemSettingInterface.getSystemSetting(SystemSettingInterface.SUB_CATEGORY_AUTO_CODE).valueAsInteger();
        String prefix = "";
        if (number != 0) {
            try {
                String code = AutoCodeUtil.getCode(prefix, number, "m_main_category", "branch='" + HashUtil.getBranch() + "'");
                txtCode.setCValue(code);
            } catch (ApplicationException ex) {
                Logger.getLogger(PCCompany.class.getName()).log(Level.SEVERE, null, ex);
                mOptionPane.showMessageDialog(null, "Unable to auto create code. Please check settings.", "Code Generator", mOptionPane.WARNING_MESSAGE);
            }
        }
    }
}