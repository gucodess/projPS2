package projps2;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Indexed;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresas")
public class Empresa {
    @Id @GeneratedValue
    private long id;
    private String nome;
    private String cnpj;
    private String emailContato;    
}
