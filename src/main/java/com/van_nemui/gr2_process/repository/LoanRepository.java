package com.van_nemui.gr2_process.repository;

import com.van_nemui.gr2_process.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
