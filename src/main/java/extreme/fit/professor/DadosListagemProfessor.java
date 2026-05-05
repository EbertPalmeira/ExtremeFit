package extreme.fit.professor;

public record DadosListagemProfessor(Long id, String nome,String email, String cref) {


    public DadosListagemProfessor(Professor professor) {
        this(professor.getId(), professor.getNome(), professor.getEmail(), professor.getCref());
    }
}
