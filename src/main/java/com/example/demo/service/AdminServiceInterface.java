package com.example.demo.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Admin;

public interface AdminServiceInterface {
	public Admin register(Admin admin);

	public ResponseEntity<?> login(Admin admin);

	public Optional<Admin> findByEmailId(String emailId);

}
