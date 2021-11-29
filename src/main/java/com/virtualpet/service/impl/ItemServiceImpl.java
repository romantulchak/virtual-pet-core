package com.virtualpet.service.impl;

import com.virtualpet.exeption.ItemNotFoundException;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.repository.SwordRepository;
import com.virtualpet.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private SwordRepository swordRepository;

    @Autowired
    public ItemServiceImpl(SwordRepository swordRepository){
        this.swordRepository = swordRepository;
    }


    @Override
    public void createSword(Sword sword) {
        if(sword != null) {
            sword.setItemCategory(EItemCategory.SWORD);
            sword.setItemType(EItemType.WEAPON);
            swordRepository.save(sword);
        }

        throw new ItemNotFoundException();
    }

    @Override
    public void createArmor(Armor armor) {

    }
}
