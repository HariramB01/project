package com.project.CartService.Repository;

import com.project.CartService.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByItemId(Long itemId);

    Optional<Cart> findByItemId(Long itemId);
}
