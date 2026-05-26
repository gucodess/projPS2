package projps2.Controllers;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import projps2.Entidades.AreaInteresse;
import projps2.Entidades.Vaga;
import projps2.Repositorios.AreaInteresseRepo;
import projps2.Repositorios.CursoRepo;
import projps2.Repositorios.EmpresaRepo;
import projps2.Repositorios.VagaRepo;

@RestController
public class VagaController {
    @Autowired
    private VagaRepo vagaRepo;

    @Autowired
    private EmpresaRepo empresaRepo;

    @Autowired
    private AreaInteresseRepo areaInteresseRepo;

    @Autowired
    private CursoRepo cursoRepo;

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

    //GETMAPPING VAGA X AREA INTERESSE

    @GetMapping("api/vagas/{idVaga}/areas-interesse")
    public Iterable<AreaInteresse> getAreaInteresseVaga(@PathVariable long idVaga){
        Optional<Vaga> opt = vagaRepo.findById(idVaga);
        if(opt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada!");
        }

        return opt.get().getAreasInteresse();
    }

    //GETMAPPING VAGA X CURSO

    @GetMapping("api/vagas/{idVaga}/cursos")
    public Iterable<Curso> getCursoVaga(@PathVariable long idVaga){
        Optional<Vaga> opt = vagaRepo.findById(idVaga);

        if(opt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
        }
        return opt.get().getCursos();
    }

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

    //PUTMAPPING DE VAGA X AREA INTERESSE

    @PutMapping("api/vagas/{idVaga}/areas-interesse/{idArea}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarAreaInteresseVaga(@PathVariable long idVaga, @PathVariable long idArea){
        Optional<Vaga> vagaOpt = vagaRepo.findById(idVaga);
        Optional<AreaInteresse> areaOpt = areaInteresseRepo.findById(idArea);

        if(vagaOpt.isEmpty() || areaOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga ou Área não encontradas");
        }

        Vaga vaga = vagaOpt.get();
        AreaInteresse area = areaOpt.get();

        if(vaga.getAreasInteresse().contains(area)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Associação já existente!");
        }

        vaga.getAreasInteresse().add(area);
        vagaRepo.save(vaga);
    }

    //PUTMAPPING VAGA X CURSO

    @PutMapping("/api/vagas/{idVaga}/cursos/{idCurso}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarCursoVaga(@PathVariable long idVaga, @PathVariable long idArea){
        
    }

    @DeleteMapping(value = "/api/vagas/{id}")
    void deleteVaga(@PathVariable long id) {vagaRepo.deleteById(id);}
}
