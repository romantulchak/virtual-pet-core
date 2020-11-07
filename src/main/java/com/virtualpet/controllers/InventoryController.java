package com.virtualpet.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.models.Item;
import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;
import com.virtualpet.models.Views;
import com.virtualpet.payload.request.SetItemRequest;
import com.virtualpet.services.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(value = "*", maxAge = 3600L)
public class InventoryController {

    private InventoryServiceImpl inventoryService;

    @Autowired
    public InventoryController(InventoryServiceImpl inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping("/getItems/{subId}")
    @JsonView(Views.InventoryView.class)
    public List<Item> getItems(@PathVariable("subId") Sub sub){
        return inventoryService.getItems(sub);
    }


    @PutMapping("/setItem")
    @JsonView(Views.SubView.class)
    public ResponseEntity<?> setItem(@RequestBody SetItemRequest setItemRequest){
       return inventoryService.setItem(setItemRequest);
    }
    @PutMapping("/withdrawArmor")
    @JsonView(Views.SubView.class)
    public ResponseEntity<?> withdrawArmor(@RequestBody SetItemRequest setItemRequest){
        return inventoryService.withdrawArmor(setItemRequest);
    }
    @PutMapping("/withdrawWeapon")
    @JsonView(Views.SubView.class)
    public ResponseEntity<?> withdrawWeapon(@RequestBody SetItemRequest setItemRequest){
        return inventoryService.withdrawWeapon(setItemRequest);
    }
}
