/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.item_price_change;

import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import com.mac.registration.item_price_change.object.MItem;
import com.mac.registration.transactor_category.object.MTransactorCategory;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.item.ItemBatchUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilanga
 */
public class REGItemPriceChange extends AbstractRegistrationForm<MItem> {

    @Override
    public AbstractObjectCreator getObjectCreator() {
        return new PCItemPriceChange();
    }

    @Override
    public Class getObjectClass() {
        return com.mac.registration.item_price_change.object.MItem.class;
    }

    @Override
    public CTableModel getTableModel() {
        return new CTableModel(
                new CTableColumn("Code", "code"),
                new CTableColumn("Name", "name"),
                new CTableColumn("BarCode", "barcode"));
    }

    @Override
    protected int getRegistrationType() {
        return EDIT_ONLY_REGISTRATION_TYPE;
    }

    @Override
    protected int save(MItem object) throws DatabaseException {
        try {
            ItemBatchUtil.updatePrice(getDatabaseService(),
                    object.getHash(),
                    object.getSalePrice(),
                    object.getLastSalePrice(),
                    object.getWholeSalePrice(),
                    object.getCostPrice());

            return SAVE_SUCCESS;
        } catch (ApplicationException ex) {
            Logger.getLogger(REGItemPriceChange.class.getName()).log(Level.SEVERE, null, ex);
            return SAVE_FAILED;
        }
    }

    @Override
    protected List getTableData() throws DatabaseException {
        Criteria criteria = getDatabaseService().initCriteria(MItem.class);
        criteria.add(Restrictions.eq("branch", HashUtil.getBranch()));
        return criteria.list();
    }
}
