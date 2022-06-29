package com.ironhack.MidtermProject.model;

/*
ThirdParty_id INT NOT NULL AUTO_INCREMENT,
User_Type_id VARCHAR(15),
ThirdParty_name VARCHAR(15),
HashedKey VARCHAR(32),
PRIMARY KEY (ThirdParty_id),
FOREIGN KEY(User_Types_code) REFERENCES User_types(User_Types_code));
 */

import javax.persistence.*;

@Entity
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer thirdPartyId;

    private String thirdPartyName;

    private String hashedKey;

    @OneToOne
    @JoinColumn(name="User_Types_id")
    private UserTypesId userTypesId;

    //CONSTRUCTORS
    public ThirdParty(Integer thirdPartyId, String thirdPartyName, String hashedKey, UserTypesId userTypesId) {
        this.thirdPartyId = thirdPartyId;
        this.thirdPartyName = thirdPartyName;
        this.hashedKey = hashedKey;
        this.userTypesId = userTypesId;
    }

    public ThirdParty() {
    }

    //GETTERS & SETTERS
    public String getThirdPartyName() {
        return thirdPartyName;
    }

    public Integer getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public UserTypesId getUserTypesId() {
        return userTypesId;
    }

    public void setUserTypesId(UserTypesId userTypesId) {
        this.userTypesId = userTypesId;
    }

    public void setThirdPartyName(String thirdPartyName) {
        this.thirdPartyName = thirdPartyName;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
