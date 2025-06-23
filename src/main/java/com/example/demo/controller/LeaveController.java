package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Leave;
import com.example.demo.repository.LeaveRepository;

@RestController
@CrossOrigin("http://localhost:5173")
public class LeaveController {

	@Autowired
	private LeaveRepository leaveRepository;

	// Add new leave request
	@PostMapping("/addleave")
	public Leave addLeave(@RequestBody Leave leave) {
		return leaveRepository.save(leave);
	}

	// ✅ Get leaves by employee email
	@GetMapping("/leaves/employee/{email}")
	public List<Leave> getLeavesByEmail(@PathVariable String email) {
		return leaveRepository.findByEmailId(email);
	}

	// ✅ Get all leave requests
	@GetMapping("/leaves")
	public List<Leave> getAllLeaves() {
		return leaveRepository.findAll();
	}

	// ✅ Update leave status (Approve/Reject)
	@PutMapping("/leaves/{id}")
	public Leave updateLeaveStatus(@PathVariable Long id, @RequestBody Leave updatedLeave) {
		Leave existing = leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));
		existing.setStatus(updatedLeave.getStatus());
		return leaveRepository.save(existing);
	}

}
