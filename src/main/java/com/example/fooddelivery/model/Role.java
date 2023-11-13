package com.example.fooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    @OneToMany
    private List<User> user;
}