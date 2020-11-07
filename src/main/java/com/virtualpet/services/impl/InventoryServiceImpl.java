package com.virtualpet.services.impl;

import com.virtualpet.dtos.SubDTO;
import com.virtualpet.models.DressedItem;
import com.virtualpet.models.Enums.EItemCategory;
import com.virtualpet.models.Item;
import com.virtualpet.models.Items.Armor;
import com.virtualpet.models.Items.Sword;
import com.virtualpet.models.Sub;
import com.virtualpet.payload.request.SetItemRequest;
import com.virtualpet.payload.response.SubResponse;
import com.virtualpet.repos.InventoryRepository;
import com.virtualpet.repos.SubRepository;
import com.virtualpet.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;
    private SubRepository subRepository;
    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, SubRepository subRepository){
        this.inventoryRepository = inventoryRepository;
        this.subRepository = subRepository;

    }
    @Override
    public ResponseEntity<?> setItem(SetItemRequest setItemRequest){
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElse(null);
        if (sub != null){

            switch (setItemRequest.getItemType()){
                case ARMOR:
                    Armor armor = sub.getInventory().getArmors().stream().filter(x->x.getId() == setItemRequest.getItemId()).findFirst().orElse(null);
                    System.out.println(armor.getHealth());
                    setArmor(sub, armor, armor.geteItemCategory());
                    break;
                case WEAPON:
                    Sword weapon = sub.getInventory().getSwords().stream().filter(x->x.getId() == setItemRequest.getItemId()).findFirst().orElse(null);
                    setWeapon(sub, weapon);
                    break;
                default:
                    return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub),"Something wrong" ,HttpStatus.OK), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub),"Ok" ,HttpStatus.OK), HttpStatus.OK);

        }
        return null;
    }

    private void setWeapon(Sub sub, Sword sword){
        if(sub.getDressedItems().getSword() != null){
            sub.setAttack(sub.getAttack() - sub.getDressedItems().getSword().getAttack());
        }
        sub.getDressedItems().setSword(sword);
        sub.setAttack(sub.getAttack() + sword.getAttack());
        subRepository.save(sub);
    }

    private void armorIsExist(Sub sub, Armor armor, EItemCategory eItemCategory){


    }

    private void setArmor(Sub sub, Armor armor, EItemCategory eItemCategory){
        Armor currentArmor = null;
        switch (eItemCategory){
            case BODY:
                currentArmor = sub.getDressedItems().getBody();
                sub.getDressedItems().setBody(armor);
                break;
            case LEGS:
                currentArmor = sub.getDressedItems().getLegs();
                sub.getDressedItems().setLegs(armor);
                break;
            case HANDS:
                currentArmor = sub.getDressedItems().getHands();
                sub.getDressedItems().setHands(armor);
                break;
            case SHIELD:
                currentArmor = sub.getDressedItems().getShield();
                sub.getDressedItems().setShield(armor);
                break;
            case SHOULDERS:
                currentArmor = sub.getDressedItems().getShoulders();
                sub.getDressedItems().setShoulders(armor);
                break;
            case HEAD:
                currentArmor = sub.getDressedItems().getHead();
                sub.getDressedItems().setHead(armor);
                break;
        }
        if(currentArmor != null){
            sub.setHealth(sub.getHealth() - currentArmor.getHealth());
            sub.setDefence(sub.getDefence() - currentArmor.getArmor());
        }
        sub.setHealth(sub.getHealth() + armor.getHealth());
        sub.setDefence(sub.getDefence() + armor.getArmor());
        subRepository.save(sub);

    }


    @Override
    public ResponseEntity<?> withdrawWeapon(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElse(null);
        if(sub != null){
            Sword sword = sub.getDressedItems().getSword();
            sub.getDressedItems().setSword(null);
            sub.setAttack(sub.getAttack() - sword.getAttack());
            subRepository.save(sub);
            return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "Item has been removed", HttpStatus.OK), HttpStatus.OK);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> withdrawArmor(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElse(null);
        if(sub != null) {
            Armor armor = null;
            switch (setItemRequest.getBodyPosition()) {
                case SHOULDERS:
                    armor = sub.getDressedItems().getShoulders();
                    sub.getDressedItems().setShoulders(null);
                    break;
                case SHIELD:
                    armor = sub.getDressedItems().getShield();
                    sub.getDressedItems().setShield(null);
                    break;
                case HANDS:
                    armor = sub.getDressedItems().getHands();
                    sub.getDressedItems().setHands(null);
                    break;
                case LEGS:
                    armor = sub.getDressedItems().getLegs();
                    sub.getDressedItems().setLegs(null);
                    break;
                case BODY:
                    armor = sub.getDressedItems().getBody();
                    sub.getDressedItems().setBody(null);
                    break;
                case HEAD:
                    armor = sub.getDressedItems().getHead();
                    sub.getDressedItems().setHead(null);
                    break;
            }
            if (armor != null){
                sub.setDefence(sub.getDefence() - armor.getArmor());
                sub.setHealth(sub.getHealth() - armor.getHealth());
                subRepository.save(sub);
                return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "Item has been removed", HttpStatus.OK), HttpStatus.OK);
            }
            return new ResponseEntity<>(new SubResponse<SubDTO>(new SubDTO(sub), "Item didn't remove", HttpStatus.OK), HttpStatus.BAD_REQUEST);

        }
        return null;
    }

    @Override
    public List<Item> getItems(Sub sub) {
        if(sub != null){
            List<Armor> armors = new ArrayList<Armor>(){{
                add(sub.getDressedItems().getBody());
                add(sub.getDressedItems().getHands());
                add(sub.getDressedItems().getHead());
                add(sub.getDressedItems().getLegs());
                add(sub.getDressedItems().getShield());
                add(sub.getDressedItems().getShoulders());
            }};


            if(armors != null){
                sub.getInventory().getArmors().removeAll(armors);
            }
            if(sub.getDressedItems().getSword() != null){
                sub.getInventory().getSwords().remove(sub.getDressedItems().getSword());
            }

            return new ArrayList<Item>(){{
                addAll(sub.getInventory().getArmors());
                addAll(sub.getInventory().getSwords());
             }};
        }
        return null;
    }

    @Override
    public List<Item> getDressedItems(List<Long> itemId){
        return null;
    }
}
