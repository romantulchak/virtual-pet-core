package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.ShopDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Views;
import com.virtualpet.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/shop")
public class ShopController {

    private ShopServiceImpl shopService;

    @Autowired
    public ShopController(ShopServiceImpl shopService){
        this.shopService = shopService;
    }


    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.ShopView.class)
    public ShopDTO getShop(){
        return shopService.getShop();
    }

    @PutMapping("/addSkillToShop")
    @PreAuthorize("hasRole('ADMIN')")
    public void addSkillToShop(@RequestBody SkillAbstract skillAbstract){
        shopService.addSkillToShop(skillAbstract);
    }

    @PutMapping("/addItemToShop")
    @PreAuthorize("hasRole('ADMIN')")
    public void addItemToShop(@RequestBody Item item){
        shopService.addItemToShop(item);
    }

    @PutMapping("/removeSkillFromShop")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeSkillFromShop(@RequestBody SkillAbstract skillAbstract){
        shopService.removeSkillFromShop(skillAbstract);
    }
}

