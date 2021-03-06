package com.virtualpet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dto.DamageSkillDTO;
import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.service.SkillService;
import com.virtualpet.service.impl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillServiceImpl skillService;
    @Autowired
    public SkillController(SkillServiceImpl skillService){
        this.skillService = skillService;
    }

    @PostMapping("/createDamageSkill")
    @PreAuthorize("hasRole('ADMIN')")
    public DamageSkillDTO createDamageSkill(@RequestBody DamageSkill damageSkill){
        return skillService.createDamageSkill(damageSkill);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.SkillView.class)
    public List<SkillAbstract> getSkills(){
        return skillService.getSkills();
    }

    @DeleteMapping("/{id}/{skillCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSkill(@PathVariable("id") long id, @PathVariable("skillCategory")ESkillCategory eSkillCategory){
        skillService.deleteSkill(id, eSkillCategory);
    }
    @PostMapping("/uploadSkillImage")
    @PreAuthorize("hasRole('ADMIN')")
    public void uploadSkillImage(@RequestBody MultipartFile file){
        skillService.uploadImageSkill(file);
    }
}
