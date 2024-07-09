package com.van_nemui.gr2_process.service;

import com.van_nemui.gr2_process.model.Loan;

import java.util.List;

public interface LoanService {
    List<Loan>getAllLoan(String username);
    List<Loan>getLoanByType(String username, Long type);
    Loan getLoanById(String username, Long id);
    Loan addNewLoan(Loan loan);
    int removeLoan(String username, Long id);
}
