package com.eCommerce.UserService.Mapper;

import com.eCommerce.UserService.DTO.ProductDTO;
import com.eCommerce.UserService.Feign.InventoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class WishlistResponseMapper {

    private final static Logger logger = LoggerFactory.getLogger(WishlistResponseMapper.class);

    @Autowired
    private InventoryClient inventoryClient;

    public List<ProductDTO> productIdsToProducts(List<Long> productIds){
        List<ProductDTO> products = new ArrayList<>();
        for(Long id : productIds){
            products.add(inventoryClient.getProductById(id));
        }
        logger.info("List of product Ids converted into products", products);
        return products;
    }

}
