package extreme.fit.controller;


import extreme.fit.exercicio.DadosCadastroExercicio;
import extreme.fit.exercicio.Exercicio;
import extreme.fit.exercicio.ExercicioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exercicio")
public class ExercioController {

    @Autowired
    private ExercicioRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroExercicio dados) {
        repository.save(new Exercicio(dados));

    }
}
