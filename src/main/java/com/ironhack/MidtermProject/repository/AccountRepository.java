package com.ironhack.MidtermProject.repository;

import com.ironhack.MidtermProject.classes.Money;
import com.ironhack.MidtermProject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
}
