package extreme.fit.controller;
import extreme.fit.professor.ProfessorRepository;
import extreme.fit.treino.DadosCadastroTreino;
import extreme.fit.treino.Treino;
import extreme.fit.treino.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("treino")
public class TreinoController {

    @Autowired
    private TreinoRepository repository;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroTreino dados) {
        var professor = professorRepository.findById(dados.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        var treino = new Treino(dados, professor);
        repository.save(treino);
    }
}
