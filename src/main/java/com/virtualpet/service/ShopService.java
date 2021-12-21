package com.virtualpet.service;

import com.virtualpet.dto.ShopDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.skills.SkillAbstract;
import com.virtualpet.model.Sub;

public interface ShopService {
    Shop getShop();
    ShopDTO getShopForSub(Sub sub);
    void addSkillToShop(SkillAbstract skillAbstract);
    void addItemToShop(Item item);
    void removeSkillFromShop(SkillAbstract skillAbstract);
    void buyItem(Item item, long subId);
    void buySkill(long id, long subId);
}
