package projps2.DTOs;

import java.time.LocalDate;

import projps2.Entidades.Vaga;

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
