package com.example.GameStopGradsProject.integrationtest.create;

import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import com.example.GameStopGradsProject.service.GameConsoleService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateGameConsoleIntegrationTest {

    @MockBean
    private GameConsoleRepository gameConsoleRepository;

    @Autowired
    private GameConsoleService gameConsoleService;

    @Autowired
    private DataSource dataSource;

    @AfterAll
    public void teardown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

    @Test
    @DisplayName("""
            If we call the method, then the game console will be added in the
            data base.
            """)
    void test1() {
        GameConsole gameConsole = new GameConsole("anything");
        gameConsoleService.create(gameConsole);

        verify(gameConsoleRepository, times(1)).save(any());
    }
}
