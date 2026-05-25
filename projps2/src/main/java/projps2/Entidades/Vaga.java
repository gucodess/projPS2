package projps2.Entidades;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// import org.springframework.stereotype.Indexed;
import java.time.*;
import java.util.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vagas")
public class Vaga {
    @Id @GeneratedValue
    private long id;

    @NotBlank(message = "Este campo é obrigatório!")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "Este campo é obrigatório")
    @Column(nullable = false, length = 2000)
    private String descricao;

    @NotNull(message = "Este campo é obrigatório")
    private LocalDate dataPublicacao;

    @NotBlank(message = "Este campo é obrigatório")
    private long idEmpresa;

    public enum status{
        ABERTA, 
        FECHADA,
        EM_PROCESSO,
        CANCELADA
    }

    @ManyToMany(mappedBy = "vagas")
    private List<AreaInteresse> areasInteresse = new ArrayList<>();

    @ManyToMany(mappedBy = "vagas")
    private List<Curso> cursos = new ArrayList<>();
}
