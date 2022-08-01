package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private GameStopShopRepository gameStopShopRepository;

    public EmployeeService(EmployeeRepository employeeRepository, GameStopShopRepository gameStopShopRepository) {
        this.employeeRepository = employeeRepository;
        this.gameStopShopRepository = gameStopShopRepository;
    }

    @Transactional
    public Employee create(Employee employee) {

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employee.getGameStopShop().setEmployees(employees);

        gameStopShopRepository.save(employee.getGameStopShop());
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee findEmployeeById(Long id) {

        return employeeRepository.findEmployeeById(id);
    }
}
