package com.uber.UberReviewService.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.uber.UberReviewService.model.Review}
 */
@Value
public class CreateReviewDto implements Serializable {
    private Double rating;
    private String comment;
    private Long bookingId;
}