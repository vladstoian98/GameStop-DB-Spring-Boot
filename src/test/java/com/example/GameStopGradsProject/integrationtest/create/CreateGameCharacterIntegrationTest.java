package com.example.GameStopGradsProject.integrationtest.create;

import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.example.GameStopGradsProject.service.GameCharacterService;
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
public class CreateGameCharacterIntegrationTest {

    @MockBean
    private GameCharacterRepository gameCharacterRepository;

    @Autowired
    private GameCharacterService gameCharacterService;

    @Autowired
    private DataSource dataSource;

    @AfterAll
    public void teardown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

    @Test
    @DisplayName("""
            If we call the method, then the game character will be added in the
            data base.
            """)
    void test1() {
        GameCharacter gameCharacter = new GameCharacter("anything");
        gameCharacterService.create(gameCharacter);

        verify(gameCharacterRepository, times(1)).save(any());
    }
}
