package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public Integer applyForJob(JobApplication jobApplication) {
            if (!userRepository.existsById(jobApplication.getUserId())) return 3;
            if (!jobPostRepository.existsById(jobApplication.getJobPostId())) return 2;

            for (JobApplication existingApplication : jobApplicationRepository.findAll()) {
                if (existingApplication.getUserId().equals(jobApplication.getUserId()) &&
                        existingApplication.getJobPostId().equals(jobApplication.getJobPostId())) return 1;
            }


            jobApplicationRepository.save(jobApplication);
            return 0;
    }

    public Integer withdrawJobApplication(Integer userId, Integer applicationId) {
            if (!userRepository.existsById(userId)) return 2;
            if (!jobApplicationRepository.existsById(applicationId)) return 1;
            for (JobApplication existingApplication : jobApplicationRepository.findAll()) {
                if (existingApplication.getUserId().equals(userId) && existingApplication.getJobPostId().equals(applicationId)) {
                    jobApplicationRepository.delete(existingApplication);
                    return 0;
                }
            }
            return 3;
    }
}
