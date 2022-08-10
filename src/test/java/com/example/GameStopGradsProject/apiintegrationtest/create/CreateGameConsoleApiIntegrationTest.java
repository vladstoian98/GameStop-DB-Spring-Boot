package com.example.GameStopGradsProject.apiintegrationtest.create;

import com.example.GameStopGradsProject.model.GameConsole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateGameConsoleApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @Autowired
    private DataSource dataSource;

    @AfterAll
    public void teardown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

    @Test
    @DisplayName("""
            If the following endpoint POST /gameconsoles is called
            then the HTTP response should be 201 CREATED.
            """)
    @WithMockUser(username = "anything", authorities = "write")
    void Test1() throws Exception {
        GameConsole gameConsole = new GameConsole("anything");

        mockMvc.perform(post("/gameconsoles")
                        .content(objectMapper.writeValueAsString(gameConsole))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
