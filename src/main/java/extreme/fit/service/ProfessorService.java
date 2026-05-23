package extreme.fit.service;

import extreme.fit.domain.professor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public Professor cadastrar(DadosCadastroProfessor dados) {
        var professor = new Professor(dados);
        repository.save(professor);
        return professor;

    }

    public Professor atualizar(DadosAtualizacaoProfessor dados) {
        var professor = repository.getReferenceById(dados.id());
        professor.atualizar(dados);
        return professor;
    }

    public Page<DadosListagemProfessor> listar(Pageable paginacao) {
        var page= repository.findAllByAtivoTrue(paginacao).map(DadosListagemProfessor::new);
        return page ;
    }

    public Professor excluir(Long id) {
        var professor = repository.getReferenceById(id);
        return  professor;
    }

    public Professor detalhar(Long id) {
        var professor = repository.getReferenceById(id);
        return professor;
    }
}
