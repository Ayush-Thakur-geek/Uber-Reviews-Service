package com.uber.UberReviewService.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private int reviewId;
    private Date createdAt;
    private Date updatedAt;
    private Double rating;
    private String comment;
}
