package com.ironhack.MidtermProject.repository;

import com.ironhack.MidtermProject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

}
