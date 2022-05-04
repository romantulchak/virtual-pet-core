package com.virtualpet.repository;

import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.template.DamageSkillTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DamageSkillRepository extends JpaRepository<DamageSkillTemplate, Long> {
    boolean existsByName(String skillDamageName);

    @Query("SELECT d FROM DamageSkillTemplate d ORDER BY d.id DESC")
    List<DamageSkillTemplate> allOrderByDesc();

    Optional<DamageSkillTemplate> findDamageSkillByNameAndCategory(String skillName, ESkillCategory eSkillCategory);
}
