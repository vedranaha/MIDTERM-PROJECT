package com.ironhack.MidtermProject.controller.dto;

import com.ironhack.MidtermProject.classes.Money;

public class TransferDTO {
    private Money amount;
    private String ownerName;
    private Long accountId;

    //CONSTRUCTORS
    public TransferDTO() {
    }

    public TransferDTO(Money amount, String ownerName, Long accountId) {
        this.amount = amount;
        this.ownerName = ownerName;
        this.accountId = accountId;
    }

    //GETTERS AND SETTERS
    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}