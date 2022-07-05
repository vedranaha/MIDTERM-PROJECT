package com.ironhack.MidtermProject.repository;

import com.ironhack.MidtermProject.model.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
}
