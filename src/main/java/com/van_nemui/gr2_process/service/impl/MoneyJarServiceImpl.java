package com.van_nemui.gr2_process.service.impl;

import com.van_nemui.gr2_process.model.MoneyJar;
import com.van_nemui.gr2_process.model.User;
import com.van_nemui.gr2_process.repository.MoneyJarRepository;
import com.van_nemui.gr2_process.service.MoneyJarService;
import com.van_nemui.gr2_process.util.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyJarServiceImpl implements MoneyJarService {

    private static final Logger logger = LogManager.getLogger(MoneyJarService.class);

    @Autowired
    private MoneyJarRepository moneyJarRepository;

    @Override
    public MoneyJar getMoneyJarById(Long id) {
        try {
            return moneyJarRepository.findMoneyJarById(id);
        }catch (Exception e){
            logger.error("Get money jar by id fail", e);
            return null;
        }
    }

    @Override
    public List<MoneyJar> getByUserId(String username) {
        try{
            User user = validateUser(username);
            if(user == null){
                return  null;
            }
            return moneyJarRepository.findMoneyJarByUserId(user.getId());
        }catch (Exception e) {
            logger.error("Get money jar by user id fail");
            return null;
        }
    }

    @Override
    public MoneyJar getByName(String username, String name) {
        try{
            User user = validateUser(username);
            if(user == null){
                return  null;
            }
            if(name == null || "".equals(name)){
                logger.error("name money jar is null " + username);
                return null;
            }
            return moneyJarRepository.findMoneyJarByName(user.getId(), name);
        }catch (Exception e) {
            logger.error("Get money jar by user id fail");
            return null;
        }
    }

    @Override
    public MoneyJar addNewMoneyJar(MoneyJar moneyJar) {
        try{
            if(moneyJar == null){
                logger.error("add new money jar fail");
                return null;
            }
            return moneyJarRepository.save(moneyJar);
        }catch (Exception e){
            logger.error("add new money jar fail");
            return null;
        }
    }

    @Override
    public int removeMoneyJar(String username, Long id) {
        try{
            User user = validateUser(username);
            if(user == null){
                return  0;
            }
            MoneyJar moneyJar = moneyJarRepository.findMoneyJarById(id);
            if(moneyJar == null || moneyJar.getUser_id() == 0L){
                logger.error("money jar not exist" + id);
                return 0;
            }
            if(moneyJar.getUser_id() != user.getId()){
                logger.error("user is not admin" + username);
                return 0;
            }
            moneyJarRepository.deleteById(id);
            return 1;
        }catch (Exception e){
            logger.error("remove money jar fail" , e);
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
