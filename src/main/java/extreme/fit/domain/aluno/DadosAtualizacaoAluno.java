package extreme.fit.domain.aluno;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        String dataNascimento
             ) {
}
