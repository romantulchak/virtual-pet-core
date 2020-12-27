package com.virtualpet.service;

import com.virtualpet.model.Items.Armor;
import com.virtualpet.model.Items.Sword;
import org.springframework.http.ResponseEntity;

public interface ItemService {

    ResponseEntity<?> createSword(Sword sword);
    ResponseEntity<?> createArmor(Armor armor);


}
