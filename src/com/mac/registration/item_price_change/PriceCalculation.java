/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.registration.item_price_change;

/**
 *
 * @author thilanga
 */
public class PriceCalculation {

    private static double VALUE = 100;

    public PriceCalculation() {
    }

    public static double getLastSalePrice(double salePrice, double maxDiscountPercentage) {
        return salePrice - (salePrice * maxDiscountPercentage / VALUE);
    }

    public static double getSalesPrice(double costPrice, double salesMargin) {
        return costPrice + (costPrice * salesMargin / VALUE);
    }

    public static double getWholeSalePrice(double costPrice, double wholeSaleMargin) {
        return costPrice + (costPrice * wholeSaleMargin / VALUE);
    }
  
}
