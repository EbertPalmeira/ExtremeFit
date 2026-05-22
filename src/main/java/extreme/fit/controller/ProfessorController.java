package extreme.fit.controller;


import extreme.fit.domain.professor.*;
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
@RequestMapping("professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder) {
        var professor = new Professor(dados);
        repository.save(professor);

        var uri = uriBuilder.path("professor/{id}").buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProfessor(professor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProfessor dados) {
        var professor = repository.getReferenceById(dados.id());
        professor.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DadosListagemProfessor>> listar(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){
        var page= repository.findAllByAtivoTrue(paginacao).map(DadosListagemProfessor::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var professor = repository.getReferenceById(id);
        professor.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id){
        var professor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }
}
