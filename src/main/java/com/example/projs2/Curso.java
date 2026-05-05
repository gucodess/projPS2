package com.example.projs2;
import jakarta.persistence.*;

@Entity
public class Curso {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true, length = 120)
    public String nome;
}
