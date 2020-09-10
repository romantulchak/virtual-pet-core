package com.virtualpet.services.impl;

import com.virtualpet.models.Inventory;
import com.virtualpet.models.Sub;
import com.virtualpet.payload.request.SubRequest;
import com.virtualpet.repos.InventoryRepository;
import com.virtualpet.repos.SubRepository;
import com.virtualpet.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private SubRepository subRepository;
    private InventoryRepository inventoryRepository;
    @Autowired
    public ProfileServiceImpl(SubRepository subRepository, InventoryRepository inventoryRepository){
        this.subRepository = subRepository;
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public ResponseEntity<?> createSub(SubRequest subRequest) {
        if(subRequest != null){
            Inventory inventory = new Inventory();
            inventoryRepository.save(inventory);
            subRepository.save(new Sub(subRequest.getName(), 15, inventory, 10, subRequest.getUser()));
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad", HttpStatus.BAD_GATEWAY);
    }
}
