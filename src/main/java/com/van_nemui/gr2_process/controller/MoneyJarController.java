package com.van_nemui.gr2_process.controller;

import com.van_nemui.gr2_process.model.MoneyJar;
import com.van_nemui.gr2_process.service.MoneyJarService;
import com.van_nemui.gr2_process.service.impl.MoneyJarServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gr2/money_jar")
public class MoneyJarController {

    private static final Logger logger = LogManager.getLogger(MoneyJarController.class);

    @Autowired
    private MoneyJarService moneyJarService = new MoneyJarServiceImpl();

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long id){
        try{
            return ResponseEntity.ok(moneyJarService.getMoneyJarById(id));
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestParam String username){
        try{
            return ResponseEntity.ok(moneyJarService.getByUserId(username));
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(@RequestParam String name, @RequestParam String username){
        try{
            return ResponseEntity.ok(moneyJarService.getByName(username, name));
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addNewMoneyJar")
    public ResponseEntity<?> addNewMoneyJar(@RequestBody MoneyJar moneyJar){
        try{
            return ResponseEntity.ok(moneyJarService.addNewMoneyJar(moneyJar));
        }catch (Exception e){
            logger.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable String userName, @PathVariable Long id){
        try{
            return ResponseEntity.ok(moneyJarService.removeMoneyJar(userName, id));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
