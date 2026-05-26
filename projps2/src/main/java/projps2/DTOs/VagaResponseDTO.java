package projps2.DTOs;

import java.time.LocalDate;
import lombok.Getter;
import projps2.Entidades.Vaga;

@Getter
public class VagaResponseDTO {
    private long id;
    private String titulo;
    private String descricao;
    private LocalDate dataPublicacao;
    private long idEmpresa;

    public VagaResponseDTO(Vaga v){
        this.id = v.getId();
        this.titulo = v.getTitulo();
        this.descricao = v.getDescricao();
        this.dataPublicacao = v.getDataPublicacao();
        this.idEmpresa = v.getIdEmpresa();
    }
}
