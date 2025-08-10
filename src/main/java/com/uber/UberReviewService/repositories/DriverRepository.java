package com.uber.UberReviewService.repositories;

import com.uber.UberReviewService.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

//    @Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id = :id AND license_number = :licenseNumber") // Raw mysql query, where error is thrown at runtime
//    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query("FROM Driver as d WHERE d.id = :id AND d.licenseNumber = :ln") // hibernate query, where error is thrown at compile time.
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String ln);
}
