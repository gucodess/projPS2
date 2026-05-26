package projps2.Controllers;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import jakarta.transaction.Transactional;
import projps2.Entidades.AreaInteresse;
import projps2.Entidades.Estudante;
import projps2.Repositorios.CursoRepo;
import projps2.Repositorios.EstudanteRepo;
import projps2.DTOs.AreaInteresseResponseDTO;
import projps2.DTOs.EstudanteResponseDTO;

@RestController
class EstudanteController{
    @Autowired
    private EstudanteRepo estudanteRepo;
    @Autowired
    private CursoRepo cursoRepo;
    @Autowired
    private AreaInteresseRepo areaInteresseRepo;

    public EstudanteController(){}

    @GetMapping("/api/estudantes")
    Iterable<Estudante> getEstudante(@RequestParam(required = false) Long idCurso, @RequestParam(required = false) Integer anoIngresso, @RequestParam(required = false) Integer anoFormatura, @RequestParam(required = false) String nome) {
        if(idCurso != null){
            return estudanteRepo.findByIdCurso(idCurso);
        }else if(anoIngresso != null){
            return estudanteRepo.findByAnoIngresso(anoIngresso);
        }else if(anoFormatura != null){
            return estudanteRepo.findByAnoFormatura(anoFormatura);
        }else if(nome != null){
            return estudanteRepo.findByNomeContainingIgnoreCase(nome);
        }else{
            return estudanteRepo.findAll();
        }
     }

    @GetMapping("/api/estudantes/{id}")
    Optional<Estudante> getEstudante(@PathVariable long id) {return estudanteRepo.findById(id); }

    // GETMAPPING DA RELAÇÃO ESTUDANTE X ÁREA DE INTERESSE

    @GetMapping("/api/estudantes/{idEstudante}/areas-interesse")
    public Iterable<AreaInteresse> getAreaInteresseEstudante(@PathVariable long idEstudante){
        Optional<Estudante> opt = estudanteRepo.findById(idEstudante);
        if(opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
        }
        return opt.get().getAreasInteresse();
    }


    @PostMapping("/api/estudantes")
    Estudante createEstudante(@RequestBody Estudante e) {
        if (estudanteRepo.existsByEmail(e.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe um estudante cadastrado com este e-mail."
            );
        }

        if (!cursoRepo.existsById(e.getIdCurso())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Não é permitido cadastrar estudante com idCurso inexistente."
            );
        }

        if (e.getAnoFormatura() < e.getAnoIngresso()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O ano de formatura deve ser maior ou igual ao ano de ingresso."
            );
        }

        return estudanteRepo.save(e);
    }

    @PutMapping("/api/estudantes/{id}")
    Optional<Estudante> updateEstudante(@RequestBody Estudante estudante, @PathVariable long id) {
        Optional<Estudante> opt = this.getEstudante(id);

        if(opt.isPresent() && opt.get().getId() == estudante.getId()){

            if (!cursoRepo.existsById(estudante.getIdCurso())) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é permitido atualizar estudante com idCurso inexistente."
                );
            }

            if (estudante.getAnoFormatura() < estudante.getAnoIngresso()) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O ano de formatura deve ser maior ou igual ao ano de ingresso."
                );
            }

            return Optional.of(estudanteRepo.save(estudante));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados do estudante com id " + id);
    }

    // PUTMAPPING DA RELAÇÃO ESTUDANTE X ÁREA DE INTERESSE

    @PutMapping("api/estudantes/{idEstudante}/areas-interesse/{idArea}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // HTTP 204
    public void associarAreaInteresseEstudante(@PathVariable long idEstudante, @PathVariable long idArea){
        Optional<Estudante> estudanteOpt = estudanteRepo.findById(idEstudante);
        Optional<AreaInteresse> areaOpt = areaInteresseRepo.findById(idArea);

        if(estudanteOpt.isEmpty() || areaOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante ou área de interesse não encontrados");
        }

        Estudante estudante = estudanteOpt.get();
        AreaInteresse area = areaOpt.get();

        if(estudante.getAreasInteresse().contains(area)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta associação já existe!");
        }

        estudante.getAreasInteresse().add(area);
        estudanteRepo.save(estudante);
    }

    @DeleteMapping(value = "/api/estudantes/{id}")
    void deleteEstudante(@PathVariable long id) {estudanteRepo.deleteById(id);}

    // DELETEMAPPING DA RELAÇÃO ESTUDANTE X ÁREA DE INTERESSE

    @DeleteMapping("api/estudantes/{idEstudante}/areas-interesse/{idArea}")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void desassociarAreaInteresseEstudante(@PathVariable long idEstudante, @PathVariable long idArea){
        Optional<Estudante> estudanteOpt = estudanteRepo.findById(idEstudante);
        Optional<AreaInteresse> areaOpt = areaInteresseRepo.findById(idArea);

        if(estudanteOpt.isEmpty() || areaOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante ou área de interesse não encontrados");
        } 

        Estudante estudante = estudanteOpt.get();
        AreaInteresse area = areaOpt.get();

        estudante.getAreasInteresse().remove(area);
        estudanteRepo.save(estudante);
    }
}