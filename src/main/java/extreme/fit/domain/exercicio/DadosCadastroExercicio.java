package extreme.fit.domain.exercicio;

import jakarta.validation.constraints.NotBlank;


public record DadosCadastroExercicio(

        @NotBlank
        String nome,

        DadosGrupoMuscular dadosGrupoMuscular


) {
}
