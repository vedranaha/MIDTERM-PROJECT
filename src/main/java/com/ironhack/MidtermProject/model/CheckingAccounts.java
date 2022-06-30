package com.ironhack.MidtermProject.model;

/*
TABLE Checking_Accounts(
Checking_Accounts_id INT NOT NULL AUTO_INCREMENT ,
Balance DOUBLE,
secretKey BIGINT,
PrimaryOwner VARCHAR(255),
SecondaryOwner VARCHAR(255),
MinimumBalance DOUBLE,
PenaltyFee DOUBLE,
MonthlyMaintenanceFee DOUBLE,
CreationDate DATE,
Status VARCHAR(255),
PRIMARY KEY (Checking_Accounts_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));
 */

import com.ironhack.MidtermProject.classes.Money;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn (name="account_id")
public class CheckingAccounts extends Account{

    private BigInteger secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "currency_amount"))
    })
    private Money minimumBalance;

    private Double penaltyFee;

    private Double monthlyMaintenanceFee;

    private Date creationDate;

    private String status;

    //CONSTRUCTORS
    public CheckingAccounts(Integer accountId, String accountsName, Date dateOpened, String primaryOwner,
                            String secondaryOwner, Money balance, BigInteger secretKey, Money minimumBalance,
                            Double penaltyFee, Double monthlyMaintenanceFee, Date creationDate, String status) {
        super(accountId, accountsName, dateOpened, primaryOwner, secondaryOwner, balance);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.creationDate = creationDate;
        this.status = status;
    }

    public CheckingAccounts() {
    }

    //getters & setters
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

    public Double getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Double monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
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
