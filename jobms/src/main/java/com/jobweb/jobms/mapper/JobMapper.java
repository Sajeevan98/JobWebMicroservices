package com.jobweb.jobms.mapper;

import com.jobweb.jobms.dto.JobDataDTO;
import com.jobweb.jobms.entity.Job;
import com.jobweb.jobms.external.Company;
import com.jobweb.jobms.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDataDTO mapJobWithCompanyDto(Job job, Company company, List<Review> review) {

        JobDataDTO jobDataDTO = new JobDataDTO();
        jobDataDTO.setId(job.getId());
        jobDataDTO.setTitle(job.getTitle());
        jobDataDTO.setDescription(job.getDescription());
        jobDataDTO.setMinSalary(job.getMinSalary());
        jobDataDTO.setMaxSalary(job.getMaxSalary());
        jobDataDTO.setLocation(job.getLocation());
        jobDataDTO.setCompany(company);
        jobDataDTO.setReview(review);

        return jobDataDTO;
    }
}
