package com.uber.UberReviewService.adapters;

import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberReviewService.model.Booking;
import com.uber.UberReviewService.model.Review;
import com.uber.UberReviewService.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewToReviewAdapterImpl implements CreateReviewToReviewAdapter {

    private final BookingRepository bookingRepository;

    public CreateReviewToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review convertDto(CreateReviewDto createReviewDto) {
        System.out.println(createReviewDto.getBookingId());
        Optional<Booking> booking = bookingRepository.findById(createReviewDto.getBookingId());
        return booking.map(value -> Review.builder()
                .comment(createReviewDto.getComment())
                .rating(createReviewDto.getRating())
                .booking(value)
                .build()).orElse(null);
    }
}
