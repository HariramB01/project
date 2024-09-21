package com.eCommerce.InventoryService.Service;

import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.eCommerce.InventoryService.DTO.ProductFilter;
import com.eCommerce.InventoryService.Entity.Inventory;
import com.eCommerce.InventoryService.Entity.Product;
import com.eCommerce.InventoryService.Exception.NoItemsAvailableException;
import com.eCommerce.InventoryService.ProductSpecification;
import com.eCommerce.InventoryService.Repository.InventoryRepository;
import com.eCommerce.InventoryService.Repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY_PREFIX = "product:";
    private static final String CACHE_KEY_ALL = CACHE_KEY_PREFIX + "all";
    private static final String CACHE_KEY_SEARCH = "productSearch:";

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product saveProduct(Long inventoryId, Product product) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new NoItemsAvailableException("Inventory not found"));
        product.setInventory(inventory);
        Product savedProduct = productRepository.save(product);
        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + savedProduct.getId(), savedProduct, 60, TimeUnit.SECONDS);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>) redisTemplate.opsForValue().get(CACHE_KEY_ALL);

        if (products == null) {
            products = productRepository.findAll();
            if (!products.isEmpty()) {
                redisTemplate.opsForValue().set(CACHE_KEY_ALL, products, 30, TimeUnit.SECONDS);
            }
        }
        return products;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        String cacheKey = CACHE_KEY_PREFIX + id;
        Object cachedProductObj = redisTemplate.opsForValue().get(cacheKey);
        if (cachedProductObj != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ProductDTO cachedProduct = objectMapper.convertValue(cachedProductObj, ProductDTO.class);
            System.out.println("Retrieving product data from Redis Cache");
            return cachedProduct;
        }
        System.out.println("Retrieving product data from DB");
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new NoItemsAvailableException("Product not found"));
        ProductDTO productDTO = convertToProductDTO(productFromDB);
        redisTemplate.opsForValue().set(cacheKey, productDTO, 60, TimeUnit.SECONDS);
        return productDTO;
    }



    private ProductDTO convertToProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getQuantity(),
                product.getPrice(),
                product.getAvailabilityStatus(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getExpiryDate()
        );
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
        redisTemplate.delete(CACHE_KEY_PREFIX + id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "products", key = "#id"),
            @CacheEvict(value = "productSearch", allEntries = true)
    })
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoItemsAvailableException("Product not found"));

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setAvailabilityStatus(updatedProduct.getAvailabilityStatus());
        existingProduct.setCategory(updatedProduct.getCategory());

        Product updated = productRepository.save(existingProduct);
        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + id, updated, 60, TimeUnit.SECONDS);
        return updated;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> products = (List<Product>) redisTemplate.opsForValue().get(CACHE_KEY_SEARCH + keyword);
        if (products == null) {
            products = productRepository.searchProducts(keyword);
            if (!products.isEmpty()) {
                redisTemplate.opsForValue().set(CACHE_KEY_SEARCH + keyword, products, 30, TimeUnit.SECONDS);
            }
        }
        return products;
    }

    @Override
    public List<Product> searchProductsByFilter(ProductFilter filter) {
        Specification<Product> spec = ProductSpecification.withFilter(filter);
        String cacheKey = buildFilterCacheKey(filter);
        List<Product> cachedProducts = (List<Product>) redisTemplate.opsForValue().get(cacheKey);
        if (cachedProducts == null) {
            List<Product> products = productRepository.findAll(spec);
            if (!products.isEmpty()) {
                redisTemplate.opsForValue().set(cacheKey, products, 30, TimeUnit.SECONDS);
            }
            return products;
        }
        return cachedProducts;
    }

    private String buildFilterCacheKey(ProductFilter filter) {
        StringBuilder cacheKeyBuilder = new StringBuilder(CACHE_KEY_PREFIX);

        if (filter.getKeyword() != null) {
            cacheKeyBuilder.append("keyword=").append(filter.getKeyword()).append("&");
        }
        if (filter.getMinPrice() != null) {
            cacheKeyBuilder.append("minPrice=").append(filter.getMinPrice()).append("&");
        }
        if (filter.getMaxPrice() != null) {
            cacheKeyBuilder.append("maxPrice=").append(filter.getMaxPrice()).append("&");
        }
        if (filter.getMinQuantity() != null) {
            cacheKeyBuilder.append("minQuantity=").append(filter.getMinQuantity()).append("&");
        }
        if (filter.getMaxQuantity() != null) {
            cacheKeyBuilder.append("maxQuantity=").append(filter.getMaxQuantity()).append("&");
        }

        return cacheKeyBuilder.toString();
    }
}
