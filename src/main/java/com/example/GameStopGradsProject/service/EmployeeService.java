package com.example.GameStopGradsProject.service;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.repository.GameStopShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

//        ArrayList<Employee> employees = new ArrayList<>();
//        employees.add(employee);
//        employee.getGameStopShop().setEmployees(employees);

        //gameStopShopRepository.save(employee.getGameStopShop());
        return employeeRepository.save(employee);
    }

    @Transactional
    public Optional<Employee> findEmployeeById(Long id) {

        var foundEmployee = employeeRepository.findEmployeeById(id);

        if(foundEmployee.isEmpty())
            throw new IdDoesNotExist("Employee", id);
        else
            return foundEmployee;
    }

    @Transactional
    public void assignGameStopShop(long employeeId, long gameStopShopId) {

        var employee = employeeRepository.findEmployeeById(employeeId);
        var gameStopShop = gameStopShopRepository.findGameStopShopById(gameStopShopId);

        if(employee.isEmpty())
            throw new IdDoesNotExist("Employee", employeeId);
        else if(gameStopShop.isEmpty())
            throw new IdDoesNotExist("GameStopShop", gameStopShopId);
        else {
            Employee e = employee.get();
            GameStopShop g = gameStopShop.get();

            e.setGameStopShop(g);
        }
    }

















}
