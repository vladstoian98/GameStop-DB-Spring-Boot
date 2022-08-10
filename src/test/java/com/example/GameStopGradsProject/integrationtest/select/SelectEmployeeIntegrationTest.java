package com.example.GameStopGradsProject.integrationtest.select;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.service.EmployeeService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelectEmployeeIntegrationTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DataSource dataSource;

    @AfterAll
    public void tearDown() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }

    @Test
    @DisplayName("""
            If we call the method and the id exists in the data base,
            then the employee with that id will be printed.
            """)
    void test1() {
        long id = 1;

        when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.of(new Employee()));

        employeeService.findEmployeeById(id);

        verify(employeeRepository, times(1)).findEmployeeById(id);
    }

    @Test
    @DisplayName("""
            If we call the method and the id does not exist in the data base,
            then the method throws IdDoesNotExistException.
            """)
    void test2() {
        long id = 1;
        when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.empty());

        assertThrows(IdDoesNotExist.class, () -> employeeService.findEmployeeById(id));
    }


}
