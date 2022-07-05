package com.ironhack.MidtermProject.repository.service.interfaces;

import com.ironhack.MidtermProject.classes.Address;
import com.ironhack.MidtermProject.model.AccountHolder;
import com.ironhack.MidtermProject.model.Admin;
import com.ironhack.MidtermProject.model.ThirdParty;
import com.ironhack.MidtermProject.model.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
@Service
public interface UserService {
    User findUserById(Long id);
    ThirdParty findThirdPartyById(Long id);
    User findMyUser(Long userId);
    Admin createAdmin(String username, String password, String roleName);
    AccountHolder createAccountHolder(String username, String password, String roleName, Date dateOfBirth,
                                      Address primaryAddress, Address mailingAddress);
    ThirdParty createThirdParty(String hashedKey, String name);
    void modifyAdmin(Long id,String username, String password, String roleName);
    void modifyAccountHolder(Long id, String username, String password, String roleName, Date dateOfBirth,
                             Address primaryAddress, Address mailingAddress);
    void modifyThirdParty(Long id,String hashedKey, String name);
    void deleteUser(Long id);
    void deleteThirdParty(Long id);
}
