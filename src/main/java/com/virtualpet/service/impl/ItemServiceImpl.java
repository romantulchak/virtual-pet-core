package com.virtualpet.service.impl;

import com.virtualpet.exeption.ItemNotFoundException;
import com.virtualpet.model.Enums.EItemCategory;
import com.virtualpet.model.Enums.EItemType;
import com.virtualpet.model.Items.Armor;
import com.virtualpet.model.Items.Sword;
import com.virtualpet.payload.response.MessageResponse;
import com.virtualpet.repository.SwordRepository;
import com.virtualpet.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            sword.seteItemCategory(EItemCategory.SWORD);
            sword.seteItemType(EItemType.WEAPON);
            swordRepository.save(sword);
        }

        throw new ItemNotFoundException();
    }

    @Override
    public void createArmor(Armor armor) {

    }
}
