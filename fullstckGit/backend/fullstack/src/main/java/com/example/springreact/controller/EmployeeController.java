package com.example.springreact.controller;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springreact.exception.ResourceNotFoundException;
import com.example.springreact.model.Employee;
import com.example.springreact.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all the employee
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	// Create Employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return employeeRepository.save(emp);
	}

	// Get Employee by Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id : " + id));
		return ResponseEntity.ok(emp);
	}

	// Update Employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee empDetails) {

		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id : " + id));
		emp.setFirstName(empDetails.getFirstName());
		emp.setLastName(empDetails.getLastName());
		emp.setEmailId(empDetails.getEmailId());
		Employee updatedEmp = employeeRepository.save(emp);
		return ResponseEntity.ok(updatedEmp);
	}
	
	
	//Delete Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmp(@PathVariable Long id){
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id : " + id));
		employeeRepository.delete(emp);
		Map<String,Boolean> response=new HashMap<String,Boolean>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
