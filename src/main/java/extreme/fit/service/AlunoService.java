package extreme.fit.service;

import extreme.fit.domain.aluno.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;


    public Aluno cadastrar( DadosCadastroAluno dados ) {
        var aluno = new Aluno(dados);
        repository.save(aluno);
        return aluno;
    }

    public  Page<DadosListagemAluno> listar(Pageable paginacao){
        var page= repository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);
        return page;

    }

    public Aluno atualizar( DadosAtualizacaoAluno dados) {
        var aluno = repository.getReferenceById(dados.id());
        aluno.atualizar(dados);

        return repository.save(aluno);
    }

    public void excluir(Long id){
        var aluno = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        aluno.excluir();
        repository.save(aluno);
    }
}
