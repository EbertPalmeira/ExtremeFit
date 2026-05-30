package extreme.fit.controller;

import extreme.fit.domain.aluno.Aluno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AlunoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void deveriaDevolverCodigo200ParaCadastroDeAluno() throws Exception {
        String json = """
                {
                        "nome": "ebert",
                        "matricula": "212121212",
                        "email": "ebert@123.com",
                        "telefone": "64981476764",
                        "dataNascimento": "28/04/2001"
                }
                """;

        var response = mvc.perform(
                post("/aluno")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

      assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}