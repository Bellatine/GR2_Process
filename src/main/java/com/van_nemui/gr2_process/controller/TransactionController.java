package com.van_nemui.gr2_process.controller;

import com.van_nemui.gr2_process.model.Transaction;
import com.van_nemui.gr2_process.service.TransactionService;
import com.van_nemui.gr2_process.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/gr2/trans")
public class TransactionController {

    @Autowired
    private TransactionService transactionService = new TransactionServiceImpl();

    @GetMapping("/all")
    public ResponseEntity<?> getAllTransaction(@RequestParam String username){
        try{
            return ResponseEntity.ok(transactionService.getAllTransaction(username));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<?> getTransactionByCategory(@RequestParam String username, @RequestParam int categoryId){
        try{
            return ResponseEntity.ok(transactionService.getTransactionByCategory(username,categoryId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getTransactionById(@RequestParam String username, @RequestParam Long id){
        try{
            return ResponseEntity.ok(transactionService.getTransactionById(username, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBydate")
    public ResponseEntity<?> getTransactionByDate(@RequestParam String username, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        try{
            return ResponseEntity.ok(transactionService.getTransactionByDate(username,date));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByType")
    public ResponseEntity<?> getTransactionByType(@RequestParam String username, @RequestParam Long type){
        try{
            return ResponseEntity.ok(transactionService.getTransactionByType(username, type));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction){
        try{
            return ResponseEntity.ok(transactionService.addTransaction(transaction));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> removeTransaction(@PathVariable String userName, @PathVariable Long id){
        try{
            return ResponseEntity.ok(transactionService.removeTransactionById(userName, id));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
