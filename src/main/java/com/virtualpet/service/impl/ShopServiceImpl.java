package com.virtualpet.service.impl;

import com.virtualpet.exeption.ItemNotFoundException;
import com.virtualpet.exeption.SkillAlreadyExistException;
import com.virtualpet.exeption.SkillNotFoundException;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import com.virtualpet.repository.*;
import com.virtualpet.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final DamageSkillRepository damageSkillRepository;
    private final DefenceSkillRepository defenceSkillRepository;
    private final SwordRepository swordRepository;
    private final ArmorRepository armorRepository;
    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, DamageSkillRepository damageSkillRepository, DefenceSkillRepository defenceSkillRepository, SwordRepository swordRepository, ArmorRepository armorRepository){
        this.shopRepository = shopRepository;
        this.damageSkillRepository = damageSkillRepository;
        this.defenceSkillRepository = defenceSkillRepository;
        this.swordRepository = swordRepository;
        this.armorRepository = armorRepository;
    }

    @Override
    public Shop getShop() {
        return shopRepository.findAll().stream().findFirst().orElse(createShop());
    }

    private Shop createShop(){
        Shop shop = new Shop();
        shopRepository.save(shop);
        return shop;
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
        }else {
            throw new SkillNotFoundException();
        }
    }

    private void addDamageSkill(String skillName, ESkillCategory skillCategory){
        DamageSkill damageSkill = damageSkillRepository.findDamageSkillByNameAndSkillCategory(skillName, skillCategory).orElseThrow(SkillNotFoundException::new);
        Shop shop = getShop();
        if(!shop.getDamageSkills().contains(damageSkill)) {
            shop.getDamageSkills().add(damageSkill);
            damageSkill.setShop(shop);
            shopRepository.save(shop);
            damageSkillRepository.save(damageSkill);
        }else {
            throw new SkillAlreadyExistException(damageSkill.getName());
        }
    }
    private void addDefenceSkill(String skillName, ESkillCategory skillCategory){
        DefenceSkill defenceSkill = defenceSkillRepository.findDefenceSkillByNameAndSkillCategory(skillName, skillCategory).orElseThrow(SkillNotFoundException::new);
        Shop shop = getShop();
        if (!shop.getDefenceSkills().contains(defenceSkill)){
            shop.getDefenceSkills().add(defenceSkill);
            defenceSkill.setShop(shop);
            shopRepository.save(shop);
            defenceSkillRepository.save(defenceSkill);
        }else {
            throw new SkillAlreadyExistException(defenceSkill.getName());
        }

    }

    @Override
    public void addItemToShop(Item item) {
        if (item != null){
            if(item.geteItemCategory() == EItemCategory.SWORD){
                addSwordToShop(item.getId());
            }else {
                addArmorToShop(item.getId());
            }
        }else {
            throw new ItemNotFoundException();
        }
    }


    private void addSwordToShop(long itemId){
        Sword sword = swordRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = getShop();
        shop.getItemSwords().add(sword);
        shopRepository.save(shop);
    }

    private void addArmorToShop(long itemId){
        Armor armor = armorRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = getShop();
        shop.getItemArmors().add(armor);
        shopRepository.save(shop);
    }
}