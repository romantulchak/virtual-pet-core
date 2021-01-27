package com.virtualpet.repository;

import com.virtualpet.model.skills.DamageSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageSkillRepository extends JpaRepository<DamageSkill, Long> {
}
