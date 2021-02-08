package com.virtualpet.service;

import com.virtualpet.dto.ShopDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {
    ShopDTO getShop(Sub sub);
    void addSkillToShop(SkillAbstract skillAbstract);
    void addItemToShop(Item item);
    void removeSkillFromShop(SkillAbstract skillAbstract);
    void buyItem(Item item, long subId);
}
