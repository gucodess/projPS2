package projps2.Repositorios;
import projps2.Entidades.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepo extends CrudRepository<Curso, Long> {
    boolean existsByNome(String nome);
}

