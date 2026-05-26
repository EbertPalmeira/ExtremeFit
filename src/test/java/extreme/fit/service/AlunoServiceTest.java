package extreme.fit.service;

import extreme.fit.domain.aluno.Aluno;
import extreme.fit.domain.aluno.AlunoRepository;
import extreme.fit.domain.aluno.DadosCadastroAluno;
import extreme.fit.domain.aluno.DadosListagemAluno;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoService service;


    private DadosCadastroAluno dto;


    private DadosListagemAluno dtoListagem;

    @Test
    void deveriaCadastrarAluno() {
        //Arrange
        this.dto=new DadosCadastroAluno("ebert", "212121212",
                "ebert@123", "64981476764","28/04/2001");

        //act
        var resultado = service.cadastrar(dto);

        //assert

        assertNotNull(resultado,"Não e nulo");

        assertEquals("ebert", resultado.getNome());
        assertEquals("212121212", resultado.getMatricula());
        assertEquals("ebert@123" , resultado.getEmail());
        assertEquals("64981476764", resultado.getTelefone());
        assertEquals("28/04/2001", resultado.getDataNascimento());


    }
    @Test
    void deveriaListarAlunos() {

        //arrange
        var dadosAluno =new DadosCadastroAluno("ebert", "212121212",
                "ebert@123", "64981476764","28/04/2001");
        var aluno = new Aluno(dadosAluno);

        var listaAlunos = List.of(aluno);
        var page = new PageImpl<>(listaAlunos);


        //act


        //assert

    }

}