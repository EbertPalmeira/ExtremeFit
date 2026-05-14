package extreme.fit.domain.aluno;

public record DadosDetalhamentoAluno(Long id ,
                                     String nome,
                                     String email,
                                     String matricula,
                                     String telefone) {


    public DadosDetalhamentoAluno(Aluno aluno){
        this(aluno.getId(),aluno.getNome(),aluno.getEmail(),aluno.getMatricula(),aluno.getTelefone());
    }
}
