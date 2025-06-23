package com.example.demo.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService implements AdminServiceInterface {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin register(Admin admin) {
		return adminRepository.save(admin);
	}

	// login method
	public ResponseEntity<?> login(Admin admin) {
		Optional<Admin> optionalUser = adminRepository.findByEmailId(admin.getEmailId().trim());

		if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(admin.getPassword().trim())) {
			return ResponseEntity.ok(Map.of("message", "Login Successful", "user", optionalUser.get()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@Override
	public Optional<Admin> findByEmailId(String emailId) {
		return adminRepository.findByEmailId(emailId);
	}

}