package extreme.fit.domain.usuario;

public record DadosCadastroUsuario(String login, String senha) {

    public DadosCadastroUsuario(DadosCadastroUsuario dados) {
        this(dados.login, dados.senha);
    }
}
