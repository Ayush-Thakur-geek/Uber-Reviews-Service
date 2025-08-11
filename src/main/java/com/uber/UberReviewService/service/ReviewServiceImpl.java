package com.uber.UberReviewService.service;

import com.uber.UberReviewService.model.Booking;
import com.uber.UberReviewService.model.CustomDetails;
import com.uber.UberReviewService.model.CustomDriver;
import com.uber.UberReviewService.model.Driver;
import com.uber.UberReviewService.repositories.BookingRepository;
import com.uber.UberReviewService.repositories.DriverRepository;
import com.uber.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService, CommandLineRunner {

    private final DriverRepository driverRepository;
    private final ReviewRepository reviewRepo;
    private final BookingRepository bookingRepo;

    public ReviewServiceImpl(ReviewRepository reviewRepo, BookingRepository bookingRepo, DriverRepository driverRepository) {
        this.reviewRepo = reviewRepo;
        this.bookingRepo = bookingRepo;
        this.driverRepository = driverRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        Review review = Review.builder()
//                .rating(5.0)
//                .comment("This is a review")
//                .build();
//
//        Booking b = Booking.builder()
//                .review(review)
//                .endTime(new Date())
//                .build();
//
////        reviewRepo.save(review);
//        bookingRepo.save(b);
//
//        System.out.println(review.toString());
//
//        List<Review> reviews = reviewRepo.findAll();
//
//        System.out.println(review.getId());
//
//        for (Review rev : reviews) {
//            System.out.println(rev.getComment());
//        }
//        Optional<Booking> b = bookingRepo.findById(52L);
//        if (b.isPresent()) {
//            bookingRepo.delete(b.get());
//        }

//        Optional<CustomDriver> driver = driverRepository.rawFindByIdAndLicenseNumber(1L, "DL1212");
//
//        if (driver.isPresent()) {
//            System.out.println(driver.get().getName());
//            List<Booking> b = driver.get().getBookings();
//
//            for (Booking booking : b) {
//                System.out.println(booking.getId());
//            }
//            List<Booking> bookings = bookingRepo.findByDriverId(driver.get().getId());
//            for (Booking booking : bookings) {
//                System.out.println(booking.getBookingStatus());
//            }
//        }

        Long[] dId = {1L, 2L, 3L};

//        List<CustomDetails> details = driverRepository.rawFindBookingsByDriverIds(Arrays.asList(dId));
//        for (CustomDetails detail : details) {
//            System.out.println("driver id: " + detail.getId() + ", driver name: " + detail.getName() + ", booking id: " + detail.getBookingId() + ", total distance: " + detail.getTotalDistance());
//        }
        List<Driver> details = driverRepository.findAllWithBookings(Arrays.asList(dId));
        for (Driver d : details) {
            System.out.println(d.getName());
            for (Booking b : d.getBookings()) {
                System.out.println(b.getTotalDistance());
            }
        }
    }
}
