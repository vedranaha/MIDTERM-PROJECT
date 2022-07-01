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
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="user_id")
public class AccountHolders extends User{
    private Date dateOfBirth;

    private String primaryAddress;

    private String mailingAddress;


    //CONSTRUCTORS
    public AccountHolders(Integer userId, String name, Date dateOfBirth, String primaryAddress, String mailingAddress) {
        super(userId, name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }
    public AccountHolders() {
    }

    //GETTERS & SETTERS
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
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
