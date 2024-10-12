package com.eCommerce.InventoryService.Service;

import com.eCommerce.basedomains.DTO.InventoryDTO;
import com.eCommerce.basedomains.DTO.ProductDTO;
import com.eCommerce.InventoryService.Entity.Inventory;
import com.eCommerce.InventoryService.Entity.Product;
import com.eCommerce.InventoryService.Exception.NoItemsAvailableException;
import com.eCommerce.InventoryService.Repository.InventoryRepository;
import com.eCommerce.InventoryService.Repository.ProductRepository;
import com.eCommerce.basedomains.Event.OrderFinalizedEvent;
import com.eCommerce.basedomains.DTO.OrderProduct;
import com.eCommerce.basedomains.DTO.OrderReq;
import com.eCommerce.basedomains.Event.StockDeductedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private static final String STOCK_DEDUCTED_SUCCESS_TOPIC = "stock-deducted-topic-success";
    private static final String STOCK_DEDUCTED_FAILURE_TOPIC = "stock-deducted-topic-failure";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public InventoryServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

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


//    @KafkaListener(topics = "order-event", groupId = "inventory_group")
//    public void consumeOrderRequest(OrderReq orderReq) {
//        List<OrderProduct> finalizedOrderProducts = new ArrayList<>();
//        logger.info("Received order request: {}", orderReq);
//
//        for (OrderProduct product : orderReq.getItems()) {
//            Optional<Product> existingProduct = productRepository.findById(product.getId());
//
//            if (existingProduct.isPresent() && existingProduct.get().getQuantity() >= product.getQuantity()) {
//                Product updatedProduct = existingProduct.get();
//                updatedProduct.setQuantity(updatedProduct.getQuantity() - product.getQuantity());
//                productRepository.save(updatedProduct);
//                finalizedOrderProducts.add(product);
//                logger.info("Product updated: {}, new quantity: {}", updatedProduct.getId(), updatedProduct.getQuantity());
//            } else {
//                logger.warn("Product not available or insufficient quantity for product ID: {}", product.getId());
//            }
//        }
//
//        // Check if there are finalized products and send appropriate events
//        if (finalizedOrderProducts.isEmpty()) {
//            logger.info("No products finalized. Sending failure event.");
//            kafkaTemplate.send(STOCK_DEDUCTED_FAILURE_TOPIC, new StockDeductedEvent(false, orderReq));
//        } else {
//            // Produce the OrderFinalizedEvent with finalized products
//            OrderFinalizedEvent orderFinalizedEvent = new OrderFinalizedEvent(true, orderReq, finalizedOrderProducts);
//            logger.info("Finalized order products: {}", finalizedOrderProducts);
//            kafkaTemplate.send(STOCK_DEDUCTED_SUCCESS_TOPIC, new StockDeductedEvent(true, orderReq));
//            kafkaTemplate.send("finalized-order-topic", orderFinalizedEvent); // Send to your finalized order topic
//            logger.info("Sent finalized order event to topic 'finalized-order-topic'");
//        }
//    }

    @KafkaListener(topics = "order-event", groupId = "inventory_group")
    public void consumeOrderRequest(OrderReq orderReq) {
        List<OrderProduct> finalizedOrderProducts = new ArrayList<>();
        logger.info("Received order request: {}", orderReq);

        try {
            for (OrderProduct product : orderReq.getItems()) {
                Optional<Product> existingProduct = productRepository.findById(product.getId());

                if (existingProduct.isPresent() && existingProduct.get().getQuantity() >= product.getQuantity()) {
                    Product updatedProduct = existingProduct.get();
                    updatedProduct.setQuantity(updatedProduct.getQuantity() - product.getQuantity());
                    productRepository.save(updatedProduct);
                    finalizedOrderProducts.add(product);
                    logger.info("Product updated: {}, new quantity: {}", updatedProduct.getId(), updatedProduct.getQuantity());
                } else {
                    logger.warn("Product not available or insufficient quantity for product ID: {}", product.getId());
                    throw new NoItemsAvailableException("Insufficient stock for product ID: " + product.getId());
                }
            }

            kafkaTemplate.send(STOCK_DEDUCTED_SUCCESS_TOPIC, new StockDeductedEvent(true, orderReq));

        } catch (Exception e) {
            logger.error("Stock deduction failed for orderReq: {}. Rolling back.", orderReq, e);
            rollbackStock(finalizedOrderProducts);
            kafkaTemplate.send(STOCK_DEDUCTED_FAILURE_TOPIC, new StockDeductedEvent(false, orderReq));
        }
    }

    private void rollbackStock(List<OrderProduct> finalizedOrderProducts) {
        for (OrderProduct product : finalizedOrderProducts) {
            Optional<Product> existingProduct = productRepository.findById(product.getId());
            if (existingProduct.isPresent()) {
                Product updatedProduct = existingProduct.get();
                updatedProduct.setQuantity(updatedProduct.getQuantity() + product.getQuantity());
                productRepository.save(updatedProduct);
                logger.info("Rolled back product: {}, restored quantity: {}", updatedProduct.getId(), updatedProduct.getQuantity());
            }
        }
    }



}