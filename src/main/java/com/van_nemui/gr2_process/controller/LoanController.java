package com.van_nemui.gr2_process.controller;

import com.van_nemui.gr2_process.model.Loan;
import com.van_nemui.gr2_process.service.LoanService;
import com.van_nemui.gr2_process.service.impl.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gr2/loan")
public class LoanController {
    @Autowired
    private LoanService loanService = new LoanServiceImpl();

    @GetMapping("/getAllLoan")
    public ResponseEntity<?> getAllLoan(@RequestParam String username){
        try{
            return ResponseEntity.ok(loanService.getAllLoan(username));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByType")
    public ResponseEntity<?> getLoanByType(@RequestParam String username, @RequestParam Long type){
        try{
            return ResponseEntity.ok(loanService.getLoanByType(username, type));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getLoanById")
    public ResponseEntity<?> getAllLoan(@RequestParam String username, @RequestParam Long id){
        try{
            return ResponseEntity.ok(loanService.getLoanById(username, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addNewLoan")
    public ResponseEntity<?> getAllLoan(@RequestBody Loan loan){
        try{
            return ResponseEntity.ok(loanService.addNewLoan(loan));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> removeLoan(@PathVariable String userName, @PathVariable Long id){
        try{
            return ResponseEntity.ok(loanService.removeLoan(userName, id));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
