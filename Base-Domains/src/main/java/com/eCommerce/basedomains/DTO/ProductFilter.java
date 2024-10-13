package com.eCommerce.basedomains.DTO;


public class ProductFilter {

    private String keyword;
    private Double minPrice;
    private Double maxPrice;
    private Integer minQuantity;
    private Integer maxQuantity;

    private ProductFilter(Builder builder) {
        this.keyword = builder.keyword;
        this.minPrice = builder.minPrice;
        this.maxPrice = builder.maxPrice;
        this.minQuantity = builder.minQuantity;
        this.maxQuantity = builder.maxQuantity;
    }

    public static class Builder {
        private String keyword;
        private Double minPrice;
        private Double maxPrice;
        private Integer minQuantity;
        private Integer maxQuantity;

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public Builder minPrice(Double minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public Builder maxPrice(Double maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public Builder minQuantity(Integer minQuantity) {
            this.minQuantity = minQuantity;
            return this;
        }

        public Builder maxQuantity(Integer maxQuantity) {
            this.maxQuantity = maxQuantity;
            return this;
        }

        public ProductFilter build() {
            return new ProductFilter(this);
        }
    }

    // Getters
    public String getKeyword() { return keyword; }
    public Double getMinPrice() { return minPrice; }
    public Double getMaxPrice() { return maxPrice; }
    public Integer getMinQuantity() { return minQuantity; }
    public Integer getMaxQuantity() { return maxQuantity; }
}
