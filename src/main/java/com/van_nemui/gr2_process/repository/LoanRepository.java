package com.van_nemui.gr2_process.repository;

import com.van_nemui.gr2_process.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
