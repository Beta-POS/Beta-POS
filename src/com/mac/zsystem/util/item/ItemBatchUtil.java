/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.zsystem.util.item;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.zsystem.util.hash.HashUtil;
import com.mac.zsystem.util.index.IndexNumberUtil;
import com.mac.zsystem.util.item.object.MItemBatch;
import com.mac.zsystem.util.item.object.MItem;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ItemBatchUtil {

    public static void updatePrice(HibernateDatabaseService databaseService, String itemHash, Double salesPrice, Double wholesalePrice, Double costPrice) throws DatabaseException {
        MItem item = (MItem) databaseService.getObject(MItem.class, itemHash);
        try {
            updatePrice(databaseService, item, salesPrice, wholesalePrice, costPrice);
        } catch (ApplicationException ex) {
            Logger.getLogger(ItemBatchUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex.getMessage());
        }
    }

    public static void updatePrice(HibernateDatabaseService databaseService, String itemHash, Double costPrice) throws DatabaseException {
        MItem item = (MItem) databaseService.getObject(MItem.class, itemHash);

        double salesPrice = costPrice * (1 + (item.getSaleMargin() / 100));
        double wholeSalePrice = costPrice * (1 + (item.getWholeSaleMargin() / 100));
        try {
            updatePrice(databaseService, item, salesPrice, wholeSalePrice, costPrice);
        } catch (ApplicationException ex) {
            Logger.getLogger(ItemBatchUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex.getMessage());
        }
    }

    private static void updatePrice(HibernateDatabaseService databaseService, MItem item, Double salesPrice, Double wholesalePrice, Double costPrice) throws DatabaseException, ApplicationException {
        double lastSalesPrice = salesPrice * (1 - (item.getMaxDiscountPercent() / 100));

        //set lastSalesPrice to the item
        item.setSalePrice(salesPrice);
        item.setLastSalePrice(lastSalesPrice);
        item.setWholeSalePrice(wholesalePrice);
        item.setCostPrice(costPrice);

        //batch
        MItemBatch lastBatch = null;
        if (item.isBatch()) {
            int nextBatch = IndexNumberUtil.getNextIndex("m_item_batch", "`item`='" + item.getHash() + "'");

            lastBatch = new MItemBatch();
            lastBatch.setHash(HashUtil.getHash(item.getCode(), String.valueOf(nextBatch)));
            lastBatch.setMItem(item);
            lastBatch.setBranch(HashUtil.getBranch());
            lastBatch.setBatchNumber(nextBatch);
        } else {
            Iterator<MItemBatch> iterator = item.getMItemBatchs().iterator();
            while (iterator.hasNext()) {
                MItemBatch batch = iterator.next();
                lastBatch = lastBatch != null
                        ? (batch.getBatchNumber() > lastBatch.getBatchNumber()) ? batch : lastBatch
                        : batch;
            }

            if (lastBatch == null) {
                lastBatch = new MItemBatch();
                lastBatch.setHash(HashUtil.getHash(item.getCode(), String.valueOf(1)));
                lastBatch.setMItem(item);
                lastBatch.setBranch(HashUtil.getBranch());
                lastBatch.setBatchNumber(1);
            }
        }

        lastBatch.setSalePrice(salesPrice);
        lastBatch.setCostPrice(costPrice);
        lastBatch.setWholeSalePrice(wholesalePrice);
        lastBatch.setLastSalesPrice(lastSalesPrice);
        lastBatch.setExpireDate(new Date());

        databaseService.save(lastBatch);
        databaseService.save(item);
    }

    public  static void updatePrice(HibernateDatabaseService databaseService, String itemHash, Double salesPrice, Double lastSalesPrice, Double wholesalePrice, Double costPrice) throws DatabaseException, ApplicationException {
        MItem item = (MItem) databaseService.getObject(MItem.class, itemHash);

        //set lastSalesPrice to the item
        item.setSalePrice(salesPrice);
        item.setLastSalePrice(lastSalesPrice);
        item.setWholeSalePrice(wholesalePrice);
        item.setCostPrice(costPrice);

        //batch
        MItemBatch lastBatch = null;
        if (item.isBatch()) {
            int nextBatch = IndexNumberUtil.getNextIndex("m_item_batch", "`item`='" + item.getHash() + "'");

            lastBatch = new MItemBatch();
            lastBatch.setHash(HashUtil.getHash(item.getCode(), String.valueOf(nextBatch)));
            lastBatch.setMItem(item);
            lastBatch.setBranch(HashUtil.getBranch());
            lastBatch.setBatchNumber(nextBatch);
        } else {
            Iterator<MItemBatch> iterator = item.getMItemBatchs().iterator();
            while (iterator.hasNext()) {
                MItemBatch batch = iterator.next();
                lastBatch = lastBatch != null
                        ? (batch.getBatchNumber() > lastBatch.getBatchNumber()) ? batch : lastBatch
                        : batch;
            }

            if (lastBatch == null) {
                lastBatch = new MItemBatch();
                lastBatch.setHash(HashUtil.getHash(item.getCode(), String.valueOf(1)));
                lastBatch.setMItem(item);
                lastBatch.setBranch(HashUtil.getBranch());
                lastBatch.setBatchNumber(1);
            }
        }

        lastBatch.setSalePrice(salesPrice);
        lastBatch.setCostPrice(costPrice);
        lastBatch.setWholeSalePrice(wholesalePrice);
        lastBatch.setLastSalesPrice(lastSalesPrice);
        lastBatch.setExpireDate(new Date());

        databaseService.save(lastBatch);
        databaseService.save(item);
    }
}
