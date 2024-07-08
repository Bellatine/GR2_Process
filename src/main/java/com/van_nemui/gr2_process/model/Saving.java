package com.van_nemui.gr2_process.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "saving")
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Date date_from;

    @Column(nullable = false)
    private Date date_to;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private int interest_rates;

    @Column(nullable = false)
    private String description;
}
