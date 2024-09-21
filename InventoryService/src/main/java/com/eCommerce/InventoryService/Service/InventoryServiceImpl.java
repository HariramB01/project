package com.eCommerce.InventoryService.Service;

import com.eCommerce.InventoryService.DTO.InventoryDTO;
import com.eCommerce.InventoryService.DTO.ProductDTO;
import com.eCommerce.InventoryService.Entity.Inventory;
import com.eCommerce.InventoryService.Entity.Product;
import com.eCommerce.InventoryService.Exception.NoItemsAvailableException;
import com.eCommerce.InventoryService.Repository.InventoryRepository;
import com.eCommerce.InventoryService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @CacheEvict(value = "inventories", allEntries = true)
    public Inventory createOrUpdateInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setId(inventoryDTO.getId());
        inventory.setInventoryLocation(inventoryDTO.getInventoryLocation());
        inventory.setTotalItems(inventoryDTO.getTotalItems());

        List<Product> productList = new ArrayList<>();
        for (ProductDTO productDto : inventoryDTO.getProducts()) {
            Product product = new Product();
            product.setProductName(productDto.getProductName());
            product.setQuantity(productDto.getQuantity());
            product.setPrice(productDto.getPrice());
            product.setAvailabilityStatus(productDto.getAvailabilityStatus());
            product.setCategory(productDto.getCategory());
            product.setInventory(inventory); // Associate product with inventory
            productList.add(product);
        }
        inventory.setProducts(productList);

        return inventoryRepository.save(inventory);
    }



    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new NoItemsAvailableException("Inventory not found"));
    }

    @Override
    @CacheEvict(value = "inventories", key = "#id")
    public void deleteInventoryById(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "inventories", allEntries = true)
    public Inventory updateInventory(Long id, InventoryDTO inventoryDTO) {
        Inventory existingInventory = getInventoryById(id);

        existingInventory.setInventoryLocation(inventoryDTO.getInventoryLocation());
        for (ProductDTO productDto : inventoryDTO.getProducts()) {
            if (productDto.getId() != null) {
                Product existingProduct = productRepository.findById(productDto.getId())
                        .orElseThrow(() -> new NoItemsAvailableException("Product not found"));
                // Update existing product
                existingProduct.setProductName(productDto.getProductName());
                existingProduct.setQuantity(productDto.getQuantity());
                existingProduct.setPrice(productDto.getPrice());
                existingProduct.setAvailabilityStatus(productDto.getAvailabilityStatus());
                existingProduct.setCategory(productDto.getCategory());
                productRepository.save(existingProduct);
            } else {
                // Create new product
                Product newProduct = new Product();
                newProduct.setProductName(productDto.getProductName());
                newProduct.setQuantity(productDto.getQuantity());
                newProduct.setPrice(productDto.getPrice());
                newProduct.setAvailabilityStatus(productDto.getAvailabilityStatus());
                newProduct.setCategory(productDto.getCategory());
                newProduct.setInventory(existingInventory);
                productRepository.save(newProduct);
            }
        }
        return inventoryRepository.save(existingInventory);
    }
}
