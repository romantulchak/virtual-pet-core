package com.virtualpet.service;

import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.dto.DefenceSkillDTO;
import com.virtualpet.dto.SkillAbstractDTO;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SkillService {

    /**
     * Creates DamageSkill with described characteristics
     *
     * @param damageSkill that will be created in system
     * @return {@link DamageSkillDTO}
     */
    DamageSkillDTO createDamageSkill(DamageSkill damageSkill);

    /**
     * Creates DefenceSkill with described characteristics
     *
     * @param defenceSkill that will be created in system
     * @return {@link DefenceSkillDTO}
     */
    DefenceSkillDTO createDefenceSkill(DefenceSkill defenceSkill);

    /**
     * Removes a skill from the game and returns the money to the subs who acquired the skill
     *
     * @param skillId that will be deleted
     * @param skillCategory category of skill
     */
    void deleteSkill(long skillId, ESkillCategory skillCategory);

    /**
     * Gets list of skills in game
     *
     * @param page for pageable object
     * @return list of skills in game
     */
    List<SkillAbstractDTO> getSkills(String page);

    @Deprecated(forRemoval = true)
    /**
     * To be reworked to upload image together with creation
     */
    void uploadImageSkill(MultipartFile file);

    /**
     * Gets list of available skill in shop for sub.
     * If sub has already bought skill this skill won't be displayed in shop
     *
     * @param subId to get current sub
     * @param page to pageable object
     * @return list of available skill in shop for sub
     */
    List<SkillAbstractDTO> getSkillsInShopForSub(long subId, String page);

}
