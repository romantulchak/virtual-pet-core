package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.virtualpet.dto.skill.template.SkillAbstractTemplateDTO;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.service.skill.SkillTemplateService;
import com.virtualpet.service.impl.SkillServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillServiceImpl skillService;
    private final SkillTemplateService skillTemplateService;

    @PostMapping("/create-damage-skill")
    @PreAuthorize("hasRole('ADMIN')")
    public SkillAbstractTemplateDTO createDamageSkill(@RequestPart(value = "damageSkill") String damageSkill, @RequestPart(value = "image") MultipartFile image) throws JsonProcessingException {
        return skillTemplateService.createDamageSkillTemplate(damageSkill, image);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.SkillView.class)
    public List<SkillAbstractTemplateDTO> getSkills(@RequestParam("page") String page) {
        return skillTemplateService.getSkills(page);
    }

    @GetMapping("/shop-skills/{subId}")
    @PreAuthorize("isAuthenticated()")
    @JsonView(Views.SkillView.class)
    public List<SkillAbstractTemplateDTO> getSkillsInShopForSub(@PathVariable("subId") long subId, @RequestParam("page") String page) {
        return skillService.getSkillsInShopForSub(subId, page);
    }

    @DeleteMapping("/{id}/{skillCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSkill(@PathVariable("id") long id, @PathVariable("skillCategory") ESkillCategory eSkillCategory) {
        skillTemplateService.deleteSkill(id, eSkillCategory);
    }
}
