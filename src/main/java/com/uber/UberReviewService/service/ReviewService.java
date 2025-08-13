package com.uber.UberReviewService.service;

import com.uber.UberReviewService.model.RatingCommentView;
import com.uber.UberReviewService.model.Review;

import java.util.List;

public interface ReviewService {

    public Review findReviewById(Long id);

    public List<Review> findAllReviews();

    public boolean deleteReviewById(Long id);

    public Long publishReview(Review request);

    public List<RatingCommentView> getAllReviews();

    public Review getReview(Long reviewId);

    public void updateReview(Long revieiwId, Review request);
}
