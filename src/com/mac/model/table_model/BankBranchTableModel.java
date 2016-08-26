/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.model.table_model;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;

/**
 *
 * @author thilanga
 */
public class BankBranchTableModel extends CTableModel {

    public BankBranchTableModel() {
        super(new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"));
    }
}
