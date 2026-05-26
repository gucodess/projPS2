package projps2.DTOs;

import projps2.Entidades.AreaInteresse;
import lombok.Getter;

@Getter
public class AreaInteresseResponseDTO {
    
    private long id;
    private String nome;

    public AreaInteresseResponseDTO(AreaInteresse area){
        this.id = area.getId();
        this.nome = area.getNome();
    }

    public Long getId(){return id;}
    public String getNome(){return nome;}
}
