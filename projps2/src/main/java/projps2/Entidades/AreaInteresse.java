package projps2.Entidades;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "areas_interesse")
public class AreaInteresse {
    @Id
    @GeneratedValue
    public Long id;

    @NotBlank(message = "Este campo é obrigatório") 
    @Column(nullable = false, unique = true, length = 120)
    public String nome;

    @ManyToMany(mappedBy = "areasInteresse")
    @JsonIgnore
    private List<Estudante> estudantes = new ArrayList<>();

    @ManyToMany(mappedBy = "areasInteresse")
    @JsonIgnore
    private List<Vaga> vagas = new ArrayList<>();

}

