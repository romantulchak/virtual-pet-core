package com.virtualpet.service;

import com.virtualpet.model.Item;
import com.virtualpet.model.Sub;
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
