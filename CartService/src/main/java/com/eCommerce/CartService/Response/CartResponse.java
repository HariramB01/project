package com.eCommerce.CartService.Response;

import com.eCommerce.basedomains.DTO.ProductDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CartResponse {

    private Long id;
    private Long uId;
    private double totalAmount;
    private List<ProductDTO> products;

    public CartResponse(Long id, Long uId, double totalAmount, List<ProductDTO> products) {
        this.id = id;
        this.uId = uId;
        this.totalAmount = totalAmount;
        this.products = new ArrayList<>();
    }

}
