package com.eCommerce.WishlistService;

public class WishlistRequest {

    private Long id;
    private Long uId;
    private Long productId;

    public WishlistRequest() {
    }

    public WishlistRequest(Long id, Long uId, Long productId) {
        this.id = id;
        this.uId = uId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "WishlistRequest{" +
                "id=" + id +
                ", uId=" + uId +
                ", productId=" + productId +
                '}';
    }
}
