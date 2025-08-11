package com.uber.UberReviewService.service;

import com.uber.UberReviewService.model.Review;

import java.util.List;

public interface ReviewService {

    public Review findReviewById(Long id);

    public List<Review> findAllReviews();

    public boolean deleteReviewById(Long id);
}
