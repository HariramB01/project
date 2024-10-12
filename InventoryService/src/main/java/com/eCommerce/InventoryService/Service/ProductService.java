package com.eCommerce.InventoryService.Service;

import com.eCommerce.basedomains.DTO.ProductDTO;
import com.eCommerce.basedomains.DTO.ProductFilter;
import com.eCommerce.InventoryService.Entity.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Long inventoryId, Product product);

    List<Product> getAllProducts();

    ProductDTO getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(Long id, Product updatedProduct);

    List<Product> searchProducts(String keyword);

    List<Product> searchProductsByFilter(ProductFilter filter);
}
