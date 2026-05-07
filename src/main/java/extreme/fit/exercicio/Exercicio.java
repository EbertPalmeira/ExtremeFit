package extreme.fit.exercicio;


import extreme.fit.treino.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exercicio")
@EqualsAndHashCode(of = "id")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "grupoMuscular")
    private DadosGrupoMuscular grupoMuscular;

    @ManyToMany(mappedBy = "exercicios")
    private List<Treino> treinos = new ArrayList<>();



    public Exercicio(DadosCadastroExercicio dados) {
        this.nome= dados.nome();
        this.grupoMuscular= dados.dadosGrupoMuscular();

    }


}
