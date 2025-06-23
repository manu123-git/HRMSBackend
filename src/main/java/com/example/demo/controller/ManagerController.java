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

import com.example.demo.entity.Manager;
import com.example.demo.service.ManagerService;

@RestController
@CrossOrigin("http://localhost:5173")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@PostMapping("/manager")
	public Manager register(@RequestBody Manager manager) {
		return managerService.register(manager);
	}

	@PostMapping("/managerlogin")
	public ResponseEntity<?> login(@RequestBody Manager manager) {
		return managerService.login(manager);
	}

	@GetMapping("/departments")
	public List<Manager> getAllEmployees() {
		return managerService.getAllEmployees();
	}

	@GetMapping("/getmanager/{id}")
	public Optional<Manager> getManagerById(@PathVariable Long id) {
		return managerService.getManagerById(id);
	}

	@PutMapping("/updatemanager/{id}")
	public Manager updateManager(@PathVariable Long id, @RequestBody Manager updatedManager) {
		return managerService.updateManager(id, updatedManager);
	}

	@DeleteMapping("/deletemanager/{id}")
	public String deleteManager(@PathVariable Long id) {
		return managerService.deleteManager(id);
	}

}
