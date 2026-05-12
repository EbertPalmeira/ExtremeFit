package extreme.fit.domain.professor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroProfessor(



        @NotBlank
        String nome,
        @NotBlank
        String email,

        @NotBlank
        String cref,

        @NotNull
        Especialidade especialidade



) {
}
