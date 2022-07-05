package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.classes.Address;
import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.controller.dto.AccountHolderDTO;
import com.ironhack.MidtermProject.controller.dto.AdminDTO;
import com.ironhack.MidtermProject.controller.dto.ThirdPartyDTO;
import com.ironhack.MidtermProject.enums.TransactionType;
import com.ironhack.MidtermProject.model.*;
import com.ironhack.MidtermProject.repository.AccountRepository;
import com.ironhack.MidtermProject.repository.RoleRepository;
import com.ironhack.MidtermProject.repository.ThirdPartyRepository;
import com.ironhack.MidtermProject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

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
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerImplTest {
    @Autowired
    private MockMvc mockMvc; //Simular peticiones HTTP
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private AccountRepository accountRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Role adminRole, accountHolderRole;
    Admin admin;
    AccountHolder accountHolder1, accountHolder2;
    Address address1, address2;
    ThirdParty thirdParty;
    Savings savings;

    @BeforeEach
    void setUp() {
        adminRole = new Role("ADMIN");
        accountHolderRole = new Role("ACCOUNT_HOLDER");
        admin = new Admin("Vedrana", passwordEncoder.encode("1234"), adminRole);
        address1 = new Address("Pelai", 2, "Barcelona", 12, "Spain");
        accountHolder1 = new AccountHolder("Kim", passwordEncoder.encode("1234"), accountHolderRole,
                Date.valueOf("2006-10-23"),address1);
        address2 = new Address("Via Augusta", 12,"Barcelona",
                25,"Spain");
        accountHolder2 = new AccountHolder("Quim", passwordEncoder.encode("1234"), accountHolderRole,
                Date.valueOf("1999-06-15"), address2,address2);
        savings = new Savings(new Money(BigDecimal.valueOf(1500)),accountHolder1,accountHolder2,
                UUID.nameUUIDFromBytes(("1234").getBytes()).toString(), new Money(BigDecimal.valueOf(150)),
                BigDecimal.valueOf(0.0025), LocalDate.of(2011,6,1));
        thirdParty = new ThirdParty(UUID.nameUUIDFromBytes(("1234").getBytes()).toString(),"Zara");
        Transaction transaction =
                new Transaction(LocalDateTime.of(2020,3,3,11,12,1),
                        new Money(BigDecimal.valueOf(30)),savings,thirdParty, TransactionType.REFUND);
        savings.getTransactionsReceived().add(transaction);
        thirdParty.getTransactionsDone().add(transaction);

        roleRepository.saveAll(List.of(adminRole,accountHolderRole));
        userRepository.saveAll(List.of(admin,accountHolder1,accountHolder2));
        accountRepository.save(savings);
        thirdPartyRepository.save(thirdParty);
    }

    @AfterEach
    void tearDown() {
        thirdPartyRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void findUsers() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/users").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Vedrana"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Quim"));
    }

    @Test
    void findAdmins() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/users/admins").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Vedrana"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Kim"));
    }

    @Test
    void findAccountHolders() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/users/account-holders").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Vedrana"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Quim"));
    }

    @Test
    void findThirdParties() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/users/third-parties").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Kim"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Vedrana"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Roc"));
    }

    @Test
    void findUserById() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/users/"+accountHolder1.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Kim"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Vedrana"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Quim"));
    }

    @Test
    void findThirdPartyById() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/users/third-parties/"
                        +thirdParty.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Roc"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Vedrana"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Quim"));
    }

    @Test
    void findMyUser_Admin() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/my-user").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Roc"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Vedrana"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Kim"));
    }

    @Test
    void findMyUser_AccountHolder() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic TGlhOjEyMzQ="); //username: Lia, password: 1234

        MvcResult mvcResult = mockMvc.perform(get("/my-user").headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Roc"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Quim"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Kim"));
    }

    @Test
    void createAdmin() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        AdminDTO adminDTO = new AdminDTO("Alf","1234",adminRole.getName());
        String body = objectMapper.writeValueAsString(adminDTO);


        MvcResult mvcResult = mockMvc.perform(
                        post("/new/admin").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alf"));

        assertTrue(userRepository.count()==4);
    }

    @Test
    void createAdmin_WrongRole() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        AdminDTO adminDTO = new AdminDTO("Alf","1234","AMIN");
        String body = objectMapper.writeValueAsString(adminDTO);


        MvcResult mvcResult = mockMvc.perform(
                        post("/new/admin").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void createAdmin_AdminAlreadyExists() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        AdminDTO adminDTO = new AdminDTO("Vedrana","1234","ADMIN");
        String body = objectMapper.writeValueAsString(adminDTO);

        // Hago la llamada HTTP
        MvcResult mvcResult = mockMvc.perform(
                        post("/new/admin").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isImUsed())
                .andReturn();
    }

    @Test
    void createAccountHolder() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        AccountHolderDTO accountHolderDTO = new AccountHolderDTO("Pep",
                passwordEncoder.encode("1234"), accountHolderRole.getName(),
                Date.valueOf("20013-04-23"),address1);
        String body = objectMapper.writeValueAsString(accountHolderDTO);
        System.out.println(body);


        MvcResult mvcResult = mockMvc.perform(
                        post("/new/account-holder").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alf"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2013-04-22"));

        assertTrue(userRepository.count()==4);
    }

    @Test
    void modifyAdmin() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        AdminDTO adminDTO = new AdminDTO("Alf","1234",adminRole.getName());
        String body = objectMapper.writeValueAsString(adminDTO);


        MvcResult mvcResult = mockMvc.perform(
                        put("/users/admins/"+admin.getId()+"/modify-data").headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNoContent())
                .andReturn();

        // Compruebo que se haya guardado en la base de datos
        assertTrue(userRepository.count()==3);
        assertEquals("Alf",userRepository.findById(admin.getId()).get().getUsername());
    }

    @Test
    void modifyAccountHolder() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Basic QWxiYToxMjM0"); //username: Vedrana, password: 1234

        AccountHolderDTO accountHolderDTO = new AccountHolderDTO("Alf",
                passwordEncoder.encode("1234"),accountHolderRole.getName(),
                Date.valueOf("20013-04-23"),address1,address1);
        String body = objectMapper.writeValueAsString(accountHolderDTO);

        MvcResult mvcResult = mockMvc.perform(
                        put("/users/account-holders/"+accountHolder1.getId()+"/modify-data")
                                .headers(httpHeaders)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNoContent())
                .andReturn();

        assertTrue(userRepository.count()==3);
        assertEquals("Pep",userRepository.findById(accountHolder1.getId()).get().getUsername());
    }
}