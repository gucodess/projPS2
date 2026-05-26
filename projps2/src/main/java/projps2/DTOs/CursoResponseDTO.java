package projps2.DTOs;

import projps2.Entidades.Curso;
import lombok.Getter;

@Getter
public class CursoResponseDTO {
    
    private long id;
    private String nome;

    public CursoResponseDTO(Curso c){
        this.id = c.getId();
        this.nome = c.getNome();
    }

    public Long getId(){return id;}
    public String getNome(){return nome;}
}
