package com.project.ReviewService.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ReviewDTO {

    private Long id;

    @NotBlank(message = "comment must not be empty")
    @Size(min = 4, max = 100, message = "comment length must be in 4 to 100 characters")
    private String comment;

    @DecimalMin(value = "0.1", message = "Rating must be greater than 0")
    @DecimalMax(value = "10.0", message = "Rating must be less than 10")
    private float rating;

    private LocalDateTime commentCreated;

    private Long inventoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "comment must not be empty") @Size(min = 4, max = 100, message = "comment length must be in 4 to 100 characters") String getComment() {
        return comment;
    }

    public void setComment(@NotBlank(message = "comment must not be empty") @Size(min = 4, max = 100, message = "comment length must be in 4 to 100 characters") String comment) {
        this.comment = comment;
    }

    @DecimalMin(value = "0.1", message = "Rating must be greater than 0")
    @DecimalMax(value = "10.0", message = "Rating must be less than 10")
    public float getRating() {
        return rating;
    }

    public void setRating(@DecimalMin(value = "0.1", message = "Rating must be greater than 0") @DecimalMax(value = "10.0", message = "Rating must be less than 10") float rating) {
        this.rating = rating;
    }

    public LocalDateTime getCommentCreated() {
        return commentCreated;
    }

    public void setCommentCreated(LocalDateTime commentCreated) {
        this.commentCreated = commentCreated;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", commentCreated=" + commentCreated +
                ", inventoryId=" + inventoryId +
                '}';
    }
}
