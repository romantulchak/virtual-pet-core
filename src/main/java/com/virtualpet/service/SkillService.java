package com.virtualpet.service;

import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SkillService {

    DamageSkillDTO createDamageSkill(DamageSkill damageSkill);
    void createDefenceSkill(DefenceSkill defenceSkill);
    void deleteSkill(long skillId, ESkillCategory skillCategory);
    List<SkillAbstract> getSkills(String page);
    void uploadImageSkill(MultipartFile file);

}
