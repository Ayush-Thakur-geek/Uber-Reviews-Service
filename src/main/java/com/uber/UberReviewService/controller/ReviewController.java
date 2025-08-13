package com.uber.UberReviewService.controller;

import com.uber.UberReviewService.adapters.CreateReviewToReviewAdapter;
import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.model.RatingCommentView;
import com.uber.UberReviewService.model.Review;
import com.uber.UberReviewService.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final CreateReviewToReviewAdapter createReviewToReviewAdapter;

    public ReviewController(ReviewService reviewService, CreateReviewToReviewAdapter createReviewToReviewAdapter) {
        this.reviewService = reviewService;
        this.createReviewToReviewAdapter = createReviewToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<Long> publishReview(@RequestBody CreateReviewDto request) {
        Long id = reviewService.publishReview(createReviewToReviewAdapter.convertDto(request));
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllReviews() {
        try {
            List<RatingCommentView> list = this.reviewService.getAllReviews();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(List.of(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable Long reviewId) {
        try {
            Review r = this.reviewService.getReview(reviewId);
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request) {
        try {
            reviewService.updateReview(reviewId, request);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Review updated", HttpStatus.OK);
    }
}
