package extreme.fit.controller;


import extreme.fit.domain.exercicio.DadosCadastroExercicio;
import extreme.fit.domain.exercicio.DadosDetalhamentoExercicio;
import extreme.fit.domain.exercicio.Exercicio;
import extreme.fit.domain.exercicio.ExercicioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("exercicio")
public class ExercioController {

    @Autowired
    private ExercicioRepository repository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroExercicio dados, UriComponentsBuilder uriBuilder) {
        var exercicio = new Exercicio();
        repository.save(exercicio);
        var uri= uriBuilder.path("/exercicio/{id}").buildAndExpand(exercicio.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoExercicio(exercicio));


    }
}
