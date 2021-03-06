/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.transactor;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.message.mOptionPane;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.model.table_model.ClientTableModel;
import com.mac.model.table_model.TransactorTableModel;
import com.mac.registration.company.PCCompany;
import com.mac.registration.transactor.object.MTransactor;
import com.mac.registration.transactor.object.MTransactorCategory;
import com.mac.zsystem.object_creator.HashObjectCreator;
import com.mac.zsystem.settings.system_settings.SystemSettingInterface;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.AutoCodeUtil;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

/**
 *
 * @author thilanga
 */
public class PCClient extends HashObjectCreator<MTransactor> {

    private boolean isClient;
    private boolean isSupplier;

    //  private boolean isDealer;
    /**
     * Creates new form PCClient
     */
    public PCClient(boolean isClient, boolean isSupplier) {
        initComponents();
        initOthers();
        this.isClient = isClient;
        this.isSupplier = isSupplier;
    }

    private List getRouts() {
        List routs;
        try {
            String hql = "FROM com.mac.registration.transactor.object.MRoute where branch = '"+HashUtil.getBranch()+"'";
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            routs = databaseService.getCollection(hql);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            routs = new ArrayList();
        }
        return routs;
    }

    private List getCategory() {
        List category;
        try {
            String hql = "FROM com.mac.registration.transactor.object.MTransactorCategory where branch = '"+HashUtil.getBranch()+"'";
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            category = databaseService.getCollection(hql);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            category = new ArrayList();
        }
        return category;
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {

        JComboBox comboBox = cboCategory.getComboBox();

        comboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                generateCode();
            }
        });

        cboRoute.setExpressEditable(true);
        cboRoute.setTableModel(new ClientTableModel());
        cboCategory.setExpressEditable(true);
        cboCategory.setTableModel(new TransactorTableModel());

        //text  feild max size
        txtCode.setLength(10);
        txtName.setLength(50);

        txtContactPerson.setLength(50);
        txtMobile.setLength(25);
        txtAddressLine1.setLength(50);
        txtAddressLine2.setLength(50);
        txtAddressLine3.setLength(50);
        txtTelephone1.setLength(25);
        txtTelephone2.setLength(25);
        txtTelephone3.setLength(25);
        txtEmail.setLength(50);
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
        lblRoute = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        txtContactPerson = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel5 = new com.mac.af.component.derived.display.label.CDLabel();
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtTelephone3 = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel7 = new com.mac.af.component.derived.display.label.CDLabel();
        txtAddressLine1 = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel8 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel9 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel10 = new com.mac.af.component.derived.display.label.CDLabel();
        txtMobile = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel11 = new com.mac.af.component.derived.display.label.CDLabel();
        txtTelephone1 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtTelephone2 = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel13 = new com.mac.af.component.derived.display.label.CDLabel();
        txtFax = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel14 = new com.mac.af.component.derived.display.label.CDLabel();
        txtEmail = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel15 = new com.mac.af.component.derived.display.label.CDLabel();
        chkActive = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboRoute = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return getRouts();
            }
        };
        txtCreditLimit = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        cboCategory = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return getCategory();
            }

        };
        txtAddressLine2 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtAddressLine3 = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtCreditPeriod = new com.mac.af.component.derived.input.textfield.CIIntegerField();

        cDLabel1.setText("Code:");

        lblRoute.setText("Route:");

        cDLabel3.setText("Contact Person:");

        cDLabel5.setText("Name:");

        cDLabel7.setText("Address :");

        cDLabel8.setText("Category:");

        cDLabel9.setText("Credit Limit:");

        cDLabel10.setText("Mobile:");

        cDLabel11.setText("Telephone :");

        cDLabel13.setText("Fax:");

        cDLabel14.setText("Email :");

        cDLabel15.setText("Credit Period:");

        chkActive.setText("Active");

        txtCreditLimit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtCreditPeriod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cDLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRoute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboRoute, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(txtContactPerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddressLine2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddressLine3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddressLine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelephone3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelephone2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCreditLimit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtCreditPeriod, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMobile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelephone1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddressLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddressLine2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddressLine3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelephone3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel10;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel11;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel13;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel14;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel15;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel5;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel7;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel8;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel9;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboCategory;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboRoute;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkActive;
    private com.mac.af.component.derived.display.label.CDLabel lblRoute;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddressLine1;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddressLine2;
    private com.mac.af.component.derived.input.textfield.CIStringField txtAddressLine3;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    private com.mac.af.component.derived.input.textfield.CIStringField txtContactPerson;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtCreditLimit;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtCreditPeriod;
    private com.mac.af.component.derived.input.textfield.CIStringField txtEmail;
    private com.mac.af.component.derived.input.textfield.CIStringField txtFax;
    private com.mac.af.component.derived.input.textfield.CIStringField txtMobile;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTelephone1;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTelephone2;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTelephone3;
    // End of variables declaration//GEN-END:variables
    private static boolean isNew;

    @Override
    protected List<Component> getIdentityComponents() {
        return Arrays.asList((Component) txtCode);
    }

    @Override
    protected List<Component> getEssentialComponents() {
        return Arrays.asList((Component) txtName);
    }

    @Override
    protected List<Component> getOtherFieldComponents() {
        return Arrays.asList(
                (Component) txtContactPerson,
                (Component) txtTelephone2,
                (Component) txtTelephone1,
                (Component) cboRoute,
                (Component) txtCreditPeriod,
                (Component) txtMobile,
                (Component) txtTelephone3,
                (Component) txtFax,
                (Component) txtEmail,
                (Component) cboCategory,
                (Component) txtAddressLine1,
                (Component) txtAddressLine2,
                (Component) txtAddressLine3,
                (Component) txtCreditLimit,
                //(Component) chkClient,
                //(Component) chkSupplier,
                //(Component) chkDealer,
                (Component) chkActive);
    }

    @Override
    protected List<CInputComponentBinder> getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtAddressLine1, "addressLine1"),
                new CInputComponentBinder(txtAddressLine2, "addressLine2"),
                new CInputComponentBinder(txtAddressLine3, "addressLine3"),
                new CInputComponentBinder(cboCategory, "MTransactorCategory"),
                new CInputComponentBinder(txtCreditLimit, "creditLimit"),
                new CInputComponentBinder(txtCreditPeriod, "creditPeriod"),
                new CInputComponentBinder(cboRoute, "MRoute"),
                new CInputComponentBinder(txtEmail, "email"),
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(txtFax, "fax"),
                new CInputComponentBinder(txtTelephone3, "telephone3"),
                new CInputComponentBinder(txtMobile, "mobile"),
                new CInputComponentBinder(txtName, "name"),
                new CInputComponentBinder(txtContactPerson, "contactPerson"),
                // new CInputComponentBinder((CInputComponent) txtCreditPeriod, "creditPeriod"),
                new CInputComponentBinder(txtTelephone1, "telephone1"),
                new CInputComponentBinder(txtTelephone2, "telephone2"),
                // new CInputComponentBinder(chkClient, "client"),
                // new CInputComponentBinder(chkSupplier, "supplier"),
                // new CInputComponentBinder(chkDealer, "dealer"),
                new CInputComponentBinder(chkActive, "active"));
    }

    @Override
    protected Class<? extends MTransactor> getObjectClass() {
        return MTransactor.class;
    }

    @Override
    protected void afterInitObject(MTransactor object) throws ObjectCreatorException {
        super.afterInitObject(object);
        object.setHash(HashUtil.getHash(object.getCode()));
        object.setBranch(HashUtil.getBranch());

        ButtonGroup btnGroup = new ButtonGroup();
        //btnGroup.add(chkClient); 
        //btnGroup.add(chkDealer);
        // btnGroup.add(chkSupplier);

        MTransactor mTransactor = object;
        mTransactor.setClient(isClient);
        mTransactor.setSupplier(isSupplier);
    }

    @Override
    protected void initInterface() throws ObjectCreatorException {
        super.initInterface();
        if (isSupplier == true) {
            lblRoute.setVisible(false);
            cboRoute.setVisible(false);
        }
    }

    @Override
    public void setNewMood() {
        super.setNewMood();
        isNew = true;
    }

    @Override
    public void setEditMood() {
        super.setEditMood();
        isNew = false;
    }

    @Override
    public void setIdleMood() {
        super.setIdleMood();
        isNew = false;
    }

    private void generateCode() {
        if (isNew) {
            int numbers;
            
            String prefix = cboCategory.getCValue() != null
                    ? ((MTransactorCategory) cboCategory.getCValue()).getCode()
                    : "";
            prefix = (isClient?"C":"S") + prefix;

            if (isClient) {
                numbers = SystemSettingInterface.getSystemSetting(SystemSettingInterface.CLIENT_AUTO_CODE).valueAsInteger();
                if (numbers != 0) {
                    try {
                        String code = AutoCodeUtil.getCode(prefix, numbers, "m_transactor", "branch='" + HashUtil.getBranch() + "' AND client=true "/* + (!prefix.isEmpty() ? "AND category = '" + prefix + "'" : "")*/);
                        txtCode.setCValue(code);
                    } catch (ApplicationException ex) {

                        Logger.getLogger(PCCompany.class.getName()).log(Level.SEVERE, null, ex);
                        mOptionPane.showMessageDialog(null, "Unable to auto create code. Please check settings.", "Code Generator", mOptionPane.WARNING_MESSAGE);
                    }
                }
            } else if (isSupplier) {
                numbers = SystemSettingInterface.getSystemSetting(SystemSettingInterface.SUPPLIER_AUTO_CODE).valueAsInteger();
                if (numbers != 0) {
                    try {
                        String code = AutoCodeUtil.getCode(prefix, numbers, "m_transactor", "branch='" + HashUtil.getBranch() + "' AND supplier=true " + (!prefix.isEmpty() ? "AND category = '" + prefix + "'" : ""));
                        txtCode.setCValue(code);
                    } catch (ApplicationException ex) {
                        Logger.getLogger(PCCompany.class.getName()).log(Level.SEVERE, null, ex);
                        mOptionPane.showMessageDialog(null, "Unable to auto create code. Please check settings.", "Code Generator", mOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }
}
