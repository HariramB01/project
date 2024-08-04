package com.project.InventoryService.Repository;

import com.project.InventoryService.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByCategoryAndItemName(String category, String itemName);

//    int findByQuantity(Long itemId);
}
