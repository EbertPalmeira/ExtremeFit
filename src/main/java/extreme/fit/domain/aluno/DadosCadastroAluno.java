package extreme.fit.domain.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAluno(

        @NotBlank
        String nome,
        @NotBlank
        String matricula,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String dataNascimento


) {
}
