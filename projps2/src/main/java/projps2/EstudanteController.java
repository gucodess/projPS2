package projps2;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
class EstudanteController{
    @Autowired
    private EstudanteRepo estudanteRepo;
    @Autowired
    private CursoRepo cursoRepo;
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

    @DeleteMapping(value = "/api/estudantes/{id}")
    void deleteEstudante(@PathVariable long id) {estudanteRepo.deleteById(id);}
}