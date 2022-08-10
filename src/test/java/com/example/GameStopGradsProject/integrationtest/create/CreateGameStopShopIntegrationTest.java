package com.example.GameStopGradsProject.integrationtest.create;

import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.model.GameConsole;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.model.VideoGame;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.repository.GameConsoleRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import com.example.GameStopGradsProject.repository.VideoGameRepository;
import com.example.GameStopGradsProject.service.GameStopShopService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CreateGameStopShopIntegrationTest {

    @MockBean
    private GameStopShopRepository gameStopShopRepository;

    @MockBean
    private VideoGameRepository videoGameRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private GameConsoleRepository gameConsoleRepository;

    @Autowired
    private GameStopShopService gameStopShopService;

    @Test
    @DisplayName("""
            If we call the method, then the game stop shop will be added in the
            data base.
            """)
    void test1() {
        GameStopShop gameStopShop = new GameStopShop("anything");

        List<VideoGame> videoGames = new ArrayList<>();
        VideoGame videoGame = new VideoGame();
        videoGames.add(videoGame);

        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employees.add(employee);

        List<GameConsole> gameConsoles = new ArrayList<>();
        GameConsole gameConsole = new GameConsole();
        gameConsoles.add(gameConsole);

        gameStopShop.setVideoGames(videoGames);
        gameStopShop.setEmployees(employees);
        gameStopShop.setGameConsoles(gameConsoles);

        gameStopShopService.create(gameStopShop);

        verify(gameStopShopRepository, times(1)).save(any());
        verify(videoGameRepository, times(1)).saveAll(any());
        verify(employeeRepository, times(1)).saveAll(any());
        verify(gameConsoleRepository, times(1)).saveAll(any());
    }
}
