package com.ironhack.MidtermProject.model;

/*
User_Types_code VARCHAR(15),
Account_holders_name VARCHAR(15),
PrimaryAddress VARCHAR(255),
mailingAddress VARCHAR(255),
PRIMARY KEY (accountHoldersId),
FOREIGN KEY(UserTypeId) REFERENCES UserTypes(UserTypeId));
 */

import javax.persistence.*;
import java.util.Date;

@Entity
public class AccountHolders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountHoldersId;

    private String accountHoldersName;

    private String primaryAddress;

    private String mailingAddress;

    @OneToOne
    @JoinColumn(name="User_Types_id")
    private UserTypesId userTypesId;

    public AccountHolders(Integer accountHoldersId, String accountHoldersName, String primaryAddress,
                          String mailingAddress, UserTypesId userTypesId) {
        this.accountHoldersId = accountHoldersId;
        this.accountHoldersName = accountHoldersName;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.userTypesId = userTypesId;
    }

    public AccountHolders() {
    }

    public Integer getAccountHoldersId() {
        return accountHoldersId;
    }

    public void setAccountHoldersId(Integer accountHoldersId) {
        this.accountHoldersId = accountHoldersId;
    }

    public String getAccountHoldersName() {
        return accountHoldersName;
    }

    public void setAccountHoldersName(String accountHoldersName) {
        this.accountHoldersName = accountHoldersName;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public UserTypesId getUserTypesId() {
        return userTypesId;
    }

    public void setUserTypesId(UserTypesId userTypesId) {
        this.userTypesId = userTypesId;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
