package projps2.Controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import projps2.Entidades.Curso;
import projps2.Repositorios.CursoRepo;

@RestController
public class CursoController {
    @Autowired
    private CursoRepo cursoRepo;
    public CursoController(){}

    @GetMapping("/api/cursos")
    Iterable<Curso> getEmpresa() {return cursoRepo.findAll(); }

    @GetMapping("/api/cursos/{id}")
    Optional<Curso> getCurso(@PathVariable long id) {return cursoRepo.findById(id); }

    @PostMapping("/api/cursos")
    Curso createCurso(@RequestBody Curso c) {return cursoRepo.save(c); }

    @PutMapping("/api/cursos/{id}")
    Optional<Curso> updateCurso(@RequestBody Curso curso, @PathVariable long id) {
        Optional<Curso> opt = this.getCurso(id);
        if(opt.isPresent() && opt.get().getId() == curso.getId()){
            return Optional.of(cursoRepo.save(curso));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do curso de id " + id);
    }

    @DeleteMapping(value = "/api/cursos/{id}")
    void deleteCurso(@PathVariable long id) {cursoRepo.deleteById(id);}
}
