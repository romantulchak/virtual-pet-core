package com.virtualpet.services.impl;

import com.virtualpet.models.Inventory;
import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;
import com.virtualpet.repos.InventoryRepository;
import com.virtualpet.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public List<Sword> getSwords(Sub sub) {
        if(sub != null){
            if(sub.getInventory() != null){
                Inventory inventory = inventoryRepository.findById(sub.getInventory().getId()).orElse(null);
                assert inventory != null;
                return inventory.getSwords();
            }
        }

        return null;
    }
}
