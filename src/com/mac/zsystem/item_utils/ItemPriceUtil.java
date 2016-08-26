/*
 *  ItemPriceUtil.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Jan 13, 2015, 3:07:42 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.item_utils;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.database.hibernate.HibernateSQLQuery;
import com.mac.zsystem.item_utils.object.MItem;
import com.mac.zsystem.item_utils.object.MItemBatch;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mohan
 */
public class ItemPriceUtil {

    public void changeItemPrice(HibernateDatabaseService databaseService, String itemCode) throws DatabaseException {
        MItem item = (MItem) databaseService.getObject(MItem.class, itemCode);

        //item found
        if (item != null) {
            MItemBatch itemBatch = getLastItemBatch(databaseService, itemCode);
            if (itemBatch==null) {
                itemBatch = new MItemBatch();
                itemBatch.setMItem(item);
            }
            
            //is batch item
            if (item.isBatch()) {
                
            }else{
                
            }
        }
    }

    //private
    private MItemBatch getLastItemBatch(HibernateDatabaseService databaseService, String itemCode) throws DatabaseException {
        String hql = "SELECT * FROM m_item_batch WHERE m_item_batch.item = :ITEM_CODE HAVING MAX(m_item_batch.index_no)";
        HibernateSQLQuery query = new HibernateSQLQuery(hql, MItemBatch.class);

        HashMap<String, Object> params = new HashMap<>();
        params.put("ITEM_CODE", itemCode);

        List<MItemBatch> itemBatchs = databaseService.getCollection(query, params);

        MItemBatch itemBatch;
        if (!itemBatchs.isEmpty()) {//latest item batch in collection
            itemBatch = itemBatchs.get(0);
        } else {//no batches found
            itemBatch = null;
        }

        return itemBatch;
    }
}
