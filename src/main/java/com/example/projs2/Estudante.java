package com.example.projs2;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Estudante {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, length = 150)
    public String nome;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public LocalDate dataNascimento;

    @ManyToOne
    public Curso curso;

    @Column(nullable = false, length = 150)
    public String faculdade;

    public Integer anoIngresso;
    public Integer anoFormatura;

    @ManyToMany
    public List<AreaInteresse> areas = new ArrayList<>();
}
