package com.ironhack.MidtermProject.model;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn (name="account_id")
public class StudentChecking extends Account{
    private BigInteger secretKey;

    private Double minimumBalance;

    private Double penaltyFee;

    private Double monthlyMaintenanceFee;

    private Date creationDate;

    private String status;

    @OneToOne
    @JoinColumn(name="Account_Types_id")
    private AccountTypesId accountTypesId;

    //CONSTRUCTORS
    public StudentChecking(String accountsName, Date dateOpened, Double balance, String primaryOwner,
                           String secondaryOwner, AccountTypesId accountTypesId, BigInteger secretKey,
                           Double minimumBalance, Double penaltyFee, Double monthlyMaintenanceFee, Date creationDate,
                           String status, AccountTypesId accountTypesId1) {
        super(accountsName, dateOpened, balance, primaryOwner, secondaryOwner, accountTypesId);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.creationDate = creationDate;
        this.status = status;
        this.accountTypesId = accountTypesId1;
    }

    public StudentChecking() {
    }

    //GETTERS & SETTERS
    public BigInteger getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(BigInteger secretKey) {
        this.secretKey = secretKey;
    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Double minimumBalance) {
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

    @Override
    public AccountTypesId getAccountTypesId() {
        return accountTypesId;
    }

    @Override
    public void setAccountTypesId(AccountTypesId accountTypesId) {
        this.accountTypesId = accountTypesId;
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
