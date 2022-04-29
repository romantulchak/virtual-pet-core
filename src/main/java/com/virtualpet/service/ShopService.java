package com.virtualpet.service;

import com.virtualpet.dto.ShopDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.skills.SkillAbstract;
import com.virtualpet.model.Sub;

public interface ShopService {

    @Deprecated(forRemoval = true)
    Shop getShop();

    @Deprecated(forRemoval = true)
    ShopDTO getShopForSub(Sub sub);

    /**
     * Adds skill to shop for ability to buy it
     *
     * @param skillAbstract to be added to shop
     */
    void addSkillToShop(SkillAbstract skillAbstract);

    /**
     * Adds item to shop for ability to buy it
     *
     * @param item to be added to buy
     */
    void addItemToShop(Item item);

    /**
     * Removes skill from shop
     *
     * @param skillAbstract to be removed from shop
     */
    void removeSkillFromShop(SkillAbstract skillAbstract);

    /**
     * Prepare ability to buy item for sub
     *
     * @param item to be bought
     * @param subId to find sub in system
     */
    void buyItem(Item item, long subId);

    /**
     * Prepare ability to buy skill for sub
     *
     * @param id of skill to be bought
     * @param subId to find sub in system
     */
    void buySkill(long id, long subId);
}
