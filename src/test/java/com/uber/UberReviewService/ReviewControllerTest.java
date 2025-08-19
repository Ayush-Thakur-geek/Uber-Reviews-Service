package com.uber.UberReviewService;

import com.uber.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.uber.UberReviewService.controllers.ReviewController;
import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.dtos.ReviewDto;
import com.uber.UberEntityService.models.Booking;
import com.uber.UberReviewService.models.RatingCommentView;
import com.uber.UberEntityService.models.Review;
import com.uber.UberReviewService.services.ReviewService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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

    @Test
    public void testUpdateReview_Success() {
        Long reviewId = 1L;
        Review request = Review.builder()
                .rating(4.0)
                .comment("Test update review api")
                .build();
        request.setId(reviewId);

        // Mock void method
        doNothing().when(reviewService).updateReview(reviewId, request);

        // Call controller directly
        ResponseEntity<?> response = reviewController.updateReview(reviewId, request);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Review updated", response.getBody());

        // Verify
        verify(reviewService, times(1)).updateReview(reviewId, request);
    }

    @Test
    public void testGetAllReview_Success() {
        RatingCommentView rc = new RatingCommentView() {
            @Override
            public Double getRating() {
                return 4.0;
            }

            @Override
            public String getComment() {
                return "Testing getAllReview api";
            }
        };

        List<RatingCommentView> rcList = new ArrayList<>();
        rcList.add(rc);
        when(reviewService.getAllReviews()).thenReturn(rcList);
        ResponseEntity<?> response = reviewController.getAllReviews();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<RatingCommentView> returnedList = (List<RatingCommentView>) response.getBody();
        assertNotNull(returnedList);
        assertEquals(rcList, returnedList);
    }

    @Test
    void testGetAllReviews_Failure() {
        // Arrange: mock the service to throw an exception
        when(reviewService.getAllReviews()).thenThrow(new RuntimeException("Database error"));

        // Act
        ResponseEntity<List<?>> response = reviewController.getAllReviews();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().get(0).toString().contains("Database error"));

        // Verify interaction
        verify(reviewService, times(1)).getAllReviews();
    }

    @Test
    public void testUpdateReview_Failure() {
        Long reviewId = 1L;
        Review request = Review.builder()
                .rating(4.0)
                .comment("Test update review api")
                .build();
        request.setId(reviewId);

        // Mock void method
        doThrow(new RuntimeException("Update failed"))
                .when(reviewService).updateReview(reviewId, request);

        ResponseEntity<?> response = reviewController.updateReview(reviewId, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Update failed", response.getBody());

        // Verify
        verify(reviewService, times(1)).updateReview(reviewId, request);
    }

    @Test
    public void testGetReview_Failure() {
        long reviewId = 1L;

        //mocking
        when(reviewService.getReview(reviewId)).thenReturn(null);

        //perform the test
        ResponseEntity<?> response = reviewController.getReview(reviewId);

        //assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Review returnedReview = (Review) response.getBody();
        assertNull(returnedReview);
    }

    @Test
    public void testPublishReview_Failure() {
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
        when(reviewService.publishReview(incomingReview)).thenReturn(null);

        ResponseEntity<?> response = reviewController.publishReview(requestDto);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ReviewDto returnedReview = (ReviewDto) response.getBody();
        assertNull(returnedReview);
    }

}
