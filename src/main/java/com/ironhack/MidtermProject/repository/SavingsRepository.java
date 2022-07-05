package com.ironhack.MidtermProject.repository;

import com.ironhack.MidtermProject.model.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
