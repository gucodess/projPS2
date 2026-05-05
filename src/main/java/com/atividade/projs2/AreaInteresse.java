package com.atividade.projs2;

import jakarta.persistence.*;

@Entity
public class AreaInteresse {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true, length = 120)
    public String nome;

}

