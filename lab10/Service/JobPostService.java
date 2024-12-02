package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer id, JobPost jobPost) {
        JobPost oldJobPost = jobPostRepository.getById(id);

        if (oldJobPost == null) return false;

        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setSalary(jobPost.getSalary());
        jobPostRepository.save(oldJobPost);
        return true;
    }

    public Boolean deleteJobPost(Integer id) {
        if (jobPostRepository.existsById(id)) {
            jobPostRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
