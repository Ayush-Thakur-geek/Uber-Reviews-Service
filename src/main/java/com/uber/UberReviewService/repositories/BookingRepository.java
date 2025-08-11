package com.uber.UberReviewService.repositories;

import com.uber.UberReviewService.model.Booking;
import com.uber.UberReviewService.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDriverId(Long driverId);

    List<Booking> findAllByDriverIn(List<Driver> drivers);

}
