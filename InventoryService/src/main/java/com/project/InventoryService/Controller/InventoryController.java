package com.project.InventoryService.Controller;

import com.project.InventoryService.DTO.InventoryDTO;
import com.project.InventoryService.Entity.Inventory;
import com.project.InventoryService.Exception.NoItemsAvailableException;
import com.project.InventoryService.Service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllItems() {
        List<Inventory> items = inventoryService.getAllInventory();
        if (items.isEmpty()) {
            throw new NoItemsAvailableException("There are no items currently available in the inventory.");
        }
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Inventory> addItemInInventory(@RequestBody @Valid InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryService.addItemInInventory(inventoryDTO);
        return new ResponseEntity<>(inventory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getItemById(@PathVariable Long id) {
        Optional<Inventory> item = inventoryService.getItemById(id);
        if (item.isEmpty()) {
            throw new NoItemsAvailableException("There are no items currently available with ID " + id + " in the inventory.");
        }
        return new ResponseEntity<>(item.get(), HttpStatus.OK);
    }

//    @GetMapping("/{id}/quantity")
//    public boolean getItemQuantity( @PathVariable("id") Long itemId){
//        return inventoryService.getItemByQuantity(itemId) > 0;
//    }

}
