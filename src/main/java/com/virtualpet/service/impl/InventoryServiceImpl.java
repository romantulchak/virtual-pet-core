package com.virtualpet.service.impl;

import com.virtualpet.dto.SubDTO;
import com.virtualpet.exeption.InventoryNotFoundException;
import com.virtualpet.exeption.ItemNotFoundException;
import com.virtualpet.exeption.SubNotFoundException;
import com.virtualpet.model.Inventory;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.Item;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.Sub;
import com.virtualpet.payload.request.SetItemRequest;
import com.virtualpet.repository.InventoryRepository;
import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public SubDTO setItem(SetItemRequest setItemRequest){
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(()-> new SubNotFoundException(setItemRequest.getSubId()));
            switch (setItemRequest.getItemType()){
                case ARMOR:
                    Armor armor = sub.getInventory().getArmors().stream().filter(x->x.getId() == setItemRequest.getItemId()).findFirst().orElseThrow(ItemNotFoundException::new);
                    setArmor(sub, armor, armor.geteItemCategory());
                    break;
                case WEAPON:
                    Sword weapon = sub.getInventory().getSwords().stream().filter(x->x.getId() == setItemRequest.getItemId()).findFirst().orElseThrow(ItemNotFoundException::new);
                    setWeapon(sub, weapon);
                    break;
            }
            return new SubDTO(sub);

    }

    private void setWeapon(Sub sub, Sword sword){
        checkDressedWeapon(sub);
        sub.getDressedItems().setSword(sword);
        sub.setAttack(sub.getAttack() + sword.getAttack());
        subRepository.save(sub);
    }

    private void checkDressedWeapon(Sub sub) {
        if(sub.getDressedItems().getSword() != null){
            sub.setAttack(sub.getAttack() - sub.getDressedItems().getSword().getAttack());
            sub.getDressedItems().setSword(null);
        }
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
    public SubDTO withdrawWeapon(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(()-> new SubNotFoundException(setItemRequest.getSubId()));
        Sword sword = sub.getDressedItems().getSword();
        sub.getDressedItems().setSword(null);
        sub.setAttack(sub.getAttack() - sword.getAttack());
        subRepository.save(sub);
        return new SubDTO(sub);
    }

    @Override
    public SubDTO withdrawArmor(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(() -> new SubNotFoundException(setItemRequest.getSubId()));
        Armor armor = getArmor(setItemRequest.getBodyPosition(), sub);
        if (armor != null){
                subRepository.save(sub);
                return new SubDTO(sub);
            }
            throw new ItemNotFoundException(setItemRequest.getItemId());
    }

    private Armor getArmor(EItemCategory bodyPosition, Sub sub) {
        Armor armor = null;
        switch (bodyPosition) {
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
        if(armor !=null){
            sub.setDefence(sub.getDefence() - armor.getArmor());
            sub.setHealth(sub.getHealth() - armor.getHealth());
        }
        return armor;
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
        throw new SubNotFoundException();
    }

    @Override
    public SubDTO sellItem(Item item, long subId) {
        if(item != null) {
            Sub sub = subRepository.findById(subId).orElseThrow(SubNotFoundException::new);
            Inventory inventory = inventoryRepository.findById(subId).orElseThrow(() -> new InventoryNotFoundException(sub.getName()));
            switch (item.geteItemType()){
                case WEAPON:
                    checkDressedWeapon(sub);
                    getMoneyForSell(item, sub);
                    inventory.getSwords().remove(item);
                    break;
                case ARMOR:
                    getArmor(item.geteItemCategory(), sub);
                    getMoneyForSell(item, sub);
                    inventory.getArmors().remove(item);
                    break;
            }
            inventoryRepository.save(inventory);
            subRepository.save(sub);
            return new SubDTO(sub);

        }else {
            throw new ItemNotFoundException();
        }
    }

    private void getMoneyForSell(Item item, Sub sub) {
        sub.getCurrency().setMoney(Math.round(sub.getCurrency().getMoney() + (item.getPrice() * 0.85)));
    }
    @Override
    public List<Item> getDressedItems(List<Long> itemId){
        return null;
    }
}
