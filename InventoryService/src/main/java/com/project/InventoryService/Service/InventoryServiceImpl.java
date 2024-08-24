package com.project.InventoryService.Service;

import com.project.InventoryService.DTO.InventoryDTO;
import com.project.InventoryService.Entity.Inventory;
import com.project.InventoryService.Exception.DuplicateValueException;
import com.project.InventoryService.Exception.NoItemsAvailableException;
import com.project.InventoryService.Mapper.ObjectMapper;
import com.project.InventoryService.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        if(inventories.isEmpty()){
            throw new NoItemsAvailableException("Currently there are no items available in the inventory.");
        }
        for(Inventory inventory : inventories){
            System.out.println(inventory);
        }
        return inventories;
    }

    @Override
    public Inventory addItemInInventory(InventoryDTO inventoryDTO) {
        if (inventoryRepository.findByCategoryAndItemName(inventoryDTO.getCategory(), inventoryDTO.getItemName()).isPresent()) {
            throw new DuplicateValueException("Item with name '" + inventoryDTO.getItemName() + "' and category '" + inventoryDTO.getCategory() + "' already exists in the inventory.");
        }

        Inventory inventory = ObjectMapper.convertToDTO(inventoryDTO);
        inventory.setItemCreatedDate(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    @Override
    public Optional<Inventory> getItemById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if(inventory.isEmpty()){
            throw new NoItemsAvailableException("Currently there are no items available for this Id "+id +" in the inventory.");
        }
        return inventory;
    }

//    @Override
//    public int getItemByQuantity(Long itemId) {
//        return inventoryRepository.findByQuantity(itemId);
//    }

}
