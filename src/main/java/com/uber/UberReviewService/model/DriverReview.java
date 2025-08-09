package com.uber.UberReviewService.model;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class DriverReview extends Review {

    private String driverReviewComment;

}
