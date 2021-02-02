package com.virtualpet.repository;

import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DefenceSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DefenceSkillRepository extends JpaRepository<DefenceSkill, Long> {
    boolean existsByName(String skillDefenceName);
    Optional<DefenceSkill> findDefenceSkillByNameAndSkillCategory(String skillName, ESkillCategory skillCategory);
}
