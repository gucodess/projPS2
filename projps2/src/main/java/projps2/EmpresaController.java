package projps2;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
public class EmpresaController {
    @Autowired
    private EmpresaRepo empresaRepo;

    @Autowired
    private VagaRepo vagaRepo;

    public EmpresaController(){}

    @GetMapping("/api/empresas")
    Iterable<Empresa> getEmpresa() {return empresaRepo.findAll(); }

    @GetMapping("/api/empresas/{id}")
    Optional<Empresa> getEmpresa(@PathVariable long id) {return empresaRepo.findById(id); }

    @PostMapping("/api/empresas")
    Empresa createEmpresa(@RequestBody Empresa e) {
        if (empresaRepo.existsByCnpj(e.getCnpj())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe uma empresa cadastrada com este CNPJ."
            );
        }

        return empresaRepo.save(e);
    }

    @PutMapping("/api/empresas/{id}")
    Optional<Empresa> updateEmpresa(@RequestBody Empresa empresa, @PathVariable long id) {
        Optional<Empresa> opt = this.getEmpresa(id);
        if(opt.isPresent() && opt.get().getId() == empresa.getId()){
            return Optional.of(empresaRepo.save(empresa));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da empresa com id " + id);
    }

    @DeleteMapping(value = "/api/empresas/{id}")
    void deleteEmpresa(@PathVariable long id) {
        if (vagaRepo.existsByIdEmpresa(id)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Não é permitido excluir empresa vinculada a vagas."
            );
        }

        empresaRepo.deleteById(id);
}
}
