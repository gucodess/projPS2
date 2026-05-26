package projps2.Repositorios;
import projps2.Entidades.Empresa;
import org.springframework.data.repository.JpaRepository;

public interface EmpresaRepo extends JpaRepository<Empresa, Long> {
    boolean existsByCnpj(String cnpj);
}

