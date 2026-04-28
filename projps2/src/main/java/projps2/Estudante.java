package projps2;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Indexed;
import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudantes")
public class Estudante {
    @Id @GeneratedValue
    private long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private long idCurso;
    private String faculdade;
    private Integer anoIngrasso;
    private Integer anoFormatura;
}
