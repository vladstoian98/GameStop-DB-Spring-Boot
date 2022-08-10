package com.example.GameStopGradsProject.apiintegrationtest.delete;

import com.example.GameStopGradsProject.model.User;
import com.example.GameStopGradsProject.repository.UserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteUserApiIntegrationTest {

    @MockBean
    private UserRepository userRepository;

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
            If the following endpoint DELETE /users/deletion/{id} is called and
            the id exists in the user table, then the HTTP response should be
            200 OK.
            """)
    @WithMockUser(username = "anything", password = "anything")
    void Test1() throws Exception {
        long id = 1;

        when(userRepository.findUserById(id)).thenReturn(Optional.of(new User()));

        mockMvc.perform(delete("/users/deletion/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("""
            If the following endpoint DELETE /users/deletion/{id} is called and
            the id does not exist in the user table, then the HTTP response should be
            404 NOT FOUND.
            """)
    @WithMockUser(username = "anything", password = "anything")
    void Test2() throws Exception {
        long id = 1;

        when(userRepository.findUserById(id)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/users/deletion/" + id))
                .andExpect(status().isNotFound());
    }
}
