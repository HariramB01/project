package com.eCommerce.InventoryService.Service;

import com.eCommerce.InventoryService.DTO.InventoryDTO;
import com.eCommerce.InventoryService.Entity.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory createOrUpdateInventory(InventoryDTO inventoryDTO);

    List<Inventory> getAllInventory();

    Inventory getInventoryById(Long id);

    void deleteInventoryById(Long id);

    Inventory updateInventory(Long id, InventoryDTO updatedInventory);
}
