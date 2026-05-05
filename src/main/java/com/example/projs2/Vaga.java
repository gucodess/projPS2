package com.example.projs2;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Vaga {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, length = 150)
    public String titulo;

    @Column(nullable = false, length = 2000)
    public String descricao;

    @Column(nullable = false)
    public LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    public StatusVaga status;

    @ManyToOne
    public Empresa empresa;

    @ManyToMany
    public List<AreaInteresse> areas = new ArrayList<>();

    @ManyToMany
    public List<Curso> cursos = new ArrayList<>();
}
