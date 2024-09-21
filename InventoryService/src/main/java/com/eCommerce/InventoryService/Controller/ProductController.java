package com.eCommerce.InventoryService.Controller;

import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.eCommerce.InventoryService.DTO.ProductFilter;
import com.eCommerce.InventoryService.Entity.Product;
import com.eCommerce.InventoryService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{inventoryId}")
    public ResponseEntity<Product> createOrUpdateProduct(@PathVariable Long inventoryId, @RequestBody Product product) {
        Product savedProduct = productService.saveProduct(inventoryId, product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> searchProductsByFilter(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minQuantity,
            @RequestParam(required = false) Integer maxQuantity) {

        ProductFilter filter = new ProductFilter.Builder()
                .keyword(keyword)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .minQuantity(minQuantity)
                .maxQuantity(maxQuantity)
                .build();

        List<Product> filteredProducts = productService.searchProductsByFilter(filter);
        return ResponseEntity.ok(filteredProducts);
    }
}
