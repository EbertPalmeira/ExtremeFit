package extreme.fit.controller;
import extreme.fit.domain.exercicio.ExercicioRepository;
import extreme.fit.domain.professor.ProfessorRepository;
import extreme.fit.domain.treino.DadosCadastroTreino;
import extreme.fit.domain.treino.DadosListagemTreino;
import extreme.fit.domain.treino.Treino;
import extreme.fit.domain.treino.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("treino")
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroTreino dados) {
        var professor = professorRepository.findById(dados.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        var treino = new Treino(dados, professor);
        treinoRepository.save(treino);
    }

    @PostMapping("/{treinoId}/exercicios/{exercicioId}")
    @Transactional
    public void cadastrarExercicio(@PathVariable Long treinoId, @PathVariable Long exercicioId) {
        var treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));;

        var exercicio = exercicioRepository.findById(exercicioId).orElseThrow();

        treino.getExercicios().add(exercicio);
        treinoRepository.save(treino);

    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DadosListagemTreino>> listar(@PageableDefault(size = 10, sort = "nome")Pageable paginacao ){
        var page =treinoRepository.findAll(paginacao).map(DadosListagemTreino::new);
        return ResponseEntity.ok(page);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
       treinoRepository.deleteById(id);
       return ResponseEntity.noContent().build();

    }


}
