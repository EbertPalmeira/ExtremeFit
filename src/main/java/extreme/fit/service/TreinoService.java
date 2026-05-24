package extreme.fit.service;

import extreme.fit.domain.aluno.AlunoRepository;
import extreme.fit.domain.professor.ProfessorRepository;
import extreme.fit.domain.treino.DadosCadastroTreino;
import extreme.fit.domain.treino.Treino;
import extreme.fit.domain.treino.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository  alunoRepository;

    public Treino cadastrar( DadosCadastroTreino dados) {
        var professor = professorRepository.findById(dados.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        var aluno = alunoRepository.findById(dados.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        var treino = new Treino(dados, professor ,aluno);
        treinoRepository.save(treino);
        return treino;
    }
}
