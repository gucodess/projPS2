package projps2.Entidades;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
// import org.springframework.stereotype.Indexed;
import java.time.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudantes")
public class Estudante {
    @Id @GeneratedValue
    private long id;

    @NotBlank(message = "Este campo é obrigatório") 
    @Column(nullable = false, length = 150)
    private String nome;

    @NotBlank(message = "Este campo é obrigatório") 
    @Email(message = "Insira um formato válido de e-mail")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotNull(message = "Este campo é obrigatório") 
    private LocalDate dataNascimento;
    
    @NotNull(message = "Este campo é obrigatório") 
    private long idCurso;
    
    @NotBlank(message = "Este campo é obrigatório") 
    @Column(nullable = false, length = 150)
    private String faculdade;
    
    @NotNull(message = "Este campo é obrigatório") 
    private Integer anoIngresso;
    
    @NotNull(message = "Este campo é obrigatório") 
    private Integer anoFormatura;

    @ManyToMany
    @JoinTable(
            name = "estudante_area_interesse",
            joinColumns = @JoinColumn(name = "estudante_id"),
            inverseJoinColumns = @JoinColumn(name = "area_interesse_id")
        )
    private List<AreaInteresse> areasInteresse = new ArrayList<>();
}
