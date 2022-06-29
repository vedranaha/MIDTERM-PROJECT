package com.ironhack.MidtermProject.model;

/*
Account_id INT NOT NULL AUTO_INCREMENT,
Accounts_name VARCHAR(255),
date_opened DATE,
PRIMARY KEY (Account_id),
FOREIGN KEY(Account_Types_id REFERENCES Account_types(Account_Types_id));
 */

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private String accountsName;

    private Date dateOpened;

    private Double balance;

    private String PrimaryOwner;

    private String SecondaryOwner;


   @OneToOne
   @JoinColumn(name="Account_Types_id")
   private AccountTypesId accountTypesId;


    //CONSTRUCTORS
    public Account( String accountsName, Date dateOpened, Double balance, String primaryOwner,
                   String secondaryOwner, AccountTypesId accountTypesId) {
        this.accountsName = accountsName;
        this.dateOpened = dateOpened;
        this.balance = balance;
        PrimaryOwner = primaryOwner;
        SecondaryOwner = secondaryOwner;
        this.accountTypesId = accountTypesId;
    }

    public Account() {
    }


    //GETTERS & SETTERS

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountsName() {
        return accountsName;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPrimaryOwner() {
        return PrimaryOwner;
    }

    public void setPrimaryOwner(String primaryOwner) {
        PrimaryOwner = primaryOwner;
    }

    public String getSecondaryOwner() {
        return SecondaryOwner;
    }

    public void setSecondaryOwner(String secondaryOwner) {
        SecondaryOwner = secondaryOwner;
    }

    public AccountTypesId getAccountTypesId() {
        return accountTypesId;
    }

    public void setAccountTypesId(AccountTypesId accountTypesId) {
        this.accountTypesId = accountTypesId;
    }
}








