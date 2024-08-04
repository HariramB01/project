package com.project.ReviewService.Service;

import com.project.ReviewService.DTO.ReviewDTO;
import com.project.ReviewService.Entity.Review;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Review createReview(@Valid ReviewDTO reviewDTO);

    List<Review> getAllReview();

    List<Review> getReviews(Long inventoryId, Long id);

    Optional<Review> getReviewsById(Long id);
}
