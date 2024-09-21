package com.eCommerce.WishlistService.DTO;

public class OrderDTO {
    private String oId;
    private BookingDTO bookingDTO;
    private double totalAmount;

    public OrderDTO() {
    }

    public OrderDTO(String oId, BookingDTO bookingDTO, double totalAmount) {
        this.oId = oId;
        this.bookingDTO = bookingDTO;
        this.totalAmount = totalAmount;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public BookingDTO getBookingDTO() {
        return bookingDTO;
    }

    public void setBookingDTO(BookingDTO bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oId='" + oId + '\'' +
                ", bookingDTO=" + bookingDTO +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
