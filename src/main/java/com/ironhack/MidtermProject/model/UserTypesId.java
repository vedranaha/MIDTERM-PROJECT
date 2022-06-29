package com.ironhack.MidtermProject.model;

/*User
        userTypeDescription VARCHAR(255),
        PRIMARY KEY (UserTypeId));
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserTypesId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userTypeId;

    private String userTypeDescription;

    public UserTypesId(Integer userTypeId, String userTypeDescription) {
        this.userTypeId = userTypeId;
        this.userTypeDescription = userTypeDescription;
    }

    public UserTypesId() {
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeDescription() {
        return userTypeDescription;
    }

    public void setUserTypeDescription(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
    }
}
