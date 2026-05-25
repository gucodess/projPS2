package projps2.Repositorios;

import org.springframework.data.repository.CrudRepository;

import projps2.Entidades.Estudante;

import java.util.List;

public interface EstudanteRepo extends CrudRepository<Estudante, Long> {
    List<Estudante> findByIdCurso(Long idCurso);
    List<Estudante> findByAnoIngresso(Integer anoIngresso);
    List<Estudante> findbyAnoFormatura(Integer anoFormatura);
    List<Estudante> findByNomeContainingIgnoreCase(String nome);
}


