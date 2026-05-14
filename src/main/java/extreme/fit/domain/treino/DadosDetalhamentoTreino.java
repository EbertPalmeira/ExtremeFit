package extreme.fit.domain.treino;

public record DadosDetalhamentoTreino(
        Long id,
        String nome ,
        String descricao,
        String duracaoMinutos ,
        Nivel nivel,
        Long professorId,
        Long alunoId

) {

    public DadosDetalhamentoTreino(Treino treino){
        this(treino.getId(),treino.getNome(),treino.getDescricao(),treino.getDuracaoMinutos(),treino.getNivel(), treino.getProfessor().getId(), treino.getAluno().getId());
    }
}
