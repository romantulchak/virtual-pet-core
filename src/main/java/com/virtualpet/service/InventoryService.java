package com.virtualpet.service;

import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Item;
import com.virtualpet.payload.request.SetItemRequest;

import java.util.List;

public interface InventoryService {

    /**
     * Get items for sub
     *
     * @param id of sub
     * @return gets list of item for sub
     */
    List<Item> getItems(long id);

    /**
     * Sets item for sub and update its characteristics
     *
     * @param setItemRequest item to be set
     * @return sub with updated characteristics
     */
    SubDTO setItem(SetItemRequest setItemRequest);

    /**
     * Takes off current weapon from sub
     *
     * @param setItemRequest to get itemId and subId
     * @return sub with updated characteristics without weapon
     */
    SubDTO withdrawWeapon(SetItemRequest setItemRequest);

    /**
     * Takes off current armor from sub
     *
     * @param setItemRequest to get itemId and subId
     * @return sub with updated characteristics without a certain part of the armor
     */
    SubDTO withdrawArmor(SetItemRequest setItemRequest);

    /**
     * Sell selected item from current sub
     *
     * @param item to be sold
     * @param subId to check if current sub has this item in inventory
     * @return sub with updated characteristics
     */
    SubDTO sellItem(Item item, long subId);

}
