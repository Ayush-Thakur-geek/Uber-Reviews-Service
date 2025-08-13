package com.uber.UberReviewService.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class PassengerReview extends Review {

    private Double passengerRating;
    private String passengerReviewComment;

}
