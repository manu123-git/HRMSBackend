package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Manager;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ManagerRepository;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ManagerRepository managerRepository;

	public void autoAssignManagers() {
		List<Manager> managers = managerRepository.findAll();
		List<Employee> employees = employeeRepository.findAll();

		for (Employee emp : employees) {
			for (Manager mgr : managers) {
				if (emp.getDepartment().equalsIgnoreCase(mgr.getDepartment())) {
					emp.setManager(mgr);
					break; // stop after first match
				}
			}
		}

		employeeRepository.saveAll(employees);
	}

	@Override
	public Employee register(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public ResponseEntity<?> login(Employee employee) {
		Optional<Employee> existing = employeeRepository.findByEmailId(employee.getEmailId().trim());

		if (existing.isPresent() && existing.get().getPassword().equals(employee.getPassword().trim())) {
			return ResponseEntity.ok(Map.of("message", "Login Successful", "user", existing.get()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@Override
	public Optional<Employee> findByEmailId(String emailId) {
		return employeeRepository.findByEmailId(emailId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return employeeRepository.findByEmailId(email)
				.orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
	}

	@Override
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		return employeeRepository.findById(id).map(emp -> {
			emp.setName(updatedEmployee.getName());
			emp.setEmailId(updatedEmployee.getEmailId());
			emp.setPassword(updatedEmployee.getPassword());
			emp.setDepartment(updatedEmployee.getDepartment());
			emp.setSpecificrole(updatedEmployee.getSpecificrole());
			emp.setStatus(updatedEmployee.getStatus());
			return employeeRepository.save(emp);
		}).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
	}

	@Override
	public String deleteEmployee(Long id) {
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return "Employee deleted with id: " + id;
		} else {
			return "Employee not found";
		}
	}

	public Employee updateSalary(Long id, Employee updatedSalary) {
		return employeeRepository.findById(id).map(sal -> {
			sal.setName(updatedSalary.getName());
			sal.setDepartment(updatedSalary.getDepartment());
			sal.setSpecificrole(updatedSalary.getSpecificrole());
			sal.setBasesalary(updatedSalary.getBasesalary());
			sal.setBonus(updatedSalary.getBonus());
			return employeeRepository.save(sal);
		}).orElseThrow(() -> new RuntimeException("Employee Not found: " + id));
	}

	public void deleteSalary(Long id) {
		if (employeeRepository.existsById(id))
			employeeRepository.deleteById(id);
		else
			throw new RuntimeException("Not found: " + id);
	}

	@Override
	public Employee update(Long id, Employee updatedSalary) {
		return null; // Not used, can be removed or implemented later
	}

	@Override
	public List<Employee> getEmployeesByManagerId(Long managerId) {
		Manager manager = managerRepository.findById(managerId).orElse(null);
		if (manager != null) {
			String department = manager.getDepartment().trim().toLowerCase();
			return employeeRepository.findByDepartmentIgnoreCase(department);
		}
		return List.of();
	}

	@Override
	public List<Employee> findByDepartment(String department) {
		return employeeRepository.findByDepartment(department);
	}

	@Override
	public List<Employee> findByManagerId(Long managerId) {
		return employeeRepository.findByManager_Id(managerId);
	}

}