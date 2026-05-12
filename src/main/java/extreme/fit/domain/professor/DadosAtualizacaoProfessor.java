package extreme.fit.domain.professor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProfessor(
        @NotNull
        Long id,
        String nome,
        String email,
        Especialidade especialidade

) {
}
