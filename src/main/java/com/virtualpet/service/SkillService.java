package com.virtualpet.service;

import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;

import java.util.List;

public interface SkillService {

    void createDamageSkill(DamageSkill damageSkill);
    void createDefenceSkill(DefenceSkill defenceSkill);
    void deleteSkill(long skillId, ESkillCategory skillCategory);
    List<SkillAbstract> getSkills();


}
