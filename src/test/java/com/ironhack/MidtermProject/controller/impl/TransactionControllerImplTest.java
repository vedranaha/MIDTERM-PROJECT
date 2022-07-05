package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.classes.Address;
import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.model.*;
import com.ironhack.MidtermProject.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Role adminRole, accountHolderRole;
    Admin admin;
    AccountHolder accountHolder1, accountHolder2;
    Checking checking;
    CreditCard creditCard;
    Savings savings;
    StudentChecking studentChecking;
    Address address1, address2;
    ThirdParty thirdParty;

    @BeforeEach
    void setUp() {
        adminRole = new Role("ADMIN");
        accountHolderRole = new Role("ACCOUNT_HOLDER");
        admin = new Admin("Vedrana", passwordEncoder.encode("1234"), adminRole);
        address1 = new Address("Via Augusta", 2, "Barcelona", 8, "Spain");
        accountHolder1 = new AccountHolder("Kim", passwordEncoder.encode("1234"),
                accountHolderRole, Date.valueOf("2002-12-15"), address1);
        address2 = new Address("Diputacio", 101, "Barcelona",
                25, "Spain");
        accountHolder2 = new AccountHolder("Kim", passwordEncoder.encode("1234"),
                accountHolderRole, Date.valueOf("1999-06-8"), address2, address2);
        checking = new Checking(new Money(BigDecimal.valueOf(500)), accountHolder2,
                UUID.nameUUIDFromBytes(("1234").getBytes()).toString(), LocalDate.of(2022, 7, 4));
        studentChecking = new StudentChecking(new Money(BigDecimal.valueOf(500)), accountHolder1, accountHolder2,
                UUID.nameUUIDFromBytes(("1234").getBytes()).toString(), LocalDate.of(2022, 7, 5));
        creditCard = new CreditCard(new Money(BigDecimal.valueOf(1000)), accountHolder1,
                UUID.nameUUIDFromBytes(("1234").getBytes()).toString(), new Money(BigDecimal.valueOf(200)),
                BigDecimal.valueOf(0.2), LocalDate.of(2022, 7, 1));
        savings = new Savings(new Money(BigDecimal.valueOf(1500)), accountHolder1, accountHolder2,
                UUID.nameUUIDFromBytes(("1234").getBytes()).toString(), new Money(BigDecimal.valueOf(150)),
                BigDecimal.valueOf(0.0025), LocalDate.of(2019, 5, 2));
        thirdParty = new ThirdParty(UUID.nameUUIDFromBytes(("1234").getBytes()).toString(), "Roc");

        roleRepository.saveAll(List.of(adminRole, accountHolderRole));
        userRepository.saveAll(List.of(admin, accountHolder1, accountHolder2));
        accountRepository.saveAll(List.of(checking, studentChecking, creditCard, savings));
        thirdPartyRepository.save(thirdParty);
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        thirdPartyRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

}