package com.example.GameStopGradsProject.apiintegrationtest.select;

import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelectVideoGameApiIntegrationTest {

    @MockBean
    private VideoGameRepository videoGameRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @AfterAll
    public void teardown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

    @Test
    @DisplayName("""
            If the following endpoint GET /videogames/{id} is called and the
            id exists in the video_game table, then the HTTP
            response should be 201 CREATED.
            """)
    @WithMockUser(username = "anything", password = "anything", authorities = "read")
    void Test1() throws Exception {
        long id = 1;

        when(videoGameRepository.findVideoGameById(id)).thenReturn(Optional.of(new VideoGame()));

        mockMvc.perform(get("/videogames/" + id))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("""
            If the following endpoint GET /videogames/{id} is called and the
            id does not exist in the video_game table, then the HTTP
            response should be 404 NOT FOUND.
            """)
    @WithMockUser(username = "anything", password = "anything", authorities = "read")
    void Test2() throws Exception {
        long id = 1;

        when(videoGameRepository.findVideoGameById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/videogames/" + id))
                .andExpect(status().isNotFound());
    }

}
