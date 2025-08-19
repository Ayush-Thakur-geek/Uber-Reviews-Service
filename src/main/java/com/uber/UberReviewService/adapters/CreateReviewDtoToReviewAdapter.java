package com.uber.UberReviewService.adapters;

import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberEntityService.models.Review;

public interface CreateReviewDtoToReviewAdapter {

    public Review convertDto(CreateReviewDto createReviewDto);

}
