/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.reports.report_parameter;

import com.mac.af.component.base.textfield.CTextField;
import com.mac.af.component.derived.input.CInputComponent;
import com.mac.af.component.derived.input.combobox.CIComboBox;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.reports.report_parameter.object.MBranch;
import com.mac.reports.report_parameter.object.MDepartment;
import com.mac.reports.report_parameter.object.MEmployee;
import com.mac.reports.report_parameter.object.MItem;
import com.mac.reports.report_parameter.object.MItemProperty;
import com.mac.reports.report_parameter.object.MMainCategory;
import com.mac.reports.report_parameter.object.MRoute;
import com.mac.reports.report_parameter.object.MStore;
import com.mac.reports.report_parameter.object.MSubCategory;
import com.mac.reports.report_parameter.object.MTransactor;
import com.mac.reports.report_parameter.object.MTransactorCategory;
import com.mac.reports.report_parameter.transaction_type_object.RTransactionType;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import net.sf.jasperreports.engine.JRParameter;

/**
 *
 * @author Udayanga
 */
public class PCParameterList extends AbstractObjectCreator<HashMap<String, Object>> {

    private Map<String, Component> labels;
    private Map<String, Component> components;
    private SERReportParameter service;
    private List<JRParameter> reportsParameters;//required parameters for the report
    //
    private List<String> acceptableParameters;//parameters which is in possible list
    //
    private PCParameter nonAcceptableParameterPanel;

    /**
     * Creates new form PCParameterList
     */
    public PCParameterList() {
        service = new SERReportParameter(this);

        initComponents();
        initOthers();
        initComponentMaps();
        initNonAcceptableParams();
    }

    public void setReportParameters(List<JRParameter> parameters) {
        this.reportsParameters = parameters;

        showRequiredComponents();

        showNonAcceptableParams();
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        cboBranch.setExpressEditable(true);
        cboBrand.setExpressEditable(true);
        cboClient.setExpressEditable(true);
        cboDepartment.setExpressEditable(true);
        cboEmployee.setExpressEditable(true);
        cboGenaric.setExpressEditable(true);
        cboItem.setExpressEditable(true);
        cboSubCategory.setExpressEditable(true);
        cboMainCategory.setExpressEditable(true);
        cboMake.setExpressEditable(true);
        cboModel.setExpressEditable(true);
        cboRoute.setExpressEditable(true);
        cboSize.setExpressEditable(true);
        cboStore.setExpressEditable(true);
        cboSupplier.setExpressEditable(true);
        cboTransactionCategory.setExpressEditable(true);
        cboTransactionType.setExpressEditable(true);
        cboUnit.setExpressEditable(true);
    }

    private void initComponentMaps() {

        labels = new HashMap<>();
        labels.put(ParameterData.AS_AT_DATE, lblAsAtDate);
        labels.put(ParameterData.BRANCH, lblBranch);
        labels.put(ParameterData.BRAND, lblBrand);
        labels.put(ParameterData.CLIENT, lblClient);
        labels.put(ParameterData.DEPARTMENT, lblDepartment);
        labels.put(ParameterData.EMPLOYEE, lblEmployee);
        labels.put(ParameterData.FROME_DATE, lblFromDate);
        labels.put(ParameterData.GENERIC, lblGenaric);
        labels.put(ParameterData.ITEM, lblItem);
        labels.put(ParameterData.SUB_CATEGORY, lblJobCategory);
        labels.put(ParameterData.MAIN_CATEGORY, lblMainCategory);
        labels.put(ParameterData.MAKE, lblMake);
        labels.put(ParameterData.MODEL, lblModel);
        labels.put(ParameterData.ROUTE, lblRoute);
        labels.put(ParameterData.SIZE, lblSize);
        labels.put(ParameterData.STORE, lblStore);
        labels.put(ParameterData.SUPPLIER, lblSupplier);
        labels.put(ParameterData.TO_DATE, lblToDate);
        labels.put(ParameterData.TRANSACTOR_CATEGORY, lblTransactorCategory);
        labels.put(ParameterData.TRANSACTION_TYPE, lblTransactionType);
        labels.put(ParameterData.UNIT, lblUnit);

        components = new HashMap<>();
        components.put(ParameterData.AS_AT_DATE, txtAsAtDate);
        components.put(ParameterData.BRANCH, cboBranch);
        components.put(ParameterData.BRAND, cboBrand);
        components.put(ParameterData.CLIENT, cboClient);
        components.put(ParameterData.DEPARTMENT, cboDepartment);
        components.put(ParameterData.EMPLOYEE, cboEmployee);
        components.put(ParameterData.FROME_DATE, txtFromDate);
        components.put(ParameterData.GENERIC, cboGenaric);
        components.put(ParameterData.ITEM, cboItem);
        components.put(ParameterData.SUB_CATEGORY, cboSubCategory);
        components.put(ParameterData.MAIN_CATEGORY, cboMainCategory);
        components.put(ParameterData.MAKE, cboMake);
        components.put(ParameterData.MODEL, cboModel);
        components.put(ParameterData.ROUTE, cboRoute);
        components.put(ParameterData.SIZE, cboSize);
        components.put(ParameterData.STORE, cboStore);
        components.put(ParameterData.SUPPLIER, cboSupplier);
        components.put(ParameterData.TO_DATE, txtToDate);
        components.put(ParameterData.TRANSACTOR_CATEGORY, cboTransactionCategory);
        components.put(ParameterData.TRANSACTION_TYPE, cboTransactionType);
        components.put(ParameterData.UNIT, cboUnit);
    }

    private void initNonAcceptableParams() {
        nonAcceptableParameterPanel = new PCParameter();
        pnlParameter.setLayout(new BoxLayout(pnlParameter, BoxLayout.LINE_AXIS));
        pnlParameter.add(nonAcceptableParameterPanel);
    }

    private void showNonAcceptableParams() {
        List<JRParameter> nonAccetpableParams = new ArrayList<>();

        boolean acceptable;
        for (JRParameter reportParameter : reportsParameters) {
            acceptable = false;

            for (String string : acceptableParameters) {
                if (string.equals(reportParameter.getName())) {
                    acceptable = true;
                }
            }

            if (!acceptable) {
                nonAccetpableParams.add(reportParameter);
            }
        }

        nonAcceptableParameterPanel.setReportParameters(nonAccetpableParams);
    }

    private void showRequiredComponents() {
        acceptableParameters = new ArrayList<>();

        for (String string : ParameterData.POSSIBILITES) {
            labels.get(string).setVisible(false);
            components.get(string).setVisible(false);
        }

        for (JRParameter parameter : reportsParameters) {
            for (String possibilites : ParameterData.POSSIBILITES) {
                if (possibilites.equals(parameter.getName())) {
                    acceptableParameters.add(possibilites);

                    labels.get(possibilites).setVisible(true);
                    components.get(possibilites).setVisible(true);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDepartment = new com.mac.af.component.derived.display.label.CDLabel();
        cboDepartment = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getDepartments();
            }
        }
        ;
        lblMainCategory = new com.mac.af.component.derived.display.label.CDLabel();
        cboMainCategory = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return service.getMainCategories();
            }
        }
        ;
        lblJobCategory = new com.mac.af.component.derived.display.label.CDLabel();
        cboSubCategory = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return service.getSubCategories();
            }

        }

        ;
        lblEmployee = new com.mac.af.component.derived.display.label.CDLabel();
        cboEmployee = new com.mac.af.component.derived.input.combobox.CIComboBox();
        lblStore = new com.mac.af.component.derived.display.label.CDLabel();
        cboStore = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getStores();
            }
        };
        cboSupplier = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getSuppliers();
            }
        }
        ;
        lblClient = new com.mac.af.component.derived.display.label.CDLabel();
        cboClient = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getClients();
            }
        }
        ;
        lblSupplier = new com.mac.af.component.derived.display.label.CDLabel();
        lblItem = new com.mac.af.component.derived.display.label.CDLabel();
        cboItem = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getItems();
            }
        };
        lblBranch = new com.mac.af.component.derived.display.label.CDLabel();
        cboBranch = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return service.getBranches();
            }
        }

        ;
        lblRoute = new com.mac.af.component.derived.display.label.CDLabel();
        cboRoute = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getRouts();
            }
        };
        lblBrand = new com.mac.af.component.derived.display.label.CDLabel();
        cboBrand = new com.mac.af.component.derived.input.combobox.CIComboBox()
        {
            @Override
            public List getComboData(){
                return service.getBrands();
            }
        }
        ;
        lblMake = new com.mac.af.component.derived.display.label.CDLabel();
        cboMake = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getMakes();
            }
        }
        ;
        cboModel = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getModels();
            }
        }
        ;
        lblModel = new com.mac.af.component.derived.display.label.CDLabel();
        lblSize = new com.mac.af.component.derived.display.label.CDLabel();
        cboSize = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getSizes();
            }
        }
        ;
        lblUnit = new com.mac.af.component.derived.display.label.CDLabel();
        cboUnit = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getUnits();
            }
        }
        ;
        cboGenaric = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getGenarics();
            }
        }
        ;
        lblGenaric = new com.mac.af.component.derived.display.label.CDLabel();
        lblTransactorCategory = new com.mac.af.component.derived.display.label.CDLabel();
        cboTransactionCategory = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return service.getTransactorCategories();
            }
        };
        lblFromDate = new com.mac.af.component.derived.display.label.CDLabel();
        txtFromDate = new com.mac.af.component.derived.input.textfield.CIDateField();
        lblToDate = new com.mac.af.component.derived.display.label.CDLabel();
        txtToDate = new com.mac.af.component.derived.input.textfield.CIDateField();
        txtAsAtDate = new com.mac.af.component.derived.input.textfield.CIDateField();
        lblAsAtDate = new com.mac.af.component.derived.display.label.CDLabel();
        pnlParameter = new javax.swing.JPanel();
        lblTransactionType = new com.mac.af.component.derived.display.label.CDLabel();
        cboTransactionType = new com.mac.af.component.derived.input.combobox.CIComboBox(){

            @Override
            public List getComboData(){
                return service.getTransactionType();
            }
        };

        lblDepartment.setText("Department :");

        lblMainCategory.setText("Main Category:");

        lblJobCategory.setText("Sub Category:");

        lblEmployee.setText("Employee:");

        lblStore.setText("Store:");

        lblClient.setText("Client:");

        lblSupplier.setText("Supplier:");

        lblItem.setText("Item:");

        lblBranch.setText("Branch:");

        lblRoute.setText("Route:");

        lblBrand.setText("Brand:");

        lblMake.setText("Make:");

        lblModel.setText("Model:");

        lblSize.setText("Size:");

        lblUnit.setText("Unit:");

        lblGenaric.setText("Genaric:");

        lblTransactorCategory.setText("Transactor Category:");

        lblFromDate.setText("From Date:");

        lblToDate.setText("To Date:");

        lblAsAtDate.setText("As At Date:");

        pnlParameter.setLayout(new javax.swing.BoxLayout(pnlParameter, javax.swing.BoxLayout.LINE_AXIS));

        lblTransactionType.setText("Transaction Type:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlParameter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJobCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGenaric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTransactorCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblToDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAsAtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMainCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSubCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboStore, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboClient, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboItem, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboBranch, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboRoute, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboBrand, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboMake, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboModel, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboSize, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboUnit, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboGenaric, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(cboTransactionCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFromDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtToDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAsAtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTransactionType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblMainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblJobCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblGenaric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGenaric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTransactorCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTransactionCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblToDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtAsAtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAsAtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlParameter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.input.combobox.CIComboBox cboBranch;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboBrand;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboClient;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboDepartment;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboEmployee;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboGenaric;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboItem;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboMainCategory;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboMake;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboModel;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboRoute;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSize;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboStore;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSubCategory;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSupplier;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboTransactionCategory;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboTransactionType;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboUnit;
    private com.mac.af.component.derived.display.label.CDLabel lblAsAtDate;
    private com.mac.af.component.derived.display.label.CDLabel lblBranch;
    private com.mac.af.component.derived.display.label.CDLabel lblBrand;
    private com.mac.af.component.derived.display.label.CDLabel lblClient;
    private com.mac.af.component.derived.display.label.CDLabel lblDepartment;
    private com.mac.af.component.derived.display.label.CDLabel lblEmployee;
    private com.mac.af.component.derived.display.label.CDLabel lblFromDate;
    private com.mac.af.component.derived.display.label.CDLabel lblGenaric;
    private com.mac.af.component.derived.display.label.CDLabel lblItem;
    private com.mac.af.component.derived.display.label.CDLabel lblJobCategory;
    private com.mac.af.component.derived.display.label.CDLabel lblMainCategory;
    private com.mac.af.component.derived.display.label.CDLabel lblMake;
    private com.mac.af.component.derived.display.label.CDLabel lblModel;
    private com.mac.af.component.derived.display.label.CDLabel lblRoute;
    private com.mac.af.component.derived.display.label.CDLabel lblSize;
    private com.mac.af.component.derived.display.label.CDLabel lblStore;
    private com.mac.af.component.derived.display.label.CDLabel lblSupplier;
    private com.mac.af.component.derived.display.label.CDLabel lblToDate;
    private com.mac.af.component.derived.display.label.CDLabel lblTransactionType;
    private com.mac.af.component.derived.display.label.CDLabel lblTransactorCategory;
    private com.mac.af.component.derived.display.label.CDLabel lblUnit;
    private javax.swing.JPanel pnlParameter;
    private com.mac.af.component.derived.input.textfield.CIDateField txtAsAtDate;
    private com.mac.af.component.derived.input.textfield.CIDateField txtFromDate;
    private com.mac.af.component.derived.input.textfield.CIDateField txtToDate;
    // End of variables declaration//GEN-END:variables
    private HashMap<String, Object> parameters;

    @Override
    public void setNewMood() {
    }

    @Override
    public void setEditMood() {
    }

    @Override
    public void setIdleMood() {
    }

    @Override
    public void resetFields() {
    }

    @Override
    protected void setValueAbstract(HashMap<String, Object> value) throws ObjectCreatorException {
    }

    @Override
    protected HashMap<String, Object> getValueAbstract() throws ObjectCreatorException {
        return parameters;
    }

    @Override
    protected void initInterface() throws ObjectCreatorException {
        showRequiredComponents();
    }

    @Override
    protected void initObject() throws ObjectCreatorException {
        parameters = new HashMap<>();

        //ACCEPTABLE PARAMETERS
        Component component;
        for (String string : acceptableParameters) {
            component = components.get(string);

            if (component instanceof CTextField) {
                parameters.put(string, ((CInputComponent) component).getCValue());
            } else if (component instanceof CIComboBox) {
                switch (string) {
                    case ParameterData.EMPLOYEE:
                        parameters.put(string, ((MEmployee) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.CLIENT:
                        parameters.put(string, ((MTransactor) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.SUPPLIER:
                        parameters.put(string, ((MTransactor) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.STORE:
                        parameters.put(string, ((MStore) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.ITEM:
                        parameters.put(string, ((MItem) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.BRANCH:
                        parameters.put(string, ((MBranch) ((CInputComponent) component).getCValue()).getCode());
                        break;
                    case ParameterData.ROUTE:
                        parameters.put(string, ((MRoute) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.TRANSACTOR_CATEGORY:
                        parameters.put(string, ((MTransactorCategory) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.DEPARTMENT:
                        parameters.put(string, ((MDepartment) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.MAIN_CATEGORY:
                        parameters.put(string, ((MMainCategory) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.SUB_CATEGORY:
                        parameters.put(string, ((MSubCategory) ((CInputComponent) component).getCValue()).getHash());
                        break;
                    case ParameterData.BRAND:
                        parameters.put(string, ((MItemProperty) ((CInputComponent) component).getCValue()).getIndexNo());
                        break;
                    case ParameterData.MAKE:
                        parameters.put(string, ((MItemProperty) ((CInputComponent) component).getCValue()).getIndexNo());
                        break;
                    case ParameterData.MODEL:
                        parameters.put(string, ((MItemProperty) ((CInputComponent) component).getCValue()).getIndexNo());
                        break;
                    case ParameterData.SIZE:
                        parameters.put(string, ((MItemProperty) ((CInputComponent) component).getCValue()).getIndexNo());
                        break;
                    case ParameterData.UNIT:
                        parameters.put(string, ((MItemProperty) ((CInputComponent) component).getCValue()).getIndexNo());
                        break;
                    case ParameterData.GENERIC:
                        parameters.put(string, ((MItemProperty) ((CInputComponent) component).getCValue()).getIndexNo());
                        break;
                    case ParameterData.TRANSACTION_TYPE:
                        parameters.put(string, ((RTransactionType) ((CInputComponent) component).getCValue()).getCode());
                        break;
                    default:
                }
            }
        }

        //ADDITIONAL FOR NON-ACCEPTABLE PARAMETERS
        parameters.putAll(nonAcceptableParameterPanel.getValue());
    }
}
