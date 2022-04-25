package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.ShopDTO;
import com.virtualpet.model.*;
import com.virtualpet.model.skills.SkillAbstract;
import com.virtualpet.service.impl.ShopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopServiceImpl shopService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @JsonView(Views.ShopView.class)
    public ShopDTO getShop(@RequestParam("subId")Sub sub){
        return shopService.getShopForSub(sub);
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
    @PreAuthorize("isAuthenticated()")
    public void buyItem(@RequestBody Item item, @PathVariable("subId") long subId){
        shopService.buyItem(item, subId);
    }

    @PostMapping("/buySkill/{subId}")
    @PreAuthorize("isAuthenticated()")
    public void buySkill(@RequestBody long id, @PathVariable("subId") long subId){
       shopService.buySkill(id, subId);
    }
}

