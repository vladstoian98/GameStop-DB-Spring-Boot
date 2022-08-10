package com.example.GameStopGradsProject.integrationtest.select;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import com.example.GameStopGradsProject.service.GameStopShopService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SelectGameStopShopIntegrationTest {

    @MockBean
    private GameStopShopRepository gameStopShopRepository;

    @Autowired
    private GameStopShopService gameStopShopService;


    @Test
    @DisplayName("""
            If we call the method and the id exists in the data base,
            then the game stop shop with that id will be printed.
            """)
    void test1() {
        long id = 1;

        when(gameStopShopRepository.findGameStopShopById(id)).thenReturn(Optional.of(new GameStopShop()));

        gameStopShopService.findGameStopShopById(id);

        verify(gameStopShopRepository, times(1)).findGameStopShopById(id);
    }

    @Test
    @DisplayName("""
            If we call the method and the id does not exist in the data base,
            then the method throws IdDoesNotExistException.
            """)
    void test2() {
        long id = 1;
        when(gameStopShopRepository.findGameStopShopById(id)).thenReturn(Optional.empty());

        assertThrows(IdDoesNotExist.class, () -> gameStopShopService.findGameStopShopById(id));
    }
}
