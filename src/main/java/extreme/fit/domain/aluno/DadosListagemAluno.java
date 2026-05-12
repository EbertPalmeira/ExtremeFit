package extreme.fit.domain.aluno;

public record DadosListagemAluno(


        Long Id,


        String nome,


        String email,


        String matricula,


        String telefone



) {

    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.getTelefone());
    }
}
