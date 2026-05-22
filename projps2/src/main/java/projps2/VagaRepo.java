package projps2;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

public interface VagaRepo extends CrudRepository<Vaga, Long> {
    List<Vaga> findByIdEmpresa(Long idEmpresa);
    List<Vaga> findByTitulo(String titulo);
    boolean existsByIdEmpresa(Long idEmpresa);
}


