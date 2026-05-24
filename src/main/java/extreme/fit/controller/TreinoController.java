package extreme.fit.controller;
import extreme.fit.domain.aluno.AlunoRepository;
import extreme.fit.domain.exercicio.ExercicioRepository;
import extreme.fit.domain.professor.ProfessorRepository;
import extreme.fit.domain.treino.*;
import extreme.fit.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("treino")
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    TreinoService  treinoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroTreino dados,
                                    UriComponentsBuilder uriBuilder) {
        var treino = treinoService.cadastrar(dados);

        var uri = uriBuilder.path("/treino/{id}")
                .buildAndExpand(treino.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTreino(treino));
    }

    @PostMapping("/{treinoId}/exercicios/{exercicioId}")
    @Transactional
    public ResponseEntity cadastrarExercicio(@PathVariable Long treinoId, @PathVariable Long exercicioId , UriComponentsBuilder uriBuilder) {
        var treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado"));;

        var exercicio = exercicioRepository.findById(exercicioId).orElseThrow();

        treino.getExercicios().add(exercicio);
        treinoRepository.save(treino);

        var uri =  uriBuilder.path("/treino/{treinoId}/exercicios/{exercicioId}").buildAndExpand(treinoId, exercicioId).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTreino(treino));

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
