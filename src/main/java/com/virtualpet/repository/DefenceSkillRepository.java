package com.virtualpet.repository;

import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.template.DefenceSkillTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefenceSkillRepository extends JpaRepository<DefenceSkillTemplate, Long> {

    boolean existsByName(String skillDefenceName);

    Optional<DefenceSkillTemplate> findDefenceSkillByNameAndCategory(String skillName, ESkillCategory skillCategory);

    @Query("SELECT d FROM DefenceSkillTemplate d ORDER BY d.id DESC ")
    List<DefenceSkillTemplate> allOrderByDesc();
}
