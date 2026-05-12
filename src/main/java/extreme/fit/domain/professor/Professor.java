package extreme.fit.domain.professor;


import extreme.fit.domain.treino.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "professor")
@Entity(name = "professor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cref")
    private String cref;

    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Column(name = "ativo")
    private boolean ativo;


    @OneToMany(mappedBy = "professor")
    private List<Treino> treinos = new ArrayList<>();




    public Professor(DadosCadastroProfessor dados){
        this.ativo = true;
        this.nome= dados.nome();
        this.email= dados.email();
        this.cref= dados.cref();
        this.especialidade= dados.especialidade();
    }

    public void atualizar(DadosAtualizacaoProfessor dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.especialidade() != null) {
            this.especialidade = dados.especialidade();
        }
    }
    public void excluir() {
        this.ativo = false;
    }


}
