package com.virtualpet.service.impl;

import com.virtualpet.dto.*;
import com.virtualpet.exeption.*;
import com.virtualpet.model.*;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import com.virtualpet.repository.*;
import com.virtualpet.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final DamageSkillRepository damageSkillRepository;
    private final DefenceSkillRepository defenceSkillRepository;
    private final SwordRepository swordRepository;
    private final ArmorRepository armorRepository;
    private final SubRepository subRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public ShopDTO getShopForSub(Sub sub) {
        Shop shop = shopRepository.findFirstByOrderById().orElse(null);
        if (shop != null) {
            return convertShopToDTO(shop, sub);
        }
        throw new ShopNotFoundException();
    }

    private Shop shop() {
        return shopRepository.findFirstByOrderById().orElse(null);
    }

    @Override
    public void addSkillToShop(SkillAbstract skillAbstract) {
        if (skillAbstract != null) {
            switch (skillAbstract.getSkillCategory()) {
                case PHYS_DAMAGE:
                    addDamageSkill(skillAbstract.getName(), skillAbstract.getSkillCategory());
                    break;
                case DEFENCE:
                    addDefenceSkill(skillAbstract.getName(), skillAbstract.getSkillCategory());
                    break;
                case MONEY:
                    break;
            }
        } else {
            throw new SkillNotFoundException();
        }
    }

    private void addDamageSkill(String skillName, ESkillCategory skillCategory) {
        DamageSkill damageSkill = damageSkillRepository.findDamageSkillByNameAndSkillCategory(skillName, skillCategory).orElseThrow(SkillNotFoundException::new);
        Shop shop = shop();
        if (!shop.getDamageSkills().contains(damageSkill)) {
            shop.getDamageSkills().add(damageSkill);
            damageSkill.setShop(shop);
            shopRepository.save(shop);
            damageSkillRepository.save(damageSkill);
        } else {
            throw new SkillAlreadyExistException(damageSkill.getName());
        }
    }

    private void addDefenceSkill(String skillName, ESkillCategory skillCategory) {
        DefenceSkill defenceSkill = defenceSkillRepository.findDefenceSkillByNameAndSkillCategory(skillName, skillCategory).orElseThrow(SkillNotFoundException::new);
        Shop shop = shop();
        if (!shop.getDefenceSkills().contains(defenceSkill)) {
            shop.getDefenceSkills().add(defenceSkill);
            defenceSkill.setShop(shop);
            shopRepository.save(shop);
            defenceSkillRepository.save(defenceSkill);
        } else {
            throw new SkillAlreadyExistException(defenceSkill.getName());
        }

    }

    @Override
    public void addItemToShop(Item item) {
        if (item != null) {
            if (item.getItemCategory() == EItemCategory.SWORD) {
                addSwordToShop(item.getId());
            } else {
                addArmorToShop(item.getId());
            }
        } else {
            throw new ItemNotFoundException();
        }
    }


    @Override
    public void removeSkillFromShop(SkillAbstract skillAbstract) {
        if (skillAbstract != null) {
            switch (skillAbstract.getSkillCategory()) {
                case PHYS_DAMAGE:
                    removeDamageSkillFromShop((DamageSkill) skillAbstract);
                    break;
                case DEFENCE:
                    removeDefenceSkillFromShop((DefenceSkill) skillAbstract);
                    break;
                case MONEY:
                    break;
            }
        } else {
            throw new SkillNotFoundException();
        }
    }

    private void addSwordToShop(long itemId) {
        Sword sword = swordRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = shop();
        shop.getItemSwords().add(sword);
        shopRepository.save(shop);
    }

    private void addArmorToShop(long itemId) {
        Armor armor = armorRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = shop();
        shop.getItemArmors().add(armor);
        shopRepository.save(shop);
    }

    private void removeDamageSkillFromShop(DamageSkill damageSkill) {
        Shop shop = shop();
        if (!shop.getDamageSkills().remove(damageSkill))
            throw new SkillNotFoundException();
        shopRepository.save(shop);
        damageSkill.setShop(null);
        damageSkillRepository.save(damageSkill);
    }

    private void removeDefenceSkillFromShop(DefenceSkill defenceSkill) {
        Shop shop = shop();
        if (!shop.getDefenceSkills().remove(defenceSkill))
            throw new SkillNotFoundException();
        shopRepository.save(shop);
        defenceSkill.setShop(null);
        defenceSkillRepository.save(defenceSkill);
    }

    @Override
    public void buyItem(Item item, long subId) {
        if (item != null) {
            Sub sub = subRepository.findById(subId).orElseThrow(SubNotFoundException::new);
            if (checkEnoughMoney(sub, item.getPrice())) {
                if (!sub.getInventory().getItems().contains(item) && !sub.getInventory().getItems().contains(item)) {
                    Inventory inventory = inventoryRepository.findById(sub.getInventory().getId()).orElseThrow(() -> new InventoryNotFoundException(sub.getName()));
                    addItemToInventory(item, inventory);
                    sub.getCurrency().setMoney(sub.getCurrency().getMoney() - item.getPrice());
                    subRepository.save(sub);
                } else {
                    throw new ItemAlreadyBoughtException(item.getName());
                }
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public SubDTO buySkill(SkillAbstract skill, long subId) {
        if (skill != null) {
            Sub sub = subRepository.findById(subId).orElseThrow(SubNotFoundException::new);
            if (checkEnoughMoney(sub, skill.getPrice())) {
                if (!sub.getDefenceSkills().contains(skill) && !sub.getDamageSkills().contains(skill)) {
                    addSkillToSub(sub, skill);
                    sub.getCurrency().setMoney(sub.getCurrency().getMoney() - skill.getPrice());
                    subRepository.save(sub);
                    return new SubDTO(sub);
                } else {
                    throw new SkillAlreadyBoughtException(sub.getName(), skill.getName());
                }
            }
        }
        throw new SkillNotFoundException();
    }

    private void addSkillToSub(Sub sub, SkillAbstract skill) {
        if (skill instanceof DamageSkill) {
            sub.getDamageSkills().add((DamageSkill) skill);
        } else if (skill instanceof DefenceSkill) {
            sub.getDefenceSkills().add((DefenceSkill) skill);
        }
    }

    private void addItemToInventory(Item item, Inventory inventory) {
        inventory.getItems().add(item);
        inventoryRepository.save(inventory);
    }

    private ShopDTO convertShopToDTO(Shop shop, Sub sub) {
        List<DamageSkillDTO> damageSkillDTOS = shop.getDamageSkills().stream().map(x -> new DamageSkillDTO(x, sub)).collect(Collectors.toList());
        List<DefenceSkillDTO> defenceSkillDTOS = shop.getDefenceSkills().stream().map(x -> new DefenceSkillDTO(x, sub)).collect(Collectors.toList());
        List<SwordDTO> swordDTOS = shop.getItemSwords().stream().map(x -> new SwordDTO(x, sub)).collect(Collectors.toList());
        List<ArmorDTO> armorDTOS = shop.getItemArmors().stream().map(x -> new ArmorDTO(x, sub)).collect(Collectors.toList());
        return new ShopDTO(shop, damageSkillDTOS, defenceSkillDTOS, swordDTOS, armorDTOS);
    }

    private boolean checkEnoughMoney(Sub sub, int itemPrice) {
        if (sub.getCurrency().getMoney() >= itemPrice) {
            return true;
        }
        throw new NotEnoughMoneyException(sub.getName(), sub.getCurrency().getMoney(), itemPrice);
    }


}
