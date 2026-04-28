package projps2;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.stereotype.Indexed;
import java.time.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vagas")
public class Vaga {
    @Id @GeneratedValue
    private long id;
    private String titulo;
    private String descricao;
    private LocalDate dataPublicacao;
    private long idEmpresa;

    public enum status{
        ABERTA, 
        FECHADA,
        EM_PROCESSO,
        CANCELADA
    }
}
