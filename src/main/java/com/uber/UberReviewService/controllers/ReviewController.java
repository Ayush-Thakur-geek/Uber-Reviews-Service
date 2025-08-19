package com.uber.UberReviewService.controllers;

import com.uber.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.dtos.ReviewDto;
import com.uber.UberReviewService.models.RatingCommentView;
import com.uber.UberEntityService.models.Review;
import com.uber.UberReviewService.services.ReviewService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> publishReview(@RequestBody CreateReviewDto request) {
        Review review = reviewService.publishReview(createReviewDtoToReviewAdapter.convertDto(request));
        if (review == null) {
            System.out.println("Null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReviewDto rDto = ReviewDto.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();

        return new ResponseEntity<>(rDto, HttpStatus.CREATED);
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
        Review r = this.reviewService.getReview(reviewId);
        if  (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request) {
        try {
            reviewService.updateReview(reviewId, request);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Review updated", HttpStatus.OK);
    }
}
