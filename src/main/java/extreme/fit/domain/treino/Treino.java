package extreme.fit.domain.treino;

import extreme.fit.domain.professor.Professor;
import extreme.fit.domain.aluno.Aluno;
import extreme.fit.domain.exercicio.Exercicio;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treino")
@EqualsAndHashCode(of = "id")
public class Treino {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "duracao")
    private String duracaoMinutos;

    @Column(name = "nivel")
    private Nivel nivel;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToMany
    @JoinTable(
            name = "treino_exercicio",
            joinColumns = @JoinColumn(name = "treino_id"),
            inverseJoinColumns = @JoinColumn(name = "exercicio_id")
    )
    private List<Exercicio> exercicios = new ArrayList<>();

    public Treino(DadosCadastroTreino dados, Professor professor) {

        this.nome= dados.nome();
        this.descricao= dados.descricao();
        this.duracaoMinutos= dados.duracaoMinutos();
        this.nivel= dados.nivel();
        this.professor = professor;

    }




}
