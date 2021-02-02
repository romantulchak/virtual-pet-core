package com.virtualpet.service;

import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;

public interface ShopService {
    Shop getShop();
    void addSkillToShop(SkillAbstract skillAbstract);
    void addItemToShop(Item item);
}
