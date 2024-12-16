package com.jobweb.jobms.service;

import com.jobweb.jobms.dto.JobDataDTO;
import com.jobweb.jobms.entity.Job;
import java.util.List;

public interface JobService {

    List<JobDataDTO> findAllJob();
    boolean createJob(Job job);
    JobDataDTO findJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job updateJob);


}
