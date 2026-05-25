package projps2.Entidades;
import lombok.*;
import jakarta.persistence.*;
// import org.springframework.stereotype.Indexed;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresas")
public class Empresa {
    @Id @GeneratedValue
    private long id;

    @NotBlank(message = "Este campo é obrigatório") 
    @Column(nullable = false, unique = false, length = 150)
    private String nome;

    @NotBlank(message = "Este campo é obrigatório") 
    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @NotBlank(message = "Este campo é obrigatório") 
    @Email(message = "Insira um formato de e-mail valido!")
    @Column(nullable = false)
    private String emailContato;    
}
