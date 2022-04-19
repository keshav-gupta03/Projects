package com.example.springreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springreact.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long > {
              
}
