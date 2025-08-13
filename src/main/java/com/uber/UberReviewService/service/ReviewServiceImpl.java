package com.uber.UberReviewService.service;

import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.model.Booking;
import com.uber.UberReviewService.model.RatingCommentView;
import com.uber.UberReviewService.model.Review;
import com.uber.UberReviewService.repositories.BookingRepository;
import com.uber.UberReviewService.repositories.ReviewRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final BookingRepository bookingRepository;
    private ReviewRepository reviewRepository;

    ReviewServiceImpl(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review findReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            reviewRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Long publishReview(Review request) {
        try {
            return reviewRepository.save(request).getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<RatingCommentView> getAllReviews() {
        List<RatingCommentView> list = reviewRepository.findRatingsAndComments();
        return list;
    }

    @Override
    public Review getReview(Long reviewId) {
        Optional<Review> r = reviewRepository.findById(reviewId);
        return r.orElse(null);
    }

    @Transactional
    @Override
    public void updateReview(Long revieiwId, Review request) {
        reviewRepository.updateReviewById(revieiwId, request.getRating(), request.getComment());
    }
}
