package com.virtualpet.service.impl;

import com.virtualpet.dto.ShopDTO;
import com.virtualpet.exeption.InventoryNotFoundException;
import com.virtualpet.exeption.NotEnoughMoneyException;
import com.virtualpet.exeption.ShopNotFoundException;
import com.virtualpet.exeption.item.ItemAlreadyBoughtException;
import com.virtualpet.exeption.item.ItemNotFoundException;
import com.virtualpet.exeption.skill.SkillAlreadyBoughtException;
import com.virtualpet.exeption.skill.SkillAlreadyExistException;
import com.virtualpet.exeption.skill.SkillNotFoundException;
import com.virtualpet.exeption.sub.SubNotFoundException;
import com.virtualpet.model.*;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.SkillAbstract;
import com.virtualpet.repository.*;
import com.virtualpet.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final DamageSkillRepository damageSkillRepository;
    private final DefenceSkillRepository defenceSkillRepository;
    private final SkillRepository skillRepository;
    private final SwordRepository swordRepository;
    private final ArmorRepository armorRepository;
    private final SubRepository subRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public ShopDTO getShopForSub(Sub sub) {
        Shop shop = getShop();
        if (shop != null) {
            return convertShopToDTO(shop, sub);
        }
        throw new ShopNotFoundException();
    }

    @Override
    public Shop getShop() {
        return shopRepository.findFirstByOrderById().orElseThrow(ShopNotFoundException::new);
    }

    @Override
    public void addSkillToShop(SkillAbstract skillAbstract) {
        if (skillAbstract != null) {
            DamageSkill damageSkill = damageSkillRepository.findDamageSkillByNameAndCategory(skillAbstract.getName(), skillAbstract.getCategory())
                    .orElseThrow(SkillNotFoundException::new);
            Shop shop = getShop();
            if (!shop.getSkills().contains(damageSkill)) {
                shop.getSkills().add(damageSkill);
                damageSkill.setShop(shop);
                shopRepository.save(shop);
                damageSkillRepository.save(damageSkill);
            } else {
                throw new SkillAlreadyExistException(damageSkill.getName());
            }
        } else {
            throw new SkillNotFoundException();
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
            skillRepository.delete(skillAbstract);
        } else {
            throw new SkillNotFoundException();
        }
    }

    private void addSwordToShop(long itemId) {
        Sword sword = swordRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = getShop();
        shop.getItemSwords().add(sword);
        shopRepository.save(shop);
    }

    private void addArmorToShop(long itemId) {
        Armor armor = armorRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = getShop();
        shop.getItemArmors().add(armor);
        shopRepository.save(shop);
    }

    @Override
    public void buyItem(Item item, long subId) {
        if (item != null) {
            Sub sub = subRepository.findById(subId).orElseThrow(SubNotFoundException::new);
            checkEnoughMoney(sub, item.getPrice());
            if (!sub.getInventory().getItems().contains(item)) {
                Inventory inventory = inventoryRepository.findById(sub.getInventory().getId()).orElseThrow(() -> new InventoryNotFoundException(sub.getName()));
                addItemToInventory(item, inventory);
                sub.getCurrency().setMoney(sub.getCurrency().getMoney() - item.getPrice());
                subRepository.save(sub);
            } else {
                throw new ItemAlreadyBoughtException(item.getName());
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Transactional
    @Override
    public void buySkill(long id, long subId) {
        SkillAbstract skill = skillRepository.findByIdAndShopNotNull(id)
                .orElseThrow(SkillNotFoundException::new);
        if (skillRepository.existsByReferenceAndSubId(skill.getReference(), subId)) {
            throw new SkillAlreadyBoughtException();
        }
        Sub sub = subRepository.findById(subId).orElseThrow(SubNotFoundException::new);
        checkEnoughMoney(sub, skill.getPrice());
        SkillAbstract boughtSkill = getBoughtSkill(skill, sub);
        if (boughtSkill != null) {
            long subMoney = sub.getCurrency().getMoney() - skill.getPrice();
            subRepository.updateSubCurrency(subId, subMoney);
            skillRepository.save(boughtSkill);
        }
    }

    private SkillAbstract getBoughtSkill(SkillAbstract skill, Sub sub){
        SkillAbstract skillForSub = null;
        switch (skill.getCategory()) {
            case PHYS_DAMAGE:
                skillForSub = generateDamageSkill(skill, sub);
                break;
            case DEFENCE:
                break;
            case MONEY:
                break;
            default:
                throw new SkillNotFoundException();
        }
        return skillForSub;
    }

    private DamageSkill generateDamageSkill(SkillAbstract skillAbstract, Sub sub){
        return new DamageSkill((DamageSkill) skillAbstract, sub);
    }

    private void addItemToInventory(Item item, Inventory inventory) {
        inventory.getItems().add(item);
        inventoryRepository.save(inventory);
    }

    private ShopDTO convertShopToDTO(Shop shop, Sub sub) {
//        List<DamageSkillDTO> damageSkillDTOS = shop.gets().stream().map(x -> new DamageSkillDTO(x, sub)).collect(Collectors.toList());
//        List<DefenceSkillDTO> defenceSkillDTOS = shop.getDefenceSkills().stream().map(x -> new DefenceSkillDTO(x, sub)).collect(Collectors.toList());
//        List<SwordDTO> swordDTOS = shop.getItemSwords().stream().map(x -> new SwordDTO(x, sub)).collect(Collectors.toList());
//        List<ArmorDTO> armorDTOS = shop.getItemArmors().stream().map(x -> new ArmorDTO(x, sub)).collect(Collectors.toList());
//        return new ShopDTO(shop, damageSkillDTOS, defenceSkillDTOS, swordDTOS, armorDTOS);
        return null;
    }

    private void checkEnoughMoney(Sub sub, int itemPrice) {
        if (sub.getCurrency().getMoney() <= itemPrice) {
            throw new NotEnoughMoneyException(sub.getName(), sub.getCurrency().getMoney(), itemPrice);
        }
    }


}
