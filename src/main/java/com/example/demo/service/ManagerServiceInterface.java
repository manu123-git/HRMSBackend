package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Manager;

public interface ManagerServiceInterface {

	public Manager register(Manager manager);

	public ResponseEntity<?> login(Manager manager); // ✅ Changed return type

	public Optional<Manager> findByEmailId(String emailId);

	public Optional<Manager> getManagerById(Long id);

	public List<Manager> getAllEmployees();

	public Manager updateManager(Long id, Manager updatedManager);

	public String deleteManager(Long id);
}
