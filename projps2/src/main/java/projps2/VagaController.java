package projps2;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

public class VagaController {
    @Autowired
    private VagaRepo vagaRepo;
    public VagaController(){}

    @GetMapping("/api/vagas")
    Iterable<Vaga> getVaga() {return vagaRepo.findAll(); }

    @GetMapping("/api/vagas/{id}")
    Optional<Vaga> getVaga(@PathVariable long id) {return vagaRepo.findById(id); }

    @PostMapping("/api/vagas")
    Vaga createVaga(@RequestBody Vaga v) {return vagaRepo.save(v); }

    @PutMapping("/api/vagas/{id}")
    Optional<Vaga> updateVaga(@RequestBody Vaga vaga, @PathVariable long id) {
        Optional<Vaga> opt = this.getVaga(id);
        if(opt.isPresent() && opt.get().getId() == vaga.getId()){
            return Optional.of(vagaRepo.save(vaga));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da vaga de id " + id);
    }

    @DeleteMapping(value = "/api/vagas/{id}")
    void deleteVaga(@PathVariable long id) {vagaRepo.deleteById(id);}
}
