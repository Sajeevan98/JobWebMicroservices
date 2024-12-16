package com.jobweb.jobms.dto;

import com.jobweb.jobms.external.Company;
import com.jobweb.jobms.external.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JobDataDTO {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> review;


}
