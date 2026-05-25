package projps2.Repositorios;
import projps2.Entidades.AreaInteresse;
import org.springframework.data.repository.CrudRepository;

public interface AreaInteresseRepo extends CrudRepository<AreaInteresse, Long> {
    boolean existsByNome(String nome);
}
