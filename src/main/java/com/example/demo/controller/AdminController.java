package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

@RestController
@CrossOrigin("http://localhost:5173")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/admin")
	public Admin register(@RequestBody Admin admin) {
		return adminService.register(admin);
	}

	@PostMapping("/adminlogin")
	public ResponseEntity<?> login(@RequestBody Admin admin) {
		return adminService.login(admin);
	}
}
