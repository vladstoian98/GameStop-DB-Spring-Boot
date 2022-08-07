package com.example.GameStopGradsProject.apiintegrationtest.create;

import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateGameStopShopApiIntegrationTest {

    @MockBean
    private GameStopShopRepository gameStopShopRepository;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("""
            If the following endpoint POST /gamestopshops is called
            then the HTTP response should be 200 OK.
            """)
    @WithMockUser(username = "anything", authorities = "write")
    void Test1() throws Exception {

        GameStopShop gameStopShop = new GameStopShop("anything");

        mockMvc.perform(post("/gamestopshops")
                        .content(objectMapper.writeValueAsString(gameStopShop))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
