package com.virtualpet.service.skill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.virtualpet.dto.skill.DamageSkillDTO;
import com.virtualpet.dto.skill.DefenceSkillDTO;
import com.virtualpet.dto.skill.template.SkillAbstractTemplateDTO;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.template.DefenceSkillTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SkillTemplateService {

    /**
     * Creates DamageSkill with described characteristics
     *
     * @param damageSkillJson that will be created in system
     * @param image skill icon
     * @return {@link DamageSkillDTO}
     */
    SkillAbstractTemplateDTO createDamageSkillTemplate(String damageSkillJson, MultipartFile image) throws JsonProcessingException;

    /**
     * Creates DefenceSkill with described characteristics
     *
     * @param defenceSkillTemplate that will be created in system
     * @return {@link DefenceSkillDTO}
     */
    SkillAbstractTemplateDTO createDefenceSkill(DefenceSkillTemplate defenceSkillTemplate);

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
    List<SkillAbstractTemplateDTO> getSkills(String page);
}
