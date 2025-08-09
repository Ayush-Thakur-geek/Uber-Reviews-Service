package com.uber.UberReviewService.service;

import com.uber.UberReviewService.model.Review;
import com.uber.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService, CommandLineRunner {

    private ReviewRepository reviewRepo;

    public ReviewServiceImpl(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Review review = Review.builder()
                .rating(5.0)
                .comment("This is a review")
                .build();

        System.out.println(review.toString());

        reviewRepo.save(review);

        List<Review> reviews = reviewRepo.findAll();

        System.out.println(review.getId());

        for (Review rev : reviews) {
            System.out.println(rev.getComment());
        }
    }
}
