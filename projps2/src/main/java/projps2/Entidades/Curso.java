package projps2.Entidades;
import lombok.*;
import jakarta.persistence.*;
// import org.springframework.stereotype.Indexed;
import jakarta.validation.constraints.NotBlank;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {
    @Id @GeneratedValue
    public Long id;

    @NotBlank(message = "Este campo é obrigatório") 
    @Column(nullable = false, unique = true, length = 120)
    public String nome;

    @ManyToMany(mappedBy = "cursos")
    @JsonIgnore
    private List<Vaga> vagas = new ArrayList<>();
}
