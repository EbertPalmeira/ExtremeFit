package extreme.fit.aluno;


import extreme.fit.treino.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "aluno")
@Entity(name = "aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data-nascimento")
    private String dataNascimento;

    @Column(name = "ativo")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToMany(mappedBy = "aluno")
    private List<Treino> treinos = new ArrayList<>();


    public Aluno(DadosCadastroAluno dados){
        this.ativo = true;
        this.nome= dados.nome();
        this.email= dados.email();
        this.telefone= dados.telefone();

    }

    public void atualizar(DadosAtualizacaoAluno dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }


    }
    public void excluir() {
        this.ativo = false;
    }

}
