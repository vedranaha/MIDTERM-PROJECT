package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.model.Account;
import com.ironhack.MidtermProject.model.Transaction;
import com.ironhack.MidtermProject.repository.AccountRepository;
import com.ironhack.MidtermProject.repository.ThirdPartyRepository;
import com.ironhack.MidtermProject.repository.TransactionRepository;
import com.ironhack.MidtermProject.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public BigDecimal findHighestDailyTotal(Account account) {
        if (account.getTransactionsDone().size() < 1) {
            return BigDecimal.valueOf(0);
        }
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeYesterday = timeNow.minusDays(1);
        BigDecimal maxTransaction = BigDecimal.valueOf(0);
        BigDecimal dailyTotal = BigDecimal.valueOf(0);
        Transaction firstTransaction = (Transaction) account.getTransactionsDone().toArray()[0];
        LocalDate transactionDate = firstTransaction.getTransactionDate().toLocalDate();
        for (int i = 0; i < account.getTransactionsDone().size(); i++) {
            Transaction transaction = (Transaction) account.getTransactionsDone().toArray()[i];
            if (transaction.getTransactionDate().isBefore(timeYesterday)) {
                if (transaction.getTransactionDate().toLocalDate().equals(transactionDate)) {
                    dailyTotal.add(transaction.getAmount().getAmount());
                    if (dailyTotal.compareTo(maxTransaction) >= 0) {
                        maxTransaction = dailyTotal;
                    }
                } else {
                    dailyTotal = BigDecimal.valueOf(0);
                    transactionDate = transaction.getTransactionDate().toLocalDate();
                }
            }
        }
        return maxTransaction;
    }

    public BigDecimal transactionTotalInLastDay(Account account) {
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeYesterday = timeNow.minusDays(1);
        BigDecimal transactionTotal = BigDecimal.valueOf(0);
        for (int i = 0; i < account.getTransactionsDone().size(); i++) {
            Transaction transaction = (Transaction) account.getTransactionsDone().toArray()[i];
            if (transaction.getTransactionDate().isBefore(timeNow) &&
                    transaction.getTransactionDate().isAfter(timeYesterday)) {
                transactionTotal.add(transaction.getAmount().getAmount());
            }
        }
        return transactionTotal;
    }

    @Override
    public String transferFoundings(Long userId, Long receivingAccountId, String receivingAccountOwnerName, Money amount, Long id) {
        return null;
    }

    @Override
    public String thirdPartyRefund(String hashedKey, Long receivingAccountId, String secretKey, Money amount) {
        return null;
    }

    @Override
    public String thirdPartyDischarge(String hashedKey, Long sendingAccountId, String secretKey, Money amount) {
        return null;
    }
}
