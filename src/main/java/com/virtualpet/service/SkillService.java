package com.virtualpet.service;

import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.skills.DamageSkill;

import java.util.List;

public interface SkillService {

    void createDamageSkill(DamageSkill damageSkill);
    void deleteDamage(long skillId);
    List<SkillAbstract> getSkills();

}
