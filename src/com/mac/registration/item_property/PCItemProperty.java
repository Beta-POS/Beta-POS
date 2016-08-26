/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.item_property;

import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.registration.item_property.object.MItemProperty;
import com.mac.zsystem.util.hash.HashUtil;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user
 */
public abstract class PCItemProperty extends DefaultObjectCreator<MItemProperty> {

    /**
     * Creates new form PCItemProperty
     */
    public PCItemProperty() {
        initComponents();
        initOthers();
    }
    
    private void initOthers()
    {
//        set max text lengh
        txtName.setLength(50);
    }

    protected abstract String getType();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();

        cDLabel2.setText("Name :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List getIdentityComponents() {
        return Arrays.asList();
    }

    @Override
    protected List getEssentialComponents() {
        return Arrays.asList(txtName);
    }

    @Override
    protected List getOtherFieldComponents() {
        return Arrays.asList();
    }

    @Override
    protected List getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtName, "name"));
    }

    @Override
    protected Class getObjectClass() {
        return com.mac.registration.item_property.object.MItemProperty.class;
    }

    @Override
    protected void afterInitObject(MItemProperty object) throws ObjectCreatorException {
        super.afterInitObject(object);
        object.setType(getType());
        object.setBranch(HashUtil.getBranch());
    }
}
