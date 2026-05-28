package extreme.fit.service;

import extreme.fit.domain.aluno.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoService service;


    private DadosCadastroAluno dto;




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
        when(repository.findAllByAtivoTrue(any(Pageable.class))).thenReturn(page);


        //act
        var resultado = service.listar(Pageable.unpaged());

        //assert
        assertNotNull(resultado,"Não e nulo");
        assertEquals(1, resultado.getTotalElements());
        Mockito.verify(repository, Mockito.times(1)).findAllByAtivoTrue(any(Pageable.class));
    }
    @Test
    void deveriaAtualizarAluno() {

        //arrange
        var dadosCadastro = new DadosCadastroAluno("ebert", "12133",
                "123@GM", "64023I9329","28/04/2001");
        var aluno = new Aluno(dadosCadastro);

        var dadosAtualizacao = new DadosAtualizacaoAluno(1L, "Ebert Atualizado", "novoemail@email.com", "64999999999", "01/01/2000");
        aluno.setId(1L);

        when(repository.getReferenceById(1L)).thenReturn(aluno);
        when(repository.save(any(Aluno.class))).thenReturn(aluno);


        //act
        var resultado = service.atualizar(dadosAtualizacao);


        //assert
        assertNotNull(resultado);
        assertEquals("Ebert Atualizado", resultado.getNome());
        assertEquals("novoemail@email.com", resultado.getEmail());
        verify(repository, times(1)).save(any(Aluno.class));


    }

    @Test
    void deveriaExcluir(){
        //arrange
        var dadosCadastro = new DadosCadastroAluno("ebert", "12133",
                "123@GM", "64023I9329","28/04/2001");
        var aluno = new Aluno(dadosCadastro);
        aluno.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(aluno));

        // act
        service.excluir(1L);


        //assert
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Aluno.class));
    }

}