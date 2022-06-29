package com.ironhack.MidtermProject.model;

/*
Admins_id INT NOT NULL AUTO_INCREMENT,
Admins_name VARCHAR(15),
FOREIGN KEY(userTypeId) REFERENCES User_types(userTypeId));
 */

import javax.persistence.*;

@Entity
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminsId;

    private String adminsName;

    @OneToOne
    @JoinColumn(name="User_Types_id")
    private UserTypesId userTypesId;

    //CONSTRUCTORS
    public Admins(Integer adminsId, String adminsName, UserTypesId userTypesId) {
        this.adminsId = adminsId;
        this.adminsName = adminsName;
        this.userTypesId = userTypesId;
    }

    public Admins() {
    }

    //GETTERS & SETTERS
    public Integer getAdminsId() {
        return adminsId;
    }

    public UserTypesId getUserTypesId() {
        return userTypesId;
    }

    public void setUserTypesId(UserTypesId userTypesId) {
        this.userTypesId = userTypesId;
    }

    public void setAdminsId(Integer adminsId) {
        this.adminsId = adminsId;
    }

    public String getAdminsName() {
        return adminsName;
    }

    public void setAdminsName(String adminsName) {
        this.adminsName = adminsName;
    }
}
