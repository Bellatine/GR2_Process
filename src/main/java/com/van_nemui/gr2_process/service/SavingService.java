package com.van_nemui.gr2_process.service;

import com.van_nemui.gr2_process.model.Saving;

import java.util.List;

public interface SavingService {
    List<Saving> getAllSaving(String username);
    List<Saving>getSavingByType(String username, Long type);
    Saving getSavingById(String username, Long id);
    Saving addNewSaving(Saving saving);
    int removeSaving(String username, Long id);

}
