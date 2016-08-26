   /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.item;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.environment.CApplication;
import com.mac.af.panel.object.CInputComponentBinder;
import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.model.table_model.item.ItemBrandTableModel;
import com.mac.model.table_model.item.ItemDepartmentTableModel;
import com.mac.model.table_model.item.ItemGenaricTableModel;
import com.mac.model.table_model.item.ItemMainCategoryTableModel1;
import com.mac.model.table_model.item.ItemMakeTableModel;
import com.mac.model.table_model.item.ItemModelTableModel;
import com.mac.model.table_model.item.ItemSizeTableModel;
import com.mac.model.table_model.item.ItemSubCategoryTableModel;
import com.mac.model.table_model.item.ItemUnitTableModel;
import com.mac.registration.item.object.MDepartment;
import com.mac.registration.item.object.MItem;
import com.mac.registration.item.object.MItemSettings;
import com.mac.registration.item.object.MMainCategory;
import com.mac.registration.item.object.MSubCategory;
import com.mac.registration.item_price_change.PriceCalculation;
import com.mac.zsystem.settings.system_settings.SystemSettingInterface;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.AutoCodeUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thilanga
 */
public class PCItem extends DefaultObjectCreator<MItem> {

    public SERItem serItem;
    private MDepartment department;
    private MMainCategory mainCategory;

    /**
     * Creates new form PCItem
     */
    public PCItem(SERItem serItem) {
        initComponents();
        initOthers();
        this.serItem = serItem;
    }

    private void calculateDefaultPrices() {
        double costPrice = txtCostPrice.getCValue();
        double salesMargin = txtSaleMargin.getCValue();
        double wholesaleMargin = txtWholeSalePriceMargin.getCValue();
        double maxDiscountPercent = txtMaxDiscountPrecent.getCValue();

        double salesPrice = PriceCalculation.getSalesPrice(costPrice, salesMargin);
        double lastSalesPrice = PriceCalculation.getLastSalePrice(salesPrice, maxDiscountPercent);
        double wholesalePrice = PriceCalculation.getWholeSalePrice(costPrice, wholesaleMargin);

        txtSalesPrice.setCValue(salesPrice);
        txtLastSalePrice.setCValue(lastSalesPrice);
        txtWholeSalePrice.setCValue(wholesalePrice);
    }

    private void calculateDefaultPercentages() {
        double costPrice = txtCostPrice.getCValue();
        double salesPrice = txtSalesPrice.getCValue();
        double lastSalesPrice = txtLastSalePrice.getCValue();
        double wholeSalePrice = txtWholeSalePrice.getCValue();

        double salesMargin = (salesPrice - costPrice) / costPrice * 100;
        double wholeSalesMargin = (wholeSalePrice - costPrice) / costPrice * 100;
        double maxDiscountPercent = (salesPrice - lastSalesPrice) / salesPrice * 100;

        salesMargin = getCorrectNumber(salesMargin);
        wholeSalesMargin = getCorrectNumber(wholeSalesMargin);
        maxDiscountPercent = getCorrectNumber(maxDiscountPercent);

        txtSaleMargin.setCValue(salesMargin);
        txtWholeSalePriceMargin.setCValue(wholeSalesMargin);
        txtMaxDiscountPrecent.setCValue(maxDiscountPercent);
    }

    private double getCorrectNumber(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return 0.0;
        } else {
            return d;
        }
    }

    private void generateItemCode() {
        MDepartment department = (MDepartment) cboDepartment.getCValue();
        MMainCategory mainCategory = (MMainCategory) cboMainCategory.getCValue();
        MSubCategory subCategory = (MSubCategory) cboSubCategory.getCValue();

        String where = "1=1";

        String code = "";
        if (department != null) {
            code = department.getCode();
            where = where + " AND department = '" + department.getHash() + "'";
        }
        if (mainCategory != null) {
            code = mainCategory.getCode();
            where = where + " AND main_category = '" + mainCategory.getHash() + "'";
        }
        if (subCategory != null) {
            code = subCategory.getCode();
            where = where + " AND sub_category = '" + subCategory.getHash() + "'";
        }

        try {
//            int number = IndexNumberUtil.getNextIndex("m_item", where);
//            String format = "0000";
//
//            DecimalFormat decimalFormat = new DecimalFormat(format);
//            code = code + decimalFormat.format(number);

            int numbers = SystemSettingInterface.getSystemSetting(SystemSettingInterface.ITEM_AUTO_CODE).valueAsInteger();

            code = AutoCodeUtil.getCode(code, numbers, "m_item", where);

            txtCode.setCValue(code);
        } catch (ApplicationException ex) {
            Logger.getLogger(PCItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {

        txtCode.setLength(10);
        txtBarCode.setLength(25);
        txtName.setLength(50);
        txtShelf.setLength(50);

        cboDepartment.setExpressEditable(true);
        cboDepartment.setTableModel(new ItemDepartmentTableModel());
        cboMainCategory.setExpressEditable(true);
        cboMainCategory.setTableModel(new ItemMainCategoryTableModel1());
        cboSubCategory.setExpressEditable(true);
        cboSubCategory.setTableModel(new ItemSubCategoryTableModel());

        cboBrand.setExpressEditable(true);
        cboBrand.setTableModel(new ItemBrandTableModel());
        cboMake.setExpressEditable(true);
        cboMake.setTableModel(new ItemMakeTableModel());
        cboModel.setExpressEditable(true);
        cboModel.setTableModel(new ItemModelTableModel());
        cboSize.setExpressEditable(true);
        cboSize.setTableModel(new ItemSizeTableModel());
        cboUnit.setExpressEditable(true);
        cboUnit.setTableModel(new ItemUnitTableModel());
        cboGenaric.setExpressEditable(true);
        cboGenaric.setTableModel(new ItemGenaricTableModel());

        //Test
        cboDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!preventStateChange) {
                    department = (MDepartment) cboDepartment.getCValue();
                    cboMainCategory.doRefresh();
                    cboMainCategory.setCValue(null);
                }
//                generateItemCode();
            }
        });

        cboMainCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!preventStateChange) {
                    mainCategory = (MMainCategory) cboMainCategory.getCValue();
                    cboSubCategory.doRefresh();
                    cboSubCategory.setCValue(null);
                }
//                generateItemCode();
            }
        });


//        cboSubCategory.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                generateItemCode();
//            }
//        });

        FocusAdapter codeGen = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                generateItemCode();
            }
        };
        txtCode.addFocusListener(codeGen);



        FocusAdapter focusAdapter = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateDefaultPrices();
            }
        };

        txtCostPrice.addFocusListener(focusAdapter);
        txtSaleMargin.addFocusListener(focusAdapter);
        txtMaxDiscountPrecent.addFocusListener(focusAdapter);
        txtWholeSalePriceMargin.addFocusListener(focusAdapter);


        FocusAdapter percentAdapter = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateDefaultPercentages();
            }
        };

        txtCostPrice.addFocusListener(percentAdapter);
        txtSalesPrice.addFocusListener(percentAdapter);
        txtLastSalePrice.addFocusListener(percentAdapter);
        txtWholeSalePrice.addFocusListener(percentAdapter);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        chkSerialNumber = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboGenaric = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getItemProperties(ItemPropertyTypes.GENERIC);
            }
        };
        cboMainCategory = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getMainCategory(department);
            }

            @Override
            protected void afterRefresh(){
                try {
                    MItem item;
                    if ((item = getValueAbstract()) != null) {
                        setCValue(item.getMMainCategory());
                    }
                } catch (ObjectCreatorException ex) {
                    Logger.getLogger(PCItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        chkServiceItem = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboSize = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getItemProperties(ItemPropertyTypes.SIZE);
            }
        };
        txtName = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel13 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel7 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel8 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel11 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel3 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel2 = new com.mac.af.component.derived.display.label.CDLabel();
        cboDepartment = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getDepartments();
            }

            @Override
            protected void afterRefresh(){
                try {
                    MItem item;
                    if ((item = getValueAbstract()) != null) {
                        setCValue(item.getMDepartment());
                    }
                } catch (ObjectCreatorException ex) {
                    Logger.getLogger(PCItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        txtShinhalaName = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel5 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel12 = new com.mac.af.component.derived.display.label.CDLabel();
        cboMake = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getItemProperties(ItemPropertyTypes.MAKE);
            }
        };
        txtTamilName = new com.mac.af.component.derived.input.textfield.CIStringField();
        cDLabel10 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel9 = new com.mac.af.component.derived.display.label.CDLabel();
        txtCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        txtBarCode = new com.mac.af.component.derived.input.textfield.CIStringField();
        cboUnit = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getItemProperties(ItemPropertyTypes.UNIT);
            }
        };
        cDLabel14 = new com.mac.af.component.derived.display.label.CDLabel();
        cboSubCategory = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getSubCategory(mainCategory);
            }

            @Override
            protected void afterRefresh(){
                try {
                    MItem item;
                    if ((item = getValueAbstract()) != null) {
                        setCValue(item.getMSubCategory());
                    }
                } catch (ObjectCreatorException ex) {
                    Logger.getLogger(PCItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        cboBrand = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getItemProperties(ItemPropertyTypes.BRAND);
            }
        };
        cDLabel6 = new com.mac.af.component.derived.display.label.CDLabel();
        chkBatchPrice = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        cboModel = new com.mac.af.component.derived.input.combobox.CIComboBox(){
            @Override
            public List getComboData(){
                return serItem.getItemProperties(ItemPropertyTypes.MODEL);
            }
        };
        cDLabel4 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel15 = new com.mac.af.component.derived.display.label.CDLabel();
        chkActive = new com.mac.af.component.derived.input.checkbox.CICheckBox();
        jPanel2 = new javax.swing.JPanel();
        cDLabel16 = new com.mac.af.component.derived.display.label.CDLabel();
        txtSalesPrice = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        txtLastSalePrice = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        cDLabel17 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel18 = new com.mac.af.component.derived.display.label.CDLabel();
        txtWholeSalePrice = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        txtCostPrice = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        cDLabel19 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel20 = new com.mac.af.component.derived.display.label.CDLabel();
        txtMaxDiscountPrecent = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        cDLabel24 = new com.mac.af.component.derived.display.label.CDLabel();
        txtSaleMargin = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        cDLabel25 = new com.mac.af.component.derived.display.label.CDLabel();
        txtWholeSalePriceMargin = new com.mac.af.component.derived.input.textfield.CIDoubleField();
        jPanel3 = new javax.swing.JPanel();
        cDLabel21 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel22 = new com.mac.af.component.derived.display.label.CDLabel();
        cDLabel23 = new com.mac.af.component.derived.display.label.CDLabel();
        txtReordrLevel = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        txtReorderQuantity = new com.mac.af.component.derived.input.textfield.CIIntegerField();
        txtShelf = new com.mac.af.component.derived.input.textfield.CIStringField();

        cDLabel1.setText("Code:");

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        chkSerialNumber.setText("Serial Numbers");
        chkSerialNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSerialNumberActionPerformed(evt);
            }
        });

        chkServiceItem.setText("Service Item");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        cDLabel13.setText("Genaric:");

        cDLabel7.setText("Tamil Name:");

        cDLabel8.setText("Brand:");

        cDLabel11.setText("Size:");

        cDLabel3.setText("Sub Category:");

        cDLabel2.setText("Main Category:");

        txtShinhalaName.setFont(new java.awt.Font("FMBindumathi", 0, 14)); // NOI18N
        txtShinhalaName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtShinhalaNameActionPerformed(evt);
            }
        });

        cDLabel5.setText("Barcode:");

        cDLabel12.setText("Unit:");

        txtTamilName.setFont(new java.awt.Font("Kalaham", 0, 11)); // NOI18N
        txtTamilName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTamilNameActionPerformed(evt);
            }
        });

        cDLabel10.setText("Model:");

        cDLabel9.setText("Make:");

        txtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeActionPerformed(evt);
            }
        });

        cDLabel14.setText("Department:");

        cDLabel6.setText("Shinhala Name:");

        chkBatchPrice.setText("Batch Prices");
        chkBatchPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBatchPriceActionPerformed(evt);
            }
        });

        cDLabel4.setText("Name:");

        cDLabel15.setText("Item Code:");

        chkActive.setText("Active");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cDLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cDLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cDLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMainCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSubCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtShinhalaName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTamilName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMake, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboUnit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboGenaric, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBarCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(147, 147, 147))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkBatchPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(chkSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkServiceItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMainCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtShinhalaName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTamilName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cDLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGenaric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkServiceItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkSerialNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkBatchPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Basic", jPanel1);

        cDLabel16.setText("Sale Price:");

        txtSalesPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSalesPrice.setText("");
        txtSalesPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalesPriceActionPerformed(evt);
            }
        });

        txtLastSalePrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLastSalePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastSalePriceActionPerformed(evt);
            }
        });

        cDLabel17.setText("Last Sale Price:");

        cDLabel18.setText("Whole Sale Price:");

        txtWholeSalePrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtCostPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCostPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostPriceActionPerformed(evt);
            }
        });

        cDLabel19.setText("Cost Price:");

        cDLabel20.setText("Max Discount (%):");

        txtMaxDiscountPrecent.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        cDLabel24.setText("Sale Margin(%):");

        txtSaleMargin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        cDLabel25.setText("Whole Sale Price Margin(%):");

        txtWholeSalePriceMargin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtWholeSalePriceMargin, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(txtLastSalePrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaxDiscountPrecent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSalesPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSaleMargin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCostPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtWholeSalePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaleMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSalesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxDiscountPrecent, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWholeSalePriceMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWholeSalePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Item Price", jPanel2);

        cDLabel21.setText("Reorder Level:");

        cDLabel22.setText("Reorder Quantity:");

        cDLabel23.setText("Shelf:");

        txtReordrLevel.setText("");

        txtReorderQuantity.setText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cDLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReordrLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtReorderQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(txtShelf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReordrLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReorderQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cDLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtShelf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(337, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Item Settings", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkBatchPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBatchPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBatchPriceActionPerformed

    private void txtTamilNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTamilNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamilNameActionPerformed

    private void txtShinhalaNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtShinhalaNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtShinhalaNameActionPerformed

    private void txtSalesPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalesPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalesPriceActionPerformed

    private void txtLastSalePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastSalePriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastSalePriceActionPerformed

    private void txtCostPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostPriceActionPerformed

    private void txtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void chkSerialNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSerialNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSerialNumberActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mac.af.component.derived.display.label.CDLabel cDLabel1;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel10;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel11;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel12;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel13;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel14;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel15;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel16;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel17;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel18;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel19;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel2;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel20;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel21;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel22;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel23;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel24;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel25;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel3;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel4;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel5;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel6;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel7;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel8;
    private com.mac.af.component.derived.display.label.CDLabel cDLabel9;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboBrand;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboDepartment;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboGenaric;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboMainCategory;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboMake;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboModel;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSize;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboSubCategory;
    private com.mac.af.component.derived.input.combobox.CIComboBox cboUnit;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkActive;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkBatchPrice;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkSerialNumber;
    private com.mac.af.component.derived.input.checkbox.CICheckBox chkServiceItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.mac.af.component.derived.input.textfield.CIStringField txtBarCode;
    private com.mac.af.component.derived.input.textfield.CIStringField txtCode;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtCostPrice;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtLastSalePrice;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtMaxDiscountPrecent;
    private com.mac.af.component.derived.input.textfield.CIStringField txtName;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtReorderQuantity;
    private com.mac.af.component.derived.input.textfield.CIIntegerField txtReordrLevel;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtSaleMargin;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtSalesPrice;
    private com.mac.af.component.derived.input.textfield.CIStringField txtShelf;
    private com.mac.af.component.derived.input.textfield.CIStringField txtShinhalaName;
    private com.mac.af.component.derived.input.textfield.CIStringField txtTamilName;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtWholeSalePrice;
    private com.mac.af.component.derived.input.textfield.CIDoubleField txtWholeSalePriceMargin;
    // End of variables declaration//GEN-END:variables
    private boolean preventStateChange = false;

    @Override
    protected List getIdentityComponents() {
        return Arrays.asList(txtCode);
    }

    @Override
    protected List getEssentialComponents() {
        return Arrays.asList(
                cboDepartment,
                cboMainCategory,
                txtName);
    }

    @Override
    protected List getOtherFieldComponents() {
        return Arrays.asList(
                txtBarCode,
                txtShinhalaName,
                txtTamilName,
                cboSubCategory,
                cboBrand,
                cboGenaric,
                cboMake,
                cboModel,
                cboSize,
                cboUnit,
                txtSalesPrice,
                txtLastSalePrice,
                txtWholeSalePrice,
                txtCostPrice,
                txtSaleMargin,
                chkBatchPrice,
                chkSerialNumber,
                chkServiceItem,
                txtWholeSalePriceMargin,
                txtMaxDiscountPrecent,
                chkActive);
    }

    @Override
    protected List getInputComponentBinders() {
        return Arrays.asList(
                new CInputComponentBinder(cboDepartment, "MDepartment"),
                new CInputComponentBinder(cboMainCategory, "MMainCategory"),
                new CInputComponentBinder(cboSubCategory, "MSubCategory"),
                //
                new CInputComponentBinder(txtCode, "code"),
                new CInputComponentBinder(txtBarCode, "barcode"),
                new CInputComponentBinder(txtName, "name"),
                new CInputComponentBinder(txtShinhalaName, "sinhalaName"),
                new CInputComponentBinder(txtTamilName, "tamilName"),
                //
                
                new CInputComponentBinder(cboBrand, "MItemPropertyByBrand"),
                new CInputComponentBinder(cboMake, "MItemPropertyByMake"),
                new CInputComponentBinder(cboModel, "MItemPropertyByModel"),
                new CInputComponentBinder(cboSize, "MItemPropertyBySize"),
                new CInputComponentBinder(cboUnit, "MItemPropertyByUnit"),
                new CInputComponentBinder(cboGenaric, "MItemPropertyByGeneric"),
                //
                new CInputComponentBinder(chkBatchPrice, "batch"),
                new CInputComponentBinder(chkSerialNumber, "serial"),
                new CInputComponentBinder(chkServiceItem, "service"),
                new CInputComponentBinder(chkActive, "active"),
                //
                new CInputComponentBinder(txtCostPrice, "costPrice"),
                new CInputComponentBinder(txtSaleMargin, "saleMargin"),
                new CInputComponentBinder(txtSalesPrice, "salePrice"),
                new CInputComponentBinder(txtMaxDiscountPrecent, "maxDiscountPercent"),
                new CInputComponentBinder(txtLastSalePrice, "lastSalePrice"),
                new CInputComponentBinder(txtWholeSalePriceMargin, "wholeSaleMargin"),
                new CInputComponentBinder(txtWholeSalePrice, "wholeSalePrice")
                
                );
    }

    @Override
    protected Class getObjectClass() {
        return MItem.class;
    }

    @Override
    protected void afterInitObject(MItem object) throws ObjectCreatorException {
        super.afterInitObject(object);

        object.setHash(HashUtil.getHash(object.getCode()));
        object.setBranch(HashUtil.getBranch());

        MItem mItem = (MItem) object;
        if (mItem != null) {
            mItem.setAveragePrice(0.0);

            MItemSettings mItemSettings = getMItemSettings(mItem);
            mItemSettings.setReorderLevel(txtReordrLevel.getCValue());
            mItemSettings.setReorderQuantity(txtReorderQuantity.getCValue());
            mItemSettings.setShelf(txtShelf.getCValue());

            mItem.getMItemSettingses().add(mItemSettings);
        }
    }

    private MItemSettings getMItemSettings(MItem item) {
        String store = (String) CApplication.getSessionVariable(CApplication.STORE_ID);

        MItemSettings returnValue = null;

        if (item != null) {
            for (MItemSettings mItemSettings : item.getMItemSettingses()) {
                if (mItemSettings.getStore() != null ? mItemSettings.getStore().equals(store) : false) {
                    returnValue = mItemSettings;
                }
            }

            if (returnValue == null) {
                returnValue = new MItemSettings();
                returnValue.setHash(HashUtil.getHash(item.getCode()));
                returnValue.setBranch(HashUtil.getBranch());
                returnValue.setMItem(item);
//                returnValue.setStore(store);
            }
        }

        return returnValue;
    }

    @Override
    protected void initInterface() throws ObjectCreatorException {
        super.initInterface();
        MItem mItem = getValueAbstract();

        if (mItem != null) {
            MItemSettings mItemSettings = getMItemSettings(mItem);

            txtReordrLevel.setCValue(mItemSettings.getReorderLevel());
            txtReorderQuantity.setCValue(mItemSettings.getReorderQuantity());
            txtShelf.setCValue(mItemSettings.getShelf());
        }
    }

    @Override
    public void setNewMood() {
        super.setNewMood();

        txtSalesPrice.setValueEditable(true);
        txtLastSalePrice.setValueEditable(true);
        txtWholeSalePrice.setValueEditable(true);
        txtCostPrice.setValueEditable(true);
        
        txtWholeSalePriceMargin.setValueEditable(true);
        txtSaleMargin.setValueEditable(true);
        txtMaxDiscountPrecent.setValueEditable(true);

//        chkKeepSalesPrice.setValueEditable(true);
        //  chkKeepWholeSalePrice.setValueEditable(true);

        //  txtxKeepWholeSalesPricei.setValueEditable(true);
        // txtKeepSalesPricei.setValueEditable(true);

        txtReordrLevel.setValueEditable(true);
        txtReorderQuantity.setValueEditable(true);
        txtShelf.setValueEditable(true);
    }

    @Override
    public void setEditMood() {
        super.setEditMood();

        txtSalesPrice.setValueEditable(false);
        txtLastSalePrice.setValueEditable(false);
        txtWholeSalePrice.setValueEditable(false);
        txtCostPrice.setValueEditable(false);
        
//        txtWholeSalePriceMargin.setValueEditable(false);
//        txtSaleMargin.setValueEditable(false);
//        txtMaxDiscountPrecent.setValueEditable(false);
        txtWholeSalePriceMargin.setValueEditable(true);
        txtSaleMargin.setValueEditable(true);
        txtMaxDiscountPrecent.setValueEditable(true);
        
        // chkKeepSalesPrice.setValueEditable(false);
        //  txtKeepSalesPricei.setValueEditable(false);
        //  chkKeepWholeSalePrice.setValueEditable(false);
        //  txtxKeepWholeSalesPricei.setValueEditable(false);

        txtReordrLevel.setValueEditable(true);
        txtReorderQuantity.setValueEditable(true);
        txtShelf.setValueEditable(true);
    }

    @Override
    public void setIdleMood() {
        super.setIdleMood();
        txtReordrLevel.setValueEditable(false);
        txtReorderQuantity.setValueEditable(false);
        txtShelf.setValueEditable(false);
    }

    @Override
    public void setValue(MItem value) throws ObjectCreatorException {
        super.setValue(value);
//        if (value != null) {
//            preventStateChange = true;
//            cboDepartment.doRefresh();
//            cboDepartment.setCValue(value.getMDepartment());
//
//            cboMainCategory.doRefresh();
//            cboMainCategory.setCValue(value.getMMainCategory());
//
//            cboSubCategory.doRefresh();
//            cboSubCategory.setCValue(value.getMSubCategory());
//            preventStateChange = false;
//        }
    }

    @Override
    public Object getIdentifier() {
        return HashUtil.getHash(txtCode.getCValue());

    }
}
