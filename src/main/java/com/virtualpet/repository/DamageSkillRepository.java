package com.virtualpet.repository;

import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DamageSkillRepository extends JpaRepository<DamageSkill, Long> {
    boolean existsByName(String skillDamageName);

    @Query("SELECT d FROM DamageSkill d ORDER BY d.id DESC")
    List<DamageSkill> allOrderByDesc();

    Optional<DamageSkill> findDamageSkillByNameAndCategory(String skillName, ESkillCategory eSkillCategory);
}
