package com.example.GameStopGradsProject.apiintegrationtest.assign;

import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AssignGameStopShopToEmployeeApiIntegrationTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private GameStopShopRepository gameStopShopRepository;

    @Autowired
    private MockMvc mockMvc;

//    private  static ObjectMapper objectMapper;
//
//    @BeforeAll
//    public static void beforeAll() {
//        objectMapper = new ObjectMapper();
//    }

//    @Test
//    @DisplayName("""
//            If the following endpoint POST /employees/shopassignation/{employeeId}/{gameStopShopId}
//            is called and both ids exist in their respective tables, then the HTTPS
//            response should be 201 CREATED.
//            """)
//    @WithMockUser(username = "anything", password = "anything", authorities = "read")
//    void Test1() throws Exception {
//        long employeeId = 1;
//        long gameStopShopId = 1;
//
//        when(employeeRepository.findEmployeeById(employeeId)).thenReturn(Optional.of(new Employee()));
//        when(gameStopShopRepository.findGameStopShopById(gameStopShopId)).thenReturn(Optional.of(new GameStopShop()));
//
//        mockMvc.perform(post("/employees/shopassignation" + employeeId + gameStopShopId))
//                .andExpect(status().isOk());
//    }
}
