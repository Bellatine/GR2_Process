package com.van_nemui.gr2_process.service;

import com.van_nemui.gr2_process.model.MoneyJar;

import java.util.List;

public interface MoneyJarService {
    MoneyJar getMoneyJarById(Long id);
    List<MoneyJar> getByUserId(String username);
    MoneyJar getByName(String username, String name);
    MoneyJar addNewMoneyJar(MoneyJar moneyJar);
    int removeMoneyJar(String username, Long id);
}
