package extreme.fit.controller;


import extreme.fit.aluno.Aluno;
import extreme.fit.aluno.AlunoRepository;
import extreme.fit.aluno.DadosCadastroAluno;
import extreme.fit.aluno.DadosListagemAluno;
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




}
