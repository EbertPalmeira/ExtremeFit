package extreme.fit.domain.professor;

public record DadosDetalhamentoProfessor(
        Long id ,
        String nome,
        String email,
        String cref,
        Especialidade especialidade

) {

    public DadosDetalhamentoProfessor(Professor professor) {
        this(professor.getId(), professor.getNome(), professor.getEmail(), professor.getCref(), professor.getEspecialidade());
    }
}
