package extreme.fit.domain.exercicio;

public record DadosDetalhamentoExercicio(
        Long id,
        String nome,
        DadosGrupoMuscular dadosGrupoMuscular

) {

    public DadosDetalhamentoExercicio(Exercicio exercicio){
        this(exercicio.getId(), exercicio.getNome(),exercicio.getGrupoMuscular());
    }
}
