package com.uber.UberReviewService.adapters;

import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.model.Review;

public interface CreateReviewDtoToReviewAdapter {

    public Review convertDto(CreateReviewDto createReviewDto);

}
