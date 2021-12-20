package com.virtualpet.service;

import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.dto.SkillAbstractDTO;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SkillService {

    DamageSkillDTO createDamageSkill(DamageSkill damageSkill);
    void createDefenceSkill(DefenceSkill defenceSkill);
    void deleteSkill(long skillId, ESkillCategory skillCategory);
    List<SkillAbstractDTO> getSkills(String page);
    void uploadImageSkill(MultipartFile file);
    List<SkillAbstractDTO> getSkillsInShopForSub(long subId, String page);

}
