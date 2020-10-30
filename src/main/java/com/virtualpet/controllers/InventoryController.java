package com.virtualpet.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;
import com.virtualpet.models.Views;
import com.virtualpet.services.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Sword> getItems(@PathVariable("subId") Sub sub){
        return inventoryService.getSwords(sub);
    }

}
