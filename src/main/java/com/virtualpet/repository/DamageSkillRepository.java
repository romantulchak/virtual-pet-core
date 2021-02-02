package com.virtualpet.repository;

import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DamageSkillRepository extends JpaRepository<DamageSkill, Long> {
    boolean existsByName(String skillDamageName);

    Optional<DamageSkill> findDamageSkillByNameAndSkillCategory(String skillName, ESkillCategory eSkillCategory);
}
