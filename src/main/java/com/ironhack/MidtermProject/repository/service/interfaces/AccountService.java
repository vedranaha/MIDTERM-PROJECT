package com.ironhack.MidtermProject.repository.service.interfaces;

import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.model.Account;
import com.ironhack.MidtermProject.model.Checking;
import com.ironhack.MidtermProject.model.CreditCard;
import com.ironhack.MidtermProject.model.Savings;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface AccountService {
    List<Account> findAllAccounts();
    List<Checking> findAllChecking();
    Account findAccount(Long id);
    Account findMyAccount(Long userId, Long id);
    Account createChecking(Money balance, String secretKey, Long primaryOwnerId,
                           Long optionalSecondaryOwnerId);
    void modifyCheckingAccount(Money balance,String secretKey,Long primaryOwnerId,
                               Long optionalSecondaryOwnerId,Long id);
    CreditCard createCreditCard(Money balance, String secretKey, Money creditLimit, BigDecimal interestRate,
                                Long primaryOwnerId, Long optionalSecondaryOwnerId);
    void modifyCreditCardAccount(Money balance,String secretKey,Money creditLimit,BigDecimal interestRate,
                                 Long primaryOwnerId, Long optionalSecondaryOwnerId, Long id);
    Savings createSavingsAccount(Money balance, String secretKey, Money minimumBalance, BigDecimal interestRate,
                                 Long primaryOwnerId, Long optionalSecondaryOwnerId);
    void modifySavingsAccount(Money balance,String secretKey,Money minimumBalance,BigDecimal interestRate,
                              Long primaryOwnerId,Long optionalSecondaryOwnerId,Long id);
    void modifyBalance(Long id,Money newBalance);
}