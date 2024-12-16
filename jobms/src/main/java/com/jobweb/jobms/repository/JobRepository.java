package com.jobweb.jobms.repository;

import com.jobweb.jobms.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
