package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;

@Service
public class ManagerService implements ManagerServiceInterface {

	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public Manager register(Manager manager) {
		return managerRepository.save(manager);
	}

	@Override
	public ResponseEntity<?> login(Manager manager) {
		Optional<Manager> optionalUser = managerRepository.findByEmailId(manager.getEmailId().trim());

		if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(manager.getPassword().trim())) {
			return ResponseEntity.ok(Map.of("message", "Login Successful", "user", optionalUser.get()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@Override
	public Optional<Manager> findByEmailId(String emailId) {
		return managerRepository.findByEmailId(emailId);
	}

	@Override
	public Optional<Manager> getManagerById(Long id) {
		return managerRepository.findById(id);
	}

	@Override
	public List<Manager> getAllEmployees() {
		return managerRepository.findAll();
	}

	@Override
	public Manager updateManager(Long id, Manager updatedManager) {
		return managerRepository.findById(id).map(manager -> {
			manager.setFname(updatedManager.getFname());
			manager.setLname(updatedManager.getLname());
			manager.setEmailId(updatedManager.getEmailId());
			manager.setPassword(updatedManager.getPassword());
			manager.setGender(updatedManager.getGender());
			manager.setDepartment(updatedManager.getDepartment());
			manager.setSalary(updatedManager.getSalary());
			manager.setExperience(updatedManager.getExperience());
			return managerRepository.save(manager);
		}).orElseThrow(() -> new RuntimeException("Manager not found with ID: " + id));
	}

	@Override
	public String deleteManager(Long id) {
		if (managerRepository.existsById(id)) {
			managerRepository.deleteById(id);
			return "Manager deleted with ID: " + id;
		} else {
			return "Manager not found";
		}
	}
}
