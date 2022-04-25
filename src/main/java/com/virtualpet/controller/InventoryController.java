package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.Views;
import com.virtualpet.payload.request.SetItemRequest;
import com.virtualpet.service.impl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(value = "*", maxAge = 3600L)
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @GetMapping("/getItems/{id}")
    @PreAuthorize("isAuthenticated() && @subAccess.hasAccess(#id, authentication)")
    @JsonView(Views.InventoryView.class)
    public List<Item> getItems(@PathVariable("id") long id){
        return inventoryService.getItems(id);
    }


    @PutMapping("/setItem")
    @JsonView(Views.SubView.class)
    public SubDTO setItem(@RequestBody SetItemRequest setItemRequest){
       return inventoryService.setItem(setItemRequest);
    }
    @PutMapping("/withdrawArmor")
    @JsonView(Views.SubView.class)
    public SubDTO withdrawArmor(@RequestBody SetItemRequest setItemRequest){
        return inventoryService.withdrawArmor(setItemRequest);
    }
    @PutMapping("/withdrawWeapon")
    @JsonView(Views.SubView.class)
    public SubDTO withdrawWeapon(@RequestBody SetItemRequest setItemRequest){
        return inventoryService.withdrawWeapon(setItemRequest);
    }
    @PostMapping("/sellItem/{subId}")
    @JsonView(Views.SubView.class)
    public SubDTO sellItem(@RequestBody Item item, @PathVariable("subId") long subId){
       return inventoryService.sellItem(item, subId);
    }
}
