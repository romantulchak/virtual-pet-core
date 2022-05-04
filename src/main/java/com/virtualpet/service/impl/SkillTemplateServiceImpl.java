package com.virtualpet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualpet.constant.AppConstants;
import com.virtualpet.dto.skill.template.SkillAbstractTemplateDTO;
import com.virtualpet.exeption.skill.SkillAlreadyExistException;
import com.virtualpet.exeption.skill.SkillNotFoundException;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.template.DamageSkillTemplate;
import com.virtualpet.model.skills.template.DefenceSkillTemplate;
import com.virtualpet.payload.request.skill.DamageSkillRequest;
import com.virtualpet.repository.skill.SkillTemplateRepository;
import com.virtualpet.service.skill.SkillTemplateService;
import com.virtualpet.transformer.Transformer;
import com.virtualpet.utils.AppHelper;
import com.virtualpet.utils.FileSaver;
import lombok.RequiredArgsConstructor;
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
public class SkillTemplateServiceImpl implements SkillTemplateService {

    private final SkillTemplateRepository skillTemplateRepository;
    private final Transformer transformer;
    @Value("${upload.path}")
    private String path;

    /**
     * {@inheritDoc}
     */
    public SkillAbstractTemplateDTO createDamageSkillTemplate(String damageSkillJson, MultipartFile image) throws JsonProcessingException {
        if(damageSkillJson != null){
            ObjectMapper objectMapper = new ObjectMapper();
            DamageSkillRequest damageSkillRequest = objectMapper.readValue(damageSkillJson, DamageSkillRequest.class);
            if (skillTemplateRepository.existsByName(damageSkillRequest.getName())) {
                String skillImagePath = FileSaver.saveFile(image, path, AppConstants.SKILL_IMAGE_FOLDER);
                DamageSkillTemplate damageSkillTemplate = new DamageSkillTemplate()
                        .setCriticalChance(damageSkillRequest.getCriticalChance())
                        .setDamage(damageSkillRequest.getDamage())
                        .setDescription(damageSkillRequest.getDescription())
                        .setMaxCooldown(damageSkillRequest.getMaxCooldown())
                        .setName(damageSkillRequest.getName())
                        .setCategory(ESkillCategory.PHYS_DAMAGE)
                        .setIcon(skillImagePath)
                        .setPrice(damageSkillRequest.getPrice())
                        .setReference(UUID.randomUUID());
                skillTemplateRepository.save(damageSkillTemplate);
                return transformer.getSkillTemplateDTO(damageSkillTemplate);
            }else {
                throw new SkillAlreadyExistException(damageSkillRequest.getName());
            }
        }else {
            throw new SkillNotFoundException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SkillAbstractTemplateDTO createDefenceSkill(DefenceSkillTemplate defenceSkillTemplate) {
        if (defenceSkillTemplate != null) {
            if (skillTemplateRepository.existsByName(defenceSkillTemplate.getName())) {
                defenceSkillTemplate.setCategory(ESkillCategory.DEFENCE);
                defenceSkillTemplate.setReference(UUID.randomUUID());
                skillTemplateRepository.save(defenceSkillTemplate);
                return null;
            } else {
                throw new SkillAlreadyExistException(defenceSkillTemplate.getName());
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
        skillTemplateRepository.deleteById(skillId);
//                returnSkillPrice(damageSkill.getPrice(), damageSkill.getSubs());//TODO: fix it
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SkillAbstractTemplateDTO> getSkills(String page) {
        Pageable pageable = PageRequest.of(AppHelper.getCurrentPage(page), 10);
        return skillTemplateRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(transformer::getSkillTemplateDTO)
                .collect(Collectors.toList());
    }
}
