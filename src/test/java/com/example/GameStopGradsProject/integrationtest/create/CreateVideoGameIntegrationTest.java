package com.example.GameStopGradsProject.integrationtest.create;

import com.example.GameStopGradsProject.model.GameCharacter;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.GameCharacterRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import com.example.GameStopGradsProject.service.VideoGameService;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateVideoGameIntegrationTest {

    @MockBean
    private VideoGameRepository videoGameRepository;

    @MockBean
    private GameCharacterRepository gameCharacterRepository;

    @Autowired
    private VideoGameService videoGameService;

    @Autowired
    private DataSource dataSource;

    @AfterAll
    public void teardown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

    @Test
    @DisplayName("""
            If we call the method, then the video game will be added in the
            data base.
            """)
    void test1() {
        VideoGame videoGame = new VideoGame("anything");

        List<GameCharacter> gameCharacters = new ArrayList<>();
        GameCharacter gameCharacter1 = new GameCharacter("anything1");
        GameCharacter gameCharacter2 = new GameCharacter("anything2");
        gameCharacters.add(gameCharacter1);
        gameCharacters.add(gameCharacter2);

        videoGame.setGameCharacters(gameCharacters);

        videoGameService.create(videoGame);

        verify(videoGameRepository, times(1)).save(any());
        verify(gameCharacterRepository, times(1)).saveAll(any());
    }
}
