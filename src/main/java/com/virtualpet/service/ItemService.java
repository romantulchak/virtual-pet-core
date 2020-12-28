package com.virtualpet.service;

import com.virtualpet.model.Items.Armor;
import com.virtualpet.model.Items.Sword;
import org.springframework.http.ResponseEntity;

public interface ItemService {

    void createSword(Sword sword);
    void  createArmor(Armor armor);


}
