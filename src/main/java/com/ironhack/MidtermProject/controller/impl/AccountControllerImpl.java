package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.controller.dto.BalanceDTO;
import com.ironhack.MidtermProject.controller.dto.CheckingDTO;
import com.ironhack.MidtermProject.controller.dto.CreditCardDTO;
import com.ironhack.MidtermProject.controller.dto.SavingsDTO;
import com.ironhack.MidtermProject.controller.interfaces.AccountController;
import com.ironhack.MidtermProject.model.*;
import com.ironhack.MidtermProject.repository.AccountRepository;
import com.ironhack.MidtermProject.repository.StudentCheckingRepository;
import com.ironhack.MidtermProject.repository.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import security.CustomUserDetails;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountControllerImpl implements AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private AccountService accountService;

    // Show all accounts (admin)
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountService.findAllAccounts();
    }

    // Show all checking accounts (admin)
    @GetMapping("/accounts/checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> findAllChecking(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountService.findAllChecking();
    }

    // Show all student checking accounts (admin)
    @GetMapping("/accounts/student-checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> findAllStudentChecking(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return studentCheckingRepository.findAll();
    }

    // Show account by id (admin)
    @GetMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @PathVariable Long id) {
        return accountService.findAccount(id);
    }

    // Show my account by id (account holder)
    @GetMapping("/my-accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findMyAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @PathVariable Long id) {
        Long userId = userDetails.getUser().getId();

        return accountService.findMyAccount(userId,id);
    }

    // Create checking account if primary owner is older than 24 and student checking if he/she is younger (admin)
    @PostMapping("/new/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createChecking(@AuthenticationPrincipal CustomUserDetails userDetails,
                                  @RequestBody @Valid CheckingDTO checkingDTO){
        Money balance = checkingDTO.getBalance();
        String secretKey = checkingDTO.getSecretKey();
        Long primaryOwnerId = checkingDTO.getPrimaryOwnerId();
        Long secondaryOwnerId = checkingDTO.getSecondaryOwnerId();

        return accountService.createChecking(balance,secretKey,primaryOwnerId,secondaryOwnerId);
    }

    // Create credit card account (admin)
    @PostMapping("/new/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCard(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @RequestBody @Valid CreditCardDTO creditCardDTO){
        Money balance = creditCardDTO.getBalance();
        String secretKey = creditCardDTO.getSecretKey();
        Money creditLimit = new Money(creditCardDTO.getCreditLimitAmount(), creditCardDTO.getCreditLimitCurrency());
        BigDecimal interestRate = creditCardDTO.getInterestRate();
        Long primaryOwnerId = creditCardDTO.getPrimaryOwnerId();
        Long optionalSecondaryOwnerId = creditCardDTO.getSecondaryOwnerId();

        return accountService.createCreditCard(balance,secretKey,creditLimit,interestRate,primaryOwnerId,
                optionalSecondaryOwnerId);
    }

    // Create savings account (admin)
    @PostMapping("/new/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingsAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @RequestBody @Valid SavingsDTO savingsDTO){
        Money balance = savingsDTO.getBalance();
        String secretKey = savingsDTO.getSecretKey();
        Money minimumBalance = new Money(savingsDTO.getMinimumBalanceAmount(),
                savingsDTO.getMinimumBalanceCurrency());
        BigDecimal interestRate = savingsDTO.getInterestRate();
        Long primaryOwnerId = savingsDTO.getPrimaryOwnerId();
        Long optionalSecondaryOwnerId = savingsDTO.getSecondaryOwnerId();

        return accountService.createSavingsAccount(balance,secretKey,minimumBalance,interestRate,primaryOwnerId,
                optionalSecondaryOwnerId);
    }

    // Modify checking account data (admin)
    @PutMapping("/accounts/checkings/{id}/modify-data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyCheckingAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                                      @PathVariable Long id, @RequestBody @Valid CheckingDTO checkingDTO){
        Money balance = checkingDTO.getBalance();
        String secretKey = checkingDTO.getSecretKey();
        Long primaryOwnerId = checkingDTO.getPrimaryOwnerId();
        Long optionalSecondaryOwnerId = checkingDTO.getSecondaryOwnerId();

        accountService.modifyCheckingAccount(balance,secretKey,primaryOwnerId,optionalSecondaryOwnerId,id);
    }

    // Modify credit card account data (admin)
    @PutMapping("/accounts/credit-cards/{id}/modify-data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyCreditCardAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @PathVariable Long id, @RequestBody @Valid CreditCardDTO creditCardDTO){
        Money balance = creditCardDTO.getBalance();
        String secretKey = creditCardDTO.getSecretKey();
        Money creditLimit = new Money(creditCardDTO.getCreditLimitAmount(), creditCardDTO.getCreditLimitCurrency());
        BigDecimal interestRate = creditCardDTO.getInterestRate();
        Long primaryOwnerId = creditCardDTO.getPrimaryOwnerId();
        Long optionalSecondaryOwnerId = creditCardDTO.getSecondaryOwnerId();

        accountService.modifyCreditCardAccount(balance,secretKey,creditLimit,interestRate,primaryOwnerId,
                optionalSecondaryOwnerId,id);
    }

    // Modify savings account data (admin)
    @PutMapping("/accounts/savings/{id}/modify-data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifySavingsAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @PathVariable Long id, @RequestBody @Valid SavingsDTO savingsDTO){
        Money balance = savingsDTO.getBalance();
        String secretKey = savingsDTO.getSecretKey();
        Money minimumBalance = new Money(savingsDTO.getMinimumBalanceAmount(), savingsDTO.getMinimumBalanceCurrency());
        BigDecimal interestRate = savingsDTO.getInterestRate();
        Long primaryOwnerId = savingsDTO.getPrimaryOwnerId();
        Long optionalSecondaryOwnerId = savingsDTO.getSecondaryOwnerId();

        accountService.modifySavingsAccount(balance,secretKey,minimumBalance,interestRate,primaryOwnerId,
                optionalSecondaryOwnerId,id);
    }

    // Modify balance of an account (admin)
    @PatchMapping("/accounts/{id}/modify-balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyBalance(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @PathVariable Long id, @RequestBody BalanceDTO balanceDTO){
        Money newBalance = balanceDTO.getNewBalance();

        accountService.modifyBalance(id,newBalance);
    }

    // Delete account by id (admin)
    @DeleteMapping("/delete/account/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @PathVariable Long id) {
        Account account = accountService.findAccount(id);
        accountRepository.delete(account);
    }
}