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
import com.virtualpet.repository.ItemRepository;
import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SubRepository subRepository;
    private final ItemRepository itemRepository;

    @Override
    public SubDTO setItem(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(() -> new SubNotFoundException(setItemRequest.getSubId()));
        switch (setItemRequest.getItemType()) {
            case ARMOR:
                Armor armor = (Armor) sub.getInventory().getItems()
                        .stream()
                        .filter(item -> item.getId() == setItemRequest.getItemId())
                        .findFirst()
                        .orElseThrow(ItemNotFoundException::new);
                setArmor(sub, armor, armor.getItemCategory());
                break;
            case WEAPON:
                Sword weapon = (Sword) sub.getInventory().getItems()
                        .stream()
                        .filter(item -> item.getId() == setItemRequest.getItemId())
                        .findFirst().orElseThrow(ItemNotFoundException::new);
                setWeapon(sub, weapon);
                break;
        }
        return new SubDTO(sub);

    }

    private void setWeapon(Sub sub, Sword sword) {
        checkDressedWeapon(sub);
        sub.getDressedItems().setSword(sword);
        sub.setAttack(sub.getAttack() + sword.getAttack());
        sword.setHasDressed(true);
        subRepository.save(sub);
    }

    private void checkDressedWeapon(Sub sub) {
        if (sub.getDressedItems().getSword() != null) {
            sub.setAttack(sub.getAttack() - sub.getDressedItems().getSword().getAttack());
            sub.getDressedItems().setSword(null);
        }
    }

    private void armorIsExist(Sub sub, Armor armor, EItemCategory eItemCategory) {


    }

    private void setArmor(Sub sub, Armor armor, EItemCategory itemCategory) {
        Armor currentArmor = null;
        switch (itemCategory) {
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
        if (currentArmor != null) {
            sub.setHealth(sub.getHealth() - currentArmor.getHealth());
            sub.setDefence(sub.getDefence() - currentArmor.getArmor());
        }
        armor.setHasDressed(true);
        sub.setHealth(sub.getHealth() + armor.getHealth());
        sub.setDefence(sub.getDefence() + armor.getArmor());
        subRepository.save(sub);

    }


    @Override
    public SubDTO withdrawWeapon(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(() -> new SubNotFoundException(setItemRequest.getSubId()));
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
        if (armor != null) {
            armor.setHasDressed(false);
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
        if (armor != null) {
            sub.setDefence(sub.getDefence() - armor.getArmor());
            sub.setHealth(sub.getHealth() - armor.getHealth());
        }
        return armor;
    }

    @Override
    public List<Item> getItems(long id) {
        return itemRepository.findAllByInventorySubIdAndHasDressed(id, false);
    }

    @Override
    public SubDTO sellItem(Item item, long subId) {
        if (item != null) {
            Sub sub = subRepository.findById(subId).orElseThrow(SubNotFoundException::new);
            Inventory inventory = inventoryRepository.findById(subId).orElseThrow(() -> new InventoryNotFoundException(sub.getName()));
            switch (item.getItemType()) {
                case WEAPON:
                    checkDressedWeapon(sub);
                    getMoneyForSell(item, sub);
                    inventory.getItems().remove(item);
                    break;
                case ARMOR:
                    getArmor(item.getItemCategory(), sub);
                    getMoneyForSell(item, sub);
                    inventory.getItems().remove(item);
                    break;
            }
            inventoryRepository.save(inventory);
            subRepository.save(sub);
            return new SubDTO(sub);

        } else {
            throw new ItemNotFoundException();
        }
    }

    private void getMoneyForSell(Item item, Sub sub) {
        sub.getCurrency().setMoney(Math.round(sub.getCurrency().getMoney() + (item.getPrice() * 0.85)));
    }

    @Override
    public List<Item> getDressedItems(List<Long> itemId) {
        return null;
    }
}
