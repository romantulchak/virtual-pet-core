package com.virtualpet.controller;

import com.virtualpet.model.items.Sword;
import com.virtualpet.service.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemServiceImpl itemService;

    @PostMapping("/createSword")
    @PreAuthorize("hasRole('ADMIN')")
    public void createSword(@RequestBody Sword sword){
        itemService.createSword(sword);
    }
}
