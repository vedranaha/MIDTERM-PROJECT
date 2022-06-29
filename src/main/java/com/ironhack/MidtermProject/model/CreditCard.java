package com.ironhack.MidtermProject.model;

/*
CreditCard_id INT NOT NULL AUTO_INCREMENT,
Account_Types_code VARCHAR(15),
Balance DOUBLE,
PrimaryOwner VARCHAR(255),
SecondaryOwner VARCHAR(255),
CreditLimit DOUBLE,
InterestRate DOUBLE,
PenaltyFee DOUBLE,
PRIMARY KEY (CreditCard_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));
 */

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn (name="account_id")
public class CreditCard extends Account {
    private Double creditLimit;

    private Double penaltyFee;

    private Double interestRate;

    @OneToOne
    @JoinColumn(name="Account_Types_id")
    private AccountTypesId accountTypesId;

    //CONSTRUCTORS
    public CreditCard(String accountsName, Date dateOpened, Double balance, String primaryOwner, String secondaryOwner,
                      AccountTypesId accountTypesId, Double creditLimit, Double penaltyFee, Double interestRate,
                      AccountTypesId accountTypesId1) {
        super(accountsName, dateOpened, balance, primaryOwner, secondaryOwner, accountTypesId);
        this.creditLimit = creditLimit;
        this.penaltyFee = penaltyFee;
        this.interestRate = interestRate;
        this.accountTypesId = accountTypesId1;
    }

    public CreditCard() {
    }

    //GETTERS & SETTERS
    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getPenaltyFee() {
        return penaltyFee;
    }

    @Override
    public AccountTypesId getAccountTypesId() {
        return accountTypesId;
    }

    @Override
    public void setAccountTypesId(AccountTypesId accountTypesId) {
        this.accountTypesId = accountTypesId;
    }

    public void setPenaltyFee(Double penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}