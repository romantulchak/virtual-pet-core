package com.virtualpet.service;

import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;

public interface ItemService {

    /**
     * Create sword (weapon) with the specified characteristics
     * @param sword to be created
     */
    void createSword(Sword sword);

    /**
     * Create armor with the specified characteristics
     * @param armor to be created
     */
    void  createArmor(Armor armor);
}
