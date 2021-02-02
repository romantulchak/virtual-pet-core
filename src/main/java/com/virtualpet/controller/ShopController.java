package com.virtualpet.controller;

import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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
    public Shop getShop(){
        return shopService.getShop();
    }
        @PutMapping("/addSkillToShop")
    public void addSkillToShop(@RequestBody SkillAbstract skillAbstract){
        shopService.addSkillToShop(skillAbstract);
    }
    @PutMapping("/addItemToShop")
    public void addItemToShop(@RequestBody Item item){
        shopService.addItemToShop(item);
    }


}
