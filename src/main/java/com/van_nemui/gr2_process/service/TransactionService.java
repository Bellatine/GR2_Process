package com.van_nemui.gr2_process.service;

import com.van_nemui.gr2_process.model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionToday(String username);
    List<Transaction> getAllTransaction(String username);
    List<Transaction> getTransactionByCategory(String username, int category_id);
    List<Transaction> getTransactionByDate(String username, Date trans_time);
    List<Transaction> getTransactionByType(String username, Long type);
    List<Transaction> getTransactionById(String username, Long id);
    Transaction addTransaction(Transaction transaction);
    int removeTransactionById(String username, Long transactionId);
}
