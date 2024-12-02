package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jop-post")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (jobPostService.updateJobPost(id, jobPost)) return ResponseEntity.status(200).body(new ApiResponse("Job post updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Job post not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        if (jobPostService.deleteJobPost(id)) return ResponseEntity.status(200).body(new ApiResponse("Job post deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Job post not found"));
    }


}
