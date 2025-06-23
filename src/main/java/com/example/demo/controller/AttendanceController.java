package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Attendance;
import com.example.demo.repository.AttendanceRepository;

@CrossOrigin(origins = "http://localhost:3000") // adjust if needed
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceRepository repository;

	@PostMapping
	public Attendance addAttendance(@RequestBody Attendance attendance) {
		return repository.save(attendance);
	}

	@GetMapping
	public List<Attendance> getAllAttendance() {
		return repository.findAll();
	}
}
