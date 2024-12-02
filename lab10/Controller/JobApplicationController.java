package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jop-application")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllJobApplications() {
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/apply")
    public ResponseEntity applyForJob(@RequestBody @Valid JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        return switch (jobApplicationService.applyForJob(jobApplication)){
          case 0 -> ResponseEntity.status(200).body(new ApiResponse("Application done successfully"));
          case 1 -> ResponseEntity.status(400).body(new ApiResponse("Application already exists"));
          case 2 -> ResponseEntity.status(400).body(new ApiResponse("Job post not found"));
          case 3 -> ResponseEntity.status(400).body(new ApiResponse("User not found"));
          default -> ResponseEntity.status(400).body(new ApiResponse("Invalid request"));
        };
    }

    @DeleteMapping("/withdraw/{userId}/{applicationId}")
    public ResponseEntity withdrawJobApplication(@PathVariable Integer userId, @PathVariable Integer applicationId) {
        return switch (jobApplicationService.withdrawJobApplication(userId, applicationId)){
          case 0 -> ResponseEntity.status(200).body(new ApiResponse("Withdraw done successfully"));
          case 1 -> ResponseEntity.status(400).body(new ApiResponse("Application not found"));
          case 2 -> ResponseEntity.status(400).body(new ApiResponse("User not found"));
          case 3 -> ResponseEntity.status(400).body(new ApiResponse("No application for this user"));
          default -> ResponseEntity.status(400).body(new ApiResponse("Invalid request"));
        };
    }
}
