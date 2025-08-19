package com.uber.UberReviewService.services;

import com.uber.UberReviewService.models.RatingCommentView;
import com.uber.UberEntityService.models.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> findAllReviews();

    public boolean deleteReviewById(Long id);

    public Review publishReview(Review request);

    public List<RatingCommentView> getAllReviews();

    public Review getReview(Long reviewId);

    public void updateReview(Long reviewId, Review request);
}
