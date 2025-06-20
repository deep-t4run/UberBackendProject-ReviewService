package com.example.uberreviewservice.controller;

import com.example.uberreviewservice.adapters.CreateReviewDtoToReviewAdapterImpl;
import com.example.uberreviewservice.dto.CreateReviewDto;
import com.example.uberreviewservice.dto.ReviewDto;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private CreateReviewDtoToReviewAdapterImpl createReviewDtoToReviewAdapterImpl;

    public ReviewController(ReviewService reviewService,  CreateReviewDtoToReviewAdapterImpl createReviewDtoToReviewAdapterImpl) {
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapterImpl = createReviewDtoToReviewAdapterImpl;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAllReviews() {
        List<Review> reviews = this.reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> findReviewById(@PathVariable Long id) {
        Optional<Review> review = this.reviewService.findReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@Validated @RequestBody CreateReviewDto givenReview) {
        Review incomingReview = this.createReviewDtoToReviewAdapterImpl.convertDto(givenReview);
        if(incomingReview  == null){
            return new ResponseEntity<>("Invalid arguments",HttpStatus.BAD_REQUEST);
        }
        Review review = this.reviewService.publishReview(incomingReview);
        ReviewDto response = ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .booking(review.getBooking().getId())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id){
        boolean review = this.reviewService.deleteReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }


}
