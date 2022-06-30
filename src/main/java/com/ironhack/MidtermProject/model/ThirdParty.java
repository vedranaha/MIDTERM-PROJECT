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


    //CONSTRUCTORS
    public ThirdParty(Integer thirdPartyId, String thirdPartyName, String hashedKey) {
        this.thirdPartyId = thirdPartyId;
        this.thirdPartyName = thirdPartyName;
        this.hashedKey = hashedKey;
    }

    public ThirdParty() {
    }

    //GETTERS & SETTERS
    public Integer getThirdPartyId() {
        return thirdPartyId;
    }
    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }
    public String getThirdPartyName() {
        return thirdPartyName;
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
