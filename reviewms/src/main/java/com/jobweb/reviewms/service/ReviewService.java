package com.jobweb.reviewms.service;

import com.jobweb.reviewms.enity.Review;

import java.util.List;

public interface ReviewService {

    Review findReviewById(Long reviewId);
    boolean addReview(Long companyId, Review review);
    List<Review> findAllReview(Long companyId);
    boolean updateReview(Long reviewId, Review updateReview);
    boolean deleteReview(Long reviewId);
}
