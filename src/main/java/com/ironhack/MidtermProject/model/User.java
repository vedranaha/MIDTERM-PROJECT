package com.ironhack.MidtermProject.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;

    //CONSTRUCTORS
    public User(Integer userId, String name) {
        userId = userId;
        this.name = name;
    }
    public User() {
    }

    //GETTERS & SETTERS
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
