package com.virtualpet.service.impl;

import com.virtualpet.exeption.SkillAlreadyExistException;
import com.virtualpet.exeption.SkillNotFoundException;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import com.virtualpet.repository.DamageSkillRepository;
import com.virtualpet.repository.DefenceSkillRepository;
import com.virtualpet.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private final DamageSkillRepository damageSkillRepository;
    private final DefenceSkillRepository defenceSkillRepository;
    @Autowired
    public SkillServiceImpl(DamageSkillRepository damageSkillRepository, DefenceSkillRepository defenceSkillRepository){
        this.damageSkillRepository = damageSkillRepository;
        this.defenceSkillRepository = defenceSkillRepository;
    }




    @Override
    public void createDamageSkill(DamageSkill damageSkill) {
         if(damageSkill != null){
             if (!damageSkillRepository.existsByName(damageSkill.getName())) {
                 damageSkill.setSkillCategory(ESkillCategory.PHYS_DAMAGE);
                 damageSkillRepository.save(damageSkill);
             }else {
                 throw new SkillAlreadyExistException(damageSkill.getName());
             }
         }else {
             throw new SkillNotFoundException();
         }
    }

    @Override
    public List<SkillAbstract> getSkills() {
        List<DamageSkill> damageSkills = damageSkillRepository.findAll();
        List<DefenceSkill> defenceSkills = defenceSkillRepository.findAll();
        return new ArrayList<SkillAbstract>(){{
            addAll(damageSkills);
            addAll(defenceSkills);
        }};
    }

    @Override
    public void createDefenceSkill(DefenceSkill defenceSkill) {
        if (defenceSkill != null) {
            if (!defenceSkillRepository.existsByName(defenceSkill.getName())) {
                defenceSkill.setSkillCategory(ESkillCategory.DEFENCE);
                defenceSkillRepository.save(defenceSkill);
            } else {
                throw new SkillAlreadyExistException(defenceSkill.getName());
            }
        } else {
            throw new SkillNotFoundException();

        }
    }

    @Override
    public void deleteSkill(long skillId, ESkillCategory skillCategory) {
        SkillAbstract skillAbstract;
        switch (skillCategory){
            case PHYS_DAMAGE:
                skillAbstract = damageSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
                damageSkillRepository.delete((DamageSkill) skillAbstract);
                break;
            case MAG_DAMAGE:
                break;
            case DEFENCE:
                skillAbstract = defenceSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
                defenceSkillRepository.delete((DefenceSkill) skillAbstract);
                break;
            case MONEY:
                break;
        }
    }
}
