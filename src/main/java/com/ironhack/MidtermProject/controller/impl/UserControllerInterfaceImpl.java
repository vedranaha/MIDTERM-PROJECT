package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.classes.Address;
import com.ironhack.MidtermProject.controller.dto.AccountHolderDTO;
import com.ironhack.MidtermProject.controller.dto.AdminDTO;
import com.ironhack.MidtermProject.controller.interfaces.UserControllerInterface;

import com.ironhack.MidtermProject.model.AccountHolder;
import com.ironhack.MidtermProject.model.Admin;
import com.ironhack.MidtermProject.model.ThirdParty;
import com.ironhack.MidtermProject.model.User;
import com.ironhack.MidtermProject.repository.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.AdminRepository;
import com.ironhack.MidtermProject.repository.ThirdPartyRepository;
import com.ironhack.MidtermProject.repository.UserRepository;
import com.ironhack.MidtermProject.repository.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import security.CustomUserDetails;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
public class UserControllerInterfaceImpl implements UserControllerInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private UserService userService;

    //Show all users -> admins and account-holders (admin)
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findUsers(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userRepository.findAll();
    }

    //Show all admins (admin)
    @GetMapping("/users/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> findAdmins(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return adminRepository.findAll();
    }

    //Show all account-holders (admin)
    @GetMapping("/users/account-holders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> findAccountHolders(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountHolderRepository.findAll();
    }

    //Show user by id (admin)
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        return userService.findUserById(id);
    }

    //Show third party by id (admin)
    @GetMapping("/users/third-parties/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty findThirdPartyById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        return userService.findThirdPartyById(id);
    }

    //Create admin (admin)
    @PostMapping("/new/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@AuthenticationPrincipal CustomUserDetails userDetails,
                             @RequestBody @Valid AdminDTO adminDTO) {
        String username = adminDTO.getUsername();
        String password = adminDTO.getPassword();
        String roleName = adminDTO.getRole();
        return userService.createAdmin(username, password, roleName);
    }

    //Create account holder (admin)
    @PostMapping("/new/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@AuthenticationPrincipal CustomUserDetails userDetails,
                                             @RequestBody @Valid AccountHolderDTO accountHolderDTO) {
        String username = accountHolderDTO.getUsername();
        String password = accountHolderDTO.getPassword();
        String roleName = accountHolderDTO.getRole();
        Date dateOfBirth = accountHolderDTO.getDateOfBirth();
        Address primaryAddress = accountHolderDTO.getPrimaryAddress();
        Address mailingAddress = accountHolderDTO.getMailingAddress();
        return userService.createAccountHolder(username, password, roleName, dateOfBirth, primaryAddress, mailingAddress);
    }

    //Modify admin data (admin)
    @PutMapping("/users/admins/{id}/modify-data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyAdmin(@AuthenticationPrincipal CustomUserDetails userDetails,
                            @PathVariable Long id, @RequestBody @Valid AdminDTO adminDTO) {
        String username = adminDTO.getUsername();
        String password = adminDTO.getPassword();
        String roleName = adminDTO.getRole();
        userService.modifyAdmin(id, username, password, roleName);
    }

    // Delete user by id (admin)
    @DeleteMapping("/delete/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                           @PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Delete third-party by id (admin)
    @DeleteMapping("/delete/third-party/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThirdParty(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @PathVariable Long id) {
        userService.deleteThirdParty(id);
    }
}

