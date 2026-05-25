package projps2.Controllers;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import projps2.Entidades.AreaInteresse;
import projps2.Repositorios.AreaInteresseRepo;

@RestController
public class AreaInteresseController {
    @Autowired
    private AreaInteresseRepo areaInteresseRepo;
    public AreaInteresseController(){}

    @GetMapping("/api/areas-interesse")
    Iterable<AreaInteresse> getAreaInteresse() {return areaInteresseRepo.findAll(); }

    @GetMapping("/api/areas-interesse/{id}")
    Optional<AreaInteresse> getAreaInteresse(@PathVariable long id) {return areaInteresseRepo.findById(id); }

    @PostMapping("/api/areas-interesse")
    AreaInteresse createAreaInteresse(@RequestBody AreaInteresse a) {return areaInteresseRepo.save(a); }

    @PutMapping("/api/areas-interesse/{id}")
    Optional<AreaInteresse> updateAreaInteresse(@RequestBody AreaInteresse areaInteresse, @PathVariable long id) {
        Optional<AreaInteresse> opt = this.getAreaInteresse(id);
        if(opt.isPresent() && opt.get().getId() == areaInteresse.getId()){
            return Optional.of(areaInteresseRepo.save(areaInteresse));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da área de interesse de id " + id);
    }

    @DeleteMapping(value = "/api/areas-interesse/{id}")
    void deleteAreaInteresse(@PathVariable long id) {areaInteresseRepo.deleteById(id);}
}
