/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.zsystem.payment.object;

/**
 *
 * @author pc-n
 */
public class Payment {

    private double netValue;
    private double cashAmount;
    private double chequeAmount;
    private double creditAmount;

    public double getNetValue() {

        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }
}
