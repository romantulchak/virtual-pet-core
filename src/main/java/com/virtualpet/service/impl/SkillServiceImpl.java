package com.virtualpet.service.impl;

import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.dto.DefenceSkillDTO;
import com.virtualpet.exeption.SkillAlreadyExistException;
import com.virtualpet.exeption.SkillNotFoundException;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import com.virtualpet.repository.DamageSkillRepository;
import com.virtualpet.repository.DefenceSkillRepository;
import com.virtualpet.service.SkillService;
import com.virtualpet.utils.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    private final DamageSkillRepository damageSkillRepository;
    private final DefenceSkillRepository defenceSkillRepository;

    @Value("${upload.path}")
    private String path;
    private String skillImage;
    @Autowired
    public SkillServiceImpl(DamageSkillRepository damageSkillRepository, DefenceSkillRepository defenceSkillRepository){
        this.damageSkillRepository = damageSkillRepository;
        this.defenceSkillRepository = defenceSkillRepository;
    }




    @Override
    public DamageSkillDTO createDamageSkill(DamageSkill damageSkill) {
         if(damageSkill != null){
             if (!damageSkillRepository.existsByName(damageSkill.getName())) {
                 damageSkill.setSkillCategory(ESkillCategory.PHYS_DAMAGE);
                 damageSkill.setSkillImage(skillImage);
                 damageSkillRepository.save(damageSkill);
                 return new DamageSkillDTO(damageSkill);
             }else {
                 throw new SkillAlreadyExistException(damageSkill.getName());
             }
         }else {
             throw new SkillNotFoundException();
         }
    }

    @Override
    public List<SkillAbstract> getSkills() {
        List<DamageSkillDTO> damageSkills = damageSkillRepository.allOrderByDesc().stream().map(this::convertToDamageSkillDTO).collect(Collectors.toList());
        List<DefenceSkillDTO> defenceSkills = defenceSkillRepository.allOrderByDesc().stream().map(this::convertToDefenceSkillDTO).collect(Collectors.toList());
        return new ArrayList<SkillAbstract>(){{
            addAll(damageSkills);
            addAll(defenceSkills);
        }};
    }
    @Override
    public void uploadImageSkill(MultipartFile file) {
        if (file != null){
            skillImage = FileSaver.saveFile(file, path, "skillImage");
        }else {
            throw new RuntimeException("File not found");
        }
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
            case DEFENCE:
                skillAbstract = defenceSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
                defenceSkillRepository.delete((DefenceSkill) skillAbstract);
                break;
            case MONEY:
                break;
        }
    }

    private DamageSkillDTO convertToDamageSkillDTO(DamageSkill damageSkill){
        return new DamageSkillDTO(damageSkill);
    }
    private DefenceSkillDTO convertToDefenceSkillDTO(DefenceSkill defenceSkill){
        return new DefenceSkillDTO(defenceSkill);
    }
}
