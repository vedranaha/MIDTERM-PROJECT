package com.ironhack.MidtermProject.model;

/*
Admins_id INT NOT NULL AUTO_INCREMENT,
Admins_name VARCHAR(15),
FOREIGN KEY(userTypeId) REFERENCES User_types(userTypeId));
 */

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="user_id")
public class Admins extends User{


    //CONSTRUCTORS
    public Admins(Integer userId, String name, Integer adminsId) {
        super(userId, name);

    }

    public Admins() {
    }

    //GETTERS & SETTERS

}
