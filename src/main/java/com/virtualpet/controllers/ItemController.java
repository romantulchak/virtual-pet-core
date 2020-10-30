package com.virtualpet.controllers;

import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;
import com.virtualpet.services.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("api/items")
public class ItemController {

    private ItemServiceImpl itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemService){
        this.itemService = itemService;
    }

    @PostMapping("/createSword")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSword(@RequestBody Sword sword){
        return itemService.createSword(sword);
    }
}
