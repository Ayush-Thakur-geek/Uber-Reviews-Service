package com.uber.UberReviewService.service;

import com.uber.UberReviewService.model.CustomDriverPassengerReview;
import com.uber.UberReviewService.model.Driver;
import com.uber.UberReviewService.model.Passenger;
import com.uber.UberReviewService.model.Review;
import com.uber.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Test implements CommandLineRunner {
    ReviewRepository reviewRepository;
    Test(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        CustomDriverPassengerReview c = reviewRepository.findDriverPassengerReviewByBookingId(1L);
        Driver d = c.getDriver();
        Passenger p = c.getPassenger();
        Review r = c.getReview();
        System.out.println(d.getName());
        System.out.println(p.getName());
        System.out.println(r.getComment());
    }
}
