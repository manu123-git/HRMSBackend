package com.example.demo.controller;

import java.io.File;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.JobApplication;
import com.example.demo.repository.JobApplicationRepository;

@RestController
@RequestMapping("/job-apply")
@CrossOrigin(origins = "http://localhost:5173")
public class JobApplicationController {

	@Autowired
	private JobApplicationRepository repo;

	@PostMapping
	public ResponseEntity<String> applyForJob(@RequestParam String fullName, @RequestParam String emailId,
			@RequestParam String mobileNo, @RequestParam String experience, @RequestParam MultipartFile resume,
			@RequestParam Long jobId) {
		try {
			// ✅ Set absolute directory path (customize as per your system)
			String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "resumes";

			File dir = new File(uploadDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// ✅ Save file to that absolute path
			String filePath = uploadDir + File.separator + resume.getOriginalFilename();
			resume.transferTo(new File(filePath));

			JobApplication app = new JobApplication();
			app.setFullName(fullName);
			app.setEmailId(emailId);
			app.setMobileNo(mobileNo);
			app.setExperience(experience);
			app.setResumeFileName(resume.getOriginalFilename());
			app.setAppliedDate(LocalDate.now());
			app.setJobId(jobId);

			repo.save(app);

			return ResponseEntity.ok("Application submitted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Resume upload failed: " + e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<?> getAllApplications() {
		return ResponseEntity.ok(repo.findAll());
	}

	@GetMapping("/by-job")
	public ResponseEntity<?> getByJobId(@RequestParam Long jobId) {
		return ResponseEntity.ok(repo.findByJobId(jobId));
	}

	@PutMapping("/update-status")
	public ResponseEntity<?> updateStatus(@RequestParam Long appId, @RequestParam String status) {
		return repo.findById(appId).map(app -> {
			app.setStatus(status);
			repo.save(app);
			return ResponseEntity.ok("Status updated to: " + status);
		}).orElse(ResponseEntity.status(404).body("Application not found"));
	}

}
