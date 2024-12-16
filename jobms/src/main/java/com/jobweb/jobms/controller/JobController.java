package com.jobweb.jobms.controller;

import com.jobweb.jobms.dto.JobDataDTO;
import com.jobweb.jobms.entity.Job;
import com.jobweb.jobms.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobDataDTO>> getAll() {
        List<JobDataDTO> jobDataDTO = jobService.findAllJob();
        if (jobDataDTO != null) {
            return new ResponseEntity<>(jobDataDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(jobDataDTO, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        boolean response = jobService.createJob(job);
        if (response)
            return new ResponseEntity<>("Job successfully created", HttpStatus.OK);
        else
            return new ResponseEntity<>("Oops! Company not existing with id: " + job.getCompanyId(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDataDTO> getById(@PathVariable Long id) {
        JobDataDTO jobDataDTO = jobService.findJobById(id);
        if (jobDataDTO != null)
            return new ResponseEntity<>(jobDataDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(jobDataDTO, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean result = jobService.deleteJobById(id);
        if (result)
            return new ResponseEntity<>("Job deleted successfully existing with Id: " + id, HttpStatus.OK);
        else
            return new ResponseEntity<>("Oops! Job not existing with id: " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody Job updateJob) {
        boolean result = jobService.updateJobById(id, updateJob);
        String msg = "Job updated successfully existing with Id: " + id;
        if (result)
            return new ResponseEntity<>(msg, HttpStatus.OK);
        return new ResponseEntity<>("Oops! Job not existing with id: " + id, HttpStatus.NOT_FOUND);
    }

}
