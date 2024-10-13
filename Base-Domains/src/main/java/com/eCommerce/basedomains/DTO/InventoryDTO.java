package com.eCommerce.basedomains.DTO;

import java.util.List;

public class InventoryDTO {

    private Long id;
    private int totalItems;
    private String inventoryLocation;
    private List<ProductDTO> products;

    public InventoryDTO() {}

    public InventoryDTO(Long id, int totalItems, String inventoryLocation, List<ProductDTO> products) {
        this.id = id;
        this.totalItems = totalItems;
        this.inventoryLocation = inventoryLocation;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "id=" + id +
                ", totalItems=" + totalItems +
                ", inventoryLocation='" + inventoryLocation + '\'' +
                ", products=" + products +
                '}';
    }
}
