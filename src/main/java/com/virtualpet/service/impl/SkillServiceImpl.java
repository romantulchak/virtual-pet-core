package com.virtualpet.service.impl;

import com.virtualpet.dto.skill.template.SkillAbstractTemplateDTO;
import com.virtualpet.model.Sub;
import com.virtualpet.repository.*;
import com.virtualpet.repository.skill.SkillRepository;
import com.virtualpet.repository.skill.SkillTemplateRepository;
import com.virtualpet.service.skill.SkillService;
import com.virtualpet.transformer.Transformer;
import com.virtualpet.utils.AppHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillTemplateRepository skillTemplateRepository;
    private final SubRepository subRepository;
    private final Transformer transformer;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SkillAbstractTemplateDTO> getSkillsInShopForSub(long subId, String page) {
        List<UUID> subSkillReferences = skillRepository.findSubSkillReferences(subId);
        Pageable pageable = PageRequest.of(AppHelper.getCurrentPage(page), 10);
        if (subSkillReferences.isEmpty()){
            return skillTemplateRepository.findAll(pageable)
                    .getContent()
                    .stream()
                    .map(transformer::getSkillTemplateDTO)
                    .collect(Collectors.toList());
        }
        return skillTemplateRepository.findAllByReferenceNotInOrReferenceIsNull(subSkillReferences, pageable)
                .getContent()
                .stream()
                .map(transformer::getSkillTemplateDTO)
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
}
