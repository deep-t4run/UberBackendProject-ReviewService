package com.example.uberreviewservice.adapters;

import com.example.uberreviewservice.dto.CreateReviewDto;
import com.example.uberreviewservice.models.Review;

public interface CreateReviewDtoToReviewAdapter {
    public Review convertDto(CreateReviewDto createReviewDto);
}
