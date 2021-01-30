package com.virtualpet.controller;

import com.virtualpet.model.SkillAbstract;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.service.SkillService;
import com.virtualpet.service.impl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public void createDamageSkill(@RequestBody DamageSkill damageSkill){
        skillService.createDamageSkill(damageSkill);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<SkillAbstract> getSkills(){
        return skillService.getSkills();
    }

    @DeleteMapping("/{id}/{skillCategory}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSkill(@PathVariable("id") long id, @PathVariable("skillCategory")ESkillCategory eSkillCategory){
        skillService.deleteSkill(id, eSkillCategory);
    }
}
