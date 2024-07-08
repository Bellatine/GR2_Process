package com.van_nemui.gr2_process.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "money_jar")
public class MoneyJar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private int category_id;

    @Column(nullable = false)
    private int amount_max;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int balance;
}
