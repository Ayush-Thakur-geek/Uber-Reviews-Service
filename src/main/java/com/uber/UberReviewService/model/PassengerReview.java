package com.uber.UberReviewService.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PassengerReview extends Review {

    private String passengerReviewComment;

}
