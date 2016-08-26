/*
 *  AccountTransactionQueue.java
 * 
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 * 
 *  Created on Oct 14, 2014, 4:56:04 PM
 *  Copyrights channa mohan, All rights reserved.
 * 
 */
package com.mac.zsystem.transaction.account;

/**
 *
 * @author mohan
 */
public class AccountTransactionQueue {

    private String accountSettingCode;
    private String description;
    private Double amount;
    private String type;

    public String getAccountSettingCode() {
        return accountSettingCode;
    }

    public void setAccountSettingCode(String accountSettingCode) {
        this.accountSettingCode = accountSettingCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
