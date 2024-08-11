package com.project.CartService.Entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Cart_Table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "cartId"),
        @UniqueConstraint(columnNames = "itemId")
})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cartId;
    private Long itemId;



    public Cart() {
    }

    public Cart(Long id, String cartId, Long itemId) {
        this.id = id;
        this.cartId = cartId;
        this.itemId = itemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartId='" + cartId + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}
