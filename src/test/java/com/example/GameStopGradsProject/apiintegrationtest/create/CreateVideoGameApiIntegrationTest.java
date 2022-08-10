package com.example.GameStopGradsProject.apiintegrationtest.create;

import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateVideoGameApiIntegrationTest {

    @MockBean
    GameCharacterRepository gameCharacterRepository;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @Autowired
    private DataSource dataSource;

    @AfterAll
    public void teardown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

    @BeforeAll
    public static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("""
            If the following endpoint POST /videogames is called
            then the HTTP response should be 201 CREATED.
            """)
    @WithMockUser(username = "anything", authorities = "write")
    void Test1() throws Exception {
        VideoGame videoGame = new VideoGame("anything");

        List<GameCharacter> gameCharacters = new ArrayList<>();
        GameCharacter gameCharacter1 = new GameCharacter("anything1");
        GameCharacter gameCharacter2 = new GameCharacter("anything2");
        gameCharacters.add(gameCharacter1);
        gameCharacters.add(gameCharacter2);

        mockMvc.perform(post("/videogames")
                        .content(objectMapper.writeValueAsString(videoGame))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        gameCharacters.forEach(gc -> {
                    try {
                        mockMvc.perform(post("/gamecharacters")
                                        .content(objectMapper.writeValueAsString(gc))
                                        .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
