package extreme.fit.treino;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTreino(
        @NotBlank
        Long id,

        String nome,

        String descricao,

        String duracaoMinutos,

        Nivel nivel,

        Long professorId,

        Long alunoId




) {
}
