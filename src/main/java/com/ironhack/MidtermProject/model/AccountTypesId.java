package com.ironhack.MidtermProject.model;

/*
    Account_Types_code VARCHAR(15),
        Account_Types_description VARCHAR(255),
        PRIMARY KEY (AccountTypesId));
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountTypesId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer AccountTypesId;
    private String  accountTypesDescription;

    //constructors
    public AccountTypesId( String accountTypesDescription) {
        this.accountTypesDescription = accountTypesDescription;
    }

    public AccountTypesId() {
    }

    // getters & setters
    public String getAccountTypesDescription() {
        return accountTypesDescription;
    }

    public void setAccountTypesDescription(String accountTypesDescription) {
        this.accountTypesDescription = accountTypesDescription;
    }
}
