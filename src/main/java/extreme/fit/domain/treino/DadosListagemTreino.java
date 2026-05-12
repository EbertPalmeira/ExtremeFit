package extreme.fit.domain.treino;

public record DadosListagemTreino(Long id,
                                  String nome,
                                  String descricao,
                                  String duracaoMinutos,
                                  Nivel nivel,
                                  String nomeProfessor) {


    public DadosListagemTreino(Treino treino){
        this(treino.getId(),
              treino.getNome(),
                treino.getDescricao(),
                treino.getDuracaoMinutos(),
                treino.getNivel(),
                treino.getProfessor().getNome());

    }
}
