/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.model.table_model.sub_category;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;

/**
 *
 * @author thilanga
 */
public class SubCategoryTableModel extends CTableModel {

    public SubCategoryTableModel() {
        super(new CTableColumn("Code", new String[]{"code"}),
                new CTableColumn("Name", new String[]{"name"}));
    }
}
