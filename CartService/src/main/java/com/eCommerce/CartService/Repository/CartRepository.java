package com.eCommerce.CartService.Repository;

import com.eCommerce.CartService.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.uId = :uId")
    Cart findCartByUId(@Param("uId") Long uId);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.uId = :uId")
    void deleteCartByUId(@Param("uId") Long uId);
}
