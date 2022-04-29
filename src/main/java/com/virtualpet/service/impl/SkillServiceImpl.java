package com.virtualpet.service.impl;

import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.dto.DefenceSkillDTO;
import com.virtualpet.dto.SkillAbstractDTO;
import com.virtualpet.exeption.skill.SkillAlreadyExistException;
import com.virtualpet.exeption.skill.SkillNotFoundException;
import com.virtualpet.model.skills.SkillAbstract;
import com.virtualpet.model.Sub;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import com.virtualpet.repository.DamageSkillRepository;
import com.virtualpet.repository.DefenceSkillRepository;
import com.virtualpet.repository.SkillRepository;
import com.virtualpet.repository.SubRepository;
import com.virtualpet.service.ShopService;
import com.virtualpet.service.SkillService;
import com.virtualpet.utils.AppHelper;
import com.virtualpet.utils.FileSaver;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final DamageSkillRepository damageSkillRepository;
    private final DefenceSkillRepository defenceSkillRepository;
    private final ShopService shopService;
    private final SkillRepository skillRepository;
    private final SubRepository subRepository;
    private final ModelMapper modelMapper;

    @Value("${upload.path}")
    private String path;
    private String skillImage;

    /**
     * {@inheritDoc}
     */
    @Override
    public DamageSkillDTO createDamageSkill(DamageSkill damageSkill) {
         if(damageSkill != null){
             if (!skillRepository.existsByName(damageSkill.getName())) {
                 damageSkill.setCategory(ESkillCategory.PHYS_DAMAGE);
                 damageSkill.setIcon(skillImage);
                 damageSkill.setReference(UUID.randomUUID());
                 damageSkill.setShop(shopService.getShop());
                 skillRepository.save(damageSkill);
                 return new DamageSkillDTO(damageSkill);
             }else {
                 throw new SkillAlreadyExistException(damageSkill.getName());
             }
         }else {
             throw new SkillNotFoundException();
         }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SkillAbstractDTO> getSkills(String page) {
        Pageable pageable = PageRequest.of(AppHelper.getCurrentPage(page), 10);
        return skillRepository.findAllBySubIsNull(pageable)
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

    /**
     * {@inheritDoc}
     */
    @Override
    public DefenceSkillDTO createDefenceSkill(DefenceSkill defenceSkill) {
        if (defenceSkill != null) {
            if (!defenceSkillRepository.existsByName(defenceSkill.getName())) {
                defenceSkill.setCategory(ESkillCategory.DEFENCE);
                defenceSkill.setReference(UUID.randomUUID());
                defenceSkillRepository.save(defenceSkill);
                return new DefenceSkillDTO();
            } else {
                throw new SkillAlreadyExistException(defenceSkill.getName());
            }
        } else {
            throw new SkillNotFoundException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSkill(long skillId, ESkillCategory skillCategory) {
        switch (skillCategory){
            case PHYS_DAMAGE:
                DamageSkill damageSkill = damageSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
//                returnSkillPrice(damageSkill.getPrice(), damageSkill.getSubs());//TODO: fix it
                damageSkillRepository.delete(damageSkill);
                break;
            case DEFENCE:
                DefenceSkill defenceSkill = defenceSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
//                returnSkillPrice(defenceSkill.getPrice(), defenceSkill.getSubs());
                defenceSkillRepository.delete(defenceSkill);
                break;
            case MONEY:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SkillAbstractDTO> getSkillsInShopForSub(long subId, String page) {
        List<UUID> subSkillReferences = skillRepository.findSubSkillReferences(subId);
        Pageable pageable = PageRequest.of(AppHelper.getCurrentPage(page), 10);
        return skillRepository.findAllBySubIdIsNullAndReferenceNotInOrReferenceIsNull(subSkillReferences, pageable)
                .getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gives the opportunity to return the money for the acquired skill in case of his removal from the game
     *
     * @param skillPrice to be returned to sub
     * @param subs subs that have this skill bought
     */
    private void returnSkillPrice(long skillPrice, List<Sub> subs){
        subs.forEach(sub -> {
            sub.getCurrency().setMoney(sub.getCurrency().getMoney() + skillPrice);
            subRepository.save(sub);
        });
    }

    /**
     * Converts skills to DTO depends on the category (type)
     *
     * @param skillAbstract to be converted to DTO
     * @return DTO of skill {@link DamageSkillDTO} or {@link DefenceSkillDTO}
     */
    private SkillAbstractDTO convertToDTO(SkillAbstract skillAbstract){
        SkillAbstractDTO skill;
        if (skillAbstract instanceof DamageSkill){
            skill = modelMapper.map(skillAbstract, DamageSkillDTO.class);
        }else{
            skill = modelMapper.map(skillAbstract, DefenceSkillDTO.class);
        }
        return skill;
    }
}
