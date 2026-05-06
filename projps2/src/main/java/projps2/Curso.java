package projps2;
import lombok.*;
import jakarta.persistence.*;
// import org.springframework.stereotype.Indexed;
import jakarta.validation.constraints.NotBlank;

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
}
