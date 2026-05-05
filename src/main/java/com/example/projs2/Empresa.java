package com.example.projs2;
import jakarta.persistence.*;

@Entity
public class Empresa {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, length = 150)
    public String nome;

    @Column(nullable = false, unique = true)
    public String cnpj;

    @Column(nullable = false)
    public String emailContato;

}
