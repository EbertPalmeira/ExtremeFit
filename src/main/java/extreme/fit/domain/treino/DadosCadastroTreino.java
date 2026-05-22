package extreme.fit.domain.treino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTreino(
        @NotNull
        Long id,

        String nome,

        String descricao,

        String duracaoMinutos,

        Nivel nivel,

        Long professorId,

        Long alunoId




) {
}
