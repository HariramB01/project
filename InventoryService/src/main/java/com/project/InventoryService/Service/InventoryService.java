package com.project.InventoryService.Service;

import com.project.InventoryService.DTO.InventoryDTO;
import com.project.InventoryService.Entity.Inventory;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    public List<Inventory> getAllInventory();

    public Inventory  addItemInInventory(@Valid InventoryDTO inventoryDTO);

    Optional<Inventory> getItemById(Long id);

//    int getItemByQuantity(Long itemId);
}
