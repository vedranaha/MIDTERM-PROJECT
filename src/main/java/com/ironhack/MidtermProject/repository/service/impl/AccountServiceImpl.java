package com.ironhack.MidtermProject.repository.service.impl;

import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.model.*;
import com.ironhack.MidtermProject.repository.*;
import com.ironhack.MidtermProject.repository.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public void modifyBalance(Long id,Money newBalance){
        Account account = findAccount(id);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        Account account = findAccount(id);
        List<Transaction> transactionList = transactionRepository.findTransactionsByAccountId(id);
        transactionRepository.deleteAll(transactionList);
        accountRepository.delete(account);
    }
}