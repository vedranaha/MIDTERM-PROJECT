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
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private String accountsName;

    private Date dateOpened;

    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    private AccountHolders primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private AccountHolders secondaryOwner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "currency_amount"))
    })
    private Money balance;

    //CONSTRUCTORS


    public Account(Integer accountId, String accountsName, Date dateOpened, AccountHolders primaryOwner,
                   AccountHolders secondaryOwner, Money balance) {
        this.accountId = accountId;
        this.accountsName = accountsName;
        this.dateOpened = dateOpened;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
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

    public AccountHolders getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolders primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolders getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolders secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}








