package projps2;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EstudanteRepo extends CrudRepository<Estudante, Long> {
    List<Estudante> findByIdCurso(Long idCurso);
    List<Estudante> findByAnoIngresso(Integer anoIngresso);
    List<Estudante> findByAnoFormatura(Integer anoFormatura);
    List<Estudante> findByNomeContainingIgnoreCase(String nome);

    boolean existsByEmail(String email);
}


