package com.virtualpet.services;

import com.virtualpet.models.Items.Armor;
import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;
import org.springframework.http.ResponseEntity;

public interface ItemService {

    ResponseEntity<?> createSword(Sword sword);
    ResponseEntity<?> createArmor(Armor armor);


}
