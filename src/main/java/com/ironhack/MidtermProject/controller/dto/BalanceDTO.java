package com.ironhack.MidtermProject.controller.dto;

import com.ironhack.MidtermProject.classes.Money;
public class BalanceDTO {
    private Money newBalance;

    //CONSTRUCTORS
    public BalanceDTO() {
    }

    public BalanceDTO(Money newBalance) {
        this.newBalance = newBalance;
    }

    //GETTERS & SETTERS
    public Money getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Money newBalance) {
        this.newBalance = newBalance;
    }
}