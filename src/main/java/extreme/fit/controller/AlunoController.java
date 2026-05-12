package extreme.fit.controller;


import extreme.fit.domain.aluno.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroAluno dados) {repository.save(new Aluno(dados));}


    @GetMapping
    @Transactional
    public Page<DadosListagemAluno> listar(@PageableDefault (size = 10, sort = "nome")Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);

    }


    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        var aluno = repository.getReferenceById(dados.id());
        aluno.atualizar(dados);
    }



    @DeleteMapping("{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var aluno = repository.getReferenceById(id);
        aluno.excluir();
    }



}
