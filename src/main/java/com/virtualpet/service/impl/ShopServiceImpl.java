package com.virtualpet.service.impl;

import com.virtualpet.dto.ShopDTO;
import com.virtualpet.exeption.ItemNotFoundException;
import com.virtualpet.exeption.ShopNotFoundException;
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
import com.virtualpet.utils.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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
        if(!this.shopRepository.findFirstByOrderById().isPresent()){
            createShop();
        }
    }

    @Override
    public ShopDTO getShop() {
        Shop shop = shopRepository.findFirstByOrderById().orElse(null);
        if (shop != null) {
            return new ShopDTO(shop);
        }
        throw new ShopNotFoundException();
    }
    private Shop shop(){
        return shopRepository.findFirstByOrderById().orElse(null);
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
        Shop shop = shop();
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
        Shop shop = shop();
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



    @Override
    public void removeSkillFromShop(SkillAbstract skillAbstract) {
        if (skillAbstract != null){
            switch (skillAbstract.getSkillCategory()){
                case PHYS_DAMAGE:
                    removeDamageSkillFromShop((DamageSkill) skillAbstract);
                    break;
                case DEFENCE:
                    removeDefenceSkillFromShop((DefenceSkill) skillAbstract);
                    break;
                case MONEY:
                    break;
            }
        }else {
            throw new SkillNotFoundException();
        }
    }

    private void addSwordToShop(long itemId){
        Sword sword = swordRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = shop();
        shop.getItemSwords().add(sword);
        shopRepository.save(shop);
    }

    private void addArmorToShop(long itemId){
        Armor armor = armorRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Shop shop = shop();
        shop.getItemArmors().add(armor);
        shopRepository.save(shop);
    }

    private void removeDamageSkillFromShop(DamageSkill damageSkill){
        Shop shop = shop();
        if (!shop.getDamageSkills().remove(damageSkill))
            throw new SkillNotFoundException();
        shopRepository.save(shop);
        damageSkill.setShop(null);
        damageSkillRepository.save(damageSkill);
    }

    private void removeDefenceSkillFromShop(DefenceSkill defenceSkill){
        Shop shop = shop();
        if (!shop.getDefenceSkills().remove(defenceSkill))
            throw new SkillNotFoundException();
        shopRepository.save(shop);
        defenceSkill.setShop(null);
        defenceSkillRepository.save(defenceSkill);
    }
}
