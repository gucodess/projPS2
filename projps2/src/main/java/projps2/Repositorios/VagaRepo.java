package projps2.Repositorios;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import projps2.Entidades.Vaga;

public interface VagaRepo extends CrudRepository<Vaga, Long> {
    List<Vaga> findByIdEmpresa(Long idEmpresa);
    List<Vaga> findByTitulo(String titulo);
    List<Vaga> findByStatus(Vaga.Status status);
    List<Vaga> findByCursosId(Long idCurso);
    boolean existsByIdEmpresa(Long idEmpresa);
}


