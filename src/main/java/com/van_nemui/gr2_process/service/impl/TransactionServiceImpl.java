package com.van_nemui.gr2_process.service.impl;

import com.van_nemui.gr2_process.model.Category;
import com.van_nemui.gr2_process.model.Transaction;
import com.van_nemui.gr2_process.model.User;
import com.van_nemui.gr2_process.repository.TransactionRepository;
import com.van_nemui.gr2_process.service.TransactionService;
import com.van_nemui.gr2_process.util.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionToday(String username) {
        try{
            LocalDate localDate = LocalDate.now();
            Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return getTransactionByDate(username, today);
        }catch (Exception e) {
            logger.error("Error get transaction today" + username, e);
            return null;
        }
    }

    @Override
    public List<Transaction> getAllTransaction(String username) {
        try {
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            return transactionRepository.findByUserId(user.getId());
        }catch(Exception e){
            logger.error("Error get all transaction " + username, e);
            return null;
        }
    }

    @Override
    public List<Transaction> getTransactionByCategory(String username, int category_id) {
        try {
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            Category category = Cache.Categories.ALL_CATEGORIES.get(category_id);
            if(category == null){
                logger.error("Category is null");
                return null;
            }
            return transactionRepository.findByCategoryId(user.getId(), category.getId());
        }catch(Exception e){
            logger.error("Error get transaction by category" + username, e);
            return null;
        }
    }

    @Override
    public List<Transaction> getTransactionByDate(String username, Date trans_time) {
        try {
            User user = validateUser(username);
            if (user == null) {
                return null;
            }
            LocalDate localDate = trans_time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDateTime startOfDay = localDate.atStartOfDay();
            LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);
            return transactionRepository.findAllByTransTime(user.getId(),startOfDay,endOfDay);
        }catch (Exception e){
            logger.error("Error get transaction by trans_time" + username, e);
            return null;
        }
    }

    @Override
    public List<Transaction> getTransactionByType(String username, Long type, int month, int year) {
        try{
            User user = validateUser(username);
            if (user == null) {
                return null;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            logger.info("start: " + calendar.getTime());
            LocalDateTime startTime = parseCalendar(calendar);
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            logger.info("end: " + calendar.getTime());
            LocalDateTime endTime = parseCalendar(calendar);
            return transactionRepository.findTransactionByType(user.getId(),type, startTime, endTime);
        }catch (Exception e) {
            logger.error("Error get transaction by type" + username, e);
            return null;
        }
    }

    @Override
    public List<Transaction> getTransactionById(String username, Long id) {
        try{
            User user = validateUser(username);
            if (user == null) {
                return null;
            }
            return transactionRepository.findById(user.getId(), id);
        }catch (Exception e) {
            logger.error("Error get transaction by id" + username, e);
            return null;
        }
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        try {
            if (transaction == null) {
                logger.error("Transaction is null");
                return null;
            }
            return transactionRepository.save(transaction);
        }catch (Exception e){
            logger.error("Error add transaction ", e);
            return null;
        }
    }

    @Override
    public int removeTransactionById(String username, Long transactionId) {
        try{
            User user = validateUser(username);
            if (user == null) {
                return 0;
            }
            transactionRepository.deleteById(transactionId);
            return 1;
        }catch (Exception e) {
            logger.error("Error delete transaction" + username, e);
            return 0;
        }
    }

    private User validateUser(String username){
        if (username == null || "".equals(username)) {
            logger.error("username is null or empty");
            return null;
        }

        User user = Cache.Users.ALLUSERS.get(username);
        if (user == null) {
            logger.error("no user info");
            return null;
        }
        return user;
    }

    private LocalDateTime parseCalendar(Calendar calendar){
        Instant instant = calendar.toInstant();
        return LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
    }
}
