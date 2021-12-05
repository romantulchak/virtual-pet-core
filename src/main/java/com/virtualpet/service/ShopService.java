package com.virtualpet.service;

import com.virtualpet.dto.ShopDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;

public interface ShopService {
    ShopDTO getShopForSub(Sub sub);
    void addSkillToShop(SkillAbstract skillAbstract);
    void addItemToShop(Item item);
    void removeSkillFromShop(SkillAbstract skillAbstract);
    void buyItem(Item item, long subId);
    SubDTO buySkill(SkillAbstract skillAbstract, long subId);
}
