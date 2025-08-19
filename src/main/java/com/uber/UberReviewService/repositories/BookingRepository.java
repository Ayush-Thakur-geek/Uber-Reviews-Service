package com.uber.UberReviewService.repositories;

import com.uber.UberEntityService.models.Booking;
import com.uber.UberEntityService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDriverId(Long driverId);

    List<Booking> findAllByDriverIn(List<Driver> drivers);

}
