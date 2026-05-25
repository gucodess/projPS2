package projps2.Controllers;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import projps2.Entidades.Vaga;
import projps2.Repositorios.EmpresaRepo;
import projps2.Repositorios.VagaRepo;

@RestController
public class VagaController {
    @Autowired
    private VagaRepo vagaRepo;

    @Autowired
    private EmpresaRepo empresaRepo;

    public VagaController(){}

    @GetMapping("/api/vagas")
    Iterable<Vaga> getVaga(@RequestParam(required = false) Long idEmpresa, @RequestParam(required = false) String titulo) {
        if(idEmpresa != null){
            return vagaRepo.findByIdEmpresa(idEmpresa);
        }else if(titulo != null){
            return vagaRepo.findByTitulo(titulo);
        }else{
            return vagaRepo.findAll();
        }
    }

    @GetMapping("/api/vagas/{id}")
    Optional<Vaga> getVaga(@PathVariable long id) {return vagaRepo.findById(id); }

    @PostMapping("/api/vagas")
    Vaga createVaga(@RequestBody Vaga v) {
        if (!empresaRepo.existsById(v.getIdEmpresa())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Não é permitido cadastrar vaga com idEmpresa inexistente."
            );
        }

        return vagaRepo.save(v);
    }

    @PutMapping("/api/vagas/{id}")
    Optional<Vaga> updateVaga(@RequestBody Vaga vaga, @PathVariable long id) {
        Optional<Vaga> opt = this.getVaga(id);

        if(opt.isPresent() && opt.get().getId() == vaga.getId()){

            if (!empresaRepo.existsById(vaga.getIdEmpresa())) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é permitido atualizar vaga com idEmpresa inexistente."
                );
            }

            return Optional.of(vagaRepo.save(vaga));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da vaga de id " + id);
    }

    @DeleteMapping(value = "/api/vagas/{id}")
    void deleteVaga(@PathVariable long id) {vagaRepo.deleteById(id);}
}
