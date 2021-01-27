package com.virtualpet.controller;

import com.virtualpet.model.items.Sword;
import com.virtualpet.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createSword(@RequestBody Sword sword){
        itemService.createSword(sword);
    }
}
