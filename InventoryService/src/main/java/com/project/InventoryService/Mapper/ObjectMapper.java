package com.project.InventoryService.Mapper;

import com.project.InventoryService.DTO.InventoryDTO;
import com.project.InventoryService.Entity.Inventory;

public class ObjectMapper {

    public static Inventory convertToDTO(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setItemName(inventoryDTO.getItemName());
        inventory.setPrice(inventoryDTO.getPrice());
        inventory.setCategory(inventoryDTO.getCategory());
        inventory.setMadeInLocation(inventoryDTO.getMadeInLocation());
        inventory.setImportedDate(inventoryDTO.getImportedDate());
        inventory.setManufacturedDate(inventoryDTO.getManufacturedDate());
//        inventory.setItemCreatedDate(inventoryDTO.getItemCreatedDate()); // Ensure that itemCreatedDate is handled
        inventory.setTotalPieces(inventoryDTO.getTotalPieces());
        return inventory;
    }
}
