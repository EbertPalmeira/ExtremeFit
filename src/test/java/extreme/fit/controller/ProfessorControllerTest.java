package extreme.fit.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProfessorControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    @WithMockUser
    void deveriaDevolverCodigo200ParaCadastroDeProfessor() throws Exception {
        String json = """
                {
                        
                }
                """;

        var response = mvc.perform(
                post("/professor")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo200AoListarProfessores() throws Exception {
        var response = mvc.perform(
                get("/professor")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo200AoAtualizarProfessor() throws Exception {
        String json = """
                {
                        "id":1,
                        "nome": "lucas",
                        "email": "lucas@123.com",
                        "Especialidade": "MUSCULACAO"
                }
                """;


        var response = mvc.perform(
                put("/professor")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }
    @Test
    @WithMockUser
    void deveriaDevolverCodigo204AoExcluirProfessor() throws Exception {
        String json = """
                {
                        "id":"1",
                        "nome": "ebert",
                        "email": "ebert@123.com",
                        "cref": "476764",
                        "Especialidade": "HIPERTROFIA"
                }
                """;


        var response = mvc.perform(
                delete("/professor/{id}",1)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());


    }
}