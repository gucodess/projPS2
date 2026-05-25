package projps2.Entidades;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "areas-interesse")
public class AreaInteresse {
    @Id
    @GeneratedValue
    public Long id;

    @NotBlank(message = "Este campo é obrigatório") 
    @Column(nullable = false, unique = true, length = 120)
    public String nome;

}

