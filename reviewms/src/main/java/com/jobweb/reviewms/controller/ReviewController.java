package com.jobweb.reviewms.controller;

import com.jobweb.reviewms.enity.Review;
import com.jobweb.reviewms.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReview(@RequestParam Long companyId){
        List<Review> reviewList =  reviewService.findAllReview(companyId);
        if (!reviewList.isEmpty())
            return new ResponseEntity<>(reviewList, HttpStatus.OK);
        else{
            return new ResponseEntity<>(reviewList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean response = reviewService.addReview(companyId, review);
        if(response)
            return new ResponseEntity<>("review successfully added to company existing with id: "+companyId, HttpStatus.OK);
        else
            return new ResponseEntity<>("No company found existing with id: "+companyId, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewByReviewId(@PathVariable Long reviewId){
        Review review = reviewService.findReviewById(reviewId);
        if (review != null)
            return new ResponseEntity<>( review, HttpStatus.OK );
        else
            return new ResponseEntity<>( review, HttpStatus.NOT_FOUND );
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review updateReview){

        boolean isUpdated = reviewService.updateReview(reviewId, updateReview);
        if (isUpdated)
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>("failed! Check again...", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if (isDeleted)
            return new ResponseEntity<>("Review with id: "+reviewId+" is deleted successfully.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not found with id: "+ reviewId, HttpStatus.NOT_FOUND);
    }

}
