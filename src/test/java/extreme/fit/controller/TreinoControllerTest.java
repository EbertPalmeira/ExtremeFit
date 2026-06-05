package extreme.fit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TreinoControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void deveRetornarUmaTreinoComSucesso() throws Exception {
        String json = """
        {
            "id": 1,
            "nome": "Treino A",
            "descricao": "Treino de força",
            "duracaoMinutos": "60",
            "nivel": "INICIANTE",
            "professorId": 1,
            "alunoId": 1
        }
        """;

        var response = mvc.perform(
                post("/treino")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
    }

