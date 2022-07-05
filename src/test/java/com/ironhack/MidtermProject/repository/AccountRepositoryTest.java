package com.ironhack.MidtermProject.repository;

import com.ironhack.MidtermProject.model.Account;
import com.ironhack.MidtermProject.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    
    private int account1;
    private Account account2;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_JPAMethods() {
        // Test findAll
        List<Account> accountList = accountRepository.findAll(); // size = 2
        assertEquals(2, accountList.size());

        // Test findById
        // Optional: Clase especial de java que se usa cuando podemos recibir un valor null
        // Siempre va a instanciado, osea que nunca va a ser igual a null
       //Optional<Account> optionalAccount = accountRepository.findById(account1.getAccountId());
       //assertTrue(optionalAccount.isPresent());
       //assertEquals(account1, optionalAccount.get()); // El método get me trae la instancia que estaba buscando
    }

    private void assertEquals(int i, int size) {
    }


}