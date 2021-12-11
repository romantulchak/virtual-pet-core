package com.virtualpet.service.impl;

import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.dto.DefenceSkillDTO;
import com.virtualpet.exeption.SkillAlreadyExistException;
import com.virtualpet.exeption.SkillNotFoundException;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import com.virtualpet.repository.DamageSkillRepository;
import com.virtualpet.repository.DefenceSkillRepository;
import com.virtualpet.repository.SkillRepository;
import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.SkillService;
import com.virtualpet.utils.AppHelper;
import com.virtualpet.utils.FileSaver;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final DamageSkillRepository damageSkillRepository;
    private final DefenceSkillRepository defenceSkillRepository;
    private final SkillRepository skillRepository;
    private final SubRepository subRepository;
    private final ModelMapper modelMapper;

    @Value("${upload.path}")
    private String path;
    private String skillImage;

    @Override
    public DamageSkillDTO createDamageSkill(DamageSkill damageSkill) {
         if(damageSkill != null){
             if (!damageSkillRepository.existsByName(damageSkill.getName())) {
                 damageSkill.setSkillCategory(ESkillCategory.PHYS_DAMAGE);
                 damageSkill.setIcon(skillImage);
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
    public List<SkillAbstract> getSkills(String page) {
        Pageable pageable = PageRequest.of(AppHelper.getCurrentPage(page), 10);
        return skillRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
        switch (skillCategory){
            case PHYS_DAMAGE:
                DamageSkill damageSkill = damageSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
                returnSkillPrice(damageSkill.getPrice(), damageSkill.getSubs());
                damageSkillRepository.delete(damageSkill);
                break;
            case DEFENCE:
                DefenceSkill defenceSkill = defenceSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
                returnSkillPrice(defenceSkill.getPrice(), defenceSkill.getSubs());
                defenceSkillRepository.delete(defenceSkill);
                break;
            case MONEY:
                break;
        }
    }
    private void returnSkillPrice(long skillPrice, List<Sub> subs){
        subs.forEach(sub -> {
            sub.getCurrency().setMoney(sub.getCurrency().getMoney() + skillPrice);
            subRepository.save(sub);
        });
    }

    private SkillAbstract convertToDTO(SkillAbstract skillAbstract){
        SkillAbstract sk;
        if (skillAbstract instanceof DamageSkill){
            sk = modelMapper.map(skillAbstract, DamageSkillDTO.class);
        }else{
            sk = modelMapper.map(skillAbstract, DefenceSkill.class);
        }
        System.out.println(sk);
        return sk;
    }

    private DamageSkillDTO convertToDefenceSkillDTO(DefenceSkill damageSkill){
        DamageSkillDTO map = modelMapper.map(damageSkill, DamageSkillDTO.class);
        System.out.println(map);
        return map;
    }


//    private DamageSkillDTO convertToDamageSkillDTO(DamageSkill damageSkill){
//        return new DamageSkillDTO(damageSkill);
//    }
//    private DefenceSkillDTO convertToDefenceSkillDTO(DefenceSkill defenceSkill){
//        return new DefenceSkillDTO(defenceSkill);
//    }
}
