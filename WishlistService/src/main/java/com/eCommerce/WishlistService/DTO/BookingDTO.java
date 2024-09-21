package com.eCommerce.WishlistService.DTO;



import java.util.List;

public class BookingDTO {
    private String bId;
    private List<ProductDTO> product;
    private double totalAmount;

    public BookingDTO() {
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public List<ProductDTO> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDTO> product) {
        this.product = product;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bId='" + bId + '\'' +
                ", product=" + product +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
