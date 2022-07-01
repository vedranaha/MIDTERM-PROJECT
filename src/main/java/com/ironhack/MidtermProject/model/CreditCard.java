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

import com.ironhack.MidtermProject.classes.Money;
import javax.persistence.*;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn (name="account_id")
public class CreditCard extends Account {
    private Double creditLimit;

    private Double penaltyFee;

    private Double interestRate;

    //CONSTRUCTORS
    public CreditCard(Integer accountId, String accountsName, Date dateOpened, AccountHolders primaryOwner,
                      AccountHolders secondaryOwner, Money balance, Double creditLimit, Double penaltyFee,
                      Double interestRate) {
        super(accountId, accountsName, dateOpened, primaryOwner, secondaryOwner, balance);
        this.creditLimit = creditLimit;
        this.penaltyFee = penaltyFee;
        this.interestRate = interestRate;
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
