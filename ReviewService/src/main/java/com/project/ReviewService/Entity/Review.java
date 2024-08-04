package com.project.ReviewService.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Review_Table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotBlank(message = "comment must not be empty")
//    @Size(min=4, max = 100, message = "comment length must be in 4 to 100 characters")
    private String comment;
//    @DecimalMin(value = "0.1", message = "Rating must be greater than 0")
//    @DecimalMax(value = "10.0", message = "Rating must be less than 10")
    private float rating;
    private LocalDateTime commentCreated;


    private Long inventoryId;

    public Review() {
    }

    public Review(Long id, String comment, float rating, LocalDateTime commentCreated, Long inventoryId) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.commentCreated = commentCreated;
        this.inventoryId = inventoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", commentCreated=" + commentCreated +
                ", inventoryId=" + inventoryId +
                '}';
    }
}
