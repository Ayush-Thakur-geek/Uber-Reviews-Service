package com.uber.UberReviewService.repositories;

import com.uber.UberReviewService.model.CustomDriverPassengerReview;
import com.uber.UberReviewService.model.RatingCommentView;
import com.uber.UberReviewService.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Integer countAllByRatingIsLessThanEqual(Integer rating);

    List<Review> findAllByRatingIsLessThanEqual(Integer rating);

    List<Review> findAllByCreatedAtBefore(Date createdAt);

    @Query("SELECT r FROM Booking b INNER JOIN Review r ON b.review = r WHERE b.id = :bookingId")
    Review findReviewByBookingId(Long bookingId);

    @Query("""
    SELECT b.review AS review, b.passenger AS passenger, b.driver AS driver
    FROM Booking b
    WHERE b.id = :bookingId
""")
    CustomDriverPassengerReview findDriverPassengerReviewByBookingId(Long bookingId);

    @Query("""
       SELECT r.rating AS rating, r.comment AS comment
       FROM Review r
       """)
    List<RatingCommentView> findRatingsAndComments();

    @Modifying
    @Query("""
        UPDATE Review
        SET rating = :rating, comment = :comment
        WHERE id = :reviewId
""")
    void updateReviewById(Long reviewId, Double rating, String comment);
}
