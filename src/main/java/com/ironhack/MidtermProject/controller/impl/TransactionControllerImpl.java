package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.controller.dto.ThirdPartyTransactionDTO;
import com.ironhack.MidtermProject.controller.dto.TransferDTO;
import com.ironhack.MidtermProject.controller.interfaces.TransactionController;


import com.ironhack.MidtermProject.repository.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import security.CustomUserDetails;

import javax.validation.Valid;

@RestController
public class TransactionControllerImpl implements TransactionController {
    @Autowired
    private TransactionService transactionService;

    //Do a transfer from account (account-holder)
    @PostMapping("/my-accounts/{id}/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String transferFoundings(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @PathVariable Long id,
                                    @RequestBody @Valid TransferDTO transferDTO){
        Long userId = userDetails.getUser().getId();
        Long receivingAccountId = transferDTO.getAccountId();
        String receivingAccountOwnerName = transferDTO.getOwnerName();
        Money amount = transferDTO.getAmount();

        return transactionService.transferFoundings(userId,receivingAccountId,receivingAccountOwnerName,
                amount,id);
    }

    //Do a refund to an account (anyone)
    @PostMapping("/third-party/{hashedKey}/refund")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String thirdPartyRefund(@PathVariable String hashedKey,
                                   @RequestBody @Valid ThirdPartyTransactionDTO thirdPartyTransactionDTO){
        Long receivingAccountId = thirdPartyTransactionDTO.getAccountId();
        String secretKey = thirdPartyTransactionDTO.getAccountSecretKey();
        Money amount = thirdPartyTransactionDTO.getAmount();

        return transactionService.thirdPartyRefund(hashedKey,receivingAccountId,secretKey,amount);
    }

    //Do a discharge to an account (anyone)
    @PostMapping("/third-party/{hashedKey}/discharge")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String thirdPartyDischarge(@PathVariable String hashedKey,
                                      @RequestBody @Valid ThirdPartyTransactionDTO thirdPartyTransactionDTO){
        Long sendingAccountId = thirdPartyTransactionDTO.getAccountId();
        String secretKey = thirdPartyTransactionDTO.getAccountSecretKey();
        Money amount = thirdPartyTransactionDTO.getAmount();

        return transactionService.thirdPartyDischarge(hashedKey,sendingAccountId,secretKey,amount);
    }
}