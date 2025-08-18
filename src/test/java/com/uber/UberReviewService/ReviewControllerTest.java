package com.uber.UberReviewService;

import com.uber.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.uber.UberReviewService.controller.ReviewController;
import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.dtos.ReviewDto;
import com.uber.UberReviewService.model.Booking;
import com.uber.UberReviewService.model.Review;
import com.uber.UberReviewService.service.ReviewService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {

    private AutoCloseable closeable;

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Mock
    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;


    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetReview_Success() {
        long reviewId = 1L;
        Review mockReview = Review.builder().build();
        mockReview.setId(reviewId);

        //mocking
        when(reviewService.getReview(reviewId)).thenReturn(mockReview);

        //perform the test
        ResponseEntity<?> response = reviewController.getReview(reviewId);

        //assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Review returnedReview = (Review) response.getBody();
        Assertions.assertNotNull(returnedReview);
        assertEquals(reviewId, returnedReview.getId());
    }

    @Test
    public void testPublishReview_Success() {
        Long reviewId = 1L;
        Long bookingId = 1L;
        CreateReviewDto requestDto = new CreateReviewDto();
        Booking booking = Booking.builder().build();
        booking.setId(bookingId);
        Review incomingReview = Review.builder()
                .rating(4.0)
                .comment("Test publish review api")
                .booking(booking)
                .build();
        incomingReview.setId(reviewId);

        when(createReviewDtoToReviewAdapter.convertDto(requestDto)).thenReturn(incomingReview);
        Review savedReview = createReviewDtoToReviewAdapter.convertDto(requestDto);
        when(reviewService.publishReview(incomingReview)).thenReturn(savedReview);

        ResponseEntity<?> response = reviewController.publishReview(requestDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ReviewDto returnedReview = (ReviewDto) response.getBody();
        assertNotNull(returnedReview);
        assertEquals(reviewId, returnedReview.getReviewId());
    }

}
