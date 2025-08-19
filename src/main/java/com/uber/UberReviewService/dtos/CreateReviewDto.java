package com.uber.UberReviewService.dtos;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.uber.UberEntityService.models.Review}
 */
//@Value
@Data
public class CreateReviewDto implements Serializable {
    private Double rating;
    private String comment;
    private Long bookingId;
}