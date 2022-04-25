package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.dto.SkillAbstractDTO;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
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

    @PostMapping("/createDamageSkill")
    @PreAuthorize("hasRole('ADMIN')")
    public DamageSkillDTO createDamageSkill(@RequestBody DamageSkill damageSkill) {
        return skillService.createDamageSkill(damageSkill);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.SkillView.class)
    public List<SkillAbstractDTO> getSkills(@RequestParam("page") String page) {
        return skillService.getSkills(page);
    }

    @GetMapping("/shop-skills/{subId}")
    @PreAuthorize("isAuthenticated()")
    @JsonView(Views.SkillView.class)
    public List<SkillAbstractDTO> getSkillsInShopForSub(@PathVariable("subId") long subId, @RequestParam("page") String page) {
        return skillService.getSkillsInShopForSub(subId, page);
    }


    @DeleteMapping("/{id}/{skillCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSkill(@PathVariable("id") long id, @PathVariable("skillCategory") ESkillCategory eSkillCategory) {
        skillService.deleteSkill(id, eSkillCategory);
    }

    @PostMapping("/uploadSkillImage")
    @PreAuthorize("hasRole('ADMIN')")
    public void uploadSkillImage(@RequestBody MultipartFile file) {
        skillService.uploadImageSkill(file);
    }
}
