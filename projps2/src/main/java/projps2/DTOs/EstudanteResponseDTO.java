package projps2.DTOs;

import java.time.LocalDate;

import projps2.Entidades.Estudante;

public class EstudanteResponseDTO {
    private long id;
    private String nome;
    private String email;
    private LocalDate dataNasc;
    private Long idCurso;
    private String faculdade;
    private Integer anoIngresso;
    private Integer anoFormatura;

    public EstudanteResponseDTO(Estudante e){
        this.id = e.getId();
        this.nome = e.getNome();
        this.email = e.getEmail();
        this.dataNasc = e.getDataNascimento();
        this.idCurso = e.getIdCurso();
        this.faculdade = e.getFaculdade();
        this.anoIngresso = e.getAnoIngresso();
        this.anoFormatura = e.getAnoFormatura();

    }
}
