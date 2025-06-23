package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ContactMessage;
import com.example.demo.repository.ContactMessageRepository;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

	@Autowired
	private ContactMessageRepository repository;

	@PostMapping
	public ResponseEntity<String> submitMessage(@RequestBody ContactMessage message) {
		repository.save(message);
		return ResponseEntity.ok("Message submitted successfully");
	}

	@GetMapping
	public List<ContactMessage> getAllMessages() {
		return repository.findAll(); 
	}
}
