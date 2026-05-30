package extreme.fit.service;

import extreme.fit.domain.aluno.Aluno;
import extreme.fit.domain.aluno.DadosCadastroAluno;
import extreme.fit.domain.professor.*;
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
class ProfessorServiceTest {

    @Mock
    ProfessorRepository repository;

    @InjectMocks
    ProfessorService service;

    @Mock
    DadosCadastroProfessor dto;


    @Test
    void devecadastrarUmProfessor() {

        //ARRANGE
        this.dto = new DadosCadastroProfessor("lucas","lucas@gmail.com",
                "23231312", Especialidade.MUSCULACAO);

        //ACT
        var resultado = service.cadastrar(dto);


        //ASSERT
        assertNotNull(resultado);
        assertEquals("lucas", resultado.getNome());
        assertEquals("lucas@gmail.com", resultado.getEmail());
        assertEquals("23231312",resultado.getCref());
        assertEquals(Especialidade.MUSCULACAO,resultado.getEspecialidade());
    }

    @Test
    void deveriaAtualizarUmProfessor() {

        //Arrange
        var dadosProfessor = new DadosCadastroProfessor("lucas", "lucas@gmail.com",
                "2311312", Especialidade.MUSCULACAO);
        var professor = new Professor(dadosProfessor);
        professor.setId(1L);

        var dadosAtualizacao = new DadosAtualizacaoProfessor(1L, "joao",
                "joao@gmail.com",Especialidade.MUSCULACAO);

        when(repository.getReferenceById(1L)).thenReturn(professor);
        when(repository.save(any(Professor.class))).thenReturn(professor);

        //act
        var resultado = service.atualizar(dadosAtualizacao);


        //assert
        assertNotNull(resultado);
        assertEquals("joao", resultado.getNome());
        assertEquals("joao@gmail.com", resultado.getEmail());
        verify(repository, times(1)).save(any(Professor.class));
    }



    @Test
    void deveriaListarProfessores() {


        //arrange
        var dadosProfessor = new DadosCadastroProfessor("lucas", "lucas@gmail.com",
                "2311312", Especialidade.MUSCULACAO);

        var professor =  new Professor(dadosProfessor);
        var listaProfessor = List.of(professor);

        var page = new PageImpl<>(listaProfessor);
        when(repository.findAllByAtivoTrue(any(Pageable.class))).thenReturn(page);


        //act
        var resultado = service.listar(Pageable.unpaged());


        //assert
        assertNotNull(resultado, "Não e nulo");
        assertEquals(1,resultado.getTotalElements());
        Mockito.verify(repository, times(1)).findAllByAtivoTrue(any(Pageable.class));

    }

    @Test
    void deveriaExcluirProfessor(){
        //arrange
        var dadosCadastro = new DadosCadastroProfessor("ebert", "12133",
                "123@GM", Especialidade.MUSCULACAO);

        var professor = new Professor(dadosCadastro);
        professor.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(professor));

        // act
        service.excluir(1L);


        //assert
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Professor.class));
    }
}