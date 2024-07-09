package com.van_nemui.gr2_process.service.impl;

import com.van_nemui.gr2_process.model.Loan;
import com.van_nemui.gr2_process.model.User;
import com.van_nemui.gr2_process.repository.LoanRepository;
import com.van_nemui.gr2_process.service.LoanService;
import com.van_nemui.gr2_process.util.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private static final Logger logger = LogManager.getLogger(LoanService.class);

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoan(String username) {
        try{
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            return loanRepository.findByUserId(user.getId());
        }catch (Exception e) {
            logger.error("Error get all loan ", e);
            return null;
        }
    }

    @Override
    public List<Loan> getLoanByType(String username, Long type) {
        try{
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            return loanRepository.findLoanByType(user.getId(), type);
        }catch (Exception e) {
            logger.error("Error get loan by type ", e);
            return null;
        }
    }

    @Override
    public Loan getLoanById(String username, Long id) {
        try{
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            return loanRepository.findById(user.getId(), id);
        }catch (Exception e) {
            logger.error("Error get loan by id ", e);
            return null;
        }
    }

    @Override
    public Loan addNewLoan(Loan loan) {
        try{
            if(loan == null){
                logger.error("Loan is null ");
                return null;
            }
            return loanRepository.save(loan);
        }catch (Exception e) {
            logger.error("Error add new loan ", e);
            return null;
        }
    }

    @Override
    public int removeLoan(String username, Long id) {
        try{
            User user = validateUser(username);
            if(user == null){
                return 0;
            }
            Loan loan = loanRepository.findById(user.getId(), id);
            if(loan == null || loan.getId() == 0){
                logger.error("loan not exist for user " + username + " *** " + id);
                return 0;
            }
            loanRepository.deleteById(id);
            return 1;
        }catch (Exception e) {
            logger.error("Error remove loan ", e);
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
}
