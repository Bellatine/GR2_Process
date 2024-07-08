package com.van_nemui.gr2_process.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private int category_id;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    private Date trans_time;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int shop_id;
}
