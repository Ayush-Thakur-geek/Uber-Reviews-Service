package com.uber.UberReviewService.adapters;

import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.model.Review;

public interface CreateReviewToReviewAdapter {

    public Review convertDto(CreateReviewDto createReviewDto);

}
