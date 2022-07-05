package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.classes.Address;
import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.controller.dto.BalanceDTO;
import com.ironhack.MidtermProject.controller.dto.CheckingDTO;
import com.ironhack.MidtermProject.controller.dto.CreditCardDTO;
import com.ironhack.MidtermProject.controller.dto.SavingsDTO;
import com.ironhack.MidtermProject.model.*;
import com.ironhack.MidtermProject.repository.AccountRepository;
import com.ironhack.MidtermProject.repository.RoleRepository;
import com.ironhack.MidtermProject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerImplTest {

    @Autowired
    private MockMvc mockMvc; //
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Role adminRole, accountHolderRole;
    Admin admin;
    AccountHolder accountHolder1, accountHolder2;
    Checking checking;
    CreditCard creditCard;
    Savings savings;
    StudentChecking studentChecking;
    Address address1, address2;

    @BeforeEach
    void setUp() {
        adminRole = new Role("ADMIN");
        accountHolderRole = new Role("ACCOUNT_HOLDER");
        admin = new Admin("Vedrana", passwordEncoder.encode("1234"), adminRole);
        address1 = new Address("CallePelai", 2, "Barcelona", 17, "Spain");
        accountHolder1 = new AccountHolder("Kim",passwordEncoder.encode("1234"),
                accountHolderRole,Date.valueOf("2002-12-15"),address1);
        address2 = new Address("Valencia", 10,"Barcelona",
                25, "Spain");
        accountHolder2 = new AccountHolder("Quim", passwordEncoder.encode("1234"),
                accountHolderRole,Date.valueOf("1999-09-12"), address2,address2);
        checking = new Checking(new Money(BigDecimal.valueOf(500)),accountHolder2,
                UUID.nameUUIDFromBytes(("1234").getBytes()).toString(), LocalDate.of(2022,7,4));
        Transaction transaction =
                new Transaction(LocalDateTime.of(2020,9,9,11,9,1),
                        new Money(BigDecimal.valueOf(30)),savings,checking);
        savings.getTransactionsReceived().add(transaction);

        roleRepository.saveAll(List.of(adminRole,accountHolderRole));
        userRepository.saveAll(List.of(admin,accountHolder1,accountHolder2));

    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void findAllAccounts() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/accounts").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Quim"));
    }

    @Test
    void findAllChecking() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username:Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/accounts/checkings").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Quim"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("808"));
    }

    @Test
    void findAllSavings() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/accounts/savings").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        ;
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("4443.75"));
    }

    @Test
    void findAllMyAccounts_Kim() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic TGlhOjEyMzQ="); //username: Kim, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/my-accounts").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));

    }

    @Test
    void findAccount_CorrectId_CorrectAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/accounts/"+savings.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));
    }

    @Test
    void findAccount_IncorrectId_NoAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/accounts/3").headers(httpHeaders))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    @Test
    void createChecking_PrimaryOwnerOlderThan24_CheckingAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        CheckingDTO checkingDTO = new CheckingDTO(new Money(BigDecimal.valueOf(5000)),
                accountHolder2.getId(), "1234");
        String body = objectMapper.writeValueAsString(checkingDTO);

        // calling HTTP
        MvcResult mvcResult = mockMvc.perform(
                        post("/new/checking").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        // response format
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5000"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Quim"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("minimumBalance"));
    }

    @Test
    void modifyCreditCardAccount() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        CreditCardDTO creditCardDTO = new CreditCardDTO(new Money(BigDecimal.valueOf(5000)),accountHolder1.getId(),
                accountHolder2.getId(),BigDecimal.valueOf(200), Currency.getInstance("USD"),
                "1234",BigDecimal.valueOf(0.15));
        String body = objectMapper.writeValueAsString(creditCardDTO);


        MvcResult mvcResult = mockMvc.perform(
                        put("/accounts/credit-cards/"+creditCard.getId()+"/modify-data").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNoContent())
                .andReturn();

        assertTrue(accountRepository.count()==4);
        assertTrue(accountRepository.existsById(creditCard.getId()));
        assertEquals(new Money(BigDecimal.valueOf(5000)),
                accountRepository.findById(creditCard.getId()).get().getBalance());
    }

    @Test
    void deleteAccount_CorrectId_AccountDeleted() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(delete("/delete/account/"+savings.getId()).headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(accountRepository.existsById(savings.getId()));
    }
}