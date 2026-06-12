package extreme.fit.controller;


import extreme.fit.domain.aluno.*;
import extreme.fit.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("aluno")
@SecurityRequirement(name = "bearer-key")
public class AlunoController {

    @Autowired
    private AlunoService  alunoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAluno dados ,UriComponentsBuilder uriBuilder) {
        var aluno = this.alunoService.cadastrar(dados);
        var uri = uriBuilder.path("aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }


    @GetMapping
    @Transactional
    public ResponseEntity <Page<DadosListagemAluno>>listar(@PageableDefault (size = 10, sort = "nome")Pageable paginacao){
        var page= this.alunoService.listar(paginacao);
        return ResponseEntity.ok(page);

    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        var aluno = this.alunoService.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }



    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        this.alunoService.excluir(id);
        return ResponseEntity.noContent().build();
    }





}
