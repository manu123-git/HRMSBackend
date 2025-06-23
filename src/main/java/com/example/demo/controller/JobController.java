package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Job;
import com.example.demo.repository.JobRepository;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {

	@Autowired
	private JobRepository jobRepository;

	@PostMapping("/create")
	public ResponseEntity<?> createJob(@RequestBody Job job) {
		jobRepository.save(job);
		return new ResponseEntity<>("Job Posted Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Job>> getAllJobs() {
		return ResponseEntity.ok(jobRepository.findAll());
	}

	@GetMapping("/department/{dept}")
	public ResponseEntity<List<Job>> getJobsByDepartment(@PathVariable String dept) {
		return ResponseEntity.ok(jobRepository.findByDepartment(dept));
	}
	
	@GetMapping("/posted-by/{managerId}")
	public ResponseEntity<List<Job>> getJobsByManager(@PathVariable Long managerId) {
	    return ResponseEntity.ok(jobRepository.findByPostedBy(managerId));
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteJob(@PathVariable Long id) {
		if (!jobRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
		}
		jobRepository.deleteById(id);
		return ResponseEntity.ok("Job deleted successfully");
	}
}
