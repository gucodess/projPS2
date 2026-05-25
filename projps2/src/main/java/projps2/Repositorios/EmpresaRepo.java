package projps2.Repositorios;
import projps2.Entidades.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepo extends CrudRepository<Empresa, Long> {
    boolean existsByCnpj(String cnpj);
}

