package extreme.fit.controller;
import extreme.fit.exercicio.ExercicioRepository;
import extreme.fit.professor.ProfessorRepository;
import extreme.fit.treino.DadosCadastroTreino;
import extreme.fit.treino.DadosListagemTreino;
import extreme.fit.treino.Treino;
import extreme.fit.treino.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DadosListagemTreino> listar(@PageableDefault(size = 10, sort = "nome")Pageable paginacao ){
        return treinoRepository.findAll(paginacao).map(DadosListagemTreino::new);

    }


}
