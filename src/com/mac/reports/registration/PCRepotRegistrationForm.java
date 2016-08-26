/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.reports.registration;

import com.mac.af.component.base.button.action.Action;
import com.mac.af.component.base.button.action.ActionUtil;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.af.resources.ApplicationResources;
import com.mac.reports.object.MReport;
import com.mac.reports.registration.highlighter.XmlEditorKit;
import exersices.Readers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author user
 */
public class PCRepotRegistrationForm extends DefaultObjectCreator<MReport> {

    /**
     * Creates new form PCRepotRegistrationForm
     */
    public PCRepotRegistrationForm() {
        initComponents();
        initOthers();
    }

    @Action(asynchronous = false)
    public void doBrowseReports() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);
        File selectedPfile = chooser.getSelectedFile();

        if (selectedPfile != null) {
            String path = selectedPfile.getPath();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedPfile));

                StringBuilder stringBuilder = new StringBuilder();
                String text;
                while ((text=reader.readLine())!=null) {
                    stringBuilder.append(text);
                    stringBuilder.append("\n");
                }
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                txtReport.setText(stringBuilder.toString());
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PCRepotRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PCRepotRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String[] getComboData() {
        return ReportCategories.All;
    }

    private void initOthers() {
        //set maximum textbox with
        txtCode.setLength(10);
        txtName.setLength(50);

        ActionUtil actionUtil = new ActionUtil(this);
        actionUtil.setAction(btnBrowseReport, "doBrowseReports");

        btnBrowseReport.setIcon(ApplicationResources.getImageIcon(ApplicationResources.ACTION_OPEN, 16, 16));


        txtReport.setEditorKitForContentType("text/xml", new XmlEditorKit());
        txtReport.setContentType("text/xml");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cDLabel1 = new com.mac.af.component.derived.display.label.CDLabel();
        txtCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        cboCategory = new com.mac.af.component.derived.input.combobox.CIComboBox(getComboData());
        cDLabel4 = new com.mac.af.component.derived.display.label.CDLabel();
        btnBrowseReport = new com.mac.af.component.derived.command.button.CCButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtReport = new javax.swing.JTextPane();

        cDLabel1.setText("Code :");

        cDLabel2.setText("Name :");

        cDLabel3.setText("Category :");

        cDLabel4.setText("Report :");

        btnBrowseReport.setText("Browse");
        btnBrowseReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseReportActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtReport);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cboCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBrowseReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBrowseReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBrowseReportActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.command.button.CCButton btnBrowseReport;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel4;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboCategory;
    private javax.swing.JScrollPane jScrollPane2;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    private javax.swing.JTextPane txtReport;
    // End of variables declaration//GEN-END:variables

    @Override
    protected List getIdentityComponents() {
        return Arrays.asList(txtCode);
    }

    @Override
    protected List getEssentialComponents() {
        return Arrays.asList(
                txtName,
                cboCategory,
                txtReport);

    }

    @Override
    protected List getOtherFieldComponents() {
        return Arrays.asList();

    }

    @Override
    protected List getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(txtName, "name"),
                new CInputComponentBinder(cboCategory, "category"));

    }

    @Override
    protected Class getObjectClass() {
        return com.mac.reports.object.MReport.class;
    }

    @Override
    protected void afterInitObject(MReport object) throws ObjectCreatorException {
        object.setReport(txtReport.getText().getBytes());
    }

    @Override
    protected void initInterface() throws ObjectCreatorException {
        super.initInterface();

        MReport mReport = getValueAbstract();

        if (mReport != null) {
            txtReport.setText(new String(mReport.getReport() != null ? mReport.getReport() : new byte[]{}));
        } else {
            txtReport.setText("");
        }
    }

    @Override
    public void setNewMood() {
        super.setNewMood();

        btnBrowseReport.setEnabled(true);
    }

    @Override
    public void setEditMood() {
        super.setEditMood();

        btnBrowseReport.setEnabled(true);
    }

    @Override
    public void setIdleMood() {
        super.setIdleMood();

        btnBrowseReport.setEnabled(false);
    }
}
