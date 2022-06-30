package com.ironhack.MidtermProject.model;

/*
Account_id INT NOT NULL AUTO_INCREMENT,
Accounts_name VARCHAR(255),
date_opened DATE,
PRIMARY KEY (Account_id),
FOREIGN KEY(Account_Types_id REFERENCES Account_types(Account_Types_id));
 */

import com.ironhack.MidtermProject.classes.Money;
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

    private String PrimaryOwner;
    private String SecondaryOwner;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "currency_amount"))
    })
    private Money balance;

    //CONSTRUCTORS
    public Account(Integer accountId, String accountsName, Date dateOpened, String primaryOwner,
                   String secondaryOwner, Money balance) {
        this.accountId = accountId;
        this.accountsName = accountsName;
        this.dateOpened = dateOpened;
        PrimaryOwner = primaryOwner;
        SecondaryOwner = secondaryOwner;
        this.balance = balance;
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

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}








