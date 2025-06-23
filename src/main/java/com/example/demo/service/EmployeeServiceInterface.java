package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeServiceInterface {

	Employee register(Employee employee);

	ResponseEntity<?> login(Employee employee); // updated return type

	Optional<Employee> findByEmailId(String emailId);

	List<Employee> getAllEmployees();

	Optional<Employee> getEmployeeById(Long id);
	
	Employee getEmployeeByEmail(String email);

	Employee updateEmployee(Long id, Employee updatedEmployee);

	String deleteEmployee(Long id);

	Employee update(Long id, Employee updatedSalary);

	List<Employee> findByDepartment(String department);

	public List<Employee> findByManagerId(Long managerId);
	
	List<Employee> getEmployeesByManagerId(Long managerId);
}
