package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
	List<JobApplication> findByJobId(Long jobId);
}