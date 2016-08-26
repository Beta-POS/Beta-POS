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
public class BranchTableModel extends CTableModel {

    public BranchTableModel() {
        super(
                new CTableColumn("Code", new String[]{"code"}),
                new CTableColumn("Name", new String[]{"name"}));
    }
}
