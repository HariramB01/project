package com.eCommerce.CartService.Repository;

import com.eCommerce.CartService.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByUId(Long id);

    void deleteCartByUId(Long id);
}
