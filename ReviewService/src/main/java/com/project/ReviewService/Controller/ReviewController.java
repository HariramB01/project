package com.project.ReviewService.Controller;

import com.project.ReviewService.DTO.ReviewDTO;
import com.project.ReviewService.Entity.Review;
import com.project.ReviewService.Service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview() {
        return ResponseEntity.ok(reviewService.getAllReview());
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        Review review = reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/{inventoryId}/{id}")
    public ResponseEntity<List<Review>> getReviewsByInventoryIdAndId(
            @PathVariable Long inventoryId, @PathVariable Long id) {
        List<Review> reviews = reviewService.getReviews(inventoryId, id);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Review>> getReviewsById(@PathVariable Long id) {
        Optional<Review> reviews = reviewService.getReviewsById(id);
        return ResponseEntity.ok(reviews);
    }
}
