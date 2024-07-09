package com.van_nemui.gr2_process.service.impl;

import com.van_nemui.gr2_process.model.Saving;
import com.van_nemui.gr2_process.model.User;
import com.van_nemui.gr2_process.repository.SavingRepository;
import com.van_nemui.gr2_process.service.SavingService;
import com.van_nemui.gr2_process.util.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingServiceImpl implements SavingService {

    private static final Logger logger = LogManager.getLogger(SavingService.class);

    @Autowired
    private SavingRepository savingRepository;

    @Override
    public List<Saving> getAllSaving(String username) {
        try{
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            return savingRepository.findByUserId(user.getId());
        }catch (Exception e) {
            logger.error("Error get all Saving ", e);
            return null;
        }
    }

    @Override
    public List<Saving> getSavingByType(String username, Long type) {
        try{
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            return savingRepository.findSavingByType(user.getId(), type);
        }catch (Exception e) {
            logger.error("Error get Saving by type ", e);
            return null;
        }
    }

    @Override
    public Saving getSavingById(String username, Long id) {
        try{
            User user = validateUser(username);
            if(user == null){
                return null;
            }
            return savingRepository.findById(user.getId(), id);
        }catch (Exception e) {
            logger.error("Error get Saving by id ", e);
            return null;
        }
    }

    @Override
    public Saving addNewSaving(Saving saving) {
        try{
            if(saving == null){
                logger.error("saving is null ");
                return null;
            }
            return savingRepository.save(saving);
        }catch (Exception e) {
            logger.error("Error add new saving ", e);
            return null;
        }
    }

    @Override
    public int removeSaving(String username, Long id) {
        try{
            User user = validateUser(username);
            if(user == null){
                return 0;
            }
            Saving saving = savingRepository.findById(user.getId(), id);
            if(saving == null || saving.getId() == 0){
                logger.error("Saving not exist for user " + username + " *** " + id);
                return 0;
            }
            savingRepository.deleteById(id);
            return 1;
        }catch (Exception e) {
            logger.error("Error remove Saving ", e);
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
