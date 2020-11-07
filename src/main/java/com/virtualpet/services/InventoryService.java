package com.virtualpet.services;

import com.virtualpet.models.Item;
import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;
import com.virtualpet.payload.request.SetItemRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {

    List<Item> getItems(Sub sub);
    ResponseEntity<?> setItem(SetItemRequest setItemRequest);
    List<Item> getDressedItems(List<Long> itemId);

    ResponseEntity<?> withdrawWeapon(SetItemRequest setItemRequest);
    ResponseEntity<?> withdrawArmor(SetItemRequest setItemRequest);


}
