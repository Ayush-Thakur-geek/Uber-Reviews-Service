package com.uber.UberReviewService.adapters;

import com.uber.UberReviewService.dtos.CreateReviewDto;
import com.uber.UberEntityService.models.Booking;
import com.uber.UberEntityService.models.Review;
import com.uber.UberReviewService.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter {

    private final BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
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
