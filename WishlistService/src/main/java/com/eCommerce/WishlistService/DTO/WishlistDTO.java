package com.eCommerce.WishlistService.DTO;

import java.util.List;

public class WishlistDTO {

    private String wId;
    private List<ProductDTO> items;

    public WishlistDTO() {
    }

    public WishlistDTO(String wId, List<ProductDTO> items) {
        this.wId = wId;
        this.items = items;
    }

    public String getwId() {
        return wId;
    }

    public void setwId(String wId) {
        this.wId = wId;
    }

    public List<ProductDTO> getItems() {
        return items;
    }

    public void setItems(List<ProductDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "WishlistDTO{" +
                "wId='" + wId + '\'' +
                ", items=" + items +
                '}';
    }
}
