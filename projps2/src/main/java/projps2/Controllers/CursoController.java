package projps2.Controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import projps2.Entidades.Curso;
import projps2.Repositorios.CursoRepo;
import projps2.Repositorios.EstudanteRepo;
import projps2.Repositorios.VagaRepo;

@RestController
public class CursoController {
    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private EstudanteRepo estudanteRepo;

    @Autowired
    private VagaRepo vagaRepo;

    public CursoController(){}

    @GetMapping("/api/cursos")
    Iterable<Curso> getEmpresa() {return cursoRepo.findAll(); }

    @GetMapping("/api/cursos/{id}")
    Optional<Curso> getCurso(@PathVariable long id) {return cursoRepo.findById(id); }

    @PostMapping("/api/cursos")
    Curso createCurso(@RequestBody Curso c) {
        if (cursoRepo.existsByNome(c.getNome())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe um curso cadastrado com este nome."
            );
        }

        return cursoRepo.save(c);
    }

    @PutMapping("/api/cursos/{id}")
    Optional<Curso> updateCurso(@RequestBody Curso curso, @PathVariable long id) {
        Optional<Curso> opt = this.getCurso(id);
        if(opt.isPresent() && opt.get().getId() == curso.getId()){
            return Optional.of(cursoRepo.save(curso));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do curso de id " + id);
    }

    @DeleteMapping(value = "/api/cursos/{id}")
    void deleteCurso(@PathVariable long id) {
        if (!estudanteRepo.findByIdCurso(id).isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Não é permitido excluir curso vinculado a estudantes."
            );
        }

        if(!vagaRepo.findByCursosId(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é permitido excluir curso vínculado a vagas!");
        }

        cursoRepo.deleteById(id);
    }
}
