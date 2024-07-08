package com.van_nemui.gr2_process.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private int role;

}
