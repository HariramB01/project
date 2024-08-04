package com.project.ReviewService.Service;

import com.project.ReviewService.DTO.ReviewDTO;
import com.project.ReviewService.Entity.Review;
import com.project.ReviewService.Exception.NoItemsAvailableException;
import com.project.ReviewService.Mapper.ObjectMapper;
import com.project.ReviewService.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        Review review = ObjectMapper.convertToDTO(reviewDTO); // Assuming the method name is convertToEntity
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            throw new NoItemsAvailableException("OOPS!! Currently there are no reviews posted on this product");
        }
        return reviews;
    }

    @Override
    public List<Review> getReviews(Long inventoryId, Long id) {
        List<Review> reviews = reviewRepository.findByInventoryIdAndId(inventoryId, id);
        if (reviews.isEmpty()) {
            throw new NoItemsAvailableException("OOPS!! Currently there are no reviews posted for this inventory item");
        }
        return reviews;
    }

    @Override
    public Optional<Review> getReviewsById(Long id) {
        Optional<Review> reviews = reviewRepository.findById(id);
        if (reviews.isEmpty()) {
            throw new NoItemsAvailableException("OOPS!! Currently there are no reviews posted for this inventory item");
        }
        return reviews;
    }
}
