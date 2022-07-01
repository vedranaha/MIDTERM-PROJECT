package com.ironhack.MidtermProject.model;

/*
CREATE TABLE Savings(
Account_Types_code VARCHAR(15),
Savings_id INT NOT NULL AUTO_INCREMENT ,
Balance DOUBLE,
secretKey BIGINT,
PrimaryOwner VARCHAR(255),
SecondaryOwner VARCHAR(255),
MinimumBalance DOUBLE,
PenaltyFee DOUBLE,
InterestRate DOUBLE,
CreationDate DATE,
PRIMARY KEY (Savings_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));
*/

import com.ironhack.MidtermProject.classes.Money;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn (name="account_id")
public class Savings extends Account{

    private BigInteger secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "currency_amount"))
    })
    private Money minimumBalance;

    private Double penaltyFee;

    private Double interestRate;

    private Date creationDate;

    private String status;


    //CONSTRUCTORS
    public Savings(Integer accountId, String accountsName, Date dateOpened, AccountHolders primaryOwner,
                   AccountHolders secondaryOwner, Money balance, BigInteger secretKey, Money minimumBalance,
                   Double penaltyFee, Double interestRate, Date creationDate, String status) {
        super(accountId, accountsName, dateOpened, primaryOwner, secondaryOwner, balance);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.interestRate = interestRate;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Savings() {
    }

    //GETTERS & SETTERS
    public BigInteger getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(BigInteger secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
