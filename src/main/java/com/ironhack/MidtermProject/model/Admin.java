package com.ironhack.MidtermProject.model;

/*
Admin_id INT NOT NULL AUTO_INCREMENT,
Admin_name VARCHAR(15),
 */

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {
    //CONSTRUCTORS
    public Admin() {
    }

    public Admin(String username, String password, Role role) {
        super(username, password, role);
    }

    //it Is equal
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
