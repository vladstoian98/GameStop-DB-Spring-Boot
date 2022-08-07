package com.example.GameStopGradsProject.apiintegrationtest.select;

import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SelectGameConsoleApiIntegrationTest {

    @MockBean
    private GameConsoleRepository gameConsoleRepository;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("""
            If the following endpoint GET /gameconsoles/{id} is called and the 
            id exists in the game_console table, then the HTTP
            response should be 201 CREATED.
            """)
    @WithMockUser(username = "anything", password = "anything", authorities = "read")
    void Test1() throws Exception {
        long id = 1;

        when(gameConsoleRepository.findGameConsoleById(id)).thenReturn(Optional.of(new GameConsole()));

        mockMvc.perform(get("/gameconsoles/" + id))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("""
            If the following endpoint GET /gameconsoles/{id} is called and the
            id does not exist in the game_console table, then the HTTP
            response should be 404 NOT FOUND.
            """)
    @WithMockUser(username = "anything", password = "anything", authorities = "read")
    void Test2() throws Exception {
        long id = 1;

        when(gameConsoleRepository.findGameConsoleById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/gameconsoles/" + id))
                .andExpect(status().isNotFound());
    }

}