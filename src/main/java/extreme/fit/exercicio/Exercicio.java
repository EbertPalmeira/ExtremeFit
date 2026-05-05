package extreme.fit.exercicio;

import extreme.fit.treino.Treino;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercicio")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "exercicios")
    private List<Treino> treinos = new ArrayList<>();

}
