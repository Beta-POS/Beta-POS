/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.zsystem.settings.branch_settings;

import com.mac.af.component.base.button.action.Action;
import com.mac.af.component.base.button.action.ActionUtil;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.tab.TabFunctions;
import com.mac.af.core.message.mOptionPane;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.zsystem.settings.branch_settings.object.RBranchSetting;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PCBranchSettings extends DefaultObjectCreator<RBranchSetting>{

    /**
     * Creates new form PCBranchSettings
     */
    public PCBranchSettings() {
        initComponents();

        initOthers();
    }

    @Action(asynchronous = true)
    public void soSave() {
        int q = mOptionPane.showConfirmDialog(null, "Do you sure want to save satting ?", "Save Setting", mOptionPane.YES_NO_OPTION);
        if (q == mOptionPane.YES_OPTION) {
            try {
                ((BranchSettings) getCPanel()).save(getValue());

                mOptionPane.showMessageDialog(null, "Successfully saved !!!", "Save Setting", mOptionPane.INFORMATION_MESSAGE);
                setIdleMood();
            } catch (DatabaseException | ObjectCreatorException ex) {
                Logger.getLogger(PCBranchSettings.class.getName()).log(Level.SEVERE, null, ex);
                mOptionPane.showMessageDialog(null, "Failed to save !!!\n", "Save Setting", mOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Action
    public void doEdit() {
        setEditMood();
    }

    @Action
    public void doClose() {
        TabFunctions.closeTab(this.getCPanel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initOthers() {
        ActionUtil actionUtil = new ActionUtil(this);
        actionUtil.setAction(btnEdit, "doEdit");
        actionUtil.setAction(btnSave, "doSave");
        actionUtil.setAction(btnClose, "doClose");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        lblDefaultValueString = new com.mac.af.component.derived.display.label.CDLabel();
        lblValueString = new com.mac.af.component.derived.display.label.CDLabel();
        txtSetting = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtDefaultValueString = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtValueString = new com.mac.af.component.derived.input.textfield.CIStringField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new com.mac.af.component.derived.input.textarea.CITextArea();
        btnSave = new com.mac.af.component.derived.command.button.CCButton();
        btnClose = new com.mac.af.component.derived.command.button.CCButton();
        lblDefaultValueDouble = new com.mac.af.component.derived.display.label.CDLabel();
        lblValueDouble = new com.mac.af.component.derived.display.label.CDLabel();
        txtDefaultValueDouble = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        txtValueDouble = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        lblDefaultValueInteger = new com.mac.af.component.derived.display.label.CDLabel();
        lblValueInteger = new com.mac.af.component.derived.display.label.CDLabel();
        txtDefaultValueInteger = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        txtValueInteger = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        chkDefaultValue = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        chkValue = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        btnEdit = new com.mac.af.component.derived.command.button.CCButton();

        cDLabel1.setText("Setting :");

        cDLabel2.setText("Description :");

        lblDefaultValueString.setText("Default Value :");

        lblValueString.setText("Value :");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        btnSave.setText("Save");

        btnClose.setText("Close");

        lblDefaultValueDouble.setText("Default Value :");

        lblValueDouble.setText("Value :");

        lblDefaultValueInteger.setText("Default Value :");

        lblValueInteger.setText("Value :");

        chkDefaultValue.setText("Default Value");

        chkValue.setText("Value");

        btnEdit.setText("Edit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDefaultValueString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDefaultValueDouble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueDouble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDefaultValueInteger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueInteger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSetting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(txtDefaultValueString, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValueString, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDefaultValueDouble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValueDouble, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDefaultValueInteger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValueInteger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkDefaultValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDefaultValueString, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDefaultValueString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValueString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValueString, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDefaultValueDouble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDefaultValueDouble, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValueDouble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValueDouble, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDefaultValueInteger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDefaultValueInteger, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValueInteger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValueInteger, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkDefaultValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.command.button.CCButton btnClose;
    private com.mac.af.component.derived.command.button.CCButton btnEdit;
    private com.mac.af.component.derived.command.button.CCButton btnSave;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkDefaultValue;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkValue;
    private javax.swing.JScrollPane jScrollPane1;
    private com.mac.af.component.derived.display.label.CDLabel lblDefaultValueDouble;
    private com.mac.af.component.derived.display.label.CDLabel lblDefaultValueInteger;
    private com.mac.af.component.derived.display.label.CDLabel lblDefaultValueString;
    private com.mac.af.component.derived.display.label.CDLabel lblValueDouble;
    private com.mac.af.component.derived.display.label.CDLabel lblValueInteger;
    private com.mac.af.component.derived.display.label.CDLabel lblValueString;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtDefaultValueDouble;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtDefaultValueInteger;
    private com.mac.af.component.derived.input.textfield.CIStringField txtDefaultValueString;
    private com.mac.af.component.derived.input.textarea.CITextArea txtDescription;
    private com.mac.af.component.derived.input.textfield.CIStringField txtSetting;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtValueDouble;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtValueInteger;
    private com.mac.af.component.derived.input.textfield.CIStringField txtValueString;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List getIdentityComponents() {
        return Arrays.asList(txtSetting);
    }

    @Override
    protected List getEssentialComponents() {
        return Arrays.asList(
                txtDescription,
                txtDefaultValueString,
                txtValueString);

    }

    @Override
    protected List getOtherFieldComponents() {
        return Arrays.asList();
    }

    @Override
    protected List getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtSetting, "setting"),
                new CInputComponentBinder(txtDescription, "description"));
    }

    @Override
    protected List getUneditableComponents() {
        return Arrays.asList(
                txtSetting,
                txtDescription,
                txtDefaultValueString,
                txtDefaultValueDouble,
                txtDefaultValueInteger,
                chkDefaultValue);
    }

    @Override
    protected Class getObjectClass() {
        return com.mac.zsystem.settings.branch_settings.object.RBranchSetting.class;
    }

    @Override
    protected void initInterface() throws ObjectCreatorException {
        RBranchSetting rBranchSetting = getValueAbstract();

        if (rBranchSetting == null) {
            txtDefaultValueString.setVisible(false);
            txtDefaultValueDouble.setVisible(false);
            txtDefaultValueInteger.setVisible(false);
            chkDefaultValue.setVisible(false);

            txtValueString.setVisible(false);
            txtValueDouble.setVisible(false);
            txtValueInteger.setVisible(false);
            chkValue.setVisible(false);

            lblDefaultValueString.setVisible(false);
            lblDefaultValueDouble.setVisible(false);
            lblDefaultValueInteger.setVisible(false);

            lblValueString.setVisible(false);
            lblValueDouble.setVisible(false);
            lblValueInteger.setVisible(false);
        } else {
            switch (rBranchSetting.getType()) {
                case BranchSettingTypes.STRING:
                    //VISIBILITY
                    txtDefaultValueString.setVisible(true);
                    txtDefaultValueDouble.setVisible(false);
                    txtDefaultValueInteger.setVisible(false);
                    chkDefaultValue.setVisible(false);

                    txtValueString.setVisible(true);
                    txtValueDouble.setVisible(false);
                    txtValueInteger.setVisible(false);
                    chkValue.setVisible(false);

                    lblDefaultValueString.setVisible(true);
                    lblDefaultValueDouble.setVisible(false);
                    lblDefaultValueInteger.setVisible(false);

                    lblValueString.setVisible(true);
                    lblValueDouble.setVisible(false);
                    lblValueInteger.setVisible(false);

                    //VALUE
                    txtDefaultValueString.setCValue(String.valueOf(rBranchSetting.getDefaultValue()));
                    txtValueString.setCValue(String.valueOf(rBranchSetting.getValue()));
                    break;
                case BranchSettingTypes.DOUBLE:
                    txtDefaultValueString.setVisible(false);
                    txtDefaultValueDouble.setVisible(true);
                    txtDefaultValueInteger.setVisible(false);
                    chkDefaultValue.setVisible(false);

                    txtValueString.setVisible(false);
                    txtValueDouble.setVisible(true);
                    txtValueInteger.setVisible(false);
                    chkValue.setVisible(false);

                    lblDefaultValueString.setVisible(false);
                    lblDefaultValueDouble.setVisible(true);
                    lblDefaultValueInteger.setVisible(false);

                    lblValueString.setVisible(false);
                    lblValueDouble.setVisible(true);
                    lblValueInteger.setVisible(false);

                    //VALUE
                    txtDefaultValueDouble.setCValue(Double.valueOf(rBranchSetting.getDefaultValue()));
                    txtValueDouble.setCValue(Double.valueOf(rBranchSetting.getValue()));
                    break;
                case BranchSettingTypes.INTEGER:
                    txtDefaultValueString.setVisible(false);
                    txtDefaultValueDouble.setVisible(false);
                    txtDefaultValueInteger.setVisible(true);
                    chkDefaultValue.setVisible(false);

                    txtValueString.setVisible(false);
                    txtValueDouble.setVisible(false);
                    txtValueInteger.setVisible(true);
                    chkValue.setVisible(false);

                    lblDefaultValueString.setVisible(false);
                    lblDefaultValueDouble.setVisible(false);
                    lblDefaultValueInteger.setVisible(true);

                    lblValueString.setVisible(false);
                    lblValueDouble.setVisible(false);
                    lblValueInteger.setVisible(true);

                    //VALUE
                    txtDefaultValueInteger.setCValue(Integer.valueOf(rBranchSetting.getDefaultValue()));
                    txtValueInteger.setCValue(Integer.valueOf(rBranchSetting.getValue()));
                    break;
                case BranchSettingTypes.BOOLEAN:
                    txtDefaultValueString.setVisible(false);
                    txtDefaultValueDouble.setVisible(false);
                    txtDefaultValueInteger.setVisible(false);
                    chkDefaultValue.setVisible(true);

                    txtValueString.setVisible(false);
                    txtValueDouble.setVisible(false);
                    txtValueInteger.setVisible(false);
                    chkValue.setVisible(true);

                    lblDefaultValueString.setVisible(false);
                    lblDefaultValueDouble.setVisible(false);
                    lblDefaultValueInteger.setVisible(false);

                    lblValueString.setVisible(false);
                    lblValueDouble.setVisible(false);
                    lblValueInteger.setVisible(false);

                    //VALUE
                    chkDefaultValue.setCValue(Boolean.valueOf(rBranchSetting.getDefaultValue()));
                    chkValue.setCValue(Boolean.valueOf(rBranchSetting.getValue()));
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    @Override
    protected void initObject() throws ObjectCreatorException {
        super.initObject();

        RBranchSetting rBranchSetting = getValueAbstract();

        switch (rBranchSetting.getType()) {
            case BranchSettingTypes.STRING:
                rBranchSetting.setValue(String.valueOf(txtValueString.getCValue()));
                break;
            case BranchSettingTypes.DOUBLE:
                rBranchSetting.setValue(String.valueOf(txtValueDouble.getCValue()));
                break;
            case BranchSettingTypes.INTEGER:
                rBranchSetting.setValue(String.valueOf(txtValueInteger.getCValue()));
                break;
            case BranchSettingTypes.BOOLEAN:
                rBranchSetting.setValue(String.valueOf(chkValue.getCValue()));
                break;
        }

    }

    @Override
    public void setEditMood() {
        super.setEditMood();

        btnSave.setVisible(true);
        btnEdit.setVisible(false);
        btnClose.setVisible(true);
    }

    @Override
    public void setIdleMood() {
        try {
            super.setIdleMood();

            btnSave.setVisible(false);
            btnEdit.setVisible(getValueAbstract() != null);
            btnClose.setVisible(true);
        } catch (ObjectCreatorException ex) {
            Logger.getLogger(PCBranchSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}