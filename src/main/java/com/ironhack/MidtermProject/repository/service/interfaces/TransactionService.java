package com.ironhack.MidtermProject.repository.service.interfaces;

import com.ironhack.MidtermProject.classes.Money;

public interface TransactionService {
    String transferFoundings(Long userId, Long receivingAccountId, String receivingAccountOwnerName,
                             Money amount, Long id);
    String thirdPartyRefund(String hashedKey, Long receivingAccountId,String secretKey,Money amount);
    String thirdPartyDischarge(String hashedKey,Long sendingAccountId,String secretKey,Money amount);
}
