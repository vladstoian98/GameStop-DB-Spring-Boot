package com.example.GameStopGradsProject.controller;

import com.example.GameStopGradsProject.model.Employee;
import com.example.GameStopGradsProject.repository.EmployeeRepository;
import com.example.GameStopGradsProject.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<Employee> findEmployeeById(
            @PathVariable Long id) {

        Employee selectedEmployee = employeeService.findEmployeeById(id);

        return ResponseEntity
                .created(URI.create("/employees" + "/id"))
                .body(selectedEmployee);
    }
}
