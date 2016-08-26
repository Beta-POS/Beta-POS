/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.branch;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.model.table_model.BranchTableModel;
import com.mac.registration.branch.object.MBranch;
import com.mac.registration.company.PCCompany;
import com.mac.zsystem.settings.system_settings.SystemSettingInterface;
import com.mac.zsystem.settings.system_settings.object.RSystemSetting;
import com.mac.zsystem.util.index.AutoCodeUtil;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PCBranch extends DefaultObjectCreator<MBranch> {

    /**
     * Creates new form PCBranch
     */
    public PCBranch() {
        initComponents();
        initOthers();
    }

    private List getCompany() {
        List routs;
        try {
            String hql = "FROM com.mac.registration.branch.object.MCompany";
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            routs = databaseService.getCollection(hql);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            routs = new ArrayList();
        }
        return routs;
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        txtCode.setLength(10);
        txtName.setLength(50);
        txtAddress1.setLength(50);
        txtAddress2.setLength(50);
        txtAddress3.setLength(50);
        txtHotLine.setLength(25);
        txtTelephone1.setLength(25);
        txtTelephone2.setLength(25);
        txtFax.setLength(25);
        txtEmail.setLength(50);
        //  txtNote.setLength(WIDTH);

        cboName.setExpressEditable(true);
        cboName.setTableModel(new BranchTableModel());
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
        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel6 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel7 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel9 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel10 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel11 = new com.mac.af.component.derived.display.label.CDLabel();
        txtCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtAddress1 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtAddress2 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtAddress3 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtHotLine = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtTelephone1 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtTelephone2 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtFax = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtEmail = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtNote = new com.mac.af.component.derived.input.textfield.CIStringField();
        chkActive = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboName = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return getCompany();
            }
        };
        cDLabel12 = new com.mac.af.component.derived.display.label.CDLabel();
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();

        cDLabel1.setText("Code :");

        cDLabel2.setText("Company :");

        cDLabel3.setText("Address :");

        cDLabel6.setText("Hot Line :");

        cDLabel7.setText("Telephone :");

        cDLabel9.setText("Fax :");

        cDLabel10.setText("Email :");

        cDLabel11.setText("Note :");

        chkActive.setText("Active");

        cDLabel12.setText("Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cDLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cDLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cDLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddress2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddress3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboName, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtFax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(txtHotLine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkActive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelephone1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(24, 24, 24)
                        .addComponent(txtTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddress3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHotLine, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel10;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel11;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel12;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel6;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel7;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel9;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboName;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkActive;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddress1;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddress2;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddress3;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    private com.mac.af.component.derived.input.textfield.CIStringField txtEmail;
    private com.mac.af.component.derived.input.textfield.CIStringField txtFax;
    private com.mac.af.component.derived.input.textfield.CIStringField txtHotLine;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    private com.mac.af.component.derived.input.textfield.CIStringField txtNote;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTelephone1;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTelephone2;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List<Component> getIdentityComponents() {
        return Arrays.asList((Component) txtCode);
    }

    @Override
    protected List<Component> getEssentialComponents() {
        return Arrays.asList();


    }

    @Override
    protected List<Component> getOtherFieldComponents() {
        return Arrays.asList(
                (Component) txtAddress1,
                (Component) txtAddress2,
                (Component) txtName,
                (Component) txtAddress3,
                (Component) cboName,
                (Component) txtHotLine,
                (Component) txtTelephone1,
                (Component) txtTelephone2,
                (Component) txtFax,
                (Component) txtEmail,
                (Component) txtNote,
                (Component) chkActive);
    }

    @Override
    protected List<CInputComponentBinder> getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(txtName, "name"),
                new CInputComponentBinder(cboName, "MCompany"),
                new CInputComponentBinder(txtAddress1, "addressLine1"),
                new CInputComponentBinder(txtAddress2, "addressLine2"),
                new CInputComponentBinder(txtAddress3, "addressLine3"),
                new CInputComponentBinder(txtHotLine, "hotline"),
                new CInputComponentBinder(txtTelephone1, "telephone1"),
                new CInputComponentBinder(txtTelephone2, "telephone2"),
                new CInputComponentBinder(txtFax, "fax"),
                new CInputComponentBinder(txtEmail, "email"),
                new CInputComponentBinder(txtNote, "note"),
                new CInputComponentBinder(chkActive, "active"));
    }

    @Override
    protected Class<? extends MBranch> getObjectClass() {
        return com.mac.registration.branch.object.MBranch.class;
    }

    @Override
    public void setNewMood() {
        super.setNewMood();
        int number = SystemSettingInterface.getSystemSetting(SystemSettingInterface.BRANCH_AUTO_CODE).valueAsInteger();
        String prefix = "";
        if (number != 0) {

            try {
                String code = AutoCodeUtil.getCode(prefix, number, "m_branch", "1=1");
                txtCode.setCValue(code);
            } catch (ApplicationException ex) {
                Logger.getLogger(PCCompany.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void afterNewObject(MBranch object) {
        object.setWorkingDate(new Date());
    }
    
    
}
