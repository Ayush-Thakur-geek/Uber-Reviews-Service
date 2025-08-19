package com.uber.UberReviewService.models;

import com.uber.UberEntityService.models.Review;
import com.uber.UberEntityService.models.Passenger;
import com.uber.UberEntityService.models.Driver;

public interface CustomDriverPassengerReview {
    Review getReview();
    Passenger getPassenger();
    Driver getDriver();
}
