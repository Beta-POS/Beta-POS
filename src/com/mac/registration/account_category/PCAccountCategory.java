/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.account_category;

import com.mac.account.AccountTypes;
import com.mac.registration.account_category.object.MAccountCategory;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mac.af.component.derived.input.radiobutton.CRadioButtonGroup;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;

/**
 *
 * @author user
 */
public class PCAccountCategory extends DefaultObjectCreator {

    private CRadioButtonGroup radioButtonGroup;

    /**
     * Creates new form PCAccountCategory
     */
    public PCAccountCategory() {
        initComponents();
        initOther();
    }

    private List<MAccountCategory> getAccountCategories() {
        try {
            HibernateDatabaseService databaseService = getCPanel().getDatabaseService();
            return databaseService.getCollection("from com.mac.registration.account_category.object.MAccountCategory");
        } catch (DatabaseException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private void initOther() {
        radioButtonGroup = new CRadioButtonGroup();

        radioButtonGroup.setDefaultRadioButton(radBalanceSheet);

        radioButtonGroup.addRadioButton(radBalanceSheet);
        radioButtonGroup.addRadioButton(radProfitLost);

        radBalanceSheet.setValue(AccountTypes.BALANCE_SHEET);
        radProfitLost.setValue(AccountTypes.PROFIT_AND_LOSS);

        cboAccountCategory.setExpressEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        cLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        txtCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        cLabel5 = new com.mac.af.component.derived.display.label.CDLabel();
        cboAccountCategory = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return getAccountCategories();
            }
        }
        ;
        radBalanceSheet = new com.mac.af.component.derived.input.radiobutton.CIRadioButton();
        radProfitLost = new com.mac.af.component.derived.input.radiobutton.CIRadioButton();
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();

        cLabel1.setText("Code :");

        cLabel2.setText("Name :");

        cLabel5.setText("Parent:");

        radBalanceSheet.setText("Balance Sheet");

        radProfitLost.setText("Profit & Lost");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboAccountCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(radBalanceSheet, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radProfitLost, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboAccountCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radBalanceSheet, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radProfitLost, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cLabel2;
    private com.mac.af.component.derived.display.label.CDLabel cLabel5;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboAccountCategory;
    private com.mac.af.component.derived.input.radiobutton.CIRadioButton radBalanceSheet;
    private com.mac.af.component.derived.input.radiobutton.CIRadioButton radProfitLost;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List<Component> getIdentityComponents() {
        return Arrays.asList(
                (Component) txtCode);
    }

    @Override
    protected List<Component> getEssentialComponents() {
        return Arrays.asList(
                (Component) txtName);
    }

    @Override
    protected List<Component> getOtherFieldComponents() {
        return Arrays.asList(
                (Component) cboAccountCategory,
                (Component) radBalanceSheet,
                (Component) radProfitLost);
    }

    @Override
    protected List<CInputComponentBinder> getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(txtName, "name"),
                new CInputComponentBinder(cboAccountCategory, "MAccountCategory"),
                new CInputComponentBinder(radioButtonGroup, "type"));
    }

    @Override
    protected Class getObjectClass() {
        return com.mac.registration.account_category.object.MAccountCategory.class;
    }

    @Override
    protected void afterNewObject(Object object) {
    }
}
