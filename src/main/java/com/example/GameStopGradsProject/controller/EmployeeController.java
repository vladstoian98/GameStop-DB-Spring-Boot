package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.exception.IdDoesNotExist;
import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.model.GameStopShop;
import com.example.GameStopGradsProject.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> create(
            @RequestBody Employee employee) {

        Employee createdEmployee = employeeService.create(employee);

        return ResponseEntity
                .created(URI.create("/employees" + ""))
                .body(createdEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> findEmployeeById(
            @PathVariable Long id) {

        try {
            Optional<Employee> selectedEmployee = employeeService.findEmployeeById(id);

            return ResponseEntity
                    .created(URI.create("/employees" + "/id"))
                    .body(selectedEmployee);
        } catch (IdDoesNotExist x) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("/shopassignation/{employeeId}/{gameStopShopId}")
    public ResponseEntity<?> assignGameStopShop(
            @PathVariable long employeeId, @PathVariable long gameStopShopId) {

        employeeService.assignGameStopShop(employeeId, gameStopShopId);

        return ResponseEntity
                .ok()
                .build();
    }

}
