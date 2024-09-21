package com.project.ReviewService.Repository;

import com.project.ReviewService.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM Review_Table WHERE inventory_id = :inventoryId AND id = :id", nativeQuery = true)
    List<Review> findByInventoryIdAndId(@Param("inventoryId") Long inventoryId, @Param("id") Long id);
}
