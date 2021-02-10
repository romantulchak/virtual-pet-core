package com.virtualpet.service;

import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Item;
import com.virtualpet.model.Sub;
import com.virtualpet.payload.request.SetItemRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {

    List<Item> getItems(Sub sub);
    SubDTO setItem(SetItemRequest setItemRequest);
    List<Item> getDressedItems(List<Long> itemId);

    SubDTO withdrawWeapon(SetItemRequest setItemRequest);
    SubDTO withdrawArmor(SetItemRequest setItemRequest);

    SubDTO sellItem(Item item, long subId);

}
