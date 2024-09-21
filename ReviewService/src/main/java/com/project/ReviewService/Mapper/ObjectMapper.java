package com.project.ReviewService.Mapper;

import com.project.ReviewService.DTO.ReviewDTO;
import com.project.ReviewService.Entity.Review;

import java.time.LocalDateTime;

public class ObjectMapper {

    public static Review convertToDTO(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setCommentCreated(LocalDateTime.now());
        review.setInventoryId(reviewDTO.getInventoryId());
        return review;
    }
}
