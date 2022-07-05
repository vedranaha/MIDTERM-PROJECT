package com.ironhack.MidtermProject.controller.dto;

import com.ironhack.MidtermProject.classes.Money;

public class ThirdPartyTransactionDTO {
    Money amount;
    Long accountId;
    String accountSecretKey;

    //CONSTRUCTORS
    public ThirdPartyTransactionDTO() {
    }

    public ThirdPartyTransactionDTO(Money amount, Long accountId, String accountSecretKey) {
        this.amount = amount;
        this.accountId = accountId;
        this.accountSecretKey = accountSecretKey;
    }

    //GETTERS AND SETTERS
    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountSecretKey() {
        return accountSecretKey;
    }

    public void setAccountSecretKey(String accountSecretKey) {
        this.accountSecretKey = accountSecretKey;
    }
}
