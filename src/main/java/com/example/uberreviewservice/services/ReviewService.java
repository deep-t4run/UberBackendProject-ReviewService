package com.example.uberreviewservice.services;

import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService implements CommandLineRunner {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*********************");

        Review r = Review
                .builder()
                .content("It was a good write")
                .rating(4.8)
                .build();  //code to create plain java objects
        System.out.println(r);
        reviewRepository.save(r); //this code executes sql queries

        List<Review> reviews = reviewRepository.findAll();

        for(Review review : reviews) {
            System.out.println(review.getContent());
        }

    }
}
