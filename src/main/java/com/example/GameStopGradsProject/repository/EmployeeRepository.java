package com.example.GameStopGradsProject.repository;

import com.example.GameStopGradsProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeById(Long id);

    Optional<Employee> deleteEmployeeById(Long id);

}
