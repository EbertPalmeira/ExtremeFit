package extreme.fit.professor;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String nome;
    private String email;
    private String cref;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private boolean ativo;


    public Professor(DadosCadastroProfessor dados){
        this.ativo = true;
        this.nome= dados.nome();
        this.email= dados.email();
        this.cref= dados.cref();
        this.especialidade= dados.especialidade();
    }


}
