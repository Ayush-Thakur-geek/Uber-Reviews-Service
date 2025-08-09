package com.uber.UberReviewService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseModel {

    @Enumerated(value = EnumType.STRING) // Default would be EnumType.ORDINAL, i.e. tiny int.
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private Double totalDistance;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private Review review; // Defined 1:1 relationship between booking and review.

    @ManyToOne
    private Driver driver; // Many side has a foreign key

    @ManyToOne
    private Passenger passenger;

}
