package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin("http://localhost:5173")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/emp")
	public Employee register(@RequestBody Employee employee) {
		return employeeService.register(employee);
	}

	@PostMapping("/employeelogin")
	public ResponseEntity<?> login(@RequestBody Employee employee) {
		return employeeService.login(employee);
	}

	@GetMapping("/getemployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/getemployee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/getemploye/{email}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
		return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
	}

	@PutMapping("/updateemp/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
		return employeeService.updateEmployee(id, updatedEmployee);
	}

	@DeleteMapping("/deleteemp/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		return employeeService.deleteEmployee(id);
	}

	@PutMapping("/updatesalary/{id}")
	public Employee updateSalary(@PathVariable Long id, @RequestBody Employee updatedEmpSalary) {
		return employeeService.updateSalary(id, updatedEmpSalary);
	}

	@DeleteMapping("/deletesalary/{id}")
	public String deleteSalary(@PathVariable Long id) {
		employeeService.deleteSalary(id);
		return "Deleted salary record " + id;
	}

	@GetMapping("/employees/manager-department/{managerId}")
	public List<Employee> getEmployeesByManagerId(@PathVariable Long managerId) {
		return employeeService.getEmployeesByManagerId(managerId);
	}

	@GetMapping("/assign-managers")
	public ResponseEntity<String> assignManagers() {
		employeeService.autoAssignManagers();
		return ResponseEntity.ok("Employees assigned to managers based on department.");
	}

}
