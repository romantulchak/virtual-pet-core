package com.virtualpet.service.impl;

import com.virtualpet.exeption.item.ItemNotFoundException;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.repository.SwordRepository;
import com.virtualpet.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final SwordRepository swordRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createSword(Sword sword) {
        if(sword != null) {
            sword.setItemCategory(EItemCategory.SWORD);
            sword.setItemType(EItemType.WEAPON);
            swordRepository.save(sword);
        }
        throw new ItemNotFoundException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createArmor(Armor armor) {
        //to be implemented
    }
}
