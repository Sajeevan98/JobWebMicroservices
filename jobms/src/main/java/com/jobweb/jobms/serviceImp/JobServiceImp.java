package com.jobweb.jobms.serviceImp;

import com.jobweb.jobms.client.CompanyClient;
import com.jobweb.jobms.client.ReviewClient;
import com.jobweb.jobms.dto.JobDataDTO;
import com.jobweb.jobms.entity.Job;
import com.jobweb.jobms.external.Company;
import com.jobweb.jobms.external.Review;
import com.jobweb.jobms.mapper.JobMapper;
import com.jobweb.jobms.repository.JobRepository;
import com.jobweb.jobms.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImp implements JobService {

    @Autowired
    RestTemplate restTemplate;

    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewClient reviewsClient;

    public JobServiceImp(JobRepository jobRepository, ReviewClient reviewsClient, CompanyClient companyClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewsClient = reviewsClient;
    }

    @Override
    public List<JobDataDTO> findAllJob() {
        List<Job> jobs = jobRepository.findAll();

        // 'stream()' is a sequence of elements that can be process in a pipeline.
        return jobs.stream().map(this::convertToDTOs)
                .collect(Collectors.toList());
    }

    private JobDataDTO convertToDTOs(Job job) {

//        // get a single object as return type
//        Company company = restTemplate.getForObject(
//                "http://COMPANY-SERVICE:8083/companies/" + job.getCompanyId(),
//                Company.class);

//        // get the list as return type
//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                "http://REVIEW-SERVICE:8084/reviews/all?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });
//        List<Review> reviews = reviewResponse.getBody();

        // use Feign-Client insteadof RestTemplate
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewsClient.getReviews(job.getCompanyId());

        // create instance & pass the job & company objects to mapping-class.
        return JobMapper.mapJobWithCompanyDto(job, company, reviews);
    }

    @Override
    public boolean createJob(Job job) {
        jobRepository.save(job);
        return true;
    }

    @Override
    public JobDataDTO findJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null)
            return convertToDTOs(job);
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
