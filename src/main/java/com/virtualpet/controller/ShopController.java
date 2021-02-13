package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.ShopDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.*;
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
    public ShopDTO getShop(@RequestParam("subId")Sub sub){
        return shopService.getShop(sub);
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

    @PostMapping("/buyItem/{subId}")
    @PreAuthorize("hasRole('USER')")
    public void buyItem(@RequestBody Item item, @PathVariable("subId") long subId){
        shopService.buyItem(item, subId);
    }

    @PostMapping("/buySkill/{subId}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.SubView.class)
    public SubDTO buySkill(@RequestBody SkillAbstract skillAbstract, @PathVariable("subId") long subId){
       return shopService.buySkill(skillAbstract, subId);
    }
}

