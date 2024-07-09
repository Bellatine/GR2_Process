package com.van_nemui.gr2_process.controller;

import com.van_nemui.gr2_process.model.Saving;
import com.van_nemui.gr2_process.service.SavingService;
import com.van_nemui.gr2_process.service.impl.SavingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gr2/saving")
public class SavingController {
    @Autowired
    private SavingService savingService = new SavingServiceImpl();

    @GetMapping("/getAllSaving")
    public ResponseEntity<?> getAllSaving(@RequestParam String username){
        try{
            return ResponseEntity.ok(savingService.getAllSaving(username));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByType")
    public ResponseEntity<?> getSavingByType(@RequestParam String username, @RequestParam Long type){
        try{
            return ResponseEntity.ok(savingService.getSavingByType(username, type));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getSavingById")
    public ResponseEntity<?> getAllSaving(@RequestParam String username, @RequestParam Long id){
        try{
            return ResponseEntity.ok(savingService.getSavingById(username, id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addNewSaving")
    public ResponseEntity<?> getAllSaving(@RequestBody Saving saving){
        try{
            return ResponseEntity.ok(savingService.addNewSaving(saving));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> removeSaving(@PathVariable String userName, @PathVariable Long id){
        try{
            return ResponseEntity.ok(savingService.removeSaving(userName, id));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
