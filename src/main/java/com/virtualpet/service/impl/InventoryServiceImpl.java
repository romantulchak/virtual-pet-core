package com.virtualpet.service.impl;

import com.virtualpet.dto.SubDTO;
import com.virtualpet.exeption.item.ItemCategoryNotFoundException;
import com.virtualpet.exeption.item.ItemNotFoundException;
import com.virtualpet.exeption.sub.SubNotFoundException;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.Item;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.Sub;
import com.virtualpet.payload.request.SetItemRequest;
import com.virtualpet.repository.InventoryRepository;
import com.virtualpet.repository.ItemRepository;
import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.InventoryService;
import com.virtualpet.transformer.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SubRepository subRepository;
    private final ItemRepository itemRepository;
    private final Transformer transformer;

    /**
     * {@inheritDoc}
     */
    @Override
    public SubDTO setItem(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(() -> new SubNotFoundException(setItemRequest.getSubId()));
        if (setItemRequest.getItemType() == EItemType.ARMOR) {
            Armor armor = (Armor) sub.getInventory().getItems()
                    .stream()
                    .filter(item -> item.getId() == setItemRequest.getItemId())
                    .findFirst()
                    .orElseThrow(ItemNotFoundException::new);
            setArmor(sub, armor, armor.getItemCategory());
        } else if (setItemRequest.getItemType() == EItemType.WEAPON) {
            Sword weapon = (Sword) sub.getInventory().getItems()
                    .stream()
                    .filter(item -> item.getId() == setItemRequest.getItemId())
                    .findFirst().orElseThrow(ItemNotFoundException::new);
            setWeapon(sub, weapon);
        } else {
            throw new ItemCategoryNotFoundException();
        }
        return transformer.getSubDTO(sub);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubDTO withdrawWeapon(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(() -> new SubNotFoundException(setItemRequest.getSubId()));
        Sword sword = sub.getDressedItems().getSword();
        sub.getDressedItems().setSword(null);
        sub.setAttack(sub.getAttack() - sword.getAttack());
        subRepository.save(sub);
        return transformer.getSubDTO(sub);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubDTO withdrawArmor(SetItemRequest setItemRequest) {
        Sub sub = subRepository.findById(setItemRequest.getSubId()).orElseThrow(() -> new SubNotFoundException(setItemRequest.getSubId()));
        Armor armor = getArmor(setItemRequest.getBodyPosition(), sub);
        if (armor != null) {
            armor.setHasDressed(false);
            subRepository.save(sub);
            return transformer.getSubDTO(sub);
        }
        throw new ItemNotFoundException(setItemRequest.getItemId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getItems(long id) {
        return itemRepository.findAllByInventorySubIdAndHasDressed(id, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubDTO sellItem(Item item, long subId) {
        if (item != null) {
            Sub sub = subRepository.findById(subId).orElseThrow(SubNotFoundException::new);
            if (item.getItemType() == EItemType.WEAPON) {
                checkDressedWeapon(sub);
                getMoneyForSell(item, sub);
            } else if (item.getItemType() == EItemType.ARMOR) {
                getArmor(item.getItemCategory(), sub);
                getMoneyForSell(item, sub);
            } else {
                throw new ItemCategoryNotFoundException();
            }
            item.setInventory(null);
            itemRepository.save(item);
            subRepository.save(sub);
            return transformer.getSubDTO(sub);

        } else {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Checks whether the sub has any armor installed in a position if so then resets this armor and
     * also changes the characteristics that gives this armor to the sub
     *
     * @param bodyPosition to know which part of armor
     * @param sub to obtain the actual state of the armor on the sub
     * @return the armor that was worn on @bodyPosition
     */
    private Armor getArmor(EItemCategory bodyPosition, Sub sub) {
        Armor armor;
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
            default:
                throw new ItemCategoryNotFoundException();
        }
        if (armor != null) {
            sub.setDefence(sub.getDefence() - armor.getArmor());
            sub.setHealth(sub.getHealth() - armor.getHealth());
        }
        return armor;
    }

    /**
     * Adds money to sell the item to the current sub
     *
     * @param item to be its price
     * @param sub to add money
     */
    private void getMoneyForSell(Item item, Sub sub) {
        sub.getCurrency().setMoney(Math.round(sub.getCurrency().getMoney() + (item.getPrice() * 0.85)));
    }


    /**
     * Set armor for current body position if the sub has already pulled the armor to the position
     * where the new one is installed, the old armor is changed to the new one
     * and the characteristics are also
     *
     * @param sub to obtain the actual state of the armor on the sub
     * @param armor new item to be set
     * @param itemCategory armor position on the body
     */
    private void setArmor(Sub sub, Armor armor, EItemCategory itemCategory) {
        Armor currentArmor;
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
            default:
                throw new ItemCategoryNotFoundException();
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

    /**
     * Sets weapon for current sub
     *
     * @param sub for which weapon should be set
     * @param sword item which will be set
     */
    private void setWeapon(Sub sub, Sword sword) {
        checkDressedWeapon(sub);
        sub.getDressedItems().setSword(sword);
        sub.setAttack(sub.getAttack() + sword.getAttack());
        sword.setHasDressed(true);
        subRepository.save(sub);
    }

    /**
     * Checks if sub has dressed weapon if yes it removes the weapon and changes
     * the characteristics of the sub to the state to which they were without a weapon
     * otherwise do nothing
     *
     * @param sub to check current weapon state
     */
    private void checkDressedWeapon(Sub sub) {
        if (sub.getDressedItems().getSword() != null) {
            sub.setAttack(sub.getAttack() - sub.getDressedItems().getSword().getAttack());
            sub.getDressedItems().setSword(null);
        }
    }
}
