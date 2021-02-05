package com.virtualpet.service;

import com.virtualpet.dto.ShopDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {
    ShopDTO getShop();
    void addSkillToShop(SkillAbstract skillAbstract);
    void addItemToShop(Item item);
    void removeSkillFromShop(SkillAbstract skillAbstract);
}
