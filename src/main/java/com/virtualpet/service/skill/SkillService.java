package com.virtualpet.service.skill;

import com.virtualpet.dto.skill.template.SkillAbstractTemplateDTO;

import java.util.List;

public interface SkillService {

    /**
     * Gets list of available skill in shop for sub.
     * If sub has already bought skill this skill won't be displayed in shop
     *
     * @param subId to get current sub
     * @param page to pageable object
     * @return list of available skill in shop for sub
     */
    List<SkillAbstractTemplateDTO> getSkillsInShopForSub(long subId, String page);

}
